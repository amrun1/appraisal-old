<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.permata.appraisal2.model.PersonLevel" %>
<c:set var="taskPosition" value="<%= PersonLevel.APPRAISAL_VALUER %>"/>
<c:set var="showButton" value="${roleActive.level == taskPosition}"></c:set>
<c:set var="totalPasar" value="0"></c:set>
<c:set var="totalDokumen" value="0"></c:set>
<c:forEach items="${dataList}" var="row" varStatus="status">
    <tr class="${status.index % 2 == 0 ? "odd" : "even"}">
        <td width="100px">
            <div class="input-append">
            <button data-id="${row.id}" type="button" title="Ubah Data" class="btn btn-success bahan-baku-edit"><i class="icon-white icon-edit"></i></button>
            <c:if test="${showButton}">
                <%--<button data-id="${row.id}" type="button" title="Delete Data" class="btn btn-danger bahan-baku-delete"><i class="icon-white icon-remove"></i></button>--%>
            </c:if>
        </td>
        <td>${row.name}</td>
        <td>${row.totalDoc}</td>
        <td>${row.totalFisik}</td>
    </tr>
</c:forEach>
<%--<tr class="odd">--%>
    <%--<th colspan="7">&nbsp;</th>--%>
    <%--<th id="countBahanBaku">${countBahanBaku}</th>--%>
    <%--<th id="t_totalPasar">${totalPasar}</th>--%>
<%--</tr>--%>