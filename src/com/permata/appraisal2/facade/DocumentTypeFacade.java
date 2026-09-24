package com.permata.appraisal2.facade;

import com.permata.appraisal2.common.LabelValue;
import com.permata.appraisal2.common.LabelValueId;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.DocumentTypeDto;

import java.util.List;

public interface DocumentTypeFacade {

    public List<LabelValue<Long, String>> findLvByAktiva(Long activaId);

    public List<DocumentTypeDto> findByAktiva(Long activaId);

    public List<DocumentTypeDto> findListTableByAktiva(Long activaId);

    public DocumentTypeDto get(Long id);

    public DocumentTypeDto save(DocumentTypeDto dto);

    public void delete(Long id) throws Exception;

    public PaginatedList<DocumentTypeDto> find(DocumentTypeDto filter, PagingInfo pagingInfo);

}