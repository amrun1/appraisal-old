package com.permata.appraisal2.controller;

import com.permata.appraisal2.common.BaseController;
import com.permata.appraisal2.common.DataTables;
import com.permata.appraisal2.dto.SecurityLogDto;
import com.permata.appraisal2.facade.SecurityLogFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


public class SecurityLogController extends BaseController {

    private final String DIR_VIEW = "admin/securityLog";


    private SecurityLogFacade facade;


    public String list() {
        return DIR_VIEW + "list";
    }


    public DataTables<SecurityLogDto> find(@ModelAttribute("personId") String personId) {
        return facade.find(personId, getPagingInfo(), getOrder()).cloneToDataTable();
    }

}