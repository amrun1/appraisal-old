package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.common.LabelValue;
import com.permata.appraisal2.dao.PriorityCategoryDao;
import com.permata.appraisal2.dto.PriorityCategoryDto;
import com.permata.appraisal2.facade.PriorityCategoryFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.PriorityCategory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class PriorityCategoryFacadeImpl extends FacadeBase<PriorityCategoryDto, PriorityCategory> implements PriorityCategoryFacade {

    @Autowired
    private PriorityCategoryDao dao;

    @Override
    public List<PriorityCategoryDto> list() {
        return toListWrapper(dao.find(new PriorityCategory()));
    }

    @Override
    public Map<String, Object> getReference() {
        Map<String, Object> result = new HashMap<String, Object>();
        List<PriorityCategory> dataList = dao.find(new PriorityCategory());
        List<LabelValue<Long, String>> lvGroupParameter = new ArrayList<LabelValue<Long, String>>();
        for (PriorityCategory row : dataList) {
            lvGroupParameter.add(new LabelValue<Long, String>(row.getId(),row.getNama()));
        }
        result.put("lvPriortyCategory", lvGroupParameter);

        return result;
    }

    @Override
    public PriorityCategoryDto toWrapper(PriorityCategory e) {
        PriorityCategoryDto w = new PriorityCategoryDto();
        BeanUtils.copyProperties(e, w);
        return w;
    }

    @Override
    public PriorityCategory toEntity(PriorityCategoryDto priorityCategoryDto) {
        return null;
    }
}