package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.SurveyKendaraanDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.model.SurveyKendaraan;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class SurveyKendaraanDaoImpl extends DaoBaseImpl<SurveyKendaraan, Long> implements SurveyKendaraanDao {

    @Override
    protected Criteria createCriteria(SurveyKendaraan example, OrderSpec orderSpec, String... excludedProperties) {
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