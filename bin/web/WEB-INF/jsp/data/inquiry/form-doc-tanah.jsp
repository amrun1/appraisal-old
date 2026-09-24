<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.permata.appraisal2.model.PersonLevel" %>
<c:set var="taskPosition" value="<%= PersonLevel.APPRAISAL_VALUER %>"/>
<c:set var="showButton" value="${roleActive.level == taskPosition}"/>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<div class="container">
    <f:form modelAttribute="formBean" action="save-doc-tanah" id="form-doc-tanah" class="form-horizontal" method="post">
        <f:hidden path="id"/>
        <f:hidden path="commonId"/>
        <input type="hidden" name="jenisAktiva" value="${jenisAktiva}">

        <div class="control-group">
            <label class="control-label" for="readDocTanah">Status Document</label>
            <div class="controls">
                <f:select path="readDocTanah">
                    <f:options items="${listReadDocTAnah}" itemLabel="code" itemValue="value"/>
                </f:select>
                <f:errors path="readDocTanah" cssClass="help-block"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Jenis Dokumen</label>
            <div class="controls">
                <f:select path="jenisDokumenId">
                    <f:option value="" label="-- Select --" data-jatuh-tempo="true"/>
                    <c:forEach items="${listJenisDokumen}" var="row">
                        <f:option value="${row.id}" label="${row.name}" data-jatuh-tempo="${row.jatuhTempo == null ? false : row.jatuhTempo}"/>
                    </c:forEach>
                </f:select>
                <f:errors path="jenisDokumenId" cssClass="help-block error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Nomer Dokumen</label>
            <div class="controls">
                <f:input path="namaDokumen"/>
                <f:errors path="namaDokumen" cssClass="help-block error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Tanggal Sertipikat</label>
            <div class="controls">
                <f:input path="tanggalSertipikat" data-date-format="dd-mm-yyyy" data-provide="datepicker"  class="tutup-otomatis" placeholder="DD-MM-YYYY" data-date-end-date="0d"/>
                <f:errors path="tanggalSertipikat" cssClass="help-block error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Nama Jalan</label>
            <div class="controls">
                <f:input path="namaJalan"/>
                <f:errors path="namaJalan" cssClass="help-block error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Block/Gang/Lantai</label>
            <div class="controls">
                <f:input path="blockGangLantai"/>
                <f:errors path="blockGangLantai" cssClass="help-block error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Nomor</label>
            <div class="controls">
                <f:input path="nomor"/>
                <f:errors path="nomor" cssClass="help-block error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">RT</label>
            <div class="controls">
                <f:input path="rt" data-mask="number" class="input-mini"/>
                <f:errors path="rt" cssClass="help-block error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">RW</label>
            <div class="controls">
                <f:input path="rw" data-mask="number" class="input-mini"/>
                <f:errors path="rw" cssClass="help-block error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Desa/Kelurahan</label>
            <div class="controls">
                <f:input path="kelurahan"/>
                <f:errors path="kelurahan" cssClass="help-block error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Kecamatan</label>
            <div class="controls">
                <f:input path="kecamatan"/>
                <f:errors path="kecamatan" cssClass="help-block error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Kota/Kabupaten</label>
            <div class="controls">
                <f:input path="kota"/>
                <f:errors path="kota" cssClass="help-block error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Propinsi</label>
            <div class="controls">
                <f:input path="propinsi"/>
                <f:errors path="propinsi" cssClass="help-block error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Nama Pemegang Hak</label>
            <div class="controls">
                <f:input path="namaPemegangHak"/>
                <f:errors path="namaPemegangHak" cssClass="help-block error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Tanggal Berakhir Hak</label>
            <div class="controls">
                <f:input path="tanggalBerakhirHak" data-date-format="dd-mm-yyyy"
                       data-provide="datepicker"  class="tutup-otomatis" placeholder="DD-MM-YYYY"/>
                <f:errors path="tanggalBerakhirHak" cssClass="help-block error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Gbr Situasi/Surat Ukur</label>
            <div class="controls">
                <f:input path="suratUkur"/>
                <f:errors path="suratUkur" cssClass="help-block error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Tanggal Surat Ukur</label>
            <div class="controls">
                <f:input path="tanggalSuratUkur" data-date-format="dd-mm-yyyy"
                       data-provide="datepicker"  class="tutup-otomatis" placeholder="DD-MM-YYYY" data-date-end-date="0d"/>
                <f:errors path="tanggalSuratUkur" cssClass="help-block error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Luas Tanah Sesuai Dokumen</label>
            <div class="controls">
                <f:input path="luasTanahStr" data-mask="luas-tanah" cssClass="input-mini"/>
                <f:errors path="luasTanahStr" cssClass="help-block error"/>
            </div>
        </div>
        <%--<div class="control-group">
            <label class="control-label">Luas Tanah Sesuai Rencana Tata Kota</label>
            <div class="controls">
                <f:input path="luasTanahSesuaiTataKota" class="input-small" data-mask="luas-tanah"/>
                <f:errors path="luasTanahSesuaiTataKota" cssClass="help-block error"/>
            </div>
        </div>--%>
        <c:if test="${showButton}">
            <div class="control-group">
                <label class="control-label">&nbsp;</label>
                <div class="controls">
                    <%--<button id="btn-submit-doc-tanah" type="submit" class="btn btn-success"><i class="icon-white icon-ok"></i> Save</button>--%>
                </div>
            </div>
        </c:if>
    </f:form>
</div>

<script type="text/javascript" src="${ctxPath}/js/main-app.js">

</script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#form-doc-tanah').find('input, textarea, select').prop('disabled', true);
    });
</script>
</body>
</html>
