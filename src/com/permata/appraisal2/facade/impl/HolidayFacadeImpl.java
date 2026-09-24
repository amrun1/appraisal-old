package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.dao.HolidayDao;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.HolidayDto;
import com.permata.appraisal2.facade.HolidayFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.Holiday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class HolidayFacadeImpl extends FacadeBase<HolidayDto, Holiday> implements HolidayFacade {

    @Autowired
    private HolidayDao dao;


    @Override
    public PaginatedList<HolidayDto> find(HolidayDto dto, PagingInfo pagingInfo) {
        PaginatedList<Holiday> pgList;
        pgList = dao.find(pagingInfo, dto, OrderSpec.create("tanggal", OrderSpec.OrderType.asc));
        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
    }

    @Override
    public  Boolean findDuplicate(HolidayDto dto) {
        return dao.findDuplicate(toEntity(dto))==0;
    }

    @Override
    @Transactional(readOnly = false)
    public HolidayDto save(HolidayDto dto) {
        return toWrapper(dao.save(toEntity(dto)));
    }

    @Override
    public HolidayDto get(Long id){ return toWrapper(dao.get(id)); }

    @Transactional(readOnly = false)
    public void delete(Long id) throws Exception {
        dao.delete(id);
    }

    @Override
    public HolidayDto toWrapper(Holiday e) {
        HolidayDto dto = new HolidayDto();
        dto.setId(e.getId());
        dto.setKeterangan(e.getKeterangan());
        dto.setTanggal(e.getTanggal());
        return dto;
    }

    @Override
    public Holiday toEntity(HolidayDto e) {
        Holiday w = new Holiday();
        w.setId(e.getId());
        w.setKeterangan(e.getKeterangan());
        w.setTanggal(e.getTanggal());
        return w;
    }

}