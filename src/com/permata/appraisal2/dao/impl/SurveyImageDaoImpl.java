package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.SurveyImageDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.model.SurveyCommon;
import com.permata.appraisal2.model.SurveyImage;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SurveyImageDaoImpl extends DaoBaseImpl<SurveyImage, Long> implements SurveyImageDao {

    @Override
    protected Criteria createCriteria(SurveyImage example, OrderSpec orderSpec, String... excludedProperties) {
        Criteria criteria = super.createCriteria(example, orderSpec, excludedProperties);
        try {
            if (example.getCommon().getId() != null) {
                criteria.add(Restrictions.eq("common.id", example.getCommon().getId()));
            }
        } catch (Exception e) {
            // biarkan jika branch tidak ada isinya
        }
        return criteria;
    }

    @Override
    public List<SurveyImage> findBySurvey(Long id) {
//        String hql = "from SurveyImage where common.id = ?";
//        return find(createQuery(hql).setParameter(0, id));
        SurveyImage sample = new SurveyImage();
        SurveyCommon surveyCommon = new SurveyCommon();
        surveyCommon.setId(id);
        sample.setCommon(surveyCommon);
        Criteria criteria = createCriteria(sample, null);
        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("id"), "id")
                .add(Projections.property("common"), "common")
                .add(Projections.property("lpaId"), "lpaId")
                .add(Projections.property("documentLpaType"), "documentLpaType")
                .add(Projections.property("contentType"), "contentType")
                .add(Projections.property("catatan"), "catatan"))
                .setResultTransformer(Transformers.aliasToBean(SurveyImage.class));
        return criteria.list();
    }

//    @Override
//    public List<SurveyImage> findBySurveyXLS(Long id) {
//        String hql = "from SurveyImage where contentType = 'application/vnd.ms-excel' and lpaId = ?";
//        return find(createQuery(hql).setParameter(0, id));
//    }

    @Override
    public List<SurveyImage> findByLpa(Long id) {
//        String hql = "from SurveyImage where lpaId = ?";
//        return find(createQuery(hql).setParameter(0, id));
        SurveyImage sample = new SurveyImage();
        sample.setLpaId(id);
        Criteria criteria = createCriteria(sample, null);
        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("id"), "id")
                .add(Projections.property("common"), "common")
                .add(Projections.property("lpaId"), "lpaId")
                .add(Projections.property("documentLpaType"), "documentLpaType")
                .add(Projections.property("contentType"), "contentType")
                .add(Projections.property("catatan"), "catatan"))
                .setResultTransformer(Transformers.aliasToBean(SurveyImage.class));
        return criteria.list();
    }


    @Override
    public List<SurveyImage> findWorkshetByLpa(Long id) {
        String hql = "from SurveyImage where lpaId = ? and documentLpaType.jenisData='Worksheet'";
        return find(createQuery(hql).setParameter(0, id));
    }

    @Override
    public List<SurveyImage> findDokPelengkapByLpa(Long id) {
        String hql = "from SurveyImage where lpaId = ? and documentLpaType.jenisData='Dokumen Pelengkap'";
        return find(createQuery(hql).setParameter(0, id));
    }

    @Override
    public List<SurveyImage> findByLpaOrSurvey(Long survey, Long lpa) {
        Criteria criteria = createCriteria(SurveyImage.class);
        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("id"), "id")
                .add(Projections.property("common"), "common")
                .add(Projections.property("lpaId"), "lpaId")
                .add(Projections.property("documentLpaType"), "documentLpaType")
                .add(Projections.property("contentType"), "contentType")
                .add(Projections.property("catatan"), "catatan"))
                .add(Restrictions.or(Restrictions.eq("common.id", survey),Restrictions.eq("lpaId", lpa)))
                .setResultTransformer(Transformers.aliasToBean(SurveyImage.class));
        return criteria.list();
//        String hql = "from SurveyImage where lpaId = ? OR common.id = ?";
//        return find(createQuery(hql).setParameter(0, lpa).setParameter(1, survey));
    }
}