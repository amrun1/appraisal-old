<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.permata.appraisal2.model.PersonLevel" %>
<c:set var="showButton" value="false"/>
<c:set var="surveyor" value="<%=PersonLevel.APPRAISAL_SURVEYOR%>"/>
<c:if test="${roleActive.level == surveyor}">
    <c:set var="showButton" value="true"/>
</c:if>
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
    <link href="${ctxPath}/css/main-app.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${ctxPath}/js/html5shiv.js"></script>
    <script src="${ctxPath}/js/respond.min.js"></script>
    <![endif]-->

    <style type="text/css">
        .list-view label {
            display: block;
            margin-bottom: 5px;
            margin-left: 20px;
        }
        .list-view input {
            float: left;
            margin-right: 5px;
        }
    </style>
</head>
<body>
<div class="">
    <div class="page-header hide">
        <h3>Survey Data Point</h3>
    </div>
    <c:if test="${not empty msg}">
        <div class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${msg}</strong>
        </div>
    </c:if>
    <f:form method="post" modelAttribute="formBean" action="save-point?${_csrf.parameterName}=${_csrf.token}" class="form-horizontal"
            enctype="multipart/form-data">
        <f:hidden path="id"/>
        <f:hidden path="detailId"/>
        <f:hidden path="category"/>
        <div class="control-group hide">
            <label class="control-label" for="positif">Penilaian Positif</label>
            <div class="controls">
                <f:textarea path="positif" rows="4" cssClass="input-large"/>
                <f:errors path="positif" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="catatan">Catatan</label>
            <div class="controls">
                <f:textarea path="catatan" rows="3"/>
                <f:errors path="catatan" class="error has-error"/>
            </div>
        </div>
        <div class="control-group" style="margin-bottom: 15px;">
            <label class="control-label" for="listNegatif">List Penilaian Negatif</label>
            <div class="controls list-view">
                <f:checkboxes path="listNegatif" items="${listLegative}" itemValue="value" itemLabel="label"/>
                <f:errors path="listNegatif" class="error has-error"/>
            </div>
        </div>
        <div class="control-group" style="margin-bottom: 15px;">
            <label class="control-label" for="listPertimbangan">List Pertimbangan Khusus</label>
            <div class="controls list-view">
                <f:checkboxes path="listPertimbangan" items="${listPertimbangan}" itemValue="value" itemLabel="label"/>
                <f:errors path="listPertimbangan" class="error has-error"/>
            </div>
        </div>
        <c:if test="${showButton && !inquery}">
            <div class="control-group span12">
                <label class="control-label">&nbsp;</label>
                <div class="controls">
                    <button type="submit" class="btn btn-success"><i class="icon-white icon-ok-circle"></i> Save</button>
                    <button type="reset" class="btn btn-warning"><i class="icon-white icon-refresh"></i> Reset</button>
                </div>
            </div>
        </c:if>
    </f:form>
</div>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
    });
</script>
</body>
</html>