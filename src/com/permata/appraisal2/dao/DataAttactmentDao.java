package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.model.DataAttactment;
import com.permata.appraisal2.model.DataCommon;
import com.permata.appraisal2.model.DataDetail;

import java.util.List;

public interface DataAttactmentDao extends DaoBase<DataAttactment, Long> {
    public Long countByDetailId(Long detailId);
    public DataCommon getDataUmumByNameFile(String namaFile);
    List<DataAttactment> getAttachmentsWithoutBlob(DataDetail idDataDetail);
}