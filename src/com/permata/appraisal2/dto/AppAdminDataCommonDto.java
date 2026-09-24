package com.permata.appraisal2.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AppAdminDataCommonDto extends DataCommonDto implements Serializable{

    private List<DataMessageDto> messages = new ArrayList<DataMessageDto>();
//    private List<DataHistoryDto> histories = new ArrayList<DataHistoryDto>();
    private Long kjppId;
    private String kjppName;

    public Long getKjppId() {
        return kjppId;
    }

    public void setKjppId(Long kjppId) {
        this.kjppId = kjppId;
    }

    public String getKjppName() {
        return kjppName;
    }

    public void setKjppName(String kjppName) {
        this.kjppName = kjppName;
    }

    public List<DataMessageDto> getMessages() {
        return messages;
    }

    public void setMessages(List<DataMessageDto> messages) {
        this.messages = messages;
    }

    public void addMessage(DataMessageDto add) {
        messages.add(add);
    }

//    public List<DataHistoryDto> getHistories() {
//        return histories;
//    }
//
//    public void setHistories(List<DataHistoryDto> histories) {
//        this.histories = histories;
//    }
//
//    public void addHistory(DataHistoryDto add) {
//        histories.add(add);
//    }
}