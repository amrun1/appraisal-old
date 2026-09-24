<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="_total" value="0"/>
<c:forEach items="${listData}" var="row" varStatus="status">
    <tr class="${status.index % 2 == 0 ? "odd" : "even"}">
        <td>
            <div class="action-group"><div class="btn-group m-b-sm navbar-right">
                <a href="#" data-id="${row.id}" title="Edit Data" class="btn btn btn-success lampiran-edit"><i class="icon-white icon-edit"></i></a>
                <%--<c:if test="${showButton && !inquery}">--%>
                <a href="#" data-id="${row.id}" title="Delete Data" class="btn btn btn-danger lampiran-delete"><i class="icon-white icon-remove"></i></a>
                <%--</c:if>--%>
            </div></div>
        </td>
        <td>${row.jenis}</td>
        <td>${row.merk}</td>
        <td>${row.typeModel}</td>
        <td><fmt:formatNumber value="${row.jumlah}" groupingUsed="true"/> </td>
    </tr>
    <c:set var="_total" value="${_total + row.jumlah}"/>
</c:forEach>
<input type="hidden" name="_total_unit" value="${_total}">