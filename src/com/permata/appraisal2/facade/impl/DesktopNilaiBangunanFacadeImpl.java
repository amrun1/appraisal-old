package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.dao.DesktopNilaiBangunanDao;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.DesktopNilaiBangunanDto;
import com.permata.appraisal2.facade.DesktopNilaiBangunanFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.DesktopNilaiBangunan;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional (readOnly = true)
public class DesktopNilaiBangunanFacadeImpl extends FacadeBase<DesktopNilaiBangunanDto, DesktopNilaiBangunan> implements DesktopNilaiBangunanFacade {

    @Autowired
    private DesktopNilaiBangunanDao dao;

    @Override
    public PaginatedList<DesktopNilaiBangunanDto> find(DesktopNilaiBangunanDto dto, PagingInfo pagingInfo) {
        PaginatedList<DesktopNilaiBangunan> pgList = dao.find(pagingInfo, toEntity(dto));
        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
    }

    @Override
    public DesktopNilaiBangunanDto toWrapper(DesktopNilaiBangunan e) {
        DesktopNilaiBangunanDto w = new DesktopNilaiBangunanDto();
        BeanUtils.copyProperties(e, w);
        return w;
    }

    @Override
    public DesktopNilaiBangunan toEntity(DesktopNilaiBangunanDto w) {
        DesktopNilaiBangunan e = new DesktopNilaiBangunan();
        if (w.getId() != null) {
            e = dao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        return e;
    }
}
