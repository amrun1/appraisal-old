package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.model.LpaDesktop;

@Deprecated
public interface LpaDesktopDao extends DaoBase<LpaDesktop, Long> {

    public LpaDesktop getByUmum(Long id);

}