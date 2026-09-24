<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.permata.appraisal2.model.PersonLevel" %>
<c:set var="taskPosition" value="<%= PersonLevel.APPRAISAL_VALUER %>"/>
<c:set var="showButton" value="${roleActive.level == taskPosition}"/>
<c:set var="isEksternal" value="${fn:containsIgnoreCase(formBean.kategoriJenisAppraisal, 'eksternal')}"/>
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
            max-height: 448px;
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
    <f:form id="form-lpa" method="post" modelAttribute="formBean" action="draft-lpa" class="form-horizontal">
        <%--<f:hidden path="commonId"/>
        <f:hidden path="detailId"/>
        <f:hidden path="idLpa"/>
        <f:hidden path="id"/>
        <f:hidden path="draft"/>
        <f:hidden path="jenisAktiva"/>
        <f:hidden path="jenisAppraisal"/>
        <f:hidden path="surveyId"/>
        <f:hidden path="kategoriSla"/>
        <f:hidden path="create"/>
        <f:hidden path="kategoriJenisAppraisal"/>
        <f:hidden path="kategoriObjectPenilaian"/>
        <f:hidden path="catatanSurvey"/>--%>
        <div class="page-header-page">
            <div class="absolute-right input-append">
                <c:if test="${formBean.id != null}">
                    <div class="btn-group">
                        <button class="btn btn-info dropdown-toggle" data-toggle="dropdown"><i class="icon-white icon-file"></i>Preview Laporan <span class="caret"></span></button>
                        <ul class="dropdown-menu">
                            <c:if test="${isEksternal}">
                                <li><a href="laporan-lpa-kendaraan?PIdLpaUmum=${formBean.idLpa}&lpa=lpa-ext" target="_blank">LPA</a></li>
                            </c:if>
                            <c:if test="${!isEksternal}">
                                <li><a href="laporan-lpa-kendaraan?PIdLpaUmum=${formBean.idLpa}&lpa=lpa-int" target="_blank">LPA</a></li>
                            </c:if>
                            <li><a href="laporan-lpa-kendaraan?PIdLpaUmum=${formBean.idLpa}&lpa=lampiran" target="_blank">Lampiran Object LPA Kendaraan </a></li>
                            <li class="divider"></li>
                            <li><a href="laporan-lpa-kendaraan?PIdLpaUmum=${formBean.idLpa}&lpa=img-survey" target="_blank">Image Hasil Survey</a></li>
                            <li><a href="laporan-lpa-kendaraan?PIdLpaUmum=${formBean.idLpa}&lpa=img-peta" target="_blank">Image Peta Wilayah / Lokasi</a></li>
                            <li><a href="#myModalLain" data-toggle="modal">Image Dokumen Lain</a></li>
                        </ul>
                    </div>
                </c:if>


                    <button type="button" onclick="gotoUrl('${ctxPath}/app-approval/view?id=${formBean.commonId}');" class="btn btn-warning"><i class="icon-white icon-arrow-left"></i> Back</button>

            </div>
            <h1>Generate LPA ${formBean.kategoriJenisAppraisal} ${formBean.jenisAppraisal}</h1>
        </div>
        <div class="clearfix" style="margin-top:60px"></div>
        <spring:hasBindErrors name="formBean">
            <div class="alert alert-error">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                Terdapat ${fn:length(errors.allErrors)} Error Validasi, Silahkan Check Form Isian Anda
            </div>
        </spring:hasBindErrors>
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#menu1">Data Umum <span class="badge badge-warning hide"></span></a></li>
            <li><a data-toggle="tab" href="#menu2">Lampiran Kendaraan <span class="badge badge-warning hide"></span></a></li>
            <c:if test="${isEksternal}">
                <li><a data-toggle="tab" href="#menu10">Nilai Agunan (Eksternal) <span class="badge badge-warning hide"></span></a></li>
                <li><a data-toggle="tab" href="#menu3">Nilai Agunan (Internal) <span class="badge badge-warning hide"></span></a></li>
            </c:if>
            <c:if test="${!isEksternal}">
                <li><a data-toggle="tab" href="#menu3">Nilai Agunan <span class="badge badge-warning hide"></span></a></li>
            </c:if>
            <li><a data-toggle="tab" href="#menu4">Faktor Marketability & Catatan<span class="badge badge-warning hide"></span></a></li>
            <li><a data-toggle="tab" href="#menu5">Negative List Appraisal <span class="badge badge-warning hide"></span></a></li>
            <%--<li><a data-toggle="tab" href="#menu6">Pertimbangan Khusus <span class="badge badge-warning hide"></span></a></li>--%>
            <%--<li><a data-toggle="tab" href="#menu7">Catatan <span class="badge badge-warning hide"></span></a></li>--%>
            <c:if test="${isEksternal}">
                <li><a data-toggle="tab" href="#menu8">Opini Internal <span class="badge badge-warning hide"></span></a></li>
            </c:if>
            <li><a data-toggle="tab" href="#menu9">Data Lampiran <span class="badge badge-warning hide"></span></a></li>
        </ul>
        <div class="tab-content">
            <div id="menu1" class="tab-pane fade in active panel controls-row">
                <jsp:include page="../form-umum.jsp"/>
            </div>
            <div id="menu4" class="tab-pane fade controls-row panel">
                <div class="control-group">
                    <label class="control-label" for="faktorMarketability">Faktor Marketability</label>
                    <div class="controls custom-ui">
                        <f:radiobuttons path="faktorMarketability" items="${listFaktorMarket}" itemValue="label"
                                        itemLabel="label"/>
                        <f:errors path="faktorMarketability" cssClass="help-block error has-error"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="faktorMarketCatatan">&nbsp;</label>
                    <div class="controls">
                        <f:select path="faktorMarketCatatan" cssClass="input-xxlarge">
                            <f:option value="">-- Select --</f:option>
                            <f:options items="${listFaktorUnMarket}" itemValue="label" itemLabel="label"/>
                        </f:select>
                        <f:errors path="faktorMarketCatatan" cssClass="help-block error has-error"/>
                    </div>
                </div>
                <div class="control-group" style="margin-top: 10px;">
                    <label class="control-label" for="catatanPoint">Catatan yang Perlu Diperhatikan</label>
                    <div class="controls">
                        <f:textarea path="catatanPoint" rows="4" cssClass="input-xxlarge"/>
                        <f:errors path="catatanPoint" cssClass="help-block error has-error"/>
                    </div>
                </div>
                <div class="control-group" style="margin-top: 10px; display: none;">
                    <label class="control-label" for="positifPoint">Positive Point</label>
                    <div class="controls">
                        <f:textarea path="positifPoint" rows="4" cssClass="input-xxlarge"/>
                        <f:errors path="positifPoint" cssClass="help-block error has-error"/>
                    </div>
                </div>
                <div class="control-group" style="margin-top: 10px;">
                    <label class="control-label" for="pembanding1">Data Pembanding 1</label>
                    <div class="controls">
                        <f:input path="pembanding1" cssClass="input-xxlarge"/>
                        <f:errors path="pembanding1" cssClass="help-block error has-error"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="pembanding2">Data Pembanding 2</label>
                    <div class="controls">
                        <f:input path="pembanding2" cssClass="input-xxlarge"/>
                        <f:errors path="pembanding2" cssClass="help-block error has-error"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="pembanding3">Data Pembanding 3</label>
                    <div class="controls">
                        <f:input path="pembanding3" cssClass="input-xxlarge"/>
                        <f:errors path="pembanding3" cssClass="help-block error has-error"/>
                    </div>
                </div>
            </div>
            <div id="menu5" class="tab-pane fade controls-row panel">
                <div class="control-group">
                    <label class="control-label" for="listNegative">Negative List Appraisal</label>
                    <div class="controls">
                        <f:checkboxes path="listNegative" items="${listNegative}" itemLabel="label" itemValue="value"/>
                        <f:errors path="listNegative" cssClass="help-block error has-error"/>
                    </div>
                </div>
                <div class="control-group" style="margin-top: 15px;">
                    <label class="control-label" for="listPertimbangan">Pertimbangan Khusus</label>
                    <div class="controls">
                        <f:checkboxes path="listPertimbangan" items="${listPertimbangan}" itemLabel="label" itemValue="value"/>
                        <f:errors path="listPertimbangan" cssClass="help-block error has-error"/>
                    </div>
                </div>
            </div>
            <div id="menu6" class="tab-pane fade controls-row panel"></div>
            <div id="menu7" class="tab-pane fade controls-row panel"></div>
            <c:if test="${isEksternal}">
                <div id="menu8" class="tab-pane fade controls-row panel">
                    <div class="control-group">
                        <label class="control-label" for="opini">Opini Internal Atas Nilai Eksternal</label>
                        <div class="controls">
                            <f:select path="opini">
                                <f:option value="" label="-- Select --"/>
                                <f:options items="${listOpini}" itemValue="value" itemLabel="label"/>
                            </f:select>
                            <f:errors path="opini" cssClass="help-block error has-error"/>
                        </div>
                    </div>
                    <div class="control-group" style="margin-top:10px;">
                        <label class="control-label" for="deviasiDoc">Deviasi Nilai Dokumen</label>
                        <div class="controls">
                            <div class="input-append">
                                <f:input path="deviasiDoc" cssClass="input-mini" data-mask="devisiasi"/>
                                <button type="button" class="btn btn-info">%</button>
                            </div>
                            <f:errors path="deviasiDoc" cssClass="help-block error has-error"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="deviasiFisik">Deviasi Nilai Fisik</label>
                        <div class="controls">
                            <div class="input-append">
                                <f:input path="deviasiFisik" cssClass="input-mini" data-mask="devisiasi"/>
                                <button type="button" class="btn btn-info">%</button>
                            </div>
                            <f:errors path="deviasiFisik" cssClass="help-block error has-error"/>
                        </div>
                    </div>
                </div>
            </c:if>
            <div id="menu9" class="tab-pane fade controls-row panel">
                <div class="absolute-right">
                    <c:if test="${formBean.id != null}"><button id="btn-add-image" type="button" class="btn btn-success"><i class="icon-white icon-plus"></i> Add</button></c:if>
                </div>
                <table class="table dataTable" style="margin-top:28px;">
                    <thead><tr><th width="100px">#</th><th>Kategori</th><th>Catatan</th></tr></thead>
                    <tbody id="tbody-image"></tbody>
                </table>
            </div>
            <div id="menu2" class="tab-pane fade controls-row panel">
                <div class="page-header">
                    <c:if test="${showButton}">
                        <c:if test="${formBean.id != null}">
                            <div class="absolute-right">
                                <button id="btn-add-kendaraan" type="button" class="btn btn-info"><i class="icon-white icon-plus"></i> Add</button>
                            </div>
                        </c:if>
                    </c:if>
                    <h3 onclick="refreshTableKendaraan()">Data Kendaraan</h3>
                </div>
                <div>
                    <table class="table dataTable">
                        <thead>
                        <tr>
                            <th style="width: 80px;">#</th>
                            <th>Penggunaan</th>
                            <th>Merk/Model</th>
                            <th>No Polisi</th>
                            <th>No BPKB</th>
                            <th>Warna</th>
                            <th>No Rangka</th>
                            <th>Nilai Dokumen</th>
                            <th>Nilai Pasar</th>
                        </tr>
                        </thead>
                        <tbody id="tbody-doc-kendaraan">
                        <c:if test="${formBean.id == null}">
                            <tr class="odd">
                                <td colspan="10" align="center"><i><b>Untuk Menambah Lampiran Data Kendaraan, lakukan Simpan dahulu</b></i></td>
                            </tr>
                            <c:forEach items="${formBean.kendaraan}" var="row" varStatus="status">
                                <tr>
                                    <td>${status.index + 1}</td>
                                    <td>${row.penggunaan}</td>
                                    <td><b>${row.merkKendaraan}</b><br>${row.modelType}</td>
                                    <td>${row.noPolisi}</td>
                                    <td>${row.noBpkb}</td>
                                    <td>${row.warna}</td>
                                    <td>${row.noRangka}</td>
                                    <td>${row.nilaiPasar}</td>
                                    <td>${row.nilaiDokumen}</td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
            <div id="menu3" class="tab-pane fade controls-row panel">
                <div class="control-group">
                    <div class="span4">Nilai Dokumen Pasar</div>
                    <div class="span4">
                        <f:input path="totalDokumen" readonly="true" data-mask="luas-tanah"/>
                    </div>
                    <div class="custom-ui span4">
                        <f:radiobutton path="beriNilaiDokumen" label="Diberi Nilai" value="1"/>
                        <f:radiobutton path="beriNilaiDokumen" label="Tidak Diberi Nilai" value="0"/>
                    </div>
                </div>
                <div class="control-group">
                    <div class="span4">Nilai Fisik Pasar</div>
                    <div class="span4">
                        <f:input path="totalFisik" readonly="true" data-mask="luas-tanah"/>
                    </div>
                    <div class="custom-ui span4">
                        <f:radiobutton path="beriNilaiFisik" label="Diberi Nilai" value="1"/>
                        <f:radiobutton path="beriNilaiFisik" label="Tidak Diberi Nilai" value="0"/>
                    </div>
                </div>
                <div class="control-group" style="margin-bottom: 4px;">
                    <div class="span4">Nilai Likuidasi</div>
                    <div class="span8">
                        <div class="input-append">
                            <f:input path="persenLikuidasi" cssClass="input-mini" data-mask="persen"/>
                            <button type="button" class="btn btn-info">%</button>
                        </div>
                    </div>
                </div>
                <div class="control-group" style="margin-bottom: 4px;">
                    <div class="span4">Nilai Dokumen Likuidasi</div>
                    <div class="span8">
                        <f:input path="likuidasiDokumen" readonly="true" data-mask="luas-tanah"/>
                    </div>
                </div>
                <div class="control-group" style="margin-bottom: 4px;">
                    <div class="span4">Nilai Fisik Likuidasi</div>
                    <div class="span8">
                        <f:input path="likuidasiFisik" readonly="true" data-mask="luas-tanah"/>
                    </div>
                </div>
            </div>
            <div id="menu10" class="tab-pane fade controls-row panel">
                <div class="control-group">
                    <div class="span4">Nilai Dokumen Pasar</div>
                    <div class="span4">
                        <f:input path="totalDokumenEks" data-mask="luas-tanah"/>
                    </div>
                    <div class="custom-ui span4">
                        <f:radiobutton path="beriNilaiDokumenEks" label="Diberi Nilai" value="1"/>
                        <f:radiobutton path="beriNilaiDokumenEks" label="Tidak Diberi Nilai" value="0"/>
                    </div>
                </div>
                <div class="control-group">
                    <div class="span4">Nilai Fisik Pasar</div>
                    <div class="span4">
                        <f:input path="totalFisikEks" data-mask="luas-tanah"/>
                    </div>
                    <div class="custom-ui span4">
                        <f:radiobutton path="beriNilaiFisikEks" label="Diberi Nilai" value="1"/>
                        <f:radiobutton path="beriNilaiFisikEks" label="Tidak Diberi Nilai" value="0"/>
                    </div>
                </div>
                <div class="control-group" style="margin-bottom: 4px;">
                    <div class="span4">Nilai Likuidasi</div>
                    <div class="span8">
                        <div class="input-append">
                            <f:input path="persenLikuidasiEks" cssClass="input-mini" data-mask="persen"/>
                            <button type="button" class="btn btn-info">%</button>
                        </div>
                    </div>
                </div>
                <div class="control-group" style="margin-bottom: 4px;">
                    <div class="span4">Nilai Dokumen Likuidasi</div>
                    <div class="span8">
                        <f:input path="likuidasiDokumenEks" readonly="true" data-mask="luas-tanah"/>
                    </div>
                </div>
                <div class="control-group" style="margin-bottom: 4px;">
                    <div class="span4">Nilai Fisik Likuidasi</div>
                    <div class="span8">
                        <f:input path="likuidasiFisikEks" readonly="true" data-mask="luas-tanah"/>
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
    <div class="modal-body" style="height: 650px;">
        <iframe id="iframe-image" src="#" width="100%" height="100%" frameborder="0"></iframe>
    </div>
