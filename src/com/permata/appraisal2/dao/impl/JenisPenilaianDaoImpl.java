package com.permata.appraisal2.dao.impl;

import com.permata.appraisal2.dao.JenisPenilaianDao;
import com.permata.appraisal2.dao.common.DaoBaseImpl;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.JenisPenilaianDto;
import com.permata.appraisal2.model.JenisPenilaian;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JenisPenilaianDaoImpl extends DaoBaseImpl<JenisPenilaian, Long> implements JenisPenilaianDao {
    @Override
    public List<JenisPenilaian> listByKategoriAktiva(String code) {
        String hql = "from JenisPenilaian where categoryType.code=?";
        return createQuery(hql).setParameter(0, code).list();
    }

	@Override
    public PaginatedList<JenisPenilaian> findList(PagingInfo pagingInfo, JenisPenilaianDto dto){
        String hql = "from JenisPenilaian where 1=1 ";

        if (dto.getKategori()!=null){
            hql+=String.format(" and kategori = '%s'", dto.getKategori());
        }

        if(dto.getCategoryCode()!=null){
            hql += String.format(" and categoryType.code = '%s'", dto.getCategoryCode());
        }
        if(dto.getNama()!=null){
            hql += " and lower(nama) like '%"+dto.getNama()+"%' ";
        }
        if(dto.getSla()!=null){
            hql += " and sla = "+dto.getSla();
        }

        return find(pagingInfo, createQuery(hql), createQuery("select count(*) " + hql), null);
    }
}