package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.common.LabelValue;
import com.permata.appraisal2.common.LabelValueId;
import com.permata.appraisal2.dao.PersonDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.model.Person;
import com.permata.appraisal2.model.PersonLevel;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class PersonDaoImpl extends DaoBaseImpl<Person, String> implements PersonDao {


    @Override
    protected Criteria createCriteria(Person example, OrderSpec orderSpec, String... excludedProperties) {
        Criteria criteria = super.createCriteria(example, orderSpec, excludedProperties);
        try {
            if (StringUtils.isNotBlank(example.getUserId())) {
                criteria.add(Restrictions.ilike("userId",  example.getUserId(),MatchMode.ANYWHERE));
            }
        } catch (Exception e) {
        }
        return criteria;
    }

    @Override
    public List<LabelValue<String, String>> findLvSurveyor() {
        List<LabelValue<String, String>> result = new ArrayList<LabelValue<String, String>>();
        String hql = "select distinct a.person from PersonRole a where a.level = ? ";
        List<Person> dataList = createQuery(hql).setParameter(0, PersonLevel.APPRAISAL_SURVEYOR).list();
        for (Person row : dataList) {
            result.add(new LabelValue<String, String>(row.getUserId(), row.getName()));
        }
        return result;
    }

    @Override
    public List<LabelValue<String, String>> personMarketing() {
        List<LabelValue<String, String>> result = new ArrayList<LabelValue<String, String>>();
        String hql = "select distinct a.person from PersonRole a where a.level = ? ";
        List<Person> dataList = createQuery(hql).setParameter(0, PersonLevel.MARKETING).list();
        for (Person row : dataList) {
            result.add(new LabelValue<String, String>(row.getUserId(), row.getName()));
        }
        return result;
    }

    @Override
    public List<LabelValue<String,String>> findMarketing(Long branchId) {
        List<LabelValue<String, String>> result = new ArrayList<LabelValue<String, String>>();
        String hql = "select distinct a.person from PersonRole a where a.level = ? and a.branch.id = ? ";
        List<Person> dataList = createQuery(hql).setParameter(0, PersonLevel.MARKETING).setParameter(1, branchId).list();
        for(Person row : dataList){
            result.add(new LabelValue<String, String>(row.getUserId(), row.getName()));
        }
        return result;
    }

    @Override
    public List<LabelValue<String,String>> findUserAppraisal(Enum divisi) {
        List<LabelValue<String, String>> result = new ArrayList<LabelValue<String, String>>();
        String hql = "select distinct a.person from PersonRole a where a.level = ? ";
        List<Person> dataList = createQuery(hql).setParameter(0, divisi).list();
        for(Person row : dataList){
            result.add(new LabelValue<String, String>(row.getUserId(), row.getName()));
        }
        return result;
    }

    @Override
    public List<LabelValueId<String, Boolean, String>> personValuerWithId() {
        List<LabelValueId<String, Boolean, String>> result = new ArrayList<LabelValueId<String, Boolean, String>>();
        String hql = "select distinct a.person from PersonRole a where a.level = ? ";
        List<Person> dataList = createQuery(hql).setParameter(0, PersonLevel.APPRAISAL_VALUER).list();
        for (Person row : dataList) {
            result.add(new LabelValueId<String, Boolean, String>(row.getUserId(), true, row.getName()));
        }
        return result;
    }




    @Override
    public void inActive(String userId,boolean action) throws Exception {
        String act ="Update Person set inactive=? where userId=? ";
        createQuery(act).setParameter(0,action).setParameter(1,userId).executeUpdate();


    }


    @Override
    public void active(String userId,boolean action) throws Exception {
        String act ="Update Person set inactive=?, errorCount=? where userId=?";
        createQuery(act).setParameter(0,action).setParameter(1,0).setParameter(2,userId).executeUpdate();
    }



    @Override
    public void undelete(String userId) throws Exception {
        String act ="Update Person set deleted = 0 where userId= ? ";
        createQuery(act).setParameter(0,userId).executeUpdate();
    }


    @Override
    public PaginatedList<Person> findDelete(PagingInfo pagingInfo, String userId) {
        String hql = "from Person where deleted=1";
            if (!StringUtils.isBlank(userId)){
                hql = hql.concat( "and lower(userId) like '%"+userId.toLowerCase()+"%' ");
            }

        return find(pagingInfo, createQuery(hql));
    }


    @Override
    public PaginatedList<Person> findByAppBranch(PagingInfo pagingInfo, Long id) {
        String hql = "from Person where userId in (select person.userId from PersonRole where appBranch.id = "+id+")";
        return find(pagingInfo, createQuery(hql));
    }

    @Override
    public PaginatedList<Person> findByBranch(PagingInfo pagingInfo, Long id) {
        String hql = "from Person where userId in (select person.userId from PersonRole where branch.id = "+id+")";
        return find(pagingInfo, createQuery(hql));
    }

    public PaginatedList<Person> getPersonActvity(Person sample, PagingInfo pagingInfo, OrderSpec orderSpec) {
        Criteria criteria = createCriteria(Person.class);
        if(StringUtils.isNotBlank(sample.getUserId())) {
            criteria.add(Restrictions.ilike("userId", sample.getUserId(), MatchMode.ANYWHERE));
        }
        if(sample.getInactive() != null) {
            criteria.add(Restrictions.eq("inactive", sample.getInactive()));
        }

        return executeFind(pagingInfo, criteria, Person.class);
    }


}