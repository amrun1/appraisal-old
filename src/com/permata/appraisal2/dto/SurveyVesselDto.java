package com.permata.appraisal2.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SurveyVesselDto extends SurveyCommonDto  implements Serializable {

    private Long id;
    private Long detailId;
    private Long vesselId;
    private Long idCommon;

    private String nama;
    private String pemilik;
    private String kebangsaan;
    private String selar;
    private String galangan;
    private String kelas;
    private Integer tahun;
    private String manufaktur;
    private Double panjang;
    private Double lebar;
    private Double tinggi;
    private Double isiKotor;
    private Double isiBersih;
    private String statusAgunan;
    private String statusJaminanKapal;
    private String kondisiUmum;

    // data Mesin LpaKapal
    private String mesinMerk1;
    private String mesinMerk2;
    private String mesinMerk3;
    private String mesinNomor1;
    private String mesinNomor2;
    private String mesinNomor3;
    private String mesinKapasitas1;
    private String mesinKapasitas2;
    private String mesinKapasitas3;
    private String mesinBackupMerk1;
    private String mesinBackupMerk2;
    private String mesinBackupNomor1;
    private String mesinBackupNomor2;
    private String mesinBackupKapasitas1;
    private String mesinBackupKapasitas2;

    private Long idPoint;
    private String positif;
    private String catatanPoint;
    private List<Long> listNegatif = new ArrayList<Long>();
    private List<Long> listPertimbangan = new ArrayList<Long>();

    public SurveyVesselDto() {

    }

    public Long getIdCommon() {
        return idCommon;
    }

    public void setIdCommon(Long idCommon) {
        this.idCommon = idCommon;
    }

    public Long getIdPoint() {
        return idPoint;
    }

    public void setIdPoint(Long idPoint) {
        this.idPoint = idPoint;
    }

    public Long getVesselId() {
        return vesselId;
    }

    public void setVesselId(Long vesselId) {
        this.vesselId = vesselId;
    }

    public SurveyVesselDto(Long detailId) {
        this.detailId = detailId;
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPemilik() {
        return pemilik;
    }

    public void setPemilik(String pemilik) {
        this.pemilik = pemilik;
    }

    public String getKebangsaan() {
        return kebangsaan;
    }

    public void setKebangsaan(String kebangsaan) {
        this.kebangsaan = kebangsaan;
    }

    public String getSelar() {
        return selar;
    }

    public void setSelar(String selar) {
        this.selar = selar;
    }

    public String getGalangan() {
        return galangan;
    }

    public void setGalangan(String galangan) {
        this.galangan = galangan;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public Integer getTahun() {
        return tahun;
    }

    public void setTahun(Integer tahun) {
        this.tahun = tahun;
    }

    public String getManufaktur() {
        return manufaktur;
    }

    public void setManufaktur(String manufaktur) {
        this.manufaktur = manufaktur;
    }

    public Double getPanjang() {
        return panjang;
    }

    public void setPanjang(Double panjang) {
        this.panjang = panjang;
    }

    public Double getLebar() {
        return lebar;
    }

    public void setLebar(Double lebar) {
        this.lebar = lebar;
    }

    public Double getTinggi() {
        return tinggi;
    }

    public void setTinggi(Double tinggi) {
        this.tinggi = tinggi;
    }

    public Double getIsiKotor() {
        return isiKotor;
    }

    public void setIsiKotor(Double isiKotor) {
        this.isiKotor = isiKotor;
    }

    public Double getIsiBersih() {
        return isiBersih;
    }

    public void setIsiBersih(Double isiBersih) {
        this.isiBersih = isiBersih;
    }

    public String getStatusAgunan() {
        return statusAgunan;
    }

    public void setStatusAgunan(String statusAgunan) {
        this.statusAgunan = statusAgunan;
    }

    public String getKondisiUmum() {
        return kondisiUmum;
    }

    public void setKondisiUmum(String kondisiUmum) {
        this.kondisiUmum = kondisiUmum;
    }

    public String getMesinMerk1() {
        return mesinMerk1;
    }

    public void setMesinMerk1(String mesinMerk1) {
        this.mesinMerk1 = mesinMerk1;
    }

    public String getMesinMerk2() {
        return mesinMerk2;
    }

    public void setMesinMerk2(String mesinMerk2) {
        this.mesinMerk2 = mesinMerk2;
    }

    public String getMesinMerk3() {
        return mesinMerk3;
    }

    public void setMesinMerk3(String mesinMerk3) {
        this.mesinMerk3 = mesinMerk3;
    }

    public String getMesinNomor1() {
        return mesinNomor1;
    }

    public void setMesinNomor1(String mesinNomor1) {
        this.mesinNomor1 = mesinNomor1;
    }

    public String getMesinNomor2() {
        return mesinNomor2;
    }

    public void setMesinNomor2(String mesinNomor2) {
        this.mesinNomor2 = mesinNomor2;
    }

    public String getMesinNomor3() {
        return mesinNomor3;
    }

    public void setMesinNomor3(String mesinNomor3) {
        this.mesinNomor3 = mesinNomor3;
    }

    public String getMesinKapasitas1() {
        return mesinKapasitas1;
    }

    public void setMesinKapasitas1(String mesinKapasitas1) {
        this.mesinKapasitas1 = mesinKapasitas1;
    }

    public String getMesinKapasitas2() {
        return mesinKapasitas2;
    }

    public void setMesinKapasitas2(String mesinKapasitas2) {
        this.mesinKapasitas2 = mesinKapasitas2;
    }

    public String getMesinKapasitas3() {
        return mesinKapasitas3;
    }

    public void setMesinKapasitas3(String mesinKapasitas3) {
        this.mesinKapasitas3 = mesinKapasitas3;
    }

    public String getMesinBackupMerk1() {
        return mesinBackupMerk1;
    }

    public void setMesinBackupMerk1(String mesinBackupMerk1) {
        this.mesinBackupMerk1 = mesinBackupMerk1;
    }

    public String getMesinBackupMerk2() {
        return mesinBackupMerk2;
    }

    public void setMesinBackupMerk2(String mesinBackupMerk2) {
        this.mesinBackupMerk2 = mesinBackupMerk2;
    }

    public String getMesinBackupNomor1() {
        return mesinBackupNomor1;
    }

    public void setMesinBackupNomor1(String mesinBackupNomor1) {
        this.mesinBackupNomor1 = mesinBackupNomor1;
    }

    public String getMesinBackupNomor2() {
        return mesinBackupNomor2;
    }

    public void setMesinBackupNomor2(String mesinBackupNomor2) {
        this.mesinBackupNomor2 = mesinBackupNomor2;
    }

    public String getMesinBackupKapasitas1() {
        return mesinBackupKapasitas1;
    }

    public void setMesinBackupKapasitas1(String mesinBackupKapasitas1) {
        this.mesinBackupKapasitas1 = mesinBackupKapasitas1;
    }

    public String getMesinBackupKapasitas2() {
        return mesinBackupKapasitas2;
    }

    public void setMesinBackupKapasitas2(String mesinBackupKapasitas2) {
        this.mesinBackupKapasitas2 = mesinBackupKapasitas2;
    }

    public String getPositif() {
        return positif;
    }

    public void setPositif(String positif) {
        this.positif = positif;
    }

    public String getCatatanPoint() {
        return catatanPoint;
    }

    public void setCatatanPoint(String catatanPoint) {
        this.catatanPoint = catatanPoint;
    }

    public List<Long> getListNegatif() {
        return listNegatif;
    }

    public void setListNegatif(List<Long> listNegatif) {
        this.listNegatif = listNegatif;
    }

    public List<Long> getListPertimbangan() {
        return listPertimbangan;
    }

    public void setListPertimbangan(List<Long> listPertimbangan) {
        this.listPertimbangan = listPertimbangan;
    }

    public String getStatusJaminanKapal() {
        return statusJaminanKapal;
    }

    public void setStatusJaminanKapal(String statusJaminanKapal) {
        this.statusJaminanKapal = statusJaminanKapal;
    }

    public void addNegatif(Long id){
        listNegatif.add(id);
    }

    public void addPertimbangan(Long id){
        listPertimbangan.add(id);
    }
}