package com.permata.appraisal2.controller;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.common.BaseController;
import com.permata.appraisal2.common.DataTables;
import com.permata.appraisal2.dto.*;
import com.permata.appraisal2.facade.DataCommonFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("master/dropData")
@SessionAttributes({"person", "roleActive"})
public class DropDataController extends BaseController {

    private final String DIR_VIEW = "master/drop-data/";


    @Autowired
    private DataCommonFacade facade;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(@RequestParam(value = "task", required = false) String task,
                       @ModelAttribute("person") PersonLoginDto person, Model model) {
        model.addAttribute("task", AppConstans.TaskPosition.DROP );
        return DIR_VIEW + "list";
    }



    @RequestMapping(value = "find", method = RequestMethod.GET)
    @ResponseBody
    public DataTables<DataCommonDto> find(AppAdminDataCommonDto dto, @ModelAttribute("person") PersonLoginDto person,
                                          @ModelAttribute("roleActive") PersonRoleDto role,
                                          @RequestParam(value = "task") String task) {
        dto.setPosition(AppConstans.TaskPosition.DROP);
        dto.setTaskList(task);
        // dto.setBranchId(role.getAppBranchId());
        return facade.find(dto, getPagingInfo(), getOrder()).cloneToDataTable();
    }



    @RequestMapping(value = {"view"}, method = RequestMethod.GET)
    public String view(Model model, @RequestParam(value = "id") Long id,
                       @ModelAttribute("person") PersonLoginDto person,
                       @ModelAttribute("formBean") AppValuerDataCommonDto formBean) {
        formBean = facade.getByValuer(id);
        model.addAttribute("formBean", formBean);

        return DIR_VIEW + "view";
    }


    @RequestMapping(value = {"claim"}, method = RequestMethod.POST)
    public String claim(@ModelAttribute("person") PersonLoginDto person,
                        @ModelAttribute("formBean") DataCommonDto formBean,
                        final RedirectAttributes redirectAttributes,
                        @ModelAttribute("roleActive") PersonRoleDto roleActive) {
        try {
            facade.reClaimDrop(formBean.getId(), person.getUserId(),null);
            redirectAttributes.addFlashAttribute("flashMessage", "Claim data success");
            return "redirect:list";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("flashMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("css", "error");
            return "redirect:list";
        }
    }

}
