package com.permata.appraisal2.util;

import com.permata.appraisal2.ws.client.carm.*;
import com.permata.appraisal2.ws.client.plus.WSHttpBinding_ISyncReplyStub;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

@Component
@Scope
public class CarmAttachmentClientUtils {

    private final int timeout = 1000 * 10 * 1;
    private ReceiveAppraisalServiceLocator locator = null;
    private ReceiveAppraisalServiceSoap_PortType service = null;
    private static CarmAttachmentClientUtils utils = null;
    private String url = "";
    private String username = "";
    private String password = "";
//
//    @Autowired
//    private SystemParamFacade systemParamFacade;

    public static CarmAttachmentClientUtils getInstance(String receiveUrl, String username, String password) throws MalformedURLException, RemoteException {
        if(null== utils) {
            utils = new CarmAttachmentClientUtils();
            utils.setUrl(receiveUrl);
            utils.setPassword(password);
            utils.setUsername(username);
            utils.initParam();
        }
        return utils; //.service.receiveAppraisal_Attachment(attachment);
    }

    private void initParam() throws MalformedURLException {
        try {
            if (locator==null) {
                locator = new ReceiveAppraisalServiceLocator();
            }
            /**
             *
             */
//            String url = systemParamFacade.getValue(PARAM_CODE);//"http://localhost:888/PermataApprsWs/api/soap12";//systemParamFacade.getValue(PARAM_CODE);
            if (StringUtils.isNotBlank(url)) {
                service = locator.getReceiveAppraisalServiceSoap(new URL(url));
            } else {
                service = locator.getReceiveAppraisalServiceSoap();
            }
            ((ReceiveAppraisalServiceSoap_BindingStub) service).setTimeout(timeout);
            ((ReceiveAppraisalServiceSoap_BindingStub) service).setUsername(username);
            ((ReceiveAppraisalServiceSoap_BindingStub) service).setPassword(password);
        } catch (javax.xml.rpc.ServiceException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ReceiveAppraisalServiceSoap_PortType getService() {
        return service;
    }

    public void setService(ReceiveAppraisalServiceSoap_PortType service) {
        this.service = service;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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