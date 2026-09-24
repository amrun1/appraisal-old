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
        .aktif-link {
            display: none;
        }
    </style>
</head>
<body>
<div class="container">
    <f:form id="form-lpa" method="post" modelAttribute="formBean" action="draft-lpa" class="form-horizontal">
        <div class="page-header-page">
            <div class="absolute-right input-append">
                <c:if test="${formBean.id != null}">
                    <div class="btn-group">
                        <button class="btn btn-info dropdown-toggle" data-toggle="dropdown"><i class="icon-white icon-file"></i>Preview Laporan <span class="caret"></span></button>
                        <ul class="dropdown-menu">
                            <c:if test="${isEksternal}">
                                <li><a href="laporan-lpa-mesin?PIdLpaUmum=${formBean.idLpa}&lpa=lpa-ext" target="_blank">LPA</a></li>
                            </c:if>
                            <c:if test="${!isEksternal}">
                                <li><a href="laporan-lpa-mesin?PIdLpaUmum=${formBean.idLpa}&lpa=lpa-int" target="_blank">LPA</a></li>
                            </c:if>
                            <li id="lampiran-dok" class="aktif-link"><a href="laporan-lpa-mesin?PIdLpaUmum=${formBean.idLpa}&lpa=lampiran" target="_blank">Lampiran Object LPA Mesin </a></li>
                            <li class="divider"></li>
                            <li><a href="laporan-lpa-mesin?PIdLpaUmum=${formBean.idLpa}&lpa=img-survey" target="_blank">Image Hasil Survey</a></li>
                            <li><a href="laporan-lpa-mesin?PIdLpaUmum=${formBean.idLpa}&lpa=img-peta" target="_blank">Image Peta Wilayah / Lokasi</a></li>
                            <li><a href="#myModalLain" data-toggle="modal">Image Dokumen Lain</a></li>
                        </ul>
                    </div>
                </c:if>
                <c:if test="${showButton}">
                    <button type="submit" id="btn-save-all" class="btn btn-success"><i class="icon-white icon-ok"></i>Save</button>
                    <c:if test="${disableDraft}">
                        <button type="submit" id="btn-save-draft" class="btn btn-info"><i class="icon-white icon-camera"></i> Draft</button>
                    </c:if>
                    <button type="reset" class="btn btn-primary"><i class="icon-white icon-refresh"></i> Reset</button>
                    <button type="button" onclick="gotoUrl('${ctxPath}/app-valuer/view?id=${formBean.commonId}');" class="btn btn-warning"><i class="icon-white icon-arrow-left"></i> Back</button>
                    <c:if test="${formBean.id != null}">
                        <a href="delete-lpa?id=${formBean.id}&commonId=${formBean.commonId}" id="btn-delete-lpa" class="btn btn-danger"><i class="icon-white icon-remove"></i> Delete</a>
                    </c:if>
                </c:if>
                <c:if test="${!showButton}">
                    <button type="button" onclick="gotoUrl('${ctxPath}/app-approval/view?id=${formBean.commonId}');" class="btn btn-warning"><i class="icon-white icon-arrow-left"></i> Back</button>
                </c:if>
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
            <li><a data-toggle="tab" href="#menu2">Dokumen & Uraian Mesin <span class="badge badge-warning hide"></span></a></li>
            <li><a data-toggle="tab" href="#menu6">Lampiran Mesin <span class="badge badge-warning hide"></span></a></li>
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
                <li><a data-toggle="tab" id="xx" href="#menu8">Opini Internal <span class="badge badge-warning hide"></span></a></li>
            </c:if>
            <li><a data-toggle="tab" href="#menu9">Data Lampiran <span class="badge badge-warning hide"></span></a></li>
        </ul>
        <div class="tab-content">
            <div id="menu1" class="tab-pane fade in active panel controls-row">
                <jsp:include page="../form-umum.jsp"/>
            </div>
            <div id="menu4" class="tab-pane fade controls-row panel">
                <jsp:include page="../form-market.jsp"/>
            </div>
            <div id="menu5" class="tab-pane fade controls-row panel">
                <jsp:include page="../form-pertimbangan-dan-negative.jsp"/>
            </div>
            <div id="menu6" class="tab-pane fade controls-row panel">
                <div class="absolute-right">
                    <%--<button type="button" onclick="javascript:refreshTableMesin()">Refresh</button>--%>
                    <c:if test="${formBean.id != null}"><button id="btn-add-mesin" type="button" class="btn btn-success"><i class="icon-white icon-plus"></i> Add</button></c:if>
                </div>
                <table class="table dataTable" style="margin-top:28px;">
                    <thead>
                        <tr>
                            <th width="100px">#</th>
                            <th>Jenis Mesin</th>
                            <th>Merk Mesin</th>
                            <th>Tipe / Model</th>
                            <th>Nilai Dokumen Pasar</th>
                            <th>Nilai Fisik Pasar</th>
                        </tr>
                    </thead>
                    <tbody id="tbody-lampiran">
                        <c:if test="${formBean.id == null}">
                            <tr>
                                <td colspan="5"><i>Data akan terlihat setelah melakukan save draft</i></td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
                <div class="control-group span6">
                    <label class="control-label" for="jumlahUnit">Jumlah Unit</label>
                    <div class="controls">
                        <f:input path="jumlahUnit" readonly="true" cssClass="input-mini"/>
                        <f:errors path="jumlahUnit" cssClass="help-block"/>
                    </div>
                </div>
            </div>
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
                <div id="menu10" class="tab-pane fade controls-row panel custom-ui">
                    <div class="control-group span12">
                        <label class="control-label" for="manualEks">&nbsp;</label>
                        <div class="controls">
                            <f:checkbox path="manualEks" label="Hitung Manual"/>
                            <f:errors path="manualEks" cssClass="help-block"/>
                        </div>
                    </div>
                    <div class="control-group span6">
                        <label class="control-label" for="nilaiDocPasarEks">Nilai Dokumen Pasar</label>
                        <div class="controls">
                            <f:input path="nilaiDocPasarEks" data-mask="luas-tanah"/>
                            <f:errors path="nilaiDocPasarEks" cssClass="help-block"/>
                        </div>
                    </div>
                    <div class="control-group span6">
                        <label class="control-label" for="beriNilaiDocEks">&nbsp;</label>
                        <div class="controls">
                            <f:radiobutton path="beriNilaiDocEks" value="1" label="Beri Nilai"/>
                            <f:radiobutton path="beriNilaiDocEks" value="0" label="Tidak Beri Nilai"/>
                            <f:errors path="beriNilaiDocEks" cssClass="help-block"/>
                        </div>
                    </div>
                    <div class="control-group span6">
                        <label class="control-label" for="nilaiFisikPasarEks">Nilai Fisik Pasar</label>
                        <div class="controls">
                            <f:input path="nilaiFisikPasarEks" data-mask="luas-tanah"/>
                            <f:errors path="nilaiFisikPasarEks" cssClass="help-block"/>
                        </div>
                    </div>
                    <div class="control-group span6">
                        <label class="control-label" for="beriNilaiFisikEks">&nbsp;</label>
                        <div class="controls">
                            <f:radiobutton path="beriNilaiFisikEks" value="1" label="Beri Nilai"/>
                            <f:radiobutton path="beriNilaiFisikEks" value="0" label="Tidak Beri Nilai"/>
                            <f:errors path="beriNilaiFisikEks" cssClass="help-block"/>
                        </div>
                    </div>
                    <div class="control-group span6">
                        <label class="control-label" for="likuidasiEks">Persen Likuidasi</label>
                        <div class="controls">
                            <div class="input-append">
                                <f:input path="likuidasiEks" data-mask="persen" cssClass="input-mini"/>
                                <span class="btn btn-primary"> % </span>
                            </div>
                            <f:errors path="likuidasiEks" cssClass="help-block"/>
                        </div>
                    </div>
                    <div class="control-group span12">
                        <label class="control-label" for="nilaiDocLikuidasiEks">Nilai Dokumen Likuidasi</label>
                        <div class="controls">
                            <f:input path="nilaiDocLikuidasiEks" data-mask="luas-tanah"/>
                            <f:errors path="nilaiDocLikuidasiEks" cssClass="help-block"/>
                        </div>
                    </div>
                    <div class="control-group span12">
                        <label class="control-label" for="nilaiFisikLikuidasiEks">Nilai Fisik Likuidasi</label>
                        <div class="controls">
                            <f:input path="nilaiFisikLikuidasiEks" data-mask="luas-tanah"/>
                            <f:errors path="nilaiFisikLikuidasiEks" cssClass="help-block"/>
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
                <div class="control-group span6">
                    <label class="control-label" for="rangkaian">Rangkaian Mesin</label>
                    <div class="controls">
                        <f:input path="rangkaian"/>
                        <f:errors path="rangkaian" cssClass="help-block"/>
                    </div>
                </div>
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
                    <label class="control-label" for="waktuPemakaian">Waktu Penggunaan</label>
                    <div class="controls">
                        <f:input path="waktuPemakaian"/>
                        <f:errors path="waktuPemakaian" cssClass="help-block"/>
                    </div>
                </div>
            </div>
            <div id="menu3" class="tab-pane fade controls-row panel custom-ui">
                <div class="control-group span12">
                    <label class="control-label" for="manual">&nbsp;</label>
                    <div class="controls">
                        <f:checkbox path="manual" label="Hitung Manual"/>
                        <f:errors path="manual" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="nilaiDocPasar">Nilai Dokumen Pasar</label>
                    <div class="controls">
                        <f:input path="nilaiDocPasar" data-mask="luas-tanah"/>
                        <f:errors path="nilaiDocPasar" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="beriNilaiDoc">&nbsp;</label>
                    <div class="controls">
                        <f:radiobutton path="beriNilaiDoc" value="1" label="Beri Nilai"/>
                        <f:radiobutton path="beriNilaiDoc" value="0" label="Tidak Beri Nilai"/>
                        <f:errors path="beriNilaiDoc" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="nilaiFisikPasar">Nilai Fisik Pasar</label>
                    <div class="controls">
                        <f:input path="nilaiFisikPasar" data-mask="luas-tanah"/>
                        <f:errors path="nilaiFisikPasar" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="beriNilaiFisik">&nbsp;</label>
                    <div class="controls">
                        <f:radiobutton path="beriNilaiFisik" value="1" label="Beri Nilai"/>
                        <f:radiobutton path="beriNilaiFisik" value="0" label="Tidak Beri Nilai"/>
                        <f:errors path="beriNilaiFisik" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="likuidasi">Persen Likuidasi</label>
                    <div class="controls">
                        <div class="input-append">
                            <f:input path="likuidasi" data-mask="persen" cssClass="input-mini"/>
                            <span class="btn btn-primary"> % </span>
                        </div>
                        <f:errors path="likuidasi" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span12">
                    <label class="control-label" for="nilaiDocLikuidasi">Nilai Dokumen Likuidasi</label>
                    <div class="controls">
                        <f:input path="nilaiDocLikuidasi" data-mask="luas-tanah"/>
                        <f:errors path="nilaiDocLikuidasi" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span12">
                    <label class="control-label" for="nilaiFisikLikuidasi">Nilai Fisik Likuidasi</label>
                    <div class="controls">
                        <f:input path="nilaiFisikLikuidasi" data-mask="luas-tanah"/>
                        <f:errors path="nilaiFisikLikuidasi" cssClass="help-block"/>
                    </div>
                </div>
            </div>
        </div>
    </f:form>
