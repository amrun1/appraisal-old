package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.model.SurveyMesin;

public interface SurveyMesinDao extends DaoBase<SurveyMesin, Long> {
    public SurveyMesin getByDetail(Long detailId);
}