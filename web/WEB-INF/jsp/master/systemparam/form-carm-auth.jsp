<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
    <title>Appraisal</title>
    <meta http-equiv="Expires" content="-1"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${ctxPath}/js/bootstrap_2.3.2/css/bootstrap.css" rel="stylesheet">
    <link href="${ctxPath}/js/datatables.css" rel="stylesheet">
    <link href="${ctxPath}/css/main-app.css" rel="stylesheet">
    <title>Update CARM Credential</title>
</head>
<body>
<div class="container">
    <div class="page-header">
        <h1>Update Authentication for Interface ACL</h1>
    </div>
    <c:if test="${not empty msg}">
        <div class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${msg}</strong>
        </div>
    </c:if>


    <form method="post" action="form-carm-auth?${_csrf.parameterName}=${_csrf.token}" modelAttribute="formBean" class="form-horizontal">
                    <div class="control-group">
                        <label class="control-label" for="formBean.code">User </label>
                        <div class="controls">
                            <f:input path="formBean.code" type="text" class="form-control" placeholder="code" value="${user}" />
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="formBean.value">Pasword </label>
                        <div class="controls">
                            <f:input path="formBean.value" type="password" class="form-control" placeholder="value" value="${password}" />
                        </div>
                    </div>
        <div class="control-group">
            <label class="control-label">&nbsp;</label>
            <div class="controls">
                <button type="submit" class="btn btn-small btn-success"><i class="icon-white icon-ok"></i> Save</button>

            </div>
        </div>
    </form>
 </div>
<%--<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>--%>
<%--<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>--%>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<script src="${ctxPath}/js/bootstrap-datepicker-1.6.4/js/bootstrap-datepicker.min.js"></script>
<script src="${ctxPath}/js/jquery.inputmask.bundle.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<%--<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>--%>
</body>
</html>
