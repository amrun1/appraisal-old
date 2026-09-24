package com.permata.appraisal2.facade;

import com.permata.appraisal2.dto.SurveyInspeksiDto;

public interface SurveyInspeksiFacade {

    public SurveyInspeksiDto save(SurveyInspeksiDto dto);
    public SurveyInspeksiDto get(Long id);


    public SurveyInspeksiDto getByDetail(Long idDetail);

}
