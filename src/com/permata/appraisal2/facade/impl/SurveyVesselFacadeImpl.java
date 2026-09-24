package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.dao.*;
import com.permata.appraisal2.dto.SurveyVesselDto;
import com.permata.appraisal2.facade.SurveyVesselFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional (readOnly = true)
public class SurveyVesselFacadeImpl extends FacadeBase<SurveyVesselDto, SurveyVessel> implements SurveyVesselFacade {

    @Autowired
    private SurveyVesselDao dao;
    @Autowired
    private DataDetailDao detailDao;
    @Autowired
    private SurveyCommonDao commonDao;
    @Autowired
    private SurveyPointDao pointDao;
    @Autowired
    private ParameterDao parameterDao;

    @Override
    public SurveyVesselDto getByDetail(Long detailId) {
        DataDetail detail = detailDao.get(detailId);
        SurveyVesselDto w = toWrapper(dao.getByDetail(detailId));
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
    @Transactional
    public SurveyVesselDto save(SurveyVesselDto dto) {
        SurveyVessel e = dao.save(toEntity(dto));
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

        if (dto.getListNegatif() !=null && !dto.getListNegatif().isEmpty()){
            for (Long row : dto.getListNegatif()) {
                listNegative.add(parameterDao.get(row));
            }
        }


        point.setNegatif(listNegative);
        List<Parameter> listPertimbangan = new ArrayList<Parameter>();

        if (dto.getListPertimbangan() !=null && !dto.getListPertimbangan().isEmpty()){
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
    public SurveyVesselDto toWrapper(SurveyVessel e) {
        SurveyVesselDto w = new SurveyVesselDto();
        if (e != null) {
            BeanUtils.copyProperties(e, w, "id");
            w.setVesselId(e.getId());
            w.setDetailId(e.getDetail().getId());
        }
        return w;
    }

    @Override
    public SurveyVessel toEntity(SurveyVesselDto w) {
        DataDetail detail = detailDao.get(w.getDetailId());

        SurveyVessel e = new SurveyVessel();
        if (w.getVesselId() != null) {
            e = dao.get(w.getVesselId());
        }
        BeanUtils.copyProperties(w, e, "id");
        e.setId(w.getVesselId());
        e.setDetail(detail);
        return e;
    }
}