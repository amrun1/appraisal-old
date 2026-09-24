package com.permata.appraisal2.facade;

import com.permata.appraisal2.dto.MataUangDto;

import java.util.List;

public interface MataUangFacade {
    public List<MataUangDto> list(MataUangDto filter);
}