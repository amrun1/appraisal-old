<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.permata.appraisal2.model.PersonLevel" %>
<%@ page import="com.permata.appraisal2.common.AppConstans" %>
<c:set var="kendaraan" value="<%=AppConstans.JenisKategoriCode.KENDARAAN%>"/>
<c:set var="lnb" value="<%=AppConstans.JenisKategoriCode.LNB%>"/>
<c:set var="surveyor" value="<%=PersonLevel.APPRAISAL_SURVEYOR%>"/>
<c:set var="isKendaraan" value="${fn:containsIgnoreCase(formBean, kendaraan)}"/>
<c:set var="isLnb" value="${fn:containsIgnoreCase(formBean.category, lnb)}"/>
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
    <link href="${ctxPath}/js/bootstrap-datepicker-1.6.4/css/bootstrap-datepicker.min.css" rel="stylesheet">
    <link href="${ctxPath}/css/main-app.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${ctxPath}/js/html5shiv.js"></script>
    <script src="${ctxPath}/js/respond.min.js"></script>
    <![endif]-->

    <style type="text/css">
        /*.modal { width:90%; left: 30%;}*/
        .modal-body {
            /*max-height: 448px;*/
            padding: 10px;
        }
        .modal.fade.in {
            top: 5%;
        }
        .modal-header {
            padding: 5px 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <f:form id="form-lpa" method="post" modelAttribute="formBean" action="save" class="form-horizontal">
        <f:hidden path="id"/>
        <f:hidden path="idMesin"/>
        <f:hidden path="idPoint"/>
        <f:hidden path="detailId"/>
        <f:hidden path="category"/>
        <f:hidden path="activa"/>

        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#menu1">Data Umum <span class="badge badge-warning hide"></span></a></li>
            <li><a data-toggle="tab" href="#menu2">Dokumen & Uraian Mesin <span class="badge badge-warning hide"></span></a></li>
            <li><a data-toggle="tab" href="#menu6">Lampiran Mesin <span class="badge badge-warning hide"></span></a></li>
            <li><a data-toggle="tab" href="#menu5">Negative List Appraisal <span class="badge badge-warning hide"></span></a></li>
        </ul>
        <div class="tab-content">
            <div id="menu1" class="tab-pane fade in active panel controls-row">
                <div class="control-group span6">
                    <label class="control-label" for="hasil">Hasil Survey</label>
                    <div class="controls">
                        <f:input path="hasil" type="text" class="form-control" placeholder="Hasil Survey"/>
                        <f:errors path="hasil" class="error has-error"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="jenisObject">Jenis Object</label>
                    <div class="controls">
                        <f:select path="jenisObject">
                            <f:option value="">-- Select --</f:option>
                            <f:options items="${listJenisObject}" itemValue="nama" itemLabel="nama"/>
                        </f:select>
                        <f:errors path="jenisObject" class="error has-error"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="lokasi">Lokasi</label>
                    <div class="controls">
                        <f:input path="lokasi"/>
                        <f:errors path="lokasi" class="error has-error"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="dipergunakan">Dipergunakan Sebagai</label>
                    <div class="controls">
                        <f:input path="dipergunakan"/>
                        <f:errors path="dipergunakan" class="error has-error"/>
                    </div>
                </div>
                <c:if test="${isLnb}">
                    <div class="control-group span6">
                        <label class="control-label" for="dihuni">Dihuni Oleh</label>
                        <div class="controls">
                            <f:input path="dihuni"/>
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
                        <f:input path="ditinjau" type="text" class="form-control" placeholder=""/>
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
                <div style="clear: both; margin-top: 10px;">&nbsp;</div>
                <c:if test="${formBean.id != null}">
                    <div class="page-header" style="clear: both">

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
                                                    <a href="${ctxPath}/survey-mesin/data-image?id=${row.id}&commonId=${row.commonId}&detailId=${formBean.detailId}"
                                                       class="btn btn-success"><i class="icon-white icon-edit"></i></a>
                                                    <a href="${ctxPath}/survey-mesin/delete-image?id=${row.id}&commonId=${row.commonId}&detailId=${formBean.detailId}"
                                                       class="btn btn-danger delete-image"><i class="icon-white icon-remove"></i></a>
                                                </c:if>
                                                <%--<c:if test="${fn:length(row.image) > 0}">--%>
                                                    <a href="${ctxPath}/survey/preview?id=${row.id}" target="_blank" class="btn btn-info"><i
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
                </c:if>
            </div>
            <div id="menu4" class="tab-pane fade controls-row panel">
            </div>
            <div id="menu5" class="tab-pane fade controls-row panel">
                <div class="control-group hide">
                    <label class="control-label" for="positif">Penilaian Positif</label>
                    <div class="controls">
                        <f:textarea path="positif" rows="4" cssClass="input-large"/>
                        <f:errors path="positif" class="error has-error"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="catatanPoint">Catatan</label>
                    <div class="controls">
                        <f:textarea path="catatanPoint" rows="3"/>
                        <f:errors path="catatanPoint" class="error has-error"/>
                    </div>
                </div>
                <div class="control-group" style="margin-bottom: 15px;">
                    <label class="control-label" for="listNegatif">List Penilaian Negatif</label>
                    <div class="controls list-view">
                        <f:checkboxes path="listNegatif" items="${listLegative}" itemValue="value" itemLabel="label"/>
                        <f:errors path="listNegatif" class="error has-error"/>
                    </div>
                </div>
                <div class="control-group" style="margin-bottom: 15px;">
                    <label class="control-label" for="listPertimbangan">List Pertimbangan Khusus</label>
                    <div class="controls list-view">
                        <f:checkboxes path="listPertimbangan" items="${listPertimbangan}" itemValue="value" itemLabel="label"/>
                        <f:errors path="listPertimbangan" class="error has-error"/>
                    </div>
                </div>
            </div>
            <div id="menu6" class="tab-pane fade controls-row panel">
                <table class="table dataTable" style="margin-top:28px;">
                    <thead>
                    <tr>
                        <th width="100px">#</th>
                        <th>Jenis Mesin</th>
                        <th>Merk Mesin</th>
                        <th>Tipe / Model</th>
                        <th>Jumlah Unit</th>
                    </tr>
                    </thead>
                    <tbody id="tbody-lampiran"></tbody>
                </table>
                <div class="control-group span6">
                    <label class="control-label" for="jumlahUnit">Jumlah Unit</label>
                    <div class="controls">
                        <f:input path="jumlahUnit" readonly="true" cssClass="input-mini" data-mask="luas-tanah"/>
                        <f:errors path="jumlahUnit" cssClass="help-block"/>
                    </div>
                </div>
            </div>
            <div id="menu2" class="tab-pane fade controls-row panel">
                <div class="control-group span6">
                    <label class="control-label" for="tempat">Tempat Operasional</label>
                    <div class="controls">
                        <f:select path="tempat">
                            <f:option value="">-- Select --</f:option>
                            <f:options items="${listTempat}" itemLabel="label" itemValue="value"/>
                        </f:select>
                        <f:errors path="tempat" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="kondisi">Kondisi Tempat Operasional</label>
                    <div class="controls">
                        <f:select path="kondisi">
                            <f:option value="">-- Select --</f:option>
                            <f:options items="${listKondisi}" itemLabel="label" itemValue="value"/>
                        </f:select>
                        <f:errors path="kondisi" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="lingkungan">Lingkungan Sekitar</label>
                    <div class="controls">
                        <f:select path="lingkungan">
                            <f:option value="">-- Select --</f:option>
                            <f:options items="${listLingkungan}" itemLabel="label" itemValue="value"/>
                        </f:select>
                        <f:errors path="lingkungan" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="teknisi">Teknisi</label>
                    <div class="controls">
                        <f:select path="teknisi">
                            <f:option value="">-- Select --</f:option>
                            <f:options items="${listTeknisi}" itemLabel="label" itemValue="value"/>
                        </f:select>
                        <f:errors path="teknisi" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="cukuCadang">Penyedia Suku Cadang</label>
                    <div class="controls">
                        <f:select path="cukuCadang">
                            <f:option value="">-- Select --</f:option>
                            <f:options items="${listCukuCadang}" itemLabel="label" itemValue="value"/>
                        </f:select>
                        <f:errors path="cukuCadang" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="catatanMesin">Catatan</label>
                    <div class="controls">
                        <f:input path="catatanMesin"/>
                        <f:errors path="catatanMesin" cssClass="help-block"/>
                    </div>
                </div>
            </div>
        </div>
    </f:form>
</div>
<div id="modal-image" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="modal-image-label" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="modal-image-label">Data Lampiran</h3>
    </div>
    <div class="modal-body" style="height: 500px;">
        <iframe id="iframe-image" src="#" width="100%" height="100%" frameborder="0"></iframe>
    </div>
</div>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<script src="${ctxPath}/js/bootstrap-datepicker-1.6.4/js/bootstrap-datepicker.min.js"></script>
<script src="${ctxPath}/js/jquery.inputmask.bundle.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
    var callbackModal;
    var refreshTableImage;
    var refreshTableMesin;
    $(document).ready(function () {
        callbackModal = function(){
            refreshTableMesin();
            refreshTableImage();
            $('#modal-image').modal('hide');
        }
        refreshTableImage = function(){
            $.ajax({
                type: "GET",
                cache: false,
                dataType: "html",
                url: '${ctxPath}/app-valuer/table-image?surveyId=&lpaId=${formBean.id}',
                success: function (html) {
                    fillTbodyImage(html);
                    $('#modal-image').modal('hide');
                }
            })
        }
        refreshTableMesin = function(){
            $.ajax({
                type: "GET",
                cache: false,
                dataType: "html",
                url: '${ctxPath}/survey-mesin/show-lampiran-valuer?idSurveyMesin=${formBean.idMesin}',
                success: function (data) {
                    fillTbody(data);
                }
            });
        }
        function fillTbodyImage(html){
            $('#tbody-image').html(html);
            unbindAndbindImage();
        }
        function fillTbody(html) {
            $('#tbody-lampiran').html(html);
            unbindAndbind();
            $('#jumlahUnit').val($('input[name=_total_unit]').val());
        }
        function unbindAndbindImage() {
        }
        function unbindAndbind() {
            $(".lampiran-edit").unbind('click');
            $(".lampiran-edit").bind('click', function () {
                var id = $(this).data('id');
                $('#iframe-image').prop('src', "${ctxPath}/survey-mesin/form-lampiran?idSurveyMesin=${formBean.idMesin}&id="+id);
                $('#modal-image').modal('show');
            });
            $(".lampiran-delete").unbind('click');
            $(".lampiran-delete").bind('click', function () {
                if (confirm('Anda yakin hapus data')) {
                    var id = $(this).data('id');
                    $.ajax({
                        type: "GET",
                        cache: false,
                        dataType: "html",
                        url: 'delete-lampiran?id=' + id + '&idSurveyMesin=${formBean.idMesin}',
                        success: function (data) {
                            fillTbody(data);
                        }
                    })
                }
            });
        }
        $('#btn-add-mesin').click(function () {
            $('#modal-image-label').text('Data Lampiran Mesin');
            $('#iframe-image').prop('src', "${ctxPath}/survey-mesin/form-lampiran?idSurveyMesin=${formBean.idMesin}");
            $('#modal-image').modal('show');
        });

        // main app thread
        <c:if test="${formBean.id != null}">
        refreshTableMesin();
        </c:if>
    });
</script>
</body>
</html>