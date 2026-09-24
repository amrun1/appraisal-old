package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.model.DataDetail;

import java.util.List;

public interface DataDetailDao extends DaoBase<DataDetail, Long> {


    void updatePicByCommonId(Long id,String picName);
    List<DataDetail> findByFileName (String fileName);
    boolean gotAttachment(Long id);
}