package com.permata.appraisal2.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class DesktopNilaiTanahXls implements Serializable {

    private Integer no;
    private String nama;
    private String jalan;
    private String posisi;
    private String kelurahan;
    private String kecamatan;
    private String kota;
    private String propinsi;
    private String periode;
    private BigDecimal harga;

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJalan() {
        return jalan;
    }

    public void setJalan(String jalan) {
        this.jalan = jalan;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getPropinsi() {
        return propinsi;
    }

    public void setPropinsi(String propinsi) {
        this.propinsi = propinsi;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DesktopNilaiTanahXls{");
        sb.append("no=").append(no);
        sb.append(", nama='").append(nama).append('\'');
        sb.append(", jalan='").append(jalan).append('\'');
        sb.append(", posisi='").append(posisi).append('\'');
        sb.append(", kelurahan='").append(kelurahan).append('\'');
        sb.append(", kecamatan='").append(kecamatan).append('\'');
        sb.append(", kota='").append(kota).append('\'');
        sb.append(", propinsi='").append(propinsi).append('\'');
        sb.append(", periode='").append(periode).append('\'');
        sb.append(", harga=").append(harga);
        sb.append('}');
        return sb.toString();
    }
}