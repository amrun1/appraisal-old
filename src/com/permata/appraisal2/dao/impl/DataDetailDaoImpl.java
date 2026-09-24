package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.DataDetailDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.model.DataCommon;
import com.permata.appraisal2.model.DataDetail;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Repository
public class DataDetailDaoImpl extends DaoBaseImpl<DataDetail, Long> implements DataDetailDao {
    @Override
    public void updatePicByCommonId(Long id, String picName) {
        createQuery("update DataDetail set picName=? where dataCommon.id =?  ").setParameter(0,picName).setParameter(1,id).executeUpdate();
    }

    public List<DataDetail> findByFileName (String fileName) {
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -6);
        Timestamp timestamp = new Timestamp(startDate.getTime().getTime());
        String sql = "Select distinct(id_data_detail)\n" +
                "                from data_attact da\n" +
                "        join data_detail dd on dd.id = da.id_data_detail" +
                "        join data_umum du on du.id = dd.id_data_umum" +
                "        where da.nama = '"+fileName+"'" +
                "        and du.created_date > '"+timestamp+"'";
        List idDataDetails = getSession().createSQLQuery(sql).list();
        List<DataDetail> list = new ArrayList<DataDetail>();
        for(Object item:idDataDetails) {
            list.add(get(Long.parseLong(item.toString())));
        }
        return list;
    }



    public boolean gotAttachment(Long id) {
        Query query = createQuery("select count(id) from DataAttactment where dataDetail.id =" + id);
        Integer totalAttach = ((Long) query.uniqueResult()).intValue();
        return totalAttach>0?true:false;
    }
}
