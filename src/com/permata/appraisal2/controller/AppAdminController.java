package com.permata.appraisal2.controller;

import com.permata.appraisal2.common.*;
import com.permata.appraisal2.dto.*;
import com.permata.appraisal2.facade.DataAttactmentFacade;
import com.permata.appraisal2.facade.DataCommonFacade;
import com.permata.appraisal2.facade.FlowListFacade;
import com.permata.appraisal2.facade.KjppFacade;
import com.permata.appraisal2.model.DataCommon;
import com.permata.appraisal2.model.DataDetail;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("app-admin")
@SessionAttributes({"person", "roleActive"})
public class AppAdminController extends BaseController {

    private String POSISI = AppConstans.TaskPosition.APP_ADMIN;
    private final String DIR_VIEW = "app-admin/";

    @Autowired
    private DataCommonFacade facade;
    @Autowired
    private FlowListFacade flowListFacade;
    @Autowired
    private KjppFacade kjppFacade;

    @Autowired
    private DataAttactmentFacade facadeAttachment;

    @Autowired
    // @Qualifier("dataSource")
    private BasicDataSource dataSource;

    @Autowired
    ServletContext servletContext;

    @Autowired
    private ServletContext context;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(@RequestParam(value = "task", required = false) String task,
                       @ModelAttribute("person") PersonLoginDto person, Model model) {


        model.addAttribute("task", StringUtils.isEmpty(task) ? person.getUserId() : task);
        model.addAttribute("posisi", POSISI);
        model.addAttribute("formBean", new DataCommonTableDto());
        return DIR_VIEW + "list";
    }

    @RequestMapping(value = "find", method = RequestMethod.GET)
    @ResponseBody
    public DataTables<DataCommonDto> find(AppAdminDataCommonDto dto, @ModelAttribute("person") PersonLoginDto person,
                                          @ModelAttribute("roleActive") PersonRoleDto role,
                                          @RequestParam(value = "task") String task) {
        dto.setPosition(POSISI);
        dto.setTaskList(StringUtils.isEmpty(task) ? person.getUserId() : task);
        dto.setAppBranchId(role.getAppBranchId());
        if(!StringUtils.isEmpty(task))  dto.setSourceStatus(AppConstans.SourceStatusConstans.MARKETING_TO_ADMIN);
        dto.setCreatedBy(person.getUserId());
        DataTables<DataCommonDto> test = facade.find(dto, getPagingInfo(), getOrder()).cloneToDataTable();
        return test;
    }

