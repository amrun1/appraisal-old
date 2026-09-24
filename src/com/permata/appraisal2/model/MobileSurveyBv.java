package com.permata.appraisal2.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.Date;
//
//@Entity
//@Table(name = AppConstans.HibernateConstanstan.PREFIX_DB_NAME + "MOB_SURVEY_BV")
@Deprecated
/**
 * aplikasi mobile survey tidak jadi langsung tembak data survey ke postgree,
 * tapi harus via MySQL terdahulu
 */
public class MobileSurveyBv extends EntityBase<String> {

    private String ditinjau; // surveyor
    private String orderNumber;
    private String alamat;
    private String vendor;
    private String sudahPindah;
    private Date tanggal;
    private Boolean papanNama;
    private String kegiatanUsaha;
    private String lokasiDitemukan;
    private String jam;

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

    private String surveyorDiterimaDi;
    private String jumlahKaryawan;
    private String omzetPenjualan;
    private String detailOmzet;
    private String kondisiUsaha;
    private String jenisUsaha;
    private String bidangUsaha;
    private String lamaPenempatan;
    private String lingkunganSekitar;
    private String jalanTempatUsaha;
    private Boolean masukMobil;
    private String bertingkat;
    private String luasBangunan;
    private String luasTanah;
    private String jenisBangunan;
    private Boolean telepon;
    private Boolean komputer;
    private Boolean fotocopy;
    private Boolean internet;
    private Boolean kendaraanOps;
    private Integer jumlahMotor;
    private Integer jumlahMobil;
    private Integer jumlahTruk;
    private String statusLokasi;
    private String statusUsaha;
    private Boolean fotoDepan;
    private Boolean fotoDepanPlang;
    private Boolean fotoPlangNama;
    private Boolean fotoStokDalam;
    private Boolean fotoStokLuar;
    private Boolean fotoAktifitas;
    private Boolean fotoLingkungan;
    private Boolean fotoMesin;
    private String infoS1;
    private String infoS2;
    private String infoS3;
    private String note;
    private String statusSurvey;

    private byte[] foto1;
    private byte[] foto2;
    private byte[] foto3;
    private byte[] foto4;
    private byte[] foto5;
    private byte[] foto6;
    private byte[] foto7;
    private byte[] foto8;
    private byte[] foto9;
    private byte[] foto10;

    @Id
    @Column(name = "ID_SURVEY")
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Column(name = "ID_SURVEYOR")
    public String getDitinjau() {
        return ditinjau;
    }

    public void setDitinjau(String ditinjau) {
        this.ditinjau = ditinjau;
    }

    @Column(name = "ALAMAT_SURVEY")
    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Column(name = "VENDOR")
    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    @Column(name = "TGL_SUBMIT_SURVEY")
    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    @Column(name = "PAPAN_NAMA")
    public Boolean getPapanNama() {
        return papanNama;
    }

    public void setPapanNama(Boolean papanNama) {
        this.papanNama = papanNama;
    }

    @Column(name = "KEGIATAN_SAAT_SURVEY")
    public String getKegiatanUsaha() {
        return kegiatanUsaha;
    }

    public void setKegiatanUsaha(String kegiatanUsaha) {
        this.kegiatanUsaha = kegiatanUsaha;
    }

    @Column(name = "LOKASI_OBJEK")
    public String getLokasiDitemukan() {
        return lokasiDitemukan;
    }

    public void setLokasiDitemukan(String lokasiDitemukan) {
        this.lokasiDitemukan = lokasiDitemukan;
    }

    @Column(name = "LOKASI_OBJEK_JAM")
    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    @Column(name = "LOKASI_OBJEK_NOTE")
    public String getSudahPindah() {
        return sudahPindah;
    }

    public void setSudahPindah(String sudahPindah) {
        this.sudahPindah = sudahPindah;
    }

    @Column(name = "SI_1_DITEMUKAN")
    public Boolean getS1Ditemukan() {
        return s1Ditemukan;
    }

    public void setS1Ditemukan(Boolean s1Ditemukan) {
        this.s1Ditemukan = s1Ditemukan;
    }

    @Column(name = "SI_1_NAMA")
    public String getS1Nama() {
        return s1Nama;
    }

    public void setS1Nama(String s1Nama) {
        this.s1Nama = s1Nama;
    }

    @Column(name = "SI_1_SEX")
    public String getS1Kelamin() {
        return s1Kelamin;
    }

    public void setS1Kelamin(String s1Kelamin) {
        this.s1Kelamin = s1Kelamin;
    }

    @Column(name = "SI_1_HUBUNGAN")
    public String getS1Hubungan() {
        return s1Hubungan;
    }

