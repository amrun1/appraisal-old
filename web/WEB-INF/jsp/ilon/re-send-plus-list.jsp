<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.permata.appraisal2.model.IloanProgres" %>
<%@ page import="com.permata.appraisal2.model.SendToSourceLog" %>
<%@ page import="com.permata.appraisal2.common.AppConstans" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"> <!--<![endif]-->
   <head>
      <head>
        <c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
        <title>Appraisal</title>
        <meta http-equiv="Expires" content="-1"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <meta http-equiv="Cache-Control" content="no-cache"/>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

        <link href="${ctxPath}/js/bootstrap_2.3.2/css/bootstrap.css" rel="stylesheet">
        <link href="${ctxPath}/js/datatables.css" rel="stylesheet">
        <link rel="stylesheet" href="${ctxPath}/js/DataTables-1.10.12/css/dataTables.bootstrap.min.css"
              type="text/css"/>
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
        <div class="btn-group absolute-right hide">
            <a id="btn-search" type="button" class="btn btn-info"><i class="icon-white icon-filter"></i> Filter</a>
        </div>
        <h3>List Data PLUS Gagal Kirim</h3>
    </div>
    <%--<div class="alert alert-info">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <strong>${msg}</strong>
    </div>--%>
    <form id="form_search" method="get" action="#" modelAttribute="formSearch" class="form-horizontal">

    </form>
    <table id="table-list-plus" class="table table-striped bg-white" width="100%"></table>
</div>

<script type="text/javascript" src="${ctxPath}/js/jquery-ui-1.10.3/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/DataTables-1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/jquery.inputmask.bundle.min.js"></script>
<script src="${ctxPath}/js/bootstrap-datepicker-1.6.4/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
    var dataTablePlus;
    var _csrf_token = "${_csrf.token}";

    $(document).ready(function () {
        var _data_list;

        $('#maturDate').datepicker({
            autoclose: true
        });

        $('#createdDate').datepicker({
            autoclose: true
        });


        dataTablePlus = $('#table-list-plus').dataTable({
            // 'bSort': true,
            "sAjaxSource": "findPlus",
            "fnServerData": function (sSource, aoData, fnCallback, oSettings) {
                var _form_search = $("#form_search");
                aoData.push(

                        {
                            "name" : "status",
                            "value" : <%= SendToSourceLog.ERROR %>
                        },
                        {
                            "name" : "source",
                            "value" :"PLUS"
                        }
                );
                $.ajax({
                    "dataType": 'json',
                    "type": "GET",
                    "url": sSource + "?${_csrf.parameterName}=${_csrf.token}",
                    "data": aoData,
                    "success": fnCallback
                }).done(function (data) {
                    _data_list = data.aaData;
                });
            },
            "aoColumns": [
                {"mData": null, "sTitle": "#", "sWidth": "30px"},

                {"mData": "noOrder", "sTitle": "No Order"},

                {"mData": "plusId", "sTitle": "Plus ID"},
                {"mData": "jenisAppraisal", "sTitle": "Jenis Appraisal"},

                {"mData": "fortmatDate", "sTitle": "Tanggal"},
                {"mData": "message", "sTitle": "Message"}


            ],
            "aoColumnDefs": [
                {
                    "aTargets": [0],
                    "mRender": function (data, type, row) {
                        return drawTindakan(data);
                    }
                }

            ],
            "fnDrawCallback": function (settings, json) {
                bindingLink();
                mask_form();
            }
        });

        function drawTindakan(_row) {
            var _dom = '' +
                    '<div class="action-group">' +
                    '<div class="btn-group m-b-sm navbar-right">' +
                    '<a data-id="' + _row.idCommon + '" title="Kirim Ulang Data" class="btn btn-small btn-success click-edit-data"><i class="icon-white icon-chevron-up"></i></a> ' +
                    '</div>' +
                    '</div>';
            return _dom;
        }

        function bindingLink() {
            $(".click-edit-data").unbind('click');
            $(".click-edit-data").bind('click', function() {
                if(window.confirm('Are you sure to Send data?')){
                    var _a = $(this);
                    var _id = _a.data('id');
                    $.ajax({
                        type : "POST",
                        url : "<s:url value="re-send-plus"/>",
                        dataType : "json",
                        data : {
                            "id" : _id,
                            "${_csrf.parameterName}": "${_csrf.token}"
                        }
                    }).success(function(data) {
                        if(data.code == 200) {
                            dataTablePlus.DataTable().ajax.reload();
                            window.alert('Success Send data');
                        } else {
                            window.alert(data.msg);
                        }
                    });
                }
            });





            <%--$(".click-edit-data").unbind('click');--%>
            <%--$(".click-edit-data").bind('click', function () {--%>
                <%--var _a = $(this);--%>
                <%--var _id = _a.data('id');--%>
                <%--var _tr = _a.parent().parent().parent().parent();--%>
                <%--_tr.hide('slow');--%>
                <%--$.ajax({--%>
                    <%--type : "POST",--%>
                    <%--url : "<s:url value="re-send-plus"/>",--%>
                    <%--data : {--%>
                        <%--"id" : _id,--%>
                        <%--"${_csrf.parameterName}": "${_csrf.token}"--%>
                    <%--}--%>
                <%--})--%>
                <%--return false;--%>
            <%--});--%>

        }

        // component event
        $('#btn-search').click(function () {
            $('#form_search').toggle('slow');
        });
        $('#form_search').submit(function () {
            dataTableKodePos.DataTable().ajax.reload();
            $('#form_search').toggle('slow');
            return false;
        });
        // main app
        dataTablePlus.DataTable().ajax.reload();
    });
</script>
</body>
</html>
