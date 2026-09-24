<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.permata.appraisal2.model.PersonLevel" %>
<c:set var="showButton" value="false"/>
<c:set var="surveyor" value="<%=PersonLevel.APPRAISAL_SURVEYOR%>"/>
<c:set var="totalDoc" value="0"></c:set>
<c:set var="totalFisik" value="0"></c:set>
<c:if test="${roleActive.level == surveyor}">
	<c:set var="showButton" value="true"/>
</c:if>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"> <!--<![endif]-->
<head>
	<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
	<title>Appraisal</title>
	<meta http-equiv="Expires" content="-1" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Cache-Control" content="no-cache" />
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<link href="${ctxPath}/js/bootstrap_2.3.2/css/bootstrap.css" rel="stylesheet">
	<link href="${ctxPath}/js/datatables.css" rel="stylesheet">
	<link rel="stylesheet" href="${ctxPath}/js/DataTables-1.10.12/css/dataTables.bootstrap.min.css" type="text/css" />
	<link href="${ctxPath}/css/main-app.css" rel="stylesheet">

	<!--[if lt IE 9]>
	<script src="${ctxPath}/js/html5shiv.js"></script>
	<script src="${ctxPath}/js/respond.min.js"></script>
	<![endif]-->
</head>
<body>
<div class="container">
	<c:if test="${not empty msg}">
		<div class="alert alert-success">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<strong>${msg}</strong>
		</div>
	</c:if>
	<div class="page-header">
		<c:if test="${showButton}">
		<div class="absolute-right">
			<a href="form-barang?detailId=${detailId}&typeGoods=Raw_Materials" title="Add Data" class="btn btn btn-success click-edit-data"><i class="icon-white icon-plus"></i> Add Data</a>
		</div>
		</c:if>
		<h1>Lampiran Data Bahan Baku</h1>
	</div>
	<c:if test="${not empty flashMessage}">
		<div class="alert alert-success">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<strong>${flashMessage}</strong>
		</div>
	</c:if>
	<div class="control-group span6">
		<label class="control-label" for="subTotalRawMaterial">Sub Total Bahan Baku</label>
		<div class="controls">
			<input type="text" readonly="true" id="subTotalRawMaterial">
		</div>
	</div>
	<div class="control-group span6">
		<label class="control-label" for="subTotalRawMaterialFisik">Sub Total Bahan Baku Fisik</label>
		<div class="controls">
			<input type="text" readonly="true" id="subTotalRawMaterialFisik">
		</div>
	</div>
	<table id="table-list" class="table table-striped bg-white dataTable ">
		<thead>
		<tr>
			<th width="80px">Action</th>

			<th>Bahan Baku</th>
			<th>Jumlah Sesuai Dokumen</th>
			<th>Jumlah Sesuai Fisik</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${dataList}" var="row" varStatus="status">
			<tr class="${status.index % 2 == 0 ? "odd" : "even"}">
				<td>
					<div class="action-group"><div class="btn-group m-b-sm navbar-right">
					<a href="form-barang?id=${row.id}&detailId=${detailId}&typeGoods=Raw_Materials" title="Edit Data" class="btn btn btn-success click-edit-data"><i class="icon-white icon-edit"></i></a>
					<c:if test="${showButton}">
					<a href="delete-barang?id=${row.id}&detailId=${detailId}&typeGoods=Raw_Materials" title="Delete Data" class="btn btn btn-danger click-delete-data"><i class="icon-white icon-remove"></i></a>
					</c:if>
					</div></div>
				</td>

				<td>${row.name}</td>
				<td>${row.totalDoc}</td>
				<td>${row.totalFisik}</td>
				<c:set var="totalDoc" value="${totalDoc + row.totalDoc}"/>
				<c:set var="totalFisik" value="${totalFisik + row.totalFisik}"/>
			</tr>
		</c:forEach>
		<input  type="hidden" id="hasil-ajax-totalDocRawMaterial" value="${totalDoc}"/>
		<input  type="hidden" id="hasil-ajax-totalFisikRawMaterial" value="${totalFisik}"/>
		</tbody>
	</table>
</div>
<script type="text/javascript" src="${ctxPath}/js/jquery-ui-1.10.3/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/jquery.inputmask.bundle.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
		$(".click-delete-data").click(function () {
			return confirm('Anda yakin akan hapus data?');
		});
		var totalDocRawMaterial = $('#hasil-ajax-totalDocRawMaterial').val();
		document.getElementById('subTotalRawMaterial').value=totalDocRawMaterial;
		var totalFisikRawMaterial = $('#hasil-ajax-totalFisikRawMaterial').val();
		document.getElementById('subTotalRawMaterialFisik').value=totalFisikRawMaterial;
	})
</script>
</body>
</html>