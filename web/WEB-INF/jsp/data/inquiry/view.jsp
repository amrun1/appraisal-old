<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.permata.appraisal2.common.AppConstans" %>
<%@ page import="com.permata.appraisal2.model.PersonLevel" %>
<c:set var="taskPosition" value="<%= AppConstans.TaskPosition.APP_VALUER %>"/>
<c:set var="aproval" value="<%= AppConstans.TaskPosition.APP_APPROVAL %>"/>
<c:set var="closed" value="<%= AppConstans.TaskPosition.CLOSE %>"/>
<c:set var="marketing" value="<%= PersonLevel.MARKETING %>"/>
<c:set var="isMarketing" value="${roleActive.level == marketing}"/>
<c:set var="isEksternal" value="${fn:containsIgnoreCase(formBean.appraisalTypeCategory, 'eksternal')}"/>
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

    <!--[if gte IE 9]><!-->
    <link rel="stylesheet" media="screen" href="${ctxPath}/css/glyphicons/glyphicons.min.css">
    <!--<![endif]-->
    <!--[if lt IE 9]>
    <script src="${ctxPath}/js/html5shiv.js"></script>
    <script src="${ctxPath}/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <div class="page-header-page">
        <div class="absolute-right">
			<div class="btn-group">
                <button type="button" class="btn btn-info" onclick="window.open('laporan-order-detail?PIdDataDetail=${formBean.detail.id}','_blank')"><i class="icon-white icon-file"></i>View Order Detail</button>
				<button type="button" class="btn btn-warning" onclick="gotoUrl('list')"><i
						class="icon-white icon-step-backward"></i> Back
				</button>
			</div>
        </div>
        <h1>Inquery Data Detail</h1>
    </div>
    <f:form method="post" modelAttribute="formBean" action="claim" class="form-horizontal" style="margin-top:60px">
        <f:hidden path="id"/>
        <div style="border:1px solid #ddd; padding: 8px;" class="controls-row">
            <div class="control-group span6">
                <label class="control-label" for="id">No. Order</label>
                <div class="controls">
                    <f:input path="orderNumber" type="text" class="form-control" readonly="true"/>
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
                <label class="control-label" for="appraisalTypeCategory">Kategori Appraisal</label>
                <div class="controls">
                    <f:input path="appraisalTypeCategory" class="form-control" readonly="true"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="appraisalTypeName">Jenis Appraisal</label>
                <div class="controls">
                    <f:input path="appraisalTypeName" type="text" class="form-control" readonly="true"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="typeReport">Jenis Laporan</label>
                <div class="controls">
                    <f:input path="typeReport" readonly="true"/>
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
                    <f:input path="marketingTelp" type="text" class="form-control" readonly="true"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="marketingHp">No. HP Marketing</label>
                <div class="controls">
                    <f:input path="marketingHp" type="text" class="form-control" readonly="true"/>
                </div>
            </div>
            <div class="controls-row span12">
                <label class="control-label" for="collateralId">Collateral ID</label>
                <div class="controls">
                    <f:input path="collateralId" type="text" class="form-control" readonly="true"/>
                </div>
            </div>
        </div>
        <div class="page-header" id="header-survey">
            <h3><img src="${ctxPath}/images/survey.jpg">Data Detail & Survey</h3>
        </div>
        <div style="border:1px solid #ddd; padding: 8px;" class="controls-row">
            <div class="control-group span6">
                <label class="control-label">No. Detail</label>
                <div class="controls">
                    <input type="text" value="${formBean.detail.id}" class="form-control" readonly="true">
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label">Kategori Aktiva</label>
                <div class="controls">
                    <f:input path="detail.categoryName" readonly="true"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label">Jenis Aktiva</label>
                <div class="controls">
                    <input type="text" value="${formBean.detail.assetTypeName}" class="form-control" readonly="true">
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label">Komplek / Perumahan</label>
                <div class="controls">
                    <input type="text" value="${formBean.detail.homeApartment}" class="form-control" readonly="true">
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label">Cluster / Tower</label>
                <div class="controls">
                    <input type="text" value="${formBean.detail.clusterTower}" class="form-control" readonly="true">
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label">Block / Lantai</label>
                <div class="controls">
                    <input type="text" value="${formBean.detail.blokFloor}" class="form-control" readonly="true">
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label">Alamat</label>
                <div class="controls">
                    <input type="text" value="${formBean.detail.alamat}" class="form-control" readonly="true">
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label">RW</label>
                <div class="controls">
                    <f:input path="detail.rw" readonly="true"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label">RT</label>
                <div class="controls">
                    <f:input path="detail.rt" readonly="true"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label">Nomor</label>
                <div class="controls">
                    <f:input path="detail.number" readonly="true"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label">Kode POS</label>
                <div class="controls">
                    <f:input path="detail.zipCode" readonly="true"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label">Propinsi</label>
                <div class="controls">
                    <input type="text" value="${formBean.detail.province}" class="form-control" readonly="true">
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label">Kota</label>
                <div class="controls">
                    <input type="text" value="${formBean.detail.city}" class="form-control" readonly="true">
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label">Kecamatan</label>
                <div class="controls">
                    <input type="text" value="${formBean.detail.district}" class="form-control" readonly="true">
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label">Kelurahan</label>
                <div class="controls">
                    <input type="text" value="${formBean.detail.subDistrict}" class="form-control" readonly="true">
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label">Input Data Survey</label>
                <div class="controls">
                    <input type="text" value="${formBean.detail.from}" class="form-control" readonly="true">
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label">Status Survey</label>
                <div class="controls">
                    <input type="text" value="${formBean.detail.surveyStatus}" class="form-control" readonly="true">
                </div>
            </div>

            <div class="control-group span12">
                <label class="control-label">&nbsp;</label>
                <div class="controls">
                    <a a href="#myModal" role="button" data-toggle="modal" class="btn btn-info"><i
                            class="icon-white icon-share"></i> Attactment
                        <span class="badge badge-warning">${fn:length(formBean.attactments)}</span></a>
                    <c:if test="${formBean.surveyBy != null}">
                        <%--com.sun.xml.internal.bind.v2.TODO fixing ke report bukan ke screen--%>
                    <%--<a href="${ctxPath}/app-approval/laporan-survey?PIdDataDetail=${formBean.detail.id}&survey=" class="btn btn-info"><i class="icon-white icon-share"></i> Data Survey</a>--%>
                    </c:if>
                </div>
            </div>
        </div>
    </f:form>
    <div class="page-header">
        <h3><img src="${ctxPath}/images/survey.jpg">Data LPA</h3>
    </div>
    <c:set var="lpaUrl" value=""/>
    <c:set var="lpaUrlMenu" value="edit-lpa"/>
    <c:choose>


        <c:when test="${fn:containsIgnoreCase(formBean.appraisalTypeName, 'rab')}">
            <%--<c:set var="lpaUrl" value="${ctxPath}/lpa-rab/"/>--%>
            <c:set var="lpaUrlMenu" value="edit-lpa-rab"/>
        </c:when>
        <c:when test="${fn:containsIgnoreCase(formBean.appraisalTypeName, 'rv')}">
            <c:set var="lpaUrlMenu" value="edit-lpa-Rv"/>
        </c:when>
        <c:when test="${fn:containsIgnoreCase(formBean.appraisalTypeName, 'bv')}">
            <c:set var="lpaUrlMenu" value="edit-lpa-bv"/>
        </c:when>
        <c:when test="${fn:containsIgnoreCase(formBean.detail.categoryName, 'kendaraan')}">

            <c:set var="lpaUrlMenu" value="edit-lpa-kendaraan-new"/>
        </c:when>
        <c:when test="${fn:containsIgnoreCase(formBean.detail.categoryName, 'inspeksi stock')}">
            <c:set var="lpaUrlMenu" value="edit-lpa-inspeksi"/>
        </c:when>
        <c:when test="${fn:containsIgnoreCase(formBean.detail.categoryName, 'stock')}">
            <c:set var="lpaUrlMenu" value="edit-lpa-stock"/>
        </c:when>
        <c:when test="${fn:containsIgnoreCase(formBean.detail.categoryName, 'mesin')}">
            <c:set var="lpaUrlMenu" value="edit-lpa-mesin"/>
        </c:when>

        <c:when test="${fn:containsIgnoreCase(formBean.detail.categoryName, 'vessel') || fn:containsIgnoreCase(formBean.detail.categoryName, 'kapal')}">

            <c:set var="lpaUrlMenu" value="edit-lpa-vessel"/>
        </c:when>



    </c:choose>
    <c:choose>
        <c:when test="${formBean.completeLpa == false}">
            <i>Data LPA belum dibuat</i>
        </c:when>


        <c:otherwise>
            <c:if test="${formBean.completeLpa == true}">
                <table class="table dataTable">
                    <thead>
                    <tr>
                        <th>No LPA</th>
                        <th>Lokasi Agunan</th>
                        <th>Jenis Agunan</th>
                        <th width="60px">#</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${formBean.lpa.lpaNumber}</td>
                        <td>${formBean.lpa.lokasiFisik}</td>
                        <td>${formBean.lpa.jenisObjectPenilaiFisik}</td>
                        <td>
                            <c:if test="${!isMarketing || formBean.taskList==closed}" >
                                <a href="${lpaUrl}${lpaUrlMenu}?id=${formBean.lpa.idLpa}" type="button" class="btn btn-success"><i
                                        class="icon-white icon-edit"></i></a>
                            </c:if>
            <c:if test="${isMarketing && formBean.taskList!=closed}" >Progress</c:if>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </c:if>
        </c:otherwise>
    </c:choose>
    <c:if test="${!isMarketing}">
        <div class="page-header" style="margin-top: 20px;">
            <h3><img src="${ctxPath}/images/message.jpg"> Message History</h3>
        </div>
        <c:if test="${fn:length(formBean.messages) > 0 }">
            <table class="table dataTable">
                <thead>
                <tr>
                    <th>Tanggal</th>
                    <th>Jam</th>
                    <th>Divisi</th>
                    <th>Oleh</th>
                    <th>Message</th>
                </tr>
                </thead>
                <c:forEach items="${formBean.messages}" var="row">
                    <tr>
                        <td><fmt:formatDate type="date" value="${row.date}" pattern="dd-MM-yyyy"/></td>
                        <td><fmt:formatDate type="time" value="${row.date}" pattern="HH:mm:ss"/></td>
                        <td>${row.position}</td>
                        <td>${row.userName}</td>
                        <td>${row.message}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <div class="page-header" style="margin-top: 20px;">
            <h3><img src="${ctxPath}/images/message.jpg"> Task History</h3>
        </div>
        <c:if test="${fn:length(formBean.histories) > 0 }">
        <table class="table dataTable">
            <thead>
            <tr>
                <th>Tanggal</th>
                <th>Jam</th>
                <th>Oleh</th>
                <th>Keterangan</th>
            </tr>
            </thead>
            <c:forEach items="${formBean.histories}" var="row">
                <tr>
                    <td><fmt:formatDate type="date" value="${row.date}" pattern="dd-MM-yyyy"/></td>
                    <td><fmt:formatDate type="time" value="${row.date}" pattern="HH:mm:ss"/></td>
                    <td>${row.userId}</td>
                    <td>${row.description}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    </c:if>
</div>
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">Data Lampiran</h3>
    </div>
    <div class="modal-body">
        <table class="table table-bordered dataTable">
            <thead>
            <tr>
                <th>No</th>
                <th>Jenis Dokumen</th>
                <th>Nama File</th>
            </tr>
            </thead>
            <c:forEach items="${formBean.attactments}" var="row" varStatus="status">
                <tr class="${status.index % 2 == 0 ? "even" : "odd"}">
                    <td>${status.index + 1}</td>
                    <td>${row.name}</td>
                    <td><a href="${ctxPath}/data-attactment/preview?id=${row.id}"
                           target="_blank">${row.name}.${row.extension}</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
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