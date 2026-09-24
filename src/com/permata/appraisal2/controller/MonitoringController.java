package com.permata.appraisal2.controller;

import com.permata.appraisal2.common.BaseController;
import com.permata.appraisal2.common.DataTables;
import com.permata.appraisal2.common.LabelValue;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.*;
import com.permata.appraisal2.facade.*;
import com.permata.appraisal2.model.PersonLevel;
import com.permata.appraisal2.model.StatusJaminan;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static javax.swing.UIManager.get;

/**
 * Created by w on 2/16/2017.
 */
@Controller
@RequestMapping("monitoring")
public class MonitoringController extends BaseController {

    @Autowired
    private AppraisalBranchFacade cabangAppraisalFacade;

    @Autowired
    private BranchFacade cabangFacade;

    @Autowired
    private DataCommonFacade facade;

    @Autowired
    private PersonFacade personFacade;

    @Autowired
    private MobileSendSurveyFacade mobFacade;

    @Autowired
    // @Qualifier("dataSource")
    private BasicDataSource dataSource;

    @Autowired
    private ServletContext context;

    @Autowired
    private BranchFacade facadeBranch;

    @Autowired
    private StatusJaminanFacade statusJaminanFacade;

    private final String DIR_VIEW = "/monitoring/";

    @RequestMapping(value = "report-order", method = RequestMethod.GET)
    public String reportOrder(Model model, @ModelAttribute("formBean") DataCommonDto dto) {
        return DIR_VIEW + "report-order";
    }

    @RequestMapping(value = "report-rwa", method = RequestMethod.GET)
    public String reportRwa(Model model, @ModelAttribute("formBean") DataCommonDto dto) {
        return DIR_VIEW + "report-rwa";
    }

    @RequestMapping(value = "report-logs", method = RequestMethod.GET)
    public String reportLogs(Model model, @ModelAttribute("formBean") DataCommonDto dto) {
        PersonLevel[] personLevels = (PersonLevel[]) ArrayUtils.removeElement(PersonLevel.values(), PersonLevel.ROLE_911);
        model.addAttribute("listLevel", personLevels);
        return DIR_VIEW + "report-logs";
    }

    @RequestMapping(value = "report-sla", method = RequestMethod.GET)
    public String reportSla(Model model, @ModelAttribute("formBean") DataCommonDto dto) {
        initCreateLpa(model, dto);
        return DIR_VIEW + "report-sla";
    }

    @RequestMapping(value = "report-productivity", method = RequestMethod.GET)
    public String reportProductivity(Model model, @ModelAttribute("formBean") DataCommonDto dto) {
        initCreateLpa(model, dto);
        return DIR_VIEW + "report-productivity";
    }

    @RequestMapping(value = "report-pending", method = RequestMethod.GET)
    public String reportPending(Model model, @ModelAttribute("formBean") DataCommonDto dto) {
        initCreateLpa(model, dto);
        return DIR_VIEW + "report-pending";
    }

    @RequestMapping(value = "pending-jobs", method = RequestMethod.GET)
    public String pendingJobs(Model model, @ModelAttribute("formBean") DataCommonDto dto) {
        return DIR_VIEW + "pending-jobs";
    }

    @RequestMapping(value = "pending-jobs-header", method = RequestMethod.GET)
    public String pendingJobsHeader(Model model, @ModelAttribute("formBean") DataCommonDto dto) {
        return DIR_VIEW + "pending-jobs-header";
    }

    @RequestMapping(value = "pending-jobs-isi", method = RequestMethod.GET)
    public String pendingJobsIsi(Model model, @ModelAttribute("formBean") DataCommonDto dto) {
        initCreateLpa(model, dto);
        return DIR_VIEW + "pending-jobs-isi";
    }

    @RequestMapping(value = "pending-jobs-cust-header", method = RequestMethod.GET)
    public String pendingJobsCustHeader(Model model, @ModelAttribute("formBean") DataCommonDto dto) {
        initCreateLpa(model, dto);
        return DIR_VIEW + "pending-jobs-cust-header";
    }

    @RequestMapping(value = "pending-jobs-cust-isi", method = RequestMethod.GET)
    public String pendingJobsCustIsi(Model model, @ModelAttribute("formBean") DataCommonDto dto) {
        initCreateLpa(model, dto);
        return DIR_VIEW + "pending-jobs-cust-isi";
    }

    @RequestMapping(value = "report-status-jaminan", method = RequestMethod.GET)
    public String reportStatusJaminan(Model model, @ModelAttribute("formBean") DataCommonDto dto, @RequestParam("createdDate") Date createdDate,@RequestParam("closedDate") Date closedDate) {
        logger.info("tanggalann "+createdDate);
        logger.info("tanggalann "+closedDate);
        initBranch(model, dto, createdDate, closedDate);
        return DIR_VIEW + "report-status-jaminan";
    }

    @RequestMapping(value = "report-type-object-aktiva", method = RequestMethod.GET)
    public String reportTypeObjectAktiva(Model model, @ModelAttribute("formBean") DataCommonDto dto, @RequestParam("createdDate") Date createdDate,@RequestParam("closedDate") Date closedDate) {
        logger.info("tanggalann "+createdDate);
        logger.info("tanggalann "+closedDate);
        initTypeObject(model, dto, createdDate, closedDate);
        return DIR_VIEW + "report-type-object";
    }

    @RequestMapping(value = "report-type-object-lpa", method = RequestMethod.GET)
    public String reportTypeObjectLpa(Model model, @ModelAttribute("formBean") DataCommonDto dto, @RequestParam("createdDate") Date createdDate,@RequestParam("closedDate") Date closedDate) {
        logger.info("tanggalann "+createdDate);
        logger.info("tanggalann "+closedDate);
        initJenisObject(model, dto, createdDate, closedDate);
        return DIR_VIEW + "report-type-object";
    }

    @RequestMapping(value = "report-intra-day", method = RequestMethod.GET)
    public String reportIntraDay(Model model, @ModelAttribute("formBean") DataCommonDto dto, @RequestParam("createdDate") Date createdDate,@RequestParam("closedDate") Date closedDate) {
        initIntraDay(model, dto,createdDate,closedDate);
        return DIR_VIEW + "report-intra-day";
    }

