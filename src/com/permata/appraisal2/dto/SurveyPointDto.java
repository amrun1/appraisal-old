package com.permata.appraisal2.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SurveyPointDto  implements Serializable {

    private Long id;
    private Long detailId;
    private String category;
    private String positif;
    private String catatan;
    private List<Long> listNegatif = new ArrayList<Long>();
    private List<Long> listPertimbangan = new ArrayList<Long>();

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Long> getListPertimbangan() {
        return listPertimbangan;
    }

    public void setListPertimbangan(List<Long> listPertimbangan) {
        this.listPertimbangan = listPertimbangan;
    }

    public SurveyPointDto() {
    }

    public SurveyPointDto(Long detailId) {
        this.detailId = detailId;
    }

    public SurveyPointDto(Long detailId, String category) {
        this.detailId = detailId;
        this.category = category;
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

    public String getPositif() {
        return positif;
    }

    public void setPositif(String positif) {
        this.positif = positif;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public List<Long> getListNegatif() {
        return listNegatif;
    }

    public void setListNegatif(List<Long> listNegatif) {
        this.listNegatif = listNegatif;
    }
}