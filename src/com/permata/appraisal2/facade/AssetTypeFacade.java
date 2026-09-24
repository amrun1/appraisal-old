package com.permata.appraisal2.facade;

import com.permata.appraisal2.common.LabelValueId;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.AssetTypeDto;

import java.util.List;
import java.util.Map;

public interface AssetTypeFacade {
    public List<LabelValueId<Long, String, String>> findLvId();

    public Map<String, Object> findLvIdForm();

    public AssetTypeDto get(Long id);

    public void delete(Long id) throws Exception;

    public Map<String, Object> getReference();

    public PaginatedList<AssetTypeDto> find(AssetTypeDto dto, PagingInfo pagingInfo);

    public AssetTypeDto save(AssetTypeDto dto);
}