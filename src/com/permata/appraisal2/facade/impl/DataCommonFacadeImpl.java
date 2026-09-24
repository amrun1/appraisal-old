package com.permata.appraisal2.facade.impl;

import java.io.File;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.holders.IntegerWrapperHolder;
import javax.xml.rpc.holders.StringHolder;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.common.LabelValue;
import com.permata.appraisal2.common.LabelValueId;
import com.permata.appraisal2.dao.AppraisalBranchDao;
import com.permata.appraisal2.dao.BranchDao;
import com.permata.appraisal2.dao.DataCommonDao;
import com.permata.appraisal2.dao.DataDetailDao;
import com.permata.appraisal2.dao.DataMessageDao;
import com.permata.appraisal2.dao.DataRejectDao;
import com.permata.appraisal2.dao.GroupDao;
import com.permata.appraisal2.dao.IloanDao;
import com.permata.appraisal2.dao.IloanProgresDao;
import com.permata.appraisal2.dao.JenisAppraisalDao;
import com.permata.appraisal2.dao.KjppDao;
import com.permata.appraisal2.dao.LpaBangunanDao;
import com.permata.appraisal2.dao.LpaItemKendaraanDao;
import com.permata.appraisal2.dao.LpaMesinLampiranDao;
import com.permata.appraisal2.dao.LpaTanahDao;
import com.permata.appraisal2.dao.LpaUmumDao;
import com.permata.appraisal2.dao.MobileSendSurveyDao;
import com.permata.appraisal2.dao.ParameterDao;
import com.permata.appraisal2.dao.PersonDao;
import com.permata.appraisal2.dao.SendToSourceLogAttachmenDao;
import com.permata.appraisal2.dao.SendToSourceLogDao;
import com.permata.appraisal2.dao.StatusJaminanDao;
import com.permata.appraisal2.dao.StatusNasabahDao;
import com.permata.appraisal2.dao.SurveyCommonDao;
import com.permata.appraisal2.dao.SurveyImageDao;
import com.permata.appraisal2.dao.TableSequenceDao;
import com.permata.appraisal2.dao.TaskHistoryDao;
import com.permata.appraisal2.dao.ZipCodeDao;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.AppAdminDataCommonDto;
import com.permata.appraisal2.dto.AppValuerDataCommonDto;
import com.permata.appraisal2.dto.AppraisalBranchDto;
import com.permata.appraisal2.dto.DataCommonDto;
import com.permata.appraisal2.dto.DataCommonTableDto;
import com.permata.appraisal2.dto.DataDetailDto;
import com.permata.appraisal2.dto.DataHistoryDto;
import com.permata.appraisal2.dto.DataMessageDto;
import com.permata.appraisal2.dto.InqueryDataCommonDto;
import com.permata.appraisal2.dto.LpaUmumDto;
import com.permata.appraisal2.dto.MobileSendSurveyDto;
import com.permata.appraisal2.dto.PendingJobsByUsersDto;
import com.permata.appraisal2.dto.PersonLoginDto;
import com.permata.appraisal2.dto.PersonRoleDto;
import com.permata.appraisal2.dto.StatusJaminanDto;
import com.permata.appraisal2.dto.TypeObjectViewDto;
import com.permata.appraisal2.facade.DataAttactmentFacade;
import com.permata.appraisal2.facade.DataCommonFacade;
import com.permata.appraisal2.facade.DataDetailFacade;
import com.permata.appraisal2.facade.SystemParamFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.AppraisalBranch;
import com.permata.appraisal2.model.DataCommon;
import com.permata.appraisal2.model.DataDetail;
import com.permata.appraisal2.model.DataMessage;
import com.permata.appraisal2.model.DataReject;
import com.permata.appraisal2.model.DocumentLpaType;
import com.permata.appraisal2.model.Iloan;
import com.permata.appraisal2.model.IloanProgres;
import com.permata.appraisal2.model.JenisAppraisal;
import com.permata.appraisal2.model.LpaBangunan;
import com.permata.appraisal2.model.LpaBv;
import com.permata.appraisal2.model.LpaItemKendaraan;
import com.permata.appraisal2.model.LpaKapal;
import com.permata.appraisal2.model.LpaKendaraan;
import com.permata.appraisal2.model.LpaMesin;
import com.permata.appraisal2.model.LpaMesinLampiran;
import com.permata.appraisal2.model.LpaRab;
import com.permata.appraisal2.model.LpaRv;
import com.permata.appraisal2.model.LpaStock;
import com.permata.appraisal2.model.LpaTanah;
import com.permata.appraisal2.model.LpaTanahBangunan;
import com.permata.appraisal2.model.LpaUmum;
import com.permata.appraisal2.model.MobileSendSurvey;
import com.permata.appraisal2.model.Parameter;
import com.permata.appraisal2.model.Person;
import com.permata.appraisal2.model.PersonLevel;
import com.permata.appraisal2.model.SendToSourceAttachment;
import com.permata.appraisal2.model.SendToSourceLog;
import com.permata.appraisal2.model.StatusJaminan;
import com.permata.appraisal2.model.StatusNasabah;
import com.permata.appraisal2.model.SurveyCommon;
import com.permata.appraisal2.model.SurveyImage;
import com.permata.appraisal2.model.SurveyStatus;
import com.permata.appraisal2.model.TaskHistory;
import com.permata.appraisal2.util.CarmAttachmentClientUtils;
import com.permata.appraisal2.util.CarmClientUtils;
import com.permata.appraisal2.util.EmailUtils;
import com.permata.appraisal2.util.JasperUtils;
import com.permata.appraisal2.util.ListUtils;
import com.permata.appraisal2.util.ParserUtils;
import com.permata.appraisal2.util.PlusClientUtils;
import com.permata.appraisal2.util.SmsUtils;
import com.permata.appraisal2.ws.client.carm.Approval;
import com.permata.appraisal2.ws.client.carm.Approval_L_B_Internal;
import com.permata.appraisal2.ws.client.carm.Approval_Machine_External;
import com.permata.appraisal2.ws.client.carm.Approval_Machine_Internal;
import com.permata.appraisal2.ws.client.carm.Approval_Stock_External;
import com.permata.appraisal2.ws.client.carm.Approval_Stock_Internal;
import com.permata.appraisal2.ws.client.carm.Approval_Vehicle_External;
import com.permata.appraisal2.ws.client.carm.Approval_Vehicle_Internal;
import com.permata.appraisal2.ws.client.carm.Approval_Vessel;
import com.permata.appraisal2.ws.client.carm.Attachment;
import com.permata.appraisal2.ws.client.carm.Catatan_yang_Perlu_Diperhatikan;
import com.permata.appraisal2.ws.client.carm.Catatan_yang_Perlu_Diperhatikan_L_B_Internal;
import com.permata.appraisal2.ws.client.carm.Catatan_yang_Perlu_Diperhatikan_Machine_External;
import com.permata.appraisal2.ws.client.carm.Catatan_yang_Perlu_Diperhatikan_Machine_Internal;
import com.permata.appraisal2.ws.client.carm.Catatan_yang_Perlu_Diperhatikan_Stock_External;
import com.permata.appraisal2.ws.client.carm.Catatan_yang_Perlu_Diperhatikan_Stock_Internal;
import com.permata.appraisal2.ws.client.carm.Catatan_yang_Perlu_Diperhatikan_Vehicle_External;
import com.permata.appraisal2.ws.client.carm.Catatan_yang_Perlu_Diperhatikan_Vehicle_Internal;
import com.permata.appraisal2.ws.client.carm.Catatan_yang_Perlu_Diperhatikan_Vessel;
import com.permata.appraisal2.ws.client.carm.Data_Bangunan;
import com.permata.appraisal2.ws.client.carm.Data_Bangunan_L_B_Internal;
import com.permata.appraisal2.ws.client.carm.Data_Bangunan_item;
import com.permata.appraisal2.ws.client.carm.Data_Bangunan_item_L_B_Internal;
import com.permata.appraisal2.ws.client.carm.Data_Kapal;
import com.permata.appraisal2.ws.client.carm.Data_Kendaraan_item;
import com.permata.appraisal2.ws.client.carm.Data_Kendaraan_item_Vehicle_External;
import com.permata.appraisal2.ws.client.carm.Data_Legalitas_Kapal;
import com.permata.appraisal2.ws.client.carm.Data_Mesin_item;
import com.permata.appraisal2.ws.client.carm.Data_Mesin_item_Machine_Internal;
import com.permata.appraisal2.ws.client.carm.Data_Pembanding;
import com.permata.appraisal2.ws.client.carm.Data_Pembanding_L_B_Internal;
import com.permata.appraisal2.ws.client.carm.Data_Pembanding_Machine_External;
import com.permata.appraisal2.ws.client.carm.Data_Pembanding_Machine_Internal;
import com.permata.appraisal2.ws.client.carm.Data_Pembanding_Vehicle_External;
import com.permata.appraisal2.ws.client.carm.Data_Pembanding_Vehicle_Internal;
import com.permata.appraisal2.ws.client.carm.Data_Tanah;
import com.permata.appraisal2.ws.client.carm.Data_Tanah_L_B_Internal;
import com.permata.appraisal2.ws.client.carm.Data_Tanah_item;
import com.permata.appraisal2.ws.client.carm.Data_Tanah_item_L_B_Internal;
import com.permata.appraisal2.ws.client.carm.Document_Detail;
import com.permata.appraisal2.ws.client.carm.Document_Detail_Stock_External;
import com.permata.appraisal2.ws.client.carm.Faktor_Marketability;
import com.permata.appraisal2.ws.client.carm.Faktor_Marketability_L_B_Internal;
import com.permata.appraisal2.ws.client.carm.Informasi_Nilai;
import com.permata.appraisal2.ws.client.carm.Informasi_Nilai_L_B_Internal;
import com.permata.appraisal2.ws.client.carm.Informasi_Nilai_Vessel;
import com.permata.appraisal2.ws.client.carm.L_B_External;
import com.permata.appraisal2.ws.client.carm.L_B_Internal;
import com.permata.appraisal2.ws.client.carm.List_of_Data_Kendaraan;
import com.permata.appraisal2.ws.client.carm.List_of_Data_Kendaraan_Vehicle_External;
import com.permata.appraisal2.ws.client.carm.List_of_Data_Mesin;
import com.permata.appraisal2.ws.client.carm.List_of_Data_Mesin_Machine_Internal;
import com.permata.appraisal2.ws.client.carm.Machine_External;
import com.permata.appraisal2.ws.client.carm.Machine_Internal;
import com.permata.appraisal2.ws.client.carm.Negative_List_Appraisal;
import com.permata.appraisal2.ws.client.carm.Negative_List_Appraisal_L_B_Internal;
import com.permata.appraisal2.ws.client.carm.Nilai_Aktiva_Sesuai_Eksternal;
import com.permata.appraisal2.ws.client.carm.Order_Data_Detail;
import com.permata.appraisal2.ws.client.carm.Order_Data_Detail_L_B_Internal;
import com.permata.appraisal2.ws.client.carm.Order_Data_Detail_Machine_External;
import com.permata.appraisal2.ws.client.carm.Order_Data_Detail_Machine_Internal;
import com.permata.appraisal2.ws.client.carm.Order_Data_Detail_Stock_External;
import com.permata.appraisal2.ws.client.carm.Order_Data_Detail_Stock_Internal;
import com.permata.appraisal2.ws.client.carm.Order_Data_Detail_Vehicle_External;
import com.permata.appraisal2.ws.client.carm.Order_Data_Detail_Vehicle_Internal;
import com.permata.appraisal2.ws.client.carm.Order_Data_Detail_Vessel;
import com.permata.appraisal2.ws.client.carm.Property_Dalam_Perhatian_Khusus;
import com.permata.appraisal2.ws.client.carm.Property_Dalam_Perhatian_Khusus_L_B_Internal;
import com.permata.appraisal2.ws.client.carm.Reject;
import com.permata.appraisal2.ws.client.carm.Response;
import com.permata.appraisal2.ws.client.carm.Stock_External;
import com.permata.appraisal2.ws.client.carm.Stock_Internal;
import com.permata.appraisal2.ws.client.carm.Valuation;
import com.permata.appraisal2.ws.client.carm.Valuation_Machine_Internal;
import com.permata.appraisal2.ws.client.carm.Valuation_Stock_External;
import com.permata.appraisal2.ws.client.carm.Valuation_Stock_Internal;
import com.permata.appraisal2.ws.client.carm.Valuation_Vehicle_External;
import com.permata.appraisal2.ws.client.carm.Valuation_Vehicle_Internal;
import com.permata.appraisal2.ws.client.carm.Vehicle_External;
import com.permata.appraisal2.ws.client.carm.Vehicle_Internal;
import com.permata.appraisal2.ws.client.carm.Vessel;
import com.permata.appraisal2.ws.client.iloan.los.Wbapr_bndStub;
import com.permata.appraisal2.ws.client.iloan.los.Wbapr_idLocator;
import com.permata.appraisal2.ws.client.iloan.los.Wbapr_prt;
import com.permata.appraisal2.ws.client.plus.AppraisalModel;
import com.permata.appraisal2.ws.client.plus.AttachModel;
import com.permata.appraisal2.ws.client.plus.CertModel;
import com.permata.appraisal2.ws.client.plus.ImbModel;
import com.permata.appraisal2.ws.client.plus.NegativeModel;
import com.permata.appraisal2.ws.client.plus.SpecialConsideration;
import com.permata.appraisal2.ws.client.plus.SurveyModel;

import net.sf.jasperreports.engine.JRException;

@Service
@Transactional(readOnly = true)
public class DataCommonFacadeImpl extends FacadeBase<DataCommonDto, DataCommon> implements DataCommonFacade {

    @Autowired
    private SurveyImageDao surveyImageDao;
    @Autowired
    private SurveyCommonDao surveyCommonDao;

    @Autowired
    private DataCommonDao dao;
    @Autowired
    private StatusJaminanDao jaminanDao;
    @Autowired
    private StatusNasabahDao nasabahDao;
    @Autowired
    private AppraisalBranchDao appraisalCabangDao;
    @Autowired
    private JenisAppraisalDao jenisAppraisalDao;
    @Autowired
    private GroupDao groupDao;
    @Autowired
    private BranchDao cabangDao;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private TaskHistoryDao historyDao;
    @Autowired
    private DataRejectDao rejectDao;
    @Autowired
    private DataMessageDao messageDao;
    @Autowired
    private DataDetailDao detailDao;
    @Autowired
    private TableSequenceDao sequenceDao;
    @Autowired
    private ParameterDao parameterDao;
    @Autowired
    private KjppDao kjppDao;
    @Autowired
    private LpaUmumDao lpaUmumDao;
    @Autowired
    private IloanDao loanDao;
    @Autowired
    private IloanProgresDao loanProgresDao;
    @Autowired
    private SendToSourceLogDao sendToSourceLogDao;

    @Autowired
    private SendToSourceLogAttachmenDao sendToSourceLogAttachmenDao;

    @Autowired
    private IloanProgresDao iloanProgresDao;
    @Autowired
    private MobileSendSurveyDao mobileSendSurveyDao;

    @Autowired
    private DataDetailFacade detailFacade;
    @Autowired
    private DataAttactmentFacade attactmentFacade;
    @Autowired
    private SystemParamFacade systemParamFacade;

//    private SendAttachmentPlusFacade sendAttachmentPlusFacade;
//
//    private SendAttachmentCarmFacade sendAttachmentCarmFacade;

//    @Autowired
//    private PlusClientUtils plusWs;


    @Autowired
    private ZipCodeDao zipCodeDao;

    /**
     * Need by Report generate
     */
    @Autowired
    private ServletContext context;
    @Autowired
    private BasicDataSource dataSource;

    @Autowired
    private LpaBangunanDao lpaBangunanDao;

    @Autowired
    private LpaTanahDao lpaTanahDao;


    @Autowired
    private LpaItemKendaraanDao itemDao;


    @Autowired
    private LpaMesinLampiranDao lampiranDao;

    private String[] formatDocOthers = new String[]{"image/jpeg","image/png","image/tiff","application/vnd.ms-excel", "application/pdf"};

//    public ServletContext getContext() {
//        return context;
//    }
//
//    public SendToSourceLogAttachmenDao getSendToSourceLogAttachmenDao() {
//        return sendToSourceLogAttachmenDao;
//    }
//
//    public SurveyImageDao getSurveyImageDao() {
//        return surveyImageDao;
//    }
//
//    public BasicDataSource getDataSource() {
//        return dataSource;
//    }
//
//    public String[] getFormatDocOthers() {
//        return formatDocOthers;
//    }
//
//    public DataCommonDao getDao() {
//        return dao;
//    }

    public PaginatedList<DataCommonDto> findOrderFailedSendSource(PagingInfo pagingInfo, OrderSpec order) {
        DataCommonDto dto = new DataCommonDto();
        dto.setSourceStatus(5);
        dto.setTaskList("CLOSE");
        dto.setSource("PLUS");
        order = OrderSpec.create("orderNumber", OrderSpec.OrderType.desc);
        PaginatedList<DataCommon> pgList = dao.find(pagingInfo, toEntity(dto), order);
        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
    }

    public List<DataCommonDto> findOrderFailedSendSourceList(PagingInfo pagingInfo, OrderSpec order) {
        DataCommonDto dto = new DataCommonDto();
        dto.setSourceStatus(5);
        dto.setTaskList("CLOSE");
        dto.setSource("PLUS");
        order = OrderSpec.create("orderNumber", OrderSpec.OrderType.desc);
        List<DataCommon> list = dao.find(toEntity(dto), order);
        return toListWrapperPlus(list);
    }

    public List<DataCommonDto> toListWrapperPlus(List<DataCommon> listE) {
        List<DataCommonDto> listW = new ArrayList<DataCommonDto>();
        for (DataCommon e : listE) {
            DataCommonDto d = toWrapper(e);
            if(d.getPlusTanggal()!=null) {
                listW.add(d);
            }
        }
        return listW;
    }

    public PaginatedList<DataCommonDto> findOrderFailedRejectSource(PagingInfo pagingInfo, OrderSpec order) {
        DataCommonDto dto = new DataCommonDto();
        dto.setSourceStatus(8);
        dto.setSource("PLUS");
        order = OrderSpec.create("orderNumber", OrderSpec.OrderType.desc);
        PaginatedList<DataCommon> pgList = dao.find(pagingInfo, toEntity(dto), order);
        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
    }

    public PaginatedList<DataCommonDto> findOrderFailedRejectSourceCarm(PagingInfo pagingInfo, OrderSpec order) {
        DataCommonDto dto = new DataCommonDto();
        dto.setSourceStatus(8);
        dto.setSource("CARM");
        order = OrderSpec.create("orderNumber", OrderSpec.OrderType.desc);
        PaginatedList<DataCommon> pgList = dao.find(pagingInfo, toEntity(dto), order);
        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
    }



    public String getByCommonIdJenis (Long idCommon){

       SendToSourceLog entity = sendToSourceLogDao.getByIdCommon(idCommon);

       if (entity != null){
           return entity.getJenis();
       }

        return null;
    }

    public PaginatedList<DataCommonDto> findOrderFailedSendSourceCarm(PagingInfo pagingInfo, OrderSpec order) {
        DataCommonDto dto = new DataCommonDto();
        dto.setSourceStatus(5);
        dto.setTaskList("CLOSE");
        dto.setSource("CARM");
        order = OrderSpec.create("orderNumber", OrderSpec.OrderType.desc);
        PaginatedList<DataCommon> pgList = dao.find(pagingInfo, toEntity(dto), order);
        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
    }

    int timeout = 1000 * 10 * 1; // mili * second * minute

    public PaginatedList<DataCommonDto> find(DataCommonDto dto, PagingInfo pagingInfo, OrderSpec order) {
        order = OrderSpec.create("orderNumber", OrderSpec.OrderType.desc);
        PaginatedList<DataCommon> pgList = dao.find(pagingInfo, toEntity(dto), order);
//        PaginatedList<DataCommonDto> p = pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
//        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
        return pgList.cloneAndUpdateList(toListWrapperValuerAndApprove(pgList.getDataList(),""));
//        return p;
    }

    public PaginatedList<DataCommonDto> findForValuerAndApprove(DataCommonDto dto, PagingInfo pagingInfo, OrderSpec order,String userId) {
        order = OrderSpec.create("orderNumber", OrderSpec.OrderType.desc);
        PaginatedList<DataCommon> pgList = dao.find(pagingInfo, toEntity(dto), order);
        return pgList.cloneAndUpdateList(toListWrapperValuerAndApprove(pgList.getDataList(),userId));
    }


    private List<DataCommonDto> toListWrapperValuerAndApprove(List<DataCommon> listE,String userId) {
        List<DataCommonDto> listW = new ArrayList<DataCommonDto>();
        for (DataCommon e : listE) {
//            if (!StringUtils.isBlank(e.getSurveyBy()) && e.getSurveyBy().equalsIgnoreCase(userId)){
//                continue;
//            }
            listW.add(toWrapper(e));
        }
        return listW;
    }

    private List<DataCommonDto> toListWrapperCol(List<DataCommon> listE) {
        List<DataCommonDto> listW = new ArrayList<DataCommonDto>();
        for (DataCommon e : listE) {

            if (StringUtils.isBlank(e.getCollateralId())){
                listW.add(toWrapper(e));
            }
        }
        return listW;
    }

    public PaginatedList<DataCommonDto> findColNull(DataCommonDto dto, PagingInfo pagingInfo, OrderSpec order) {
        PaginatedList<DataCommon> pgList = dao.find(pagingInfo, toEntity(dto), order);
        return pgList.cloneAndUpdateList(toListWrapperCol(pgList.getDataList()));
    }

    public PaginatedList<DataCommonDto> findOrderPending(DataCommonDto dto, PagingInfo pagingInfo, OrderSpec order) {
        PaginatedList<DataCommon> pgList = dao.findOrderPending(pagingInfo, toEntity(dto), order);
        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
    }

    @Transactional
    public DataCommonDto save(DataCommonDto dto) {
        DataCommon data = toEntity(dto);
        if (data.getId() == null) {
            // new data, create order number
            String code = AppConstans.OrderDataCommon.getOrderPattern();
            Integer orderNumber = sequenceDao.nextVal(code.concat("-ORDER"));
            data.setOrderNumber(String.format(AppConstans.OrderDataCommon.ORDER_PATTERN, code, orderNumber));
        }
        DataCommon saved = dao.save(data);
        if (dto.getRwaId() != null && dto.getId() == null) {
            // save RWA Progress waktu create saja
            saveRwa(dto.getRwaId(), saved);
        }
        return toWrapper(saved);
    }

    @Override
    public List<LabelValue<String, String>> getAllPic() {

        List<LabelValue<String, String>> result = new ArrayList<LabelValue<String, String>>();
        for (String zip : zipCodeDao.getAllPic()) {
            result.add(new LabelValue<String, String>(zip, zip));
        }
        return result;
    }

    @Override
    @Transactional(readOnly = false)
    public void updatePic(Long id, String picName) {

        detailDao.updatePicByCommonId(id, picName);

    }

    private void saveRwa(Long rwaId, DataCommon dataUmum) {
        // create header
        Iloan head = loanDao.get(rwaId);
        IloanProgres headItem = new IloanProgres();
        BeanUtils.copyProperties(head, headItem, "id", "priority");
        headItem.setPriority(head.getPriority().getId());
        headItem.setRwaId(head.getId());
        headItem.setDataCommon(dataUmum);
        headItem.setStatus(IloanProgres.STATUS_SAVE);
        headItem.setWaktuSimpan(new Date());
        headItem.setHeadId(null);
        loanProgresDao.save(headItem);

        // create children
        List<Iloan> dataList = loanDao.findChildByHeadId(head.getCollateralId(), head.getGroupVal());
        for (Iloan row : dataList) {
            IloanProgres item = new IloanProgres();
            BeanUtils.copyProperties(row, item, "id", "priority");
            item.setPriority(row.getPriority().getId());
            item.setRwaId(row.getId());
            item.setDataCommon(dataUmum);
            item.setStatus(IloanProgres.STATUS_SAVE);
            item.setWaktuSimpan(new Date());
            item.setHeadId(head.getId());
            loanProgresDao.save(item);
        }
    }

    public DataCommonDto get(Long id) {
        return toWrapperWithDetail(dao.get(id));
    }

    @Override
    public DataCommonDto getByLpaId(Long lpaId) {
        return toWrapperWithDetail(dao.getByLpaId(lpaId));
    }

    @Override
    public AppAdminDataCommonDto getByAdmin(Long id) {
        return toWrapperByAdmin(dao.get(id));
    }

    @Transactional
    public void delete(Long id) throws Exception {
        dao.delete(id);
    }

    @Override
    public DataCommonDto toWrapper(DataCommon e) {
        DataCommonDto w = new DataCommonDto();
        BeanUtils.copyProperties(e, w);

        w.setValuerBy(e.getValueBy());
        // setup relation
        if (e.getCustomerStatus() != null) {
            w.setCustomerStatusId(e.getCustomerStatus().getId());
        }
        if (e.getCollateralStatus() != null) {
            w.setCollateralStatusId(e.getCollateralStatus().getId());
        }
        if (e.getAppraisalType() != null) {
            w.setAppraisalTypeId(e.getAppraisalType().getId());
            w.setAppraisalTypeName(e.getAppraisalType().getName());
        }
        if (e.getMarketingBranch() != null) {
            w.setBranchBussinesName(e.getMarketingBranch().getName());
            w.setBrancId(e.getMarketingBranch().getId());
        }
        if (StringUtils.isNotBlank(e.getCreatedBy())) {
            Person person = personDao.get(e.getCreatedBy());
            if (person.getRole(PersonLevel.MARKETING) != null) {
                w.setMarketingTelp(person.getPhone());
                w.setMarketingHp(person.getHp());
            }
        }
        if (e.getDataDetail() == null) {//KAYAKNYA INI BIKIN LAMA
            w.setStatusData(DataCommonDto.StatusData.NO_DETAIL);
            w.setDetail(new DataDetailDto(w.getId()));
        } else {
            w.setDataDetailId(e.getDataDetail().getId());
            w.setDetail(detailFacade.toWrapper(e.getDataDetail()));
//            w.setStatusData(e.getSourceStatus()==null?DataCommonDto.StatusData.NEW:(e.getSourceStatus()>0?DataCommonDto.StatusData.COMPLETE:DataCommonDto.StatusData.NO_ATTACTMENT));
            if (detailDao.gotAttachment(e.getDataDetail().getId())) {
//                w.setAttactments(attactmentFacade.toListWrapper(e.getDataDetail().getAttactments()));
                w.setStatusData(DataCommonDto.StatusData.COMPLETE);
            } else {
                w.setStatusData(DataCommonDto.StatusData.NO_ATTACTMENT);
            }
        }

        if (e.getMessages() != null && !e.getMessages().isEmpty()) {
            DataMessage lastMessage = e.getMessages().get(0);
            DataMessageDto dataMessageDto = new DataMessageDto();
            BeanUtils.copyProperties(lastMessage, dataMessageDto);
            dataMessageDto.setUserId(lastMessage.getPerson().getUserId());
            dataMessageDto.setUserName(lastMessage.getPerson().getName());
            w.setLastMessage(dataMessageDto);
        }

            if (e.getSourceStatus()!= null && e.getSourceStatus()== AppConstans.SourceStatusConstans.FAILED_SEND &&
                    e.getSource().equals("PLUS") && e.getTaskList().equals("CLOSE")){
            w.setSourceId(e.getSourceId());
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:MM:ss");
            SendToSourceLog log = sendToSourceLogDao.getByIdCommon(e.getId());
            SendToSourceAttachment logAtt = sendToSourceLogAttachmenDao.getByIdSource(log.getId());
            w.setPlusTanggal(sdf.format(log.getTanggal()));
            w.setPlusMessage(log.getMessage());
            if (logAtt!=null){
                w.setPlusImageMessage(logAtt.getMessage());
            }

            }


        if (e.getSourceStatus()!= null && e.getSourceStatus()== AppConstans.SourceStatusConstans.FAILED_SEND &&
                e.getSource().equals("CARM") && e.getTaskList().equals("CLOSE")){
            w.setSourceId(e.getSourceId());
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:MM:ss");
            SendToSourceLog log = sendToSourceLogDao.getByIdCommon(e.getId());
            SendToSourceAttachment logAtt = sendToSourceLogAttachmenDao.getByIdSource(log.getId());
            w.setPlusTanggal(sdf.format(log.getTanggal()));
            w.setPlusMessage(log.getMessage());
            if (logAtt!=null){
                w.setPlusImageMessage(logAtt.getMessage());
            }

        }

        if (e.getSourceStatus()!= null && e.getSourceStatus()== AppConstans.SourceStatusConstans.FAILED_SEND_REJECT &&
                e.getSource().equals("PLUS")){
            w.setSourceId(e.getSourceId());
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:MM:ss");
            SendToSourceLog log = sendToSourceLogDao.getByIdCommon(e.getId());
            SendToSourceAttachment logAtt = sendToSourceLogAttachmenDao.getByIdSource(log.getId());
            w.setPlusTanggal(sdf.format(log.getTanggal()));
            w.setPlusMessage(log.getMessage());

        }


        if (e.getSourceStatus()!= null && e.getSourceStatus()== AppConstans.SourceStatusConstans.FAILED_SEND_REJECT &&
                e.getSource().equals("CARM")){
            w.setSourceId(e.getSourceId());
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:MM:ss");
            SendToSourceLog log = sendToSourceLogDao.getByIdCommon(e.getId());
            SendToSourceAttachment logAtt = sendToSourceLogAttachmenDao.getByIdSource(log.getId());
            w.setPlusTanggal(sdf.format(log.getTanggal()));
            w.setPlusMessage(log.getMessage());

        }

        return w;
    }

    public DataCommonDto toWrapperWithDetail(DataCommon e) {
        DataCommonDto w = toWrapper(e);

        w.setCustomerStatusName(e.getCustomerStatus().getName());
        w.setCollateralStatusName(e.getCollateralStatus().getName());
        if (e.getBranch() != null) {
            w.setAppBranchId(e.getBranch().getId());
            w.setBranchAppName(e.getBranch().getName());
        }
        if(e.getDataDetail()!=null) w.setAttactments(attactmentFacade.getAttachmentsWithoutBlob(e.getDataDetail()));
        return w;
    }

