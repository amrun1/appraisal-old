package com.permata.appraisal2.facade;

import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.JenisPenilaianDto;

import java.util.List;

public interface JenisPenilaianFacade {

    public PaginatedList<JenisPenilaianDto> find(JenisPenilaianDto dto, PagingInfo pagingInfo);

    public List<JenisPenilaianDto> listByKategoriAktiva(String code);

    public JenisPenilaianDto get(Long id);

    public JenisPenilaianDto save(JenisPenilaianDto dto);
}