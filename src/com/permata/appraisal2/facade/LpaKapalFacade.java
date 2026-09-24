package com.permata.appraisal2.facade;

import com.permata.appraisal2.dto.LpaKapalDto;

public interface LpaKapalFacade {

    public LpaKapalDto create(Long commonId);

    public LpaKapalDto save(LpaKapalDto e, String userId) throws Exception;

    public LpaKapalDto getByUmum(Long lpaId);

    public void delete(Long id) throws Exception;
}