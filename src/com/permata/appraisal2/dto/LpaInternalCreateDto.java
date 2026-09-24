package com.permata.appraisal2.dto;

import com.permata.appraisal2.common.AppConstans;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LpaInternalCreateDto extends LpaUmumDto implements Validator, Serializable {

    @Override
    public boolean supports(Class<?> aClass) {
        return LpaInternalCreateDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
//  ValidationUtils.rejectIfEmpty(errors, "faktorMarketability", "name.empty");
        LpaInternalCreateDto w = (LpaInternalCreateDto) o;


        if (w.getFaktorMarketability()!=null && w.getFaktorMarketability().equalsIgnoreCase("Tidak Marketable")) {
            if (StringUtils.isBlank(w.getFaktorMarketCatatan())) {
                ValidationUtils.rejectIfEmpty(errors, "faktorMarketCatatan", "name.empty", "Tidak Marketable, Catatan harus ada");
            }
        }

        if(w.isEksternal()){
            ValidationUtils.rejectIfEmpty(errors, "opini",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Opini Internal Atas Eksternal"));
            ValidationUtils.rejectIfEmpty(errors, "deviasiDoc",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Nilai Deviasi Dokumen"));
            ValidationUtils.rejectIfEmpty(errors, "deviasiFisik",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Nilai Deviasi Fisik"));
            ValidationUtils.rejectIfEmpty(errors, "tanggalPenilaian",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Tanggal Penilaian"));
            ValidationUtils.rejectIfEmpty(errors, "tanggalOrderKeEksternal",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Tanggal Order Ke Eksternal"));
            ValidationUtils.rejectIfEmpty(errors, "tanggalTerimaDariEksternal",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Tanggal Terima Dari Eksternal"));
            ValidationUtils.rejectIfEmpty(errors, "idKjpp",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "KJPP"));
        }



        if (w.getReadDocBangunan().equalsIgnoreCase("Jelas")){
            ValidationUtils.rejectIfEmpty(errors, "dokumenBangunan.nomorImb",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Nomor IMB "));


            ValidationUtils.rejectIfEmpty(errors, "dokumenBangunan.tanggalImb",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Tanggal IMB "));


            ValidationUtils.rejectIfEmpty(errors, "dokumenBangunan.ijinSesuaiImb",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Ijin Sesuai IMB "));


            ValidationUtils.rejectIfEmpty(errors, "dokumenBangunan.luasBangunan",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Luas Bangunan "));


            ValidationUtils.rejectIfEmpty(errors, "dokumenBangunan.jumlahLantai",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Jumlah Lantai "));

        }



        if (w.getReadDocTanah().equalsIgnoreCase("Jelas")){

            ValidationUtils.rejectIfEmpty(errors, "dokumenTanah.jenisDokumenId",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Jenis Dokumen "));

            ValidationUtils.rejectIfEmpty(errors, "dokumenTanah.namaDokumen",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Nomor Dokumen "));


            ValidationUtils.rejectIfEmpty(errors, "dokumenTanah.tanggalSertipikat",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Tanggal Sertipikat "));


            ValidationUtils.rejectIfEmpty(errors, "dokumenTanah.namaJalan",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Nama Jalan"));

            ValidationUtils.rejectIfEmpty(errors, "dokumenTanah.blockGangLantai",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Block/Lantai "));


            ValidationUtils.rejectIfEmpty(errors, "dokumenTanah.rt",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "RT "));


            ValidationUtils.rejectIfEmpty(errors, "dokumenTanah.kelurahan",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Kelurahan "));


            ValidationUtils.rejectIfEmpty(errors, "dokumenTanah.kota",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Kota "));


            ValidationUtils.rejectIfEmpty(errors, "dokumenTanah.namaPemegangHak",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Nama pemegang hak "));


            ValidationUtils.rejectIfEmpty(errors, "dokumenTanah.suratUkur",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Surat Ukur "));


            ValidationUtils.rejectIfEmpty(errors, "dokumenTanah.nomor",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Nomor "));


            ValidationUtils.rejectIfEmpty(errors, "dokumenTanah.rw",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Rw "));



            ValidationUtils.rejectIfEmpty(errors, "dokumenTanah.kecamatan",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Kecamatan "));


            ValidationUtils.rejectIfEmpty(errors, "dokumenTanah.propinsi",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Propinsi "));


//            ValidationUtils.rejectIfEmpty(errors, "dokumenTanah.tanggalBerakhirHak",
//                    AppConstans.ErrorMessage.CODE_REQUIRED,
//                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Tanggal Berakhir Hak "));



            ValidationUtils.rejectIfEmpty(errors, "dokumenTanah.tanggalSuratUkur",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Tanggal Surat Ukur "));


            ValidationUtils.rejectIfEmpty(errors, "dokumenTanah.luasTanah",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Luas tanah "));





        }

    }

    public void validate(Errors errors) {
        validate(this, errors);
    }

    private Long id;
    private String appraisalTypeCategory;
    @NotNull private String posisiLokasiAgunan;
    /* // pindahkan ke LPA UMUM DTO
    private Long surveyId; // eksternal tidak memiliki survey
    @NotNull private Long detailId;
    @NotNull private Long commonId;
    @NotNull private Long branchId;
    @NotNull private String jenisAppraisal;
    @NotNull private String orderNumber;
    private String lpaNumber;
    @NotNull private String contactPerson;
    @NotNull private String namaDebitur;
    @NotNull private String jenisObjectPenilain;
    @NotNull private String namaPerumahan;
    @NotNull private String namaCluster;
    @NotNull private String block;
    @NotNull private String nomor;
    @NotNull private String rt;
    @NotNull private String rw;
    @NotNull private String kodePos;
    @NotNull private String propinsi;
    @NotNull private String kota;
    @NotNull private String kecamatan;
    @NotNull private String kelurahan;
    @NotNull private Long statusJaminan;
    @NotNull private Date tanggalOrder;
    @NotNull private Long kategoriSla;
    @NotNull private String ditujuke;
    @NotNull private Long jenisAktiva;
    @NotNull private String jenisObjectPenilaiFisik;
    @NotNull private String lokasiFisik;
    @NotNull private Date tanggalSurvey;
    @NotNull private String ditinjau;
    @NotNull private String ditemui;
    @NotNull private String catatanSurvey;
    @NotNull private Boolean draft; */

    // data tanah
    @NotNull @Valid
    private LpaDokumenTanahDto dokumenTanah = new LpaDokumenTanahDto();
    @NotNull
    private List<LpaDokumenTanahDto> listDokumenTanah = new ArrayList<LpaDokumenTanahDto>();

    // data fisik tanah
    @NotNull
    private String bentukTanah;
    @NotNull
    private String konturTanah;
    @NotNull
    private String ketinggianTanah;
    @NotNull
    private String peruntukanTanah;

    @NotNull
    private String peruntukanBangunan;



    @NotNull
    private String batasTanah;
    @NotNull
    private String batasUtaraTanah;
    @NotNull
    private String batasSelatanTanah;
    @NotNull
    private String batasBaratTanah;
    @NotNull
    private String batasTimurTanah;
    @NotNull
    private String posisiTanah;
    @NotNull
    private String kondisiTanah;
    @NotNull
    private String jenisTanah;
    @NotNull
    private Double totalLuasTanahDokumen;

    private Double luasTanahSesuaiTataKota;

    // data Bangunan@NotNull
    @NotNull
    @Valid
    private LpaDokumenBangunanDto dokumenBangunan = new LpaDokumenBangunanDto();
    @NotNull
    private List<LpaDokumenBangunanDto> listDokumenBangunan = new ArrayList<LpaDokumenBangunanDto>();

    // data bangunan fisik
    @NotNull
    private Double luasBangunanImb;
    @NotNull
    private Double luasLantai1;
    private Double luasLantai2;
    private Double luasLantai3;
    private Double luasLantai4;
    private Double luasLantai5;
    @NotNull
    private Double luasBangunan;
    @NotNull
    private Integer jumlahLantaiFisik;
    @NotNull
    private Integer jumlahLantaiTampakDepan;
    @NotNull
    private String jenisBangunan;
    @NotNull
    private String digunakanSebagai;
    @NotNull
    private String dihuniOleh;

    private String statusAgunanDisewakan;
    @NotNull
    private Integer tahunDibangun;
    @NotNull
    private Integer tahunDirenov;
    @NotNull
    private String kelasBangunan;
    @NotNull
    private String kondisiUmum;
    @NotNull
    private String prosesPembangunan;
    @NotNull
    private String statusBangunan;


    private Double prosesKonstruksi;

    private Date tanggalProsesKonstruksi;
    @NotNull
    private boolean hitungManual;

    // data lingkungan
    @NotNull
    private String lingkunganSekitar;
    @NotNull
    private String tingkatKepadatan;
    @NotNull
    private String tingkatKemacetan;
    @NotNull
    private String rencanaJalan;
    @NotNull
    private Double lebarJalan;
    @NotNull
    private Double gsb;
    @NotNull
    private Double gss;
    @NotNull
    private String kendaraanRoda4;

    // data agunan Internal

    private BigDecimal hargaTanah;

    private BigDecimal hargaBangunan;

    @NotNull
    private Boolean hitungManual1;
    @NotNull
    private String uraian1;
    @NotNull
    private BigDecimal nilaiDokumen1;
    @NotNull
    private Boolean diberiNilaiDokumen1;
    @NotNull
    private BigDecimal nilaiFisik1;
    @NotNull
    private Boolean diberiNilaiFisik1;
    @NotNull
    private Double prosentase1;
    private Boolean hitungManual2;
    @NotNull
    private String uraian2;
    @NotNull
    private BigDecimal nilaiDokumen2;
    @NotNull
    private Boolean diberiNilaiDokumen2;
    @NotNull
    private BigDecimal nilaiFisik2;
    @NotNull
    private Boolean diberiNilaiFisik2;
    @NotNull
    private Double prosentase2;
    @NotNull
    private BigDecimal totalSesuaiDokumen;
    @NotNull
    private BigDecimal totalSesuaiFisik;
    @NotNull private BigDecimal likuidasiDokumen1;
    @NotNull private BigDecimal likuidasiFisik1;
    @NotNull private BigDecimal likuidasiDokumen2;
    @NotNull private BigDecimal likuidasiFisik2;
    @NotNull private BigDecimal totalLikuidasiDokumen;
    @NotNull private BigDecimal totalLikuidasiFisik;
    @NotNull private BigDecimal toleransi1;
    @NotNull private BigDecimal toleransi2;

    // data agunan Eksternal
    private BigDecimal hargaTanahEks;
    private BigDecimal hargaBangunanEks;
    private Boolean hitungManualEks;
    private String uraian1Eks;
    private BigDecimal nilaiDokumen1Eks;
    private Boolean diberiNilaiDokumen1Eks;
    private BigDecimal nilaiFisik1Eks;
    private Boolean diberiNilaiFisik1Eks;
    private Double prosentase1Eks;
    private String uraian2Eks;
    private BigDecimal nilaiDokumen2Eks;
    private Boolean diberiNilaiDokumen2Eks;
    private BigDecimal nilaiFisik2Eks;
    private Boolean diberiNilaiFisik2Eks;
    private Double prosentase2Eks;
    private BigDecimal totalSesuaiDokumenEks;
    private BigDecimal totalSesuaiFisikEks;
    private BigDecimal likuidasiDokumen1Eks;
    private BigDecimal likuidasiFisik1Eks;
    private BigDecimal likuidasiDokumen2Eks;
    private BigDecimal likuidasiFisik2Eks;
    private BigDecimal totalLikuidasiDokumenEks;
    private BigDecimal totalLikuidasiFisikEks;
    private BigDecimal toleransi1Eks;
    private BigDecimal toleransi2Eks;


    //untuk status_doc
    private String readDocTanah;
    private String readDocBangunan;

    private String typeReport;



    // data Faktor Market
//    @NotNull private String faktorMarketability;
//    private String faktorMarketCatatan;

    // data Negative List
//    @NotNull private List<Long> listNegative = new ArrayList<Long>();
//    @NotNull private List<Long> listPertimbangan = new ArrayList<Long>();
//    @NotNull private String positifPoint;
//    @NotNull private String catatanPoint;

    // data Pertimbangan
//    @NotNull private String pembanding1;
//    @NotNull private String pembanding2;
//    @NotNull private String pembanding3;

    public String getAppraisalTypeCategory() {
        return appraisalTypeCategory;
    }

    public void setAppraisalTypeCategory(String appraisalTypeCategory) {
        this.appraisalTypeCategory = appraisalTypeCategory;
    }

    private List<LpaImageDto> listImage = new ArrayList<LpaImageDto>();

    public List<LpaImageDto> getListImage() {
        return listImage;
    }

    public void addListImage(LpaImageDto image) {
        image.setLpaId(getId());
        listImage.add(image);
    }

    public void setListImage(List<LpaImageDto> listImage) {
        this.listImage = listImage;
    }

    public Boolean getHitungManual1() {
        return hitungManual1;
    }

    public void setHitungManual1(Boolean hitungManual1) {
        this.hitungManual1 = hitungManual1;
    }

    public Boolean getHitungManual2() {
        return hitungManual2;
    }

    public void setHitungManual2(Boolean hitungManual2) {
        this.hitungManual2 = hitungManual2;
    }

    public Double getTotalLuasTanahDokumen() {
        return totalLuasTanahDokumen;
    }

    public void setTotalLuasTanahDokumen(Double totalLuasTanahDokumen) {
        this.totalLuasTanahDokumen = totalLuasTanahDokumen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long idLpa) {
        this.id = idLpa;
    }

    public BigDecimal getHargaTanah() {
        return hargaTanah;
    }

    public void setHargaTanah(BigDecimal hargaTanah) {
        this.hargaTanah = hargaTanah;
    }

    public BigDecimal getHargaBangunan() {
        return hargaBangunan;
    }

    public void setHargaBangunan(BigDecimal hargaBangunan) {
        this.hargaBangunan = hargaBangunan;
    }

    public String getUraian1() {
        return uraian1;
    }

    public void setUraian1(String uraian1) {
        this.uraian1 = uraian1;
    }

    public BigDecimal getNilaiDokumen1() {
        return nilaiDokumen1;
    }

    public void setNilaiDokumen1(BigDecimal nilaiDokumen1) {
        this.nilaiDokumen1 = nilaiDokumen1;
    }

    public Boolean getDiberiNilaiDokumen1() {
        return diberiNilaiDokumen1;
    }

    public void setDiberiNilaiDokumen1(Boolean diberiNilaiDokumen1) {
        this.diberiNilaiDokumen1 = diberiNilaiDokumen1;
    }

    public BigDecimal getNilaiFisik1() {
        return nilaiFisik1;
    }

    public void setNilaiFisik1(BigDecimal nilaiFisik1) {
        this.nilaiFisik1 = nilaiFisik1;
    }

    public Boolean getDiberiNilaiFisik1() {
        return diberiNilaiFisik1;
    }

    public void setDiberiNilaiFisik1(Boolean diberiNilaiFisik1) {
        this.diberiNilaiFisik1 = diberiNilaiFisik1;
    }

    public Double getProsentase1() {
        return prosentase1;
    }

    public void setProsentase1(Double prosentase1) {
        this.prosentase1 = prosentase1;
    }

    public String getUraian2() {
        return uraian2;
    }

    public void setUraian2(String uraian2) {
        this.uraian2 = uraian2;
    }

    public BigDecimal getNilaiDokumen2() {
        return nilaiDokumen2;
    }

    public void setNilaiDokumen2(BigDecimal nilaiDokumen2) {
        this.nilaiDokumen2 = nilaiDokumen2;
    }

    public Boolean getDiberiNilaiDokumen2() {
        return diberiNilaiDokumen2;
    }

    public void setDiberiNilaiDokumen2(Boolean diberiNilaiDokumen2) {
        this.diberiNilaiDokumen2 = diberiNilaiDokumen2;
    }

    public BigDecimal getNilaiFisik2() {
        return nilaiFisik2;
    }

    public void setNilaiFisik2(BigDecimal nilaiFisik2) {
        this.nilaiFisik2 = nilaiFisik2;
    }

    public Boolean getDiberiNilaiFisik2() {
        return diberiNilaiFisik2;
    }

    public void setDiberiNilaiFisik2(Boolean diberiNilaiFisik2) {
        this.diberiNilaiFisik2 = diberiNilaiFisik2;
    }

    public Double getProsentase2() {
        return prosentase2;
    }

    public void setProsentase2(Double prosentase2) {
        this.prosentase2 = prosentase2;
    }

    public String getPosisiLokasiAgunan() {
        return posisiLokasiAgunan;
    }

    public void setPosisiLokasiAgunan(String posisiLokasiAgunan) {
        this.posisiLokasiAgunan = posisiLokasiAgunan;
    }

    public String getLingkunganSekitar() {
        return lingkunganSekitar;
    }

    public void setLingkunganSekitar(String lingkunganSekitar) {
        this.lingkunganSekitar = lingkunganSekitar;
    }

    public String getTingkatKepadatan() {
        return tingkatKepadatan;
    }

    public void setTingkatKepadatan(String tingkatKepadatan) {
        this.tingkatKepadatan = tingkatKepadatan;
    }

    public String getTingkatKemacetan() {
        return tingkatKemacetan;
    }

    public void setTingkatKemacetan(String tingkatKemacetan) {
        this.tingkatKemacetan = tingkatKemacetan;
    }

    public String getRencanaJalan() {
        return rencanaJalan;
    }

    public void setRencanaJalan(String rencanaJalan) {
        this.rencanaJalan = rencanaJalan;
    }

    public Double getLebarJalan() {
        return lebarJalan;
    }

    public void setLebarJalan(Double lebarJalan) {
        this.lebarJalan = lebarJalan;
    }

    public Double getGsb() {
        return gsb;
    }

    public void setGsb(Double gsb) {
        this.gsb = gsb;
    }

    public Double getGss() {
        return gss;
    }

    public void setGss(Double gss) {
        this.gss = gss;
    }

    public String getKendaraanRoda4() {
        return kendaraanRoda4;
    }

    public void setKendaraanRoda4(String kendaraanRoda4) {
        this.kendaraanRoda4 = kendaraanRoda4;
    }

    public boolean isHitungManual() {
        return hitungManual;
    }

    public void setHitungManual(boolean hitungManual) {
        this.hitungManual = hitungManual;
    }

    public Double getLuasLantai1() {
        return luasLantai1;
    }

    public void setLuasLantai1(Double luasLantai1) {
        this.luasLantai1 = luasLantai1;
    }

    public Double getLuasLantai2() {
        return luasLantai2;
    }

    public void setLuasLantai2(Double luasLantai2) {
        this.luasLantai2 = luasLantai2;
    }

    public Double getLuasLantai3() {
        return luasLantai3;
    }

    public void setLuasLantai3(Double luasLantai3) {
        this.luasLantai3 = luasLantai3;
    }

    public Double getLuasLantai4() {
        return luasLantai4;
    }

    public void setLuasLantai4(Double luasLantai4) {
        this.luasLantai4 = luasLantai4;
    }

    public Double getLuasLantai5() {
        return luasLantai5;
    }

    public void setLuasLantai5(Double luasLantai5) {
        this.luasLantai5 = luasLantai5;
    }

    public Double getLuasBangunan() {
        return luasBangunan;
    }

    public void setLuasBangunan(Double luasBangunan) {
        this.luasBangunan = luasBangunan;
    }

    public Integer getJumlahLantaiFisik() {
        return jumlahLantaiFisik;
    }

    public void setJumlahLantaiFisik(Integer jumlahLantaiFisik) {
        this.jumlahLantaiFisik = jumlahLantaiFisik;
    }

    public Integer getJumlahLantaiTampakDepan() {
        return jumlahLantaiTampakDepan;
    }

    public void setJumlahLantaiTampakDepan(Integer jumlahLantaiTampakDepan) {
        this.jumlahLantaiTampakDepan = jumlahLantaiTampakDepan;
    }

    public String getJenisBangunan() {
        return jenisBangunan;
    }

    public void setJenisBangunan(String jenisBangunan) {
        this.jenisBangunan = jenisBangunan;
    }

    public String getDigunakanSebagai() {
        return digunakanSebagai;
    }

    public void setDigunakanSebagai(String digunakanSebagai) {
        this.digunakanSebagai = digunakanSebagai;
    }

    public String getDihuniOleh() {
        return dihuniOleh;
    }

    public void setDihuniOleh(String dihuniOleh) {
        this.dihuniOleh = dihuniOleh;
    }

    public String getStatusAgunanDisewakan() {
        return statusAgunanDisewakan;
    }

    public void setStatusAgunanDisewakan(String statusAgunanDisewakan) {
        this.statusAgunanDisewakan = statusAgunanDisewakan;
    }

    public Integer getTahunDibangun() {
        return tahunDibangun;
    }

    public void setTahunDibangun(Integer tahunDibangun) {
        this.tahunDibangun = tahunDibangun;
    }

    public Integer getTahunDirenov() {
        return tahunDirenov;
    }

    public void setTahunDirenov(Integer tahunDirenov) {
        this.tahunDirenov = tahunDirenov;
    }

    public String getKelasBangunan() {
        return kelasBangunan;
    }

    public void setKelasBangunan(String kelasBangunan) {
        this.kelasBangunan = kelasBangunan;
    }

    public String getKondisiUmum() {
        return kondisiUmum;
    }

    public void setKondisiUmum(String kondisiUmum) {
        this.kondisiUmum = kondisiUmum;
    }

    public String getProsesPembangunan() {
        return prosesPembangunan;
    }

    public void setProsesPembangunan(String prosesPembangunan) {
        this.prosesPembangunan = prosesPembangunan;
    }

    public String getStatusBangunan() {
        return statusBangunan;
    }

    public void setStatusBangunan(String statusBangunan) {
        this.statusBangunan = statusBangunan;
    }

    public Double getProsesKonstruksi() {
        return prosesKonstruksi;
    }

    public void setProsesKonstruksi(Double prosesKonstruksi) {
        this.prosesKonstruksi = prosesKonstruksi;
    }

    public Date getTanggalProsesKonstruksi() {
        return tanggalProsesKonstruksi;
    }

    public void setTanggalProsesKonstruksi(Date tanggalProsesKonstruksi) {
        this.tanggalProsesKonstruksi = tanggalProsesKonstruksi;
    }

    public LpaDokumenBangunanDto getDokumenBangunan() {
        return dokumenBangunan;
    }

    public void setDokumenBangunan(LpaDokumenBangunanDto dokumenBangunan) {
        this.dokumenBangunan = dokumenBangunan;
    }

    public List<LpaDokumenBangunanDto> getListDokumenBangunan() {
        return listDokumenBangunan;
    }

    public void addDokumenBangunan(LpaDokumenBangunanDto bangunan) {
        listDokumenBangunan.add(bangunan);
    }

    public void setListDokumenBangunan(List<LpaDokumenBangunanDto> listDokumenBangunan) {
        this.listDokumenBangunan = listDokumenBangunan;
    }

    public String getBentukTanah() {
        return bentukTanah;
    }

    public void setBentukTanah(String bentukTanah) {
        this.bentukTanah = bentukTanah;
    }

    public String getKonturTanah() {
        return konturTanah;
    }

    public void setKonturTanah(String konturTanah) {
        this.konturTanah = konturTanah;
    }

    public String getKetinggianTanah() {
        return ketinggianTanah;
    }

    public void setKetinggianTanah(String ketinggianTanah) {
        this.ketinggianTanah = ketinggianTanah;
    }

    public String getPeruntukanTanah() {
        return peruntukanTanah;
    }

    public void setPeruntukanTanah(String peruntukanTanah) {
        this.peruntukanTanah = peruntukanTanah;
    }

    public String getBatasTanah() {
        return batasTanah;
    }

    public void setBatasTanah(String batasTanah) {
        this.batasTanah = batasTanah;
    }

    public String getBatasUtaraTanah() {
        return batasUtaraTanah;
    }

    public void setBatasUtaraTanah(String batasUtaraTanah) {
        this.batasUtaraTanah = batasUtaraTanah;
    }

    public String getBatasSelatanTanah() {
        return batasSelatanTanah;
    }

    public void setBatasSelatanTanah(String batasSelatanTanah) {
        this.batasSelatanTanah = batasSelatanTanah;
    }

    public String getBatasBaratTanah() {
        return batasBaratTanah;
    }

    public void setBatasBaratTanah(String batasBaratTanah) {
        this.batasBaratTanah = batasBaratTanah;
    }

    public String getBatasTimurTanah() {
        return batasTimurTanah;
    }

    public void setBatasTimurTanah(String batasTimurTanah) {
        this.batasTimurTanah = batasTimurTanah;
    }

    public String getPosisiTanah() {
        return posisiTanah;
    }

    public void setPosisiTanah(String posisiTanah) {
        this.posisiTanah = posisiTanah;
    }

    public String getKondisiTanah() {
        return kondisiTanah;
    }

    public void setKondisiTanah(String kondisiTanah) {
        this.kondisiTanah = kondisiTanah;
    }

    public String getJenisTanah() {
        return jenisTanah;
    }

    public void setJenisTanah(String jenisTanah) {
        this.jenisTanah = jenisTanah;
    }

    public LpaDokumenTanahDto getDokumenTanah() {
        return dokumenTanah;
    }

    public void setDokumenTanah(LpaDokumenTanahDto dokumenTanah) {
        this.dokumenTanah = dokumenTanah;
    }

    public List<LpaDokumenTanahDto> getListDokumenTanah() {
        return listDokumenTanah;
    }

    public void addDokumenTanah(LpaDokumenTanahDto dto) {
        this.listDokumenTanah.add(dto);
    }

    public void setListDokumenTanah(List<LpaDokumenTanahDto> listDokumenTanah) {
        this.listDokumenTanah = listDokumenTanah;
    }

    public Double getLuasBangunanImb() {
        return luasBangunanImb;
    }

    public void setLuasBangunanImb(Double luasBangunanImb) {
        this.luasBangunanImb = luasBangunanImb;
    }

    public BigDecimal getTotalSesuaiDokumen() {
        return totalSesuaiDokumen;
    }

    public void setTotalSesuaiDokumen(BigDecimal totalSesuaiDokumen) {
        this.totalSesuaiDokumen = totalSesuaiDokumen;
    }

    public BigDecimal getTotalSesuaiFisik() {
        return totalSesuaiFisik;
    }

    public void setTotalSesuaiFisik(BigDecimal totalSesuaiFisik) {
        this.totalSesuaiFisik = totalSesuaiFisik;
    }

    public BigDecimal getLikuidasiDokumen1() {
        return likuidasiDokumen1;
    }

    public void setLikuidasiDokumen1(BigDecimal likuidasiDokumen1) {
        this.likuidasiDokumen1 = likuidasiDokumen1;
    }

    public BigDecimal getLikuidasiFisik1() {
        return likuidasiFisik1;
    }

    public void setLikuidasiFisik1(BigDecimal likuidasiFisik1) {
        this.likuidasiFisik1 = likuidasiFisik1;
    }

    public BigDecimal getLikuidasiDokumen2() {
        return likuidasiDokumen2;
    }

    public void setLikuidasiDokumen2(BigDecimal likuidasiDokumen2) {
        this.likuidasiDokumen2 = likuidasiDokumen2;
    }

    public BigDecimal getLikuidasiFisik2() {
        return likuidasiFisik2;
    }

    public void setLikuidasiFisik2(BigDecimal likuidasiFisik2) {
        this.likuidasiFisik2 = likuidasiFisik2;
    }

    public BigDecimal getTotalLikuidasiDokumen() {
        return totalLikuidasiDokumen;
    }

    public void setTotalLikuidasiDokumen(BigDecimal totalLikuidasiDokumen) {
        this.totalLikuidasiDokumen = totalLikuidasiDokumen;
    }

    public BigDecimal getTotalLikuidasiFisik() {
        return totalLikuidasiFisik;
    }

    public void setTotalLikuidasiFisik(BigDecimal totalLikuidasiFisik) {
        this.totalLikuidasiFisik = totalLikuidasiFisik;
    }

    public BigDecimal getToleransi1() {
        return toleransi1;
    }

    public void setToleransi1(BigDecimal toleransi1) {
        this.toleransi1 = toleransi1;
    }

    public BigDecimal getToleransi2() {
        return toleransi2;
    }

    public void setToleransi2(BigDecimal toleransi2) {
        this.toleransi2 = toleransi2;
    }

    public BigDecimal getHargaTanahEks() {
        return hargaTanahEks;
    }

    public void setHargaTanahEks(BigDecimal hargaTanahEks) {
        this.hargaTanahEks = hargaTanahEks;
    }

    public BigDecimal getHargaBangunanEks() {
        return hargaBangunanEks;
    }

    public void setHargaBangunanEks(BigDecimal hargaBangunanEks) {
        this.hargaBangunanEks = hargaBangunanEks;
    }

    public Boolean getHitungManualEks() {
        return hitungManualEks;
    }

    public void setHitungManualEks(Boolean hitungManualEks) {
        this.hitungManualEks = hitungManualEks;
    }

    public String getUraian1Eks() {
        return uraian1Eks;
    }

    public void setUraian1Eks(String uraian1Eks) {
        this.uraian1Eks = uraian1Eks;
    }

    public BigDecimal getNilaiDokumen1Eks() {
        return nilaiDokumen1Eks;
    }

    public void setNilaiDokumen1Eks(BigDecimal nilaiDokumen1Eks) {
        this.nilaiDokumen1Eks = nilaiDokumen1Eks;
    }

    public Boolean getDiberiNilaiDokumen1Eks() {
        return diberiNilaiDokumen1Eks;
    }

    public void setDiberiNilaiDokumen1Eks(Boolean diberiNilaiDokumen1Eks) {
        this.diberiNilaiDokumen1Eks = diberiNilaiDokumen1Eks;
    }

    public BigDecimal getNilaiFisik1Eks() {
        return nilaiFisik1Eks;
    }

    public void setNilaiFisik1Eks(BigDecimal nilaiFisik1Eks) {
        this.nilaiFisik1Eks = nilaiFisik1Eks;
    }

    public Boolean getDiberiNilaiFisik1Eks() {
        return diberiNilaiFisik1Eks;
    }

    public void setDiberiNilaiFisik1Eks(Boolean diberiNilaiFisik1Eks) {
        this.diberiNilaiFisik1Eks = diberiNilaiFisik1Eks;
    }

    public Double getProsentase1Eks() {
        return prosentase1Eks;
    }

    public void setProsentase1Eks(Double prosentase1Eks) {
        this.prosentase1Eks = prosentase1Eks;
    }

    public String getUraian2Eks() {
        return uraian2Eks;
    }

    public void setUraian2Eks(String uraian2Eks) {
        this.uraian2Eks = uraian2Eks;
    }

    public BigDecimal getNilaiDokumen2Eks() {
        return nilaiDokumen2Eks;
    }

    public void setNilaiDokumen2Eks(BigDecimal nilaiDokumen2Eks) {
        this.nilaiDokumen2Eks = nilaiDokumen2Eks;
    }

    public Boolean getDiberiNilaiDokumen2Eks() {
        return diberiNilaiDokumen2Eks;
    }

    public void setDiberiNilaiDokumen2Eks(Boolean diberiNilaiDokumen2Eks) {
        this.diberiNilaiDokumen2Eks = diberiNilaiDokumen2Eks;
    }

    public BigDecimal getNilaiFisik2Eks() {
        return nilaiFisik2Eks;
    }

    public void setNilaiFisik2Eks(BigDecimal nilaiFisik2Eks) {
        this.nilaiFisik2Eks = nilaiFisik2Eks;
    }

    public Boolean getDiberiNilaiFisik2Eks() {
        return diberiNilaiFisik2Eks;
    }

    public void setDiberiNilaiFisik2Eks(Boolean diberiNilaiFisik2Eks) {
        this.diberiNilaiFisik2Eks = diberiNilaiFisik2Eks;
    }

    public Double getProsentase2Eks() {
        return prosentase2Eks;
    }

    public void setProsentase2Eks(Double prosentase2Eks) {
        this.prosentase2Eks = prosentase2Eks;
    }

    public BigDecimal getTotalSesuaiDokumenEks() {
        return totalSesuaiDokumenEks;
    }

    public void setTotalSesuaiDokumenEks(BigDecimal totalSesuaiDokumenEks) {
        this.totalSesuaiDokumenEks = totalSesuaiDokumenEks;
    }

    public BigDecimal getTotalSesuaiFisikEks() {
        return totalSesuaiFisikEks;
    }

    public void setTotalSesuaiFisikEks(BigDecimal totalSesuaiFisikEks) {
        this.totalSesuaiFisikEks = totalSesuaiFisikEks;
    }

    public BigDecimal getLikuidasiDokumen1Eks() {
        return likuidasiDokumen1Eks;
    }

    public void setLikuidasiDokumen1Eks(BigDecimal likuidasiDokumen1Eks) {
        this.likuidasiDokumen1Eks = likuidasiDokumen1Eks;
    }

    public BigDecimal getLikuidasiFisik1Eks() {
        return likuidasiFisik1Eks;
    }

    public void setLikuidasiFisik1Eks(BigDecimal likuidasiFisik1Eks) {
        this.likuidasiFisik1Eks = likuidasiFisik1Eks;
    }

    public BigDecimal getLikuidasiDokumen2Eks() {
        return likuidasiDokumen2Eks;
    }

    public void setLikuidasiDokumen2Eks(BigDecimal likuidasiDokumen2Eks) {
        this.likuidasiDokumen2Eks = likuidasiDokumen2Eks;
    }

    public BigDecimal getLikuidasiFisik2Eks() {
        return likuidasiFisik2Eks;
    }

    public void setLikuidasiFisik2Eks(BigDecimal likuidasiFisik2Eks) {
        this.likuidasiFisik2Eks = likuidasiFisik2Eks;
    }

    public BigDecimal getTotalLikuidasiDokumenEks() {
        return totalLikuidasiDokumenEks;
    }

    public void setTotalLikuidasiDokumenEks(BigDecimal totalLikuidasiDokumenEks) {
        this.totalLikuidasiDokumenEks = totalLikuidasiDokumenEks;
    }

    public BigDecimal getTotalLikuidasiFisikEks() {
        return totalLikuidasiFisikEks;
    }

    public void setTotalLikuidasiFisikEks(BigDecimal totalLikuidasiFisikEks) {
        this.totalLikuidasiFisikEks = totalLikuidasiFisikEks;
    }

    public BigDecimal getToleransi1Eks() {
        return toleransi1Eks;
    }

    public void setToleransi1Eks(BigDecimal toleransi1Eks) {
        this.toleransi1Eks = toleransi1Eks;
    }

    public BigDecimal getToleransi2Eks() {
        return toleransi2Eks;
    }

    public void setToleransi2Eks(BigDecimal toleransi2Eks) {
        this.toleransi2Eks = toleransi2Eks;
    }

    public Double getLuasTanahSesuaiTataKota() {
        return luasTanahSesuaiTataKota;
    }

    public void setLuasTanahSesuaiTataKota(Double luasTanahSesuaiTataKota) {
        this.luasTanahSesuaiTataKota = luasTanahSesuaiTataKota;
    }


    public String getReadDocTanah() {
        return readDocTanah;
    }

    public void setReadDocTanah(String readDocTanah) {
        this.readDocTanah = readDocTanah;
    }

    public String getReadDocBangunan() {
        return readDocBangunan;
    }

    public void setReadDocBangunan(String readDocBangunan) {
        this.readDocBangunan = readDocBangunan;
    }


    public String getPeruntukanBangunan() {
        return peruntukanBangunan;
    }

    public void setPeruntukanBangunan(String peruntukanBangunan) {
        this.peruntukanBangunan = peruntukanBangunan;
    }

    public String getTypeReport() {
        return typeReport;
    }

    public void setTypeReport(String typeReport) {
        this.typeReport = typeReport;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LpaInternalCreateDto{");
        sb.append("id=").append(id);
        sb.append(", appraisalTypeCategory='").append(appraisalTypeCategory).append('\'');
        sb.append(", posisiLokasiAgunan='").append(posisiLokasiAgunan).append('\'');
        sb.append(", dokumenTanah=").append(dokumenTanah);
        sb.append(", listDokumenTanah=").append(listDokumenTanah);
        sb.append(", bentukTanah='").append(bentukTanah).append('\'');
        sb.append(", konturTanah='").append(konturTanah).append('\'');
        sb.append(", ketinggianTanah='").append(ketinggianTanah).append('\'');
        sb.append(", peruntukanTanah='").append(peruntukanTanah).append('\'');
        sb.append(", batasTanah='").append(batasTanah).append('\'');
        sb.append(", batasUtaraTanah='").append(batasUtaraTanah).append('\'');
        sb.append(", batasSelatanTanah='").append(batasSelatanTanah).append('\'');
        sb.append(", batasBaratTanah='").append(batasBaratTanah).append('\'');
        sb.append(", batasTimurTanah='").append(batasTimurTanah).append('\'');
        sb.append(", posisiTanah='").append(posisiTanah).append('\'');
        sb.append(", kondisiTanah='").append(kondisiTanah).append('\'');
        sb.append(", jenisTanah='").append(jenisTanah).append('\'');
        sb.append(", totalLuasTanahDokumen=").append(totalLuasTanahDokumen);
        sb.append(", luasTanahSesuaiTataKota=").append(luasTanahSesuaiTataKota);
        sb.append(", dokumenBangunan=").append(dokumenBangunan);
        sb.append(", listDokumenBangunan=").append(listDokumenBangunan);
        sb.append(", luasBangunanImb=").append(luasBangunanImb);
        sb.append(", luasLantai1=").append(luasLantai1);
        sb.append(", luasLantai2=").append(luasLantai2);
        sb.append(", luasLantai3=").append(luasLantai3);
        sb.append(", luasLantai4=").append(luasLantai4);
        sb.append(", luasLantai5=").append(luasLantai5);
        sb.append(", luasBangunan=").append(luasBangunan);
        sb.append(", jumlahLantaiFisik=").append(jumlahLantaiFisik);
        sb.append(", jumlahLantaiTampakDepan=").append(jumlahLantaiTampakDepan);
        sb.append(", jenisBangunan='").append(jenisBangunan).append('\'');
        sb.append(", digunakanSebagai='").append(digunakanSebagai).append('\'');
        sb.append(", dihuniOleh='").append(dihuniOleh).append('\'');
        sb.append(", statusAgunanDisewakan='").append(statusAgunanDisewakan).append('\'');
        sb.append(", tahunDibangun=").append(tahunDibangun);
        sb.append(", tahunDirenov=").append(tahunDirenov);
        sb.append(", kelasBangunan='").append(kelasBangunan).append('\'');
        sb.append(", kondisiUmum='").append(kondisiUmum).append('\'');
        sb.append(", prosesPembangunan='").append(prosesPembangunan).append('\'');
        sb.append(", statusBangunan='").append(statusBangunan).append('\'');
        sb.append(", prosesKonstruksi=").append(prosesKonstruksi);
        sb.append(", tanggalProsesKonstruksi=").append(tanggalProsesKonstruksi);
        sb.append(", hitungManual=").append(hitungManual);
        sb.append(", lingkunganSekitar='").append(lingkunganSekitar).append('\'');
        sb.append(", tingkatKepadatan='").append(tingkatKepadatan).append('\'');
        sb.append(", tingkatKemacetan='").append(tingkatKemacetan).append('\'');
        sb.append(", rencanaJalan='").append(rencanaJalan).append('\'');
        sb.append(", lebarJalan=").append(lebarJalan);
        sb.append(", gsb=").append(gsb);
        sb.append(", gss=").append(gss);
        sb.append(", kendaraanRoda4='").append(kendaraanRoda4).append('\'');
        sb.append(", hargaTanah=").append(hargaTanah);
        sb.append(", hargaBangunan=").append(hargaBangunan);
        sb.append(", hitungManual1=").append(hitungManual1);
        sb.append(", uraian1='").append(uraian1).append('\'');
        sb.append(", nilaiDokumen1=").append(nilaiDokumen1);
        sb.append(", diberiNilaiDokumen1=").append(diberiNilaiDokumen1);
        sb.append(", nilaiFisik1=").append(nilaiFisik1);
        sb.append(", diberiNilaiFisik1=").append(diberiNilaiFisik1);
        sb.append(", prosentase1=").append(prosentase1);
        sb.append(", hitungManual2=").append(hitungManual2);
        sb.append(", uraian2='").append(uraian2).append('\'');
        sb.append(", nilaiDokumen2=").append(nilaiDokumen2);
        sb.append(", diberiNilaiDokumen2=").append(diberiNilaiDokumen2);
        sb.append(", nilaiFisik2=").append(nilaiFisik2);
        sb.append(", diberiNilaiFisik2=").append(diberiNilaiFisik2);
        sb.append(", prosentase2=").append(prosentase2);
        sb.append(", totalSesuaiDokumen=").append(totalSesuaiDokumen);
        sb.append(", totalSesuaiFisik=").append(totalSesuaiFisik);
        sb.append(", likuidasiDokumen1=").append(likuidasiDokumen1);
        sb.append(", likuidasiFisik1=").append(likuidasiFisik1);
        sb.append(", likuidasiDokumen2=").append(likuidasiDokumen2);
        sb.append(", likuidasiFisik2=").append(likuidasiFisik2);
        sb.append(", totalLikuidasiDokumen=").append(totalLikuidasiDokumen);
        sb.append(", totalLikuidasiFisik=").append(totalLikuidasiFisik);
        sb.append(", toleransi1=").append(toleransi1);
        sb.append(", toleransi2=").append(toleransi2);
        sb.append(", hargaTanahEks=").append(hargaTanahEks);
        sb.append(", hargaBangunanEks=").append(hargaBangunanEks);
        sb.append(", hitungManualEks=").append(hitungManualEks);
        sb.append(", uraian1Eks='").append(uraian1Eks).append('\'');
        sb.append(", nilaiDokumen1Eks=").append(nilaiDokumen1Eks);
        sb.append(", diberiNilaiDokumen1Eks=").append(diberiNilaiDokumen1Eks);
        sb.append(", nilaiFisik1Eks=").append(nilaiFisik1Eks);
        sb.append(", diberiNilaiFisik1Eks=").append(diberiNilaiFisik1Eks);
        sb.append(", prosentase1Eks=").append(prosentase1Eks);
        sb.append(", uraian2Eks='").append(uraian2Eks).append('\'');
        sb.append(", nilaiDokumen2Eks=").append(nilaiDokumen2Eks);
        sb.append(", diberiNilaiDokumen2Eks=").append(diberiNilaiDokumen2Eks);
        sb.append(", nilaiFisik2Eks=").append(nilaiFisik2Eks);
        sb.append(", diberiNilaiFisik2Eks=").append(diberiNilaiFisik2Eks);
        sb.append(", prosentase2Eks=").append(prosentase2Eks);
        sb.append(", totalSesuaiDokumenEks=").append(totalSesuaiDokumenEks);
        sb.append(", totalSesuaiFisikEks=").append(totalSesuaiFisikEks);
        sb.append(", likuidasiDokumen1Eks=").append(likuidasiDokumen1Eks);
        sb.append(", likuidasiFisik1Eks=").append(likuidasiFisik1Eks);
        sb.append(", likuidasiDokumen2Eks=").append(likuidasiDokumen2Eks);
        sb.append(", likuidasiFisik2Eks=").append(likuidasiFisik2Eks);
        sb.append(", totalLikuidasiDokumenEks=").append(totalLikuidasiDokumenEks);
        sb.append(", totalLikuidasiFisikEks=").append(totalLikuidasiFisikEks);
        sb.append(", toleransi1Eks=").append(toleransi1Eks);
        sb.append(", toleransi2Eks=").append(toleransi2Eks);
        sb.append(", readDocTanah='").append(readDocTanah).append('\'');
        sb.append(", readDocBangunan='").append(readDocBangunan).append('\'');
        sb.append(", typeReport='").append(typeReport).append('\'');
        sb.append(", listImage=").append(listImage);
        sb.append('}');
        return sb.toString();
    }
}