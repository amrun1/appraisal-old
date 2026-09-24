package com.permata.appraisal2.facade;

import com.permata.appraisal2.dto.PriorityCategoryDto;

import java.util.List;
import java.util.Map;

public interface PriorityCategoryFacade {
    public List<PriorityCategoryDto> list();
    public Map<String, Object> getReference();

}