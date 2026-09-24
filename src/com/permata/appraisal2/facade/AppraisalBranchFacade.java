package com.permata.appraisal2.facade;

import com.permata.appraisal2.common.LabelValue;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.AppraisalBranchDto;

import java.util.List;

public interface AppraisalBranchFacade {

    public PaginatedList<AppraisalBranchDto> find(AppraisalBranchDto dto, PagingInfo pagingInfo);

    public List<LabelValue<Long, String>> findLv(AppraisalBranchDto dto);

    public AppraisalBranchDto save(AppraisalBranchDto dto);

    public AppraisalBranchDto get(Long id);

    public void delete(Long id) throws Exception;

}