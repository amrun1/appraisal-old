package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.*;
import com.permata.appraisal2.model.DataCommon;
import org.hibernate.Criteria;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface DataCommonDao extends DaoBase<DataCommon, Long> {

    public PaginatedList<DataCommonTableDto> findForTables(PagingInfo pagingInfo, DataCommonTableDto filter);

    public List<PendingJobsByUsersDto> findPendingJobsByUsers();

    public List<PendingJobsByUsersDto> findPendingJobsByCustomers();

    public int updateRejectToMarketing(DataCommon dataCommonId);

    public DataCommon getByLpaId(Long lpaId);

    public Long copyDebiturFromProcedure(Long id,String createdBy,String colleteralId);


    public Integer getJumlahHariPenyelesaian(Date dateSubmit, Date dateClose) ;

    PaginatedList<DataCommon> findOrderPending(PagingInfo pgInfo, DataCommon filter, OrderSpec order);

    public List<DataCommon> getIntraDay(Timestamp submitDate, Timestamp closedDate);

//    public List<ViewReportJenisJaminanDto> getStatusJaminan(Timestamp submitDate, Timestamp closedDate);

    public List<TypeObjectViewDto> getTypeObject(Timestamp submitDate, Timestamp closedDate);

     List<Object[]> getReportStatusJaminan(String columnName, String crossTabColumn, Timestamp submitDate, Timestamp closedDate, String[] groupMonth, List<StatusJaminanDto> statusJaminanDtoList);



}