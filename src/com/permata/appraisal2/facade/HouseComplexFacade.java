package com.permata.appraisal2.facade;

import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.HouseComplexDto;

public interface HouseComplexFacade {
    public PaginatedList<HouseComplexDto> find(HouseComplexDto dto, PagingInfo pagingInfo);
}