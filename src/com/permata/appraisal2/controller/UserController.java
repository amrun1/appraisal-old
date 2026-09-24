package com.permata.appraisal2.controller;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.common.BaseController;
import com.permata.appraisal2.common.DataTables;
import com.permata.appraisal2.common.StatusAjaxResponse;
import com.permata.appraisal2.dto.*;
import com.permata.appraisal2.facade.AppraisalBranchFacade;
import com.permata.appraisal2.facade.BranchFacade;
import com.permata.appraisal2.facade.PersonFacade;
import com.permata.appraisal2.facade.SecurityLogFacade;
import com.permata.appraisal2.model.PersonLevel;
import com.permata.appraisal2.model.SecurityLog;
import com.permata.appraisal2.service.CustomAuthenticationProvider;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("admin/user")
public class UserController extends BaseController {

    private final String DIR_VIEW = "admin/user/";

    @Autowired
    private PersonFacade facade;
    @Autowired
    private BranchFacade branchFacade;
    @Autowired
    private AppraisalBranchFacade appraisalBranchFacade;
    @Autowired
    @Qualifier("customAuthenticationProvider")
    private CustomAuthenticationProvider authenticationProvider;

    @Autowired
    // @Qualifier("dataSource")
    private BasicDataSource dataSource;

    @Autowired
    private ServletContext context;


    private  InetAddress ine = null;



