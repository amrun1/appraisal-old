<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
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
    <link href="${ctxPath}/js/bootstrap-datepicker-1.6.4/css/bootstrap-datepicker.min.css" rel="stylesheet">
    <link href="${ctxPath}/css/main-app.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${ctxPath}/js/html5shiv.js"></script>
    <script src="${ctxPath}/js/respond.min.js"></script>
    <![endif]-->

    <style type="text/css">
        /*.modal { width:90%; left: 30%;}*/
        .modal-body {
            max-height: 448px;
            padding: 10px;
        }
        .modal.fade.in {
            top: 5%;
        }
        .modal-header {
            padding: 5px 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="page-header">
        <h1>Report Rwa</h1>
    </div>
    <f:form method="get" modelAttribute="formBean" id="act" action="report-rwa.xls" class="form-horizontal" style="margin-top:60px" target="_blank">
        <div style="border:1px solid #ddd; padding: 8px;" class="controls-row">
            <div class="control-group">
                <label class="control-label" for="createdDate">Periode Tanggal Close</label>
                <div class="controls">
                    <f:input path="createdDate" data-date-format="dd-mm-yyyy"
                             data-provide="datepicker"  class="tutup-otomatis" placeholder="DD-MM-YYYY" data-date-end-date="0d"/>
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
                    <button type="submit" id="view1" class="btn btn-small btn-info click-print-data"><i class="icon-white icon-file"></i>View Report Excel</button>
                    <%--<button type="submit" id="view2" class="btn btn-small btn-info click-print-data-rekap"><i class="icon-white icon-file"></i>View Report Rekap Excel</button>--%>
                </div>
            </div>
        </div>
    </f:form>
</div>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<script src="${ctxPath}/js/bootstrap-datepicker-1.6.4/js/bootstrap-datepicker.min.js"></script>
<script src="${ctxPath}/js/jquery.inputmask.bundle.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
$(document).ready(function() {

    $('#closedDate').change(function() {
        var start = $('#createdDate').datepicker('getDate');
        var end = $('#closedDate').datepicker('getDate');

        if (start < end) {
            var days = (end - start) / 1000 / 60 / 60 / 24;
            if(start!=null) {
                if (days > 31) {
                    alert('Jarak tanggal tidak boleh lebih dari 31 hari')
                    $('#closedDate').val('');
                    $('#closedDate').datepicker('setDate', null);
                }
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
                if (days > 31) {
                    alert('Jarak tanggal tidak boleh lebih dari 31 hari')
                    $('#createdDate').val('');
                    $('#createdDate').datepicker('setDate', null);
                }
            }
//            }else{
//                alert('Tanggal awal harus di isi')
//                $('#closedDate').val('');
//                $('#closedDate').datepicker('setDate', null);
//            }
        }else if (start>end && end !=null){
            alert("Tanggal Awal tidak boleh lebih besar dari Tanggal Akhir");
            $('#createdDate').val("");
            $('#createdDate').datepicker('setDate', null);
        }

    });



    $("#view1").click(function () {
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
        }else {
            var frm = document.getElementById('act') || null;
            if(frm) {
                frm.action = 'report-rwa.xls'
            }
            $('#param').val("1");
        }
    });


	function bindingLink(){			
			$(".click-print-data").unbind('click');
			$(".click-print-data").bind('click', function() {				
					var _a = $(this);
					var _createdDate = _a.data('createdDate');
					var _closedDate = _a.data('closedDate');
					$.ajax({
						type : "GET",
						url : "<s:url value='/monitoring/report-rwa.xls'/>",
						dataType : "json",
						data : {
							"createdDate" : _createdDate,
							"closedDate" : _closedDate,
							_csrf : _csrf_token
						}
					});
				
			});
	}

});
</script>
</body>
</html>
