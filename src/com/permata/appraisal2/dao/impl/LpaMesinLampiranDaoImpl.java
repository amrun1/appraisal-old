package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.LpaMesinLampiranDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.model.LpaMesinLampiran;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LpaMesinLampiranDaoImpl extends DaoBaseImpl<LpaMesinLampiran, Long> implements LpaMesinLampiranDao {

    @Override
    public List<LpaMesinLampiran> findByLpaMesin(Long idLpaMesin) {
        String hql = "from LpaMesinLampiran where lpaMesin.id = ? ";
        return createQuery(hql).setParameter(0, idLpaMesin).list();
    }

    @Override
    public Integer getCountMesin(Long idLpaMesin) {
        String hql = "select count(mesinLampiran) from LpaMesinLampiran mesinLampiran where lpaMesin.id = ? ";
        return ((Long)createQuery(hql).setParameter(0, idLpaMesin).uniqueResult()).intValue();
    }
    
}