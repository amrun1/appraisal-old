package com.permata.appraisal2.facade;

import com.permata.appraisal2.dto.LpaBvDto;

public interface LpaBvFacade {

    public LpaBvDto create(Long commonId);

    public LpaBvDto save(LpaBvDto e, String userId) throws Exception;

    public LpaBvDto getByUmum(Long lpaId);

}