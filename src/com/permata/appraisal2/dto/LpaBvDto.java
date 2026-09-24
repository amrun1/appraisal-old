package com.permata.appraisal2.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class LpaBvDto extends LpaUmumDto  implements Serializable {

    private Long id;

    // data umum
    @NotNull private Boolean lokasiDitemukan;
    @NotNull private Boolean papanNama;
    @NotNull private String kegiatanUsaha;
    @NotNull private Boolean sudahPindah;

    // informasi 1 (S1)
    @NotNull private Boolean s1Ditemukan;
    @NotNull private String s1Nama;
    @NotNull private String s1Kelamin;
    @NotNull private String s1Hubungan;
    @NotNull private Boolean s1Kooperatif;

    // informasi 2 (S2)
    @NotNull private Boolean s2Ditemukan;
    @NotNull private String s2Nama;
    @NotNull private String s2Kelamin;
    @NotNull private Boolean s2Kenal;
    @NotNull private String s2Hubungan;
    @NotNull private Boolean s2Kooperatif;

    // informasi 3 (S3)
    @NotNull private Boolean s3Ditemukan;
    @NotNull private String s3Nama;
    @NotNull private String s3Kelamin;
    @NotNull private Boolean s3Kenal;
    @NotNull private String s3Hubungan;
    @NotNull private Boolean s3Kooperatif;

    // tempat usaha
    @NotNull private String surveyorDiterimaDi;
    @NotNull private String jumlahKaryawan;
    @NotNull private String omzetPenjualan;
    @NotNull private BigDecimal detailOmzet;
    @NotNull private Boolean kondisiUsaha;
    @NotNull private String jenisUsaha;
    @NotNull private String bidangUsaha;
    @NotNull private String lamaPenempatan;
    @NotNull private String lingkunganSekitar;
    @NotNull private String jalanTempatUsaha;
    @NotNull private Boolean masukMobil;
    @NotNull private Boolean bertingkat;
    @NotNull private String luasBangunan;
    @NotNull private String luasTanah;
    @NotNull private String jenisBangunan;
    private List<String> fasilitas;
    @NotNull private Integer jumlahMotor;
    @NotNull private Integer jumlahMobil;
    @NotNull private Integer jumlahTruk;
    @NotNull private String statusLokasi;
    @NotNull private String statusUsaha;

    // informasi tambahan
    @NotNull private List<String> fotoPada;
    @NotNull private String infoS1;
    @NotNull private String infoS2;
    @NotNull private String infoS3;
    @NotNull private String note;

    // lampiran foto
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

    //tambahan 213-6-17
    private String vendor;
    private String lokasi_objek_jam;
    private String jam;
    private String menit;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getLokasiDitemukan() {
        return lokasiDitemukan;
    }

    public void setLokasiDitemukan(Boolean lokasiDitemukan) {
        this.lokasiDitemukan = lokasiDitemukan;
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

    public Boolean getSudahPindah() {
        return sudahPindah;
    }

    public void setSudahPindah(Boolean sudahPindah) {
        this.sudahPindah = sudahPindah;
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

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getLokasi_objek_jam() {
        return lokasi_objek_jam;
    }

    public void setLokasi_objek_jam(String lokasi_objek_jam) {
        this.lokasi_objek_jam = lokasi_objek_jam;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getMenit() {
        return menit;
    }

    public void setMenit(String menit) {
        this.menit = menit;
    }
}