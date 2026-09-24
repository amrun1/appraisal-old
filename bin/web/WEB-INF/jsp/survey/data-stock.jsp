<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.permata.appraisal2.model.PersonLevel" %>
<%@ page import="com.permata.appraisal2.common.AppConstans" %>
<c:set var="kendaraan" value="<%=AppConstans.JenisKategoriCode.KENDARAAN%>"/>
<c:set var="lnb" value="<%=AppConstans.JenisKategoriCode.LNB%>"/>
<c:set var="surveyor" value="<%=PersonLevel.APPRAISAL_SURVEYOR%>"/>
<c:set var="showButton" value="false"/>

<c:if test="${roleActive.level == surveyor}">
    <c:set var="showButton" value="true"/>
</c:if>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
    <title>Appraisal Stock</title>
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
    <link href="${ctxPath}/js/datatables.min.css" rel="stylesheet">
    <link href="${ctxPath}/js/bootstrap-datepicker-1.6.4/css/bootstrap-datepicker.min.css" rel="stylesheet">
    <link href="${ctxPath}/css/main-app.css" rel="stylesheet">

    <!--[if lt IE 9]>
    <script src="${ctxPath}/js/html5shiv.js"></script>
    <script src="${ctxPath}/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <div class="page-header hide">
        <h3>Survey Data Stock</h3>
    </div>
    <c:if test="${not empty msg}">
        <div class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${msg}</strong>
        </div>
    </c:if>
    <f:form method="post" modelAttribute="formBean" action="save-survey-stock?${_csrf.parameterName}=${_csrf.token}"
            class="form-horizontal"
            enctype="multipart/form-data">
        <f:hidden path="id"/>
        <f:hidden path="idDetail"/>

        <div class="control-group span6">
            <label class="control-label" for="jenisPersedian">Jenis Barang</label>
            <div class="controls">
                <f:input path="jenisPersedian" type="text" class="form-control" placeholder="Jenis Barang"/>
                <f:errors path="jenisPersedian" class="error has-error"/>
            </div>
        </div>

        <div class="control-group span6">
            <label class="control-label" for="asalPembuatan">Asal Pembuatan</label>
            <div class="controls">
                <f:input path="asalPembuatan"/>
                <f:errors path="asalPembuatan" cssClass="help-block"/>
            </div>
        </div>

        <div class="control-group span6">
            <label class="control-label" for="penempatanBarang">Penempatan Barang</label>
            <div class="controls">
                <f:select path="penempatanBarang">
                    <f:option value="" label="-- Select --"/>
                    <f:options items="${listPenempatanBarang}" itemValue="value" itemLabel="label"/>
                </f:select>
                <f:errors path="penempatanBarang" cssClass="help-block"/>
            </div>
        </div>

        <div class="control-group span6">
            <label class="control-label" for="kemasanBarang">Kemasan Barang</label>
            <div class="controls">
                <f:select path="kemasanBarang">
                    <f:option value="" label="-- Select --"/>
                    <f:options items="${listKemasanBarang}" itemValue="value" itemLabel="label"/>
                </f:select>
                <f:errors path="kemasanBarang" cssClass="help-block"/>
            </div>
        </div>

        <div class="control-group span6">
            <label class="control-label" for="kondisiBarang">Kondisi Barang</label>
            <div class="controls">
                <f:select path="kondisiBarang">
                    <f:option value="" label="-- Select --"/>
                    <f:options items="${listKondisiBarang}" itemValue="value" itemLabel="label"/>
                </f:select>
                <f:errors path="kondisiBarang" cssClass="help-block"/>
            </div>
        </div>

        <div class="control-group span6">
            <label class="control-label" for="metodePemeriksaan">Metode Pemeriksaan</label>
            <div class="controls">
                <f:select path="metodePemeriksaan">
                    <f:option value="" label="-- Select --"/>
                    <f:options items="${listMetodePemeriksaan}" itemValue="value" itemLabel="label"/>
                </f:select>
                <f:errors path="metodePemeriksaan" cssClass="help-block"/>
            </div>
        </div>

        <div class="control-group span6">
            <label class="control-label" for="jumlahPersedian">Jumlah Persediaan</label>
            <div class="controls">
                <f:select path="jumlahPersedian">
                    <f:option value="" label="-- Select --"/>
                    <f:options items="${listJumlahPersediaan}" itemValue="value" itemLabel="label"/>
                </f:select>
                <f:errors path="jumlahPersedian" cssClass="help-block"/>
            </div>
        </div>

        <div class="control-group span6">
            <label class="control-label" for="kondisiPenyimpanan">Kondisi Tempat Operasional</label>
            <div class="controls">
                <f:select path="kondisiPenyimpanan">
                    <f:option value="" label="-- Select --"/>
                    <f:options items="${listKondisiPenyimpanan}" itemValue="value" itemLabel="label"/>
                </f:select>
                <f:errors path="kondisiPenyimpanan" cssClass="help-block"/>
            </div>
        </div>

        <div class="control-group span6">
            <label class="control-label" for="lingkunganSekitar">Lingkungan Sekitar</label>
            <div class="controls">
                <f:select path="lingkunganSekitar">
                    <f:option value="" label="-- Select --"/>
                    <f:options items="${listLingkunganPenyimpanan}" itemValue="value" itemLabel="label"/>
                </f:select>
                <f:errors path="lingkunganSekitar" cssClass="help-block"/>
            </div>
        </div>

        <div class="control-group span6">
            <label class="control-label" for="peralatanGudang">Peralatan Gudang</label>
            <div class="controls">
                <f:select path="peralatanGudang">
                    <f:option value="" label="-- Select --"/>
                    <f:options items="${listPeralatanGudang}" itemValue="value" itemLabel="label"/>
                </f:select>
                <f:errors path="peralatanGudang" cssClass="help-block"/>
            </div>
        </div>

        <div class="control-group span6">
            <label class="control-label" for="catatan">Catatan</label>
            <div class="controls">
                <f:input path="catatan" type="text" class="form-control" placeholder="Catatan"/>
                <f:errors path="catatan" class="error has-error"/>
            </div>
        </div>

        <div class="input-group">
            <div class="input-group-addon">
                <span class="icon icon-"></span>
            </div>
        </div>
        <c:if test="${showButton && !inquery}">
            <div class="control-group span12">
                <label class="control-label">&nbsp;</label>
                <div class="controls">
                    <button type="submit" class="btn btn-success"><i class="icon-white icon-ok-circle"></i> Save
                    </button>
                    <button id="btn-reset" type="reset" class="btn btn-warning"><i class="icon-white icon-refresh"></i>
                        Reset
                    </button>
                </div>
            </div>
        </c:if>
    </f:form>

</div>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<script src="${ctxPath}/js/bootstrap-datepicker-1.6.4/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
    // $(document).off('.datepicker.data-api');
    $(document).ready(function () {

    });
</script>
</body>
</html>