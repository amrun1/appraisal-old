package com.permata.appraisal2.controller;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.common.BaseController;
import com.permata.appraisal2.common.DataTables;
import com.permata.appraisal2.common.StatusAjaxResponse;
import com.permata.appraisal2.dto.*;
import com.permata.appraisal2.facade.*;
import com.permata.appraisal2.model.DataCommon;
import com.permata.appraisal2.model.DataDetail;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@SessionAttributes({"person", "roleActive"})
@RequestMapping("rwa")
public class RwaController extends BaseController {

    private final String DIR_VIEW = "ilon/";

    @Autowired
    private IloanFacade facade;
    @Autowired
    private PriorityCategoryFacade priorityCategoryFacade;
    @Autowired
    private DataCommonFacade dataCommonFacade;


    @Autowired
    private FlowListFacade flowListFacade;
    @Autowired
    private KjppFacade kjppFacade;

    @Autowired
    private DataAttactmentFacade facadeAttachment;



    @RequestMapping(value = {"re-send-list"}, method = RequestMethod.GET)
    public String reSendList(PersonLoginDto person, Model model) {
        model.addAllAttributes(priorityCategoryFacade.getReference());
        return DIR_VIEW + "re-send-list";
    }


    @RequestMapping(value = {"send-manual-list"}, method = RequestMethod.GET)
    public String sendManualList(PersonLoginDto person, Model model) {
        model.addAllAttributes(priorityCategoryFacade.getReference());
        return DIR_VIEW + "send-manual-list";
    }

    @RequestMapping(value = {"find"}, method = RequestMethod.GET)
    @ResponseBody
    public DataTables<IloanProgresDto> find(@ModelAttribute("formSearch") IloanProgresDto dto) {
        return facade.findHead(getPagingInfo(), dto).cloneToDataTable();
    }

    @RequestMapping(value = {"re-send-find"}, method = RequestMethod.GET)
    @ResponseBody
    public DataTables<IloanProgresDto> reSendFind(@ModelAttribute("formSearch") IloanProgresDto dto) {
        return facade.findHead(getPagingInfo(), dto).cloneToDataTable();
    }

    @RequestMapping(value = {"re-send"}, method = RequestMethod.POST)
    @Transactional
    public String reSend(@ModelAttribute("id") Long id) {
        logger.info("Kirim RWA Resend: "+id);
        dataCommonFacade.sendToRwa(id);
        return "redirect:re-send-list";
    }


    @RequestMapping(value = {"re-send-Col"}, method = RequestMethod.POST)
    @Transactional
    public String reSendc(@ModelAttribute("id") Long id,@ModelAttribute("collateralId") String collateralId) {


       DataCommonDto d = dataCommonFacade.get(id);
       d.setCollateralId(collateralId);
       dataCommonFacade.save(d);

        dataCommonFacade.sendToRwa(id);
        return "redirect:listResendByCol";
    }



    @RequestMapping(value = {"manual-send-Col"}, method = RequestMethod.POST)
    @Transactional
    public String manualSendC(@ModelAttribute("id") Long id,@ModelAttribute("collateralId") String collateralId) {
        DataCommonDto d = dataCommonFacade.get(id);
        d.setCollateralId(collateralId);
        dataCommonFacade.save(d);
        facade.sendManualCol(id);
        return "redirect:re-send-list";
    }



    @RequestMapping(value = {"manual-send"}, method = RequestMethod.POST)
    @Transactional
    public String manualSend(@ModelAttribute("id") Long id) {
        facade.sendManual(id);
        return "redirect:re-send-list";
    }


//------------------Colleteral Id null----

    @RequestMapping(value = "listResendByCol", method = RequestMethod.GET)
    public String listCol(@RequestParam(value = "task", required = false) String task,
                       @ModelAttribute("person") PersonLoginDto person, Model model) {
        model.addAttribute("task", AppConstans.TaskPosition.CLOSE);
        model.addAttribute("posisi", AppConstans.TaskPosition.CLOSE);
        return DIR_VIEW + "resend-coleteral-list";
    }


