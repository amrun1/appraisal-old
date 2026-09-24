package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.dao.*;
import com.permata.appraisal2.dto.LpaRvDto;
import com.permata.appraisal2.dto.LpaUmumDto;
import com.permata.appraisal2.facade.LpaRvFacade;
import com.permata.appraisal2.facade.LpaUmumFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.*;
import com.permata.appraisal2.util.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class LpaRvFacadeImpl extends FacadeBase<LpaRvDto, LpaRv> implements LpaRvFacade {

    @Autowired
    private LpaRvDao dao;
    @Autowired
    private DataCommonDao commonDao;
    @Autowired
    private LpaUmumDao lpaUmumDao;
    @Autowired
    private LpaUmumFacade facade;
    @Autowired
    private TableSequenceDao sequenceDao;
    @Autowired
    private SurveyRvDao surveyRvDao;
    @Autowired
    private PersonDao personDao;

    @Override
    public LpaRvDto create(Long commonId) {
        DataCommon e = commonDao.get(commonId);
        DataDetail detail = e.getDataDetail();

        LpaRvDto w = new LpaRvDto();
        LpaUmumDto lpaUmum = facade.create(commonId);
        BeanUtils.copyProperties(lpaUmum, w);

        // not null value
        w.setPembanding1("-");
        w.setPembanding2("-");
        w.setPembanding3("-");
        w.setCatatanPoint("-");
        w.setBranchId(e.getBranch().getId());
        w.setDitujuke(personDao.get(e.getCreatedBy()).getName());
        w.setIdObjectPenilaiFisik(e.getAppraisalType().getId());
        w.setJenisObjectPenilain(e.getAppraisalType().getName());
        w.setJenisObjectPenilaiFisik(e.getAppraisalType().getName());
        w.setStatusJaminan(e.getCollateralStatus().getId());
        w.setSla(1);
        w.setKategoriSla("RV");
        w.setCatatanSurvey("-");
        w.setDitemui("-");
        w.setFaktorMarketability("-");

        if(detail.getSurvey() != null){
            SurveyRv survey = surveyRvDao.getByDetail(detail.getId());
            w.setLokasiFisik(survey.getAlamat());
            w.setNamaDebitur(survey.getVendor());
            BeanUtils.copyProperties(survey, w, "id", "detail");
            w.setFotoPada(survey.getFotoPada());
            w.setDitemui(e.getSurveyBy());
            w.setTanggalSurvey(e.getSurveyDate());
            w.setLokasi_objek_jam(survey.getJam());
        }

        return w;
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
    @Transactional
    public LpaRvDto save(LpaRvDto dto , String userId) throws Exception {
        if (!dto.getDraft() && StringUtils.isBlank(dto.getLpaNumber())) {
            dto.setLpaNumber(sequenceDao.lpaNextVal());
        }
        if(checkUserTasklist(userId,dto)) {
            return toWrapper(dao.save(toEntity(dto)));
        } else {
            throw new Exception("Gagal : Posisi Order Tidak Pada Tasklist Anda");
        }
    }

    @Override
    public LpaRvDto getByUmum(Long lpaId) {
        return toWrapper(dao.getByUmum(lpaId));
    }

    @Override
    public LpaRvDto toWrapper(LpaRv e) {
        LpaRvDto w = new LpaRvDto();
        BeanUtils.copyProperties(facade.toWrapper(e.getLpaUmum()), w);
        BeanUtils.copyProperties(e, w);
        // foto lampiran
//        if (ListUtils.isNotEmpty(e.getImages())) {
//            for (LpaBvImage image : e.getImages()) {
//                switch (image.getKategori()) {
//                    case 1:
//                        w.setFoto1(toImageWrapper(image));
//                        break;
//                    case 2:
//                        w.setFoto2(toImageWrapper(image));
//                        break;
//                    case 3:
//                        w.setFoto3(toImageWrapper(image));
//                        break;
//                    case 4:
//                        w.setFoto4(toImageWrapper(image));
//                        break;
//                    case 5:
//                        w.setFoto5(toImageWrapper(image));
//                        break;
//                    case 6:
//                        w.setFoto6(toImageWrapper(image));
//                        break;
//                    case 7:
//                        w.setFoto7(toImageWrapper(image));
//                        break;
//                    case 8:
//                        w.setFoto8(toImageWrapper(image));
//                        break;
//                    case 9:
//                        w.setFoto9(toImageWrapper(image));
//                        break;
//                    case 10:
//                        w.setFoto10(toImageWrapper(image));
//                        break;
//                    default:
//                        w.addImage(toImageWrapper(image));
//                        break;
//                }
//            }
//        }
        return w;
    }

    @Override
    public LpaRv toEntity(LpaRvDto w) {
        LpaUmum umum = lpaUmumDao.save(facade.toEntity(w));
        LpaRv e = new LpaRv();
        if (w.getId() != null) {
            e = dao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        e.setLpaUmum(umum);
        if (w.isCreate()) {

        }
        return e;
    }

    private Boolean checkUserTasklist(String userId, LpaRvDto dto) {
        DataCommon e = commonDao.get(dto.getCommonId());
        return StringUtils.equalsIgnoreCase(e.getTaskList(),userId);
    }
}