package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.model.ZipCode;

import java.util.List;

public interface ZipCodeDao extends DaoBase<ZipCode, Long> {

    public List<String> findPropinsi();

    public List<String> getAllPic();

    public List<String> findKota(String propinsi);

    public List<String> findKecamatan(String propinsi, String kota);

    public int updatePicPerKota(String province, String city, String picName);

    public int updatePicPerKecamatan(String province, String city, String district, String picName);

    public List<String> findPic();

}
