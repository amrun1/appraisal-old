package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.SurveyRvDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.model.SurveyRv;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class SurveyRvDaoImpl extends DaoBaseImpl<SurveyRv, Long> implements SurveyRvDao {

    @Override
    protected Criteria createCriteria(SurveyRv example, OrderSpec orderSpec, String... excludedProperties) {
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
    public SurveyRv getByDetail(Long detailId) {
        String hql = "from SurveyRv where detail.id = ?";
        return (SurveyRv) createQuery(hql).setParameter(0, detailId).uniqueResult();
    }
}