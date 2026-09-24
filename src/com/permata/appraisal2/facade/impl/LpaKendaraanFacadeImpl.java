package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.dao.*;
import com.permata.appraisal2.dto.LpaItemKendaraanDto;
import com.permata.appraisal2.dto.LpaKendaraanDto;
import com.permata.appraisal2.dto.LpaUmumDto;
import com.permata.appraisal2.facade.LpaKendaraanFacade;
import com.permata.appraisal2.facade.LpaUmumFacade;
import com.permata.appraisal2.model.*;
import com.permata.appraisal2.util.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class LpaKendaraanFacadeImpl implements LpaKendaraanFacade {

    private Logger logger = Logger.getLogger(LpaKendaraanFacadeImpl.class);

    @Autowired
    private LpaKendaraanDao dao;
    @Autowired
    private DataCommonDao commonDao;
    @Autowired
    private LpaItemKendaraanDao itemDao;
    @Autowired
    private LpaUmumDao lpaUmumDao;
    @Autowired
    private SurveyKendaraanDao surveyKendaraanDao;
    @Autowired
    private TableSequenceDao sequenceDao;
    @Autowired
    private ParameterDao parameterDao;

    @Autowired
    private LpaUmumFacade facade;

    @Override
    @Transactional(readOnly = false)
    public LpaKendaraanDto save(LpaKendaraanDto dto, String userId)  throws Exception{
        if (!dto.getDraft() && StringUtils.isBlank(dto.getLpaNumber())) {
            dto.setLpaNumber(sequenceDao.lpaNextVal());
        }
        if(checkUserTasklist(userId,dto)) {
            return toWrapper(dao.save(toEntity(dto)));
        } else {
            throw new Exception("Gagal : Posisi Order Tidak Pada Tasklist Anda");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) throws Exception {
        try {
            dao.delete(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public List<LpaItemKendaraanDto> listItemKendaraan(Long idLpa) {
        List<LpaItemKendaraanDto> result = new ArrayList<LpaItemKendaraanDto>();
        if(idLpa != null) {
            LpaItemKendaraan filter = new LpaItemKendaraan(new LpaKendaraan(idLpa));
            List<LpaItemKendaraan> dataList = itemDao.find(filter);
            for (LpaItemKendaraan row : dataList) {
                result.add(toItemWrapper(row));
            }
        }
        return result;
    }

    @Override
    public LpaItemKendaraanDto getItem(Long id) {
        return toItemWrapper(itemDao.get(id));
    }

    @Override
    @Transactional(readOnly = false)
    public LpaItemKendaraanDto saveKendaraan(LpaItemKendaraanDto dto, String userId) throws Exception{
        LpaItemKendaraan lpaItemKendaraan = toItemEntity(dto);
        if(checkUserTasklist(userId,toWrapper(lpaItemKendaraan.getLpaKendaraan()))) {
            return toItemWrapper(itemDao.save(lpaItemKendaraan));
        } else {
            throw new Exception("Gagal : Posisi Order Tidak Pada Tasklist Anda");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteKendaraan(Long id) throws Exception {
        try {
            itemDao.delete(id);
        } catch (Exception e) {
            throw e;
        }
    }

    private LpaItemKendaraan toItemEntity(LpaItemKendaraanDto w) {
        LpaItemKendaraan e = new LpaItemKendaraan();
        if (w.getId() != null) {
            e = itemDao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        e.setLpaKendaraan(dao.get(w.getLpaKendaraanId()));
        return e;
    }

    private LpaItemKendaraanDto toItemWrapper(LpaItemKendaraan e) {
        LpaItemKendaraanDto w = new LpaItemKendaraanDto();
        BeanUtils.copyProperties(e, w);
        w.setLpaKendaraanId(e.getLpaKendaraan().getId());
        w.setLpaId(e.getLpaKendaraan().getLpaUmum().getId());
        return w;
    }

    @Override
    public LpaKendaraanDto getByUmum(Long lpaId) {
        return toWrapper(dao.getByUmum(lpaId));
    }

    private LpaKendaraan toEntity(LpaKendaraanDto w) {
        LpaUmum umum = lpaUmumDao.save(facade.toEntity(w));
        LpaKendaraan e = new LpaKendaraan();
        if (w.getId() != null) {
            e = dao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        e.setLpaUmum(umum);
        if (w.isCreate()) {
            /**
             * jika create baru, copy data kendaraan dari survey ke lpa
             */
            DataDetail detail = new DataDetail();
            detail.setId(w.getDetailId());
            SurveyKendaraan filter = new SurveyKendaraan();
            filter.setDetail(detail);
            List<SurveyKendaraan> listKendaraan = surveyKendaraanDao.find(filter);
            if (ListUtils.isNotEmpty(listKendaraan)) {
                for (SurveyKendaraan row : listKendaraan) {
                    LpaItemKendaraan item = new LpaItemKendaraan();
                    BeanUtils.copyProperties(row, item, "id");
                    item.setNilaiPasar(BigDecimal.ZERO);
                    item.setNilaiDokumen(BigDecimal.ZERO);
                    e.addKendaraan(item);
                }
            }
        }
        return e;
    }

    private LpaKendaraanDto toWrapper(LpaKendaraan e) {
        LpaKendaraanDto w = new LpaKendaraanDto();
        BeanUtils.copyProperties(facade.toWrapper(e.getLpaUmum()), w);
        BeanUtils.copyProperties(e, w);
        return w;
    }

    @Override
    public LpaKendaraanDto create(Long commonId) {

        DataCommon e = commonDao.get(commonId);
        DataDetail detail = e.getDataDetail();

        LpaKendaraanDto w = new LpaKendaraanDto();
        LpaUmumDto lpaUmum = facade.create(commonId);
        BeanUtils.copyProperties(lpaUmum, w);

        Double nilaiProsentase = 50d;
        List<Parameter> prosentaseLikuidasi = parameterDao.findByGroup(AppConstans.GroupParameter.PROSENTASE_LIKUIDASI);
        if (ListUtils.isNotEmpty(prosentaseLikuidasi)) {
            nilaiProsentase = Double.parseDouble(prosentaseLikuidasi.get(0).getValue());
        }
        w.setPersenLikuidasi(nilaiProsentase);
        w.setPersenLikuidasiEks(nilaiProsentase);
        w.setBeriNilaiFisik(true);
        w.setBeriNilaiDokumen(true);
        w.setBeriNilaiDokumenEks(true);
        w.setBeriNilaiFisikEks(true);

        // ambil data survey
        if (ListUtils.isNotEmpty(detail.getKendaraan())) {
            for (SurveyKendaraan row : detail.getKendaraan()) {
                LpaItemKendaraanDto kendaraanDto = new LpaItemKendaraanDto();
                BeanUtils.copyProperties(row, kendaraanDto);
                w.addKendaraan(kendaraanDto);
            }
        }

        return w;
    }

    private Boolean checkUserTasklist(String userId, LpaKendaraanDto dto) {
        DataCommon e = commonDao.get(dto.getCommonId());
        return StringUtils.equalsIgnoreCase(e.getTaskList(),userId);
    }
}