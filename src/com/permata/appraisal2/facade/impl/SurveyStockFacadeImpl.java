package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.dao.DataDetailDao;
import com.permata.appraisal2.dao.SurveyStockDao;
import com.permata.appraisal2.dto.SurveyStockDto;
import com.permata.appraisal2.facade.SurveyStockFacade;
import com.permata.appraisal2.model.DataDetail;
import com.permata.appraisal2.model.SurveyStock;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SurveyStockFacadeImpl implements SurveyStockFacade {

    @Autowired
    private SurveyStockDao dao;

    @Autowired
    private DataDetailDao detailDao;






    private SurveyStockDto toWrapper(SurveyStock e){
            SurveyStockDto dto = new SurveyStockDto();
            BeanUtils.copyProperties(e,dto);
            if (e.getDetail()!=null){
                dto.setIdDetail(e.getDetail().getId());
            }
            return dto;

    }


    private SurveyStock toEntity(SurveyStockDto dto){
        SurveyStock entity = new SurveyStock();
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
    public SurveyStockDto save(SurveyStockDto dto) {
        return toWrapper(dao.save(toEntity(dto)));
    }

    @Override
    public SurveyStockDto get(Long id) {
        return toWrapper(dao.get(id));
    }

    @Override
    public SurveyStockDto getByDetail(Long idDetail) {
//        DataDetail d = detailDao.get(idDetail);
//        SurveyStock example = new SurveyStock();
//        example.setDetail(d);
        SurveyStockDto surveyStockDto = new SurveyStockDto();
        surveyStockDto.setIdDetail(idDetail);
       List<SurveyStock> ls = dao.find(toEntity(surveyStockDto));
        if (ls !=null && !ls.isEmpty()){
            return toWrapper(ls.get(0));
        }
        return new SurveyStockDto(idDetail);
    }
}
