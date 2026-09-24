package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.model.LpaMesinLampiran;

import java.util.List;

public interface LpaMesinLampiranDao extends DaoBase<LpaMesinLampiran, Long> {
    public List<LpaMesinLampiran> findByLpaMesin(Long idLpaMesin);
    Integer getCountMesin(Long idLpaMesin);
}