    @RequestMapping(value = "param-intra-day", method = RequestMethod.GET)
    public String paramIntraDay(Model model, @ModelAttribute("formBean") DataCommonDto dto) {
        return DIR_VIEW + "param-intra-day";
    }

    @RequestMapping(value = "param-pda-survey", method = RequestMethod.GET)
    public String paramPdaSurvey(Model model, @ModelAttribute("formBean") MobileSendSurveyDto dto) {
        initPdaSurvey(model,dto);
        return DIR_VIEW + "param-pda-survey";
    }

    @RequestMapping(value = "find-pda", method = RequestMethod.GET)
    @ResponseBody
    public DataTables<MobileSendSurveyDto> findPda(@ModelAttribute("formBean") MobileSendSurveyDto dto) {
        return mobFacade.findPdaValuer(getPagingInfo(), dto).cloneToDataTable();
    }

    @RequestMapping(value = "find-pda2", method = RequestMethod.GET)
    @ResponseBody
    public DataTables<MobileSendSurveyDto> findPda2(@ModelAttribute("formBean") MobileSendSurveyDto dto) {
        return mobFacade.findPdaSurvey(getPagingInfo(), dto).cloneToDataTable();
    }

    @RequestMapping(value = "param-type-object", method = RequestMethod.GET)
    public String paramTypeObject(Model model, @ModelAttribute("formBean") DataCommonDto dto) {
        return DIR_VIEW + "param-type-object";
    }

    @RequestMapping(value = "param-status-jaminan", method = RequestMethod.GET)
    public String paramStatusJaminan(Model model, @ModelAttribute("formBean") DataCommonDto dto) {
        return DIR_VIEW + "param-status-jaminan";
    }


    @ModelAttribute(value = "listCabangBisnis")
    public List<AppraisalBranchDto> listCabangBisnis() {
        return cabangFacade.find(new AppraisalBranchDto(), PagingInfo.createToRetrieveAll()).getDataList();
    }

    @ModelAttribute(value = "listCabang")
    public List<AppraisalBranchDto> listCabang() {
        return cabangAppraisalFacade.find(new AppraisalBranchDto(), PagingInfo.createToRetrieveAll()).getDataList();
    }

    private void initTypeObject(Model model, DataCommonDto formBean, Date createdDate, Date closedDate){
        Calendar createdDateTime = Calendar.getInstance();
        createdDateTime.setTime(createdDate);
        createdDateTime.set(Calendar.HOUR_OF_DAY, 0);
        createdDateTime.set(Calendar.MINUTE, 00);
        createdDateTime.set(Calendar.SECOND, 00);
        createdDateTime.set(Calendar.MILLISECOND, 0);
        Timestamp createdDateTimestamp = new Timestamp(createdDateTime.getTimeInMillis());

        Calendar closedDateTime = Calendar.getInstance();
        closedDateTime.setTime(closedDate);
        closedDateTime.set(Calendar.HOUR_OF_DAY, 23);
        closedDateTime.set(Calendar.MINUTE, 59);
        closedDateTime.set(Calendar.SECOND, 59);
        closedDateTime.set(Calendar.MILLISECOND, 0);
        Timestamp closedDateTimestamp = new Timestamp(closedDateTime.getTimeInMillis());

        List<TypeObjectViewDto> listObject = facade.getTypeObject(createdDateTimestamp, closedDateTimestamp);
        int counter = 0;
        TypeObjectViewDto obj = null;
        List<String> listTlGroup = new ArrayList<String>();
        List<String> listTotalGroup = new ArrayList<String>();
        List<AppraisalBranchDto> listBranch = facadeBranch.getAll();
        for (int a=0;a<listBranch.size();a++){
            listTlGroup.add(listBranch.get(a).getName());
            listTotalGroup.add("0");
        }

        counter = 0;
        List<DetailTypeOfObjectViewDto> listDetail = new ArrayList<DetailTypeOfObjectViewDto>();
        DetailTypeOfObjectViewDto detail = null;
        for (TypeObjectViewDto tmp : listObject){
            if (counter == 0){
                detail = new DetailTypeOfObjectViewDto();
                detail.setJenisAktiva(tmp.getJenisaktiva());
                List<String> listData = new ArrayList<String>();
                for (int x = 0;x < listTlGroup.size(); x++){
                    if (tmp.getGrouptl().equalsIgnoreCase(listTlGroup.get(x))){
                        listData.add(String.valueOf(tmp.getCount()));
                        listTotalGroup.set(x, String.valueOf(tmp.getCount()));
                    }else {
                        listData.add("0");
                    }
                }
                detail.setBisnisBranch(listData);
            }else {
                if (detail.getJenisAktiva().equalsIgnoreCase(tmp.getJenisaktiva())){
                    List<String> listData = detail.getBisnisBranch();
                    for (int x = 0;x < listTlGroup.size(); x++){
                        if (tmp.getGrouptl().equalsIgnoreCase(listTlGroup.get(x))){
                            listData.set(x, String.valueOf(Integer.parseInt(listData.get(x))+tmp.getCount()));
                            listTotalGroup.set(x, String.valueOf(Integer.parseInt(listTotalGroup.get(x))+tmp.getCount()));
                        }
                        detail.setBisnisBranch(listData);
                    }
                }else {
                    listDetail.add(detail);
                    detail = new DetailTypeOfObjectViewDto();
                    detail.setJenisAktiva(tmp.getJenisaktiva());
                    List<String> listData = new ArrayList<String>();
                    for (int x = 0;x < listTlGroup.size(); x++){
                        if (tmp.getGrouptl().equalsIgnoreCase(listTlGroup.get(x))){
                            listData.add(String.valueOf(tmp.getCount()));
                            listTotalGroup.set(x, String.valueOf(Integer.parseInt(listTotalGroup.get(x))+tmp.getCount()));
                        }else {
                            listData.add("0");
                        }
                    }
                    detail.setBisnisBranch(listData);
                }
            }
            counter++;
        }
        if (detail != null){
            listDetail.add(detail);
        }

        model.addAttribute("ListTotalGroup", listTotalGroup);
        model.addAttribute("ListBranch", listBranch);
        model.addAttribute("ListDetail", listDetail);
        model.addAttribute("paramss", "1");
        model.addAttribute("createdDate", new SimpleDateFormat("dd MMM yyyy").format(createdDate));
        model.addAttribute("closedDate", new SimpleDateFormat("dd MMM yyyy").format(closedDate));
        model.addAttribute("formBean", formBean);

    }

