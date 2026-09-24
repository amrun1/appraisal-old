package com.permata.appraisal2.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Abdul Rochman on 4/25/2017.
 */
public class TypeObjectViewDto  implements Serializable {
    private String jenisaktiva;
    private String jenislpa;
    private Integer count;
    private Integer countinternal;
    private Integer counteksternal;
    private Integer countindikasi;
    private String grouptl;
    private Date closeddate;

    public String getJenisaktiva() {
        return jenisaktiva;
    }

    public void setJenisaktiva(String jenisaktiva) {
        this.jenisaktiva = jenisaktiva;
    }

    public String getJenislpa() {
        return jenislpa;
    }

    public void setJenislpa(String jenislpa) {
        this.jenislpa = jenislpa;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCountinternal() {
        return countinternal;
    }

    public void setCountinternal(Integer countinternal) {
        this.countinternal = countinternal;
    }

    public Integer getCounteksternal() {
        return counteksternal;
    }

    public void setCounteksternal(Integer counteksternal) {
        this.counteksternal = counteksternal;
    }

    public Integer getCountindikasi() {
        return countindikasi;
    }

    public void setCountindikasi(Integer countindikasi) {
        this.countindikasi = countindikasi;
    }

    public String getGrouptl() {
        return grouptl;
    }

    public void setGrouptl(String grouptl) {
        this.grouptl = grouptl;
    }

    public Date getCloseddate() {
        return closeddate;
    }

    public void setCloseddate(Date closeddate) {
        this.closeddate = closeddate;
    }
}