</div>
<div id="myModalLain" id="modal-lain" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
        <h3 id="myModalLabel">Dokumen Lain - lain</h3>
    </div>
    <div class="modal-body">
        <table class="table table-bordered dataTable">
            <thead>
            <tr>
                <th>No</th>
                <th>Konten</th>
                <th>Keterangan</th>
                <th>View</th>
            </tr>
            </thead>
            <tbody id="tbody-image-other">
            <tr class="odd"><td colspan="3" align="center"><i>Tidak Ada data</i></td></tr>
            </tbody>
        </table>
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
    var callbackWorksheet;
    var refreshTableImageOther;
    $(document).ready(function () {

        callbackWorksheet = function(data){

           $('.nav-tabs a[href="#menu3"]').tab('show');

//            $('#hitungManual11').trigger('change');
            $('#totalDokumen').val(data.nilaiDokPasar);
            $('#totalDokumen').removeProp("readonly");

            $('#totalFisik').val(data.nilaiFisikPasar);
            $('#totalFisik').removeProp("readonly");

            $('#likuidasiDokumen').val(data.nilaiDokLikuidasi);
            $('#likuidasiDokumen').removeProp("readonly");

            $('#likuidasiFisik').val(data.nilaiFisikLikuidasi);
            $('#likuidasiFisik').removeProp("readonly");

//            $('#nilaiFisik1').val(data.fisikTanah);
//            $('#nilaiDokumen2').val(data.docBangunan);
//            $('#nilaiFisik2').val(data.fisikBangunan);
//            $('#totalSesuaiDokumen').val(data.totalDoc);
//            $('#totalSesuaiFisik').val(data.totalFisik);
//            $('#likuidasiDokumen1').val(data.likuidasiDocTanah);
//            $('#likuidasiFisik1').val(data.likuidasiFisikTanah);
//            $('#likuidasiDokumen2').val(data.likuidasiDocBangunan);
//            $('#likuidasiFisik2').val(data.likuidasiFisikBangunan);
//            $('#totalLikuidasiDokumen').val(data.totalLikuidasiDoc);
//            $('#totalLikuidasiFisik').val(data.totalLikuidasiFisik);
        }
        callbackModal = function(){
            refreshTableKendaraan();
            refreshTableImage();
            $('#modal-image').modal('hide');
        }
        refreshTableImage = function(){
            $.ajax({
                type: "GET",
                cache: false,
                dataType: "html",
                url: '${ctxPath}/app-valuer/table-image?surveyId=${formBean.surveyId}&lpaId=${formBean.idLpa}',
                success: function (html) {
                    fillTbodyImage(html);
                    $('#modal-image').modal('hide');
                    refreshTableImageOther();
                }
            })
        }
        refreshTableImageOther = function(){
            $.ajax({
                type: "GET",
                cache: false,
                dataType: "html",
                url: '${ctxPath}/app-valuer/table-image-other?lpaId=${formBean.idLpa}',
                success: function (html) {
                    fillTbodyImageOther(html);
                    $('#myModalLain').modal('hide');
                }
            })
        }
        function fillTbodyImageOther(html){
            $('#tbody-image-other').html(html);
        }
        var refreshTableKendaraan = function(){
            $.ajax({
                type: "GET",
                cache: false,
                dataType: "html",
                url: 'table-kendaraan?idLpa=${formBean.id}',
                success: function (data) {
                    fillTbody(data);

                    $('#t_totalDokumen, #t_totalPasar').inputmask("numeric", {
                        clearMaskOnLostFocus : true,
                        digits: 2,
                        placeholder : '0.00',
                        groupSeparator: ',',
                        autoGroup : true,
                        allowMinus: false,
                        rightAlign: true
                    });

                    $('#totalFisik').val(parseToFloat($('#t_totalPasar').inputmask('unmaskedvalue')));
                    $('#totalDokumen').val(parseToFloat($('#t_totalDokumen').inputmask('unmaskedvalue')));
                    $('#modal-image').modal('hide');
                    $('#totalDokumen').trigger('change');
                }
            });
        }
        function fillTbodyImage(html){
            $('#tbody-image').html(html);
            unbindAndbindImage();
        }
        function fillTbody(html) {
            $('#tbody-doc-kendaraan').html(html);
            unbindAndbind();
        }
        function unbindAndbindImage() {
            $(".image-edit").unbind('click');
            $(".image-edit").bind('click', function () {
                var id = $(this).data('id');
                $('#iframe-image').prop('src', "${ctxPath}/app-valuer/form-image?id="+id+"&lpaId=${formBean.idLpa}&activaId=${formBean.jenisAktiva}");
                $('#modal-image').modal('show');
                refreshTableImageOther();
            });
            $(".image-delete").unbind('click');
            $(".image-delete").bind('click', function () {
                if (confirm('Anda yakin hapus data')) {
                    var id = $(this).data('id');
                    $.ajax({
                        type: "GET",
                        cache: false,
                        dataType: "html",
                        url: '${ctxPath}/app-valuer/delete-image?id=' + id + '&surveyId=${formBean.surveyId}&lpaId=${formBean.id}',
                        success: function (data) {
                            fillTbodyImage(data);
                            refreshTableImageOther();
                        }
                    })
                }
            });
        }
        function unbindAndbind() {
            $(".kendaraan-edit").unbind('click');
            $(".kendaraan-edit").bind('click', function () {
                var id = $(this).data('id');
                $('#iframe-image').prop('src', "${ctxPath}/lpa-kendaraan/form-kendaraan?lpaId=${formBean.idLpa}&id="+id);
                $('#modal-image').modal('show');
            });
            $(".kendaraan-delete").unbind('click');
            $(".kendaraan-delete").bind('click', function () {
                if (confirm('Anda yakin hapus data')) {
                    var id = $(this).data('id');
                    $.ajax({
                        type: "GET",
                        cache: false,
                        dataType: "html",
                        url: 'delete-kendaraan?id=' + id + '&idLpa=${formBean.idLpa}',
                        success: function (data) {
                            fillTbody(data);
                        }
                    })
                }
            });
        }
        function calc(){
            var totalDoc = parseToFloat($('#totalDokumen').inputmask('unmaskedvalue'));
            var totalFisik = parseToFloat($('#totalFisik').inputmask('unmaskedvalue'));
            var persen = parseToFloat($('#persenLikuidasi').inputmask('unmaskedvalue'));
            var beriNilaiDoc = $('input[name=beriNilaiDokumen]:checked').val();
            var beriNilaiFisik = $('input[name=beriNilaiFisik]:checked').val();
            var likuidasiDoc = totalDoc * persen / 100;
            var likuidasiFisik = totalFisik * persen / 100;
            if(beriNilaiDoc != "1"){
                likuidasiDoc = 0;
            }if(beriNilaiFisik != "1"){
                likuidasiFisik = 0;
            }

            $('#likuidasiDokumen').val(likuidasiDoc);
            $('#likuidasiFisik').val(likuidasiFisik);
        }
        function calcEks(){
            var totalDoc = parseToFloat($('#totalDokumenEks').inputmask('unmaskedvalue'));
            var totalFisik = parseToFloat($('#totalFisikEks').inputmask('unmaskedvalue'));
            var persen = parseToFloat($('#persenLikuidasiEks').inputmask('unmaskedvalue'));
            var beriNilaiDoc = $('input[name=beriNilaiDokumenEks]:checked').val();
            var beriNilaiFisik = $('input[name=beriNilaiFisikEks]:checked').val();
            var likuidasiDoc = totalDoc * persen / 100;
            var likuidasiFisik = totalFisik * persen / 100;
            if(beriNilaiDoc != "1"){
                likuidasiDoc = 0;
            }if(beriNilaiFisik != "1"){
                likuidasiFisik = 0;
            }

            $('#likuidasiDokumenEks').val(likuidasiDoc);
            $('#likuidasiFisikEks').val(likuidasiFisik);
        }
        $('#btn-add-kendaraan').click(function () {
            $('#modal-image-label').text('Form Kendaraan');
            $('#iframe-image').prop('src', "${ctxPath}/lpa-kendaraan/form-kendaraan?lpaId=${formBean.id}");
            $('#modal-image').modal('show');
        });
        $('#btn-add-image').click(function () {
            $('#modal-image-label').text('Form Lampiran');
            $('#iframe-image').prop('src', "${ctxPath}/app-valuer/form-image?lpaId=${formBean.idLpa}&activaId=${formBean.jenisAktiva}");
            $('#modal-image').modal('show');
        });
        $('input[name=faktorMarketability]').change(function () {
            if ($('input[name=faktorMarketability]:checked').val() == 'Tidak Marketable') {
                $('select[name=faktorMarketCatatan]').prop('disabled', false);
            } else {
                $('select[name=faktorMarketCatatan]').prop('disabled', true);
                $('select[name=faktorMarketCatatan]').prop('selectedIndex', 0);
            }
        })
        $('#persenLikuidasi, input[name=beriNilaiDokumen], input[name=beriNilaiFisik], #totalDokumen, #totalFisik').change(function () {
            calc();
        });
        <c:if test="${isEksternal}">
            $('#persenLikuidasiEks, input[name=beriNilaiDokumenEks], input[name=beriNilaiFisikEks], #totalDokumenEks, #totalFisikEks').change(function () {
                calcEks();
            });
        </c:if>
        $('#btn-save-all').click(function () {
            $('#draft').val(false);
            $('#form-lpa').attr('action', 'save-lpa');
            $('select').prop('disabled', false);
        })
        // main app thread
        <c:if test="${formBean.id != null}">
            refreshTableKendaraan();
            refreshTableImageOther();
        </c:if>
        refreshTableImage();
        $('input[name=faktorMarketability]').trigger('change');

        <spring:hasBindErrors name="formBean">
        function showErrorLpa(field){
            var menuId = $('[name="'+field+'"]').parents('.tab-pane').attr('id');
            var _url = $('[href="#' + menuId + '"]');
            _url.addClass('error');
            var text_ = _url.find('span').text();
            if (text_ == "") text_ = 0;
            _url.find('span').text(parseInt(text_) + 1);
            _url.find('span').show();
            $($('[name="'+field+'"]').parents('div.control-group')).addClass('error');
        }
        <c:forEach var="error" items="${errors.allErrors}">
        showErrorLpa("${error.field}");
        </c:forEach></spring:hasBindErrors>
    });
</script>
</body>
</html>