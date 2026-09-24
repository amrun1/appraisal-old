package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.common.LabelValue;
import com.permata.appraisal2.dao.DataAttactmentDao;
import com.permata.appraisal2.dao.DataCommonDao;
import com.permata.appraisal2.dao.DataDetailDao;
import com.permata.appraisal2.dao.DocumentTypeDao;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.DataAttactmentDto;
import com.permata.appraisal2.facade.DataAttactmentFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.*;
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
public class DataAttactmentFacadeImpl extends FacadeBase<DataAttactmentDto, DataAttactment> implements DataAttactmentFacade {

    @Autowired
    private DataAttactmentDao dao;
    @Autowired
    private DataDetailDao detailDao;
    @Autowired
    private DocumentTypeDao documentTypeDao;
    @Autowired
    private DataCommonDao dataCommonDao;

    @Override
    public Map<String, Object> findDetailInfo(Long detailId) {
        DataDetail detail = detailDao.get(detailId);
        Map<String, Object> result = new HashMap<String, Object>();
        boolean isGroup = false;
        DataCommon umum =detail.getDataCommon();
        if (umum.getPosition().equals("User_TL") && umum.getTaskList().equals("User_TL")){
            isGroup = true;
        }
        result.put("namaDebitur", detail.getDataCommon().getName());
        result.put("alamatAgunan", detail.getAlamat());
        result.put("isGr", isGroup);
        return result;
    }

    @Override
    public List<DataDetail> getDataUmumByNameFile(String namaFile) {
//        return dao.getDetailWithFileName(namaFile);
        return detailDao.findByFileName(namaFile);
    }

    @Override
    public PaginatedList<DataAttactmentDto> find(DataAttactmentDto dto, PagingInfo pagingInfo) {
        PaginatedList<DataAttactment> pgList = dao.find(pagingInfo, toEntity(dto));
        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
    }

    @Override
    @Transactional(readOnly = false)
    public DataAttactmentDto save(DataAttactmentDto dto) {
        return toWrapper(dao.save(toEntity(dto)));
    }

    @Override
    public DataAttactmentDto get(Long id) {
        return toWrapper(dao.get(id));
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) throws Exception {
        dao.delete(id);
    }

    @Override
    public Map<String, Object> getReference(Long assetTypeId) {
        Map<String, Object> result = new HashMap<String, Object>();

        DocumentType filter = new DocumentType();
        filter.setAssetType(new AssetType(assetTypeId));
        List<DocumentType> listData = documentTypeDao.find(filter);
        List<LabelValue<Long, String>> listLv = new ArrayList<LabelValue<Long, String>>();
        for (DocumentType row : listData) {
            listLv.add(new LabelValue<Long, String>(row.getId(), row.getName()));
        }
        result.put("lvJenisDokumen", listLv);
        return result;
    }

    @Override
    public DataAttactmentDto toWrapper(DataAttactment e) {
        DataAttactmentDto w = new DataAttactmentDto();
        BeanUtils.copyProperties(e, w);

        w.setDataDetailId(e.getDataDetail().getId());
        w.setDocumentTypeId(e.getDocumentType().getId());
        w.setDocumentTypeName(e.getDocumentType().getName());
        return w;
    }

    @Override
    public DataAttactment toEntity(DataAttactmentDto w) {
        DataDetail detail = detailDao.get(w.getDataDetailId());
        DataAttactment e = new DataAttactment();
        if (w.getId() != null) {
            e = dao.get(w.getId());
        }
        // exclude file byte[], copy manual after copy properties
        BeanUtils.copyProperties(w, e, "file");

        if (w.getFile() != null && w.getFile().length > 0) {
            e.setFile(w.getFile());
        }
        e.setDataDetail(detail);
        if(w.getDocumentTypeId() != null) {
            e.setDocumentType(documentTypeDao.get(w.getDocumentTypeId()));
        }
        return e;
    }

    @Override
    public Long countByDetail(Long detailId) {
        return dao.countByDetailId(detailId);
    }

    @Override
    public List<DataAttactmentDto> getAttachmentsWithoutBlob(DataDetail idDataDetail) {
        return toListWrapper(dao.getAttachmentsWithoutBlob(idDataDetail));
    }

    private List<DataAttactmentDto> toListWrapperWithoutBlob(List<DataAttactment> dataAttactments) {
        if(dataAttactments.size()>0) {
            List<DataAttactmentDto> dataAttactmentDtos = new ArrayList<DataAttactmentDto>();
            for(DataAttactment dataAttactment : dataAttactments) {
                DataAttactmentDto dataAttactmentDto = new DataAttactmentDto();
                dataAttactmentDto.setName(dataAttactment.getName());
                dataAttactmentDto.setDocumentTypeId(dataAttactment.getDocumentType().getId());
                dataAttactmentDto.setDocumentTypeName(dataAttactment.getDocumentType().getName());
                dataAttactmentDto.setExtension(dataAttactment.getExtension());
                dataAttactmentDto.setId(dataAttactment.getId());
                dataAttactmentDtos.add(dataAttactmentDto);
            }
            return dataAttactmentDtos;
        }
        return null;
    }
}