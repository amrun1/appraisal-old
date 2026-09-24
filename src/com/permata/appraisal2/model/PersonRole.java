package com.permata.appraisal2.model;

import com.permata.appraisal2.common.AppConstans;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = AppConstans.HibernateConstanstan.PREFIX_DB_NAME + "PERSON_ROLES", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "person_level"}))
public class PersonRole extends EntityBase<Long> {

    private Long id;
    private Person person;
    private PersonLevel level;
    private Branch branch;
    private AppraisalBranch appBranch;
    private BigDecimal limitApproval;

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "PERSON_ROLES_LOG", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "PERSON_ROLES_LOG", sequenceName = "PERSON_ROLES_SEQ", initialValue = 1, allocationSize = 1)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Column(name = "PERSON_LEVEL", nullable = false)
    @Enumerated(EnumType.STRING)
    public PersonLevel getLevel() {
        return level;
    }

    public void setLevel(PersonLevel level) {
        this.level = level;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CABANG_ID", nullable = true)
    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "APP_CABANG_ID", nullable = true)
    public AppraisalBranch getAppBranch() {
        return appBranch;
    }

    public void setAppBranch(AppraisalBranch appBranch) {
        this.appBranch = appBranch;
    }

    @Column(name = "LIMIT_APPROVAL")
    public BigDecimal getLimitApproval() {
        return limitApproval;
    }

    public void setLimitApproval(BigDecimal limitApproval) {
        this.limitApproval = limitApproval;
    }
}