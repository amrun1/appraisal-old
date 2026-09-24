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
    <link href="${ctxPath}/js/bootstrap-datepicker-1.6.4/css/bootstrap-datepicker.min.css" rel="stylesheet">
    <link href="${ctxPath}/css/main-app.css" rel="stylesheet">

    <!--[if lt IE 9]>
    <script src="${ctxPath}/js/html5shiv.js"></script>
    <script src="${ctxPath}/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <f:form id="form-lampiran-mesin" method="post" modelAttribute="formBean" action="save-lampiran" class="form-horizontal">
        <f:hidden path="id"/>
        <f:hidden path="idLpaMesin"/>
        <div class="control-group span6">
            <label class="control-label" for="jenis">Jenis Mesin</label>
            <div class="controls">
                <f:input path="jenis"/>
                <f:errors path="jenis" cssClass="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="merk">Merk</label>
            <div class="controls">
                <f:input path="merk"/>
                <f:errors path="merk" cssClass="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="typeModel">Type</label>
            <div class="controls">
                <f:input path="typeModel"/>
                <f:errors path="typeModel" cssClass="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="noSeri">No. Seri</label>
            <div class="controls">
                <f:input path="noSeri"/>
                <f:errors path="noSeri" cssClass="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="tahun">Tahun Pembuatan</label>
            <div class="controls">
                <f:input path="tahun" data-mask="number" maxlength="4" cssClass="input-mini"/>
                <f:errors path="tahun" cssClass="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="asal">Asal Pembuatan</label>
            <div class="controls">
                <f:input path="asal"/>
                <f:errors path="asal" cssClass="error help-block"/>
            </div>
        </div>
       <%-- <div class="control-group span6">
            <label class="control-label" for="jumlah">Jumlah Unit</label>
            <div class="controls">
                <f:input path="jumlah" data-mask="luas-tanah" cssClass="input-mini"/>
                <f:errors path="jumlah" cssClass="error help-block"/>
            </div>
        </div>--%>
        <div class="control-group span6">
            <label class="control-label" for="kegunaan">Kegunaan</label>
            <div class="controls">
                <f:input path="kegunaan"/>
                <f:errors path="kegunaan" cssClass="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="kondisi">Kondisi Mesin</label>
            <div class="controls">
                <f:select path="kondisi">
                    <f:option value="" label="-- Select --"/>
                    <f:options items="${listKondisi}" itemValue="label" itemLabel="label"/>
                </f:select>
                <f:errors path="kondisi" cssClass="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="rangkaian">Rangkaian Mesin</label>
            <div class="controls">
                <f:select path="rangkaian">
                    <f:option value="" label="-- Select --"/>
                    <f:options items="${listRangkaian}" itemValue="label" itemLabel="label"/>
                </f:select>
                <f:errors path="rangkaian" cssClass="error help-block"/>
            </div>
        </div>
        <%--<div class="control-group span6">
            <label class="control-label" for="kegunaan">Waktu Penggunaan</label>
            <div class="controls">
                <f:select path="kegunaan">
                    <f:option value="" label="-- Select --"/>
                    <f:options items="${listPenggunaan}" itemValue="label" itemLabel="label"/>
                </f:select>
                <f:errors path="kegunaan" cssClass="error help-block"/>
            </div>
        </div>--%>
        <div class="control-group span6" style="margin-top: 15px;">
            <label class="control-label" for="noInvoice">No. Invoice / Faktur</label>
            <div class="controls">
                <f:input path="noInvoice"/>
                <f:errors path="noInvoice" cssClass="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="tanggal">Tanggal Invoice / Faktur</label>
            <div class="controls">
                <f:input path="tanggal" cssClass="input-small" data-provide="datepicker"  class="tutup-otomatis" data-date-end-date="0d" placeholder="DD-MM-YYYY" data-date-format="dd-mm-yyyy"/>
                <f:errors path="tanggal" cssClass="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="pemilik">Pemilik Sesuai Faktur</label>
            <div class="controls">
                <f:input path="pemilik"/>
                <f:errors path="pemilik" cssClass="error help-block"/>
            </div>
        </div>
        <div class="control-group span6" style="margin-top: 15px;">
            <label class="control-label" for="nilaiDoc">Nilai Dokumen Pasar</label>
            <div class="controls">
                <f:input path="nilaiDoc" cssClass="input-small" data-mask="luas-tanah"/>
                <f:errors path="nilaiDoc" cssClass="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="nilaiFisik">Nilai Fisik Pasar</label>
            <div class="controls">
                <f:input path="nilaiFisik" cssClass="input-small" data-mask="luas-tanah"/>
                <f:errors path="nilaiFisik" cssClass="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label">&nbsp;</label>
            <div class="controls">
                <button type="submit" class="btn btn-success"><i class="icon-white icon-check"></i> Save</button>
                <button type="reset" class="btn btn-info"><i class="icon-white icon-repeat"></i> Reset</button>
            </div>
        </div>
    </f:form>
</div>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<script src="${ctxPath}/js/jquery.inputmask.bundle.min.js"></script>
<script src="${ctxPath}/js/bootstrap-datepicker-1.6.4/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
//        window.parent.refreshTableMesin();
        <c:if test="${inquery}" >
            $('#form-lampiran-mesin').find('input, textarea, select,button').prop('disabled', true);
        </c:if>
    });
</script>
</body>
</html>