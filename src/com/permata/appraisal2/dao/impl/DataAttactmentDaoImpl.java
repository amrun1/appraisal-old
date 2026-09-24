package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.DataAttactmentDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.model.DataAttactment;
import com.permata.appraisal2.model.DataCommon;
import com.permata.appraisal2.model.DataDetail;
import com.permata.appraisal2.ws.client.carm.Attachment;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Repository
public class DataAttactmentDaoImpl extends DaoBaseImpl<DataAttactment, Long> implements DataAttactmentDao {


    @Override
    protected Criteria createCriteria(DataAttactment example, OrderSpec orderSpec, String... excludedProperties) {
        Criteria criteria = super.createCriteria(example, orderSpec, excludedProperties);
        try {
            if (example.getDataDetail().getId() != null) {
                criteria.add(Restrictions.eq("dataDetail.id", example.getDataDetail().getId()));
            }
        } catch (Exception e) {
            // biarkan jika branch tidak ada isinya
        }
        return criteria;
    }

    @Override
    public PaginatedList<DataAttactment> find(PagingInfo pagingInfo, DataAttactment sample, String... excludedProperties) {
        Criteria criteria = createCriteria(sample, null, excludedProperties);
        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("id"), "id")
                .add(Projections.property("name"), "name")
                .add(Projections.property("documentType"), "documentType")
                .add(Projections.property("dataDetail"), "dataDetail")
                .add(Projections.property("extension"), "extension"))
                .setResultTransformer(Transformers.aliasToBean(DataAttactment.class));
        return executeFind(pagingInfo, criteria, null);
    }

    @Override
    public Long countByDetailId(Long detailId) {
        if(detailId != null) {
            String hql = "select count(id) from DataAttactment where dataDetail.id=?";
            return (Long) createQuery(hql).setParameter(0, detailId).uniqueResult();
        } else {
            return 0L;
        }
    }



    @Override
    public DataCommon getDataUmumByNameFile(String namaFile) {

        String hql = "from DataAttactment a  where  lower(a.name) ='"+namaFile.toLowerCase()+"'";
        List<DataAttactment> listAttach = find(createQuery(hql));
        if (listAttach!=null && !listAttach.isEmpty()){
            DataAttactment d = listAttach.get(0);
                if (d.getDataDetail()!=null){
                    DataDetail detail = d.getDataDetail();
                    if (detail.getDataCommon()!=null){
                        DataCommon dataCommon = detail.getDataCommon();
                        return dataCommon;
                    }
                }
        }

        return null;
    }

    @Override
    public List<DataAttactment> getAttachmentsWithoutBlob(DataDetail dataDetail) {
        DataAttactment example = new DataAttactment();
        example.setDataDetail(dataDetail);
        Criteria criteria = createCriteria(example, null);
        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("id"), "id")
                .add(Projections.property("name"), "name")
                .add(Projections.property("documentType"), "documentType")
                .add(Projections.property("dataDetail"), "dataDetail")
                .add(Projections.property("extension"), "extension"))
                .setResultTransformer(Transformers.aliasToBean(DataAttactment.class));

        List<DataAttactment> list = criteria.list();
        return list;
//        String hql = "select d.id, d.name, d.extension, d.documentType from DataAttactment d where d.dataDetail.id = ?";
////        List<DataAttactment> listAttach = find(createQuery(hql));
//        return createQuery(hql).setParameter(0,idDataDetail).list();
    }
}