    public void setS1Hubungan(String s1Hubungan) {
        this.s1Hubungan = s1Hubungan;
    }

    @Column(name = "SI_1_KOOPERATIF")
    public Boolean getS1Kooperatif() {
        return s1Kooperatif;
    }

    public void setS1Kooperatif(Boolean s1Kooperatif) {
        this.s1Kooperatif = s1Kooperatif;
    }

    @Column(name = "SI_2_DITEMUKAN")
    public Boolean getS2Ditemukan() {
        return s2Ditemukan;
    }

    public void setS2Ditemukan(Boolean s2Ditemukan) {
        this.s2Ditemukan = s2Ditemukan;
    }

    @Column(name = "SI_2_NAMA")
    public String getS2Nama() {
        return s2Nama;
    }

    public void setS2Nama(String s2Nama) {
        this.s2Nama = s2Nama;
    }

    @Column(name = "SI_2_SEX")
    public String getS2Kelamin() {
        return s2Kelamin;
    }

    public void setS2Kelamin(String s2Kelamin) {
        this.s2Kelamin = s2Kelamin;
    }

    @Column(name = "SI_2_KENAL")
    public Boolean getS2Kenal() {
        return s2Kenal;
    }

    public void setS2Kenal(Boolean s2Kenal) {
        this.s2Kenal = s2Kenal;
    }

    @Column(name = "SI_2_HUBUNGAN")
    public String getS2Hubungan() {
        return s2Hubungan;
    }

    public void setS2Hubungan(String s2Hubungan) {
        this.s2Hubungan = s2Hubungan;
    }

    @Column(name = "SI_2_KOOPERATIF")
    public Boolean getS2Kooperatif() {
        return s2Kooperatif;
    }

    public void setS2Kooperatif(Boolean s2Kooperatif) {
        this.s2Kooperatif = s2Kooperatif;
    }

    @Column(name = "SI_3_DITEMUKAN")
    public Boolean getS3Ditemukan() {
        return s3Ditemukan;
    }

    public void setS3Ditemukan(Boolean s3Ditemukan) {
        this.s3Ditemukan = s3Ditemukan;
    }

    @Column(name = "SI_3_NAMA")
    public String getS3Nama() {
        return s3Nama;
    }

    public void setS3Nama(String s3Nama) {
        this.s3Nama = s3Nama;
    }

    @Column(name = "SI_3_SEX")
    public String getS3Kelamin() {
        return s3Kelamin;
    }

    public void setS3Kelamin(String s3Kelamin) {
        this.s3Kelamin = s3Kelamin;
    }

    @Column(name = "SI_3_KENAL")
    public Boolean getS3Kenal() {
        return s3Kenal;
    }

    public void setS3Kenal(Boolean s3Kenal) {
        this.s3Kenal = s3Kenal;
    }

    @Column(name = "SI_3_HUBUNGAN")
    public String getS3Hubungan() {
        return s3Hubungan;
    }

    public void setS3Hubungan(String s3Hubungan) {
        this.s3Hubungan = s3Hubungan;
    }

    @Column(name = "SI_3_KOOPERATIF")
    public Boolean getS3Kooperatif() {
        return s3Kooperatif;
    }

    public void setS3Kooperatif(Boolean s3Kooperatif) {
        this.s3Kooperatif = s3Kooperatif;
    }

    @Column(name = "TEMPAT_DITERIMA")
    public String getSurveyorDiterimaDi() {
        return surveyorDiterimaDi;
    }

    public void setSurveyorDiterimaDi(String surveyorDiterimaDi) {
        this.surveyorDiterimaDi = surveyorDiterimaDi;
    }

    @Column(name = "JUMLAH_KARYAWAN")
    public String getJumlahKaryawan() {
        return jumlahKaryawan;
    }

    public void setJumlahKaryawan(String jumlahKaryawan) {
        this.jumlahKaryawan = jumlahKaryawan;
    }

    @Column(name = "OMZET")
    public String getOmzetPenjualan() {
        return omzetPenjualan;
    }

    public void setOmzetPenjualan(String omzetPenjualan) {
        this.omzetPenjualan = omzetPenjualan;
    }

    @Column(name = "OMZET_DETAIL")
    public String getDetailOmzet() {
        return detailOmzet;
    }

    public void setDetailOmzet(String detailOmzet) {
        this.detailOmzet = detailOmzet;
    }

    @Column(name = "KONDISI_TMPT_USAHA")
    public String getKondisiUsaha() {
        return kondisiUsaha;
    }

    public void setKondisiUsaha(String kondisiUsaha) {
        this.kondisiUsaha = kondisiUsaha;
    }

    @Column(name = "JENIS_USAHA")
    public String getJenisUsaha() {
        return jenisUsaha;
    }

