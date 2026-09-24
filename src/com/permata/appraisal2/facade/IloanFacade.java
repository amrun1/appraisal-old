package com.permata.appraisal2.facade;

import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.IloanProgresDto;
import com.permata.appraisal2.dto.IlonDto;
import com.permata.appraisal2.dto.SendtoSourceLogDto;

import java.util.List;

public interface IloanFacade {

    public PaginatedList<IlonDto> lookup(PagingInfo pagingInfo, IlonDto filter, OrderSpec order);

    public void saveAll(List<IlonDto> listDto) throws Exception;

    public void trancate();

    public PaginatedList<IloanProgresDto> findHead(PagingInfo pagingInfo, IloanProgresDto filter);

    public PaginatedList<IloanProgresDto> find(PagingInfo pagingInfo, IloanProgresDto filter);

    public void sendManual(Long id);

    public void sendManualCol(Long id);

    public PaginatedList<SendtoSourceLogDto> findPlus (PagingInfo pagingInfo, SendtoSourceLogDto filter);
}