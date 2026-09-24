package com.permata.appraisal2.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DesktopNilaiBangunanDto implements Serializable {

    private Long id;
    private String nama;
    private BigDecimal harga;
    private Date modifiedDate;

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

    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}