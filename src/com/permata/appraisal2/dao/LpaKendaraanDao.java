package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.model.LpaKendaraan;

public interface LpaKendaraanDao extends DaoBase<LpaKendaraan, Long> {
    public LpaKendaraan getByUmum(Long lpaUmum);
}