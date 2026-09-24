package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.LpaKendaraanDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.model.LpaKendaraan;
import org.springframework.stereotype.Repository;

@Repository
public class LpaKendaraanDaoImpl extends DaoBaseImpl<LpaKendaraan, Long> implements LpaKendaraanDao {

    @Override
    public LpaKendaraan getByUmum(Long lpaUmum) {
        String hql = "from LpaKendaraan where lpaUmum.id=? ";
        return (LpaKendaraan) createQuery(hql).setParameter(0, lpaUmum).uniqueResult();
    }
}