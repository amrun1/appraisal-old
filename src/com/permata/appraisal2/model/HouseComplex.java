package com.permata.appraisal2.model;

import com.permata.appraisal2.common.AppConstans;

import javax.persistence.*;

@Entity
@Table(name = AppConstans.HibernateConstanstan.PREFIX_DB_NAME + "KOMPLEK")
public class HouseComplex extends EntityAuditrailBase<Long> {

    private static final long serialVersionUID = 925117423356046390L;
    private Long id;
    private String name;
    private String address;

    @Id
    @GeneratedValue(generator = "KOMPLEK_SEQ", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "KOMPLEK_SEQ", sequenceName = "KOMPLEK_SEQ", initialValue = 1, allocationSize = 1)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAMA", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "ALAMAT")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}