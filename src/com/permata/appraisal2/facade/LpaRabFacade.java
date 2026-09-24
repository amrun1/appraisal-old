package com.permata.appraisal2.facade;

import com.permata.appraisal2.dto.LpaRabDto;

public interface LpaRabFacade {

    public LpaRabDto create(Long commonId);

    public LpaRabDto save(LpaRabDto e, String userId) throws Exception;

    public LpaRabDto getByUmum(Long lpaId);

    public void delete(Long id) throws Exception;
}