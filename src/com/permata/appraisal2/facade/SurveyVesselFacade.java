package com.permata.appraisal2.facade;

import com.permata.appraisal2.dto.SurveyVesselDto;

public interface SurveyVesselFacade {

    public SurveyVesselDto getByDetail(Long detailId);

    public SurveyVesselDto save(SurveyVesselDto dto);

}