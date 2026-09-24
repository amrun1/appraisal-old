package com.permata.appraisal2.controller;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.common.BaseController;
import com.permata.appraisal2.dto.PersonLoginDto;
import com.permata.appraisal2.dto.PersonRoleDto;
import com.permata.appraisal2.facade.PersonFacade;
import com.permata.appraisal2.model.Person;
import com.permata.appraisal2.model.PersonLevel;
import com.permata.appraisal2.model.PersonRole;
import com.permata.appraisal2.util.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"person", "roleActive"})
public class MainController extends BaseController {

    @Autowired
    private PersonFacade facade;

    @RequestMapping("change-role")
    public String changeRole(Model model, @ModelAttribute("roleActive") PersonRoleDto roleActive,
                             @ModelAttribute("person") PersonLoginDto login, @RequestParam("id") Long roleId) {

        PersonRoleDto changeTo = new PersonRoleDto();
        for (PersonRoleDto role : login.getRoles()) {
            if (role.getId().equals(roleId)) {
                changeTo = role;
                break;
            }
        }
        model.addAttribute("roleActive", changeTo);
        return "redirect:page-main";
    }

    @RequestMapping({"page-main"})
    public String pageMain(Model model,HttpServletRequest req) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.debug("auth.getCredentials() -> " + auth.getDetails());

        PersonLoginDto login = null;
        if(auth.getName().equals(AppConstans.Role911.USER_ID)){
            login = new PersonLoginDto();
            login.setUserId(AppConstans.Role911.USER_ID);
            login.setName(AppConstans.Role911.USER_ID);
            login.setPassword(AppConstans.Role911.PASSWORD);

            PersonRoleDto roleDto = new PersonRoleDto();
            roleDto.setUserId(AppConstans.Role911.USER_ID);
            roleDto.setLevel(PersonLevel.ROLE_911);
            roleDto.setId(Long.parseLong("-911"));
            PersonRoleDto roleAdminDto = new PersonRoleDto();
            roleAdminDto.setUserId(AppConstans.Role911.USER_ID);
            roleAdminDto.setLevel(PersonLevel.ADMIN);
            roleAdminDto.setId(Long.parseLong("-913"));
            PersonRoleDto roleKingDto = new PersonRoleDto();
            roleKingDto.setUserId(AppConstans.Role911.USER_ID);
            roleKingDto.setLevel(PersonLevel.KING);
            roleKingDto.setId(Long.parseLong("-912"));

            List<PersonRoleDto> roles = new ArrayList<PersonRoleDto>();
            roles.add(roleAdminDto);
            roles.add(roleDto);
            roles.add(roleKingDto);
            login.setRoles(roles);
        } else {
            Person user = new Person();
            user.setUserId(auth.getName());
            login = facade.getPersonLogin(user.getUserId());

        }
        model.addAttribute("person", login);
        model.addAttribute("dformat", req.getSession().getAttribute("dformat"));
        model.addAttribute("failExist", req.getSession().getAttribute("failExist"));

        if (!model.containsAttribute("roleActive")) {

            if (ListUtils.isNotEmpty(login.getRoles()) && login.getRoles().size()>1){
                int urut = login.getRoles().size()-1;
                model.addAttribute("roleActive", login.getRoles().get(urut));
            }else{

                model.addAttribute("roleActive", login.getRoles().get(0));
            }
        }

        return "template/page-main";
    }

    @RequestMapping({"page-header"})
    public String header(Model model, HttpSession session) {
        return "template/page-header";
    }

    @RequestMapping({"page-menu"})
    public String menu(Model model, HttpSession session) {
        return "template/page-menu";
    }

    @RequestMapping("page-content")
    public String content(Model model, HttpSession session) {
        return "template/page-content";
    }
}