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
		<h1>Master Cabang Marketing</h1>
	</div>
	<div class="btn-group navbar-right" style="float:right;">
		<a href="add" type="submit" class="btn btn-small btn-success"><i class="icon-white icon-plus"></i> Add New Record</a>
		<a id="btn-search" type="button" class="btn btn-small btn-info"><i class="icon-white icon-filter"></i> Filter</a>
	</div>
	<f:form id="form_search" method="get" modelAttribute="formBean" action="#" class="form-horizontal">
		<div class="control-group">
			<label class="control-label" for="fname">Nama</label>
			<div class="controls">
				<input id="fname" name="fname" type="text" class="form-control"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="kodeCabang">Kode Cabang</label>
			<div class="controls">
				<f:input path="kodeCabang" type="text" class="form-control" placeholder="Kode Cabang" />
				<f:errors path="kodeCabang" class="error has-error" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&nbsp;</label>
			<div class="controls">
				<button id="btn_fsearch" type="submit" class="btn btn-small btn-success"><i class="icon-white icon-search"></i> Search</button>
				<button type="reset" class="btn btn-small btn-warning"><i class="icon-white icon-refresh"></i> Reset</button>
			</div>
		</div>
	</f:form>
	<table id="table-list" class="table table-striped bg-white"></table>
</div>

<script type="text/javascript" src="${ctxPath}/js/jquery-ui-1.10.3/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${ctxPath}/js/DataTables-1.10.12/js/jquery.dataTables.min.js"></script>
<%--<script type="text/javascript" src="${ctxPath}/js/DataTables-1.10.12/js/dataTables.bootstrap.min.js"></script>--%>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
	var dataTable;
	var _csrf_token = "${_csrf.token}";
	// init component
	$(document).ready(function() {

        $.active = false;
        $('body').bind('click keypress', function() { $.active = true; });
        var logout = '${ctxPath}/logoutExpired';
        var heartBeat = '${ctxPath}/heartbeat';
        checkActivity(${maxInterval}, 60000, 0,logout,heartBeat);

		dataTable = $('#table-list').dataTable({
			"sAjaxSource" : "<s:url value="find"/>",
			"fnServerData" : function(sSource, aoData, fnCallback, oSettings) {
				var _form_search = $("#form_search");
				aoData.push(
					{
						"name" : "name",
						"value" : _form_search.find('input[name=fname]').val()
					},
						{
							"name" : "kodeCabang",
							"value" : _form_search.find('input[name=kodeCabang]').val()
						}
				);
				$.ajax({
					"dataType" : 'json',
					"type" : "GET",
					"url" : sSource + "?${_csrf.parameterName}=${_csrf.token}",
					"data" : aoData,
					"success" : fnCallback
				});
			},
			"aoColumns" : [
				{"mData" : null, "sTitle" : "Action", "sWidth": "30px"},
				{"mData" : "name", "sTitle" : "Name"},
				{"mData" : "kodeCabang", "sTitle" : "Kode Cabang"}
			],
			"aoColumnDefs": [
				{
					"aTargets": [0],
					"mRender": function (data,type,row) {

						return drawTindakan(data);
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
//						'<a data-id="'+ _row.id+'" title="Delete Data" class="btn btn-small btn-danger click-delete-data"><i class="icon-white icon-remove"></i></a>'+
					'</div>'+
					'</div>';
			return _dom;
		}
		function bindingLink(){
			$(".click-edit-data").unbind('click');
			$(".click-edit-data").bind('click', function() {
				var _a = $(this);
				var _id = _a.data('id');
				window.location = 'edit?id='+_id;
			});
			$(".click-delete-data").unbind('click');
			$(".click-delete-data").bind('click', function() {
				if(window.confirm('Are you sure to delete data?')){
					var _a = $(this);
					var _id = _a.data('id');
					$.ajax({
						type : "POST",
						url : "<s:url value="/master/cabang-marketing/delete"/>",
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
		// component event
		$('#btn-search').click(function(){
			$('#form_search').toggle('slow');
		});
		$('#form_search').submit(function(){
			dataTable.DataTable().ajax.reload();
			$('#form_search').toggle('slow');
			return false;
		});
		
		// main app
		dataTable.DataTable().ajax.reload();


	});
</script>
</body>
</html>