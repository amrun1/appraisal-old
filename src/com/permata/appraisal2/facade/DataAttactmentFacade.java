package com.permata.appraisal2.facade;

import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.DataAttactmentDto;
import com.permata.appraisal2.model.DataAttactment;
import com.permata.appraisal2.model.DataCommon;
import com.permata.appraisal2.model.DataDetail;

import java.util.List;
import java.util.Map;

public interface DataAttactmentFacade {

    public PaginatedList<DataAttactmentDto> find(DataAttactmentDto dto, PagingInfo pagingInfo);

    public DataAttactmentDto save(DataAttactmentDto dto);

    public DataAttactmentDto get(Long id);

    public void delete(Long id) throws Exception;

    public Map<String, Object> getReference(Long assetTypeId);

    public DataAttactmentDto toWrapper(DataAttactment e);

    public List<DataAttactmentDto> toListWrapper(List<DataAttactment> listE);

    public Long countByDetail(Long detailId);

    public Map<String, Object> findDetailInfo(Long detailId);

    List<DataDetail> getDataUmumByNameFile(String namaFile);
    List<DataAttactmentDto> getAttachmentsWithoutBlob(DataDetail idDataDetail);
}