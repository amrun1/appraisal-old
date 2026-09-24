<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="totalTanahDokumen" value="0"/>
<%@ page import="com.permata.appraisal2.model.PersonLevel" %>
<c:set var="taskPosition" value="<%= PersonLevel.APPRAISAL_VALUER %>"/>
<c:set var="showButton" value="${roleActive.level == taskPosition}"></c:set>
<c:forEach items="${listDocOther}" var="row" varStatus="status">
    <tr class="${status.index % 2 == 0 ? "odd" : "even"}">
        <td>${status.index + 1}</td>
        <td>${row.content}</td>
        <td>${row.catatan}</td>
        <td><a href="<spring:url value='/app-approval/preview-image?id=${row.ids}'/>" target="_blank">view</a></td>
    </tr>
</c:forEach>