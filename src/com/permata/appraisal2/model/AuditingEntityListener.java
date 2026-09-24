package com.permata.appraisal2.model;

import org.apache.log4j.Logger;

import javax.persistence.*;

public class AuditingEntityListener {

    private Logger logger = Logger.getLogger(AuditingEntityListener.class);

    @PrePersist
    public void prePersist(Object e) {
        logger.debug("prePersist(TestEntity) - start"); //$NON-NLS-1$
        logger.debug("prePersist(TestEntity) - end"); //$NON-NLS-1$
    }

    @PostPersist
    public void postPersist(Object e) {
        logger.debug("postPersist(TestEntity) - start"); //$NON-NLS-1$
        logger.debug("postPersist(TestEntity) - end"); //$NON-NLS-1$
    }

    @PostLoad
    public void postLoad(Object e) {
        logger.debug("postLoad(TestEntity) - start"); //$NON-NLS-1$
        logger.debug("postLoad(TestEntity) - end"); //$NON-NLS-1$
    }

    @PreUpdate
    public void preUpdate(Object e) {
        logger.debug("preUpdate(TestEntity) - start"); //$NON-NLS-1$
        logger.debug("preUpdate(TestEntity) - end"); //$NON-NLS-1$
    }

    @PostUpdate
    public void postUpdate(Object e) {
        logger.debug("postUpdate(TestEntity) - start"); //$NON-NLS-1$
        logger.debug("postUpdate(TestEntity) - end"); //$NON-NLS-1$
    }

    @PreRemove
    public void preRemove(Object e) {
        logger.debug("preRemove(TestEntity) - start"); //$NON-NLS-1$
        logger.debug("preRemove(TestEntity) - end"); //$NON-NLS-1$
    }

    @PostRemove
    public void postRemove(Object e) {
        logger.debug("postRemove(TestEntity) - start"); //$NON-NLS-1$
        logger.debug("postRemove(TestEntity) - end"); //$NON-NLS-1$
    }
}