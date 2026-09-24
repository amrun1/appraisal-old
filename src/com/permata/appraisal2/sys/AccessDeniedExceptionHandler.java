package com.permata.appraisal2.sys;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.dao.PersonDao;
import com.permata.appraisal2.dao.SecurityLogDao;
import com.permata.appraisal2.dto.PersonDto;
import com.permata.appraisal2.model.SecurityLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;


@Service
@Transactional
public class AccessDeniedExceptionHandler implements AccessDeniedHandler {

    @Autowired
    private PersonDao personDao;


    @Autowired
    private SecurityLogDao securityLogDao;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth !=null){
                InetAddress ine = InetAddress.getLocalHost();
                SecurityLog s = new SecurityLog();
                s.setDateTime(new Date());
                s.setIpAddress(ine.getHostAddress());
                s.setKeterangan("Unauthorize Access");
                String detail ="Try To Access Page "+String.format("URL [%s] ", request.getServletPath())+" ";
                s.setDetail(detail);
                s.setAppName("Web Appraisal");
                s.setComputerName(ine.getHostName());
                s.setUserAffected(null);
                s.setPerson(personDao.get(auth.getName()));
                s.setLogCode(AppConstans.SecurityLogConstans.UNAUTHORIZE_ACCESS);
                securityLogDao.save(s);
            }
            } catch (UnknownHostException ex) {
                ex.printStackTrace();
            }
        String uri =  request.getContextPath().concat("/access-denied");
        response.sendRedirect(uri);
    }

}
