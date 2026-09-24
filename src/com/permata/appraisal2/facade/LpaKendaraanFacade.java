package com.permata.appraisal2.facade;

import com.permata.appraisal2.dto.LpaItemKendaraanDto;
import com.permata.appraisal2.dto.LpaKendaraanDto;

import java.util.List;

public interface LpaKendaraanFacade {

    public void delete(Long id) throws Exception;

    public LpaKendaraanDto getByUmum(Long lpaId);

    public LpaKendaraanDto create(Long commonId);

    public LpaKendaraanDto save(LpaKendaraanDto dto, String userId)  throws Exception;

    public List<LpaItemKendaraanDto> listItemKendaraan(Long idLpa);

    public LpaItemKendaraanDto getItem(Long id);

    public LpaItemKendaraanDto saveKendaraan(LpaItemKendaraanDto dto, String userId) throws Exception;

    public void deleteKendaraan(Long id) throws Exception;
}