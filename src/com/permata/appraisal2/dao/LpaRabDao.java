package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.model.LpaRab;

public interface LpaRabDao extends DaoBase<LpaRab, Long> {
    public LpaRab getByUmum(Long idLpa);
}