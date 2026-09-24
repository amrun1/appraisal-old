package com.permata.appraisal2.facade;

import com.permata.appraisal2.dto.SurveyRvDto;

public interface SurveyRvFacade {
    public SurveyRvDto get(Long id);

    public SurveyRvDto getByDetail(Long detailId);

    public void delete(Long id) throws Exception ;

    public SurveyRvDto save(SurveyRvDto e);

    public void ambilDariMobile(Long commonId, Long detailId, String orderNum);
}