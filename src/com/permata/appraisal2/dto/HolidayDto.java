package com.permata.appraisal2.dto;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Bachri on 1/17/2017.
 */
public class HolidayDto  implements Serializable {
    private Long id;
    @NotNull
    @Future(message = "Add/Edit Tidak Bisa Back Date")
    private Date tanggal;
    private String keterangan;

    private Date tanggal_end;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Date getTanggal_end() {
        return tanggal_end;
    }

    public void setTanggal_end(Date tanggal_end) {
        this.tanggal_end = tanggal_end;
    }

}
