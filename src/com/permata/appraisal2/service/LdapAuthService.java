package com.permata.appraisal2.service;

import com.permata.appraisal2.dao.PersonDao;
import com.permata.appraisal2.model.Person;
import com.permata.appraisal2.model.PersonLevel;
import com.permata.appraisal2.model.PersonRole;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.*;

// @Service
@Deprecated
public class LdapAuthService implements UserDetailsService {

    private Logger logger = Logger.getLogger(LdapAuthService.class);

    @Autowired
    private PersonDao personDao;

    @Transactional
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        logger.debug("LdapAuthService starting {}" + username);

        /**
         * LDAP test login
         */
        final String ldapAdServer = "ldap://10.87.16.101";
        final String ldapSearchBase = "OU=Employees,OU=User-Accounts,OU=JKT-Bintaro,DC=permatadev,DC=co,DC=id";
        final String ldapUsername = username;
        final String ldapPassword = "P@ssw0rd";

        Hashtable<String, Object> env = new Hashtable<String, Object>();
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        if (ldapUsername != null) {
            env.put(Context.SECURITY_PRINCIPAL, ldapUsername);
        }
        if (ldapPassword != null) {
            env.put(Context.SECURITY_CREDENTIALS, ldapPassword);
        }
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, ldapAdServer);

        env.put("java.naming.ldap.attributes.binary", "objectSID");
        LdapContext ctx = null;
//        try {
//            ctx = new InitialLdapContext(env, null);
//        } catch (NamingException e) {
//            e.printStackTrace();
//            throw new UsernameNotFoundException("LDAP error : " + e.getMessage(), e);
//        } finally {
//            try {
//                if (ctx != null) ctx.close();
//            } catch (Exception e) {
//            }
//        }

        Person person = personDao.get(username);
        logger.debug("person {}" + person);

        if (person == null) {
            throw new UsernameNotFoundException("User not exists on database");
        }

        List<GrantedAuthority> authorities = buildUserAuthority(person);
        logger.info("userId {}" + person.getUserId());
        logger.info("authorities {}" + authorities);
        return buildUserForAuthentication(person, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Person person) {
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        logger.debug("CHECK PERSON LEVEL");
        for (PersonRole role : person.getRoles()) {
            setAuths.add(new SimpleGrantedAuthority(role.getLevel().toString()));
        }

        return new ArrayList<GrantedAuthority>(setAuths);
    }

    private User buildUserForAuthentication(Person person, List<GrantedAuthority> authorities) {
        return new User(person.getUserId(), person.getPassword(), true, true, true, true, authorities);
    }

}