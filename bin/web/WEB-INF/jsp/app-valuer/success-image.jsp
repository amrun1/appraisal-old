<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>ok</title>
</head>
<body>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script type="text/javascript">
    window.parent.refreshTableImage();
    <c:if test="${wsStatus}">
        window.parent.callbackWorksheet(${wsDto});
    </c:if>
</script>
</body>
</html>
