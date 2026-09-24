package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.SurveyPointDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.model.SurveyPoint;
import org.springframework.stereotype.Repository;

@Repository
public class SurveyPointDaoImpl extends DaoBaseImpl<SurveyPoint, Long> implements SurveyPointDao {

    @Override
    public SurveyPoint getByDetail(Long detailId) {
        String hql = "from SurveyPoint where detail.id=?";
        return (SurveyPoint) createQuery(hql).setParameter(0, detailId).uniqueResult();
    }

}