package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.LpaBangunanDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.model.LpaBangunan;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LpaBangunanDaoImpl extends DaoBaseImpl<LpaBangunan, Long> implements LpaBangunanDao {
    
    @Override
    protected Criteria createCriteria(LpaBangunan example, OrderSpec orderSpec, String... excludedProperties) {
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
    public Integer getCountBangunan(LpaBangunan example) {
        Criteria criteria = createCriteria(example, null);
        criteria.setProjection(Projections.rowCount());
        return ((Long) criteria.uniqueResult()).intValue();
    }

    @Override
    public List<LpaBangunan> getBangunanByIdLnb(Long idLnb) {
        String hql = "from LpaBangunan where common.id=? order by id asc";
        return (List<LpaBangunan>) createQuery(hql).setParameter(0, idLnb).list();
    }
}