package com.permata.appraisal2.facade;

import com.permata.appraisal2.common.LabelValue;

import java.util.List;

public interface CategoryTypeFacade {
    public List<LabelValue<String, String>> findLv();
}