    private void initJenisObject(Model model, DataCommonDto formBean, Date createdDate, Date closedDate){
        Calendar createdDateTime = Calendar.getInstance();
        createdDateTime.setTime(createdDate);
        createdDateTime.set(Calendar.HOUR_OF_DAY, 0);
        createdDateTime.set(Calendar.MINUTE, 00);
        createdDateTime.set(Calendar.SECOND, 00);
        createdDateTime.set(Calendar.MILLISECOND, 0);
        Timestamp createdDateTimestamp = new Timestamp(createdDateTime.getTimeInMillis());

        Calendar closedDateTime = Calendar.getInstance();
        closedDateTime.setTime(closedDate);
        closedDateTime.set(Calendar.HOUR_OF_DAY, 23);
        closedDateTime.set(Calendar.MINUTE, 59);
        closedDateTime.set(Calendar.SECOND, 59);
        closedDateTime.set(Calendar.MILLISECOND, 0);
        Timestamp closedDateTimestamp = new Timestamp(closedDateTime.getTimeInMillis());

        List<TypeObjectViewDto> listObject = facade.getTypeObject(createdDateTimestamp, closedDateTimestamp);
        int counter = 0;
        TypeObjectViewDto obj = null;
        List<String> listTlGroup = new ArrayList<String>();
        List<String> listTotalGroup = new ArrayList<String>();
        List<AppraisalBranchDto> listBranch = facadeBranch.getAll();
        for (int a=0;a<listBranch.size();a++){
            listTlGroup.add(listBranch.get(a).getName());
            listTotalGroup.add("0");
            listTotalGroup.add("0");
            listTotalGroup.add("0");
        }

        counter = 0;
        List<DetailTypeOfObjectViewDto> listDetail = new ArrayList<DetailTypeOfObjectViewDto>();
        DetailTypeOfObjectViewDto detail = null;
        for (TypeObjectViewDto tmp : listObject){
            if (counter == 0){
                detail = new DetailTypeOfObjectViewDto();
                detail.setJenisAktiva(tmp.getJenisaktiva());
                List<String> listData = new ArrayList<String>();
                for (int x = 0;x < listTlGroup.size(); x++){
                    if (tmp.getGrouptl().equalsIgnoreCase(listTlGroup.get(x))){
                        listData.add(String.valueOf(tmp.getCountinternal()));
                        listData.add(String.valueOf(tmp.getCounteksternal()));
                        listData.add(String.valueOf(tmp.getCountindikasi()));

                        listTotalGroup.set((x*3)+0, String.valueOf(tmp.getCountinternal()));
                        listTotalGroup.set((x*3)+1, String.valueOf(tmp.getCounteksternal()));
                        listTotalGroup.set((x*3)+2, String.valueOf(tmp.getCountindikasi()));
                    }else {
                        listData.add("0");
                        listData.add("0");
                        listData.add("0");
                    }
                }
                detail.setBisnisBranch(listData);
            }else {
                if (detail.getJenisAktiva().equalsIgnoreCase(tmp.getJenisaktiva())){
                    List<String> listData = detail.getBisnisBranch();
                    for (int x = 0;x < listTlGroup.size(); x++){
                        if (tmp.getGrouptl().equalsIgnoreCase(listTlGroup.get(x))){
                            listData.set((x*3)+0, String.valueOf(Integer.parseInt(listData.get((x*3)+0))+tmp.getCountinternal()));
                            listData.set((x*3)+1, String.valueOf(Integer.parseInt(listData.get((x*3)+1))+tmp.getCounteksternal()));
                            listData.set((x*3)+2, String.valueOf(Integer.parseInt(listData.get((x*3)+2))+tmp.getCountindikasi()));

                            listTotalGroup.set((x*3)+0, String.valueOf(Integer.parseInt(listTotalGroup.get((x*3)+0))+tmp.getCountinternal()));
                            listTotalGroup.set((x*3)+1, String.valueOf(Integer.parseInt(listTotalGroup.get((x*3)+1))+tmp.getCounteksternal()));
                            listTotalGroup.set((x*3)+2, String.valueOf(Integer.parseInt(listTotalGroup.get((x*3)+2))+tmp.getCountindikasi()));
                        }
                        detail.setBisnisBranch(listData);
                    }
                }else {
                    listDetail.add(detail);
                    detail = new DetailTypeOfObjectViewDto();
                    detail.setJenisAktiva(tmp.getJenisaktiva());
                    List<String> listData = new ArrayList<String>();
                    for (int x = 0;x < listTlGroup.size(); x++){
                        if (tmp.getGrouptl().equalsIgnoreCase(listTlGroup.get(x))){
                            listData.add(String.valueOf(tmp.getCountinternal()));
                            listData.add(String.valueOf(tmp.getCounteksternal()));
                            listData.add(String.valueOf(tmp.getCountindikasi()));

                            listTotalGroup.set((x*3)+0, String.valueOf(Integer.parseInt(listTotalGroup.get((x*3)+0))+tmp.getCountinternal()));
                            listTotalGroup.set((x*3)+1, String.valueOf(Integer.parseInt(listTotalGroup.get((x*3)+1))+tmp.getCounteksternal()));
                            listTotalGroup.set((x*3)+2, String.valueOf(Integer.parseInt(listTotalGroup.get((x*3)+2))+tmp.getCountindikasi()));
                        }else {
                            listData.add("0");
                            listData.add("0");
                            listData.add("0");
                        }
                    }
                    detail.setBisnisBranch(listData);
                }
            }
            counter++;
        }
        if (detail != null){
            listDetail.add(detail);
        }

        model.addAttribute("ListTotalGroup", listTotalGroup);
        model.addAttribute("ListBranch", listBranch);
        model.addAttribute("ListDetail", listDetail);
        model.addAttribute("paramss", "2");
        model.addAttribute("createdDate", new SimpleDateFormat("dd MMM yyyy").format(createdDate));
        model.addAttribute("closedDate", new SimpleDateFormat("dd MMM yyyy").format(closedDate));
        model.addAttribute("formBean", formBean);
    }

