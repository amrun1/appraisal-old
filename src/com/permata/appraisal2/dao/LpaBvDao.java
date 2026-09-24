package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.model.LpaBv;

public interface LpaBvDao extends DaoBase<LpaBv, Long> {

    public LpaBv getByUmum(Long lpaUmum);
}