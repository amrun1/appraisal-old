package com.permata.appraisal2.model;

import com.permata.appraisal2.common.AppConstans;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = AppConstans.HibernateConstanstan.PREFIX_DB_NAME + "SEND_TO_SOURCE_LOG")
public class SendToSourceLog extends EntityBase<Long>  {

    public static final int SAVE = 0;
    public static final int SUCCESS = 1;
    public static final int ERROR = 2;
    public static final int MANUAL = 3;

    public static final String JENIS_KIRIM = "Kirim";
    public static final String JENIS_TERIMA = "Terima";

    private Long id;
    private Long idCommon;
    private String jenis;
    private String source;
    private String alamat;
    private String response;
    private Date tanggal;
    private int status;
    private String message;
    private List<SendToSourceAttachment> sendToSourceAttachmentList;



    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "SEND_TO_SOURCE_LOG_SEQ", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "SEND_TO_SOURCE_LOG_SEQ", sequenceName = "SEND_TO_SOURCE_LOG_SEQ", initialValue = 1, allocationSize = 1)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "JENIS", length = 15)
    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    @Column(name = "SOURCE", nullable = false)
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Column(name = "ALAMAT_URL")
    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Column(name = "RESPON")
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sendToSourceLog")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    public List<SendToSourceAttachment> getSendToSourceAttachmentList() {
        return sendToSourceAttachmentList;
    }

    public void setSendToSourceAttachmentList(List<SendToSourceAttachment> sendToSourceAttachmentList) {
        this.sendToSourceAttachmentList = sendToSourceAttachmentList;
    }


    @Column(name = "TANGGAL", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    @Column(name = "STATUS")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Column(name = "MESSAGE")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Column(name = "ID_DATA_UMUM")
    public Long getIdCommon() {
        return idCommon;
    }

    public void setIdCommon(Long idCommon) {
        this.idCommon = idCommon;
    }
}