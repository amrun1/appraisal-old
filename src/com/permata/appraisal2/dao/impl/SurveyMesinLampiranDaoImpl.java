package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.SurveyMesinLampiranDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.model.SurveyMesinLampiran;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SurveyMesinLampiranDaoImpl extends DaoBaseImpl<SurveyMesinLampiran, Long> implements SurveyMesinLampiranDao {

    @Override
    public List<SurveyMesinLampiran> findBySurveyMesin(Long idSurveyMesin) {
        String hql = "from SurveyMesinLampiran where surveyMesin.id=?";
        return createQuery(hql).setParameter(0, idSurveyMesin).list();
    }
}