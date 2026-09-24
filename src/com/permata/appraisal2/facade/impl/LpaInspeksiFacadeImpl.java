package com.permata.appraisal2.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.permata.appraisal2.dao.DataCommonDao;
import com.permata.appraisal2.dao.LpaInspeksiDao;
import com.permata.appraisal2.dao.LpaItemInspeksiDao;
import com.permata.appraisal2.dao.LpaUmumDao;
import com.permata.appraisal2.dao.ParameterDao;
import com.permata.appraisal2.dao.SurveyInspeksiDao;
import com.permata.appraisal2.dao.SurveyItemInspeksiDao;
import com.permata.appraisal2.dao.SurveyPointDao;
import com.permata.appraisal2.dao.TableSequenceDao;
import com.permata.appraisal2.dto.LpaInspeksiDto;
import com.permata.appraisal2.dto.LpaItemInspeksiDto;
import com.permata.appraisal2.dto.LpaUmumDto;
import com.permata.appraisal2.facade.LpaInspeksiFacade;
import com.permata.appraisal2.facade.LpaUmumFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.DataCommon;
import com.permata.appraisal2.model.DataDetail;
import com.permata.appraisal2.model.LpaInspeksi;
import com.permata.appraisal2.model.LpaItemInspeksi;
import com.permata.appraisal2.model.LpaUmum;
import com.permata.appraisal2.model.SurveyInspeksi;
import com.permata.appraisal2.model.SurveyItemInspeksi;
import com.permata.appraisal2.model.SurveyPoint;

/**
 * Created by SB on 4/17/2018.
 */

@Service
@Transactional(readOnly = true)
public class LpaInspeksiFacadeImpl extends FacadeBase<LpaInspeksiDto, LpaInspeksi> implements LpaInspeksiFacade {

    @Autowired
    private LpaInspeksiDao lpaInspeksiDao;
    @Autowired
    private LpaItemInspeksiDao lpaItemInspeksiDao;
    @Autowired
    private LpaUmumFacade lpaUmumFacade;
    @Autowired
    private TableSequenceDao sequenceDao;
    @Autowired
    private DataCommonDao commonDao;
    @Autowired
    private ParameterDao parameterDao;
    @Autowired
    private LpaUmumDao lpaUmumDao;
    @Autowired
    private SurveyInspeksiDao surveyInspeksiDao;
    @Autowired
    private SurveyItemInspeksiDao surveyItemInspeksiDao;
    @Autowired
    private SurveyPointDao pointDao;

    @Override
    public LpaInspeksiDto create(Long commonId) {
        DataCommon e = commonDao.get(commonId);
        DataDetail detail = e.getDataDetail();

        LpaInspeksiDto lpaInspeksiDto = new LpaInspeksiDto();
        LpaUmumDto lpaUmum = lpaUmumFacade.create(commonId);
        BeanUtils.copyProperties(lpaUmum, lpaInspeksiDto);

        lpaInspeksiDto.setFaktorMarketability("-");

        SurveyInspeksi surveyInspeksi = new SurveyInspeksi();
        surveyInspeksi.setDetail(detail);

        List<SurveyInspeksi> listSurvey = surveyInspeksiDao.find(surveyInspeksi);
        SurveyPoint point = pointDao.getByDetail(detail.getId());

        if (listSurvey != null && !listSurvey.isEmpty()){
            SurveyInspeksi survey = listSurvey.get(0);
            lpaInspeksiDto.setJenisBarang(survey.getJenisBarang());
            lpaInspeksiDto.setNomorDaftar(survey.getNomorDaftar());
            lpaInspeksiDto.setTanggalDaftar(survey.getTanggalDaftar());
            lpaInspeksiDto.setJenisTempat(survey.getJenisTempat());
            lpaInspeksiDto.setInfoAlamatTempat(survey.getInfoAlamatTempat());
            lpaInspeksiDto.setAlamatLain(survey.getAlamatLain());
            lpaInspeksiDto.setStatusTempat(survey.getStatusTempat());
            lpaInspeksiDto.setKondisiTempat(survey.getKondisiTempat());
            lpaInspeksiDto.setAktifitasKerja(survey.getAktifitasKerja());
            lpaInspeksiDto.setPeralatanGudang(survey.getPeralatanGudang());
            lpaInspeksiDto.setAlatPemadam(survey.getAlatPemadam());
            lpaInspeksiDto.setKeamanan(survey.getKeamanan());
            lpaInspeksiDto.setKondisiJalan(survey.getKondisiJalan());
            lpaInspeksiDto.setLingkunganSekitar(survey.getLingkunganSekitar());
            lpaInspeksiDto.setMetodePeriksa(survey.getMetodePeriksa());
            lpaInspeksiDto.setTempatPersediaan(survey.getTempatPersediaan());
            lpaInspeksiDto.setSusunanTempat(survey.getSusunanTempat());
            lpaInspeksiDto.setPacking(survey.getPacking());
            lpaInspeksiDto.setSubTotalGoods(survey.getSubTotalGoods());
            lpaInspeksiDto.setSubTotalHalfGoods(survey.getSubTotalHalfGoods());
            lpaInspeksiDto.setSubTotalRawMaterial(survey.getSubTotalRawMaterial());
            lpaInspeksiDto.setSubTotalGoodsFisik(survey.getSubTotalGoodsFisik());
            lpaInspeksiDto.setSubTotalHalfGoodsFisik(survey.getSubTotalHalfGoodsFisik());
            lpaInspeksiDto.setSubTotalRawMaterialFisik(survey.getSubTotalRawMaterialFisik());

            if (point != null){
                lpaInspeksiDto.setCatatanPoint(point.getCatatan());
            }
        }

        return lpaInspeksiDto;
    }

