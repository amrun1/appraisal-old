package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.model.PersonRole;

import java.util.List;

public interface PersonRoleDao extends DaoBase<PersonRole, Long> {

    public PaginatedList<PersonRole> findByUserId(PagingInfo pagingInfo, String userId);

    public PaginatedList<PersonRole> findByAppBranch(PagingInfo pagingInfo, Long id);

    public PaginatedList<PersonRole> findByBranch(PagingInfo pagingInfo, Long id);

    public List<String> findPersonByRole();

}
