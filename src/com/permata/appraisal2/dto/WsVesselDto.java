package com.permata.appraisal2.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Admin on 3/21/2017.
 */
public class WsVesselDto  implements Serializable {

    private BigDecimal nilaiUnitSesuaiDok;
    private BigDecimal nilaiLikuidasiSesuaiDok;
    private BigDecimal nilaiUnitSesuaiFisik;
    private BigDecimal nilaiLikuidasiSesuaiFisik;


    public BigDecimal getNilaiUnitSesuaiDok() {
        return nilaiUnitSesuaiDok;
    }

    public void setNilaiUnitSesuaiDok(BigDecimal nilaiUnitSesuaiDok) {
        this.nilaiUnitSesuaiDok = nilaiUnitSesuaiDok;
    }

    public BigDecimal getNilaiLikuidasiSesuaiDok() {
        return nilaiLikuidasiSesuaiDok;
    }

    public void setNilaiLikuidasiSesuaiDok(BigDecimal nilaiLikuidasiSesuaiDok) {
        this.nilaiLikuidasiSesuaiDok = nilaiLikuidasiSesuaiDok;
    }

    public BigDecimal getNilaiUnitSesuaiFisik() {
        return nilaiUnitSesuaiFisik;
    }

    public void setNilaiUnitSesuaiFisik(BigDecimal nilaiUnitSesuaiFisik) {
        this.nilaiUnitSesuaiFisik = nilaiUnitSesuaiFisik;
    }

    public BigDecimal getNilaiLikuidasiSesuaiFisik() {
        return nilaiLikuidasiSesuaiFisik;
    }

    public void setNilaiLikuidasiSesuaiFisik(BigDecimal nilaiLikuidasiSesuaiFisik) {
        this.nilaiLikuidasiSesuaiFisik = nilaiLikuidasiSesuaiFisik;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WsVesselDto{");
        sb.append("nilaiUnitSesuaiDok=").append(nilaiUnitSesuaiDok);
        sb.append(", nilaiLikuidasiSesuaiDok=").append(nilaiLikuidasiSesuaiDok);
        sb.append(", nilaiUnitSesuaiFisik=").append(nilaiUnitSesuaiFisik);
        sb.append(", nilaiLikuidasiSesuaiFisik=").append(nilaiLikuidasiSesuaiFisik);
        sb.append('}');
        return sb.toString();
    }
}
