package com.permata.appraisal2.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class IlonDto  implements Serializable {

    private Long id;
    private Date downDate;
    private String accountNo;
    private String cifJha;
    private String name;
    private BigDecimal plafond;
    private BigDecimal outstanding;
    private Date maturDate;
    private String collateralId;
    private String groupVal;
    private String collateralCode;
    private String collateralCategory;
    private String buildingOwner;
    private Boolean sertificateStatus;
    private String titleNumber;
    private String sertificateType;
    private String picNumber;
    private String location;
    private String locationCode;
    private BigDecimal aprExt;
    private Date aprExtDate;
    private String extValCode;
    private BigDecimal aprInt;
    private Date aprIntDate;
    private String intValCode;
    private BigDecimal extLandAprDoc;
    private BigDecimal extBldAprDoc;
    private BigDecimal extTotDoc;
    private BigDecimal extLandAprFisik;
    private BigDecimal extBldAprFisik;
    private BigDecimal extTotFisik;
    private BigDecimal intLandAprDoc;
    private BigDecimal intBldAprDoc;
    private BigDecimal intTotDoc;
    private BigDecimal intLandAprFisik;
    private BigDecimal intBldAprFisik;
    private BigDecimal intTotFisik;
    private String noSht;
    private String noApht;
    private Date aphtDate;
    private String noSuratIjin;
    private String appNumber;
    private String facilityCode;
    private Long seqNumber;
    private String facilityNumber;
    private String mainFacilityAa;
    private String mainFacilityCode;
    private Long mainFacilitySeq;
    private String facilityNo;
    private Date createdDate;
    private Long priority;
    private String priorityName;

    public String getPriorityName() {
        return priorityName;
    }

    public void setPriorityName(String priorityName) {
        this.priorityName = priorityName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDownDate() {
        return downDate;
    }

    public void setDownDate(Date downDate) {
        this.downDate = downDate;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getCifJha() {
        return cifJha;
    }

    public void setCifJha(String cifJha) {
        this.cifJha = cifJha;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPlafond() {
        return plafond;
    }

    public void setPlafond(BigDecimal plafond) {
        this.plafond = plafond;
    }

    public BigDecimal getOutstanding() {
        return outstanding;
    }

    public void setOutstanding(BigDecimal outstanding) {
        this.outstanding = outstanding;
    }

    public Date getMaturDate() {
        return maturDate;
    }

    public void setMaturDate(Date maturDate) {
        this.maturDate = maturDate;
    }

    public String getCollateralId() {
        return collateralId;
    }

    public void setCollateralId(String collateralId) {
        this.collateralId = collateralId;
    }

    public String getGroupVal() {
        return groupVal;
    }

    public void setGroupVal(String groupVal) {
        this.groupVal = groupVal;
    }

    public String getCollateralCode() {
        return collateralCode;
    }

    public void setCollateralCode(String collateralCode) {
        this.collateralCode = collateralCode;
    }

    public String getCollateralCategory() {
        return collateralCategory;
    }

    public void setCollateralCategory(String collateralCategory) {
        this.collateralCategory = collateralCategory;
    }

    public String getBuildingOwner() {
        return buildingOwner;
    }

    public void setBuildingOwner(String buildingOwner) {
        this.buildingOwner = buildingOwner;
    }

    public Boolean getSertificateStatus() {
        return sertificateStatus;
    }

    public void setSertificateStatus(Boolean sertificateStatus) {
        this.sertificateStatus = sertificateStatus;
    }

    public String getTitleNumber() {
        return titleNumber;
    }

    public void setTitleNumber(String titleNumber) {
        this.titleNumber = titleNumber;
    }

    public String getSertificateType() {
        return sertificateType;
    }

    public void setSertificateType(String sertificateType) {
        this.sertificateType = sertificateType;
    }

    public String getPicNumber() {
        return picNumber;
    }

    public void setPicNumber(String picNumber) {
        this.picNumber = picNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public BigDecimal getAprExt() {
        return aprExt;
    }

    public void setAprExt(BigDecimal aprExt) {
        this.aprExt = aprExt;
    }

    public Date getAprExtDate() {
        return aprExtDate;
    }

    public void setAprExtDate(Date aprExtDate) {
        this.aprExtDate = aprExtDate;
    }

    public String getExtValCode() {
        return extValCode;
    }

    public void setExtValCode(String extValCode) {
        this.extValCode = extValCode;
    }

    public BigDecimal getAprInt() {
        return aprInt;
    }

    public void setAprInt(BigDecimal aprInt) {
        this.aprInt = aprInt;
    }

    public Date getAprIntDate() {
        return aprIntDate;
    }

    public void setAprIntDate(Date aprIntDate) {
        this.aprIntDate = aprIntDate;
    }

    public String getIntValCode() {
        return intValCode;
    }

    public void setIntValCode(String intValCode) {
        this.intValCode = intValCode;
    }

    public BigDecimal getExtLandAprDoc() {
        return extLandAprDoc;
    }

    public void setExtLandAprDoc(BigDecimal extLandAprDoc) {
        this.extLandAprDoc = extLandAprDoc;
    }

    public BigDecimal getExtBldAprDoc() {
        return extBldAprDoc;
    }

    public void setExtBldAprDoc(BigDecimal extBldAprDoc) {
        this.extBldAprDoc = extBldAprDoc;
    }

    public BigDecimal getExtTotDoc() {
        return extTotDoc;
    }

    public void setExtTotDoc(BigDecimal extTotDoc) {
        this.extTotDoc = extTotDoc;
    }

    public BigDecimal getExtLandAprFisik() {
        return extLandAprFisik;
    }

    public void setExtLandAprFisik(BigDecimal extLandAprFisik) {
        this.extLandAprFisik = extLandAprFisik;
    }

    public BigDecimal getExtBldAprFisik() {
        return extBldAprFisik;
    }

    public void setExtBldAprFisik(BigDecimal extBldAprFisik) {
        this.extBldAprFisik = extBldAprFisik;
    }

    public BigDecimal getExtTotFisik() {
        return extTotFisik;
    }

    public void setExtTotFisik(BigDecimal extTotFisik) {
        this.extTotFisik = extTotFisik;
    }

    public BigDecimal getIntLandAprDoc() {
        return intLandAprDoc;
    }

    public void setIntLandAprDoc(BigDecimal intLandAprDoc) {
        this.intLandAprDoc = intLandAprDoc;
    }

    public BigDecimal getIntBldAprDoc() {
        return intBldAprDoc;
    }

    public void setIntBldAprDoc(BigDecimal intBldAprDoc) {
        this.intBldAprDoc = intBldAprDoc;
    }

    public BigDecimal getIntTotDoc() {
        return intTotDoc;
    }

    public void setIntTotDoc(BigDecimal intTotDoc) {
        this.intTotDoc = intTotDoc;
    }

    public BigDecimal getIntLandAprFisik() {
        return intLandAprFisik;
    }

    public void setIntLandAprFisik(BigDecimal intLandAprFisik) {
        this.intLandAprFisik = intLandAprFisik;
    }

    public BigDecimal getIntBldAprFisik() {
        return intBldAprFisik;
    }

    public void setIntBldAprFisik(BigDecimal intBldAprFisik) {
        this.intBldAprFisik = intBldAprFisik;
    }

    public BigDecimal getIntTotFisik() {
        return intTotFisik;
    }

    public void setIntTotFisik(BigDecimal intTotFisik) {
        this.intTotFisik = intTotFisik;
    }

    public String getNoSht() {
        return noSht;
    }

    public void setNoSht(String noSht) {
        this.noSht = noSht;
    }

    public String getNoApht() {
        return noApht;
    }

    public void setNoApht(String noApht) {
        this.noApht = noApht;
    }

    public Date getAphtDate() {
        return aphtDate;
    }

    public void setAphtDate(Date aphtDate) {
        this.aphtDate = aphtDate;
    }

    public String getNoSuratIjin() {
        return noSuratIjin;
    }

    public void setNoSuratIjin(String noSuratIjin) {
        this.noSuratIjin = noSuratIjin;
    }

    public String getAppNumber() {
        return appNumber;
    }

    public void setAppNumber(String appNumber) {
        this.appNumber = appNumber;
    }

    public String getFacilityCode() {
        return facilityCode;
    }

    public void setFacilityCode(String facilityCode) {
        this.facilityCode = facilityCode;
    }

    public Long getSeqNumber() {
        return seqNumber;
    }

    public void setSeqNumber(Long seqNumber) {
        this.seqNumber = seqNumber;
    }

    public String getFacilityNumber() {
        return facilityNumber;
    }

    public void setFacilityNumber(String facilityNumber) {
        this.facilityNumber = facilityNumber;
    }

    public String getMainFacilityAa() {
        return mainFacilityAa;
    }

    public void setMainFacilityAa(String mainFacilityAa) {
        this.mainFacilityAa = mainFacilityAa;
    }

    public String getMainFacilityCode() {
        return mainFacilityCode;
    }

    public void setMainFacilityCode(String mainFacilityCode) {
        this.mainFacilityCode = mainFacilityCode;
    }

    public Long getMainFacilitySeq() {
        return mainFacilitySeq;
    }

    public void setMainFacilitySeq(Long mainFacilitySeq) {
        this.mainFacilitySeq = mainFacilitySeq;
    }

    public String getFacilityNo() {
        return facilityNo;
    }

    public void setFacilityNo(String facilityNo) {
        this.facilityNo = facilityNo;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }
}
