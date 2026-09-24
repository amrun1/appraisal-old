<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
	<title>Appraisal</title>
	<c:set var="edit" value='<%= request.getParameter("id") %>'/>
	<meta http-equiv="Expires" content="-1" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Cache-Control" content="no-cache" />
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">

	<link href="${ctxPath}/js/bootstrap_2.3.2/css/bootstrap.css" rel="stylesheet">
	<link href="${ctxPath}/js/bootstrap_2.3.2/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="${ctxPath}/js/bootstrap_2.3.2/css/docs.css" rel="stylesheet">
	<link href="${ctxPath}/js/bootstrap_2.3.2/js/google-code-prettify/prettify.css" rel="stylesheet">
	<link href="${ctxPath}/js/bootstrap-datepicker-1.6.4/css/bootstrap-datepicker.min.css" rel="stylesheet">
	<link href="${ctxPath}/css/main-app.css" rel="stylesheet">
	<link href="${ctxPath}/js/datatables.css" rel="stylesheet">
	<link rel="stylesheet" href="${ctxPath}/js/DataTables-1.10.12/css/dataTables.bootstrap.min.css" type="text/css" />

	<!--[if gte IE 9]><!-->
		<link rel="stylesheet" media="screen" href="${ctxPath}/css/glyphicons/glyphicons.min.css" >
	<!--<![endif]-->
	<!--[if lt IE 9]>
		<script src="${ctxPath}/js/html5shiv.js"></script>
		<script src="${ctxPath}/js/respond.min.js"></script>
	<![endif]-->
	<%--<script type="text/javascript">--%>
		<%----%>
	<%--</script>--%>
</head>
<body>
<div class="container">
	<div class="page-header">
		<h1>Monitoring PDA Survey</h1>
	</div>
	<c:if test="${not empty msg}">
		<div class="alert alert-success">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<strong>${msg}</strong>
		</div>
	</c:if>
	<f:form method="get" modelAttribute="formBean" id="idd" action="#" class="form-horizontal" style="margin-top:60px" >
		<div style="border:1px solid #ddd; padding: 8px;" class="controls-row">
			<div class="control-group">
				<label class="control-label" for="appraisalTypeName">Tim User</label>
				<div class="controls">
					<select id="appraisalTypeName" name="appraisalTypeName" path="appraisalTypeName">
						<option value="VALUER">Valuer</option>
						<option value="SURVEYOR">Surveyor</option>
					</select>
					<f:select path="closedBy">
						<f:option value="" label="All"/>
						<f:options items="${listValuers}" itemValue="id" itemLabel="label"/>
					</f:select>
					<f:errors path="closedBy" cssClass="help-block"/>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="createdDate">Periode Tanggal</label>
				<div class="controls">
					<f:input path="createdDate" data-date-format="dd-mm-yyyy"
							 data-provide="datepicker"  placeholder="DD-MM-YYYY" data-date-end-date="0d"/>
					<f:errors path="createdDate" cssClass="help-block"/>
					s/d
					<f:input path="closedDate" data-date-format="dd-mm-yyyy"
							 data-provide="datepicker"  class="tutup-otomatis" placeholder="DD-MM-YYYY" data-date-end-date="0d"/>
					<f:errors path="closedDate" cssClass="help-block"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="name">&nbsp;</label>
				<div class="controls">
					<%--<button type="submit" id="view1" class="btn btn-small btn-info"><i class="icon-white icon-file"></i>View</button>--%>
					<%--<a id="btn-search" type="button" class="btn btn-small btn-info"><i class="icon-white icon-filter"></i> Filter</a>--%>
					<button id="btn_fsearch" type="submit" class="btn btn-small btn-info"><i class="icon-white icon-file"></i> View</button>
						<%--<button type="submit" id="view2" class="btn btn-small btn-info click-print-data-rekap"><i class="icon-white icon-file"></i>View Report Rekap Excel</button>--%>
				</div>
			</div>
		</div>
	</f:form>
	<%--<c:if test="${edit!=null}">--%>
	<%--<table id="table-list" class="table table-striped bg-white"></table>--%>
	<div id="show" >
		<div id="table-valuer">
			<table id="table-list" class="table table-striped bg-white"></table>
		</div>
		<div id="table-surveyor">
			<table id="table-list2" class="table table-striped bg-white"></table>
		</div>
		<form id="print" action="survey-monitoring.xls" method="get" target="_blank">
			<input id="a" name="a" style="display: none;">
			<input id="b" name="b" style="display: none;">
			<input id="c" name="c"style="display: none;">
			<input id="d" name="d" style="display: none;">
            <button id="btn_print" type="submit" class="btn btn-small btn-info" ><i class="icon-white icon-file"></i> View Excel</button>
		</form>
		<%--<a id="btn-search"  type="button" class="btn btn-small btn-info"><i class="icon-white icon-file"></i> View Excel</a>--%>

	</div>
	<%--</c:if>--%>
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

