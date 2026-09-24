<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
    <meta http-equiv="Expires" content="-1"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${ctxPath}/js/bootstrap_2.3.2/css/bootstrap.css" rel="stylesheet">
    <!--[if gte IE 9]><!-->
    <link rel="stylesheet" media="screen" href="${ctxPath}/css/glyphicons/glyphicons.min.css">
    <!--<![endif]-->
    <link href="${ctxPath}/css/main-app.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <form action="form-send-to-mobile?${_csrf.parameterName}=${_csrf.token}" method="post" class="form-horizontal" id="form-send-to-mobile">
        <input type="hidden" name="commondId" value="${commondId}">
        <input type="hidden" name="valuerId" value="${person.userId}">
        <c:if test="${sendToMobileObj != null}">
            <div class="alert alert-error">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>Data sudah dikirim ke Mobile</strong>
            </div>
        </c:if>
        <div class="control-group">
            <label class="control-label" for="surveyorId">Nama Surveyor</label>
            <div class="controls">
                <select id="surveyorId" name="surveyorId">
                    <option value="">-- Select --</option>
                    <c:forEach items="${listSurveyor}" var="row">
                        <option value="${row.value}">${row.label}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">&nbsp;</label>
            <div class="controls">
                <c:if test="${sendToMobileObj == null}">
                    <button type="submit" class="btn btn-success"><i class="icon-white icon-ok"></i> Kirim Data</button>
                </c:if>
            </div>
        </div>
    </form>
</div>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<!--[if lt IE 9]>
<script src="${ctxPath}/js/html5shiv.js"></script>
<script src="${ctxPath}/js/respond.min.js"></script>
<![endif]-->
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
    });
</script>
</body>
</html>
