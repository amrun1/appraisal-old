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
        <h1>User Add/Edit</h1>
    </div>
    <c:if test="${not empty error}">
        <div class="alert alert-error">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${error}</strong>
        </div>
    </c:if>

    <c:if test="${not empty msg}">
        <div class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${msg}</strong>
        </div>
    </c:if>

    <f:form method="post" modelAttribute="formBean" action="save" class="form-horizontal">
        <f:hidden path="update"/>
        <div class="control-group">
            <label class="control-label" for="userId">Username</label>
            <div class="controls">
                <c:if test="${formBean.update}">
                <f:input path="userId" readonly="true" class="form-control"/>
                </c:if>
                <c:if test="${!formBean.update}">
                    <f:input path="userId" class="form-control"/>
                </c:if>
                <f:errors path="userId" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="name">Nama</label>
            <div class="controls">
                <f:input path="name" class="form-control"/>
                <f:errors path="name" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="email">Email</label>
            <div class="controls">
                <f:input path="email" class="form-control"/>
                <f:errors path="email" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="phone">No. Telepon</label>
            <div class="controls">
                <f:input path="phone" class="form-control"/>
                <f:errors path="phone" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="hp">No. HP</label>
            <div class="controls">
                <f:input path="hp" data-mask="number" class="form-control"/>
                <f:errors path="hp" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="hpAtasan">No. HP Atasan</label>
            <div class="controls">
                <f:input path="hpAtasan" data-mask="number" class="form-control"/>
                <f:errors path="hpAtasan" class="error has-error"/>
            </div>
        </div>

        <c:if test="${!formBean.update}">
        <div class="control-group">
            <label class="control-label" for="inactive">Inactive</label>
            <div class="custom-ui controls">
                <f:radiobutton path="inactive" value="true" label="Ya"/>
                <f:radiobutton path="inactive" value="false" label="Tidak"/>
                <f:errors path="inactive" class="error has-error"/>
            </div>
        </div>
        </c:if>

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

    <c:if test="${formBean.update}">
        <div class="page-header">
            <div class="absolute-right">
                <button type="button" id="btn-add-form" class="btn btn-small btn-success"><i class="icon-white icon-plus"></i> Add Role</button>
            </div>
            <h3>Data Role User</h3>
        </div>
        <table id="table-list" class="table table-striped bg-white" width="100%"></table>
        <div id="modal-form" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="modal-form-label" aria-hidden="true" data-backdrop="static" data-keyboard="false">
            <div class="modal-header">
                <button type="button" id="btnClose" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                <h3 id="modal-form-label">Form Role</h3>
            </div>
            <div class="modal-body"></div>
        </div>
    </c:if>
</div>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/DataTables-1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/jquery.inputmask.bundle.min.js"></script>
<!--[if lt IE 9]>
    <!--<script src="${ctxPath}/js/html5shiv.js"></script>-->
<!--<script src="${ctxPath}/js/respond.min.js"></script>-->
<![endif]-->
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
    <c:if test="${formBean.update}">
    var dataTable;
    var callbackModal;
    $(document).ready(function () {

        $.active = false;
        $('body').bind('click keypress', function() { $.active = true; });
        var logout = '${ctxPath}/logoutExpired';
        var heartBeat = '${ctxPath}/heartbeat';
        checkActivity(${maxInterval}, 60000, 0,logout,heartBeat);

        dataTable = $('#table-list').dataTable({
            "sAjaxSource" : "<s:url value="find-role"/>",
            "fnServerData" : function(sSource, aoData, fnCallback, oSettings) {
                var _form_search = $("#form_search");
                aoData.push(
                        {
                            "name" : "userId",
                            "value" : "${formBean.userId}"
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
                {"mData" : "level", "sTitle" : "Role"},
                {"mData" : "branchName", "sTitle" : "Cabang Bisnis"},
                {"mData" : "appBranchName", "sTitle" : "Cabang Appraisal"},
                {"mData" : "limitApproval", "sTitle" : "Limit Approve"}
            ],
            "aoColumnDefs": [
                {
                    "aTargets": [0],
                    "mRender": function (data,type,row) {
                        return drawTindakan(data);
                    }
                },
                {
                    "aTargets": [4],
                    "mRender": function (data,type,row) {
                        if(data == null || data == undefined){
                            data = 0;
                        }
                        return '<div data-mask="luas-tanah" style="margin-right:10px">'+data+'</div>';
                    }
                }
            ],
            "fnDrawCallback" : function(settings, json){
                bindingLink();
                // mask_form();
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
                $('#modal-form').find(".modal-body").load("edit-role?userId=${formBean.userId}&id="+_id, function(){
                    $('#modal-form').modal('show');
                    initModalForm();
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
                        url : "<s:url value="delete-role"/>",
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
        $('#btn-add-form').click(function(){

            $('#modal-form').find(".modal-body").load("add-role?userId=${formBean.userId}", function(){
                // initFormTanah();
                $('#modal-form').modal('show');
                initModalForm();
            });
            return false;
        });
        function initModalForm(){
            dataTable.DataTable().ajax.reload();
            $('#form-form').submit(function(){
                $.ajax({
                    type: $(this).attr('method'),
                    cache: false,
                    dataType: "html",
                    url: $(this).attr('action'),
                    data: $(this).serialize(),
                    complete: function (data) {
                        $('#modal-form').find(".modal-body").html(data.responseText);
                        initModalForm();
                    }
                });
                return false;
            });
              }
        callbackModal = function(){
           dataTable.DataTable().ajax.reload();
            $('#btnClose').trigger('click');
           // $('#modal-form').modal('hide');
           console.info("Masuk callBack Modal");


        }

        // main thread app
        dataTable.DataTable().ajax.reload();
    });
    </c:if>
</script>
</body>
</html>