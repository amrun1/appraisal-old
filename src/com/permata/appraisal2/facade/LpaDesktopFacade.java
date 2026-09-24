package com.permata.appraisal2.facade;

import com.permata.appraisal2.dto.LpaDesktopDto;

@Deprecated
public interface LpaDesktopFacade {

    public LpaDesktopDto create(Long commonId);

    public LpaDesktopDto save(LpaDesktopDto e);

    public LpaDesktopDto getByUmum(Long lpaId);

    public void delete(Long id) throws Exception;
}