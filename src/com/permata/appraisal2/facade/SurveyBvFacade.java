package com.permata.appraisal2.facade;

import com.permata.appraisal2.dto.SurveyBvDto;

public interface SurveyBvFacade {

    public SurveyBvDto get(Long id);

    public SurveyBvDto getByDetail(Long detailId);

    public void delete(Long id) throws Exception;

    public SurveyBvDto save(SurveyBvDto e);

    public void ambilDariMobile(Long commonId, Long detailId, String orderNum);
}
