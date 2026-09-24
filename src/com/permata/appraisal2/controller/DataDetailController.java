package com.permata.appraisal2.controller;

import com.permata.appraisal2.common.BaseController;
import com.permata.appraisal2.dto.DataCommonDto;
import com.permata.appraisal2.dto.DataDetailDto;
import com.permata.appraisal2.dto.PersonLoginDto;
import com.permata.appraisal2.facade.DataAttactmentFacade;
import com.permata.appraisal2.facade.DataCommonFacade;
import com.permata.appraisal2.facade.DataDetailFacade;
import com.permata.appraisal2.facade.ZipCodeFacade;
import com.permata.appraisal2.model.DataCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("data-detail")
@SessionAttributes("person")
public class DataDetailController extends BaseController {

    private final String DIR_VIEW = "data/detail/";

    @Autowired
    private DataCommonFacade commonFacade;
    @Autowired
    private DataDetailFacade facade;

//    @Autowired
//    private DataAttactmentFacade dataAttactmentFacade;

    @RequestMapping(value = {"add", "edit"}, method = RequestMethod.GET)
    public String form(Model model, @RequestParam(value = "id", required = false) Long id,
                       @RequestParam(value = "dataCommonId") Long dataCommonId,
                       @ModelAttribute("person") PersonLoginDto person,
                       @ModelAttribute("formBean") DataDetailDto formBean) {
        DataCommonDto umum =commonFacade.get(dataCommonId);
        if (id != null) {
            formBean = facade.get(id);
        } else if(umum !=null) {
            formBean = getDefaultFormBean(umum);
        } else {
            formBean = getDefaultFormBean(dataCommonId);
        }
        model.addAttribute("formBean", formBean);


        boolean isGroup = false;
        if (umum.getPosition().equals("User_TL") && umum.getTaskList().equals("User_TL")){
            isGroup = true;
        }


        model.addAllAttributes(facade.getReference());
        model.addAttribute("isGr",isGroup);
        return DIR_VIEW + "form";
    }



    @RequestMapping(value = "deleteDetail", method = RequestMethod.GET)
    public String del(@ModelAttribute("id") Long id, BindingResult result, Model model,
                       final RedirectAttributes redirectAttributes) {


        try {
            facade.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success-refresh-parent";
    }



    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("formBean") @Valid DataDetailDto dto, BindingResult result, Model model,
                       final RedirectAttributes redirectAttributes, @ModelAttribute("person") PersonLoginDto person) {

        // validator
        if (result.hasErrors()) {
            model.addAllAttributes(facade.getReference());
            return DIR_VIEW + "form";
        }

        dto.validate(result);
        if(result.hasErrors()){
            model.addAllAttributes(facade.getReference());
            return DIR_VIEW + "form";
        }

        boolean edit = dto.getId() != null;
        // save to db
        facade.save(dto);
        redirectAttributes.addFlashAttribute("css", "success");
        if (edit) {
            redirectAttributes.addFlashAttribute("msg", "Data Detail updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("msg", "Data Detail added successfully!");
        }
        // return "redirect:/data-umum/edit?id=" + dto.getDataCommonId() + "#anchor-detail";
        return "success-refresh-parent";
    }

    private DataDetailDto getDefaultFormBean(Long dataCommonId) {
        DataCommonDto commonDto = commonFacade.get(dataCommonId);
        DataDetailDto formBean = new DataDetailDto();
        formBean.setDataCommonId(dataCommonId);
        formBean.setIdJenisAppraisal(commonDto.getAppraisalTypeId());
        formBean.setJenisAppraisal(commonDto.getAppraisalTypeName());
        formBean.setJenisLaporan(commonDto.getTypeReport());
        return formBean;
    }
    private DataDetailDto getDefaultFormBean(DataCommonDto dataCommon) {
        DataDetailDto formBean = new DataDetailDto();
        formBean.setDataCommonId(dataCommon.getId());
        formBean.setIdJenisAppraisal(dataCommon.getAppraisalTypeId());
        formBean.setJenisAppraisal(dataCommon.getAppraisalTypeName());
        formBean.setJenisLaporan(dataCommon.getTypeReport());
        return formBean;
    }
}