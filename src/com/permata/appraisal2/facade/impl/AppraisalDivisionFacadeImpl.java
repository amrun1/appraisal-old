package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.dao.AppraisalBranchDao;
import com.permata.appraisal2.dao.AppraisalDivisionDao;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.AppraisalDivisionDto;
import com.permata.appraisal2.facade.AppraisalDivisionFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.AppraisalDivision;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AppraisalDivisionFacadeImpl extends FacadeBase<AppraisalDivisionDto, AppraisalDivision> implements AppraisalDivisionFacade {

    @Autowired
    private AppraisalDivisionDao dao;
    @Autowired
    private AppraisalBranchDao cabangDao;

    public PaginatedList<AppraisalDivisionDto> find(AppraisalDivisionDto dto, PagingInfo pagingInfo) {
        PaginatedList<AppraisalDivision> pgList = dao.find(pagingInfo, toEntity(dto));
        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
    }

    @Transactional(readOnly = false)
    public AppraisalDivisionDto save(AppraisalDivisionDto dto) {
        return toWrapper(dao.save(toEntity(dto)));
    }

    public AppraisalDivisionDto get(Long id) {
        return toWrapper(dao.get(id));
    }

    @Transactional(readOnly = false)
    public void delete(Long id) throws Exception {
        dao.delete(id);
    }

    public AppraisalDivisionDto toWrapper(AppraisalDivision e) {
        AppraisalDivisionDto dto = new AppraisalDivisionDto();
        BeanUtils.copyProperties(e, dto);
        // mapping for dept object manually
        if (e.getBranch() != null) {
            dto.setBranchId(e.getBranch().getId());
            dto.setBranchName(e.getBranch().getName());
        }
        return dto;
    }

    public AppraisalDivision toEntity(AppraisalDivisionDto w) {
        AppraisalDivision e = new AppraisalDivision();
        if (w.getId() != null) {
            e = dao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        if (w.getBranchId() != null) {
            e.setBranch(cabangDao.get(w.getBranchId()));
        }
        return e;
    }
}