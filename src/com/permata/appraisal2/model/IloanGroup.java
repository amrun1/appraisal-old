package com.permata.appraisal2.model;

import com.permata.appraisal2.common.AppConstans;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Immutable
@Table(name = AppConstans.HibernateConstanstan.PREFIX_DB_NAME + "ILOAN_GROUP")
public class IloanGroup {

    private Long id;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}