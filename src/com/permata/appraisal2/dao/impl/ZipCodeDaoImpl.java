package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.ZipCodeDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.model.ZipCode;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ZipCodeDaoImpl extends DaoBaseImpl<ZipCode, Long> implements ZipCodeDao {

//    @Override
//    protected Criteria createCriteria(ZipCode example, OrderSpec orderSpec, String... excludedProperties) {
//        Criteria criteria = super.createCriteria(example, orderSpec, excludedProperties);
//        try {
//            logger.debug("ZIP CODE " + example.getCode());
//            if (example.getCode() != null) {
//                criteria.add(Restrictions.ilike("code", example.getCode(), MatchMode.ANYWHERE));
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        return criteria;
//    }

    @Override
    public List<String> findPropinsi() {
        String hql = "select distinct province from ZipCode order by province asc";
        return createQuery(hql).list();
    }

    @Override
    public List<String> getAllPic() {
        String hql = "select distinct picName from ZipCode where picName is not null order by picName asc";
        return createQuery(hql).list();
    }

    @Override
    public List<String> findKota(String propinsi) {
        String hql = "select distinct city from ZipCode where province=? order by city asc";
        return createQuery(hql).setParameter(0, propinsi).list();
    }

    @Override
    public List<String> findKecamatan(String propinsi, String kota) {
        String hql = "select distinct district from ZipCode where province=? and city=? order by district asc";
        return createQuery(hql).setParameter(0, propinsi).setParameter(1, kota).list();
    }

    @Override
    public int updatePicPerKota(String province, String city, String picName) {
        String hql = "update ZipCode set picName=? where province=? and city=?";
        return createQuery(hql).setParameter(0, picName).setParameter(1, province).setParameter(2, city).executeUpdate();
    }

    @Override
    public int updatePicPerKecamatan(String province, String city, String district, String picName) {
        String hql = "update ZipCode set picName=? where province=? and city=? and district=?";
        return createQuery(hql).setParameter(0, picName).setParameter(1, province).setParameter(2, city)
                .setParameter(3, district).executeUpdate();
    }

    @Override
    public List<String> findPic() {
        String hql = "select distinct picName from ZipCode order by picName";
        return createQuery(hql).list();
    }

}