</div>
<div id="modal-image" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="modal-image-label" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="modal-image-label">Data Lampiran Mesin</h3>
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

        $.active = false;
        $('body').bind('click keypress', function() { $.active = true; });
        var logout = '${ctxPath}/logoutExpired';
        var heartBeat = '${ctxPath}/heartbeat';
        checkActivity(${maxInterval}, 60000, 0,logout,heartBeat);


        callbackWorksheet = function(data){

            $('.nav-tabs a[href="#menu3"]').tab('show');

            $('#nilaiDocPasar').val(data.nilaiDokPasar);
            $('#nilaiDocPasar').removeProp("readonly");

            $('#nilaiFisikPasar').val(data.nilaiFisikPasar);
            $('#nilaiFisikPasar').removeProp("readonly");

            $('#nilaiDocLikuidasi').val(data.nilaiDokLikuidasi);
            $('#nilaiDocLikuidasi').removeProp("readonly");

            $('#nilaiFisikLikuidasi').val(data.nilaiFisikLikuidasi);
            $('#nilaiFisikLikuidasi').removeProp("readonly");


        }



        callbackModal = function(){
            refreshTableMesin();
            refreshTableImage();
            $('#modal-image').modal('hide');
        }
        callbackModalError = function(){
            $('#modal-image').modal('hide');
            gotoUrl("${ctxPath}/app-valuer/list-error");
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
        var refreshTableMesin = function(){
            $.ajax({
                type: "GET",
                cache: false,
                dataType: "html",
                url: 'show-lampiran?idLpaMesin=${formBean.id}',
                success: function (data) {
                    fillTbody(data);
                    $('#t_totalDokumen, #t_totalPasar, #t_totalUnit').inputmask("numeric", {
                        clearMaskOnLostFocus : true,
                        digits: 2,
                        placeholder : '0.00',
                        groupSeparator: ',',
                        autoGroup : true,
                        allowMinus: false,
                        rightAlign: true
                    });
                    $('#jumlahUnit').val($('#t_totalUnit').val());
                    var hitungManual = $('input[name=manual]').prop("checked");
                    if(!hitungManual) {
                        $('#nilaiFisikPasar').val(parseToFloat($('#t_totalPasar').inputmask('unmaskedvalue')));
                        $('#nilaiDocPasar').val(parseToFloat($('#t_totalDokumen').inputmask('unmaskedvalue')));
                    }
                    $('#modal-image').modal('hide');
                    $('#nilaiDocPasar').trigger('change');
                }
            });
        }
        function fillTbodyImage(html){
            $('#tbody-image').html(html);
            unbindAndbindImage();
        }
        function fillTbody(html) {
            $('#tbody-lampiran').html(html);
            if($('#tbody-lampiran tr').length >3){
                $('#lampiran-dok').removeClass("aktif-link");
            }else{
                $('#lampiran-dok').addClass("aktif-link");
            }
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
                        url: '${ctxPath}/app-valuer/delete-image?id=' + id + '&surveyId=${formBean.surveyId}&lpaId=${formBean.idLpa}',
                        success: function (data) {
                            fillTbodyImage(data);
                            refreshTableImageOther();
                        }
                    })
                }
            });
        }
        function unbindAndbind() {
            $(".lampiran-edit").unbind('click');
            $(".lampiran-edit").bind('click', function () {
                var id = $(this).data('id');
                $('#iframe-image').prop('src', "${ctxPath}/lpa-mesin/form-lampiran?lpaId=${formBean.id}&id="+id);
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
                        url: 'delete-lampiran-mesin?id=' + id + '&idLpaMesin=${formBean.id}',
                        success: function (data) {
                            fillTbody(data);
                        }
                    })
                }
            });
        }
        function calc(){
            var hitungManual = $('input[name=manual]').prop("checked");
            if (!hitungManual) {
                $('#nilaiDocPasar').prop('readonly', true);
                $('#nilaiFisikPasar').prop('readonly', true);
                $('#nilaiDocLikuidasi').prop('readonly', true);
                $('#nilaiFisikLikuidasi').prop('readonly', true);

                var totalDoc = parseToFloat($('#nilaiDocPasar').inputmask('unmaskedvalue'));
                var totalFisik = parseToFloat($('#nilaiFisikPasar').inputmask('unmaskedvalue'));
                var persen = parseToFloat($('#likuidasi').inputmask('unmaskedvalue'));
                var beriNilaiDoc = $('input[name=beriNilaiDoc]:checked').val();
                var beriNilaiDoc = $('input[name=beriNilaiDoc]:checked').val();
                var beriNilaiFisik = $('input[name=beriNilaiFisik]:checked').val();
                var likuidasiDoc = totalDoc * persen / 100;
                var likuidasiFisik = totalFisik * persen / 100;
                if(beriNilaiDoc != "1"){
                    likuidasiDoc = 0;
                }if(beriNilaiFisik != "1"){
                    likuidasiFisik = 0;
                }

                $('#nilaiDocLikuidasi').val(likuidasiDoc);
                $('#nilaiFisikLikuidasi').val(likuidasiFisik);
            } else {
                $('#nilaiDocPasar').prop('readonly', false);
                $('#nilaiFisikPasar').prop('readonly', false);
                $('#nilaiDocLikuidasi').prop('readonly', false);
                $('#nilaiFisikLikuidasi').prop('readonly', false);
            }
        }
        function calcEks(){
            var hitungManual = $('input[name=manualEks]').prop("checked");
            if (!hitungManual) {
                $('#nilaiDocPasarEks').prop('readonly', true);
                $('#nilaiFisikPasarEks').prop('readonly', true);
                $('#nilaiDocLikuidasiEks').prop('readonly', true);
                $('#nilaiFisikLikuidasiEks').prop('readonly', true);

                var totalDoc = parseToFloat($('#nilaiDocPasarEks').inputmask('unmaskedvalue'));
                var totalFisik = parseToFloat($('#nilaiFisikPasarEks').inputmask('unmaskedvalue'));
                var persen = parseToFloat($('#likuidasiEks').inputmask('unmaskedvalue'));
                var beriNilaiDoc = $('input[name=beriNilaiDocEks]:checked').val();
                var beriNilaiDoc = $('input[name=beriNilaiDocEks]:checked').val();
                var beriNilaiFisik = $('input[name=beriNilaiFisikEks]:checked').val();
                var likuidasiDoc = totalDoc * persen / 100;
                var likuidasiFisik = totalFisik * persen / 100;
                if(beriNilaiDoc != "1"){
                    likuidasiDoc = 0;
                }if(beriNilaiFisik != "1"){
                    likuidasiFisik = 0;
                }

                $('#nilaiDocLikuidasiEks').val(likuidasiDoc);
                $('#nilaiFisikLikuidasiEks').val(likuidasiFisik);
            } else {
                $('#nilaiDocPasarEks').prop('readonly', false);
                $('#nilaiFisikPasarEks').prop('readonly', false);
                $('#nilaiDocLikuidasiEks').prop('readonly', false);
                $('#nilaiFisikLikuidasiEks').prop('readonly', false);
            }
        }
        $('#xx').click(function () {
            var totalDokumen = parseToFloat($('#nilaiDocPasar').inputmask('unmaskedvalue'));
            var totalDokumenEks = parseToFloat($('#nilaiDocPasarEks').inputmask('unmaskedvalue'));

            if (totalDokumen!=null&&totalDokumenEks!=null) {
                var devisiasi = totalDokumenEks - totalDokumen;
                        var data2 = (devisiasi / totalDokumen) * 100;
                        $('#deviasiDoc').val(data2);
            }

            var totalFisik = parseToFloat($('#nilaiFisikPasar').inputmask('unmaskedvalue'));
            var totalFisikEks = parseToFloat($('#nilaiFisikPasarEks').inputmask('unmaskedvalue'));

            if (totalFisik!=null&&totalFisikEks!=null) {
                var devisiasiFisik = totalFisikEks - totalFisik;
                        var data = (devisiasiFisik / totalFisik) * 100;
                        $('#deviasiFisik').val(data);
            }

        });
        $('#btn-add-mesin').click(function () {
            $('#modal-image-label').text('Form Lampiran Mesin');
            $('#iframe-image').prop('src', "${ctxPath}/lpa-mesin/form-lampiran?lpaId=${formBean.id}");
            $('#modal-image').modal('show');
        });
        $('#btn-add-image').click(function () {
            $('#modal-image-label').text('Form Lampiran');
            $('#iframe-image').prop('src', "${ctxPath}/app-valuer/form-image?lpaId=${formBean.idLpa}&activaId=${formBean.jenisAktiva}");
            $('#modal-image').modal('show');
        });
        $('#likuidasi, input[name=beriNilaiDoc], input[name=beriNilaiFisik], #nilaiDocPasar, #nilaiFisikPasar, input[name=manual]').change(function () {
            calc();
        });
        <c:if test="${isEksternal}">
            $('#likuidasiEks, input[name=beriNilaiDocEks], input[name=beriNilaiFisikEks], #nilaiDocPasarEks, #nilaiFisikPasarEks, input[name=manualEks]').change(function () {
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
            refreshTableMesin();
            refreshTableImage();
            refreshTableImageOther();
        </c:if>
        <c:if test="${isEksternal}">
            calcEks();
        </c:if>

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
        </c:forEach>
        </spring:hasBindErrors>
    });
</script>
</body>
</html>