package com.permata.appraisal2.controller;

import com.permata.appraisal2.common.BaseController;
import com.permata.appraisal2.common.DataTables;
import com.permata.appraisal2.common.StatusAjaxResponse;
import com.permata.appraisal2.dto.AssetTypeDto;
import com.permata.appraisal2.facade.AssetTypeFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("master/asset")
public class AssetTypeController extends BaseController {

    private final String DIR_VIEW = "master/asset/";

    @Autowired
    private AssetTypeFacade facade;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model, @ModelAttribute("formBean") AssetTypeDto dto) {
        model.addAllAttributes(facade.getReference());
        return DIR_VIEW + "list";
    }

    @RequestMapping(value = "find", method = RequestMethod.GET)
    @ResponseBody
    public DataTables<AssetTypeDto> find(@ModelAttribute("formBean") AssetTypeDto dto) {
        return facade.find(dto, getPagingInfo()).cloneToDataTable();
    }

    @RequestMapping(value = {"add", "edit"}, method = RequestMethod.GET)
    public String form(Model model, @RequestParam(value = "id", required = false) Long id) {
        model.addAllAttributes(facade.getReference());
        if (id == null) {
            model.addAttribute("formBean", new AssetTypeDto());
        } else {
            model.addAttribute("formBean", facade.get(id));
        }
        return DIR_VIEW + "form";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("formBean") @Valid AssetTypeDto dto, BindingResult result, Model model) {
        model.addAllAttributes(facade.getReference());
        if (result.hasErrors()) {
            return DIR_VIEW + "form";
        }
        boolean edit = dto.getId() != null;
        dto = facade.save(dto);

        if (edit) {
            model.addAttribute("msg", "Data updated successfully!");
        } else {
            model.addAttribute("msg", "Data added successfully!");
        }
        model.addAttribute("formBean", dto);
        return DIR_VIEW + "form";
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