package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.LpaTanahDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.model.LpaTanah;
import com.permata.appraisal2.model.LpaTanahBangunan;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class LpaTanahDaoImpl extends DaoBaseImpl<LpaTanah, Long> implements LpaTanahDao {

    @Override
    protected Criteria createCriteria(LpaTanah example, OrderSpec orderSpec, String... excludedProperties) {
        Criteria criteria = super.createCriteria(example, orderSpec, excludedProperties);

        try {
            if (example.getCommon().getId() != null) {
                criteria.add(Restrictions.eq("common.id", example.getCommon().getId()));
            }
        } catch (Exception e) {
            // biarkan jika branch tidak ada isinya
        }

        return criteria;
    }
    @Override
    public Integer getCountTanah(LpaTanah example) {
        Criteria criteria = createCriteria(example, null);
        criteria.setProjection(Projections.rowCount());
        return ((Long) criteria.uniqueResult()).intValue();
    }

    @Override
    public List<LpaTanah> getTanahByIdLnb(Long idLnb) {
        String hql = "from LpaTanah where common.id=? order by id asc";
        return (List<LpaTanah>) createQuery(hql).setParameter(0, idLnb).list();
    }
}