package com.permata.appraisal2.model;

import com.permata.appraisal2.common.AppConstans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = AppConstans.HibernateConstanstan.PREFIX_DB_NAME + "TABLE_SEQUENCE")
public class TableSequence extends EntityBase<String> {

    private String code;
    private Integer value;

    public TableSequence() {
    }

    public TableSequence(String code) {
        this.code = code;
        this.value = 1;
    }

    @Id
    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "VALUE")
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}