package com.permata.appraisal2.model;

import com.permata.appraisal2.common.AppConstans;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

//@Entity
//@Table(name = AppConstans.HibernateConstanstan.PREFIX_DB_NAME + "LPA_DESKTOP")
@Deprecated
public class LpaDesktop extends EntityBase<Long> {

    private Long id;
    private LpaUmum lpaUmum;
    private String appraisalTypeCategory;

    // data agunan internal
    private BigDecimal hargaTanah;
    private BigDecimal hargaBangunan;
    private String uraian1;
    private Boolean hitungManual1;
    private BigDecimal nilaiDokumen1;
    private Boolean diberiNilaiDokumen1;
    private BigDecimal nilaiFisik1;
    private Boolean diberiNilaiFisik1;
    private Double prosentase1;
    private Boolean hitungManual2;
    private String uraian2;
    private BigDecimal nilaiDokumen2;
    private Boolean diberiNilaiDokumen2;
    private BigDecimal nilaiFisik2;
    private Boolean diberiNilaiFisik2;
    private Double prosentase2;
    private BigDecimal totalSesuaiDokumen;
    private BigDecimal totalSesuaiFisik;
    private BigDecimal likuidasiDokumen1;
    private BigDecimal likuidasiFisik1;
    private BigDecimal likuidasiDokumen2;
    private BigDecimal likuidasiFisik2;
    private BigDecimal totalLikuidasiDokumen;
    private BigDecimal totalLikuidasiFisik;
    private BigDecimal toleransi1;
    private BigDecimal toleransi2;

    // data tanah
    private Double totalLuasTanahDokumen;
    private String bentukTanah;
    private String konturTanah;
    private String ketinggianTanah;
    private String peruntukanTanah;
    private String batasTanah;
    private String batasUtaraTanah;
    private String batasSelatanTanah;
    private String batasBaratTanah;
    private String batasTimurTanah;
    private String posisiTanah;
    private String kondisiTanah;
    private String jenisTanah;

    // data bangunan fisik
    private Double luasBangunanImb;
    private Double luasLantai1;
    private Double luasLantai2;
    private Double luasLantai3;
    private Double luasLantai4;
    private Double luasLantai5;
    private Double luasBangunan;
    private Integer jumlahLantaiFisik;
    private Integer jumlahLantaiTampakDepan;
    private String jenisBangunan;
    private String digunakanSebagai;
    private String dihuniOleh;
    private String statusAgunanDisewakan;
    private Integer tahunDibangun;
    private Integer tahunDirenov;
    private String kelasBangunan;
    private String kondisiUmum;
    private String prosesPembangunan;
    private String statusBangunan;
    private Double prosesKonstruksi;
    private Date tanggalProsesKonstruksi;
    private Boolean hitungManual;

