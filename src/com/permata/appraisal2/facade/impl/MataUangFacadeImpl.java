package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.dao.MataUangDao;
import com.permata.appraisal2.dto.MataUangDto;
import com.permata.appraisal2.facade.MataUangFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.MataUang;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MataUangFacadeImpl extends FacadeBase<MataUangDto, MataUang> implements MataUangFacade {

    @Autowired
    private MataUangDao dao;

    @Override
    public List<MataUangDto> list(MataUangDto filter) {
        return toListWrapper(dao.find(toEntity(filter)));
    }

    @Override
    public MataUangDto toWrapper(MataUang e) {
        MataUangDto w = new MataUangDto();
        BeanUtils.copyProperties(e, w);
        return w;
    }

    @Override
    public MataUang toEntity(MataUangDto w) {
        MataUang e = new MataUang();
        if (StringUtils.isNotBlank(w.getCode())) {
            e = dao.get(w.getCode());
        }
        BeanUtils.copyProperties(w, e);
        return e;
    }
}