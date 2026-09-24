package com.permata.appraisal2.facade;

import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.MobileSendSurveyDto;

/**
 * Created by Abdul Rochman on 5/3/2017.
 */
public interface MobileSendSurveyFacade {

//    public PaginatedList<MobileSendSurveyDto> getPdaSurvey(MobileSendSurveyDto dto, PagingInfo pagingInfo, OrderSpec order);
    public PaginatedList<MobileSendSurveyDto> getPdaSurvey(PagingInfo pagingInfo);

    public PaginatedList<MobileSendSurveyDto> findPdaValuer(PagingInfo pagingInfo, MobileSendSurveyDto dto);

    public PaginatedList<MobileSendSurveyDto> findPdaSurvey(PagingInfo pagingInfo, MobileSendSurveyDto dto);
}
