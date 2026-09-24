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
</head>
<body>
<div class="container">
    <div class="page-header">
        <h3>Survey Data Kendaraan </h3>
    </div>
    <c:if test="${not empty msg}">
        <div class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${msg}</strong>
        </div>
    </c:if>
    <f:form method="post" modelAttribute="formBean" action="save-kendaraan" class="form-horizontal">
        <f:hidden path="id"/>
        <f:hidden path="detailId"/>
        <div class="control-group span6">
            <label class="control-label" for="jenisKendaraan">Jenis Kendaraan</label>
            <div class="controls">
                <f:input path="jenisKendaraan"/>
                <f:errors path="jenisKendaraan" cssClass="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="merkKendaraan">Merk Kendaraan</label>
            <div class="controls">
                <f:input path="merkKendaraan"/>
                <f:errors path="merkKendaraan" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="modelType">Model / Type</label>
            <div class="controls">
                <f:input path="modelType"/>
                <f:errors path="modelType" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="isiSilinder">Isi Silinder</label>
            <div class="controls">
                <f:input path="isiSilinder" cssClass="input-small" data-mask="number"/>
                <f:errors path="isiSilinder" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="tahunPembuatan">Tahun Pembuatan</label>
            <div class="controls">
                <f:input path="tahunPembuatan" cssClass="input-mini" maxlength="4" data-mask="number"/>
                <f:errors path="tahunPembuatan" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="warna">Warna</label>
            <div class="controls">
                <f:input path="warna"/>
                <f:errors path="warna" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="penggunaan">Penggunaan</label>
            <div class="controls">
                <f:select path="penggunaan">
                    <f:option value="">-- Select --</f:option>
                    <f:options items="${listPenggunaan}" itemLabel="label" itemValue="label"/>
                </f:select>
                <f:errors path="penggunaan" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="kilometer">Kilometer</label>
            <div class="controls">
                <div class="input-append">
                    <f:input path="kilometer" cssClass="input-small" data-mask="luas-tanah"/>
                    <span class="btn btn-info">KM</span>
                </div>
                <f:errors path="kilometer" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="noPolisi">No Polisi</label>
            <div class="controls">
                <f:input path="noPolisi"/>
                <f:errors path="noPolisi" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="noRangka">No Rangka</label>
            <div class="controls">
                <f:input path="noRangka"/>
                <f:errors path="noRangka" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="noMesin">No Mesin</label>
            <div class="controls">
                <f:input path="noMesin"/>
                <f:errors path="noMesin" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="bahanBakar">Bahan Bakar</label>
            <div class="controls">
                <f:input path="bahanBakar"/>
                <f:errors path="bahanBakar" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6" style="margin-bottom: 4px;">
            <label class="control-label" for="catatan">Catatan</label>
            <div class="controls">
                <f:textarea path="catatan" cols="2"/>
                <f:errors path="catatan" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span12">
            <label class="control-label">&nbsp;</label>
            <div class="controls">
                <c:if test="${showButton && !inquery}">
                    <button type="submit" class="btn btn-success"><i class="icon-white icon-ok-circle"></i> Save</button>
                    <button id="btn-reset" type="reset" class="btn btn-danger"><i class="icon-white icon-refresh"></i> Reset</button>
                </c:if>
                <button type="button" class="btn btn-warning"
                        onclick="gotoUrl('show-kendaraan?detailId=${formBean.detailId}&inquery=${inquery}')"><i
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