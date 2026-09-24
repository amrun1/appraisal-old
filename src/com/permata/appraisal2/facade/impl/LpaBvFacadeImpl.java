package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.dao.*;
import com.permata.appraisal2.dto.LpaBvDto;
import com.permata.appraisal2.dto.LpaUmumDto;
import com.permata.appraisal2.dto.SurveyBvDto;
import com.permata.appraisal2.facade.LpaBvFacade;
import com.permata.appraisal2.facade.LpaUmumFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class LpaBvFacadeImpl extends FacadeBase<LpaBvDto, LpaBv> implements LpaBvFacade {

    @Autowired
    private LpaBvDao dao;
    @Autowired
    private DataCommonDao commonDao;
    @Autowired
    private LpaUmumDao lpaUmumDao;
    @Autowired
    private LpaUmumFacade facade;
    @Autowired
    private TableSequenceDao sequenceDao;
    @Autowired
    private SurveyBvDao surveyBvDao;
    @Autowired
    private PersonDao personDao;

    @Override
    public LpaBvDto getByUmum(Long lpaId) {
        return toWrapper(dao.getByUmum(lpaId));
    }

    @Override
    @Transactional(readOnly = false)
    public LpaBvDto save(LpaBvDto dto, String userId) throws Exception {
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
    public LpaBvDto create(Long commonId) {
        DataCommon e = commonDao.get(commonId);
        DataDetail detail = e.getDataDetail();

        LpaBvDto w = new LpaBvDto();
        LpaUmumDto lpaUmum = facade.create(commonId);
        BeanUtils.copyProperties(lpaUmum, w);


        // not null value
        w.setPembanding1("-");
        w.setPembanding2("-");
        w.setPembanding3("-");
        w.setCatatanPoint("-");
        w.setBranchId(e.getBranch().getId());
        w.setDitujuke(personDao.get(e.getCreatedBy()).getName());
        w.setIdObjectPenilaiFisik(e.getAppraisalType().getId());
        w.setJenisObjectPenilain(e.getAppraisalType().getName());
        w.setJenisObjectPenilaiFisik(e.getAppraisalType().getName());
        w.setStatusJaminan(e.getCollateralStatus().getId());
        w.setSla(1);
        w.setKategoriSla("BV");
        w.setCatatanSurvey("-");
        w.setDitemui("-");
        w.setFaktorMarketability("-");

        if(detail.getSurvey() != null){
            SurveyBv survey = surveyBvDao.getByDetail(detail.getId());
            w.setLokasiFisik(detail.getSurvey().getLokasi());
            w.setNamaDebitur(survey.getVendor());
            BeanUtils.copyProperties(survey, w, "id", "detail");
            w.setFotoPada(survey.getFotoPada());
            w.setDitemui(e.getSurveyBy());
            w.setTanggalSurvey(e.getSurveyDate());
            w.setPapanNama(survey.getPapanNama());
            w.setLokasi_objek_jam(survey.getJam());
        }

        return w;
    }

    @Override
    public LpaBvDto toWrapper(LpaBv e) {
        LpaBvDto w = new LpaBvDto();
        BeanUtils.copyProperties(facade.toWrapper(e.getLpaUmum()), w);
        BeanUtils.copyProperties(e, w);

        // foto lampiran
        return w;
    }

    @Override
    public LpaBv toEntity(LpaBvDto w) {
        LpaUmum umum = lpaUmumDao.save(facade.toEntity(w));
        LpaBv e = new LpaBv();
        if (w.getId() != null) {
            e = dao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        e.setLpaUmum(umum);
        if (w.isCreate()) {
        }
        return e;
    }

    private Boolean checkUserTasklist(String userId, LpaBvDto dto) {
        DataCommon e = commonDao.get(dto.getCommonId());
        return StringUtils.equalsIgnoreCase(e.getTaskList(),userId);
    }
}