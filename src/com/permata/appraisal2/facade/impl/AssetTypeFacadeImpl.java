package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.common.LabelValue;
import com.permata.appraisal2.common.LabelValueId;
import com.permata.appraisal2.dao.AssetTypeDao;
import com.permata.appraisal2.dao.CategoryTypeDao;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.AssetTypeDto;
import com.permata.appraisal2.facade.AssetTypeFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.AssetType;
import com.permata.appraisal2.model.CategoryType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class AssetTypeFacadeImpl extends FacadeBase<AssetTypeDto, AssetType> implements AssetTypeFacade {

    @Autowired
    private AssetTypeDao dao;
    @Autowired
    private CategoryTypeDao categoryTypeDao;

    @Override
    public PaginatedList<AssetTypeDto> find(AssetTypeDto dto, PagingInfo pagingInfo) {
        PaginatedList<AssetType> pgList = dao.find(pagingInfo, toEntity(dto));
        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
    }

    @Override
    public List<LabelValueId<Long, String, String>> findLvId() {
        List<LabelValueId<Long, String, String>> result = new ArrayList<LabelValueId<Long, String, String>>();
        List<AssetType> dataList = dao.find(new AssetType());
        for (AssetType row : dataList) {
            result.add(new LabelValueId<Long, String, String>(row.getId(), row.getCategoryType().getCode(), row.getName()));
        }
        return result;
    }

    @Override
    public Map<String, Object> findLvIdForm() {
        Map<String, Object> result = new HashMap<String, Object>();

        List<AssetType> dataList = dao.find(new AssetType());
        List<LabelValueId<Long, String, String>> lvJenisAktiva = new ArrayList<LabelValueId<Long, String, String>>();
//        List<LabelValue<Long, String>> lvJenisAktiva = new ArrayList<LabelValue<Long, String>>();
        for (AssetType row : dataList) {
            lvJenisAktiva.add(new LabelValueId<Long, String, String>(row.getId(), row.getCategoryType().getCode(), row.getName()));
        }
        result.put("listAktiva", lvJenisAktiva);

        return result;
    }

    @Override
    @Transactional(readOnly = false)
    public AssetTypeDto save(AssetTypeDto dto) {
        return toWrapper(dao.save(toEntity(dto)));
    }

    @Transactional(readOnly = false)
    public void delete(Long id) throws Exception {
        dao.delete(id);
    }

    @Override
    public AssetTypeDto get(Long id) {
        return toWrapper(dao.get(id));
    }

    @Override
    public AssetTypeDto toWrapper(AssetType e) {
        AssetTypeDto w = new AssetTypeDto();
        BeanUtils.copyProperties(e, w, "categoryType");
        w.setCategoryType(e.getCategoryType().getCode());
        if (e.getCategoryType() != null) {
            w.setCategoryCode(e.getCategoryType().getCode());
            w.setCategoryName(e.getCategoryType().getName());
        }
        return w;
    }


    @Override
    public AssetType toEntity(AssetTypeDto w) {
        AssetType e = new AssetType();
        if (w.getId() != null) {
            e = dao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        if (w.getCategoryCode() != null) {
            e.setCategoryType(categoryTypeDao.get(w.getCategoryCode())); // presistance
        }
        return e;
    }

    @Override
    public Map<String, Object> getReference() {
        Map<String, Object> result = new HashMap<String, Object>();

        List<CategoryType> dataList = categoryTypeDao.find(new CategoryType());
        List<LabelValue<String, String>> lvJenisKategori = new ArrayList<LabelValue<String, String>>();
        for (CategoryType row : dataList) {
            lvJenisKategori.add(new LabelValue<String, String>(row.getCode(), row.getName()));
        }
        result.put("lvJenisCategory", lvJenisKategori);

        return result;
    }
}