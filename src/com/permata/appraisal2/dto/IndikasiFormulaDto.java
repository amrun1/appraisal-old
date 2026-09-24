package com.permata.appraisal2.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class IndikasiFormulaDto  implements Serializable {


    private Long code;
    private String type;
    private Double luas1;
    private Double luas2;
    private BigDecimal nilai;


    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getLuas1() {
        return luas1;
    }

    public void setLuas1(Double luas1) {
        this.luas1 = luas1;
    }

    public Double getLuas2() {
        return luas2;
    }

    public void setLuas2(Double luas2) {
        this.luas2 = luas2;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IndikasiFormulaDto{");
        sb.append("code=").append(code);
        sb.append(", type='").append(type).append('\'');
        sb.append(", luas1=").append(luas1);
        sb.append(", luas2=").append(luas2);
        sb.append(", nilai=").append(nilai);
        sb.append('}');
        return sb.toString();
    }
}
