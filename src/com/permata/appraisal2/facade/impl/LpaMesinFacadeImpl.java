package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.dao.*;
import com.permata.appraisal2.dto.LpaMesinDto;
import com.permata.appraisal2.dto.LpaMesinLampiranDto;
import com.permata.appraisal2.dto.LpaUmumDto;
import com.permata.appraisal2.facade.LpaMesinFacade;
import com.permata.appraisal2.facade.LpaUmumFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.*;
import com.permata.appraisal2.util.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class LpaMesinFacadeImpl extends FacadeBase<LpaMesinDto, LpaMesin> implements LpaMesinFacade {

    @Autowired
    private LpaMesinDao dao;
    @Autowired
    private LpaMesinLampiranDao lampiranDao;
    @Autowired
    private DataCommonDao commonDao;
    @Autowired
    private LpaUmumDao lpaUmumDao;
    @Autowired
    private LpaUmumFacade facade;
    @Autowired
    private TableSequenceDao sequenceDao;
    @Autowired
    private ParameterDao parameterDao;
    @Autowired
    private SurveyMesinDao mesinDao;

    @Override
    public LpaMesinDto create(Long commonId) {
        DataCommon e = commonDao.get(commonId);
        DataDetail detail = e.getDataDetail();

        LpaMesinDto w = new LpaMesinDto();
        LpaUmumDto lpaUmum = facade.create(commonId);
        BeanUtils.copyProperties(lpaUmum, w);

        Double nilaiProsentase = 50d;
        List<Parameter> prosentaseLikuidasi = parameterDao.findByGroup(AppConstans.GroupParameter.PROSENTASE_LIKUIDASI);
        if (ListUtils.isNotEmpty(prosentaseLikuidasi)) {
            nilaiProsentase = Double.parseDouble(prosentaseLikuidasi.get(0).getValue());
        }
        w.setLikuidasi(nilaiProsentase);
        w.setBeriNilaiDoc(true);
        w.setBeriNilaiFisik(true);
        w.setLikuidasiEks(nilaiProsentase);
        w.setBeriNilaiDocEks(true);
        w.setBeriNilaiFisikEks(true);
        SurveyMesin surveyMesin = mesinDao.getByDetail(detail.getId());
        if (surveyMesin != null && surveyMesin.getId() != null) {
            w.setTempat(surveyMesin.getTempat());
            w.setKondisi(surveyMesin.getKondisi());
            w.setLingkungan(surveyMesin.getLingkungan());
            w.setTeknisi(surveyMesin.getTeknisi());
            w.setCukuCadang(surveyMesin.getCukuCadang());

            if (ListUtils.isNotEmpty(surveyMesin.getLampiran())) {

            }
        }

        return w;
    }

    @Override
    @Transactional
    public LpaMesinDto save(LpaMesinDto dto, String userId) throws Exception {
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
    @Transactional
    public void delete(Long id) throws Exception {
        try {
            dao.delete(id);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public LpaMesinDto getByUmum(Long lpaId) {
        return toWrapper(dao.getByUmum(lpaId));
    }

    @Override
    public List<LpaMesinLampiranDto> findLampiran(Long idLpaMesin) {
        List<LpaMesinLampiranDto> result = new ArrayList<LpaMesinLampiranDto>();
        List<LpaMesinLampiran> dataList = lampiranDao.findByLpaMesin(idLpaMesin);
        for (LpaMesinLampiran e : dataList) {
            result.add(toWrapperLampiran(e));
        }
        return result;
    }

    @Override
    public LpaMesinLampiranDto getLampiran(Long idLampiran) {
        return toWrapperLampiran(lampiranDao.get(idLampiran));
    }

    @Override
    @Transactional
    public LpaMesinLampiranDto saveLampiran(LpaMesinLampiranDto dto, String userId) throws Exception {
        LpaMesinLampiran lpaMesinLampiran = toEntityLampiran(dto);
        if(checkUserTasklist(userId, toWrapper(lpaMesinLampiran.getLpaMesin()))) {
            return toWrapperLampiran(lampiranDao.save(lpaMesinLampiran));
        }else{
            throw new Exception("Gagal : Posisi Order Tidak Pada Tasklist Anda");
        }
    }

    @Override
    @Transactional
    public void deleteLampiran(Long idLampiran) throws Exception {
        try {
            lampiranDao.delete(idLampiran);
        } catch (Exception e){
            throw e;
        }
    }

    private LpaMesinLampiranDto toWrapperLampiran(LpaMesinLampiran e) {
        LpaMesinLampiranDto w = new LpaMesinLampiranDto();
        BeanUtils.copyProperties(e, w);
        if(e.getLpaMesin() != null) {
            w.setIdLpaMesin(e.getLpaMesin().getId());
        }
        return w;
    }

    private LpaMesinLampiran toEntityLampiran(LpaMesinLampiranDto w) {
        LpaMesinLampiran e = new LpaMesinLampiran();
        if (w.getId() != null) {
            e = lampiranDao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        e.setLpaMesin(dao.get(w.getIdLpaMesin()));
        return e;
    }

    @Override
    public LpaMesinDto toWrapper(LpaMesin e) {
        LpaMesinDto w = new LpaMesinDto();
        BeanUtils.copyProperties(facade.toWrapper(e.getLpaUmum()), w);
        BeanUtils.copyProperties(e, w);
        return w;
    }

    @Override
    public LpaMesin toEntity(LpaMesinDto w) {
        LpaUmum umum = lpaUmumDao.save(facade.toEntity(w));
        LpaMesin e = new LpaMesin();
        if (w.getId() != null) {
            e = dao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        e.setLpaUmum(umum);
        if (w.isCreate()) {
            SurveyMesin surveyMesin = mesinDao.getByDetail(umum.getDetail().getId());
            if (surveyMesin != null && surveyMesin.getId() != null) {
                if (ListUtils.isNotEmpty(surveyMesin.getLampiran())) {
                    for (SurveyMesinLampiran row : surveyMesin.getLampiran()) {
                        LpaMesinLampiran lampiran = new LpaMesinLampiran();
                        lampiran.setJenis(row.getJenis());
                        lampiran.setMerk(row.getMerk());
                        lampiran.setTypeModel(row.getTypeModel());
                        lampiran.setNoSeri(row.getNoSeri());
                        lampiran.setAsal(row.getAsal());
                        lampiran.setTahun(row.getTahun());
                        lampiran.setKegunaan(row.getKegunaan());
                        lampiran.setKondisi(row.getKondisi());
                        lampiran.setRangkaian(row.getRangkaian());
                        e.addLampiran(lampiran);
                        // lampiranDao.save(lampiran);
                    }
                }
            }
        }
        return e;
    }

    private Boolean checkUserTasklist(String userId, LpaMesinDto dto) {
        DataCommon e = commonDao.get(dto.getCommonId());
        return StringUtils.equalsIgnoreCase(e.getTaskList(),userId);
    }

}