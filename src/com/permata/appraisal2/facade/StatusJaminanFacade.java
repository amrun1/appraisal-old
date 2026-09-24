package com.permata.appraisal2.facade;

import com.permata.appraisal2.common.LabelValue;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.StatusJaminanDto;

import java.util.List;

public interface StatusJaminanFacade {
    public List<LabelValue<Long, String>> listLv();
    PaginatedList<StatusJaminanDto> find(StatusJaminanDto dto, PagingInfo pagingInfo);
    StatusJaminanDto save(StatusJaminanDto dto);
    StatusJaminanDto get(Long id);
    List<StatusJaminanDto> getAll();
}