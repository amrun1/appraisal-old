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
    <title>Add Indikasi Formula</title>
</head>
<body>
<div class="container">
    <div class="page-header">
        <h1>New Formula</h1>
    </div>


    <form method="post" action="save?${_csrf.parameterName}=${_csrf.token}" modelAttribute="formBean" class="form-horizontal">
        <input id="code" type="hidden"  name="code" value="${formBean.code}"/>
        <div class="control-group">
            <label class="control-label" for="type">Type</label>
            <div class="controls">
                <select id="type" name="type" >
                    <c:forEach items="${listType}" var="row">
                        <c:if test="${row == formBean.type}">
                            <option value="${row}" selected="selected">${row}</option>
                        </c:if>

                        <c:if test="${row!=formBean.type}">
                            <option value="${row}">${row}</option>
                        </c:if>

                    </c:forEach>
                </select>
            </div>
        </div>


        <div class="control-group">
            <input id="code" type="hidden"  name="code" value="${formBean.code}"/>
            <label class="control-label" for="luas1">Luas 1</label>
            <div class="controls">
                <input type="text" id="luas1" name="luas1" value="${formBean.luas1}"  placeholder="Luas 1" data-mask="luas-tanah"/>

            </div>
        </div>

        <div class="control-group">
            <label class="control-label" for="luas2">Luas 2</label>
            <div class="controls">
                <input type="text" id="luas2" name="luas2" value="${formBean.luas2}"  placeholder="Luas 2" data-mask="luas-tanah"/>

            </div>
        </div>

        <div class="control-group">
            <label class="control-label" for="nilai">Nilai lama</label>
            <div class="controls">
                <input type="text" id="nilai" name="nilai" value="${formBean.nilai}" data-mask="currency" placeholder="Nilai"/>

            </div>
        </div>
        <div class="control-group">
            <label class="control-label">&nbsp;</label>
            <div class="controls">
                <button type="submit" class="btn btn-small btn-success"><i class="icon-white icon-ok"></i> Save</button>
                <button type="button" class="btn btn-small btn-warning" onclick="gotoUrl('list')"><i class="icon-white icon-step-backward"></i> Back</button>

            </div>
        </div>
    </form>
 </div>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<script src="${ctxPath}/js/jquery.inputmask.bundle.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>

<script type="text/javascript">
    $(document).ready(function () {

        $.active = false;
        $('body').bind('click keypress', function() { $.active = true; });
        var logout = '${ctxPath}/logoutExpired';
        var heartBeat = '${ctxPath}/heartbeat';
        checkActivity(${maxInterval}, 60000, 0,logout,heartBeat);

    });

</script>


</body>
</html>
