package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.dao.*;
import com.permata.appraisal2.dto.LpaKapalDto;
import com.permata.appraisal2.dto.LpaUmumDto;
import com.permata.appraisal2.facade.LpaKapalFacade;
import com.permata.appraisal2.facade.LpaUmumFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.*;
import com.permata.appraisal2.util.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional (readOnly = false)
public class LpaKapalFacadeImpl extends FacadeBase<LpaKapalDto, LpaKapal> implements LpaKapalFacade {

    @Autowired
    private LpaKapalDao dao;
    @Autowired
    private LpaUmumDao lpaUmumDao;
    @Autowired
    private DataCommonDao commonDao;
    @Autowired
    private TableSequenceDao sequenceDao;
    @Autowired
    private ParameterDao parameterDao;
    @Autowired
    private SurveyVesselDao surveyDao;

    @Autowired
    private LpaUmumFacade facade;

    @Override
    public LpaKapalDto create(Long commonId) {
        DataCommon e = commonDao.get(commonId);
        DataDetail detail = e.getDataDetail();

        LpaKapalDto w = new LpaKapalDto();
        LpaUmumDto lpaUmum = facade.create(commonId);
        BeanUtils.copyProperties(lpaUmum, w);

        // mapping hasil survey jika ada
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
        SurveyVessel survey = surveyDao.getByDetail(detail.getId());
        if (survey != null && survey.getId() != null) {
            BeanUtils.copyProperties(survey, w, "id");
        }

        return w;
    }

    @Override
    @Transactional
    public LpaKapalDto save(LpaKapalDto dto, String userId) throws Exception {
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
    public LpaKapalDto getByUmum(Long lpaId) {
        return toWrapper(dao.getByUmum(lpaId));
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
    public LpaKapalDto toWrapper(LpaKapal e) {
        LpaKapalDto w = new LpaKapalDto();
        BeanUtils.copyProperties(facade.toWrapper(e.getLpaUmum()), w);
        BeanUtils.copyProperties(e, w);
        return w;
    }

    @Override
    public LpaKapal toEntity(LpaKapalDto w) {
        LpaUmum umum = lpaUmumDao.save(facade.toEntity(w));
        LpaKapal e = new LpaKapal();
        if (w.getId() != null) {
            e = dao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        e.setLpaUmum(umum);
        if (w.isCreate()) {

        }
        return e;
    }

    private Boolean checkUserTasklist(String userId, LpaKapalDto dto) {
        DataCommon e = commonDao.get(dto.getCommonId());
        return StringUtils.equalsIgnoreCase(e.getTaskList(),userId);
    }
}