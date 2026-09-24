package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.LpaItemInspeksiDao;
import com.permata.appraisal2.dao.common.CriteriaVisitor;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.dao.common.ExampleHelper;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.model.LpaItemInspeksi;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SB on 4/17/2018.
 */
@Repository
public class LpaItemInspeksiDaoImpl extends DaoBaseImpl<LpaItemInspeksi, Long> implements LpaItemInspeksiDao {
    @Override
    protected Criteria createCriteria(LpaItemInspeksi example, OrderSpec orderSpec, String... excludedProperties) {
        Criteria criteria = createCriteria(example.getClass(), orderSpec);
        if (example != null) {
            Example ex = ExampleHelper.createExample(example, excludedProperties);
            criteria.add(ex.ignoreCase());
            List<CriteriaVisitor<LpaItemInspeksi>> v = getVisitors();
            for (CriteriaVisitor<LpaItemInspeksi> criteriaVisitor : v) {
                criteriaVisitor.visit(criteria, example, excludedProperties);
            }
        }
        try {
            if (example.getLpaInspeksi().getId() != null) {
                criteria.add(Restrictions.eq("lpaInspeksi.id", example.getLpaInspeksi().getId()));
            }
        } catch (Exception e) {
        }
        return criteria;
    }
}
