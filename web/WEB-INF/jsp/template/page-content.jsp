<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
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
    <link href="${ctxPath}/css/main-app.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="page-header">
        <h1>Appraisal Web</h1>
    </div>
    <div class="page-content">
        Content paste here
    </div>
</div>

<script type="text/javascript" src="js/bootstrap_2.3.2/js/jquery.js"></script>
<script type="text/javascript" src="js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#toggle-menu').click(function () {
            window.top.closeMenu();
        })
    })
</script>
</body>
</html>