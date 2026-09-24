package com.permata.appraisal2.model;

import com.permata.appraisal2.common.AppConstans;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = AppConstans.HibernateConstanstan.PREFIX_DB_NAME + "INDIKASI_FORMULA")
public class IndikasiFormula extends EntityBase<Long> {

    private Long code;
    private String type;
    private Double luas1;
    private Double luas2;
    private BigDecimal nilai;

    @Id
    @Column (name = "CODE", nullable = false, length = 30)
    @GeneratedValue(generator = "INDIKASI_SEQ", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "INDIKASI_SEQ", sequenceName = "INDIKASI_SEQ", initialValue = 1, allocationSize = 1)
    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }


    @Column(name = "TYPE", nullable = false)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "LUAS_1", nullable = false)
    public Double getLuas1() {
        return luas1;
    }

    public void setLuas1(Double luas1) {
        this.luas1 = luas1;
    }

    @Column(name = "LUAS_2", nullable = false)
    public Double getLuas2() {
        return luas2;
    }

    public void setLuas2(Double luas2) {
        this.luas2 = luas2;
    }


    @Column(name = "NILAI", nullable = false)
    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IndikasiFormula{");
        sb.append("code=").append(code);
        sb.append(", type='").append(type).append('\'');
        sb.append(", luas1=").append(luas1);
        sb.append(", luas2=").append(luas2);
        sb.append(", nilai=").append(nilai);
        sb.append('}');
        return sb.toString();
    }
}