    @Override
    public DataCommon toEntity(DataCommonDto w) {
        DataCommon e = new DataCommon();
        if (w.getId() != null) {
            e = dao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        if (w.getBrancId() != null) {

            e.setMarketingBranch(cabangDao.get(w.getBrancId()));
            logger.info("MarketingBranch: "+e.getMarketingBranch().toString());
        }
        if (w.getAppBranchId() != null) {
            e.setBranch(appraisalCabangDao.get(w.getAppBranchId()));
        }
        if (w.getCustomerStatusId() != null) {
            e.setCustomerStatus(nasabahDao.get(w.getCustomerStatusId()));
        }
        if (w.getCollateralStatusId() != null) {
            e.setCollateralStatus(jaminanDao.get(w.getCollateralStatusId()));
        }
        if (w.getAppraisalTypeId() != null) {
            e.setAppraisalType(jenisAppraisalDao.get(w.getAppraisalTypeId()));
        }

        e.setValueBy(w.getValuerBy());
        return e;
    }

    public Map<String, Object> getReference(int status, String flowFrom) {
        Map<String, Object> result = new HashMap<String, Object>();

        result.put("listJenisLaporan", AppConstans.JenisLaporan.getList());

        // status Nasabah
        List<StatusNasabah> dataListStatusNasabah = nasabahDao.find(new StatusNasabah());
        List<LabelValue<Long, String>> lvStatusNasabah = new ArrayList<LabelValue<Long, String>>();
        for (StatusNasabah row : dataListStatusNasabah) {
            lvStatusNasabah.add(new LabelValue<Long, String>(row.getId(), row.getName()));
        }
        result.put("lvStatusNasabah", lvStatusNasabah);

        // status Jaminan
        List<StatusJaminan> dataListStatusJaminan = jaminanDao.find(new StatusJaminan());
        List<LabelValue<Long, String>> lvStatusJaminan = new ArrayList<LabelValue<Long, String>>();
        for (StatusJaminan row : dataListStatusJaminan) {
            lvStatusJaminan.add(new LabelValue<Long, String>(row.getId(), row.getName()));
        }
        result.put("lvStatusJaminan", lvStatusJaminan);

        // Jenis Appraisal
        List<JenisAppraisal> dataListJenisApproval = jenisAppraisalDao.find(new JenisAppraisal());
        List<LabelValueId<Long, Boolean, String>> lvJenisApproval = new ArrayList<LabelValueId<Long, Boolean, String>>();
        for (JenisAppraisal row : dataListJenisApproval) {
            lvJenisApproval.add(new LabelValueId<Long, Boolean, String>(row.getId(), row.getExternal(), row.getName()));
        }
        result.put("lvJenisApproval", lvJenisApproval);

        if (status != DataCommonDto.StatusData.NEW) {
            List<LabelValue<Long, String>> lvReject = new ArrayList<LabelValue<Long, String>>();
            List<Parameter> listParams = parameterDao.findByGroup(AppConstans.GroupParameter.REJECT_CATEGORY);
            for (Parameter row : listParams) {
                lvReject.add(new LabelValue<Long, String>(row.getId(), row.getValue()));
            }
            result.put("lvReject", lvReject);
        }

        if (status == DataCommonDto.StatusData.COMPLETE) {
//            FlowList filter = new FlowList();
//            filter.setFrom(flowFrom);
//            filter.setType(FlowList.REJECT);
//            List<FlowList> listData = flowListDao.find(filter);
//            List<LabelValue<Long, String>> lvDrops = new ArrayList<LabelValue<Long, String>>();
//            for (FlowList row : listData) {
//                if (!row.getDestination().equalsIgnoreCase("User_AO")) {
//                    lvDrops.add(new LabelValue<Long, String>(row.getId(), row.getDestination()));
//                }
//            }
//            result.put("lvDrops", lvDrops);

            List<AppraisalBranch> listBranch = appraisalCabangDao.find(new AppraisalBranch());
            List<LabelValue<Long, String>> lvBranchs = new ArrayList<LabelValue<Long, String>>();
            for (AppraisalBranch row : listBranch) {
                lvBranchs.add(new LabelValue<Long, String>(row.getId(), row.getName()));
            }
            result.put("lvBranchs", lvBranchs);
        }
        return result;
    }

    @Override
    @Transactional
    public void reject(Long id, String rejectTo, String message, String userId) {
        DataCommon data = dao.get(id);
        data.setPosition(rejectTo);
        data.setTaskList(rejectTo);
        dao.save(data);
        Person person = personDao.get(userId);
        saveHistory(person, data, "TL Reject ke " + rejectTo, 0);
        saveDataMessage(message, person, data, AppConstans.TaskPosition.USER_TL);
        sendReject(data, person, message);
    }

    @Override
    @Transactional
    public Boolean rejectFromValuer(Long id, String rejectTo, String message, String userId) {
        // // TODO: 11/20/2017 this method wrong when reject (taskflow error)
        DataCommon data = dao.get(id);
        DataCommon saved;
        data.setPosition(rejectTo);
        data.setTaskList(rejectTo);
        data.setValueBy(userId);
        data.setValueDate(new Date());
        data.setLastPosition(AppConstans.TaskPosition.APP_VALUER);
        data.setSourceStatus(rejectTo.equalsIgnoreCase("App_Admin")?AppConstans.SourceStatusConstans.MARKETING_TO_ADMIN:AppConstans.SourceStatusConstans.REJECT_TO_MARKETING);
        saved = dao.save(data);
        Person person = personDao.get(userId);
        Integer param = 99;
        if (rejectTo.equalsIgnoreCase("App_Admin")) {
            param = 14;
        }

        saveHistory(person, data, "Valuer Reject ke " + rejectTo, param);
        saveDataMessage(message, person, data, AppConstans.TaskPosition.APP_VALUER);
        sendReject(data, person, message);

        //send To Source
        Boolean status = true;
        if (rejectTo.equalsIgnoreCase( AppConstans.TaskPosition.USER_TL)){
            if (saved.getId() != null && saved.getSource() != null && saved.getSource().equals("PLUS")){
                String userIdNpk = person.getUserId().concat(" - ").concat(person.getName());
                StringHolder msg = new StringHolder(message);
                status = sendRejectPlus(saved,userIdNpk,msg);
                saved.setTaskList("PLUS");
                dao.save(saved);
            } else if (saved.getId() != null && saved.getSource() != null && saved.getSource().equals("CARM")){
                status = sendRejectCarm(saved,person,message);
                saved.setTaskList("CARM");
                dao.save(saved);
            }

            sendSmsAndEmail(data,person,message);
        }
        return status;
    }

    @Override
    @Transactional
    public void submit(Long id, Long sendTo, String message, String userId) throws Exception {
        AppraisalBranch branch = appraisalCabangDao.get(sendTo);
        DataCommon data = dao.get(id);
        if(!StringUtils.equalsIgnoreCase(data.getTaskList(),userId)) {
            throw new Exception("Gagal : Posisi Order Tidak Pada Tasklist Anda");
        } else {
            data.setPosition(AppConstans.TaskPosition.APP_ADMIN);
            data.setTaskList(AppConstans.TaskPosition.APP_ADMIN);
            data.setBranch(branch);
            data.setCreatedBy(userId);
            data.setCreatedDate(new Date());
            data.setSubmitDate(data.getCreatedDate());
            data.setLastPosition(AppConstans.TaskPosition.USER_TL);
            data.setSourceStatus(AppConstans.SourceStatusConstans.MARKETING_TO_ADMIN);
            dao.save(data);

            Person person = personDao.get(userId);
            saveHistory(person, data, "TL Submit ke Cabang Appraisal " + branch.getName(), 1);
            saveDataMessage(message, person, data, AppConstans.TaskPosition.USER_TL);
        }
    }

    @Override
    @Transactional
    public void submitToApproval(DataCommonDto dto, String message, String userId, String to) throws Exception{
        DataCommon e = dao.get(dto.getId());
        if(!StringUtils.equalsIgnoreCase(e.getTaskList(),userId)) {
            throw new Exception("Gagal : Posisi Order Tidak Pada Tasklist Anda");
        } else {
            e.setPosition(to);
            e.setTaskList(to);
            e.setValueBy(userId);
            e.setValueDate(new Date());
            e.setRetention(dto.getRetention());
            e.setLastPosition(AppConstans.TaskPosition.APP_VALUER);
            if (StringUtils.isNotBlank(dto.getCollateralId())) {
                e.setCollateralId(dto.getCollateralId());
            }
            dao.save(e);

            Person person = personDao.get(userId);
            Integer param = 0;
            if (to.equalsIgnoreCase("App_Surveyor")) {
                param = 5;
            } else if (to.equalsIgnoreCase("App_Approval")) {
                param = 9;
            }
            saveHistory(person, e, "Valuer Submit ke " + to, param);
            saveDataMessage(message, person, e, AppConstans.TaskPosition.APP_VALUER);

            if (to.equalsIgnoreCase(AppConstans.TaskPosition.USER_TL) || to.equalsIgnoreCase(AppConstans.TaskPosition.APP_ADMIN)) {
                sendReject(e, person, dto.getMessage());

                if (to.equalsIgnoreCase(AppConstans.TaskPosition.USER_TL)) {
                    sendSmsAndEmail(e, person, message);
                }


            }

            /**
             * jika to adalah CLOSE and jenis appraisal indikasi
             */
            if (to.equalsIgnoreCase(AppConstans.TaskPosition.CLOSE) && e.getSource() != null) {
                sendToSource(e);
            }
        }
    }

    @Override
    @Transactional
    public void submitApproval(DataCommonDto dto, String message, String userId, String to, PersonRoleDto role) throws Exception {
        if (role.getLimitApproval() == null) {
            throw new Exception("Limit Approval Anda Kosong");
        }
        DataCommon e = dao.get(dto.getId());
        BigDecimal totalNilai = null;
        Person person = personDao.get(userId);
        // find LPA
        if(!StringUtils.equalsIgnoreCase(e.getTaskList(),userId)) {
            throw new Exception("Gagal : Posisi Order Tidak Pada Tasklist Anda");
        } else {
            LpaUmum lpaUmum = lpaUmumDao.getByDetail(e.getDataDetail().getId());

            if (e.getAppraisalType().getName().equalsIgnoreCase(AppConstans.JenisAppraisalName.RAB)) {
                // nilai menurut customer/ internal yang plg tinggi
                if (lpaUmum.getRab().iterator().hasNext()) {
                    LpaRab lpaRab = lpaUmum.getRab().iterator().next();
                    totalNilai = lpaRab.getNilaiReviewCustomer().compareTo(lpaRab.getNilaiReviewInternal()) == 1 ? lpaRab.getNilaiReviewCustomer() : lpaRab.getNilaiReviewInternal();
                }
            } else if (e.getAppraisalType().getName().equalsIgnoreCase(AppConstans.JenisAppraisalName.APPRAISAL)) {
                if (e.getDataDetail().getAssetType().getCategoryType().getCode().equalsIgnoreCase(AppConstans.JenisKategoriCode.LNB)) {
                    if (lpaUmum.getTanahBangunan().iterator().hasNext()) {
                        LpaTanahBangunan lpaTanahBangunan = lpaUmum.getTanahBangunan().iterator().next();
                        if (e.getAppraisalTypeCategory().equalsIgnoreCase(AppConstans.JenisAppraisalCategory.EKSTERNAL)) {
                            BigDecimal totalFisikInt = lpaTanahBangunan.getTotalSesuaiFisik();
                            BigDecimal totalFisikEks = lpaTanahBangunan.getTotalSesuaiFisikEks();
                            if (totalFisikInt != null && totalFisikEks != null) {
                                totalNilai = totalFisikEks.compareTo(totalFisikInt) == 1 ? totalFisikEks : totalFisikInt;
                            } else if (totalFisikInt != null) {
                                totalNilai = totalFisikInt;
                            } else {
                                totalNilai = totalFisikEks;
                            }
                        } else {
                            totalNilai = lpaTanahBangunan.getTotalSesuaiFisik();
                        }
                    }
                } else if (e.getDataDetail().getAssetType().getCategoryType().getCode().equalsIgnoreCase(AppConstans.JenisKategoriCode.KENDARAAN)) {
                    if (lpaUmum.getKendaraan().iterator().hasNext()) {
                        LpaKendaraan lpaKendaraan = lpaUmum.getKendaraan().iterator().next();
                        if (e.getAppraisalTypeCategory().equalsIgnoreCase(AppConstans.JenisAppraisalCategory.EKSTERNAL)) {
                            BigDecimal totalFisikInt = lpaKendaraan.getTotalFisik();
                            BigDecimal totalFisikEks = lpaKendaraan.getTotalFisikEks();
                            if (totalFisikInt != null && totalFisikEks != null) {
                                totalNilai = totalFisikEks.compareTo(totalFisikInt) == 1 ? totalFisikEks : totalFisikInt;
                            } else if (totalFisikInt != null) {
                                totalNilai = totalFisikInt;
                            } else {
                                totalNilai = totalFisikEks;
                            }
                        } else {
                            totalNilai = lpaKendaraan.getTotalFisik();
                        }
                    }
                } else if (e.getDataDetail().getAssetType().getCategoryType().getCode().equalsIgnoreCase(AppConstans.JenisKategoriCode.MESIN)) {
                    if (lpaUmum.getMesin().iterator().hasNext()) {
                        LpaMesin lpaMesin = lpaUmum.getMesin().iterator().next();
                        if (e.getAppraisalTypeCategory().equalsIgnoreCase(AppConstans.JenisAppraisalCategory.EKSTERNAL)) {
                            BigDecimal totalFisikInt = lpaMesin.getNilaiFisikPasar();
                            BigDecimal totalFisikEks = lpaMesin.getNilaiFisikPasarEks();
                            if (totalFisikInt != null && totalFisikEks != null) {
                                totalNilai = totalFisikEks.compareTo(totalFisikInt) == 1 ? totalFisikEks : totalFisikInt;
                            } else if (totalFisikInt != null) {
                                totalNilai = totalFisikInt;
                            } else {
                                totalNilai = totalFisikEks;
                            }
                        } else {
                            totalNilai = lpaMesin.getNilaiFisikPasar();
                        }
                    }
                } else if (e.getDataDetail().getAssetType().getCategoryType().getCode().equalsIgnoreCase(AppConstans.JenisKategoriCode.STOCK)) {
                    if (lpaUmum.getStock().iterator().hasNext()) {
                        LpaStock lpaStock = lpaUmum.getStock().iterator().next();
                        if (e.getAppraisalTypeCategory().equalsIgnoreCase(AppConstans.JenisAppraisalCategory.EKSTERNAL)) {
                            BigDecimal totalFisikInt = lpaStock.getNilaiFisik();
                            BigDecimal totalFisikEks = lpaStock.getNilaiFisikEks();
                            if (totalFisikInt != null && totalFisikEks != null) {
                                totalNilai = totalFisikEks.compareTo(totalFisikInt) == 1 ? totalFisikEks : totalFisikInt;
                            } else if (totalFisikInt != null) {
                                totalNilai = totalFisikInt;
                            } else {
                                totalNilai = totalFisikEks;
                            }
                        } else {
                            totalNilai = lpaStock.getNilaiFisik();
                        }
                    }
                } else if (e.getDataDetail().getAssetType().getCategoryType().getCode().equalsIgnoreCase(AppConstans.JenisKategoriCode.VESEL)) {
                    if (lpaUmum.getKapal().iterator().hasNext()) {
                        LpaKapal lpaKapal = lpaUmum.getKapal().iterator().next();
                        if (e.getAppraisalTypeCategory().equalsIgnoreCase(AppConstans.JenisAppraisalCategory.EKSTERNAL)) {
                            BigDecimal totalFisikInt = lpaKapal.getNilaiFisikPasar();
                            BigDecimal totalFisikEks = lpaKapal.getNilaiFisikPasarEks();
                            if (totalFisikInt != null && totalFisikEks != null) {
                                totalNilai = totalFisikEks.compareTo(totalFisikInt) == 1 ? totalFisikEks : totalFisikInt;
                            } else if (totalFisikInt != null) {
                                totalNilai = totalFisikInt;
                            } else {
                                totalNilai = totalFisikEks;
                            }
                        } else {
                            totalNilai = lpaKapal.getNilaiFisikPasar();
                        }
                    }
                }
            } else {
                totalNilai = BigDecimal.ZERO;
            }

            if (totalNilai != null) {
                if (!to.equalsIgnoreCase("App_Valuer") && totalNilai.compareTo(role.getLimitApproval()) == 1) {
                    e.setTaskList(AppConstans.TaskPosition.APP_APPROVAL);
                    dao.save(e);
                    saveHistory(person, e, "Approval Melebihi Limit - Send Back To Order List", 100);
                    throw new Exception("Limit Exceeded");
                }
            }
            e.setPosition(to);
            e.setTaskList(to);
            e.setLastPosition(AppConstans.TaskPosition.APP_APPROVAL);
            if (to.equalsIgnoreCase("CLOSE")) {
                if(StringUtils.isBlank(e.getSource())) {
                    e.setSourceStatus(AppConstans.SourceStatusConstans.APPROVAL_CLOSE);
                } else {
                    e.setSourceStatus(AppConstans.SourceStatusConstans.FAILED_SEND);
                }
                e.setClosedBy(userId);
                e.setClosedDate(new Date());
            }
            dao.save(e);

            Integer param = 0;
            if (to.equalsIgnoreCase("App_Valuer")) {
                param = 12;
            } else if (to.equalsIgnoreCase("CLOSE")) {
                param = 11;
            }

            saveHistory(person, e, "Approval Submit ke " + to, param);
            saveDataMessage(message, person, e, AppConstans.TaskPosition.APP_APPROVAL);
//        if (to.equalsIgnoreCase(AppConstans.TaskPosition.CLOSE)) {
//            if (StringUtils.isNotBlank(e.getSourceId()) && StringUtils.isNotBlank(e.getSource()) && e.getSourceStatus() != 4) {
//                if (e.getSource().equalsIgnoreCase(AppConstans.Source.CARM)) {
//                    logger.debug("SEND TO CARM: ---> ");
//                    sendToCarm(e);
//                } else if (e.getSource().equalsIgnoreCase(AppConstans.Source.PLUS)) {
//                    logger.debug("SEND TO PLUS: ---> ");
//                    sendToPlus(e);
//                }
//            } else  if (StringUtils.isNotBlank(e.getSource()) && e.getRetention() != null && e.getRetention() != 1) {
//                logger.debug("SEND TO RWA: ---> ");
//                if (StringUtils.equalsIgnoreCase(e.getSource(), AppConstans.Source.RWA)) sendToRwa(e);
//            }
//        } else {
//            sendReject(e, person, dto.getMessage());
//        }
        }
    }

    @Override
    @Transactional
    public Boolean createCarmOrPlus(DataCommonDto dto, String message, String userId, String to, PersonRoleDto role)  throws Exception{
        DataCommon e = dao.get(dto.getId());
        Person person = personDao.get(userId);
        Boolean status = false;
        if (to.equalsIgnoreCase(AppConstans.TaskPosition.CLOSE)) {
            if (StringUtils.isNotBlank(e.getSourceId()) && StringUtils.isNotBlank(e.getSource()) && e.getSourceStatus() != 4) {
                if (e.getSource().equalsIgnoreCase(AppConstans.Source.CARM)) {
                    logger.debug("SEND TO CARM: ---> ");
                    status = sendToCarm(e);
                } else if (e.getSource().equalsIgnoreCase(AppConstans.Source.PLUS)) {
                    logger.debug("SEND TO PLUS: ---> ");
                    status = sendToPlus(e);
                }
            } else  if (StringUtils.isNotBlank(e.getSource()) && e.getRetention() != null && e.getRetention() != 1) {
                logger.debug("SEND TO RWA: ---> ");
                if (StringUtils.equalsIgnoreCase(e.getSource(), AppConstans.Source.RWA)) sendToRwa(e);
                return true;
            }
        } else {
            sendReject(e, person, dto.getMessage());
        }
        return status;
    }


    @Override
    @Transactional
    public void submitToValuer(AppAdminDataCommonDto dto, String message, String userId, String to) throws Exception {
        DataCommon e = dao.get(dto.getId());
        Person person = personDao.get(userId);
        Boolean status = true;
        if(!StringUtils.equalsIgnoreCase(e.getTaskList(),userId)) {
            throw new Exception("Gagal : Posisi Order Tidak Pada Tasklist Anda");
        } else {
            DataCommon saved;
            e.setPosition(to);
            e.setTaskList(to);
            e.setAdminBy(userId);
            e.setAdminDate(new Date());
            e.setRetention(dto.getRetention());
            e.setLastPosition(AppConstans.TaskPosition.APP_ADMIN);
            if (StringUtils.isNotBlank(dto.getCollateralId())) {
                e.setCollateralId(dto.getCollateralId());
            }
            if (dto.getKjppId() != null) {
                e.setKjpp(kjppDao.get(dto.getKjppId()));
            }
            saved = dao.save(e);
            Integer param = 0;
            if (to.equalsIgnoreCase("App_Valuer")) {
                param = 3;
            }
            saveHistory(person, e, "Admin  Submit ke " + to, param);
            saveDataMessage(message, person, e, AppConstans.TaskPosition.APP_ADMIN);
            if (to.equalsIgnoreCase(AppConstans.TaskPosition.USER_TL) || to.equalsIgnoreCase(AppConstans.TaskPosition.APP_ADMIN)) {
                sendReject(e, person, dto.getMessage());

                if (to.equalsIgnoreCase(AppConstans.TaskPosition.USER_TL)) {
                    saved.setSourceStatus(AppConstans.SourceStatusConstans.REJECT_TO_MARKETING);
                    //send To plus
                    if (saved.getId() != null && saved.getSource() != null && saved.getSource().equals("PLUS")) {
                        String userIdNpk = person.getUserId().concat(" - ").concat(person.getName());
                        StringHolder msg = new StringHolder(message);
                        status = sendRejectPlus(saved, userIdNpk, msg);
                        saved.setTaskList("PLUS");
                    } else if (saved.getId() != null && saved.getSource() != null && saved.getSource().equals("CARM")) {
                        status = sendRejectCarm(saved, person, message);
                        saved.setTaskList("CARM");
                    }

                    //Jika ke Marketing kirim Sms & Email
                    sendSmsAndEmail(e, person, message);
                }


                dao.save(saved);
                if(!status) throw new Exception("Reject data success, Gagal Kirim ke Sumber "+saved.getSource());
            }
        }
    }

    @Transactional
    private void sendReject(DataCommon common, Person person, String message) {
        DataReject reject = new DataReject();
        reject.setDataCommon(common);
        reject.setPerson(person);
        reject.setDate(new Date());
        reject.setMessage(message);
        rejectDao.save(reject);
    }

    private void sendSmsAndEmail(DataCommon common, Person person, String message){
        sendEmail(common,person,message);
        sendSms(common,person,message);
    }

    @Transactional
    private void sendEmail(DataCommon common,Person userLogin,String pesan) {
        try {
            if(StringUtils.equalsIgnoreCase(systemParamFacade.get("EMAIL_SENDING").getValue(), "True")) {

                DataDetail detail = common.getDataDetail();
                Person p = personDao.get(common.getCreatedBy());
                String alamat = "";
                alamat = detail.getClusterTower()+ ", " + detail.getSubDistrict() + ", " + detail.getDistrict() + ", " + detail.getCity() + ", " + detail.getProvince();
                //Send Email Notification
                String smtpAddr = systemParamFacade.get("EMAIL_SMTP_ADDR").getValue();
                String emailPort = systemParamFacade.get("EMAIL_SMTP_PORT").getValue();
                String emailFrom = systemParamFacade.get("EMAIL_SENDER").getValue();
                String emailSubject = "WebAppraisal : Order Number [" + common.getOrderNumber() + "/" +common.getName()+ "] has been rejected";
                EmailUtils sm = new EmailUtils(emailFrom, new String[]{p.getEmail()}, emailSubject, "",smtpAddr,emailPort);
                sm.sendNotificationReject(common.getOrderNumber(),common.getId().toString(), common.getName().toUpperCase(), alamat, pesan,
                userLogin.getUserId(), "Admin", "Phone : " + userLogin.getHp() + " / " + userLogin.getPhone() + " Email : " + userLogin.getEmail().toLowerCase() + "<br><font color=\"white\">Admin ke Marketing</font>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Transactional
    private void sendSms(DataCommon common,Person userLogin,String pesan) {
        try {

            DataDetail detail = common.getDataDetail();
            Person p = personDao.get(common.getCreatedBy());

            String rmPhone = p.getHp();
            String bdmPhone = p.getHpAtasan();
            String message = "REJ";
            String detailMsgOrderNumber = common.getOrderNumber();
            String detailMsgDebiturName = common.getName();
            String detailMsgJalan = "";
            String detailMsgKelurahan = "";
            String detailMsgRejector = userLogin.getUserId();
            String detailMsgReason = pesan;
            String alamat = "";
             detailMsgJalan = detail.getAlamat();
                detailMsgKelurahan = detail.getSubDistrict();
                alamat = detail.getClusterTower()+ ", " + detail.getSubDistrict() + ", " + detail.getDistrict() + ", " + detail.getCity() + ", " + detail.getProvince();


            if (detailMsgOrderNumber.length() > 15) {
                detailMsgOrderNumber = detailMsgOrderNumber.substring(0, 15);
            }

            if (detailMsgDebiturName.length() > 19) {
                detailMsgDebiturName = detailMsgDebiturName.substring(0, 19);
            }

            if (detailMsgJalan.length() > 30) {
                detailMsgJalan = detailMsgJalan.substring(0, 30);
            }

            if (detailMsgKelurahan.length() > 8) {
                detailMsgKelurahan = detailMsgKelurahan.substring(0, 8);
            }

            if (detailMsgRejector.length() > 28) {
                detailMsgRejector = detailMsgRejector.substring(0, 19);
            }

            message = message.concat(" NO:").concat(detailMsgOrderNumber);
            message = message.concat(",").concat(detailMsgDebiturName);
            message = message.concat(",").concat(detailMsgJalan);
            message = message.concat(",").concat(detailMsgKelurahan);
            message = message.concat(",").concat(detailMsgRejector);

            if (message.length() >= 72) {
                if (detailMsgReason.length() > 88) {
                    detailMsgReason = detailMsgReason.substring(0, 88);
                }
            } else {
                int x = 160 - message.length(); //160 karakter maksimal isi SMS
                if (x < detailMsgReason.length()) {
                    detailMsgReason = detailMsgReason.substring(0, x);
                }
            }
            message = message.concat(",").concat(detailMsgReason);
            SmsUtils util = new SmsUtils();
            String responseRm = "";
            String responseBdm = "";
            String smsUrl =   systemParamFacade.get("SMS_URL").getValue();
            String smsUserId =   systemParamFacade.get("SMS_USER_ID").getValue();
            String smsPassword =   systemParamFacade.get("SMS_PASSWORD").getValue();
            if ((rmPhone != null) && (!rmPhone.equals(""))) {
                responseRm = util.sendSMS(smsUrl, smsUserId, smsPassword, rmPhone, message);
            }
            if ((bdmPhone != null) && (!bdmPhone.equals(""))) {
                responseBdm = util.sendSMS(smsUrl, smsUserId, smsPassword, bdmPhone, message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    @Transactional
    public void submitSurveyToValuer(DataCommonDto dto, String message, String userId, String to) throws Exception {
        DataCommon e = dao.get(dto.getId());
        if(!StringUtils.equalsIgnoreCase(e.getTaskList(),userId)) {
            throw new Exception("Gagal : Posisi Order Tidak Pada Tasklist Anda");
        } else {
            e.setPosition(to);
            e.setTaskList(to);
            e.setSurveyBy(userId);
            e.setSurveyDate(new Date());
            e.setLastPosition(AppConstans.TaskPosition.APP_SURVEYOR);
            dao.save(e);

            Person person = personDao.get(userId);
            saveHistory(person, e, "Surveyor  Submit ke " + to, 7);
            saveDataMessage(message, person, e, AppConstans.TaskPosition.APP_SURVEYOR);
        }
    }

    private void saveDataMessage(String message, Person person, DataCommon e, String position) {
        if (StringUtils.isNotBlank(message)) {
            DataMessage dataMessage = new DataMessage();
            dataMessage.setDate(new Date());
            dataMessage.setMessage(message);
            dataMessage.setPerson(person);
            dataMessage.setDataCommon(e);
            dataMessage.setPosition(position);
            messageDao.save(dataMessage);
        }
    }

    private void saveHistory(Person person, DataCommon e, String description, Integer param) {
        if (param == 0) {
            update(e);
        }
        TaskHistory history = new TaskHistory();
        history.setPerson(person);
        history.setDataCommon(e);
        history.setDescription(description);
        history.setDate(new Date());
        history.setParam(param);
        historyDao.save(history);
    }

    private void update(DataCommon e) {
        dao.updateRejectToMarketing(e);
    }

    @Override
    @Transactional(readOnly = false)
    public void pendingAdmin(DataCommonDto dto, String message, String userId) throws Exception {
        DataCommon e = dao.get(dto.getId());
//        if (e.getPendingDate() != null) {
//            throw new Exception("Order sudah pernah di pending oleh " + e.getPendingBy());
//        }
        if(StringUtils.equalsIgnoreCase(e.getTaskList(),userId)) {
            e.setTaskList(AppConstans.TaskPosition.APP_ADMIN);
            e.setPendingDate(new Date());
            e.setPendingBy(userId);
            e.setPendingPosition(AppConstans.TaskPosition.APP_ADMIN);
            e.setSourceStatus(AppConstans.SourceStatusConstans.MARKETING_TO_ADMIN);
            dao.save(e);

            Person person = personDao.get(userId);
            saveHistory(person, e, "Admin Pending Order", 2);
            saveDataMessage(message, person, e, AppConstans.TaskPosition.APP_ADMIN);
        } else {
            throw new Exception("Gagal : Posisi Order tidak pada Tasklist Anda");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void claim(Long id, String userId, String description, Integer param) throws Exception {
        DataCommon data = dao.get(id);
        String claimposition;
        if(param==10)claimposition=AppConstans.TaskPosition.APP_APPROVAL;
        else if(param==6)claimposition=AppConstans.TaskPosition.APP_SURVEYOR;
        else claimposition=AppConstans.TaskPosition.USER_TL;
        if(!StringUtils.equalsIgnoreCase(data.getTaskList(), claimposition)) {
            Person person = personDao.get(data.getTaskList());
            String tasklist = "Order List - " + data.getTaskList();
            if(person != null) {
                tasklist = "Tasklist - " + person.getName();
            }
            throw new Exception("Claim Gagal : Posisi Order Sudah di " + tasklist);
        } else {
            data.setTaskList(userId);
            dao.save(data);
            saveHistory(personDao.get(userId), data, description.concat(" Claim"), param);
        }
    }


    @Override
    @Transactional(readOnly = false)
    public void reClaim(Long id, String userId) throws Exception {
        DataCommon data = dao.get(id);
        if(StringUtils.equalsIgnoreCase(data.getTaskList(), AppConstans.TaskPosition.CLOSE)) {
            data.setTaskList(AppConstans.TaskPosition.APP_APPROVAL);
            data.setPosition(AppConstans.TaskPosition.APP_APPROVAL);
            dao.save(data);
            saveHistory(personDao.get(userId), data, "Re-Open to Order List Approval by Admin", 22);
        } else {
            throw new Exception("Gagal : Data sudah tidak berada di POSISI Close");
        }
        //saveDataMessage(message, person, e, AppConstans.TaskPosition.APP_APPROVAL);
    }


    @Override
    @Transactional(readOnly = false)
    public void reClaimDrop(Long id, String userId,String posisi) throws Exception {
        DataCommon data = dao.get(id);
        if(StringUtils.equalsIgnoreCase(data.getTaskList(), AppConstans.TaskPosition.DROP)) {
            data.setTaskList(AppConstans.TaskPosition.USER_TL);
            data.setPosition(AppConstans.TaskPosition.USER_TL);
            dao.save(data);
            saveHistory(personDao.get(userId), data, "Re-Open to Order List Marketing by Admin", 21);
        } else {
            throw new Exception("Gagal : Data sudah tidak berada di POSISI Drop");
        }
        //saveDataMessage(message, person, e, AppConstans.TaskPosition.APP_APPROVAL);
    }

    @Override
    @Transactional(readOnly = false)
    public void reClaimOrderPending(Long id, String userId) throws Exception{
        DataCommon data = dao.get(id);
        if(StringUtils.equalsIgnoreCase(data.getPosition(),data.getTaskList())) {
            throw new Exception("Gagal : Data sudah berada di Order List");
        } else {
            data.setTaskList(data.getPosition());
//        data.setPosition(posisi);
            dao.save(data);
            saveHistory(personDao.get(userId), data,"Realease to Group by Admin", 20);
        }
    }

    @Override
    @Transactional
    public void claimByAdmin(Long id, String userId, String description, String position, Integer param) throws Exception {
        DataCommon data = dao.get(id);
        if(!StringUtils.equalsIgnoreCase(data.getTaskList(), AppConstans.TaskPosition.APP_ADMIN)) {
            Person person = personDao.get(data.getTaskList());
            String tasklist = "Order List - " + data.getTaskList();
            if(person != null) {
                tasklist = "Tasklist - " + person.getName();
            }
            throw new Exception("Claim Gagal : Posisi Order Sudah di " + tasklist);
        } else {
            // TODO: 11/20/2017 harus di cek terpengaruh dengan reject valuer yang merusak task flow history
            data.setTaskList(userId);
            if (data.getPendingDate() != null) {
                data.setUnpendingBy(userId);
                data.setUnpendingDate(new Date());
            }
            data.setSourceStatus(AppConstans.SourceStatusConstans.ADMIN_CLAIM);
            dao.save(data);
            if (param == 2) {
                if (data.getLastPosition() != null && data.getLastPosition().equalsIgnoreCase("App_Valuer")) {
                    param = 15;
                }
            }
            saveHistory(personDao.get(userId), data, description.concat(" Claim"), param);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void claimByValuer(Long id, String userId) throws Exception {
        DataCommon data = dao.get(id);
        if(!StringUtils.equalsIgnoreCase(data.getTaskList(), AppConstans.TaskPosition.APP_VALUER)) {
            Person person = personDao.get(data.getTaskList());
            String tasklist = "Order List - " + data.getTaskList();
            if(person != null) {
                tasklist = "Tasklist - " + person.getName();
            }
            throw new Exception("Claim Gagal : Posisi Order Sudah di " + tasklist);
        } else {
            try {
                if (StringUtils.isNotBlank(data.getLastPosition())) {
                    if (data.getLastPosition().equalsIgnoreCase(AppConstans.TaskPosition.APP_ADMIN)) {
                        // data claim dari admin ke valuer
                        if (StringUtils.isNotBlank(data.getSurveyBy())) {
                            // tetapi memiliki data survey, mungkin hasil dari reject atau copy debitur
                        } else {

                        }

                    }
                    if (data.getLastPosition().equalsIgnoreCase(AppConstans.TaskPosition.APP_SURVEYOR)) {
                        // data claim dari surveyor ke valuer
                        if (data.getSurveyBy().equalsIgnoreCase(userId)) {
                            throw new Exception("Surveyor dan Valuer tidak boleh sama");
                        }
                    }
                }
                data.setTaskList(userId);
                dao.save(data);
                Integer param = 777;
                if (data.getLastPosition().equalsIgnoreCase("App_Admin")) {
                    param = 4;
                } else if (data.getLastPosition().equalsIgnoreCase("App_Surveyor")) {
                    param = 8;
                } else if (data.getLastPosition().equalsIgnoreCase("App_Approval")) {
                    param = 13;
                }
                saveHistory(personDao.get(userId), data, "Valuer Appraisal Claim", param);
            } catch (Exception e) {
                throw e;
            }
        }
    }

    @Override
    @Transactional(readOnly = false)
    public boolean createSurvey(Long detailId, String userId) {
        try {
            DataDetail detail = detailDao.get(detailId);
            detail.setSurveyStatus(SurveyStatus.New);
            detailDao.save(detail);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional(readOnly = false)
    public AppValuerDataCommonDto saveSurvey(AppValuerDataCommonDto dto, PersonLoginDto person) throws Exception{
        DataDetail detail = detailDao.get(dto.getDetail().getId());
        if(StringUtils.equalsIgnoreCase(detail.getDataCommon().getTaskList(), person.getUserId())) {
            detail.setFrom(dto.getDetail().getFrom());
            detail.setDay(dto.getDetail().getDay());
            detail.setMessage(dto.getDetail().getMessage());
            detail.setSurveyStatus(dto.getDetail().getSurveyStatus());
        } else {
            throw new Exception("Gagal : Posisi Order Tidak Pada Tasklist Anda");
        }
        return null;
    }

    public AppAdminDataCommonDto toWrapperByAdmin(DataCommon e) {
        DataCommonDto w = toWrapper(e);
        w.setCustomerStatusName(e.getCustomerStatus().getName());
        w.setCollateralStatusName(e.getCollateralStatus().getName());
        if(e.getDataDetail()!=null) w.setAttactments(attactmentFacade.getAttachmentsWithoutBlob(e.getDataDetail()));
        if (e.getBranch() != null) {
            w.setAppBranchId(e.getBranch().getId());
            w.setBranchAppName(e.getBranch().getName());
        }

        AppAdminDataCommonDto result = new AppAdminDataCommonDto();
        BeanUtils.copyProperties(w, result);
        if (e.getKjpp() != null) {
            result.setKjppId(e.getKjpp().getId());
            result.setKjppName(e.getKjpp().getName());
        }
        if (e.getMessages() != null && !e.getMessages().isEmpty()) {
            for (DataMessage msg : e.getMessages()) {
                DataMessageDto dto = new DataMessageDto();
                BeanUtils.copyProperties(msg, dto);
                dto.setUserId(msg.getPerson().getUserId());
                dto.setCommonId(msg.getDataCommon().getId());
                dto.setUserName(msg.getPerson().getName());
                result.addMessage(dto);
            }
        }
        return result;
    }

    private InqueryDataCommonDto toWrapperByInquery(DataCommon e) {
        InqueryDataCommonDto result = new InqueryDataCommonDto();
        AppValuerDataCommonDto w = toWrapperByValuer(e);
        BeanUtils.copyProperties(w, result);

        if (ListUtils.isNotEmpty(e.getHistories())) {
            for (TaskHistory history : e.getHistories()) {
                DataHistoryDto item = new DataHistoryDto();
                BeanUtils.copyProperties(history, item);
//                item.setUserId(history.getPerson().getUserId());
                item.setUserId(history.getPerson().getName());
                result.addHistory(item);
            }
        }
        if (ListUtils.isNotEmpty(e.getMessages())) {
//            result.setMessages(null);
            List<DataMessageDto> messages = new ArrayList<DataMessageDto>();
            for (DataMessage historyMessage : e.getMessages()) {
                DataMessageDto item = new DataMessageDto();
                BeanUtils.copyProperties(historyMessage, item);
                item.setUserId(historyMessage.getPerson().getUserId());
                item.setUserName(historyMessage.getPerson().getName());
                messages.add(item);
//                result.addMessage(item);
            }
            result.setMessages(messages);
//            messages.clear();
//            messages = null;
        }
        return result;
    }

    public AppValuerDataCommonDto toWrapperByValuer(DataCommon e) {
        AppAdminDataCommonDto w = toWrapperByAdmin(e);
        AppValuerDataCommonDto result = new AppValuerDataCommonDto();
        BeanUtils.copyProperties(w, result);

        /**
         * harus check LPA yang lainnya juga
         */
        if (!result.isHasLpa()) {
            logger.debug("id Detail:----> "+e.getDataDetail().getId());
            result.setHasLpa(lpaUmumDao.isDetailHasLpa(e.getDataDetail().getId()));
            result.setCompleteLpa(!lpaUmumDao.isDraftLpa(e.getDataDetail().getId()));
            if (result.isHasLpa()) {
                LpaUmumDto lpaDto = new LpaUmumDto();
                LpaUmum umum = lpaUmumDao.getByDetail(e.getDataDetail().getId());
                BeanUtils.copyProperties(umum, lpaDto);
                lpaDto.setIdLpa(umum.getId());
                result.setLpa(lpaDto);
            }
        }
        return result;
    }

    @Override
    public AppValuerDataCommonDto getByValuer(Long id) {
        return toWrapperByValuer(dao.get(id));
    }

    @Override
    public InqueryDataCommonDto getByInquery(Long id) {
        return toWrapperByInquery(dao.get(id));
    }

    public PaginatedList<DataCommonTableDto> findForTables(PagingInfo pagingInfo, DataCommonTableDto filter) {
        return dao.findForTables(pagingInfo, filter);
    }

    public List<PendingJobsByUsersDto> findPendingJobsByUsers() {
        return dao.findPendingJobsByUsers();
    }

//    public List<ViewReportJenisJaminanDto> getStatusJaminan(Timestamp submitDate, Timestamp closedDate) {
//        return dao.getStatusJaminan(submitDate, closedDate);
//    }

    public List<Object[]> getReportStatusJaminan(Calendar submitDate, Calendar closedDate, List<AppraisalBranchDto> branchs, List<StatusJaminanDto> statusJaminanDtoList) {
        String columnName = "";
        String crossTabColumn = "";
        String total = "";
        Integer beginYear = submitDate.get(Calendar.YEAR);
        Integer beginMonth = submitDate.get(Calendar.MONTH)+1;
        Integer endYear = closedDate.get(Calendar.YEAR);
        Integer endMonth = closedDate.get(Calendar.MONTH)+1;
        Integer yearDiff = endYear - beginYear;
        Integer monthDiff = 0;
        if (yearDiff==0) {
            monthDiff = endMonth - beginMonth;
        } else {
            monthDiff = (endMonth+12) - beginMonth;
        }
        String[] groupMonth = new String[monthDiff+1];
        int newYear = 1;
        for(int i=0; i<=monthDiff; i++) {
            if(beginMonth+i > 12) {
                groupMonth[i] = (beginYear+1) + "0" + newYear;
                newYear++;
            } else {
                groupMonth[i] = beginYear + "" + String.format("%02d",  (beginMonth + i));
            }
        }
        int i = 0;
        for(AppraisalBranchDto branchDto : branchs) {
            if(i>0) {
                columnName += ", ";
                total += "+";
                crossTabColumn += ", ";
            }
            columnName += "coalesce(\"" + branchDto.getName() + "\", 0) as \"" + branchDto.getName() + "\"";
            total += "coalesce(\""+branchDto.getName()+"\", 0)";
            crossTabColumn += "\"" + branchDto.getName() + "\" bigint";
            i++;
        }
        columnName += ", " + total;
        Timestamp closedDateTimestamp = new Timestamp(closedDate.getTimeInMillis());
        Timestamp createdDateTimestamp = new Timestamp(submitDate.getTimeInMillis());
        return dao.getReportStatusJaminan(columnName, crossTabColumn, createdDateTimestamp, closedDateTimestamp, groupMonth, statusJaminanDtoList);
    }

    public List<TypeObjectViewDto> getTypeObject(Timestamp submitDate, Timestamp closedDate){
        return dao.getTypeObject(submitDate, closedDate);
    }



    public List<DataCommonDto> getIntraDay(Timestamp submitDate, Timestamp closedDate){
        List<DataCommonDto> result = new ArrayList<DataCommonDto>();
        List<DataCommon> list = dao.getIntraDay(submitDate, closedDate);
        for (DataCommon row : list){
            result.add(toItemWrapper(row));
        }

        return  result;
    }

    private DataCommonDto toItemWrapper(DataCommon e) {
        DataCommonDto w = new DataCommonDto();
        BeanUtils.copyProperties(e, w);
        w.setCreatedDate(e.getCreatedDate());
//        w.setGroupDate(new SimpleDateFormat("MMM yyyy").format(e.getCreatedDate()));
        w.setCollateralStatusId(e.getCollateralStatus().getId());
        w.setBranchId(e.getMarketingBranch().getId());
        return w;
    }


    public List<PendingJobsByUsersDto> findPendingJobsByCustomers() {
        return dao.findPendingJobsByCustomers();
    }

    //@Override
    public void sendToSource(Long id) {
        sendToCarm(dao.get(id));
    }

    private void sendToSource(DataCommon e) {
        if (StringUtils.isNotBlank(e.getSourceId()) && StringUtils.isNotBlank(e.getSource()) && e.getSourceStatus() != 4) {
            if (e.getSource().equalsIgnoreCase(AppConstans.Source.CARM)) {
                logger.debug("SEND TO CARM: ---> ");
                sendToCarm(e);
            } else if (e.getSource().equalsIgnoreCase(AppConstans.Source.PLUS)) {
                logger.debug("SEND TO PLUS: ---> ");
                sendToPlus(e);
            }
        } else  if (StringUtils.isNotBlank(e.getSource()) && e.getRetention() != null && e.getRetention() != 1) {
            logger.debug("SEND TO RWA: ---> ");
            if (StringUtils.equalsIgnoreCase(e.getSource(), AppConstans.Source.RWA)) sendToRwa(e);
        }
//        sendToPlus(e);
//        sendToCarm(e);
//        sendToRwa(e);
    }

    @Override
    public void sendAttactmentToPlus(Long lpaId) {
        DataCommon e = dao.getByLpaId(lpaId);
        logger.debug("COMMON_ID : " + e.getId());

    }

    private Boolean sendRejectCarm  (DataCommon dataCommon, Person person,String message){
//        ReceiveAppraisalServiceLocator locator = new ReceiveAppraisalServiceLocator();
        Boolean status = false;
        try {
//            ReceiveAppraisalServiceSoap_PortType service = locator.getReceiveAppraisalServiceSoap(new URL(systemParamFacade.getValue("WS_CARM_IP")));
//            ((ReceiveAppraisalServiceSoap_BindingStub) service).setUsername(systemParamFacade.getValue("WS_CARM_USER"));
//            ((ReceiveAppraisalServiceSoap_BindingStub) service).setPassword(systemParamFacade.getValue("WS_CARM_PASSWORD"));
            Reject reject = new Reject();
            reject.setACL_ID(dataCommon.getSourceId());
            reject.setORDERID(Long.valueOf(dataCommon.getOrderNumber()));
            reject.setREASON(message);
            reject.setUSERNAME(person.getName());
            reject.setUSERID(person.getUserId());
            reject.setREJECTDATE(Calendar.getInstance());
            reject.setTGL_ORDER(ParserUtils.parseToCalendar(dataCommon.getCreatedDate()));

            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(dataCommon.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("receiveAppraisal_Reject");
            log.setTanggal(new Date());
            log.setJenis("REJECT");
            log = sendToSourceLogDao.save(log);

            Response response = CarmClientUtils.getInstance(systemParamFacade.getValue("WS_CARM_IP"), systemParamFacade.getValue("WS_CARM_USER"), systemParamFacade.getValue("WS_CARM_PASSWORD")).getService().receiveAppraisal_Reject(reject);

            log.setStatus(response.getError_Code().equals(AppConstans.SourceStatusConstans.SUCCESS_SEND_REJECT_CARM)?1:2);
            log.setMessage(response.getStatus());
            log.setResponse(response.getError_Code());
            log = sendToSourceLogDao.save(log);
            if (log.getStatus()==2){
                dataCommon.setSourceStatus(AppConstans.SourceStatusConstans.FAILED_SEND_REJECT);
                status = false;
            } else {
                dataCommon.setSourceStatus(AppConstans.SourceStatusConstans.REJECT_TO_MARKETING);
                status = true;
            }
            dao.save(dataCommon);
            return status;
        }  catch (ServiceException e1) {
            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(dataCommon.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("receiveAppraisal_Reject");
            log.setTanggal(new Date());
            log.setMessage("Error Catch ServiceException : "+e1.getMessage());
            log.setResponse("ERROR");
            log.setJenis("Reject");
            log = sendToSourceLogDao.save(log);
            dataCommon.setSourceStatus(AppConstans.SourceStatusConstans.FAILED_SEND_REJECT);
            dao.save(dataCommon);
            e1.printStackTrace();
            return false;
        } catch (MalformedURLException e1) {
            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(dataCommon.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("receiveAppraisal_Reject");
            log.setTanggal(new Date());
            log.setMessage("Error Catch MalformedURLException : "+e1.getMessage());
            log.setResponse("ERROR");
            log.setJenis("Reject");
            log = sendToSourceLogDao.save(log);
            dataCommon.setSourceStatus(AppConstans.SourceStatusConstans.FAILED_SEND_REJECT);
            dao.save(dataCommon);
            e1.printStackTrace();
            return false;
        } catch (RemoteException e1) {
            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(dataCommon.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("receiveAppraisal_Reject");
            log.setTanggal(new Date());
            log.setMessage("Error Catch RemoteException : "+e1.getMessage());
            log.setResponse("ERROR");
            log.setJenis("Reject");
            log = sendToSourceLogDao.save(log);
            dataCommon.setSourceStatus(AppConstans.SourceStatusConstans.FAILED_SEND_REJECT);
            dao.save(dataCommon);
            e1.printStackTrace();
            return false;
        }

    }

    private Boolean sendRejectPlus (DataCommon e,String userReject,StringHolder message){

//        ISyncReply wsclient = plusWs.factory();
        DataDetail detail = e.getDataDetail();
        Boolean status = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            if (e.getAppraisalType().getName().equalsIgnoreCase(AppConstans.JenisAppraisalName.RV)) {

                SendToSourceLog log = new SendToSourceLog();
                log.setIdCommon(e.getId());
                log.setStatus(SendToSourceLog.ERROR);
                log.setSource(AppConstans.Source.PLUS);
                log.setAlamat("rejectOrderBvrv");
                log.setTanggal(new Date());
                log.setJenis("REJECT");
                log = sendToSourceLogDao.save(log);

                Integer responseCode = PlusClientUtils.getInstance(systemParamFacade.getValue(AppConstans.SystemParamCode.PLUS_CLIENT_URL)).getiSyncReply().rejectOrderBvrv(message,e.getSourceId(),sdf.format(new Date()),userReject,e.getOrderNumber());

                log.setStatus(responseCode==0?1:2);
                log.setMessage(message.value);
                log.setResponse(responseCode.toString());
                log = sendToSourceLogDao.save(log);
                 if (log.getStatus()==2){
                     e.setSourceStatus(AppConstans.SourceStatusConstans.FAILED_SEND_REJECT);
                     status = false;
                 } else {
                     e.setSourceStatus(AppConstans.SourceStatusConstans.REJECT_TO_MARKETING);
                     status = true;
                 }
            } else if  (e.getAppraisalType().getName().equalsIgnoreCase(AppConstans.JenisAppraisalName.BV)) {

                SendToSourceLog log = new SendToSourceLog();
                log.setIdCommon(e.getId());
                log.setStatus(SendToSourceLog.ERROR);
                log.setSource(AppConstans.Source.PLUS);
                log.setAlamat("rejectOrderBvrv");
                log.setTanggal(new Date());
                log.setJenis("REJECT");
                log = sendToSourceLogDao.save(log);

                Integer responseCode = PlusClientUtils.getInstance(systemParamFacade.getValue(AppConstans.SystemParamCode.PLUS_CLIENT_URL)).getiSyncReply().rejectOrderBvrv(message,e.getSourceId(),sdf.format(new Date()),userReject,e.getOrderNumber());

                log.setStatus(responseCode==0?1:2);
                log.setMessage(message.value);
                log = sendToSourceLogDao.save(log);
                if (log.getStatus()==2){
                    e.setSourceStatus(AppConstans.SourceStatusConstans.FAILED_SEND_REJECT);
                    status = false;
                } else {
                    e.setSourceStatus(AppConstans.SourceStatusConstans.REJECT_TO_MARKETING);
                    status = true;
                }
            } else if (detail.getAssetType().getCategoryType().getCode().equals(AppConstans.JenisKategoriCode.LNB)){

                SendToSourceLog log = new SendToSourceLog();
                log.setIdCommon(e.getId());
                log.setStatus(SendToSourceLog.ERROR);
                log.setSource(AppConstans.Source.PLUS);
                log.setAlamat("rejectOrderLPA");
                log.setTanggal(new Date());
                log.setJenis("REJECT");
                log = sendToSourceLogDao.save(log);

                Integer responseCode = PlusClientUtils.getInstance(systemParamFacade.getValue(AppConstans.SystemParamCode.PLUS_CLIENT_URL)).getiSyncReply().rejectOrderLPA(message,e.getSourceId(),sdf.format(new Date()),userReject,e.getOrderNumber());

                log.setStatus(responseCode==0?1:2);
                log.setMessage(message.value);
                log = sendToSourceLogDao.save(log);
                if (log.getStatus()==2){
                    e.setSourceStatus(AppConstans.SourceStatusConstans.FAILED_SEND_REJECT);
                    status = false;
                } else {
                    e.setSourceStatus(AppConstans.SourceStatusConstans.REJECT_TO_MARKETING);
                    status = true;
                }
            }
            dao.save(e);
            return status;
        } catch (Exception e1){
            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.PLUS);
            log.setAlamat("rejectOrderPlus");
            log.setTanggal(new Date());
            log.setJenis("REJECT");
            logger.error("Error REJECT PLUS:--> "+e1.getMessage());
            log.setStatus(2);
            log.setMessage("Error Catch : " + e1.getMessage());
            sendToSourceLogDao.save(log);
            e.setSourceStatus(AppConstans.SourceStatusConstans.FAILED_SEND_REJECT);
            dao.save(e);
            return false;
        }


    }


    private Boolean sendToPlus(DataCommon e) {
        Boolean status = false;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            DataDetail detail = e.getDataDetail();
            // response, var passing by value
            StringHolder stringHolder = new StringHolder();
            IntegerWrapperHolder integerWrapperHolder = new IntegerWrapperHolder();
            Person personApprover = personDao.get(e.getClosedBy());
            Person personValuer = personDao.get(e.getValueBy());
            String url = systemParamFacade.getValue(AppConstans.SystemParamCode.PLUS_CLIENT_URL);

            if (e.getAppraisalType().getName().equalsIgnoreCase(AppConstans.JenisAppraisalName.RV)) {
                //Set Jadi Fail terlebih dahulu
                DataCommon d = dao.get(e.getId());
                d.setSourceStatus(AppConstans.SourceStatusConstans.FAILED_SEND);
                dao.save(d);
                LpaUmum lpaUmum = e.getDataDetail().getLpaUmum().iterator().next();
                LpaRv rv = lpaUmum.getRv().iterator().next();

                SurveyModel model = new SurveyModel();
                model.setAddr1(lpaUmum.getLokasiFisik());
                model.setApplicantAge(rv.getUmur());
                model.setApplicantNDebCollector(rv.getDidatangiDebtCollector());
                model.setApplicantSameName(rv.getDikenalNamaSama()?"Ya":"Tidak, "+rv.getNamaPanggilan());
                if (e.getClosedDate()!=null){
                    model.setApprovedDate(sdf.format(e.getClosedDate()));
                }
//                model.setApproverName(personDao.get(e.getClosedBy()).getName()); // comment meminta di kirim NPK - Nama
                model.setApproverName(personApprover.getUserId().concat(" - ").concat(personApprover.getName()));
                model.setBldArea(rv.getLuasBangunan());
                // model.setBldCond(rv.getKondisiRumah() ? "Bertingkat" : "Tidak Bertingkat"); // field BV
                model.setBldType(rv.getJenisBangunan());
                model.setKjppCode(rv.getVendor());
                model.setCity(lpaUmum.getKota());
                String fasilitas = "";
                model.setNumOfEmployee(rv.getTelepon());
                fasilitas += rv.getTelepon()!=null ?"Telepon":"Tidak ada Telepon";
                fasilitas += rv.getGarasi() ?", Garasi":"";
                fasilitas += rv.getCarport()?", Carport":"";
                model.setFacility(fasilitas);
                Boolean foto1 = false;
                Boolean foto2 = false;
                Boolean foto3 = false;
                Boolean foto4 = false;
                Integer fotoCounter = 0;
                for (int i = 0; i<rv.getFotoPada().size(); i++) {
                    if (rv.getFotoPada().get(i).equalsIgnoreCase(parameterDao.findRVBVFotopada(AppConstans.GroupParameter.RV_FOTO_PADA, 1))) {
                        foto1 = true;
                        fotoCounter+=1;
                    } else if (rv.getFotoPada().get(i).equalsIgnoreCase(parameterDao.findRVBVFotopada(AppConstans.GroupParameter.RV_FOTO_PADA, 2))) {
                        model.setFoto2(true);
                        fotoCounter+=1;
                    } else if (rv.getFotoPada().get(i).equalsIgnoreCase(parameterDao.findRVBVFotopada(AppConstans.GroupParameter.RV_FOTO_PADA, 3))){
                        model.setFoto3(true);
                        fotoCounter+=1;
                    } else if (rv.getFotoPada().get(i).equalsIgnoreCase(parameterDao.findRVBVFotopada(AppConstans.GroupParameter.RV_FOTO_PADA, 4))) {
                        model.setFoto4(true);
                        fotoCounter+=1;
                    }
                    if(fotoCounter>=4) break;
                }
                model.setFoto1(foto1);
                model.setFoto2(foto2);
                model.setFoto3(foto3);
                model.setFoto4(foto4);
                model.setInfoSource1(rv.getS1Ditemukan() ? "Ditemukan" : "Tidak Ditemukan");
                model.setInfoSource2(rv.getS2Ditemukan() ? "Ditemukan" : "Tidak Ditemukan");
                model.setInfoSource3(rv.getS3Ditemukan() ? "Ditemukan" : "Tidak Ditemukan");
                model.setInfoSourceCoop1(rv.getS1Kooperatif() ? "Ya" : "Tidak");
                model.setInfoSourceCoop2(rv.getS2Kooperatif() ? "Ya" : "Tidak");
                model.setInfoSourceCoop3(rv.getS3Kooperatif() ? "Ya" : "Tidak");
                model.setInfoSourceGender1(StringUtils.equalsIgnoreCase(rv.getS1Kelamin(),"L")?"Pria":"Wanita");
                model.setInfoSourceGender2(StringUtils.equalsIgnoreCase(rv.getS2Kelamin(),"L")?"Pria":"Wanita");
                model.setInfoSourceGender3(StringUtils.equalsIgnoreCase(rv.getS3Kelamin(),"L")?"Pria":"Wanita");
                model.setInfoSourceKnow2(rv.getS2Kenal() ? "Ya" : "Tidak");
                model.setInfoSourceKnow3(rv.getS3Kenal() ? "Ya" : "Tidak");
                model.setInfoSourceName1(rv.getS1Nama());
                model.setInfoSourceName2(rv.getS2Nama());
                model.setInfoSourceName3(rv.getS3Nama());
                model.setInfoSourceRel1(rv.getS1Hubungan());
                model.setInfoSourceRel2(rv.getS2Hubungan());
                model.setInfoSourceRel3(rv.getS3Hubungan());
                model.setInfoSourceRemark1(rv.getInfoS1());
                model.setInfoSourceRemark2(rv.getInfoS2());
                model.setInfoSourceRemark3(rv.getInfoS3());
                model.setLandArea(rv.getLuasTanah());
                model.setLocObjSurvey(rv.getSudahPindah()?"Sudah Pindah":rv.getLokasiDitemukan()?"Ditemukan":"Tidak Ditemukan");
                model.setLongOfOccup(rv.getLamaPenempatan());
                // model.setNamePlate(rv.getPapanNama() ? "Ada" : "Tidak Ada"); // BV field
                model.setNotes(rv.getNote());
                // model.setNumOfEmployee(bv.getJumlahKaryawan());
                model.setObjectCond(rv.getKondisiRumah() ? "Terawat" : "Tidak Terawat");
                model.setObjectOwnType(rv.getStatusKepemilikan());
                model.setOccupiedBy(rv.getPenghuni());
                // model.setOmzet(rv.getDetailOmzet().doubleValue());
                if(e.getSubmitDate()!=null) model.setOrderDate(sdf.format(e.getSubmitDate()));
                if(lpaUmum.getTanggalTerimaDariEksternal()!=null) model.setRecvDate(sdf.format(lpaUmum.getTanggalTerimaDariEksternal()));
                model.setReportNum(lpaUmum.getLpaNumber());
                // model.setRoadCond(rv.getJalanTempatUsaha()); // field BV
                model.setSurround(rv.getLingkunganSekitar());
                if(lpaUmum.getTanggalSurvey()!=null) model.setSurveyDate(sdf.format(lpaUmum.getTanggalSurvey()));
                model.setSurveyorAcceptIn(rv.getSurveyorDiterimaDi());
                model.setSurveyorName(lpaUmum.getDitinjau());
//                model.setKjppCode(lpaUmum.getNamaDebitur());
                if(e.getValueDate()!=null) model.setValuationDate(sdf.format(e.getValueDate()));
                model.setValuerName(personValuer.getUserId().concat(" - ").concat(personValuer.getName()));
                // model.setVehOperational(rv.getJumlahMobil() + rv.getJumlahMotor() + rv.getJumlahTruk()); // field BV
                model.setVerType("RV");
                model.setWaOrderNum(e.getOrderNumber());
                model.setZipCode(detail.getZipCode());
                model.setTotalAttach(2);
                //tambahan 20/06/17
                model.setVerTime(rv.getLokasi_objek_jam());
                model.setLocObjSurvey(rv.getLokasiDitemukan() ? "Ditemukan" : "Tidak Ditemukan");
                SendToSourceLog log = new SendToSourceLog();
                log.setIdCommon(e.getId());
                log.setStatus(SendToSourceLog.ERROR);
                log.setSource(AppConstans.Source.PLUS);
                log.setAlamat("submitBvrv");
                log.setTanggal(new Date());
                log.setJenis("Kirim");
                log = sendToSourceLogDao.save(log);
                PlusClientUtils.getInstance(url).getiSyncReply().submitBvrv(model, stringHolder, integerWrapperHolder);
                log.setStatus(integerWrapperHolder.value != 0?2:1);
                log.setResponse(String.valueOf(integerWrapperHolder.value));
                log.setMessage(stringHolder.value);

                log = sendToSourceLogDao.save(log);


                //Send attachment RV
                if (log.getStatus() == SendToSourceLog.SUCCESS){
                    return sendAttachments(e, log, url, false, false);
                } else {
                    return false;
                }
            } else if (e.getAppraisalType().getName().equalsIgnoreCase(AppConstans.JenisAppraisalName.BV)) {

                LpaUmum lpaUmum = e.getDataDetail().getLpaUmum().iterator().next();
                LpaBv bv = lpaUmum.getBv().iterator().next();

                SurveyModel model = new SurveyModel();
                model.setAddr1(lpaUmum.getLokasiFisik());
                model.setKjppCode(bv.getVendor());
                model.setApplicantSameName(lpaUmum.getContactPerson());
                if(e.getClosedDate() != null) model.setApprovedDate(sdf.format(e.getClosedDate()));
                model.setApproverName(personApprover.getUserId().concat(" - ").concat(personApprover.getName())); // Plus Request di kirim NPK - Nama
                // model.setBldArea(bv.getLuasBangunan()); ??
                model.setBldCond(bv.getBertingkat() ? "Bertingkat" : "Tidak Bertingkat");
                model.setBldType(bv.getJenisBangunan());
                model.setBusinessActivity(bv.getKegiatanUsaha());
                model.setBusinessOwnType(bv.getStatusUsaha());
                model.setBusinessType(bv.getJenisUsaha());
                model.setCity(lpaUmum.getKota());
                String fasilitas = "";
                for (String r : bv.getFasilitas()) {
                    //fasilitas.concat(r).concat(",");
                    fasilitas += r+",";
                }
                if (fasilitas.length() > 0) fasilitas = fasilitas.substring(0, fasilitas.length() - 1);
                model.setFacility(fasilitas);
                Boolean foto1 = false;
                Boolean foto2 = false;
                Boolean foto3 = false;
                Boolean foto4 = false;
                Boolean foto5 = false;
                Boolean foto6 = false;
                Boolean foto7 = false;
                Boolean foto8 = false;
                Integer fotoCounter = 0;
                for(int i = 0; i < bv.getFotoPada().size(); i++) {
                    if(bv.getFotoPada().get(i).equalsIgnoreCase(parameterDao.findRVBVFotopada(AppConstans.GroupParameter.BV_FOTO_PADA, 1))) {
                        foto1 = true;
                        fotoCounter++;
                    } else if(bv.getFotoPada().get(i).equalsIgnoreCase(parameterDao.findRVBVFotopada(AppConstans.GroupParameter.BV_FOTO_PADA, 2))) {
                        foto2 = true;
                        fotoCounter++;
                    } else if(bv.getFotoPada().get(i).equalsIgnoreCase(parameterDao.findRVBVFotopada(AppConstans.GroupParameter.BV_FOTO_PADA, 3))) {
                        foto3 = true;
                        fotoCounter++;
                    } else if(bv.getFotoPada().get(i).equalsIgnoreCase(parameterDao.findRVBVFotopada(AppConstans.GroupParameter.BV_FOTO_PADA, 4))) {
                        foto4 = true;
                        fotoCounter++;
                    } else if(bv.getFotoPada().get(i).equalsIgnoreCase(parameterDao.findRVBVFotopada(AppConstans.GroupParameter.BV_FOTO_PADA, 5))) {
                        foto5 = true;
                        fotoCounter++;
                    } else if(bv.getFotoPada().get(i).equalsIgnoreCase(parameterDao.findRVBVFotopada(AppConstans.GroupParameter.BV_FOTO_PADA, 6))) {
                        foto6 = true;
                        fotoCounter++;
                    } else if(bv.getFotoPada().get(i).equalsIgnoreCase(parameterDao.findRVBVFotopada(AppConstans.GroupParameter.BV_FOTO_PADA, 7))) {
                        foto7 = true;
                        fotoCounter++;
                    } else if(bv.getFotoPada().get(i).equalsIgnoreCase(parameterDao.findRVBVFotopada(AppConstans.GroupParameter.BV_FOTO_PADA, 8))) {
                        foto8 = true;
                        fotoCounter++;
                    }
                    if(fotoCounter>=8) break;
                }

                model.setFoto1(foto1);
                model.setFoto2(foto2);
                model.setFoto3(foto3);
                model.setFoto4(foto4);
                model.setFoto5(foto5);
                model.setFoto6(foto6);
                model.setFoto7(foto7);
                model.setFoto8(foto8);
                model.setInfoSource1(bv.getS1Ditemukan()?"Ditemukan":"Tidak Ditemukan");
                model.setInfoSource2(bv.getS2Ditemukan()?"Ditemukan":"Tidak Ditemukan");
                model.setInfoSource3(bv.getS3Ditemukan()?"Ditemukan":"Tidak Ditemukan");
                model.setInfoSourceCoop1(bv.getS1Kooperatif() ? "Ya" : "Tidak");
                model.setInfoSourceCoop2(bv.getS2Kooperatif() ? "Ya" : "Tidak");
                model.setInfoSourceCoop3(bv.getS3Kooperatif() ? "Ya" : "Tidak");
                model.setInfoSourceGender1(StringUtils.equalsIgnoreCase(bv.getS1Kelamin(), "L")?"Pria":"Wanita");
                model.setInfoSourceGender2(StringUtils.equalsIgnoreCase(bv.getS2Kelamin(), "L")?"Pria":"Wanita");
                model.setInfoSourceGender3(StringUtils.equalsIgnoreCase(bv.getS3Kelamin(), "L")?"Pria":"Wanita");
                model.setInfoSourceKnow2(bv.getS2Kenal() ? "Ya" : "Tidak");
                model.setInfoSourceKnow3(bv.getS3Kenal() ? "Ya" : "Tidak");
                model.setInfoSourceName1(bv.getS1Nama());
                model.setInfoSourceName2(bv.getS2Nama());
                model.setInfoSourceName3(bv.getS3Nama());
                model.setInfoSourceRel1(bv.getS1Hubungan());
                model.setInfoSourceRel2(bv.getS2Hubungan());
                model.setInfoSourceRel3(bv.getS3Hubungan());
                model.setInfoSourceRemark1(bv.getInfoS1());
                model.setInfoSourceRemark2(bv.getInfoS2());
                model.setInfoSourceRemark3(bv.getInfoS3());
                model.setLandArea(bv.getLuasTanah());
                model.setLocObjSurvey(bv.getLokasiDitemukan()?"Ditemukan":"Tidak Ditemukan");
                model.setLongOfOccup(bv.getLamaPenempatan());
                model.setNamePlate(bv.getPapanNama() ? "Ada" : "Tidak Ada");
                model.setNotes(bv.getNote());
                model.setNumOfEmployee(bv.getJumlahKaryawan());
                model.setObjectCond(bv.getKondisiUsaha() ? "Terawat" : "Tidak Terawat");
                model.setObjectOwnType(bv.getStatusLokasi());
                // model.setOccupiedBy(); rv field
                model.setOmzet(bv.getDetailOmzet().doubleValue());
                if(e.getSubmitDate() != null) model.setOrderDate(sdf.format(e.getSubmitDate()));
                if(lpaUmum.getTanggalTerimaDariEksternal() != null) model.setRecvDate(sdf.format(lpaUmum.getTanggalTerimaDariEksternal()));
                model.setReportNum(lpaUmum.getLpaNumber());
                model.setRoadCond((bv.getMasukMobil()?"":"Tidak ")+"Masuk Mobil, "+bv.getJalanTempatUsaha());
                model.setSurround(bv.getLingkunganSekitar());
                if(lpaUmum.getTanggalSurvey() != null) model.setSurveyDate(sdf.format(lpaUmum.getTanggalSurvey()));
                model.setSurveyorAcceptIn(bv.getSurveyorDiterimaDi());
                model.setSurveyorName(lpaUmum.getDitinjau());
//                model.setKjppCode(lpaUmum.getNamaDebitur());
                // model.setTotalAttach();
                if(e.getValueDate() != null) model.setValuationDate(sdf.format(e.getValueDate()));
                model.setValuerName(personValuer.getUserId().concat(" - ").concat(personValuer.getName())); // Plus Request di kirim NPK - Nama
                model.setVehOperational(bv.getJumlahMobil());
                model.setApplicantAge(Integer.toString(bv.getJumlahMotor()));     // Jumlah Motor & Truck Menggunakan field ini di karenakan tidak ada
                model.setApplicantSameName(Integer.toString(bv.getJumlahTruk())); // field tersedia sesuai dengan email dari isti DMS tanggal 18-07-2017
                // model.setVerTime();
                model.setVerType("BV");
                model.setWaOrderNum(e.getOrderNumber());
                model.setZipCode(detail.getZipCode());
                model.setTotalAttach(2);
                //tambahan 20/06/17
                model.setVerTime(bv.getLokasi_objek_jam());
                model.setBldArea(bv.getLuasBangunan());
                model.setLocObjSurvey(bv.getLokasiDitemukan() ? "Ditemukan" : "Tidak Ditemukan");



//                SendToSourceLog log = sendToSourceLogDao.getByIdCommon(e.getId());
//                if (log==null){
//                    log = new SendToSourceLog();
//                    log.setIdCommon(e.getId());
//                }

                SendToSourceLog log = new SendToSourceLog();
                log.setIdCommon(e.getId());
                log.setStatus(SendToSourceLog.ERROR);
                log.setSource(AppConstans.Source.PLUS);
                log.setAlamat("submitBV");
                log.setTanggal(new Date());
                log.setJenis("Kirim");
                log = sendToSourceLogDao.save(log);

                PlusClientUtils.getInstance(url).getiSyncReply().submitBvrv(model, stringHolder, integerWrapperHolder);

                log.setStatus(integerWrapperHolder.value != 0 ? 2 : 1);
                log.setResponse(String.valueOf(integerWrapperHolder.value));
                log.setMessage(stringHolder.value);
                log = sendToSourceLogDao.save(log);

                //send attachment
                DataCommon d = dao.get(e.getId());
                d.setSourceStatus(AppConstans.SourceStatusConstans.FAILED_SEND);
                dao.save(d);

                if (log.getStatus() == SendToSourceLog.SUCCESS) {
                    return sendAttachments(e, log, url, false, false);
                } else {
                    return false;
                }
            } else if (e.getAppraisalType().getName().equalsIgnoreCase(AppConstans.JenisAppraisalName.RAB)) {
                AppraisalModel appraisalModelRab = new AppraisalModel();
                LpaUmum lpaUmum = e.getDataDetail().getLpaUmum().iterator().next();
                LpaRab lpaRab = lpaUmum.getRab().iterator().next();
                NegativeModel[] listNegative;
                SpecialConsideration[] listSpecialConsider;
                List<SurveyImage> docPelengkap = surveyImageDao.findDokPelengkapByLpa(lpaUmum.getId());

                List<Parameter> lpaNegative = lpaUmum.getListNegative();
                List<Parameter> lpaPertimbangan = lpaUmum.getListPertimbangan();

                listNegative = new NegativeModel[lpaNegative.size()];
                listSpecialConsider = new SpecialConsideration[lpaPertimbangan.size()];

                appraisalModelRab.setBranchId(e.getBranch().getId().toString());
                appraisalModelRab.setWaOrderNum(e.getOrderNumber());
                appraisalModelRab.setReportNum(lpaUmum.getLpaNumber());
                appraisalModelRab.setReportType(e.getAppraisalType().getName());
                appraisalModelRab.setObjType(lpaUmum.getJenisObjectPenilaiFisik());
                appraisalModelRab.setAddrTypeName(lpaUmum.getNamaPerumahan());
                appraisalModelRab.setAddrPosName(lpaUmum.getNamaCluster());
                appraisalModelRab.setAddr1(lpaUmum.getLokasiFisik());
                appraisalModelRab.setBlock(lpaUmum.getBlock());
                appraisalModelRab.setAddrNo(lpaUmum.getNomor());
                appraisalModelRab.setRt(lpaUmum.getRt());
                appraisalModelRab.setRw(lpaUmum.getRw());
                appraisalModelRab.setProv(lpaUmum.getPropinsi());
                appraisalModelRab.setCity(lpaUmum.getKota());
                appraisalModelRab.setKec(lpaUmum.getKecamatan());
                appraisalModelRab.setKel(lpaUmum.getKelurahan());
                appraisalModelRab.setZipcode(lpaUmum.getKodePos());
                appraisalModelRab.setLocation(lpaUmum.getPosisiLokasiAgunan());
                if(e.getSubmitDate() != null) appraisalModelRab.setOrderDate(sdf.format(e.getSubmitDate()));
                appraisalModelRab.setValuationTo(lpaUmum.getDitujuke());
                appraisalModelRab.setCollSta(e.getCollateralStatus().getName());
                appraisalModelRab.setSLACategory(String.valueOf(lpaUmum.getSla()));


                Integer lamaPenyelesaian = dao.getJumlahHariPenyelesaian(e.getSubmitDate()==null?e.getAdminDate():e.getSubmitDate(), e.getClosedDate()).intValue();


                appraisalModelRab.setCompletionDay(lamaPenyelesaian); // Jumlah waktu Penyelesaian
                appraisalModelRab.setCompletionCat(lpaUmum.getSla() < lamaPenyelesaian ? "Melebihi" : "-"); // Kategori Penyelesaian

                if(lpaRab.getBiayaDilampirkan()!=null)appraisalModelRab.setRabTotalReq(lpaRab.getBiayaDilampirkan().doubleValue());
                appraisalModelRab.setRabBeaExclude1Desc(lpaRab.getTidakDiperhitungkan1txt());
                if(lpaRab.getTidakDiperhitungkan1()!=null)appraisalModelRab.setRabBeaExclude1(lpaRab.getTidakDiperhitungkan1().doubleValue());
                appraisalModelRab.setRabBeaExclude2Desc(lpaRab.getTidakDiperhitungkan2txt());
                if(lpaRab.getTidakDiperhitungkan2()!=null)appraisalModelRab.setRabBeaExclude2(lpaRab.getTidakDiperhitungkan2().doubleValue());
                appraisalModelRab.setRabBeaExclude3Desc(lpaRab.getTidakDiperhitungkan3txt());
                if(lpaRab.getTidakDiperhitungkan3()!=null)appraisalModelRab.setRabBeaExclude3(lpaRab.getTidakDiperhitungkan3().doubleValue());
                appraisalModelRab.setRabBeaExclude4Desc(lpaRab.getTidakDiperhitungkan4txt());
                if(lpaRab.getTidakDiperhitungkan4()!=null)appraisalModelRab.setRabBeaExclude4(lpaRab.getTidakDiperhitungkan4().doubleValue());
                appraisalModelRab.setRabBeaExclude5Desc(lpaRab.getTidakDiperhitungkan5txt());
                if(lpaRab.getTidakDiperhitungkan5()!=null)appraisalModelRab.setRabBeaExclude5(lpaRab.getTidakDiperhitungkan5().doubleValue());
                appraisalModelRab.setRabBeaInclude(lpaRab.getBiayaDiperhitungkan().doubleValue());
                appraisalModelRab.setRabTotalBuildArea(lpaRab.getLuasBangungan());
                appraisalModelRab.setRabBuildVal(lpaRab.getHargaBangunanRab().doubleValue());
                appraisalModelRab.setRabBuildValInt(lpaRab.getHargaBangunanInternal().doubleValue());
                appraisalModelRab.setRabRevCust(lpaRab.getNilaiReviewCustomer().doubleValue());
                appraisalModelRab.setRabRevInt(lpaRab.getNilaiReviewInternal().doubleValue());
                appraisalModelRab.setNotes2(parameterDao.get(Long.parseLong(lpaRab.getKewajaranNilai())).getValue());
                appraisalModelRab.setValuerName( personValuer.getUserId().concat(" - ").concat(personValuer.getName())); // Plus Request di kirim NPK - Nama);
                if(e.getValueDate() != null) appraisalModelRab.setValuationDate(sdf.format(e.getValueDate()));
                appraisalModelRab.setApproverName(personApprover.getUserId().concat(" - ").concat(personApprover.getName())); // Plus Request di kirim NPK - Nama
                if(e.getClosedDate() != null) appraisalModelRab.setApprovedDate(sdf.format(e.getClosedDate()));
                appraisalModelRab.setTotalAttach(3);
//                appraisalModelRab.setTotalAttach(3+docPelengkap.size());


                if (lpaNegative !=null && !lpaNegative.isEmpty()){
                    int  i = 0;
                    for (Parameter r : lpaNegative) {
                        NegativeModel w = new NegativeModel();
                        w.setNegListDesc(r.getValue());
                        listNegative[i] = w;
                        i++;
                    }
                }


                if (lpaPertimbangan !=null && !lpaPertimbangan.isEmpty()){
                  int  i = 0;
                    for (Parameter r : lpaPertimbangan) {
                        SpecialConsideration w = new SpecialConsideration();
                        w.setSpecialConsCode(r.getValue());
                        listSpecialConsider[i] = w;
                        i++;
                    }
                }


                SendToSourceLog log = new SendToSourceLog();
                log.setIdCommon(e.getId());
                log.setStatus(SendToSourceLog.ERROR);
                log.setSource(AppConstans.Source.PLUS);
                log.setAlamat("SubmitRAB");
                log.setTanggal(new Date());
                log.setJenis("Kirim");
                log = sendToSourceLogDao.save(log);

                // kirim ke Web Service
                PlusClientUtils.getInstance(url).getiSyncReply().submitLPAResult(appraisalModelRab, null, null, listNegative, listSpecialConsider, stringHolder, integerWrapperHolder);

                log.setMessage(stringHolder.value);
                log.setStatus(integerWrapperHolder.value != 0?2:1);

                log = sendToSourceLogDao.save(log);

                log.setResponse(String.valueOf(integerWrapperHolder.value));
                sendToSourceLogDao.save(log);


                DataCommon d = dao.get(e.getId());
                d.setSourceStatus(AppConstans.SourceStatusConstans.FAILED_SEND);
                dao.save(d);

                if (log.getStatus() == SendToSourceLog.SUCCESS){
                    StringHolder stringHolderAttact = new StringHolder();
                    IntegerWrapperHolder integerWrapperHolderAttact = new IntegerWrapperHolder();
//                    sendAttactmentToPlusRAB(e,stringHolderAttact,integerWrapperHolderAttact,log, docPelengkap);
                    return sendAttachments(e, log, url, false, false);
                } else {
                    return false;
                }
            } else if (detail.getAssetType().getCategoryType().getCode().equals(AppConstans.JenisKategoriCode.LNB)) {

                // Object order. survey, dan LPA untuk kirim
                AppraisalModel appraisalModel;
                CertModel[] listCert;
                ImbModel[] listImb;
                NegativeModel[] listNegative;
                SpecialConsideration[] listSpecialConsider;

                // Data LPA dkk
                LpaUmum lpaUmum = e.getDataDetail().getLpaUmum().iterator().next();
                LpaTanahBangunan lnb = lpaUmum.getTanahBangunan().iterator().next();
                List<LpaTanah> listTanah = lnb.getDocumentTanah();
                List<LpaBangunan> listBangunan = lnb.getDocumentBangunan();
                List<Parameter> lpaNegative = lpaUmum.getListNegative();
                List<Parameter> lpaPertimbangan = lpaUmum.getListPertimbangan();
                Boolean sendDokTanah = listDokumenTanahCheck(lnb.getId());
                Boolean sendDokBangunan = listDokumenBangunanCheck(lnb.getId());
                List<SurveyImage> docWs = surveyImageDao.findWorkshetByLpa(lpaUmum.getId());
                List<SurveyImage> docPelengkap = surveyImageDao.findDokPelengkapByLpa(lpaUmum.getId());
                Integer totalFile = 3 + (sendDokBangunan?1:0) + (sendDokTanah?1:0) + docWs.size() + docPelengkap.size();
                logger.info("Jumlah totalFile "+ totalFile);

                appraisalModel = new AppraisalModel();
                listCert = new CertModel[listTanah.size()];
                listImb = new ImbModel[listBangunan.size()];
                listNegative = new NegativeModel[lpaNegative.size()];
                listSpecialConsider = new SpecialConsideration[lpaPertimbangan.size()];

                // appraisal model atau lpa umum tanah dan bangunan
                appraisalModel.setAddr1(lpaUmum.getLokasiFisik());
                // logger.error("<Addr1>"+lpaUmum.getLokasiFisik()+"</Addr1>");
                appraisalModel.setAddrNo(lpaUmum.getNomor());
                // logger.error("<AddrNo>"+lpaUmum.getNomor()+"</AddrNo>");
                appraisalModel.setAddrPosName(lpaUmum.getNamaCluster());
                // logger.error("<AddrPosName>"+lpaUmum.getNamaCluster()+"</AddrPosName>");
                appraisalModel.setAddrTypeName(lpaUmum.getNamaPerumahan());
                // logger.error("<AddrTypeName>"+lpaUmum.getNamaPerumahan()+"<AddrTypeName>");
                if (e.getClosedDate()!=null) appraisalModel.setApprovedDate(sdf.format(e.getClosedDate()));
                // logger.error("<ApprovedDate>"+sdf.format(e.getClosedDate())+"<ApprovedDate>");
                appraisalModel.setApproverName(personApprover.getUserId().concat(" - ").concat(personApprover.getName())); // Plus Request di kirim NPK - Nama
                // logger.error("<ApproverName>"+personApprover.getUserId().concat(" - ").concat(personApprover.getName())+"<ApproverName>");
                appraisalModel.setBlock(lpaUmum.getBlock());
                // logger.error("<Block>"+lpaUmum.getBlock()+"<Block>");
                appraisalModel.setBorderEast(lnb.getBatasTimurTanah());
                // logger.error("<BorderEast>"+lnb.getBatasTimurTanah()+"<BorderEast>");
                appraisalModel.setBorderNorth(lnb.getBatasUtaraTanah());
                // logger.error("<BorderNorth>"+lnb.getBatasUtaraTanah()+"<BorderNorth>");
                appraisalModel.setBorderSouth(lnb.getBatasSelatanTanah());
                // logger.error("<BorderSouth>"+lnb.getBatasSelatanTanah()+"<BorderSouth>");
                appraisalModel.setBorderWest(lnb.getBatasBaratTanah());
                // logger.error("<BorderWest>"+lnb.getBatasBaratTanah()+"<BorderWest>");
                appraisalModel.setBorderSta(lnb.getBatasTanah());
                // logger.error("<BorderSta>"+lnb.getBatasTanah()+"<BorderSta>");
                appraisalModel.setBranchId(e.getBranch().getId().toString());
                // logger.error("<BranchId>"+e.getBranch().getId().toString()+"<BranchId>");
                appraisalModel.setBuildArea(lnb.getLuasBangunan());
                // logger.error("<BuildArea>"+lnb.getLuasBangunan()+"<BuildArea>");
                appraisalModel.setBuildAreaImb(lnb.getLuasBangunanImb());
                // logger.error("<BuildAreaImb>"+lnb.getLuasBangunanImb()+"<BuildAreaImb>");
                appraisalModel.setBuildClass(lnb.getKelasBangunan());
                // logger.error("<BuildClass>"+lnb.getKelasBangunan()+"<BuildClass>");
                appraisalModel.setBuildPurpose(lnb.getPeruntukanBangunan());
                // logger.error("<BuildPurpose>"+lnb.getPeruntukanBangunan()+"<BuildPurpose>");
                appraisalModel.setBuildRentalSta(lnb.getStatusAgunanDisewakan());
                // logger.error("<BuildRentalSta>"+lnb.getStatusAgunanDisewakan()+"<BuildRentalSta>");
                appraisalModel.setBuildSta(lnb.getStatusBangunan());
                // logger.error("<BuildSta>"+lnb.getStatusBangunan()+"<BuildSta>");
                //proses konstruksi
                appraisalModel.setProgresBangunan(lnb.getProsesKonstruksi()==null?0:lnb.getProsesKonstruksi());
                // logger.error("<ProgresBangunan>"+lnb.getProsesKonstruksi()==null?0:lnb.getProsesKonstruksi()+"<ProgresBangunan>");
                appraisalModel.setBuildType(lnb.getJenisBangunan());
                // logger.error("<BuildType>"+lnb.getJenisBangunan()+"<BuildType>");
                appraisalModel.setCity(detail.getCity());
                // logger.error("<City>"+detail.getCity()+"<City>");
                appraisalModel.setCollSta(e.getCollateralStatus().getName());
                // logger.error("<CollSta>"+e.getCollateralStatus().getName()+"<CollSta>");


                Integer lamaPenyelesaian = dao.getJumlahHariPenyelesaian(e.getSubmitDate()==null?e.getAdminDate():e.getSubmitDate(), e.getClosedDate()).intValue();


                appraisalModel.setCompletionDay(lamaPenyelesaian);
                // logger.error("<CompletionDay>"+lamaPenyelesaian+"<CompletionDay>");
                appraisalModel.setCompletionCat(lpaUmum.getSla() < lamaPenyelesaian ? "Melebihi" : "-");
                // logger.error("<CompletionCat>"+(lpaUmum.getSla() < lamaPenyelesaian ? "Melebihi" : "-")+"<CompletionCat>");
                // ini SLA waktu terima s.d. close
                appraisalModel.setDapatRoda4(lnb.getKendaraanRoda4());
                // logger.error("<DapatRoda4>"+lnb.getKendaraanRoda4()+"<DapatRoda4>");
                appraisalModel.setDesinty(lnb.getTingkatKepadatan());
                // logger.error("<Desinty>"+lnb.getTingkatKepadatan()+"<Desinty>");
                appraisalModel.setDevStatus(lnb.getProsesPembangunan());
                // logger.error("<DevStatus>"+lnb.getProsesPembangunan()+"<DevStatus>");
                appraisalModel.setExtBldLiquidValDoc(lnb.getLikuidasiDokumen2Eks()==null?0d:lnb.getLikuidasiDokumen2Eks().doubleValue());
                // logger.error("<ExtBldLiquidValDoc>"+(lnb.getLikuidasiDokumen2Eks()==null?0d:lnb.getLikuidasiDokumen2Eks().doubleValue())+"<ExtBldLiquidValDoc>");
                appraisalModel.setExtBldLiquidValPhy(lnb.getLikuidasiFisik2Eks()==null?0d:lnb.getLikuidasiFisik2Eks().doubleValue());
                // logger.error("<ExtBldLiquidValPhy>"+(lnb.getLikuidasiFisik2Eks()==null?0d:lnb.getLikuidasiFisik2Eks().doubleValue())+"<ExtBldLiquidValPhy>");
                appraisalModel.setExtBldMarketPriceDoc(lnb.getNilaiDokumen2Eks()==null?0d:lnb.getNilaiDokumen2Eks().doubleValue());
                // logger.error("<ExtBldMarketPriceDoc>"+(lnb.getNilaiDokumen2Eks()==null?0d:lnb.getNilaiDokumen2Eks().doubleValue())+"<ExtBldMarketPriceDoc>");
                appraisalModel.setExtBldMarketPricePhy(lnb.getNilaiFisik2Eks()==null?0d:lnb.getNilaiFisik2Eks().doubleValue());
                // logger.error("<ExtBldMarketPricePhy>"+(lnb.getNilaiFisik2Eks()==null?0d:lnb.getNilaiFisik2Eks().doubleValue())+"<ExtBldMarketPricePhy>");
                appraisalModel.setExtBldMeterPricePhy(lnb.getHargaBangunanEks()==null?0d:lnb.getHargaBangunanEks().doubleValue());
                // logger.error("<ExtBldMeterPricePhy>"+(lnb.getHargaBangunanEks()==null?0d:lnb.getHargaBangunanEks().doubleValue())+"<ExtBldMeterPricePhy>");
                appraisalModel.setExtLandLiquidValDoc(lnb.getLikuidasiDokumen1Eks()==null?0d:lnb.getLikuidasiDokumen1Eks().doubleValue());
                // logger.error("<ExtLandLiquidValDoc>"+(lnb.getLikuidasiDokumen1Eks()==null?0d:lnb.getLikuidasiDokumen1Eks().doubleValue())+"<ExtLandLiquidValDoc>");
                appraisalModel.setExtLandLiquidValPhy(lnb.getLikuidasiFisik1Eks()==null?0d:lnb.getLikuidasiFisik1Eks().doubleValue());
                // logger.error("<ExtLandLiquidValPhy>"+(lnb.getLikuidasiFisik1Eks()==null?0d:lnb.getLikuidasiFisik1Eks().doubleValue())+"<ExtLandLiquidValPhy>");
                appraisalModel.setExtLandMarketPriceDoc(lnb.getNilaiDokumen1Eks()==null?0d:lnb.getNilaiDokumen1Eks().doubleValue());
                // logger.error("<ExtLandMarketPriceDoc>"+(lnb.getNilaiDokumen1Eks()==null?0d:lnb.getNilaiDokumen1Eks().doubleValue())+"<ExtLandMarketPriceDoc>");
                appraisalModel.setExtLandMarketPricePhy(lnb.getNilaiFisik1Eks()==null?0d:lnb.getNilaiFisik1Eks().doubleValue());
                // logger.error("<ExtLandMarketPricePhy>"+(lnb.getNilaiFisik1Eks()==null?0d:lnb.getNilaiFisik1Eks().doubleValue())+"<ExtLandMarketPricePhy>");
                appraisalModel.setExtLandMeterPricePhy(lnb.getHargaTanahEks()==null?0d:lnb.getHargaTanahEks().doubleValue());
                // logger.error("<ExtLandMeterPricePhy>"+(lnb.getHargaTanahEks()==null?0d:lnb.getHargaTanahEks().doubleValue())+"<ExtLandMeterPricePhy>");
                appraisalModel.setFloorArea1(lnb.getLuasLantai1());
                // logger.error("<FloorArea1>"+lnb.getLuasLantai1()+"<FloorArea1>");
                appraisalModel.setFloorArea2(lnb.getLuasLantai2());
                // logger.error("<FloorArea2>"+lnb.getLuasLantai2()+"<FloorArea2>");
                appraisalModel.setFloorArea3(lnb.getLuasLantai3());
                // logger.error("<FloorArea3>"+lnb.getLuasLantai3()+"<FloorArea3>");
                appraisalModel.setFloorArea4(lnb.getLuasLantai4());
                // logger.error("<FloorArea4>"+lnb.getLuasLantai4()+"<FloorArea4>");
                appraisalModel.setFloorArea5(lnb.getLuasLantai5());
                // logger.error("<FloorArea5>"+lnb.getLuasLantai5()+"<FloorArea5>");
                appraisalModel.setGapTotalDoc(lpaUmum.getDeviasiDoc());
                // logger.error("<GapTotalDoc>"+lpaUmum.getDeviasiDoc()+"<GapTotalDoc>");
                appraisalModel.setGapTotalPhy(lpaUmum.getDeviasiFisik());
                // logger.error("<GapTotalPhy>"+lpaUmum.getDeviasiFisik()+"<GapTotalPhy>");
                appraisalModel.setGSB(lnb.getGsb());
                // logger.error("<GSB>"+lnb.getGsb()+"<GSB>");
                appraisalModel.setGSS(lnb.getGss());
                // logger.error("<GSS>"+lnb.getGss()+"<GSS>");
                appraisalModel.setIntBldLiquidValDoc(lnb.getLikuidasiDokumen2()==null? 0d :lnb.getLikuidasiDokumen2().doubleValue());
                // logger.error("<IntBldLiquidValDoc>"+(lnb.getLikuidasiDokumen2()==null? 0d :lnb.getLikuidasiDokumen2().doubleValue())+"<IntBldLiquidValDoc>");
                appraisalModel.setIntBldLiquidValPhy(lnb.getLikuidasiFisik2()==null?0d:lnb.getLikuidasiFisik2().doubleValue());
                // logger.error("<IntBldLiquidValPhy>"+(lnb.getLikuidasiFisik2()==null?0d:lnb.getLikuidasiFisik2().doubleValue())+"<IntBldLiquidValPhy>");
                appraisalModel.setIntBldMarketPriceDoc(lnb.getNilaiDokumen2()==null?0d:lnb.getNilaiDokumen2().doubleValue());
                // logger.error("<IntBldMarketPriceDoc>"+(lnb.getNilaiDokumen2()==null?0d:lnb.getNilaiDokumen2().doubleValue())+"<IntBldMarketPriceDoc>");
                appraisalModel.setIntBldMarketPricePhy(lnb.getNilaiFisik2()==null?0d:lnb.getNilaiFisik2().doubleValue());
                // logger.error("<IntBldMarketPricePhy>"+(lnb.getNilaiFisik2()==null?0d:lnb.getNilaiFisik2().doubleValue())+"<IntBldMarketPricePhy>");
                appraisalModel.setIntBldMeterPricePhy(lnb.getHargaBangunan()==null?0d:lnb.getHargaBangunan().doubleValue());
                // logger.error("<IntBldMeterPricePhy>"+(lnb.getHargaBangunan()==null?0d:lnb.getHargaBangunan().doubleValue())+"<IntBldMeterPricePhy>");
                appraisalModel.setIntLandLiquidValDoc(lnb.getLikuidasiDokumen1()==null?0d:lnb.getLikuidasiDokumen1().doubleValue());
                // logger.error("<IntLandLiquidValDoc>"+(lnb.getLikuidasiDokumen1()==null?0d:lnb.getLikuidasiDokumen1().doubleValue())+"<IntLandLiquidValDoc>");
                appraisalModel.setIntLandLiquidValPhy(lnb.getLikuidasiFisik1()==null?0:lnb.getLikuidasiFisik1().doubleValue());
                // logger.error("<IntLandLiquidValPhy>"+(lnb.getLikuidasiFisik1()==null?0:lnb.getLikuidasiFisik1().doubleValue())+"<IntLandLiquidValPhy>");
                appraisalModel.setIntLandMarketPriceDoc(lnb.getNilaiDokumen1()==null?0d:lnb.getNilaiDokumen1().doubleValue());
                // logger.error("<IntLandMarketPriceDoc>"+(lnb.getNilaiDokumen1()==null?0d:lnb.getNilaiDokumen1().doubleValue())+"<IntLandMarketPriceDoc>");
                appraisalModel.setIntLandMarketPricePhy(lnb.getNilaiFisik1()==null?0d:lnb.getNilaiFisik1().doubleValue());
                // logger.error("<IntLandMarketPricePhy>"+(lnb.getNilaiFisik1()==null?0d:lnb.getNilaiFisik1().doubleValue())+"<IntLandMarketPricePhy>");
                appraisalModel.setIntLandMeterPricePhy(lnb.getHargaTanah()==null?0d:lnb.getHargaTanah().doubleValue());
                // logger.error("<IntLandMeterPricePhy>"+(lnb.getHargaTanah()==null?0d:lnb.getHargaTanah().doubleValue())+"<IntLandMeterPricePhy>");
                appraisalModel.setKel(detail.getSubDistrict());
                // logger.error("<Kel>"+detail.getSubDistrict()+"<Kel>");
                appraisalModel.setKec(detail.getDistrict());
                // logger.error("<kec>"+detail.getDistrict()+"<kec>");
                appraisalModel.setKjjpCode(lpaUmum.getKjpp() == null ? "" : lpaUmum.getKjpp().getId().toString());
                // logger.error("<KjjpCode>"+(lpaUmum.getKjpp() == null ? "" : lpaUmum.getKjpp().getId().toString())+"<KjjpCode>");
                appraisalModel.setLandArea(lnb.getTotalLuasTanahDokumen()==null?0:lnb.getTotalLuasTanahDokumen().intValue());
                // logger.error("<LandArea>"+(lnb.getTotalLuasTanahDokumen()==null?0:lnb.getTotalLuasTanahDokumen().intValue())+"<LandArea>");
                appraisalModel.setLandAreaCityPlan(lnb.getLuasTanahSesuaiTataKota()==null?0:lnb.getLuasTanahSesuaiTataKota().intValue());
                // logger.error("<LandAreaCityPlan>"+(lnb.getLuasTanahSesuaiTataKota()==null?0:lnb.getLuasTanahSesuaiTataKota().intValue())+"<LandAreaCityPlan>");
                appraisalModel.setLandContours(lnb.getKonturTanah());
                // logger.error("<LandContours>"+lnb.getKonturTanah()+"<LandContours>");
//                appraisalModel.setLandForm(lnb.getJenisTanah());
                appraisalModel.setLandForm(lnb.getBentukTanah());
                // logger.error("<LandForm>"+lnb.getBentukTanah()+"<LandForm>");
                appraisalModel.setLandPurpose(lnb.getPeruntukanTanah());
                // logger.error("<LandPurpose>"+lnb.getPeruntukanTanah()+"<LandPurpose>");
                appraisalModel.setLandRoadHeight(lnb.getKetinggianTanah());
                // logger.error("<LandRoadHeight>"+lnb.getKetinggianTanah()+"<LandRoadHeight>");
                appraisalModel.setLandType(lnb.getKondisiTanah());
                // logger.error("<LandType>"+lnb.getKondisiTanah()+"<LandType>");
                appraisalModel.setLocation(lpaUmum.getPosisiLokasiAgunan());
                // logger.error("<Location>"+lpaUmum.getPosisiLokasiAgunan()+"<Location>");
                appraisalModel.setMarket(lpaUmum.getFaktorMarketability());
                // logger.error("<Market>"+lpaUmum.getFaktorMarketability()+"<Market>");
                appraisalModel.setMeetPerson(lpaUmum.getDitemui());
                // logger.error("<MeetPerson>"+lpaUmum.getDitemui()+"<MeetPerson>");
                appraisalModel.setNotes1(lpaUmum.isEksternal()?lpaUmum.getCatatanKewajaranNil():lpaUmum.getCatatanPoint());
                // logger.error("<Notes1>"+(lpaUmum.isEksternal()?lpaUmum.getCatatanKewajaranNil():lpaUmum.getCatatanPoint())+"<Notes1>");
                appraisalModel.setNotes2(lpaUmum.isEksternal()?lpaUmum.getCatatanKesesuaian():"");
                // logger.error("<Notes2>"+(lpaUmum.isEksternal()?lpaUmum.getCatatanKesesuaian():"")+"<Notes2>");
                appraisalModel.setNumOfFloor(lnb.getJumlahLantaiFisik());
                // logger.error("<NumOfFloor>"+lnb.getJumlahLantaiFisik()+"<NumOfFloor>");
                appraisalModel.setNumOfFloorImb(listBangunan.get(0).getJumlahLantai()==null?0:listBangunan.get(0).getJumlahLantai().intValue());
                // logger.error("<NumOfFloorImb>"+(listBangunan.get(0).getJumlahLantai()==null?0:listBangunan.get(0).getJumlahLantai().intValue())+"<NumOfFloorImb>");
                appraisalModel.setFrontView(Integer.toString(lnb.getJumlahLantaiTampakDepan()));
                // logger.error("<FrontView>"+Integer.toString(lnb.getJumlahLantaiTampakDepan())+"<FrontView>");
                appraisalModel.setObjType(lpaUmum.getJenisObjectPenilaiFisik());
                // logger.error("<ObjType>"+lpaUmum.getJenisObjectPenilaiFisik()+"<ObjType>");
                appraisalModel.setOccupiedBy(lnb.getDihuniOleh());
                // logger.error("<OccupiedBy>"+lnb.getDihuniOleh()+"<OccupiedBy>");
                if(e.getSubmitDate() != null) appraisalModel.setOrderDate(sdf.format(e.getSubmitDate()));
                // logger.error("<OrderDate>"+sdf.format(e.getSubmitDate())+"<OrderDate>");
                appraisalModel.setPembanding1(lpaUmum.getPembanding1());
                // logger.error("<Pembanding1>"+lpaUmum.getPembanding1()+"<Pembanding1>");
                appraisalModel.setPembanding2(lpaUmum.getPembanding2());
                // logger.error("<Pembanding2>"+lpaUmum.getPembanding2()+"<Pembanding2>");
                appraisalModel.setPembanding3(lpaUmum.getPembanding3());
                // logger.error("<Pembanding3>"+lpaUmum.getPembanding3()+"<Pembanding3>");
                appraisalModel.setProv(detail.getProvince());
                // logger.error("<Prov>"+detail.getProvince()+"<Prov>");
                appraisalModel.setRabBeaExclude1Desc(StringUtils.equalsIgnoreCase(lnb.getRencanaJalan(),"0")?"Tidak":"Ya"); // Notes karena WSDL PLUS salah type maka menggunakan field ini untuk rencana Jalan
                // logger.error("<RabBeaExclude1Desc>"+(StringUtils.equalsIgnoreCase(lnb.getRencanaJalan(),"0")?"Tidak":"Ya")+"<RabBeaExclude1Desc>");
                appraisalModel.setReportNum(lpaUmum.getLpaNumber());
                // logger.error("<ReportNum>"+lpaUmum.getLpaNumber()+"<ReportNum>");
                appraisalModel.setReportType(e.getDataDetail().getAssetType().getName());
                // logger.error("<ReportType>"+e.getDataDetail().getAssetType().getName()+"<ReportType>");
                appraisalModel.setRoadWidth(lnb.getLebarJalan().intValue());// // TODO: 7/10/2017 dr sana integer di wa double
                // logger.error("<RoadWidth>"+lnb.getLebarJalan().intValue()+"<RoadWidth>");
                appraisalModel.setRt(lpaUmum.getRt());
                // logger.error("<Rt>"+lpaUmum.getRt()+"<Rt>");
                appraisalModel.setRw(lpaUmum.getRw());
                // logger.error("<Rw>"+lpaUmum.getRw()+"<Rw>");
                appraisalModel.setSLACategory(String.valueOf(lpaUmum.getSla()));
                // logger.error("<SLACategory>"+String.valueOf(lpaUmum.getSla())+"<SLACategory>");
                appraisalModel.setSurround(lnb.getLingkunganSekitar());
                // logger.error("<Surround>"+lnb.getLingkunganSekitar()+"<Surround>");
                if (lpaUmum.getTanggalSurvey() != null) appraisalModel.setSurveyDate(sdf.format(lpaUmum.getTanggalSurvey()));
                // logger.error("<SurveyDate>"+sdf.format(lpaUmum.getTanggalSurvey())+"<SurveyDate>");
                appraisalModel.setSurveyorName(lpaUmum.getDitinjau());
                // logger.error("<SurveyorName>"+lpaUmum.getDitinjau()+"<SurveyorName>");
                appraisalModel.setValuerName(personValuer.getUserId().concat(" - ").concat(personValuer.getName())); // Notes Plus Request di kirim NPK - Nama
                // logger.error("<ValuerName>"+personValuer.getUserId().concat(" - ").concat(personValuer.getName())+"<ValuerName>");

                if (e.getValueDate()!=null) appraisalModel.setValuationDate(sdf.format(e.getValueDate()));
                // logger.error("<ValuationDate>"+sdf.format(e.getValueDate())+"<ValuationDate>");
                appraisalModel.setTrafic(lnb.getTingkatKemacetan());
                // logger.error("<Trafic>"+lnb.getTingkatKemacetan()+"<Trafic>");
                appraisalModel.setValuationTo(lpaUmum.getDitujuke());
                // logger.error("<ValuationTo>"+lpaUmum.getDitujuke()+"<ValuationTo>");
                appraisalModel.setWaOrderNum(e.getOrderNumber());
                // logger.error("<WaOrderNum>"+e.getOrderNumber()+"<WaOrderNum>");
                appraisalModel.setZipcode(detail.getZipCode());
                // logger.error("<Zipcode>"+detail.getZipCode()+"<Zipcode>");
                appraisalModel.setTotalAttach(totalFile);
                // logger.error("<TotalAttach>"+totalFile+"<TotalAttach>");

                int i = 0;
                for (LpaTanah r : listTanah) {
                    CertModel w = new CertModel();
                    // logger.error("<CertModel>");
                    w.setAddr1(r.getNamaJalan());
                    // logger.error("<Addr1>"+r.getNamaJalan()+"<Addr1>");
                    w.setAddrNo(r.getNomor());
                    // logger.error("<AddrNo>"+r.getNomor()+"<AddrNo>");
                    w.setBlock(r.getBlockGangLantai());
                    // logger.error("<Block>"+r.getBlockGangLantai()+"<Block>");

                    if (r.getJenisDokumen() !=null){
                        w.setCertCode(r.getJenisDokumen().getName());
                        // logger.error("<CertCode>"+r.getJenisDokumen().getName()+"<CertCode>");
                    }
                    w.setCertNum(r.getNamaDokumen());
                    // logger.error("<CertNum>"+r.getNamaDokumen()+"<CertNum>");
                    w.setCity(r.getKota());
                    // logger.error("<City>"+r.getKota()+"<City>");
                    if(r.getTanggalBerakhirHak()!=null){
                        w.setExpDate(sdf.format(r.getTanggalBerakhirHak()));
                        // logger.error("<ExpDate>"+sdf.format(r.getTanggalBerakhirHak())+"<ExpDate>");
                    }
                    if(r.getTanggalSuratUkur()!=null){
                        w.setGSDate(sdf.format(r.getTanggalSuratUkur()));
                        // logger.error("<GSDate>"+sdf.format(r.getTanggalSuratUkur())+"<GSDate>");
                    }
                    w.setGSNum(r.getSuratUkur());
                    // logger.error("<GSNum>"+r.getSuratUkur()+"<GSNum>");
                    if(r.getTanggalSertipikat()!=null){
                        w.setIssuedDate(sdf.format(r.getTanggalSertipikat()));
                        // logger.error("<IssuedDate>"+sdf.format(r.getTanggalSertipikat())+"<IssuedDate>");
                    }
                    w.setKec(r.getKecamatan());
                    // logger.error("<kec>"+r.getKecamatan()+"<kec>");
                    w.setKel(r.getKelurahan());
                    // logger.error("<Kel>"+r.getKelurahan()+"<Kel>");
                    w.setLandArea(r.getLuasTanah());
                    // logger.error("<LandArea>"+r.getLuasTanah()+"<LandArea>");
                    w.setOwnerName(r.getNamaPemegangHak());
                    // logger.error("<OwnerName>"+r.getNamaPemegangHak()+"<OwnerName>");
                    w.setProv(r.getPropinsi());
                    // logger.error("<Prov>"+r.getPropinsi()+"<Prov>");
                    w.setRw(r.getRw());
                    // logger.error("<Rw>"+r.getRw()+"<Rw>");
                    w.setRt(r.getRt());
                    // logger.error("<Rt>"+r.getRt()+"<Rt>");
                    // logger.error("</CertModel>");
                    listCert[i] = w;
                    i++;
                }

                i = 0;
                for (LpaBangunan r : listBangunan) {
                    ImbModel w = new ImbModel();
                    // logger.error("<ImbModel>");
                    w.setBuildArea(r.getLuasBangunan()==null?0: r.getLuasBangunan().intValue());
                    // logger.error("<BuildArea>"+(r.getLuasBangunan()==null?0: r.getLuasBangunan().intValue())+"<BuildArea>");
                    w.setImbNum(r.getNomorImb());
                    // logger.error("<ImbNum>"+r.getNomorImb()+"<ImbNum>");

                    if (r.getTanggalImb() !=null){
                        w.setIssuedDate(sdf.format(r.getTanggalImb()));
                        // logger.error("<IssuedDate>"+sdf.format(r.getTanggalImb())+"<IssuedDate>");
                    }

                    w.setPurpose(r.getIjinSesuaiImb());
                    // logger.error("<Purpose>"+r.getIjinSesuaiImb()+"<Purpose>");
                    // logger.error("<ImbModel>");
                    listImb[i] = w;
                    i++;
                }

                i = 0;
                for (Parameter r : lpaNegative) {
                    NegativeModel w = new NegativeModel();
                    // logger.error("<NefativeModel>");
                    w.setNegListDesc(r.getValue());
                    // logger.error("<NegListDesc>"+r.getValue()+"<NegListDesc>");
                    // logger.error("<NefativeModel>");
                    listNegative[i] = w;
                    i++;
                }

                i = 0;
                for (Parameter r : lpaPertimbangan) {
                    SpecialConsideration w = new SpecialConsideration();
                    // logger.error("<SpecialConsideration>");
                    w.setSpecialConsCode(r.getValue());
                    // logger.error("<SpecialConsCode>"+r.getValue()+"<SpecialConsCode>");
                    // logger.error("<SpecialConsideration>");
                    listSpecialConsider[i] = w;
                    i++;
                }

//                SendToSourceLog log = sendToSourceLogDao.getByIdCommon(e.getId());
//                if (log==null){
//                    log = new SendToSourceLog();
//                    log.setIdCommon(e.getId());
//                }

                SendToSourceLog log = new SendToSourceLog();
                log.setIdCommon(e.getId());
                log.setStatus(SendToSourceLog.ERROR);
                log.setSource(AppConstans.Source.PLUS);
                log.setAlamat("submitLPAResult");
                log.setTanggal(new Date());
                log.setJenis("Kirim");
                log = sendToSourceLogDao.save(log);

                // kirim ke Web Service
                PlusClientUtils.getInstance(url).getiSyncReply().submitLPAResult(appraisalModel, listCert, listImb, listNegative, listSpecialConsider, stringHolder, integerWrapperHolder);

                log.setMessage(stringHolder.value);
                log.setStatus(integerWrapperHolder.value != 0?2:1);

                log = sendToSourceLogDao.save(log);

                log.setResponse(String.valueOf(integerWrapperHolder.value));
                sendToSourceLogDao.save(log);


                DataCommon d = dao.get(e.getId());
                d.setSourceStatus(AppConstans.SourceStatusConstans.FAILED_SEND);
                dao.save(d);

                if (log.getStatus() == SendToSourceLog.SUCCESS){
                    return sendAttachments(e, log, url, checkLampTanah(lnb.getId()), checkLampBangunan(lnb.getId()));
                } else {
                    return false;
                }
            }
        } catch (Exception ex) {
            SendToSourceLog log = sendToSourceLogDao.getByIdCommon(e.getId());
            if (log==null){
                log = new SendToSourceLog();
                log.setIdCommon(e.getId());
            }

            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.PLUS);
            log.setMessage("Catch ERROR Send Order: "+ex.getMessage());
            log.setAlamat("submitLPAResult");
            log.setTanggal(new Date());
            log = sendToSourceLogDao.save(log);


            DataCommon d = dao.get(e.getId());
            d.setSourceStatus(AppConstans.SourceStatusConstans.FAILED_SEND);
            dao.save(d);
            ex.printStackTrace();
            return false;
        }
        return status;
    }

    @Override
    @Transactional(readOnly = false)
    public void sendToRwa(Long id) {
        sendToRwa(dao.get(id));
    }



    @Override
    @Transactional(readOnly = false)
    public void sendToCarm(Long id) {
        sendToCarm(dao.get(id));
    }




    @Override
    @Transactional(readOnly = false)
    public void sendToPlus(Long id) {
        sendToPlus(dao.get(id));
    }


    @Override
    @Transactional(readOnly = false)
    public void sendRejectPlus(Long id) {
        DataCommon d =dao.get(id);
        StringHolder msg = new StringHolder(d.getRejectList().get(d.getRejectList().size()-1).getMessage());
        String userId = d.getRejectList().get(d.getRejectList().size()-1).getPerson().getUserId();
        sendRejectPlus(dao.get(id), userId, msg);
    }

    @Override
    @Transactional(readOnly = false)
    public void sendRejectCarm(Long id) {
        DataCommon d =dao.get(id);
        String msg = d.getRejectList().get(d.getRejectList().size()-1).getMessage();
        Person userId = d.getRejectList().get(d.getRejectList().size()-1).getPerson();
        sendRejectCarm(dao.get(id), userId, msg);
    }



    private void sendToRwa(DataCommon e) {
        DataDetail detail = e.getDataDetail();
        if (detail.getAssetType().getCategoryType().getCode().equalsIgnoreCase(AppConstans.JenisKategoriCode.LNB)) {

            SimpleDateFormat tglFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat progresFormat = new SimpleDateFormat("yyyyMMdd");

            // Data LPA dkk
            LpaUmum lpaUmum = e.getDataDetail().getLpaUmum().iterator().next();
            LpaTanahBangunan lnb = lpaUmum.getTanahBangunan().iterator().next();

            if (ListUtils.isNotEmpty(e.getIloan())) {

                Date waktuKirim = new Date();

                SendToSourceLog log = new SendToSourceLog();
                log.setStatus(SendToSourceLog.SAVE);
                log.setSource(AppConstans.Source.RWA);
                log.setAlamat("wbapr_opr");
                log.setTanggal(waktuKirim);
                log = sendToSourceLogDao.save(log);

                try {
                    Wbapr_idLocator locator = new Wbapr_idLocator();
                    Wbapr_prt service = locator.getwbapr_srv_Port(new URL(systemParamFacade.getValue(AppConstans.SystemParamCode.RWA_WS_IP_SEND)));
                    ((Wbapr_bndStub) service).setUsername(systemParamFacade.getValue(AppConstans.SystemParamCode.RWA_WS_USER));
                    ((Wbapr_bndStub) service).setPassword(systemParamFacade.getValue(AppConstans.SystemParamCode.RWA_WS_PASSWORD));
                    ((Wbapr_bndStub) service).setTimeout(timeout);

                    StringHolder var1 = new StringHolder();
                    StringHolder var2 = new StringHolder();
                    boolean allSuccess = true;
                    for (IloanProgres r : e.getIloan()) {
                        // r.getHeadId() == null jika parent, tidak null jika child, lihat proses saveRwa
                        r.setWaktuKirim(waktuKirim);
                        try {
                            service.wbapr_opr(
                                    e.getCollateralId(), // collid
                                    lpaUmum.getLpaNumber(), // nolap
                                    e.getValueBy(), // created
                                    ParserUtils.parseToDate(tglFormat, lpaUmum.getTanggalSurvey(), "0"), // tgls
                                    r.getHeadId() == null ? ParserUtils.parseToFloat(lnb.getNilaiDokumen1()) : 0f, // i_tnhdoc
                                    r.getHeadId() == null ? ParserUtils.parseToFloat(lnb.getNilaiDokumen2()) : 0f, // i_bgndoc
                                    r.getHeadId() == null ? ParserUtils.parseToFloat(lnb.getNilaiFisik1()) : 0f, // i_tnhfsk
                                    r.getHeadId() == null ? ParserUtils.parseToFloat(lnb.getNilaiFisik2()) : 0f, // i_bgnfsk
                                    "", // createde, ext survey
                                    r.getHeadId() == null ? ParserUtils.parseToFloat(lnb.getNilaiDokumen1Eks()) : 0f, // e_tnhdoc
                                    r.getHeadId() == null ? ParserUtils.parseToFloat(lnb.getNilaiDokumen2Eks()) : 0f, // e_bgndoc
                                    r.getHeadId() == null ? ParserUtils.parseToFloat(lnb.getNilaiFisik1Eks()) : 0f, // e_tnhfsk
                                    r.getHeadId() == null ? ParserUtils.parseToFloat(lnb.getNilaiFisik2Eks()) : 0f, // e_bgnfsk
                                    ParserUtils.parseToFloat(ParserUtils.parseToDate(progresFormat, lnb.getTanggalProsesKonstruksi(), "0")), // tglprg
                                    ParserUtils.parseToFloat(lnb.getProsesKonstruksi()), // persenprg
                                    var1,
                                    var2
                            );
                            r.setStatus(IloanProgres.STATUS_SEND_SUKSES);
                            r.setRespon(var1.value + " - " + var2.value);
                            iloanProgresDao.save(r);
                        } catch (java.rmi.RemoteException ex) {
                            allSuccess = false;
                            r.setStatus(IloanProgres.STATUS_SEND_GAGAL);
                            r.setRespon(ex.getMessage());
                            iloanProgresDao.save(r);
                            break; // lupakan selanjutnya jika ada gagal
                        }
                    }
                    if (allSuccess) {
                        log.setMessage("OK");
                        log.setResponse("OK");
                    } else {
                        log.setMessage("not OK");
                        log.setResponse("Lihat data detail di ILoan Progress");
                    }
                    sendToSourceLogDao.save(log);
                } catch (javax.xml.rpc.ServiceException ex) {
                    log.setMessage(ex.getMessage());
                    log.setResponse("error");
                    sendToSourceLogDao.save(log);
                }catch (MalformedURLException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    private Boolean lnbInternalCarm(DataCommon e) {
        LpaUmum lpaUmum = e.getDataDetail().getLpaUmum().iterator().next();
        try {
//            ReceiveAppraisalServiceLocator locator = new ReceiveAppraisalServiceLocator();
//            ReceiveAppraisalServiceSoap_PortType service = locator.getReceiveAppraisalServiceSoap(new URL(systemParamFacade.getValue("WS_CARM_IP")));
//            ((ReceiveAppraisalServiceSoap_BindingStub) service).setUsername(systemParamFacade.getValue("WS_CARM_USER"));
//            ((ReceiveAppraisalServiceSoap_BindingStub) service).setPassword(systemParamFacade.getValue("WS_CARM_PASSWORD"));
//            ((ReceiveAppraisalServiceSoap_BindingStub) service).setTimeout(timeout);
            L_B_Internal lnbInternal = new L_B_Internal();


            lnbInternal.setACL_ID(e.getSourceId());

            Catatan_yang_Perlu_Diperhatikan_L_B_Internal catatan = new Catatan_yang_Perlu_Diperhatikan_L_B_Internal();
            catatan.setCATATAN_DIPERHATIKAN(lpaUmum.getCatatanPoint());
            if (ListUtils.isNotEmpty(lpaUmum.getListNegative())) {
                String[] listData = new String[lpaUmum.getListNegative().size()];
                for (int i = 0; i < lpaUmum.getListNegative().size(); i++) {
                    Parameter param = lpaUmum.getListNegative().get(i);
                    listData[i] = param.getValue();

                }
                Negative_List_Appraisal_L_B_Internal negatifList = new Negative_List_Appraisal_L_B_Internal(listData);
                lnbInternal.setNegative_List_Appraisal(negatifList);
                catatan.setNEGATIF(lpaUmum.getListNegative().get(0).getValue());
            }


            if (ListUtils.isNotEmpty(lpaUmum.getListPertimbangan())) {
                String[] listData = new String[lpaUmum.getListPertimbangan().size()];

                for (int i = 0; i < lpaUmum.getListPertimbangan().size(); i++) {
                    Parameter param = lpaUmum.getListPertimbangan().get(i);
                    listData[i] = param.getValue();

                }
                Property_Dalam_Perhatian_Khusus_L_B_Internal positifList = new Property_Dalam_Perhatian_Khusus_L_B_Internal(listData);
                lnbInternal.setProperty_Dalam_Perhatian_Khusus(positifList);
                catatan.setPOSITIF(lpaUmum.getListPertimbangan().get(0).getValue());
            }


            lnbInternal.setCatatan_yang_Perlu_Diperhatikan(catatan);

            // list data tanah
            LpaTanahBangunan lnb = lpaUmum.getTanahBangunan().iterator().next();

            Data_Tanah_item_L_B_Internal[] listData = null;
            if (ListUtils.isNotEmpty(lnb.getDocumentTanah())) {
                listData = new Data_Tanah_item_L_B_Internal[lnb.getDocumentTanah().size()];
                for (int i = 0; i < lnb.getDocumentTanah().size(); i++) {
                    Data_Tanah_item_L_B_Internal data1 = new Data_Tanah_item_L_B_Internal();

                    LpaTanah tanah = lnb.getDocumentTanah().get(i);

                    data1.setJENIS_DOKUMEN(tanah.getJenisDokumen() == null?"":tanah.getJenisDokumen().getName());
                    data1.setLUASDOK_1(tanah.getLuasTanah()==null?BigDecimal.ZERO:new BigDecimal(tanah.getLuasTanah()));
                    data1.setNO_DOKUMEN(tanah.getNamaDokumen());
                    if (tanah.getTanggalBerakhirHak() != null) {
                        data1.setTGL_BERAKHIRNYA_HAK(ParserUtils.parseToCalendar(tanah.getTanggalBerakhirHak()));
                    }
                    if (tanah.getTanggalSertipikat() != null) {
                        data1.setTGL_DOKUMEN(ParserUtils.parseToCalendar(tanah.getTanggalSertipikat()));
                    }
                    data1.setNAMA_PEMEGANG_HAK(tanah.getNamaPemegangHak());
                    listData[i] = data1;

                }

            }
            // data tanah
            Data_Tanah_L_B_Internal tanah = new Data_Tanah_L_B_Internal();
            tanah.setList_of_Data_Tanah(listData);
            tanah.setTotal_luas_tanah(new BigDecimal(lnb.getTotalLuasTanahDokumen() == null ? 0d : lnb.getTotalLuasTanahDokumen()));
            lnbInternal.setData_Tanah(tanah);

            // list data bangunan
            Data_Bangunan_L_B_Internal bangunan = new Data_Bangunan_L_B_Internal();

            bangunan.setKELASBANGUNAN(lnb.getKelasBangunan());
            bangunan.setDIPERGUNAKAN_SEBAGAI(lnb.getDigunakanSebagai());
            bangunan.setDIHUNI_OLEH(lnb.getDihuniOleh());
            bangunan.setSTATUSAGUNAN(lnb.getStatusAgunanDisewakan());
            bangunan.setLANTAI_SESUAI_FISIK(lnb.getJumlahLantaiFisik());
            bangunan.setPROSESPEMBANGUNAN(lnb.getProsesPembangunan());
            bangunan.setTotal_Physical_Building_Area(lnb.getLuasBangunan()==null?BigDecimal.ZERO:BigDecimal.valueOf(lnb.getLuasBangunan()));


            // list sertifikat bangunan


            if (ListUtils.isNotEmpty(lnb.getDocumentBangunan())) {
                List<LpaBangunan> entityList = lnb.getDocumentBangunan();
                Data_Bangunan_item_L_B_Internal[] listBangunan = new Data_Bangunan_item_L_B_Internal[entityList.size()];
                for (int i = 0; i < entityList.size(); i++) {
                    LpaBangunan b = entityList.get(i);
                    Data_Bangunan_item_L_B_Internal dataItem = new Data_Bangunan_item_L_B_Internal();
                    dataItem.setNO_IMB(b.getNomorImb());
                    dataItem.setTGL_IMB(ParserUtils.parseToCalendar(b.getTanggalImb()));
                    dataItem.setIJIN_SESUAI_IMB(b.getIjinSesuaiImb());
                    dataItem.setLUAS_SESUAI_IMB(b.getLuasBangunan() == null ? "" : b.getLuasBangunan().toString());
                    listBangunan[i] = dataItem;
                }
                bangunan.setLANTAI_SESUAI_IMB(entityList.get(0).getJumlahLantai()==null?0:entityList.get(0).getJumlahLantai().intValue());
                bangunan.setList_of_Data_Bangunan(listBangunan);
            }


            lnbInternal.setData_Bangunan(bangunan);


            // data detail order
            Order_Data_Detail_L_B_Internal detail = new Order_Data_Detail_L_B_Internal();

            detail.setKODECABANG(e.getBranch()==null?"":e.getBranch().getId().toString());
            detail.setORDER_ID(Long.parseLong(e.getOrderNumber()));
            detail.setNO_LAPORAN(lpaUmum.getLpaNumber());
            detail.setJENIS_LAPORAN(e.getTypeReport());
            detail.setNAMA(e.getName());
            detail.setJENIS_OBJECT(lpaUmum.getJenisObjectPenilain());
            detail.setOBJECTFISIK(lpaUmum.getJenisObjectPenilaiFisik());
            detail.setNAMAKOMPLEK(lpaUmum.getNamaPerumahan());
            detail.setCluster_Name_Tower_Name(lpaUmum.getNamaCluster());
            detail.setLOKASI(lpaUmum.getLokasiFisik());
            detail.setBlock_Alley_Floor(lpaUmum.getBlock());
            detail.setNumber(lpaUmum.getNomor());
            detail.setRT(lpaUmum.getRt());
            detail.setRW(lpaUmum.getRw());
            detail.setPROPINSI(lpaUmum.getPropinsi());
            detail.setKABUPATEN(lpaUmum.getKota());
            detail.setKECAMATAN(lpaUmum.getKecamatan());
            detail.setKELURAHAN(lpaUmum.getKelurahan());
            detail.setKODEPOS(lpaUmum.getKodePos());
            detail.setLOKASIAGUNAN(lpaUmum.getPosisiLokasiAgunan());

            Calendar c = new GregorianCalendar();
            if (lpaUmum.getTanggalOrder() != null) {
                c.setTime(lpaUmum.getTanggalOrder());
                detail.setTGL_ORDER(c);
            }

            if (lpaUmum.getTanggalSurvey() != null) {
                c.setTime(lpaUmum.getTanggalSurvey());
                detail.setTGL_SURVEY(c);
            }


            detail.setDITUJUKAN_KE(lpaUmum.getDitujuke());
            detail.setDITINJAU_OLEH(lpaUmum.getDitinjau());
            detail.setSTATUSJAMINAN(e.getCollateralStatus()==null?"":e.getCollateralStatus().getName());
            detail.setSLAAGUNAN(lpaUmum.getSla());
            Integer lamaPenyelesaian = dao.getJumlahHariPenyelesaian(e.getSubmitDate()==null?e.getAdminDate():e.getSubmitDate(), e.getClosedDate()).intValue();
            detail.setNumber_of_the_Day_Completion(lamaPenyelesaian);
            detail.setCategory_of_Completion(lpaUmum.getSla() < lamaPenyelesaian ? "Melebihi" : "-");
            lnbInternal.setOrder_Data_Detail(detail);

            // factor marekt
            lnbInternal.setFaktor_Marketability(new Faktor_Marketability_L_B_Internal(lpaUmum.getFaktorMarketability()));


            // informasi nilai
            Informasi_Nilai_L_B_Internal nilai = new Informasi_Nilai_L_B_Internal();
            nilai.setCURRENCY(lpaUmum.getMataUang());
            nilai.setNILAI_DOK_PASAR_TANAH(lnb.getNilaiDokumen1());
            nilai.setNILAI_DOK_PASAR_BANGUNAN(lnb.getNilaiDokumen2());
            nilai.setNILAI_DOK_LIKUIDASI_TANAH(lnb.getLikuidasiDokumen1());
            nilai.setNILAI_DOK_LIKUIDASI_BANGUNAN(lnb.getLikuidasiDokumen2());
            nilai.setNILAI_FISIK_PASAR_TANAH(lnb.getNilaiFisik1());
            nilai.setNILAI_FISIK_PASAR_BANGUNAN(lnb.getNilaiFisik2());
            nilai.setNILAI_FISIK_LIKUIDASI_TANAH(lnb.getLikuidasiFisik1());
            nilai.setNILAI_FISIK_LIKUIDASI_BANGUNAN(lnb.getLikuidasiFisik2());
            lnbInternal.setInformasi_Nilai(nilai);

            // informasi pembanding
            Data_Pembanding_L_B_Internal informasi = new Data_Pembanding_L_B_Internal();

            informasi.setDATA_PEMBANDING1(lpaUmum.getPembanding1());
            informasi.setDATA_PEMBANDING2(lpaUmum.getPembanding2());
            informasi.setDATA_PEMBANDING3(lpaUmum.getPembanding3());
            lnbInternal.setData_Pembanding(informasi);

            // valuer
            Approval_L_B_Internal approval = new Approval_L_B_Internal();
            approval.setVALUER(e.getValueBy());
            approval.setVALUE_DATE(ParserUtils.parseToCalendar(e.getValueDate()));
            approval.setAPPROVAL(e.getClosedBy());
            approval.setAPPROVE_DATE(ParserUtils.parseToCalendar(e.getClosedDate()));
            lnbInternal.setApproval(approval);


            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("lnb_Internal_Carm");
            log.setTanggal(new Date());
            log.setJenis("Kirim");
            log = sendToSourceLogDao.save(log);

            Response responseInternal = CarmClientUtils.getInstance(systemParamFacade.getValue("WS_CARM_IP"),systemParamFacade.getValue("WS_CARM_USER"),systemParamFacade.getValue("WS_CARM_PASSWORD")).getService().receiveAppraisal_L_B_Internal(lnbInternal);

            log.setStatus(responseInternal.getError_Code().equals(AppConstans.SourceStatusConstans.SUCCESS_SEND_ORDER_CARM)?1:2);
            log.setResponse(responseInternal.getError_Code());
            log.setMessage(responseInternal.getStatus());

            log = sendToSourceLogDao.save(log);
            Boolean nextToAttach = log.getStatus()==1;

            if (nextToAttach){
//                sendAttachCarmLnb(e,log);
                return sendAttachment(e,log, systemParamFacade.getValue("WS_IP_CARM_ATT"), systemParamFacade.getValue("WS_CARM_USER_ATT"), systemParamFacade.getValue("WS_CARM_PASSWORD_ATT"), checkLampTanah(lnb.getId()), checkLampBangunan(lnb.getId()));
            } else {
                return false;
            }



//        } catch (javax.xml.rpc.ServiceException ex) {
//            SendToSourceLog log = new SendToSourceLog();
//            log.setIdCommon(e.getId());
//            log.setStatus(SendToSourceLog.ERROR);
//            log.setSource(AppConstans.Source.CARM);
//            log.setAlamat("lnb_Internal_Carm");
//            log.setTanggal(new Date());
//            log.setMessage("Error Catch javax.xml.rpc.ServiceException : "+ex.getMessage());
//            log.setResponse("ERROR");
//            log.setJenis("Kirim");
//            log = sendToSourceLogDao.save(log);
//            ex.printStackTrace();
        } catch (java.rmi.RemoteException ex) {
            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("lnb_Internal_Carm");
            log.setTanggal(new Date());
            log.setMessage("Error catch java.rmi.RemoteException : "+ex.getMessage());
            log.setResponse("ERROR");
            log.setJenis("Kirim");
            log = sendToSourceLogDao.save(log);
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("lnb_Internal_Carm");
            log.setTanggal(new Date());
            log.setMessage("Error Exception: "+ex.getMessage());
            log.setResponse("ERROR");
            log.setJenis("Kirim");
            log = sendToSourceLogDao.save(log);
            ex.printStackTrace();
            return false;
        }
    }

    private Boolean kendaraanInternalCarm(DataCommon e) {

        try {
//            ReceiveAppraisalServiceLocator locator = new ReceiveAppraisalServiceLocator();
//            ReceiveAppraisalServiceSoap_PortType service = locator.getReceiveAppraisalServiceSoap(new URL(systemParamFacade.getValue("WS_CARM_IP")));
//            ((ReceiveAppraisalServiceSoap_BindingStub) service).setUsername(systemParamFacade.getValue("WS_CARM_USER"));
//            ((ReceiveAppraisalServiceSoap_BindingStub) service).setPassword(systemParamFacade.getValue("WS_CARM_PASSWORD"));
//            ((ReceiveAppraisalServiceSoap_BindingStub) service).setTimeout(timeout);
            DataDetail detail = e.getDataDetail();
            LpaUmum lpaUmum = e.getDataDetail().getLpaUmum().iterator().next();
            LpaKendaraan lpaKendaraan = lpaUmum.getKendaraan().iterator().next();


            Vehicle_Internal vehicle_internal = new Vehicle_Internal();

            Order_Data_Detail_Vehicle_Internal detailVehicle = new Order_Data_Detail_Vehicle_Internal();
            detailVehicle.setORDER_ID(Long.parseLong(e.getOrderNumber()));
            detailVehicle.setNO_LAPORAN(lpaUmum.getLpaNumber());
            detailVehicle.setDEBITUR(e.getName());
            detailVehicle.setTGL_ORDER(ParserUtils.parseToCalendar(lpaUmum.getTanggalOrder()));
            detailVehicle.setTGL_SURVEY(ParserUtils.parseToCalendar(lpaUmum.getTanggalSurvey()));
            detailVehicle.setDITUJUKAN_KE(lpaUmum.getDitujuke());
            detailVehicle.setALAMAT(detail.getAlamat());
            detailVehicle.setPROPINSI(detail.getProvince());
            detailVehicle.setKABUPATEN(lpaUmum.getKota());
            detailVehicle.setKECAMATAN(lpaUmum.getKecamatan());
            detailVehicle.setKELURAHAN(lpaUmum.getKelurahan());
            detailVehicle.setKODEPOS(lpaUmum.getKodePos());
            detailVehicle.setDITINJAU_OLEH(lpaUmum.getDitinjau());
            detailVehicle.setDITEMUI_OLEH(lpaUmum.getDitemui());
            detailVehicle.setSTATUSJAMINAN(lpaUmum.getStatusJaminan()==null?"":lpaUmum.getStatusJaminan().toString());


            vehicle_internal.setACL_ID(e.getSourceId());
            vehicle_internal.setOrder_Data_Detail(detailVehicle);


            if (ListUtils.isNotEmpty(lpaKendaraan.getKendaraan())) {
                List_of_Data_Kendaraan_Vehicle_External listKendaraan = new List_of_Data_Kendaraan_Vehicle_External();
                List<LpaItemKendaraan> itemList = lpaKendaraan.getKendaraan();
                Data_Kendaraan_item_Vehicle_External[] kendaraanItem = new Data_Kendaraan_item_Vehicle_External[itemList.size()];
                for (int i = 0; i < itemList.size(); i++) {
                    LpaItemKendaraan item = itemList.get(i);
                    Data_Kendaraan_item_Vehicle_External target = new Data_Kendaraan_item_Vehicle_External();
                    target.setJENIS_KENDARAAN(item.getJenisKendaraan());
                    target.setMERK(item.getMerkKendaraan());
                    target.setTAHUN_PEMBUATAN(item.getTahunPembuatan()==null?"":item.getTahunPembuatan().toString());

                    kendaraanItem[i] = target;
                }

                listKendaraan.setData_Kendaraan_List(kendaraanItem);
                vehicle_internal.setList_of_Data_Kendaraan(listKendaraan);
            }


            Valuation_Vehicle_Internal valueInternal = new Valuation_Vehicle_Internal();
            valueInternal.setCURRENCY(lpaUmum.getMataUang());
            valueInternal.setNILAI_DOK_PASAR(lpaKendaraan.getTotalDokumen());
            valueInternal.setNILAI_FISIK_PASAR(lpaKendaraan.getTotalFisik());
            valueInternal.setNILAI_FISIK_LIKUIDASI(lpaKendaraan.getLikuidasiFisik());
            valueInternal.setDIBERI_NILAI_DOK(lpaKendaraan.getBeriNilaiDokumen()==null?"":lpaKendaraan.getBeriNilaiDokumen().toString());
            valueInternal.setDIBERI_NILAI_FISIK(lpaKendaraan.getBeriNilaiFisik()==null?"":lpaKendaraan.getBeriNilaiFisik().toString());
            vehicle_internal.setValuation(valueInternal);

            Catatan_yang_Perlu_Diperhatikan_Vehicle_Internal catatanInt = new Catatan_yang_Perlu_Diperhatikan_Vehicle_Internal();

            catatanInt.setCATATAN_DIPERHATIKAN(lpaUmum.getCatatanPoint());
            if (ListUtils.isNotEmpty(lpaUmum.getListNegative())) {
                catatanInt.setNEGATIF_POINT(lpaUmum.getListNegative().get(0).getValue());
            }
            if (ListUtils.isNotEmpty(lpaUmum.getListPertimbangan())) {
                catatanInt.setPOSITIF_POINT(lpaUmum.getListPertimbangan().get(0).getValue());
            }
            vehicle_internal.setCatatan_yang_Perlu_Diperhatikan(catatanInt);


            Data_Pembanding_Vehicle_Internal pembandingInt = new Data_Pembanding_Vehicle_Internal();
            pembandingInt.setDATA_PEMBANDING1(lpaUmum.getPembanding1());
            pembandingInt.setDATA_PEMBANDING2(lpaUmum.getPembanding2());
            pembandingInt.setDATA_PEMBANDING3(lpaUmum.getPembanding3());
            vehicle_internal.setData_Pembanding(pembandingInt);

            Approval_Vehicle_Internal internalApprov = new Approval_Vehicle_Internal();
            internalApprov.setVALUERNAME(personDao.get(e.getValueBy()).getName());
            internalApprov.setVALUER_DATE(ParserUtils.parseToCalendar(e.getValueDate()));
            internalApprov.setAPPROVALNAME(e.getClosedBy());
            internalApprov.setAPPROVAL_DATE(ParserUtils.parseToCalendar(e.getClosedDate()));
            vehicle_internal.setApproval(internalApprov);


            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("kendaraan_Internal_Carm");
            log.setTanggal(new Date());
            log.setJenis("Kirim");
            log = sendToSourceLogDao.save(log);

            Response responseInternalKendaraan = CarmClientUtils.getInstance(systemParamFacade.getValue("WS_CARM_IP"),systemParamFacade.getValue("WS_CARM_USER"),systemParamFacade.getValue("WS_CARM_PASSWORD")).getService().receiveAppraisal_Vehicle_Internal(vehicle_internal);

            log.setStatus(responseInternalKendaraan.getError_Code().equals(AppConstans.SourceStatusConstans.SUCCESS_SEND_ORDER_CARM)?1:2);
            log.setResponse(responseInternalKendaraan.getError_Code());
            log.setMessage(responseInternalKendaraan.getStatus());

            log = sendToSourceLogDao.save(log);
            Boolean nextToAttach = log.getStatus()==1;

            if (nextToAttach){
//                sendAttachCarmKendaraan(e,log);
                return sendAttachment(e,log, systemParamFacade.getValue("WS_IP_CARM_ATT"), systemParamFacade.getValue("WS_CARM_USER_ATT"), systemParamFacade.getValue("WS_CARM_PASSWORD_ATT"), checkListKendaraan(lpaKendaraan.getId()), false);
            } else {
                return false;
            }

//        } catch (javax.xml.rpc.ServiceException ex) {
//            SendToSourceLog log = new SendToSourceLog();
//            log.setIdCommon(e.getId());
//            log.setStatus(SendToSourceLog.ERROR);
//            log.setSource(AppConstans.Source.CARM);
//            log.setAlamat("kendaraan_Internal_Carm");
//            log.setTanggal(new Date());
//            log.setMessage("Error Catch javax.xml.rpc.ServiceException : "+ex.getMessage());
//            log.setResponse("ERROR");
//            log = sendToSourceLogDao.save(log);
//            ex.printStackTrace();
        } catch (Exception ex) {
            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("kendaraan_Internal_Carm");
            log.setTanggal(new Date());
            log.setMessage("Error Catch Exception : "+ex.getMessage());
            log.setResponse("ERROR");
            log = sendToSourceLogDao.save(log);
            ex.printStackTrace();
            return false;
        }


    }


    private Boolean kendaraanEksternalCarm(DataCommon e) {

        try {
//            ReceiveAppraisalServiceLocator locator = new ReceiveAppraisalServiceLocator();
//            ReceiveAppraisalServiceSoap_PortType service = locator.getReceiveAppraisalServiceSoap(new URL(systemParamFacade.getValue("WS_CARM_IP")));
//            ((ReceiveAppraisalServiceSoap_BindingStub) service).setUsername(systemParamFacade.getValue("WS_CARM_USER"));
//            ((ReceiveAppraisalServiceSoap_BindingStub) service).setPassword(systemParamFacade.getValue("WS_CARM_PASSWORD"));
//            ((ReceiveAppraisalServiceSoap_BindingStub) service).setTimeout(timeout);
            DataDetail detail = e.getDataDetail();
            LpaUmum lpaUmum = e.getDataDetail().getLpaUmum().iterator().next();
            LpaKendaraan lpaKendaraan = lpaUmum.getKendaraan().iterator().next();


            Vehicle_External vehicle_external = new Vehicle_External();
            vehicle_external.setACL_ID(e.getSourceId());

            Order_Data_Detail_Vehicle_External orderExternal = new Order_Data_Detail_Vehicle_External();
            orderExternal.setORDER_ID(Long.parseLong(e.getOrderNumber()));
            orderExternal.setNO_LAPORAN(lpaUmum.getLpaNumber());
            orderExternal.setCABANGBISNIS(lpaUmum.getBranchId()==null?"":lpaUmum.getBranchId().toString());
            orderExternal.setNAMADEBITUR(e.getName());
            orderExternal.setALAMAT(detail.getAlamat());
            orderExternal.setPROPINSI(detail.getProvince());
            orderExternal.setKABUPATEN(lpaUmum.getKota());
            orderExternal.setKECAMATAN(lpaUmum.getKecamatan());
            orderExternal.setKELURAHAN(lpaUmum.getKelurahan());
            orderExternal.setTGL_SURVEY(ParserUtils.parseToCalendar(lpaUmum.getTanggalSurvey()));
            orderExternal.setDITINJAU_OLEH(lpaUmum.getDitinjau());
            orderExternal.setSTATUSJAMINAN(lpaUmum.getStatusJaminan()==null?"":lpaUmum.getStatusJaminan().toString());
            orderExternal.setTGL_PENILAIEKS(ParserUtils.parseToCalendar(lpaUmum.getTanggalTerimaDariEksternal()));
            vehicle_external.setOrder_Data_Detail(orderExternal);

            if (ListUtils.isNotEmpty(lpaKendaraan.getKendaraan())) {
                List_of_Data_Kendaraan dataKendaraan = new List_of_Data_Kendaraan();
                List<LpaItemKendaraan> itemList = lpaKendaraan.getKendaraan();
                Data_Kendaraan_item[] detailKen = new Data_Kendaraan_item[itemList.size()];
                for (int i = 0; i < itemList.size(); i++) {
                    LpaItemKendaraan item = itemList.get(i);
                    Data_Kendaraan_item target = new Data_Kendaraan_item();
                    target.setTAHUN_PEMBUATAN(item.getTahunPembuatan()==null?"":item.getTahunPembuatan().toString());
                    target.setJENIS_KENDARAAN(item.getJenisKendaraan());
                    target.setMERK(item.getMerkKendaraan());
                    detailKen[i] = target;

                }
                dataKendaraan.setData_Kendaraan_List(detailKen);
                vehicle_external.setList_of_Data_Kendaraan(dataKendaraan);
            }


            Valuation_Vehicle_External valEks = new Valuation_Vehicle_External();
            valEks.setCURRENCY(lpaUmum.getMataUang());
            valEks.setNILAI_DOK_PASAR(lpaKendaraan.getTotalDokumenEks());
            valEks.setNILAI_FISIK_PASAR(lpaKendaraan.getTotalFisikEks());
            valEks.setNILAI_FISIK_LIKUIDASI(lpaKendaraan.getLikuidasiFisikEks());
            valEks.setDIBERI_NILAI_DOK(lpaKendaraan.getBeriNilaiDokumenEks()==null?"":lpaKendaraan.getBeriNilaiDokumenEks().toString());
            valEks.setDIBERI_NILAI_FISIK(lpaKendaraan.getBeriNilaiFisikEks()==null?"":lpaKendaraan.getBeriNilaiFisikEks().toString());
            vehicle_external.setValuation(valEks);

            Catatan_yang_Perlu_Diperhatikan_Vehicle_External catatanEks = new Catatan_yang_Perlu_Diperhatikan_Vehicle_External();

            catatanEks.setCATATAN_DIPERHATIKAN(lpaUmum.getCatatanPoint());
            if (ListUtils.isNotEmpty(lpaUmum.getListNegative())) {
                catatanEks.setNEGATIF_POINT(lpaUmum.getListNegative().get(0).getValue());
            }
            if (ListUtils.isNotEmpty(lpaUmum.getListPertimbangan())) {
                catatanEks.setPOSITIF_POINT(lpaUmum.getListPertimbangan().get(0).getValue());
            }
            vehicle_external.setCatatan_yang_Perlu_Diperhatikan(catatanEks);

            Data_Pembanding_Vehicle_External pembandingEks = new Data_Pembanding_Vehicle_External();
            pembandingEks.setDATA_PEMBANDING1(lpaUmum.getPembanding1());
            pembandingEks.setDATA_PEMBANDING2(lpaUmum.getPembanding2());
            pembandingEks.setDATA_PEMBANDING3(lpaUmum.getPembanding3());

            vehicle_external.setData_Pembanding(pembandingEks);

            Approval_Vehicle_External approveEks = new Approval_Vehicle_External();
            approveEks.setVALUER(e.getValueBy());
            approveEks.setVALUER_DATE(ParserUtils.parseToCalendar(e.getValueDate()));
            approveEks.setAPPROVAL(e.getClosedBy());
            approveEks.setAPPROVAL_DATE(ParserUtils.parseToCalendar(e.getClosedDate()));
            vehicle_external.setApproval(approveEks);

            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("kendaraan_Eksternal_Carm");
            log.setTanggal(new Date());
            log.setJenis("Kirim");
            log = sendToSourceLogDao.save(log);


            Response responseExternal = CarmClientUtils.getInstance(systemParamFacade.getValue("WS_CARM_IP"),systemParamFacade.getValue("WS_CARM_USER"),systemParamFacade.getValue("WS_CARM_PASSWORD")).getService().receiveAppraisal_Vehicle_External(vehicle_external);

            log.setStatus(responseExternal.getError_Code().equals(AppConstans.SourceStatusConstans.SUCCESS_SEND_ORDER_CARM)?1:2);
            log.setResponse(responseExternal.getError_Code());
            log.setMessage(responseExternal.getStatus());

            log = sendToSourceLogDao.save(log);
            Boolean nextToAttach = log.getStatus()==1;
            if (nextToAttach){
//                sendAttachCarmKendaraan(e,log);
                return sendAttachment(e,log, systemParamFacade.getValue("WS_IP_CARM_ATT"), systemParamFacade.getValue("WS_CARM_USER_ATT"), systemParamFacade.getValue("WS_CARM_PASSWORD_ATT"), checkListKendaraan(lpaKendaraan.getId()), false);
            } else {
                return false;
            }

//        } catch (javax.xml.rpc.ServiceException ex) {
//
//            SendToSourceLog log = new SendToSourceLog();
//            log.setIdCommon(e.getId());
//            log.setStatus(SendToSourceLog.ERROR);
//            log.setSource(AppConstans.Source.CARM);
//            log.setAlamat("kendaraan_Eksternal_Carm");
//            log.setTanggal(new Date());
//            log.setMessage("Error Catch javax.xml.rpc.ServiceException : "+ex.getMessage());
//            log.setResponse("ERROR");
//            log = sendToSourceLogDao.save(log);
//            ex.printStackTrace();
        } catch (Exception ex) {
            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("kendaraan_Eksternal_Carm");
            log.setTanggal(new Date());
            log.setMessage("Exception : "+ex.getMessage());
            log.setResponse("ERROR");
            log = sendToSourceLogDao.save(log);
            ex.printStackTrace();
            return false;
        }

    }

    private Boolean machineExternalCarm(DataCommon e) {
        try {
//            ReceiveAppraisalServiceLocator locator = new ReceiveAppraisalServiceLocator();
//            ReceiveAppraisalServiceSoap_PortType service = locator.getReceiveAppraisalServiceSoap(new URL(systemParamFacade.getValue("WS_CARM_IP")));
//            ((ReceiveAppraisalServiceSoap_BindingStub) service).setUsername(systemParamFacade.getValue("WS_CARM_USER"));
//            ((ReceiveAppraisalServiceSoap_BindingStub) service).setPassword(systemParamFacade.getValue("WS_CARM_PASSWORD"));
//            ((ReceiveAppraisalServiceSoap_BindingStub) service).setTimeout(timeout);
            DataDetail detail = e.getDataDetail();
            LpaUmum lpaUmum = e.getDataDetail().getLpaUmum().iterator().next();
            LpaMesin lpaMesin = lpaUmum.getMesin().iterator().next();

            Machine_External machineExternal = new Machine_External();
            machineExternal.setACL_ID(e.getSourceId());

            Order_Data_Detail_Machine_External detailExt = new Order_Data_Detail_Machine_External();
            detailExt.setORDER_ID(Long.parseLong(e.getOrderNumber()));
            detailExt.setNO_LAPORAN(lpaUmum.getLpaNumber());
            detailExt.setCABANGBISNIS(lpaUmum.getBranchId()==null?"":lpaUmum.getBranchId().toString());
            detailExt.setNAMADEBITUR(e.getName());
            detailExt.setALAMAT(detail.getAlamat());
            detailExt.setPROPINSI(detail.getProvince());
            detailExt.setKABUPATEN(lpaUmum.getKota());
            detailExt.setKECAMATAN(lpaUmum.getKecamatan());
            detailExt.setKELURAHAN(lpaUmum.getKelurahan());
            detailExt.setTGL_SURVEY(ParserUtils.parseToCalendar(lpaUmum.getTanggalSurvey()));
            detailExt.setDITINJAU_OLEH(lpaUmum.getDitinjau());
            detailExt.setSTATUSJAMINAN(lpaUmum.getStatusJaminan()==null?"":lpaUmum.getStatusJaminan().toString());
            detailExt.setTGL_PENILAIEKS(ParserUtils.parseToCalendar(lpaUmum.getTanggalTerimaDariEksternal()));
            machineExternal.setOrder_Data_Detail(detailExt);


            if (ListUtils.isNotEmpty(lpaMesin.getLampiran())) {
                List<LpaMesinLampiran> mesinLamp = lpaMesin.getLampiran();
                List_of_Data_Mesin dataM = new List_of_Data_Mesin();
                Data_Mesin_item[] itemMesin = new Data_Mesin_item[mesinLamp.size()];
                for (int i = 0; i < mesinLamp.size(); i++) {
                    LpaMesinLampiran data = mesinLamp.get(i);
                    Data_Mesin_item targetEks = new Data_Mesin_item();
                    targetEks.setJENIS_MESIN(data.getJenis());
                    targetEks.setMERK(data.getMerk());
                    targetEks.setTYPE(data.getTypeModel());
                    targetEks.setNO_SERI(data.getNoSeri());
                    targetEks.setASAL_PEMBUATAN(data.getAsal());
                    targetEks.setTAHUN_PEMBUATAN(data.getTahun());
                    targetEks.setKEGUNAAN(data.getKegunaan());
                    targetEks.setKONDISIMESIN(data.getKondisi());
                    targetEks.setJUMLAH_UNIT(mesinLamp.size());
                    targetEks.setRANGKAIANMESIN(data.getRangkaian());
                    targetEks.setNO_FAKTUR(data.getNoInvoice());
                    targetEks.setTGL_FAKTUR(ParserUtils.parseToCalendar(data.getTanggal()));
                    itemMesin[i] = targetEks;

                }
                dataM.setData_Mesin_List(itemMesin);
                machineExternal.setList_of_Data_Mesin(dataM);
            }


            Valuation valExt = new Valuation();
            valExt.setCURRENCY(lpaUmum.getMataUang());
            valExt.setNILAI_DOK_PASAR(lpaMesin.getNilaiDocPasarEks());
            valExt.setNILAI_DOK_LIKUIDASI(lpaMesin.getNilaiDocLikuidasiEks());
            valExt.setNILAI_FISIK_PASAR(lpaMesin.getNilaiFisikPasarEks());
            valExt.setNILAI_FISIK_LIKUIDASI(lpaMesin.getNilaiFisikLikuidasiEks());
            valExt.setDIBERI_NILAI_DOK(lpaMesin.getBeriNilaiDocEks()==null?"":lpaMesin.getBeriNilaiDocEks().toString());
            valExt.setDIBERI_NILAI_FISIK(lpaMesin.getBeriNilaiFisikEks()==null?"":lpaMesin.getBeriNilaiFisikEks().toString());
            machineExternal.setValuation(valExt);


            Catatan_yang_Perlu_Diperhatikan_Machine_External catatanEks = new Catatan_yang_Perlu_Diperhatikan_Machine_External();
            catatanEks.setCATATAN_DIPERHATIKAN(lpaUmum.getCatatanPoint());
            if (ListUtils.isNotEmpty(lpaUmum.getListNegative())) {
                catatanEks.setNEGATIF_POINT(lpaUmum.getListNegative().get(0).getValue());
            }
            if (ListUtils.isNotEmpty(lpaUmum.getListPertimbangan())) {
                catatanEks.setPOSITIF_POINT(lpaUmum.getListPertimbangan().get(0).getValue());
            }
            machineExternal.setCatatan_yang_Perlu_Diperhatikan(catatanEks);

            Data_Pembanding_Machine_External pembEks = new Data_Pembanding_Machine_External();
            pembEks.setDATA_PEMBANDING1(lpaUmum.getPembanding1());
            pembEks.setDATA_PEMBANDING2(lpaUmum.getPembanding2());
            pembEks.setDATA_PEMBANDING3(lpaUmum.getPembanding3());
            machineExternal.setData_Pembanding(pembEks);


            Approval_Machine_External approveEks = new Approval_Machine_External();
            approveEks.setVALUER(e.getValueBy());
            approveEks.setAPPROVAL(e.getClosedBy());
            approveEks.setVALUER_DATE(ParserUtils.parseToCalendar(e.getValueDate()));
            approveEks.setAPPROVAL_DATE(ParserUtils.parseToCalendar(e.getClosedDate()));
            machineExternal.setApproval(approveEks);


            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("Machine_Eksternal_Carm");
            log.setTanggal(new Date());
            log.setJenis("Kirim");
            log = sendToSourceLogDao.save(log);

            Response responseExternal = CarmClientUtils.getInstance(systemParamFacade.getValue("WS_CARM_IP"),systemParamFacade.getValue("WS_CARM_USER"),systemParamFacade.getValue("WS_CARM_PASSWORD")).getService().receiveAppraisal_Machine_External(machineExternal);


            log.setStatus(responseExternal.getError_Code().equals(AppConstans.SourceStatusConstans.SUCCESS_SEND_ORDER_CARM)?1:2);
            log.setResponse(responseExternal.getError_Code());
            log.setMessage(responseExternal.getStatus());

            log = sendToSourceLogDao.save(log);
            Boolean nextToAttach = log.getStatus()==1;

            if (nextToAttach){
//                sendAttachCarmMachine(e,log);
                return sendAttachment(e,log, systemParamFacade.getValue("WS_IP_CARM_ATT"), systemParamFacade.getValue("WS_CARM_USER_ATT"), systemParamFacade.getValue("WS_CARM_PASSWORD_ATT"), checkLampMesin(lpaMesin.getId()), false);
            } else {
                return false;
            }


//        } catch (ServiceException e1) {
//            SendToSourceLog log = new SendToSourceLog();
//            log.setIdCommon(e.getId());
//            log.setStatus(SendToSourceLog.ERROR);
//            log.setSource(AppConstans.Source.CARM);
//            log.setAlamat("Machine_Eksternal_Carm");
//            log.setTanggal(new Date());
//            log.setMessage("Error Catch javax.xml.rpc.ServiceException : "+e1.getMessage());
//            log = sendToSourceLogDao.save(log);
//            e1.printStackTrace();
        } catch (MalformedURLException e1) {
            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("Machine_Eksternal_Carm");
            log.setTanggal(new Date());
            log.setMessage("Error Catch MalformedURLException : "+StringUtils.substring(e1.getMessage(), 0, 200));
            log = sendToSourceLogDao.save(log);
            e1.printStackTrace();
            return false;
        } catch (RemoteException e1) {
            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("Machine_Eksternal_Carm");
            log.setTanggal(new Date());
            log.setMessage("Error Catch RemoteException : "+StringUtils.substring(e1.getMessage(), 0, 200));
            log = sendToSourceLogDao.save(log);
            e1.printStackTrace();
            return false;
        } catch (ServiceException e1) {
            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("Machine_Eksternal_Carm");
            log.setTanggal(new Date());
            log.setMessage("Error Catch ServiceException : "+StringUtils.substring(e1.getMessage(), 0, 200));
            log = sendToSourceLogDao.save(log);
            e1.printStackTrace();
            return false;
        }
    }


    private Boolean stockInternalCarm(DataCommon e) {

        try {
//            ReceiveAppraisalServiceLocator locator = new ReceiveAppraisalServiceLocator();
//            ReceiveAppraisalServiceSoap_PortType service = locator.getReceiveAppraisalServiceSoap(new URL(systemParamFacade.getValue("WS_CARM_IP")));
//            ((ReceiveAppraisalServiceSoap_BindingStub) service).setUsername(systemParamFacade.getValue("WS_CARM_USER"));
//            ((ReceiveAppraisalServiceSoap_BindingStub) service).setPassword(systemParamFacade.getValue("WS_CARM_PASSWORD"));

            DataDetail detail = e.getDataDetail();
            LpaUmum lpaUmum = e.getDataDetail().getLpaUmum().iterator().next();
            LpaStock lpaStock = lpaUmum.getStock().iterator().next();

            Stock_Internal stckInternal = new Stock_Internal();
            stckInternal.setACL_ID(e.getSourceId());

            Order_Data_Detail_Stock_Internal detailInternal = new Order_Data_Detail_Stock_Internal();
            detailInternal.setORDER_ID(Long.parseLong(e.getOrderNumber()));
            detailInternal.setNO_LAPORAN(lpaUmum.getLpaNumber());
            detailInternal.setDEBITUR(e.getName());
            detailInternal.setTGL_ORDER(ParserUtils.parseToCalendar(lpaUmum.getTanggalOrder()));
            detailInternal.setTGL_SURVEY(ParserUtils.parseToCalendar(lpaUmum.getTanggalSurvey()));
            detailInternal.setDITUJUKAN_KE(lpaUmum.getDitujuke());
            detailInternal.setALAMAT(detail.getAlamat());
            detailInternal.setPROPINSI(detail.getProvince());
            detailInternal.setKABUPATEN(lpaUmum.getKota());
            detailInternal.setKECAMATAN(lpaUmum.getKecamatan());
            detailInternal.setKELURAHAN(lpaUmum.getKelurahan());
            detailInternal.setKODEPOS(lpaUmum.getKodePos());
            detailInternal.setDITINJAU_OLEH(lpaUmum.getDitinjau());
            detailInternal.setDITEMUI_OLEH(lpaUmum.getDitemui());
            detailInternal.setSTATUSJAMINAN(lpaUmum.getStatusJaminan()==null?"":lpaUmum.getStatusJaminan().toString());
            stckInternal.setOrder_Data_Detail(detailInternal);

            Document_Detail_Stock_External docDetail = new Document_Detail_Stock_External(ParserUtils.parseToCalendar(lpaStock.getTanggalDokumen()));
            stckInternal.setDocument_Detail(docDetail);


            Valuation_Stock_Internal valInt = new Valuation_Stock_Internal();
            valInt.setCURRENCY(lpaUmum.getMataUang());
            valInt.setNILAI_DOK_PASAR(lpaStock.getNilaiDokumen());
            valInt.setNILAI_DOK_LIKUIDASI(lpaStock.getLikuidasiDokumen());
            valInt.setNILAI_FISIK_PASAR(lpaStock.getNilaiFisik());
            valInt.setNILAI_FISIK_LIKUIDASI(lpaStock.getLikuidasiFisik());
            valInt.setDIBERI_NILAI_DOK(lpaStock.getBerinilaiDokumen()==null?"":lpaStock.getBerinilaiDokumen().toString());
            valInt.setDIBERI_NILAI_FISIK(lpaStock.getBerinilaiFisik()==null?"":lpaStock.getBerinilaiFisik().toString());

            stckInternal.setValuation(valInt);

            Catatan_yang_Perlu_Diperhatikan_Stock_Internal catatanInt = new Catatan_yang_Perlu_Diperhatikan_Stock_Internal();

            catatanInt.setCATATAN_DIPERHATIKAN(lpaUmum.getCatatanPoint());
            if (ListUtils.isNotEmpty(lpaUmum.getListNegative())) {
                catatanInt.setNEGATIF_POINT(lpaUmum.getListNegative().get(0).getValue());
            }
            if (ListUtils.isNotEmpty(lpaUmum.getListPertimbangan())) {
                catatanInt.setPOSITIF_POINT(lpaUmum.getListPertimbangan().get(0).getValue());
            }

            stckInternal.setCatatan_yang_Perlu_Diperhatikan(catatanInt);

            Approval_Stock_Internal approveInternal = new Approval_Stock_Internal();
            approveInternal.setVALUERNAME(e.getValueBy());
            approveInternal.setAPPROVALNAME(e.getClosedBy());
            approveInternal.setVALUER_DATE(ParserUtils.parseToCalendar(e.getValueDate()));
            approveInternal.setAPPROVAL_DATE(ParserUtils.parseToCalendar(e.getClosedDate()));
            stckInternal.setApproval(approveInternal);

            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("Stock_Internal_carm");
            log.setTanggal(new Date());
            log.setJenis("Kirim");
            log = sendToSourceLogDao.save(log);


            Response responseInternalStock = CarmClientUtils.getInstance(systemParamFacade.getValue("WS_CARM_IP"),systemParamFacade.getValue("WS_CARM_USER"),systemParamFacade.getValue("WS_CARM_PASSWORD")).getService().receiveAppraisal_Stock_Internal(stckInternal);

            log.setStatus(responseInternalStock.getError_Code().equals(AppConstans.SourceStatusConstans.SUCCESS_SEND_ORDER_CARM)?1:2);
            log.setResponse(responseInternalStock.getError_Code());
            log.setMessage(responseInternalStock.getStatus());

            log = sendToSourceLogDao.save(log);
            Boolean nextToAttach = log.getStatus()==1;

            if (nextToAttach){
                return sendAttachment(e,log, systemParamFacade.getValue("WS_IP_CARM_ATT"), systemParamFacade.getValue("WS_CARM_USER_ATT"), systemParamFacade.getValue("WS_CARM_PASSWORD_ATT"), false, false);
            } else {
                return false;
            }
        } catch (MalformedURLException e1) {
            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("Stock_Internal_carm");
            log.setTanggal(new Date());
            log.setMessage("Error Catch MailFormedURKEXCEption : "+StringUtils.substring(e1.getMessage(), 0, 200));
            log = sendToSourceLogDao.save(log);
            e1.printStackTrace();
            return false;
        } catch (RemoteException e1) {
            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("Stock_Internal_carm");
            log.setTanggal(new Date());
            log.setMessage("Error Catch RemoteException : "+StringUtils.substring(e1.getMessage(), 0, 200));
            log = sendToSourceLogDao.save(log);
            e1.printStackTrace();
            return false;
        } catch (ServiceException e1) {
            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("Machine_Eksternal_Carm");
            log.setTanggal(new Date());
            log.setMessage("Error Catch ServiceException : "+StringUtils.substring(e1.getMessage(), 0, 200));
            log = sendToSourceLogDao.save(log);
            e1.printStackTrace();
            return false;
        }

    }


    private Boolean stockEksternalCarm(DataCommon e) {
        try {
            DataDetail detail = e.getDataDetail();
            LpaUmum lpaUmum = e.getDataDetail().getLpaUmum().iterator().next();
            LpaStock lpaStock = lpaUmum.getStock().iterator().next();

            Stock_External stockExternal = new Stock_External();
            stockExternal.setACL_ID(e.getSourceId());


            Order_Data_Detail_Stock_External detailExt = new Order_Data_Detail_Stock_External();
            detailExt.setORDER_ID(Long.parseLong(e.getOrderNumber()));
            detailExt.setNO_LAPORAN(lpaUmum.getLpaNumber());
            detailExt.setCABANGBISNIS(lpaUmum.getBranchId()==null?"":lpaUmum.getBranchId().toString());
            detailExt.setNAMADEBITUR(e.getName());
            detailExt.setALAMAT(detail.getAlamat());
            detailExt.setPROPINSI(detail.getProvince());
            detailExt.setKABUPATEN(lpaUmum.getKota());
            detailExt.setKECAMATAN(lpaUmum.getKecamatan());
            detailExt.setKELURAHAN(lpaUmum.getKelurahan());
            detailExt.setTGL_SURVEY(ParserUtils.parseToCalendar(lpaUmum.getTanggalSurvey()));
            detailExt.setDITINJAU_OLEH(lpaUmum.getDitinjau());
            detailExt.setSTATUSJAMINAN(lpaUmum.getStatusJaminan()==null?"":lpaUmum.getStatusJaminan().toString());
            detailExt.setTGL_PENILAIEKS(ParserUtils.parseToCalendar(lpaUmum.getTanggalTerimaDariEksternal()));
            stockExternal.setOrder_Data_Detail(detailExt);

            Document_Detail docDetail = new Document_Detail(ParserUtils.parseToCalendar(lpaStock.getTanggalDokumen()));
            stockExternal.setDocument_Detail(docDetail);


            Valuation_Stock_External valEks = new Valuation_Stock_External();
            valEks.setCURRENCY(lpaUmum.getMataUang());
            valEks.setNILAI_DOK_PASAR(lpaStock.getNilaiDokumenEks());
            valEks.setNILAI_DOK_LIKUIDASI(lpaStock.getLikuidasiDokumenEks());
            valEks.setNILAI_FISIK_PASAR(lpaStock.getNilaiFisikEks());
            valEks.setNILAI_FISIK_LIKUIDASI(lpaStock.getLikuidasiFisikEks());
            valEks.setDIBERI_NILAI_DOK(lpaStock.getBerinilaiDokumenEks()== null?"":lpaStock.getBerinilaiDokumenEks().toString());
            valEks.setDIBERI_NILAI_FISIK(lpaStock.getBerinilaiFisikEks()== null?"":lpaStock.getBerinilaiFisikEks().toString());
            stockExternal.setValuation(valEks);


            Catatan_yang_Perlu_Diperhatikan_Stock_External catatanEks = new Catatan_yang_Perlu_Diperhatikan_Stock_External();
            catatanEks.setCATATAN_DIPERHATIKAN(lpaUmum.getCatatanPoint());
            if (ListUtils.isNotEmpty(lpaUmum.getListNegative())) {
                catatanEks.setNEGATIF_POINT(lpaUmum.getListNegative().get(0).getValue());
            }
            if (ListUtils.isNotEmpty(lpaUmum.getListPertimbangan())) {
                catatanEks.setPOSITIF_POINT(lpaUmum.getListPertimbangan().get(0).getValue());
            }
            stockExternal.setCatatan_yang_Perlu_Diperhatikan(catatanEks);


            Approval_Stock_External approveEkStock = new Approval_Stock_External();
            approveEkStock.setVALUER(e.getValueBy());
            approveEkStock.setAPPROVAL(e.getClosedBy());
            approveEkStock.setVALUER_DATE(ParserUtils.parseToCalendar(e.getValueDate()));
            approveEkStock.setAPPROVAL_DATE(ParserUtils.parseToCalendar(e.getClosedDate()));
            stockExternal.setApproval(approveEkStock);

            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("Stock_Eksternal_carm");
            log.setTanggal(new Date());
            log.setJenis("Kirim");
            log = sendToSourceLogDao.save(log);

            Response responseExternal = CarmClientUtils.getInstance(systemParamFacade.getValue("WS_CARM_IP"),systemParamFacade.getValue("WS_CARM_USER"),systemParamFacade.getValue("WS_CARM_PASSWORD")).getService().receiveAppraisal_Stock_External(stockExternal);

            log.setStatus(responseExternal.getError_Code().equals(AppConstans.SourceStatusConstans.SUCCESS_SEND_ORDER_CARM)?1:2);
            log.setResponse(responseExternal.getError_Code());
            log.setMessage(responseExternal.getStatus());

            log = sendToSourceLogDao.save(log);
            Boolean nextToAttach = log.getStatus()==1;

            if (nextToAttach){
                return sendAttachment(e,log, systemParamFacade.getValue("WS_IP_CARM_ATT"), systemParamFacade.getValue("WS_CARM_USER_ATT"), systemParamFacade.getValue("WS_CARM_PASSWORD_ATT"), false, false);
            } else {
                return false;
            }

        } catch (MalformedURLException e1) {
            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("Stock_Eksternal_carm");
            log.setTanggal(new Date());
            log.setMessage("Error MalformedURLException : "+StringUtils.substring(e1.getMessage(), 0, 200));
            log = sendToSourceLogDao.save(log);
            e1.printStackTrace();
            return false;
        } catch (RemoteException e1) {
            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("Stock_Eksternal_carm");
            log.setTanggal(new Date());
            log.setMessage("Error RemoteException : "+StringUtils.substring(e1.getMessage(), 0, 200));
            log = sendToSourceLogDao.save(log);
            e1.printStackTrace();
            return false;
        } catch (ServiceException e1) {
            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("Machine_Eksternal_Carm");
            log.setTanggal(new Date());
            log.setMessage("Error Catch ServiceException : "+StringUtils.substring(e1.getMessage(), 0, 200));
            log = sendToSourceLogDao.save(log);
            e1.printStackTrace();
            return false;
        }
    }

    private Boolean machineInternalCarm(DataCommon e) {

        try {
//            ReceiveAppraisalServiceLocator locator = new ReceiveAppraisalServiceLocator();
//            ReceiveAppraisalServiceSoap_PortType service = locator.getReceiveAppraisalServiceSoap(new URL(systemParamFacade.getValue("WS_CARM_IP")));
//            ((ReceiveAppraisalServiceSoap_BindingStub) service).setUsername(systemParamFacade.getValue("WS_CARM_USER"));
//            ((ReceiveAppraisalServiceSoap_BindingStub) service).setPassword(systemParamFacade.getValue("WS_CARM_PASSWORD"));
//            ((ReceiveAppraisalServiceSoap_BindingStub) service).setTimeout(timeout);
            DataDetail detail = e.getDataDetail();
            LpaUmum lpaUmum = e.getDataDetail().getLpaUmum().iterator().next();
            LpaMesin lpaMesin = lpaUmum.getMesin().iterator().next();


            Machine_Internal machineInternal = new Machine_Internal();
            machineInternal.setACL_ID(e.getSourceId());


            Order_Data_Detail_Machine_Internal detailMachineInternal = new Order_Data_Detail_Machine_Internal();
            detailMachineInternal.setORDER_ID(Long.parseLong(e.getOrderNumber()));
            detailMachineInternal.setNO_LAPORAN(lpaUmum.getLpaNumber());
            detailMachineInternal.setDEBITUR(e.getName());
            detailMachineInternal.setTGL_ORDER(ParserUtils.parseToCalendar(lpaUmum.getTanggalOrder()));
            detailMachineInternal.setTGL_SURVEY(ParserUtils.parseToCalendar(lpaUmum.getTanggalSurvey()));
            detailMachineInternal.setDITUJUKAN_KE(lpaUmum.getDitujuke());
            detailMachineInternal.setALAMAT(detail.getAlamat());
            detailMachineInternal.setPROPINSI(detail.getProvince());
            detailMachineInternal.setKABUPATEN(lpaUmum.getKota());
            detailMachineInternal.setKECAMATAN(lpaUmum.getKecamatan());
            detailMachineInternal.setKELURAHAN(lpaUmum.getKelurahan());
            detailMachineInternal.setKODEPOS(lpaUmum.getKodePos());
            detailMachineInternal.setDITINJAU_OLEH(lpaUmum.getDitinjau());
            detailMachineInternal.setDITEMUI_OLEH(lpaUmum.getDitemui());
            detailMachineInternal.setSTATUSJAMINAN(lpaUmum.getStatusJaminan()==null?"":lpaUmum.getStatusJaminan().toString());
            machineInternal.setOrder_Data_Detail(detailMachineInternal);

            if (ListUtils.isNotEmpty(lpaMesin.getLampiran())) {
                List<LpaMesinLampiran> mesinLamp = lpaMesin.getLampiran();
                List_of_Data_Mesin_Machine_Internal listMesin = new List_of_Data_Mesin_Machine_Internal();
                Data_Mesin_item_Machine_Internal[] lstMsn = new Data_Mesin_item_Machine_Internal[mesinLamp.size()];
                for (int i = 0; i < mesinLamp.size(); i++) {
                    LpaMesinLampiran data = mesinLamp.get(i);
                    Data_Mesin_item_Machine_Internal target = new Data_Mesin_item_Machine_Internal();
                    target.setJENIS_MESIN(data.getJenis());
                    target.setMERK(data.getMerk());
                    target.setTYPE(data.getTypeModel());
                    target.setNO_SERI(data.getNoSeri());
                    target.setASAL_PEMBUATAN(data.getAsal());
                    target.setTAHUN_PEMBUATAN(data.getTahun());
                    target.setKEGUNAAN(data.getKegunaan());
                    target.setKONDISIMESIN(data.getKondisi());
                    target.setJUMLAH_UNIT(mesinLamp.size());
                    target.setRANGKAIANMESIN(data.getRangkaian());
                    target.setNO_FAKTUR(data.getNoInvoice());
                    target.setTGL_FAKTUR(ParserUtils.parseToCalendar(data.getTanggal()));
                    lstMsn[i] = target;
                }

                listMesin.setData_Mesin_List(lstMsn);
                machineInternal.setList_of_Data_Mesin(listMesin);
            }


            Valuation_Machine_Internal valInternal = new Valuation_Machine_Internal();
            valInternal.setCURRENCY(lpaUmum.getMataUang());
            valInternal.setNILAI_DOK_PASAR(lpaMesin.getNilaiDocPasar());
            valInternal.setNILAI_DOK_LIKUIDASI(lpaMesin.getNilaiDocLikuidasi());
            valInternal.setNILAI_FISIK_PASAR(lpaMesin.getNilaiFisikPasar());
            valInternal.setNILAI_FISIK_LIKUIDASI(lpaMesin.getNilaiFisikLikuidasi());
            valInternal.setDIBERI_NILAI_DOK(lpaMesin.getBeriNilaiDoc()==null?"":lpaMesin.getBeriNilaiDoc().toString());
            valInternal.setDIBERI_NILAI_FISIK(lpaMesin.getBeriNilaiFisik()==null?"":lpaMesin.getBeriNilaiFisik().toString());
            machineInternal.setValuation(valInternal);

            Catatan_yang_Perlu_Diperhatikan_Machine_Internal catatanInt = new Catatan_yang_Perlu_Diperhatikan_Machine_Internal();
            catatanInt.setCATATAN_DIPERHATIKAN(lpaUmum.getCatatanPoint());
            if (ListUtils.isNotEmpty(lpaUmum.getListNegative())) {
                catatanInt.setNEGATIF_POINT(lpaUmum.getListNegative().get(0).getValue());
            }
            if (ListUtils.isNotEmpty(lpaUmum.getListPertimbangan())) {
                catatanInt.setPOSITIF_POINT(lpaUmum.getListPertimbangan().get(0).getValue());
            }
            machineInternal.setCatatan_yang_Perlu_Diperhatikan(catatanInt);

            Data_Pembanding_Machine_Internal pembInt = new Data_Pembanding_Machine_Internal();
            pembInt.setDATA_PEMBANDING1(lpaUmum.getPembanding1());
            pembInt.setDATA_PEMBANDING2(lpaUmum.getPembanding2());
            pembInt.setDATA_PEMBANDING3(lpaUmum.getPembanding3());
            machineInternal.setData_Pembanding(pembInt);


            Approval_Machine_Internal approveInt = new Approval_Machine_Internal();
            approveInt.setVALUERNAME(e.getValueBy());
            approveInt.setAPPROVALNAME(e.getClosedBy());
            approveInt.setVALUER_DATE(ParserUtils.parseToCalendar(e.getValueDate()));
            approveInt.setAPPROVAL_DATE(ParserUtils.parseToCalendar(e.getClosedDate()));
            machineInternal.setApproval(approveInt);


            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("Machine_internal_carm");
            log.setTanggal(new Date());
            log.setJenis("Kirim");
            log = sendToSourceLogDao.save(log);

            Response responseMachineInternal = CarmClientUtils.getInstance(systemParamFacade.getValue("WS_CARM_IP"),systemParamFacade.getValue("WS_CARM_USER"),systemParamFacade.getValue("WS_CARM_PASSWORD")).getService().receiveAppraisal_Machine_Internal(machineInternal);

            log.setStatus(responseMachineInternal.getError_Code().equals(AppConstans.SourceStatusConstans.SUCCESS_SEND_ORDER_CARM)?1:2);
            log.setResponse(responseMachineInternal.getError_Code());
            log.setMessage(responseMachineInternal.getStatus());

            log = sendToSourceLogDao.save(log);
            Boolean nextToAttach = log.getStatus()==1;

            if (nextToAttach){
//                sendAttachCarmMachine(e,log);
                return sendAttachment(e,log, systemParamFacade.getValue("WS_IP_CARM_ATT"), systemParamFacade.getValue("WS_CARM_USER_ATT"), systemParamFacade.getValue("WS_CARM_PASSWORD_ATT"), checkLampMesin(lpaMesin.getId()), false);
            } else {
                return false;
            }

//        } catch (ServiceException e1) {
//            SendToSourceLog log = new SendToSourceLog();
//            log.setIdCommon(e.getId());
//            log.setStatus(SendToSourceLog.ERROR);
//            log.setSource(AppConstans.Source.CARM);
//            log.setAlamat("Machine_internal_carm");
//            log.setTanggal(new Date());
//            log.setMessage("Error Catch javax.xml.rpc.ServiceException : "+e1.getMessage());
//            log = sendToSourceLogDao.save(log);
//            e1.printStackTrace();
        } catch (MalformedURLException e1) {
            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("Machine_internal_carm");
            log.setTanggal(new Date());
            log.setMessage("Error Catch MalformedURLException : "+StringUtils.substring(e1.getMessage(), 0, 200));
            log = sendToSourceLogDao.save(log);
            e1.printStackTrace();
            return false;
        } catch (RemoteException e1) {
            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("Machine_internal_carm");
            log.setTanggal(new Date());
            log.setMessage("Error Catch RemoteException : "+StringUtils.substring(e1.getMessage(), 0, 200));
            log = sendToSourceLogDao.save(log);
            e1.printStackTrace();
            return false;
        } catch (ServiceException e1) {
            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("Machine_Eksternal_Carm");
            log.setTanggal(new Date());
            log.setMessage("Error Catch ServiceException : "+StringUtils.substring(e1.getMessage(), 0, 200));
            log = sendToSourceLogDao.save(log);
            e1.printStackTrace();
            return false;
        }

    }

    private Boolean checkLampMesin(Long idLpaMesin) {
        return lampiranDao.getCountMesin(idLpaMesin) > 1;
    }


    private Boolean findCheckMachine(Long idLpaMesin) {
//        List<LpaMesinLampiranDto> result = new ArrayList<LpaMesinLampiranDto>();
        List<LpaMesinLampiran> dataList = lampiranDao.findByLpaMesin(idLpaMesin);

        return dataList.size() > 1;
    }

    private Boolean listItemKendaraanCheck(Long idLpa) {

            LpaItemKendaraan filter = new LpaItemKendaraan(new LpaKendaraan(idLpa));
            List<LpaItemKendaraan> dataList = itemDao.find(filter);
            return dataList.size() > 1;
        }

    private Boolean checkListKendaraan(Long idLpaKendaraan) {
        LpaItemKendaraan filter = new LpaItemKendaraan(new LpaKendaraan(idLpaKendaraan));
        Integer resultCount = itemDao.getCountKendaraan(filter);
        return resultCount>1;
    }

    private Boolean checkLampTanah(Long lpaTanahBangunanId) {
        LpaTanah filter = new LpaTanah();
        filter.setCommon(new LpaTanahBangunan(lpaTanahBangunanId));
        return lpaTanahDao.getCountTanah(filter) > 1;
    }
    private Boolean checkLampBangunan(Long lpaTanahBangunanId) {
        LpaBangunan filter = new LpaBangunan();
        filter.setCommon(new LpaTanahBangunan(lpaTanahBangunanId));
        return lpaBangunanDao.getCountBangunan(filter) > 1;
    }

    private Boolean listDokumenBangunanCheck(Long commonId) {
        LpaBangunan filter = new LpaBangunan();
        filter.setCommon(new LpaTanahBangunan(commonId));
        List<LpaBangunan> listDocBangunan = lpaBangunanDao.find(filter);
        return listDocBangunan.size() > 1;
    }

    private Boolean listDokumenTanahCheck(Long commonId) {

        LpaTanah filter = new LpaTanah();
        filter.setCommon(new LpaTanahBangunan(commonId));
        List<LpaTanah> listDocTanah = lpaTanahDao.find(filter);

        return listDocTanah.size() > 1;
    }

    private Boolean vesselInternalCarm(DataCommon e) {
        try {
            DataDetail detail = e.getDataDetail();
            LpaUmum lpaUmum = detail.getLpaUmum().iterator().next();
            LpaKapal lpaKapal = lpaUmum.getKapal().iterator().next();
            Vessel vessel = new Vessel();
            vessel.setACL_ID(e.getSourceId());
            Order_Data_Detail_Vessel detailVessel = new Order_Data_Detail_Vessel();
            detailVessel.setKODECABANG(e.getBranch()==null?"":e.getBranch().getId().toString());
            detailVessel.setORDER_ID(Long.parseLong(e.getOrderNumber()));
            detailVessel.setNO_LAPORAN(lpaUmum.getLpaNumber());
            detailVessel.setJENIS_LAPORAN(e.getTypeReport());
            detailVessel.setNAMA(e.getName());
            detailVessel.setJENIS_OBJECT(lpaUmum.getJenisObjectPenilain());
            detailVessel.setNAMAKOMPLEK(lpaUmum.getNamaPerumahan());
            detailVessel.setCLUSTER(lpaUmum.getNamaCluster());
            detailVessel.setLOKASI(lpaUmum.getLokasiFisik());
            detailVessel.setPROPINSI(lpaUmum.getPropinsi());
            detailVessel.setKABUPATEN(lpaUmum.getKota());
            detailVessel.setKECAMATAN(lpaUmum.getKecamatan());
            detailVessel.setKELURAHAN(lpaUmum.getKelurahan());
            detailVessel.setKODEPOS(lpaUmum.getKodePos());
            detailVessel.setLOKASIAGUNAN(lpaUmum.getPosisiLokasiAgunan());
            detailVessel.setTGL_ORDER(ParserUtils.parseToCalendar(lpaUmum.getTanggalOrder()));
            detailVessel.setTGL_SURVEY(ParserUtils.parseToCalendar(lpaUmum.getTanggalSurvey()));
            detailVessel.setDITUJUKAN_KE(lpaUmum.getDitujuke());
            detailVessel.setDITINJAU_OLEH(lpaUmum.getDitinjau());
            detailVessel.setDITEMUI_OLEH(lpaUmum.getDitemui());
            detailVessel.setSTATUSJAMINAN(lpaUmum.getStatusJaminan()==null?"":lpaUmum.getStatusJaminan().toString());
            detailVessel.setSLAAGUNAN(lpaUmum.getSla());
            Integer lamaPenyelesaian = dao.getJumlahHariPenyelesaian(e.getSubmitDate()==null?e.getAdminDate():e.getSubmitDate(), e.getClosedDate());
            detailVessel.setLAMA_PENYELESAIAN(lamaPenyelesaian);
            detailVessel.setKATEGORI_PENYELESAIAN(lpaUmum.getSla() < lamaPenyelesaian ? "Melebihi" : "-");

            vessel.setOrder_Data_Detail(detailVessel);

            Data_Kapal data_kapal = new Data_Kapal();
            data_kapal.setNAMA_KAPAL(lpaKapal.getNama());
            data_kapal.setKEBANGSAAN(lpaKapal.getKebangsaan());
            data_kapal.setASAL_PEMBUATAN(lpaKapal.getManufaktur());
            data_kapal.setTAHUN(lpaKapal.getTahun());
            data_kapal.setISI_KOTOR(lpaKapal.getIsiKotor()==null?BigDecimal.ZERO:new BigDecimal(lpaKapal.getIsiKotor()));

            vessel.setData_Kapal(data_kapal);

            Data_Legalitas_Kapal data_legalitas_kapal = new Data_Legalitas_Kapal();
            data_legalitas_kapal.setJENIS_KAPAL(lpaKapal.getJenis());
            data_legalitas_kapal.setNO_GROSS_AKTA(lpaKapal.getNoGrossAkta());
            data_legalitas_kapal.setTANGGAL_GROSS_AKTA(ParserUtils.parseToCalendar(lpaKapal.getTanggalGrossAkta()));
            data_legalitas_kapal.setNO_SURAT_UKUR(lpaKapal.getNoSuratUkur());
            data_legalitas_kapal.setTANGGAL_SURAT_UKUR(ParserUtils.parseToCalendar(lpaKapal.getTanggalSuratUkur()));
            vessel.setData_Legalitas_Kapal(data_legalitas_kapal);


            Informasi_Nilai_Vessel informasi_nilai_vessel = new Informasi_Nilai_Vessel();
            informasi_nilai_vessel.setCURRENCY(lpaUmum.getMataUang());
            informasi_nilai_vessel.setNILAI_KURS(lpaUmum.getNilaiRate());
            informasi_nilai_vessel.setTANGGAL_KURS(ParserUtils.parseToCalendar(lpaUmum.getTanggalRate()));
            informasi_nilai_vessel.setNILAI_DOK(lpaKapal.getNilaiDocPasar());
            informasi_nilai_vessel.setDIBERI_NILAI_DOK(lpaKapal.getBeriNilaiDoc()==null?"":lpaKapal.getBeriNilaiDoc().toString());
            informasi_nilai_vessel.setNILAI_FISIK(lpaKapal.getNilaiFisikPasar()==null?"":lpaKapal.getNilaiFisikPasar().toString());
            informasi_nilai_vessel.setDIBERI_NILAI_FISIK(lpaKapal.getBeriNilaiFisik().toString());
            informasi_nilai_vessel.setPERSEN_LIKUIDASI(lpaKapal.getLikuidasi()==null?BigDecimal.ZERO:new BigDecimal(lpaKapal.getLikuidasi()));
            informasi_nilai_vessel.setNILAI_DOK_LIKUIDASI(lpaKapal.getNilaiDocLikuidasi());
            informasi_nilai_vessel.setNILAI_FISIK_LIKUIDASI(lpaKapal.getNilaiFisikLikuidasi());

            vessel.setInformasi_Nilai(informasi_nilai_vessel);

            Catatan_yang_Perlu_Diperhatikan_Vessel catatanVessel = new Catatan_yang_Perlu_Diperhatikan_Vessel();
            catatanVessel.setCATATAN_DIPERHATIKAN(lpaUmum.getCatatanPoint());
            if (ListUtils.isNotEmpty(lpaUmum.getListNegative())) {
                catatanVessel.setNEGATIF(lpaUmum.getListNegative().get(0).getValue());
            }
            if (ListUtils.isNotEmpty(lpaUmum.getListPertimbangan())) {
                catatanVessel.setPOSITIF(lpaUmum.getListPertimbangan().get(0).getValue());
            }

            catatanVessel.setDATA_PEMBANDING1(lpaUmum.getPembanding1());
            catatanVessel.setDATA_PEMBANDING2(lpaUmum.getPembanding2());
            catatanVessel.setDATA_PEMBANDING3(lpaUmum.getPembanding3());
            vessel.setCatatan_yang_Perlu_Diperhatikan(catatanVessel);


            Approval_Vessel approval_vessel = new Approval_Vessel();

            approval_vessel.setVALUER(e.getValueBy());
            approval_vessel.setAPPROVAL(e.getClosedBy());
            approval_vessel.setVALUE_DATE(ParserUtils.parseToCalendar(e.getValueDate()));
            approval_vessel.setAPPROVE_DATE(ParserUtils.parseToCalendar(e.getClosedDate()));
            vessel.setApproval(approval_vessel);

            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("Vessel_internal_carm");
            log.setTanggal(new Date());
            log.setJenis("Kirim");
            log = sendToSourceLogDao.save(log);

            Response responseInternalVessel = CarmClientUtils.getInstance(systemParamFacade.getValue("WS_CARM_IP"),systemParamFacade.getValue("WS_CARM_USER"),systemParamFacade.getValue("WS_CARM_PASSWORD")).getService().receiveAppraisal_Vessel(vessel);

            log.setStatus(responseInternalVessel.getError_Code().equals(AppConstans.SourceStatusConstans.SUCCESS_SEND_ORDER_CARM)?1:2);
            log.setResponse(responseInternalVessel.getError_Code());
            log.setMessage(responseInternalVessel.getStatus());

            log = sendToSourceLogDao.save(log);
            Boolean nextToAttach = log.getStatus()==1;

            if (nextToAttach){
                return sendAttachment(e,log, systemParamFacade.getValue("WS_IP_CARM_ATT"), systemParamFacade.getValue("WS_CARM_USER_ATT"), systemParamFacade.getValue("WS_CARM_PASSWORD_ATT"), false, false);
            } else {
                return false;
            }

        } catch (MalformedURLException e1) {
            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("Vessel_internal_carm");
            log.setTanggal(new Date());
            log.setMessage("Error MalformedURLException: "+e1.getMessage());
            log = sendToSourceLogDao.save(log);
            e1.printStackTrace();
            return false;
        } catch (RemoteException e1) {
            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("Vessel_internal_carm");
            log.setTanggal(new Date());
            log.setMessage("Error RemoteException: "+e1.getMessage());
            log = sendToSourceLogDao.save(log);
            e1.printStackTrace();
            return false;
        } catch (Exception e1) {
            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("Vessel_internal_carm");
            log.setTanggal(new Date());
            log.setMessage("Error Catch javax.xml.rpc.ServiceException : "+e1.getMessage());
            log = sendToSourceLogDao.save(log);
            e1.printStackTrace();
            return false;
        }

    }

    private Boolean vesselEksternalCarm(DataCommon e) {
        try {
            DataDetail detail = e.getDataDetail();
            LpaUmum lpaUmum = detail.getLpaUmum().iterator().next();
            LpaKapal lpaKapal = lpaUmum.getKapal().iterator().next();
            Vessel vessel = new Vessel();
            vessel.setACL_ID(e.getSourceId());
            Order_Data_Detail_Vessel detailVessel = new Order_Data_Detail_Vessel();
            detailVessel.setKODECABANG(e.getBranch()==null?"":e.getBranch().getId().toString());
            detailVessel.setORDER_ID(Long.parseLong(e.getOrderNumber()));
            detailVessel.setNO_LAPORAN(lpaUmum.getLpaNumber());
            detailVessel.setJENIS_LAPORAN(e.getTypeReport());
            detailVessel.setNAMA(e.getName());
            detailVessel.setJENIS_OBJECT(lpaUmum.getJenisObjectPenilain());
            detailVessel.setNAMAKOMPLEK(lpaUmum.getNamaPerumahan());
            detailVessel.setCLUSTER(lpaUmum.getNamaCluster());
            detailVessel.setLOKASI(lpaUmum.getLokasiFisik());
            detailVessel.setPROPINSI(lpaUmum.getPropinsi());
            detailVessel.setKABUPATEN(lpaUmum.getKota());
            detailVessel.setKECAMATAN(lpaUmum.getKecamatan());
            detailVessel.setKELURAHAN(lpaUmum.getKelurahan());
            detailVessel.setKODEPOS(lpaUmum.getKodePos());
            detailVessel.setLOKASIAGUNAN(lpaUmum.getPosisiLokasiAgunan());
            detailVessel.setTGL_ORDER(ParserUtils.parseToCalendar(lpaUmum.getTanggalOrder()));
            detailVessel.setTGL_SURVEY(ParserUtils.parseToCalendar(lpaUmum.getTanggalSurvey()));
            detailVessel.setDITUJUKAN_KE(lpaUmum.getDitujuke());
            detailVessel.setDITINJAU_OLEH(lpaUmum.getDitinjau());
            detailVessel.setDITEMUI_OLEH(lpaUmum.getDitemui());
            detailVessel.setSTATUSJAMINAN(lpaUmum.getStatusJaminan()==null?"":lpaUmum.getStatusJaminan().toString());
            detailVessel.setSLAAGUNAN(lpaUmum.getSla());
            Integer lamaPenyelesaian = dao.getJumlahHariPenyelesaian(e.getSubmitDate()==null?e.getAdminDate():e.getSubmitDate(), e.getClosedDate()).intValue();
            detailVessel.setLAMA_PENYELESAIAN(lamaPenyelesaian);
            detailVessel.setKATEGORI_PENYELESAIAN(lpaUmum.getSla() < lamaPenyelesaian ? "Melebihi" : "-");

            vessel.setOrder_Data_Detail(detailVessel);

            Data_Kapal data_kapal = new Data_Kapal();
            data_kapal.setNAMA_KAPAL(lpaKapal.getNama());
            data_kapal.setKEBANGSAAN(lpaKapal.getKebangsaan());
            data_kapal.setASAL_PEMBUATAN(lpaKapal.getManufaktur());
            data_kapal.setTAHUN(lpaKapal.getTahun());
            data_kapal.setISI_KOTOR(new BigDecimal(lpaKapal.getIsiKotor()));

            vessel.setData_Kapal(data_kapal);

            Data_Legalitas_Kapal data_legalitas_kapal = new Data_Legalitas_Kapal();
            data_legalitas_kapal.setJENIS_KAPAL(lpaKapal.getJenis());
            data_legalitas_kapal.setNO_GROSS_AKTA(lpaKapal.getNoGrossAkta());
            data_legalitas_kapal.setTANGGAL_GROSS_AKTA(ParserUtils.parseToCalendar(lpaKapal.getTanggalGrossAkta()));
            data_legalitas_kapal.setNO_SURAT_UKUR(lpaKapal.getNoSuratUkur());
            data_legalitas_kapal.setTANGGAL_SURAT_UKUR(ParserUtils.parseToCalendar(lpaKapal.getTanggalSuratUkur()));
            vessel.setData_Legalitas_Kapal(data_legalitas_kapal);


            Informasi_Nilai_Vessel informasi_nilai_vessel = new Informasi_Nilai_Vessel();
            informasi_nilai_vessel.setCURRENCY(lpaUmum.getMataUang());
            informasi_nilai_vessel.setNILAI_KURS(lpaUmum.getNilaiRate());
            informasi_nilai_vessel.setTANGGAL_KURS(ParserUtils.parseToCalendar(lpaUmum.getTanggalRate()));
            informasi_nilai_vessel.setNILAI_DOK(lpaKapal.getNilaiDocPasarEks());
            informasi_nilai_vessel.setDIBERI_NILAI_DOK(lpaKapal.getBeriNilaiDocEks()==null?"":lpaKapal.getBeriNilaiDocEks().toString());
            informasi_nilai_vessel.setNILAI_FISIK(lpaKapal.getNilaiFisikPasarEks()==null?"":lpaKapal.getNilaiFisikPasarEks().toString());
            informasi_nilai_vessel.setDIBERI_NILAI_FISIK(lpaKapal.getBeriNilaiFisikEks()==null?"":lpaKapal.getBeriNilaiFisikEks().toString());
            informasi_nilai_vessel.setPERSEN_LIKUIDASI(lpaKapal.getLikuidasiEks()==null?BigDecimal.ZERO:new BigDecimal(lpaKapal.getLikuidasiEks()));
            informasi_nilai_vessel.setNILAI_DOK_LIKUIDASI(lpaKapal.getNilaiDocLikuidasiEks());
            informasi_nilai_vessel.setNILAI_FISIK_LIKUIDASI(lpaKapal.getNilaiFisikLikuidasiEks());

            vessel.setInformasi_Nilai(informasi_nilai_vessel);

            Catatan_yang_Perlu_Diperhatikan_Vessel catatanVessel = new Catatan_yang_Perlu_Diperhatikan_Vessel();
            catatanVessel.setCATATAN_DIPERHATIKAN(lpaUmum.getCatatanPoint());
            if (ListUtils.isNotEmpty(lpaUmum.getListNegative())) {
                catatanVessel.setNEGATIF(lpaUmum.getListNegative().get(0).getValue());
            }
            if (ListUtils.isNotEmpty(lpaUmum.getListPertimbangan())) {
                catatanVessel.setPOSITIF(lpaUmum.getListPertimbangan().get(0).getValue());
            }

            catatanVessel.setDATA_PEMBANDING1(lpaUmum.getPembanding1());
            catatanVessel.setDATA_PEMBANDING2(lpaUmum.getPembanding2());
            catatanVessel.setDATA_PEMBANDING3(lpaUmum.getPembanding3());
            vessel.setCatatan_yang_Perlu_Diperhatikan(catatanVessel);


            Approval_Vessel approval_vessel = new Approval_Vessel();

            approval_vessel.setVALUER(e.getValueBy());
            approval_vessel.setAPPROVAL(e.getClosedBy());
            approval_vessel.setVALUE_DATE(ParserUtils.parseToCalendar(e.getValueDate()));
            approval_vessel.setAPPROVE_DATE(ParserUtils.parseToCalendar(e.getClosedDate()));
            vessel.setApproval(approval_vessel);

            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("Vessel_Eksternal_carm");
            log.setTanggal(new Date());
            log.setJenis("Kirim");
            log = sendToSourceLogDao.save(log);


            Response responseExternal = CarmClientUtils.getInstance(systemParamFacade.getValue("WS_CARM_IP"),systemParamFacade.getValue("WS_CARM_USER"),systemParamFacade.getValue("WS_CARM_PASSWORD")).getService().receiveAppraisal_Vessel(vessel);

            log.setStatus(responseExternal.getError_Code().equals(AppConstans.SourceStatusConstans.SUCCESS_SEND_ORDER_CARM)?1:2);
            log.setResponse(responseExternal.getError_Code());
            log.setMessage(responseExternal.getStatus());

            log = sendToSourceLogDao.save(log);
            Boolean nextToAttach = log.getStatus()==1;

            if (nextToAttach){
//                sendAttachCarmVessel(e,log);
                return sendAttachment(e,log, systemParamFacade.getValue("WS_IP_CARM_ATT"), systemParamFacade.getValue("WS_CARM_USER_ATT"), systemParamFacade.getValue("WS_CARM_PASSWORD_ATT"), false, false);
            } else {
                return false;
            }

        } catch (MalformedURLException e1) {
            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("Vessel_Eksternal_carm");
            log.setTanggal(new Date());
            log.setMessage("Error Catch MalformedURLException : "+e1.getMessage());
            log = sendToSourceLogDao.save(log);
            e1.printStackTrace();
            return false;
        } catch (RemoteException e1) {
            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("Vessel_Eksternal_carm");
            log.setTanggal(new Date());
            log.setMessage("Error Catch RemoteException : "+e1.getMessage());
            log = sendToSourceLogDao.save(log);
            e1.printStackTrace();
            return false;
        } catch (Exception e1) {
            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("Vessel_Eksternal_carm");
            log.setTanggal(new Date());
            log.setMessage("Error Catch javax.xml.rpc.ServiceException : "+e1.getMessage());
            log = sendToSourceLogDao.save(log);
            e1.printStackTrace();
            return false;
        }

    }

    private Boolean lnbEksternalCarm(DataCommon e) {


//        ReceiveAppraisalServiceLocator locator = new ReceiveAppraisalServiceLocator();


        try {

            DataDetail detail = e.getDataDetail();
            LpaUmum lpaUmum = detail.getLpaUmum().iterator().next();
            LpaTanahBangunan lpaLnB = lpaUmum.getTanahBangunan().iterator().next();


//            ReceiveAppraisalServiceSoap_PortType service = locator.getReceiveAppraisalServiceSoap(new URL(systemParamFacade.getValue("WS_CARM_IP")));
//            ((ReceiveAppraisalServiceSoap_BindingStub) service).setUsername(systemParamFacade.getValue("WS_CARM_USER"));
//            ((ReceiveAppraisalServiceSoap_BindingStub) service).setPassword(systemParamFacade.getValue("WS_CARM_PASSWORD"));
//            ((ReceiveAppraisalServiceSoap_BindingStub) service).setTimeout(timeout);
            L_B_External lnbExternal = new L_B_External();
            Order_Data_Detail detailEx = new Order_Data_Detail();


            lnbExternal.setACL_ID(e.getSourceId());
            detailEx.setKODECABANG(e.getBranch()==null?"":e.getBranch().getName());
            detailEx.setORDER_ID(Long.parseLong(e.getOrderNumber()));
            detailEx.setNO_LAPORAN(lpaUmum.getLpaNumber());
            detailEx.setJENIS_LAPORAN(e.getTypeReport());

            detailEx.setTGL_ORDER1(ParserUtils.parseToCalendar(lpaUmum.getTanggalOrder()));
            detailEx.setTGL_ORDER2(ParserUtils.parseToCalendar(lpaUmum.getTanggalOrder()));
            detailEx.setTGL_SURVEY(ParserUtils.parseToCalendar(lpaUmum.getTanggalSurvey()));
            detailEx.setTGL_PENILAIEKS(ParserUtils.parseToCalendar(lpaUmum.getTanggalPenilaian()));
            detailEx.setTGL_ORDEREKS(ParserUtils.parseToCalendar(lpaUmum.getTanggalOrderKeEksternal()));
            detailEx.setTGL_TERIMAEKS(ParserUtils.parseToCalendar(lpaUmum.getTanggalTerimaDariEksternal()));
            detailEx.setNAMA(e.getName());
            detailEx.setJENIS_OBJECT(lpaUmum.getJenisObjectPenilain());
            detailEx.setOBJECTFISIK(lpaUmum.getJenisObjectPenilaiFisik());
            detailEx.setNAMAKOMPLEK(lpaUmum.getNamaPerumahan());
            detailEx.setCluster_Name_Tower_Name(lpaUmum.getNamaCluster());
            detailEx.setLOKASI(lpaUmum.getLokasiFisik());
            detailEx.setBlock_Alley_Floor(lpaUmum.getBlock());
            detailEx.setNumber(lpaUmum.getNomor());
            detailEx.setRT(lpaUmum.getRt());
            detailEx.setRW(lpaUmum.getRw());
            detailEx.setPROPINSI(lpaUmum.getPropinsi());
            detailEx.setKABUPATEN(lpaUmum.getKota());
            detailEx.setKECAMATAN(lpaUmum.getKecamatan());
            detailEx.setKELURAHAN(lpaUmum.getKelurahan());
            detailEx.setKODEPOS(lpaUmum.getKodePos());
            detailEx.setLOKASIAGUNAN(lpaUmum.getPosisiLokasiAgunan());
            detailEx.setDITUJUKAN_KE(lpaUmum.getDitujuke());
            detailEx.setDITINJAU_OLEH(lpaUmum.getDitinjau());
            detailEx.setDITEMUI_OLEH(lpaUmum.getDitemui());
            detailEx.setSTATUSJAMINAN(lpaUmum.getStatusJaminan()==null?"":lpaUmum.getStatusJaminan().toString());
            detailEx.setSLAAGUNAN(lpaUmum.getSla());

            Integer lamaPenyelesaian = dao.getJumlahHariPenyelesaian(e.getSubmitDate()==null?e.getAdminDate():e.getSubmitDate(), e.getClosedDate()).intValue();
            detailEx.setNumber_of_the_Day_Completion(lamaPenyelesaian);
            detailEx.setCategory_of_Completion(lpaUmum.getSla() < lamaPenyelesaian ? "Melebihi" : "-");


            Data_Tanah tanah = new Data_Tanah();
            if (ListUtils.isNotEmpty(lpaLnB.getDocumentTanah())) {
                Data_Tanah_item[] tanahItemList = new Data_Tanah_item[lpaLnB.getDocumentTanah().size()];
                for (int i = 0; i < lpaLnB.getDocumentTanah().size(); i++) {
                    LpaTanah t = lpaLnB.getDocumentTanah().get(i);
                    Data_Tanah_item item = new Data_Tanah_item();
                    item.setJENIS_DOKUMEN(t.getJenisDokumen()==null?"":t.getJenisDokumen().getName());
                    item.setNO_DOKUMEN(t.getNamaDokumen());
                    if (t.getTanggalBerakhirHak() != null) {
                        item.setTGL_BERAKHIRNYA_HAK(ParserUtils.parseToCalendar(t.getTanggalBerakhirHak()));
                    }
                    if (t.getTanggalSertipikat() != null) {
                        item.setTGL_DOKUMEN(ParserUtils.parseToCalendar(t.getTanggalSertipikat()));
                    }
//                    item.setTGL_DOKUMEN(ParserUtils.parseToCalendar(t.getTanggalSertipikat()));
//                    item.setTGL_BERAKHIRNYA_HAK(ParserUtils.parseToCalendar(t.getTanggalBerakhirHak()));
                    item.setNAMA_PEMEGANG_HAK(t.getNamaPemegangHak());
                    item.setLUASDOK_1(new BigDecimal(t.getLuasTanah() == null ? 0d : t.getLuasTanah()));
                    tanahItemList[i] = item;

                }
                tanah.setList_of_Data_Tanah(tanahItemList);
            }
            tanah.setTotal_luas_tanah(new BigDecimal(lpaLnB.getTotalLuasTanahDokumen() == null ? 0d : lpaLnB.getTotalLuasTanahDokumen()));

            Data_Bangunan bangunan = new Data_Bangunan();
            if (ListUtils.isNotEmpty(lpaLnB.getDocumentBangunan())) {
                List<LpaBangunan> listEntity = lpaLnB.getDocumentBangunan();
                Data_Bangunan_item[] itemBangunan = new Data_Bangunan_item[listEntity.size()];
                for (int i = 0; i < listEntity.size(); i++) {
                    LpaBangunan b = listEntity.get(i);
                    Data_Bangunan_item item = new Data_Bangunan_item();
                    item.setNO_IMB(b.getNomorImb());
                    item.setTGL_IMB(ParserUtils.parseToCalendar(b.getTanggalImb()));
                    item.setIJIN_SESUAI_IMB(b.getIjinSesuaiImb());
                    item.setLUAS_SESUAI_IMB(b.getLuasBangunan() == null ? "" : b.getLuasBangunan().toString());
                    itemBangunan[i] = item;

                }
                bangunan.setList_of_Data_Bangunan(itemBangunan);
                bangunan.setLANTAI_SESUAI_IMB(listEntity.get(0).getJumlahLantai().intValue());
            }

            bangunan.setLANTAI_SESUAI_FISIK(lpaLnB.getJumlahLantaiFisik());
            bangunan.setKELASBANGUNAN(lpaLnB.getKelasBangunan());
            bangunan.setDIPERGUNAKAN_SEBAGAI(lpaLnB.getDigunakanSebagai());
            bangunan.setDIHUNI_OLEH(lpaLnB.getDihuniOleh());
            bangunan.setSTATUSAGUNAN(lpaLnB.getStatusAgunanDisewakan());
            bangunan.setPROSESPEMBANGUNAN(lpaLnB.getProsesPembangunan());
            bangunan.setTotal_Physical_Building_Area(lpaLnB.getLuasBangunan()==null?BigDecimal.ZERO:BigDecimal.valueOf(lpaLnB.getLuasBangunan()));

            lnbExternal.setOrder_Data_Detail(detailEx);
            lnbExternal.setData_Tanah(tanah);
            lnbExternal.setData_Bangunan(bangunan);
            lnbExternal.setFaktor_Marketability(new Faktor_Marketability(lpaUmum.getFaktorMarketability()));

            if (ListUtils.isNotEmpty(lpaUmum.getListNegative())) {
                List<Parameter> negativList = lpaUmum.getListNegative();
                String[] dataNegatif = new String[negativList.size()];
                for (int i = 0; i < negativList.size(); i++) {
                    Parameter p = negativList.get(i);
                    dataNegatif[i] = p.getValue();
                }
                lnbExternal.setNegative_List_Appraisal(new Negative_List_Appraisal(dataNegatif));
            }

            if (ListUtils.isNotEmpty(lpaUmum.getListPertimbangan())) {
                List<Parameter> positifList = lpaUmum.getListPertimbangan();
                String[] dataPositif = new String[positifList.size()];
                for (int i = 0; i < positifList.size(); i++) {
                    Parameter p = positifList.get(i);
                    dataPositif[i] = p.getValue();
                }

                lnbExternal.setProperty_Dalam_Perhatian_Khusus(new Property_Dalam_Perhatian_Khusus(dataPositif));
            }


            Informasi_Nilai infoNil = new Informasi_Nilai();
            infoNil.setCURRENCY(lpaUmum.getMataUang());
            Nilai_Aktiva_Sesuai_Eksternal aktifaEks = new Nilai_Aktiva_Sesuai_Eksternal();
            aktifaEks.setNILAI_DOK_PASAR_TANAH(lpaLnB.getNilaiDokumen1Eks());
            aktifaEks.setNILAI_DOK_PASAR_BANGUNAN(lpaLnB.getNilaiDokumen2Eks());
            aktifaEks.setNILAI_DOK_LIKUIDASI_TANAH(lpaLnB.getLikuidasiDokumen1Eks());
            aktifaEks.setNILAI_DOK_LIKUIDASI_BANGUNAN(lpaLnB.getLikuidasiDokumen2Eks());
            aktifaEks.setNILAI_FISIK_PASAR_TANAH(lpaLnB.getNilaiFisik1Eks());
            aktifaEks.setNILAI_FISIK_PASAR_BANGUNAN(lpaLnB.getNilaiFisik2Eks());
            aktifaEks.setNILAI_FISIK_LIKUIDASI_TANAH(lpaLnB.getLikuidasiFisik1Eks());
            aktifaEks.setNILAI_FISIK_LIKUIDASI_BANGUNAN(lpaLnB.getLikuidasiFisik2Eks());

            infoNil.setNilai_Aktiva_Sesuai_Eksternal(aktifaEks);
            lnbExternal.setInformasi_Nilai(infoNil);

            Catatan_yang_Perlu_Diperhatikan catatanPerhatikan = new Catatan_yang_Perlu_Diperhatikan();
            catatanPerhatikan.setCATATAN_KEWAJARAN(lpaUmum.getCatatanPoint());

            if (ListUtils.isNotEmpty(lpaUmum.getListNegative())) {
                catatanPerhatikan.setNEGATIF(lpaUmum.getListNegative().get(0).getValue());
            }
            if (ListUtils.isNotEmpty(lpaUmum.getListPertimbangan())) {
                catatanPerhatikan.setPOSITIF(lpaUmum.getListPertimbangan().get(0).getValue());
            }

            lnbExternal.setCatatan_yang_Perlu_Diperhatikan(catatanPerhatikan);

            Data_Pembanding dataPem = new Data_Pembanding();
            dataPem.setDATA_PEMBANDING1(lpaUmum.getPembanding1());
            dataPem.setDATA_PEMBANDING2(lpaUmum.getPembanding2());
            dataPem.setDATA_PEMBANDING3(lpaUmum.getPembanding3());
            lnbExternal.setData_Pembanding(dataPem);

            Approval approv = new Approval();
            approv.setVALUER(e.getValueBy());
            approv.setVALUE_DATE(ParserUtils.parseToCalendar(e.getValueDate()));
            approv.setAPPROVAL(e.getClosedBy());
            approv.setAPPROVE_DATE(ParserUtils.parseToCalendar(e.getClosedDate()));
            lnbExternal.setApproval(approv);



            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("lnb_Eksternal_Carm");
            log.setTanggal(new Date());
            log.setJenis("Kirim");
            log = sendToSourceLogDao.save(log);


            Response responseExternal = CarmClientUtils.getInstance(systemParamFacade.getValue("WS_CARM_IP"),systemParamFacade.getValue("WS_CARM_USER"),systemParamFacade.getValue("WS_CARM_PASSWORD")).getService().receiveAppraisal_L_B_External(lnbExternal);

            log.setStatus(responseExternal.getError_Code().equals(AppConstans.SourceStatusConstans.SUCCESS_SEND_ORDER_CARM)?1:2);
            log.setResponse(responseExternal.getError_Code());
            log.setMessage(responseExternal.getStatus());

            log = sendToSourceLogDao.save(log);
            Boolean nextToAttach = log.getStatus()==1;

            if (nextToAttach){
//                sendAttachCarmLnb(e,log);
                return sendAttachment(e,log, systemParamFacade.getValue("WS_IP_CARM_ATT"), systemParamFacade.getValue("WS_CARM_USER_ATT"), systemParamFacade.getValue("WS_CARM_PASSWORD_ATT"), checkLampTanah(lpaLnB.getId()), checkLampBangunan(lpaLnB.getId()));
            } else {
                return false;
            }



        } catch (MalformedURLException e1) {
            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("lnb_Eksternal_Carm");
            log.setTanggal(new Date());
            log.setMessage("Error Catch MalformedURLException : "+e1.getMessage());
            log.setResponse("ERROR");
            log = sendToSourceLogDao.save(log);
            e1.printStackTrace();
            return false;
        } catch (RemoteException e1) {
            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("lnb_Eksternal_Carm");
            log.setTanggal(new Date());
            log.setMessage("Error Catch RemoteException : "+e1.getMessage());
            log.setResponse("ERROR");
            log = sendToSourceLogDao.save(log);
            e1.printStackTrace();
            return false;
        } catch (Exception e1) {
            SendToSourceLog log = new SendToSourceLog();
            log.setIdCommon(e.getId());
            log.setStatus(SendToSourceLog.ERROR);
            log.setSource(AppConstans.Source.CARM);
            log.setAlamat("lnb_Eksternal_Carm");
            log.setTanggal(new Date());
            log.setMessage("Error Catch ServiceException : "+e1.getMessage());
            log.setResponse("ERROR");
            log = sendToSourceLogDao.save(log);
            e1.printStackTrace();
            return false;
        }


    }


    private Boolean sendToCarm(DataCommon e) {
        Boolean status = false;
        if (e != null) {
            DataDetail d = e.getDataDetail();
            e.setSourceStatus(AppConstans.SourceStatusConstans.FAILED_SEND);
            dao.save(e);

            if (d.getAssetType().getCategoryType().getCode().equalsIgnoreCase(AppConstans.JenisKategoriCode.LNB)) {
                if (e.getAppraisalTypeCategory().equalsIgnoreCase(AppConstans.JenisAppraisalCategory.INTERNAL)) {
                    status = lnbInternalCarm(e);
                } else if (e.getAppraisalTypeCategory().equalsIgnoreCase(AppConstans.JenisAppraisalCategory.EKSTERNAL)) {
                    status = lnbEksternalCarm(e);
                }
            } else if (d.getAssetType().getCategoryType().getCode().equalsIgnoreCase(AppConstans.JenisKategoriCode.KENDARAAN)) {
                if (e.getAppraisalTypeCategory().equalsIgnoreCase(AppConstans.JenisAppraisalCategory.INTERNAL)) {
                    status = kendaraanInternalCarm(e);
                } else if (e.getAppraisalTypeCategory().equalsIgnoreCase(AppConstans.JenisAppraisalCategory.EKSTERNAL)) {
                    status = kendaraanEksternalCarm(e);
                }
            } else if (d.getAssetType().getCategoryType().getCode().equalsIgnoreCase(AppConstans.JenisKategoriCode.MESIN)) {
                if (e.getAppraisalTypeCategory().equalsIgnoreCase(AppConstans.JenisAppraisalCategory.INTERNAL)) {
                    status = machineInternalCarm(e);
                } else if (e.getAppraisalTypeCategory().equalsIgnoreCase(AppConstans.JenisAppraisalCategory.EKSTERNAL)) {
                    status = machineExternalCarm(e);
                }
            } else if (d.getAssetType().getCategoryType().getCode().equalsIgnoreCase(AppConstans.JenisKategoriCode.STOCK)) {
                if (e.getAppraisalTypeCategory().equalsIgnoreCase(AppConstans.JenisAppraisalCategory.INTERNAL)) {
                    status = stockInternalCarm(e);

                } else if (e.getAppraisalTypeCategory().equalsIgnoreCase(AppConstans.JenisAppraisalCategory.EKSTERNAL)) {
                    status = stockEksternalCarm(e);
                }
            } else if (d.getAssetType().getCategoryType().getCode().equalsIgnoreCase(AppConstans.JenisKategoriCode.VESEL)) {
                if (e.getAppraisalTypeCategory().equalsIgnoreCase(AppConstans.JenisAppraisalCategory.INTERNAL)) {
                    status = vesselInternalCarm(e);
                } else if (e.getAppraisalTypeCategory().equalsIgnoreCase(AppConstans.JenisAppraisalCategory.EKSTERNAL)) {
                    status = vesselEksternalCarm(e);
                }
            }

        }
        return status;
    }

    @Override
    @Transactional
    public void sendToMobile(Long commonId, String surveyorId, String valuerId) {

        DataCommon dataUmum = dao.get(commonId);
        DataDetail dataDetail = dataUmum.getDataDetail();
        Person survey = personDao.get(surveyorId);
        Person valuer = personDao.get(valuerId);

        MobileSendSurvey e = new MobileSendSurvey();
        e.setBlockLantai(dataDetail.getBlokFloor());
        e.setClusterTower(dataDetail.getClusterTower());
        e.setCollateralId(dataUmum.getCollateralId());
        e.setContactName(dataUmum.getContactName());
        e.setContactPhone(dataUmum.getContactTelp());
        e.setDebiturName(dataUmum.getName());
        e.setJenisAppraisal(dataUmum.getAppraisalType().getName());
        e.setNomor(dataDetail.getNumber());
        e.setNotes(dataUmum.getCatatan());
        e.setObjectType(dataDetail.getAssetType().getName());
        e.setOrderNo(dataUmum.getOrderNumber());
        e.setPerumahanApartemen(dataDetail.getHomeApartment());
        e.setRt(dataDetail.getRt());
        e.setRw(dataDetail.getRw());
        e.setSurveyAddress(dataDetail.getAlamat());
        e.setSurveyCode(survey.getUserId());
        e.setStatus(MobileSendSurvey.NEW);
        e.setCreatedBy(valuer.getUserId());
        e.setCreatedDate(new Date());

        mobileSendSurveyDao.save(e);
    }

    @Override
    public MobileSendSurveyDto getSendToMoble(String orderNo) {
        MobileSendSurvey e = mobileSendSurveyDao.get(orderNo);
        if (e != null) {
            MobileSendSurveyDto w = new MobileSendSurveyDto();
            BeanUtils.copyProperties(e, w);
            return w;
        }
        return null;
    }


    @Override
    @Transactional
    public Long copyDebiturFromProcedure(Long id, String createdBy, String colateralId) {
        return dao.copyDebiturFromProcedure(id, createdBy, colateralId);

    }


    @Override
    @Transactional
    public Integer getJumlahHari(Date start, Date end) {
        return dao.getJumlahHariPenyelesaian(start, end);

    }

    @Override
    public Boolean checkUserTasklist(String userId, Long lpaId, Long surveyId) {
        DataCommon dataCommon;
        if (lpaId != null) {
            LpaUmum lpaUmum = lpaUmumDao.get(lpaId);
            dataCommon = dao.get(lpaUmum.getCommonId());
        } else {
            SurveyCommon surveyCommon = surveyCommonDao.get(surveyId);
            dataCommon = surveyCommon.getDetail().getDataCommon();
        }

        return StringUtils.equalsIgnoreCase(dataCommon.getTaskList(),userId);
    }

    public String getJasperNameAppraisalType(String appraisalCategoryType, String appraisalCategoryCode) {
        // get jaspername for appraisaltype "Appraisal only" (Internal/External)
        String jasperName = "";
        if (appraisalCategoryType.equalsIgnoreCase(AppConstans.JenisAppraisalCategory.EKSTERNAL)) {
            if (StringUtils.equalsIgnoreCase(appraisalCategoryCode, AppConstans.JenisKategoriCode.LNB)) {
                jasperName = "rptFormatLpaExt.jasper";
            } else if (StringUtils.equalsIgnoreCase(appraisalCategoryCode, AppConstans.JenisKategoriCode.KENDARAAN)) {
                jasperName = "rptLpaKendaraanExt.jasper";
            } else if (StringUtils.equalsIgnoreCase(appraisalCategoryCode, AppConstans.JenisKategoriCode.MESIN)) {
                jasperName = "rptLpaMesinExt.jasper";
            } else if (StringUtils.equalsIgnoreCase(appraisalCategoryCode, AppConstans.JenisKategoriCode.STOCK)) {
                jasperName = "rptLpaStockExt.jasper";
            } else if (StringUtils.equalsIgnoreCase(appraisalCategoryCode, AppConstans.JenisKategoriCode.VESEL)) {
                jasperName = "rptLpaKapalExt.jasper";
            }
        } else {
            if (StringUtils.equalsIgnoreCase(appraisalCategoryCode, AppConstans.JenisKategoriCode.LNB)) {
                jasperName = "rptFormatLpaInt.jasper";
            } else if (StringUtils.equalsIgnoreCase(appraisalCategoryCode, AppConstans.JenisKategoriCode.KENDARAAN)) {
                jasperName = "rptLpaKendaraan.jasper";
            } else if (StringUtils.equalsIgnoreCase(appraisalCategoryCode, AppConstans.JenisKategoriCode.MESIN)) {
                jasperName = "rptLpaMesin.jasper";
            } else if (StringUtils.equalsIgnoreCase(appraisalCategoryCode, AppConstans.JenisKategoriCode.STOCK)) {
                jasperName = "rptLpaStock.jasper";
            } else if (StringUtils.equalsIgnoreCase(appraisalCategoryCode, AppConstans.JenisKategoriCode.VESEL)) {
                jasperName = "rptLpaKapal.jasper";
            }
        }
        return jasperName;
    }

    // PLUS Attach Begin
    public Boolean sendAttachments(DataCommon dataCommon, SendToSourceLog logParent, String url, Boolean sendDokTanah, Boolean sendDokBangunan) {
        DataDetail detail = dataCommon.getDataDetail();
        Boolean status = false;
        // LPA Umum
        LpaUmum lpaUmum = dataCommon.getDataDetail().getLpaUmum().iterator().next();

        Integer fileNumber = 1;
        List<SurveyImage> docWs = surveyImageDao.findWorkshetByLpa(lpaUmum.getId());
        List<SurveyImage> docPelengkap = surveyImageDao.findDokPelengkapByLpa(lpaUmum.getId());
        Integer totalFile;// = 3 + (sendDokBangunan?1:0) + (sendDokTanah?1:0) +docWs.size()+docPelengkap.size();

//            Boolean lpaReportSucceed = sendLPAReport(e, fileNumber,totalFile,logParent,url,username,password);
        Integer coreReportStatus = 0;
        Integer lampiranDocTanahStatus = 0;
        Integer lampiranDocBangunanStatus = 0;
        Boolean worksheetStatus = true;
        Integer counter;
        if(dataCommon.getAppraisalType().getName().equalsIgnoreCase(AppConstans.JenisAppraisalName.BV)
                || dataCommon.getAppraisalType().getName().equalsIgnoreCase(AppConstans.JenisAppraisalName.RV)) {
            counter = 2;
            totalFile = 2;
        } else if(dataCommon.getAppraisalType().getName().equalsIgnoreCase(AppConstans.JenisAppraisalName.RAB)) {
            counter = 3;
            totalFile = 3;
        } else {
            counter = 3;
            totalFile = 3 + (sendDokBangunan?1:0) + (sendDokTanah?1:0) +docWs.size()+docPelengkap.size();
        }
        while(fileNumber<=counter) {
            coreReportStatus = sendLPAReport(dataCommon, fileNumber,totalFile,logParent,url,dataCommon.getDataDetail().getAssetType().getCategoryType().getCode());
            if(coreReportStatus==1&&totalFile==fileNumber) status = true;
            if(coreReportStatus!=0) break;
            if(fileNumber<counter) {fileNumber++;} else {break;}
        }
        // End Notes  : This all 3 core Report to Send (LPA, ImageSurvey, Peta/Denah)
        if (coreReportStatus == 0) {
            // Notes : only for Appraisal (Internal & External)
            Boolean loop = true; //dummyboolean
            while (loop) { // only for break out
                if (dataCommon.getAppraisalType().getName().equalsIgnoreCase(AppConstans.JenisAppraisalName.APPRAISAL)
                        || dataCommon.getAppraisalType().getName().equalsIgnoreCase(AppConstans.JenisAppraisalName.DESKTOP)) {
                    // Notes :  Pemisahan Jenis Aktiva
                    if (dataCommon.getDataDetail().getAssetType().getCategoryType().getCode().equals(AppConstans.JenisKategoriCode.LNB)) {
                        if (sendDokTanah) { // Tanah Bangunan
                            fileNumber++;
                            // // TODO: 11/8/2017 send Lampiran doc tanah here
                            lampiranDocTanahStatus = sendLPAReport(dataCommon, fileNumber,totalFile,logParent,url, "docTanah");
                        }
                        if(lampiranDocTanahStatus != 0) {
                            status = lampiranDocTanahStatus==1?true:false;
                            break;
                        }
                        if (sendDokBangunan ) {
                            fileNumber++;
                            // TODO: 11/8/2017 send Lampiran doc Bangunan here
                            lampiranDocBangunanStatus = sendLPAReport(dataCommon, fileNumber,totalFile,logParent,url, "docBangunan");
                        }
                        if(lampiranDocBangunanStatus != 0) {
                            status = lampiranDocBangunanStatus==1?true:false;
                            break;
                        }
                    } else if (dataCommon.getDataDetail().getAssetType().getCategoryType().getCode().equals(AppConstans.JenisKategoriCode.KENDARAAN)) {
                        // Kendaraan
                        if (sendDokTanah) { // Tanah Bangunan
                            fileNumber++;
                            lampiranDocTanahStatus = sendLPAReport(dataCommon, fileNumber,totalFile,logParent,url, "lampKendaraan");
                        }
                        if(lampiranDocTanahStatus != 0)  {
                            status = lampiranDocTanahStatus==1?true:false;
                            break;
                        }
                    } else if (dataCommon.getDataDetail().getAssetType().getCategoryType().getCode().equals(AppConstans.JenisKategoriCode.MESIN)) {
                        // Mesin
                        if (sendDokTanah) { // Tanah Bangunan
                            fileNumber++;
                            lampiranDocTanahStatus = sendLPAReport(dataCommon, fileNumber,totalFile,logParent,url, "lampMesin");
                        }
                        if(lampiranDocTanahStatus != 0) {
                            status = lampiranDocTanahStatus==1?true:false;
                            break;
                        }
//                        } else if (e.getDataDetail().getAssetType().getCategoryType().getCode().equals(AppConstans.JenisKategoriCode.STOCK)) {
//                            // Stok
//                        } else if (e.getDataDetail().getAssetType().getCategoryType().getCode().equals(AppConstans.JenisKategoriCode.VESEL)) {
//                            // Kapal
                    }
                    if(docWs.size()>0) {
                        // // TODO: 11/8/2017 Call method who gather WS dan Send it
                        fileNumber+=docWs.size();
                        worksheetStatus = sendWorksheet(dataCommon, docWs,fileNumber, totalFile, logParent, url);
                        docWs.clear();
                        docWs = null;
                    }
                    if(failedOrComplete(worksheetStatus, totalFile, fileNumber, dataCommon)) {
                        status = worksheetStatus?true:false;
                        break;
                    }
                }
                status = sendDokumenPelengkap(dataCommon, docPelengkap,fileNumber, totalFile, logParent, url);
                loop = false; //dummy loop to prevent from infinite loops
            }
        } else if (coreReportStatus != 1) {
            dataCommon.setSourceStatus(AppConstans.SourceStatusConstans.FAILED_SEND);
            dao.save(dataCommon);
            return status;
//        } else {
//            return true;
        }
        return status;
    }

    private Integer sendLPAReport(DataCommon dataCommon, Integer fileNumber, Integer totalFile, SendToSourceLog logParent, String url, String reportName) {
        // Notes : Return 0 success send, 1 success and complete, other failed/error
        String jasperName = "";
        String docCode = "";
        String fileName = "";
        Integer responseStatus = 99;
        byte[] reportByte = null;
//        List<AttachModel> listAttachment = null;
        AttachModel rptLaporan = null;
        StringHolder stringHolderAttact = null;
        IntegerWrapperHolder integerWrapperHolderAttact = null;
        SendToSourceAttachment sendToSourceAttachment = new SendToSourceAttachment();
        Map<String, Object> parameterMap = null;
        if(fileNumber==1) {  // LPA Report
            if(dataCommon.getAppraisalType().getName().equalsIgnoreCase(AppConstans.JenisAppraisalName.BV)) {
                jasperName = "rptLaporanBVBox.jasper";
                docCode = "Report LPA BV";
                fileName = String.format("report-lpa-BV-%s.pdf", dataCommon.getOrderNumber());
            } else if(dataCommon.getAppraisalType().getName().equalsIgnoreCase(AppConstans.JenisAppraisalName.RV)) {
                jasperName = "rptLaporanRVBox.jasper";
                docCode = "Report LPA RV";
                fileName = String.format("report-lpa-RV-%s.pdf", dataCommon.getOrderNumber());
            } else if(dataCommon.getAppraisalType().getName().equalsIgnoreCase(AppConstans.JenisAppraisalName.RAB)) {
                jasperName = "rptFormatLpaRab.jasper";
                docCode = "Report LPA RAB";
                fileName = String.format(String.format("report-RAB-%s.pdf", dataCommon.getOrderNumber()));
            } else if(dataCommon.getAppraisalType().getName().equalsIgnoreCase(AppConstans.JenisAppraisalName.DESKTOP)) {
                jasperName = "rptFormatLpaDesktop.jasper";
                docCode = "Report LPA Desktop";
                fileName = String.format(String.format("report-LPA-Desktop-%s.pdf", dataCommon.getOrderNumber()));
            } else if(dataCommon.getSource().equalsIgnoreCase(AppConstans.Source.PLUS) && dataCommon.getTypeReport().equalsIgnoreCase(AppConstans.JenisLaporan.PROGRESS)) {
                jasperName = "rptFormatLpaIntProgress.jasper";
                docCode = "Report LPA Progress";
                fileName = String.format(String.format("report-LPA-Progress-%s.pdf", dataCommon.getOrderNumber()));
            } else {
                jasperName = getJasperNameAppraisalType(dataCommon.getAppraisalTypeCategory(), reportName);
                fileName = dataCommon.getAppraisalTypeCategory().equalsIgnoreCase(AppConstans.JenisAppraisalCategory.EKSTERNAL)?"Report-LPA-External-%s.pdf":"Report-LPA-%s.pdf";
                docCode = "Report LPA";
            }
        } else if (fileNumber==2) { // Image Survey
            if(dataCommon.getAppraisalType().getName().equalsIgnoreCase(AppConstans.JenisAppraisalName.BV)
                    || dataCommon.getAppraisalType().getName().equalsIgnoreCase(AppConstans.JenisAppraisalName.RV)) {
                jasperName = "rptLampiranImageOther.jasper";
                fileName = "report-LPA-Image-other-%s.pdf";
                docCode = "Report LPA Image Survey";
            } else {
                jasperName = "rptLampiranImageSurvey.jasper";
                fileName = "Report-LPA-Image-survey-%s.pdf";
                docCode = "Report LPA Image Survey";
            }
        } else if (fileNumber==3) { //  Peta / Denah
            jasperName = "rptLampiranImagePeta.jasper";
            docCode = "Report LPA Image Peta";
            fileName = "Report-LPA-Image-Peta-%s.pdf";
        } else {
            if(StringUtils.equalsIgnoreCase(reportName, "docTanah")) {
                jasperName = "rptLampiranDocTanah.jasper";
                docCode = "Report Lampiran Tanah";
                fileName = "Lampiran-tanah-%s.pdf";
            } else if(StringUtils.equalsIgnoreCase(reportName, "docBangunan")) {
                jasperName = "rptLampiranDocBangunan.jasper";
                docCode = "Report Lampiran Bangunan";
                fileName = "Lampiran-bangunan-%s.pdf";
            } else if(StringUtils.equalsIgnoreCase(reportName, "lampKendaraan")) {
                jasperName = "rptLampiranKendaraan.jasper";
                docCode = "Report Lampiran Kendaraan";
                fileName = "Lampiran-Kendaraan-%s.pdf";
            } else if(StringUtils.equalsIgnoreCase(reportName, "lampMesin")) {
                jasperName = "rptLampiranMesin.jasper";
                docCode = "Report Lampiran Mesin";
                fileName = "Lampiran-Mesin-%s.pdf";
            }
        }
        sendToSourceAttachment.setSendToSourceLog(logParent);
        sendToSourceAttachment.setIdCommon(dataCommon.getId());
        sendToSourceAttachment.setFileName(String.format(fileName, dataCommon.getOrderNumber()));
        sendToSourceAttachment.setTanggal(new Date());
        try {
            stringHolderAttact = new StringHolder();
            integerWrapperHolderAttact = new IntegerWrapperHolder();
            parameterMap = new HashMap<String, Object>();
            stringHolderAttact = new StringHolder();
            integerWrapperHolderAttact = new IntegerWrapperHolder();
            String jasperFile = String.format("%s%s%s%s%s%s%s", context.getRealPath(""), File.separator, "WEB-INF",
                    File.separator, "report", File.separator, jasperName);
            String jasperDir = String.format("%s%s%s%s%s%s", context.getRealPath(""), File.separator, "WEB-INF",
                    File.separator, "report", File.separator);
            parameterMap.put("SUBREPORT_DIR", jasperDir);
            parameterMap.put("datasource", dataSource);
            parameterMap.put("PIdLpaUmum", dataCommon.getDataDetail().getLpaUmum().iterator().next().getId());
            reportByte = JasperUtils.generate(parameterMap, jasperFile, dataSource);
//            listAttachment = new ArrayList<AttachModel>();
            rptLaporan = new AttachModel();
            rptLaporan.setByteData(reportByte);
            rptLaporan.setContentType("application/pdf");
            rptLaporan.setDocCode(docCode);
            rptLaporan.setFileName(fileName);
//            listAttachment.add(rptLaporan);
            if(dataCommon.getAppraisalType().getName().equalsIgnoreCase(AppConstans.JenisAppraisalName.BV)
                    || dataCommon.getAppraisalType().getName().equalsIgnoreCase(AppConstans.JenisAppraisalName.RV)) {
                PlusClientUtils.getInstance(url).getiSyncReply().submitBvrvAttachment(new AttachModel[]{rptLaporan}, dataCommon.getOrderNumber(), stringHolderAttact, integerWrapperHolderAttact);
            } else {
                PlusClientUtils.getInstance(url).getiSyncReply().submitLPAResultAttachment(new AttachModel[]{rptLaporan}, dataCommon.getOrderNumber(), stringHolderAttact, integerWrapperHolderAttact);
            }
            sendToSourceAttachment.setMessage(stringHolderAttact.value);
            sendToSourceAttachment.setStatus(integerWrapperHolderAttact.value);
            sendToSourceAttachment.setResponse(String.valueOf(integerWrapperHolderAttact.value));
            responseStatus = integerWrapperHolderAttact.value;
            if(integerWrapperHolderAttact.value==1) {
                // cek udah semua apa belum karena notif dari PLUS sudah selesai. jika belum berarti ada yang salah
                // jika sudah terakhir update data umum menjadi send sukses
                if(fileNumber != totalFile){
                    sendToSourceAttachment.setMessage(sendToSourceAttachment.getMessage() + ", But Not All Attachment send");
                    dataCommon.setSourceStatus(AppConstans.SourceStatusConstans.FAILED_SEND);
                    dao.save(dataCommon);
                    responseStatus = 98;
                } else {
                    dataCommon.setSourceStatus(AppConstans.SourceStatusConstans.SUCCESS_SEND);
                    dao.save(dataCommon);
                }
            } else if(integerWrapperHolderAttact.value!=0) {
                dataCommon.setSourceStatus(AppConstans.SourceStatusConstans.FAILED_SEND);
                dao.save(dataCommon);
            }
        } catch (JRException e) {
            responseStatus = 99;
            sendToSourceAttachment.setMessage("Error Jasper Report Exception");
            sendToSourceAttachment.setResponse(StringUtils.substring(e.getCause().getMessage(),0,254));
            e.printStackTrace();
        } catch (SQLException e) {
            responseStatus = 99;
            sendToSourceAttachment.setMessage("Error SQL Exception");
            sendToSourceAttachment.setResponse(StringUtils.substring(e.getCause().getMessage(),0,254));
            e.printStackTrace();
        } catch (RemoteException e) {
            responseStatus = 99;
            if(StringUtils.containsIgnoreCase(e.getCause().getMessage(),"Bad envelope tag")) {
                sendToSourceAttachment.setMessage("Failed send : File too large....");
            } else {
                sendToSourceAttachment.setMessage("Error Remote Exception");
            }
            sendToSourceAttachment.setResponse(StringUtils.substring(e.getCause().getMessage(),0,254));
            e.printStackTrace();
        } catch (MalformedURLException e) {
            responseStatus = 99;
            sendToSourceAttachment.setMessage("Error Malformed URL Exception");
            sendToSourceAttachment.setResponse(StringUtils.substring(e.getCause().getMessage(),0,254));
            e.printStackTrace();
        } finally {
            sendToSourceLogAttachmenDao.save(sendToSourceAttachment);
            if(reportByte!=null)reportByte=null;
            if(rptLaporan!=null)rptLaporan=null;
            if(parameterMap!=null) parameterMap.clear();
            return  responseStatus;
        }
    }

    private Boolean sendWorksheet(DataCommon dataCommon, List<SurveyImage> docWs, Integer fileNumber, Integer totalFile, SendToSourceLog logParent, String url) {
        Boolean status = true;
        Boolean complete = false;
        AttachModel worksheet = null;
        StringHolder stringHolderAttact = null;
        IntegerWrapperHolder integerWrapperHolderAttact = null;
        for (SurveyImage image: docWs){
            stringHolderAttact = new StringHolder();
            integerWrapperHolderAttact = new IntegerWrapperHolder();
            fileNumber++;
            String namaFile = String.format("Worksheet-"+image.getId()+"-%s.xls", dataCommon.getOrderNumber());
            SendToSourceAttachment sendToSourceAttachment = new SendToSourceAttachment();
            sendToSourceAttachment.setSendToSourceLog(logParent);
            sendToSourceAttachment.setIdCommon(dataCommon.getId());
            sendToSourceAttachment.setTanggal(new Date());
            sendToSourceAttachment.setFileName(namaFile);
//            String nameFileFix ="";
            try {
                worksheet = new AttachModel();
                worksheet.setByteData(image.getImage());
                worksheet.setContentType(image.getContentType());
                worksheet.setDocCode("Attach LPA Worksheeet");
                worksheet.setFileName(namaFile);
//            listAttachment.add(rptLaporan);
                PlusClientUtils.getInstance(url).getiSyncReply().submitLPAResultAttachment(new AttachModel[]{worksheet}, dataCommon.getOrderNumber(), stringHolderAttact, integerWrapperHolderAttact);

                sendToSourceAttachment.setStatus(integerWrapperHolderAttact.value);
                sendToSourceAttachment.setMessage(stringHolderAttact.value);
                sendToSourceAttachment.setResponse(String.valueOf(integerWrapperHolderAttact.value));
                status = sendToSourceAttachment.getStatus()==0;
                complete = sendToSourceAttachment.getStatus()==1;
                if(complete) {
                    status = true;
                    break;
                } else if(!status) break;
            } catch (MalformedURLException e) {
                sendToSourceAttachment.setStatus(99);
                sendToSourceAttachment.setMessage("Error Malformed URL Exception");
                sendToSourceAttachment.setResponse(StringUtils.substring(e.getCause().getMessage(),0,254));
                e.printStackTrace();
                status=false;
                break;
            } catch (RemoteException e) {
                sendToSourceAttachment.setStatus(99);
                sendToSourceAttachment.setMessage("Error Remote Exception");
                sendToSourceAttachment.setResponse(StringUtils.substring(e.getCause().getMessage(),0,254));
                e.printStackTrace();
                status=false;
                break;
            } finally {
                sendToSourceLogAttachmenDao.save(sendToSourceAttachment);
                worksheet = null;
            }
        }
        if(docWs!=null)docWs.clear();
        return status;
    }

    private Boolean sendDokumenPelengkap (DataCommon dataCommon, List<SurveyImage> docPelengkap, Integer fileNumber, Integer totalFile, SendToSourceLog logParent, String url) {
//                List<SurveyImage> docWs = surveyImageDao.findBySurveyXLS(lpaUmum.getId());
        Boolean status = true;
        StringHolder stringHolderAttact = null;
        IntegerWrapperHolder integerWrapperHolderAttact = null;
        if (docPelengkap.size()>0){
            AttachModel otherAttach;
            for (SurveyImage imagePelengkap: docPelengkap){
                stringHolderAttact = new StringHolder();
                integerWrapperHolderAttact = new IntegerWrapperHolder();
                SendToSourceAttachment sendToSourceAttachment = new SendToSourceAttachment();
                sendToSourceAttachment.setSendToSourceLog(logParent);
                sendToSourceAttachment.setIdCommon(dataCommon.getId());
                sendToSourceAttachment.setTanggal(new Date());
                try {
                    fileNumber++;
                    DocumentLpaType typeDoc = imagePelengkap.getDocumentLpaType();
                    String namaFile = typeDoc.getName() + "-" + imagePelengkap.getId();
                    String nameFileFix ="";
                    otherAttach = new AttachModel();
                    otherAttach.setByteData(imagePelengkap.getImage());
                    otherAttach.setContentType(imagePelengkap.getContentType());
                    otherAttach.setDocCode("Attach LPA LNB Others");
                    if (imagePelengkap.getContentType()!=null && imagePelengkap.getContentType().length() > 0 ){
                        String type = imagePelengkap.getContentType();
                        if (type.equals(formatDocOthers[4])){
                            nameFileFix = ".pdf";
                        } else if (type.equals(formatDocOthers[0])) {
                            nameFileFix = ".jpeg";
                        } else if (type.equals(formatDocOthers[1])) {
                            nameFileFix = ".png";
                        }
                        otherAttach.setFileName(String.format(namaFile+"-%s-"+nameFileFix, dataCommon.getOrderNumber()));
                    }else{
                        nameFileFix = ".unknown";
                        otherAttach.setFileName(String.format(namaFile+"-%s-"+nameFileFix, dataCommon.getOrderNumber()));
                    }
                    sendToSourceAttachment.setFileName(String.format(namaFile+"-%s-"+nameFileFix, dataCommon.getOrderNumber()));
                    PlusClientUtils.getInstance(url).getiSyncReply().submitLPAResultAttachment(new AttachModel[]{otherAttach}, dataCommon.getOrderNumber(), stringHolderAttact, integerWrapperHolderAttact);
                    sendToSourceAttachment.setStatus(integerWrapperHolderAttact.value);
                    sendToSourceAttachment.setMessage(stringHolderAttact.value);
                    sendToSourceAttachment.setResponse(String.valueOf(integerWrapperHolderAttact.value));
                    status = sendToSourceAttachment.getStatus()==0;
                    Boolean complete = sendToSourceAttachment.getStatus()==1;
                    if(complete) {
                        sendToSourceAttachment.setStatus(1);
                        dataCommon.setSourceStatus(AppConstans.SourceStatusConstans.SUCCESS_SEND);
                        dao.save(dataCommon);
                        return true;
                    } else if(!status) {
                        dataCommon.setSourceStatus(AppConstans.SourceStatusConstans.FAILED_SEND);
                        dao.save(dataCommon);
                        return false;
                    }
                } catch (MalformedURLException e) {
                    sendToSourceAttachment.setStatus(99);
                    sendToSourceAttachment.setMessage("Error Malformed URL Exception");
                    sendToSourceAttachment.setResponse(StringUtils.substring(e.getCause().getMessage(),0,254));
                    e.printStackTrace();
                    return false;
                } catch (RemoteException e) {
                    sendToSourceAttachment.setStatus(99);
                    sendToSourceAttachment.setMessage("Error Remote Exception");
                    sendToSourceAttachment.setResponse(StringUtils.substring(e.getCause().getMessage(),0,254));
                    e.printStackTrace();
                    return false;
                } finally {
                    sendToSourceLogAttachmenDao.save(sendToSourceAttachment);
                    otherAttach = null;

                }
            }
        }
        if(docPelengkap!=null)docPelengkap.clear();
        return false;
    }

    private void saveCompletedAttachment(DataCommon dataCommon) {
        dataCommon.setSourceStatus(AppConstans.SourceStatusConstans.SUCCESS_SEND);
        dao.save(dataCommon);
    }

    private Boolean failedOrComplete(Boolean status, Integer totalFile, Integer fileNumber, DataCommon dataCommon) {
        if(!status) {
            dataCommon.setSourceStatus(AppConstans.SourceStatusConstans.FAILED_SEND);
            dao.save(dataCommon);
            return true;
        } else if (totalFile == fileNumber) {
            saveCompletedAttachment(dataCommon);
            return true;
        }
        return  false;
    }

    //PLUS ATTACH END

    // CARM ATTACH Begin
    public Boolean sendAttachment(DataCommon e,SendToSourceLog logParent, String url, String username, String password, Boolean sendDokTanah, Boolean sendDokBangunan) {
        Boolean status = false;
        LpaUmum lpaUmum = e.getDataDetail().getLpaUmum().iterator().next();
//            LpaTanahBangunan lpaTanahBangunan = lpaUmum.getTanahBangunan().iterator().next();
//            Boolean sendDokTanah = listDokumenTanahCheck(lpaTanahBangunan.getId());
//            Boolean sendDokBangunan = listDokumenBangunanCheck(lpaTanahBangunan.getId());
        Boolean isExternal = e.getAppraisalTypeCategory().equalsIgnoreCase(AppConstans.JenisAppraisalCategory.EKSTERNAL);

        Integer fileNumber = 1;
        List<SurveyImage> docWs = surveyImageDao.findWorkshetByLpa(lpaUmum.getId());
        List<SurveyImage> docPelengkap = surveyImageDao.findDokPelengkapByLpa(lpaUmum.getId());
        Integer totalFile = 3 + (sendDokBangunan?1:0) + (sendDokTanah?1:0) +docWs.size()+docPelengkap.size();

//            Boolean lpaReportSucceed = sendLPAReport(e, fileNumber,totalFile,logParent,url,username,password);
        Boolean coreReportStatus = false;
        Boolean lampiranDocTanahStatus = true;
        Boolean lampiranDocBangunanStatus = true;
        Boolean worksheetStatus = true;
//            Boolean next2 = true ; //lpaReportSucceed;
//            Boolean next3 = false;
//            Boolean next4 = false;
//            Boolean next5 = false;
//            Boolean next6 = false;
//            Boolean nextXLS = false;
//            Boolean allSucceed = true;
//            Boolean completed = false;
        // Notes  : This all 3 core Report to Send (LPA, ImageSurvey, Peta/Denah)
        while(fileNumber<=3) {
            coreReportStatus = sendLPAReport(e, fileNumber,totalFile,logParent,url,username,password, e.getDataDetail().getAssetType().getCategoryType().getCode());
            if(!coreReportStatus) break;
            if(fileNumber<3) {fileNumber++;} else {break;}
        }
        // End Notes  : This all 3 core Report to Send (LPA, ImageSurvey, Peta/Denah)
        if (coreReportStatus && totalFile == fileNumber) {
            e.setSourceStatus(AppConstans.SourceStatusConstans.SUCCESS_SEND);
            dao.save(e);
            status = true;
        } else if (coreReportStatus) {
            // Notes : only for Appraisal (Internal & External)
            Boolean loop = true; //dummyboolean
            while (loop) { // only for break out
                if (e.getAppraisalType().getName().equalsIgnoreCase(AppConstans.JenisAppraisalName.APPRAISAL)) {
                    // Notes :  Pemisahan Jenis Aktiva
                    if (e.getDataDetail().getAssetType().getCategoryType().getCode().equals(AppConstans.JenisKategoriCode.LNB)) {
                        if (sendDokTanah) { // Tanah Bangunan
                            fileNumber++;
                            // // TODO: 11/8/2017 send Lampiran doc tanah here
                            lampiranDocTanahStatus = sendLPAReport(e, fileNumber,totalFile,logParent,url,username,password, "docTanah");
                        }
                        if(failedOrComplete(lampiranDocTanahStatus, totalFile, fileNumber, e)) {
                            status=lampiranDocTanahStatus?true:false;
                            break;
                        }
                        if (sendDokBangunan ) {
                            fileNumber++;
                            // TODO: 11/8/2017 send Lampiran doc Bangunan here
                            lampiranDocBangunanStatus = sendLPAReport(e, fileNumber,totalFile,logParent,url,username,password, "docBangunan");
                        }
                        if(failedOrComplete(lampiranDocBangunanStatus, totalFile, fileNumber, e)) {
                            status=lampiranDocBangunanStatus?true:false;
                            break;
                        }
                    } else if (e.getDataDetail().getAssetType().getCategoryType().getCode().equals(AppConstans.JenisKategoriCode.KENDARAAN)) {
                        // Kendaraan
                        if (sendDokTanah) { // Tanah Bangunan
                            fileNumber++;
                            lampiranDocTanahStatus = sendLPAReport(e, fileNumber,totalFile,logParent,url,username,password, "lampKendaraan");
                        }
                        if(failedOrComplete(lampiranDocTanahStatus, totalFile, fileNumber, e)) {
                            status=lampiranDocTanahStatus?true:false;
                            break;
                        }
                    } else if (e.getDataDetail().getAssetType().getCategoryType().getCode().equals(AppConstans.JenisKategoriCode.MESIN)) {
                        // Mesin
                        if (sendDokTanah) { // Tanah Bangunan
                            fileNumber++;
                            lampiranDocTanahStatus = sendLPAReport(e, fileNumber,totalFile,logParent,url,username,password, "lampMesin");
                        }
                        if(failedOrComplete(lampiranDocTanahStatus, totalFile, fileNumber, e)) {
                            status=lampiranDocTanahStatus?true:false;
                            break;
                        }
//                        } else if (e.getDataDetail().getAssetType().getCategoryType().getCode().equals(AppConstans.JenisKategoriCode.STOCK)) {
//                            // Stok
//                        } else if (e.getDataDetail().getAssetType().getCategoryType().getCode().equals(AppConstans.JenisKategoriCode.VESEL)) {
//                            // Kapal
                    }
                    if(docWs.size()>0) {
                        // // TODO: 11/8/2017 Call method who gather WS dan Send it
                        fileNumber+=docWs.size();
                        worksheetStatus = sendWorksheet(e, docWs,fileNumber, totalFile, logParent, url, username, password);
                        docWs.clear();
                        docWs = null;
                    }
                    if(failedOrComplete(worksheetStatus, totalFile, fileNumber, e)) {
                        status=worksheetStatus?true:false;
                        break;
                    }
                    status = sendDokumenPelengkap(e, docPelengkap,fileNumber, totalFile, logParent, url, username, password);
                    break;
                }
                loop = false; //dummy loop to stop infinite loops
            }
        } else {
            e.setSourceStatus(AppConstans.SourceStatusConstans.FAILED_SEND);
            dao.save(e);
            status = false;
        }
        return status;
    }

    private Boolean sendWorksheet(DataCommon dataCommon, List<SurveyImage> docWs, Integer fileNumber, Integer totalFile, SendToSourceLog logParent, String url, String username, String password) {
        Boolean status = true;
        Response responseAtt;
        Attachment worksheet;
        for (SurveyImage image: docWs){
            fileNumber++;
            String namaFile = String.format("Worksheet"+image.getId()+"-%s.xls", dataCommon.getOrderNumber());
            SendToSourceAttachment sendToSourceAttachment = new SendToSourceAttachment();
            sendToSourceAttachment.setSendToSourceLog(logParent);
            sendToSourceAttachment.setIdCommon(dataCommon.getId());
            sendToSourceAttachment.setTanggal(new Date());
            sendToSourceAttachment.setFileName(namaFile);
//            String nameFileFix ="";
            try {
                worksheet = new Attachment();
                worksheet.setACL_ID(dataCommon.getSourceId());
                worksheet.setORDERID(Long.parseLong(dataCommon.getOrderNumber()));
                worksheet.setFILENAME(namaFile);
                worksheet.setFILEEXTENSION("xls");
                worksheet.setFILECONTENT(image.getImage());
                worksheet.setFILENUMBER(fileNumber);
                worksheet.setFILETOTAL(totalFile);
                responseAtt = CarmAttachmentClientUtils.getInstance(url, username, password).getService().receiveAppraisal_Attachment(worksheet);

                sendToSourceAttachment.setStatus(responseAtt.getError_Code().equals(AppConstans.SourceStatusConstans.SUCCESS_SEND_ATTACH_CARM)?0:2);
                sendToSourceAttachment.setMessage(responseAtt.getStatus());
                sendToSourceAttachment.setResponse(responseAtt.getError_Code());
                status = sendToSourceAttachment.getStatus()==0;
                if(!status) break;
            } catch (MalformedURLException e) {
                sendToSourceAttachment.setStatus(2);
                sendToSourceAttachment.setMessage("Error Malformed URL Exception");
                sendToSourceAttachment.setResponse(StringUtils.substring(e.getCause().getMessage(),0,254));
                e.printStackTrace();
                status=false;
                break;
            } catch (RemoteException e) {
                sendToSourceAttachment.setStatus(2);
                sendToSourceAttachment.setMessage("Error Remote Exception");
                sendToSourceAttachment.setResponse(StringUtils.substring(e.getCause().getMessage(),0,254));
                e.printStackTrace();
                status=false;
                break;
            } finally {
                sendToSourceLogAttachmenDao.save(sendToSourceAttachment);
                worksheet = null;
                responseAtt = null;
            }
        }
        if(docWs!=null)docWs.clear();
        return status;
    }

    private Boolean sendDokumenPelengkap (DataCommon dataCommon, List<SurveyImage> docPelengkap, Integer fileNumber, Integer totalFile, SendToSourceLog logParent, String url, String username, String password) {
//                List<SurveyImage> docWs = surveyImageDao.findBySurveyXLS(lpaUmum.getId());
        Boolean status = true;
        Boolean sendStatus = false;
        if (docPelengkap.size()>0){
            Response responseAtt;
            Attachment attachment;
            for (SurveyImage imagePelengkap: docPelengkap){
                SendToSourceAttachment sendToSourceAttachment = new SendToSourceAttachment();
                sendToSourceAttachment.setSendToSourceLog(logParent);
                sendToSourceAttachment.setIdCommon(dataCommon.getId());
                sendToSourceAttachment.setTanggal(new Date());
                try {
                    fileNumber++;
                    DocumentLpaType typeDoc = imagePelengkap.getDocumentLpaType();
                    String namaFile = typeDoc.getName() + "-" + imagePelengkap.getId();
                    String nameFileFix ="";
                    attachment = new Attachment();
                    attachment.setACL_ID(dataCommon.getSourceId());
                    attachment.setORDERID(Long.parseLong(dataCommon.getOrderNumber()));
                    if (imagePelengkap.getContentType()!=null && imagePelengkap.getContentType().length() > 0 ){
                        String type = imagePelengkap.getContentType();
                        if (type.equals(formatDocOthers[4])){
                            nameFileFix = ".pdf";
                            attachment.setFILEEXTENSION("pdf");
                        } else if (type.equals(formatDocOthers[0])) {
                            nameFileFix = ".jpeg";
                            attachment.setFILEEXTENSION("jpeg");
                        } else if (type.equals(formatDocOthers[1])) {
                            nameFileFix = ".png";
                            attachment.setFILEEXTENSION("png");
                        }
                        attachment.setFILENAME(String.format(namaFile+"-%s-"+nameFileFix, dataCommon.getOrderNumber()));
                    }else{
                        nameFileFix = ".unknown";
                        attachment.setFILENAME(String.format(namaFile+"-%s-"+nameFileFix, dataCommon.getOrderNumber()));
                        attachment.setFILEEXTENSION("unknown");
                    }
                    attachment.setFILECONTENT(imagePelengkap.getImage());
                    attachment.setFILENUMBER(fileNumber);
                    attachment.setFILETOTAL(totalFile);

                    sendToSourceAttachment.setFileName(String.format(namaFile+"-%s-"+nameFileFix, dataCommon.getOrderNumber()));

                    responseAtt = CarmAttachmentClientUtils.getInstance(url, username, password).getService().receiveAppraisal_Attachment(attachment);


                    sendToSourceAttachment.setStatus(responseAtt.getError_Code().equals(AppConstans.SourceStatusConstans.SUCCESS_SEND_ATTACH_CARM)?0:2);
                    sendToSourceAttachment.setMessage(responseAtt.getStatus());
                    sendToSourceAttachment.setResponse(responseAtt.getError_Code());
                    status = sendToSourceAttachment.getStatus()==0;
                    Boolean update = status && totalFile==fileNumber;
                    if (update){
                        sendToSourceAttachment.setStatus(1);
                        dataCommon.setSourceStatus(AppConstans.SourceStatusConstans.SUCCESS_SEND);
                        dao.save(dataCommon);
                        attachment = null;
                        responseAtt = null;
                        sendStatus = true;
                        break;
                    } else if (!status){
                        dataCommon.setSourceStatus(AppConstans.SourceStatusConstans.FAILED_SEND);
                        dao.save(dataCommon);
                        attachment = null;
                        responseAtt = null;
                        sendStatus = false;
//                        getSendToSourceLogAttachmenDao().save(sendToSourceAttachment);
                        break;
                    }
                } catch (MalformedURLException e) {
                    sendToSourceAttachment.setStatus(2);
                    sendToSourceAttachment.setMessage("Error Malformed URL Exception");
                    sendToSourceAttachment.setResponse(StringUtils.substring(e.getCause().getMessage(),0,254));
                    sendStatus = false;
                    e.printStackTrace();
                    break;
                } catch (RemoteException e) {
                    sendToSourceAttachment.setStatus(2);
                    sendToSourceAttachment.setMessage("Error Remote Exception");
                    sendToSourceAttachment.setResponse(StringUtils.substring(e.getCause().getMessage(),0,254));
                    sendStatus = false;
                    e.printStackTrace();
                    break;
                } finally {
                    sendToSourceLogAttachmenDao.save(sendToSourceAttachment);
                    attachment = null;
                    responseAtt = null;

                }
            }
        }
        if(docPelengkap!=null)docPelengkap.clear();
        return sendStatus;
    }

    private Boolean sendLPAReport(DataCommon dataCommon, Integer fileNumber, Integer totalFile, SendToSourceLog logParent, String url, String username, String password, String reportName) {
        String jasperName = "";
        String fileName = "";
        byte[] reportByte = null;
        Response responseAtt = null;
        Attachment rptFormatLpaInt = null;
        if(fileNumber==1) {  // LPA Report
            jasperName = getJasperNameAppraisalType(dataCommon.getAppraisalTypeCategory(), reportName);
            fileName = dataCommon.getAppraisalTypeCategory().equalsIgnoreCase(AppConstans.JenisAppraisalCategory.EKSTERNAL)?"Report-LPA-External-%s.pdf":"Report-LPA-%s.pdf";
        } else if (fileNumber==2) { // Image Survey
            jasperName = "rptLampiranImageSurvey.jasper";
            fileName = "Report-LPA-Image-survey-%s.pdf";
        } else if (fileNumber==3) { //  Peta / Denah
            jasperName = "rptLampiranImagePeta.jasper";
            fileName = "Report-LPA-Image-Peta-%s.pdf";
        } else {
            if(StringUtils.equalsIgnoreCase(reportName, "docTanah")) {
                jasperName = "rptLampiranDocTanah.jasper";
                fileName = "Lampiran-tanah-%s.pdf";
            } else if(StringUtils.equalsIgnoreCase(reportName, "docBangunan")) {
                jasperName = "rptLampiranDocBangunan.jasper";
                fileName = "Lampiran-bangunan-%s.pdf";
            } else if(StringUtils.equalsIgnoreCase(reportName, "lampKendaraan")) {
                jasperName = "rptLampiranKendaraan.jasper";
                fileName = "Lampiran-Kendaraan-%s.pdf";
            } else if(StringUtils.equalsIgnoreCase(reportName, "lampMesin")) {
                jasperName = "rptLampiranMesin.jasper";
                fileName = "Lampiran-Mesin-%s.pdf";
            }
        }
        SendToSourceAttachment sendToSourceAttachment = new SendToSourceAttachment();
        sendToSourceAttachment.setSendToSourceLog(logParent);
        sendToSourceAttachment.setIdCommon(dataCommon.getId());
        sendToSourceAttachment.setFileName(String.format(fileName, dataCommon.getOrderNumber()));
        sendToSourceAttachment.setTanggal(new Date());
        Map<String, Object> parameterMap = null;

        try {
            parameterMap = new HashMap<String, Object>();
            String jasperFile = String.format("%s%s%s%s%s%s%s", context.getRealPath(""), File.separator, "WEB-INF",
                    File.separator, "report", File.separator, jasperName);
            String jasperDir = String.format("%s%s%s%s%s%s", context.getRealPath(""), File.separator, "WEB-INF",
                    File.separator, "report", File.separator);
            parameterMap.put("SUBREPORT_DIR", jasperDir);
            parameterMap.put("datasource", dataSource);
            parameterMap.put("PIdLpaUmum", dataCommon.getDataDetail().getLpaUmum().iterator().next().getId());
            reportByte = JasperUtils.generate(parameterMap, jasperFile, dataSource);

            rptFormatLpaInt = new Attachment();
            rptFormatLpaInt.setACL_ID(dataCommon.getSourceId());
            rptFormatLpaInt.setORDERID(Long.parseLong(dataCommon.getOrderNumber()));
            rptFormatLpaInt.setFILENAME(String.format(fileName, dataCommon.getOrderNumber()));
            rptFormatLpaInt.setFILEEXTENSION("pdf");
            rptFormatLpaInt.setFILECONTENT(reportByte);
            rptFormatLpaInt.setFILENUMBER(fileNumber);
            rptFormatLpaInt.setFILETOTAL(totalFile);

            responseAtt = CarmAttachmentClientUtils.getInstance(url, username, password).getService().receiveAppraisal_Attachment(rptFormatLpaInt);
            Boolean sendStatus = responseAtt.getError_Code().equals(AppConstans.SourceStatusConstans.SUCCESS_SEND_ATTACH_CARM);
            sendToSourceAttachment.setStatus(sendStatus ? (totalFile==fileNumber?1:0) : 2);
            sendToSourceAttachment.setMessage(responseAtt.getStatus());
            sendToSourceAttachment.setResponse(responseAtt.getError_Code());
            return sendStatus;
        } catch (RemoteException e1) {
            sendToSourceAttachment.setStatus(99);
            if(StringUtils.containsIgnoreCase(e1.getCause().getMessage(),"Software caused connection abort")) {
                sendToSourceAttachment.setMessage("File "+ StringUtils.remove(fileName, "-%s") +" too Large " + url);
            } else {
                sendToSourceAttachment.setMessage("Error Remote Exception - " + StringUtils.substring(e1.getCause().getMessage(),0,50));
            }
            sendToSourceAttachment.setResponse(StringUtils.substring(e1.getCause().getMessage(),0,254));
            e1.printStackTrace();
            return false;
        } catch (SQLException e1) {
            sendToSourceAttachment.setStatus(99);
            sendToSourceAttachment.setMessage("Error SQL Exception");
            sendToSourceAttachment.setResponse(StringUtils.substring(e1.getCause().getMessage(),0,254));
            e1.printStackTrace();
            return false;
        } catch (MalformedURLException e1) {
            sendToSourceAttachment.setStatus(99);
            sendToSourceAttachment.setMessage("Error Malformed URL Exception");
            sendToSourceAttachment.setResponse(StringUtils.substring(e1.getCause().getMessage(),0,254));
            e1.printStackTrace();
            return false;
        } catch (JRException e1) {
            sendToSourceAttachment.setStatus(99);
            sendToSourceAttachment.setMessage("Error Jasper Report Exception");
            sendToSourceAttachment.setResponse(StringUtils.substring(e1.getCause().getMessage(),0,254));
            e1.printStackTrace();
            return false;
        }  catch (Exception e1) {
            sendToSourceAttachment.setStatus(99);
            sendToSourceAttachment.setMessage("Error Unhandled Exception");
            sendToSourceAttachment.setResponse(StringUtils.substring(e1.getCause().getMessage(),0,254));
            e1.printStackTrace();
            return false;
        } finally {
            sendToSourceLogAttachmenDao.save(sendToSourceAttachment);
            if(rptFormatLpaInt != null) rptFormatLpaInt = null;
            if(reportByte != null) reportByte = null;
            if(parameterMap!=null) parameterMap.clear();
        }
    }

//    private void saveCompletedAttachment(DataCommon dataCommon) {
//        dataCommon.setSourceStatus(AppConstans.SourceStatusConstans.SUCCESS_SEND);
//        getDao().save(dataCommon);
//    }
//
//    private Boolean failedOrComplete(Boolean status, Integer totalFile, Integer fileNumber, DataCommon dataCommon) {
//        if(!status) {
//            dataCommon.setSourceStatus(AppConstans.SourceStatusConstans.FAILED_SEND);
//            getDao().save(dataCommon);
//            return true;
//        } else if (totalFile == fileNumber) {
//            saveCompletedAttachment(dataCommon);
//            return true;
//        }
//        return  false;
//    }
    // CARM ATTACH END

    @Override
    public Boolean checkUserTasklistByCommonId(String userId, Long commonId) {
        DataCommon e = dao.get(commonId);
        return StringUtils.equalsIgnoreCase(e.getTaskList(),userId);
    }
}