<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
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

        <!--[if lt IE 9]>
        <script src="${ctxPath}/js/html5shiv.js"></script>
        <script src="${ctxPath}/js/respond.min.js"></script>
        <![endif]-->
    </head>
<body>
<div class="container">
    <div class="page-header">
        <h3>Pencarian Nilai Tanah Desktop</h3>
    </div>
    <div class="btn-group navbar-right" style="float:right;">
        <a id="btn-search" type="button" class="btn btn-info"><i class="icon-white icon-filter"></i> Filter</a>
    </div>
    <form id="form_search" method="get" action="#" class="form-horizontal">
        <div class="control-group">
            <label class="control-label" for="fnama">Nama Tempat</label>
            <div class="controls">
                <input name="fnama" id="fnama" type="text" class="form-control"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="fjalan">Nama Jalan</label>
            <div class="controls">
                <input name="fjalan" id="fjalan" type="text" class="form-control"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="fposisi">Posisi</label>
            <div class="controls">
                <input name="fposisi" id="fposisi" type="text" class="form-control"/>
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
    <table id="datatables" class="table table-striped bg-white" width="100%"></table>
</div>

<script type="text/javascript" src="${ctxPath}/js/jquery-ui-1.10.3/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${ctxPath}/js/DataTables-1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
    var dataTable;
    var _csrf_token = "${_csrf.token}";
    $(document).ready(function () {
        var _data_list;
        dataTable = $('#datatables').dataTable({
            "sAjaxSource": "find",
            "fnServerData": function (sSource, aoData, fnCallback, oSettings) {
                var _form_search = $("#form_search");
                aoData.push(
                        {
                            "name": "nama",
                            "value": _form_search.find('input[name=fnama]').val()
                        },
                        {
                            "name": "jalan",
                            "value": _form_search.find('input[name=fjalan]').val()
                        },
                        {
                            "name": "posisi",
                            "value": _form_search.find('input[name=fposisi]').val()
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
                {"mData": null, "sTitle": "Action", "sWidth": "30px"},
                {"mData": "nama", "sTitle": "Nama Tempat"},
                {"mData": "jalan", "sTitle": "Nama Jalan"},
                {"mData": "posisi", "sTitle": "Posisi"},
                {"mData": "harga", "sTitle": "Nilai"}
            ],
            "aoColumnDefs": [
                {
                    "aTargets": [0],
                    "mRender": function (data, type, row) {
                        return drawTindakan(data);
                    }
                },
                {
                    "aTargets": [4],
                    "mRender": function (data, type, row) {
                        return data;
                    }
                }
            ],
            "fnDrawCallback": function (settings, json) {
                bindingLink();
            }
        });
        function drawTindakan(_row) {
            var _dom = '' +
                    '<div class="action-group">' +
                    '<div class="btn-group m-b-sm navbar-right">' +
                    '<a data-id="' + _row.id + '" title="Choose Data" class="btn btn-small btn-success click-edit-data"><i class="icon-white icon-edit"></i></a>' +
                    '</div>' +
                    '</div>';
            return _dom;
        }

        function bindingLink() {
            $(".click-edit-data").unbind('click');
            $(".click-edit-data").bind('click', function () {
                var _a = $(this);
                var _id = _a.data('id');
                var callback_function = null;

                if (typeof callbackNilaiTanah === 'function') {
                    //console.log('used dialog to open this lookup');
                    callback_function = callbackNilaiTanah;
                } else if (window.opener != null) {
                    //console.log("used open new window to open this lookup");
                    callback_function = window.opener.callbackNilaiTanah;
                } else {
                    alert("no callback function");
                }

                if(callback_function != null) {
                    if (confirm('Anda yakin akan pilih data?')) {
                        for (var i = 0; i < _data_list.length; i++) {
                            var row = _data_list[i];
                            //console.log(row);
                            if (row.id == _id) {
                                callback_function(row);
                            }
                        }
                    }
                }
            });
        }

        // component event
        $('#btn-search').click(function () {
            $('#form_search').toggle('slow');
        });
        $('#form_search').submit(function () {
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