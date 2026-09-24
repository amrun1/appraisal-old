<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="t_totalDokumen" value="0"/>
<c:set var="t_totalPasar" value="0"/>
<c:forEach items="${listData}" var="row" varStatus="status">
    <tr class="${status.index % 2 == 0 ? "odd" : "even"}">
        <td>
            <div class="action-group"><div class="btn-group m-b-sm navbar-right">
                <a href="#" data-id="${row.id}" title="Edit Data" class="btn btn btn-success lampiran-edit"><i class="icon-white icon-edit"></i></a>
                    <c:if test="${showButton}">
                <a href="#" data-id="${row.id}" title="Delete Data" class="btn btn btn-danger lampiran-delete"><i class="icon-white icon-remove"></i></a>
                    </c:if>
            </div></div>
        </td>
        <td>${row.jenis}</td>
        <td>${row.merk}</td>
        <td>${row.typeModel}</td>
        <td><fmt:formatNumber value="${row.nilaiDoc}" groupingUsed="true"/> </td>
        <td><fmt:formatNumber value="${row.nilaiFisik}" groupingUsed="true"/> </td>
    </tr>
    <c:set var="t_totalDokumen" value="${t_totalDokumen + row.nilaiDoc}"/>
    <c:set var="t_totalPasar" value="${t_totalPasar + row.nilaiFisik}"/>
</c:forEach>
<tr class="odd">
    <th colspan="4">&nbsp;</th>
    <th id="t_totalDokumen">${t_totalDokumen}</th>
    <th id="t_totalPasar">${t_totalPasar}</th>
</tr>
<tr class="even" style="display: none;">
    <th colspan="6"><div id="t_totalUnit">${fn:length(listData)}</div></th>
</tr>