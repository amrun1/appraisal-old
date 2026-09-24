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
            <button data-id="${row.id}" type="button" title="Ubah Data" class="btn btn-success kendaraan-edit"><i class="icon-white icon-edit"></i></button>
            <c:if test="${showButton}">
                <button data-id="${row.id}" type="button" title="Delete Data" class="btn btn-danger kendaraan-delete"><i class="icon-white icon-remove"></i></button>
            </c:if>
        </td>
        <td>${row.penggunaan}</td>
        <td><b>${row.merkKendaraan}</b><br>${row.modelType}</td>
        <td>${row.noPolisi}</td>
        <td>${row.noBpkb}</td>
        <td>${row.warna}</td>
        <td>${row.noRangka}</td>
        <td style="text-align: right"><fmt:formatNumber value="${row.nilaiDokumen}" groupingUsed="true" minFractionDigits="2"/></td>
        <td style="text-align: right"><fmt:formatNumber value="${row.nilaiPasar}" groupingUsed="true" minFractionDigits="2" /></td>
        <c:set var="totalPasar" value="${totalPasar + row.nilaiPasar}"/>
        <c:set var="totalDokumen" value="${totalDokumen + row.nilaiDokumen}"/>
    </tr>
</c:forEach>
<tr class="odd">
    <th colspan="7">&nbsp;</th>
    <th id="t_totalDokumen">${totalDokumen}</th>
    <th id="t_totalPasar">${totalPasar}</th>
</tr>