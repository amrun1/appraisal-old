package com.permata.appraisal2.facade;

import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.StatusNasabahDto;

public interface StatusNasabahFacade {
    public PaginatedList<StatusNasabahDto> find(StatusNasabahDto dto, PagingInfo pagingInfo);
    public StatusNasabahDto save(StatusNasabahDto dto);
    public StatusNasabahDto get(Long id);
}