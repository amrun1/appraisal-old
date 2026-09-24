package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.model.SurveyBv;

public interface SurveyBvDao extends DaoBase<SurveyBv, Long> {

    public SurveyBv getByDetail(Long idDetail);
}