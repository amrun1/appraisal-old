package com.permata.appraisal2.controller;

import com.permata.appraisal2.common.BaseController;
import com.permata.appraisal2.common.DataTables;
import com.permata.appraisal2.common.StatusAjaxResponse;
import com.permata.appraisal2.dto.AppraisalBranchDto;
import com.permata.appraisal2.dto.PersonDto;
import com.permata.appraisal2.dto.PersonRoleDto;
import com.permata.appraisal2.facade.AppraisalBranchFacade;
import com.permata.appraisal2.facade.BranchFacade;
import com.permata.appraisal2.facade.PersonFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("admin/cabang-marketing")
public class BranchMarketingController extends BaseController {

    @Autowired
    private BranchFacade facade;
    @Autowired
    private PersonFacade facadePerson;
    private final String DIR_VIEW = "admin/cabang-marketing/";

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model, @ModelAttribute("formBean") AppraisalBranchDto dto) {
//        PagingInfo pagingInfo = getPagingInfo();
//        logger.debug("pagingInfo -> " + pagingInfo);
//        PaginatedList<AppraisalBranchDto> pgList = facade.find(new AppraisalBranchDto(), pagingInfo);
//        model.addAttribute("dataList", pgList.getDataList());
//        model.addAttribute("pgList" + pgList);
        return DIR_VIEW + "list";
    }

    @RequestMapping(value = "find", method = RequestMethod.GET)
    @ResponseBody
    public DataTables<AppraisalBranchDto> find(@ModelAttribute("formBean") AppraisalBranchDto dto) {
        logger.debug("DTO : " + dto);
        logger.debug("pagingInfo : " + getPagingInfo());
        return facade.find(dto, getPagingInfo()).cloneToDataTable();
    }

    @RequestMapping(value = "find-person", method = RequestMethod.GET)
    @ResponseBody
    public DataTables<PersonDto> findPerson(@RequestParam(value = "id") Long id) {
        return facadePerson.findByBranch(id, getPagingInfo()).cloneToDataTable();
    }

    @RequestMapping(value = {"add", "edit"}, method = RequestMethod.GET)
    public String form(Model model, @RequestParam(value = "id", required = false) Long id) {
        logger.debug("id " + id);
        if (id == null) {
            model.addAttribute("isEdit",false);
            model.addAttribute("formBean", new AppraisalBranchDto());
        } else {
            Integer jml = facadePerson.findByBranch(id, getPagingInfo()).getDataList().size();

            Integer jj = facade.getAll().size();

            List<AppraisalBranchDto> list = facade.getAll();

            for (int i = 0; i<list.size(); i++){
                logger.info("namas " +list.get(i).getName());
            }


            logger.info("jumlah jj "+jj);

            model.addAttribute("j", facade.getAll());
            model.addAttribute("jmlh", jml);
            model.addAttribute("isEdit",true);
            model.addAttribute("formBean", facade.get(id));
        }
        return DIR_VIEW + "form";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("formBean") @Valid AppraisalBranchDto dto, BindingResult result, Model model,
                       final RedirectAttributes redirectAttributes) {

        // validator
        if (result.hasErrors()) {
            return DIR_VIEW + "form";
        }

        boolean edit = dto.getId() != null;

        // save to db
        dto = facade.save(dto);
        logger.debug("formBean : " + dto);

        redirectAttributes.addFlashAttribute("css", "success");
        if (edit) {
            redirectAttributes.addFlashAttribute("msg", "Data updated successfully!"); // when using redirect
            model.addAttribute("msg", "Data updated successfully!");
            model.addAttribute("formBean", dto);
        } else {
            redirectAttributes.addFlashAttribute("msg", "Data added successfully!"); // when using redirect
            model.addAttribute("msg", "Data added successfully!");
            // reset form bean to new
            model.addAttribute("formBean", new AppraisalBranchDto());
        }
        return DIR_VIEW + "list";
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