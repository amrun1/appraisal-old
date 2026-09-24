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
	<div class="page-header-page">
		<div class="absolute-right btn-group">
			<a href="add" class="btn btn-small btn-success"><i class="icon-white icon-plus"></i> Add</a>
			<a href="upload" class="btn btn-small btn-info"><i class="icon-white icon-upload"></i> Upload</a>
			<a href="updatePic" class="btn btn-small btn-primary"><i class="icon-white icon-edit"></i> PIC</a>
			<a id="btn-search" class="btn btn-small btn-info"><i class="icon-white icon-filter"></i> Filter</a>
		</div>
		<h1>Master Kode Pos</h1>
	</div>
	<f:form id="form_search" method="get" modelAttribute="formBean" action="#" class="form-horizontal" cssStyle="margin-top: 50px;">
		<div class="control-group">
			<label class="control-label" for="province">Propinsi</label>
			<div class="controls">
				<input id="province" type="text" class="form-control"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="city">Kota</label>
			<div class="controls">
				<input id="city" type="text" class="form-control"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="district">Kecamatan</label>
			<div class="controls">
				<input id="district" type="text" class="form-control"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="subDistrict">Kelurahan</label>
			<div class="controls">
				<input id="subDistrict" type="text" class="form-control"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="code">Kode Pos</label>
			<div class="controls">
				<input id="code" type="text" class="form-control"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="picName">PIC</label>
			<div class="controls">
				<input id="picName" type="text" class="form-control"/>
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
	<div style="margin-top: 50px;">&nbsp;</div>
	<c:if test="${not empty msg}">
		<div class="alert alert-${css}">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<strong>${msg}</strong>
		</div>
	</c:if>
	<table id="table-list" class="table table-striped bg-white" width="100%"></table>
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
						"name" : "province",
						"value" : _form_search.find('input[id=province]').val()
					},
					{
						"name" : "city",
						"value" : _form_search.find('input[id=city]').val()
					},
					{
						"name" : "district",
						"value" : _form_search.find('input[id=district]').val()
					},
					{
						"name" : "subDistrict",
						"value" : _form_search.find('input[id=subDistrict]').val()
					},
					{
						"name" : "code",
						"value" : _form_search.find('input[id=code]').val()
					},
					{
						"name" : "picName",
						"value" : _form_search.find('input[id=picName]').val()
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
				{"mData" : "province", "sTitle" : "Propinsi"},
				{"mData" : "city", "sTitle" : "Kota"},
				{"mData" : "district", "sTitle" : "Kecamatan"},
				{"mData" : "subDistrict", "sTitle" : "Kelurahan"},
				{"mData" : "code", "sTitle" : "Kode Pos"},
				{"mData" : "picName", "sTitle" : "PIC"}
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
				window.location = 'edit?id='+_id;
			});
			$(".click-delete-data").unbind('click');
			$(".click-delete-data").bind('click', function() {
				if(window.confirm('Are you sure to delete data?')){
					var _a = $(this);
					var _id = _a.data('id');
					$.ajax({
						type : "POST",
						url : "<s:url value="delete"/>",
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