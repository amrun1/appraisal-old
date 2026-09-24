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
		<h1>Master Indikasi Formula</h1>
	</div>
    <c:if test="${not empty msg}">
        <div class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${msg}</strong>
        </div>
    </c:if>
		<div class="control-group">
			<%--<label class="control-label" for="type">Jenis</label>--%>
			<div class="controls">
               Jenis :
                <select id="type" name="type" >
                    <option value="Ruko">Ruko / Rukan</option>
                    <option value="Rumah Tinggal">Rumah Tinggal</option>
                    <option value="Villa Cottage">Villa Cottage</option>
                </select>
			</div>
		</div>
    <div class="btn-group navbar-right" style="float:right;">
        <a href="add" type="submit" class="btn btn-small btn-success"><i class="icon-white icon-plus"></i> Add New Record</a>

    </div>
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
        // component event

        $.active = false;
        $('body').bind('click keypress', function() { $.active = true; });
        var logout = '${ctxPath}/logoutExpired';
        var heartBeat = '${ctxPath}/heartbeat';
        checkActivity(${maxInterval}, 60000, 0,logout,heartBeat);

		dataTable = $('#table-list').dataTable({
			"sAjaxSource" : "<s:url value="find"/>",
			"fnServerData" : function(sSource, aoData, fnCallback, oSettings) {
				aoData.push(

					{
						"name" : "type",
						"value" : $("#type").val()
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
                {"mData" : "type", "sTitle" : "Nama"},
				{"mData" : "luas1", "sTitle" : "Lantai 1"},
                {"mData" : "luas2", "sTitle" : "Lantai 2"},
				{"mData" : "nilai", "sTitle" : "nilai"}
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
			console.log(_row);
			var _dom = '' +
					'<div class="action-group">' +
					'<div class="btn-group m-b-sm navbar-right">'+
						'<a data-id="'+ _row.code+'" title="Edit Data" class="btn btn-small btn-success click-edit-data"><i class="icon-white icon-edit"></i></a>'+

					'</div>'+
					'</div>';
			return _dom;
		}
		function bindingLink(){
			$(".click-edit-data").unbind('click');
			$(".click-edit-data").bind('click', function() {
				var _a = $(this);
				var _id = _a.data('id');
				window.location = 'edit?code='+_id;
			});

		}

        $("#type").change(function () {
            var  type =  this.value;
            dataTable.DataTable().ajax.reload();

            if(type=='Ruko'){
                $('#table-list thead th:eq(2)').text("Lantai 1");
                $('#table-list thead th:eq(3)').text("Lantai 2");
            }else{
                $('#table-list thead th:eq(2)').text("Luas 1");
                $('#table-list thead th:eq(3)').text("Luas 2");
            }



        });


		// main app
	        dataTable.DataTable().ajax.reload();
		
	});
</script>
</body>
</html>