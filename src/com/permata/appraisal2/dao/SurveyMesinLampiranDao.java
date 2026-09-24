package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.model.SurveyMesinLampiran;

import java.util.List;

public interface SurveyMesinLampiranDao extends DaoBase<SurveyMesinLampiran, Long> {
    public List<SurveyMesinLampiran> findBySurveyMesin(Long idSurveyMesin);
}