package com.permata.appraisal2.controller;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.common.BaseController;
import com.permata.appraisal2.common.DataTables;
import com.permata.appraisal2.dto.SystemParamsDto;
import com.permata.appraisal2.facade.SecurityLogFacade;
import com.permata.appraisal2.facade.SystemParamFacade;
import com.permata.appraisal2.model.SecurityLog;
import com.permata.appraisal2.model.SystemParam;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("master/systemparam")
public class SystemParamController extends BaseController {

    @Autowired
    SystemParamFacade facade;

    @Autowired
    private SecurityLogFacade facadeLog;

    private final String DIR_VIEW = "master/systemparam/";


    @RequestMapping(value = {"list", ""}, method = RequestMethod.GET)
    public String list(Model model, @ModelAttribute("formBean") SystemParam dto) {
        return DIR_VIEW + "list";
    }

    @RequestMapping(value = "automatic-logout", method = RequestMethod.GET)
    public String formAutoLogout(Model model, @ModelAttribute("formBean") SystemParam dto){

        SystemParamsDto entity = facade.getCot("AUTOMATIC_LOGOUT");
        if(entity!=null) {
            dto.setValue(entity.getValue());
        }else{
            dto.setValue("");
        }

        model.addAttribute("formBean", dto);
        return DIR_VIEW + "automatic-logout";
    }

    @RequestMapping(value = "updateAutoLogout", method = RequestMethod.POST)
    public String updateAutoLogout(@RequestParam(value = "value",required = false) String minute,Model model,@ModelAttribute("formBean") SystemParamsDto dto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        facade.update("AUTOMATIC_LOGOUT",minute);

        model.addAttribute("formBean", dto);
        model.addAttribute("msg", "Automatic Logout Time Berhasil disimpan");
        return DIR_VIEW + "automatic-logout";

    }

    @RequestMapping(value = {"addCot"}, method = RequestMethod.GET)
    public String formCot(Model model) {
        List<String> hour = new ArrayList<String>();
        List<String> minute = new ArrayList<String>();
        for (int i = 0; i <= 23; i++) {
            String jam = Integer.valueOf(i).toString();
            hour.add(jam.length() < 2 ? "0".concat(jam) : jam);
        }
        for (int i = 0; i < 60; i++) {
            if (i % 15 == 0) {
                String menit = Integer.valueOf(i).toString();
                minute.add(menit.length() < 2 ? "0".concat(menit) : menit);
            }

        }

        model.addAttribute("listJam", hour);
        model.addAttribute("listMenit", minute);
        SystemParamsDto formBean = new SystemParamsDto();
        SystemParamsDto entity = facade.getCot("COT");



        if (entity!=null){
            String[] jam = entity.getValue().split(":");
            logger.debug("Jam = " + jam[0]);
            logger.debug("Menit="+jam[1]);
            formBean.setValue(jam[0]);
            formBean.setCode(jam[1]);
        }else {
            formBean.setValue("");
            formBean.setCode("");
        }

        model.addAttribute("formBean", formBean);
        return DIR_VIEW + "cot";
    }


    @RequestMapping(value = "updateCot", method = RequestMethod.POST)
    public String updateCot(@RequestParam(value = "jam",required = false) String jam,
                            @RequestParam(value = "menit", required = false) String menit,Model model,@ModelAttribute("formBean") SystemParamsDto dto) {

//        String value = jam.concat(":").concat(menit);
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String jam2  = dto.getValue().concat(":").concat(dto.getCode());
        facade.update("COT",jam2);


        List<String> hour = new ArrayList<String>();
        List<String> minute = new ArrayList<String>();
        for (int i = 0; i <= 23; i++) {
            String jamp = Integer.valueOf(i).toString();
            hour.add(jamp.length() < 2 ? "0".concat(jamp) : jamp);
        }
        for (int i = 0; i < 60; i++) {
            if (i % 15 == 0) {
                String menitp = Integer.valueOf(i).toString();
                minute.add(menitp.length() < 2 ? "0".concat(menitp) : menitp);
            }

        }

        model.addAttribute("listJam", hour);
        model.addAttribute("listMenit", minute);
        model.addAttribute("formBean", dto);
        model.addAttribute("msg", "Cut of time Berhasil disimpan");

        return DIR_VIEW + "cot";
    }


