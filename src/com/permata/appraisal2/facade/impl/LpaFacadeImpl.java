package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.dao.*;
import com.permata.appraisal2.dto.*;
import com.permata.appraisal2.facade.LpaFacade;
import com.permata.appraisal2.facade.LpaUmumFacade;
import com.permata.appraisal2.model.*;
import com.permata.appraisal2.util.ListUtils;
import com.permata.appraisal2.util.ParserUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class LpaFacadeImpl implements LpaFacade {

    @Autowired
    private SurveyTanahDao surveyTanahDao;
    @Autowired
    private SurveyBangunanDao surveyBangunanDao;
    @Autowired
    private LpaTanahBangunanDao lpaTanahBangunanDao;
    @Autowired
    private DataCommonDao commonDao;
    @Autowired
    private LpaTanahDao lpaTanahDao;
    @Autowired
    private DocumentTypeDao documentTypeDao;
    @Autowired
    private LpaBangunanDao lpaBangunanDao;
    @Autowired
    private TableSequenceDao sequenceDao;
    @Autowired
    private SurveyImageDao surveyImageDao;
    @Autowired
    private LpaUmumDao lpaUmumDao;
    @Autowired
    private DocumentLpaTypeDao documentLpaTypeDao;
    @Autowired
    private ParameterDao parameterDao;

    @Autowired
    private LpaUmumFacade facade;
    @Autowired
    private DataAttactmentDao dataAttactmentDao;

    @Override
    @Transactional(readOnly = false)
    public LpaInternalCreateDto saveTanahBangunan(LpaInternalCreateDto dto, String userId) throws Exception {
        if (!dto.getDraft() && StringUtils.isBlank(dto.getLpaNumber())&&dto.getKategoriJenisAppraisal().equals(AppConstans.JenisAppraisalCategory.INTERNAL)) {
            String code = AppConstans.OrderDataCommon.getOrderPattern();
            Integer orderNumber = sequenceDao.nextVal(code.concat("-LPA"));
            dto.setLpaNumber(String.format(AppConstans.OrderDataCommon.ORDER_PATTERN, code, orderNumber));
        }
        // check dokumen tanah
        if (ListUtils.isEmpty(dto.getListDokumenTanah()) && dto.getDokumenTanah() != null) {
            dto.addDokumenTanah(dto.getDokumenTanah());
        }
        // check dokumen bangunan
//        if (ListUtils.isEmpty(dto.getListDokumenBangunan()) && dto.getDokumenBangunan() != null) {
//            dto.addDokumenBangunan(dto.getDokumenBangunan());
//        }

        if(checkUserTasklist(userId,dto)) {
            return toTanahBangunanWrapper(lpaTanahBangunanDao.save(toTanahBangunanEntity(dto)));
        } else {
            throw new Exception("Gagal : Posisi Order Tidak Pada Tasklist Anda");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public LpaDokumenBangunanDto saveDokumenBangunan(LpaDokumenBangunanDto dto) {
        LpaBangunan bangunan = new LpaBangunan();
        if (dto.getId() != null) {
            bangunan = lpaBangunanDao.get(dto.getId());
        }
        BeanUtils.copyProperties(dto, bangunan);
        bangunan.setCommon(lpaTanahBangunanDao.get(dto.getCommonId()));
        lpaBangunanDao.save(bangunan);

        return toDokumenBangunanWrapper(bangunan);
    }

    @Override
    @Transactional(readOnly = false)
    public LpaDokumenTanahDto saveDokumenTanah(LpaDokumenTanahDto dto) {
        LpaTanah tanah = new LpaTanah();
        if (dto.getId() != null) {
            tanah = lpaTanahDao.get(dto.getId());
        }
        BeanUtils.copyProperties(dto, tanah);
        tanah.setCommon(lpaTanahBangunanDao.get(dto.getCommonId()));
        tanah.setJenisDokumen(documentTypeDao.get(dto.getJenisDokumenId()));
        lpaTanahDao.save(tanah);

        return toDokumenTanahWrapper(tanah);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteDokumenBangunan(Long id) throws Exception {
        try {
            lpaBangunanDao.delete(id);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteDokumenTanah(Long id) throws Exception {
        try {
            lpaTanahDao.delete(id);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteLpa(Long id) throws Exception {
        try {
//            LpaTanahBangunan lpa = lpaTanahBangunanDao.get(id);
//            for (LpaTanah row : lpa.getDocumentTanah()) {
//                lpaTanahDao.delete(row.getId());
//            }
//            lpa.getDocumentTanah().clear();
//            for (LpaBangunan row : lpa.getDocumentBangunan()) {
//                lpaBangunanDao.delete(row.getId());
//            }
//            lpa.getDocumentBangunan().clear();
            lpaTanahBangunanDao.delete(id);
        } catch (Exception e) {
            throw e;
        }
    }

    private LpaDokumenTanahDto toDokumenTanahWrapper(LpaTanah e) {
        LpaDokumenTanahDto w = new LpaDokumenTanahDto();
        BeanUtils.copyProperties(e, w);
        w.setCommonId(e.getCommon().getId());
        if(e.getJenisDokumen()!=null) {
            w.setJenisDokumenId(e.getJenisDokumen().getId());
            w.setJenisDokumenNama(e.getJenisDokumen().getName());
        }
        return w;
    }

    private LpaDokumenBangunanDto toDokumenBangunanWrapper(LpaBangunan e) {
        LpaDokumenBangunanDto w = new LpaDokumenBangunanDto();
        BeanUtils.copyProperties(e, w);
        w.setCommonId(e.getCommon().getId());
        return w;
    }

    @Override
    public List<LpaDokumenBangunanDto> listDokumenBangunan(Long commonId) {
        if (commonId == null) {
            return new ArrayList<LpaDokumenBangunanDto>();
        }
        LpaBangunan filter = new LpaBangunan();
        filter.setCommon(new LpaTanahBangunan(commonId));
        List<LpaBangunan> listDocBangunan = lpaBangunanDao.getBangunanByIdLnb(commonId);

        List<LpaDokumenBangunanDto> result = new ArrayList<LpaDokumenBangunanDto>();
        for (LpaBangunan r : listDocBangunan) {
            result.add(toDokumenBangunanWrapper(r));
        }
        return result;
    }


    public List<SurveyImageDto> getDocOther(Long idUmum){
        return lpaUmumDao.getDocOther(idUmum);
    }

    @Override
    public List<LpaDokumenTanahDto> listDokumenTanah(Long commonId) {
        if (commonId == null) {
            return new ArrayList<LpaDokumenTanahDto>();
        }
        LpaTanah filter = new LpaTanah();
        filter.setCommon(new LpaTanahBangunan(commonId));
//        List<LpaTanah> listDocBangunan = lpaTanahDao.find(filter);
        List<LpaTanah> listDocTanah = lpaTanahDao.getTanahByIdLnb(commonId);

        List<LpaDokumenTanahDto> result = new ArrayList<LpaDokumenTanahDto>();
        for (LpaTanah r : listDocTanah) {
            result.add(toDokumenTanahWrapper(r));
        }
        return result;
    }

    @Override
    public LpaDokumenTanahDto getTanah(Long id) {
        LpaTanah e = lpaTanahDao.get(id);
        return toDokumenTanahWrapper(e);
    }

    @Override
    public LpaInternalCreateDto get(Long id) {
        return toTanahBangunanWrapper(lpaTanahBangunanDao.getByUmum(id));
    }

    private LpaInternalCreateDto toTanahBangunanWrapper(LpaTanahBangunan e) {
        DataDetail detail = e.getLpaUmum().getDetail();
        DataCommon dataUmum = detail.getDataCommon();

        LpaInternalCreateDto w = new LpaInternalCreateDto();
        BeanUtils.copyProperties(facade.toWrapper(e.getLpaUmum()), w);
        BeanUtils.copyProperties(e, w);

        //Request, Peruntukan softdefault dari data Survey, jika punya


        w.setTypeReport(dataUmum.getTypeReport());
        if (w.getPeruntukanBangunan()== null){
            SurveyBangunan s = new SurveyBangunan();
            s.setDetail(detail);
            List<SurveyBangunan> listSurveyBangunan= surveyBangunanDao.find(s,"");
            if (listSurveyBangunan!=null && !listSurveyBangunan.isEmpty()){
                w.setPeruntukanBangunan(listSurveyBangunan.get(0).getPeruntukan());
            }
        }


if (w.getPeruntukanTanah()==null){
    SurveyTanah surveyTanah = new SurveyTanah();
    surveyTanah.setDetail(detail);
    List<SurveyTanah> listSurveyTanah= surveyTanahDao.find(surveyTanah,"");
    if (listSurveyTanah!=null && !listSurveyTanah.isEmpty()){
        w.setPeruntukanTanah(listSurveyTanah.get(0).getPeruntukanTanah());
    }
}





//        w.setDetailId(e.getDetail().getId());
//        if (ListUtils.isNotEmpty(e.getListNegative())) {
//            for (Parameter param : e.getListNegative()) {
//                w.addListNegative(param.getId());
//            }
//        }
//        if (ListUtils.isNotEmpty(e.getListPertimbangan())) {
//            for (Parameter param : e.getListPertimbangan()) {
//                w.addListPertimbangan(param.getId());
//            }
//        }
        if (ListUtils.isNotEmpty(e.getDocumentTanah())) {
            boolean first = true;
            List<LpaTanah> listDocTanah = lpaTanahDao.getTanahByIdLnb(e.getId());
            for (LpaTanah tanah : listDocTanah) {
                LpaDokumenTanahDto docTanah = new LpaDokumenTanahDto();
                BeanUtils.copyProperties(tanah, docTanah);

                docTanah.setJenisDokumenId(null);
                docTanah.setJenisDokumenNama(null);

                if (tanah.getJenisDokumen() !=null){
                    docTanah.setJenisDokumenId(tanah.getJenisDokumen().getId());
                    docTanah.setJenisDokumenNama(tanah.getJenisDokumen().getName());
                }


                w.addDokumenTanah(docTanah);
                if (first) {
                    w.setDokumenTanah(docTanah);
                    first = false;
                }
            }
        }
        if (ListUtils.isNotEmpty(e.getDocumentBangunan())) {
            boolean first = true;
            Double totalBangunanFisik = 0d;
            List<LpaBangunan> listDocBangunan = lpaBangunanDao.getBangunanByIdLnb(e.getId());
            for (LpaBangunan bangunan : listDocBangunan) {
                LpaDokumenBangunanDto docTanah = new LpaDokumenBangunanDto();
                BeanUtils.copyProperties(bangunan, docTanah);
                w.addDokumenBangunan(docTanah);
                totalBangunanFisik = totalBangunanFisik + (bangunan.getLuasBangunan() == null ? 0d : bangunan.getLuasBangunan());
                if (first) {
                    w.setDokumenBangunan(docTanah);
                    first = false;
                }
            }
            w.setLuasBangunanImb(totalBangunanFisik);
        }
        return w;
    }

    private LpaTanahBangunan toTanahBangunanEntity(LpaInternalCreateDto w) {
        if (w.getHargaTanah()==null){
            w.setHargaTanah(BigDecimal.ZERO);
        }

        if (w.getHargaBangunan()==null){
            w.setHargaBangunan(BigDecimal.ZERO);
        }

        LpaUmum umum = lpaUmumDao.save(facade.toEntity(w));
        LpaTanahBangunan e = new LpaTanahBangunan();
        if (w.getId() != null) {
            e = lpaTanahBangunanDao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        e.setLpaUmum(umum);

        if (ListUtils.isNotEmpty(w.getListDokumenTanah())) {
            for (LpaDokumenTanahDto row : w.getListDokumenTanah()) {
                LpaTanah tanah = new LpaTanah();
                if (row.getId() != null) {
                    tanah = lpaTanahDao.get(row.getId());
                }
                BeanUtils.copyProperties(row, tanah);
                tanah.setJenisDokumen(null);
                if (row.getJenisDokumenId()!=null){
                    tanah.setJenisDokumen(documentTypeDao.get(row.getJenisDokumenId()));
                }

                e.addDocumentTanah(tanah);
            }
        }
        if (w.getDokumenBangunan() != null) {
            LpaBangunan entity = new LpaBangunan();
            if (w.getDokumenBangunan().getId() != null) {
                entity = lpaBangunanDao.get(w.getDokumenBangunan().getId());
            }
            BeanUtils.copyProperties(w.getDokumenBangunan(), entity);
            e.addDocumentBangunan(entity);
        }

//        if (ListUtils.isNotEmpty(w.getListNegative())){
//            for(Long row : w.getListNegative()){
//
//            }
//        }

        if (ListUtils.isNotEmpty(w.getListDokumenBangunan())) {
            for (LpaDokumenBangunanDto row : w.getListDokumenBangunan()) {
                LpaBangunan entity = new LpaBangunan();
                if (row.getId() != null) {
                    entity = lpaBangunanDao.get(row.getId());
                }
                BeanUtils.copyProperties(row, entity);
                e.addDocumentBangunan(entity);
            }
        }
        return e;
    }

    @Override
    public LpaInternalCreateDto createTanahBangungan(Long dataCommonId) {

        DataCommon e = commonDao.get(dataCommonId);
        DataDetail detail = e.getDataDetail();

        LpaInternalCreateDto w = new LpaInternalCreateDto();
        LpaUmumDto lpaUmum = facade.create(dataCommonId);
        BeanUtils.copyProperties(lpaUmum, w);

        Double nilaiProsentase = 50d;
        List<Parameter> prosentaseLikuidasi = parameterDao.findByGroup(AppConstans.GroupParameter.PROSENTASE_LIKUIDASI);
        if (ListUtils.isNotEmpty(prosentaseLikuidasi)) {
            nilaiProsentase = Double.parseDouble(prosentaseLikuidasi.get(0).getValue());
        }

        w.setTypeReport(e.getTypeReport());
        w.setAppraisalTypeCategory(e.getAppraisalTypeCategory());
        w.setLuasBangunanImb(0d);
        w.setTotalLuasTanahDokumen(0d);
        w.setHargaTanah(BigDecimal.ZERO);
        w.setHargaBangunan(BigDecimal.ZERO);
        w.setMataUang(AppConstans.MataUang.IDR);
        w.setTanggalRate(new Date());
        w.setNilaiRate(BigDecimal.ONE);
        w.setProsentase1(nilaiProsentase);
        w.setProsentase2(nilaiProsentase);
        w.setProsentase1Eks(nilaiProsentase);
        w.setProsentase2Eks(nilaiProsentase);
        w.setLokasiFisik(detail.getAlamat());



        List<DataAttactment> dataAttactments = dataAttactmentDao.getAttachmentsWithoutBlob(e.getDataDetail());
        if (ListUtils.isNotEmpty(dataAttactments)) {
            DataAttactment attactment = dataAttactments.get(0);

            LpaDokumenTanahDto dokumenTanahDto = new LpaDokumenTanahDto();
            dokumenTanahDto.setJenisDokumenId(attactment.getDocumentType().getId());
            dokumenTanahDto.setJenisDokumenNama(attactment.getDocumentType().getName());
            dokumenTanahDto.setNamaDokumen(attactment.getName());
            dokumenTanahDto.setNamaJalan(e.getDataDetail().getAlamat());
            dokumenTanahDto.setBlockGangLantai(e.getDataDetail().getBlokFloor());
            dokumenTanahDto.setNomor(e.getDataDetail().getNumber());
            dokumenTanahDto.setRt(e.getDataDetail().getRt());
            dokumenTanahDto.setRw(e.getDataDetail().getRw());
            dokumenTanahDto.setKelurahan(e.getDataDetail().getSubDistrict());
            dokumenTanahDto.setKecamatan(e.getDataDetail().getDistrict());
            dokumenTanahDto.setKota(e.getDataDetail().getCity());
            dokumenTanahDto.setPropinsi(e.getDataDetail().getProvince());
            if (ListUtils.isNotEmpty(detail.getTanah())) {
                dokumenTanahDto.setLuasTanah(ParserUtils.parseToDouble(detail.getTanah().get(0).getLuas()));
            }
            w.setDokumenTanah(dokumenTanahDto);
        }
        if (ListUtils.isNotEmpty(detail.getTanah())) {
            SurveyTanah surveyTanah = detail.getTanah().get(0);
            w.setBentukTanah(surveyTanah.getBentuk());
            w.setKonturTanah(surveyTanah.getKontur());
            w.setKetinggianTanah(surveyTanah.getKetinggian());
            w.setBatasTanah(surveyTanah.getBatas());
            w.setBatasUtaraTanah(surveyTanah.getUtara());
            w.setBatasSelatanTanah(surveyTanah.getSelatan());
            w.setBatasBaratTanah(surveyTanah.getBarat());
            w.setBatasTimurTanah(surveyTanah.getTimur());
            w.setPosisiTanah(surveyTanah.getPosisi());
            w.setKondisiTanah(surveyTanah.getKondisi());
            w.setJenisTanah(surveyTanah.getJenis());
//            if (ListUtils.isNotEmpty(e.getDataDetail().getBangunans())) {
//                SurveyBangunan surveyBangunan = e.getDataDetail().getBangunans().get(0);
//               // w.setPeruntukanTanah(surveyBangunan.getPeruntukan());
//            }
        }
        if (ListUtils.isNotEmpty(detail.getBangunans())) {
            SurveyBangunan surveyBangunan = detail.getBangunans().get(0);
            w.setLuasLantai1(ParserUtils.parseToDouble(surveyBangunan.getLantai1()));
            w.setLuasLantai2(ParserUtils.parseToDouble(surveyBangunan.getLantai2()));
            w.setLuasLantai3(ParserUtils.parseToDouble(surveyBangunan.getLantai3()));
            w.setLuasLantai4(ParserUtils.parseToDouble(surveyBangunan.getLantai4()));
            w.setLuasLantai5(ParserUtils.parseToDouble(surveyBangunan.getLantai5()));
            w.setLuasBangunan(ParserUtils.parseToDouble(surveyBangunan.getLuas()));
            w.setJumlahLantaiFisik(surveyBangunan.getJumlahLantai());
            w.setJumlahLantaiTampakDepan(surveyBangunan.getJumlahLantaiTampakDepan());
            w.setJenisBangunan(surveyBangunan.getJenis());
            w.setHitungManual(ParserUtils.parseToBoolean(surveyBangunan.getManual()));
            if (e.getDataDetail().getSurvey() != null) {
                w.setDigunakanSebagai(detail.getSurvey().getDipergunakan());
                w.setDihuniOleh(detail.getSurvey().getDihuni());
            }
            w.setTahunDibangun(surveyBangunan.getTahunDibangun());
            w.setTahunDirenov(surveyBangunan.getTahunRenovasi());
            w.setKelasBangunan(surveyBangunan.getKelas());
            w.setKondisiUmum(surveyBangunan.getKondisiUmum());
        }
        if (detail.getLingkungan() != null) {
            w.setLingkunganSekitar(detail.getLingkungan().getSekitar());
            w.setTingkatKepadatan(detail.getLingkungan().getKepadatan());
            w.setTingkatKemacetan(detail.getLingkungan().getKemacetan());
            w.setRencanaJalan(detail.getLingkungan().getRencanaJalan());
            w.setLebarJalan(ParserUtils.parseToDouble(detail.getLingkungan().getLebarJalan()));
            w.setGsb(ParserUtils.parseToDouble(detail.getLingkungan().getGsb()));
            w.setGss(ParserUtils.parseToDouble(detail.getLingkungan().getGss()));
            w.setKendaraanRoda4(detail.getLingkungan().getRoda4());
        }
        if(detail.getAssetType().getName().toLowerCase().contains("apartemen") ||
                detail.getAssetType().getName().toLowerCase().contains("kios") ||
                detail.getAssetType().getName().toLowerCase().contains("ruang")){
            w.setUraian1("unit");
            w.setUraian2("tidak");
            w.setUraian1Eks("unit");
            w.setUraian2Eks("tidak");
        } else {
            w.setUraian1("tanah");
            w.setUraian2("bangunan");
            w.setUraian1Eks("tanah");
            w.setUraian2Eks("bangunan");
        }
        w.setDiberiNilaiDokumen1(true);
        w.setDiberiNilaiDokumen2(true);
        w.setDiberiNilaiFisik1(true);
        w.setDiberiNilaiFisik2(true);
        w.setDiberiNilaiDokumen1Eks(true);
        w.setDiberiNilaiDokumen2Eks(true);
        w.setDiberiNilaiFisik1Eks(true);
        w.setDiberiNilaiFisik2Eks(true);


        if (w.getPeruntukanBangunan()== null){
            SurveyBangunan s = new SurveyBangunan();
            s.setDetail(detail);
            List<SurveyBangunan> listSurveyBangunan= surveyBangunanDao.find(s,"");
            if (listSurveyBangunan!=null && !listSurveyBangunan.isEmpty()){
                w.setPeruntukanBangunan(listSurveyBangunan.get(0).getPeruntukan());
            }
        }


        if (w.getPeruntukanTanah()==null){
            SurveyTanah surveyTanah = new SurveyTanah();
            surveyTanah.setDetail(detail);
            List<SurveyTanah> listSurveyTanah= surveyTanahDao.find(surveyTanah,"");
            if (listSurveyTanah!=null && !listSurveyTanah.isEmpty()){
                w.setPeruntukanTanah(listSurveyTanah.get(0).getPeruntukanTanah());
            }
        }

        return w;
    }

    @Override
    public LpaImageDto getImage(Long id) {
        return toImageWrapper(surveyImageDao.get(id));
    }

    @Override
    @Transactional(readOnly = false)
    public LpaImageDto saveImage(LpaImageDto dto) {
        return toImageWrapper(surveyImageDao.save(toImageEntity(dto)));
    }

    @Override
    public List<LpaImageDto> findImage(Long surveyId, Long lpaId) {
        // // TODO: 11/14/2017  Fix not to load all attachment content see data attach load
        List<SurveyImage> listImage = new ArrayList<SurveyImage>();
        if (surveyId != null && lpaId == null) {
            listImage = surveyImageDao.findBySurvey(surveyId);
        } else if (surveyId != null && lpaId != null) {
            listImage = surveyImageDao.findByLpaOrSurvey(surveyId, lpaId);
        } else if (surveyId == null && lpaId != null) {
            listImage = surveyImageDao.findByLpa(lpaId);
        } else {
            // do nothing
        }

        List<LpaImageDto> result = new ArrayList<LpaImageDto>();
        for (SurveyImage row : listImage) {
            result.add(toImageWrapper(row));
        }
        return result;
    }

    private LpaImageDto toImageWrapper(SurveyImage e) {
        LpaImageDto w = new LpaImageDto();
        BeanUtils.copyProperties(e, w);
        if (e.getCommon() != null) {
            w.setCommonId(e.getCommon().getId());
        }
        if (e.getDocumentLpaType() != null) {
            w.setIdDocumentLpa(e.getDocumentLpaType().getId());
            w.setNamaDocumentLpa(e.getDocumentLpaType().getName());
            w.setReadFileYn(e.getDocumentLpaType().getReadFileYn());
        }
        return w;
    }

    private SurveyImage toImageEntity(LpaImageDto w) {
        SurveyImage e = new SurveyImage();
        if (w.getId() != null) {
            e = surveyImageDao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e, "common", "image", "contentType");
        if (w.getImage() != null && w.getImage().length > 0) {
            e.setImage(w.getImage());
            e.setContentType(w.getContentType());
        }
        if (w.getIdDocumentLpa() != null) {
            e.setDocumentLpaType(documentLpaTypeDao.get(w.getIdDocumentLpa()));
        }
        return e;
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteImage(Long id) throws Exception {
        try {
            surveyImageDao.delete(id);
        } catch (Exception e) {
            throw e;
        }
    }

    private Boolean checkUserTasklist(String userId, LpaInternalCreateDto dto) {
        DataCommon e = commonDao.get(dto.getCommonId());
        return StringUtils.equalsIgnoreCase(e.getTaskList(),userId);
    }
}