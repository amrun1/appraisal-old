package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.DocumentLpaTypeDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.model.AssetType;
import com.permata.appraisal2.model.DataCommon;
import com.permata.appraisal2.model.DocumentLpaType;
import com.permata.appraisal2.model.JenisAppraisal;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DocumentLpaTypeDaoImpl extends DaoBaseImpl<DocumentLpaType, Long> implements DocumentLpaTypeDao {
    @Override
    protected Criteria createCriteria(DocumentLpaType example, OrderSpec orderSpec, String... excludedProperties) {
        Criteria criteria = super.createCriteria(example, orderSpec, excludedProperties);
        try {
            criteria.add(Restrictions.eq("assetType.id", example.getAssetType().getId()));
        } catch (Exception e) {
        }
        try {
            criteria.add(Restrictions.eq("appraisal.id", example.getAppraisal().getId()));
        } catch (Exception e) {
        }
        return criteria;
    }

    @Override
    public List<DocumentLpaType> findByActiva(Long id, Boolean survey, Long appraisalId) {
//        String hql = "from DocumentLpaType where 1=1 ";
//        if(id != null){
//            hql += "and assetType.id = :jenisAktiva ";
//        }
//        if (survey != null && survey) {
//            hql += "and surveyYn = :isSurvey ";
//        }
//        Query query = createQuery(hql).setParameter(0, id);
//        if(id != null){
//            query.setParameter("jenisAktiva", survey);
//        }
//        if (survey != null && survey) {
//            query.setParameter("isSurvey", survey);
//        }
//        return query.list();
        DocumentLpaType filter = new DocumentLpaType();
        if(id != null){
            filter.setAssetType(new AssetType(id));
        }
        if(appraisalId != null){
            filter.setAppraisal(new JenisAppraisal(appraisalId));
        }
        if (survey != null && survey) {
            filter.setSurveyYn(survey);
        }
        return find(filter);
    }

    @Override
    public List<DocumentLpaType> findByAppraisal(Long idAppraisal, Boolean survey) {
        String hql = "from DocumentLpaType where appraisal.id = ? ";
        if (survey != null && survey) {
            hql += "and surveyYn = ? ";
        }
        Query query = createQuery(hql).setParameter(0, idAppraisal);
        if (survey != null && survey) {
            query.setParameter(1, survey);
        }
        return query.list();
    }

    @Override
    public PaginatedList<DocumentLpaType> find(PagingInfo pgInfo, DocumentLpaType filter,OrderSpec order, Integer jenis) {

        Criteria criteria = createCriteria(filter, order);

        if(jenis!=2){
            criteria.add(Restrictions.isNotNull("appraisal"));
        }else{
            criteria.add(Restrictions.isNotNull("assetType"));
        }

        return find(pgInfo, criteria, order, null);
    }
}