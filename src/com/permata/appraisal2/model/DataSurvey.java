package com.permata.appraisal2.model;

import javax.persistence.*;
import java.util.Date;

//@Entity
//@Table(name = AppConstans.HibernateConstanstan.PREFIX_DB_NAME + "DATA_SURVEY")
@Deprecated
public class DataSurvey extends EntityBase<Long> {

    private Long id;
    private DataDetail dataDetail;
    private String debiturName;
    private String contactName;
    private String contactPhone;
    private String day;
    private String note;
    private String appraisalType;
    private String assetType;
    private String assetName;
    private SurveyStatus status;
    private String description;
    private Person createby;
    private Date createdate;
    private String result;
    private String complexApartment;
    private String clusterTower;
    private String blockFloor;
    private String number;
    private String rt;
    private String rw;

    @Id
    @GeneratedValue(generator = "DATA_SURVEY_SEQ", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "DATA_SURVEY_SEQ", sequenceName = "DATA_SURVEY_SEQ", initialValue = 1, allocationSize = 1)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_DATA_DETAIL")
    public DataDetail getDataDetail() {
        return dataDetail;
    }

    public void setDataDetail(DataDetail dataDetail) {
        this.dataDetail = dataDetail;
    }

    @Column(name = "NAMA_DEBITUR")
    public String getDebiturName() {
        return debiturName;
    }

    public void setDebiturName(String debiturName) {
        this.debiturName = debiturName;
    }

    @Column(name = "NAMA_CONTACT")
    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @Column(name = "TELP_CONTACT")
    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @Column(name = "TANGGAL")
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Column(name = "CATATAN")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Column(name = "JENIS_APPRAISAL")
    public String getAppraisalType() {
        return appraisalType;
    }

    public void setAppraisalType(String appraisalType) {
        this.appraisalType = appraisalType;
    }

    @Column(name = "JENIS_OBJECT")
    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    @Column(name = "NAMA_OBJECT")
    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    public SurveyStatus getStatus() {
        return status;
    }

    public void setStatus(SurveyStatus status) {
        this.status = status;
    }

    @Column(name = "KETERANGAN")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATED_BY")
    public Person getCreateby() {
        return createby;
    }

    public void setCreateby(Person createby) {
        this.createby = createby;
    }

    @Column(name = "CREATED_DATE")
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    @Column(name = "HASIL_SURVEY")
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Column(name = "PERUM_APARTMENT")
    public String getComplexApartment() {
        return complexApartment;
    }

    public void setComplexApartment(String complexApartment) {
        this.complexApartment = complexApartment;
    }

    @Column(name = "CLUSTER_TOWER")
    public String getClusterTower() {
        return clusterTower;
    }

    public void setClusterTower(String clusterTower) {
        this.clusterTower = clusterTower;
    }

    @Column(name = "BLOK_LANTAI")
    public String getBlockFloor() {
        return blockFloor;
    }

    public void setBlockFloor(String blockFloor) {
        this.blockFloor = blockFloor;
    }

    @Column(name = "NOMOR")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Column(name = "RT")
    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    @Column(name = "RW")
    public String getRw() {
        return rw;
    }

    public void setRw(String rw) {
        this.rw = rw;
    }
}
