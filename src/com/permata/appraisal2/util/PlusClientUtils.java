package com.permata.appraisal2.util;

import com.permata.appraisal2.facade.SystemParamFacade;
import com.permata.appraisal2.ws.client.plus.ISyncReply;
import com.permata.appraisal2.ws.client.plus.SyncReplyLocator;
import com.permata.appraisal2.ws.client.plus.WSHttpBinding_ISyncReplyStub;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;

@Component
@Scope
public class PlusClientUtils {

    private final String PARAM_CODE = "PLUS_CLIENT_URL";
    private final int timeout = 1000 * 10 * 1;
    private SyncReplyLocator locator = null;
    private ISyncReply iSyncReply = null;
    private static PlusClientUtils utils = null;
    private String url = "";
//
//    @Autowired
//    private SystemParamFacade systemParamFacade;

    public static PlusClientUtils getInstance(String receiveUrl) throws MalformedURLException {
        if(null== utils) {
            synchronized (PlusClientUtils.class) {
                if(null== utils) {
                    utils = new PlusClientUtils();
                    utils.setUrl(receiveUrl);
                    utils.initParam();
                }
        }
        }
        return utils;
    }

    private void initParam() throws MalformedURLException {
        try {
            if (locator==null) {
                locator = new SyncReplyLocator();
            }
            /**
             *
             */
//            String url = systemParamFacade.getValue(PARAM_CODE);//"http://localhost:888/PermataApprsWs/api/soap12";//systemParamFacade.getValue(PARAM_CODE);
            if (StringUtils.isNotBlank(url)) {
                iSyncReply = locator.getWSHttpBinding_ISyncReply(new URL(url));
            } else {
                iSyncReply = locator.getWSHttpBinding_ISyncReply();
            }
            ((WSHttpBinding_ISyncReplyStub) iSyncReply).setTimeout(timeout);
            //((WSHttpBinding_ISyncReplyStub)service).setUsername("user3");
            //((WSHttpBinding_ISyncReplyStub)service).setPassword("pass3");
        } catch (javax.xml.rpc.ServiceException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ISyncReply getiSyncReply() {
        return iSyncReply;
    }

    public void setiSyncReply(ISyncReply iSyncReply) {
        this.iSyncReply = iSyncReply;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
//    public ISyncReply factory() {
//        try {
//            SyncReplyLocator locator = new SyncReplyLocator();
//            /**
//             *
//             */
//            String url = systemParamFacade.getValue(PARAM_CODE);//"http://localhost:888/PermataApprsWs/api/soap12";//systemParamFacade.getValue(PARAM_CODE);
//            if (StringUtils.isNotBlank(url)) {
//                return locator.getWSHttpBinding_ISyncReply(new URL(url));
//            } else {
//                return locator.getWSHttpBinding_ISyncReply();
//            }
//            //((WSHttpBinding_ISyncReplyStub)service).setUsername("user3");
//            //((WSHttpBinding_ISyncReplyStub)service).setPassword("pass3");
//        } catch (javax.xml.rpc.ServiceException ex) {
//            ex.printStackTrace();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }
}