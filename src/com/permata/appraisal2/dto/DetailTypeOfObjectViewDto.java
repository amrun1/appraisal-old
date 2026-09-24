package com.permata.appraisal2.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Abdul Rochman on 4/25/2017.
 */
public class DetailTypeOfObjectViewDto  implements Serializable {
    private String jenisAktiva;
    private List<String> bisnisBranch;

    public String getJenisAktiva() {
        return jenisAktiva;
    }

    public void setJenisAktiva(String jenisAktiva) {
        this.jenisAktiva = jenisAktiva;
    }

    public List<String> getBisnisBranch() {
        return bisnisBranch;
    }

    public void setBisnisBranch(List<String> bisnisBranch) {
        this.bisnisBranch = bisnisBranch;
    }
}
