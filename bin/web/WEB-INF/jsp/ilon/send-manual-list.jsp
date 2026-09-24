<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.permata.appraisal2.model.IloanProgres" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <h3>List Data RWA Send Manual</h3>
    </div>
    <%--<div class="alert alert-info">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <strong>${msg}</strong>
    </div>--%>
    <form id="form_search" method="get" action="#" modelAttribute="formSearch" class="form-horizontal">
        <div class="control-group">
            <label class="control-label" for="fname">Nama Debitur</label>
            <div class="controls">
                <input name="fname" id="fname" type="text" class="form-control"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="fcollateralId">Collateral ID</label>
            <div class="controls">
                <input name="fcollateralId" id="fcollateralId" type="text" class="form-control"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="fgroupVal">Group Join Value</label>
            <div class="controls">
                <input name="fgroupVal" id="fgroupVal" type="text" class="form-control"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="flocation">Location</label>
            <div class="controls">
                <input name="flocation" id="flocation" type="text" class="form-control"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="ftitleNumber">Title Number</label>
            <div class="controls">
                <input name="ftitleNumber" id="ftitleNumber" type="text" class="form-control"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="priority" >Group Priority</label>
            <div class="controls">
                <select id="priority">
                    <option value="">-- Select --</option>
                    <c:forEach items="${lvPriortyCategory}" var="row">
                        <option value="${row.value}">${row.label}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="maturDate">Mature Date</label>
            <div class="controls">
                <input id="maturDate" name="maturDate"  data-date-format="dd-mm-yyyy"
                         data-provide="datepicker"  class="tutup-otomatis" placeholder="DD-MM-YYYY" data-date-start-date="0d"/> s/d

                <input id="createdDate" name="createdDate" data-date-format="dd-mm-yyyy"
                       data-provide="datepicker"  class="tutup-otomatis" placeholder="DD-MM-YYYY" data-date-start-date="0d"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">&nbsp;</label>
            <div class="controls">
                <button id="btn_fsearch" type="submit" class="btn btn-success"><i class="icon-white icon-search"></i>
                    Search
                </button>
                <button type="reset" class="btn btn-warning"><i class="icon-white icon-refresh"></i> Reset</button>
            </div>
        </div>
    </form>
    <table id="table-list-kode-pos" class="table table-striped bg-white" width="100%"></table>
</div>

<script type="text/javascript" src="${ctxPath}/js/jquery-ui-1.10.3/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/DataTables-1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/jquery.inputmask.bundle.min.js"></script>
<script src="${ctxPath}/js/bootstrap-datepicker-1.6.4/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
    var dataTableKodePos;
    var _csrf_token = "${_csrf.token}";

    $(document).ready(function () {
        var _data_list;

        $('#maturDate').datepicker({
            autoclose: true
        });

        $('#createdDate').datepicker({
            autoclose: true
        });


        dataTableKodePos = $('#table-list-kode-pos').dataTable({
            // 'bSort': true,
            "sAjaxSource": "find",
            "fnServerData": function (sSource, aoData, fnCallback, oSettings) {
                var _form_search = $("#form_search");
                aoData.push(
                        {
                            "name": "name",
                            "value": _form_search.find('input[name=fname]').val()
                        },
                        {
                            "name": "collateralId",
                            "value": _form_search.find('input[name=fcollateralId]').val()
                        },
                        {
                            "name": "groupVal",
                            "value": _form_search.find('input[name=fgroupVal]').val()
                        },
                        {
                            "name" : "status",
                            "value" : <%= IloanProgres.STATUS_SEND_MANUAL %>
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
                {"mData": "orderNumber", "sTitle": "Nomor Order"},
                {"mData": "name", "sTitle": "Nama Debitur"},
                {"mData": "collateralId", "sTitle": "Collateral ID"},
                {"mData": "groupVal", "sTitle": "Group Join Value"},
                {"mData": "waktuKirim", "sTitle": "Waktu Kirim"},
                {"mData": "plafond", "sTitle": "Plafon"}
            ],
            "aoColumnDefs": [
                {
                    "aTargets": [0],
                    "mRender": function (data, type, row) {
                        return drawTindakan(data);
                    }
                },
                {
                    "aTargets": [5],
                    "mRender": function (data, type, row) {
                        return date_format_full(data);
                    }
                },
                {
                    "aTargets": [6],
                    "mRender": function (data, type, row) {
                        return '<div data-mask="luas-tanah">'+data+'</div>';
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
                    '<a data-id="' + _row.dataCommonId + '" title="Kirim Ulang Data" class="btn btn-small btn-success click-edit-data"><i class="icon-white icon-pencil"></i></a> ' +
                    '</div>' +
                    '</div>';
            return _dom;
        }

        function bindingLink() {
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
        dataTableKodePos.DataTable().ajax.reload();
    });
</script>
</body>
</html>
