package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.common.LabelValue;
import com.permata.appraisal2.dao.CategoryTypeDao;
import com.permata.appraisal2.facade.CategoryTypeFacade;
import com.permata.appraisal2.model.CategoryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CategoryTypeFacadeImpl implements CategoryTypeFacade {

    @Autowired
    private CategoryTypeDao dao;

    @Override
    public List<LabelValue<String, String>> findLv() {
        List<LabelValue<String, String>> result = new ArrayList<LabelValue<String, String>>();
        List<CategoryType> dataList = dao.find(new CategoryType());
        for (CategoryType row : dataList) {
            result.add(new LabelValue<String, String>(row.getCode(), row.getName()));
        }
        return result;
    }
}