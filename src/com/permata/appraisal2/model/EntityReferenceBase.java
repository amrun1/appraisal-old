package com.permata.appraisal2.model;

import com.permata.appraisal2.dao.common.SoftDeleteSupport;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class EntityReferenceBase<ID extends Serializable> extends EntityAuditrailBase<ID> implements SoftDeleteSupport {

    private Integer deleted;

    @Override
    @Column(name = "DELETED")
    public Integer getDeleted() {
        return deleted;
    }

    @Override
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}