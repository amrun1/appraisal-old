package com.permata.appraisal2.facade;

import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.ZipCodeDto;

import java.util.List;

public interface ZipCodeFacade {

    public PaginatedList<ZipCodeDto> find(ZipCodeDto dto, PagingInfo pagingInfo);

    public ZipCodeDto get(Long id);

    public ZipCodeDto save(ZipCodeDto dto);

    public String save(List<ZipCodeDto> dataList);

    public void delete(Long id) throws Exception;

    public List<String> findPropinsi();

    public List<String> findKota(String propinsi);

    public List<String> findKecamatan(String propinsi, String kota);

    public int updatePic(String province, String city, String district, String picName);

    public List<String> findPic();

    public List<String> findPersonByRole();
}
