package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.LpaStockDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.model.LpaStock;
import org.springframework.stereotype.Repository;

@Repository
public class LpaStockDaoImpl extends DaoBaseImpl<LpaStock, Long> implements LpaStockDao {

    @Override
    public LpaStock getByUmum(Long lpaUmum) {
        String hql = "from LpaStock where lpaUmum.id=? ";
        return (LpaStock) createQuery(hql).setParameter(0, lpaUmum).uniqueResult();
    }
}