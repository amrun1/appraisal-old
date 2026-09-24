package com.permata.appraisal2.dto;

import java.io.Serializable;

public class SystemParamsDto extends Throwable implements Serializable {

    private String code;
    private String value;
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SystemParamsDto)) return false;

        SystemParamsDto that = (SystemParamsDto) o;

        if (!code.equals(that.code)) return false;
        if (!description.equals(that.description)) return false;
        if (!value.equals(that.value)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code.hashCode();
        result = 31 * result + value.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SystemParamsDto{");
        sb.append("code='").append(code).append('\'');
        sb.append(", value='").append(value).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
