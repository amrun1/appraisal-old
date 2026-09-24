package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.SurveyInspeksiDao;
import com.permata.appraisal2.dao.SurveyItemInspeksiDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.model.SurveyInspeksi;
import com.permata.appraisal2.model.SurveyItemInspeksi;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository
public class SurveyItemInspeksiDaoImpl extends DaoBaseImpl<SurveyItemInspeksi, Long> implements SurveyItemInspeksiDao{
    @Override
    protected Criteria createCriteria(SurveyItemInspeksi example, OrderSpec orderSpec, String... excludedProperties) {
        Criteria criteria = super.createCriteria(example, orderSpec, excludedProperties);
        try {
            if (example.getSurveyInspeksi().getId() != null) {
                criteria.add(Restrictions.eq("surveyInspeksi.id", example.getSurveyInspeksi().getId()));
            }
        } catch (Exception e) {
        }
        return criteria;
    }

    @Override
    protected Criteria createCriteriaExact(SurveyItemInspeksi example, OrderSpec orderSpec, String... excludedProperties) {
        Criteria criteria = super.createCriteriaExact(example, orderSpec, excludedProperties);
        try {
            if (example.getSurveyInspeksi().getId() != null) {
                criteria.add(Restrictions.eq("surveyInspeksi.id", example.getSurveyInspeksi().getId()));
            }
        } catch (Exception e) {
        }
        return criteria;
    }

}
