package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.AssetTypeDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.model.AssetType;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class AssetTypeDaoImpl extends DaoBaseImpl<AssetType, Long> implements AssetTypeDao {

    @Override
    protected Criteria createCriteria(AssetType example, OrderSpec orderSpec, String... excludedProperties) {
        Criteria criteria = super.createCriteria(example, orderSpec, excludedProperties);

        try {
            if (example.getCategoryType() != null && example.getCategoryType().getCode() != null) {
                criteria.add(Restrictions.eq("categoryType.code", example.getCategoryType().getCode()));
            }
        } catch (Exception e) {
            // biarkan jika branch tidak ada isinya
        }

        return criteria;
    }
}