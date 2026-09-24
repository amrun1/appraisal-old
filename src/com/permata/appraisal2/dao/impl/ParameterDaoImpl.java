package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.ParameterDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.model.Parameter;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class ParameterDaoImpl extends DaoBaseImpl<Parameter, Long> implements ParameterDao {

    @Override
    protected Criteria createCriteria(Parameter example, OrderSpec orderSpec, String... excludedProperties) {
        Criteria criteria = super.createCriteria(example, orderSpec, excludedProperties);
        try {
            if (StringUtils.isNotBlank(example.getGroup().getCode())) {
                criteria.add(Restrictions.eq("group.code", example.getGroup().getCode()));
            }
        } catch (Exception e) {
        }
        // TODO hendra.. nanti di buka lgi kalau sudah bisa sorting
        // criteria.addOrder(Order.asc("sorting"));
        return criteria;
    }

    @Override
    public List<Parameter> findByGroup(String groupCode) {
        String hql = "from Parameter where group.code=? and active is true order by sorting asc";
        return find(createQuery(hql).setParameter(0, groupCode));
    }

    @Override
    public List<Parameter> findByCodeAndGroup(String code, String groupCode) {
        String hql = "from Parameter where group.code=? and code=?";
        return find(createQuery(hql).setParameter(0, groupCode).setParameter(1, code));
    }

    public String findRVBVFotopada(String codeGroup, Integer sort){
        String hql = "select value from Parameter where group.code=? and sorting=? ";
        return (String) createQuery(hql).setParameter(0, codeGroup).setParameter(1, sort).uniqueResult();
    }






}