package com.permata.appraisal2.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class AppraisalDivisionDto implements Serializable {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Long branchId;
    private String branchName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

}