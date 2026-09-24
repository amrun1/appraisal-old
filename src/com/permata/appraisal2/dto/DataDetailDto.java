package com.permata.appraisal2.dto;

import com.permata.appraisal2.model.SurveyStatus;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class DataDetailDto implements Serializable, Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return DataDetailDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        DataDetailDto w = (DataDetailDto) o;
        if(w.getIndikasi()){
            ValidationUtils.rejectIfEmpty(errors, "luasTanah", "empty", "Luas Tanah can't empty");
            ValidationUtils.rejectIfEmpty(errors, "luasBangunan", "empty", "Luas Bangunan can't empty");
            ValidationUtils.rejectIfEmpty(errors, "jumlahLantai", "empty", "Jumlah Lantai can't empty");
            ValidationUtils.rejectIfEmpty(errors, "hargaJual", "empty", "Harga Jual can't empty");
        }
    }

    public void validate(Errors errors) {
        validate(this, errors);
    }

    private Long id;
    @NotNull
    private Long dataCommonId;
    @NotNull
    private Long assetTypeId;
    private String assetTypeName;
    @NotNull
    private String categoryCode;
    private String categoryName;
    //private Long complexId;
    //private String complexName;
    //private String complexAddress;
    @NotNull
    private String alamat;
    private Integer sla;
    private Integer status;
    @NotNull
    private String zipCode;
    @NotNull
    private String province;
    @NotNull
    private String city;
    private String district;
    private String picName;
    @NotNull
    private String subDistrict;
    private String homeApartment;
    private String clusterTower;
    private String blokFloor;
    private String number;
    private String rt;
    private String rw;

    // survey fields
    private String day;
    private String message;
    private SurveyStatus surveyStatus;
    private String from;
    private Long countAttactment;

    // indikasi field
    private Double luasTanah;
    private Double luasBangunan;
    private Integer jumlahLantai;
    private BigDecimal hargaJual;

    // Data Umum field
    private Long idJenisAppraisal;
    private String jenisAppraisal;
    private String jenisLaporan;
    private Boolean indikasi = false;

    public DataDetailDto() {
        super();
    }

    public Boolean getIndikasi() {
        if(StringUtils.isNotBlank(jenisAppraisal)){
            if(jenisAppraisal.toLowerCase().contains("indikasi")){
                indikasi = true;
            }
        }
        return indikasi;
    }

    public void setIndikasi(Boolean indikasi) {
        this.indikasi = indikasi;
    }

    public DataDetailDto(Long dataCommonId) {
        this.dataCommonId = dataCommonId;
    }

    public Long getCountAttactment() {
        return countAttactment;
    }

    public void setCountAttactment(Long countAttactment) {
        this.countAttactment = countAttactment;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SurveyStatus getSurveyStatus() {
        return surveyStatus;
    }

    public void setSurveyStatus(SurveyStatus surveyStatus) {
        this.surveyStatus = surveyStatus;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getAssetTypeName() {
        return assetTypeName;
    }

    public void setAssetTypeName(String assetTypeName) {
        this.assetTypeName = assetTypeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAssetTypeId() {
        return assetTypeId;
    }

    public void setAssetTypeId(Long assetTypeId) {
        this.assetTypeId = assetTypeId;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getDataCommonId() {
        return dataCommonId;
    }

    public void setDataCommonId(Long dataCommonId) {
        this.dataCommonId = dataCommonId;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Integer getSla() {
        return sla;
    }

    public void setSla(Integer sla) {
        this.sla = sla;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getSubDistrict() {
        return subDistrict;
    }

    public void setSubDistrict(String subDistrict) {
        this.subDistrict = subDistrict;
    }

    public String getHomeApartment() {
        return homeApartment;
    }

    public void setHomeApartment(String homeApartment) {
        this.homeApartment = homeApartment;
    }

    public String getClusterTower() {
        return clusterTower;
    }

    public void setClusterTower(String clusterTower) {
        this.clusterTower = clusterTower;
    }

    public String getBlokFloor() {
        return blokFloor;
    }

    public void setBlokFloor(String blokFloor) {
        this.blokFloor = blokFloor;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public String getRw() {
        return rw;
    }

    public void setRw(String rw) {
        this.rw = rw;
    }

    public Long getIdJenisAppraisal() {
        return idJenisAppraisal;
    }

    public void setIdJenisAppraisal(Long idJenisAppraisal) {
        this.idJenisAppraisal = idJenisAppraisal;
    }

    public String getJenisAppraisal() {
        return jenisAppraisal;
    }

    public void setJenisAppraisal(String jenisAppraisal) {
        this.jenisAppraisal = jenisAppraisal;
    }

    public Double getLuasTanah() {
        return luasTanah;
    }

    public void setLuasTanah(Double luasTanah) {
        this.luasTanah = luasTanah;
    }

    public Double getLuasBangunan() {
        return luasBangunan;
    }

    public void setLuasBangunan(Double luasBangunan) {
        this.luasBangunan = luasBangunan;
    }

    public Integer getJumlahLantai() {
        return jumlahLantai;
    }

    public void setJumlahLantai(Integer jumlahLantai) {
        this.jumlahLantai = jumlahLantai;
    }

    public BigDecimal getHargaJual() {
        return hargaJual;
    }

    public void setHargaJual(BigDecimal hargaJual) {
        this.hargaJual = hargaJual;
    }

    public String getJenisLaporan() {
        return jenisLaporan;
    }

    public void setJenisLaporan(String jenisLaporan) {
        this.jenisLaporan = jenisLaporan;
    }
}