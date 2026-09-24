<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
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
	<link href="${ctxPath}/js/bootstrap-datepicker-1.6.4/css/bootstrap-datepicker.min.css" rel="stylesheet">

	<!--[if lt IE 9]>
		<script src="${ctxPath}/js/html5shiv.js"></script>
		<script src="${ctxPath}/js/respond.min.js"></script>
	<![endif]-->
</head>
<body>
<div class="container">
	<div class="page-header">
		<h1>List Data Hari Libur</h1>
	</div>
	<div class="btn-group navbar-right" style="float:right;">
		<a href="add" type="submit" class="btn btn-small btn-success"><i class="icon-white icon-plus"></i> Add New Record</a>
		<a id="btn-search" type="button" class="btn btn-small btn-info"><i class="icon-white icon-filter"></i> Filter</a>
	</div>
	<f:form id="form_search" method="get" modelAttribute="formBean" action="#" class="form-horizontal">
		<div class="control-group">
			<label class="control-label" for="fketerangan">Keterangan</label>
			<div class="controls">
				<input name="fketerangan" id="fketerangan" type="text" class="form-control"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="ftanggal">Tanggal</label>
			<div class="controls">
				<input id="ftanggal" name="ftanggal"  data-date-format="dd-mm-yyyy"
					   data-provide="datepicker" class="tutup-otomatis" placeholder="DD-MM-YYYY"/> s/d

				<input id="ftanggal_end" name="ftanggal_end" data-date-format="dd-mm-yyyy"
					   data-provide="datepicker" class="tutup-otomatis" placeholder="DD-MM-YYYY"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="btn_fsearch">&nbsp;</label>
			<div class="controls">
				<button id="btn_fsearch" type="submit" class="btn btn-small btn-success"><i class="icon-white icon-search"></i> Search</button>
				<button type="reset" class="btn btn-small btn-warning"><i class="icon-white icon-refresh"></i> Reset</button>
			</div>
		</div>
	</f:form>
	<table id="table-list" class="table table-striped bg-white" width="100%"></table>
</div>

<script type="text/javascript" src="${ctxPath}/js/jquery-ui-1.10.3/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${ctxPath}/js/DataTables-1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/DataTables-1.10.12/js/dataTables.datetime.js"></script>
<script src="${ctxPath}/js/bootstrap-datepicker-1.6.4/js/bootstrap-datepicker.min.js"></script>
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
						"name" : "tanggal",
						"value" : _form_search.find('input[name=ftanggal]').val()
					},
					{
						"name" : "keterangan",
						"value" : _form_search.find('input[name=fketerangan]').val()
					},
					{
						"name" : "tanggal_end",
						"value" : _form_search.find('input[name=ftanggal_end]').val()
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
				{"mData" : "tanggal", "sTitle" : "Tanggal"},
				{"mData" : "keterangan", "sTitle" : "Keterangan"}
			],
			"aoColumnDefs": [
				{
					"aTargets": [0],
					"mRender": function (data,type,row) {
						return drawTindakan(data);
					}
				},
				{
					"aTargets": [1],
					"mRender": function (data, type, row) {
						return date_format(data);
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
						url : "<s:url value="/master/holiday/delete"/>",
						dataType : "json",
						data : {
							"id" : _id,
							_csrf : _csrf_token
						}
					}).success(function(data) {
						if(data.code == 200) {
							dataTable.DataTable().ajax.reload();
							window.alert('Success Delete Data Hari Libur');
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

		$('#ftanggal_end').change(function() {
			var start = $('#ftanggal').datepicker('getDate');
			var end = $('#ftanggal_end').datepicker('getDate');

			if (start < end) {
				if(start==null) {
					alert('Tanggal awal harus di isi')
					$('#ftanggal_end').val('');
					$('#ftanggal_end').datepicker('setDate', null);
				}
			}else if (start>end && end !=null){
				alert("Tanggal Awal tidak boleh lebih besar dari Tanggal Akhir");
				$('#ftanggal_end').val("");
				$('#ftanggal_end').datepicker('setDate', null);
			}
		});
		
	});
</script>
</body>
</html>