    public void setJenisUsaha(String jenisUsaha) {
        this.jenisUsaha = jenisUsaha;
    }

    @Column(name = "BIDANG_USAHA")
    public String getBidangUsaha() {
        return bidangUsaha;
    }

    public void setBidangUsaha(String bidangUsaha) {
        this.bidangUsaha = bidangUsaha;
    }

    @Column(name = "LAMA_PENEMPATAN")
    public String getLamaPenempatan() {
        return lamaPenempatan;
    }

    public void setLamaPenempatan(String lamaPenempatan) {
        this.lamaPenempatan = lamaPenempatan;
    }

    @Column(name = "LINGKUNGAN")
    public String getLingkunganSekitar() {
        return lingkunganSekitar;
    }

    public void setLingkunganSekitar(String lingkunganSekitar) {
        this.lingkunganSekitar = lingkunganSekitar;
    }

    @Column(name = "KONDISI_JALAN")
    public String getJalanTempatUsaha() {
        return jalanTempatUsaha;
    }

    public void setJalanTempatUsaha(String jalanTempatUsaha) {
        this.jalanTempatUsaha = jalanTempatUsaha;
    }

    @Column(name = "MASUK_MOBIL")
    public Boolean getMasukMobil() {
        return masukMobil;
    }

    public void setMasukMobil(Boolean masukMobil) {
        this.masukMobil = masukMobil;
    }

    @Column(name = "KONDISI_BANGUNAN")
    public String getBertingkat() {
        return bertingkat;
    }

    public void setBertingkat(String bertingkat) {
        this.bertingkat = bertingkat;
    }

    @Column(name = "LUAS_BANGUNAN")
    public String getLuasBangunan() {
        return luasBangunan;
    }

    public void setLuasBangunan(String luasBangunan) {
        this.luasBangunan = luasBangunan;
    }

    @Column(name = "LUAS_TANAH")
    public String getLuasTanah() {
        return luasTanah;
    }

    public void setLuasTanah(String luasTanah) {
        this.luasTanah = luasTanah;
    }

    @Column(name = "JENIS_BANGUNAN")
    public String getJenisBangunan() {
        return jenisBangunan;
    }

    public void setJenisBangunan(String jenisBangunan) {
        this.jenisBangunan = jenisBangunan;
    }

    @Column(name = "TELEPON")
    public Boolean getTelepon() {
        return telepon;
    }

    public void setTelepon(Boolean telepon) {
        this.telepon = telepon;
    }

    @Column(name = "KOMPUTER")
    public Boolean getKomputer() {
        return komputer;
    }

    public void setKomputer(Boolean komputer) {
        this.komputer = komputer;
    }

    @Column(name = "PHOTOCOPY")
    public Boolean getFotocopy() {
        return fotocopy;
    }

    public void setFotocopy(Boolean fotocopy) {
        this.fotocopy = fotocopy;
    }

    @Column(name = "INTERNET")
    public Boolean getInternet() {
        return internet;
    }

    public void setInternet(Boolean internet) {
        this.internet = internet;
    }

    @Column(name = "KENDARAAN_OPS")
    public Boolean getKendaraanOps() {
        return kendaraanOps;
    }

    public void setKendaraanOps(Boolean kendaraanOps) {
        this.kendaraanOps = kendaraanOps;
    }

    @Column(name = "JUMLAH_MOTOR")
    public Integer getJumlahMotor() {
        return jumlahMotor;
    }

    public void setJumlahMotor(Integer jumlahMotor) {
        this.jumlahMotor = jumlahMotor;
    }

    @Column(name = "JUMLAH_MOBIL")
    public Integer getJumlahMobil() {
        return jumlahMobil;
    }

    public void setJumlahMobil(Integer jumlahMobil) {
        this.jumlahMobil = jumlahMobil;
    }

    @Column(name = "JUMLAH_TRUK")
    public Integer getJumlahTruk() {
        return jumlahTruk;
    }

    public void setJumlahTruk(Integer jumlahTruk) {
        this.jumlahTruk = jumlahTruk;
    }

    @Column(name = "STATUS_MILIK_TEMPAT")
    public String getStatusLokasi() {
        return statusLokasi;
    }

    public void setStatusLokasi(String statusLokasi) {
        this.statusLokasi = statusLokasi;
    }

    @Column(name = "STATUS_MILIK_USAHA")
    public String getStatusUsaha() {
        return statusUsaha;
    }

    public void setStatusUsaha(String statusUsaha) {
        this.statusUsaha = statusUsaha;
    }

    @Column(name = "FOTO_DEPAN")
    public Boolean getFotoDepan() {
        return fotoDepan;
    }

