package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.SurveyMesinDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.model.SurveyMesin;
import org.springframework.stereotype.Repository;

@Repository
public class SurveyMesinDaoImpl extends DaoBaseImpl<SurveyMesin, Long> implements SurveyMesinDao {

    @Override
    public SurveyMesin getByDetail(Long detailId) {
        String hql = "from SurveyMesin where detail.id = ?";
        return (SurveyMesin) createQuery(hql).setParameter(0, detailId).uniqueResult();
    }
}