    // data lingkungan
    private String lingkunganSekitar;
    private String tingkatKepadatan;
    private String tingkatKemacetan;
    private String rencanaJalan;
    private Double lebarJalan;
    private Double gsb;
    private Double gss;
    private String kendaraanRoda4;

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "LPA_DESKTOP_SEQ", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "LPA_DESKTOP_SEQ", sequenceName = "LPA_DESKTOP_SEQ", initialValue = 1, allocationSize = 1)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "ID_LPA_UMUM")
    public LpaUmum getLpaUmum() {
        return lpaUmum;
    }

    public void setLpaUmum(LpaUmum lpaUmum) {
        this.lpaUmum = lpaUmum;
    }

    @Column(name = "KATEGORI_JENIS_APPRAISAL", length = 50)
    public String getAppraisalTypeCategory() {
        return appraisalTypeCategory;
    }

    public void setAppraisalTypeCategory(String appraisalTypeCategory) {
        this.appraisalTypeCategory = appraisalTypeCategory;
    }

    @Column(name = "HARGA_TANAH")
    public BigDecimal getHargaTanah() {
        return hargaTanah;
    }

    public void setHargaTanah(BigDecimal hargaTanah) {
        this.hargaTanah = hargaTanah;
    }

    @Column(name = "HARGA_BANGUNAN")
    public BigDecimal getHargaBangunan() {
        return hargaBangunan;
    }

    public void setHargaBangunan(BigDecimal hargaBangunan) {
        this.hargaBangunan = hargaBangunan;
    }

    @Column(name = "URAIAN_1")
    public String getUraian1() {
        return uraian1;
    }

    public void setUraian1(String uraian1) {
        this.uraian1 = uraian1;
    }

    @Column(name = "NILAI_DOKUMEN_1")
    public BigDecimal getNilaiDokumen1() {
        return nilaiDokumen1;
    }

    public void setNilaiDokumen1(BigDecimal nilaiDokumen1) {
        this.nilaiDokumen1 = nilaiDokumen1;
    }

    @Column(name = "HITUNG_DOKUMEN_1")
    public Boolean getDiberiNilaiDokumen1() {
        return diberiNilaiDokumen1;
    }

    public void setDiberiNilaiDokumen1(Boolean diberiNilaiDokumen1) {
        this.diberiNilaiDokumen1 = diberiNilaiDokumen1;
    }

    @Column(name = "NILAI_FISIK_1")
    public BigDecimal getNilaiFisik1() {
        return nilaiFisik1;
    }

    public void setNilaiFisik1(BigDecimal nilaiFisik1) {
        this.nilaiFisik1 = nilaiFisik1;
    }

    @Column(name = "HITUNG_FISIK_1")
    public Boolean getDiberiNilaiFisik1() {
        return diberiNilaiFisik1;
    }

    public void setDiberiNilaiFisik1(Boolean diberiNilaiFisik1) {
        this.diberiNilaiFisik1 = diberiNilaiFisik1;
    }

    @Column(name = "PROSENTASE_1")
    public Double getProsentase1() {
        return prosentase1;
    }

    public void setProsentase1(Double prosentase1) {
        this.prosentase1 = prosentase1;
    }

    @Column(name = "HITUNG_MANUAL_1")
    public Boolean getHitungManual1() {
        return hitungManual1;
    }

    public void setHitungManual1(Boolean hitungManual1) {
        this.hitungManual1 = hitungManual1;
    }

    @Column(name = "HITUNG_MANUAL_2")
    public Boolean getHitungManual2() {
        return hitungManual2;
    }

    public void setHitungManual2(Boolean hitungManual2) {
        this.hitungManual2 = hitungManual2;
    }

    @Column(name = "URAIAN_2")
    public String getUraian2() {
        return uraian2;
    }

    public void setUraian2(String uraian2) {
        this.uraian2 = uraian2;
    }

    @Column(name = "NILAI_DOKUMEN_2")
    public BigDecimal getNilaiDokumen2() {
        return nilaiDokumen2;
    }

    public void setNilaiDokumen2(BigDecimal nilaiDokumen2) {
        this.nilaiDokumen2 = nilaiDokumen2;
    }

    @Column(name = "HITUNG_DOKUMEN_2")
    public Boolean getDiberiNilaiDokumen2() {
        return diberiNilaiDokumen2;
    }

    public void setDiberiNilaiDokumen2(Boolean diberiNilaiDokumen2) {
        this.diberiNilaiDokumen2 = diberiNilaiDokumen2;
    }

    @Column(name = "NILAI_FISIK_2")
    public BigDecimal getNilaiFisik2() {
        return nilaiFisik2;
    }

    public void setNilaiFisik2(BigDecimal nilaiFisik2) {
        this.nilaiFisik2 = nilaiFisik2;
    }

    @Column(name = "HITUNG_FISIK_2")
    public Boolean getDiberiNilaiFisik2() {
        return diberiNilaiFisik2;
    }

    public void setDiberiNilaiFisik2(Boolean diberiNilaiFisik2) {
        this.diberiNilaiFisik2 = diberiNilaiFisik2;
    }

    @Column(name = "PROSENTASE_2")
    public Double getProsentase2() {
        return prosentase2;
    }

    public void setProsentase2(Double prosentase2) {
        this.prosentase2 = prosentase2;
    }

    @Column(name = "BENTUK_TANAH")
    public String getBentukTanah() {
        return bentukTanah;
    }

    public void setBentukTanah(String bentukTanah) {
        this.bentukTanah = bentukTanah;
    }

    @Column(name = "KONTUR_TANAH")
    public String getKonturTanah() {
        return konturTanah;
    }

    public void setKonturTanah(String konturTanah) {
        this.konturTanah = konturTanah;
    }

    @Column(name = "KETINGGIAN_TANAH")
    public String getKetinggianTanah() {
        return ketinggianTanah;
    }

    public void setKetinggianTanah(String ketinggianTanah) {
        this.ketinggianTanah = ketinggianTanah;
    }

    @Column(name = "PERUNTUKAN_TANAH")
    public String getPeruntukanTanah() {
        return peruntukanTanah;
    }

    public void setPeruntukanTanah(String peruntukanTanah) {
        this.peruntukanTanah = peruntukanTanah;
    }

    @Column(name = "KESESUAIAN_BATAS")
    public String getBatasTanah() {
        return batasTanah;
    }

    public void setBatasTanah(String batasTanah) {
        this.batasTanah = batasTanah;
    }

    @Column(name = "BATAS_UTARA")
    public String getBatasUtaraTanah() {
        return batasUtaraTanah;
    }

    public void setBatasUtaraTanah(String batasUtaraTanah) {
        this.batasUtaraTanah = batasUtaraTanah;
    }

    @Column(name = "BATAS_SELATAN")
    public String getBatasSelatanTanah() {
        return batasSelatanTanah;
    }

    public void setBatasSelatanTanah(String batasSelatanTanah) {
        this.batasSelatanTanah = batasSelatanTanah;
    }

    @Column(name = "BATAS_BARAT")
    public String getBatasBaratTanah() {
        return batasBaratTanah;
    }

    public void setBatasBaratTanah(String batasBaratTanah) {
        this.batasBaratTanah = batasBaratTanah;
    }

    @Column(name = "BATAS_TIMUR")
    public String getBatasTimurTanah() {
        return batasTimurTanah;
    }

    public void setBatasTimurTanah(String batasTimurTanah) {
        this.batasTimurTanah = batasTimurTanah;
    }

    @Column(name = "POSISI_TANAH")
    public String getPosisiTanah() {
        return posisiTanah;
    }

    public void setPosisiTanah(String posisiTanah) {
        this.posisiTanah = posisiTanah;
    }

    @Column(name = "KONDISI_TANAH")
    public String getKondisiTanah() {
        return kondisiTanah;
    }

    public void setKondisiTanah(String kondisiTanah) {
        this.kondisiTanah = kondisiTanah;
    }

    @Column(name = "JENIS_TANAH")
    public String getJenisTanah() {
        return jenisTanah;
    }

    public void setJenisTanah(String jenisTanah) {
        this.jenisTanah = jenisTanah;
    }

    // double data
