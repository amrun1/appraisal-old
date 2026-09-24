package com.permata.appraisal2.model;

import com.permata.appraisal2.common.AppConstans;

import javax.persistence.*;

@Entity
@Table(name = AppConstans.HibernateConstanstan.PREFIX_DB_NAME + "KODE_POS")
public class ZipCode extends EntityBase<Long> {

    private Long id;
    private String code;
    private String province;
    private String city;
    private String district;
    private String subDistrict;
    private String picName;

    @Id
    @GeneratedValue(generator = "KODE_POS_SEQ", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "KODE_POS_SEQ", sequenceName = "KODE_POS_SEQ", initialValue = 1, allocationSize = 1)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "KODE", length = 8)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "PROPINSI", nullable = false, length = 50)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Column(name = "KOTA", nullable = false, length = 50)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "KECAMATAN", nullable = false, length = 50)
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Column(name = "KELURAHAN", nullable = false, length = 50)
    public String getSubDistrict() {
        return subDistrict;
    }

    public void setSubDistrict(String subDistrict) {
        this.subDistrict = subDistrict;
    }

    @Column(name = "NAMA_PIC", nullable =true, length = 50)
    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }
}