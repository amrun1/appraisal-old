package com.permata.appraisal2.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Deprecated
public class LpaDesktopDto  extends LpaUmumDto  implements Serializable {

    private Long id;
    private String appraisalTypeCategory;
    @NotNull private String posisiLokasiAgunan;

    // data fisik tanah
    @NotNull private String bentukTanah;
    @NotNull private String konturTanah;
    @NotNull private String ketinggianTanah;
    @NotNull private String peruntukanTanah;
    @NotNull private String batasTanah;
    @NotNull private String batasUtaraTanah;
    @NotNull private String batasSelatanTanah;
    @NotNull private String batasBaratTanah;
    @NotNull private String batasTimurTanah;
    @NotNull private String posisiTanah;
    @NotNull private String kondisiTanah;
    @NotNull private String jenisTanah;
    @NotNull private Double totalLuasTanahDokumen;

    // data bangunan fisik
    @NotNull private Double luasBangunanImb;
    @NotNull private Double luasLantai1;
    private Double luasLantai2;
    private Double luasLantai3;
    private Double luasLantai4;
    private Double luasLantai5;
    @NotNull private Double luasBangunan;
    @NotNull private Integer jumlahLantaiFisik;
    @NotNull private Integer jumlahLantaiTampakDepan;
    @NotNull private String jenisBangunan;
    @NotNull private String digunakanSebagai;
    @NotNull private String dihuniOleh;
    private String statusAgunanDisewakan;
    @NotNull private Integer tahunDibangun;
    @NotNull private Integer tahunDirenov;
    @NotNull private String kelasBangunan;
    @NotNull private String kondisiUmum;
    @NotNull private String prosesPembangunan;
    @NotNull private String statusBangunan;
    @NotNull private Double prosesKonstruksi;
    private Date tanggalProsesKonstruksi;
    @NotNull private boolean hitungManual;

    // data lingkungan
    @NotNull private String lingkunganSekitar;
    @NotNull private String tingkatKepadatan;
    @NotNull private String tingkatKemacetan;
    @NotNull private String rencanaJalan;
    @NotNull private Double lebarJalan;
    @NotNull private Double gsb;
    @NotNull private Double gss;
    @NotNull private String kendaraanRoda4;

    // data agunan Internal
    @NotNull private BigDecimal hargaTanah;
    @NotNull private BigDecimal hargaBangunan;
    @NotNull private Boolean hitungManual1;
    @NotNull private String uraian1;
    @NotNull private BigDecimal nilaiDokumen1;
    @NotNull private Boolean diberiNilaiDokumen1;
    @NotNull private BigDecimal nilaiFisik1;
    @NotNull private Boolean diberiNilaiFisik1;
    @NotNull private Double prosentase1;
    private Boolean hitungManual2;
    @NotNull private String uraian2;
    @NotNull private BigDecimal nilaiDokumen2;
    @NotNull private Boolean diberiNilaiDokumen2;
    @NotNull private BigDecimal nilaiFisik2;
    @NotNull private Boolean diberiNilaiFisik2;
    @NotNull private Double prosentase2;
    @NotNull private BigDecimal totalSesuaiDokumen;
    @NotNull private BigDecimal totalSesuaiFisik;
    @NotNull private BigDecimal likuidasiDokumen1;
    @NotNull private BigDecimal likuidasiFisik1;
    @NotNull private BigDecimal likuidasiDokumen2;
    @NotNull private BigDecimal likuidasiFisik2;
    @NotNull private BigDecimal totalLikuidasiDokumen;
    @NotNull private BigDecimal totalLikuidasiFisik;
    @NotNull private BigDecimal toleransi1;
    @NotNull private BigDecimal toleransi2;

    // data tanah
    @NotNull @Valid private LpaDokumenTanahDto dokumenTanah = new LpaDokumenTanahDto();
    @NotNull private List<LpaDokumenTanahDto> listDokumenTanah = new ArrayList<LpaDokumenTanahDto>();
    @NotNull @Valid private LpaDokumenBangunanDto dokumenBangunan = new LpaDokumenBangunanDto();
    @NotNull private List<LpaDokumenBangunanDto> listDokumenBangunan = new ArrayList<LpaDokumenBangunanDto>();

    private String typeReport;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppraisalTypeCategory() {
        return appraisalTypeCategory;
    }

    public void setAppraisalTypeCategory(String appraisalTypeCategory) {
        this.appraisalTypeCategory = appraisalTypeCategory;
    }

    @Override
    public String getPosisiLokasiAgunan() {
        return posisiLokasiAgunan;
    }

    @Override
    public void setPosisiLokasiAgunan(String posisiLokasiAgunan) {
        this.posisiLokasiAgunan = posisiLokasiAgunan;
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

    public Double getTotalLuasTanahDokumen() {
        return totalLuasTanahDokumen;
    }

    public void setTotalLuasTanahDokumen(Double totalLuasTanahDokumen) {
        this.totalLuasTanahDokumen = totalLuasTanahDokumen;
    }

    public Double getLuasBangunanImb() {
        return luasBangunanImb;
    }

    public void setLuasBangunanImb(Double luasBangunanImb) {
        this.luasBangunanImb = luasBangunanImb;
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

    public boolean isHitungManual() {
        return hitungManual;
    }

    public void setHitungManual(boolean hitungManual) {
        this.hitungManual = hitungManual;
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

    public Boolean getHitungManual1() {
        return hitungManual1;
    }

    public void setHitungManual1(Boolean hitungManual1) {
        this.hitungManual1 = hitungManual1;
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

    public Boolean getHitungManual2() {
        return hitungManual2;
    }

    public void setHitungManual2(Boolean hitungManual2) {
        this.hitungManual2 = hitungManual2;
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

    public String getTypeReport() {
        return typeReport;
    }

    public void setTypeReport(String typeReport) {
        this.typeReport = typeReport;
    }
}