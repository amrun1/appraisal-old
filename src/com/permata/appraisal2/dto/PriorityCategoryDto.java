package com.permata.appraisal2.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class PriorityCategoryDto  implements Serializable {

    private Long id;
    private String nama;
    private BigDecimal nilai;

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

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }
}