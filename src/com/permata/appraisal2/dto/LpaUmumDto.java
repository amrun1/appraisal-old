package com.permata.appraisal2.dto;

import com.permata.appraisal2.common.AppConstans;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LpaUmumDto  implements Serializable {

    private Long idLpa;
    private Long surveyId;
    @NotNull private Long detailId;
    @NotNull private Long commonId;
    @NotNull private Long branchId;
    private Long idJenisAppraisal;
    @NotNull private String jenisAppraisal;
    private String kategoriJenisAppraisal;
    @NotNull private String orderNumber;
    private String lpaNumber;
    @NotNull private String contactPerson;
    @NotNull private String namaDebitur;
    @NotNull private String jenisObjectPenilain;
    private String kategoriObjectPenilaian;
    private String namaPerumahan;
     private String namaCluster;
     private String block;
   private String nomor;
   private String rt;
     private String rw;
    @NotNull private String kodePos;
    @NotNull private String propinsi;
    @NotNull private String kota;
    @NotNull private String kecamatan;
    @NotNull private String kelurahan;
    @NotNull private Long statusJaminan;
    @NotNull private Date tanggalOrder;
    @NotNull private String kategoriSla;
    @NotNull private Integer sla;
    @NotNull private String ditujuke;
    @NotNull private Long jenisAktiva;
    @NotNull private Long idObjectPenilaiFisik;
    @NotNull private String jenisObjectPenilaiFisik;
    @NotNull private String lokasiFisik;
    @NotNull private Date tanggalSurvey;
    @NotNull private String ditinjau;
    @NotNull private String ditemui;
    @NotNull private Boolean draft;
    @NotNull private String catatanSurvey;
    @NotNull private String mataUang;
    @NotNull private Date tanggalRate;
    @NotNull private BigDecimal nilaiRate;
    private String posisiLokasiAgunan;

    // data Faktor Market
    @NotNull private String faktorMarketability;
    private String faktorMarketCatatan;

    // data Negative List
    @NotNull private List<Long> listNegative = new ArrayList<Long>();
    @NotNull private List<Long> listPertimbangan = new ArrayList<Long>();
    private String positifPoint;
    @NotNull private String catatanPoint;

    // data Pertimbangan
    private String pembanding1;
    private String pembanding2;
    private String pembanding3;

    // opini eksternal
    private String opini;
    private Double deviasiDoc;
    private Double deviasiFisik;
    private Date tanggalPenilaian;
    private Date tanggalOrderKeEksternal;
    private Date tanggalTerimaDariEksternal;
    private Long idKjpp;

    //catatan eksternal
    private String catatanKewajaranNil;
    private String catatanKesesuaian;

    public String getCatatanKewajaranNil() {
        return catatanKewajaranNil;
    }

    public void setCatatanKewajaranNil(String catatanKewajaranNil) {
        this.catatanKewajaranNil = catatanKewajaranNil;
    }

    public String getCatatanKesesuaian() {
        return catatanKesesuaian;
    }

    public void setCatatanKesesuaian(String catatanKesesuaian) {
        this.catatanKesesuaian = catatanKesesuaian;
    }

    private boolean create;

    public boolean isEksternal(){
        if(StringUtils.isNotBlank(getKategoriJenisAppraisal())){
            return AppConstans.JenisAppraisalCategory.EKSTERNAL.equalsIgnoreCase(getKategoriJenisAppraisal());
        }
        return false;
    }



    public Long getIdJenisAppraisal() {
        return idJenisAppraisal;
    }

    public void setIdJenisAppraisal(Long idJenisAppraisal) {
        this.idJenisAppraisal = idJenisAppraisal;
    }

    public Long getIdObjectPenilaiFisik() {
        return idObjectPenilaiFisik;
    }

    public void setIdObjectPenilaiFisik(Long idObjectPenilaiFisik) {
        this.idObjectPenilaiFisik = idObjectPenilaiFisik;
    }

    public Integer getSla() {
        return sla;
    }

    public void setSla(Integer sla) {
        this.sla = sla;
    }

    public Long getIdKjpp() {
        return idKjpp;
    }

    public void setIdKjpp(Long idKjpp) {
        this.idKjpp = idKjpp;
    }

    public String getOpini() {
        return opini;
    }

    public void setOpini(String opini) {
        this.opini = opini;
    }

    public Double getDeviasiDoc() {
        return deviasiDoc;
    }

    public void setDeviasiDoc(Double deviasiDoc) {
        this.deviasiDoc = deviasiDoc;
    }

    public Double getDeviasiFisik() {
        return deviasiFisik;
    }

    public void setDeviasiFisik(Double deviasiFisik) {
        this.deviasiFisik = deviasiFisik;
    }

    public String getPosisiLokasiAgunan() {
        return posisiLokasiAgunan;
    }

    public void setPosisiLokasiAgunan(String posisiLokasiAgunan) {
        this.posisiLokasiAgunan = posisiLokasiAgunan;
    }

    public String getKategoriObjectPenilaian() {
        return kategoriObjectPenilaian;
    }

    public void setKategoriObjectPenilaian(String kategoriObjectPenilaian) {
        this.kategoriObjectPenilaian = kategoriObjectPenilaian;
    }

    public boolean isCreate() {
        return create;
    }

    public void setCreate(boolean create) {
        this.create = create;
    }

    public String getCatatanSurvey() {
        return catatanSurvey;
    }

    public void setCatatanSurvey(String catatanSurvey) {
        this.catatanSurvey = catatanSurvey;
    }

    public String getLokasiFisik() {
        return lokasiFisik;
    }

    public void setLokasiFisik(String lokasiFisik) {
        this.lokasiFisik = lokasiFisik;
    }

    public Long getIdLpa() {
        return idLpa;
    }

    public void setIdLpa(Long idLpa) {
        this.idLpa = idLpa;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Long getCommonId() {
        return commonId;
    }

    public void setCommonId(Long commonId) {
        this.commonId = commonId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getKategoriJenisAppraisal() {
        return kategoriJenisAppraisal;
    }

    public void setKategoriJenisAppraisal(String kategoriJenisAppraisal) {
        this.kategoriJenisAppraisal = kategoriJenisAppraisal;
    }

    public String getJenisAppraisal() {
        return jenisAppraisal;
    }

    public void setJenisAppraisal(String jenisAppraisal) {
        this.jenisAppraisal = jenisAppraisal;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getLpaNumber() {
        return lpaNumber;
    }

    public void setLpaNumber(String lpaNumber) {
        this.lpaNumber = lpaNumber;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getNamaDebitur() {
        return namaDebitur;
    }

    public void setNamaDebitur(String namaDebitur) {
        this.namaDebitur = namaDebitur;
    }

    public String getJenisObjectPenilain() {
        return jenisObjectPenilain;
    }

    public void setJenisObjectPenilain(String jenisObjectPenilain) {
        this.jenisObjectPenilain = jenisObjectPenilain;
    }

    public String getNamaPerumahan() {
        return namaPerumahan;
    }

    public void setNamaPerumahan(String namaPerumahan) {
        this.namaPerumahan = namaPerumahan;
    }

    public String getNamaCluster() {
        return namaCluster;
    }

    public void setNamaCluster(String namaCluster) {
        this.namaCluster = namaCluster;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public String getRw() {
        return rw;
    }

    public void setRw(String rw) {
        this.rw = rw;
    }

    public String getKodePos() {
        return kodePos;
    }

    public void setKodePos(String kodePos) {
        this.kodePos = kodePos;
    }

    public String getPropinsi() {
        return propinsi;
    }

    public void setPropinsi(String propinsi) {
        this.propinsi = propinsi;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public Long getStatusJaminan() {
        return statusJaminan;
    }

    public void setStatusJaminan(Long statusJaminan) {
        this.statusJaminan = statusJaminan;
    }

    public Date getTanggalOrder() {
        return tanggalOrder;
    }

    public void setTanggalOrder(Date tanggalOrder) {
        this.tanggalOrder = tanggalOrder;
    }

    public String getKategoriSla() {
        return kategoriSla;
    }

    public void setKategoriSla(String kategoriSla) {
        this.kategoriSla = kategoriSla;
    }

    public String getDitujuke() {
        return ditujuke;
    }

    public void setDitujuke(String ditujuke) {
        this.ditujuke = ditujuke;
    }

    public Long getJenisAktiva() {
        return jenisAktiva;
    }

    public void setJenisAktiva(Long jenisAktiva) {
        this.jenisAktiva = jenisAktiva;
    }

    public String getJenisObjectPenilaiFisik() {
        return jenisObjectPenilaiFisik;
    }

    public void setJenisObjectPenilaiFisik(String jenisObjectPenilaiFisik) {
        this.jenisObjectPenilaiFisik = jenisObjectPenilaiFisik;
    }

    public Date getTanggalSurvey() {
        return tanggalSurvey;
    }

    public void setTanggalSurvey(Date tanggalSurvey) {
        this.tanggalSurvey = tanggalSurvey;
    }

    public String getDitinjau() {
        return ditinjau;
    }

    public void setDitinjau(String ditinjau) {
        this.ditinjau = ditinjau;
    }

    public String getDitemui() {
        return ditemui;
    }

    public void setDitemui(String ditemui) {
        this.ditemui = ditemui;
    }

    public Boolean getDraft() {
        return draft;
    }

    public void setDraft(Boolean draft) {
        this.draft = draft;
    }

    public String getFaktorMarketability() {
        return faktorMarketability;
    }

    public void setFaktorMarketability(String faktorMarketability) {
        this.faktorMarketability = faktorMarketability;
    }

    public String getFaktorMarketCatatan() {
        return faktorMarketCatatan;
    }

    public void setFaktorMarketCatatan(String faktorMarketCatatan) {
        this.faktorMarketCatatan = faktorMarketCatatan;
    }

    public List<Long> getListNegative() {
        return listNegative;
    }

    public void setListNegative(List<Long> listNegative) {
        this.listNegative = listNegative;
    }

    public List<Long> getListPertimbangan() {
        return listPertimbangan;
    }

    public void setListPertimbangan(List<Long> listPertimbangan) {
        this.listPertimbangan = listPertimbangan;
    }

    public String getPositifPoint() {
        return positifPoint;
    }

    public void setPositifPoint(String positifPoint) {
        this.positifPoint = positifPoint;
    }

    public String getCatatanPoint() {
        return catatanPoint;
    }

    public void setCatatanPoint(String catatanPoint) {
        this.catatanPoint = catatanPoint;
    }

    public String getPembanding1() {
        return pembanding1;
    }

    public void setPembanding1(String pembanding1) {
        this.pembanding1 = pembanding1;
    }

    public String getPembanding2() {
        return pembanding2;
    }

    public void setPembanding2(String pembanding2) {
        this.pembanding2 = pembanding2;
    }

    public String getPembanding3() {
        return pembanding3;
    }

    public void setPembanding3(String pembanding3) {
        this.pembanding3 = pembanding3;
    }

    public void addListNegative(Long id) {
        listNegative.add(id);
    }

    public void addListPertimbangan(Long id) {
        listPertimbangan.add(id);
    }

    public String getMataUang() {
        return mataUang;
    }

    public void setMataUang(String mataUang) {
        this.mataUang = mataUang;
    }

    public Date getTanggalRate() {
        return tanggalRate;
    }

    public void setTanggalRate(Date tanggalRate) {
        this.tanggalRate = tanggalRate;
    }

    public BigDecimal getNilaiRate() {
        return nilaiRate;
    }

    public void setNilaiRate(BigDecimal nilaiRate) {
        this.nilaiRate = nilaiRate;
    }

    public Date getTanggalPenilaian() {
        return tanggalPenilaian;
    }

    public void setTanggalPenilaian(Date tanggalPenilaian) {
        this.tanggalPenilaian = tanggalPenilaian;
    }

    public Date getTanggalOrderKeEksternal() {
        return tanggalOrderKeEksternal;
    }

    public void setTanggalOrderKeEksternal(Date tanggalOrderKeEksternal) {
        this.tanggalOrderKeEksternal = tanggalOrderKeEksternal;
    }

    public Date getTanggalTerimaDariEksternal() {
        return tanggalTerimaDariEksternal;
    }

    public void setTanggalTerimaDariEksternal(Date tanggalTerimaDariEksternal) {
        this.tanggalTerimaDariEksternal = tanggalTerimaDariEksternal;
    }

}