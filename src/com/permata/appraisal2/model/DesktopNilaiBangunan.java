package com.permata.appraisal2.model;

import com.permata.appraisal2.common.AppConstans;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = AppConstans.HibernateConstanstan.PREFIX_DB_NAME + "NILAI_BANGUNAN")
public class DesktopNilaiBangunan extends EntityAuditrailBase<Long> {

    private Long id;
    private String nama;
    private BigDecimal harga;

    @Id
    @GeneratedValue(generator = "NILAI_BANGUNAN_SEQ", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "NILAI_BANGUNAN_SEQ", sequenceName = "NILAI_BANGUNAN_SEQ", initialValue = 1, allocationSize = 1)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAMA_BANGUNAN", length = 100)
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @Column(name = "NILAI_BANGUNAN")
    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }
}
