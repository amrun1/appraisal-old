package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.model.SendToSourceAttachment;

/**
 * Created by Admin on 4/27/2017.
 */
public interface SendToSourceLogAttachmenDao extends DaoBase<SendToSourceAttachment, Long> {

    public SendToSourceAttachment getByIdSource(Long idSource);

}
