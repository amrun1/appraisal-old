package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.model.IloanProgres;

public interface IloanProgresDao extends DaoBase<IloanProgres, Long> {

    public PaginatedList<IloanProgres> findHead(PagingInfo pagingInfo, IloanProgres filter);

    public void deleteProgressByCommonId(Long id);

    public void updateHeadStatus(Long commonId);

    public void sendManual(Long commonId);

    public void sendManualCol(Long commonId);

}