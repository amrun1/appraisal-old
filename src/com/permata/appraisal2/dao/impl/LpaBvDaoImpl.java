package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.LpaBvDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.model.LpaBv;
import org.springframework.stereotype.Repository;

@Repository
public class LpaBvDaoImpl extends DaoBaseImpl<LpaBv, Long> implements LpaBvDao {

    @Override
    public LpaBv getByUmum(Long lpaUmum) {
        String hql = "from LpaBv where lpaUmum.id=? ";
        return (LpaBv) createQuery(hql).setParameter(0, lpaUmum).uniqueResult();
    }
}