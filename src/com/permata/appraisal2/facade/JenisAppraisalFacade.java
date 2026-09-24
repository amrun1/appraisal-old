package com.permata.appraisal2.facade;

import com.permata.appraisal2.common.LabelValue;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.JenisAppraisalDto;

import java.util.List;
import java.util.Map;

public interface JenisAppraisalFacade {
    public PaginatedList<JenisAppraisalDto> find(JenisAppraisalDto dto, PagingInfo pagingInfo);
    public JenisAppraisalDto save(JenisAppraisalDto dto);
    public JenisAppraisalDto get(Long id);

    public List<LabelValue<Long, String>> findLv();

    public Map<String, Object> findLvForm();


}