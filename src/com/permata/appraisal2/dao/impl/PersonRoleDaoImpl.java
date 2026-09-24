package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.dao.PersonRoleDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.model.PersonLevel;
import com.permata.appraisal2.model.PersonRole;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRoleDaoImpl extends DaoBaseImpl<PersonRole, Long> implements PersonRoleDao {

    @Override
    public PaginatedList<PersonRole> findByUserId(PagingInfo pagingInfo, String userId) {
        String hql = "from PersonRole where person.userId='" + userId + "'";
        return find(pagingInfo, createQuery(hql));
    }

    @Override
    public PaginatedList<PersonRole> findByAppBranch(PagingInfo pagingInfo, Long id) {
        String hql = "from PersonRole where appBranch.id=" + id;
        return find(pagingInfo, createQuery(hql));
    }

    @Override
    public PaginatedList<PersonRole> findByBranch(PagingInfo pagingInfo, Long id) {
        String hql = "from PersonRole where branch.id=" + id;
        return find(pagingInfo, createQuery(hql));
    }

    @Override
    public List<String> findPersonByRole() {
        String queryS ="select distinct a.nama from person a, \n" +
                "person_roles b \n" +
                "where a.user_id=b.user_id \n" +
                "and b.person_level in ('"+ PersonLevel.APPRAISAL_APPROVAL+"','"+PersonLevel.APPRAISAL_VALUER+"') "+ //APPRAISAL_APPROVAL','APPRAISAL_VALUER' ) \n" +
                "and a.nama is not null \n" +
                "order by a.nama asc";
        SQLQuery query = (SQLQuery)
                getSession().createSQLQuery(queryS);
        List result = query.list();
        if (result!=null && !result.isEmpty()){
            return result;
        }
        return null;

    }

}