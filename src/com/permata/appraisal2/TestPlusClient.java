package com.permata.appraisal2;

import com.permata.appraisal2.ws.client.plus.ISyncReply;
import com.permata.appraisal2.ws.client.plus.SyncReplyLocator;

public class TestPlusClient {
    public static void main(String[] argv) {
        try {
            SyncReplyLocator locator = new SyncReplyLocator();
            ISyncReply service = locator.getWSHttpBinding_ISyncReply();
            // If auth
            //
            // orization is required
            //((WSHttpBinding_ISyncReplyStub)service).setUsername("user3");
            //((WSHttpBinding_ISyncReplyStub)service).setPassword("pass3");
            // invoke business method
            service.hello("Saiful Haqqi");
        } catch (javax.xml.rpc.ServiceException ex) {
            ex.printStackTrace();
        } catch (java.rmi.RemoteException ex) {
            ex.printStackTrace();
        }
    }
}