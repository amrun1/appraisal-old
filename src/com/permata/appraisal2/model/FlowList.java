package com.permata.appraisal2.model;

import com.permata.appraisal2.common.AppConstans;

import javax.persistence.*;

@Entity
@Table(name = AppConstans.HibernateConstanstan.PREFIX_DB_NAME + "FLOW_LIST")
public class FlowList extends EntityBase<Long> {

    public static final String REJECT = "REJECT";
    public static final String SUBMIT = "SUBMIT";

    private Long id;
    private String settingCode;
    private String from;
    private String type;
    private String destination;
    private Integer sorting;

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "FLOW_LIST_SEQ", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "FLOW_LIST_SEQ", sequenceName = "FLOW_LIST_SEQ", initialValue = 1, allocationSize = 1)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "KODE")
    public String getSettingCode() {
        return settingCode;
    }

    public void setSettingCode(String settingCode) {
        this.settingCode = settingCode;
    }

    @Column(name = "ASAL")
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Column(name = "JENIS")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "TUJUAN")
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Column(name = "URUT")
    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }
}