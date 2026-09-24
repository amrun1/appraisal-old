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
    <link href="${ctxPath}/css/main-app.css" rel="stylesheet">

    <!--[if lt IE 9]>
    <script src="${ctxPath}/js/html5shiv.js"></script>
    <script src="${ctxPath}/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="">
    <div class="page-header">
        <h3>Survey Data Lingkungan</h3>
    </div>
    <c:if test="${not empty msg}">
        <div class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${msg}</strong>
        </div>
    </c:if>
    <f:form method="post" modelAttribute="formBean" action="save-lingkungan?${_csrf.parameterName}=${_csrf.token}"
            class="form-horizontal"
            enctype="multipart/form-data">
        <f:hidden path="id"/>
        <f:hidden path="detailId"/>
        <div class="control-group">
            <label class="control-label" for="sekitar">Lingkungan Sekitar</label>
            <div class="controls">
                <f:select path="sekitar">
                    <f:option value="">-- Select --</f:option>
                    <f:options items="${listLingkunganSekitar}" itemLabel="label" itemValue="label"/>
                </f:select>
                <f:errors path="sekitar" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="kepadatan">Kepadatan Penduduk</label>
            <div class="controls">
                <f:select path="kepadatan">
                    <f:option value="">-- Select --</f:option>
                    <f:options items="${listKepadatanPenduduk}" itemLabel="label" itemValue="label"/>
                </f:select>
                <f:errors path="kepadatan" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="kemacetan">Kemacetan Jalan</label>
            <div class="controls">
                <f:select path="kemacetan">
                    <f:option value="">-- Select --</f:option>
                    <f:options items="${listTingkatKemacetan}" itemLabel="label" itemValue="label"/>
                </f:select>
                <f:errors path="kemacetan" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="rencanaJalan">Terkena Rencana Jalan</label>
            <div class="controls">
                <f:select path="rencanaJalan">
                    <f:option value="">-- Select --</f:option>
                    <f:option value="0">Tidak</f:option>
                    <f:option value="1">Ya</f:option>
                </f:select>
                <f:errors path="kemacetan" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="lebarJalan">Lebar Jalan</label>
            <div class="controls">
                <f:input path="lebarJalan" cssClass="input-mini" data-mask="luas-tanah"/>
                <f:errors path="lebarJalan" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="gsb">Bangunan Terkena GSB</label>
            <div class="controls">
                <f:input path="gsb" cssClass="input-mini" data-mask="luas-tanah"/>
                <f:errors path="gsb" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="gss">Garis Sempadan Sungai (GSS)</label>
            <div class="controls">
                <f:input path="gss" cssClass="input-mini" data-mask="luas-tanah"/>
                <f:errors path="gss" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="roda4">Dilalui Roda 4</label>
            <div class="controls">
                <f:select path="roda4">
                    <f:option value="">-- Select --</f:option>
                    <f:option value="0">Tidak</f:option>
                    <f:option value="1">Ya</f:option>
                </f:select>
                <f:errors path="roda4" class="error has-error"/>
            </div>
        </div>
        <c:if test="${showButton}">
            <div class="control-group">
                <label class="control-label">&nbsp;</label>
                <div class="controls">
                    <button type="submit" class="btn btn-success"><i class="icon-white icon-ok-circle"></i> Save
                    </button>
                    <button type="reset" class="btn btn-warning"><i class="icon-white icon-refresh"></i> Reset</button>
                </div>
            </div>
        </c:if>
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