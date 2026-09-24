<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.permata.appraisal2.common.AppConstans" %>
<c:set var="taskPosition" value="<%= AppConstans.TaskPosition.USER_TL %>"/>
<c:set var="isGroup" value="${task == taskPosition}"></c:set>
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
	<div class="page-header">
		<c:choose>
		<c:when test="${! isGroup}">
			<form method="get" action="form" class="absolute-right">
				<button type="submit" class="btn btn-success"><i class="icon-white icon-plus"></i> Add New Record</button>
			</form>
			<h1>Marketing - Task List</h1>
		</c:when>
		<c:otherwise>
			<h1>Marketing - Order List</h1>
		</c:otherwise>
		</c:choose>

		<div class="absolute-right">
			<a href="javascript:toggleTarget('#form-filter')" title="Show Form Filter" class="btn btn-small btn-success"><i class="icon-white icon-filter"></i></a>
		</div>

		<div>
			<f:form id="form-filter" method="post" modelAttribute="formBean" action="#" cssClass="controls-row form-horizontal panel hide">
				<div class="control-group span6">
					<label class="control-label" for="noOrder">No. Order</label>
					<div class="controls">
						<f:input path="noOrder"/>
					</div>
				</div>

				<div class="control-group span6">
					<label class="control-label" for="namaDebitur">Nama Debitur</label>
					<div class="controls">
						<f:input path="namaDebitur"/>
					</div>
				</div>


				<div class="control-group span12" style="margin-top: 15px; padding-left: 20px;">
					<label class="control-label" for="posisi">&nbsp;</label>
					<button type="submit" class="btn btn-small btn-success"><i class="icon-white icon-search"> </i>Cari Data</button>
					<button type="reset" class="btn btn-small btn-warning"><i class="icon-white icon-remove-sign"> </i>Reset Form</button>
				</div>
			</f:form>
		</div>

	</div>
	<c:if test="${not empty flashMessage}">
		<div class="alert alert-${css == null ? "success" : css}">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<strong>${flashMessage}</strong>
		</div>
	</c:if>
	<div class="dataTables_scroll" data-height="100%" data-disable-fade-out="true" data-distance="0" data-size="10px" data-color="#00bddd">
		<table id="table-list" class="table dataTable table-hover"></table>
	</div>
</div>
<script type="text/javascript" src="${ctxPath}/js/jquery-ui-1.10.3/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${ctxPath}/js/DataTables-1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
	var dataTable;
	var _csrf_token = "${_csrf.token}";
	// init component
	$(document).ready(function() {


		// timeout = 30 minutes (dari DB), interval = 1 minute.
        $.active = false;
        $('body').bind('click keypress', function() { $.active = true; });
        var logout = '${ctxPath}/logoutExpired';
        var heartBeat = '${ctxPath}/heartbeat';
        checkActivity(${maxInterval}, 60000, 0,logout,heartBeat);


		$("#form-filter").submit(function(){
			dataTable.DataTable().ajax.reload();
			$('#form-filter').toggle('slow');
			return false;
		});
		dataTable = $('#table-list').dataTable({
			"sAjaxSource" : "<s:url value="find"/>",
			"fnServerData" : function(sSource, aoData, fnCallback, oSettings) {
//				var _form_search = $("#div-form-search-bj");
				var _form_search = $("#form-filter");
				aoData.push(
						{
							"name" : "orderNumber",
							"value" : _form_search.find("input[name=noOrder]").val()
						},
						{
							"name" : "name",
							"value" : _form_search.find("input[name=namaDebitur]").val()
						}
				);
				$.ajax({
					"dataType" : 'json',
					"type" : "GET",
					"url" : sSource + "?task=${task}&${_csrf.parameterName}=${_csrf.token}",
					"data" : aoData,
					"success" : fnCallback
				});
			},
			"order": [[ 1, "asc" ]],
			"aoColumns" : [
				{"mData" : null, "sTitle" : "Action", "sWidth" : "30px"},
				{"mData" : "orderNumber", "sTitle" : "No. Order"},
				{"mData" : "name", "sTitle" : "Nama Debitur"},
				{"mData" : "detail.alamat", "sTitle" : "Lokasi"},
				{"mData" : "detail.subDistrict", "sTitle" : "Kelurahan"},
				{"mData" : "detail.district", "sTitle" : "Kecamatan"},
				{"mData" : "detail.city", "sTitle" : "Kota / Kab"},
				{"mData" : "detail.province", "sTitle" : "Propinsi"},
				{"mData" : "branchBussinesName", "sTitle" : "Cabang Bisnis"},
				{"mData" : "appraisalTypeCategory", "sTitle" : "Jenis Appraisal"},
				{"mData" : "detail.assetTypeName", "sTitle" : "Jenis Aktiva"},
				{"mData" : "typeReport", "sTitle" : "Jenis Laporan"}
			],
			"aoColumnDefs": [
				{ 'bSortable': true, 'aTargets': [1, 2] },
				{ 'bSortable': false, 'aTargets': [0, 3, 4, 5, 6, 7, 8, 9, 10, 11] },
				{
					"aTargets": [0],
					"mRender": function (data,type,row) {
						return drawTindakan(data);
					}
				},
				{
					"aTargets": [9],
					"mRender": function (data, type, row) {
						if(data == null){
							return row.appraisalTypeName;
						}
						return data + ' - ' + row.appraisalTypeName;
					}
				},
				{
					"aTargets": [3, 4, 5, 6, 7, 10],
					"mRender": function (data, type, row) {
						if(data == null){
							return "-";
						}
						return data;
					}
				}
			],
			"fnDrawCallback" : function(settings, json){
				bindingLink();
			}
		});
		function drawTindakan(_row){
			var _dom = '' +
					'<div class="action-group">' +
					'<div class="btn-group m-b-sm navbar-right">'+
						'<a data-id="'+ _row.id+'" title="Edit Data" class="btn btn-small btn-success click-edit-data"><i class="icon-white icon-edit"></i></a>'+
						'<a data-id="'+ _row.id+'" title="Delete Data" class="btn btn-small btn-danger click-delete-data"><i class="icon-white icon-remove"></i></a>'+
					'</div>'+
					'</div>';
			return _dom;
		}
		function bindingLink(){
			$(".click-edit-data").unbind('click');
			$(".click-edit-data").bind('click', function() {
				var _a = $(this);
				var _id = _a.data('id');
				window.location = 'form?id='+_id;
			});
			$(".click-delete-data").unbind('click');
			$(".click-delete-data").bind('click', function() {
				if(window.confirm('Are you sure to delete data?')){
					var _a = $(this);
					var _id = _a.data('id');
					$.ajax({
						type : "POST",
						url : "delete",
						dataType : "json",
						data : {
							"id" : _id,
							_csrf : _csrf_token
						}
					}).success(function(data) {
						if(data.code == 200) {
							dataTable.DataTable().ajax.reload();
							window.alert('Success Delete data');
						} else {
							window.alert(data.msg);
						}
					});
				}
			});
		}
		$('#refresh').click(function(){
			alert('refresh');
			dataTable.DataTable().ajax.reload();
		});

		// main app
		dataTable.DataTable().ajax.reload();
	});

</script>
</body>
</html>