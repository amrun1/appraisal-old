package com.permata.appraisal2.dto;

import com.permata.appraisal2.model.LpaItemInspeksi;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by SB on 4/17/2018.
 */
public class LpaInspeksiDto extends LpaUmumDto implements Serializable {
    private Long id;
    @NotNull String jenisBarang;
    private String nomorDaftar;
    private Date tanggalDaftar;
    @NotNull private String jenisTempat;
    @NotNull private String infoAlamatTempat;
    private String alamatLain;
    @NotNull private String statusTempat;
    @NotNull private String kondisiTempat;
    @NotNull private String aktifitasKerja;
    @NotNull private String peralatanGudang;
    @NotNull private String alatPemadam;
    @NotNull private String keamanan;
    @NotNull private String kondisiJalan;
    @NotNull private String lingkunganSekitar;
    @NotNull private String metodePeriksa;
    @NotNull private String tempatPersediaan;
    @NotNull private String susunanTempat;
    @NotNull private String packing;
    private String subTotalGoods;
    private String subTotalHalfGoods;
    private String subTotalRawMaterial;
    private String subTotalGoodsFisik;
    private String subTotalHalfGoodsFisik;
    private String subTotalRawMaterialFisik;
    private List<LpaItemInspeksiDto> lpaItemInspeksis = new ArrayList<LpaItemInspeksiDto>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJenisBarang() {
        return jenisBarang;
    }

    public void setJenisBarang(String jenisBarang) {
        this.jenisBarang = jenisBarang;
    }

    public String getNomorDaftar() {
        return nomorDaftar;
    }

    public void setNomorDaftar(String nomorDaftar) {
        this.nomorDaftar = nomorDaftar;
    }

    public Date getTanggalDaftar() {
        return tanggalDaftar;
    }

    public void setTanggalDaftar(Date tanggalDaftar) {
        this.tanggalDaftar = tanggalDaftar;
    }

    public String getJenisTempat() {
        return jenisTempat;
    }

    public void setJenisTempat(String jenisTempat) {
        this.jenisTempat = jenisTempat;
    }

    public String getInfoAlamatTempat() {
        return infoAlamatTempat;
    }

    public void setInfoAlamatTempat(String infoAlamatTempat) {
        this.infoAlamatTempat = infoAlamatTempat;
    }

    public String getAlamatLain() {
        return alamatLain;
    }

    public void setAlamatLain(String alamatLain) {
        this.alamatLain = alamatLain;
    }

    public String getStatusTempat() {
        return statusTempat;
    }

    public void setStatusTempat(String statusTempat) {
        this.statusTempat = statusTempat;
    }

    public String getKondisiTempat() {
        return kondisiTempat;
    }

    public void setKondisiTempat(String kondisiTempat) {
        this.kondisiTempat = kondisiTempat;
    }

    public String getAktifitasKerja() {
        return aktifitasKerja;
    }

    public void setAktifitasKerja(String aktifitasKerja) {
        this.aktifitasKerja = aktifitasKerja;
    }

    public String getPeralatanGudang() {
        return peralatanGudang;
    }

    public void setPeralatanGudang(String peralatanGudang) {
        this.peralatanGudang = peralatanGudang;
    }

    public String getAlatPemadam() {
        return alatPemadam;
    }

    public void setAlatPemadam(String alatPemadam) {
        this.alatPemadam = alatPemadam;
    }

    public String getKeamanan() {
        return keamanan;
    }

    public void setKeamanan(String keamanan) {
        this.keamanan = keamanan;
    }

    public String getKondisiJalan() {
        return kondisiJalan;
    }

    public void setKondisiJalan(String kondisiJalan) {
        this.kondisiJalan = kondisiJalan;
    }

    public String getLingkunganSekitar() {
        return lingkunganSekitar;
    }

    public void setLingkunganSekitar(String lingkunganSekitar) {
        this.lingkunganSekitar = lingkunganSekitar;
    }

    public String getMetodePeriksa() {
        return metodePeriksa;
    }

    public void setMetodePeriksa(String metodePeriksa) {
        this.metodePeriksa = metodePeriksa;
    }

    public String getTempatPersediaan() {
        return tempatPersediaan;
    }

    public void setTempatPersediaan(String tempatPersediaan) {
        this.tempatPersediaan = tempatPersediaan;
    }

    public String getSusunanTempat() {
        return susunanTempat;
    }

    public void setSusunanTempat(String susunanTempat) {
        this.susunanTempat = susunanTempat;
    }

    public String getPacking() {
        return packing;
    }

    public void setPacking(String packing) {
        this.packing = packing;
    }

    public String getSubTotalGoods() {
        return subTotalGoods;
    }

    public void setSubTotalGoods(String subTotalGoods) {
        this.subTotalGoods = subTotalGoods;
    }

    public String getSubTotalHalfGoods() {
        return subTotalHalfGoods;
    }

    public void setSubTotalHalfGoods(String subTotalHalfGoods) {
        this.subTotalHalfGoods = subTotalHalfGoods;
    }

    public String getSubTotalRawMaterial() {
        return subTotalRawMaterial;
    }

    public void setSubTotalRawMaterial(String subTotalRawMaterial) {
        this.subTotalRawMaterial = subTotalRawMaterial;
    }

    public List<LpaItemInspeksiDto> getLpaItemInspeksis() {
        return lpaItemInspeksis;
    }

    public void setLpaItemInspeksis(List<LpaItemInspeksiDto> lpaItemInspeksis) {
        this.lpaItemInspeksis = lpaItemInspeksis;
    }

    public void
    ItemInspeksi(LpaItemInspeksiDto item) {
        lpaItemInspeksis.add(item);
    }

    public String getSubTotalGoodsFisik() {
        return subTotalGoodsFisik;
    }

    public void setSubTotalGoodsFisik(String subTotalGoodsFisik) {
        this.subTotalGoodsFisik = subTotalGoodsFisik;
    }

    public String getSubTotalHalfGoodsFisik() {
        return subTotalHalfGoodsFisik;
    }

    public void setSubTotalHalfGoodsFisik(String subTotalHalfGoodsFisik) {
        this.subTotalHalfGoodsFisik = subTotalHalfGoodsFisik;
    }

    public String getSubTotalRawMaterialFisik() {
        return subTotalRawMaterialFisik;
    }

    public void setSubTotalRawMaterialFisik(String subTotalRawMaterialFisik) {
        this.subTotalRawMaterialFisik = subTotalRawMaterialFisik;
    }
}
