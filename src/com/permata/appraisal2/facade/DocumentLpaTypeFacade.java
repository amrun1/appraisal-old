package com.permata.appraisal2.facade;

import com.permata.appraisal2.common.LabelValue;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.DocumentLpaTypeDto;

import java.util.List;
import java.util.Map;

public interface DocumentLpaTypeFacade {

    public List<LabelValue<Long, String>> findLvForLpa(Long activaId, Long appraisalId);

    String getKategoriLpa(Long activaId);

    Map<Long,String> findLvForLpaMandatory(Long activaId, Long appraisalId);

    public List<LabelValue<Long, String>> findLvForSurvey(Long activaId, Long appraisalId);

    public DocumentLpaTypeDto get(Long id);

    public PaginatedList<DocumentLpaTypeDto> find(DocumentLpaTypeDto filter, PagingInfo pagingInfo, Integer jenis);

    public DocumentLpaTypeDto save(DocumentLpaTypeDto dto);

    public void delete(Long id) throws Exception;

//    public PaginatedList<DocumentLpaTypeDto> findListJenis(DocumentLpaTypeDto dto, PagingInfo pagingInfo, Integer jenis);

}