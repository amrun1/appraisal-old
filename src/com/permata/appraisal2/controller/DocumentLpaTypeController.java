package com.permata.appraisal2.controller;

import com.permata.appraisal2.common.*;
import com.permata.appraisal2.dto.DocumentLpaTypeDto;
import com.permata.appraisal2.facade.AssetTypeFacade;
import com.permata.appraisal2.facade.DocumentLpaTypeFacade;
import com.permata.appraisal2.facade.JenisAppraisalFacade;
import com.permata.appraisal2.facade.ParameterFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("master/jenis-dokumen-lpa")
public class DocumentLpaTypeController extends BaseController {

    private final String DIR_VIEW = "master/jenis-dokumen-lpa/";

    @Autowired
    private DocumentLpaTypeFacade facade;
    @Autowired
    private AssetTypeFacade assetTypeFacade;
    @Autowired
    private JenisAppraisalFacade appraisalFacade;
    @Autowired
    private ParameterFacade parameterFacade;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(@ModelAttribute("formBean") DocumentLpaTypeDto dto, Model model, @RequestParam(value = "jenis", required = false) Integer jenis) {
        initReference(model);
        model.addAttribute("jenis", jenis);
        return DIR_VIEW + "list";
    }

    private void initReference(Model model) {
        model.addAttribute("listAppraisal", appraisalFacade.findLv());
        model.addAttribute("listAktiva", assetTypeFacade.findLvId());

    }

    @RequestMapping(value = "find", method = RequestMethod.GET)
    @ResponseBody
    public DataTables<DocumentLpaTypeDto> find(@ModelAttribute("formBean") DocumentLpaTypeDto dto, @RequestParam(value = "jenis", required = false) Integer jenis) {
        DataTables<DocumentLpaTypeDto> test = facade.find(dto, getPagingInfo(), jenis).cloneToDataTable();
        return facade.find(dto, getPagingInfo(), jenis).cloneToDataTable();
    }

    @RequestMapping(value = {"add", "edit"}, method = RequestMethod.GET)
    public String form(Model model, @RequestParam(value = "id", required = false) Long id, @RequestParam(value = "jenis", required = false) Integer jenis) {

        Map<String, Object> jenisData = new HashMap<String, Object>();
        List<LabelValue<String, String>> listJenisData = new ArrayList<LabelValue<String, String>>();
        listJenisData.add(new LabelValue<String, String>("Foto Survey", "Foto Survey (Image)")); //lo edit sendiri Label nya mo kya gmn
        listJenisData.add(new LabelValue<String, String>("Lain-Lain", "Lain-Lain (Image, Pdf, Tiff)"));

        if (jenis == 2){
            listJenisData.add(new LabelValue<String, String>("Peta/Denah", "Peta/Denah (Image)"));
            listJenisData.add(new LabelValue<String, String>("Worksheet", "Worksheet (Excel)"));
        }
        listJenisData.add(new LabelValue<String, String>("Dokumen Pelengkap", "Dokumen Pelengkap (Image, Pdf)"));

        jenisData.put("jenisData",listJenisData);
        model.addAttribute("jenis", jenis);
        model.addAllAttributes(assetTypeFacade.findLvIdForm());
        model.addAllAttributes(appraisalFacade.findLvForm());
        model.addAllAttributes(jenisData);

        if (id == null) {
            DocumentLpaTypeDto empty =    new DocumentLpaTypeDto();
            empty.setSurveyYn(true);
            model.addAttribute("formBean", empty);
        } else {
            model.addAttribute("formBean", facade.get(id));
        }

        return DIR_VIEW + "form";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("formBean") @Valid DocumentLpaTypeDto dto, BindingResult result, Model model, @RequestParam(value = "jenis", required = false) Integer jenis) {
        if (result.hasErrors()) {
            return DIR_VIEW + "form";
        }
        boolean edit = dto.getId() != null;
        if (dto.getJenisData().equalsIgnoreCase("Worksheet")){
            dto.setReadFileYn(true);
        }
        dto = facade.save(dto);

        if (edit) {
            model.addAttribute("msg", "Data updated successfully!");
        } else {
            model.addAttribute("msg", "Data added successfully!");
        }
//        return "success-close-modal";
//        model.addAttribute("formBean", dto);
        model.addAttribute("jenis", jenis);
//        return DIR_VIEW + "list";
        return "redirect:list";
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