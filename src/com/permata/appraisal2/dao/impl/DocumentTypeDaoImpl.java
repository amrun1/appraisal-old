package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.DocumentTypeDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.model.DocumentType;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentTypeDaoImpl extends DaoBaseImpl<DocumentType, Long> implements DocumentTypeDao {

    @Override
    protected Criteria createCriteria(DocumentType example, OrderSpec orderSpec, String... excludedProperties) {
        Criteria criteria = super.createCriteria(example, orderSpec, excludedProperties);

        try {
            logger.debug("check Asset ID " + example.getAssetType().getId());
            criteria.add(Restrictions.eq("assetType.id", example.getAssetType().getId()));
        } catch (Exception e) {
            // biarkan jika tidak ada isinya
        }

        return criteria;
    }
}