package com.permata.appraisal2.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Admin on 2/28/2017.
 */
public class WsKendaraanDto  implements Serializable {

    private BigDecimal nilaiDokPasar;
    private BigDecimal nilaiFisikPasar;
    private BigDecimal nilaiDokLikuidasi;
    private BigDecimal nilaiFisikLikuidasi;


    public BigDecimal getNilaiDokPasar() {
        return nilaiDokPasar;
    }

    public void setNilaiDokPasar(BigDecimal nilaiDokPasar) {
        this.nilaiDokPasar = nilaiDokPasar;
    }

    public BigDecimal getNilaiFisikPasar() {
        return nilaiFisikPasar;
    }

    public void setNilaiFisikPasar(BigDecimal nilaiFisikPasar) {
        this.nilaiFisikPasar = nilaiFisikPasar;
    }

    public BigDecimal getNilaiDokLikuidasi() {
        return nilaiDokLikuidasi;
    }

    public void setNilaiDokLikuidasi(BigDecimal nilaiDokLikuidasi) {
        this.nilaiDokLikuidasi = nilaiDokLikuidasi;
    }

    public BigDecimal getNilaiFisikLikuidasi() {
        return nilaiFisikLikuidasi;
    }

    public void setNilaiFisikLikuidasi(BigDecimal nilaiFisikLikuidasi) {
        this.nilaiFisikLikuidasi = nilaiFisikLikuidasi;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WsKendaraanDto{");
        sb.append("nilaiDokPasar=").append(nilaiDokPasar);
        sb.append(", nilaiFisikPasar=").append(nilaiFisikPasar);
        sb.append(", nilaiDokLikuidasi=").append(nilaiDokLikuidasi);
        sb.append(", nilaiFisikLikuidasi=").append(nilaiFisikLikuidasi);
        sb.append('}');
        return sb.toString();
    }
}
