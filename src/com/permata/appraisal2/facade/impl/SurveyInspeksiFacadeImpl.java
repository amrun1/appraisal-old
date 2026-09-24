package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.dao.DataDetailDao;
import com.permata.appraisal2.dao.SurveyInspeksiDao;
import com.permata.appraisal2.dao.SurveyPointDao;
import com.permata.appraisal2.dto.SurveyInspeksiDto;
import com.permata.appraisal2.dto.SurveyPointDto;
import com.permata.appraisal2.facade.SurveyInspeksiFacade;
import com.permata.appraisal2.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SurveyInspeksiFacadeImpl implements SurveyInspeksiFacade {

    @Autowired
    private SurveyInspeksiDao dao;

    @Autowired
    private DataDetailDao detailDao;

    @Autowired
    private SurveyPointDao pointDao;






    private SurveyInspeksiDto toWrapper(SurveyInspeksi e){
            SurveyInspeksiDto dto = new SurveyInspeksiDto();
            BeanUtils.copyProperties(e,dto);
            if (e.getDetail()!=null){
                dto.setIdDetail(e.getDetail().getId());
                SurveyPoint sp = pointDao.getByDetail(dto.getIdDetail());
                if(sp!=null){
                    dto.setCatatan(sp.getCatatan());
                }
            }
            return dto;

    }


    private SurveyInspeksi toEntity(SurveyInspeksiDto dto){
        SurveyInspeksi entity = new SurveyInspeksi();
        DataDetail detail = detailDao.get(dto.getIdDetail());
        if (dto.getId() != null){
            entity = dao.get(dto.getId());
        }
        BeanUtils.copyProperties(dto, entity);
        if (detail != null){
            entity.setDetail(detail);
        }

        return entity;

    }

    @Override
    @Transactional(readOnly = false)
    public SurveyInspeksiDto save(SurveyInspeksiDto dto) {
        SurveyPointDto dtoPoint = new SurveyPointDto();
        SurveyPoint point = pointDao.getByDetail(dto.getIdDetail());
        if(point!=null) {
            dtoPoint.setId(point.getId());
        }
        dtoPoint.setCatatan(dto.getCatatan());
        dtoPoint.setDetailId(dto.getIdDetail());

        toPointWrapper(pointDao.save(toPointEntity(dtoPoint)));
        dto.setCatatan(dtoPoint.getCatatan());
        return toWrapper(dao.save(toEntity(dto)));
    }

    @Override
    public SurveyInspeksiDto get(Long id) {
        return toWrapper(dao.get(id));
    }

    @Override
    public SurveyInspeksiDto getByDetail(Long idDetail) {
//        DataDetail d = detailDao.get(idDetail);
//        SurveyStock example = new SurveyStock();
//        example.setDetail(d);
        SurveyInspeksiDto surveyInspeksiDto = new SurveyInspeksiDto();
        surveyInspeksiDto.setIdDetail(idDetail);
       List<SurveyInspeksi> ls = dao.find(toEntity(surveyInspeksiDto));
        if (ls !=null && !ls.isEmpty()){
            return toWrapper(ls.get(0));
        }
        return new SurveyInspeksiDto(idDetail);
    }


    private SurveyPoint toPointEntity(SurveyPointDto w) {
        SurveyPoint e = new SurveyPoint();
        if (w.getId() != null) {
            e = pointDao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        e.setDetail(detailDao.get(w.getDetailId()));

        return e;
    }

    private SurveyPointDto toPointWrapper(SurveyPoint e) {
        SurveyPointDto w = new SurveyPointDto();
        BeanUtils.copyProperties(e, w);
        w.setDetailId(e.getDetail().getId());
        w.setCategory(e.getDetail().getAssetType().getCategoryType().getCode());

        return w;
    }
}
