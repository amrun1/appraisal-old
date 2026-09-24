package com.permata.appraisal2.facade.impl;

import com.permata.appraisal2.common.AppConstans;
import com.permata.appraisal2.dao.*;
import com.permata.appraisal2.dao.common.PagingInfo;
import com.permata.appraisal2.dto.SurveyRvDto;
import com.permata.appraisal2.facade.SurveyRvFacade;
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
public class SurveyRvFacadeImpl extends FacadeBase<SurveyRvDto, SurveyRv> implements SurveyRvFacade {

    @Autowired
    private SurveyRvDao dao;
    @Autowired
    private DataDetailDao detailDao;
    @Autowired
    private SurveyCommonDao surveyCommonDao;
    @Autowired
    private JenisAppraisalDao jenisAppraisalDao;
    @Autowired
    private DocumentLpaTypeDao documentLpaTypeDao;
    @Autowired
    private SurveyImageDao surveyImageDao;
    @Autowired
    private DataCommonDao dataCommonDao;
    @Autowired
    private SystemParamDao systemParamDao;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private ParameterDao parameterDao;

    @Override
    public SurveyRvDto get(Long id) {
        return toWrapper(dao.get(id));
    }

    @Override
    public SurveyRvDto getByDetail(Long detailId) {
        SurveyRvDto w = toWrapper(dao.getByDetail(detailId));
        if (w == null || w.getId() == null) {
            DataDetail detail = detailDao.get(detailId);
            w = new SurveyRvDto();
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
    public SurveyRvDto save(SurveyRvDto e) {
        SurveyRv surveyRv = toEntity(e);
        SurveyCommon surveyCommon = new SurveyCommon();
        if (e.getCommonId() != null) {
            surveyCommon = surveyCommonDao.get(e.getCommonId());
        }
        surveyCommon.setDetail(surveyRv.getDetail());
        surveyCommon.setLokasi(e.getAlamat());
        surveyCommon.setDitinjau(e.getDitinjau());
        surveyCommonDao.save(surveyCommon);
        return toWrapper(dao.save(surveyRv));
    }

    @Override
    public SurveyRvDto toWrapper(SurveyRv e) {
        SurveyRvDto w = new SurveyRvDto();
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
        }
        return w;
    }

    @Override
    public SurveyRv toEntity(SurveyRvDto w) {
        SurveyRv e = new SurveyRv();
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
        DataDetail detail = detailDao.get(detailId);
        SurveyCommon surveyCommon = new SurveyCommon();
        surveyCommon.setDetail(detail);

        Connection con = null;
        try {
            SurveyRv rv = new SurveyRv();
            Class.forName("com.mysql.jdbc.Driver");

            String urlConn = systemParamDao.get("URL_DB_RV").getValue();
            String userNameDB = systemParamDao.get("USER_DB_RV").getValue();
            String passDb = systemParamDao.get("PASS_DB_RV").getValue();

            con = DriverManager.getConnection(urlConn,userNameDB, passDb);
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_mob", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from survey_rv where id_survey = '" + orderNum + "'");
            logger.debug("select * from survey_bv where id_survey = '" + orderNum + "'");
            while (rs.next()) {
                Person person = personDao.get(rs.getString(4));
                DataCommon dataCommon = detail.getDataCommon();
                dataCommon.setSurveyBy(person.getUserId());
                dataCommon.setSurveyDate(rs.getTimestamp(5));
                surveyCommon.setLokasi(rs.getString(2)); // ALAMAT_SURVEY
                surveyCommon.setDitinjau(person.getName()); // ID_SURVEYOR
                surveyCommon.setTanggal(rs.getTimestamp(5)); // TGL_SUBMIT_SURVEY

                rv.setVendor(rs.getString(3)); // VENDOR
                rv.setUmur(rs.getString(6)); // UMUR_APLIKAN
                rv.setNamaPasangan(rs.getString(7)); // PASANGAN_APLIKAN
                rv.setLokasiDitemukan(rs.getString(8).equalsIgnoreCase("Ditemukan") ? true : false); // LOKASI_OBJEK
                rv.setJam(rs.getString(9)); // LOKASI_OBJEK_JAM
                rv.setSudahPindah(rs.getString(10).equalsIgnoreCase("Ya") ? true : false); // LOKASI_OBJEK_NOTE
                rv.setS1Ditemukan(rs.getBoolean(11)); //SI_1_DITEMUKAN
                rv.setS1Nama(rs.getString(12)); // SI_1_NAMA
                rv.setS1Kelamin(rs.getString(13).equalsIgnoreCase("f") ? "W" : "L"); // SI_1_SEX
                rv.setS1Hubungan(rs.getString(14)); // SI_1_HUBUNGAN
                rv.setS1Kooperatif(rs.getBoolean(15)); // SI_1_KOOPERATIF
                rv.setS2Ditemukan(rs.getBoolean(16)); // SI_2_DITEMUKAN
                rv.setS2Nama(rs.getString(17)); // SI_2_NAMA
                rv.setS2Kelamin(rs.getString(18).equalsIgnoreCase("f") ? "W" : "L"); // SI_2_SEX
                rv.setS2Kenal(rs.getBoolean(19)); // SI_2_KENAL
                rv.setS2Hubungan(rs.getString(20)); // SI_2_HUBUNGAN
                rv.setS2Kooperatif(rs.getBoolean(21)); // SI_2_KOOPERATIF
                rv.setS3Ditemukan(rs.getBoolean(22)); // SI_3_DITEMUKAN
                rv.setS3Nama(rs.getString(23)); // SI_3_NAMA
                rv.setS3Kelamin(rs.getString(24).equalsIgnoreCase("f") ? "W" : "L"); // SI_3_SEX
                rv.setS3Kenal(rs.getBoolean(25)); // SI_3_KENAL
                rv.setS3Hubungan(rs.getString(26)); // SI_3_HUBUNGAN
                rv.setS3Kooperatif(rs.getBoolean(27)); // SI_3_KOOPERATIF
                rv.setSurveyorDiterimaDi(rs.getString(28)); // TEMPAT_DITERIMA
                rv.setKondisiRumah(rs.getString(29).equalsIgnoreCase("Terawat") ? true : false); // KONDISI_RUMAH
                rv.setPenghuni(rs.getString(30)); // PENGHUNI
                rv.setLingkunganSekitar(rs.getString(31)); // LINGKUNGAN
                rv.setJenisBangunan(rs.getString(32)); // JENIS_BANGUNAN
                rv.setLuasBangunan(rs.getString(33)); //luas bangunan
                rv.setLuasTanah(rs.getString(34)); // LUAS_tanah
                rv.setTelepon(rs.getString(35)); // TELEPON
                rv.setGarasi(rs.getBoolean(36)); // GARASI
                rv.setCarport(rs.getBoolean(37)); // CARPORT
                rv.setStatusKepemilikan(rs.getString(38)); // STATUS_MILIK
                rv.setLamaPenempatan(rs.getString(39)); // LAMA_PENEMPATAN
                rv.setNamaPanggilan(rs.getString(40)); // PANGGILAN_APLIKAN
                rv.setDikenalNamaSama(rs.getString(40).equals("")||rs.getString(40).equals(null)?true:false);
                rv.setDidatangiDebtCollector(rs.getString(41)); // DEBT_COLLECTOR
                if (rs.getBoolean(42)) { // FOTO_DEPAN_RUMAH
//                    rv.addFotoPada("Tampak depan rumah dan terlihat nomor");
                    rv.addFotoPada(parameterDao.findRVBVFotopada(AppConstans.GroupParameter.RV_FOTO_PADA, 1));
                }
                if (rs.getBoolean(43)) { // FOTO_DALAM_RUMAH
//                    rv.addFotoPada("Tampak dalam rumah");
                    rv.addFotoPada(parameterDao.findRVBVFotopada(AppConstans.GroupParameter.RV_FOTO_PADA, 2));
                }
                if (rs.getBoolean(44)) { // FOTO_PLANG_NAMA
//                    rv.addFotoPada("Komputer");
                    rv.addFotoPada(parameterDao.findRVBVFotopada(AppConstans.GroupParameter.RV_FOTO_PADA, 3));
                }
                if (rs.getBoolean(45)) { // FOTO_LINGKUNGAN
//                    rv.addFotoPada("Plang nama jalan (jika ada)");
                    rv.addFotoPada(parameterDao.findRVBVFotopada(AppConstans.GroupParameter.RV_FOTO_PADA, 4));
                }

                rv.setInfoS1(rs.getString(46)); // INFO_SI_1
                rv.setInfoS2(rs.getString(47)); // INFO_SI_2
                rv.setInfoS3(rs.getString(48)); // INFO_SI_3
                rv.setNote(rs.getString(49)); // NOTE

                logger.debug("starting ambil foto");
                JenisAppraisal appraisal = new JenisAppraisal();
                appraisal.setName("RV");
                List<JenisAppraisal> listAppraisal = jenisAppraisalDao.findExact(PagingInfo.create(), appraisal).getDataList();
                logger.debug("listAppraisal : " + listAppraisal.get(0).getName());
                List<DocumentLpaType> listDoc = documentLpaTypeDao.findByActiva(null, true, listAppraisal.get(0).getId());
                logger.debug("listDoc : " + listDoc.size());
                if (ListUtils.isNotEmpty(listDoc)) {
                    logger.debug("foto1 " + rs.getBytes(50));
                    if (rs.getBytes(50) != null && rs.getBytes(50).length > 0) {
                        SurveyImage foto1 = new SurveyImage();
                        foto1.setCommon(surveyCommon);
                        foto1.setContentType("image/jpeg");
                        foto1.setImage(rs.getBytes(50));
                        foto1.setDocumentLpaType(findByName(listDoc, "Tampak Patokan / Plang Jalan"));
                        foto1.setCatatan("foto1");
                        surveyImageDao.save(foto1);
                    }
                    logger.debug("foto2 " + rs.getBytes(51));
                    if (rs.getBytes(51) != null && rs.getBytes(51).length > 0) {
                        SurveyImage foto2 = new SurveyImage();
                        foto2.setCommon(surveyCommon);
                        foto2.setContentType("image/jpeg");
                        foto2.setImage(rs.getBytes(51));
                        foto2.setDocumentLpaType(findByName(listDoc, "Tampak Depan Lokasi"));
                        foto2.setCatatan("foto2");
                        surveyImageDao.save(foto2);
                    }
                    logger.debug("foto3 " + rs.getBytes(52));
                    if (rs.getBytes(52) != null && rs.getBytes(52).length > 0) {
                        SurveyImage foto3 = new SurveyImage();
                        foto3.setCommon(surveyCommon);
                        foto3.setContentType("image/jpeg");
                        foto3.setImage(rs.getBytes(52));
                        foto3.setDocumentLpaType(findByName(listDoc, "Tampak No. Lokasi"));
                        foto3.setCatatan("foto3");
                        surveyImageDao.save(foto3);
                    }
                    logger.debug("foto4 " + rs.getBytes(53));
                    if (rs.getBytes(53) != null && rs.getBytes(53).length > 0) {
                        SurveyImage foto4 = new SurveyImage();
                        foto4.setCommon(surveyCommon);
                        foto4.setContentType("image/jpeg");
                        foto4.setImage(rs.getBytes(53));
                        foto4.setDocumentLpaType(findByName(listDoc, "Tampak Sekitar Lokasi"));
                        foto4.setCatatan("foto4");
                        surveyImageDao.save(foto4);
                    }
                    logger.debug("foto5 " + rs.getBytes(54));
                    if (rs.getBytes(54) != null && rs.getBytes(54).length > 0) {
                        SurveyImage foto5 = new SurveyImage();
                        foto5.setCommon(surveyCommon);
                        foto5.setContentType("image/jpeg");
                        foto5.setImage(rs.getBytes(54));
                        foto5.setDocumentLpaType(findByName(listDoc, "Tampak Sekitar Lokasi"));
                        foto5.setCatatan("foto5");
                        surveyImageDao.save(foto5);
                    }
                    logger.debug("foto6 " + rs.getBytes(55));
                    if (rs.getBytes(55) != null && rs.getBytes(55).length > 0) {
                        SurveyImage foto6 = new SurveyImage();
                        foto6.setCommon(surveyCommon);
                        foto6.setContentType("image/jpeg");
                        foto6.setImage(rs.getBytes(55));
                        foto6.setDocumentLpaType(findByName(listDoc, "Tampak Dalam Lokasi"));
                        foto6.setCatatan("foto6");
                        surveyImageDao.save(foto6);
                    }
                    logger.debug("foto7 " + rs.getBytes(56));
                    if (rs.getBytes(56) != null && rs.getBytes(56).length > 0) {
                        SurveyImage foto7 = new SurveyImage();
                        foto7.setCommon(surveyCommon);
                        foto7.setContentType("image/jpeg");
                        foto7.setImage(rs.getBytes(56));
                        foto7.setDocumentLpaType(findByName(listDoc, "Tampak Dalam Lokasi"));
                        foto7.setCatatan("foto7");
                        surveyImageDao.save(foto7);
                    }
                    if (rs.getBytes(57) != null && rs.getBytes(57).length > 0) {
                        SurveyImage foto8 = new SurveyImage();
                        foto8.setCommon(surveyCommon);
                        foto8.setContentType("image/jpeg");
                        foto8.setImage(rs.getBytes(57));
                        foto8.setDocumentLpaType(findByName(listDoc, "Tampak Dalam Lokasi"));
                        foto8.setCatatan("foto8");
                        surveyImageDao.save(foto8);
                    }
                    if (rs.getBytes(58) != null && rs.getBytes(58).length > 0) {
                        SurveyImage foto9 = new SurveyImage();
                        foto9.setCommon(surveyCommon);
                        foto9.setContentType("image/jpeg");
                        foto9.setImage(rs.getBytes(58));
                        foto9.setDocumentLpaType(findByName(listDoc, "Lain - lain"));
                        foto9.setCatatan("foto9");
                        surveyImageDao.save(foto9);
                    }
                    if (rs.getBytes(59) != null && rs.getBytes(59).length > 0) {
                        SurveyImage foto10 = new SurveyImage();
                        foto10.setCommon(surveyCommon);
                        foto10.setContentType("image/jpeg");
                        foto10.setImage(rs.getBytes(59));
                        foto10.setDocumentLpaType(findByName(listDoc, "Lain - lain"));
                        foto10.setCatatan("foto10");
                        surveyImageDao.save(foto10);
                    }
                }

                // rs.getString(1); // id_survey
                dataCommonDao.save(dataCommon);
                rv.setDetail(detail);
                dao.save(rv);
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