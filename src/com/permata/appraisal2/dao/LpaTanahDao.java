package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.model.LpaTanah;
import com.permata.appraisal2.model.LpaTanahBangunan;

import java.util.List;

public interface LpaTanahDao extends DaoBase<LpaTanah, Long> {
    Integer getCountTanah(LpaTanah example);
    List<LpaTanah> getTanahByIdLnb(Long idLnb);
}