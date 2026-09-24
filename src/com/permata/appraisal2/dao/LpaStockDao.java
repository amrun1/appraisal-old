package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.model.LpaStock;

public interface LpaStockDao extends DaoBase<LpaStock, Long> {
    public LpaStock getByUmum(Long lpaId);
}