package com.permata.appraisal2.controller;

import com.permata.appraisal2.common.BaseController;
import com.permata.appraisal2.common.DataTables;
import com.permata.appraisal2.common.StatusAjaxResponse;
import com.permata.appraisal2.dto.HolidayDto;
import com.permata.appraisal2.facade.HolidayFacade;
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
@RequestMapping("master/holiday")
public class HolidayController extends BaseController {

    @Autowired
    private HolidayFacade facade;

    private final String DIR_VIEW = "master/holiday/";

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model, @ModelAttribute("formBean") HolidayDto dto) {
        return DIR_VIEW + "list";
    }

    @RequestMapping(value = "find", method = RequestMethod.GET)
    @ResponseBody
    public DataTables<HolidayDto> find(Model model, @ModelAttribute("formBean") HolidayDto dto) {
        return facade.find(dto, getPagingInfo()).cloneToDataTable();
    }

    @RequestMapping(value = {"add", "edit"}, method = RequestMethod.GET)
    public String form(Model model, @RequestParam(value = "id", required = false) Long id) {
        logger.debug("id " + id);
        if (id == null) {
            model.addAttribute("formBean", new HolidayDto());
        } else {
            model.addAttribute("formBean", facade.get(id));
        }
        return DIR_VIEW + "form";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("formBean") @Valid HolidayDto dto, BindingResult result, Model model,
                       final RedirectAttributes redirectAttributes) {


        // validator
        if (result.hasErrors()) {
            return DIR_VIEW + "form";
        }
        if(!facade.findDuplicate(dto)) {
            result.rejectValue("tanggal", "", "Tanggal Sudah Terdaftar");
            return DIR_VIEW + "form";
        }
        boolean edit = dto.getId() != null;
        // save to db
        dto = facade.save(dto);
        logger.debug("formBean : " + dto);

        redirectAttributes.addFlashAttribute("css", "success");
        if (edit) {
            redirectAttributes.addFlashAttribute("msg", "Data updated successfully!");
            model.addAttribute("msg", "Data updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("msg", "Data added successfully!");
            model.addAttribute("msg", "Data added successfully!");
            // reset form bean to new
        }

        model.addAttribute("formBean", dto);

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
