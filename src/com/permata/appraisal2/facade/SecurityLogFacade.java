package com.permata.appraisal2.facade;

import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.SecurityLogDto;
import com.permata.appraisal2.model.SecurityLog;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

public interface SecurityLogFacade {
    public PaginatedList<SecurityLogDto> find(String personId, PagingInfo pagingInfo, OrderSpec order);

    public SecurityLogDto save(SecurityLogDto dto);
    SecurityLog save(SecurityLog securityLog);
    SecurityLogDto saveLogging (Long status, String personUserId, HttpServletRequest request, String detailEvent, String userAffected);

}