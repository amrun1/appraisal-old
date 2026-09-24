package com.permata.appraisal2.facade;

import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.DataDetailDto;
import com.permata.appraisal2.model.DataDetail;

import java.util.Map;

public interface DataDetailFacade {

    public PaginatedList<DataDetailDto> find(DataDetailDto dto, PagingInfo pagingInfo);

    public DataDetailDto save(DataDetailDto dto);

    public DataDetailDto get(Long id);

    public void delete(Long id) throws Exception;

    public Map<String, Object> getReference();

    public DataDetailDto toWrapper(DataDetail e);
}