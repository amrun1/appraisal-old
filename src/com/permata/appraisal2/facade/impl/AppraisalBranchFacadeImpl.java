package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.common.LabelValue;
import com.permata.appraisal2.dao.AppraisalBranchDao;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.AppraisalBranchDto;
import com.permata.appraisal2.facade.AppraisalBranchFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.AppraisalBranch;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class AppraisalBranchFacadeImpl extends FacadeBase<AppraisalBranchDto, AppraisalBranch>
        implements AppraisalBranchFacade {

    @Autowired
    private AppraisalBranchDao dao;

    public PaginatedList<AppraisalBranchDto> find(AppraisalBranchDto dto, PagingInfo pagingInfo) {
        PaginatedList<AppraisalBranch> pgList = dao.find(pagingInfo, toEntity(dto));
        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
    }

    @Transactional(readOnly = false)
    public AppraisalBranchDto save(AppraisalBranchDto dto) {
        return toWrapper(dao.save(toEntity(dto)));
    }

    public AppraisalBranchDto get(Long id) {
        return toWrapper(dao.get(id));
    }

    @Transactional(readOnly = false)
    public void delete(Long id) throws Exception {
        dao.delete(id);
    }

    @Override
    public List<LabelValue<Long, String>> findLv(AppraisalBranchDto dto) {
        List<LabelValue<Long, String>> result = new ArrayList<LabelValue<Long, String>>();
        List<AppraisalBranch> datas = dao.find(toEntity(dto));
        for (AppraisalBranch row : datas) {
            result.add(new LabelValue<Long, String>(row.getId(), row.getName()));
        }
        return result;
    }

    @Override
    public AppraisalBranchDto toWrapper(AppraisalBranch e) {
        AppraisalBranchDto dto = new AppraisalBranchDto();
        BeanUtils.copyProperties(e, dto);
        return dto;
    }

    @Override
    public AppraisalBranch toEntity(AppraisalBranchDto w) {
        AppraisalBranch e = new AppraisalBranch();
        if (w.getId() != null) {
            e = dao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        return e;
    }

}