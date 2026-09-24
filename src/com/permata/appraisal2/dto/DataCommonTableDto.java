package com.permata.appraisal2.dto;

import java.io.Serializable;

public class DataCommonTableDto  implements Serializable {

    private Long id;
    private String noOrder;
    private String noLaporan;
    private String namaDebitur;
    private String lokasi;
    private String kelurahan;
    private String kecamatan;
    private String kota;
    private String propinsi;
    private String cabangBisnis;
    private String cabangAppraisal;
    private String posisi;
    private String noHp;
    private String pesanTerakhir;
    private String asalPesan;
    private String kategoriAppraisal;
    private String jenisAppraisal;
    private String kategoriAktiva;
    private String jenisAktiva;
    private String jenisLaporan;
    private String userId;
    private String namaMarketing;
    private String sourceId;

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCabangAppraisal() {
        return cabangAppraisal;
    }

    public void setCabangAppraisal(String cabangAppraisal) {
        this.cabangAppraisal = cabangAppraisal;
    }

    public String getKategoriAktiva() {
        return kategoriAktiva;
    }

    public void setKategoriAktiva(String kategoriAktiva) {
        this.kategoriAktiva = kategoriAktiva;
    }

    public String getJenisAktiva() {
        return jenisAktiva;
    }

    public void setJenisAktiva(String jenisAktiva) {
        this.jenisAktiva = jenisAktiva;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoOrder() {
        return noOrder;
    }

    public void setNoOrder(String noOrder) {
        this.noOrder = noOrder;
    }

    public String getNoLaporan() {
        return noLaporan;
    }

    public void setNoLaporan(String noLaporan) {
        this.noLaporan = noLaporan;
    }

    public String getNamaDebitur() {
        return namaDebitur;
    }

    public void setNamaDebitur(String namaDebitur) {
        this.namaDebitur = namaDebitur;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getPropinsi() {
        return propinsi;
    }

    public void setPropinsi(String propinsi) {
        this.propinsi = propinsi;
    }

    public String getNamaMarketing() {
        return namaMarketing;
    }

    public void setNamaMarketing(String namaMarketing) {
        this.namaMarketing = namaMarketing;
    }

    public String getCabangBisnis() {
        return cabangBisnis;
    }

    public void setCabangBisnis(String cabangBisnis) {
        this.cabangBisnis = cabangBisnis;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getPesanTerakhir() {
        return pesanTerakhir;
    }

    public void setPesanTerakhir(String pesanTerakhir) {
        this.pesanTerakhir = pesanTerakhir;
    }

    public String getAsalPesan() {
        return asalPesan;
    }

    public void setAsalPesan(String asalPesan) {
        this.asalPesan = asalPesan;
    }

    public String getJenisAppraisal() {
        return jenisAppraisal;
    }

    public void setJenisAppraisal(String jenisAppraisal) {
        this.jenisAppraisal = jenisAppraisal;
    }

    public String getJenisLaporan() {
        return jenisLaporan;
    }

    public void setJenisLaporan(String jenisLaporan) {
        this.jenisLaporan = jenisLaporan;
    }

    public String getKategoriAppraisal() {
        return kategoriAppraisal;
    }

    public void setKategoriAppraisal(String kategoriAppraisal) {
        this.kategoriAppraisal = kategoriAppraisal;
    }
}