    public void setFotoDepan(Boolean fotoDepan) {
        this.fotoDepan = fotoDepan;
    }

    @Column(name = "FOTO_DEPAN_PLANG")
    public Boolean getFotoDepanPlang() {
        return fotoDepanPlang;
    }

    public void setFotoDepanPlang(Boolean fotoDepanPlang) {
        this.fotoDepanPlang = fotoDepanPlang;
    }

    @Column(name = "FOTO_PLANG_NAMA")
    public Boolean getFotoPlangNama() {
        return fotoPlangNama;
    }

    public void setFotoPlangNama(Boolean fotoPlangNama) {
        this.fotoPlangNama = fotoPlangNama;
    }

    @Column(name = "FOTO_STOK_DALAM")
    public Boolean getFotoStokDalam() {
        return fotoStokDalam;
    }

    public void setFotoStokDalam(Boolean fotoStokDalam) {
        this.fotoStokDalam = fotoStokDalam;
    }

    @Column(name = "FOTO_STOK_LUAR")
    public Boolean getFotoStokLuar() {
        return fotoStokLuar;
    }

    public void setFotoStokLuar(Boolean fotoStokLuar) {
        this.fotoStokLuar = fotoStokLuar;
    }

    @Column(name = "FOTO_AKTIFITAS")
    public Boolean getFotoAktifitas() {
        return fotoAktifitas;
    }

    public void setFotoAktifitas(Boolean fotoAktifitas) {
        this.fotoAktifitas = fotoAktifitas;
    }

    @Column(name = "FOTO_LINGKUNGAN")
    public Boolean getFotoLingkungan() {
        return fotoLingkungan;
    }

    public void setFotoLingkungan(Boolean fotoLingkungan) {
        this.fotoLingkungan = fotoLingkungan;
    }

    @Column(name = "FOTO_MESIN")
    public Boolean getFotoMesin() {
        return fotoMesin;
    }

    public void setFotoMesin(Boolean fotoMesin) {
        this.fotoMesin = fotoMesin;
    }

    @Column(name = "INFO_SI_1")
    public String getInfoS1() {
        return infoS1;
    }

    public void setInfoS1(String infoS1) {
        this.infoS1 = infoS1;
    }

    @Column(name = "INFO_SI_2")
    public String getInfoS2() {
        return infoS2;
    }

    public void setInfoS2(String infoS2) {
        this.infoS2 = infoS2;
    }

    @Column(name = "INFO_SI_3")
    public String getInfoS3() {
        return infoS3;
    }

    public void setInfoS3(String infoS3) {
        this.infoS3 = infoS3;
    }

    @Column(name = "NOTE")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Column(name = "STATUS_SURVEY")
    public String getStatusSurvey() {
        return statusSurvey;
    }

    public void setStatusSurvey(String statusSurvey) {
        this.statusSurvey = statusSurvey;
    }

    @Column(name = "FOTO1")
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    public byte[] getFoto1() {
        return foto1;
    }

    public void setFoto1(byte[] foto1) {
        this.foto1 = foto1;
    }

    @Column(name = "FOTO2")
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    public byte[] getFoto2() {
        return foto2;
    }

    public void setFoto2(byte[] foto2) {
        this.foto2 = foto2;
    }

    @Column(name = "FOTO3")
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    public byte[] getFoto3() {
        return foto3;
    }

    public void setFoto3(byte[] foto3) {
        this.foto3 = foto3;
    }

    @Column(name = "FOTO4")
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    public byte[] getFoto4() {
        return foto4;
    }

    public void setFoto4(byte[] foto4) {
        this.foto4 = foto4;
    }

    @Column(name = "FOTO5")
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    public byte[] getFoto5() {
        return foto5;
    }

    public void setFoto5(byte[] foto5) {
        this.foto5 = foto5;
    }

    @Column(name = "FOTO6")
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    public byte[] getFoto6() {
        return foto6;
    }

    public void setFoto6(byte[] foto6) {
        this.foto6 = foto6;
    }

    @Column(name = "FOTO7")
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    public byte[] getFoto7() {
        return foto7;
    }

    public void setFoto7(byte[] foto7) {
        this.foto7 = foto7;
    }

    @Column(name = "FOTO8")
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    public byte[] getFoto8() {
        return foto8;
    }

    public void setFoto8(byte[] foto8) {
        this.foto8 = foto8;
    }

    @Column(name = "FOTO9")
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    public byte[] getFoto9() {
        return foto9;
    }

    public void setFoto9(byte[] foto9) {
        this.foto9 = foto9;
    }

    @Column(name = "FOTO10")
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    public byte[] getFoto10() {
        return foto10;
    }

    public void setFoto10(byte[] foto10) {
        this.foto10 = foto10;
    }
}