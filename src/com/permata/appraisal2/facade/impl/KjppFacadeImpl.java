package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.common.LabelValue;
import com.permata.appraisal2.dao.KjppDao;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.KjppDto;
import com.permata.appraisal2.facade.KjppFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.Kjpp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class KjppFacadeImpl extends FacadeBase<KjppDto, Kjpp> implements KjppFacade {

    @Autowired
    private KjppDao dao;

    @Override
    public List<LabelValue<Long, String>> findLv() {
        List<LabelValue<Long, String>> result = new ArrayList<LabelValue<Long, String>>();
        List<Kjpp> dataList = dao.find(new Kjpp());
        for (Kjpp r : dataList) {
            result.add(new LabelValue<Long, String>(r.getId(), r.getName()));
        }
        return result;
    }

    @Override
    public PaginatedList<KjppDto> find(KjppDto dto, PagingInfo pagingInfo) {
        PaginatedList<Kjpp> pgList = dao.find(pagingInfo, toEntity(dto), OrderSpec.create("name", OrderSpec.OrderType.asc));
        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
    }

    @Override
    @Transactional(readOnly = false)
    public KjppDto save(KjppDto dto) {
        return toWrapper(dao.save(toEntity(dto)));
    }

    @Override
    public KjppDto get(Long id){ return toWrapper(dao.get(id)); }

    @Transactional(readOnly = false)
    public void delete(Long id) throws Exception {
        dao.delete(id);
    }

    @Override
    public KjppDto toWrapper(Kjpp e) {
        KjppDto dto = new KjppDto();
        BeanUtils.copyProperties(e, dto);
        return dto;
    }

    @Override
    public Kjpp toEntity(KjppDto w) {
        Kjpp e = new Kjpp();
        BeanUtils.copyProperties(w, e);
        return e;
    }

}