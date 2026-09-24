package com.permata.appraisal2.facade;

import com.permata.appraisal2.common.LabelValue;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.DocumentTypeDto;
import com.permata.appraisal2.dto.ParameterDto;

import java.util.List;
import java.util.Map;

public interface ParameterFacade {

    public List<LabelValue<Long, String>> findLvByGroup(String groupCode);

    public List<ParameterDto> findParamByGroup(String groupCode);

    public String getValue(String code, String groupCode);

    public ParameterDto get(Long id);

    public Map<String, Object> getReference();

    public PaginatedList<ParameterDto> find(ParameterDto dto, PagingInfo pagingInfo);

    public ParameterDto save(ParameterDto dto);

    public void delete(Long id) throws Exception;


}