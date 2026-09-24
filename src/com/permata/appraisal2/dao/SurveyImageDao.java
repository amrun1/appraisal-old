package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.model.SurveyImage;

import java.util.List;

public interface SurveyImageDao extends DaoBase<SurveyImage, Long> {

    public List<SurveyImage> findBySurvey(Long id);

//    public List<SurveyImage> findBySurveyXLS(Long id);

    public List<SurveyImage> findByLpa(Long id);

    public List<SurveyImage> findByLpaOrSurvey(Long survey, Long lpa);

    public List<SurveyImage> findWorkshetByLpa(Long id);

    public List<SurveyImage> findDokPelengkapByLpa(Long id);
}