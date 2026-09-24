package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.dao.*;
import com.permata.appraisal2.dto.LpaBvDto;
import com.permata.appraisal2.dto.LpaStockDto;
import com.permata.appraisal2.dto.LpaUmumDto;
import com.permata.appraisal2.facade.LpaStockFacade;
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
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class LpaStockFacadeImpl implements LpaStockFacade {

    @Autowired
    private LpaStockDao dao;
    @Autowired
    private DataCommonDao commonDao;
    @Autowired
    private LpaUmumDao lpaUmumDao;
    @Autowired
    private TableSequenceDao sequenceDao;
    @Autowired
    private ParameterDao parameterDao;

    @Autowired
    private LpaUmumFacade facade;

    @Override
    public void delete(Long id) throws Exception {
        try {
            dao.delete(id);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public LpaStockDto getByUmum(Long lpaId) {
        return toWrapper(dao.getByUmum(lpaId));
    }

    @Override
    public LpaStockDto create(Long commonId) {
        DataCommon e = commonDao.get(commonId);
        DataDetail detail = e.getDataDetail();

        LpaStockDto w = new LpaStockDto();
        LpaUmumDto lpaUmum = facade.create(commonId);
        BeanUtils.copyProperties(lpaUmum, w);
        w.setBerinilaiDokumen(true);
        w.setBerinilaiFisik(true);
        w.setBerinilaiDokumenEks(true);
        w.setBerinilaiFisikEks(true);
        Double nilaiProsentase = 50d;
        List<Parameter> prosentaseLikuidasi = parameterDao.findByGroup(AppConstans.GroupParameter.PROSENTASE_LIKUIDASI);
        if (ListUtils.isNotEmpty(prosentaseLikuidasi)) {
            nilaiProsentase = Double.parseDouble(prosentaseLikuidasi.get(0).getValue());
        }
        w.setPersentase(nilaiProsentase);
        w.setPersentaseEks(nilaiProsentase);


        List<SurveyStock> listSurvey = detail.getSurveyStocks();
        if (listSurvey != null && !listSurvey.isEmpty()){
            SurveyStock survey = listSurvey.get(0);
            w.setJenisPersedian(survey.getJenisPersedian());
            w.setPenempatanBarang(survey.getPenempatanBarang());
            w.setKemasanBarang(survey.getKemasanBarang());
            w.setKondisiBarang(survey.getKondisiBarang());
            w.setMetodePemeriksaan(survey.getMetodePemeriksaan());
            w.setJumlahPersedian(survey.getJumlahPersedian());
            w.setAsalPembuatan(survey.getAsalPembuatan());
            w.setKondisiPenyimpanan(survey.getKondisiPenyimpanan());
            w.setLingkunganSekitar(survey.getLingkunganSekitar());
            w.setPeralatanGudang(survey.getPeralatanGudang());
        }

        return w;
    }

    @Override
    @Transactional(readOnly = false)
    public LpaStockDto save(LpaStockDto dto, String userId) throws Exception {
        if (!dto.getDraft() && StringUtils.isBlank(dto.getLpaNumber())) {
            dto.setLpaNumber(sequenceDao.lpaNextVal());
        }
        if(checkUserTasklist(userId,dto)) {
            return toWrapper(dao.save(toEntity(dto)));
        } else {
            throw new Exception("Gagal : Posisi Order Tidak Pada Tasklist Anda");
        }
    }

    private LpaStock toEntity(LpaStockDto w) {
        LpaUmum umum = lpaUmumDao.save(facade.toEntity(w));
        LpaStock e = new LpaStock();
        if (w.getId() != null) {
            e = dao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        e.setLpaUmum(umum);
        return e;
    }

    private LpaStockDto toWrapper(LpaStock e) {
        LpaStockDto w = new LpaStockDto();
        BeanUtils.copyProperties(facade.toWrapper(e.getLpaUmum()), w);
        BeanUtils.copyProperties(e, w);
        return w;
    }

    private Boolean checkUserTasklist(String userId, LpaStockDto dto) {
        DataCommon e = commonDao.get(dto.getCommonId());
        return StringUtils.equalsIgnoreCase(e.getTaskList(),userId);
    }
}