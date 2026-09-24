package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.common.LabelValue;
import com.permata.appraisal2.dao.GroupParameterDao;
import com.permata.appraisal2.dao.ParameterDao;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.ParameterDto;
import com.permata.appraisal2.facade.ParameterFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.AssetType;
import com.permata.appraisal2.model.DocumentType;
import com.permata.appraisal2.model.GroupParameter;
import com.permata.appraisal2.model.Parameter;
import org.apache.commons.collections.ComparatorUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class ParameterFacadeImpl extends FacadeBase<ParameterDto, Parameter> implements ParameterFacade {

    @Autowired
    private ParameterDao dao;

    @Autowired
    private GroupParameterDao groupParameterDao;

    private Map<String, List<Parameter>> mapParams = new HashMap<String, List<Parameter>>();

    @Override
    public List<LabelValue<Long, String>> findLvByGroup(String groupCode) {
        List<LabelValue<Long, String>> result = new ArrayList<LabelValue<Long, String>>();
        List<Parameter> list = getListParameter(groupCode);
        for (Parameter row : list) {
            result.add(new LabelValue<Long, String>(row.getId(), row.getValue()));
        }
        return result;
    }

    @Override
    public List<ParameterDto> findParamByGroup(String groupCode) {
        return toListWrapper(getListParameter(groupCode));
    }

    private List<Parameter> getListParameter(String groupCode) {
        return dao.findByGroup(groupCode);
//        if (!mapParams.containsKey(groupCode)) {
//            List<Parameter> list = dao.findByGroup(groupCode);
//            mapParams.put(groupCode, list);
//        }
//        return mapParams.get(groupCode);
    }

    @Override
    public String getValue(String code, String groupCode) {
        List<Parameter> listData = dao.findByCodeAndGroup(code, groupCode);
        if (listData == null || listData.isEmpty()) {
            return "";
        }
        return listData.get(0).getValue();
    }

    @Override
    public ParameterDto toWrapper(Parameter model){
        ParameterDto w = new ParameterDto();
        BeanUtils.copyProperties(model, w, "group");
            if (model.getGroup()!=null){
                w.setGroupCode(model.getGroup().getCode());
                w.setGroupDesc(model.getGroup().getDesctiption());
            }
        return w;
    }

    @Override
    public Parameter toEntity(ParameterDto dto){
        Parameter e = new Parameter();
        boolean isNew = dto.getId()==null;
        if (!isNew) {
            e = dao.get(dto.getId());
        }
        BeanUtils.copyProperties(dto, e);

        if (dto.getGroupCode() != null) {
            e.setGroup(groupParameterDao.get(dto.getGroupCode()));
        }


        return e;

    }


    @Override
    public ParameterDto get(Long id) {
        if (id!=null){
            return toWrapper(dao.get(id));
        }
        return null;
    }

    @Override
    public Map<String, Object> getReference() {

        Map<String, Object> result = new HashMap<String, Object>();
        List<LabelValue<String, String>> lvGroupParameter = new ArrayList<LabelValue<String, String>>();
        try {
            Field[] listGroupParameter = AppConstans.GroupParameter.class.getDeclaredFields();

            for (Field fieldGroupParam : listGroupParameter) {
                String groupParamValue = (String) fieldGroupParam.get(AppConstans.GroupParameter.class);
                lvGroupParameter.add(new LabelValue<String, String>(groupParamValue, groupParamValue));
            }
            Collections.sort(lvGroupParameter, LabelValue.valueComparator); // // TODO: 1/8/2018 notes add order, easier to find
            result.put("lvGroupParameter", lvGroupParameter);

            return result;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }

    }


    @Override
    public PaginatedList<ParameterDto> find(ParameterDto dto, PagingInfo pagingInfo) {
        PaginatedList<Parameter> pgList = dao.find(pagingInfo, toEntity(dto));
        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));

    }



    @Override
    @Transactional(readOnly = false)
    public ParameterDto save(ParameterDto dto) {
        return toWrapper(dao.save(toEntity(dto)));

    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) throws Exception {
        dao.delete(id);
    }



}