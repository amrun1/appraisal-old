package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.dao.*;
import com.permata.appraisal2.dto.SurveyMesinDto;
import com.permata.appraisal2.dto.SurveyMesinLampiranDto;
import com.permata.appraisal2.facade.SurveyMesinFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = false)
public class SurveyMesinFacadeImpl extends FacadeBase<SurveyMesinDto, SurveyMesin> implements SurveyMesinFacade {

    @Autowired
    private SurveyMesinDao dao;
    @Autowired
    private SurveyCommonDao commonDao;
    @Autowired
    private SurveyPointDao pointDao;
    @Autowired
    private DataDetailDao detailDao;
    @Autowired
    private ParameterDao parameterDao;
    @Autowired
    private SurveyMesinLampiranDao lampiranDao;

    @Override
    @Transactional
    public SurveyMesinDto save(SurveyMesinDto dto) {
        SurveyMesin e = dao.save(toEntity(dto));
        SurveyCommon common = new SurveyCommon();
        if (dto.getId() != null) {
            common = commonDao.get(dto.getId());
        }
        BeanUtils.copyProperties(dto, common);
        common.setDetail(e.getDetail());
        common.setTanggal(dto.getTanggal());
        commonDao.save(common);

        SurveyPoint point = new SurveyPoint();
        if (dto.getIdPoint() != null) {
            point = pointDao.get(dto.getIdPoint());
        }
        point.setPositif(dto.getPositif());
        point.setCatatan(dto.getCatatanPoint());
        List<Parameter> listNegative = new ArrayList<Parameter>();

        if (dto.getListNegatif() != null && !dto.getListNegatif().isEmpty()){
            for (Long row : dto.getListNegatif()) {
                listNegative.add(parameterDao.get(row));
            }
        }
        point.setNegatif(listNegative);
        List<Parameter> listPertimbangan = new ArrayList<Parameter>();

        if (dto.getListPertimbangan() != null && !dto.getListPertimbangan().isEmpty()){
            for (Long row : dto.getListPertimbangan()) {
                listPertimbangan.add(parameterDao.get(row));
            }
        }
        point.setPertimbangan(listPertimbangan);
        point.setDetail(e.getDetail());
        pointDao.save(point);

        return toWrapper(e);
    }

    @Override
    public SurveyMesinDto getByDetail(Long detailId) {
        DataDetail detail = detailDao.get(detailId);
        SurveyMesinDto w = toWrapper(dao.getByDetail(detailId));
        w.setDetailId(detailId);
        w.setCategory(detail.getAssetType().getCategoryType().getCode());
        w.setActiva(detail.getAssetType().getName());
        w.setIdCommon(detail.getDataCommon().getId());

        // survey common
        SurveyCommon survey = commonDao.getByDetail(detailId);
        if (survey != null && survey.getId() != null) {
            BeanUtils.copyProperties(survey, w);
        }

        // survey point
        SurveyPoint point = pointDao.getByDetail(detailId);
        if (point != null && point.getId() != null) {
            BeanUtils.copyProperties(point, w, "id", "catatan");
            w.setIdPoint(point.getId());
            w.setCatatanPoint(point.getCatatan());

            for (Parameter row : point.getNegatif()) {
                w.addNegatif(row.getId());
            }
            for (Parameter row : point.getPertimbangan()) {
                w.addPertimbangan(row.getId());
            }
        }
        return w;
    }

    @Override
    public SurveyMesinLampiranDto getLampiran(Long id) {
        return toWrapperLampiran(lampiranDao.get(id));
    }

    @Override
    public List<SurveyMesinLampiranDto> findLampiran(Long idSurveyMesin) {
        List<SurveyMesinLampiranDto> result = new ArrayList<SurveyMesinLampiranDto>();
        List<SurveyMesinLampiran> listData = lampiranDao.findBySurveyMesin(idSurveyMesin);
        for(SurveyMesinLampiran e : listData){
            result.add(toWrapperLampiran(e));
        }
        return result;
    }

    @Override
    @Transactional
    public void deleteLampiran(Long id) throws Exception {
        try {
            lampiranDao.delete(id);
        } catch (Exception e){
            throw e;
        }
    }

    @Override
    @Transactional
    public SurveyMesinLampiranDto saveLampiran(SurveyMesinLampiranDto dto) {
        return toWrapperLampiran(lampiranDao.save(toEntityLampiran(dto)));
    }

    private SurveyMesinLampiranDto toWrapperLampiran(SurveyMesinLampiran e) {
        SurveyMesinLampiranDto w = new SurveyMesinLampiranDto();
        BeanUtils.copyProperties(e, w);
        w.setIdSurveyMesin(e.getSurveyMesin().getId());
        return w;
    }

    private SurveyMesinLampiran toEntityLampiran(SurveyMesinLampiranDto w) {
        SurveyMesinLampiran e = new SurveyMesinLampiran();
        if (w.getId() != null) {
            e = lampiranDao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        e.setSurveyMesin(dao.get(w.getIdSurveyMesin()));
        return e;
    }

    @Override
    public SurveyMesinDto toWrapper(SurveyMesin e) {
        SurveyMesinDto w = new SurveyMesinDto();
        if (e != null) {
            BeanUtils.copyProperties(e, w, "id");
            w.setCatatanMesin(e.getCatatan());
            w.setIdMesin(e.getId());
            w.setDetailId(e.getDetail().getId());
        }
        return w;
    }

    @Override
    public SurveyMesin toEntity(SurveyMesinDto w) {
        DataDetail detail = detailDao.get(w.getDetailId());

        SurveyMesin e = new SurveyMesin();
        if (w.getIdMesin() != null) {
            e = dao.get(w.getIdMesin());
        }
        BeanUtils.copyProperties(w, e, "id", "catatan");
        e.setId(w.getIdMesin());
        e.setCatatan(w.getCatatanMesin());
        e.setDetail(detail);
        return e;
    }
}