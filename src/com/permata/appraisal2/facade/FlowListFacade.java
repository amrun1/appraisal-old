package com.permata.appraisal2.facade;

import com.permata.appraisal2.dto.FlowListDto;

import java.util.List;

public interface FlowListFacade {
    public List<FlowListDto> list(FlowListDto filter);
}
