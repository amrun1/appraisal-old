package com.permata.appraisal2.controller;

import javax.validation.Valid;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.common.BaseController;
import com.permata.appraisal2.common.DataTables;
import com.permata.appraisal2.common.StatusAjaxResponse;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.dto.*;
import com.permata.appraisal2.facade.*;
import com.permata.appraisal2.model.DataCommon;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;

@Controller
@RequestMapping("data-umum")
@SessionAttributes({"person", "roleActive"})
public class DataCommonController extends BaseController {

    private final String DIR_VIEW = "data/umum/";
    private String POSISI = AppConstans.TaskPosition.USER_TL;

    @Autowired
    private DataCommonFacade facade;
    @Autowired
    private JenisAppraisalFacade appraisalFacade;
    @Autowired
    private AppraisalBranchFacade appraisalBranchFacade;
    @Autowired
    private BranchFacade branchFacade;
    @Autowired
    private AssetTypeFacade assetTypeFacade;
    @Autowired
    private CategoryTypeFacade categoryTypeFacade;


    @RequestMapping(value = "reappraise", method = RequestMethod.GET)
    public String reappraise(Model model) {
        model.addAttribute("formBean", new DataCommonTableDto());
        model.addAttribute("listJenisAppraisal", appraisalFacade.findLv());
        model.addAttribute("listCabangBisnis", branchFacade.findLv());
        model.addAttribute("listCabangAppraisal", appraisalBranchFacade.findLv(new AppraisalBranchDto()));
        model.addAttribute("listKategoriAktiva", categoryTypeFacade.findLv());
        model.addAttribute("listJenisAktiva", assetTypeFacade.findLvId());
        return DIR_VIEW + "reappraise";
    }

