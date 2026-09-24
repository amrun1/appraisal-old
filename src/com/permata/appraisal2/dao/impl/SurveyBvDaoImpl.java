package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.SurveyBvDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.model.SurveyBv;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class SurveyBvDaoImpl extends DaoBaseImpl<SurveyBv, Long> implements SurveyBvDao {

    @Override
    protected Criteria createCriteria(SurveyBv example, OrderSpec orderSpec, String... excludedProperties) {
        Criteria criteria = super.createCriteria(example, orderSpec, excludedProperties);
        try {
            if (example.getDetail().getId() != null) {
                criteria.add(Restrictions.eq("detail.id", example.getDetail().getId()));
            }
        } catch (Exception e) {
        }
        return criteria;
    }

    @Override
    public SurveyBv getByDetail(Long detailId) {
        String hql = "from SurveyBv where detail.id = ?";
        return (SurveyBv) createQuery(hql).setParameter(0, detailId).uniqueResult();
    }

}