    private void initBranch(Model model, DataCommonDto formBean, Date createdDate, Date closedDate){
        Calendar createdDateTime = Calendar.getInstance();
        createdDateTime.setTime(createdDate);
        createdDateTime.set(Calendar.HOUR_OF_DAY, 0);
        createdDateTime.set(Calendar.MINUTE, 00);
        createdDateTime.set(Calendar.SECOND, 00);
        createdDateTime.set(Calendar.MILLISECOND, 0);
//        Timestamp createdDateTimestamp = new Timestamp(createdDateTime.getTimeInMillis());

        Calendar closedDateTime = Calendar.getInstance();
        closedDateTime.setTime(closedDate);
        closedDateTime.set(Calendar.HOUR_OF_DAY, 23);
        closedDateTime.set(Calendar.MINUTE, 59);
        closedDateTime.set(Calendar.SECOND, 59);
        closedDateTime.set(Calendar.MILLISECOND, 0);
//        Timestamp closedDateTimestamp = new Timestamp(closedDateTime.getTimeInMillis());


        List<AppraisalBranchDto> listBranch = facadeBranch.getAll();
//        List<ViewReportJenisJaminanDto> listView = facade.getStatusJaminan(createdDateTimestamp, closedDateTimestamp);
        List<StatusJaminanDto> listStatusJaminan =  statusJaminanFacade.getAll();
        List<Object[]> listReportStatus = facade.getReportStatusJaminan(createdDateTime, closedDateTime, listBranch, listStatusJaminan);
        // TODO: 8/9/2017  StatusJaminan distinct then fill the record summary according to status jaminan must distinct status jaminan to data master status jaminan first
        String statusJaminan = "";
        int bulan = 0;
        int tahun = 0;
        List<List<Object>> listArrayList = new ArrayList<List<Object>>();
        int total = 0;
        for (Object[] reportStatus : listReportStatus) {
            List<Object> objectList = new ArrayList<Object>();
            String[] statusAndDate = StringUtils.split(reportStatus[0].toString(), "__");
            try {
                objectList.add(0, new SimpleDateFormat("MMM yyyy").format(new SimpleDateFormat("yyyyMM").parse(statusAndDate[0])));
            } catch (ParseException e) {
                e.printStackTrace();
            }
//            objectList.add(0, statusAndDate[0]); // Get Date
            objectList.add(1, statusAndDate[1]); // GetStatus Jaminan
            for(int i=0;i<listBranch.size();i++) {
                objectList.add(i+2,reportStatus[i+1]);
            }
            objectList.add(listBranch.size()+2,reportStatus[listBranch.size()+1]);
            total += Integer.parseInt(reportStatus[listBranch.size()+1].toString());
            listArrayList.add(objectList);
        }
        model.addAttribute("listStatusJaminan", listArrayList);
        model.addAttribute("totalSemua", total);
//        List<ViewPendingDto> listPending = new ArrayList<ViewPendingDto>();

//        List<DataDto> tmpData = new ArrayList<DataDto>();



//        for (AppraisalBranchDto dt1 : listBranch){
//            DataDto data = new DataDto();
//            data.setNama(dt1.getName());
//            data.setCount(0);
//            tmpData.add(data);
//        }
//
//        int cnt = 0;


        model.addAttribute("listBranch", listBranch);
//        model.addAttribute("listView", listPending);

        model.addAttribute("formBean", formBean);

    }

