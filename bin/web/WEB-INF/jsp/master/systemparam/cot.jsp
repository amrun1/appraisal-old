<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%--<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>--%>
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
    <link href="${ctxPath}/css/main-app.css" rel="stylesheet">
    <title>Update COT</title>
</head>
<body>
<div class="container">
    <div class="page-header">
        <h1>Update COT</h1>
    </div>
    <c:if test="${not empty msg}">
        <div class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${msg}</strong>
        </div>
    </c:if>


    <form method="post" action="updateCot?${_csrf.parameterName}=${_csrf.token}" modelAttribute="formBean" class="form-horizontal">
        <div class="control-group">
            <label class="control-label" for="jam">Hour</label>
            <div class="controls">
                <select id="value" name="value" >
                    <option value="">-- Select --</option>
                    <c:forEach items="${listJam}" var="row">
                        <c:if test="${row == formBean.value}">
                            <option value="${row}" selected="selected">${row}</option>
                        </c:if>

                        <c:if test="${row!=formBean.value}">
                            <option value="${row}">${row}</option>
                        </c:if>

                    </c:forEach>
                </select>
                <b>:</b>
                <select id="code" name="code" >
                    <option value="">-- Select --</option>
                    <c:forEach items="${listMenit}" var="row">

                        <c:if test="${row == formBean.code}">
                            <option value="${row}" selected="selected">${row}</option>
                        </c:if>

                        <c:if test="${row!=formBean.code}">
                            <option value="${row}">${row}</option>
                        </c:if>


                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">&nbsp;</label>
            <div class="controls">
                <button type="submit" class="btn btn-small btn-success"><i class="icon-white icon-ok"></i> Save</button>

            </div>
        </div>
    </form>
 </div>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
</body>
</html>
