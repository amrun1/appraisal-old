package com.permata.appraisal2.facade;

import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.SystemParamsDto;
import com.permata.appraisal2.model.SystemParam;

public interface SystemParamFacade {

    public String getValue(String code);

    public void update(String code, String value);

    public PaginatedList<SystemParamsDto> find(SystemParamsDto dto, PagingInfo pagingInfo);

    public SystemParamsDto get(String id);

    public SystemParamsDto getCot(String id);
}