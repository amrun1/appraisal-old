package com.permata.appraisal2.facade;

import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.HolidayDto;

public interface HolidayFacade {
    public PaginatedList<HolidayDto> find(HolidayDto dto, PagingInfo pagingInfo);
    public  Boolean findDuplicate(HolidayDto dto);
    public HolidayDto save(HolidayDto dto);
    public HolidayDto get(Long id);
    public void delete(Long id) throws Exception;
}