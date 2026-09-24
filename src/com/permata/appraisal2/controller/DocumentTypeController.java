package com.permata.appraisal2.controller;

import com.permata.appraisal2.common.BaseController;
import com.permata.appraisal2.common.DataTables;
import com.permata.appraisal2.common.StatusAjaxResponse;
import com.permata.appraisal2.dto.DocumentTypeDto;
import com.permata.appraisal2.facade.DocumentTypeFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("master/jenis-dokumen")
public class DocumentTypeController extends BaseController {

    private final String DIR_VIEW = "master/jenis-dokumen/";

    @Autowired
    private DocumentTypeFacade facade;

    @RequestMapping(value = "find", method = RequestMethod.GET)
    @ResponseBody
    public DataTables<DocumentTypeDto> find(@ModelAttribute("formBean") DocumentTypeDto dto) {
        return facade.find(dto, getPagingInfo()).cloneToDataTable();
    }

    @RequestMapping(value = {"add", "edit"}, method = RequestMethod.GET)
    public String form(Model model, @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "jenisAktiva") Long jenisAktiva) {
        if (id == null) {
            model.addAttribute("formBean", new DocumentTypeDto(jenisAktiva));
        } else {
            model.addAttribute("formBean", facade.get(id));
        }
        return DIR_VIEW + "form";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("formBean") @Valid DocumentTypeDto dto, BindingResult result, Model model) {
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
        return "success-close-modal";
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