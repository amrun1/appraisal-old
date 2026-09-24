package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.SendToSourceLogAttachmenDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.model.SendToSourceAttachment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Admin on 4/27/2017.
 */

@Repository
public class SendToSourceLogAttachmenDaoImp extends DaoBaseImpl<SendToSourceAttachment, Long> implements SendToSourceLogAttachmenDao {

    @Override
    public SendToSourceAttachment getByIdSource(Long idSource) {
        String hql = "from SendToSourceAttachment a where a.sendToSourceLog.id=? order by a.id desc";
        List<SendToSourceAttachment> ls = createQuery(hql).setParameter(0, idSource).list();
        if (ls!=null && !ls.isEmpty()){
            return ls.get(0);
        }
        return null;
    }

}
