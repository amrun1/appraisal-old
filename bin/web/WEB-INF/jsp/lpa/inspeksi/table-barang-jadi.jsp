<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.permata.appraisal2.model.PersonLevel" %>
<c:set var="taskPosition" value="<%= PersonLevel.APPRAISAL_VALUER %>"/>
<c:set var="showButton" value="${roleActive.level == taskPosition}"></c:set>
<c:set var="totalDoc" value="0"></c:set>
<c:set var="totalFisik" value="0"></c:set>
<c:forEach items="${dataList}" var="row" varStatus="status">
    <tr class="${status.index % 2 == 0 ? "odd" : "even"}">
        <td width="100px">
            <div class="input-append">
                <c:if test="${row.id!=null}">
                <button data-id="${row.id}" type="button" title="Ubah Data" class="btn btn-success barang-jadi-edit"><i class="icon-white icon-edit"></i></button>
                <c:if test="${showButton}">
                <button data-id="${row.id}" type="button" title="Delete Data" class="btn btn-danger barang-jadi-delete"><i class="icon-white icon-remove"></i></button>
                </c:if>
                </c:if>
        </td>
        <td>${row.name}</td>
        <td>${row.totalDoc}</td>
        <td>${row.totalFisik}</td>
        <c:set var="totalDoc" value="${totalDoc + row.totalDoc}"/>
        <c:set var="totalFisik" value="${totalFisik + row.totalFisik}"/>
    </tr>
</c:forEach>
<input  type="hidden" id="hasil-ajax-totalDoc" value="${totalDoc}"/>
<input  type="hidden" id="hasil-ajax-totalFisik" value="${totalFisik}"/>
<%--<tr class="odd">--%>
    <%--<th colspan="7">&nbsp;</th>--%>
    <%--<th id="t_totalDokumen">${totalDokumen}</th>--%>
    <%--<th id="t_totalPasar">${totalPasar}</th>--%>
<%--</tr>--%>