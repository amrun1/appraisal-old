package com.permata.appraisal2.facade;

import com.permata.appraisal2.dto.LpaMesinDto;
import com.permata.appraisal2.dto.LpaMesinLampiranDto;

import java.util.List;

public interface LpaMesinFacade {

    public LpaMesinDto create(Long commonId);

    public LpaMesinDto save(LpaMesinDto e, String userId) throws Exception;

    public LpaMesinDto getByUmum(Long lpaId);

    public void delete(Long id) throws Exception;

    public List<LpaMesinLampiranDto> findLampiran(Long idLpaMesin);

    public LpaMesinLampiranDto getLampiran(Long idLampiran);

    public LpaMesinLampiranDto saveLampiran(LpaMesinLampiranDto dto, String userId) throws Exception;

    public void deleteLampiran(Long idLampiran) throws Exception;
}