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
public class CarmClientUtils {

    private final int timeout = 1000 * 10 * 1;
    private ReceiveAppraisalServiceLocator locator = null;
    private ReceiveAppraisalServiceSoap_PortType service = null;
    private static CarmClientUtils utils = null;
    private String url = "";
    private String username = "";
    private String password = "";
//
//    @Autowired
//    private SystemParamFacade systemParamFacade;

    public static CarmClientUtils getInstance(String receiveUrl, String username, String password) throws MalformedURLException, RemoteException, javax.xml.rpc.ServiceException {
        if(null== utils) {
            utils = new CarmClientUtils();
            utils.setUrl(receiveUrl);
            utils.setPassword(password);
            utils.setUsername(username);
            utils.initParam();
        }
        return utils;
    }

    private void initParam() throws MalformedURLException, javax.xml.rpc.ServiceException {

            if (locator==null) {
                locator = new ReceiveAppraisalServiceLocator();
            }
            /**
             *
             */
            if (StringUtils.isNotBlank(url)) {
                service = locator.getReceiveAppraisalServiceSoap(new URL(url));
            } else {
                service = locator.getReceiveAppraisalServiceSoap();
            }
            ((ReceiveAppraisalServiceSoap_BindingStub) service).setTimeout(timeout);
            ((ReceiveAppraisalServiceSoap_BindingStub) service).setUsername(username);
            ((ReceiveAppraisalServiceSoap_BindingStub) service).setPassword(password);
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
}