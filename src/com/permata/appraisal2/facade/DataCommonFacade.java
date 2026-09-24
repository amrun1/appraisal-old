package com.permata.appraisal2.facade;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.common.LabelValue;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.*;
import com.permata.appraisal2.model.DataCommon;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface DataCommonFacade {
    public PaginatedList<DataCommonDto> findOrderFailedSendSource(PagingInfo pagingInfo, OrderSpec order);
    public List<DataCommonDto> findOrderFailedSendSourceList(PagingInfo pagingInfo, OrderSpec order);
    public PaginatedList<DataCommonDto> findOrderFailedSendSourceCarm(PagingInfo pagingInfo, OrderSpec order);

    public PaginatedList<DataCommonDto> findOrderFailedRejectSource(PagingInfo pagingInfo, OrderSpec order);
    public PaginatedList<DataCommonDto> findOrderFailedRejectSourceCarm(PagingInfo pagingInfo, OrderSpec order);

    public String getByCommonIdJenis (Long idCommon);


    public PaginatedList<DataCommonDto> find(DataCommonDto dto, PagingInfo pagingInfo, OrderSpec order);



    public PaginatedList<DataCommonDto> findForValuerAndApprove(DataCommonDto dto, PagingInfo pagingInfo, OrderSpec order,String userId);

    public PaginatedList<DataCommonDto> findColNull(DataCommonDto dto, PagingInfo pagingInfo, OrderSpec order);



    PaginatedList<DataCommonDto> findOrderPending(DataCommonDto dto, PagingInfo pagingInfo, OrderSpec order);

    public DataCommonDto save(DataCommonDto dto);

    public List<LabelValue<String, String>> getAllPic();

    public void updatePic(Long id,String picName);

    public DataCommonDto get(Long id);

    public DataCommonDto getByLpaId(Long lpaId);

    public AppAdminDataCommonDto getByAdmin(Long id);

    public AppValuerDataCommonDto getByValuer(Long id);

    public InqueryDataCommonDto getByInquery(Long id);

    public AppValuerDataCommonDto toWrapperByValuer(DataCommon e);

    public void delete(Long id) throws Exception;

    public void reject(Long id, String rejectTo, String message, String userId);

    public Boolean rejectFromValuer(Long id, String rejectTo, String message, String userId);

    public void submit(Long id, Long sendTo, String message, String userId)  throws Exception;

    public void submitApproval(DataCommonDto dto, String message, String userId, String to, PersonRoleDto role) throws Exception;

    public Boolean createCarmOrPlus(DataCommonDto dto, String message, String userId, String to, PersonRoleDto role)  throws Exception;

    public void submitToValuer(AppAdminDataCommonDto dto, String message, String userId, String to)  throws Exception ;

    public void submitToApproval(DataCommonDto dto, String message, String userId, String to)  throws Exception;

    public void submitSurveyToValuer(DataCommonDto dto, String message, String userId, String to) throws Exception;

    public void pendingAdmin(DataCommonDto dto, String message, String userId) throws Exception;

    public void claim(Long id, String userId, String description, Integer param)  throws Exception;

    public void reClaim(Long id, String userId) throws Exception ;

    public void reClaimDrop(Long id, String userId,String posisi) throws Exception ;

    public void reClaimOrderPending(Long id, String userId)  throws Exception;

    public void claimByAdmin(Long id, String userId, String description, String position, Integer param) throws Exception;

    public void claimByValuer(Long id, String userId) throws Exception;

    public Map<String, Object> getReference(int status, String flowFrom);

    public boolean createSurvey(Long detailId, String userId);

    public AppValuerDataCommonDto saveSurvey(AppValuerDataCommonDto dto, PersonLoginDto person) throws Exception;

    public PaginatedList<DataCommonTableDto> findForTables(PagingInfo pagingInfo, DataCommonTableDto filter);

    public List<PendingJobsByUsersDto> findPendingJobsByUsers();

    public List<DataCommonDto> getIntraDay(Timestamp submitDate, Timestamp closedDate);

//    public List<ViewReportJenisJaminanDto> getStatusJaminan(Timestamp submitDate, Timestamp closedDate);

    public List<TypeObjectViewDto> getTypeObject(Timestamp submitDate, Timestamp closedDate);

    public List<PendingJobsByUsersDto> findPendingJobsByCustomers();

    // public void sendToSource(Long id);


    public void sendToMobile(Long commonId, String surveyorId, String valuerId);

    public MobileSendSurveyDto getSendToMoble(String orderNo);

    public Long copyDebiturFromProcedure(Long id, String createdBy,String colateralId);


    public Integer getJumlahHari(Date start,Date end);
    Boolean checkUserTasklist(String userId, Long lpaId, Long surveyId);

    public void sendToRwa(Long id);

    public void sendToCarm(Long id);

    public void sendToPlus(Long id);

    public void sendRejectPlus(Long id);

    public void sendRejectCarm(Long id);

//    public void sendRejectPlus(Long id,String userIdReject);

    /**
     * testing send file ke PLPUS
     */
    public void sendAttactmentToPlus(Long lpaId);

    List<Object[]> getReportStatusJaminan(Calendar submitDate, Calendar closedDate, List<AppraisalBranchDto> branchs, List<StatusJaminanDto> statusJaminanDtoList);

    Boolean checkUserTasklistByCommonId(String userId, Long commonId);

}