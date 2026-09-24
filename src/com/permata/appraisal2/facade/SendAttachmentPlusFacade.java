package com.permata.appraisal2.facade;

import com.permata.appraisal2.model.DataCommon;
import com.permata.appraisal2.model.SendToSourceLog;

/**
 * Created by QS on 11/10/2017.
 */
public interface SendAttachmentPlusFacade {
    void sendAttachments(DataCommon e, SendToSourceLog logParent, String url, Boolean sendDokTanah, Boolean sendDokBangunan);
}
