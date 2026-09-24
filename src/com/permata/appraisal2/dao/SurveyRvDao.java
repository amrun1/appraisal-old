package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.model.SurveyRv;

public interface SurveyRvDao extends DaoBase<SurveyRv, Long> {

    public SurveyRv getByDetail(Long detailId);
    
}