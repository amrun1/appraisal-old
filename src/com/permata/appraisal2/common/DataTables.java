package com.permata.appraisal2.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataTables<T> implements Serializable {

    private static final long serialVersionUID = 4886196670709560729L;
    private Integer sEcho;
    private Long iTotalRecords;
    private Long iTotalDisplayRecords;
    private List<T> aaData;

    public List<T> getAaData() {
        return aaData;
    }

    public void setAaData(List<T> aaData) {
        this.aaData = aaData;
    }

    public void add(T t) {
        if (aaData == null) {
            aaData = new ArrayList<T>();
        }
        aaData.add(t);
    }

    public Integer getsEcho() {
        return sEcho;
    }

    public void setsEcho(Integer sEcho) {
        this.sEcho = sEcho;
    }

    public Long getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(Long iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public Long getiTotalDisplayRecords() {
        if (iTotalDisplayRecords == null) {
            if (aaData != null && !aaData.isEmpty()) {
                iTotalDisplayRecords = (long) aaData.size();
            } else {
                iTotalDisplayRecords = 0l;
            }
        }
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(Long iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

}