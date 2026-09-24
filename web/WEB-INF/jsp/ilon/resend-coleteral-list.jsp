<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
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

			<div class="page-header">
				<h1>List Order Close</h1>
			</div>

	<c:if test="${not empty flashMessage}">
		<div class="alert alert-success">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<strong>${flashMessage}</strong>
		</div>
	</c:if>
	<div class="" data-height="100%" data-disable-fade-out="true" data-distance="0" data-size="10px" data-color="#00bddd">
		<table id="table-list" class="table dataTable"></table>
	</div>
</div>
<script type="text/javascript" src="${ctxPath}/js/jquery-ui-1.10.3/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/DataTables-1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
	var dataTable;
	var _csrf_token = "${_csrf.token}";
	// init component
	$(document).ready(function() {
		dataTable = $('#table-list').dataTable({
			"deferLoading": 0,
			'bFilter' : false,
			'bSort' : false,
			"sAjaxSource" : "<s:url value="findColleteral"/>",
			"fnServerData" : function(sSource, aoData, fnCallback, oSettings) {
				var _form_search = $("#div-form-search-bj");
				aoData.push(
						{
							"name" : "debitur",
							"value" : _form_search.find("input[name=debitur]").val()
						},
						{
							"name" : "atasNama",
							"value" : _form_search.find("input[name=atasNama]").val()
						}
				);
				$.ajax({
					"dataType" : 'json',
					"type" : "GET",
					"url" : sSource + "?task=${task}&${_csrf.parameterName}=${_csrf.token}",
					"data" : aoData,
					"success" : fnCallback
				}).done(function (data) {
				});
			},
			"aoColumns" : [
				{"mData" : null, "sTitle" : "Action", "sWidth" : "30px"},
				{"mData" : "orderNumber", "sTitle" : "No. Order"},

                {"mData" : "collateralId", "sTitle" : "Colleteral Id"},

				{"mData" : "name", "sTitle" : "Nama Debitur"},
				{"mData" : "detail.name", "sTitle" : "Lokasi"},
				{"mData" : "detail.subDistrict", "sTitle" : "Kelurahan"},
				{"mData" : "detail.district", "sTitle" : "Kecamatan"},
				{"mData" : "detail.city", "sTitle" : "Kota / Kab"},
				{"mData" : "detail.province", "sTitle" : "Propinsi"},
				{"mData" : "branchBussinesName", "sTitle" : "Cabang Bisnis"},
				{"mData" : "appraisalTypeCategory", "sTitle" : "Jenis Appraisal"},
				{"mData" : "detail.assetTypeName", "sTitle" : "Jenis Aktiva"},
				{"mData" : "typeReport", "sTitle" : "Jenis Laporan"},
				{"mData" : "lastMessage.message", "sTitle" : "Message Terakhir"}
			],
			"aoColumnDefs": [
				{
					"aTargets": [0],
					"mRender": function (data,type,row) {
						return drawTindakan(data);
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
					"aTargets": [12],
					"mRender": function (data,type,row) {
						if(row.lastMessage.userName != null)
							return "<b>"+row.lastMessage.userName +"</b><br>" + data;
						return "";
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
						'<a data-id="'+ _row.id+'" title="Edit Data 2" class="btn btn-small btn-success click-edit-data"><i class="icon-white icon-book"></i></a>'+
					'</div>'+
					'</div>';
			return _dom;
		}
		function bindingLink(){
			$(".click-edit-data").unbind('click');
			$(".click-edit-data").bind('click', function() {
				var _a = $(this);
				var _id = _a.data('id');
				window.location = 'viewByCol?id='+_id;
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