    public UserController() {
        try {
            ine = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }


    @Autowired
    private SecurityLogFacade facadeLog;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(@ModelAttribute("formBean") PersonDto formBean) {
        return DIR_VIEW + "list";
    }

    @RequestMapping(value = "find", method = RequestMethod.GET)
    @ResponseBody
    public DataTables<PersonDto> find(@ModelAttribute("formBean") PersonDto dto) {
        DataTables<PersonDto> drperson = facade.find(dto, getPagingInfo()).cloneToDataTable();
//        return facade.find(dto, getPagingInfo()).cloneToDataTable();
        return drperson;
    }




    @RequestMapping(value = "listDeleted", method = RequestMethod.GET)
    public String listDel(@ModelAttribute("formBean") PersonDto formBean) {
        return DIR_VIEW + "listPersonDelete";
    }

    @RequestMapping(value = "findDeleted", method = RequestMethod.GET)
    @ResponseBody
    public DataTables<PersonDto> findDeleted(@ModelAttribute("formBean") PersonDto dto) {
        return facade.findDelete(dto, getPagingInfo()).cloneToDataTable();
    }


    @RequestMapping(value = "find-role", method = RequestMethod.GET)
    @ResponseBody
    public DataTables<PersonRoleDto> find(@RequestParam(value = "userId") String userId) {
        return facade.findRole(userId, getPagingInfo()).cloneToDataTable();
    }




    @RequestMapping(value = "listPersonAct", method = RequestMethod.GET)
    public String list() {
        return DIR_VIEW + "listPersonAct";
    }

    @RequestMapping(value = "listLog", method = RequestMethod.GET)
    public String list(@ModelAttribute("personId") String personId) {
        return DIR_VIEW + "listLog";
    }

    @RequestMapping(value = "findLog", method = RequestMethod.GET)
    @ResponseBody
    public DataTables<SecurityLogDto> findLog(@RequestParam("personId") String personId) {
        return facadeLog.find(personId, getPagingInfo(), getOrder()).cloneToDataTable();
    }

    @RequestMapping(value = {"add", "edit"}, method = RequestMethod.GET)
    public String form(Model model, @RequestParam(value = "userId", required = false) String userId,@RequestParam(value = "msg", required = false) String msg) {

        if (StringUtils.isBlank(userId)) {
            PersonDto emptyDto = new PersonDto();
            emptyDto.setInactive(false);
            model.addAttribute("formBean", emptyDto);
        } else {
            model.addAttribute("formBean", facade.get(userId));
        }
        model.addAttribute("msg",msg);
        return DIR_VIEW + "form";
    }

    @RequestMapping(value = {"add-role", "edit-role"}, method = RequestMethod.GET)
    public String formRole(Model model, @RequestParam(value = "userId") String userId,
                           @RequestParam(value = "id", required = false) Long id) {
        if (id == null) {
            model.addAttribute("formBean", new PersonRoleDto(userId));
        } else {
            model.addAttribute("formBean", facade.getRole(id));
        }
        reference(model);
        return DIR_VIEW + "form-role";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("formBean") @Valid PersonDto dto, BindingResult result, Model model,HttpServletRequest request) {
        Authentication  auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isNew =false;


        PersonDto personBackup=null;

        if (result.hasErrors()) {
            return DIR_VIEW + "form";
        }
//        if(!authenticationProvider.ldapLogin(dto.getUserId(), dto.getPassword())){
//            model.addAttribute("error", "Username dan Password salah login ke LDAP");
//            return DIR_VIEW + "form";
//        }
        if(!dto.isUpdate()){
            if(facade.existsUserid(dto.getUserId())){
                model.addAttribute("error", "Username sudah terdaftar di database");
                return DIR_VIEW + "form";
            }
            isNew = true;
        }else{
            personBackup = facade.get(dto.getUserId());
        }


        dto = facade.save(dto);
        //INSERT TO LOG
        if (isNew){
                String detailEvent =auth.getName()+" Creation for User "+dto.getUserId()+" "+dto.toStringProfile();
                facadeLog.saveLogging(AppConstans.SecurityLogConstans.USER_PROFILE_CREATION,auth.getName(),request,detailEvent,dto.getUserId());
                facadeLog.saveLogging(AppConstans.SecurityLogConstans.USER_PROFILE_CREATION,dto.getUserId(),request,detailEvent,null);
        }else{
            boolean emailChange = personBackup.getEmail().equals(dto.getEmail());
            boolean phoneChange =personBackup.getPhone().equals(dto.getPhone());
            boolean hpChange =personBackup.getHp().equals(dto.getHp());
            boolean noHpAtasanChange =personBackup.getHpAtasan().equals(dto.getHpAtasan());

            if (!personBackup.getName().equals(dto.getName())){
                String detailEvent = auth.getName()+ " Renaming for User "+dto.getUserId()+", name from "+personBackup.getName()+" to "+dto.getName();
                facadeLog.saveLogging(AppConstans.SecurityLogConstans.USER_PROFILE_RENAMING,auth.getName(),request,detailEvent,dto.getUserId());
                facadeLog.saveLogging(AppConstans.SecurityLogConstans.USER_PROFILE_RENAMING,dto.getUserId(),request,detailEvent,null);
            }
            StringBuilder stringBuilder = new StringBuilder("User Profile Change for User "+dto.getUserId()+",");
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
                facadeLog.saveLogging(AppConstans.SecurityLogConstans.USER_PROFILE_CHANGE,auth.getName(),request,stringBuilder.toString(),dto.getUserId());
                facadeLog.saveLogging(AppConstans.SecurityLogConstans.USER_PROFILE_CHANGE,dto.getUserId(),request,stringBuilder.toString(),null);
            }

        }
        model.addAttribute("msg", isNew?"Save Data Successfully!":"Updated data Successfully!");
        return "redirect:edit?userId=" + dto.getUserId();
    }

