package com.permata.appraisal2.facade;

import com.permata.appraisal2.common.LabelValue;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.KjppDto;

import java.util.List;

public interface KjppFacade {
    public List<LabelValue<Long, String>> findLv();
    public PaginatedList<KjppDto> find(KjppDto dto, PagingInfo pagingInfo);
    public KjppDto save(KjppDto dto);
    public KjppDto get(Long id);
    public void delete(Long id) throws Exception;
}