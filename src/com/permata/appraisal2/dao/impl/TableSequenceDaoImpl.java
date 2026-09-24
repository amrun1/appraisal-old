package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.dao.TableSequenceDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.model.TableSequence;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

@Repository
public class TableSequenceDaoImpl extends DaoBaseImpl<TableSequence, String> implements TableSequenceDao {

    @Override
    public Integer nextVal(String code) {
        TableSequence seq = get(code);
        Integer nextVal = 1;
        if (seq == null || StringUtils.isBlank(seq.getCode())) {
            save(new TableSequence(code));
        } else {
            seq.setValue(seq.getValue() + 1);
            save(seq);
            nextVal = seq.getValue();
        }
        return nextVal;
    }

    @Override
    public String orderNextVal() {
        String code = AppConstans.OrderDataCommon.getOrderPattern();
        Integer orderNumber = nextVal(code.concat(AppConstans.OrderDataCommon.ORDER_CODE));
        return String.format(AppConstans.OrderDataCommon.ORDER_PATTERN, code, orderNumber);
    }

    @Override
    public String lpaNextVal() {
        String code = AppConstans.OrderDataCommon.getOrderPattern();
        Integer orderNumber = nextVal(code.concat(AppConstans.OrderDataCommon.LPA_CODE));
        return String.format(AppConstans.OrderDataCommon.ORDER_PATTERN, code, orderNumber);
    }
}