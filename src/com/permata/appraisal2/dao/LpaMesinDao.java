package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.model.LpaMesin;

public interface LpaMesinDao extends DaoBase<LpaMesin, Long> {
    public LpaMesin getByUmum(Long lpaId);
}