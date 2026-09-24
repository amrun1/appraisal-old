package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.SystemParamDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.model.SystemParam;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class SystemParamDaoImpl extends DaoBaseImpl<SystemParam, String> implements SystemParamDao {
    @Override
    protected Criteria createCriteria(SystemParam example, OrderSpec orderSpec, String... excludedProperties) {
        Criteria criteria = super.createCriteria(example, orderSpec, excludedProperties);
        try {
            if (StringUtils.isNotBlank(example.getCode())) {
                criteria.add(Restrictions.ilike("code", "%" + example.getCode() + "%"));
            }
        } catch (Exception e) {
        }
        return criteria;
    }

}