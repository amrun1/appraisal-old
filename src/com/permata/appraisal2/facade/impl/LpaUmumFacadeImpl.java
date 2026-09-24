package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.dao.*;
import com.permata.appraisal2.dto.LpaUmumDto;
import com.permata.appraisal2.facade.LpaUmumFacade;
import com.permata.appraisal2.model.*;
import com.permata.appraisal2.util.ListUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Service
@Transactional(readOnly = true)
public class LpaUmumFacadeImpl implements LpaUmumFacade {

    private Logger logger = Logger.getLogger(LpaUmumFacadeImpl.class);

    @Autowired
    private LpaUmumDao dao;
    @Autowired
    private DataCommonDao commonDao;
    @Autowired
    private DataDetailDao detailDao;
    @Autowired
    private ParameterDao parameterDao;
    @Autowired
    private KjppDao kjppDao;
    @Autowired
    private PersonDao personDao;

    @Override
    @Transactional(readOnly = false)
    public LpaUmumDto save(LpaUmumDto dto) {
        return toWrapper(dao.save(toEntity(dto)));
    }

    @Override
    public LpaUmumDto get(Long id) {
        return toWrapper(dao.get(id));
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) throws Exception {
        try {
            dao.delete(id);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public LpaUmumDto toWrapper(LpaUmum e) {
        LpaUmumDto w = new LpaUmumDto();
        BeanUtils.copyProperties(e, w, "listNegative", "listPertimbangan", "id");
        w.setIdLpa(e.getId());
        w.setDetailId(e.getDetail().getId());
        w.setKategoriJenisAppraisal(e.getKategoriJenisAppraisal());
        w.setIdJenisAppraisal(e.getDetail().getDataCommon().getAppraisalType().getId());
        if (ListUtils.isNotEmpty(e.getListNegative())) {
            for (Parameter param : e.getListNegative()) {
                w.addListNegative(param.getId());
            }
        }
        if (ListUtils.isNotEmpty(e.getListPertimbangan())) {
            for (Parameter param : e.getListPertimbangan()) {
                w.addListPertimbangan(param.getId());
            }
        }
        if (e.getKjpp() != null) {
            w.setIdKjpp(e.getKjpp().getId());
        }
        return w;
    }

    @Override
    public LpaUmum toEntity(LpaUmumDto w) {
        LpaUmum e = new LpaUmum();
        e.setId(w.getIdLpa());
        if (w.getIdLpa() != null) {
            e = dao.get(w.getIdLpa());
            e.setListNegative(null);
            e.setListPertimbangan(null);
        }
        BeanUtils.copyProperties(w, e, "listNegative", "listPertimbangan", "id");
        if (w.getDetailId() != null) {
            e.setDetail(detailDao.get(w.getDetailId()));
        }
        if (ListUtils.isNotEmpty(w.getListNegative())) {
            for (Long id : w.getListNegative()) {
                e.addListNegative(parameterDao.get(id));
            }
        }
        if (ListUtils.isNotEmpty(w.getListPertimbangan())) {
            for (Long id : w.getListPertimbangan()) {
                e.addPertimbangan(parameterDao.get(id));
            }
        }
        if (w.getIdKjpp() != null) {
            e.setKjpp(kjppDao.get(w.getIdKjpp()));
        }
        return e;
    }

    @Override
    public LpaUmumDto create(Long commonId) {
        DataCommon e = commonDao.get(commonId);
        DataDetail detail = e.getDataDetail();

        LpaUmumDto w = new LpaUmumDto();
        w.setSurveyId(detail.getSurvey() != null ? detail.getSurvey().getId() : null);
        w.setCommonId(e.getId());
        w.setDetailId(detail.getId());
        w.setJenisAppraisal(e.getAppraisalType().getName());
        w.setIdJenisAppraisal(e.getAppraisalType().getId());
        w.setKategoriJenisAppraisal(e.getAppraisalTypeCategory());
        w.setBranchId(e.getBranch().getId());
        w.setOrderNumber(e.getOrderNumber());
        w.setContactPerson(e.getContactName());
        w.setNamaDebitur(e.getName());
        w.setJenisAktiva(detail.getAssetType().getId());
        w.setKategoriObjectPenilaian(detail.getAssetType().getCategoryType().getCode());
        w.setJenisObjectPenilain(detail.getAssetType().getName());
        w.setNamaPerumahan(detail.getHomeApartment());
        w.setNamaCluster(detail.getClusterTower());
        w.setBlock(detail.getBlokFloor());
        w.setNomor(detail.getNumber());
        w.setRt(detail.getRt());
        w.setRw(detail.getRw());
        w.setKodePos(detail.getZipCode());
        w.setPropinsi(detail.getProvince());
        w.setKota(detail.getCity());
        w.setKecamatan(detail.getDistrict());
        w.setKelurahan(detail.getSubDistrict());
        w.setStatusJaminan(e.getCollateralStatus().getId());
        w.setTanggalOrder(e.getCreatedDate());
        w.setDitujuke(personDao.get(e.getCreatedBy()).getName());
        w.setDraft(true);
        w.setCreate(true);
        w.setSla(0);
        w.setMataUang(AppConstans.MataUang.IDR);
        w.setTanggalRate(new Date());
        w.setNilaiRate(BigDecimal.ONE);

        SurveyCommon surveyUmum = e.getDataDetail().getSurvey();
        if (surveyUmum != null) {
            w.setPosisiLokasiAgunan(e.getDataDetail().getSurvey().getLokasi());
            w.setTanggalSurvey(e.getDataDetail().getSurvey().getTanggal());
            w.setDitinjau(e.getDataDetail().getSurvey().getDitinjau());
            w.setDitemui(e.getDataDetail().getSurvey().getDitemui());
            w.setCatatanSurvey(e.getDataDetail().getSurvey().getCatatanSurvey());
            if(surveyUmum.getJenisPenilaian() != null) {
                w.setIdObjectPenilaiFisik(surveyUmum.getJenisPenilaian().getId());
            }
        }

        if (detail.getPoint() != null) {
            w.setCatatanPoint(detail.getPoint().getCatatan());
            w.setPositifPoint(detail.getPoint().getPositif());
            if (ListUtils.isNotEmpty(detail.getPoint().getNegatif())) {
                for (Parameter row : detail.getPoint().getNegatif()) {
                    w.addListNegative(row.getId());
                }
            }
            if (ListUtils.isNotEmpty(detail.getPoint().getPertimbangan())) {
                for (Parameter row : detail.getPoint().getPertimbangan()) {
                    w.addListPertimbangan(row.getId());
                }
            }
        }

        return w;
    }
}