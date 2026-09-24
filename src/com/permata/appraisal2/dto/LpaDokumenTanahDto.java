package com.permata.appraisal2.dto;

import com.permata.appraisal2.common.AppConstans;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class LpaDokumenTanahDto implements Validator, Serializable {

    @Override
    public boolean supports(Class<?> aClass) {
        return LpaDokumenTanahDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        // ValidationUtils.rejectIfEmpty(errors, "faktorMarketability", "name.empty");
        LpaDokumenTanahDto w = (LpaDokumenTanahDto) o;
        ValidationUtils.rejectIfEmpty(errors, "jenisDokumenId",
                AppConstans.ErrorMessage.CODE_REQUIRED,
                String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Jenis Dokumen "));
        if (w.getReadDocTanah().equalsIgnoreCase("Jelas")){

            ValidationUtils.rejectIfEmpty(errors, "namaDokumen",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Nomor Dokumen "));


            ValidationUtils.rejectIfEmpty(errors, "tanggalSertipikat",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Tanggal Sertipikat "));


            ValidationUtils.rejectIfEmpty(errors, "namaJalan",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Nama Jalan"));


            ValidationUtils.rejectIfEmpty(errors, "blockGangLantai",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Block/Lantai "));


            ValidationUtils.rejectIfEmpty(errors, "rt",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "RT "));


            ValidationUtils.rejectIfEmpty(errors, "kelurahan",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Kelurahan "));


            ValidationUtils.rejectIfEmpty(errors, "kota",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Kota "));


            ValidationUtils.rejectIfEmpty(errors, "namaPemegangHak",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Nama pemegang hak "));


            ValidationUtils.rejectIfEmpty(errors, "suratUkur",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Surat Ukur "));


            ValidationUtils.rejectIfEmpty(errors, "nomor",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Nomor "));


            ValidationUtils.rejectIfEmpty(errors, "rw",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Rw "));



            ValidationUtils.rejectIfEmpty(errors, "kecamatan",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Kecamatan "));


            ValidationUtils.rejectIfEmpty(errors, "propinsi",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Propinsi "));


//            ValidationUtils.rejectIfEmpty(errors, "dokumenTanah.tanggalBerakhirHak",
//                    AppConstans.ErrorMessage.CODE_REQUIRED,
//                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Tanggal Berakhir Hak "));



            ValidationUtils.rejectIfEmpty(errors, "tanggalSuratUkur",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Tanggal Surat Ukur "));


            ValidationUtils.rejectIfEmpty(errors, "luasTanah",
                    AppConstans.ErrorMessage.CODE_REQUIRED,
                    String.format(AppConstans.ErrorMessage.MSG_REQUIRED, "Luas tanah "));

        }


    }

    public void validate(Errors errors) {
        validate(this, errors);
    }

    private Long id;
    private Long commonId;
    private Long jenisDokumenId;
    private String jenisDokumenNama;
     private String namaDokumen;
     private Date tanggalSertipikat;
     private String namaJalan;
     private String blockGangLantai;
     private String nomor;
    private String rt;
    private String rw;
     private String kelurahan;
    private String kecamatan;
     private String kota;
     private String propinsi;
     private String namaPemegangHak;
    private Date tanggalBerakhirHak;
     private String suratUkur;
     private Date tanggalSuratUkur;
     private Double luasTanah;

     private String luasTanahStr;
    private String readDocTanah;


    public String getReadDocTanah() {
        return readDocTanah;
    }

    public void setReadDocTanah(String readDocTanah) {
        this.readDocTanah = readDocTanah;
    }

    public String getLuasTanahStr() {
        return luasTanahStr;
    }

    public void setLuasTanahStr(String luasTanahStr) {
        this.luasTanahStr = luasTanahStr;
    }

    public Long getCommonId() {
        return commonId;
    }

    public void setCommonId(Long commonId) {
        this.commonId = commonId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJenisDokumenId() {
        return jenisDokumenId;
    }

    public void setJenisDokumenId(Long jenisDokumenId) {
        this.jenisDokumenId = jenisDokumenId;
    }

    public String getJenisDokumenNama() {
        return jenisDokumenNama;
    }

    public void setJenisDokumenNama(String jenisDokumenNama) {
        this.jenisDokumenNama = jenisDokumenNama;
    }

    public String getNamaDokumen() {
        return namaDokumen;
    }

    public void setNamaDokumen(String namaDokumen) {
        this.namaDokumen = namaDokumen;
    }

    public Date getTanggalSertipikat() {
        return tanggalSertipikat;
    }

    public void setTanggalSertipikat(Date tanggalSertipikat) {
        this.tanggalSertipikat = tanggalSertipikat;
    }

    public String getNamaJalan() {
        return namaJalan;
    }

    public void setNamaJalan(String namaJalan) {
        this.namaJalan = namaJalan;
    }

    public String getBlockGangLantai() {
        return blockGangLantai;
    }

    public void setBlockGangLantai(String blockGangLantai) {
        this.blockGangLantai = blockGangLantai;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
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

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getPropinsi() {
        return propinsi;
    }

    public void setPropinsi(String propinsi) {
        this.propinsi = propinsi;
    }

    public String getNamaPemegangHak() {
        return namaPemegangHak;
    }

    public void setNamaPemegangHak(String namaPemegangHak) {
        this.namaPemegangHak = namaPemegangHak;
    }

    public Date getTanggalBerakhirHak() {
        return tanggalBerakhirHak;
    }

    public void setTanggalBerakhirHak(Date tanggalBerakhirHak) {
        this.tanggalBerakhirHak = tanggalBerakhirHak;
    }

    public String getSuratUkur() {
        return suratUkur;
    }

    public void setSuratUkur(String suratUkur) {
        this.suratUkur = suratUkur;
    }

    public Date getTanggalSuratUkur() {
        return tanggalSuratUkur;
    }

    public void setTanggalSuratUkur(Date tanggalSuratUkur) {
        this.tanggalSuratUkur = tanggalSuratUkur;
    }

    public Double getLuasTanah() {
        return luasTanah;
    }

    public void setLuasTanah(Double luasTanah) {
        this.luasTanah = luasTanah;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LpaDokumenTanahDto{");
        sb.append("id=").append(id);
        sb.append(", commonId=").append(commonId);
        sb.append(", jenisDokumenId=").append(jenisDokumenId);
        sb.append(", jenisDokumenNama='").append(jenisDokumenNama).append('\'');
        sb.append(", namaDokumen='").append(namaDokumen).append('\'');
        sb.append(", tanggalSertipikat=").append(tanggalSertipikat);
        sb.append(", namaJalan='").append(namaJalan).append('\'');
        sb.append(", blockGangLantai='").append(blockGangLantai).append('\'');
        sb.append(", nomor='").append(nomor).append('\'');
        sb.append(", rt='").append(rt).append('\'');
        sb.append(", rw='").append(rw).append('\'');
        sb.append(", kelurahan='").append(kelurahan).append('\'');
        sb.append(", kecamatan='").append(kecamatan).append('\'');
        sb.append(", kota='").append(kota).append('\'');
        sb.append(", propinsi='").append(propinsi).append('\'');
        sb.append(", namaPemegangHak='").append(namaPemegangHak).append('\'');
        sb.append(", tanggalBerakhirHak=").append(tanggalBerakhirHak);
        sb.append(", suratUkur='").append(suratUkur).append('\'');
        sb.append(", tanggalSuratUkur=").append(tanggalSuratUkur);
        sb.append(", luasTanah=").append(luasTanah);
        sb.append('}');
        return sb.toString();
    }
}