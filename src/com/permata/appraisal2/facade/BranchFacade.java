package com.permata.appraisal2.facade;

import com.permata.appraisal2.common.LabelValue;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.AppraisalBranchDto;

import java.util.List;

public interface BranchFacade {
    public List<LabelValue<Long, String>> findLv();

    public PaginatedList<AppraisalBranchDto> find(AppraisalBranchDto dto, PagingInfo pagingInfo);

    public AppraisalBranchDto get(Long id);

    public AppraisalBranchDto save(AppraisalBranchDto dto);

    public void delete(Long id) throws Exception;

    public List<AppraisalBranchDto> getAll();
}