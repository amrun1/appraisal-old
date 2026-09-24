package com.permata.appraisal2.model;

import com.permata.appraisal2.common.AppConstans;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = AppConstans.HibernateConstanstan.PREFIX_DB_NAME + "HOLIDAY")
public class Holiday extends EntityAuditrailBase<Long> {

    private Long id;
    private Date tanggal;
    private String keterangan;

    @Id
    @GeneratedValue(generator = "HOLIDAY_SEQ", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "HOLIDAY_SEQ", sequenceName = "HOLIDAY_SEQ", initialValue = 1, allocationSize = 1)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "TANGGAL", unique = true)
    @Temporal(TemporalType.DATE)
    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    @Column(name = "KETERANGAN")
    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}