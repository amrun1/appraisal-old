<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
	<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
	<title>Appraisal</title>
	<meta http-equiv="Expires" content="-1" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Cache-Control" content="no-cache" />
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />

	<link href="${ctxPath}/js/bootstrap_2.3.2/css/bootstrap.css" rel="stylesheet">
	<link href="${ctxPath}/js/datatables.css" rel="stylesheet">
	<link rel="stylesheet" href="${ctxPath}/js/DataTables-1.10.12/css/dataTables.bootstrap.min.css" type="text/css" />
	<link href="${ctxPath}/css/main-app.css" rel="stylesheet">

</head>
<body>
<div class="container">
	<div class="page-header">

        <c:if test="${!isGr}">
		<div class="absolute-right">
			<a href="${ctxPath}/data-attactment/add?dataCommonId=${dataCommonId}&assetTypeId=${assetTypeId}&dataDetailId=${dataDetailId}" class="btn btn-success"><i class="icon-white icon-plus"></i> Add New Record</a>
		</div>
        </c:if>
		<h1>Order Data Lampiran List</h1>
	</div>

    <c:if test="${not empty msg}">
        <div class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${msg}</strong>
        </div>
    </c:if>

    <c:if test="${not empty msgErrorValidate}">
        <div class="alert alert-block">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${msgErrorValidate}</strong>
        </div>
    </c:if>





	<div class="form-horizontal">
		<div class="control-group span6">
			<label class="control-label" for="name">Nama Debitur</label>
			<div class="controls">
				<input id="name" type="text" class="form-control" value="${namaDebitur}" readonly>
			</div>
		</div>
		<div class="control-group span6">
			<label class="control-label" for="alamatAgunan">Alamat Agunan</label>
			<div class="controls">
				<input id="alamatAgunan" type="text" class="form-control" value="${alamatAgunan}" readonly>
			</div>
		</div>
	</div>
	<div class="slim-scroll" data-height="100%" data-disable-fade-out="true" data-distance="0" data-size="10px" data-color="#00bddd">
		<table class="table dataTable">
			<thead>
			<tr>
				<th width="100px">Action</th>
				<th>Nama Dokumen</th>
				<%--<th>No. Lampiran</th>--%>
				<th>Nomor Dokumen</th>
				<th>Extension</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${dataList}" var="row" varStatus="status">
				<tr id="trid${row.id}" class="${status.index % 2 == 0 ? "even" : "odd"}">
					<td>
						<div class="btn-group m-b-sm navbar-right">
							<c:if test="${!isGr}">
								<a data-id="${row.id}" data-dataCommonId="${dataCommonId}"
								   data-assetTypeId="${assetTypeId}"
								   data-dataDetailId="${dataDetailId}" title="Edit Data"
								   class="btn btn-small btn-success click-edit-data"><i
										class="icon-white icon-edit"></i></a>
								<a data-id="${row.id}" title="Delete Data"
								   class="btn btn-small btn-danger click-delete-data"><i
										class="icon-white icon-remove"></i></a>
							</c:if>
							<%--<c:if test="${fn:length(row.file) > 0}">--%>
							<a data-id="${row.id}" title="Preview" class="btn btn-small btn-info click-view-data"><i
									class="icon-white icon-picture"></i></a>
							<%--</c:if>--%>
						</div>
					</td>
					<td>${row.documentTypeName}</td>
					<%--<td>${row.id}</td>--%>
					<td>${row.name}</td>
					<td>${row.extension}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<!--[if lt IE 9]>
<script src="${ctxPath}/js/html5shiv.js"></script>
<script src="${ctxPath}/js/respond.min.js"></script>
<![endif]-->
<script type="text/javascript" src="${ctxPath}/js/jquery-ui-1.10.3/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/DataTables-1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.click-edit-data').click(function () {
			window.location = '${ctxPath}/data-attactment/add?dataCommonId=' + $(this).data('datacommonid')
					+ '&assetTypeId=' + $(this).data('assettypeid') + '&dataDetailId=' + $(this).data('datadetailid')
					+ '&id=' + $(this).data('id');
		});
		$('.click-delete-data').click(function () {
			var _id = $(this).data('id');
			if (confirm('Anda yakin akan hapus data?')) {
				$.post("${ctxPath}/data-attactment/delete", {
					"id": _id,
					"${_csrf.parameterName}": "${_csrf.token}"
				}, function (data) {
					if (data.code == 200) {
						$("#trid" + _id).remove();
						var count = $('tbody tr').length;
						if(count > 0){
							//window.parent.refreshDetail();

                            window.parent.refreshParent(3,'Data Berhasil Dihapus');
						} else {
							window.parent.refreshParent(3,'Data Berhasil Dihapus');
						}
					}
				});
			}
		});

		$('.click-view-data').click(function () {
			openWindow("${ctxPath}/data-attactment/preview?id=" + $(this).data('id'));

		});
	});
</script>
</body>
</html>