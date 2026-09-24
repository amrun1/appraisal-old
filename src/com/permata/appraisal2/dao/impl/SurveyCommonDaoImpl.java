package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.SurveyCommonDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.model.SurveyCommon;
import org.springframework.stereotype.Repository;

@Repository
public class SurveyCommonDaoImpl extends DaoBaseImpl<SurveyCommon, Long> implements SurveyCommonDao {

    @Override
    public SurveyCommon getByDetail(Long detailId) {
        String hql = "from SurveyCommon where detail.id=?";
        return (SurveyCommon) createQuery(hql).setParameter(0, detailId).uniqueResult();
    }
}