//    @Column(name = "ID_JENIS_AKTIVA")
//    public Long getJenisAktiva() {
//        return jenisAktiva;
//    }
//
//    public void setJenisAktiva(Long jenisAktiva) {
//        this.jenisAktiva = jenisAktiva;
//    }
//
//    @Column(name = "ID_STATUS_JAMINAN")
//    public Long getStatusJaminan() {
//        return statusJaminan;
//    }
//
//    public void setStatusJaminan(Long statusJaminan) {
//        this.statusJaminan = statusJaminan;
//    }
//
//    @Column(name = "JENIS_APPRAISAL")
//    public String getJenisAppraisal() {
//        return jenisAppraisal;
//    }
//
//    public void setJenisAppraisal(String jenisAppraisal) {
//        this.jenisAppraisal = jenisAppraisal;
//    }
//
//    @Column(name = "NOMOR_ORDER")
//    public String getOrderNumber() {
//        return orderNumber;
//    }
//
//    public void setOrderNumber(String orderNumber) {
//        this.orderNumber = orderNumber;
//    }
//
//    @Column(name = "NAMA_CONTACT")
//    public String getContactPerson() {
//        return contactPerson;
//    }
//
//    public void setContactPerson(String contactPerson) {
//        this.contactPerson = contactPerson;
//    }
//
//    @Column(name = "NAMA_DEBITUR")
//    public String getNamaDebitur() {
//        return namaDebitur;
//    }
//
//    public void setNamaDebitur(String namaDebitur) {
//        this.namaDebitur = namaDebitur;
//    }
//
//    @Column(name = "JENIS_OBJECT")
//    public String getJenisObjectPenilain() {
//        return jenisObjectPenilain;
//    }
//
//    public void setJenisObjectPenilain(String jenisObjectPenilain) {
//        this.jenisObjectPenilain = jenisObjectPenilain;
//    }
//
//    @Column(name = "NAMA_PERUMAHAN")
//    public String getNamaPerumahan() {
//        return namaPerumahan;
//    }
//
//    public void setNamaPerumahan(String namaPerumahan) {
//        this.namaPerumahan = namaPerumahan;
//    }
//
//    @Column(name = "NAMA_CLUSTER")
//    public String getNamaCluster() {
//        return namaCluster;
//    }
//
//    public void setNamaCluster(String namaCluster) {
//        this.namaCluster = namaCluster;
//    }
//
//    @Column(name = "BLOCK")
//    public String getBlock() {
//        return block;
//    }
//
//    public void setBlock(String block) {
//        this.block = block;
//    }
//
//    @Column(name = "NOMOR")
//    public String getNomor() {
//        return nomor;
//    }
//
//    public void setNomor(String nomor) {
//        this.nomor = nomor;
//    }
//
//    @Column(name = "RT")
//    public String getRt() {
//        return rt;
//    }
//
//    public void setRt(String rt) {
//        this.rt = rt;
//    }
//
//    @Column(name = "RW")
//    public String getRw() {
//        return rw;
//    }
//
//    public void setRw(String rw) {
//        this.rw = rw;
//    }
//
//    @Column(name = "KODE_POS")
//    public String getKodePos() {
//        return kodePos;
//    }
//
//    public void setKodePos(String kodePos) {
//        this.kodePos = kodePos;
//    }
//
//    @Column(name = "PROPINSI")
//    public String getPropinsi() {
//        return propinsi;
//    }
//
//    public void setPropinsi(String propinsi) {
//        this.propinsi = propinsi;
//    }
//
//    @Column(name = "KOTA")
//    public String getKota() {
//        return kota;
//    }
//
//    public void setKota(String kota) {
//        this.kota = kota;
//    }
//
//    @Column(name = "KECAMATAN")
//    public String getKecamatan() {
//        return kecamatan;
//    }
//
//    public void setKecamatan(String kecamatan) {
//        this.kecamatan = kecamatan;
//    }
//
//    @Column(name = "KELURAHAN")
//    public String getKelurahan() {
//        return kelurahan;
//    }
//
//    public void setKelurahan(String kelurahan) {
//        this.kelurahan = kelurahan;
//    }
//
//    @Column(name = "TANGGAL_ORDER")
//    public Date getTanggalOrder() {
//        return tanggalOrder;
//    }
//
//    public void setTanggalOrder(Date tanggalOrder) {
//        this.tanggalOrder = tanggalOrder;
//    }
//
//    @Column(name = "KATEGORI_SLA")
//    public Long getKategoriSla() {
//        return kategoriSla;
//    }
//
//    public void setKategoriSla(Long kategoriSla) {
//        this.kategoriSla = kategoriSla;
//    }
//
//    @Column(name = "DITUJU_KE")
//    public String getDitujuke() {
//        return ditujuke;
//    }
//
//    public void setDitujuke(String ditujuke) {
//        this.ditujuke = ditujuke;
//    }

    @Column(name = "LUAS_BANGUNAN_IMB")
    public Double getLuasBangunanImb() {
        return luasBangunanImb;
    }

    public void setLuasBangunanImb(Double luasBangunanImb) {
        this.luasBangunanImb = luasBangunanImb;
    }

    @Column(name = "LUAS_LANTAI_1")
    public Double getLuasLantai1() {
        return luasLantai1;
    }

    public void setLuasLantai1(Double luasLantai1) {
        this.luasLantai1 = luasLantai1;
    }

    @Column(name = "LUAS_LANTAI_2")
    public Double getLuasLantai2() {
        return luasLantai2;
    }

    public void setLuasLantai2(Double luasLantai2) {
        this.luasLantai2 = luasLantai2;
    }

    @Column(name = "LUAS_LANTAI_3")
    public Double getLuasLantai3() {
        return luasLantai3;
    }

    public void setLuasLantai3(Double luasLantai3) {
        this.luasLantai3 = luasLantai3;
    }

    @Column(name = "LUAS_LANTAI_4")
    public Double getLuasLantai4() {
        return luasLantai4;
    }

    public void setLuasLantai4(Double luasLantai4) {
        this.luasLantai4 = luasLantai4;
    }

    @Column(name = "LUAS_LANTAI_5")
    public Double getLuasLantai5() {
        return luasLantai5;
    }

    public void setLuasLantai5(Double luasLantai5) {
        this.luasLantai5 = luasLantai5;
    }

    @Column(name = "LUAS_BANGUNAN")
    public Double getLuasBangunan() {
        return luasBangunan;
    }

    public void setLuasBangunan(Double luasBangunan) {
        this.luasBangunan = luasBangunan;
    }

    @Column(name = "JML_LANTAI_FISIK")
    public Integer getJumlahLantaiFisik() {
        return jumlahLantaiFisik;
    }

    public void setJumlahLantaiFisik(Integer jumlahLantaiFisik) {
        this.jumlahLantaiFisik = jumlahLantaiFisik;
    }

    @Column(name = "JML_LANTAI_DEPAN")
    public Integer getJumlahLantaiTampakDepan() {
        return jumlahLantaiTampakDepan;
    }

    public void setJumlahLantaiTampakDepan(Integer jumlahLantaiTampakDepan) {
        this.jumlahLantaiTampakDepan = jumlahLantaiTampakDepan;
    }

    @Column(name = "JENIS_BANGUNAN")
    public String getJenisBangunan() {
        return jenisBangunan;
    }

    public void setJenisBangunan(String jenisBangunan) {
        this.jenisBangunan = jenisBangunan;
    }

    @Column(name = "DIGUNAKAN_SEBAGAI")
    public String getDigunakanSebagai() {
        return digunakanSebagai;
    }

    public void setDigunakanSebagai(String digunakanSebagai) {
        this.digunakanSebagai = digunakanSebagai;
    }

    @Column(name = "DIHUNI_OLEH")
    public String getDihuniOleh() {
        return dihuniOleh;
    }

    public void setDihuniOleh(String dihuniOleh) {
        this.dihuniOleh = dihuniOleh;
    }

    @Column(name = "STATUS_AGUNAN")
    public String getStatusAgunanDisewakan() {
        return statusAgunanDisewakan;
    }

    public void setStatusAgunanDisewakan(String statusAgunanDisewakan) {
        this.statusAgunanDisewakan = statusAgunanDisewakan;
    }

    @Column(name = "TAHUN_DIBANGUN")
    public Integer getTahunDibangun() {
        return tahunDibangun;
    }

    public void setTahunDibangun(Integer tahunDibangun) {
        this.tahunDibangun = tahunDibangun;
    }

    @Column(name = "TAHUN_RENOVASI")
    public Integer getTahunDirenov() {
        return tahunDirenov;
    }

    public void setTahunDirenov(Integer tahunDirenov) {
        this.tahunDirenov = tahunDirenov;
    }

    @Column(name = "KELAS_BANGUNGAN")
    public String getKelasBangunan() {
        return kelasBangunan;
    }

    public void setKelasBangunan(String kelasBangunan) {
        this.kelasBangunan = kelasBangunan;
    }

    @Column(name = "KONDISI_UMUM")
    public String getKondisiUmum() {
        return kondisiUmum;
    }

    public void setKondisiUmum(String kondisiUmum) {
        this.kondisiUmum = kondisiUmum;
    }

    @Column(name = "PROSES_PEMBANGUNAN")
    public String getProsesPembangunan() {
        return prosesPembangunan;
    }

    public void setProsesPembangunan(String prosesPembangunan) {
        this.prosesPembangunan = prosesPembangunan;
    }

    @Column(name = "STATUS_BANGUNAN")
    public String getStatusBangunan() {
        return statusBangunan;
    }

    public void setStatusBangunan(String statusBangunan) {
        this.statusBangunan = statusBangunan;
    }

    @Column(name = "PROSES_KONSTRUKSI")
    public Double getProsesKonstruksi() {
        return prosesKonstruksi;
    }

    public void setProsesKonstruksi(Double prosesKonstruksi) {
        this.prosesKonstruksi = prosesKonstruksi;
    }

    @Column(name = "TANGGAL_KONSTRUKSI")
    public Date getTanggalProsesKonstruksi() {
        return tanggalProsesKonstruksi;
    }

    public void setTanggalProsesKonstruksi(Date tanggalProsesKonstruksi) {
        this.tanggalProsesKonstruksi = tanggalProsesKonstruksi;
    }

    @Column(name = "HITUNG_MANUAL")
    public Boolean getHitungManual() {
        return hitungManual;
    }

    public void setHitungManual(Boolean hitungManual) {
        this.hitungManual = hitungManual;
    }

    // data lingkungan
    @Column(name = "LINGKUNGAN_SEKITAR")
    public String getLingkunganSekitar() {
        return lingkunganSekitar;
    }

    public void setLingkunganSekitar(String lingkunganSekitar) {
        this.lingkunganSekitar = lingkunganSekitar;
    }

    @Column(name = "TINGKAT_KEPADATAN")
    public String getTingkatKepadatan() {
        return tingkatKepadatan;
    }

    public void setTingkatKepadatan(String tingkatKepadatan) {
        this.tingkatKepadatan = tingkatKepadatan;
    }

    @Column(name = "TINGKAT_KEMACETAN")
    public String getTingkatKemacetan() {
        return tingkatKemacetan;
    }

    public void setTingkatKemacetan(String tingkatKemacetan) {
        this.tingkatKemacetan = tingkatKemacetan;
    }

    @Column(name = "RENCANA_JALAN")
    public String getRencanaJalan() {
        return rencanaJalan;
    }

    public void setRencanaJalan(String rencanaJalan) {
        this.rencanaJalan = rencanaJalan;
    }

    @Column(name = "LEBAR_JALAN")
    public Double getLebarJalan() {
        return lebarJalan;
    }

    public void setLebarJalan(Double lebarJalan) {
        this.lebarJalan = lebarJalan;
    }

    @Column(name = "GSB")
    public Double getGsb() {
        return gsb;
    }

    public void setGsb(Double gsb) {
        this.gsb = gsb;
    }

    @Column(name = "GSS")
    public Double getGss() {
        return gss;
    }

    public void setGss(Double gss) {
        this.gss = gss;
    }

    @Column(name = "KENDARAAN_RODA_4")
    public String getKendaraanRoda4() {
        return kendaraanRoda4;
    }

    public void setKendaraanRoda4(String kendaraanRoda4) {
        this.kendaraanRoda4 = kendaraanRoda4;
    }

    @Column(name = "TOTAL_SESUAI_DOC")
    public BigDecimal getTotalSesuaiDokumen() {
        return totalSesuaiDokumen;
    }

    public void setTotalSesuaiDokumen(BigDecimal totalSesuaiDokumen) {
        this.totalSesuaiDokumen = totalSesuaiDokumen;
    }

    @Column(name = "TOTAL_SESUAI_FISIK")
    public BigDecimal getTotalSesuaiFisik() {
        return totalSesuaiFisik;
    }

    public void setTotalSesuaiFisik(BigDecimal totalSesuaiFisik) {
        this.totalSesuaiFisik = totalSesuaiFisik;
    }

    @Column(name = "LIKUIDASI_DOC_1")
    public BigDecimal getLikuidasiDokumen1() {
        return likuidasiDokumen1;
    }

    public void setLikuidasiDokumen1(BigDecimal likuidasiDokumen1) {
        this.likuidasiDokumen1 = likuidasiDokumen1;
    }

    @Column(name = "LIKUIDASI_FISIK_1")
    public BigDecimal getLikuidasiFisik1() {
        return likuidasiFisik1;
    }

    public void setLikuidasiFisik1(BigDecimal likuidasiFisik1) {
        this.likuidasiFisik1 = likuidasiFisik1;
    }

    @Column(name = "LIKUIDASI_DOC_2")
    public BigDecimal getLikuidasiDokumen2() {
        return likuidasiDokumen2;
    }

    public void setLikuidasiDokumen2(BigDecimal likuidasiDokumen2) {
        this.likuidasiDokumen2 = likuidasiDokumen2;
    }

    @Column(name = "LIKUIDASI_FISIK_2")
    public BigDecimal getLikuidasiFisik2() {
        return likuidasiFisik2;
    }

    public void setLikuidasiFisik2(BigDecimal likuidasiFisik2) {
        this.likuidasiFisik2 = likuidasiFisik2;
    }

    @Column(name = "TOTAL_LIKUIDASI_DOC")
    public BigDecimal getTotalLikuidasiDokumen() {
        return totalLikuidasiDokumen;
    }

    public void setTotalLikuidasiDokumen(BigDecimal totalLikuidasiDokumen) {
        this.totalLikuidasiDokumen = totalLikuidasiDokumen;
    }

    @Column(name = "TOTAL_LIKUIDASI_FISIK")
    public BigDecimal getTotalLikuidasiFisik() {
        return totalLikuidasiFisik;
    }

    public void setTotalLikuidasiFisik(BigDecimal totalLikuidasiFisik) {
        this.totalLikuidasiFisik = totalLikuidasiFisik;
    }

    @Column(name = "TOLERANSI_1")
    public BigDecimal getToleransi1() {
        return toleransi1;
    }

    public void setToleransi1(BigDecimal toleransi1) {
        this.toleransi1 = toleransi1;
    }

    @Column(name = "TOLERANSI_2")
    public BigDecimal getToleransi2() {
        return toleransi2;
    }

    public void setToleransi2(BigDecimal toleransi2) {
        this.toleransi2 = toleransi2;
    }
}