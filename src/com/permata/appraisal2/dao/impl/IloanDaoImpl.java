package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.IloanDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.model.Iloan;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IloanDaoImpl extends DaoBaseImpl<Iloan, Long> implements IloanDao {

    @Override
    public PaginatedList<Iloan> lookup(PagingInfo pagingInfo, Iloan filter, OrderSpec order) {


        String hql = "from Iloan a where exists (select 1 from IloanGroup b where a.id = b.id)";
        if (StringUtils.isNotBlank(filter.getName())) {
            hql += " and lower(a.name) like '%" + filter.getName().toLowerCase() + "%'";
        }
        if (StringUtils.isNotBlank(filter.getCollateralId())) {
            hql += " and lower(a.collateralId) like '%" + filter.getCollateralId().toLowerCase() + "%'";
        }
        if (StringUtils.isNotBlank(filter.getGroupVal())) {
            hql += " and lower(a.groupVal) like '%" + filter.getGroupVal().toLowerCase() + "%'";
        }
        if (StringUtils.isNotBlank(filter.getTitleNumber())) {
            hql += " and lower(a.titleNumber) like '%" + filter.getTitleNumber().toLowerCase() + "%'";
        }
        if (StringUtils.isNotBlank(filter.getLocation())) {
            hql += " and lower(a.location) like '%" + filter.getLocation().toLowerCase() + "%'";
        }

        if (filter.getPriority()!=null && filter.getPriority().getId()!=null){
            hql += " and a.priority.id = " + filter.getPriority().getId() + "";
        }

        if (filter.getMaturDate()!=null && filter.getCreatedDate()!=null){

            hql += " and a.maturDate between '"+filter.getMaturDate()+"' and '"+filter.getCreatedDate()+"'" ;
        }


        hql += " and not exists(select 1 from IloanProgres b where a.groupVal = b.groupVal and a.collateralId = b.collateralId) ";

        String strOrder = " order by a.outstanding desc";
//        if(order != null){
//            strOrder += ", a." + Order
//        }
        return find(pagingInfo, createQuery(hql + strOrder), createQuery("select count(*) " + hql), null);
    }

    @Override
    public List<Iloan> findChildByHeadId(String collateralId, String groupVal) {
        String hql = "from Iloan t where not exists (select 1 from IloanGroup b where t.id = b.id) and (t.collateralId = '" + collateralId +
                "' or t.groupVal = '" + collateralId + "'";
        if (StringUtils.isNotBlank(groupVal)) {
            hql += " or t.collateralId = '" + groupVal + "' or t.groupVal = '" + groupVal + "'";
        }
        hql += ")";
        Query query = createQuery(hql);
        return query.list();
    }

    @Override
    public void trancate() {
        String sql = "delete from Iloan";
        createQuery(sql).executeUpdate();
    }

}