package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.common.LabelValue;
import com.permata.appraisal2.dao.AssetTypeDao;
import com.permata.appraisal2.dao.DocumentTypeDao;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.DocumentTypeDto;
import com.permata.appraisal2.facade.DocumentTypeFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.AssetType;
import com.permata.appraisal2.model.DocumentType;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class DocumentTypeFacadeImpl extends FacadeBase<DocumentTypeDto, DocumentType> implements DocumentTypeFacade {

    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DocumentTypeDao dao;
    @Autowired
    private AssetTypeDao assetTypeDao;

    @Override
    public List<DocumentTypeDto> findByAktiva(Long activaId) {
        DocumentType filter = new DocumentType();
        filter.setAssetType(new AssetType(activaId));
        return toListWrapper(dao.find(filter));
    }

    @Override
    public DocumentTypeDto toWrapper(DocumentType e) {
        DocumentTypeDto w = new DocumentTypeDto();
        BeanUtils.copyProperties(e, w);
        w.setIdAssetType(e.getAssetType().getId());
        return w;
    }

    @Override
    public DocumentType toEntity(DocumentTypeDto w) {
        DocumentType e = new DocumentType();
        if(w.getId() != null){
            e = dao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        if(w.getIdAssetType() != null) {
            e.setAssetType(assetTypeDao.get(w.getIdAssetType()));
        }
        return e;
    }

    @Override
    public List<LabelValue<Long, String>> findLvByAktiva(Long activaId) {
        DocumentType filter = new DocumentType();
        filter.setAssetType(new AssetType(activaId));
        List<DocumentType> listData = dao.find(filter);
        List<LabelValue<Long, String>> listLv = new ArrayList<LabelValue<Long, String>>();
        for (DocumentType row : listData) {
            listLv.add(new LabelValue<Long, String>(row.getId(), row.getName()));
        }
        return listLv;
    }

    @Override
    public List<DocumentTypeDto> findListTableByAktiva(Long activaId) {
        DocumentType filter = new DocumentType();
        filter.setAssetType(new AssetType(activaId));
        return toListWrapper(dao.find(filter));
    }

    @Override
    public DocumentTypeDto get(Long id) {
        return toWrapper(dao.get(id));
    }

    @Override
    @Transactional
    public DocumentTypeDto save(DocumentTypeDto dto) {
        return toWrapper(dao.save(toEntity(dto)));
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
    public PaginatedList<DocumentTypeDto> find(DocumentTypeDto filter, PagingInfo pagingInfo) {
        PaginatedList<DocumentType> pgList = dao.find(pagingInfo, toEntity(filter));
        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
    }
}