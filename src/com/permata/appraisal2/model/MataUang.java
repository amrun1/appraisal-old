package com.permata.appraisal2.model;

import com.permata.appraisal2.common.AppConstans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = AppConstans.HibernateConstanstan.PREFIX_DB_NAME + "MATA_UANG")
public class MataUang extends EntityBase<String> {

    private String code;
    private String negara;
    private String simbol;
    private BigDecimal rate;
    private Date tanggal;

    @Id
    @Column(name = "KODE", nullable = false)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "NEGARA", nullable = false)
    public String getNegara() {
        return negara;
    }

    public void setNegara(String negara) {
        this.negara = negara;
    }

    @Column(name = "SIMBOL")
    public String getSimbol() {
        return simbol;
    }

    public void setSimbol(String simbol) {
        this.simbol = simbol;
    }

    @Column(name = "NILAI_RATE")
    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Column(name = "TANGGAL_RATE")
    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
}