package com.permata.appraisal2.dto;

import java.io.Serializable;

/**
 * Created by Admin on 5/1/2017.
 */
public class ReadableDocDto  implements Serializable {

    private String code;
    private String value;


    public ReadableDocDto(String code, String value) {
        this.code = code;
        this.value = value;
    }

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
}
