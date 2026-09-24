package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.common.LabelValue;
import com.permata.appraisal2.dao.IndikasiFormulaDao;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.IndikasiFormulaDto;
import com.permata.appraisal2.facade.IndikasiFormulaFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.IndikasiFormula;
import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class IndikasiFormulaFacadeBean extends FacadeBase<IndikasiFormulaDto, IndikasiFormula> implements IndikasiFormulaFacade {

    @Autowired
    private IndikasiFormulaDao dao;



    @Override
    public IndikasiFormulaDto toWrapper(IndikasiFormula e) {
        IndikasiFormulaDto dto = new IndikasiFormulaDto();
        BeanUtils.copyProperties(e, dto);
        return dto;
    }

    @Override
    public IndikasiFormula toEntity(IndikasiFormulaDto w) {
        IndikasiFormula e = new IndikasiFormula();
        if (w.getCode() != null) {
            e = dao.get(w.getCode());
        }
        BeanUtils.copyProperties(w, e);
        return e;
    }

    @Override
    public List<LabelValue<String, String>> findType() {
        return null;
    }


    @Override
    @Transactional
    public IndikasiFormulaDto save(IndikasiFormulaDto dto) {
        return toWrapper(dao.save(toEntity(dto)));
    }


    @Override
    public IndikasiFormulaDto get(Long id) {
        return toWrapper(dao.get(id));
    }


    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        try {
            dao.delete(id);
        } catch (Exception e){
            throw e;
        }
    }


    @Override
    public PaginatedList<IndikasiFormulaDto> find(IndikasiFormulaDto dto, PagingInfo pagingInfo) {
        PaginatedList<IndikasiFormula> pgList = dao.find(pagingInfo, toEntity(dto));
        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));

    }
}
