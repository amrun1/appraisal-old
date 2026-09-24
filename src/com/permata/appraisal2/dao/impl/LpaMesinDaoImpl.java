package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.LpaMesinDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.model.LpaMesin;
import org.springframework.stereotype.Repository;

@Repository
public class LpaMesinDaoImpl extends DaoBaseImpl<LpaMesin, Long> implements LpaMesinDao {

    @Override
    public LpaMesin getByUmum(Long lpaUmum) {
        String hql = "from LpaMesin where lpaUmum.id=? ";
        return (LpaMesin) createQuery(hql).setParameter(0, lpaUmum).uniqueResult();
    }
    
}