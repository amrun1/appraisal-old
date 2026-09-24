package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.SecurityLogDao;
import com.permata.appraisal2.dao.common.*;
import com.permata.appraisal2.model.Person;
import com.permata.appraisal2.model.SecurityLog;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SecurityLogDaoImpl extends DaoBaseImpl<SecurityLog, Long> implements SecurityLogDao {

    @Override
    public PaginatedList<SecurityLog> findByPersonId(String personId, PagingInfo pagingInfo, OrderSpec order) {
        SecurityLog log = new SecurityLog();
        Person p = new Person();
        p.setUserId(personId);
        log.setPerson(p);
        order = OrderSpec.create("dateTime", OrderSpec.OrderType.desc);
        Criteria criteria = createCriteria(log,null);

        return find(pagingInfo, criteria,order,SecurityLog.class);
    }


    @Override
    public SecurityLog getLogDetail(String userId,Long logCode) {
        String hql = "from SecurityLog a where a.person.userId=? and a.logCode =? order by a.dateTime desc";

        String sql = "select substring(keterangan,14) as keterangan \n" +
                "from security_log \n" +
                "where person_userid=? \n" +
                "and log_code =? \n" +
                "order by date_time desc \n" +
                "fetch first row only ";
       // List<SecurityLog> ls = find(createQuery(hql).setParameter(0, userId).setParameter(1,logCode));
        //return ls.isEmpty()?null:ls.get(0);
        return (SecurityLog) getSession().createSQLQuery(sql).setParameter(0,userId).setParameter(1,logCode).setResultTransformer(Transformers.aliasToBean(SecurityLog.class)).uniqueResult();
    }


    @Override
    protected Criteria createCriteria(SecurityLog example, OrderSpec orderSpec, String... excludedProperties) {
        Criteria criteria = createCriteria(example.getClass(), orderSpec);
        if (example != null) {
            Example ex = ExampleHelper.createExample(example, excludedProperties);
            criteria.add(ex.ignoreCase());
            List<CriteriaVisitor<SecurityLog>> v = getVisitors();
            for (CriteriaVisitor<SecurityLog> criteriaVisitor : v) {
                criteriaVisitor.visit(criteria, example, excludedProperties);
            }
        }
        try {
            if (example.getPerson().getUserId() != null) {
                criteria.add(Restrictions.eq("person.userId", example.getPerson().getUserId()));
            }
        } catch (Exception e) {
        }
        return criteria;
    }

}