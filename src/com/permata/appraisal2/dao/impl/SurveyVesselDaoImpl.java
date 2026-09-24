package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.SurveyVesselDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.model.SurveyVessel;
import org.springframework.stereotype.Repository;

@Repository
public class SurveyVesselDaoImpl extends DaoBaseImpl<SurveyVessel, Long> implements SurveyVesselDao {
    @Override
    public SurveyVessel getByDetail(Long detailId) {
        String hql = "from SurveyVessel where detail.id = ?";
        return (SurveyVessel) createQuery(hql).setParameter(0, detailId).uniqueResult();
    }
}