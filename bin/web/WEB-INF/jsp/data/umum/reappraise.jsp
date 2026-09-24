<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.permata.appraisal2.common.AppConstans" %>
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
    <style type="text/css">
        /*.table {
            width: auto;
        }
        .table.dataTable {
            width: auto;
        }*/
        div.dataTables_scrollHeadInner {
            width: 100%;
        }
        div.dataTables_wrapper {
            width: 1500px;
            margin: 0 auto;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="page-header-page">
        <div class="absolute-right">
            <a href="javascript:toggleTarget('#form-filter')" title="Show Form Filter" class="btn btn-small btn-success"><i class="icon-white icon-filter"></i></a>
        </div>
        <h1>Order Data List Re-Appraise</h1>
        <div>
            <f:form id="form-filter" method="post" modelAttribute="formBean" action="#" cssClass="controls-row form-horizontal panel hide">
                <div class="control-group span6">
                    <label class="control-label" for="noOrder">No. Order</label>
                    <div class="controls">
                        <f:input path="noOrder"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="noLaporan">No. Laporan</label>
                    <div class="controls">
                        <f:input path="noLaporan"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="namaDebitur">Nama Debitur</label>
                    <div class="controls">
                        <f:input path="namaDebitur"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="lokasi">Lokasi Agunan</label>
                    <div class="controls">
                        <f:input path="lokasi"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="jenisAppraisal">Jenis Appraisal</label>
                    <div class="controls">
                        <f:select path="jenisAppraisal">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listJenisAppraisal}" itemValue="value" itemLabel="label"/>
                        </f:select>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="kategoriAktiva">Kategori Aktiva</label>
                    <div class="controls">
                        <f:select path="kategoriAktiva">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listKategoriAktiva}" itemValue="value" itemLabel="label"/>
                        </f:select>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="jenisAktiva">Jenis Aktiva</label>
                    <div class="controls">
                        <f:select path="jenisAktiva">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listJenisAktiva}" itemValue="id" itemLabel="label"/>
                        </f:select>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="cabangBisnis">Cabang Bisnis</label>
                    <div class="controls">
                        <f:select path="cabangBisnis">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listCabangBisnis}" itemValue="value" itemLabel="label"/>
                        </f:select>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="cabangAppraisal">Cabang Appraisal</label>
                    <div class="controls">
                        <f:select path="cabangAppraisal">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listCabangAppraisal}" itemValue="value" itemLabel="label"/>
                        </f:select>
                    </div>
                </div>
                <div class="control-group span12" style="margin-top: 15px; padding-left: 20px;">
                    <label class="control-label">&nbsp;</label>
                    <button type="submit" class="btn btn-small btn-success"><i class="icon-white icon-search"> </i>Cari Data</button>
                    <button type="reset" class="btn btn-small btn-warning"><i class="icon-white icon-remove-sign"> </i>Reset Form</button>
                </div>
            </f:form>
        </div>
    </div>
    <div style="margin-top: 50px; margin-bottom: 10px;">&nbsp;</div>
    <div style="overflow-x: auto; padding: 0px;	" data-height="100%" data-disable-fade-out="true" data-distance="0" data-size="10px" data-color="#00bddd">
        <table id="table-list" class="table dataTable table-hover"></table>
    </div>
