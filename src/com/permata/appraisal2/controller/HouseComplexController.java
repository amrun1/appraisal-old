package com.permata.appraisal2.controller;

import com.permata.appraisal2.common.BaseController;
import com.permata.appraisal2.common.DataTables;
import com.permata.appraisal2.dto.HouseComplexDto;
import com.permata.appraisal2.facade.HouseComplexFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("master/house-complex")
public class HouseComplexController extends BaseController {

    @Autowired
    private HouseComplexFacade facade;

    @RequestMapping("lookup")
    public String lookup(@ModelAttribute("formSearch") HouseComplexDto dto) {
        return "master/house-complex/lookup";
    }

    @RequestMapping(value = "find", method = RequestMethod.GET)
    @ResponseBody
    public DataTables<HouseComplexDto> find(@ModelAttribute("formSearch") HouseComplexDto dto) {
        return facade.find(dto, getPagingInfo()).cloneToDataTable();
    }

}