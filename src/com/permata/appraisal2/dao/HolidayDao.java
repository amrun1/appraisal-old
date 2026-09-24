package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.HolidayDto;
import com.permata.appraisal2.model.Holiday;

public interface HolidayDao extends DaoBase<Holiday, Long> {
    PaginatedList<Holiday> find(PagingInfo pagingInfo, HolidayDto filter, OrderSpec order);
    Long findDuplicate(Holiday holiday);
}