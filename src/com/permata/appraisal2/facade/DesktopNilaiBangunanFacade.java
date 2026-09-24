package com.permata.appraisal2.facade;

import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.DesktopNilaiBangunanDto;

public interface DesktopNilaiBangunanFacade {

    public PaginatedList<DesktopNilaiBangunanDto> find(DesktopNilaiBangunanDto dto, PagingInfo pagingInfo);

}