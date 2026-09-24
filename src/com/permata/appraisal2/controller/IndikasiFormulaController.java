package com.permata.appraisal2.controller;

import com.permata.appraisal2.common.BaseController;
import com.permata.appraisal2.common.DataTables;
import com.permata.appraisal2.dto.IndikasiFormulaDto;
import com.permata.appraisal2.facade.IndikasiFormulaFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("master/Indikasi")
public class IndikasiFormulaController extends BaseController {

    private final String DIR_VIEW = "master/indikasi/";

    @Autowired
    private IndikasiFormulaFacade facade;



    @RequestMapping(value = "find", method = RequestMethod.GET)
    @ResponseBody
    public DataTables<IndikasiFormulaDto> find(@ModelAttribute("formSearch") IndikasiFormulaDto dto) {
        return facade.find(dto, getPagingInfo()).cloneToDataTable();
    }


    @RequestMapping(value = {"add", "edit"}, method = RequestMethod.GET)
    public String form(Model model, @RequestParam(value = "code", required = false) Long code) {

        List<String> type = new ArrayList<String>();
        type.add("Ruko");
        type.add("Rumah Tinggal");
        type.add("Villa Cottage");
        model.addAttribute("listType", type);
        if (code == null) {
            model.addAttribute("formBean", new IndikasiFormulaDto());
        } else {
            model.addAttribute("formBean", facade.get(code));
        }
        return DIR_VIEW + "form";
    }



    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("formBean") @Valid IndikasiFormulaDto dto, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return DIR_VIEW + "form";
        }
        boolean edit = dto.getCode() != null;
        dto = facade.save(dto);
        if (edit) {
            redirectAttributes.addFlashAttribute("msg", "Data updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("msg", "Data added successfully!");
        }
        return "redirect:list";
    }



    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model, @ModelAttribute("formBean") IndikasiFormulaDto dto) {
        return DIR_VIEW + "list";
    }


}
