package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.model.SurveyInspeksi;
import com.permata.appraisal2.model.SurveyStock;

public interface SurveyInspeksiDao extends DaoBase<SurveyInspeksi, Long> {
    Integer countSurvey(SurveyInspeksi example);
}
