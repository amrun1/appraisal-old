package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.MobileSendSurveyDto;
import com.permata.appraisal2.model.MobileSendSurvey;

public interface MobileSendSurveyDao extends DaoBase<MobileSendSurvey, String> {

    public PaginatedList<MobileSendSurveyDto> findPdaValuer(PagingInfo pgInfo, MobileSendSurveyDto dto);

    public PaginatedList<MobileSendSurveyDto> findPdaSurvey(PagingInfo pgInfo, MobileSendSurveyDto dto);
}