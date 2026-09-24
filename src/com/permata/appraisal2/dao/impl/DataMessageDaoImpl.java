package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.DataMessageDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.model.DataMessage;
import org.springframework.stereotype.Repository;

@Repository
public class DataMessageDaoImpl extends DaoBaseImpl<DataMessage, Long> implements DataMessageDao {
}
