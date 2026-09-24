package com.permata.appraisal2.dao.common;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.model.EntityBase;
import com.permata.appraisal2.model.EntityStatusBase;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DaoBaseImpl<ET extends EntityBase<ID>, ID extends Serializable> implements DaoBase<ET, ID> {

    private Class<ET> entityClass;
    protected Logger logger = Logger.getLogger(getClass());

    @Autowired
    @Qualifier(value = "sessionFactory")
    private SessionFactory sessionFactory;
    private final List<CriteriaVisitor<ET>> visitors = new LinkedList<CriteriaVisitor<ET>>();

    @SuppressWarnings("unchecked")
    public DaoBaseImpl() {
        Type[] actualTypeArguments = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
        entityClass = (Class<ET>) actualTypeArguments[0];
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected List<CriteriaVisitor<ET>> getVisitors() {
        return visitors;
    }

    protected Criteria createCriteria(Class<?> clazz) {
        return createCriteria(clazz, null);
    }

    protected Criteria createCriteria(Class<?> clazz, OrderSpec orderSpec) {
        Criteria criteria = createOrder(getSession().createCriteria(clazz), orderSpec);
        if (SoftDeleteSupport.class.isAssignableFrom(clazz)) {
            criteria.add(Restrictions.or(Restrictions.isNull("deleted"),
                    Restrictions.ne("deleted", EntityStatusBase.YES)));
        }

        return criteria;
    }

    protected Criteria createCriteria(ET example, OrderSpec orderSpec, String... excludedProperties) {
        Criteria criteria = createCriteria(example.getClass(), orderSpec);

        if (example != null) {
            Example ex = ExampleHelper.createExample(example, excludedProperties);
            criteria.add(ex.enableLike(MatchMode.ANYWHERE).ignoreCase());
            List<CriteriaVisitor<ET>> v = getVisitors();
            for (CriteriaVisitor<ET> criteriaVisitor : v) {
                criteriaVisitor.visit(criteria, example, excludedProperties);
            }
        }
        return criteria;
    }

    protected Criteria createCriteriaExact(ET example, OrderSpec orderSpec, String... excludedProperties) {
        Criteria criteria = createCriteria(example.getClass(), orderSpec);

        if (example != null) {
            Example ex = ExampleHelper.createExample(example, excludedProperties);
            criteria.add(ex.enableLike(MatchMode.EXACT).ignoreCase());
            List<CriteriaVisitor<ET>> v = getVisitors();
            for (CriteriaVisitor<ET> criteriaVisitor : v) {
                criteriaVisitor.visit(criteria, example, excludedProperties);
            }
        }
        return criteria;
    }

    private Criteria createOrder(Criteria criteria, OrderSpec orderSpec) {
        if (null != orderSpec) {
            List<Order> orderList = OrderSpecTranslator.asOrders(orderSpec);
            for (Order order : orderList) {
                criteria.addOrder(order);
            }
        }
        return criteria;
    }

    protected Query createQuery(String query) {
        return createQuery(query, null);
    }

    protected Query createQuery(String query, OrderSpec orderSpec) {
        if (null == orderSpec) {
            return getSession().createQuery(query);
        }
        return getSession().createQuery(query + " order by " + OrderSpecTranslator.asString(orderSpec));
    }

    protected String buildSearchText(String searchText) {
        return buildSearchText(searchText, true);
    }

    protected String buildSearchText(String searchText, boolean enableLike) {
        String s = null == searchText ? "" : searchText.toUpperCase();
        if (enableLike) {
            return "%" + s + "%";
        }
        return s;
    }

    private <T> PaginatedList<T> executeFind(PagingInfo pagingInfo, Query query, Class<T> transformTo) {
        if (null != transformTo) {
            query.setResultTransformer(Transformers.aliasToBean(transformTo));
        }
        return paginated(new PaginatedList<T>(pagingInfo), query);
    }

    private <T> PaginatedList<T> executeFind(PagingInfo pagingInfo, Query query, Query queryRowCount,
                                             Class<T> transformTo) {
        if (null != transformTo) {
            query.setResultTransformer(Transformers.aliasToBean(transformTo));
        }
        return paginated(new PaginatedList<T>(pagingInfo), query, queryRowCount);
    }

    // private int scrollResults(@SuppressWarnings("rawtypes") List dataList,
    // int firstRowIdx, int rowSize,
    // ScrollableResults sr) {
    // if (sr.setRowNumber(firstRowIdx)) {
    // int i = 0;
    // do {
    // dataList.add(sr.get(0));
    // } while (++i < rowSize && sr.next());
    // }
    // sr.last();
    // return sr.getRowNumber() + 1;
    // }

    /**
     * @param <T>
     * @param paginatedList
     * @param query
     * @return returns empty list if query has 0 result
     */
    @SuppressWarnings("unchecked")
    private <T> PaginatedList<T> paginated(PaginatedList<T> paginatedList, Query query) {
        List<T> dataList = new ArrayList<T>();
        int totalRowCount = 0;
        if (paginatedList.getPagingInfo().isRetrieveAll()) {
            dataList = (List<T>) query.list();
            totalRowCount = dataList.size();
        } else {
            /*
             * ScrollableResults sr =
			 * query.scroll(ScrollMode.SCROLL_INSENSITIVE); totalRowCount =
			 * scrollResults(dataList,
			 * paginatedList.getPagingInfo().getFirstRowIndex(),
			 * paginatedList.getPagingInfo().getPageSize(), sr); sr.close();
			 */
            Query queryRowCount = createQuery("select count(*) " + query.getQueryString().substring(query.getQueryString().indexOf("from"), query.getQueryString().length()));
            totalRowCount = ((Long) queryRowCount.uniqueResult()).intValue();
            query.setFirstResult(paginatedList.getPagingInfo().getFirstRowIndex());
            query.setMaxResults(paginatedList.getPagingInfo().getPageSize());
            dataList = (List<T>) query.list();
        }

        if (0 < totalRowCount) {
            paginatedList.setTotalRowCount((long) totalRowCount);
            return paginatedList.cloneAndUpdateList(dataList);
        }

        return PaginatedList.createEmptyList();
    }

    @SuppressWarnings("unchecked")
    private <T> PaginatedList<T> paginated(PaginatedList<T> paginatedList, Query query, Query queryRowCount) {
        List<T> dataList = new ArrayList<T>();
        int totalRowCount = 0;
        if (paginatedList.getPagingInfo().isRetrieveAll()) {
            dataList = (List<T>) query.list();
            totalRowCount = dataList.size();
        } else {
            totalRowCount = ((Long) queryRowCount.uniqueResult()).intValue();
            query.setFirstResult(paginatedList.getPagingInfo().getFirstRowIndex());
            query.setMaxResults(paginatedList.getPagingInfo().getPageSize());
            dataList = (List<T>) query.list();
        }

        if (0 < totalRowCount) {
            paginatedList.setTotalRowCount((long) totalRowCount);
            return paginatedList.cloneAndUpdateList(dataList);
        }

        return PaginatedList.createEmptyList();
    }

    /**
     * @param <T>
     * @param paginatedList
     * @param criteria
     * @return returns empty list if query has 0 result
     */
    @SuppressWarnings("unchecked")
    private <T> PaginatedList<T> paginated(PaginatedList<T> paginatedList, Criteria criteria) {
        List<T> dataList = new ArrayList<T>();
        int totalRowCount = 0;
        if (paginatedList.getPagingInfo().isRetrieveAll()) {
            dataList = (List<T>) criteria.list();
            totalRowCount = dataList.size();
        } else {
            totalRowCount = getRowCount(criteria).intValue();
            criteria.setFirstResult(paginatedList.getPagingInfo().getFirstRowIndex());
            criteria.setMaxResults(paginatedList.getPagingInfo().getPageSize());
            dataList = (List<T>) criteria.list();
            /*
             * ScrollableResults sr =
			 * criteria.scroll(ScrollMode.SCROLL_INSENSITIVE); totalRowCount =
			 * scrollResults(dataList,
			 * paginatedList.getPagingInfo().getFirstRowIndex(),
			 * paginatedList.getPagingInfo().getPageSize(), sr); sr.close();
			 */
        }
        if (0 < totalRowCount) {
            paginatedList.setTotalRowCount((long) totalRowCount);
            return paginatedList.cloneAndUpdateList(dataList);
        }
        return PaginatedList.createEmptyList();
    }

    protected Long getRowCount(final Criteria criteria) {
        Long count = 0L;
        Iterator<Order> orderIter = ((CriteriaImpl)criteria).iterateOrderings();
        List temp = null;
        if(orderIter != null) {
            temp = new ArrayList();
            while (orderIter.hasNext()) {
                temp.add(orderIter.next());
                orderIter.remove();
            }
        }
        criteria.setProjection(Projections.rowCount());
        try {
            count = (Long) criteria.uniqueResult();
        } catch (Exception e) {
            // do nothing
        } finally {
            criteria.setProjection(null);
            if(temp != null){
                for (Object order : temp){
                    criteria.addOrder(((CriteriaImpl.OrderEntry) order).getOrder());
                }
            }
        }
        return count;
    }

    final protected <T> PaginatedList<T> executeFind(PagingInfo pagingInfo, Criteria criteria, Class<T> transformTo) {
        if (null != transformTo) {
            criteria.setResultTransformer(Transformers.aliasToBean(transformTo));
        }
        return paginated(new PaginatedList<T>(pagingInfo), criteria);
    }

    @SuppressWarnings("unchecked")
    public ET get(ID id) {
        return (ET) getSession().get(entityClass, id);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Transactional
    public ET delete(ID id) {
        try {
            ET entity = (ET) getSession().load(entityClass, id);
            if (SoftDeleteSupport.class.isAssignableFrom(entity.getClass())) {
                ((SoftDeleteSupport) entity).setDeleted(EntityStatusBase.YES);
                return save(entity);
                /*if (((EntityStatusBase) entity).getStatusData().equals("0")) {
                    getSession().delete(entity);
				} else {
					((EntityStatusBase) entity).setStatusData("3");
				}*/
            } else {
                getSession().delete(entity);
            }
            return entity;
        } catch (HibernateException e) {
            throw SessionFactoryUtils.convertHibernateAccessException(e);
        }
    }

    @Transactional
    public ET save(ET entity) {
        try {
            getSession().saveOrUpdate(entity);
            return entity;
        } catch (HibernateException e) {
            throw SessionFactoryUtils.convertHibernateAccessException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<ET> find(ET sample, String... excludedProperties) {
        return createCriteria(sample, null, excludedProperties).list();

    }

    @SuppressWarnings("unchecked")
    public List<ET> find(ET sample, OrderSpec orderSpec, String... excludedProperties) {
        return createCriteria(sample, orderSpec, excludedProperties).list();
    }

    public PaginatedList<ET> find(PagingInfo pagingInfo, ET sample, String... excludedProperties) {
        return executeFind(pagingInfo, createCriteria(sample, null, excludedProperties), null);
    }

    public PaginatedList<ET> findExact(PagingInfo pagingInfo, ET sample, String... excludedProperties) {
        return executeFind(pagingInfo, createCriteriaExact(sample, null, excludedProperties), null);
    }

    @SuppressWarnings("unchecked")
    public List<ET> findExact(ET sample, String... excludedProperties) {
        return createCriteriaExact(sample, null, excludedProperties).list();
    }

    public PaginatedList<ET> find(PagingInfo pagingInfo, ET sample, OrderSpec orderSpec, String... excludedProperties) {
        return executeFind(pagingInfo, createCriteria(sample, orderSpec, excludedProperties), null);
    }

    @SuppressWarnings("unchecked")
    protected List<ET> find(Query query) {
        return query.list();
    }

    protected <T> PaginatedList<T> find(PagingInfo pagingInfo, Query query) {
        return find(pagingInfo, query, null);
    }

    protected <T> PaginatedList<T> find(PagingInfo pagingInfo, Query query, Class<T> transformTo) {
        return executeFind(pagingInfo, query, transformTo);
    }

    protected <T> PaginatedList<T> find(PagingInfo pagingInfo, Query query, Query queryRowCount, Class<T> transformTo) {
        return executeFind(pagingInfo, query, queryRowCount, transformTo);
    }

    @SuppressWarnings("unchecked")
    protected List<ET> find(Criteria criteria) {
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    protected List<ET> find(Criteria criteria, OrderSpec orderSpec) {
        createOrder(criteria, orderSpec);
        return criteria.list();
    }

    protected <T> PaginatedList<T> find(PagingInfo pagingInfo, Criteria criteria, OrderSpec orderSpec, Class<T> transformTo) {
        createOrder(criteria, orderSpec);
        return executeFind(pagingInfo, criteria, transformTo);
    }

    public boolean isExists(ID id) {
        return get(id) != null;
    }

    public void setEntityClass(Class<ET> entityClass) {
        this.entityClass = entityClass;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean detach(ET sample) {
        getSession().evict(sample);
        getSession().clear();
        getSession().close();
        return false;
    }
}