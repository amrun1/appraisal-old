package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.dao.MobileSendSurveyDao;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.MobileSendSurveyDto;
import com.permata.appraisal2.facade.MobileSendSurveyFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.MobileSendSurvey;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Abdul Rochman on 5/3/2017.
 */
@Service
@Transactional(readOnly = true)
public class MobileSendSurveyFacadeImpl extends FacadeBase<MobileSendSurveyDto, MobileSendSurvey> implements MobileSendSurveyFacade {

    @Autowired
    private MobileSendSurveyDao dao;

//    public PaginatedList<MobileSendSurveyDto> getPdaSurvey(MobileSendSurveyDto dto, PagingInfo pagingInfo, OrderSpec order){
    public PaginatedList<MobileSendSurveyDto> getPdaSurvey(PagingInfo pagingInfo){
        MobileSendSurveyDto dto = new MobileSendSurveyDto();
//        order = OrderSpec.create("orderNo", OrderSpec.OrderType.desc);
        dto.setStatus("NEW");
        PaginatedList<MobileSendSurvey> pgList = dao.find(pagingInfo, toEntity(dto));
        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
    }



//    @Override
//    public PaginatedList<MobileSendSurveyDto> findPda(PagingInfo pagingInfo, MobileSendSurveyDto dto){
//        PaginatedList<MobileSendSurvey> pgList = dao.findPda(pagingInfo, dto);
//        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
//    }

    public PaginatedList<MobileSendSurveyDto> findPdaValuer(PagingInfo pagingInfo, MobileSendSurveyDto filter) {
        return dao.findPdaValuer(pagingInfo, filter);
    }

    public PaginatedList<MobileSendSurveyDto> findPdaSurvey(PagingInfo pagingInfo, MobileSendSurveyDto filter) {
        return dao.findPdaSurvey(pagingInfo, filter);
    }

    @Override
    public MobileSendSurveyDto toWrapper(MobileSendSurvey e) {
        MobileSendSurveyDto w = new MobileSendSurveyDto();
        BeanUtils.copyProperties(e, w);
        return w;
    }

    @Override
    public MobileSendSurvey toEntity(MobileSendSurveyDto w) {
        MobileSendSurvey e = new MobileSendSurvey();
//        if (StringUtils.isNotBlank(w.getOrderNo())) {
//            e = dao.get(w.getOrderNo());
//        }
        if (StringUtils.isNotBlank(w.getStatus())){
            e= dao.get(w.getStatus());
        }
        BeanUtils.copyProperties(w, e);
        return e;
    }

}
