package com.permata.appraisal2.facade;

import com.permata.appraisal2.dto.SurveyMesinDto;
import com.permata.appraisal2.dto.SurveyMesinLampiranDto;

import java.util.List;

public interface SurveyMesinFacade {

    public SurveyMesinDto getByDetail(Long detailId);

    public SurveyMesinDto save(SurveyMesinDto dto);

    public SurveyMesinLampiranDto getLampiran(Long id);

    public List<SurveyMesinLampiranDto> findLampiran(Long idSurveyMesin);

    public void deleteLampiran(Long id) throws Exception;

    public SurveyMesinLampiranDto saveLampiran(SurveyMesinLampiranDto dto);

}