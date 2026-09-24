package com.permata.appraisal2.facade;

import com.permata.appraisal2.dto.*;

import java.util.List;

public interface LpaFacade {

    public LpaInternalCreateDto saveTanahBangunan(LpaInternalCreateDto dto, String userId) throws Exception;

    public LpaDokumenBangunanDto saveDokumenBangunan(LpaDokumenBangunanDto dto);

    public LpaDokumenTanahDto saveDokumenTanah(LpaDokumenTanahDto dto);

    public void deleteDokumenBangunan(Long id) throws Exception;

    public void deleteDokumenTanah(Long id) throws Exception;

    public void deleteLpa(Long id) throws Exception;

    public LpaInternalCreateDto createTanahBangungan(Long dataCommonId);

    public LpaInternalCreateDto get(Long id);

    public LpaDokumenTanahDto getTanah(Long id);

    public List<LpaDokumenBangunanDto> listDokumenBangunan(Long commonId);

    public List<LpaDokumenTanahDto> listDokumenTanah(Long commonId);

    public LpaImageDto getImage(Long id);

    public LpaImageDto saveImage(LpaImageDto dto);

    public List<LpaImageDto> findImage(Long surveyId, Long lpaId);

    public void deleteImage(Long id) throws Exception;

    public List<SurveyImageDto> getDocOther(Long idUmum);
}