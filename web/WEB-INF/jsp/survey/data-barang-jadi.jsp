<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.permata.appraisal2.model.PersonLevel" %>
<c:set var="showButton" value="false"/>
<c:set var="surveyor" value="<%=PersonLevel.APPRAISAL_SURVEYOR%>"/>
<c:if test="${roleActive.level == surveyor}">
    <c:set var="showButton" value="true"/>
</c:if>
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
    <link href="${ctxPath}/css/main-app.css" rel="stylesheet">

    <!--[if lt IE 9]>
    <script src="${ctxPath}/js/html5shiv.js"></script>
    <script src="${ctxPath}/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <div class="page-header">
        <h3>Form ${labelGoods}</h3>
    </div>
    <c:if test="${not empty msg}">
        <div class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${msg}</strong>
        </div>
    </c:if>
    <f:form method="post" modelAttribute="formBean" action="save-barang?${_csrf.parameterName}=${_csrf.token}" class="form-horizontal"
            enctype="multipart/form-data">
        <f:hidden path="id"/>
        <%--<f:hidden path="detailId"/>--%>
        <f:hidden path="surveyInspeksiId"/>
        <f:hidden path="type" />
        <div class="control-group span6">
            <label class="control-label" for="name">${labelGoods}</label>
            <div class="controls">
                <f:input path="name"/>
                <f:errors path="name" cssClass="help-block error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="totalDoc">Jumlah Sesuai Dokumen</label>
            <div class="controls">
                <f:input path="totalDoc" data-mask="number" />
                <f:errors path="totalDoc" cssClass="help-block error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="totalFisik">Jumlah Sesuai Fisik</label>
            <div class="controls">
                <f:input path="totalFisik" data-mask="number" />
                <f:errors path="totalFisik" cssClass="help-block error"/>
            </div>
        </div>

        <div class="control-group span12">
            <label class="control-label">&nbsp;</label>
            <div class="controls">
                <c:if test="${showButton}">
                <button type="submit" class="btn btn-success"><i class="icon-white icon-ok-circle"></i> Save</button>
                <button id="btn-reset" type="reset" class="btn btn-danger"><i class="icon-white icon-refresh"></i> Reset</button>
                </c:if>
                <button type="button" class="btn btn-warning" onclick="gotoUrl('show-barang?detailId=${detailId}&typeGoods=${formBean.type}')"><i
                        class="icon-white icon-step-backward"></i> Back
                </button>
            </div>
        </div>
    </f:form>
</div>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<script src="${ctxPath}/js/jquery.inputmask.bundle.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#btn-manual').click(function () {
            $("#detail-from").val("MANUAL");
            return false;
        });
        $('#btn-toggle').click(function () {
            $('#div-info').toggle('slow');
        });
        $(".btn-delete-image ").click(function () {
            var t = $(this);
            var p = t.parent().parent();
            p.find('input[id=input-delete]').val(true);
            p.find('input[type=file]').prop( "disabled", true );
        })
        $('#btn-reset').click(function () {
            $('input[type=file]').prop( "disabled", false );
        })
    });
</script>
</body>
</html>