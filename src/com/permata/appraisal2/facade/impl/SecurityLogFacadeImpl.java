package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.dao.PersonDao;
import com.permata.appraisal2.dao.SecurityLogDao;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.SecurityLogDto;
import com.permata.appraisal2.facade.SecurityLogFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.SecurityLog;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class SecurityLogFacadeImpl extends FacadeBase<SecurityLogDto, SecurityLog> implements SecurityLogFacade {

    @Autowired
    private SecurityLogDao dao;

    @Autowired
    private PersonDao personDao;

    @Override
    public PaginatedList<SecurityLogDto> find(String personId, PagingInfo pagingInfo, OrderSpec order) {
        PaginatedList<SecurityLog> pgList = dao.findByPersonId(personId, pagingInfo, order);
        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
    }

    @Override
    public SecurityLogDto toWrapper(SecurityLog e) {
        SecurityLogDto w = new SecurityLogDto();
        BeanUtils.copyProperties(e, w);
        w.setPersonId(e.getPerson().getUserId());
        if (w.getDateTime()!=null){
            SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            w.setDateFormatJson(d.format(w.getDateTime()));
        }
        return w;
    }

    @Override
    public SecurityLog toEntity(SecurityLogDto securityLogDto) {
        SecurityLog s = new SecurityLog();
        BeanUtils.copyProperties(securityLogDto, s,"person","dateTime");
        s.setPerson(personDao.get(securityLogDto.getPersonId()));
        s.setDateTime(new Date());
        return s;
    }


    @Override
    @Transactional(readOnly = false)
    public SecurityLogDto save(SecurityLogDto dto) {
        return  toWrapper(dao.save(toEntity(dto)));
    }

    @Override
    @Transactional(readOnly = false)
    public SecurityLog save(SecurityLog securityLog) {
        return  dao.save(securityLog);
    }

    @Override
    @Transactional(readOnly = false)
    public SecurityLogDto saveLogging (Long status,String personUserId,HttpServletRequest request,String detailEvent,String userAffected){
        SecurityLog s = new SecurityLog();
        s.setDateTime(new Date());
        s.setIpAddress(getClientIp(request));
        s.setKeterangan(getDesc(status));
        s.setDetail(detailEvent);
        s.setAppName("Web Appraisal");
        s.setComputerName(request.getRemoteHost());
        s.setUserAffected(userAffected);
        s.setPerson(personDao.get(personUserId));
        s.setLogCode(status);
        return toWrapper(dao.save(s));

    }

    private String getDesc(Long id){
        Map<Long,String> m = new HashMap<Long, String>();
        m.put(AppConstans.SecurityLogConstans.LOGIN_SUCCESS,"Login Success");
        m.put(AppConstans.SecurityLogConstans.LOGIN_FAILURE,"Login Failure");
        m.put(AppConstans.SecurityLogConstans.LOGOUT,"Logout");
        m.put(AppConstans.SecurityLogConstans.USER_PROFILE_CREATION,"User ID Creation");
        m.put(AppConstans.SecurityLogConstans.USER_PROFILE_ACTIVATION,"User ID Unlocked");
        m.put(AppConstans.SecurityLogConstans.USER_PROFILE_DEACTIVATION,"User ID Locked");
        m.put(AppConstans.SecurityLogConstans.USER_PROFILE_DELETION,"User ID Deleted");
        m.put(AppConstans.SecurityLogConstans.USER_PROFILE_UNDELETE,"User ID Un-Delete");
        m.put(AppConstans.SecurityLogConstans.USER_PROFILE_RENAMING,"User ID Renamed");
        m.put(AppConstans.SecurityLogConstans.USER_PROFILE_CHANGE,"Change Profile");
        m.put(AppConstans.SecurityLogConstans.USER_ROLES_ADD,"Modification of user profile access rights");
        m.put(AppConstans.SecurityLogConstans.USER_ROLES_REMOVED,"Removal of user profile access rights");
        m.put(AppConstans.SecurityLogConstans.USER_ROLES_CHANGE,"Modification of user profile access rights Desc");
        m.put(AppConstans.SecurityLogConstans.SYSTEM_PARAMETER_CHANGE,"Change System Parameter");
        m.put(AppConstans.SecurityLogConstans.UNAUTHORIZE_ACCESS,"Unauthorize Access");
        m.put(AppConstans.SecurityLogConstans.LOGOUT_SESSION_EXPIRED,"Logout  session expired");
        return m.get(id);
    }

    private String getClientIp(HttpServletRequest request) {

        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }

}
