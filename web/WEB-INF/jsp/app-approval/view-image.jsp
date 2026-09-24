<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="totalTanahDokumen" value="0"/>
<%@ page import="com.permata.appraisal2.model.PersonLevel" %>
<c:set var="taskPosition" value="<%= PersonLevel.APPRAISAL_VALUER %>"/>
<c:set var="showButton" value="${roleActive.level == taskPosition}"></c:set>
<div><h3>Foto Survey</h3></div>
<div class="control-group span6">
    <label class="control-label">Tampak Patokan / Plang Jalan</label>
    <div class="controls">
        <div class="input-append">
            <c:if test="${showButton}">
                <button type="button" title="Ubah Data" data-keterangan="foto1"
                        data-id="${foto1.id}" class="btn btn-success btn-image"><i class="icon-white icon-edit"></i></button>
                <c:if test="${foto1.id != null}">
                    <button data-id="${foto1.id}" type="button" title="Delete Data" class="btn btn-danger image-delete">
                        <i class="icon-white icon-remove"></i></button>
                </c:if>
            </c:if>
            <c:if test="${foto1.id != null}"> <%--  && fn:length(foto1.image) > 0}">--%>
                <a href="<spring:url value='/app-approval/preview-image?id=${foto1.id}'/>" target="_blank" title="Preview Image" class="btn btn-info">
                    <i class="icon-white icon-picture"></i></a>
            </c:if>
        </div>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label">Tampak Depan Lokasi</label>
    <div class="controls">
        <div class="input-append">
            <c:if test="${showButton}">
                <button type="button" title="Ubah Data" data-keterangan="foto2"
                        data-id="${foto2.id}" class="btn btn-success btn-image"><i class="icon-white icon-edit"></i></button>
                <c:if test="${foto2.id != null}">
                    <button data-id="${foto2.id}" type="button" title="Delete Data" class="btn btn-danger image-delete">
                        <i class="icon-white icon-remove"></i></button>
                </c:if>
            </c:if>
            <c:if test="${foto2.id != null}"> <%--  && fn:length(foto2.image) > 0}">--%>
                <a href="<spring:url value='/app-approval/preview-image?id=${foto2.id}'/>" target="_blank" title="Preview Image" class="btn btn-info">
                    <i class="icon-white icon-picture"></i></a>
            </c:if>
        </div>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label">Tampak No. Lokasi</label>
    <div class="controls">
        <div class="input-append">
            <c:if test="${showButton}">
                <button type="button" title="Ubah Data" data-keterangan="foto3"
                        data-id="${foto3.id}" class="btn btn-success btn-image"><i class="icon-white icon-edit"></i></button>
                <c:if test="${foto3.id != null}">
                    <button data-id="${foto3.id}" type="button" title="Delete Data" class="btn btn-danger image-delete">
                        <i class="icon-white icon-remove"></i></button>
                </c:if>
            </c:if>
            <c:if test="${foto3.id != null}"> <%--  fn:length(foto3.image) > 0}">--%>
                <a href="<spring:url value='/app-approval/preview-image?id=${foto3.id}'/>" target="_blank" title="Preview Image" class="btn btn-info">
                    <i class="icon-white icon-picture"></i></a>
            </c:if>
        </div>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label">Tampak Sekitar Lokasi</label>
    <div class="controls">
        <div class="input-append">
            <c:if test="${showButton}">
                <button type="button" title="Ubah Data" data-keterangan="foto4"
                        data-id="${foto4.id}" class="btn btn-success btn-image"><i class="icon-white icon-edit"></i></button>
                <c:if test="${foto4.id != null}">
                    <button data-id="${foto4.id}" type="button" title="Delete Data" class="btn btn-danger image-delete">
                        <i class="icon-white icon-remove"></i></button>
                </c:if>
            </c:if>
            <c:if test="${foto4.id != null}"> <%--  && fn:length(foto4.image) > 0}">--%>
                <a href="<spring:url value='/app-approval/preview-image?id=${foto4.id}'/>" target="_blank" title="Preview Image" class="btn btn-info">
                    <i class="icon-white icon-picture"></i></a>
            </c:if>
        </div>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label">Tampak Sekitar Lokasi</label>
    <div class="controls">
        <div class="input-append">
            <c:if test="${showButton}">
                <button type="button" title="Ubah Data" data-keterangan="foto5"
                        data-id="${foto5.id}" class="btn btn-success btn-image"><i class="icon-white icon-edit"></i></button>
                <c:if test="${foto5.id != null}">
                    <button data-id="${foto5.id}" type="button" title="Delete Data" class="btn btn-danger image-delete">
                        <i class="icon-white icon-remove"></i></button>
                </c:if>
            </c:if>
            <c:if test="${foto5.id != null}"> <%--  && fn:length(foto5.image) > 0}">--%>
                <a href="<spring:url value='/app-approval/preview-image?id=${foto5.id}'/>" target="_blank" title="Preview Image" class="btn btn-info">
                    <i class="icon-white icon-picture"></i></a>
            </c:if>
        </div>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label">Tampak Dalam Lokasi</label>
    <div class="controls">
        <div class="input-append">
            <c:if test="${showButton}">
                <button type="button" title="Ubah Data" data-keterangan="foto6"
                        data-id="${foto6.id}" class="btn btn-success btn-image"><i class="icon-white icon-edit"></i></button>
                <c:if test="${foto6.id != null}">
                    <button data-id="${foto6.id}" type="button" title="Delete Data" class="btn btn-danger image-delete">
                        <i class="icon-white icon-remove"></i></button>
                </c:if>
            </c:if>
            <c:if test="${foto6.id != null}"> <%--  && fn:length(foto6.image) > 0}">--%>
                <a href="<spring:url value='/app-approval/preview-image?id=${foto6.id}'/>" target="_blank" title="Preview Image" class="btn btn-info">
                    <i class="icon-white icon-picture"></i></a>
            </c:if>
        </div>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label">Tampak Dalam Lokasi</label>
    <div class="controls">
        <div class="input-append">
            <c:if test="${showButton}">
                <button type="button" title="Ubah Data" data-keterangan="foto7"
                        data-id="${foto7.id}" class="btn btn-success btn-image"><i class="icon-white icon-edit"></i></button>
                <c:if test="${foto7.id != null}">
                    <button data-id="${foto7.id}" type="button" title="Delete Data" class="btn btn-danger image-delete">
                        <i class="icon-white icon-remove"></i></button>
                </c:if>
            </c:if>
            <c:if test="${foto7.id != null}"> <%--  && fn:length(foto7.image) > 0}">--%>
                <a href="<spring:url value='/app-approval/preview-image?id=${foto7.id}'/>" target="_blank" title="Preview Image" class="btn btn-info">
                    <i class="icon-white icon-picture"></i></a>
            </c:if>
        </div>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label">Tampak Dalam Lokasi</label>
    <div class="controls">
        <div class="input-append">
            <c:if test="${showButton}">
                <button type="button" title="Ubah Data" data-keterangan="foto8"
                        data-id="${foto8.id}" class="btn btn-success btn-image"><i class="icon-white icon-edit"></i></button>
                <c:if test="${foto8.id != null}">
                    <button data-id="${foto8.id}" type="button" title="Delete Data" class="btn btn-danger image-delete">
                        <i class="icon-white icon-remove"></i></button>
                </c:if>
            </c:if>
            <c:if test="${foto8.id != null}"> <%--  && fn:length(foto8.image) > 0}">--%>
                <a href="<spring:url value='/app-approval/preview-image?id=${foto8.id}'/>" target="_blank" title="Preview Image" class="btn btn-info">
                    <i class="icon-white icon-picture"></i></a>
            </c:if>
        </div>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label">Lain - Lain</label>
    <div class="controls">
        <div class="input-append">
            <c:if test="${showButton}">
                <button type="button" title="Ubah Data" data-keterangan="foto9"
                        data-id="${foto9.id}" class="btn btn-success btn-image"><i class="icon-white icon-edit"></i></button>
                <c:if test="${foto9.id != null}">
                    <button data-id="${foto9.id}" type="button" title="Delete Data" class="btn btn-danger image-delete">
                        <i class="icon-white icon-remove"></i></button>
                </c:if>
            </c:if>
            <c:if test="${foto9.id != null}"> <%--  && fn:length(foto9.image) > 0}">--%>
                <a href="<spring:url value='/app-approval/preview-image?id=${foto9.id}'/>" target="_blank" title="Preview Image" class="btn btn-info">
                    <i class="icon-white icon-picture"></i></a>
            </c:if>
        </div>
    </div>
