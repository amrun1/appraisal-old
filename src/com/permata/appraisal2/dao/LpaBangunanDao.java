package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.model.LpaBangunan;

import java.util.List;

public interface LpaBangunanDao extends DaoBase<LpaBangunan, Long> {
    Integer getCountBangunan(LpaBangunan example);
    List<LpaBangunan> getBangunanByIdLnb(Long idLnb);
}