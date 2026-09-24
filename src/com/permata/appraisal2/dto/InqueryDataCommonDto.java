package com.permata.appraisal2.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InqueryDataCommonDto extends AppValuerDataCommonDto  implements Serializable {

    private List<DataHistoryDto> histories = new ArrayList<DataHistoryDto>();

    public List<DataHistoryDto> getHistories() {
        return histories;
    }

    public void setHistories(List<DataHistoryDto> histories) {
        this.histories = histories;
    }

    public void addHistory(DataHistoryDto add) {
        histories.add(add);
    }

}