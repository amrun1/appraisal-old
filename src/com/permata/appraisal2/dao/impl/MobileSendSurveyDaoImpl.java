package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.MobileSendSurveyDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.DataCommonTableDto;
import com.permata.appraisal2.dto.MobileSendSurveyDto;
import com.permata.appraisal2.model.MobileSendSurvey;
import org.hibernate.Criteria;
import org.hibernate.internal.SQLQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;

@Repository
public class MobileSendSurveyDaoImpl extends DaoBaseImpl<MobileSendSurvey, String> implements MobileSendSurveyDao {

    @Override
    public PaginatedList<MobileSendSurveyDto> findPdaValuer(PagingInfo pagingInfo, MobileSendSurveyDto dto) {
        MobileSendSurvey mobileSendSurvey = new MobileSendSurvey();

        BeanUtils.copyProperties(dto, mobileSendSurvey);

        Criteria criteria = createCriteria(mobileSendSurvey, null);

         String hql="select d.orderNo as orderNo, d.collateralId as collateralId, (select name from Person where userId = d.surveyCode) as surveyCode, " +
                 "(select name from Person where userId = d.createdBy) as valuer, d.contactName as contactName, d.contactPhone as contactPhone, " +
                 "d.surveyAddress as surveyAddress, d.debiturName as debiturName, d.objectType as objectType, d.notes as notes, d.jenisAppraisal as jenisAppraisal, " +
                 "d.perumahanApartemen as perumahanApartemen, d.clusterTower as clusterTower, d.blockLantai as blockLantai, d.nomor as nomor, d.rt as rt, d.rw as rw, " +
                 "d.status as status , (select to_char(tanggal, 'dd-MM-yyyy HH24:MI:SS') from SurveyCommon c where c.detail.id = b.id) as surveyDateString " +
                " from DataCommon a, DataDetail b, MobileSendSurvey d " +
                " where a.id =b.dataCommon.id" +
                " and d.orderNo = a.orderNumber ";
        String hql2=" from DataCommon a, DataDetail b, MobileSendSurvey d " +
                " where a.id =b.dataCommon.id" +
                " and d.orderNo = a.orderNumber ";//a inner join DataDetail b on b.dataCommon.id = a.id inner join SurveyCommon c on b.id = c.detail.id inner join MobileSendSurvey d on d.orderNo = a.orderNumber ";

        if (dto.getCreatedDate()!=null && dto.getClosedDate()!=null){
            String createdDate = new SimpleDateFormat("yyyy-MM-dd").format(dto.getCreatedDate())+" 00:00:00";
            String closedDate = new SimpleDateFormat("yyyy-MM-dd").format(dto.getClosedDate())+" 23:59:59";
            hql += " and d.createdDate between '"+createdDate+"' and '"+closedDate+"' ";
            hql2 += " and d.createdDate between '"+createdDate+"' and '"+closedDate+"' ";
        }

        if(dto.getClosedBy()!=null){
            hql += String.format(" and d.createdBy = '%s' ", dto.getClosedBy());
            hql2 += String.format(" and d.createdBy = '%s' ", dto.getClosedBy());
        }

        return find(pagingInfo, createQuery(hql), createQuery("select count(a.id) " + hql2) , MobileSendSurveyDto.class);

    }

    @Override
    public PaginatedList<MobileSendSurveyDto> findPdaSurvey(PagingInfo pagingInfo, MobileSendSurveyDto dto) {
        MobileSendSurvey mobileSendSurvey = new MobileSendSurvey();

        BeanUtils.copyProperties(dto, mobileSendSurvey);

        Criteria criteria = createCriteria(mobileSendSurvey, null);
        String hql="select d.orderNo as orderNo, d.collateralId as collateralId, (select name from Person where userId = d.surveyCode) as surveyCode, (select name from Person where userId = d.createdBy) as valuer, d.contactName as contactName, d.contactPhone as contactPhone, d.surveyAddress as surveyAddress, d.debiturName as debiturName, d.objectType as objectType, d.notes as notes, d.jenisAppraisal as jenisAppraisal, d.perumahanApartemen as perumahanApartemen, d.clusterTower as clusterTower, d.blockLantai as blockLantai, d.nomor as nomor, d.rt as rt, d.rw as rw, d.status as status , to_char(c.tanggal, 'dd-MM-yyyy HH24:MI:SS') as surveyDateString " +
                " from DataCommon a, DataDetail b, SurveyCommon c, MobileSendSurvey d " +
                " where a.id =b.dataCommon.id" +
                " and b.id = c.detail.id" +
                " and d.orderNo = a.orderNumber ";
        String hql2=" from DataCommon a, DataDetail b, SurveyCommon c, MobileSendSurvey d " +
                " where a.id =b.dataCommon.id" +
                " and b.id = c.detail.id" +
                " and d.orderNo = a.orderNumber ";//a inner join DataDetail b on b.dataCommon.id = a.id inner join SurveyCommon c on b.id = c.detail.id inner join MobileSendSurvey d on d.orderNo = a.orderNumber ";

        if (dto.getCreatedDate()!=null && dto.getClosedDate()!=null){
            String createdDate = new SimpleDateFormat("yyyy-MM-dd").format(dto.getCreatedDate())+" 00:00:00";
            String closedDate = new SimpleDateFormat("yyyy-MM-dd").format(dto.getClosedDate())+" 23:59:59";
            hql += " and c.tanggal between '"+createdDate+"' and '"+closedDate+"' ";
            hql2 += " and c.tanggal between '"+createdDate+"' and '"+closedDate+"' ";
        }

        if(dto.getClosedBy()!=null){
            hql += String.format(" and d.surveyCode = '%s' ", dto.getClosedBy());
            hql2 += String.format(" and d.surveyCode = '%s' ", dto.getClosedBy());
        }

        return find(pagingInfo, createQuery(hql), createQuery("select count(a.id) " + hql2) , MobileSendSurveyDto.class);

    }




}