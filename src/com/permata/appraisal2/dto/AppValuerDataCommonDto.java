package com.permata.appraisal2.dto;

import java.io.Serializable;

public class AppValuerDataCommonDto extends AppAdminDataCommonDto  implements Serializable {

    private boolean hasLpa;
    private boolean completeLpa;
    private LpaUmumDto lpa;

    public boolean isHasLpa() {
        return hasLpa;
    }

    public void setHasLpa(boolean hasLpa) {
        this.hasLpa = hasLpa;
    }

    public boolean isCompleteLpa() {
        return completeLpa;
    }

    public void setCompleteLpa(boolean completeLpa) {
        this.completeLpa = completeLpa;
    }

    public LpaUmumDto getLpa() {
        return lpa;
    }

    public void setLpa(LpaUmumDto lpa) {
        this.lpa = lpa;
    }
}
