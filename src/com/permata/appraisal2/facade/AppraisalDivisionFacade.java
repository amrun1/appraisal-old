package com.permata.appraisal2.facade;

import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.AppraisalDivisionDto;

public interface AppraisalDivisionFacade {

    public PaginatedList<AppraisalDivisionDto> find(AppraisalDivisionDto dto, PagingInfo pagingInfo);

    public AppraisalDivisionDto save(AppraisalDivisionDto dto);

    public AppraisalDivisionDto get(Long id);

    public void delete(Long id) throws Exception;
}