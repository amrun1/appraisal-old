package com.permata.appraisal2.controller;

import com.permata.appraisal2.common.BaseController;
import com.permata.appraisal2.common.DataTables;
import com.permata.appraisal2.dto.IlonDto;
import com.permata.appraisal2.facade.IloanFacade;
import com.permata.appraisal2.facade.PriorityCategoryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("iloan")
public class IloanController extends BaseController {

    @Autowired
    private IloanFacade facade;

    @Autowired
    private PriorityCategoryFacade priorityCategoryFacade;



    @RequestMapping(value = "lookup", method = RequestMethod.GET)
    public String lookup(Model model,@ModelAttribute("formSearch") IlonDto dto) {
        model.addAllAttributes(priorityCategoryFacade.getReference());
        return "ilon/lookup";
    }



    @RequestMapping(value = "find-lookup", method = RequestMethod.GET)
    @ResponseBody
    public DataTables<IlonDto> find(@ModelAttribute("formSearch") IlonDto dto) {
        return facade.lookup(getPagingInfo(), dto, getOrder()).cloneToDataTable();
    }
}