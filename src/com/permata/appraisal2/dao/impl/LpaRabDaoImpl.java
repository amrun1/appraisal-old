package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.LpaRabDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.model.LpaRab;
import org.springframework.stereotype.Repository;

@Repository
public class LpaRabDaoImpl extends DaoBaseImpl<LpaRab, Long> implements LpaRabDao {

    @Override
    public LpaRab getByUmum(Long idLpa) {
        String hql = "from LpaRab where lpaUmum.id=?";
        return (LpaRab) createQuery(hql).setParameter(0, idLpa).uniqueResult();
    }
}