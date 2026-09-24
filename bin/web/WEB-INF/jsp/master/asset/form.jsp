<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
    <link href="${ctxPath}/js/datatables.css" rel="stylesheet">
    <link href="${ctxPath}/js/DataTables-1.10.12/css/dataTables.bootstrap.min.css" type="text/css" rel="stylesheet" />
    <link href="${ctxPath}/css/main-app.css" rel="stylesheet">

</head>
<body>
<div class="container">
    <div class="page-header">
        <h1>Master Aktiva Add/Edit</h1>
    </div>
    <c:if test="${not empty msg}">
        <div class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${msg}</strong>
        </div>
    </c:if>
    <f:form method="post" modelAttribute="formBean" action="save" class="form-horizontal">
        <f:hidden path="id"/>
        <div class="control-group">
            <label class="control-label" for="categoryCode">Jenis Kategori</label>
            <div class="controls">
                <f:select path="categoryCode">
                    <f:option value="" label="-- Select --"/>
                    <f:options items="${lvJenisCategory}" itemValue="value" itemLabel="label"/>
                </f:select>
                <f:errors path="categoryCode" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="name">Nama</label>
            <div class="controls">
                <f:input path="name" type="text" class="form-control" placeholder="Nama"/>
                <f:errors path="name" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="name">&nbsp;</label>
            <div class="controls">
                <button type="submit" class="btn btn-small btn-success"><i class="icon-white icon-ok"></i> Save</button>
                <button type="button" class="btn btn-small btn-warning" onclick="gotoUrl('list')"><i
                        class="icon-white icon-step-backward"></i> Back
                </button>
            </div>
        </div>
    </f:form>
</div>
<c:if test="${formBean.id != null}">
    <div class="page-header">
        <div class="absolute-right">
            <button type="button" id="btn-add-jenis-doc" class="btn btn-small btn-success"><i class="icon-white icon-plus"></i> Add Jenis Dokumen</button>
        </div>
        <h3>Data Jenis Dokumen</h3>
    </div>
    <table id="table-list" class="table table-striped bg-white" width="100%"></table>
    <div id="modal-jenis-doc" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="modal-jenis-doc-label" aria-hidden="true" data-backdrop="static" data-keyboard="false">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
            <h3 id="modal-jenis-doc-label">Data Jenis Dokumen</h3>
        </div>
        <div class="modal-body"></div>
    </div>
</c:if>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/DataTables-1.10.12/js/jquery.dataTables.min.js"></script>
<!--[if lt IE 9]>
    <!--<script src="${ctxPath}/js/html5shiv.js"></script>-->
<!--<script src="${ctxPath}/js/respond.min.js"></script>-->
<![endif]-->
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
    <c:if test="${formBean.id != null}">
    $('#categoryCode').prop('disabled', true);
    var dataTable;
    var callbackModal;
    $(document).ready(function () {

        $.active = false;
        $('body').bind('click keypress', function() { $.active = true; });
        var logout = '${ctxPath}/logoutExpired';
        var heartBeat = '${ctxPath}/heartbeat';
        checkActivity(${maxInterval}, 60000, 0,logout,heartBeat);

        dataTable = $('#table-list').dataTable({
            "sAjaxSource" : "<s:url value="/master/jenis-dokumen/find"/>",
            "fnServerData" : function(sSource, aoData, fnCallback, oSettings) {
                var _form_search = $("#form_search");
                aoData.push(
                        {
                            "name" : "idAssetType",
                            "value" : ${formBean.id}
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
                {"mData" : "jatuhTempo", "sTitle" : "Ada Jatuh Tempo"}
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
                $('#modal-jenis-doc').find(".modal-body").load("${ctxPath}/master/jenis-dokumen/edit?jenisAktiva=${formBean.id}&id="+_id, function(){
                    // initFormTanah();
                    $('#modal-jenis-doc').modal('show');
                    initFormJenisDoc();
                });
                return false;
            });
            $(".click-delete-data").unbind('click');
            $(".click-delete-data").bind('click', function() {
                if(window.confirm('Are you sure to delete data?')){
                    var _a = $(this);
                    var _id = _a.data('id');
                    $.ajax({
                        type : "POST",
                        url : "<s:url value="/master/jenis-dokumen/delete"/>",
                        dataType : "json",
                        data : {
                            "id" : _id,
                            "${_csrf.parameterName}": "${_csrf.token}"
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
        $('#btn-add-jenis-doc').click(function(){
            $('#modal-jenis-doc').find(".modal-body").load("${ctxPath}/master/jenis-dokumen/add?jenisAktiva=${formBean.id}", function(){
                // initFormTanah();
                $('#modal-jenis-doc').modal('show');
                initFormJenisDoc();
            });
            return false;
        });
        function initFormJenisDoc(){
            $('#form-jenis-doc').submit(function(){
                $.ajax({
                    type: $(this).attr('method'),
                    cache: false,
                    dataType: "html",
                    url: $(this).attr('action'),
                    data: $(this).serialize(),
                    complete: function (data) {
                        $('#modal-jenis-doc').find(".modal-body").html(data.responseText);
                        initFormJenisDoc();
                    }
                });
                return false;
            });
        }
        callbackModal = function(){
            dataTable.DataTable().ajax.reload();
            $('#modal-jenis-doc').modal('hide');
        }

        // main thread app
        dataTable.DataTable().ajax.reload();
    });
    </c:if>
</script>
</body>
</html>