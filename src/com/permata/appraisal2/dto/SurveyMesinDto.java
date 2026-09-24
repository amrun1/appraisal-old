package com.permata.appraisal2.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SurveyMesinDto extends SurveyCommonDto  implements Serializable {

    private Long idCommon;
    private Long idMesin;
    private String tempat;
    private String kondisi;
    private String lingkungan;
    private String teknisi;
    private String cukuCadang;
    private String catatanMesin;
    private Integer jumlahUnit;

    private Long idPoint;
    private String category;
    private String positif;
    private String catatanPoint;
    private List<Long> listNegatif = new ArrayList<Long>();
    private List<Long> listPertimbangan = new ArrayList<Long>();

    public Long getIdCommon() {
        return idCommon;
    }

    public void setIdCommon(Long idCommon) {
        this.idCommon = idCommon;
    }

    public Integer getJumlahUnit() {
        return jumlahUnit;
    }

    public void setJumlahUnit(Integer jumlahUnit) {
        this.jumlahUnit = jumlahUnit;
    }

    public void addNegatif(Long idNegatif){
        listNegatif.add(idNegatif);
    }

    public void addPertimbangan(Long idPertimbangan){
        getListPertimbangan().add(idPertimbangan);
    }

    public Long getIdPoint() {
        return idPoint;
    }

    public void setIdPoint(Long idPoint) {
        this.idPoint = idPoint;
    }

    public Long getIdMesin() {
        return idMesin;
    }

    public void setIdMesin(Long idMesin) {
        this.idMesin = idMesin;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getKondisi() {
        return kondisi;
    }

    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }

    public String getLingkungan() {
        return lingkungan;
    }

    public void setLingkungan(String lingkungan) {
        this.lingkungan = lingkungan;
    }

    public String getTeknisi() {
        return teknisi;
    }

    public void setTeknisi(String teknisi) {
        this.teknisi = teknisi;
    }

    public String getCukuCadang() {
        return cukuCadang;
    }

    public void setCukuCadang(String cukuCadang) {
        this.cukuCadang = cukuCadang;
    }

    public String getCatatanMesin() {
        return catatanMesin;
    }

    public void setCatatanMesin(String catatanMesin) {
        this.catatanMesin = catatanMesin;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SurveyMesinDto{");
        sb.append("idMesin=").append(idMesin);
        sb.append(", tempat='").append(tempat).append('\'');
        sb.append(", kondisi='").append(kondisi).append('\'');
        sb.append(", lingkungan='").append(lingkungan).append('\'');
        sb.append(", teknisi='").append(teknisi).append('\'');
        sb.append(", cukuCadang='").append(cukuCadang).append('\'');
        sb.append(", catatanMesin='").append(catatanMesin).append('\'');
        sb.append(", jumlahUnit=").append(jumlahUnit);
        sb.append(", idPoint=").append(idPoint);
        sb.append(", category='").append(category).append('\'');
        sb.append(", positif='").append(positif).append('\'');
        sb.append(", catatanPoint='").append(catatanPoint).append('\'');
        sb.append(", listNegatif=").append(listNegatif);
        sb.append(", listPertimbangan=").append(listPertimbangan);
        sb.append('}');
        return sb.toString();
    }
}