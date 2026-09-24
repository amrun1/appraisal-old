<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="totalBangunanImb" value="0"/>
<%@ page import="com.permata.appraisal2.model.PersonLevel" %>
<c:set var="taskPosition" value="<%= PersonLevel.APPRAISAL_VALUER %>"/>
<c:set var="showButton" value="${roleActive.level == taskPosition}"></c:set>
<c:set var="idBangunanUsed" value="${idBangunanUsed}"></c:set>
<c:forEach items="${dataList}" var="row" varStatus="status">
    <tr class="${status.index % 2 == 0 ? "odd" : "even"}">
        <c:if test="${row.id != idBangunanUsed}">
            <td>
                <input type="hidden" id="id${row.id}" value="${row.id}"/>
                <input type="hidden" id="nomorImb${row.id}" value="${row.nomorImb}"/>
                <input type="hidden" id="tanggalImb${row.id}" value="<fmt:formatDate value="${row.tanggalImb}" pattern="dd-MM-yyyy"/>"/>
                <input type="hidden" id="ijinSesuaiImb${row.id}" value="${row.ijinSesuaiImb}"/>
                <input type="hidden" id="luasBangunan${row.id}" value="${row.luasBangunan}"/>
                <input type="hidden" id="jumlahLantai${row.id}" value="${row.jumlahLantai}"/>
                <button data-id="${row.id}" type="button" title="Ubah Data" class="btn btn-success doc-bangunan-edit"><i class="icon-white icon-edit"></i></button>
                <c:if test="${showButton}">
                <%--<button data-id="${row.id}" type="button" title="Delete Data" class="btn btn-danger doc-bangunan-delete"><i class="icon-white icon-remove"></i></button>--%>
                </c:if>
            </td>
            <td>${row.nomorImb}</td>
            <td><fmt:formatDate value="${row.tanggalImb}" pattern="dd-MM-yyyy"/></td>
            <td>${row.ijinSesuaiImb}</td>
            <td>${row.luasBangunan}</td>
            <c:set var="totalBangunanImb" value="${totalBangunanImb + row.luasBangunan}"/>
        </c:if>
    </tr>
</c:forEach>
<input type="hidden" id="hasil-ajax-totalBangunanImb" value="${totalBangunanImb}"/>