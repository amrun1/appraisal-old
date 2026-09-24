package com.permata.appraisal2.controller;

import com.permata.appraisal2.common.BaseController;
import com.permata.appraisal2.common.DataTables;
import com.permata.appraisal2.dto.DesktopNilaiBangunanDto;
import com.permata.appraisal2.facade.DesktopNilaiBangunanFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("master/nilai-bangunan")
public class DesktopNilaiBangunanController extends BaseController {

    @Autowired
    private DesktopNilaiBangunanFacade facade;


    @RequestMapping("lookup")
    public String lookup(@ModelAttribute("formSearch") DesktopNilaiBangunanDto dto) {
        return "master/nilai-bangunan/lookup";
    }

    @RequestMapping(value = "find", method = RequestMethod.GET)
    @ResponseBody
    public DataTables<DesktopNilaiBangunanDto> find(@ModelAttribute("formSearch") DesktopNilaiBangunanDto dto) {
        return facade.find(dto, getPagingInfo()).cloneToDataTable();
    }

}