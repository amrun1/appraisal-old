package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.dto.SurveyImageDto;
import com.permata.appraisal2.model.LpaUmum;

import java.util.List;

public interface LpaUmumDao extends DaoBase<LpaUmum, Long> {

    public boolean isDetailHasLpa(Long detailId);

    public boolean isDraftLpa(Long detailId);

    public LpaUmum getByDetail(Long detailId);

    public List<SurveyImageDto> getDocOther(Long idUmum);

//    public LpaUmum getByLpaId(Long lpaId);
}