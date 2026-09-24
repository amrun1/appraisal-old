package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.LpaRvDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.model.LpaRv;
import org.springframework.stereotype.Repository;

@Repository
public class LpaRvDaoImpl extends DaoBaseImpl<LpaRv, Long> implements LpaRvDao {

    @Override
    public LpaRv getByUmum(Long lpaUmum) {
        String hql = "from LpaRv where lpaUmum.id=? ";
        return (LpaRv) createQuery(hql).setParameter(0, lpaUmum).uniqueResult();
    }
}