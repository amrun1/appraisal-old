package com.permata.appraisal2.facade;

import com.permata.appraisal2.dto.LpaRvDto;

public interface LpaRvFacade {

    public LpaRvDto create(Long commonId);

    public LpaRvDto save(LpaRvDto e , String userId) throws Exception;

    public LpaRvDto getByUmum(Long lpaId);

    public void delete(Long id) throws Exception ;

}