    @RequestMapping(value = "find-reappraise", method = RequestMethod.GET)
    @ResponseBody
    public DataTables<DataCommonTableDto> findReappraise(DataCommonTableDto dto) {
        dto.setSourceId("null");
        return facade.findForTables(getPagingInfo(), dto).cloneToDataTable();
    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    @ResponseBody
    public DataCommonDto get(@RequestParam("id") Long id) {
        return facade.get(id);
    }


    @RequestMapping(value = "copy-debitur", method = RequestMethod.POST)
    public String copyDebitur(@ModelAttribute("formBean") DataCommonDto dto, Long id, @ModelAttribute("person") PersonLoginDto person ,String collateralId, Long customerStatusId, Long collateralStatusId, String typeReport) {
        if(collateralId==null) {
            collateralId = "";
        }
        Long newId = facade.copyDebiturFromProcedure(id,person.getUserId(),collateralId);
        DataCommonDto newDataCommon = facade.get(newId);
        newDataCommon.setTypeReport(typeReport);
        newDataCommon.setCustomerStatusId(customerStatusId);
        newDataCommon.setCollateralStatusId(collateralStatusId);
        facade.save(newDataCommon);
        String notif = "Berhasil Copy data Debitur";
        return "redirect:form?id=" + newId+"&msgDebitur="+notif;
    }


    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(@RequestParam(value = "task", required = false) String task,
                       @ModelAttribute("person") PersonLoginDto person,
                       Model model) {
        model.addAttribute("task", StringUtils.isEmpty(task) ? person.getUserId() : task);
        model.addAttribute("formBean", new DataCommonTableDto());

        return DIR_VIEW + "list";
    }

    @RequestMapping(value = "find", method = RequestMethod.GET)
    @ResponseBody
    public DataTables<DataCommonDto> find(DataCommonDto dto, @ModelAttribute("person") PersonLoginDto person,
                                          @RequestParam(value = "task") String task) {
        Long branchId = null;
        if (person.getRoles()!=null && !person.getRoles().isEmpty()){
            for (int i = 0; i <person.getRoles().size() ; i++) {
                    if (person.getRoles().get(i).getLevel().getValue().equals("Marketing")){
                        branchId = person.getRoles().get(i).getBranchId();
                        logger.debug("Branch nya..."+branchId);
                        break;
                    }
            }
        }
        dto.setPosition(POSISI);
        dto.setTaskList(StringUtils.isEmpty(task) ? person.getUserId() : task);
        dto.setBrancId(branchId);
        return facade.find(dto, getPagingInfo(), getOrder()==null? OrderSpec.create("orderNumber", OrderSpec.OrderType.asc):getOrder()).cloneToDataTable();
    }

    private DataCommonDto getDefaultFormBean(DataCommonDto formBean, PersonLoginDto person) {
        formBean.setPosition(POSISI);
//        formBean.setSourceStatus(0);
        formBean.setMarketingTelp(person.getPhone());
        formBean.setMarketingHp(person.getHp());
        return formBean;
    }

    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String allInOne(Model model,
                           @RequestParam(value = "id", required = false) Long id,
                           @RequestParam(value = "active", required = false) Integer active,
                           @RequestParam(value = "msg", required = false) String msg,
                           @RequestParam(value = "msgErrorValidate", required = false) String msgErrorValidate,
                           @RequestParam(value = "msgDebitur", required = false) String msgDebitur,
                           @ModelAttribute("person") PersonLoginDto person,
                           @ModelAttribute("formBean") DataCommonDto formBean) {
        if (active == null) {
            active = 1;
        }
        if (id == null) {
            formBean = getDefaultFormBean(formBean, person);
        } else {
            formBean = getDefaultFormBean(facade.get(id), person);
        }
        model.addAttribute("formBean", formBean);
        if (formBean.getDetail() != null) {
            model.addAttribute("formDetail", formBean.getDetail());
        }
        model.addAttribute("msgAttach", msg);
        model.addAttribute("msgErrorValidateAttach", msgErrorValidate);
        model.addAttribute("msgCopyDebitur",msgDebitur);
        model.addAllAttributes(facade.getReference(formBean.getStatusData(), POSISI));
        model.addAttribute("active", active);
        return DIR_VIEW + "all-in-one";
    }

    @RequestMapping(value = {"add", "edit"}, method = RequestMethod.GET)
    public String form(Model model, @RequestParam(value = "id", required = false) Long id,

                       @ModelAttribute("person") PersonLoginDto person,
                       @ModelAttribute("formBean") DataCommonDto formBean) {
        logger.debug("id " + id);
        if (id == null) {
            formBean = getDefaultFormBean(formBean, person);
        } else {
            formBean = getDefaultFormBean(facade.get(id), person);
        }
        model.addAttribute("formBean", formBean);
        model.addAllAttributes(facade.getReference(formBean.getStatusData(), POSISI));
        return DIR_VIEW + "form";
    }

    @RequestMapping(value = "submit", method = RequestMethod.POST)
    public String submit(@RequestParam("id") Long id,
                         @RequestParam("sendTo") Long sendTo,
                         @RequestParam(value = "message", required = false) String message,
                         final RedirectAttributes redirectAttributes,
                         @ModelAttribute("person") PersonLoginDto person) {
        try {
            facade.submit(id, sendTo, message, person.getUserId());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("flashMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("css", "error");
            return "redirect:list";
        }
        redirectAttributes.addFlashAttribute("flashMessage", "Submit data success");
        return "redirect:list";
    }

    @RequestMapping(value = "claim", method = RequestMethod.POST)
    public String claim(@RequestParam("id") Long id,
                        final RedirectAttributes redirectAttributes,
                        @ModelAttribute("person") PersonLoginDto person) {
        try {
            facade.claim(id, person.getUserId(), "TL USER", 0);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("flashMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("css", "error");
            return "redirect:list?task="+AppConstans.TaskPosition.USER_TL;
        }
            redirectAttributes.addFlashAttribute("flashMessage", "Claim Data Success");
            return "redirect:list";
        }

    @RequestMapping(value = "reject", method = RequestMethod.POST)
    public String reject(@RequestParam("id") Long id,
                         @RequestParam("message") String message,
                         @RequestParam("rejectCategory") String rejectCategory,
                         final RedirectAttributes redirectAttributes,
                         @ModelAttribute("person") PersonLoginDto person) {
        facade.reject(id, AppConstans.TaskPosition.DROP, rejectCategory.concat(" - ").concat(message), person.getUserId());
        redirectAttributes.addFlashAttribute("flashMessage", "Reject data success");
        return "redirect:list";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("formBean") @Valid DataCommonDto dto, BindingResult result, Model model,
                       final RedirectAttributes redirectAttributes,
                       @ModelAttribute("person") PersonLoginDto person,
                       @ModelAttribute("roleActive") PersonRoleDto role) {
        // validator
        if (result.hasErrors()) {
            model.addAllAttributes(facade.getReference(dto.getStatusData(), POSISI));
            return DIR_VIEW + "all-in-one";
        }
        boolean edit = dto.getId() != null;
        // sesuaikan DTO dari form
        dto.setPosition(POSISI);
        dto.setTaskList(person.getUserId());
        if (!edit) {
            dto.setCreatedBy(person.getUserId());
            dto.setCreatedDate(new Date());
            dto.setBrancId(role.getBranchId());
//            dto.setSourceStatus(0);
        }
        // save to db
        dto = facade.save(dto);
        redirectAttributes.addFlashAttribute("css", "success");
        if (edit) {
            redirectAttributes.addFlashAttribute("msg", "Data updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("msg", "Data added successfully!");
        }
        // return "redirect:edit?id=" + dto.getId();
        return "redirect:form?id=" + dto.getId();
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public StatusAjaxResponse delete(@RequestParam("id") Long id) {
        try {
            facade.delete(id);
            return new StatusAjaxResponse(200, "Delete success");
        } catch (Exception e) {
            return new StatusAjaxResponse(500, e.getMessage());
        }
    }
}