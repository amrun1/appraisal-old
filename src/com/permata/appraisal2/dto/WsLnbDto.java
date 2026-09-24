package com.permata.appraisal2.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class WsLnbDto  implements Serializable {

    private BigDecimal docTanah;
    private BigDecimal fisikTanah;
    private BigDecimal docBangunan;
    private BigDecimal fisikBangunan;
    private BigDecimal totalDoc;
    private BigDecimal totalFisik;
    private BigDecimal likuidasiDocTanah;
    private BigDecimal likuidasiFisikTanah;
    private BigDecimal likuidasiDocBangunan;
    private BigDecimal likuidasiFisikBangunan;
    private BigDecimal totalLikuidasiDoc;
    private BigDecimal totalLikuidasiFisik;

    private String dataPembanding1;
    private String dataPembanding2;
    private String dataPembanding3;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("WsLnbDto{");
        sb.append("docTanah=").append(docTanah);
        sb.append(", fisikTanah=").append(fisikTanah);
        sb.append(", docBangunan=").append(docBangunan);
        sb.append(", fisikBangunan=").append(fisikBangunan);
        sb.append(", totalDoc=").append(totalDoc);
        sb.append(", totalFisik=").append(totalFisik);
        sb.append(", likuidasiDocTanah=").append(likuidasiDocTanah);
        sb.append(", likuidasiFisikTanah=").append(likuidasiFisikTanah);
        sb.append(", likuidasiDocBangunan=").append(likuidasiDocBangunan);
        sb.append(", likuidasiFisikBangunan=").append(likuidasiFisikBangunan);
        sb.append(", totalLikuidasiDoc=").append(totalLikuidasiDoc);
        sb.append(", totalLikuidasiFisik=").append(totalLikuidasiFisik);
        sb.append(", dataPembanding1=").append(dataPembanding1);
        sb.append(", dataPembanding2=").append(dataPembanding2);
        sb.append(", dataPembanding3=").append(dataPembanding3);
        sb.append('}');
        return sb.toString();
    }

    public BigDecimal getTotalLikuidasiDoc() {
        return totalLikuidasiDoc;
    }

    public void setTotalLikuidasiDoc(BigDecimal totalLikuidasiDoc) {
        this.totalLikuidasiDoc = totalLikuidasiDoc;
    }

    public BigDecimal getTotalLikuidasiFisik() {
        return totalLikuidasiFisik;
    }

    public void setTotalLikuidasiFisik(BigDecimal totalLikuidasiFisik) {
        this.totalLikuidasiFisik = totalLikuidasiFisik;
    }

    public BigDecimal getTotalDoc() {
        return totalDoc;
    }

    public void setTotalDoc(BigDecimal totalDoc) {
        this.totalDoc = totalDoc;
    }

    public BigDecimal getTotalFisik() {
        return totalFisik;
    }

    public void setTotalFisik(BigDecimal totalFisik) {
        this.totalFisik = totalFisik;
    }

    public BigDecimal getDocTanah() {
        return docTanah;
    }

    public void setDocTanah(BigDecimal docTanah) {
        this.docTanah = docTanah;
    }

    public BigDecimal getFisikTanah() {
        return fisikTanah;
    }

    public void setFisikTanah(BigDecimal fisikTanah) {
        this.fisikTanah = fisikTanah;
    }

    public BigDecimal getDocBangunan() {
        return docBangunan;
    }

    public void setDocBangunan(BigDecimal docBangunan) {
        this.docBangunan = docBangunan;
    }

    public BigDecimal getFisikBangunan() {
        return fisikBangunan;
    }

    public void setFisikBangunan(BigDecimal fisikBangunan) {
        this.fisikBangunan = fisikBangunan;
    }

    public BigDecimal getLikuidasiDocTanah() {
        return likuidasiDocTanah;
    }

    public void setLikuidasiDocTanah(BigDecimal likuidasiDocTanah) {
        this.likuidasiDocTanah = likuidasiDocTanah;
    }

    public BigDecimal getLikuidasiFisikTanah() {
        return likuidasiFisikTanah;
    }

    public void setLikuidasiFisikTanah(BigDecimal likuidasiFisikTanah) {
        this.likuidasiFisikTanah = likuidasiFisikTanah;
    }

    public BigDecimal getLikuidasiDocBangunan() {
        return likuidasiDocBangunan;
    }

    public void setLikuidasiDocBangunan(BigDecimal likuidasiDocBangunan) {
        this.likuidasiDocBangunan = likuidasiDocBangunan;
    }

    public BigDecimal getLikuidasiFisikBangunan() {
        return likuidasiFisikBangunan;
    }

    public void setLikuidasiFisikBangunan(BigDecimal likuidasiFisikBangunan) {
        this.likuidasiFisikBangunan = likuidasiFisikBangunan;
    }

    public String getDataPembanding1() {
        return dataPembanding1;
    }

    public void setDataPembanding1(String dataPembanding1) {
        this.dataPembanding1 = dataPembanding1;
    }

    public String getDataPembanding2() {
        return dataPembanding2;
    }

    public void setDataPembanding2(String dataPembanding2) {
        this.dataPembanding2 = dataPembanding2;
    }

    public String getDataPembanding3() {
        return dataPembanding3;
    }

    public void setDataPembanding3(String dataPembanding3) {
        this.dataPembanding3 = dataPembanding3;
    }
}