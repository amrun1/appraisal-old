package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.model.LpaTanahBangunan;

public interface LpaTanahBangunanDao extends DaoBase<LpaTanahBangunan, Long> {

//    public boolean isDetailHasLpa(Long detailId);
//
//    public boolean isDraftLpa(Long detailId);
//
//    public LpaTanahBangunan getByDetail(Long detailId);

    public LpaTanahBangunan getByUmum(Long id);

}