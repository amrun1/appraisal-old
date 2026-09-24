package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.dao.StatusNasabahDao;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.StatusNasabahDto;
import com.permata.appraisal2.facade.StatusNasabahFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.StatusNasabah;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class StatusNasabahFacadeImpl extends FacadeBase<StatusNasabahDto, StatusNasabah> implements StatusNasabahFacade {

    @Autowired
    private StatusNasabahDao dao;

    public PaginatedList<StatusNasabahDto> find(StatusNasabahDto dto, PagingInfo pagingInfo) {
        PaginatedList<StatusNasabah> pgList = dao.find(pagingInfo, toEntity(dto));
        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
    }

    public StatusNasabahDto get(Long id) {
        return toWrapper(dao.get(id));
    }

    @Transactional(readOnly = false)
    public StatusNasabahDto save(StatusNasabahDto dto) {
        return toWrapper(dao.save(toEntity(dto)));
    }

    @Override
    public StatusNasabahDto toWrapper(StatusNasabah e) {
        StatusNasabahDto dto = new StatusNasabahDto();
        BeanUtils.copyProperties(e, dto);
        return dto;
    }

    @Override
    public StatusNasabah toEntity(StatusNasabahDto w) {
        StatusNasabah e = new StatusNasabah();
        if (w.getId() != null) {
            e = dao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        return e;
    }

}