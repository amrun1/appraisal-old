package com.permata.appraisal2.common;

import com.permata.appraisal2.dao.common.OrderSpec;
import com.permata.appraisal2.dao.common.PagingInfo;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseController {

    protected Logger logger = Logger.getLogger(getClass());

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected ApplicationContext appContext;

    @InitBinder
    private void dateBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(AppConstans.ControllerConstants.DATE_CONVERTER_PATTERN);
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);

        binder.registerCustomEditor(Date.class, editor);
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        // MultiPartFile to byte[]
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }

    protected String previewImage(HttpServletResponse response, String fileName, byte[] bytes, String meta) {
        OutputStream out = null;
        try {
            response.setHeader("Content-Disposition", "inline;filename=\"" + fileName + "\"");
            out = response.getOutputStream();
            if (StringUtils.isNotBlank(meta)) {
                response.setContentType(meta);
            }
            IOUtils.copy(new ByteArrayInputStream(bytes), out);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if(out!=null){
                try {
                    out.flush();
                    out.close();
                    IOUtils.closeQuietly(out);
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return null;
    }

    protected PagingInfo getPagingInfo() {
        /*
         * belum ada pengecekan untuk menampilkan semua data
		 */
        if (StringUtils.isNotBlank(request.getParameter("retrieveAll"))) {
            boolean value = Boolean.parseBoolean(request.getParameter("retrieveAll"));
            if (value) {
                return PagingInfo.createToRetrieveAll();
            }
        }
        if (StringUtils.isBlank(request.getParameter("iDisplayLength"))) {
            return PagingInfo.create();
        }
        if (Integer.valueOf(request.getParameter("iDisplayLength")) == -1) {
            return PagingInfo.createToRetrieveAll();
        }
        return new PagingInfo(
                (Integer.valueOf(request.getParameter("iDisplayStart"))
                        / Integer.valueOf(request.getParameter("iDisplayLength"))) + 1,
                Integer.valueOf(request.getParameter("iDisplayLength")));
    }

    protected OrderSpec getOrder() {
        if (StringUtils.isNotBlank(request.getParameter("iSortCol_0"))) {
            String columnIndex = request.getParameter("iSortCol_0");
            String column = request.getParameter("mDataProp_" + columnIndex);
            String order = request.getParameter("sSortDir_0");
//            logger.debug("order: " + order);
//            logger.debug("columnIndex: " + columnIndex);
//            logger.debug("column : " + column);
            return OrderSpec.create(column, OrderSpec.OrderType.valueOf(order));
        }
        return null;
    }

	/*protected User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = new User();
		user.setUserId(((UserDetails) auth.getPrincipal()).getUsername());
		user.setPassword(((UserDetails) auth.getPrincipal()).getPassword());
		return user;
	}*/
}