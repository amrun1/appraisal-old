package com.permata.appraisal2.model;

import com.permata.appraisal2.common.AppConstans;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = AppConstans.HibernateConstanstan.PREFIX_DB_NAME + "SECURITY_LOG")
public class SecurityLog extends EntityBase<Long> {


    private static final long serialVersionUID = 4368107058242748434L;

    private Long id;
    private Person person;
    private Date dateTime;
    private String ipAddress;
    private Long logCode;
    private String keterangan;
    private String computerName;
    private String detail;
    private String userAffected;
    private String appName;



    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "SEQ_SECURITY_LOG", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "SEQ_SECURITY_LOG", sequenceName = "SECURITY_LOG_SEQ", initialValue = 1, allocationSize = 1)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_USERID", nullable = false)
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Column(name = "DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Column(name = "IPADDRESS")
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Column(name = "LOG_CODE")
    public Long getLogCode() {
        return logCode;
    }

    public void setLogCode(Long logCode) {
        this.logCode = logCode;
    }

    @Column(name = "KETERANGAN")
    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }


    @Column(name = "COMPUTER_NAME")
    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    @Column(name = "DETAIL_EVENT")
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Column(name = "USER_AFFECTED")
    public String getUserAffected() {
        return userAffected;
    }

    public void setUserAffected(String userAffected) {
        this.userAffected = userAffected;
    }

    @Column(name = "APP_NAME")
    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SecurityLog{");
        sb.append("id=").append(id);
        sb.append(", person=").append(person);
        sb.append(", dateTime=").append(dateTime);
        sb.append(", ipAddress='").append(ipAddress).append('\'');
        sb.append(", logCode=").append(logCode);
        sb.append(", keterangan='").append(keterangan).append('\'');
        sb.append(", computerName='").append(computerName).append('\'');
        sb.append(", detail='").append(detail).append('\'');
        sb.append(", userAffected='").append(userAffected).append('\'');
        sb.append(", appName='").append(appName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}