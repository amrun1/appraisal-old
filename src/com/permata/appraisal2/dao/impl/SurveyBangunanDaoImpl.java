package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.SurveyBangunanDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.model.SurveyBangunan;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class SurveyBangunanDaoImpl extends DaoBaseImpl<SurveyBangunan, Long> implements SurveyBangunanDao {

    @Override
    protected Criteria createCriteria(SurveyBangunan example, OrderSpec orderSpec, String... excludedProperties) {
        Criteria criteria = super.createCriteria(example, orderSpec, excludedProperties);
        try {
            if (example.getDetail().getId() != null) {
                criteria.add(Restrictions.eq("detail.id", example.getDetail().getId()));
            }
        } catch (Exception e) {
        }
        return criteria;
    }
}