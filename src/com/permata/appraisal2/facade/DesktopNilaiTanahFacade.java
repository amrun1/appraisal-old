package com.permata.appraisal2.facade;

import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.DesktopNilaiTanahDto;
import com.permata.appraisal2.dto.DesktopNilaiTanahXls;

import java.util.List;

public interface DesktopNilaiTanahFacade {

    public PaginatedList<DesktopNilaiTanahDto> find(DesktopNilaiTanahDto dto, PagingInfo pagingInfo);

    public String upload(List<DesktopNilaiTanahXls> listData) throws Exception;
    
}