    @RequestMapping(value = {"pending"}, method = RequestMethod.POST)
    public String pending(@ModelAttribute("formBean") AppAdminDataCommonDto formBean,
                          final RedirectAttributes redirectAttributes,
                          @ModelAttribute("person") PersonLoginDto person) {
        try {
            facade.pendingAdmin(formBean, formBean.getMessage(), person.getUserId());
            redirectAttributes.addFlashAttribute("flashMessage", "Pending data success");
            return "redirect:list";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("flashMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("css", "error");
            return "redirect:list";
        }
    }

    @RequestMapping(value = {"submit"}, method = RequestMethod.POST)
    public String submit(@ModelAttribute("formBean") AppAdminDataCommonDto formBean,
                         @RequestParam("sendTo") String sendTo,
                         final RedirectAttributes redirectAttributes,
                         @ModelAttribute("person") PersonLoginDto person) {
        try {
            facade.submitToValuer(formBean, formBean.getMessage(), person.getUserId(), sendTo);
            redirectAttributes.addFlashAttribute("flashMessage", "Submit data success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("flashMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("css", "error");
        }
        return "redirect:list";
    }



    @RequestMapping(value = {"updatePic"}, method = RequestMethod.POST)
    public String updatePic(@RequestParam("id") Long id, @RequestParam("picName") String picName) {
        facade.updatePic(id,picName);
        return "success-close-modal";
    }


    @RequestMapping(value = {"view"}, method = RequestMethod.GET)
    public String view(Model model, @RequestParam(value = "id") Long id,
                       @ModelAttribute("person") PersonLoginDto person) {
        AppAdminDataCommonDto formBean = facade.getByAdmin(id);

        //Cek data nomor dok yang sama...
        if(formBean.getAttactments()!=null && !formBean.getAttactments().isEmpty()){
            Map<String,String> chekLokal = new HashMap<String, String>();

                for (DataAttactmentDto attach :formBean.getAttactments()) {
                    //Check, apakah dalam 1 no order ada dokumen yang sama..
                    if (chekLokal.containsKey(attach.getName())) {
                        String alert = "Pada Order ini ada Sertifikat dengan Nomor yang Sama No Attachment: " + attach.getName();
                        model.addAttribute("msgErrorValidate", alert);
                        break;
                    } else {
                        chekLokal.put(attach.getName(), attach.getName());
                    }
                    if(StringUtils.isNotBlank(attach.getName())) {
                        List<DataDetail> dataDetails = facadeAttachment.getDataUmumByNameFile(attach.getName());
                        if (dataDetails.size() > 0) {
                            String nomorOrders = "";
                            for (int i = 0; i < dataDetails.size(); i++) {
                                DataDetail detail = dataDetails.get(i);
                                if (i > 0) nomorOrders += ", ";
                                nomorOrders += detail.getDataCommon().getOrderNumber();
                            }
                            String alert = "Ada Sertifikat dengan Nomor yang Sama pernah dijaminkan oleh debitur lain dalam kurun waktu 6 bulan terakhir. No Order: " + nomorOrders + " No Attachment: " + attach.getName();
                            model.addAttribute("msgErrorValidate", alert);
                            break;
                        }
                    }
//                    if (dataDetails.size() > 0) {
//                        Calendar startCalendar = new GregorianCalendar();
//                        startCalendar.setTime(dataDetails.getCreatedDate());
//                        Calendar endCalendar = new GregorianCalendar();
//                        endCalendar.setTime(new Date());
//                        int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
//                        int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
//                        boolean validateNameFile = (diffMonth <= 6);
//                        logger.debug("order param: " + formBean.getOrderNumber());
//                        logger.debug("order Dari DB: " + dataCommon.getOrderNumber());
//                        boolean isSame = formBean.getOrderNumber().equalsIgnoreCase(dataCommon.getOrderNumber());
//                        logger.debug("isSame: " + isSame);
//                        if (validateNameFile) {
//                            if (!isSame) {
//                                String alert = "Ada Sertifikat dengan Nomor yang Sama pernah dijaminkan oleh debitur lain dalam kurun waktu 6 bulan terakhir. No Order: " + dataCommon.getOrderNumber() + " No Attachment: " + attach.getName();
//                                model.addAttribute("msgErrorValidate", alert);
//                                break;
//                            }
//                        }
//                    }
                }

        }

        if(formBean.getDetail().getPicName()!=null){
            List<LabelValue<String, String>> namaPic = facade.getAllPic();
            String nama =formBean.getDetail().getPicName();
            for(int i=0 ; i<namaPic.size();i++){
                if (!nama.equalsIgnoreCase(namaPic.get(i).getValue())){
                    formBean.getDetail().setPicName(null);
                }else {
                    formBean.getDetail().setPicName(nama);
                    break;
                }
            }
        }

        model.addAttribute("formBean", formBean);
           if (formBean.getAppraisalTypeCategory().equalsIgnoreCase("eksternal")) {
            model.addAttribute("listKjpp", kjppFacade.findLv());
        }
        if (formBean.getTaskList().equals(person.getUserId())) {
            FlowListDto flowListDto = new FlowListDto();
            flowListDto.setFrom(POSISI);
            model.addAttribute("listFlowList", flowListFacade.list(flowListDto));
        }

        model.addAttribute("listPicName",facade.getAllPic());
//        if (formBean.getDetail()!=null){
//            if(formBean.getDetail().getPicName()==null){
//                model.addAttribute("msgErrorPic", "Nama Pic Kosong, Silahkan Lengkapi data!!");
//            }
//        }
        return DIR_VIEW + "view";
    }

    @RequestMapping(value = {"claim"}, method = RequestMethod.POST)
    public String claim(@ModelAttribute("person") PersonLoginDto person,
                        @ModelAttribute("formBean") DataCommonDto formBean,
                        final RedirectAttributes redirectAttributes) {
        try {
            facade.claimByAdmin(formBean.getId(), person.getUserId(), "Admin Appraisal", AppConstans.TaskPosition.APP_ADMIN, 2);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("flashMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("css", "error");
            return "redirect:list?task=App_Admin";
        }

        redirectAttributes.addFlashAttribute("flashMessageSuccess", "Claim data success");
        return "redirect:view?id="+formBean.getId();
        //return "redirect:list";
    }

    private DataCommonDto getDefaultFormBean(DataCommonDto formBean, PersonLoginDto person) {
        // do nothing
        return formBean;
    }

    @RequestMapping(value = {"detail"}, method = RequestMethod.GET)
    public String detail(@RequestParam("id") Long id, Model model) {
        model.addAttribute("formBean", facade.get(id));
        return DIR_VIEW + "detail";
    }

    @RequestMapping(value = "laporan-order-detail", method = RequestMethod.GET)
    public ModelAndView laporanOrderDetail(@RequestParam("PIdDataDetail") Long PIdDataDetail) {
        // TODO: 4/12/2018 conditional report here for inspeksi and others 
        // TODO: 4/12/2018 dul ini kenapa integer ya? bukan long? id kan typenya long semua dul.
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        String jasperDir = String.format("%s%s%s%s%s%s", context.getRealPath(""), File.separator, "WEB-INF",
                File.separator, "report", File.separator);
        parameterMap.put("SUBREPORT_DIR", jasperDir);
        parameterMap.put("datasource", dataSource);
        parameterMap.put("PIdDataDetail", PIdDataDetail);

        return new ModelAndView("pdfLaporanOrderDetail", parameterMap);
    }


    @RequestMapping(value = {"rejectPlus-list"}, method = RequestMethod.GET)
    public String reJectListPlus(PersonLoginDto person, Model model) {
        return DIR_VIEW + "reject-plus-list";
    }

    @RequestMapping(value = {"rejectCarm-list"}, method = RequestMethod.GET)
    public String reJectListCarm(PersonLoginDto person, Model model) {
        return DIR_VIEW + "reject-carm-list";
    }

    @RequestMapping(value = {"findRejectPlus"}, method = RequestMethod.GET)
    @ResponseBody
    public DataTables<DataCommonDto> findRejectPlus(@ModelAttribute("formSearch") SendtoSourceLogDto dto) {
        return facade.findOrderFailedRejectSource(getPagingInfo(), null).cloneToDataTable();
    }

    @RequestMapping(value = {"findRejectCarm"}, method = RequestMethod.GET)
    @ResponseBody
    public DataTables<DataCommonDto> findRejectCarm(@ModelAttribute("formSearch") SendtoSourceLogDto dto) {
        return facade.findOrderFailedRejectSourceCarm(getPagingInfo(), null).cloneToDataTable();
    }

    @RequestMapping(value = {"reject-plus"}, method = RequestMethod.POST)
    @ResponseBody
    public StatusAjaxResponse rejectPlus(@ModelAttribute("id") Long id) {
        try {
            logger.info("Data Common Id reject Plus  nya: "+id);
            facade.sendRejectPlus(id);
            return new StatusAjaxResponse(200, "Send success");
        }catch (Exception e){
            return new StatusAjaxResponse(500, e.getMessage());
        }
    }

    @RequestMapping(value = {"reject-carm"}, method = RequestMethod.POST)
    @ResponseBody
    public StatusAjaxResponse rejectCarm(@ModelAttribute("id") Long id) {
        try {
            logger.info("Data Common Id reject carm  nya: "+id);
            facade.sendRejectCarm(id);
            return new StatusAjaxResponse(200, "Send success");
        }catch (Exception e){
            return new StatusAjaxResponse(500, e.getMessage());
        }
    }
}