package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.dao.*;
import com.permata.appraisal2.dto.LpaDesktopDto;
import com.permata.appraisal2.dto.LpaUmumDto;
import com.permata.appraisal2.facade.LpaDesktopFacade;
import com.permata.appraisal2.facade.LpaUmumFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.*;
import com.permata.appraisal2.util.ListUtils;
import com.permata.appraisal2.util.ParserUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

//@Service
//@Transactional (readOnly = true)
@Deprecated
public class LpaDesktopFacadeImpl extends FacadeBase<LpaDesktopDto, LpaDesktop> implements LpaDesktopFacade {

    @Autowired
    private LpaDesktopDao dao;
    @Autowired
    private DataCommonDao commonDao;
    @Autowired
    private ParameterDao parameterDao;
    @Autowired
    private TableSequenceDao sequenceDao;
    @Autowired
    private LpaUmumDao lpaUmumDao;

    @Autowired
    private LpaUmumFacade facade;

    @Override
    public LpaDesktopDto create(Long dataCommonId) {

        DataCommon e = commonDao.get(dataCommonId);
        DataDetail detail = e.getDataDetail();

        LpaDesktopDto w = new LpaDesktopDto();
        LpaUmumDto lpaUmum = facade.create(dataCommonId);
        BeanUtils.copyProperties(lpaUmum, w);

        Double nilaiProsentase = 50d;
        List<Parameter> prosentaseLikuidasi = parameterDao.findByGroup(AppConstans.GroupParameter.PROSENTASE_LIKUIDASI);
        if (ListUtils.isNotEmpty(prosentaseLikuidasi)) {
            nilaiProsentase = Double.parseDouble(prosentaseLikuidasi.get(0).getValue());
        }

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

//        if (ListUtils.isNotEmpty(e.getDataDetail().getAttactments())) {
//            DataAttactment attactment = e.getDataDetail().getAttactments().get(0);
//
//            LpaDokumenTanahDto dokumenTanahDto = new LpaDokumenTanahDto();
//            dokumenTanahDto.setJenisDokumenId(attactment.getDocumentType().getId());
//            dokumenTanahDto.setJenisDokumenNama(attactment.getDocumentType().getName());
//            dokumenTanahDto.setNamaDokumen(attactment.getName());
//            dokumenTanahDto.setNamaJalan(e.getDataDetail().getAlamat());
//            dokumenTanahDto.setBlockGangLantai(e.getDataDetail().getBlokFloor());
//            dokumenTanahDto.setNomor(e.getDataDetail().getNumber());
//            dokumenTanahDto.setRt(e.getDataDetail().getRt());
//            dokumenTanahDto.setRw(e.getDataDetail().getRw());
//            dokumenTanahDto.setKelurahan(e.getDataDetail().getSubDistrict());
//            dokumenTanahDto.setKecamatan(e.getDataDetail().getDistrict());
//            dokumenTanahDto.setKota(e.getDataDetail().getCity());
//            dokumenTanahDto.setPropinsi(e.getDataDetail().getProvince());
//            if (ListUtils.isNotEmpty(detail.getTanah())) {
//                dokumenTanahDto.setLuasTanah(ParserUtils.parseToDouble(detail.getTanah().get(0).getLuas()));
//            }
//            w.setDokumenTanah(dokumenTanahDto);
//        }
//        if (ListUtils.isNotEmpty(detail.getTanah())) {
//            SurveyTanah surveyTanah = detail.getTanah().get(0);
//            w.setBentukTanah(surveyTanah.getBentuk());
//            w.setKonturTanah(surveyTanah.getKontur());
//            w.setKetinggianTanah(surveyTanah.getKetinggian());
//            w.setBatasTanah(surveyTanah.getBatas());
//            w.setBatasUtaraTanah(surveyTanah.getUtara());
//            w.setBatasSelatanTanah(surveyTanah.getSelatan());
//            w.setBatasBaratTanah(surveyTanah.getBarat());
//            w.setBatasTimurTanah(surveyTanah.getTimur());
//            w.setPosisiTanah(surveyTanah.getPosisi());
//            w.setKondisiTanah(surveyTanah.getKondisi());
//            w.setJenisTanah(surveyTanah.getJenis());
//            if (ListUtils.isNotEmpty(e.getDataDetail().getBangunans())) {
//                SurveyBangunan surveyBangunan = e.getDataDetail().getBangunans().get(0);
//                w.setPeruntukanTanah(surveyBangunan.getPeruntukan());
//            }
//        }
//        if (ListUtils.isNotEmpty(detail.getBangunans())) {
//            SurveyBangunan surveyBangunan = detail.getBangunans().get(0);
//            w.setLuasLantai1(ParserUtils.parseToDouble(surveyBangunan.getLantai1()));
//            w.setLuasLantai2(ParserUtils.parseToDouble(surveyBangunan.getLantai2()));
//            w.setLuasLantai3(ParserUtils.parseToDouble(surveyBangunan.getLantai3()));
//            w.setLuasLantai4(ParserUtils.parseToDouble(surveyBangunan.getLantai4()));
//            w.setLuasLantai5(ParserUtils.parseToDouble(surveyBangunan.getLantai5()));
//            w.setLuasBangunan(ParserUtils.parseToDouble(surveyBangunan.getLuas()));
//            w.setJumlahLantaiFisik(surveyBangunan.getJumlahLantai());
//            w.setJumlahLantaiTampakDepan(surveyBangunan.getJumlahLantaiTampakDepan());
//            w.setJenisBangunan(surveyBangunan.getJenis());
//            w.setHitungManual(ParserUtils.parseToBoolean(surveyBangunan.getManual()));
//            if (e.getDataDetail().getSurvey() != null) {
//                w.setDigunakanSebagai(detail.getSurvey().getDipergunakan());
//                w.setDihuniOleh(detail.getSurvey().getDihuni());
//            }
//            w.setTahunDibangun(surveyBangunan.getTahunDibangun());
//            w.setTahunDirenov(surveyBangunan.getTahunRenovasi());
//            w.setKelasBangunan(surveyBangunan.getKelas());
//            w.setKondisiUmum(surveyBangunan.getKondisiUmum());
//        }
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
        w.setUraian1("tanah");
        w.setUraian2("bangunan");
        w.setDiberiNilaiDokumen1(true);
        w.setDiberiNilaiDokumen2(true);
        w.setDiberiNilaiFisik1(true);
        w.setDiberiNilaiFisik2(true);

        return w;
    }

    @Override
    @Transactional
    public LpaDesktopDto save(LpaDesktopDto dto) {
        if (!dto.getDraft() && StringUtils.isBlank(dto.getLpaNumber())) {
            dto.setLpaNumber(sequenceDao.lpaNextVal());
        }
        return toWrapper(dao.save(toEntity(dto)));
    }

    @Override
    public LpaDesktopDto getByUmum(Long lpaId) {
        return toWrapper(dao.getByUmum(lpaId));
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        try {
            dao.delete(id);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public LpaDesktopDto toWrapper(LpaDesktop e) {
        LpaDesktopDto w = new LpaDesktopDto();
        BeanUtils.copyProperties(facade.toWrapper(e.getLpaUmum()), w);
        BeanUtils.copyProperties(e, w);
        return w;
    }

    @Override
    public LpaDesktop toEntity(LpaDesktopDto w) {
        LpaUmum umum = lpaUmumDao.save(facade.toEntity(w));
        LpaDesktop e = new LpaDesktop();
        if (w.getId() != null) {
            e = dao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        e.setLpaUmum(umum);
        return e;
    }
}