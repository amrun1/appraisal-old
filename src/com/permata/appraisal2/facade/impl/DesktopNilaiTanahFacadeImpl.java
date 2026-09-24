package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.dao.DesktopNilaiTanahDao;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.DesktopNilaiTanahDto;
import com.permata.appraisal2.dto.DesktopNilaiTanahXls;
import com.permata.appraisal2.facade.DesktopNilaiTanahFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.DesktopNilaiTanah;
import com.permata.appraisal2.util.ListUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DesktopNilaiTanahFacadeImpl extends FacadeBase<DesktopNilaiTanahDto, DesktopNilaiTanah> implements DesktopNilaiTanahFacade {

    @Autowired
    private DesktopNilaiTanahDao dao;

    @Override
    public PaginatedList<DesktopNilaiTanahDto> find(DesktopNilaiTanahDto dto, PagingInfo pagingInfo) {
        PaginatedList<DesktopNilaiTanah> pgList = dao.find(pagingInfo, toEntity(dto));
        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
    }

    @Override
    @Transactional
    public String upload(List<DesktopNilaiTanahXls> listData) throws Exception {
        int add = 0;
        int update = 0;
        int data = 0;
        if (ListUtils.isNotEmpty(listData)) {
            for (DesktopNilaiTanahXls row : listData) {
                data++;
                DesktopNilaiTanah e = new DesktopNilaiTanah();
                BeanUtils.copyProperties(row, e, "harga");

                PaginatedList<DesktopNilaiTanah> listEntity = dao.findExact(PagingInfo.createToRetrieveAll(), e);
                if (ListUtils.isNotEmpty(listEntity.getDataList())) {
                    for (DesktopNilaiTanah item : listEntity.getDataList()) {
                        BeanUtils.copyProperties(row, item, "no");
                        dao.save(item);
                        update++;
                    }
                } else {
                    e.setHarga(row.getHarga());
                    dao.save(e);
                    add++;
                }
            }
        }
        return "Jumlah Data " + data + "; Add " + add + " ; Update " + update;
    }

    @Override
    public DesktopNilaiTanahDto toWrapper(DesktopNilaiTanah e) {
        DesktopNilaiTanahDto w = new DesktopNilaiTanahDto();
        BeanUtils.copyProperties(e, w);
        return w;
    }

    @Override
    public DesktopNilaiTanah toEntity(DesktopNilaiTanahDto w) {
        DesktopNilaiTanah e = new DesktopNilaiTanah();
        if (w.getId() != null) {
            e = dao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        return e;
    }
}