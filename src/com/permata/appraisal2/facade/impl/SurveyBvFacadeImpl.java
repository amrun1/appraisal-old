package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.dao.*;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.SurveyBvDto;
import com.permata.appraisal2.facade.SurveyBvFacade;
import com.permata.appraisal2.facade.common.FacadeBase;
import com.permata.appraisal2.model.*;
import com.permata.appraisal2.util.ListUtils;
import com.permata.appraisal2.util.ParserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class SurveyBvFacadeImpl extends FacadeBase<SurveyBvDto, SurveyBv> implements SurveyBvFacade {

    @Autowired
    private SurveyBvDao dao;
    @Autowired
    private DataDetailDao detailDao;
    @Autowired
    private SurveyCommonDao surveyCommonDao;
//    @Autowired
//    private MobileSurveyBvDao mobileSurveyBvDao;
    @Autowired
    private DataCommonDao dataCommonDao;
    @Autowired
    private JenisAppraisalDao jenisAppraisalDao;
    @Autowired
    private DocumentLpaTypeDao documentLpaTypeDao;
    @Autowired
    private SurveyImageDao surveyImageDao;
    @Autowired
    private PersonDao personDao;

    @Autowired
    private SystemParamDao systemParamDao;
    @Autowired
    private ParameterDao parameterDao;

    @Override
    public SurveyBvDto get(Long id) {
        return toWrapper(dao.get(id));
    }

    @Override
    public SurveyBvDto getByDetail(Long detailId) {
        SurveyBvDto w = toWrapper(dao.getByDetail(detailId));
        if (w == null || w.getId() == null) {
            DataDetail detail = detailDao.get(detailId);
            w = new SurveyBvDto();
            w.setDetailId(detailId);
            w.setDataCommonId(detail.getDataCommon().getId());
            w.setIdJenisAppraisal(detail.getDataCommon().getAppraisalType().getId());
        }
        return w;
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
    @Transactional
    public SurveyBvDto save(SurveyBvDto w) {
        SurveyBv surveyRv = toEntity(w);
        SurveyCommon surveyCommon = new SurveyCommon();
        if (w.getCommonId() != null) {
            surveyCommon = surveyCommonDao.get(w.getCommonId());
        }
        surveyCommon.setDetail(surveyRv.getDetail());
        surveyCommon.setLokasi(w.getAlamat());
        surveyCommon.setDitinjau(w.getDitinjau());
        surveyCommon.setTanggal(w.getTanggalSurvey());
        surveyCommonDao.save(surveyCommon);
        return toWrapper(dao.save(surveyRv));
    }

    @Override
    public SurveyBvDto toWrapper(SurveyBv e) {
        SurveyBvDto w = new SurveyBvDto();
        if (e == null) return w;
        BeanUtils.copyProperties(e, w);
        w.setDetailId(e.getDetail().getId());
        w.setIdJenisAppraisal(e.getDetail().getDataCommon().getAppraisalType().getId());
        w.setDataCommonId(e.getDetail().getDataCommon().getId());
        SurveyCommon surveyCommon = surveyCommonDao.getByDetail(e.getDetail().getId());
        if (surveyCommon != null) {
            w.setCommonId(surveyCommon.getId());
            w.setDitinjau(surveyCommon.getDitinjau());
            w.setAlamat(surveyCommon.getLokasi());
            w.setTanggalSurvey(surveyCommon.getTanggal());
        }
        return w;
    }

    @Override
    public SurveyBv toEntity(SurveyBvDto w) {
        SurveyBv e = new SurveyBv();
        if (w.getId() != null) {
            e = dao.get(w.getId());
        }
        BeanUtils.copyProperties(w, e);
        e.setDetail(detailDao.get(w.getDetailId()));
        return e;
    }

    @Override
    @Transactional
    public void ambilDariMobile(Long commonId, Long detailId, String orderNum) {

        // MobileSurveyBv mobileBv = mobileSurveyBvDao.get(orderNum);
        DataDetail detail = detailDao.get(detailId);
        SurveyCommon surveyCommon = new SurveyCommon();

        Connection con = null;
        try {
            SurveyBv bv = new SurveyBv();
            Class.forName("com.mysql.jdbc.Driver");


            String urlConn = systemParamDao.get("URL_DB_BV").getValue();
            String userNameDB = systemParamDao.get("USER_DB_BV").getValue();
            String passDb = systemParamDao.get("PASS_DB_BV").getValue();

            con = DriverManager.getConnection(urlConn,userNameDB, passDb);
//            con = DriverManager.getConnection("jdbc:mysql://10.87.19.88:3306/db_mob", "appraisal", "Permata123");//urlConn, userNameDB, passDb);
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_mob", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from survey_bv where id_survey = '" + orderNum + "'");
            logger.debug("select * from survey_bv where id_survey = '" + orderNum + "'");



            surveyCommon.setDetail(detail);
            while (rs.next()) {
                Person person = personDao.get(rs.getString(4));
                DataCommon dataCommon = detail.getDataCommon();
                dataCommon.setSurveyBy(person.getUserId());
                dataCommon.setSurveyDate(rs.getTimestamp(5));
                surveyCommon.setLokasi(rs.getString(2)); // ALAMAT_SURVEY
                surveyCommon.setDitinjau(personDao.get(rs.getString(4)).getName()); // ID_SURVEYOR//ganti jad nama
                surveyCommon.setTanggal(rs.getTimestamp(5)); // TGL_SUBMIT_SURVEY

                bv.setVendor(rs.getString(3)); // VENDOR
                bv.setPapanNama(rs.getBoolean(6)); // PAPAN_NAMA
                bv.setKegiatanUsaha(rs.getString(7)); // KEGIATAN_SAAT_SURVEY
                bv.setLokasiDitemukan(rs.getString(8).equalsIgnoreCase("Ditemukan") ? true : false); // LOKASI_OBJEK
                bv.setJam(rs.getString(9)); // LOKASI_OBJEK_JAM
                bv.setSudahPindah(rs.getString(10).equalsIgnoreCase("Ya") ? true : false); // LOKASI_OBJEK_NOTE
                bv.setS1Ditemukan(rs.getBoolean(11)); //SI_1_DITEMUKAN
                bv.setS1Nama(rs.getString(12)); // SI_1_NAMA
                bv.setS1Kelamin(rs.getString(13).equalsIgnoreCase("f") ? "W" : "L"); // SI_1_SEX
                bv.setS1Hubungan(rs.getString(14)); // SI_1_HUBUNGAN
                bv.setS1Kooperatif(rs.getBoolean(15)); // SI_1_KOOPERATIF
                bv.setS2Ditemukan(rs.getBoolean(16)); // SI_2_DITEMUKAN
                bv.setS2Nama(rs.getString(17)); // SI_2_NAMA
                bv.setS2Kelamin(rs.getString(18).equalsIgnoreCase("f") ? "W" : "L"); // SI_2_SEX
                bv.setS2Kenal(rs.getBoolean(19)); // SI_2_KENAL
                bv.setS2Hubungan(rs.getString(20)); // SI_2_HUBUNGAN
                bv.setS2Kooperatif(rs.getBoolean(21)); // SI_2_KOOPERATIF
                bv.setS3Ditemukan(rs.getBoolean(22)); // SI_3_DITEMUKAN
                bv.setS3Nama(rs.getString(23)); // SI_3_NAMA
                bv.setS3Kelamin(rs.getString(24).equalsIgnoreCase("f") ? "W" : "L"); // SI_3_SEX
                bv.setS3Kenal(rs.getBoolean(25)); // SI_3_KENAL
                bv.setS3Hubungan(rs.getString(26)); // SI_3_HUBUNGAN
                bv.setS3Kooperatif(rs.getBoolean(27)); // SI_3_KOOPERATIF
                bv.setSurveyorDiterimaDi(rs.getString(28)); // TEMPAT_DITERIMA
                bv.setJumlahKaryawan(rs.getString(29)); // JUMLAH_KARYAWAN
                bv.setOmzetPenjualan(rs.getString(30)); // OMZET
                bv.setDetailOmzet(ParserUtils.parseToDecimal(rs.getString(31))); // OMZET_DETAIL
                bv.setKondisiUsaha(rs.getString(32).equalsIgnoreCase("Terawat") ? true : false); // KONDISI_TMPT_USAHA
                bv.setJenisUsaha(rs.getString(33)); // JENIS_USAHA
                bv.setBidangUsaha(rs.getString(34)); // BIDANG_USAHA
                bv.setLamaPenempatan(rs.getString(35)); // LAMA_PENEMPATAN
                bv.setLingkunganSekitar(rs.getString(36)); // LINGKUNGAN
                bv.setMasukMobil(rs.getBoolean(37)); // MASUK_MOBIL
                bv.setJalanTempatUsaha(rs.getString(38)); // KONDISI_JALAN
                bv.setBertingkat(rs.getString(39).equalsIgnoreCase("Bertingkat") ? true : false);
                bv.setLuasBangunan(rs.getString(40)); // LUAS_BANGUNAN
                bv.setLuasTanah(rs.getString(41)); // LUAS_TANAH
                bv.setJenisBangunan(rs.getString(42)); // JENIS_BANGUNAN
                if (rs.getBoolean(43)) { // TELEPON
                    bv.addFasilitas("Telepon");
                }
                if (rs.getBoolean(44)) { // KOMPUTER
                    bv.addFasilitas("Komputer");
                }
                if (rs.getBoolean(45)) { // PHOTOCOPY
                    bv.addFasilitas("Photocopy");
                }
                if (rs.getBoolean(46)) { // INTERNET
                    bv.addFasilitas("Internet");
                }
                if (rs.getBoolean(47)) { // KENDARAAN_OPS
                    bv.addFasilitas("Kendaraan Operasional");
                }
                bv.setJumlahMotor(ParserUtils.parseToInteger(rs.getString(48))); // JUMLAH_MOTOR
                bv.setJumlahMobil(ParserUtils.parseToInteger(rs.getString(49))); // JUMLAH_MOBIL
                bv.setJumlahTruk(ParserUtils.parseToInteger(rs.getString(50))); // JUMLAH_TRUK
                bv.setStatusUsaha(rs.getString(51)); // STATUS_MILIK_USAHA
                bv.setStatusLokasi(rs.getString(52)); // STATUS_MILIK_TEMPAT
                if (rs.getBoolean(53)) { // FOTO_DEPAN
//                    bv.addFotoPada("Tampak depan terlihat nomor");
                    bv.addFotoPada(parameterDao.findRVBVFotopada(AppConstans.GroupParameter.BV_FOTO_PADA, 1));
                }
                if (rs.getBoolean(54)) { // FOTO_DEPAN_PLANG
//                    bv.addFotoPada("Tampak depan terlihat papan nama usaha");
                    bv.addFotoPada(parameterDao.findRVBVFotopada(AppConstans.GroupParameter.BV_FOTO_PADA, 2));
                }
                if (rs.getBoolean(55)) { // FOTO_PLANG_NAMA
//                    bv.addFotoPada("Tampak plang nama jalan (jika ada)");
                    bv.addFotoPada(parameterDao.findRVBVFotopada(AppConstans.GroupParameter.BV_FOTO_PADA, 3));
                }
                if (rs.getBoolean(56)) { // FOTO_STOK_DALAM
//                    bv.addFotoPada("Stok Barang (di dalam kotak / dus)");
                    bv.addFotoPada(parameterDao.findRVBVFotopada(AppConstans.GroupParameter.BV_FOTO_PADA, 4));
                }
                if (rs.getBoolean(57)) { // FOTO_STOK_LUAR
//                    bv.addFotoPada("Stok Barang (di luar kotak / dus)");
                    bv.addFotoPada(parameterDao.findRVBVFotopada(AppConstans.GroupParameter.BV_FOTO_PADA, 5));
                }
                if (rs.getBoolean(58)) { // FOTO_AKTIFITAS
//                    bv.addFotoPada("Aktifitas usaha");
                    bv.addFotoPada(parameterDao.findRVBVFotopada(AppConstans.GroupParameter.BV_FOTO_PADA, 6));
                }
                if (rs.getBoolean(59)) { // FOTO_LINGKUNGAN
//                    bv.addFotoPada("Tampak lingkungan lokasi usaha");
                    bv.addFotoPada(parameterDao.findRVBVFotopada(AppConstans.GroupParameter.BV_FOTO_PADA, 7));
                }
                if (rs.getBoolean(60)) { // FOTO_MESIN
//                    bv.addFotoPada("Mesin / peralatan usaha yang digunakan");
                    bv.addFotoPada(parameterDao.findRVBVFotopada(AppConstans.GroupParameter.BV_FOTO_PADA, 8));
                }
                bv.setInfoS1(rs.getString(61)); // INFO_SI_1
                bv.setInfoS2(rs.getString(62)); // INFO_SI_2
                bv.setInfoS3(rs.getString(63)); // INFO_SI_3
                bv.setNote(rs.getString(64)); // NOTE

                logger.debug("starting ambil foto");
                JenisAppraisal appraisal = new JenisAppraisal();
                appraisal.setName("BV");
                List<JenisAppraisal> listAppraisal = jenisAppraisalDao.findExact(PagingInfo.create(), appraisal).getDataList();
                logger.debug("listAppraisal : " + listAppraisal.get(0).getName());
                List<DocumentLpaType> listDoc = documentLpaTypeDao.findByActiva(null, true, listAppraisal.get(0).getId());
                logger.debug("listDoc : " + listDoc.size());
                if (ListUtils.isNotEmpty(listDoc)) {
                    logger.debug("foto1 " + rs.getBytes(65));
                    if (rs.getBytes(65) != null && rs.getBytes(65).length > 0) {
                        SurveyImage foto1 = new SurveyImage();
                        foto1.setCommon(surveyCommon);
                        foto1.setContentType("image/jpeg");
                        foto1.setImage(rs.getBytes(65));
                        foto1.setDocumentLpaType(findByName(listDoc, "Tampak Patokan / Plang Jalan"));
                        foto1.setCatatan("foto1");
                        surveyImageDao.save(foto1);
                    }
                    logger.debug("foto2 " + rs.getBytes(66));
                    if (rs.getBytes(66) != null && rs.getBytes(66).length > 0) {
                        SurveyImage foto2 = new SurveyImage();
                        foto2.setCommon(surveyCommon);
                        foto2.setContentType("image/jpeg");
                        foto2.setImage(rs.getBytes(66));
                        foto2.setDocumentLpaType(findByName(listDoc, "Tampak Depan Lokasi"));
                        foto2.setCatatan("foto2");
                        surveyImageDao.save(foto2);
                    }
                    logger.debug("foto3 " + rs.getBytes(67));
                    if (rs.getBytes(67) != null && rs.getBytes(67).length > 0) {
                        SurveyImage foto3 = new SurveyImage();
                        foto3.setCommon(surveyCommon);
                        foto3.setContentType("image/jpeg");
                        foto3.setImage(rs.getBytes(67));
                        foto3.setDocumentLpaType(findByName(listDoc, "Tampak Papan Nama Usaha"));
                        foto3.setCatatan("foto3");
                        surveyImageDao.save(foto3);
                    }
                    logger.debug("foto4 " + rs.getBytes(68));
                    if (rs.getBytes(68) != null && rs.getBytes(68).length > 0) {
                        SurveyImage foto4 = new SurveyImage();
                        foto4.setCommon(surveyCommon);
                        foto4.setContentType("image/jpeg");
                        foto4.setImage(rs.getBytes(68));
                        foto4.setDocumentLpaType(findByName(listDoc, "Tampak Sekitar Lokasi"));
                        foto4.setCatatan("foto4");
                        surveyImageDao.save(foto4);
                    }
                    logger.debug("foto5 " + rs.getBytes(69));
                    if (rs.getBytes(69) != null && rs.getBytes(69).length > 0) {
                        SurveyImage foto5 = new SurveyImage();
                        foto5.setCommon(surveyCommon);
                        foto5.setContentType("image/jpeg");
                        foto5.setImage(rs.getBytes(69));
                        foto5.setDocumentLpaType(findByName(listDoc, "Tampak No. Lokasi"));
                        foto5.setCatatan("foto5");
                        surveyImageDao.save(foto5);
                    }
                    logger.debug("foto6 " + rs.getBytes(70));
                    if (rs.getBytes(70) != null && rs.getBytes(70).length > 0) {
                        SurveyImage foto6 = new SurveyImage();
                        foto6.setCommon(surveyCommon);
                        foto6.setContentType("image/jpeg");
                        foto6.setImage(rs.getBytes(70));
                        foto6.setDocumentLpaType(findByName(listDoc, "Tampak Dalam Lokasi"));
                        foto6.setCatatan("foto6");
                        surveyImageDao.save(foto6);
                    }
                    logger.debug("foto7 " + rs.getBytes(71));
                    if (rs.getBytes(71) != null && rs.getBytes(71).length > 0) {
                        SurveyImage foto7 = new SurveyImage();
                        foto7.setCommon(surveyCommon);
                        foto7.setContentType("image/jpeg");
                        foto7.setImage(rs.getBytes(71));
                        foto7.setDocumentLpaType(findByName(listDoc, "Tampak Dalam Lokasi"));
                        foto7.setCatatan("foto7");
                        surveyImageDao.save(foto7);
                    }
                    if (rs.getBytes(72) != null && rs.getBytes(72).length > 0) {
                        SurveyImage foto8 = new SurveyImage();
                        foto8.setCommon(surveyCommon);
                        foto8.setContentType("image/jpeg");
                        foto8.setImage(rs.getBytes(72));
                        foto8.setDocumentLpaType(findByName(listDoc, "Tampak Stock Keseluruhan"));
                        foto8.setCatatan("foto8");
                        surveyImageDao.save(foto8);
                    }
                    if (rs.getBytes(73) != null && rs.getBytes(73).length > 0) {
                        SurveyImage foto9 = new SurveyImage();
                        foto9.setCommon(surveyCommon);
                        foto9.setContentType("image/jpeg");
                        foto9.setImage(rs.getBytes(73));
                        foto9.setDocumentLpaType(findByName(listDoc, "Tampak Stock Dibuka"));
                        foto9.setCatatan("foto9");
                        surveyImageDao.save(foto9);
                    }
                    if (rs.getBytes(74) != null && rs.getBytes(74).length > 0) {
                        SurveyImage foto10 = new SurveyImage();
                        foto10.setCommon(surveyCommon);
                        foto10.setContentType("image/jpeg");
                        foto10.setImage(rs.getBytes(74));
                        foto10.setDocumentLpaType(findByName(listDoc, "Tampak Mesin Keseluruhan"));
                        foto10.setCatatan("foto10");
                        surveyImageDao.save(foto10);
                    }
                }

                // rs.getString(1); // id_survey
                dataCommonDao.save(dataCommon);
                bv.setDetail(detail);
                dao.save(bv);
            }
            con.close();

            surveyCommonDao.save(surveyCommon);

            detail.setFrom("Mobile");
            detail.setSurveyStatus(SurveyStatus.Close);
            detailDao.save(detail);
        } catch (Exception e) {
            logger.error("error", e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                }
            }
        }

//        surveyCommonDao.save(surveyCommon);
//
//        detail.setFrom("Mobile");
//        detail.setSurveyStatus(SurveyStatus.Close);
//        detailDao.save(detail);

        // mobileSurveyBvDao.delete(orderNum);
    }

    private DocumentLpaType findByName(List<DocumentLpaType> listAppraisal, String name) {
        for (DocumentLpaType row : listAppraisal) {
            if (row.getName().equalsIgnoreCase(name)) {
                return row;
            }
        }
        return null;
    }

}