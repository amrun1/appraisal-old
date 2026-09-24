package com.permata.appraisal2.model;

import com.permata.appraisal2.common.AppConstans;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = AppConstans.HibernateConstanstan.PREFIX_DB_NAME + "MOB_TBL_ORDER")
public class MobileSendSurvey extends EntityAuditrailBase<String> {

    public static final String NEW = "NEW";
    public static final String COMPLETE = "COMPLETE";

    // private Long id;
    private String orderNo;
    private String collateralId;
    private String surveyCode;
    private String contactName;
    private String contactPhone;
    private String surveyAddress;
    private String debiturName;
    private String objectType;
    private String notes;
    private String jenisAppraisal;
    private String perumahanApartemen;
    private String clusterTower;
    private String blockLantai;
    private String nomor;
    private String rt;
    private String rw;
    private String status;
    private Date surveyDate;

    /*@Id
    @Column(name = "ID")
    @GeneratedValue(generator = "MOB_TBL_ORDER_SEQ", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "MOB_TBL_ORDER_SEQ", sequenceName = "MOB_TBL_ORDER_SEQ", initialValue = 1, allocationSize = 1)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }*/
    @Id
    @Column(name = "ORDER_NO")
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Column(name = "COLLATERAL_CODE")
    public String getCollateralId() {
        return collateralId;
    }

    public void setCollateralId(String collateralId) {
        this.collateralId = collateralId;
    }

    @Column(name = "SVY_CODE")
    public String getSurveyCode() {
        return surveyCode;
    }

    public void setSurveyCode(String surveyCode) {
        this.surveyCode = surveyCode;
    }

    @Column(name = "CONTACT_NAME")
    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @Column(name = "CONTACT_PHONE")
    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @Column(name = "SVY_ADDR")
    public String getSurveyAddress() {
        return surveyAddress;
    }

    public void setSurveyAddress(String surveyAddress) {
        this.surveyAddress = surveyAddress;
    }

    @Column(name = "DEBT_NAME")
    public String getDebiturName() {
        return debiturName;
    }

    public void setDebiturName(String debiturName) {
        this.debiturName = debiturName;
    }

    @Column(name = "OBJ_TYPE")
    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    @Column(name = "NOTES")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Column(name = "JENIS_APPRAISAL")
    public String getJenisAppraisal() {
        return jenisAppraisal;
    }

    public void setJenisAppraisal(String jenisAppraisal) {
        this.jenisAppraisal = jenisAppraisal;
    }

    @Column(name = "PERUMAHAN_APARTMENT")
    public String getPerumahanApartemen() {
        return perumahanApartemen;
    }

    public void setPerumahanApartemen(String perumahanApartemen) {
        this.perumahanApartemen = perumahanApartemen;
    }

    @Column(name = "CLUSTER_TOWER")
    public String getClusterTower() {
        return clusterTower;
    }

    public void setClusterTower(String clusterTower) {
        this.clusterTower = clusterTower;
    }

    @Column(name = "BLOK_LANTAI")
    public String getBlockLantai() {
        return blockLantai;
    }

    public void setBlockLantai(String blockLantai) {
        this.blockLantai = blockLantai;
    }

    @Column(name = "NOMOR")
    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
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

    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name="SURVEY_DATE", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public Date getSurveyDate() {
        return surveyDate;
    }

    public void setSurveyDate(Date surveyDate) {
        this.surveyDate = surveyDate;
    }
}