package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.HolidayDto;
import com.permata.appraisal2.model.DocumentLpaType;

import java.util.List;

public interface DocumentLpaTypeDao extends DaoBase<DocumentLpaType, Long> {

    public List<DocumentLpaType> findByActiva(Long id, Boolean survey, Long appraisalId);

    public List<DocumentLpaType> findByAppraisal(Long idAppraisal, Boolean survey);

    public PaginatedList<DocumentLpaType> find(PagingInfo pagingInfo, DocumentLpaType filter, OrderSpec order, Integer jenis);
}