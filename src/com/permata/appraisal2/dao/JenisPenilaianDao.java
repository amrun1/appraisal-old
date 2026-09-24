package com.permata.appraisal2.dao;

import com.permata.appraisal2.dao.common.DaoBase;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.JenisPenilaianDto;
import com.permata.appraisal2.model.JenisPenilaian;

import java.util.List;

public interface JenisPenilaianDao extends DaoBase<JenisPenilaian, Long> {
    public List<JenisPenilaian> listByKategoriAktiva(String code);

    public PaginatedList<JenisPenilaian> findList(PagingInfo pagingInfo, JenisPenilaianDto dto);
}