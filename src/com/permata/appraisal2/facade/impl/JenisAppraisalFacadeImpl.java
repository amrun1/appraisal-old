package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.common.LabelValue;
import com.permata.appraisal2.dao.JenisAppraisalDao;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.JenisAppraisalDto;
import com.permata.appraisal2.facade.JenisAppraisalFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.JenisAppraisal;
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
public class JenisAppraisalFacadeImpl  extends FacadeBase<JenisAppraisalDto, JenisAppraisal> implements JenisAppraisalFacade {

    @Autowired
    private JenisAppraisalDao dao;

    public PaginatedList<JenisAppraisalDto> find(JenisAppraisalDto dto, PagingInfo pagingInfo) {
        PaginatedList<JenisAppraisal> pgList = dao.find(pagingInfo, toEntity(dto));
        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
    }

    @Transactional(readOnly = false)
    public JenisAppraisalDto save(JenisAppraisalDto dto) {
        return toWrapper(dao.save(toEntity(dto)));
    }

    public JenisAppraisalDto get(Long id) {
        return toWrapper(dao.get(id));
    }

    @Transactional(readOnly = false)
    public void delete(Long id) throws Exception {
        dao.delete(id);
    }

    @Override
    public List<LabelValue<Long, String>> findLv() {
        List<LabelValue<Long, String>> result = new ArrayList<LabelValue<Long, String>>();
        List<JenisAppraisal> list = dao.find(new JenisAppraisal());
        for (JenisAppraisal row : list) {
            result.add(new LabelValue<Long, String>(row.getId(), row.getName()));
        }
        return result;
    }

    @Override
    public Map<String, Object> findLvForm() {
        Map<String, Object> result = new HashMap<String, Object>();

        List<JenisAppraisal> dataListNew = new ArrayList<JenisAppraisal>();

        List<JenisAppraisal> dataList = dao.find(new JenisAppraisal());
        for (int i = 0; i < dataList.size() ; i++) {
            JenisAppraisal d = dataList.get(i);
            if (d.getName().equalsIgnoreCase("BV") || d.getName().equalsIgnoreCase("RV")){
                dataListNew.add(d);
            }
        }
        List<LabelValue<Long, String>> lvJenisAppraisal = new ArrayList<LabelValue<Long, String>>();
        for (JenisAppraisal row : dataListNew) {
            lvJenisAppraisal.add(new LabelValue<Long, String>(row.getId(), row.getName()));
        }
        result.put("listAppraisal", lvJenisAppraisal);

        return result;
    }

    @Override
    public JenisAppraisalDto toWrapper(JenisAppraisal e) {
        JenisAppraisalDto dto = new JenisAppraisalDto();
        BeanUtils.copyProperties(e, dto);
        return dto;
    }

    @Override
    public JenisAppraisal toEntity(JenisAppraisalDto w) {
        JenisAppraisal e = new JenisAppraisal();
        if (w.getId() != null) {
            e = dao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        return e;
    }
}