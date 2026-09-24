<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="totalTanahDokumen" value="0"/>
<%@ page import="com.permata.appraisal2.model.PersonLevel" %>
<c:set var="taskPosition" value="<%= PersonLevel.APPRAISAL_VALUER %>"/>
<c:set var="showButton" value="${roleActive.level == taskPosition}"></c:set>
<c:forEach items="${dataList}" var="row" varStatus="status">
    <tr class="${status.index % 2 == 0 ? "odd" : "even"}">
        <c:if test="${status.index != 0}">
            <td>
                <input type="hidden" id="id${row.id}" value="${row.id}"/>
                <input type="hidden" id="jenisDokumenId${row.id}" value="${row.jenisDokumenId}"/>
                <input type="hidden" id="jenisDokumenNama${row.id}" value="${row.jenisDokumenNama}"/>
                <input type="hidden" id="namaDokumen${row.id}" value="${row.namaDokumen}"/>
                <input type="hidden" id="tanggalSertipikat${row.id}" value="<fmt:formatDate value="${row.tanggalSertipikat}" pattern="dd-MM-yyyy"/>"/>
                <input type="hidden" id="namaJalan${row.id}" value="${row.namaJalan}"/>
                <input type="hidden" id="blockGangLantai${row.id}" value="${row.blockGangLantai}"/>
                <input type="hidden" id="nomor${row.id}" value="${row.nomor}"/>
                <input type="hidden" id="rt${row.id}" value="${row.rt}"/>
                <input type="hidden" id="rw${row.id}" value="${row.rw}"/>
                <input type="hidden" id="kelurahan${row.id}" value="${row.kelurahan}"/>
                <input type="hidden" id="kecamatan${row.id}" value="${row.kecamatan}"/>
                <input type="hidden" id="kota${row.id}" value="${row.kota}"/>
                <input type="hidden" id="propinsi${row.id}" value="${row.propinsi}"/>
                <input type="hidden" id="namaPemegangHak${row.id}" value="${row.namaPemegangHak}"/>
                <input type="hidden" id="tanggalBerakhirHak${row.id}" value="<fmt:formatDate value="${row.tanggalBerakhirHak}" pattern="dd-MM-yyyy"/>"/>
                <input type="hidden" id="suratUkur${row.id}" value="${row.suratUkur}"/>
                <input type="hidden" id="tanggalSuratUkur${row.id}" value="<fmt:formatDate value="${row.tanggalSuratUkur}" pattern="dd-MM-yyyy"/>"/>
                <input type="hidden" id="luasTanah${row.id}" value="${row.luasTanah}"/>
                <button data-id="${row.id}" type="button" title="Ubah Data" class="btn btn-success doc-tanah-edit"><i class="icon-white icon-edit"></i></button>
                <c:if test="${showButton}">
                <button data-id="${row.id}" type="button" title="Delete Data" class="btn btn-danger doc-tanah-delete"><i class="icon-white icon-remove"></i></button>
                </c:if>
            </td>
            <td>${row.jenisDokumenNama}</td>
            <td>${row.namaDokumen}</td>
            <td>${row.luasTanah}</td>
            <td><fmt:formatDate value="${row.tanggalSertipikat}" pattern="dd-MM-yyyy"/></td>
            <td><fmt:formatDate value="${row.tanggalBerakhirHak}" pattern="dd-MM-yyyy"/></td>
            <td>${row.namaPemegangHak}</td>
            <c:set var="totalTanahDokumen" value="${totalTanahDokumen + row.luasTanah}"/>
        </c:if>
    </tr>
</c:forEach>
<input type="hidden" id="hasil-ajax-totalTanahDokumen" value="${totalTanahDokumen}"/>