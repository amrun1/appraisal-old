package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.AppraisalDivisionDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.model.AppraisalDivision;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class AppraisalDivisionDaoImpl extends DaoBaseImpl<AppraisalDivision, Long> implements AppraisalDivisionDao {

    @Override
    protected Criteria createCriteria(AppraisalDivision example, OrderSpec orderSpec, String... excludedProperties) {
        Criteria criteria = super.createCriteria(example, orderSpec, excludedProperties);

        try {
            logger.debug("check branchId " + example.getBranch().getId());
            if (example.getBranch().getId() != null) {
                criteria.add(Restrictions.eq("branch.id", example.getBranch().getId()));
            }
        } catch (Exception e) {
            // biarkan jika branch tidak ada isinya
        }

        return criteria;
    }

}