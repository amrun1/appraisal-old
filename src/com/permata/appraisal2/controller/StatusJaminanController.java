package com.permata.appraisal2.controller;

import com.permata.appraisal2.common.BaseController;
import com.permata.appraisal2.common.DataTables;
import com.permata.appraisal2.dto.StatusJaminanDto;
import com.permata.appraisal2.facade.StatusJaminanFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("master/status-jaminan")
public class StatusJaminanController extends BaseController {

    @Autowired
    private StatusJaminanFacade facade;
    private final String DIR_VIEW = "master/status-jaminan/";

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model, @ModelAttribute("formBean") StatusJaminanDto dto) {
        return DIR_VIEW + "list";
    }

    @RequestMapping(value = "find", method = RequestMethod.GET)
    @ResponseBody
    public DataTables<StatusJaminanDto> find(@ModelAttribute("formBean") StatusJaminanDto dto) {
        return facade.find(dto, getPagingInfo()).cloneToDataTable();
    }

    @RequestMapping(value = {"add", "edit"}, method = RequestMethod.GET)
    public String form(Model model, @RequestParam(value = "id", required = false) Long id) {
        logger.debug("id " + id);
        if (id == null) {
            model.addAttribute("formBean", new StatusJaminanDto());
        } else {
            model.addAttribute("formBean", facade.get(id));
        }
        return DIR_VIEW + "form";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("formBean") StatusJaminanDto dto, BindingResult result, Model model,
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
            model.addAttribute("formBean", new StatusJaminanDto());
        }
        return DIR_VIEW + "form";
    }
}