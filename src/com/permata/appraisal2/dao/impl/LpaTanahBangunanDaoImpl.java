package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.LpaTanahBangunanDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.model.LpaTanahBangunan;
import org.springframework.stereotype.Repository;

@Repository
public class LpaTanahBangunanDaoImpl extends DaoBaseImpl<LpaTanahBangunan, Long> implements LpaTanahBangunanDao {

//    @Override
//    public boolean isDetailHasLpa(Long detailId) {
//        String hql = "select count(*) from LpaTanahBangunan where detail.id=?";
//        Long count = (Long) createQuery(hql).setParameter(0, detailId).uniqueResult();
//        if (count > 0) {
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public boolean isDraftLpa(Long detailId) {
//        String hql = "select draft from LpaTanahBangunan where detail.id=?";
//        Object bool = createQuery(hql).setParameter(0, detailId).uniqueResult();
//        return bool == null ? true : (Boolean) bool;
//    }
//
//    @Override
//    public LpaTanahBangunan getByDetail(Long detailId) {
//        String hql = "from LpaTanahBangunan where detail.id=?";
//        return (LpaTanahBangunan) createQuery(hql).setParameter(0, detailId).uniqueResult();
//    }

    @Override
    public LpaTanahBangunan getByUmum(Long id) {
        String hql = "from LpaTanahBangunan where lpaUmum.id=?";
        return (LpaTanahBangunan) createQuery(hql).setParameter(0, id).uniqueResult();
    }
}