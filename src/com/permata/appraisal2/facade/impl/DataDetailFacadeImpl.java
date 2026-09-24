package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.common.LabelValue;
import com.permata.appraisal2.common.LabelValueId;
import com.permata.appraisal2.dao.*;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.DataDetailDto;
import com.permata.appraisal2.facade.DataDetailFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.AssetType;
import com.permata.appraisal2.model.CategoryType;
import com.permata.appraisal2.model.DataCommon;
import com.permata.appraisal2.model.DataDetail;
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
public class DataDetailFacadeImpl extends FacadeBase<DataDetailDto, DataDetail> implements DataDetailFacade {

    @Autowired
    private DataCommonDao dataCommonDao;
    @Autowired
    private DataDetailDao dao;
    @Autowired
    private CategoryTypeDao categoryTypeDao;
    @Autowired
    private AssetTypeDao assetTypeDao;
    @Autowired
    private DataAttactmentDao attactmentDao;

    @Override
    public PaginatedList<DataDetailDto> find(DataDetailDto dto, PagingInfo pagingInfo) {
        PaginatedList<DataDetail> pgList = dao.find(pagingInfo, toEntity(dto));
        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
    }

    @Override
    @Transactional(readOnly = false)
    public DataDetailDto save(DataDetailDto dto) {
        return toWrapper(dao.save(toEntity(dto)));
    }

    @Override
    public DataDetailDto get(Long id) {
        return toWrapper(dao.get(id));
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) throws Exception {
        dao.delete(id);
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

        List<AssetType> dataListJenisAktiva = assetTypeDao.find(new AssetType());
        List<LabelValueId<Long, String, String>> lvJenisAktiva = new ArrayList<LabelValueId<Long, String, String>>();
        for (AssetType row : dataListJenisAktiva) {
            lvJenisAktiva.add(new LabelValueId<Long, String, String>(row.getId(), row.getCategoryType().getCode(), row.getName()));
        }
        result.put("lvJenisAktiva", lvJenisAktiva);

        return result;
    }

    @Override
    public DataDetailDto toWrapper(DataDetail e) {
        DataDetailDto w = new DataDetailDto();
        BeanUtils.copyProperties(e, w);
        w.setDataCommonId(e.getDataCommon().getId());
        w.setIdJenisAppraisal(e.getDataCommon().getAppraisalType().getId());
        w.setJenisAppraisal(e.getDataCommon().getAppraisalType().getName());
        w.setJenisLaporan(e.getDataCommon().getTypeReport());
        if (e.getAssetType() != null) {
            w.setAssetTypeId(e.getAssetType().getId());
            w.setAssetTypeName(e.getAssetType().getName());
            w.setCategoryCode(e.getAssetType().getCategoryType().getCode());
            w.setCategoryName(e.getAssetType().getCategoryType().getName());
        }
        w.setCountAttactment(attactmentDao.countByDetailId(e.getId()));
        return w;
    }

    @Override
    public DataDetail toEntity(DataDetailDto w) {
        DataCommon parent = dataCommonDao.get(w.getDataCommonId());
        DataDetail e = parent.getDataDetail();
        if (w.getId() == null) {
            e = new DataDetail();
        }
        BeanUtils.copyProperties(w, e);

        e.setDataCommon(parent);
        if (w.getAssetTypeId() != null) {
            e.setAssetType(assetTypeDao.get(w.getAssetTypeId()));
        }
        return e;
    }
}