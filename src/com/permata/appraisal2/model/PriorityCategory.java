package com.permata.appraisal2.model;

import com.permata.appraisal2.common.AppConstans;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = AppConstans.HibernateConstanstan.PREFIX_DB_NAME + "PRIORITY_CATEGORY")
public class PriorityCategory extends EntityBase<Long> {

    private Long id;
    private String nama;
    private BigDecimal nilai;

    @Id
    @GeneratedValue(generator = "PRIORITY_CATEGORY_SEQ", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "PRIORITY_CATEGORY_SEQ", sequenceName = "PRIORITY_CATEGORY_SEQ", initialValue = 1, allocationSize = 1)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAMA", length = 50)
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @Column(name = "NILAI")
    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }
}