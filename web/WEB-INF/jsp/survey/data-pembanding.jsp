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
    <link href="${ctxPath}/js/bootstrap_2.3.2/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="${ctxPath}/js/bootstrap_2.3.2/css/docs.css" rel="stylesheet">
    <link href="${ctxPath}/js/bootstrap_2.3.2/js/google-code-prettify/prettify.css" rel="stylesheet">
    <link href="${ctxPath}/js/bootstrap-datepicker-1.6.4/css/bootstrap-datepicker3.min.css" rel="stylesheet"
          id="bsdp-css">
    <link href="${ctxPath}/css/main-app.css" rel="stylesheet">

    <!--[if lt IE 9]>
    <script src="${ctxPath}/js/html5shiv.js"></script>
    <script src="${ctxPath}/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <div class="page-header">
        <h3>Survey Data Pembanding</h3>
    </div>
    <c:if test="${not empty msg}">
        <div class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${msg}</strong>
        </div>
    </c:if>
    <f:form method="post" modelAttribute="formBean" action="save-pembanding?${_csrf.parameterName}=${_csrf.token}" class="form-horizontal"
            enctype="multipart/form-data">
        <f:hidden path="id"/>
        <f:hidden path="detailId"/>
        <div class="control-group span6">
            <label class="control-label" for="nama">Contact Person</label>
            <div class="controls">
                <f:input path="nama"/>
                <f:errors path="nama" class="error has-error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="telepon">Telepon</label>
            <div class="controls">
                <f:input path="telepon" data-mask="number"/>
                <f:errors path="telepon" class="error has-error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="jenisObject">Jenis Aktiva</label>
            <div class="controls">
                <f:select path="jenisObject">
                    <f:option value="">-- Select --</f:option>
                    <f:options items="${listJenisAktiva}" itemLabel="label" itemValue="label"/>
                </f:select>
                <f:errors path="jenisObject" class="error has-error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="alamat">Alamat</label>
            <div class="controls">
                <f:input path="alamat"/>
                <f:errors path="alamat" class="error has-error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="kondisiBangunan">Kondisi Bangunan</label>
            <div class="controls">
                <f:select path="kondisiBangunan">
                    <f:option value="">-- Select --</f:option>
                    <f:options items="${listKondisi}" itemLabel="label" itemValue="label"/>
                </f:select>
                <f:errors path="kondisiBangunan" class="error has-error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="kondisiLingkungan">Kondisi Lingkungan</label>
            <div class="controls">
                <f:select path="kondisiLingkungan">
                    <f:option value="">-- Select --</f:option>
                    <f:options items="${listKondisi}" itemLabel="label" itemValue="label"/>
                </f:select>
                <f:errors path="kondisiLingkungan" class="error has-error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="keterangan">Keterangan Tambahan</label>
            <div class="controls">
                <f:input path="keterangan"/>
                <f:errors path="keterangan" class="error has-error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="image">Image Pembanding</label>
            <div class="controls">
                <div class="input-append">
                    <c:if test="${fn:length(formBean.image) > 0}">
                    <a target="_blank" href="preview-pembanding?id=${formBean.id}" class="btn btn-success"><i class="icon-white icon-picture"></i></a>
                    </c:if>
                    <c:if test="${showButton}">
                    <f:input path="image" type="file"/>
                    </c:if>
                </div>
                <f:errors path="image" class="error has-error"/>
            </div>
        </div>
        <div class="control-group span12">
            <label class="control-label">&nbsp;</label>
            <div class="controls">
                <c:if test="${showButton}">
                <button type="submit" class="btn btn-success"><i class="icon-white icon-ok-circle"></i> Save</button>
                <button id="btn-reset" type="reset" class="btn btn-danger"><i class="icon-white icon-refresh"></i> Reset</button>
                </c:if>
                <button type="button" class="btn btn-warning" onclick="gotoUrl('show-pembanding?detailId=${formBean.detailId}')"><i
                        class="icon-white icon-step-backward"></i> Back
                </button>
            </div>
        </div>
    </f:form>
</div>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<script src="${ctxPath}/js/jquery.inputmask.bundle.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
    });
</script>
</body>
</html>