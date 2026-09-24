package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.IloanProgresDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.model.IloanProgres;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class IloanProgresDaoImpl extends DaoBaseImpl<IloanProgres, Long> implements IloanProgresDao {

    /**
     * pencarian data head saja
     *
     * @param pagingInfo
     * @param filter
     * @return
     */
    @Override
    public PaginatedList<IloanProgres> findHead(PagingInfo pagingInfo, IloanProgres filter) {
        Criteria crit = createCriteria(filter, null);
        crit.add(Restrictions.isNull("headId"));
        return find(pagingInfo, crit, null, null);
    }

    /**
     * hapus semua data progress
     *
     * @param id DATA_UMUM.ID
     */
    @Override
    @Transactional
    public void deleteProgressByCommonId(Long id) {
        createQuery("delete IloanProgres where dataCommon.id=" + id).executeUpdate();
    }

    /**
     * Update row HEAD saja
     *
     * @param commonId DATA_UMUM.ID
     */
    @Override
    public void updateHeadStatus(Long commonId) {
        createQuery("update IloanProgres set status = " + IloanProgres.STATUS_SEND_GAGAL + ", waktuKirim = NOW() where dataCommon.id=" + commonId + " and headId is null").executeUpdate();
    }

    /**
     * update semua status menjadi manual dan waktuKirim = sysdate
     *
     * @param commonId DATA_UMUM.ID
     */
    @Override
    public void sendManual(Long commonId) {
        createQuery("update IloanProgres set status = " + IloanProgres.STATUS_SEND_MANUAL + ", waktuKirim = NOW() where dataCommon.id=" + commonId).executeUpdate();
    }

    @Override
    public void sendManualCol(Long commonId) {
        createQuery("update IloanProgres set status = " + IloanProgres.STATUS_SEND_MANUAL + ", waktuKirim = NOW(),headId=null where dataCommon.id=" + commonId).executeUpdate();
    }

}