    private void initIntraDay(Model model, DataCommonDto formBean, Date createdDate, Date closedDate){
        Calendar createdDateTime = Calendar.getInstance();
        createdDateTime.setTime(createdDate);
        createdDateTime.set(Calendar.HOUR_OF_DAY, 0);
        createdDateTime.set(Calendar.MINUTE, 00);
        createdDateTime.set(Calendar.SECOND, 00);
        createdDateTime.set(Calendar.MILLISECOND, 0);
        Timestamp createdDateTimestamp = new Timestamp(createdDateTime.getTimeInMillis());

        Calendar closedDateTime = Calendar.getInstance();
        closedDateTime.setTime(closedDate);
        closedDateTime.set(Calendar.HOUR_OF_DAY, 23);
        closedDateTime.set(Calendar.MINUTE, 59);
        closedDateTime.set(Calendar.SECOND, 59);
        closedDateTime.set(Calendar.MILLISECOND, 0);
        Timestamp closedDateTimestamp = new Timestamp(closedDateTime.getTimeInMillis());
        List<DataCommonDto>  listIntraDay = facade.getIntraDay(createdDateTimestamp,closedDateTimestamp);

        logger.info("intraday "+listIntraDay.size());

        List<ViewReportIntraDayDto> listReport = new ArrayList<ViewReportIntraDayDto>();
        ViewReportIntraDayDto viewReport = null;

        int counter = 0;

        for(int i=0;i<listIntraDay.size();i++){
            Timestamp tlsubmit = new Timestamp(listIntraDay.get(i).getSubmitDate().getTime());
            String tgl =new SimpleDateFormat("dd-MM-yyyy").format(listIntraDay.get(i).getSubmitDate());
            DateFormat tlsubmittglString = new SimpleDateFormat("dd-MM-yyyy");
            Date tlsubmittgl;
                    try{
                        tlsubmittgl = tlsubmittglString.parse(tgl);

                        logger.info("tgltimestamp "+i +" "+tlsubmit);
            logger.info("datedate "+i+" "+tlsubmittgl);

            logger.info("jamnya "+tlsubmit.getHours());

            if (counter == 0) {
                viewReport = new ViewReportIntraDayDto();
                viewReport.setSum7(0);
                viewReport.setSum8(0);
                viewReport.setSum9(0);
                viewReport.setSum10(0);
                viewReport.setSum11(0);
                viewReport.setSum12(0);
                viewReport.setSum13(0);
                viewReport.setSum14(0);
                viewReport.setSum15(0);
                viewReport.setSum16(0);
                viewReport.setSum17(0);
                viewReport.setSum18(0);

                viewReport.setSetTgl(tgl);
                if (tlsubmit.getHours() < 8) {
                    viewReport.setSum7(1);
                } else if (tlsubmit.getHours() == 8) {
                    viewReport.setSum8(1);
                } else if ((tlsubmit.getHours() == 9)) {
                    viewReport.setSum9(1);
                } else if ((tlsubmit.getHours() == 10)) {
                    viewReport.setSum10(1);
                } else if ((tlsubmit.getHours() == 11)) {
                    viewReport.setSum11(1);
                } else if ((tlsubmit.getHours() == 12)) {
                    viewReport.setSum12(1);
                } else if ((tlsubmit.getHours() == 13)) {
                    viewReport.setSum13(1);
                } else if ((tlsubmit.getHours() == 14)) {
                    viewReport.setSum14(1);
                } else if ((tlsubmit.getHours() == 15)) {
                    viewReport.setSum15(1);
                } else if ((tlsubmit.getHours() == 16)) {
                    viewReport.setSum16(1);
                } else if ((tlsubmit.getHours() == 17)) {
                    viewReport.setSum17(1);
                } else if ((tlsubmit.getHours() >= 18)) {
                    viewReport.setSum18(1);
                }
            } else {
//                if (viewReport.getTglIntraDay().getTime() == listIntraDay.get(i).getSubmitDate().getTime()) {
                if (viewReport.getSetTgl().equals(tgl)) {
                    if (tlsubmit.getHours() < 8) {
                        viewReport.setSum7(viewReport.getSum7() + 1);
                    } else if (tlsubmit.getHours() == 8) {
                        viewReport.setSum8(viewReport.getSum8() + 1);
                    } else if ((tlsubmit.getHours() == 9)) {
                        viewReport.setSum9(viewReport.getSum9() + 1);
                    } else if ((tlsubmit.getHours() == 10)) {
                        viewReport.setSum10(viewReport.getSum10() + 1);
                    } else if ((tlsubmit.getHours() == 11)) {
                        viewReport.setSum11(viewReport.getSum11() + 1);
                    } else if ((tlsubmit.getHours() == 12)) {
                        viewReport.setSum12(viewReport.getSum12() + 1);
                    } else if ((tlsubmit.getHours() == 13)) {
                        viewReport.setSum13(viewReport.getSum13() + 1);
                    } else if ((tlsubmit.getHours() == 14)) {
                        viewReport.setSum14(viewReport.getSum14() + 1);
                    } else if ((tlsubmit.getHours() == 15)) {
                        viewReport.setSum15(viewReport.getSum15() + 1);
                    } else if ((tlsubmit.getHours() == 16)) {
                        viewReport.setSum16(viewReport.getSum16() + 1);
                    } else if ((tlsubmit.getHours() == 17)) {
                        viewReport.setSum17(viewReport.getSum17() + 1);
                    } else if ((tlsubmit.getHours() >= 18)) {
                        viewReport.setSum18(viewReport.getSum18() + 1);
                    }
                } else {//jika tanggal tidak sama
                    listReport.add(viewReport);
                    viewReport = new ViewReportIntraDayDto();
                    if (tlsubmit.getHours() < 8) {
                        viewReport.setSum7(1);
                    } else if (tlsubmit.getHours() == 8) {
                        viewReport.setSum8(1);
                    } else if ((tlsubmit.getHours() == 9)) {
                        viewReport.setSum9(1);
                    } else if ((tlsubmit.getHours() == 10)) {
                        viewReport.setSum10(1);
                    } else if ((tlsubmit.getHours() == 11)) {
                        viewReport.setSum11(1);
                    } else if ((tlsubmit.getHours() == 12)) {
                        viewReport.setSum12(1);
                    } else if ((tlsubmit.getHours() == 13)) {
                        viewReport.setSum13(1);
                    } else if ((tlsubmit.getHours() == 14)) {
                        viewReport.setSum14(1);
                    } else if ((tlsubmit.getHours() == 15)) {
                        viewReport.setSum15(1);
                    } else if ((tlsubmit.getHours() == 16)) {
                        viewReport.setSum16(1);
                    } else if ((tlsubmit.getHours() == 17)) {
                        viewReport.setSum17(1);
                    } else if ((tlsubmit.getHours() >= 18)) {
                        viewReport.setSum18(1);
                    }
                    viewReport.setSetTgl(tgl);

                }

            }
                    }catch (ParseException e) {
                        e.printStackTrace();
                    }
            counter++;
        }
        if (viewReport != null) {
            listReport.add(viewReport);
        }
        model.addAttribute("ListView", listReport);
        model.addAttribute("JumlahRecord", listReport.size());

        model.addAttribute("createdDate", new SimpleDateFormat("dd MMM yyyy").format(createdDate));
        model.addAttribute("closedDate", new SimpleDateFormat("dd MMM yyyy").format(closedDate));


    }

