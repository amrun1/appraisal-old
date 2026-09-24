package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.dao.*;
import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.dao.common.PaginatedList;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.IloanProgresDto;
import com.permata.appraisal2.dto.IlonDto;
import com.permata.appraisal2.dto.SendtoSourceLogDto;
import com.permata.appraisal2.facade.IloanFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.DataCommon;
import com.permata.appraisal2.model.Iloan;
import com.permata.appraisal2.model.IloanProgres;
import com.permata.appraisal2.model.SendToSourceLog;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class IloanFacadeImpl extends FacadeBase<IlonDto, Iloan> implements IloanFacade {

    @Autowired
    private IloanDao dao;
    @Autowired
    private PriorityCategoryDao priorityCategoryDao;
    @Autowired
    private IloanProgresDao progresDao;

    @Autowired
    private SendToSourceLogDao sendToSourceLogDao;

    @Autowired
    private DataCommonDao dataCommonDao;

    @Override
    @Transactional
    public void saveAll(List<IlonDto> listDto) throws Exception {
        for (Iloan e : toListEntity(listDto)) {
            dao.save(e);
        }
    }


    @Override
    public PaginatedList<SendtoSourceLogDto> findPlus (PagingInfo pagingInfo, SendtoSourceLogDto filter){
        SendToSourceLog f = new SendToSourceLog();
        BeanUtils.copyProperties(filter, f);
        PaginatedList<SendToSourceLog> pgList = sendToSourceLogDao.find(pagingInfo, f);
        return pgList.cloneAndUpdateList(toListSourceLog(pgList.getDataList()));
    }






    @Override
    public PaginatedList<IlonDto> lookup(PagingInfo pagingInfo, IlonDto filter, OrderSpec order) {
        PaginatedList<Iloan> pgList = dao.lookup(pagingInfo, toEntity(filter), order);
        return pgList.cloneAndUpdateList(toListWrapper(pgList.getDataList()));
    }

    @Override
    @Transactional
    public void trancate() {
        dao.trancate();
    }

    @Override
    public IlonDto toWrapper(Iloan e) {
        IlonDto w = new IlonDto();
        BeanUtils.copyProperties(e, w, "priority");
        if (e.getPriority() != null) {
            w.setPriority(e.getPriority().getId());
            w.setPriorityName(e.getPriority().getNama());
        }
        return w;
    }

    @Override
    public Iloan toEntity(IlonDto w) {
        Iloan e = new Iloan();
        BeanUtils.copyProperties(w, e, "priority");
        if (w.getPriority() != null) {
            e.setPriority(priorityCategoryDao.get(w.getPriority()));
        }
        return e;
    }

    @Override
    public PaginatedList<IloanProgresDto> findHead(PagingInfo pagingInfo, IloanProgresDto filter) {
        IloanProgres f = new IloanProgres();
        BeanUtils.copyProperties(filter, f);
        PaginatedList<IloanProgres> pgList = progresDao.findHead(pagingInfo, f);
        return pgList.cloneAndUpdateList(toListProgress(pgList.getDataList()));
    }

    @Override
    public PaginatedList<IloanProgresDto> find(PagingInfo pagingInfo, IloanProgresDto filter) {
        IloanProgres f = new IloanProgres();
        BeanUtils.copyProperties(filter, f);
        PaginatedList<IloanProgres> pgList = progresDao.findHead(pagingInfo, f);
        return pgList.cloneAndUpdateList(toListProgress(pgList.getDataList()));
    }

    /**
     * kirim manual data RWA by user/orang
     * @param id DATA_UMUM.ID
     */
    @Override
    @Transactional
    public void sendManual(Long id) {
        progresDao.sendManual(id);
    }



    @Override
    @Transactional
    public void sendManualCol(Long id) {
        progresDao.sendManualCol(id);
    }

    private List<IloanProgresDto> toListProgress(List<IloanProgres> listE) {
        List<IloanProgresDto> listW = new ArrayList<IloanProgresDto>();
        for (IloanProgres e : listE) {
            IloanProgresDto w = new IloanProgresDto();
            BeanUtils.copyProperties(e, w);
            if (e.getDataCommon() != null) {
                // query jadi berat
                w.setDataCommonId(e.getDataCommon().getId());
                w.setOrderNumber(e.getDataCommon().getOrderNumber());
            }
            listW.add(w);
        }
        return listW;
    }

    private List<SendtoSourceLogDto> toListSourceLog(List<SendToSourceLog> listE) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        List<SendtoSourceLogDto> listW = new ArrayList<SendtoSourceLogDto>();
        for (SendToSourceLog e : listE) {
            SendtoSourceLogDto w = new SendtoSourceLogDto();
            BeanUtils.copyProperties(e, w);

            DataCommon dataCommon = dataCommonDao.get(w.getIdCommon());
            w.setNoOrder(dataCommon.getOrderNumber());
            w.setPlusId(dataCommon.getSourceId());
            w.setJenisAppraisal(dataCommon.getAppraisalType().getName());
            w.setFortmatDate(sdf.format(e.getTanggal()));
            listW.add(w);
        }
        return listW;
    }

}