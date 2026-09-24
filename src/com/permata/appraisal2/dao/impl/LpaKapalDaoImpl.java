package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.LpaKapalDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.model.LpaKapal;
import org.springframework.stereotype.Repository;

@Repository
public class LpaKapalDaoImpl extends DaoBaseImpl<LpaKapal, Long> implements LpaKapalDao {

    @Override
    public LpaKapal getByUmum(Long idLpa) {
        String hql = "from LpaKapal where lpaUmum.id=?";
        return (LpaKapal) createQuery(hql).setParameter(0, idLpa).uniqueResult();
    }
}