    private Date nextMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        Date dateAsObjAfterAMonth = cal.getTime() ;
        return dateAsObjAfterAMonth;
    }

    private void initPdaSurvey(Model model, MobileSendSurveyDto formBean){
        model.addAttribute("listValuers", personFacade.personValuerWithId());
    }


    private void initCreateLpa(Model model, DataCommonDto formBean) {
        PersonLevel[] personLevels = (PersonLevel[]) ArrayUtils.removeElement(PersonLevel.values(), PersonLevel.ROLE_911);
        model.addAttribute("formBean", formBean);
        model.addAttribute("listValuers", personFacade.personValuerWithId());
        model.addAttribute("listLevel", personLevels);

        List<PendingJobsByUsersDto> listPending = facade.findPendingJobsByUsers();
        List<PendingJobsByUsersDto> namaList = new ArrayList<PendingJobsByUsersDto>();
        List<PendingJobsByUsersDto> adminList = new ArrayList<PendingJobsByUsersDto>();
        List<PendingJobsByUsersDto> surveyorList = new ArrayList<PendingJobsByUsersDto>();
        List<PendingJobsByUsersDto> approvalList = new ArrayList<PendingJobsByUsersDto>();
        List<PendingJobsByUsersDto> valuerList = new ArrayList<PendingJobsByUsersDto>();
        String nama = "       ";

//        System.out.println("sizeAdmin " + adminList.size());
//        System.out.println("listPending " + listPending.size());

        for (int bar = 0; bar < listPending.size(); bar++) {
            logger.debug("var nama : " + nama);
            PendingJobsByUsersDto dto = new PendingJobsByUsersDto();

            logger.debug("Nama : " + listPending.get(bar).getNama());
            if (listPending.get(bar).getNama() == null){
                listPending.get(bar).setNama(" ");
            }

            if (!listPending.get(bar).getNama().equalsIgnoreCase(nama)) {
                nama = listPending.get(bar).getNama();
                logger.debug("listPending.get " + nama);
                dto.setNama(nama);
                namaList.add(dto);
            }
            if (listPending.get(bar).getPosisi().equalsIgnoreCase("App_Admin")) {
                dto.setNama(nama);
                dto.setPosisi("admin");
                dto.setBd5(listPending.get(bar).getBd5());
                dto.setBd4(listPending.get(bar).getBd4());
                dto.setBd3(listPending.get(bar).getBd3());
                dto.setBd2(listPending.get(bar).getBd2());
                dto.setBd1(listPending.get(bar).getBd1());
                dto.setBd0(listPending.get(bar).getBd0());
                dto.setParam(1);
                adminList.add(dto);
            } else if (listPending.get(bar).getPosisi().equalsIgnoreCase("App_Surveyor")) {
                dto.setNama(nama);
                dto.setPosisi("surveyor");
                dto.setBd5(listPending.get(bar).getBd5());
                dto.setBd4(listPending.get(bar).getBd4());
                dto.setBd3(listPending.get(bar).getBd3());
                dto.setBd2(listPending.get(bar).getBd2());
                dto.setBd1(listPending.get(bar).getBd1());
                dto.setBd0(listPending.get(bar).getBd0());
                dto.setParam(1);
                surveyorList.add(dto);
            } else if (listPending.get(bar).getPosisi().equalsIgnoreCase("App_Valuer")) {
                dto.setNama(nama);
                dto.setPosisi("valuer");
                dto.setBd5(listPending.get(bar).getBd5());
                dto.setBd4(listPending.get(bar).getBd4());
                dto.setBd3(listPending.get(bar).getBd3());
                dto.setBd2(listPending.get(bar).getBd2());
                dto.setBd1(listPending.get(bar).getBd1());
                dto.setBd0(listPending.get(bar).getBd0());
                dto.setParam(1);
                valuerList.add(dto);
            } else if (listPending.get(bar).getPosisi().equalsIgnoreCase("App_Approval")) {
                dto.setNama(nama);
                dto.setPosisi("approval");
                dto.setBd5(listPending.get(bar).getBd5());
                dto.setBd4(listPending.get(bar).getBd4());
                dto.setBd3(listPending.get(bar).getBd3());
                dto.setBd2(listPending.get(bar).getBd2());
                dto.setBd1(listPending.get(bar).getBd1());
                dto.setBd0(listPending.get(bar).getBd0());
                dto.setParam(1);
                approvalList.add(dto);
            }
        }


        if (adminList.size() == 0) {
            adminList = null;
        }

        if (surveyorList.size() == 0) {
            surveyorList = null;
        }

        if (valuerList.size() == 0) {
            valuerList = null;
        }

        if (approvalList.size() == 0) {
            approvalList = null;
        }



        model.addAttribute("listNama", namaList);
        model.addAttribute("listAdmin", adminList);
        model.addAttribute("listSurveyor", surveyorList);
        model.addAttribute("listValuer", valuerList);
        model.addAttribute("listApproval", approvalList);

        List<PendingJobsByUsersDto> listPendingCust =facade.findPendingJobsByCustomers();
        model.addAttribute("listPendingCust", listPendingCust);
        model.addAttribute("jumlahPendingCust", listPendingCust.size());
        model.addAttribute("listData", facade.findPendingJobsByUsers());
    }

    @RequestMapping(value = "find-marketing")
    @ResponseBody
    public List<LabelValue<String, String>> findMarketing(@RequestParam("branchId") Long branchId){
        return personFacade.findMarketing(branchId);
    }

    @RequestMapping(value = "find-appraisal")
    @ResponseBody
    public List<LabelValue<String, String>> findMarketing(@RequestParam("appraisalTypeName") String divisi){
        Enum div = null;
        if(divisi.equals("VALUER")){
            div = PersonLevel.APPRAISAL_VALUER;
        }else if(divisi.equals("APPROVAL")){
            div = PersonLevel.APPRAISAL_APPROVAL;
        }else if(divisi.equals("SURVEYOR")){
            div = PersonLevel.APPRAISAL_SURVEYOR;
        }else if(divisi.equals("ADMIN")){
            div = PersonLevel.APPRAISAL_ADMIN;
        }
        logger.info("PERSONLEVEL"+ div);
        return personFacade.findUserAppraisal(div);
    }

    @RequestMapping(value = "find-role")
    @ResponseBody
    public List<LabelValue<String, String>> findMarketing(@RequestParam("level") PersonLevel divisi){
        return personFacade.findUserAppraisal(divisi);
    }

    @RequestMapping(value = {"report-order.xls","report-rekap.xls"}, method = RequestMethod.GET)
    public ModelAndView print(@RequestParam("branchId") Long branchId, @RequestParam("appraisalTypeId") Long appraisalTypeId,@RequestParam("param") Integer view,
                              @RequestParam("createdDate") Date createdDate, @RequestParam("closedDate") Date closedDate) {
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("datasource", dataSource);
        parameterMap.put("pCabangId", branchId);
        parameterMap.put("pAppraisalCabangId", appraisalTypeId);
        logger.info("createdDate" + createdDate);
        closedDate.setHours(23);
        closedDate.setMinutes(59);
        parameterMap.put("pSubmitDate", createdDate);
        parameterMap.put("pCloseDate", closedDate);
        String views = "";
        if (view==1){
            views = "xlsReportOrder";
        }else{
            views = "xlsReportRekap";
        }
        return new ModelAndView(views, parameterMap);
    }

    @RequestMapping(value = {"report-sla.xls", "report-sla-html"}, method = RequestMethod.GET)
    public ModelAndView printSla(@RequestParam("appraisalTypeName") String branchName, @RequestParam("closedBy") String closedBy, @RequestParam("branchId") Long branchId,
                                 @RequestParam("createdBy") String createdBy, @RequestParam("param") Integer view,
                              @RequestParam("createdDate") Date createdDate, @RequestParam("closedDate") Date closedDate , @RequestParam("timUser") Integer timUser) {
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        closedDate.setHours(23);
        closedDate.setMinutes(59);
        parameterMap.put("datasource", dataSource);
        parameterMap.put("pSubmitDate", createdDate);
        parameterMap.put("pCloseDate", new Timestamp(closedDate.getTime()));
        parameterMap.put("pCabangApppraisal", 1);
        parameterMap.put("pTimUser", timUser);
        String param = "";
        String paramBranch = "";
        logger.info("branchNames "+branchName);
        logger.info("apprX"+closedBy);

        if (closedBy!=null||createdBy!=null) {
            if (branchId!=null){

                paramBranch = "and c.cabang_id = "+branchId;
                param = "and c.created_by = '"+createdBy+"'";
            }else {
                if (branchName.equalsIgnoreCase("VALUER")) {
                    param = "and c.value_by = '" + closedBy + "'";
                } else if (branchName.equalsIgnoreCase("SURVEYOR")) {
                    param = "and c.survey_by = '" + closedBy + "'";
                } else if (branchName.equalsIgnoreCase("APPROVAL")) {
                    param = "and c.closed_by = '" + closedBy + "'";
                }
            }
        }else{
            param = "";
        }
        logger.info("param "+param);
        parameterMap.put("pPosisi", branchName);
        parameterMap.put("pCabang", branchId);
        parameterMap.put("pCabangDesk", paramBranch);
        parameterMap.put("pUserId", param);
        parameterMap.put("pUserName", closedBy!=null?closedBy:(createdBy!=null?createdBy:""));
        String views = "";
        if (view==1){
            views = "xlsReportSla";
        }else{
            views = "htmlReportSla";
        }
        return new ModelAndView(views, parameterMap);

    }

    @RequestMapping(value = "report-productivity.xls", method = RequestMethod.GET)
    public ModelAndView printProductivity(@RequestParam("appraisalTypeName") String branchName, @RequestParam("closedBy") String closedBy,
                                          @RequestParam("param") Integer view,
                                          @RequestParam("createdDate") Date createdDate, @RequestParam("closedDate") Date closedDate ) {
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("datasource", dataSource);
        closedDate.setHours(23);
        closedDate.setMinutes(59);
        parameterMap.put("pSubmitDate", createdDate);
        parameterMap.put("pCloseDate", new Timestamp(closedDate.getTime()));
        parameterMap.put("pCabangApppraisal", Long.valueOf("1"));
        String param = "";
        String paramBranch = "";
        logger.info("branchNames "+branchName);
        logger.info("apprX"+closedBy);

        if (closedBy!=null) {
            if (branchName.equalsIgnoreCase("VALUER")) {
                param = "and c.value_by = '" + closedBy + "'";
            } else if (branchName.equalsIgnoreCase("SURVEYOR")) {
                param = "and c.survey_by = '" + closedBy + "'";
            } else if (branchName.equalsIgnoreCase("APPROVAL")) {
                param = "and c.closed_by = '" + closedBy + "'";
            }else if (branchName.equalsIgnoreCase("ADMIN")){
                param = "and c.admin_by = '" + closedBy + "'";
            }
        }else{
            param = "";
        }
        logger.info("param "+param);
        parameterMap.put("pPosisi", branchName);
        parameterMap.put("pCabangDesk", paramBranch);
        parameterMap.put("pUserId", param);
        parameterMap.put("pUserName", closedBy!=null?closedBy:"");
        String views = "";
        if (branchName.equalsIgnoreCase("ADMIN")){
            views = "xlsReportProductivityAdmin";
        }else{
            views = "xlsReportProductivity";
        }
        return new ModelAndView(views, parameterMap);

    }

    @RequestMapping(value = "report-pending.xls", method = RequestMethod.GET)
    public ModelAndView printPendingReport(@RequestParam("appraisalTypeName") String branchName, @RequestParam("closedBy") String closedBy,@RequestParam("createdBy") String createdBy,
                                          @RequestParam("param") Integer view, @RequestParam("branchId") Long branchId, @RequestParam("timUser") Integer timUser) {
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("datasource", dataSource);
        parameterMap.put("pCabangApppraisal", Long.valueOf("1"));
        String param = "";
        String taskList = "";
        String paramBranch = "";
        logger.info("branchNames "+branchName);
        logger.info("apprX"+closedBy);

        if (branchName.equalsIgnoreCase("VALUER")) {
            param = "App_Valuer";
        } else if (branchName.equalsIgnoreCase("SURVEYOR")) {
            param = "App_Surveyor";
        } else if (branchName.equalsIgnoreCase("APPROVAL")) {
            param = "App_Approval";
        } else if (branchName.equalsIgnoreCase("ADMIN")) {
            param = "App_Admin";
        }

        if (closedBy!=null ||createdBy!=null ) {
            if (branchId!=null){
                taskList = "and c.task_list = '"+createdBy+"'";
            }else {
                if (branchName.equalsIgnoreCase("VALUER")) {
                    taskList = "and c.task_list = '" + closedBy + "'";
                } else if (branchName.equalsIgnoreCase("SURVEYOR")) {
                    taskList = "and c.task_list = '" + closedBy + "'";
                } else if (branchName.equalsIgnoreCase("APPROVAL")) {
                    taskList = "and c.task_list = '" + closedBy + "'";
                } else if (branchName.equalsIgnoreCase("ADMIN")) {
                    taskList = "and c.task_list = '" + closedBy + "'";
                }
            }
        }else{
            taskList = "";
        }
        String branchIdTxt = "";
        if (timUser==1){
            branchName = "MARKETING";
            if (branchId!=null) {
                branchIdTxt = " and c.cabang_id = " + branchId+" " ;
            }
            param = "USER_TL";
        }
        logger.info("param "+param);
        parameterMap.put("pPosisi", branchName);
        parameterMap.put("pCabangTxt", branchIdTxt);
        parameterMap.put("pPos", param);
        parameterMap.put("pUserId", taskList);
        parameterMap.put("pUserName", closedBy!=null?closedBy:(createdBy!=null?createdBy:""));
        String views = "";

        return new ModelAndView("xlsReportPending", parameterMap);

    }

    @RequestMapping(value = "report-rwa.xls", method = RequestMethod.GET)
    public ModelAndView printReportRwa(@RequestParam("createdDate") Date createdDate,@RequestParam("closedDate") Date closedDate){
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("datasource", dataSource);
        Calendar createdDateTime = Calendar.getInstance();
        createdDateTime.setTime(createdDate);
        createdDateTime.set(Calendar.HOUR_OF_DAY, 0);
        createdDateTime.set(Calendar.MINUTE, 00);
        createdDateTime.set(Calendar.SECOND, 00);
        createdDateTime.set(Calendar.MILLISECOND, 0);
        Timestamp createdDateTimestamp = new Timestamp(createdDateTime.getTimeInMillis());

        Calendar closedDateTime = Calendar.getInstance();
        closedDateTime.setTime(closedDate);
        closedDateTime.set(Calendar.HOUR_OF_DAY, 23);
        closedDateTime.set(Calendar.MINUTE, 59);
        closedDateTime.set(Calendar.SECOND, 59);
        closedDateTime.set(Calendar.MILLISECOND, 0);
        Timestamp closedDateTimestamp = new Timestamp(closedDateTime.getTimeInMillis());

        logger.info("createdDateTimestamp "+createdDateTimestamp);
        logger.info("createdDateTimestamp "+closedDateTimestamp);

        parameterMap.put("pSubmitDate", createdDateTimestamp);
        parameterMap.put("pCloseDate", closedDateTimestamp);
        return new ModelAndView("xlsReportRwa", parameterMap);
    }

    @RequestMapping(value = "report-log.xls", method = RequestMethod.GET)
    public ModelAndView printReportLog(@RequestParam("createdDate") Date createdDate,@RequestParam("closedDate") Date closedDate,
                                       @RequestParam("closedBy") String user, @RequestParam("level") String level){
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("datasource", dataSource);

        Calendar createdDateTime = Calendar.getInstance();
        createdDateTime.setTime(createdDate);
        createdDateTime.set(Calendar.HOUR_OF_DAY, 0);
        createdDateTime.set(Calendar.MINUTE, 00);
        createdDateTime.set(Calendar.SECOND, 00);
        createdDateTime.set(Calendar.MILLISECOND, 0);
        Timestamp createdDateTimestamp = new Timestamp(createdDateTime.getTimeInMillis());

        Calendar closedDateTime = Calendar.getInstance();
        closedDateTime.setTime(closedDate);
        closedDateTime.set(Calendar.HOUR_OF_DAY, 23);
        closedDateTime.set(Calendar.MINUTE, 59);
        closedDateTime.set(Calendar.SECOND, 59);
        closedDateTime.set(Calendar.MILLISECOND, 0);
        Timestamp closedDateTimestamp = new Timestamp(closedDateTime.getTimeInMillis());

        parameterMap.put("pSubmitDate", createdDateTimestamp);
        parameterMap.put("pClosedDate", closedDateTimestamp);
        logger.info("parameter "+createdDate+" "+closedDate+" "+user+" "+level);
        parameterMap.put("pUserId", user);
        parameterMap.put("pRole", level);
        return new ModelAndView("xlsReportLogs", parameterMap);
    }


    @RequestMapping(value = "survey-monitoring.xls", method = RequestMethod.GET)
    public ModelAndView rptPdaMonitoring(@RequestParam("c") Date createdDate,@RequestParam("d") Date closedDate,
                                       @RequestParam("b") String user, @RequestParam("a") String level){
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("datasource", dataSource);

        Calendar createdDateTime = Calendar.getInstance();
        createdDateTime.setTime(createdDate);
        createdDateTime.set(Calendar.HOUR_OF_DAY, 0);
        createdDateTime.set(Calendar.MINUTE, 00);
        createdDateTime.set(Calendar.SECOND, 00);
        createdDateTime.set(Calendar.MILLISECOND, 0);
        Timestamp createdDateTimestamp = new Timestamp(createdDateTime.getTimeInMillis());

        Calendar closedDateTime = Calendar.getInstance();
        closedDateTime.setTime(closedDate);
        closedDateTime.set(Calendar.HOUR_OF_DAY, 23);
        closedDateTime.set(Calendar.MINUTE, 59);
        closedDateTime.set(Calendar.SECOND, 59);
        closedDateTime.set(Calendar.MILLISECOND, 0);
        Timestamp closedDateTimestamp = new Timestamp(closedDateTime.getTimeInMillis());

        parameterMap.put("pSubmitDate", createdDateTimestamp);
        parameterMap.put("pClosedDate", closedDateTimestamp);
        logger.info("parameter "+createdDate+" "+closedDate+" "+user+" "+level);
        parameterMap.put("pUser", user!=null?user:"");
//        parameterMap.put("pRole", level);
        if (level.equalsIgnoreCase("VALUER")) {
            parameterMap.put("pUser2", user!=null?"and c.created_by = '"+user+"'":"");
            return new ModelAndView("xlsReportPdaValuer", parameterMap);
        }else if (level.equalsIgnoreCase("SURVEYOR")){
            parameterMap.put("pUser2", user!=null?"and c.svy_code = '"+user+"'":"");
            return new ModelAndView("xlsReportPdaSurveyor", parameterMap);
        }else{
            return null;
        }
    }
}
