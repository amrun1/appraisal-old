package com.permata.appraisal2.facade;

import com.permata.appraisal2.dto.SurveyStockDto;

public interface SurveyStockFacade {

    public SurveyStockDto save(SurveyStockDto dto);
    public SurveyStockDto get (Long id);


    public SurveyStockDto getByDetail(Long idDetail);

}
