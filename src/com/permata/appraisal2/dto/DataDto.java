package com.permata.appraisal2.dto;

import java.io.Serializable;

/**
 * Created by Abdul Rochman on 4/25/2017.
 */
public class DataDto  implements Serializable {
    String nama;
    long count;
    long CountOrderOver;
    long CountOrderUnder;
    long persenOrderOver;
    long persenOrderUnder;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getCountOrderOver() {
        return CountOrderOver;
    }

    public void setCountOrderOver(long countOrderOver) {
        CountOrderOver = countOrderOver;
    }

    public long getCountOrderUnder() {
        return CountOrderUnder;
    }

    public void setCountOrderUnder(long countOrderUnder) {
        CountOrderUnder = countOrderUnder;
    }

    public long getPersenOrderOver() {
        return persenOrderOver;
    }

    public void setPersenOrderOver(long persenOrderOver) {
        this.persenOrderOver = persenOrderOver;
    }

    public long getPersenOrderUnder() {
        return persenOrderUnder;
    }

    public void setPersenOrderUnder(long persenOrderUnder) {
        this.persenOrderUnder = persenOrderUnder;
    }
}
