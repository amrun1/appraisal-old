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
    <link href="${ctxPath}/js/DataTables-1.10.12/css/dataTables.bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link href="${ctxPath}/css/main-app.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <f:form method="post" modelAttribute="formBean" action="save-role" class="form-horizontal" id="form-form">
        <f:hidden path="id"/>
        <f:hidden path="userId"/>
        <c:if test="${not empty error}">
            <div class="alert alert-error">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>${error}</strong>
            </div>
        </c:if>
        <div class="control-group">
            <label class="control-label" for="level">Role</label>
            <div class="controls">
                <f:select path="level">
                    <f:option value="" label="-- Select --"/>
                    <f:options items="${listLevel}" itemLabel="value"/>
                </f:select>
                <f:errors path="level" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="branchId">Cabang Bisnis</label>
            <div class="controls">
                <f:select path="branchId">
                    <f:option value="" label="-- Select --"/>
                    <f:options items="${listBranch}" itemLabel="label" itemValue="value"/>
                </f:select>
                <f:errors path="branchId" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="appBranchId">Cabang Appraisal</label>
            <div class="controls">
                <f:select path="appBranchId">
                    <f:option value="" label="-- Select --"/>
                    <f:options items="${listAppBranch}" itemLabel="label" itemValue="value"/>
                </f:select>
                <f:errors path="appBranchId" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="limitApproval">Limit Approve</label>
            <div class="controls">
                <f:input path="limitApproval" class="form-control" data-mask="luas-tanah"/>
                <f:errors path="limitApproval" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">&nbsp;</label>
            <div class="controls">
                <button type="submit" id ="simpen" class="btn btn-small btn-success"><i class="icon-white icon-ok"></i> Save</button>
            </div>
        </div>
    </f:form>
</div>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/DataTables-1.10.12/js/jquery.dataTables.min.js"></script>
<script src="${ctxPath}/js/jquery.inputmask.bundle.min.js"></script>
<!--[if lt IE 9]>
    <!--<script src="${ctxPath}/js/html5shiv.js"></script>-->
<!--<script src="${ctxPath}/js/respond.min.js"></script>-->
<![endif]-->
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#level').change(function () {
            $('#branchId').prop('disabled', true);
            $('#branchId').val('');
            $('#appBranchId').prop('disabled', true);
            $('#appBranchId').val('');
            $('#limitApproval').prop('readonly', true);
            $('#limitApproval').val(0);
            var level = $(this).find('option:selected').val();
            if (level == "MARKETING") {
                $('#branchId').prop('disabled', false);
            } else if (level == "APPRAISAL_APPROVAL" || level == "APPRAISAL_ADMIN" || level == "APPRAISAL_VALUER"
                    || level == "APPRAISAL_SURVEYOR") {
                $('#appBranchId').prop('disabled', false);
                if (level == "APPRAISAL_APPROVAL") {
                    $('#limitApproval').prop('readonly', false);
                }
            }
        });
        <c:if test="${formBean.id != null}">
        $('#level').prop('disabled', true);
        $('#branchId').prop('disabled', true);
        $('#appBranchId').prop('disabled', true);
        $('#limitApproval').prop('readonly', true);
        var level = $('#level').find('option:selected').val();
        if (level == "MARKETING") {
            $('#branchId').removeProp('disabled');
        } else if (level == "APPRAISAL_APPROVAL" || level == "APPRAISAL_ADMIN" || level == "APPRAISAL_VALUER"
                || level == "APPRAISAL_SURVEYOR") {
            $('#appBranchId').removeProp('disabled');
            if (level == "APPRAISAL_APPROVAL") {
                $('#limitApproval').prop('readonly', false);
            }
        }


        $("#simpen").click(function(){
            $('#level').prop('disabled', false);
        });


        </c:if>
        <c:if test="${formBean.id == null}">
        $('#level').trigger('change');
        </c:if>
    });
</script>
</body>
</html>