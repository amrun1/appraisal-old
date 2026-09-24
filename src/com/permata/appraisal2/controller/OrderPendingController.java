package com.permata.appraisal2.controller;

import com.permata.appraisal2.common.BaseController;
import com.permata.appraisal2.common.DataTables;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.dto.AppValuerDataCommonDto;
import com.permata.appraisal2.dto.DataCommonDto;
import com.permata.appraisal2.dto.PersonLoginDto;
import com.permata.appraisal2.dto.PersonRoleDto;
import com.permata.appraisal2.facade.DataCommonFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by Bachri on 1/17/2017.
 */
@Controller
@RequestMapping("master/order-pending")
@SessionAttributes({"person", "roleActive"})
public class OrderPendingController extends BaseController {

    @Autowired
    private DataCommonFacade facade;

    private final String DIR_VIEW = "master/order-pending/";

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model, @ModelAttribute("formBean") DataCommonDto dto) {
        return DIR_VIEW + "list";
    }

    @RequestMapping(value = "find", method = RequestMethod.GET)
    @ResponseBody
    public DataTables<DataCommonDto> find(Model model, @ModelAttribute("formBean") DataCommonDto dto) {
        logger.info("param posisi "+dto.getPosition());
        return facade.findOrderPending(dto, getPagingInfo(), OrderSpec.create("orderNumber", OrderSpec.OrderType.asc)).cloneToDataTable();

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
        logger.info("formBean.getPosition() "+formBean.getPosition());
        logger.info("formBean.getId() "+formBean.getId());
        logger.info("person.getUserId() "+person.getUserId());
        try {
            facade.reClaimOrderPending(formBean.getId(), person.getUserId());
            redirectAttributes.addFlashAttribute("flashMessage", "Claim data success");
            return "redirect:list";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("flashMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("css", "error");
            return "redirect:list";
        }

    }

    @RequestMapping(value = {"add", "edit"}, method = RequestMethod.GET)
    public String form(Model model, @RequestParam(value = "id", required = false) Long id) {
//        logger.debug("id " + id);
//        if (id == null) {
//            model.addAttribute("formBean", new DataCommonDto());
//        } else {
//            model.addAttribute("formBean", facade.get(id));
//        }
        return DIR_VIEW + "form";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("formBean") @Valid DataCommonDto dto, BindingResult result, Model model,
                       final RedirectAttributes redirectAttributes) {


        // validator
        if (result.hasErrors()) {
            return DIR_VIEW + "form";
        }
//        boolean edit = dto.getId() != null;
//        // save to db
//        dto = facade.save(dto);
//        logger.debug("formBean : " + dto);
//
//        redirectAttributes.addFlashAttribute("css", "success");
//        if (edit) {
//            redirectAttributes.addFlashAttribute("msg", "Data updated successfully!");
//            model.addAttribute("msg", "Data updated successfully!");
//        } else {
//            redirectAttributes.addFlashAttribute("msg", "Data added successfully!");
//            model.addAttribute("msg", "Data added successfully!");
//            // reset form bean to new
//        }
//
//        model.addAttribute("formBean", dto);

        return DIR_VIEW + "list";
    }

}
