package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.model.SurveyCommon;

public interface SurveyCommonDao extends DaoBase<SurveyCommon, Long> {
    public SurveyCommon getByDetail(Long detailId);
}