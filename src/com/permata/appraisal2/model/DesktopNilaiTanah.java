package com.permata.appraisal2.model;


import com.permata.appraisal2.common.AppConstans;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = AppConstans.HibernateConstanstan.PREFIX_DB_NAME + "NILAI_TANAH")
public class DesktopNilaiTanah extends EntityAuditrailBase<Long> {

    private Long id;
    private String nama;
    private String jalan;
    private String posisi;
    private String kelurahan;
    private String kecamatan;
    private String kota;
    private String propinsi;
    private String periode;
    private BigDecimal harga;

    @Id
    @GeneratedValue(generator = "NILAI_TANAH_SEQ", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "NILAI_TANAH_SEQ", sequenceName = "NILAI_TANAH_SEQ", initialValue = 1, allocationSize = 1)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAMA_TEMPAT", length = 100)
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @Column(name = "NAMA_JALAN", length = 100)
    public String getJalan() {
        return jalan;
    }

    public void setJalan(String jalan) {
        this.jalan = jalan;
    }

    @Column(name = "POSISI", length = 50)
    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    @Column(name = "NILAI_TANAH")
    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }

    @Column(name = "KELURAHAN", length = 50)
    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    @Column(name = "KECAMATAN", length = 50)
    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    @Column(name = "KOTA", length = 50)
    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    @Column(name = "PROPINSI", length = 50)
    public String getPropinsi() {
        return propinsi;
    }

    public void setPropinsi(String propinsi) {
        this.propinsi = propinsi;
    }

    @Column(name = "PERIODE", length = 50)
    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }
}