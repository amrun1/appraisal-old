package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.common.LabelValue;
import com.permata.appraisal2.dao.AssetTypeDao;
import com.permata.appraisal2.dao.DocumentLpaTypeDao;
import com.permata.appraisal2.dao.JenisAppraisalDao;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.DataCommonDto;
import com.permata.appraisal2.dto.DocumentLpaTypeDto;
import com.permata.appraisal2.facade.DocumentLpaTypeFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.AssetType;
import com.permata.appraisal2.model.DataCommon;
import com.permata.appraisal2.model.DocumentLpaType;
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
public class DocumentLpaTypeFacadeImpl extends FacadeBase<DocumentLpaTypeDto, DocumentLpaType> implements DocumentLpaTypeFacade {

    @Autowired
    private DocumentLpaTypeDao dao;
    @Autowired
    private AssetTypeDao assetTypeDao;
    @Autowired
    private JenisAppraisalDao jenisAppraisalDao;

    @Override
    public List<LabelValue<Long, String>> findLvForLpa(Long activaId, Long appraisalId) {
        List<LabelValue<Long, String>> listLv = new ArrayList<LabelValue<Long, String>>();
        List<DocumentLpaType> listData = dao.findByActiva(activaId, Boolean.FALSE, appraisalId);
        for (DocumentLpaType row : listData) {
            listLv.add(new LabelValue<Long, String>(row.getId(), row.getName()));
        }
        return listLv;
    }

    @Override
    public String getKategoriLpa(Long activaId) {
        AssetType listAktiva = assetTypeDao.get(activaId);
        return listAktiva.getCategoryType().getCode();
    }


    @Override
    public Map<Long,String> findLvForLpaMandatory(Long activaId, Long appraisalId) {
        Map<Long,String> map = new HashMap<Long, String>();
        List<DocumentLpaType> listData = dao.findByActiva(activaId, Boolean.FALSE, appraisalId);
        for (DocumentLpaType row : listData) {
            if (row.getMandatory()){
                map.put(row.getId(),row.getName());
            }
        }
        return map;
    }



    @Override
    public List<LabelValue<Long, String>> findLvForSurvey(Long activaId, Long appraisalId) {
        List<LabelValue<Long, String>> listLv = new ArrayList<LabelValue<Long, String>>();
        List<DocumentLpaType> listData = dao.findByActiva(activaId, Boolean.TRUE, appraisalId);
        for (DocumentLpaType row : listData) {
            listLv.add(new LabelValue<Long, String>(row.getId(), row.getName()));
        }
        return listLv;
    }

    @Override
    public DocumentLpaTypeDto get(Long id) {
        return toWrapper(dao.get(id));
    }

//    @Override
//    public PaginatedList<DocumentLpaTypeDto> findListJenis(DocumentLpaTypeDto dto, PagingInfo pagingInfo, Integer jenis) {
//        PaginatedList<DocumentLpaType> pgList = dao.findListJenis(pagingInfo, toEntity(dto), jenis);
//        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
//    }

    @Override
    public PaginatedList<DocumentLpaTypeDto> find(DocumentLpaTypeDto filter, PagingInfo pagingInfo, Integer jenis) {
        PaginatedList<DocumentLpaType> pgList;
        if (jenis!=2){
             pgList= dao.find(pagingInfo, toEntity(filter), OrderSpec.create("appraisal", OrderSpec.OrderType.asc), jenis);
        }else{
            pgList= dao.find(pagingInfo, toEntity(filter), OrderSpec.create("assetType", OrderSpec.OrderType.asc), jenis);
        }
        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
    }


    @Override
    @Transactional
    public DocumentLpaTypeDto save(DocumentLpaTypeDto dto) {
        return toWrapper(dao.save(toEntity(dto)));
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) throws Exception {
        try {
            dao.delete(id);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public DocumentLpaTypeDto toWrapper(DocumentLpaType e) {
        DocumentLpaTypeDto w = new DocumentLpaTypeDto();
        BeanUtils.copyProperties(e, w);
        if (e.getAssetType() != null) {
            w.setAssetTypeId(e.getAssetType().getId());
            w.setAssetTypeName(e.getAssetType().getName());
        }
        if (e.getAppraisal() != null) {
            w.setAppraisalId(e.getAppraisal().getId());
            w.setAppraisalName(e.getAppraisal().getName());
        }
        return w;
    }

    @Override
    public DocumentLpaType toEntity(DocumentLpaTypeDto w) {
        DocumentLpaType e = new DocumentLpaType();
        BeanUtils.copyProperties(w, e);
        if (w.getAssetTypeId() != null) {
            e.setAssetType(assetTypeDao.get(w.getAssetTypeId()));
        }
        if (w.getAppraisalId() != null) {
            e.setAppraisal(jenisAppraisalDao.get(w.getAppraisalId()));
        }
        return e;
    }

}