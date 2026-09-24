package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.LpaUmumDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.dto.SurveyImageDto;
import com.permata.appraisal2.model.LpaUmum;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LpaUmumDaoImpl extends DaoBaseImpl<LpaUmum, Long> implements LpaUmumDao {

    @Override
    public boolean isDetailHasLpa(Long detailId) {
        String hql = "select count(id) from LpaUmum where detail.id=?";
        Long count = (Long) createQuery(hql).setParameter(0, detailId).uniqueResult();
        if (count > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isDraftLpa(Long detailId) {
        String hql = "select draft from LpaUmum where detail.id=?";
        Object bool = createQuery(hql).setParameter(0, detailId).uniqueResult();
        return (bool == null ? true : (Boolean) bool);
    }

    @Override
    public LpaUmum getByDetail(Long detailId) {
        String hql = "from LpaUmum where detail.id=?";
        return (LpaUmum) createQuery(hql).setParameter(0, detailId).uniqueResult();
    }


    public List<SurveyImageDto> getDocOther(Long idUmum){
        String queryS = "select b.id as ids, b.content_type as content , b.catatan as catatan " +
                "from lpa_umum a " +
                "left outer join ( " +
                "select b.id, b.content_type, b.catatan, b.id_survey_umum, b.id_lpa, c.nama from survey_image b " +
                "left outer join jenis_laporan_dokumen c on b.id_laporan_dokumen = c.id " +
                "where c.jenis_data!='Peta/Denah' " +
                ") as b on a.id_survey = b.id_survey_umum or a.id = b.id_lpa where a.id ="+idUmum+" order by B.nama ";

        return getSession().createSQLQuery(queryS).setResultTransformer(Transformers.aliasToBean(SurveyImageDto.class)).list();


    }

}