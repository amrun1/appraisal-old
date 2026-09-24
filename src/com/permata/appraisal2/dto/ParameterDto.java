package com.permata.appraisal2.dto;

import com.permata.appraisal2.model.GroupParameter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ParameterDto  implements Serializable {

    private Long id;
    private String code;
    @NotNull
    private String groupCode;
    private String groupDesc;
    private String value;
    private Integer sorting;

    @NotNull
    private Boolean active;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParameterDto)) return false;

        ParameterDto that = (ParameterDto) o;

        if (active != null ? !active.equals(that.active) : that.active != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (groupCode != null ? !groupCode.equals(that.groupCode) : that.groupCode != null) return false;
        if (groupDesc != null ? !groupDesc.equals(that.groupDesc) : that.groupDesc != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (sorting != null ? !sorting.equals(that.sorting) : that.sorting != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (groupCode != null ? groupCode.hashCode() : 0);
        result = 31 * result + (groupDesc != null ? groupDesc.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (sorting != null ? sorting.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ParameterDto{");
        sb.append("id=").append(id);
        sb.append(", code='").append(code).append('\'');
        sb.append(", groupCode='").append(groupCode).append('\'');
        sb.append(", groupDesc='").append(groupDesc).append('\'');
        sb.append(", value='").append(value).append('\'');
        sb.append(", sorting=").append(sorting);
        sb.append(", active=").append(active);
        sb.append('}');
        return sb.toString();
    }
}
