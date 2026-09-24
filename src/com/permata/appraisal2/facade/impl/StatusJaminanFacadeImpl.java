package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.common.LabelValue;
import com.permata.appraisal2.dao.StatusJaminanDao;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.StatusJaminanDto;
import com.permata.appraisal2.facade.StatusJaminanFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.StatusJaminan;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class StatusJaminanFacadeImpl extends FacadeBase<StatusJaminanDto, StatusJaminan> implements StatusJaminanFacade {

    @Autowired
    private StatusJaminanDao dao;

    @Override
    public List<LabelValue<Long, String>> listLv() {
        List<LabelValue<Long, String>> result = new ArrayList<LabelValue<Long, String>>();
        List<StatusJaminan> list = dao.find(new StatusJaminan());
        for (StatusJaminan row : list) {
            result.add(new LabelValue<Long, String>(row.getId(), row.getName()));
        }
        return result;
    }

    public PaginatedList<StatusJaminanDto> find(StatusJaminanDto dto, PagingInfo pagingInfo) {
        PaginatedList<StatusJaminan> pgList = dao.find(pagingInfo, toEntity(dto));
        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
    }

    public StatusJaminanDto get(Long id) {
        return toWrapper(dao.get(id));
    }

    public List<StatusJaminanDto> getAll() {

        return toListWrapper(dao.find(new StatusJaminan()));
    }

    @Transactional(readOnly = false)
    public StatusJaminanDto save(StatusJaminanDto dto) {
        return toWrapper(dao.save(toEntity(dto)));
    }

    @Override
    public StatusJaminanDto toWrapper(StatusJaminan e) {
        StatusJaminanDto dto = new StatusJaminanDto();
        BeanUtils.copyProperties(e, dto);
        return dto;
    }

    @Override
    public StatusJaminan toEntity(StatusJaminanDto w) {
        StatusJaminan e = new StatusJaminan();
        if (w.getId() != null) {
            e = dao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        return e;
    }
}