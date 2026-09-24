package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.dao.CategoryTypeDao;
import com.permata.appraisal2.dao.JenisPenilaianDao;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.JenisPenilaianDto;
import com.permata.appraisal2.facade.JenisPenilaianFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.JenisPenilaian;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional (readOnly = true)
public class JenisPenilaianFacadeImpl extends FacadeBase<JenisPenilaianDto, JenisPenilaian> implements JenisPenilaianFacade {

    @Autowired
    private JenisPenilaianDao dao;

    @Autowired
    private CategoryTypeDao categoryTypeDao;

    @Override
    public PaginatedList<JenisPenilaianDto> find(JenisPenilaianDto dto, PagingInfo pagingInfo) {
        PaginatedList<JenisPenilaian> pgList = dao.findList(pagingInfo, dto);
        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
    }

    public JenisPenilaianDto get(Long id) {
        return toWrapper(dao.get(id));
    }

    @Transactional(readOnly = false)
    public JenisPenilaianDto save(JenisPenilaianDto dto) {
        return toWrapper(dao.save(toEntity(dto)));
    }

    @Override
    public List<JenisPenilaianDto> listByKategoriAktiva(String code) {
        List<JenisPenilaian> dataList = dao.listByKategoriAktiva(code);
        return toListWrapper(dataList);
    }

    @Override
    public JenisPenilaianDto toWrapper(JenisPenilaian e) {
        JenisPenilaianDto w = new JenisPenilaianDto();
        BeanUtils.copyProperties(e, w, "categoryType");
        w.setCategoryType(e.getCategoryType().getCode());
        if (e.getCategoryType() != null) {
            w.setCategoryCode(e.getCategoryType().getCode());
            w.setCategoryName(e.getCategoryType().getName());
        }
        return w;
    }

    @Override
    public JenisPenilaian toEntity(JenisPenilaianDto w) {
        JenisPenilaian e = new JenisPenilaian();
        if (w.getId() != null) {
            e = dao.get(w.getId());
        }

        BeanUtils.copyProperties(w, e);
        if (w.getCategoryCode() != null) {
            e.setCategoryType(categoryTypeDao.get(w.getCategoryCode())); // presistance
        }
        return e;
    }
}