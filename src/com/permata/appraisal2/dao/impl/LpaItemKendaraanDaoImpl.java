package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.LpaItemKendaraanDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.model.LpaItemKendaraan;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class LpaItemKendaraanDaoImpl extends DaoBaseImpl<LpaItemKendaraan, Long> implements LpaItemKendaraanDao {

    @Override
    protected Criteria createCriteria(LpaItemKendaraan example, OrderSpec orderSpec, String... excludedProperties) {
        Criteria criteria = super.createCriteria(example, orderSpec, excludedProperties);

        try {
            if (example.getLpaKendaraan().getId() != null) {
                criteria.add(Restrictions.eq("lpaKendaraan.id", example.getLpaKendaraan().getId()));
            }
        } catch (Exception e) {
            // biarkan jika branch tidak ada isinya
        }

        return criteria;
    }

    @Override
    public Integer getCountKendaraan(LpaItemKendaraan example) {
        Criteria criteria = createCriteria(example, null);
        criteria.setProjection(Projections.rowCount());
        return ((Long) criteria.uniqueResult()).intValue();
    }
}