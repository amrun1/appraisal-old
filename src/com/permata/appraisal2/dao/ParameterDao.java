package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.model.Parameter;

import java.util.List;

public interface ParameterDao extends DaoBase<Parameter, Long> {

    public List<Parameter> findByGroup(String groupCode);

    public List<Parameter> findByCodeAndGroup(String code, String groupCode);

    public String findRVBVFotopada(String codeGroup, Integer sort);
}

