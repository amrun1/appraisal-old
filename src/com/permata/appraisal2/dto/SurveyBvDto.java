package com.permata.appraisal2.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SurveyBvDto  implements Serializable {
    // additional
    private Long idJenisAppraisal;
    private Long commonId;
    private Long dataCommonId;

    private Long id;
    private Long detailId;

    // data umum
    private String alamat;
    private String vendor;
    private String ditinjau;
    private Boolean lokasiDitemukan;
    private Boolean sudahPindah;
    private String jam;
    private Boolean papanNama;
    private String kegiatanUsaha;
    private Date tanggalSurvey;

    // informasi 1 (S1)
    private Boolean s1Ditemukan;
    private String s1Nama;
    private String s1Kelamin;
    private String s1Hubungan;
    private Boolean s1Kooperatif;

    // informasi 2 (S2)
    private Boolean s2Ditemukan;
    private String s2Nama;
    private String s2Kelamin;
    private Boolean s2Kenal;
    private String s2Hubungan;
    private Boolean s2Kooperatif;

    // informasi 3 (S3)
    private Boolean s3Ditemukan;
    private String s3Nama;
    private String s3Kelamin;
    private Boolean s3Kenal;
    private String s3Hubungan;
    private Boolean s3Kooperatif;

    // tempat usaha
    private String surveyorDiterimaDi;
    private String jumlahKaryawan;
    private String omzetPenjualan;
    private BigDecimal detailOmzet;
    private Boolean kondisiUsaha;
    private String jenisUsaha;
    private String bidangUsaha;
    private String lamaPenempatan;
    private String lingkunganSekitar;
    private String jalanTempatUsaha;
    private Boolean masukMobil;
    private Boolean bertingkat;
    private String luasBangunan;
    private String luasTanah;
    private String jenisBangunan;
    private List<String> fasilitas;
    private Integer jumlahMotor;
    private Integer jumlahMobil;
    private Integer jumlahTruk;
    private String statusLokasi;
    private String statusUsaha;

    // informasi tambahan
    private List<String> fotoPada;
    private String infoS1;
    private String infoS2;
    private String infoS3;
    private String note;

    // foto-foto
    private LpaImageDto foto1;
    private LpaImageDto foto2;
    private LpaImageDto foto3;
    private LpaImageDto foto4;
    private LpaImageDto foto5;
    private LpaImageDto foto6;
    private LpaImageDto foto7;
    private LpaImageDto foto8;
    private LpaImageDto foto9;
    private LpaImageDto foto10;

    public Long getIdJenisAppraisal() {
        return idJenisAppraisal;
    }

    public void setIdJenisAppraisal(Long idJenisAppraisal) {
        this.idJenisAppraisal = idJenisAppraisal;
    }

    public Long getCommonId() {
        return commonId;
    }

    public void setCommonId(Long commonId) {
        this.commonId = commonId;
    }

    public Long getDataCommonId() {
        return dataCommonId;
    }

    public void setDataCommonId(Long dataCommonId) {
        this.dataCommonId = dataCommonId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getDitinjau() {
        return ditinjau;
    }

    public void setDitinjau(String ditinjau) {
        this.ditinjau = ditinjau;
    }

    public Boolean getLokasiDitemukan() {
        return lokasiDitemukan;
    }

    public void setLokasiDitemukan(Boolean lokasiDitemukan) {
        this.lokasiDitemukan = lokasiDitemukan;
    }

    public Boolean getSudahPindah() {
        return sudahPindah;
    }

    public void setSudahPindah(Boolean sudahPindah) {
        this.sudahPindah = sudahPindah;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public Boolean getPapanNama() {
        return papanNama;
    }

    public void setPapanNama(Boolean papanNama) {
        this.papanNama = papanNama;
    }

    public String getKegiatanUsaha() {
        return kegiatanUsaha;
    }

    public void setKegiatanUsaha(String kegiatanUsaha) {
        this.kegiatanUsaha = kegiatanUsaha;
    }

    public Boolean getS1Ditemukan() {
        return s1Ditemukan;
    }

    public void setS1Ditemukan(Boolean s1Ditemukan) {
        this.s1Ditemukan = s1Ditemukan;
    }

    public String getS1Nama() {
        return s1Nama;
    }

    public void setS1Nama(String s1Nama) {
        this.s1Nama = s1Nama;
    }

    public String getS1Kelamin() {
        return s1Kelamin;
    }

    public void setS1Kelamin(String s1Kelamin) {
        this.s1Kelamin = s1Kelamin;
    }

    public String getS1Hubungan() {
        return s1Hubungan;
    }

    public void setS1Hubungan(String s1Hubungan) {
        this.s1Hubungan = s1Hubungan;
    }

    public Boolean getS1Kooperatif() {
        return s1Kooperatif;
    }

    public void setS1Kooperatif(Boolean s1Kooperatif) {
        this.s1Kooperatif = s1Kooperatif;
    }

    public Boolean getS2Ditemukan() {
        return s2Ditemukan;
    }

    public void setS2Ditemukan(Boolean s2Ditemukan) {
        this.s2Ditemukan = s2Ditemukan;
    }

    public String getS2Nama() {
        return s2Nama;
    }

    public void setS2Nama(String s2Nama) {
        this.s2Nama = s2Nama;
    }

    public String getS2Kelamin() {
        return s2Kelamin;
    }

    public void setS2Kelamin(String s2Kelamin) {
        this.s2Kelamin = s2Kelamin;
    }

    public Boolean getS2Kenal() {
        return s2Kenal;
    }

    public void setS2Kenal(Boolean s2Kenal) {
        this.s2Kenal = s2Kenal;
    }

    public String getS2Hubungan() {
        return s2Hubungan;
    }

    public void setS2Hubungan(String s2Hubungan) {
        this.s2Hubungan = s2Hubungan;
    }

    public Boolean getS2Kooperatif() {
        return s2Kooperatif;
    }

    public void setS2Kooperatif(Boolean s2Kooperatif) {
        this.s2Kooperatif = s2Kooperatif;
    }

    public Boolean getS3Ditemukan() {
        return s3Ditemukan;
    }

    public void setS3Ditemukan(Boolean s3Ditemukan) {
        this.s3Ditemukan = s3Ditemukan;
    }

    public String getS3Nama() {
        return s3Nama;
    }

    public void setS3Nama(String s3Nama) {
        this.s3Nama = s3Nama;
    }

    public String getS3Kelamin() {
        return s3Kelamin;
    }

    public void setS3Kelamin(String s3Kelamin) {
        this.s3Kelamin = s3Kelamin;
    }

    public Boolean getS3Kenal() {
        return s3Kenal;
    }

    public void setS3Kenal(Boolean s3Kenal) {
        this.s3Kenal = s3Kenal;
    }

    public String getS3Hubungan() {
        return s3Hubungan;
    }

    public void setS3Hubungan(String s3Hubungan) {
        this.s3Hubungan = s3Hubungan;
    }

    public Boolean getS3Kooperatif() {
        return s3Kooperatif;
    }

    public void setS3Kooperatif(Boolean s3Kooperatif) {
        this.s3Kooperatif = s3Kooperatif;
    }

    public String getSurveyorDiterimaDi() {
        return surveyorDiterimaDi;
    }

    public void setSurveyorDiterimaDi(String surveyorDiterimaDi) {
        this.surveyorDiterimaDi = surveyorDiterimaDi;
    }

    public String getJumlahKaryawan() {
        return jumlahKaryawan;
    }

    public void setJumlahKaryawan(String jumlahKaryawan) {
        this.jumlahKaryawan = jumlahKaryawan;
    }

    public String getOmzetPenjualan() {
        return omzetPenjualan;
    }

    public void setOmzetPenjualan(String omzetPenjualan) {
        this.omzetPenjualan = omzetPenjualan;
    }

    public BigDecimal getDetailOmzet() {
        return detailOmzet;
    }

    public void setDetailOmzet(BigDecimal detailOmzet) {
        this.detailOmzet = detailOmzet;
    }

    public Boolean getKondisiUsaha() {
        return kondisiUsaha;
    }

    public void setKondisiUsaha(Boolean kondisiUsaha) {
        this.kondisiUsaha = kondisiUsaha;
    }

    public String getJenisUsaha() {
        return jenisUsaha;
    }

    public void setJenisUsaha(String jenisUsaha) {
        this.jenisUsaha = jenisUsaha;
    }

    public String getBidangUsaha() {
        return bidangUsaha;
    }

    public void setBidangUsaha(String bidangUsaha) {
        this.bidangUsaha = bidangUsaha;
    }

    public String getLamaPenempatan() {
        return lamaPenempatan;
    }

    public void setLamaPenempatan(String lamaPenempatan) {
        this.lamaPenempatan = lamaPenempatan;
    }

    public String getLingkunganSekitar() {
        return lingkunganSekitar;
    }

    public void setLingkunganSekitar(String lingkunganSekitar) {
        this.lingkunganSekitar = lingkunganSekitar;
    }

    public String getJalanTempatUsaha() {
        return jalanTempatUsaha;
    }

    public void setJalanTempatUsaha(String jalanTempatUsaha) {
        this.jalanTempatUsaha = jalanTempatUsaha;
    }

    public Boolean getMasukMobil() {
        return masukMobil;
    }

    public void setMasukMobil(Boolean masukMobil) {
        this.masukMobil = masukMobil;
    }

    public Boolean getBertingkat() {
        return bertingkat;
    }

    public void setBertingkat(Boolean bertingkat) {
        this.bertingkat = bertingkat;
    }

    public String getLuasBangunan() {
        return luasBangunan;
    }

    public void setLuasBangunan(String luasBangunan) {
        this.luasBangunan = luasBangunan;
    }

    public String getLuasTanah() {
        return luasTanah;
    }

    public void setLuasTanah(String luasTanah) {
        this.luasTanah = luasTanah;
    }

    public String getJenisBangunan() {
        return jenisBangunan;
    }

    public void setJenisBangunan(String jenisBangunan) {
        this.jenisBangunan = jenisBangunan;
    }

    public List<String> getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(List<String> fasilitas) {
        this.fasilitas = fasilitas;
    }

    public Integer getJumlahMotor() {
        return jumlahMotor;
    }

    public void setJumlahMotor(Integer jumlahMotor) {
        this.jumlahMotor = jumlahMotor;
    }

    public Integer getJumlahMobil() {
        return jumlahMobil;
    }

    public void setJumlahMobil(Integer jumlahMobil) {
        this.jumlahMobil = jumlahMobil;
    }

    public Integer getJumlahTruk() {
        return jumlahTruk;
    }

    public void setJumlahTruk(Integer jumlahTruk) {
        this.jumlahTruk = jumlahTruk;
    }

    public String getStatusLokasi() {
        return statusLokasi;
    }

    public void setStatusLokasi(String statusLokasi) {
        this.statusLokasi = statusLokasi;
    }

    public String getStatusUsaha() {
        return statusUsaha;
    }

    public void setStatusUsaha(String statusUsaha) {
        this.statusUsaha = statusUsaha;
    }

    public List<String> getFotoPada() {
        return fotoPada;
    }

    public void setFotoPada(List<String> fotoPada) {
        this.fotoPada = fotoPada;
    }

    public String getInfoS1() {
        return infoS1;
    }

    public void setInfoS1(String infoS1) {
        this.infoS1 = infoS1;
    }

    public String getInfoS2() {
        return infoS2;
    }

    public void setInfoS2(String infoS2) {
        this.infoS2 = infoS2;
    }

    public String getInfoS3() {
        return infoS3;
    }

    public void setInfoS3(String infoS3) {
        this.infoS3 = infoS3;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LpaImageDto getFoto1() {
        return foto1;
    }

    public void setFoto1(LpaImageDto foto1) {
        this.foto1 = foto1;
    }

    public LpaImageDto getFoto2() {
        return foto2;
    }

    public void setFoto2(LpaImageDto foto2) {
        this.foto2 = foto2;
    }

    public LpaImageDto getFoto3() {
        return foto3;
    }

    public void setFoto3(LpaImageDto foto3) {
        this.foto3 = foto3;
    }

    public LpaImageDto getFoto4() {
        return foto4;
    }

    public void setFoto4(LpaImageDto foto4) {
        this.foto4 = foto4;
    }

    public LpaImageDto getFoto5() {
        return foto5;
    }

    public void setFoto5(LpaImageDto foto5) {
        this.foto5 = foto5;
    }

    public LpaImageDto getFoto6() {
        return foto6;
    }

    public void setFoto6(LpaImageDto foto6) {
        this.foto6 = foto6;
    }

    public LpaImageDto getFoto7() {
        return foto7;
    }

    public void setFoto7(LpaImageDto foto7) {
        this.foto7 = foto7;
    }

    public LpaImageDto getFoto8() {
        return foto8;
    }

    public void setFoto8(LpaImageDto foto8) {
        this.foto8 = foto8;
    }

    public LpaImageDto getFoto9() {
        return foto9;
    }

    public void setFoto9(LpaImageDto foto9) {
        this.foto9 = foto9;
    }

    public LpaImageDto getFoto10() {
        return foto10;
    }

    public void setFoto10(LpaImageDto foto10) {
        this.foto10 = foto10;
    }

    public Date getTanggalSurvey() {
        return tanggalSurvey;
    }

    public void setTanggalSurvey(Date tanggalSurvey) {
        this.tanggalSurvey = tanggalSurvey;
    }
}
