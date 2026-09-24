package com.permata.appraisal2.controller;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.common.BaseController;
import com.permata.appraisal2.common.DataTables;
import com.permata.appraisal2.dto.*;
import com.permata.appraisal2.facade.DataCommonFacade;
import com.permata.appraisal2.facade.DataSurveyFacade;
import com.permata.appraisal2.facade.FlowListFacade;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("app-surveyor")
@SessionAttributes({"person", "roleActive"})
public class AppSurveyorController extends BaseController {

    private String POSISI = AppConstans.TaskPosition.APP_SURVEYOR;
    private final String DIR_VIEW = "app-survey/";

    @Autowired
    private DataCommonFacade facade;
    @Autowired
    private FlowListFacade flowListFacade;

    @Autowired
    // @Qualifier("dataSource")
    private BasicDataSource dataSource;

    @Autowired
    private ServletContext context;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(@RequestParam(value = "task", required = false) String task,
                       @ModelAttribute("person") PersonLoginDto person, Model model) {
        model.addAttribute("task", StringUtils.isEmpty(task) ? person.getUserId() : task);
        return DIR_VIEW + "list";
    }

    @RequestMapping(value = "find", method = RequestMethod.GET)
    @ResponseBody
    public DataTables<DataCommonDto> find(DataCommonDto dto, @ModelAttribute("person") PersonLoginDto person,
                                          @ModelAttribute("roleActive") PersonRoleDto role,
                                          @RequestParam(value = "task") String task) {
        dto.setPosition(POSISI);
        dto.setTaskList(StringUtils.isEmpty(task) ? person.getUserId() : task);
        dto.setAppBranchId(role.getAppBranchId());

        dto.setCreatedBy(person.getUserId());
        dto.setAdminBy(person.getUserId());
        dto.setValuerBy(person.getUserId());


        return facade.find(dto, getPagingInfo(), getOrder()).cloneToDataTable();
    }

    @RequestMapping(value = {"view"}, method = RequestMethod.GET)
    public String view(Model model, @RequestParam(value = "id") Long id,
                       @ModelAttribute("person") PersonLoginDto person,
                       @ModelAttribute("formBean") AppSurveyDataCommonDto formBean) {
        AppValuerDataCommonDto e = facade.getByValuer(id);
        BeanUtils.copyProperties(e, formBean);
        model.addAttribute("formBean", formBean);
        if (formBean.getTaskList().equals(person.getUserId())) {
            FlowListDto flowListDto = new FlowListDto();
            flowListDto.setFrom(POSISI);
            model.addAttribute("listFlowList", flowListFacade.list(flowListDto));
        }
        return DIR_VIEW + "view";
    }

    @RequestMapping(value = {"claim"}, method = RequestMethod.POST)
    public String claim(@ModelAttribute("person") PersonLoginDto person,
                        @ModelAttribute("formBean") AppValuerDataCommonDto formBean,
                        final RedirectAttributes redirectAttributes) {
        logger.debug("formBean : " + formBean);
        try {
            facade.claim(formBean.getId(), person.getUserId(), "Surveyor Appraisal", 6);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("flashMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("css", "error");
            return "redirect:list?task="+AppConstans.TaskPosition.APP_SURVEYOR;
        }
        redirectAttributes.addFlashAttribute("flashMessage", "Claim data success");
        return "redirect:list";
    }

    @RequestMapping(value = "detail-survey", method = RequestMethod.GET)
    public String survey(Model model, @RequestParam(value = "id") Long id, @RequestParam(value = "comminId") Long comminId) {
        model.addAttribute("comminId", comminId);
        model.addAttribute("formBean", facade.getByValuer(comminId));
        return DIR_VIEW + "detail-survey";
    }

    @RequestMapping(value = {"submit"}, method = RequestMethod.POST)
    public String submit(@ModelAttribute("formBean") DataCommonDto formBean,
                         @RequestParam("sendTo") String sendTo,
                         final RedirectAttributes redirectAttributes,
                         @ModelAttribute("person") PersonLoginDto person) {
            try {
                facade.submitSurveyToValuer(formBean, formBean.getMessage(), person.getUserId(), sendTo);
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("flashMessage", e.getMessage());
                redirectAttributes.addFlashAttribute("css", "error");
                return "redirect:list";
            }
            redirectAttributes.addFlashAttribute("flashMessage", "Submit data success");
        return "redirect:list";
    }

    @RequestMapping (value = "laporan-survey", method = RequestMethod.GET)
    public ModelAndView laporanSurvey(@RequestParam ("PIdDataDetail") Long PIdDataDetail, @RequestParam ("survey") String survey){
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        String jasperDir = String.format("%s%s%s%s%s%s", context.getRealPath(""), File.separator, "WEB-INF",
                File.separator, "report", File.separator);
        parameterMap.put("SUBREPORT_DIR", jasperDir);
        parameterMap.put("datasource", dataSource);
        parameterMap.put("PIdDataDetail", PIdDataDetail);
        if(survey.equalsIgnoreCase("lnb")) {
            return new ModelAndView("pdfLaporanSurvey", parameterMap);
        }else if (survey.equalsIgnoreCase("kendaraan")){
            return new ModelAndView("pdfLaporanSurveyKendaraan", parameterMap);
        }else if(survey.equalsIgnoreCase("order")){
            return new ModelAndView("pdfLaporanOrderDetail", parameterMap);
        }else if(survey.equalsIgnoreCase("inspeksi")){
            return new ModelAndView("pdfLaporanSurveyInspeksi", parameterMap);
        }else if(survey.equalsIgnoreCase("stock")){
            return new ModelAndView("pdfLaporanSurveyStock", parameterMap);
        }else{
            return null;
        }
    }

    @RequestMapping(value = "list-error", method = RequestMethod.GET)
    public String errorSavingBackToList(@RequestParam(value = "task", required = false) String task,
                                        @ModelAttribute("person") PersonLoginDto person, Model model) {
        model.addAttribute("flashMessage", "Gagal : Posisi Order Tidak Pada Tasklist Anda");
        model.addAttribute("css", "error");
        model.addAttribute("task", StringUtils.isEmpty(task) ? person.getUserId() : task);
        model.addAttribute("formBean", new AppSurveyDataCommonDto());
        return DIR_VIEW + "list";
    }

}