package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.model.SendToSourceLog;

import java.util.List;

public interface SendToSourceLogDao extends DaoBase<SendToSourceLog, Long> {

    public SendToSourceLog getByIdCommon(Long idCommon);
}