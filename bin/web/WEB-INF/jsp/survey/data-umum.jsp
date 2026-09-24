<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.permata.appraisal2.model.PersonLevel" %>
<%@ page import="com.permata.appraisal2.common.AppConstans" %>
<c:set var="kendaraan" value="<%=AppConstans.JenisKategoriCode.KENDARAAN%>"/>
<c:set var="vessel" value="<%=AppConstans.JenisKategoriCode.VESEL%>"/>
<c:set var="lnb" value="<%=AppConstans.JenisKategoriCode.LNB%>"/>
<c:set var="surveyor" value="<%=PersonLevel.APPRAISAL_SURVEYOR%>"/>
<c:set var="isKendaraan" value="${fn:containsIgnoreCase(formBean, kendaraan)}"/>
<c:set var="isLnb" value="${fn:containsIgnoreCase(formBean.category, lnb)}"/>
<c:set var="isVessel" value="${fn:containsIgnoreCase(formBean.category, vessel)}"/>
<c:set var="showButton" value="false"/>
<c:if test="${roleActive.level == surveyor}">
    <c:set var="showButton" value="true"/>
</c:if>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
    <title>Appraisal</title>
    <meta http-equiv="Expires" content="-1"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${ctxPath}/js/bootstrap_2.3.2/css/bootstrap.css" rel="stylesheet">
    <link href="${ctxPath}/js/bootstrap_2.3.2/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="${ctxPath}/js/bootstrap_2.3.2/css/docs.css" rel="stylesheet">
    <link href="${ctxPath}/js/bootstrap_2.3.2/js/google-code-prettify/prettify.css" rel="stylesheet">
    <link href="${ctxPath}/js/datatables.min.css" rel="stylesheet">
    <link href="${ctxPath}/js/bootstrap-datepicker-1.6.4/css/bootstrap-datepicker.min.css" rel="stylesheet">
    <link href="${ctxPath}/css/main-app.css" rel="stylesheet">

    <!--[if lt IE 9]>
    <script src="${ctxPath}/js/html5shiv.js"></script>
    <script src="${ctxPath}/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <div class="page-header hide">
        <h3>Survey Data Umum</h3>

    </div>


    <c:if test="${not empty msg}">
        <div class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${msg}</strong>
        </div>
    </c:if>

    <f:form method="post" modelAttribute="formBean" action="save-umum?${_csrf.parameterName}=${_csrf.token}"
            class="form-horizontal"
            enctype="multipart/form-data">
        <f:hidden path="id"/>
        <f:hidden path="detailId"/>
        <f:hidden path="category"/>
        <f:hidden path="activa"/>
        <div class="control-group span6">
            <label class="control-label" for="hasil">Hasil Survey</label>
            <div class="controls">
                <f:input path="hasil" type="text" class="form-control" placeholder="Hasil Survey"/>
                <f:errors path="hasil" class="error has-error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="jenisPenilaian">Jenis Object</label>
            <div class="controls">
                <f:hidden path="jenisObject"/>
                <f:select path="jenisPenilaian">
                    <f:option value="">-- Select --</f:option>
                    <f:options items="${listJenisObject}" itemValue="id" itemLabel="nama"/>
                </f:select>
                <f:errors path="jenisPenilaian" class="error has-error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="lokasi">Lokasi</label>
            <div class="controls">
                <f:select path="lokasi">
                    <f:option value="">-- Select --</f:option>
                    <f:options items="${listPosisiLokasi}" itemValue="label" itemLabel="label"/>
                </f:select>
                <f:errors path="lokasi" class="error has-error"/>
            </div>
        </div>

        <c:if test="${!isVessel}">
        <div class="control-group span6">
            <label class="control-label" for="dipergunakan">Dipergunakan Sebagai</label>
            <div class="controls">
                <f:select path="dipergunakan">
                    <f:option value="">-- Select --</f:option>
                    <f:options items="${listDigunakanSebagai}" itemValue="label" itemLabel="label"/>
                </f:select>
                <f:errors path="dipergunakan" class="error has-error"/>
            </div>
        </div>
        </c:if>


        <c:if test="${isLnb}">
            <div class="control-group span6">
                <label class="control-label" for="dihuni">Dihuni Oleh</label>
                <div class="controls">
                    <f:select path="dihuni">
                        <f:option value="">-- Select --</f:option>
                        <f:options items="${listDihuniOleh}" itemValue="label" itemLabel="label"/>
                    </f:select>
                    <f:errors path="dihuni" class="error has-error"/>
                </div>
            </div>
        </c:if>
        <div class="control-group span6">
            <label class="control-label" for="ditemui">Ditemui Oleh</label>
            <div class="controls">
                <f:input path="ditemui" type="text" class="form-control" placeholder=""/>
                <f:errors path="ditemui" class="error has-error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="ditinjau">Ditinjau Oleh</label>
            <div class="controls">
                <f:input path="ditinjau" type="text" class="form-control" placeholder="" readonly="true"/>
                <f:errors path="ditinjau" class="error has-error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="tanggal">Tanggal Survey</label>
            <div class="controls">
                <f:input path="tanggal" data-date-format="dd-mm-yyyy"
                         data-provide="datepicker"  class="tutup-otomatis" placeholder="DD-MM-YYYY" data-date-end-date="0d"/>
                <f:errors path="tanggal" class="error has-error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="catatanSurvey">Catatan Hasil Survey</label>
            <div class="controls">
                <f:input path="catatanSurvey" type="text" class="form-control" placeholder=""/>
                <f:errors path="catatanSurvey" class="error has-error"/>
            </div>
        </div>
        <div class="input-group">
            <div class="input-group-addon">
                <span class="icon icon-"></span>
            </div>
        </div>
        <c:if test="${showButton && !inquery}">
            <div class="control-group span12">
                <label class="control-label">&nbsp;</label>
                <div class="controls">
                    <button type="submit" class="btn btn-success"><i class="icon-white icon-ok-circle"></i> Save
                    </button>
                    <button id="btn-reset" type="reset" class="btn btn-warning"><i class="icon-white icon-refresh"></i>
                        Reset
                    </button>
                </div>
            </div>
        </c:if>
    </f:form>
    <div class="page-header" style="clear: both">
        <c:if test="${showButton && !inquery && formBean.id != null}">
            <div class="absolute-right">
                <a href="data-image?commonId=${formBean.id}&detailId=${formBean.detailId}" class="btn btn-success"><i
                        class="icon-white icon-plus"></i>
                    Add</a>
            </div>
        </c:if>
        <h3>Image Lampiran</h3>
    </div>
    <div>
        <table class="table dataTable">
            <thead>
            <tr>
                <th width="100px">Action</th>
                <th>Kategori</th>
                <th>Catatan</th>
            </tr>
            </thead>
            <c:forEach items="${listImage}" var="row">
                <tr class="${status.index % 2 == 0 ? "odd" : "even"}">
                    <td>
                        <div class="action-group">
                            <div class="btn-group m-b-sm navbar-right">
                                <c:if test="${showButton && !inquery}">
                                    <a href="data-image?id=${row.id}&commonId=${row.commonId}&detailId=${formBean.detailId}"
                                       class="btn btn-success"><i class="icon-white icon-edit"></i></a>
                                    <a href="delete-image?id=${row.id}&commonId=${row.commonId}&detailId=${formBean.detailId}"
                                       class="btn btn-danger delete-image"><i class="icon-white icon-remove"></i></a>
                                </c:if>
                                <%--<c:if test="${fn:length(row.image) > 0}">--%>
                                    <a href="preview?id=${row.id}" target="_blank" class="btn btn-info"><i
                                            class="icon-white icon-picture"></i></a>
                                <%--</c:if>--%>
                            </div>
                        </div>
                    </td>
                    <td>${row.namaDocumentLpa}</td>
                    <td>${row.catatan}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<script src="${ctxPath}/js/bootstrap-datepicker-1.6.4/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
    // $(document).off('.datepicker.data-api');
    $(document).ready(function () {
        $('#jenisPenilaian').change(function(){
            $('#jenisObject').val($('#jenisPenilaian option:selected').text());
        })
        $('.delete-image').click(function () {
            return confirm('Anda yakin hapus data?');
        })

        // main thread
        $('#jenisPenilaian').trigger('change');
    });
</script>
</body>
</html>