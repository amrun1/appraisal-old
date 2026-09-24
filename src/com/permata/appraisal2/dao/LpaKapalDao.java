package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.model.LpaKapal;

public interface LpaKapalDao extends DaoBase<LpaKapal, Long> {
    public LpaKapal getByUmum(Long idLpa);
}