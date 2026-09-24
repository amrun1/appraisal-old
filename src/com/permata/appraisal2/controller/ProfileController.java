package com.permata.appraisal2.controller;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.common.BaseController;
import com.permata.appraisal2.dto.PersonDto;
import com.permata.appraisal2.facade.PersonFacade;
import com.permata.appraisal2.facade.SecurityLogFacade;
import com.permata.appraisal2.model.SecurityLog;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.InetAddress;
import java.net.UnknownHostException;



@Controller
@RequestMapping("profile")
public class ProfileController extends BaseController {

    private final String DIR_VIEW = "profile/";

    @Autowired
    private PersonFacade facade;



    @Autowired
    private SecurityLogFacade facadeLog;



    private InetAddress ine = null;



    public ProfileController() {
        try {
            ine = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }


    @RequestMapping(value = {"update"}, method = RequestMethod.GET)
    public String form(Model model,@ModelAttribute("msg") String msg) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (StringUtils.isBlank(auth.getName())) {
            PersonDto emptyDto = new PersonDto();
            emptyDto.setInactive(false);
            model.addAttribute("formBean", emptyDto);
        } else {
            model.addAttribute("formBean", facade.get(auth.getName()));
        }

        if (!StringUtils.isBlank(msg)){
            model.addAttribute("msg",msg);
        }


          return DIR_VIEW + "formProfile";
    }



    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("formBean") @Valid PersonDto dto, BindingResult result, Model model,HttpServletRequest request) {
        PersonDto personBackup = facade.get(dto.getUserId());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (result.hasErrors()) {
            return DIR_VIEW + "formProfile";
        }
        dto = facade.save(dto);

        boolean emailChange = personBackup.getEmail().equals(dto.getEmail());
        boolean phoneChange =personBackup.getPhone().equals(dto.getPhone());
        boolean hpChange =personBackup.getHp().equals(dto.getHp());
        boolean noHpAtasanChange =personBackup.getHpAtasan().equals(dto.getHpAtasan());


        StringBuilder stringBuilder = new StringBuilder("User Profile Changed,");
        String email =" EMAIL from '"+personBackup.getEmail()+"' to '"+dto.getEmail()+"'";
        String phone =" PHONE from '"+personBackup.getPhone()+"' to '"+dto.getPhone()+"'";
        String hp =" HP from '"+personBackup.getHp()+"' to '"+dto.getHp()+"'";
        String hpAtasan=" HP ATASAN from '"+personBackup.getHpAtasan()+"' to '"+dto.getHpAtasan()+"'";

        if (!emailChange){
            stringBuilder.append(email);
        }
        if (!phoneChange){
            stringBuilder.append(phone);
        }
        if (!hpChange){
            stringBuilder.append(hp);
        }
        if (!noHpAtasanChange){
            stringBuilder.append(hpAtasan);
        }
        if (!emailChange || !phoneChange || !hpChange || !noHpAtasanChange){
            facadeLog.saveLogging(AppConstans.SecurityLogConstans.USER_PROFILE_CHANGE,dto.getUserId(),request,stringBuilder.toString(),null);
        }


        model.addAttribute("msg", "Profile Updated Successfully!");
        return "redirect:update";
    }
}
