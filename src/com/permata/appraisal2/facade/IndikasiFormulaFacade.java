package com.permata.appraisal2.facade;

import com.permata.appraisal2.common.LabelValue;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.AppraisalBranchDto;
import com.permata.appraisal2.dto.IndikasiFormulaDto;

import java.util.List;

public interface IndikasiFormulaFacade {

    public List<LabelValue<String, String>> findType();

    public PaginatedList<IndikasiFormulaDto> find(IndikasiFormulaDto dto, PagingInfo pagingInfo);

    public IndikasiFormulaDto save(IndikasiFormulaDto dto);

    public IndikasiFormulaDto get(Long id);

    public void delete(Long id) throws Exception;
}