    @RequestMapping(value = {"add", "edit"}, method = RequestMethod.GET)
    public String form(Model model, @RequestParam(value = "code", required = false) String code) {
        if (code == null) {
            logger.info("Masukk Null");
            model.addAttribute("form_edit", "0");
            model.addAttribute("formBean", new SystemParamsDto());
        } else {
            logger.info("Masukk  tdk null");
            SystemParamsDto exist = facade.get(code);
            model.addAttribute("form_edit", "1");
            model.addAttribute("formBean", exist);

        }
        return DIR_VIEW + "form";
    }


    @RequestMapping(value = "find", method = RequestMethod.GET)
    @ResponseBody
    public DataTables<SystemParamsDto> find(@ModelAttribute("formBean") SystemParamsDto dto) {
        return facade.find(dto, getPagingInfo()).cloneToDataTable();
    }


    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("formBean") @Valid SystemParamsDto dto, BindingResult result, Model model, HttpServletRequest request) throws UnknownHostException {
        boolean isNew =dto.getCode()==null;
        SystemParamsDto dtoBackup =null;
            if (!isNew){
                dtoBackup = facade.get(dto.getCode());
            }

        if (result.hasErrors()) {
            return DIR_VIEW + "form";
        }
        boolean edit = !StringUtils.isBlank(dto.getCode());
        facade.update(dto.getCode(), dto.getValue());
        InetAddress ine = InetAddress.getLocalHost();;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
               if (edit) {
            model.addAttribute("msg", "Data updated successfully!");
        } else {
            model.addAttribute("msg", "Data added successfully!");
        }
        model.addAttribute("form_edit", "1");
        model.addAttribute("msg", "Data updated successfully!");
        model.addAttribute("formBean", dto);
//        Change "Parameter Changed(KODE_PARAMETER)" from "abc (Value)" to "def

        if(!isNew){
            String detail= "Change "+dto.getCode()+" from value '"+dtoBackup.getValue()+"' to '"+dto.getValue()+"'";
            facadeLog.saveLogging(AppConstans.SecurityLogConstans.SYSTEM_PARAMETER_CHANGE,auth.getName(),request,detail,null);
        }


        return DIR_VIEW + "form";
    }

    @RequestMapping(value = {"form-carm-auth"}, method = RequestMethod.GET)
    public String formCarmAuth(Model model) {
        SystemParamsDto formBean = new SystemParamsDto();
        SystemParamsDto user = facade.getCot("WS_CARM_USER");
//        formBean.add(user);
        SystemParamsDto password = facade.getCot("WS_CARM_PASSWORD");
//        formBean.add(password);
        formBean.setCode(user.getValue());
        formBean.setValue(password.getValue());
        model.addAttribute("user", user.getValue());
        model.addAttribute("password", password.getValue());
        model.addAttribute("formBean", formBean);
        return DIR_VIEW + "form-carm-auth";
    }

    @RequestMapping(value = "form-carm-auth", method = RequestMethod.POST)
    public String saveCarmAuth(Model model,@ModelAttribute("formBean") SystemParamsDto dto) {

//        String value = jam.concat(":").concat(menit);
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String jam2  = dto.getValue().concat(":").concat(dto.getCode());
        facade.update("WS_CARM_USER",dto.getCode()); // Saved User
        facade.update("WS_CARM_PASSWORD", dto.getValue()); // Saved password

        SystemParamsDto user = facade.getCot("WS_CARM_USER");
        SystemParamsDto password = facade.getCot("WS_CARM_PASSWORD");
        model.addAttribute("user", user.getValue());
        model.addAttribute("password", password.getValue());
        model.addAttribute("formBean", dto);
        model.addAttribute("msg", "User dan Password interface Berhasil disimpan");

        return DIR_VIEW + "form-carm-auth";
    }

}
