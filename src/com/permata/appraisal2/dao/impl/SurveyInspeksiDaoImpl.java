package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.SurveyInspeksiDao;
import com.permata.appraisal2.dao.SurveyStockDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.model.SurveyInspeksi;
import com.permata.appraisal2.model.SurveyStock;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository
public class SurveyInspeksiDaoImpl extends DaoBaseImpl<SurveyInspeksi, Long> implements SurveyInspeksiDao{
    @Override
    protected Criteria createCriteria(SurveyInspeksi example, OrderSpec orderSpec, String... excludedProperties) {
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
    public Integer countSurvey(SurveyInspeksi example) {
        Criteria criteria = createCriteria(example, null);
        criteria.setProjection(Projections.rowCount());
        return ((Long) criteria.uniqueResult()).intValue();
    }

}
