package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.model.LpaItemKendaraan;

public interface LpaItemKendaraanDao extends DaoBase<LpaItemKendaraan, Long> {
    Integer getCountKendaraan(LpaItemKendaraan example);
}