</div>
<!--[if lt IE 9]>
<script type="text/javascript" src="${ctxPath}/js/html5shiv.js"></script>
<script type="text/javascript" src="${ctxPath}/js/respond.min.js"></script>
<![endif]-->
<script type="text/javascript" src="${ctxPath}/js/jquery-ui-1.10.3/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${ctxPath}/js/DataTables-1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
    var dataTable;
    var _csrf_token = "${_csrf.token}";
    // init component
    $(document).ready(function() {
        $("#form-filter").submit(function(){
            dataTable.DataTable().ajax.reload();
            $('#form-filter').toggle('slow');
            return false;
        });
        dataTable = $('#table-list').dataTable({
            // "scrollX": true,
            "sAjaxSource" : "<s:url value="find-reappraise"/>",
            "fnServerData" : function(sSource, aoData, fnCallback, oSettings) {
                var _form_search = $("#form-filter");
                aoData.push(
                        {
                            "name" : "noOrder",
                            "value" : _form_search.find("input[name=noOrder]").val()
                        },
                        {
                            "name" : "namaDebitur",
                            "value" : _form_search.find("input[name=namaDebitur]").val()
                        },
                        {
                            "name" : "noLaporan",
                            "value" : _form_search.find("input[name=noLaporan]").val()
                        },
                        {
                            "name" : "lokasi",
                            "value" : _form_search.find("input[name=lokasi]").val()
                        },
                        {
                            "name" : "posisi",
                            "value" : 'CLOSE'
                        },
                        {
                            "name" : "cabangAppraisal",
                            "value" : _form_search.find("select[name=cabangAppraisal]").val()
                        },
                        {
                            "name" : "cabangBisnis",
                            "value" : _form_search.find("select[name=cabangBisnis]").val()
                        },
                        {
                            "name" : "jenisAktiva",
                            "value" : _form_search.find("select[name=jenisAktiva]").val()
                        },
                        {
                            "name" : "kategoriAktiva",
                            "value" : _form_search.find("select[name=kategoriAktiva]").val()
                        },
                        {
                            "name" : "jenisAppraisal",
                            "value" : _form_search.find("select[name=jenisAppraisal]").val()
                        }
                );
                $.ajax({
                    "dataType" : 'json',
                    "type" : "GET",
                    "url" : sSource + "?task=${task}&${_csrf.parameterName}=${_csrf.token}",
                    "data" : aoData,
                    "success" : fnCallback
                });
            },
            "aoColumns" : [
                {"mData" : null, "sTitle" : "Action", "sWidth" : "40px"},
                {"mData" : "noOrder", "sTitle" : "No. Order", "sWidth" : "90px"},
                {"mData" : "noLaporan", "sTitle" : "No. Laporan", "sWidth" : "90px"},
                {"mData" : "namaDebitur", "sTitle" : "Nama Debitur", "sWidth" : "200px"},
                {"mData" : "lokasi", "sTitle" : "Lokasi"},
                {"mData" : "kelurahan", "sTitle" : "Kelurahan/ Kecamatan/ Kota/ Propinsi", "sWidth" : "120px"},
                {"mData" : "kategoriAppraisal", "sTitle" : "Jenis Appraisal/ Object", "sWidth" : "120px"},
                {"mData" : "posisi", "sTitle" : "Posisi", "sWidth" : "80px"},
                {"mData" : "cabangAppraisal", "sTitle" : "Appraisal Cabang", "sWidth" : "80px"},
                {"mData" : "jenisLaporan", "sTitle" : "Jenis Laporan", "sWidth" : "80px"},
                {"mData" : "userId", "sTitle" : "Nama Marketing/ Cabang Bisnis", "sWidth" : "110px"}
            ],
            "aoColumnDefs": [
                {
                    "aTargets": [0],
                    "mRender": function (data,type,row) {
                        return drawTindakan(data);
                    }
                },
                {
                    "aTargets": [5],
                    "mRender": function (data, type, row) {
                        return data + '<br><b>' + row.kecamatan + '</b><br>' + row.kota + '<br><b>' + row.propinsi+'</b>';
                    }
                },
                {
                    "aTargets": [6],
                    "mRender": function (data, type, row) {
                        if(data == null){
                            return row.jenisAppraisal;
                        }
                        return data + '<br><b>' + row.jenisAppraisal + '</b><br>' + row.kategoriAktiva + '<br><b>' + row.jenisAktiva + '</b>';
                    }
                },
                {
                    "aTargets": [10],
                    "mRender": function (data, type, row) {
                        if(data == null){
                            return row.jenisAppraisal;
                        }
                        return data + '<br><b>' + row.namaMarketing + '</b><br>'+row.cabangBisnis;
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
                    '<a data-id="'+ _row.id+'" title="Select Data" class="btn btn-small btn-success click-edit-data"><i class="icon-white icon-check"></i></a>'+
                    '</div>'+
                    '</div>';
            return _dom;
        }
        function bindingLink(){
            $(".click-edit-data").unbind('click');
            $(".click-edit-data").bind('click', function() {
                if(confirm("Anda yakin akan pilih data?")){
                    var _a = $(this);
                    var _id = _a.data('id');
                    window.opener.callBackReappraise(_id);
                }
            });
        }
        $('#refresh').click(function(){
            dataTable.DataTable().ajax.reload();
        });

        // main app
        dataTable.DataTable().ajax.reload();
    });
</script>
</body>
</html>