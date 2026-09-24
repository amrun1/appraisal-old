package com.permata.appraisal2.dto;

import java.io.Serializable;
import java.util.Date;

public class SendtoSourceLogDto  implements Serializable {


    private Long id;
    private Long idCommon;
    private String jenis;
    private String source;
    private String alamat;
    private String response;
    private Date tanggal;
    private int status;
    private String message;

    //
    private String noOrder;
    private String plusId;
    private String jenisAppraisal;

    private String fortmatDate;

    public String getFortmatDate() {
        return fortmatDate;
    }

    public void setFortmatDate(String fortmatDate) {
        this.fortmatDate = fortmatDate;
    }

    public String getNoOrder() {
        return noOrder;
    }

    public void setNoOrder(String noOrder) {
        this.noOrder = noOrder;
    }

    public String getPlusId() {
        return plusId;
    }

    public void setPlusId(String plusId) {
        this.plusId = plusId;
    }

    public String getJenisAppraisal() {
        return jenisAppraisal;
    }

    public void setJenisAppraisal(String jenisAppraisal) {
        this.jenisAppraisal = jenisAppraisal;
    }

    public Long getIdCommon() {
        return idCommon;
    }

    public void setIdCommon(Long idCommon) {
        this.idCommon = idCommon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SendtoSourceLogDto{");
        sb.append("id=").append(id);
        sb.append(", idCommon=").append(idCommon);
        sb.append(", jenis='").append(jenis).append('\'');
        sb.append(", source='").append(source).append('\'');
        sb.append(", alamat='").append(alamat).append('\'');
        sb.append(", response='").append(response).append('\'');
        sb.append(", tanggal=").append(tanggal);
        sb.append(", status=").append(status);
        sb.append(", message='").append(message).append('\'');
        sb.append(", noOrder='").append(noOrder).append('\'');
        sb.append(", plusId='").append(plusId).append('\'');
        sb.append(", jenisAppraisal='").append(jenisAppraisal).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
