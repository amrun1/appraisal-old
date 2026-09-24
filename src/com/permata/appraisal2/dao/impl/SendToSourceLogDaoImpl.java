package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.SendToSourceLogDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.model.SendToSourceLog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SendToSourceLogDaoImpl extends DaoBaseImpl<SendToSourceLog, Long> implements SendToSourceLogDao {

    @Override
    public SendToSourceLog getByIdCommon(Long idCommon) {
        String hql = "from SendToSourceLog a where a.idCommon=? order by a.id desc";
        List<SendToSourceLog> ls = createQuery(hql).setParameter(0, idCommon).list();
            if (ls!=null && !ls.isEmpty()){
                return ls.get(0);
            }

        return null;
    }

}