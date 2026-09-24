package com.permata.appraisal2.model;

import com.permata.appraisal2.common.AppConstans;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name = AppConstans.HibernateConstanstan.PREFIX_DB_NAME + "PERSON")
public class Person extends EntityStatusBase<String> {

    private static final long serialVersionUID = 7977710738063258812L;
    private String userId;
    private String password;
    private String email;
    private String name;
    private String phone;
    private String hp;
    private String hpAtasan;
    private List<PersonRole> roles;

    // status
    private Boolean inactive;
    private Date lastLogin;
    private Date lastFail;
    private Integer errorCount;


    public String toStringProfile() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("USER_ID='").append(userId).append('\'');
        sb.append(", NAME='").append(name).append('\'');
        sb.append(", PHONE='").append(phone).append('\'');
        sb.append(", HP='").append(hp).append('\'');
        sb.append(", HP_ATASAN='").append(hpAtasan).append('\'');
        sb.append(", email='").append(email).append('\'');
        return sb.toString();
    }

    @Id
    @Column(name = "USER_ID", nullable = false, length = 30)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "NAMA", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "HP")
    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    @Column(name = "HP_ATASAN")
    public String getHpAtasan() {
        return hpAtasan;
    }

    public void setHpAtasan(String hpAtasan) {
        this.hpAtasan = hpAtasan;
    }

//    @Column(name = "PASSWORD", nullable = true)
    @Transient
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    @Cascade({CascadeType.DELETE, CascadeType.SAVE_UPDATE})
    public List<PersonRole> getRoles() {
        return roles;
    }

    public void addRole(PersonRole role) {
        if (roles == null) {
            roles = new ArrayList<PersonRole>();
        }
        role.setPerson(this);
        roles.add(role);
    }

    public void setRoles(List<PersonRole> roles) {
        this.roles = roles;
    }

    @Transient
    public PersonRole getRole(PersonLevel level) {
        if (roles != null) {
            for (PersonRole role : roles) {
                if (role.getLevel().equals(level)) {
                    return role;
                }
            }
        }
        return null;
    }

    @Column(name = "INACTIVE")
    public Boolean getInactive() {
        return inactive;
    }

    public void setInactive(Boolean inactive) {
        this.inactive = inactive;
    }

    @Column(name = "LAST_LOGIN")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Column(name = "SUM_FAILURE")
    public Integer getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    @Column(name = "DATE_FAILURE")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastFail() {
        return lastFail;
    }

    public void setLastFail(Date lastFail) {
        this.lastFail = lastFail;
    }
}