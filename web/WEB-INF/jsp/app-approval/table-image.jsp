<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="totalTanahDokumen" value="0"/>
<%@ page import="com.permata.appraisal2.model.PersonLevel" %>
<c:set var="taskPosition" value="<%= PersonLevel.APPRAISAL_VALUER %>"/>
<c:set var="showButton" value="${roleActive.level == taskPosition}"></c:set>
<c:forEach items="${dataList}" var="row" varStatus="status">
    <tr class="${status.index % 2 == 0 ? "odd" : "even"}">
        <td width="100px">
            <div class="input-append">
            <c:if test="${lpaId != null}">
                <%--<button data-id="${row.id}" type="button" title="Ubah Data" class="btn btn-success image-edit"><i class="icon-white icon-edit"></i></button>--%>
                <%--<c:if test="${showButton}">--%>
                    <%--<button data-id="${row.id}" type="button" title="Delete Data" class="btn btn-danger image-delete"><i class="icon-white icon-remove"></i></button>--%>
                <%--</c:if>--%>
            </c:if>
            <%--<c:if test="${fn:length(row.image) > 0}">--%>
            <a href="<spring:url value='/app-approval/preview-image?id=${row.id}'/>" target="_blank" title="Preview Image" class="btn btn-info"><i class="icon-white icon-picture"></i></a>
            <%--</c:if>--%>
            </div>
        </td>
        <td>${row.namaDocumentLpa}</td>
        <td>${row.catatan}</td>
    </tr>
</c:forEach>