package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.common.LabelValue;
import com.permata.appraisal2.dao.BranchDao;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.AppraisalBranchDto;
import com.permata.appraisal2.facade.BranchFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.AppraisalBranch;
import com.permata.appraisal2.model.Branch;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BranchFacadeImpl extends FacadeBase<AppraisalBranchDto, Branch> implements BranchFacade {

    @Autowired
    private BranchDao dao;

    @Override
    public List<LabelValue<Long, String>> findLv() {
        List<LabelValue<Long, String>> result = new ArrayList<LabelValue<Long, String>>();
        List<Branch> list = dao.find(new Branch());
        for (Branch row : list) {
            result.add(new LabelValue<Long, String>(row.getId(), row.getName()));
        }
        return result;
    }

    public PaginatedList<AppraisalBranchDto> find(AppraisalBranchDto dto, PagingInfo pagingInfo) {
        PaginatedList<Branch> pgList = dao.find(pagingInfo, toEntity(dto));
        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
    }

    public List<AppraisalBranchDto> getAll(){
        List<AppraisalBranchDto> result = new ArrayList<AppraisalBranchDto>();
        List<Branch> list = dao.find(new Branch(), OrderSpec.create("name", OrderSpec.OrderType.asc));
        for (Branch row : list) {
            result.add(toItemWrapper(row));
        }
        return result;
    }

    public AppraisalBranchDto get(Long id) {
        return toWrapper(dao.get(id));
    }

    @Transactional(readOnly = false)
    public AppraisalBranchDto save(AppraisalBranchDto dto) {
        return toWrapper(dao.save(toEntity(dto)));
    }

    @Transactional(readOnly = false)
    public void delete(Long id) throws Exception {
        dao.delete(id);
    }

    private AppraisalBranchDto toItemWrapper(Branch e) {
        AppraisalBranchDto w = new AppraisalBranchDto();
        BeanUtils.copyProperties(e, w);
        w.setId(e.getId());
        w.setName(e.getName());
        return w;
    }

    @Override
    public AppraisalBranchDto toWrapper(Branch e) {
        AppraisalBranchDto dto = new AppraisalBranchDto();
        BeanUtils.copyProperties(e, dto);
        return dto;
    }

    @Override
    public Branch toEntity(AppraisalBranchDto w) {
        Branch e = new Branch();
        if (w.getId() != null) {
            e = dao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        return e;
    }

}