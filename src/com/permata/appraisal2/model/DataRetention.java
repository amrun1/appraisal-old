package com.permata.appraisal2.model;

import javax.persistence.*;
import java.util.Date;

//@Entity
//@Table(name = AppConstans.HibernateConstanstan.PREFIX_DB_NAME + "DATA_RETENTION")
@Deprecated
public class DataRetention extends EntityBase<Long> {

    private Long id;
    private int retention;
    private Person updateBy;
    private Date updateDate;
    private DataCommon dataCommon;

    @Id
    @GeneratedValue(generator = "DATA_RETENTION_SEQ", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "DATA_RETENTION_SEQ", sequenceName = "DATA_RETENTION_SEQ", initialValue = 1, allocationSize = 1)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "RETENTION")
    public int getRetention() {
        return retention;
    }

    public void setRetention(int retention) {
        this.retention = retention;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UPDATE_BY")
    public Person getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Person updateBy) {
        this.updateBy = updateBy;
    }

    @Column(name = "UPDATE_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_DATA_UMUM")
    public DataCommon getDataCommon() {
        return dataCommon;
    }

    public void setDataCommon(DataCommon dataCommon) {
        this.dataCommon = dataCommon;
    }
}
