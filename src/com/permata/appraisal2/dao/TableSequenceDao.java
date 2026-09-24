package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.model.TableSequence;

public interface TableSequenceDao extends DaoBase<TableSequence, String> {
    public Integer nextVal(String code);

    public String orderNextVal();

    public String lpaNextVal();
}