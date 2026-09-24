package com.permata.appraisal2.controller;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.dto.PersonDto;
import com.permata.appraisal2.dto.SecurityLogDto;
import com.permata.appraisal2.dto.SystemParamsDto;
import com.permata.appraisal2.facade.PersonFacade;
import com.permata.appraisal2.facade.SecurityLogFacade;
import com.permata.appraisal2.facade.SystemParamFacade;
import com.permata.appraisal2.model.Person;
import com.permata.appraisal2.model.SecurityLog;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class LoginController {

    private Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private PersonFacade personFacade;


    @Autowired
    private SecurityLogFacade securityLogFacade;


    @Autowired
    private SystemParamFacade systemParamFacade;


    @RequestMapping(value = "/login-success", method = RequestMethod.GET)
    public String loginSuccess(HttpServletRequest request, HttpSession session, Principal user) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();


        if (!auth.getName().equals(AppConstans.Role911.USER_ID)) {
            PersonDto person = personFacade.get(auth.getName());
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            boolean failBefore = person.getLastFail() != null;

            String dFormat = (failBefore ? "Last Fail Login: " : "Last Login: ") + simpleFormat.format(failBefore ? person.getLastFail() : person.getLastLogin() == null ? new Date() : person.getLastLogin());
            session.setAttribute("dformat", dFormat);
            session.setAttribute("failExist", failBefore);
            person.setLastLogin(new Date());
            person.setErrorCount(0);
            person.setLastFail(null);
            personFacade.save(person);
            //Write Log
            try {
                InetAddress ine = InetAddress.getLocalHost();
                securityLogFacade.saveLogging(AppConstans.SecurityLogConstans.LOGIN_SUCCESS,person.getUserId(),request,"Login Success",null);

                SystemParamsDto param = systemParamFacade.get("AUTOMATIC_LOGOUT");
                Long timeExpi = 30l;
                if (param != null){
                    timeExpi =Long.parseLong(param.getValue());
                }
                Long maxInterval = timeExpi * 60000;
                session.setAttribute("maxInterval",maxInterval);
//                request.getSession().setMaxInactiveInterval(10);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

        } else {
            SystemParamsDto param = systemParamFacade.get("AUTOMATIC_LOGOUT");
            Long timeExpi = 30l;
            if (param != null){
                timeExpi =Long.parseLong(param.getValue());
            }
            Long maxInterval = timeExpi * 60000;
            session.setAttribute("maxInterval",maxInterval);
        }
        return "redirect:/page-main";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout,
                              @RequestParam(value = "invalidate", required = false) String invalidate,
                              @RequestHeader(value = "requestedURI", required = false) String requestedURI,
                              @RequestParam(value = "msgExp", required = false) String msgExp,
                              HttpServletRequest request,
                              HttpSession session) {
        ModelAndView model = new ModelAndView();

        if (error != null) {

            model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
        }
        if (invalidate != null) {

            model.addObject("invalidate", "true");

        }
        if (logout != null) {

            session.invalidate();
            model.addObject("msg", "You've been logged out successfully.");

        }


        if (msgExp != null){
            model.addObject("msg", msgExp);
        }


        model.addObject("spring-security-redirect", requestedURI);
        model.setViewName("login");
        return model;

    }






    @RequestMapping(value = "/access-denied", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView login(HttpServletRequest request, HttpSession session, Principal user) {
        ModelAndView model = new ModelAndView("login");
        model.addObject("error", "Akses anda ditolak, gunakan login sesuai hak akses");
        model.addObject("user", user);
        return model;
    }

    private String getErrorMessage(HttpServletRequest request, String key) {
        Exception exception = (Exception) request.getSession().getAttribute(key);

        String error = "";
        if (exception instanceof BadCredentialsException) {
            error = exception.getMessage();
        } else if (exception instanceof LockedException) {
            error = exception.getMessage();
            SecurityLog securityLog = (SecurityLog) ((LockedException) exception).getExtraInformation();
            Person person = securityLog.getPerson();
            person.setErrorCount(securityLog.getPerson().getErrorCount());
            person.setLastFail(new Date());
            securityLogFacade.save(securityLog);
            personFacade.save(person);
        } else {
            error = "error : " + exception.getMessage();
        }
        return error;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            //Write Log
            try {
                if(!auth.getName().equalsIgnoreCase(AppConstans.Role911.USER_ID)) {
                    InetAddress ine = InetAddress.getLocalHost();
                    securityLogFacade.saveLogging(AppConstans.SecurityLogConstans.LOGOUT, auth.getName(), request, "Logout", null);
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            session.invalidate();
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }



    @RequestMapping(value = "/logoutExpired", method = RequestMethod.GET)
    public String logoutExpired(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String detail ="";
        if (auth != null && !auth.getName().equalsIgnoreCase(AppConstans.Role911.USER_ID)) {
            //Write Log
            try {
                InetAddress ine = InetAddress.getLocalHost();
                Long timeExpi = 30l;
                SystemParamsDto param = systemParamFacade.get("AUTOMATIC_LOGOUT");
                if (param != null){
                    timeExpi =Long.parseLong(param.getValue());
                }
                 detail ="expired cause of inactivity for "+timeExpi+"   minutes ";
                securityLogFacade.saveLogging(AppConstans.SecurityLogConstans.LOGOUT_SESSION_EXPIRED,auth.getName(),request,detail,null);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            session.invalidate();
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }


        return "redirect:/login?invalidate=true&msgExp="+detail;
    }
}