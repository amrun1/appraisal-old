package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.LpaInspeksiDao;
import com.permata.appraisal2.dao.common.CriteriaVisitor;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.dao.common.ExampleHelper;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.model.LpaInspeksi;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SB on 4/17/2018.
 */
@Repository
public class LpaInspeksiDaoImpl extends DaoBaseImpl<LpaInspeksi, Long> implements LpaInspeksiDao {
    @Override
    public LpaInspeksi getByUmum(Long lpaUmum) {
        String hql = "from LpaInspeksi where lpaUmum.id=? ";
        return (LpaInspeksi) createQuery(hql).setParameter(0, lpaUmum).uniqueResult();
    }
    @Override
    protected Criteria createCriteria(LpaInspeksi example, OrderSpec orderSpec, String... excludedProperties) {
        Criteria criteria = createCriteria(example.getClass(), orderSpec);
        if (example != null) {
            Example ex = ExampleHelper.createExample(example, excludedProperties);
            criteria.add(ex.ignoreCase());
            List<CriteriaVisitor<LpaInspeksi>> v = getVisitors();
            for (CriteriaVisitor<LpaInspeksi> criteriaVisitor : v) {
                criteriaVisitor.visit(criteria, example, excludedProperties);
            }
        }
        try {
            if (example.getLpaUmum().getId() != null) {
                criteria.add(Restrictions.eq("lpaUmum.id", example.getLpaUmum().getId()));
            }
        } catch (Exception e) {
        }
        return criteria;
    }

    @Override
    public void copyDataItemInspeksiFromSurvey(Long idLpaInspeksi, Long idSurveyInspeksi) {
        // notes : using native sql to avoid creating in memmory object from copy to another entity
        String sqlQuery = "INSERT INTO LPA_INSPEKSI_ITEM(id, nama, total_doc, total_fisik, tipe, id_lpa_inspeksi) " +
                "SELECT NEXTVAL('LPA_INSPEKSI_ITEM_SEQ'), SURVEY_INSPEKSI_ITEM.nama, SURVEY_INSPEKSI_ITEM.total_doc, SURVEY_INSPEKSI_ITEM.total_fisik, SURVEY_INSPEKSI_ITEM.tipe, "+idLpaInspeksi+
                " FROM SURVEY_INSPEKSI_ITEM WHERE ID_SURVEY_INSPEKSI = " + idSurveyInspeksi;
        getSession().createSQLQuery(sqlQuery).executeUpdate();
    }
}
