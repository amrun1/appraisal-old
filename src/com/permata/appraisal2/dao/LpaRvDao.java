package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.model.LpaRv;

public interface LpaRvDao extends DaoBase<LpaRv, Long> {
    public LpaRv getByUmum(Long lpaUmum);
}