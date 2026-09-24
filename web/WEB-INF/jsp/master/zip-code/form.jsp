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

</head>
<body>
<div class="container">
    <div class="page-header">
        <h1>Master Aktiva Add/Edit</h1>
    </div>
    <c:if test="${not empty msg}">
        <div class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${msg}</strong>
        </div>
    </c:if>
    <f:form method="post" modelAttribute="formBean" action="save" class="form-horizontal">
        <f:hidden path="id"/>
        <div class="control-group">
            <label class="control-label" for="province">Propinsi</label>
            <div class="controls">
                <f:input path="province" type="text" class="form-control"/>
                <f:errors path="province" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="city">Kota / Kabupaten</label>
            <div class="controls">
                <f:input path="city" type="text" class="form-control"/>
                <f:errors path="city" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="district">Kecamatan</label>
            <div class="controls">
                <f:input path="district" type="text" class="form-control"/>
                <f:errors path="district" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="subDistrict">Kelurahan</label>
            <div class="controls">
                <f:input path="subDistrict" type="text" class="form-control"/>
                <f:errors path="subDistrict" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="code">Kode Pos</label>
            <div class="controls">
                <f:input path="code" type="text" class="form-control"/>
                <f:errors path="code" class="error has-error"/>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">&nbsp;</label>
            <div class="controls">
                <button type="submit" class="btn btn-small btn-success"><i class="icon-white icon-ok"></i> Save</button>
                <button type="button" class="btn btn-small btn-warning" onclick="gotoUrl('list')"><i
                        class="icon-white icon-step-backward"></i> Back
                </button>
            </div>
        </div>
    </f:form>
</div>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<!--[if lt IE 9]>
    <!--<script src="${ctxPath}/js/html5shiv.js"></script>-->
<!--<script src="${ctxPath}/js/respond.min.js"></script>-->
<![endif]-->
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
    $(document).ready(function () {

        $.active = false;
        $('body').bind('click keypress', function() { $.active = true; });
        var logout = '${ctxPath}/logoutExpired';
        var heartBeat = '${ctxPath}/heartbeat';
        checkActivity(${maxInterval}, 60000, 0,logout,heartBeat);

    });
</script>
</body>
</html>