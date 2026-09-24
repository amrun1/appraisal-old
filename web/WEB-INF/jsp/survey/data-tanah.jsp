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
        <h3>Survey Data Tanah</h3>
    </div>
    <c:if test="${not empty msg}">
        <div class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${msg}</strong>
        </div>
    </c:if>
    <f:form method="post" modelAttribute="formBean" action="save-tanah?${_csrf.parameterName}=${_csrf.token}" class="form-horizontal"
            enctype="multipart/form-data">
        <f:hidden path="id"/>
        <f:hidden path="detailId"/>
        <div class="control-group span6">
            <label class="control-label" for="luas">Luas Tanah</label>
            <div class="controls">
                <div class="input-append">
                    <f:input path="luas" cssClass="input-small" data-mask="luas-tanah"/>
                    <span class="btn btn-info">M<sup>2</sup></span>
                </div>
                <f:errors path="luas" class="error has-error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="bentuk">Bentuk Tanah</label>
            <div class="controls">
                <f:select path="bentuk">
                    <f:option value="">-- Select --</f:option>
                    <f:options items="${listBentukTanah}" itemLabel="label" itemValue="label"/>
                </f:select>
                <f:errors path="bentuk" class="error has-error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="kontur">Kontur Tanah</label>
            <div class="controls">
                <f:select path="kontur">
                    <f:option value="">-- Select --</f:option>
                    <f:options items="${listKonturTanah}" itemLabel="label" itemValue="label"/>
                </f:select>
                <f:errors path="kontur" class="error has-error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="ketinggian">Ketinggian Dari Jalan</label>
            <div class="controls">
                <f:select path="ketinggian">
                    <f:option value="">-- Select --</f:option>
                    <f:options items="${listKetinggianTanah}" itemLabel="label" itemValue="label"/>
                </f:select>
                <f:errors path="ketinggian" class="error has-error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="batas">Kesesuaian Batas Tanah</label>
            <div class="controls">
                <f:select path="batas">
                    <f:option value="">-- Select --</f:option>
                    <f:options items="${listBatasTanah}" itemLabel="label" itemValue="label"/>
                </f:select>
                <f:errors path="batas" class="error has-error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="posisi">Posisi Tanah</label>
            <div class="controls">
                <f:select path="posisi">
                    <f:option value="">-- Select --</f:option>
                    <f:options items="${listPosisiTanah}" itemLabel="label" itemValue="label"/>
                </f:select>
                <f:errors path="posisi" class="error has-error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="kondisi">Kondisi Tanah</label>
            <div class="controls">
                <f:select path="kondisi">
                    <f:option value="">-- Select --</f:option>
                    <f:options items="${listKondisiTanah}" itemLabel="label" itemValue="label"/>
                </f:select>
                <f:errors path="kondisi" class="error has-error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="jenis">Jenis Tanah</label>
            <div class="controls">
                <f:select path="jenis">
                    <f:option value="">-- Select --</f:option>
                    <f:options items="${listJenisTanah}" itemLabel="label" itemValue="label"/>
                </f:select>
                <f:errors path="jenis" class="error has-error"/>
            </div>
        </div>

        <div class="control-group span6">
            <label class="control-label" for="peruntukanTanah">Peruntukan</label>
            <div class="controls">
                <f:select path="peruntukanTanah">
                    <f:option value="">-- Select --</f:option>
                    <f:options items="${listPeruntukanTanah}" itemLabel="label" itemValue="label"/>
                </f:select>
                <f:errors path="peruntukanTanah" class="error has-error"/>
            </div>
        </div>

        <div class="control-group span12">
            <label class="control-label">&nbsp;</label>
            <div class="controls">
                <c:if test="${showButton}">
                <button type="submit" class="btn btn-success"><i class="icon-white icon-ok-circle"></i> Save</button>
                <button id="btn-reset" type="reset" class="btn btn-danger"><i class="icon-white icon-refresh"></i> Reset</button>
                </c:if>
                <button type="button" class="btn btn-warning" onclick="gotoUrl('show-tanah?detailId=${formBean.detailId}')"><i
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
        $('#btn-manual').click(function () {
            $("#detail-from").val("MANUAL");
            return false;
        });
        $('#btn-toggle').click(function () {
            $('#div-info').toggle('slow');
        });
        $(".btn-delete-image ").click(function () {
            var t = $(this);
            var p = t.parent().parent();
            p.find('input[id=input-delete]').val(true);
            p.find('input[type=file]').prop( "disabled", true );
        })
        $('#btn-reset').click(function () {
            $('input[type=file]').prop( "disabled", false );
        })
    });
</script>
</body>
</html>