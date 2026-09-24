package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.dao.*;
import com.permata.appraisal2.dto.LpaRabDto;
import com.permata.appraisal2.dto.LpaUmumDto;
import com.permata.appraisal2.facade.LpaRabFacade;
import com.permata.appraisal2.facade.LpaUmumFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.DataCommon;
import com.permata.appraisal2.model.LpaRab;
import com.permata.appraisal2.model.LpaUmum;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class LpaRabFacadeImpl extends FacadeBase<LpaRabDto, LpaRab> implements LpaRabFacade {


    @Autowired
    private LpaRabDao dao;
    @Autowired
    private LpaUmumDao lpaUmumDao;
    @Autowired
    private DataCommonDao commonDao;
    @Autowired
    private TableSequenceDao sequenceDao;

    @Autowired
    private LpaUmumFacade facade;

    @Override
    public LpaRabDto create(Long commonId) {
        DataCommon e = commonDao.get(commonId);
        // DataDetail detail = e.getDataDetail();

        LpaRabDto w = new LpaRabDto();
        LpaUmumDto lpaUmum = facade.create(commonId);
        BeanUtils.copyProperties(lpaUmum, w);

        w.setPembanding1("-");
        w.setPembanding2("-");
        w.setPembanding3("-");
        w.setFaktorMarketability("-");
        w.setCatatanPoint("-");
        return w;
    }

    @Override
    @Transactional
    public LpaRabDto save(LpaRabDto dto, String userId) throws Exception {
        if (!dto.getDraft() && StringUtils.isBlank(dto.getLpaNumber())) {
            dto.setLpaNumber(sequenceDao.lpaNextVal());
        }
        if(checkUserTasklist(userId,dto)) {
            return toWrapper(dao.save(toEntity(dto)));
        } else {
            throw new Exception("Gagal : Posisi Order Tidak Pada Tasklist Anda");
        }
    }

    @Override
    public LpaRabDto getByUmum(Long lpaId) {
        return toWrapper(dao.getByUmum(lpaId));
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        try {
            dao.delete(id);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public LpaRabDto toWrapper(LpaRab e) {
        LpaRabDto w = new LpaRabDto();
        BeanUtils.copyProperties(facade.toWrapper(e.getLpaUmum()), w);
        BeanUtils.copyProperties(e, w);
        return w;
    }

    @Override
    public LpaRab toEntity(LpaRabDto w) {
        LpaUmum umum = lpaUmumDao.save(facade.toEntity(w));
        LpaRab e = new LpaRab();
        if (w.getId() != null) {
            e = dao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        e.setLpaUmum(umum);
        if (w.isCreate()) {

        }
        return e;
    }

    private Boolean checkUserTasklist(String userId, LpaRabDto dto) {
        DataCommon e = commonDao.get(dto.getCommonId());
        return StringUtils.equalsIgnoreCase(e.getTaskList(),userId);
    }
}