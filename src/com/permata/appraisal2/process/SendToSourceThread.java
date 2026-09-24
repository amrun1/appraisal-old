package com.permata.appraisal2.process;

import com.permata.appraisal2.facade.DataCommonFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class SendToSourceThread implements Runnable {

    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DataCommonFacade facade;

    private Long dataCommonId;

    public SendToSourceThread() {
        super();
    }

    public SendToSourceThread(Long dataCommonId) {
        this.dataCommonId = dataCommonId;
    }

    @Override
    public void run() {
        logger.debug("starting Runnable ..... ");
        logger.debug("facade " + facade);
        // facade.sendToSource(dataCommonId);
        logger.debug("finishing Runnable ..... ");
    }

}