    @RequestMapping(value = "findColleteral", method = RequestMethod.GET)
    @ResponseBody
    public DataTables<DataCommonDto> findCol(AppAdminDataCommonDto dto, @ModelAttribute("person") PersonLoginDto person,
                                          @ModelAttribute("roleActive") PersonRoleDto role,
                                          @RequestParam(value = "task") String task) {
        dto.setPosition(AppConstans.TaskPosition.CLOSE);
        dto.setTaskList(task);
        return dataCommonFacade.findColNull(dto, getPagingInfo(), getOrder()).cloneToDataTable();
    }



    @RequestMapping(value = {"viewByCol"}, method = RequestMethod.GET)
    public String viewCol(Model model, @RequestParam(value = "id") Long id,
                       @ModelAttribute("person") PersonLoginDto person) {
        AppAdminDataCommonDto formBean = dataCommonFacade.getByAdmin(id);

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
                    }
                }
            }

        }

        model.addAttribute("formBean", formBean);
        if (formBean.getAppraisalTypeName().equalsIgnoreCase("eksternal")) {
            model.addAttribute("listKjpp", kjppFacade.findLv());
        }
        if (formBean.getTaskList().equals(person.getUserId())) {
            FlowListDto flowListDto = new FlowListDto();
            flowListDto.setFrom("CLOSE");
            model.addAttribute("listFlowList", flowListFacade.list(flowListDto));
        }

        model.addAttribute("listPicName",dataCommonFacade.getAllPic());
        if (formBean.getDetail()!=null){
            if(formBean.getDetail().getPicName()==null){
                model.addAttribute("msgErrorPic", "Nama Pic Kosong, Silahkan Lengkapi data!!");
            }
        }
        return DIR_VIEW + "view-Resend-col";
    }



    @RequestMapping(value = {"viewDetailColet"}, method = RequestMethod.GET)
    public String detail(@RequestParam("id") Long id, Model model) {
        model.addAttribute("formBean", dataCommonFacade.get(id));
        return DIR_VIEW + "detail-resend-coleteral";
    }





//---------------------------Plus-------------
    @RequestMapping(value = {"re-sendPlus-list"}, method = RequestMethod.GET)
    public String reSendListPlus(PersonLoginDto person, Model model) {
        return DIR_VIEW + "re-send-plus-list";
    }



    @RequestMapping(value = {"re-sendCarm-list"}, method = RequestMethod.GET)
    public String reSendListCarm(PersonLoginDto person, Model model) {
        return DIR_VIEW + "re-send-carm-list";
    }

    @RequestMapping(value = {"findPlus"}, method = RequestMethod.GET)
    @ResponseBody
    public DataTables<SendtoSourceLogDto> findPlus(@ModelAttribute("formSearch") SendtoSourceLogDto dto) {
        return facade.findPlus(getPagingInfo(), dto).cloneToDataTable();
    }



    @RequestMapping(value = {"re-send-plus"}, method = RequestMethod.POST)
    @ResponseBody
    public StatusAjaxResponse reSendPlus(@ModelAttribute("id") Long id) {
        try {
            logger.info("Data Common Id Plus  nya: "+id);
            dataCommonFacade.sendToPlus(id);
            return new StatusAjaxResponse(200, "Send success");
        }catch (Exception e){
            return new StatusAjaxResponse(500, e.getMessage());
        }


       // return "redirect:re-send-plus-list";
    }



    @RequestMapping(value = {"re-send-carm"}, method = RequestMethod.POST)
    @Transactional
    public String reSendCarm(@ModelAttribute("id") Long id) {

        logger.info("Data Common Id Carm nya: "+id);
        dataCommonFacade.sendToCarm(id);
        return "redirect:re-send-carm-list";
    }
}