</div>
<div class="control-group span6">
    <label class="control-label">Lain - Lain</label>
    <div class="controls">
        <div class="input-append">
            <c:if test="${showButton}">
                <button type="button" title="Ubah Data" data-keterangan="foto10"
                    data-id="${foto10.id}" class="btn btn-success btn-image"><i class="icon-white icon-edit"></i></button>
                <c:if test="${foto10.id != null}">
                    <button data-id="${foto10.id}" type="button" title="Delete Data" class="btn btn-danger image-delete">
                        <i class="icon-white icon-remove"></i></button>
                </c:if>
            </c:if>
            <c:if test="${foto10.id != null}"> <%--  && fn:length(foto10.image) > 0}">--%>
                <a href="<spring:url value='/app-approval/preview-image?id=${foto10.id}'/>" target="_blank" title="Preview Image" class="btn btn-info">
                    <i class="icon-white icon-picture"></i></a>
            </c:if>
        </div>
    </div>
</div>
<table class="table dataTable" style="margin-top:28px;">
    <thead><tr><th width="100px">#</th><th>Kategori</th><th>Catatan</th></tr></thead>
    <tbody>
    <c:forEach items="${dataList}" var="row" varStatus="status">
        <tr class="${status.index % 2 == 0 ? "odd" : "even"}">
            <td width="100px">
                <div class="input-append">
                    <c:if test="${lpaId != null}">
                        <c:if test="${showButton}">
                            <button data-id="${row.id}" type="button" title="Ubah Data" class="btn btn-success image-edit"><i class="icon-white icon-edit"></i></button>
                            <button data-id="${row.id}" type="button" title="Delete Data" class="btn btn-danger image-delete"><i class="icon-white icon-remove"></i></button>
                        </c:if>
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
    </tbody>
</table>