<script src="${ctxPath}/js/bootstrap-datepicker-1.6.4/js/bootstrap-datepicker.min.js"></script>
<script src="${ctxPath}/js/jquery.inputmask.bundle.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/DataTables-1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>

<script type="text/javascript">
	<%--<c:if test="${edit!=null}">--%>
	var dataTableValuer;
	var dataTableSurveyor;
	var dataTable;
	var _csrf_token_valuer = "${_csrf.token}";
	var _csrf_token_surveyor = "${_csrf.token}";
	// init component
	$(document).ready(function() {


		//VALUER
//		$('#btn_fsearch').click(function(){
		dataTableValuer = $('#table-list').dataTable({
			"sAjaxSource" : "<s:url value="find-pda"/>",
			"fnServerData" : function(sSource, aoData, fnCallback, oSettings) {
				var _form_search = $("#idd");
				aoData.push(
						{
							"name" : "createdDate",
							"value" : _form_search.find('input[name=createdDate]').val()
						},
						{
							"name" : "closedDate",
							"value" : _form_search.find('input[name=closedDate]').val()
						},
						{
							"name" : "closedBy",
							"value" : _form_search.find('select[name=closedBy]').val()
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
				{"mData" : "orderNo", "sTitle" : "No. Order"},
				{"mData" : "nomor", "sTitle" : "No. Survey"},
				{"mData" : "valuer", "sTitle" : "Valuer Id"},
				{"mData" : "debiturName", "sTitle" : "Debt Name"},
				{"mData" : "status", "sTitle" : "Status"},
				{"mData" : "surveyDateString", "sTitle" : "Survey Date"}
//				{"mData" : "closedBy", "sTitle" : "Client Date"},
//				{"mData" : "closedBy", "sTitle" : "Complete Date"}
			]


		});
		// main app
		dataTableValuer.DataTable().ajax.reload();

		//SURVEYOR
		dataTableSurveyor = $('#table-list2').dataTable({
			"sAjaxSource" : "<s:url value="find-pda2"/>",
			"fnServerData" : function(sSource, aoData, fnCallback, oSettings) {
				var _form_search = $("#idd");
				aoData.push(
						{
							"name" : "createdDate",
							"value" : _form_search.find('input[name=createdDate]').val()
						},
						{
							"name" : "closedDate",
							"value" : _form_search.find('input[name=closedDate]').val()
						},
						{
							"name" : "closedBy",
							"value" : _form_search.find('select[name=closedBy]').val()
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
				{"mData" : "orderNo", "sTitle" : "No. Order"},
				{"mData" : "nomor", "sTitle" : "No. Survey"},
				{"mData" : "valuer", "sTitle" : "Valuer Id"},
				{"mData" : "surveyCode", "sTitle" : "Survey Name"},
				{"mData" : "closedBy", "sTitle" : "CUG Number"},
				{"mData" : "debiturName", "sTitle" : "Debt Name"},
				{"mData" : "status", "sTitle" : "Status"},
				{"mData" : "surveyDateString", "sTitle" : "Survey Date"}
//				{"mData" : "closedBy", "sTitle" : "Client Date"},
//				{"mData" : "closedBy", "sTitle" : "Complite Date"}
			]

		});
		// main app
		dataTableSurveyor.DataTable().ajax.reload();


		$('#table-surveyor').hide();
		$('#appraisalTypeName').change(function () {
			var id = $(this).val();
			$('#show').hide();
			if (id=='SURVEYOR'){
				$('#table-valuer').hide();
				$('#table-surveyor').show();
//				dataTableValuer.DataTable().ajax.reload();
			}else if(id=='VALUER'){
				$('#table-valuer').show();
				$('#table-surveyor').hide();
//				dataTableValuer.DataTable().ajax.reload();
			}
		});

        $('#closedBy').change(function () {
            $('#show').hide();
        });

        $('#createdDate').change(function () {
            $('#show').hide();
        });

        $('#closedDate').change(function () {
            $('#show').hide();
        });

		$('#appraisalTypeName').change(function () {
			var id = $(this).val();
			$('#closedBy').empty();
			$('#closedBy').append($('<option>', {value:"", text:"All"}));
			if(id != ""){
				$.ajax({
					type: 'get',
					//cache: false,
					dataType: "json",
					url: 'find-appraisal',
					data: {'appraisalTypeName' : id }
				}).success(function (data) {
					//alert('aaa bbb ccc');

					for(var i=0 ; i<data.length ; i++){
						var row = data[i];
						$('#closedBy').append($('<option>', {'value' : row.value, 'text' : row.label}));
					}
				})
			}else{
				$.ajax({
					type: 'get',
					//cache: false,
					dataType: "json",
					url: 'find-appraisal',
					data: {'appraisalTypeName' : 'VALUER' }
				}).success(function (data) {
					//alert('aaa bbb ccc');

					for(var i=0 ; i<data.length ; i++){
						var row = data[i];
						$('#closedBy').append($('<option>', {'value' : row.value, 'text' : row.label}));
					}
				})
			}
			//console.log(id);
		});




		$('#show').hide();
		$('#btn_fsearch').click(function(){

			var start = $('#createdDate').datepicker('getDate');
			var end = $('#closedDate').datepicker('getDate');

			if (start==null && end==null){
				alert('Tanggal awal harus di isi \nTanggal akhir harus di isi');
				return  false;
			}else if(start==null){
				alert('Tanggal awal harus di isi \nTanggal akhir harus di isi');
				return  false;

			}else if(end==null){
				alert('Tanggal akhir harus di isi');
				return false;
			}

			var _a = $("#idd");
			var _appraisalTypeName = _a.find('select[name=appraisalTypeName]').val();
			var _closedBy = _a.find('select[name=closedBy]').val();
			var _createdDate = _a.find('input[name=createdDate]').val();
			var _closedDate = _a.find('input[name=closedDate]').val();

			var _b =  $("#print");
			$('#a').val(_appraisalTypeName);
			$('#b').val(_closedBy);
			$('#c').val(_createdDate);
			$('#d').val(_closedDate);
			$.ajax({
				type: "GET",
				url: '/monitoring/survey-monitoring.xls',
				data: {
					"appraisalTypeName": _appraisalTypeName,
					"closedBy": _closedBy,
					"createdDate": _createdDate,
					"closedDate": _closedDate,
					_csrf: _csrf_token
				},
				"success" : fnCallback
			});
		});

		$('#idd').submit(function(){
			$('#show').show();
			dataTableValuer.DataTable().ajax.reload();
			dataTableSurveyor.DataTable().ajax.reload();
//			$('#show').show();
			return false;
		});

		$('#closedDate').change(function() {
			var start = $('#createdDate').datepicker('getDate');
			var end = $('#closedDate').datepicker('getDate');

			if (start < end) {
				var days = (end - start) / 1000 / 60 / 60 / 24;
				if(start!=null) {
//                if (days > 31) {
//                    alert('Jarak tanggal tidak boleh lebih dari 31 hari')
//                    $('#closedDate').val('');
//                    $('#closedDate').datepicker('setDate', null);
//                }
				}else{
					alert('Tanggal awal harus di isi')
					$('#closedDate').val('');
					$('#closedDate').datepicker('setDate', null);
				}
			}else if (start>end && end !=null){
				alert("Tanggal Awal tidak boleh lebih besar dari Tanggal Akhir");
				$('#closedDate').val("");
				$('#closedDate').datepicker('setDate', null);
			}

		});

		$('#createdDate').change(function() {
			var start = $('#createdDate').datepicker('getDate');
			var end = $('#closedDate').datepicker('getDate');

			if (start < end) {
				var days = (end - start) / 1000 / 60 / 60 / 24;
				if(start!=null) {
//                if (days > 31) {
//                    alert('Jarak tanggal tidak boleh lebih dari 31 hari')
//                    $('#createdDate').val('');
//                    $('#createdDate').datepicker('setDate', null);
//                }
//            }
				}else{
					alert('Tanggal awal harus di isi')
					$('#closedDate').val('');
					$('#closedDate').datepicker('setDate', null);
				}
			}else if (start>end && end !=null){
				alert("Tanggal Awal tidak boleh lebih besar dari Tanggal Akhir");
				$('#createdDate').val("");
				$('#createdDate').datepicker('setDate', null);
			}

		});



	});
	<%--</c:if>--%>
</script>
</body>
</html>