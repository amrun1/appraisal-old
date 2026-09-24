package com.permata.appraisal2.model;

import javax.persistence.*;
import java.util.Date;

//@Entity
//@Table(name = AppConstans.HibernateConstanstan.PREFIX_DB_NAME + "PERSON_ACTIVITY")
@Deprecated
public class PersonActivity extends EntityBase<Long> {

    private static final long serialVersionUID = -1205764487959108216L;
    private Long id;
    private Person person;
    private Date lastLogin;
    private Date dateFailure;
    private Date passChange;
    private Long sumFailure;
    private int status;

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "PERSON_ACTIVITY_SEQ", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "PERSON_ACTIVITY_SEQ", sequenceName = "PERSON_ACTIVITY_SEQ", initialValue = 1, allocationSize = 1)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_USERID", nullable = true)
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Column(name = "LAST_LOGIN")
    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Column(name = "DATE_FAILURE")
    public Date getDateFailure() {
        return dateFailure;
    }

    public void setDateFailure(Date dateFailure) {
        this.dateFailure = dateFailure;
    }

    @Column(name = "PASS_CHANGE")
    public Date getPassChange() {
        return passChange;
    }

    public void setPassChange(Date passChange) {
        this.passChange = passChange;
    }

    @Column(name = "SUM_FAILURE")
    public Long getSumFailure() {
        return sumFailure;
    }

    public void setSumFailure(Long sumFailure) {
        this.sumFailure = sumFailure;
    }

    @Column(name = "STATUS")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}