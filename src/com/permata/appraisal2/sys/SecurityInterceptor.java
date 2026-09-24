package com.permata.appraisal2.sys;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by rans on 22/10/2016.
 */
// @Component
@Deprecated
public class SecurityInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Logger logger = Logger.getLogger(SecurityInterceptor.class);
        logger.debug("masuk ke sini");
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        String requestedURI = request.getRequestURI().replaceFirst(request.getContextPath(),"").replace("/", StringUtils.EMPTY);
        if((null == auth.getPrincipal() || auth.getPrincipal().equals("anonymousUser")) && request.getRequestURI().indexOf("login")  == -1){
            response.setHeader("requestedURI",requestedURI);
            response.sendError(500, "need login");
            logger.debug("ini");
            //response.sendRedirect(request.getContextPath()+"/login");
            return false;
        }else if(null == request.getSession(false) && request.getRequestURI().indexOf("login")  == -1){
            response.setHeader("requestedURI",requestedURI);
            response.sendError(500, "need login");
            logger.debug("itu");
            //response.sendRedirect(request.getContextPath()+"/login");
            return false;
        }
        return super.preHandle(request, response, handler);
    }
}
