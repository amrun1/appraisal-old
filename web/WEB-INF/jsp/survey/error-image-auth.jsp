<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
    <title>Title</title>
</head>
<body>
<script type="text/javascript">
    top.frames['mainFrame'].location.href = "${ctxPath}/app-surveyor/list-error";
</script>
</body>
</html>
