package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.dao.FlowListDao;
import com.permata.appraisal2.dto.FlowListDto;
import com.permata.appraisal2.facade.FlowListFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.FlowList;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class FlowListFacadeImpl extends FacadeBase<FlowListDto, FlowList> implements FlowListFacade {

    @Autowired
    private FlowListDao dao;

    @Override
    public List<FlowListDto> list(FlowListDto filter) {
        return toListWrapper(dao.find(toEntity(filter)));
    }

    @Override
    public FlowListDto toWrapper(FlowList e) {
        FlowListDto w = new FlowListDto();
        BeanUtils.copyProperties(e, w);
        return w;
    }

    @Override
    public FlowList toEntity(FlowListDto w) {
        FlowList e = new FlowList();
        if (e.getId() != null) {
            e = dao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        return e;
    }
}
