package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.model.SurveyVessel;

public interface SurveyVesselDao extends DaoBase<SurveyVessel, Long> {

    public SurveyVessel getByDetail(Long detailId);

}