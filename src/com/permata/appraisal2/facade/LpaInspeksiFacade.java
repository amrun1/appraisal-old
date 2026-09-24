package com.permata.appraisal2.facade;

import com.permata.appraisal2.dto.LpaInspeksiDto;
import com.permata.appraisal2.dto.LpaItemInspeksiDto;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by SB on 4/17/2018.
 */
public interface LpaInspeksiFacade {
    LpaInspeksiDto create(Long commonId);
    LpaInspeksiDto saveInspeksi(LpaInspeksiDto dto, String userId) throws Exception;
    LpaInspeksiDto getByUmum(Long lpaId);
    void deleteInspeksi(Long id, String userId) throws Exception;
    LpaItemInspeksiDto saveItemInspeksi(LpaItemInspeksiDto dto, String userId) throws Exception;
    LpaItemInspeksiDto getItemInspeksi(Long id);
    List<LpaItemInspeksiDto> getListItemInspeksiByInspeksiId(Long lpaInspeksiId, String typeGoods);
    List<LpaItemInspeksiDto> getListSurveyItemInspeksiByCommonId(Long commonId, String typeGoods);
    void copyItemInspeksiFromSurvey(Long commonId, Long idLpaInspeksi);
    void deleteItemInspeksi(Long id, String userId) throws Exception;
    Boolean checkSurveyStatusData(Long commonId);
}
