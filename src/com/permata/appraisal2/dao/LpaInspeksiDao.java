package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.model.LpaInspeksi;

/**
 * Created by SB on 4/17/2018.
 */
public interface LpaInspeksiDao extends DaoBase<LpaInspeksi, Long> {
    LpaInspeksi getByUmum(Long lpaUmum);
    void copyDataItemInspeksiFromSurvey(Long idLpaInspeksi, Long idSurveyInspeksi);
}
