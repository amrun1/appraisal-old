package com.permata.appraisal2.dto;

import java.io.Serializable;

public class SurveyLingkunganDto  implements Serializable {

    private Long id;
    private Long detailId;
    private String sekitar;
    private String kepadatan;
    private String kemacetan;
    private String rencanaJalan;
    private Integer lebarJalan;
    private Integer gsb;
    private Integer gss;
    private String roda4;

    public SurveyLingkunganDto() {
    }

    public SurveyLingkunganDto(Long detailId) {
        this.detailId = detailId;
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

    public String getSekitar() {
        return sekitar;
    }

    public void setSekitar(String sekitar) {
        this.sekitar = sekitar;
    }

    public String getKepadatan() {
        return kepadatan;
    }

    public void setKepadatan(String kepadatan) {
        this.kepadatan = kepadatan;
    }

    public String getKemacetan() {
        return kemacetan;
    }

    public void setKemacetan(String kemacetan) {
        this.kemacetan = kemacetan;
    }

    public String getRencanaJalan() {
        return rencanaJalan;
    }

    public void setRencanaJalan(String rencanaJalan) {
        this.rencanaJalan = rencanaJalan;
    }

    public Integer getLebarJalan() {
        return lebarJalan;
    }

    public void setLebarJalan(Integer lebarJalan) {
        this.lebarJalan = lebarJalan;
    }

    public Integer getGsb() {
        return gsb;
    }

    public void setGsb(Integer gsb) {
        this.gsb = gsb;
    }

    public Integer getGss() {
        return gss;
    }

    public void setGss(Integer gss) {
        this.gss = gss;
    }

    public String getRoda4() {
        return roda4;
    }

    public void setRoda4(String roda4) {
        this.roda4 = roda4;
    }
}
