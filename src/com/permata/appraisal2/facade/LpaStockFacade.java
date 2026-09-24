package com.permata.appraisal2.facade;

import com.permata.appraisal2.dto.LpaStockDto;

import java.util.List;

public interface LpaStockFacade {

    public void delete(Long id) throws Exception;

    public LpaStockDto getByUmum(Long lpaId);

    public LpaStockDto create(Long commonId);

    public LpaStockDto save(LpaStockDto dto, String userId) throws Exception;

}