package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.model.SurveyPoint;

public interface SurveyPointDao extends DaoBase<SurveyPoint, Long> {

    public SurveyPoint getByDetail(Long detailId);
}