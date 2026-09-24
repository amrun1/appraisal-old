<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<c:set var="isRv" value="${fn:containsIgnoreCase(formBean.appraisalTypeName, 'rv')}"/>
<c:set var="isVessel" value="${fn:containsIgnoreCase(formBean.detail.categoryName, 'vessel')}"/>
<c:set var="isKendaraan" value="${fn:containsIgnoreCase(formBean.detail.categoryName, 'kendaraan')}"/>
<c:set var="isLnb" value="${fn:containsIgnoreCase(formBean.detail.categoryName, 'Tanah dan Bangunan')}"/>
<c:set var="isInspeksi" value="${fn:containsIgnoreCase(formBean.detail.categoryName, 'Inspeksi')}"/>
<c:set var="isStock" value="${fn:containsIgnoreCase(formBean.detail.categoryName, 'stock')}"/>
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
    <div class="page-header">
        <div class="absolute-right">
			<div class="btn-group">
                <c:choose>
                    <c:when test="${isInspeksi}">
                        <button type="button" class="btn btn-info" onclick="window.open('laporan-survey?PIdDataDetail=${formBean.detail.id}&survey=inspeksi','_blank')">View Survey</button>
                    </c:when>
                    <c:when test="${isKendaraan}">
                        <button type="button" class="btn btn-info" onclick="window.open('laporan-survey?PIdDataDetail=${formBean.detail.id}&survey=kendaraan','_blank')">View Survey</button>
                    </c:when>
                    <c:when test="${isLnb}">
                        <button type="button" class="btn btn-info" onclick="window.open('laporan-survey?PIdDataDetail=${formBean.detail.id}&survey=lnb','_blank')">View Survey</button>
                    </c:when>
                    <c:when test="${isStock}">
                        <button type="button" class="btn btn-info" onclick="window.open('laporan-survey?PIdDataDetail=${formBean.detail.id}&survey=stock','_blank')">View Survey</button>
                    </c:when>
                </c:choose>
				<button type="button" class="btn btn-warning" onclick="gotoUrl('view?id=${formBean.id}#header-survey')"><i
						class="icon-white icon-step-backward"></i> Back
				</button>
				<button type="button" class="btn btn-info" id="btn-toggle"><i class="icon-white icon-minus"></i> Show/Hide</button>
			</div>
        </div>
        <h3>Detail Survey</h3>
    </div>
    <f:form method="post" modelAttribute="formBean" action="save-survey" class="form-horizontal">
        <f:hidden path="id"/>
        <f:hidden path="detail.id"/>
        <div id="div-info" style="border:1px solid #ddd; padding: 8px;" class="controls-row">
            <div class="control-group span6">
                <label class="control-label" for="name">Nama Debitur</label>
                <div class="controls">
                    <f:input path="name" type="text" class="form-control" readonly="true"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="contactName">Nama Contact Person</label>
                <div class="controls">
                    <f:input path="contactName" type="text" class="form-control" readonly="true"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="contactTelp">Telp. Contact Person</label>
                <div class="controls">
                    <f:input path="contactTelp" type="text" class="form-control" readonly="true"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="detail.assetTypeName">Jenis Object Penilaian</label>
                <div class="controls">
                    <f:input path="detail.assetTypeName" class="form-control" readonly="true"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="detail.alamat">Lokasi</label>
                <div class="controls">
                    <f:input path="detail.alamat" type="text" class="form-control" readonly="true"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="catatan">Catatan dari Bisnis Unit</label>
                <div class="controls">
                    <f:input path="catatan" type="text" class="form-control" readonly="true"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="detail.from">Input Data Hasil Survey</label>
                <div class="controls">
                    <f:input path="detail.from" id="detail-from" type="text" class="form-control" readonly="true"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="detail.day">Waktu Perjanjian</label>
                <div class="controls">
                    <f:input path="detail.day" type="text" class="form-control" readonly="true"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="detail.message">Keterangan Status Drop</label>
                <div class="controls">
                    <f:input path="detail.message" type="text" class="form-control" readonly="true"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="detail.surveyStatus">Status</label>
                <div class="controls">
                    <f:select path="detail.surveyStatus" disabled="true">
                        <f:option value="New">New</f:option>
                        <f:option value="Drop">Drop</f:option>
                        <f:option value="Close">Close</f:option>
                    </f:select>
                </div>
            </div>
        </div>
    </f:form>
    <div style="margin-top: 10px;">
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#menu1">Data Umum</a></li>
            <c:if test="${!isInspeksi}">
                <li><a data-toggle="tab" href="#menu2">Data Point</a></li>
            </c:if>
            <c:choose>
                <c:when test="${isInspeksi}">
                    <li><a data-toggle="tab" href="#menu8">Data Inspeksi</a></li>
                    <li><a data-toggle="tab" href="#menu11">Barang Jadi</a></li>
                    <li><a data-toggle="tab" href="#menu12">Barang Setengah Jadi</a></li>
                    <li><a data-toggle="tab" href="#menu13">Bahan Baku</a></li>
                </c:when>
                <c:when test="${isKendaraan}">
                    <li><a data-toggle="tab" href="#menu7">Data Kendaraan</a></li>
                </c:when>
                <c:when test="${isVessel}">
                    <li><a data-toggle="tab" href="#menu7">Data Vessel</a></li>
                </c:when>
                <c:when test="${isStock}">
                    <li><a data-toggle="tab" href="#menu7">Data Stock</a></li>
                </c:when>
                <c:otherwise>
                    <li><a data-toggle="tab" href="#menu3">Data Tanah</a></li>
                    <li><a data-toggle="tab" href="#menu4">Data Bangunan</a></li>
                    <li><a data-toggle="tab" href="#menu5">Data Lingkungan</a></li>
                    <li><a data-toggle="tab" href="#menu6">Data Pembanding</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
        <div class="tab-content">
            <div id="menu1" class="tab-pane fade in active">
                <iframe src="${ctxPath}/survey/show?detailId=${formBean.detail.id}" width="99%" height="700px" scrolling="no" frameborder="0"></iframe>
            </div>
            <div id="menu2" class="tab-pane fade">
                <iframe src="${ctxPath}/survey/show-point?detailId=${formBean.detail.id}&category=${formBean.detail.categoryCode}" width="99%" height="700px" scrolling="auto" frameborder="0"></iframe>
            </div>
            <c:if test="${isLnb}">
                <div id="menu3" class="tab-pane fade">
                    <iframe src="${ctxPath}/survey/show-tanah?detailId=${formBean.detail.id}" width="99%" height="700px" scrolling="no" frameborder="0"></iframe>
                </div>
                <div id="menu4" class="tab-pane fade">
                    <iframe src="${ctxPath}/survey/show-bangunan?detailId=${formBean.detail.id}" width="99%" height="700px" scrolling="no" frameborder="0"></iframe>
                </div>
                <div id="menu5" class="tab-pane fade">
                    <iframe src="${ctxPath}/survey/show-lingkungan?detailId=${formBean.detail.id}" width="99%" height="700px" scrolling="no" frameborder="0"></iframe>
                </div>
                <div id="menu6" class="tab-pane fade">
                    <iframe src="${ctxPath}/survey/show-pembanding?detailId=${formBean.detail.id}" width="99%" height="700px" scrolling="no" frameborder="0"></iframe>
                </div>
            </c:if>
            <c:if test="${isKendaraan}">
                <div id="menu7" class="tab-pane fade">
                    <iframe src="${ctxPath}/survey/show-kendaraan?detailId=${formBean.detail.id}" width="99%" height="700px" scrolling="no" frameborder="0"></iframe>
                </div>
            </c:if>
            <c:if test="${isVessel}">
                <div id="menu7" class="tab-pane fade">
                    <iframe src="${ctxPath}/survey/show-kendaraan?detailId=${formBean.detail.id}" width="99%" height="700px" scrolling="no" frameborder="0"></iframe>
                </div>
            </c:if>
            <c:if test="${isInspeksi}">
                <div id="menu8" class="tab-pane fade">

                    <iframe src="${ctxPath}/survey/show-inspeksi?detailId=${formBean.detail.id}" width="99%" height="700px" scrolling="no" frameborder="0"></iframe>
                        <%--<iframe src="${ctxPath}/survey/show-kendaraan?detailId=${formBean.detail.id}" width="99%" height="700px" scrolling="no" frameborder="0"></iframe>--%>
                </div>
                <div id="menu11" class="tab-pane fade">
                    <iframe src="${ctxPath}/survey/show-barang?detailId=${formBean.detail.id}&typeGoods=Goods" width="99%" height="700px" scrolling="no" frameborder="0"></iframe>
                </div>
                <div id="menu12" class="tab-pane fade">
                    <iframe src="${ctxPath}/survey/show-barang?detailId=${formBean.detail.id}&typeGoods=Half_Goods" width="99%" height="700px" scrolling="no" frameborder="0"></iframe>
                </div>
                <div id="menu13" class="tab-pane fade">
                    <iframe src="${ctxPath}/survey/show-barang?detailId=${formBean.detail.id}&typeGoods=Raw_Materials" width="99%" height="700px" scrolling="no" frameborder="0"></iframe>
                </div>
            </c:if>
            <c:if test="${isStock}">
                <div id="menu7" class="tab-pane fade">

                    <iframe src="${ctxPath}/survey/show-stock?detailId=${formBean.detail.id}" width="99%" height="700px" scrolling="no" frameborder="0"></iframe>
                    <%--<iframe src="${ctxPath}/survey/show-kendaraan?detailId=${formBean.detail.id}" width="99%" height="700px" scrolling="no" frameborder="0"></iframe>--%>
                </div>
            </c:if>



        </div>
    </div>
</div>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap-transition.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap-alert.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap-modal.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap-dropdown.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap-scrollspy.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap-tab.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap-tooltip.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap-popover.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap-button.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap-collapse.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap-carousel.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap-typeahead.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap-affix.js"></script>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
    $(document).ready(function () {

        $.active = false;
        $('body').bind('click keypress', function() { $.active = true; });
        var logout = '${ctxPath}/logoutExpired';
        var heartBeat = '${ctxPath}/heartbeat';
        checkActivity(${maxInterval}, 60000, 0,logout,heartBeat);

        $('#btn-manual').click(function () {
            $("#detail-from").val("MANUAL");
            return false;
        });
        $('#btn-toggle').click(function () {
            $('#div-info').toggle('slow');
        });
    });
</script>
</body>
</html>