    @RequestMapping(value = "save-role", method = RequestMethod.POST)
    public String saveRole(@ModelAttribute("formBean") @Valid PersonRoleDto dto, BindingResult result, Model model, HttpServletRequest request) {

        boolean isNew = dto.getId()==null;
        PersonRoleDto backupDto=null;
        if (!isNew){
             backupDto = facade.getRole(dto.getId());
        }

        Authentication  auth = SecurityContextHolder.getContext().getAuthentication();
        if (result.hasErrors()) {
            reference(model);
            return DIR_VIEW + "form-role";
        }
        String level = dto.getLevel().getValue();
        String[] lvArr = new String[]{"Appraisal Admin","Appraisal Approval","Appraisal Valuer","Appraisal Surveyor"};
        Long cabangBisnis = dto.getBranchId();
        Long cabangAppraisal =dto.getAppBranchId();

       if (level.equals("Marketing") && cabangBisnis==null){
           model.addAttribute("error", "Cabang Bisnis harus dipilih");
           reference(model);
           return DIR_VIEW + "form-role";
       }else if (Arrays.asList(lvArr).contains(level) && cabangAppraisal==null) {
           model.addAttribute("error", "Cabang Appraisal harus dipilih");
           reference(model);
           return DIR_VIEW + "form-role";
       }
        // validation
        if (facade.existsRole(dto.getUserId(), dto.getLevel(),dto.getId())) {
            model.addAttribute("error", "Level sudah ada");
          
            reference(model);
            return DIR_VIEW + "form-role";
        }

        if (level.equals("Appraisal Approval") && (dto.getLimitApproval()==null || dto.getLimitApproval().compareTo(BigDecimal.ZERO)==0 ) ){
            model.addAttribute("error", "Limit Tidak Boleh 0");

            reference(model);
            return DIR_VIEW + "form-role";
        }

       PersonRoleDto saved = facade.saveRole(dto);

        if (isNew){
            //Buat Baru
            String detail ="Add Role "+saved.getLevel().toString()+", "+saved.toStringProf()+" for User "+saved.getUserId()+"  ";
            facadeLog.saveLogging(AppConstans.SecurityLogConstans.USER_ROLES_ADD,auth.getName(),request,detail,saved.getUserId());
            facadeLog.saveLogging(AppConstans.SecurityLogConstans.USER_ROLES_ADD,saved.getUserId(),request,detail,null);
        }else {
            //Update Data

            logger.debug("BackupDto: "+backupDto.toStringProf());
            boolean levelChange = backupDto.getLevel().getValue().equals(saved.getLevel().getValue());
            boolean cabangBisnisChange =backupDto.getBranchName().equals(saved.getBranchName());
            boolean cabangAppChange =backupDto.getAppBranchName().equals(saved.getAppBranchName());
            int limitChange =backupDto.getLimitApproval().compareTo(saved.getLimitApproval());

            StringBuilder stringBuilder = new StringBuilder("Change");

            String levels =" LEVEL from '"+backupDto.getLevel().toString()+"' to '"+saved.getLevel().toString()+"',";
            String branchBusiness =" CABANG BISNIS from '"+backupDto.getBranchName()+"' to '"+saved.getBranchName()+"',";
            String cabangApp =" CABANG APPRAISAL  from '"+backupDto.getAppBranchName()+"' to '"+saved.getAppBranchName()+"',";
            String limitAppraove =" LIMIT APPROVE from "+backupDto.getLimitApproval()+" to "+saved.getLimitApproval();

            if (!levelChange){
                stringBuilder.append(levels);
            }
            if (!cabangBisnisChange){
                stringBuilder.append(branchBusiness);
            }
            if (!cabangAppChange){
                stringBuilder.append(cabangApp);
            }
            if (limitChange!=0){
                stringBuilder.append(limitAppraove+",");
            }

            stringBuilder.append(" for user id "+saved.getUserId());
            facadeLog.saveLogging(AppConstans.SecurityLogConstans.USER_ROLES_CHANGE,auth.getName(),request,stringBuilder.toString(),saved.getUserId());
            facadeLog.saveLogging(AppConstans.SecurityLogConstans.USER_ROLES_CHANGE,saved.getUserId(),request,stringBuilder.toString(),null);


        }

        return "success-close-modal";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public StatusAjaxResponse delete(@RequestParam("userId") String userId,HttpServletRequest request) {
        try {
            Authentication  auth = SecurityContextHolder.getContext().getAuthentication();
            facade.delete(userId,auth.getName());
            String detail =auth.getName()+" Deletion for User Id "+userId;
            facadeLog.saveLogging(AppConstans.SecurityLogConstans.USER_PROFILE_DELETION,auth.getName(),request,detail,userId);
            facadeLog.saveLogging(AppConstans.SecurityLogConstans.USER_PROFILE_DELETION,userId,request,detail,null);
            return new StatusAjaxResponse(200, "Delete success");
        } catch (Exception e) {
            return new StatusAjaxResponse(500, e.getMessage());
        }
    }



    @RequestMapping(value = "active", method = RequestMethod.POST)
    @ResponseBody
    public StatusAjaxResponse active(@RequestParam("userId") String userId, HttpServletRequest request) {
        try {
            Authentication  auth = SecurityContextHolder.getContext().getAuthentication();
            facade.active(userId,false,auth.getName(), new Date());
            String detail =auth.getName()+" Activation for User Id "+userId;
            facadeLog.saveLogging(AppConstans.SecurityLogConstans.USER_PROFILE_ACTIVATION,auth.getName(),request,detail,userId);
            facadeLog.saveLogging(AppConstans.SecurityLogConstans.USER_PROFILE_ACTIVATION,userId,request,detail,null);
                      return new StatusAjaxResponse(200, "active user success");
        } catch (Exception e) {
            return new StatusAjaxResponse(500, e.getMessage());
        }
    }
    @RequestMapping(value = "notActive", method = RequestMethod.POST)
    @ResponseBody
    public StatusAjaxResponse notActive(@RequestParam("userId") String userId, HttpServletRequest request) {
        try {
            Authentication  auth = SecurityContextHolder.getContext().getAuthentication();
            facade.inActive(userId, true, auth.getName(), new Date());
            String detail =auth.getName()+" Deactivation for User Id "+userId;
            facadeLog.saveLogging(AppConstans.SecurityLogConstans.USER_PROFILE_DEACTIVATION,auth.getName(),request,detail,userId);
            facadeLog.saveLogging(AppConstans.SecurityLogConstans.USER_PROFILE_DEACTIVATION,userId,request,detail,null);
            return new StatusAjaxResponse(200, "Deactive user success");
        } catch (Exception e) {
            return new StatusAjaxResponse(500, e.getMessage());
        }
    }



    @RequestMapping(value = "undelete", method = RequestMethod.POST)
    @ResponseBody
    public StatusAjaxResponse undelete(@RequestParam("userId") String userId, HttpServletRequest request) {
        try {
            Authentication  auth = SecurityContextHolder.getContext().getAuthentication();
            facade.undelete(userId);
            String detail =auth.getName()+" Un-Deletion for User Id "+userId;
            facadeLog.saveLogging(AppConstans.SecurityLogConstans.USER_PROFILE_UNDELETE,auth.getName(),request,detail,userId);
            facadeLog.saveLogging(AppConstans.SecurityLogConstans.USER_PROFILE_UNDELETE,userId,request,detail,null);
             return new StatusAjaxResponse(200, "Undelete user success");
        } catch (Exception e) {
            return new StatusAjaxResponse(500, e.getMessage());
        }
    }






    @RequestMapping(value = "delete-role", method = RequestMethod.POST)
    @ResponseBody
    public StatusAjaxResponse deleteRole(@RequestParam("id") Long id, HttpServletRequest request) {
        try {
            Authentication  auth = SecurityContextHolder.getContext().getAuthentication();
            PersonRoleDto role = facade.getRole(id);
            facade.deleteRole(id);

            String detail ="Remove Role "+role.getLevel().toString()+" for User "+role.getUserId();
            facadeLog.saveLogging(AppConstans.SecurityLogConstans.USER_ROLES_REMOVED,auth.getName(),request,detail,role.getUserId());
            facadeLog.saveLogging(AppConstans.SecurityLogConstans.USER_ROLES_REMOVED,role.getUserId(),request,detail,null);
                   return new StatusAjaxResponse(200, "Delete success");
        } catch (Exception e) {
            return new StatusAjaxResponse(500, e.getMessage());
        }
    }

    @RequestMapping(value = "check-ldap", method = RequestMethod.POST)
    @ResponseBody
    public Boolean checkLdap(@RequestParam("userId") String userId, @RequestParam("password") String password) {
        return authenticationProvider.ldapLogin(userId, password);
    }

    private void reference(Model model) {
        PersonLevel[] personLevels = (PersonLevel[]) ArrayUtils.removeElement(PersonLevel.values(), PersonLevel.ROLE_911);
        model.addAttribute("listLevel", personLevels);
        model.addAttribute("listBranch", branchFacade.findLv());
        model.addAttribute("listAppBranch", appraisalBranchFacade.findLv(new AppraisalBranchDto()));
    }

    @RequestMapping (value = "laporan-user.xls", method = RequestMethod.GET)
    public ModelAndView laporanUser(){
//        logger.info("lewat sini kok");
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        String jasperDir = String.format("%s%s%s%s%s%s", context.getRealPath(""), File.separator, "WEB-INF",
                File.separator, "report", File.separator);
        parameterMap.put("SUBREPORT_DIR", jasperDir);
        parameterMap.put("datasource", dataSource);
        return new ModelAndView("xlsReportUser", parameterMap);
    }



}