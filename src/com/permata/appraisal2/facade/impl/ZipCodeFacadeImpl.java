package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.dao.PersonRoleDao;
import com.permata.appraisal2.dao.ZipCodeDao;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.ZipCodeDto;
import com.permata.appraisal2.facade.ZipCodeFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.ZipCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ZipCodeFacadeImpl extends FacadeBase<ZipCodeDto, ZipCode> implements ZipCodeFacade {

    @Autowired
    private ZipCodeDao dao;

    @Autowired
    private PersonRoleDao personRoleDao;

    @Override
    public PaginatedList<ZipCodeDto> find(ZipCodeDto dto, PagingInfo pagingInfo) {
        PaginatedList<ZipCode> pgList = dao.find(pagingInfo, toEntity(dto));
        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
    }


    @Override
    public List<String> findPersonByRole(){
        return personRoleDao.findPersonByRole();
    }

    @Override
    public ZipCodeDto get(Long id) {
        return toWrapper(dao.get(id));
    }

    @Override
    @Transactional
    public ZipCodeDto save(ZipCodeDto dto) {
        return toWrapper(dao.save(toEntity(dto)));
    }

    @Override
    @Transactional
    public String save(List<ZipCodeDto> dataList) {
        String result = "total data " + dataList.size();
        int success = 0;
        for(ZipCodeDto w : dataList){
            save(w);
            success++;
        }
        return result + " ; sukses input " + success;
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        try {
            dao.delete(id);
        } catch (Exception e){
            throw e;
        }
    }

    @Override
    public ZipCodeDto toWrapper(ZipCode e) {
        if (e == null) {
            return null;
        }
        ZipCodeDto w = new ZipCodeDto();
        BeanUtils.copyProperties(e, w);
        return w;
    }

    @Override
    public ZipCode toEntity(ZipCodeDto w) {
        ZipCode e = new ZipCode();
        if (w.getId() != null) {
            e = dao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        return e;
    }

    @Override
    public List<String> findPropinsi() {
        return dao.findPropinsi();
    }

    @Override
    public List<String> findKota(String propinsi) {
        return dao.findKota(propinsi);
    }

    @Override
    public List<String> findKecamatan(String propinsi, String kota) {
        return dao.findKecamatan(propinsi, kota);
    }

    @Override
    @Transactional
    public int updatePic(String province, String city, String district, String picName) {
        if(StringUtils.isBlank(district)){
            // update pic per kota
            return dao.updatePicPerKota(province, city, picName);
        } else {
            // update pic per kecamatan
            return dao.updatePicPerKecamatan(province, city, district, picName);
        }
    }

    @Override
    public List<String> findPic() {
        return dao.findPic();
    }
}