package com.permata.appraisal2.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class LpaRabDto extends LpaUmumDto  implements Serializable {

    private Long id;
    @NotNull private BigDecimal biayaDilampirkan;
    @NotNull private BigDecimal biayaTidakDiperhitungkan;
    @NotNull private BigDecimal biayaDiperhitungkan;
    @NotNull private Double luasBangungan;
    @NotNull private BigDecimal hargaBangunanRab;
    @NotNull private BigDecimal hargaBangunanInternal;
    @NotNull private BigDecimal nilaiReviewCustomer;
    @NotNull private BigDecimal nilaiReviewInternal;
    @NotNull private String kewajaranNilai;
    @NotNull private BigDecimal tidakDiperhitungkan1;
    private BigDecimal tidakDiperhitungkan2;
    private BigDecimal tidakDiperhitungkan3;
    private BigDecimal tidakDiperhitungkan4;
    private BigDecimal tidakDiperhitungkan5;
    private String tidakDiperhitungkan1txt;
    private String tidakDiperhitungkan2txt;
    private String tidakDiperhitungkan3txt;
    private String tidakDiperhitungkan4txt;
    private String tidakDiperhitungkan5txt;

    public String getTidakDiperhitungkan1txt() {
        return tidakDiperhitungkan1txt;
    }

    public void setTidakDiperhitungkan1txt(String tidakDiperhitungkan1txt) {
        this.tidakDiperhitungkan1txt = tidakDiperhitungkan1txt;
    }

    public String getTidakDiperhitungkan2txt() {
        return tidakDiperhitungkan2txt;
    }

    public void setTidakDiperhitungkan2txt(String tidakDiperhitungkan2txt) {
        this.tidakDiperhitungkan2txt = tidakDiperhitungkan2txt;
    }

    public String getTidakDiperhitungkan3txt() {
        return tidakDiperhitungkan3txt;
    }

    public void setTidakDiperhitungkan3txt(String tidakDiperhitungkan3txt) {
        this.tidakDiperhitungkan3txt = tidakDiperhitungkan3txt;
    }

    public String getTidakDiperhitungkan4txt() {
        return tidakDiperhitungkan4txt;
    }

    public void setTidakDiperhitungkan4txt(String tidakDiperhitungkan4txt) {
        this.tidakDiperhitungkan4txt = tidakDiperhitungkan4txt;
    }

    public String getTidakDiperhitungkan5txt() {
        return tidakDiperhitungkan5txt;
    }

    public void setTidakDiperhitungkan5txt(String tidakDiperhitungkan5txt) {
        this.tidakDiperhitungkan5txt = tidakDiperhitungkan5txt;
    }

    public BigDecimal getTidakDiperhitungkan1() {
        return tidakDiperhitungkan1;
    }

    public void setTidakDiperhitungkan1(BigDecimal tidakDiperhitungkan1) {
        this.tidakDiperhitungkan1 = tidakDiperhitungkan1;
    }

    public BigDecimal getTidakDiperhitungkan2() {
        return tidakDiperhitungkan2;
    }

    public void setTidakDiperhitungkan2(BigDecimal tidakDiperhitungkan2) {
        this.tidakDiperhitungkan2 = tidakDiperhitungkan2;
    }

    public BigDecimal getTidakDiperhitungkan3() {
        return tidakDiperhitungkan3;
    }

    public void setTidakDiperhitungkan3(BigDecimal tidakDiperhitungkan3) {
        this.tidakDiperhitungkan3 = tidakDiperhitungkan3;
    }

    public BigDecimal getTidakDiperhitungkan4() {
        return tidakDiperhitungkan4;
    }

    public void setTidakDiperhitungkan4(BigDecimal tidakDiperhitungkan4) {
        this.tidakDiperhitungkan4 = tidakDiperhitungkan4;
    }

    public BigDecimal getTidakDiperhitungkan5() {
        return tidakDiperhitungkan5;
    }

    public void setTidakDiperhitungkan5(BigDecimal tidakDiperhitungkan5) {
        this.tidakDiperhitungkan5 = tidakDiperhitungkan5;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBiayaDilampirkan() {
        return biayaDilampirkan;
    }

    public void setBiayaDilampirkan(BigDecimal biayaDilampirkan) {
        this.biayaDilampirkan = biayaDilampirkan;
    }

    public BigDecimal getBiayaTidakDiperhitungkan() {
        return biayaTidakDiperhitungkan;
    }

    public void setBiayaTidakDiperhitungkan(BigDecimal biayaTidakDiperhitungkan) {
        this.biayaTidakDiperhitungkan = biayaTidakDiperhitungkan;
    }

    public BigDecimal getBiayaDiperhitungkan() {
        return biayaDiperhitungkan;
    }

    public void setBiayaDiperhitungkan(BigDecimal biayaDiperhitungkan) {
        this.biayaDiperhitungkan = biayaDiperhitungkan;
    }

    public Double getLuasBangungan() {
        return luasBangungan;
    }

    public void setLuasBangungan(Double luasBangungan) {
        this.luasBangungan = luasBangungan;
    }

    public BigDecimal getHargaBangunanRab() {
        return hargaBangunanRab;
    }

    public void setHargaBangunanRab(BigDecimal hargaBangunanRab) {
        this.hargaBangunanRab = hargaBangunanRab;
    }

    public BigDecimal getHargaBangunanInternal() {
        return hargaBangunanInternal;
    }

    public void setHargaBangunanInternal(BigDecimal hargaBangunanInternal) {
        this.hargaBangunanInternal = hargaBangunanInternal;
    }

    public BigDecimal getNilaiReviewCustomer() {
        return nilaiReviewCustomer;
    }

    public void setNilaiReviewCustomer(BigDecimal nilaiReviewCustomer) {
        this.nilaiReviewCustomer = nilaiReviewCustomer;
    }

    public BigDecimal getNilaiReviewInternal() {
        return nilaiReviewInternal;
    }

    public void setNilaiReviewInternal(BigDecimal nilaiReviewInternal) {
        this.nilaiReviewInternal = nilaiReviewInternal;
    }

    public String getKewajaranNilai() {
        return kewajaranNilai;
    }

    public void setKewajaranNilai(String kewajaranNilai) {
        this.kewajaranNilai = kewajaranNilai;
    }
}
