package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.dao.SystemParamDao;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.SystemParamsDto;
import com.permata.appraisal2.facade.SystemParamFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.SystemParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional (readOnly = true)
public class SystemParamFacadeImpl extends FacadeBase<SystemParamsDto, SystemParam> implements SystemParamFacade {

    @Autowired
    private SystemParamDao dao;

    @Override
    public String getValue(String code) {
        SystemParam e = dao.get(code);
        return e == null ? "" : e.getValue();
    }

    @Override
    @Transactional
    public void update(String code, String value) {
        SystemParam e = dao.get(code);
        if(e == null){
            e = new SystemParam();
            e.setCode(code);
            e.setValue(value);
            e.setDescription("create from code");
        }

        e.setValue(value);
        dao.save(e);
    }



    @Override
    public PaginatedList<SystemParamsDto> find(SystemParamsDto dto, PagingInfo pagingInfo) {
        SystemParam systemParam = new SystemParam();
        BeanUtils.copyProperties(dto, systemParam);
        PaginatedList<SystemParam> pgList = dao.find(pagingInfo, systemParam, OrderSpec.create("code", OrderSpec.OrderType.asc));
        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));

    }



    @Override
    public SystemParamsDto getCot(String id) {
        return toWrapperCot(dao.get(id));
    }

    @Override
    public SystemParamsDto get(String id) {
        return toWrapper(dao.get(id));
    }

    @Override
    public SystemParamsDto toWrapper(SystemParam systemParam) {
        SystemParamsDto dto = new SystemParamsDto();
        BeanUtils.copyProperties(systemParam, dto);
        return dto;
    }


    private SystemParamsDto toWrapperCot(SystemParam systemParam) {
        SystemParamsDto dto = new SystemParamsDto();
        if (systemParam==null){
            return null;
        }
        BeanUtils.copyProperties(systemParam, dto);
        return dto;
    }

    @Override
    public SystemParam toEntity(SystemParamsDto systemParamsDto) {
        SystemParam e = new SystemParam();
        boolean isNew = (systemParamsDto.getCode()==null || StringUtils.isBlank(systemParamsDto.getCode()));
        if (!isNew) {
            e = dao.get(systemParamsDto.getCode());
        }
        BeanUtils.copyProperties(systemParamsDto, e);
        return e;

    }
}