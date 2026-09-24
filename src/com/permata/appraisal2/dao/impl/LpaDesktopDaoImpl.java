package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.LpaDesktopDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.model.LpaDesktop;
import org.springframework.stereotype.Repository;

//@Repository
@Deprecated
public class LpaDesktopDaoImpl extends DaoBaseImpl<LpaDesktop, Long> implements LpaDesktopDao {

    @Override
    public LpaDesktop getByUmum(Long id) {
        return null;
    }

}