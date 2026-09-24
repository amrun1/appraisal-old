package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.dao.HouseComplexDao;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.HouseComplexDto;
import com.permata.appraisal2.facade.HouseComplexFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.HouseComplex;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class HouseComplexFacadeImpl extends FacadeBase<HouseComplexDto, HouseComplex> implements HouseComplexFacade {

    @Autowired
    private HouseComplexDao dao;

    @Override
    public PaginatedList<HouseComplexDto> find(HouseComplexDto dto, PagingInfo pagingInfo) {
        PaginatedList<HouseComplex> pgList = dao.find(pagingInfo, toEntity(dto));
        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
    }

    @Override
    public HouseComplexDto toWrapper(HouseComplex e) {
        HouseComplexDto w = new HouseComplexDto();
        BeanUtils.copyProperties(e, w);
        return w;
    }

    @Override
    public HouseComplex toEntity(HouseComplexDto w) {
        HouseComplex e = new HouseComplex();
        if (w.getId() != null) {
            e = dao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        return e;
    }
}
