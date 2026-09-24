package com.permata.appraisal2.model;

import com.permata.appraisal2.common.AppConstans;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Admin on 4/27/2017.
 */

@Entity
@Table(name = AppConstans.HibernateConstanstan.PREFIX_DB_NAME + "SEND_TO_SOURCE_LOG_ATTACHMENT")
public class SendToSourceAttachment extends EntityBase<Long> {


    private Long id;
    private SendToSourceLog sendToSourceLog;
    private Long idCommon;
    private String response;
    private Date tanggal;
    private int status;
    private String message;
    private String fileName;



    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "SEND_TO_SOURCE_LOG_ATTACHMENT_SEQ", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "SEND_TO_SOURCE_LOG_ATTACHMENT_SEQ", sequenceName = "SEND_TO_SOURCE_LOG_ATTACHMENT_SEQ", initialValue = 1, allocationSize = 1)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_DATA_SOURCE")
    public SendToSourceLog getSendToSourceLog() {
        return sendToSourceLog;
    }

    public void setSendToSourceLog(SendToSourceLog sendToSourceLog) {
        this.sendToSourceLog = sendToSourceLog;
    }

    @Column(name = "ID_DATA_UMUM")
    public Long getIdCommon() {
        return idCommon;
    }

    public void setIdCommon(Long idCommon) {
        this.idCommon = idCommon;
    }

    @Column(name = "RESPONSE")
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Column(name = "TANGGAL")
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


    @Column(name = "FILE_NAME")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SendToSourceAttachment{");
        sb.append("id=").append(id);
        sb.append(", sendToSourceLog=").append(sendToSourceLog);
        sb.append(", idCommon=").append(idCommon);
        sb.append(", response='").append(response).append('\'');
        sb.append(", tanggal=").append(tanggal);
        sb.append(", status=").append(status);
        sb.append(", message='").append(message).append('\'');
        sb.append(", fileName='").append(fileName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
