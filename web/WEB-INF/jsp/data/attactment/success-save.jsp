<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
<script type="text/javascript">
    <c:if test="${count == 1}">
        window.parent.refreshParent(3,'${msg}','${msgErrorValidate}');
    </c:if>
    <c:if test="${count != 1}">
        // window.parent.refreshDetail();
        window.location = "${ctxPath}/data-attactment/list?dataCommonId=${formBean.dataCommonId}&assetTypeId=${formBean.assetType}&dataDetailId=${formBean.dataDetailId}&msg=${msg}&msgErrorValidate=${msgErrorValidate}"
    </c:if>
</script>
</body>
</html>
