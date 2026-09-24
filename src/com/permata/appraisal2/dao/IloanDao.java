package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.model.Iloan;

import java.util.List;

public interface IloanDao extends DaoBase<Iloan, Long> {

    public PaginatedList<Iloan> lookup(PagingInfo pagingInfo, Iloan filter, OrderSpec order);

    public List<Iloan> findChildByHeadId(String collateralId, String groupVal);

    public void trancate();

}