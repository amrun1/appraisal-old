package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.model.SecurityLog;

public interface SecurityLogDao extends DaoBase<SecurityLog, Long> {
    public PaginatedList<SecurityLog> findByPersonId(String personId, PagingInfo pagingInfo, OrderSpec order);
    public SecurityLog getLogDetail(String userId,Long logCode);
}