    @Override
    public LpaInspeksiDto getByUmum(Long lpaId) {
        LpaInspeksi filter = new LpaInspeksi();
        filter.setLpaUmum(new LpaUmum(lpaId));
//        return toWrapper(lpaInspeksiDao.getByUmum(lpaId));
        LpaInspeksi lpaInspeksi = lpaInspeksiDao.find(filter).get(0);
        return toWrapper(lpaInspeksi);
    }

    @Override
    @Transactional(readOnly = false)
    public LpaInspeksiDto saveInspeksi(LpaInspeksiDto dto, String userId) throws Exception {
        if(checkUserTasklist(userId,dto)) {
            if (!dto.getDraft() && StringUtils.isBlank(dto.getLpaNumber())) {
                dto.setLpaNumber(sequenceDao.lpaNextVal());
            }
            return toWrapper(lpaInspeksiDao.save(toEntity(dto)));
        } else {
            throw new Exception("Gagal : Posisi Order Tidak Pada Tasklist Anda");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteInspeksi(Long id, String userId) throws Exception {
        try {
            LpaInspeksi lpaInspeksi = lpaInspeksiDao.get(id);
//            DataCommon dataCommon = commonDao.get(lpaInspeksi.getLpaUmum().getCommonId());
//            if(StringUtils.equalsIgnoreCase(dataCommon.getTaskList(), userId)) {
            if(checkUserTasklist(userId, toWrapper(lpaInspeksi))) {
                lpaInspeksiDao.delete(id);
            } else {
                throw new Exception("Gagal : Posisi Order Tidak Pada Tasklist Anda");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = false)
    public LpaItemInspeksiDto
    saveItemInspeksi(LpaItemInspeksiDto dto, String userId) throws Exception {
        LpaItemInspeksi lpaItemInspeksi = toItemEntity(dto);

//        DataCommon dataCommon = commonDao.get(lpaItemInspeksi.getLpaInspeksi().getLpaUmum().getCommonId());
//        if(StringUtils.equalsIgnoreCase(userId,dataCommon.getTaskList())) {
        if(checkUserTasklist(userId, toWrapper(lpaItemInspeksi.getLpaInspeksi()))) {
            return toItemWrapper(lpaItemInspeksiDao.save(lpaItemInspeksi));
        } else {
            throw new Exception("Gagal : Posisi Order Tidak Pada Tasklist Anda");
        }

    }

    @Override
    public LpaItemInspeksiDto getItemInspeksi(Long id) {
        return toItemWrapper(lpaItemInspeksiDao.get(id));
    }

    @Override
    public List<LpaItemInspeksiDto> getListItemInspeksiByInspeksiId(Long lpaInspeksiId, String typeGoods) {
        List<LpaItemInspeksiDto> result = new ArrayList<LpaItemInspeksiDto>();
        if(lpaInspeksiId!=null) {
            LpaItemInspeksi filter = new LpaItemInspeksi(new LpaInspeksi(lpaInspeksiId));
            filter.setType(typeGoods);
            List<LpaItemInspeksi> dataList = lpaItemInspeksiDao.find(filter);
            for(LpaItemInspeksi row : dataList) {
                result.add(toItemWrapper(row));
            }
        }
        return result;
    }

    @Override
    public List<LpaItemInspeksiDto> getListSurveyItemInspeksiByCommonId(Long commonId, String typeGoods) {
        DataCommon e = commonDao.get(commonId);
        SurveyInspeksi surveyInspeksiFilter =  new SurveyInspeksi();
        surveyInspeksiFilter.setDetail(e.getDataDetail());

        SurveyInspeksi surveyInspeksi = surveyInspeksiDao.find(surveyInspeksiFilter).get(0);
        List<LpaItemInspeksiDto> result = new ArrayList<LpaItemInspeksiDto>();
        if(surveyInspeksi!=null) {
            SurveyItemInspeksi surveyItemInspeksiFilter = new SurveyItemInspeksi(surveyInspeksiFilter);
            surveyItemInspeksiFilter.setType(typeGoods);
            surveyItemInspeksiFilter.setSurveyInspeksi(surveyInspeksi);
            List<SurveyItemInspeksi> surveyItemInspeksiList = surveyItemInspeksiDao.findExact(surveyItemInspeksiFilter);
            for (SurveyItemInspeksi surveyItemInspeksi : surveyItemInspeksiList) {
                LpaItemInspeksi lpaItemInspeksi = new LpaItemInspeksi();
                lpaItemInspeksi.setType(surveyItemInspeksi.getType());
                lpaItemInspeksi.setName(surveyItemInspeksi.getName());
                lpaItemInspeksi.setTotalDoc(surveyItemInspeksi.getTotalDoc());
                lpaItemInspeksi.setTotalFisik(surveyItemInspeksi.getTotalFisik());
                result.add(toItemWrapper(lpaItemInspeksi));
            }
        }
        return result;
    }

    @Override
    @Transactional(readOnly = false)
    public void copyItemInspeksiFromSurvey(Long commonId, Long idLpaInspeksi) {
        DataCommon e = commonDao.get(commonId);
        SurveyInspeksi surveyInspeksiFilter =  new SurveyInspeksi();
        surveyInspeksiFilter.setDetail(e.getDataDetail());
        SurveyInspeksi surveyInspeksi = surveyInspeksiDao.find(surveyInspeksiFilter).get(0);
        lpaInspeksiDao.copyDataItemInspeksiFromSurvey(idLpaInspeksi, surveyInspeksi.getId());
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteItemInspeksi(Long id, String userId) throws Exception {
        try {
            LpaItemInspeksi lpaItemInspeksi = lpaItemInspeksiDao.get(id);
            DataCommon dataCommon = commonDao.get(lpaItemInspeksi.getLpaInspeksi().getLpaUmum().getCommonId());
            if(StringUtils.equalsIgnoreCase(dataCommon.getTaskList(), userId)) {
                lpaItemInspeksiDao.delete(id);
            } else {
                throw new Exception("Gagal : Posisi Order Tidak Pada Tasklist Anda");
            }
        } catch (Exception e) {
            logger.error(e.getCause());
            throw e;
        }
    }

    @Override
    public Boolean checkSurveyStatusData(Long commonId) {
        DataCommon e = commonDao.get(commonId);
        SurveyInspeksi surveyInspeksi = new SurveyInspeksi();
        surveyInspeksi.setDetail(e.getDataDetail());
        return 0 < surveyInspeksiDao.countSurvey(surveyInspeksi);
    }

    private LpaItemInspeksi toItemEntity(LpaItemInspeksiDto w) {
        LpaItemInspeksi e = new LpaItemInspeksi();
        if (w.getId() != null) {
            e = lpaItemInspeksiDao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        e.setLpaInspeksi(lpaInspeksiDao.get(w.getLpaInspeksiId()));
        return e;
    }

    private LpaItemInspeksiDto toItemWrapper(LpaItemInspeksi e) {
        LpaItemInspeksiDto w = new LpaItemInspeksiDto();
        BeanUtils.copyProperties(e, w);
        w.setLpaInspeksiId(e.getLpaInspeksi()!=null? e.getLpaInspeksi().getId():null);
        w.setLpaId(e.getLpaInspeksi()!=null?e.getLpaInspeksi().getLpaUmum().getId():null);
        return w;
    }


     @Override
     public LpaInspeksiDto toWrapper(LpaInspeksi lpaInspeksi) {
         LpaInspeksiDto lpaInspeksiDto = new LpaInspeksiDto();
         BeanUtils.copyProperties(lpaUmumFacade.toWrapper(lpaInspeksi.getLpaUmum()),lpaInspeksiDto);
         BeanUtils.copyProperties(lpaInspeksi, lpaInspeksiDto);
         return lpaInspeksiDto;
     }

     @Override
     public LpaInspeksi toEntity(LpaInspeksiDto lpaInspeksiDto) {
         LpaUmum umum = lpaUmumDao.save(lpaUmumFacade.toEntity(lpaInspeksiDto));
         LpaInspeksi lpaInspeksi = new LpaInspeksi();
         if (lpaInspeksiDto.getId() != null) {
             lpaInspeksi = lpaInspeksiDao.get(lpaInspeksiDto.getId());
         }
         BeanUtils.copyProperties(lpaInspeksiDto, lpaInspeksi);
         lpaInspeksi.setLpaUmum(umum);
         return lpaInspeksi;
     }

     private Boolean checkUserTasklist(String userId, LpaInspeksiDto dto) {
         DataCommon e = commonDao.get(dto.getCommonId());
         return StringUtils.equalsIgnoreCase(e.getTaskList(),userId);
     }
}
