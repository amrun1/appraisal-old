<%@ taglib prefix="f"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s"   uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.permata.appraisal2.common.AppConstans" %>
<c:set var="taskPosition" value="<%= AppConstans.TaskPosition.APP_ADMIN %>"/>
<c:set var="isGroup" value="${formBean.taskList == taskPosition}"></c:set>
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
    <title>Detail</title>
</head>
<body>
<div class="container">
    <div class="page-header">
        <h3>Order Data Umum</h3>
    </div>
    <f:form method="post" modelAttribute="formBean" action="claim" class="form-horizontal">
    <div class="controls-row panel">
        <div class="control-group span6">
            <label class="control-label" for="orderNumber">No. Order</label>
            <div class="controls">
                <div class="input-append">
                    <f:input path="orderNumber" type="text" class="form-control uneditable-input" readonly="true"/>
                </div>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="name">Nama Debitur</label>
            <div class="controls">
                <f:input path="name" type="text" class="form-control" readonly="true"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="customerStatusName">Status Nasabah</label>
            <div class="controls">
                <f:input path="customerStatusName" type="text" class="form-control" readonly="true"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="collateralStatusName">Status Jaminan</label>
            <div class="controls">
                <f:input path="collateralStatusName" type="text" class="form-control" readonly="true"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="appraisalTypeName">Jenis Approval</label>
            <div class="controls">
                <f:input path="appraisalTypeName" type="text" class="form-control" readonly="true"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="appraisalTypeName">Jenis Laporan</label>
            <div class="controls">
                <%--<input class="form-control" readonly="readonly" value="" type="text">--%>
                <f:input path="typeReport" type="text" class="form-control" readonly="true"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="branchBussinesName">Cabang Bisnis</label>
            <div class="controls">
                <f:input path="branchBussinesName" type="text" class="form-control" readonly="true"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="plafonMarketing">Plafond Kredit</label>
            <div class="controls">
                <f:input path="plafonMarketing" readonly="true" data-mask="currency"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="contactName">Nama Contact Person</label>
            <div class="controls">
                <f:input path="contactName" type="text" class="form-control" readonly="true"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="contactTelp">No. Telp Contact Person</label>
            <div class="controls">
                <f:input path="contactTelp" type="text" class="form-control" readonly="true"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="catatan">Catatan dari Bisnis Unit</label>
            <div class="controls">
                <f:input path="catatan" type="text" class="form-control" readonly="true"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="marketingTelp">No. Telepon Marketing</label>
            <div class="controls">
                <f:input path="marketingTelp" type="text" class="form-control uneditable-input" readonly="true"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="marketingHp">No. HP Marketing</label>
            <div class="controls">
                <f:input path="marketingHp" type="text" class="form-control uneditable-input" readonly="true"/>
            </div>
        </div>
        <div class="control-group span12">
            <label class="control-label">Collateral ID</label>
            <div class="controls">
                <label class="form-control">(diisi oleh Appraisal Admin)</label>
            </div>
        </div>
    </div>
    <div class="page-header">
        <h3>Order Data Detail</h3>
    </div>
    <div class="controls-row panel">
        <div class="control-group span6">
            <label class="control-label" for="detail.categoryName">Jenis Kategori</label>
            <div class="controls">
                <f:input path="detail.categoryName" readonly="true"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="detail.assetTypeName">Jenis Aktiva</label>
            <div class="controls">
                <f:input path="detail.assetTypeName" readonly="true"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="detail.homeApartment">Perumahan / Apartemen</label>
            <div class="controls">
                <f:input path="detail.homeApartment" type="text" class="form-control" readonly="true"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="detail.clusterTower">Cluster / Tower</label>
            <div class="controls">
                <f:input path="detail.clusterTower" type="text" class="form-control" readonly="true"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="detail.blokFloor">Block / Lantai</label>
            <div class="controls">
                <f:input path="detail.blokFloor" type="text" class="form-control" readonly="true"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="name">Alamat Agunan</label>
            <div class="controls">
                <f:textarea path="detail.alamat" rows="3" cssClass="input-large" readonly="true"/>
            </div>
        </div>
        <div class="control-group span12">&nbsp;</div>
        <div class="control-group span6">
            <label class="control-label" for="detail.rw">RW</label>
            <div class="controls">
                <f:input path="detail.rw" cssClass="input-mini" maxlength="4" data-mask="number" readonly="true"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="detail.rt">RT</label>
            <div class="controls">
                <f:input path="detail.rt" cssClass="input-mini" maxlength="4" data-mask="number" readonly="true"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="detail.number">Nomor</label>
            <div class="controls">
                <f:input path="detail.number" cssClass="input-mini" maxlength="4" data-mask="number" readonly="true"/>
            </div>
        </div>
        <div class="control-group span12">&nbsp;</div>
        <div class="control-group span6">
            <label class="control-label" for="detail.zipCode">Kode POS</label>
            <div class="controls">
                <f:input path="detail.zipCode" type="text" class="form-control" placeholder="Kode POS" readonly="true"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="detail.province">Propinsi</label>
            <div class="controls">
                <f:input path="detail.province" type="text" class="form-control" readonly="true"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="detail.city">Kota / Kabupaten</label>
            <div class="controls">
                <f:input path="detail.city" type="text" class="form-control" readonly="true"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="detail.district">Kecamatan</label>
            <div class="controls">
                <f:input path="detail.district" type="text" class="form-control" readonly="true"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="detail.subDistrict">Kelurahan</label>
            <div class="controls">
                <f:input path="detail.subDistrict" type="text" class="form-control" readonly="true"/>
            </div>
        </div>
    </div>
    <div style="clear: both"></div>
    <div class="page-header">
        <h3>Order Data Lampiran</h3>
    </div>
    <table class="table dataTable">
        <thead>
        <tr>
            <th>Jenis Dokumen</th>
            <th>Nama File</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${formBean.attactments}" var="row" varStatus="status">
            <tr id="trid${row.id}" class="${status.index % 2 == 0 ? "even" : "odd"}">
                <td>${row.documentTypeName}</td>
                <td><a href="${ctxPath}/data-attactment/preview?id=${row.id}"
                       target="_blank">${row.name}.${row.extension}</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div style="margin-bottom: 20px;">
        <button type="button" class="btn btn-warning"
                onclick="gotoUrl('view?id=${formBean.id}')"><i
                class="icon-white icon-step-backward"></i> Back
        </button>
    </div>
    </f:form>
</div>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<script src="${ctxPath}/js/jquery.inputmask.bundle.min.js"></script>
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