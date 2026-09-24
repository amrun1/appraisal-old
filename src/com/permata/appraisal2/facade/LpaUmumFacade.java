package com.permata.appraisal2.facade;

import com.permata.appraisal2.dto.LpaUmumDto;
import com.permata.appraisal2.model.LpaUmum;

public interface LpaUmumFacade {

    public LpaUmumDto create(Long commonId);

    public LpaUmumDto get(Long id);

    public void delete(Long id) throws Exception;

    public LpaUmumDto save(LpaUmumDto dto);

    public LpaUmumDto toWrapper(LpaUmum e);

    public LpaUmum toEntity(LpaUmumDto w);
}