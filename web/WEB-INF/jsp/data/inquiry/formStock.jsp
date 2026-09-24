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
        <%--<input type="hidden" name="pembanding1" value="-">--%>
        <%--<input type="hidden" name="pembanding2" value="-">--%>
        <%--<input type="hidden" name="pembanding3" value="-">--%>
        <div class="page-header-page">
            <div class="absolute-right input-append">
                <c:if test="${formBean.id != null}">
                    <div class="btn-group">
                        <button class="btn btn-info dropdown-toggle" data-toggle="dropdown"><i class="icon-white icon-file"></i>Preview Laporan <span class="caret"></span></button>
                        <ul class="dropdown-menu">
                            <c:if test="${!isEksternal}">
                                <li><a href="laporan-lpa-stock?PIdLpaUmum=${formBean.idLpa}&lpa=lpa-stock" target="_blank">LPA</a></li>
                            </c:if>
                            <c:if test="${isEksternal}">
                                <li><a href="laporan-lpa-stock?PIdLpaUmum=${formBean.idLpa}&lpa=lpa-stock-ext" target="_blank">LPA</a></li>
                            </c:if>

                            <li class="divider"></li>
                            <li><a href="laporan-lpa-stock?PIdLpaUmum=${formBean.idLpa}&lpa=img-survey" target="_blank">Image Hasil Survey</a></li>
                            <li><a href="laporan-lpa-stock?PIdLpaUmum=${formBean.idLpa}&lpa=img-peta" target="_blank">Image Peta Wilayah / Lokasi</a></li>
                            <li><a href="#myModalLain" data-toggle="modal">Image Dokumen Lain</a></li>
                        </ul>
                    </div>
                </c:if>
                <c:if test="${showButton}">
                    <%--<button type="submit" id="btn-save-all" class="btn btn-success"><i class="icon-white icon-ok"></i>Save</button>--%>
                    <%--<button type="submit" id="btn-save-draft" class="btn btn-info"><i class="icon-white icon-camera"></i> Draf</button>--%>
                    <%--<button type="reset" class="btn btn-primary"><i class="icon-white icon-refresh"></i> Reset</button>--%>
                    <button type="button" onclick="gotoUrl('${ctxPath}/inquiry/view?id=${formBean.commonId}');" class="btn btn-warning"><i class="icon-white icon-arrow-left"></i> Back</button>
                    <%--<c:if test="${formBean.id != null}">--%>
                        <%--<a href="delete-lpa?id=${formBean.idLpa}&commonId=${formBean.commonId}" id="btn-delete-lpa" class="btn btn-danger"><i class="icon-white icon-remove"></i> Delete</a>--%>
                    <%--</c:if>--%>
                </c:if>
                <c:if test="${!showButton}">
                    <button type="button" onclick="gotoUrl('${ctxPath}/inquiry/view?id=${formBean.commonId}');" class="btn btn-warning"><i class="icon-white icon-arrow-left"></i> Back</button>
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
            <li class="active"><a data-toggle="tab" href="#menu1">Data Umum<span class="badge badge-warning hide"></span></a></li>
            <li><a data-toggle="tab" href="#menu2">Stock/Uraian Barang<span class="badge badge-warning hide"></span></a></li>
            <c:if test="${isEksternal}">
                <li><a data-toggle="tab" href="#menu7">Penilaian Agunan (Eksternal)<span class="badge badge-warning hide"></span></a></li>
                <li><a data-toggle="tab" href="#menu3">Penilaian Agunan (Internal)<span class="badge badge-warning hide"></span></a></li>
            </c:if>
            <c:if test="${!isEksternal}">
                <li><a data-toggle="tab" href="#menu3">Penilaian Agunan<span class="badge badge-warning hide"></span></a></li>
            </c:if>
            <li><a data-toggle="tab" href="#menu4">Faktor Marketability & Catatan<span class="badge badge-warning hide"></span></a></li>
            <li><a data-toggle="tab" href="#menu5">Negative List & Pertimbangan Khusus<span class="badge badge-warning hide"></span></a></li>
            <c:if test="${isEksternal}">
                <li><a data-toggle="tab" href="#menu8">Opini Internal <span class="badge badge-warning hide"></span></a></li>
            </c:if>
            <li><a data-toggle="tab" href="#menu6">Data Lampiran<span class="badge badge-warning hide"></span></a></li>
        </ul>
        <div class="tab-content">
            <div id="menu1" class="tab-pane fade in active panel controls-row">
                <jsp:include page="../../lpa/form-umum.jsp"/>
            </div>
            <div id="menu4" class="tab-pane fade controls-row panel">
                <jsp:include page="../../lpa/form-market-stock.jsp"/>
            </div>
            <div id="menu5" class="tab-pane fade controls-row panel">
                <jsp:include page="../../lpa/form-pertimbangan-dan-negative.jsp"/>
            </div>
            <div id="menu6" class="tab-pane fade controls-row panel">
                <div class="absolute-right">
                    <%--<c:if test="${formBean.id != null}"><button id="btn-add-image" type="button" class="btn btn-success"><i class="icon-white icon-plus"></i> Add</button></c:if>--%>
                </div>
                <table class="table dataTable" style="margin-top:28px;">
                    <thead><tr><th width="100px">#</th><th>Kategori</th><th>Catatan</th></tr></thead>
                    <tbody id="tbody-image"></tbody>
                </table>
            </div>
            <div id="menu2" class="tab-pane fade controls-row panel">
                <div class="page-header">
                    <h5>Data Agunan</h5>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="jenisPersedian">Jenis Persediaan</label>
                    <div class="controls custom-ui">
                        <f:input path="jenisPersedian"/>
                        <f:errors path="jenisPersedian" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="asalPembuatan">Asal Pembuatan</label>
                    <div class="controls">
                        <f:input path="asalPembuatan"/>
                        <f:errors path="asalPembuatan" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="penempatanBarang">Penempatan Barang</label>
                    <div class="controls">
                        <f:select path="penempatanBarang">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listPenempatanBarang}" itemValue="value" itemLabel="label"/>
                        </f:select>
                        <f:errors path="penempatanBarang" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="kemasanBarang">Kemasan Barang</label>
                    <div class="controls">
                        <f:select path="kemasanBarang">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listKemasanBarang}" itemValue="value" itemLabel="label"/>
                        </f:select>
                        <f:errors path="kemasanBarang" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="kondisiBarang">Kondisi Barang</label>
                    <div class="controls">
                        <f:select path="kondisiBarang">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listKondisiBarang}" itemValue="value" itemLabel="label"/>
                        </f:select>
                        <f:errors path="kondisiBarang" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="metodePemeriksaan">Metode Pemeriksaan</label>
                    <div class="controls">
                        <f:select path="metodePemeriksaan">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listMetodePemeriksaan}" itemValue="value" itemLabel="label"/>
                        </f:select>
                        <f:errors path="metodePemeriksaan" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="jumlahPersedian">Jumlah Persediaan</label>
                    <div class="controls">
                        <f:select path="jumlahPersedian">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listJumlahPersediaan}" itemValue="value" itemLabel="label"/>
                        </f:select>
                        <f:errors path="jumlahPersedian" cssClass="help-block"/>
                    </div>
                </div>
                <div class="page-header" style="clear: both">
                    <h5>Data Tempat Penyimpanan</h5>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="jenisPenyimpanan">Tempat Penyimpanan</label>
                    <div class="controls">
                        <f:select path="jenisPenyimpanan">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listTempatPenyimpanan}" itemValue="value" itemLabel="label"/>
                        </f:select>
                        <f:errors path="jenisPenyimpanan" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="kondisiPenyimpanan">Kondisi Tempat Penyimpanan</label>
                    <div class="controls">
                        <f:select path="kondisiPenyimpanan">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listKondisiPenyimpanan}" itemValue="value" itemLabel="label"/>
                        </f:select>
                        <f:errors path="kondisiPenyimpanan" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="lingkunganSekitar">Lingkungan Sekitar</label>
                    <div class="controls">
                        <f:select path="lingkunganSekitar">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listLingkunganPenyimpanan}" itemValue="value" itemLabel="label"/>
                        </f:select>
                        <f:errors path="lingkunganSekitar" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="peralatanGudang">Peralatan Gudang</label>
                    <div class="controls">
                        <f:select path="peralatanGudang">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listPeralatanGudang}" itemValue="value" itemLabel="label"/>
                        </f:select>
                        <f:errors path="peralatanGudang" cssClass="help-block"/>
                    </div>
                </div>
                <div class="page-header" style="clear: both">
                    <h5>Data Dokumen</h5>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="jenisDokumen">Jenis Dokumen</label>
                    <div class="controls">
                        <f:input path="jenisDokumen"/>
                        <f:errors path="jenisDokumen" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="nomorDokumen">Nomor Dokumen</label>
                    <div class="controls">
                        <f:input path="nomorDokumen"/>
                        <f:errors path="nomorDokumen" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="tanggalDokumen">Tanggal Dokumen</label>
                    <div class="controls">
                        <f:input path="tanggalDokumen" data-date-format="dd-mm-yyyy"
                                 data-provide="datepicker"  class="tutup-otomatis" placeholder="DD-MM-YYYY" data-date-end-date="0d"/>
                        <f:errors path="tanggalDokumen" cssClass="help-block"/>
                    </div>
                </div>
            </div>
            <div id="menu3" class="tab-pane fade controls-row panel">
                <div class="control-group span6">
                    <label class="control-label" for="nilaiDokumen">Nilai Dokumen Pasar</label>
                    <div class="controls">
                        <div>
                            <f:input path="nilaiDokumen" data-mask="luas-tanah"/>
                            <f:errors path="nilaiDokumen" cssClass="help-block"/>
                        </div>
                        <div class="custom-ui">
                            <f:radiobutton path="berinilaiDokumen" value="1" label="Diberi Nilai"/>
                            <f:radiobutton path="berinilaiDokumen" value="0" label="Tidak Diberi Nilai"/>
                            <f:errors path="berinilaiDokumen" cssClass="help-block"/>
                        </div>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="nilaiFisik">Nilai Fisik Pasar</label>
                    <div class="controls">
                        <div>
                            <f:input path="nilaiFisik" data-mask="luas-tanah"/>
                            <f:errors path="nilaiFisik" cssClass="help-block"/>
                        </div>
                        <div class="custom-ui">
                            <f:radiobutton path="berinilaiFisik" value="1" label="Diberi Nilai"/>
                            <f:radiobutton path="berinilaiFisik" value="0" label="Tidak Diberi Nilai"/>
                            <f:errors path="berinilaiFisik" cssClass="help-block"/>
                        </div>
                    </div>
                </div>
                <div class="control-group span12">
                    <label class="control-label" for="persentase">Persentase Likuidasi</label>
                    <div class="controls">
                        <div class="input-append">
                            <f:input path="persentase" cssClass="input-mini" data-mask="persen"/>
                            <button type="button" class="btn btn-info">%</button>
                        </div>
                        <f:errors path="persentase" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="likuidasiDokumen">Nilai Dokumen Likuidasi</label>
                    <div class="controls">
                        <f:input path="likuidasiDokumen" readonly="true" data-mask="luas-tanah"/>
                        <f:errors path="likuidasiDokumen" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="likuidasiFisik">Nilai Fisik Likuidasi</label>
                    <div class="controls">
                        <f:input path="likuidasiFisik" readonly="true" data-mask="luas-tanah"/>
                        <f:errors path="likuidasiFisik" cssClass="help-block"/>
                    </div>
                </div>
            </div>
            <div id="menu7" class="tab-pane fade controls-row panel">
                <div class="control-group span6">
                    <label class="control-label" for="nilaiDokumenEks">Nilai Dokumen Pasar</label>
                    <div class="controls">
                        <div>
                            <f:input path="nilaiDokumenEks" data-mask="luas-tanah"/>
                            <f:errors path="nilaiDokumenEks" cssClass="help-block"/>
                        </div>
                        <div class="custom-ui">
                            <f:radiobutton path="berinilaiDokumenEks" value="1" label="Diberi Nilai"/>
                            <f:radiobutton path="berinilaiDokumenEks" value="0" label="Tidak Diberi Nilai"/>
                            <f:errors path="berinilaiDokumenEks" cssClass="help-block"/>
                        </div>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="nilaiFisikEks">Nilai Fisik Pasar</label>
                    <div class="controls">
                        <div>
                            <f:input path="nilaiFisikEks" data-mask="luas-tanah"/>
                            <f:errors path="nilaiFisikEks" cssClass="help-block"/>
                        </div>
                        <div class="custom-ui">
                            <f:radiobutton path="berinilaiFisikEks" value="1" label="Diberi Nilai"/>
                            <f:radiobutton path="berinilaiFisikEks" value="0" label="Tidak Diberi Nilai"/>
                            <f:errors path="berinilaiFisikEks" cssClass="help-block"/>
                        </div>
                    </div>
                </div>
                <div class="control-group span12">
                    <label class="control-label" for="persentaseEks">Persentase Likuidasi</label>
                    <div class="controls">
                        <div class="input-append">
                            <f:input path="persentaseEks" cssClass="input-mini" data-mask="persen"/>
                            <button type="button" class="btn btn-info">%</button>
                        </div>
                        <f:errors path="persentaseEks" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="likuidasiDokumenEks">Nilai Dokumen Likuidasi</label>
                    <div class="controls">
                        <f:input path="likuidasiDokumenEks" readonly="true" data-mask="luas-tanah"/>
                        <f:errors path="likuidasiDokumenEks" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="likuidasiFisikEks">Nilai Fisik Likuidasi</label>
                    <div class="controls">
                        <f:input path="likuidasiFisikEks" readonly="true" data-mask="luas-tanah"/>
                        <f:errors path="likuidasiFisikEks" cssClass="help-block"/>
                    </div>
                </div>
            </div>
            <c:if test="${isEksternal}">
                <div id="menu8" class="tab-pane fade controls-row panel">
                    <jsp:include page="../../lpa/form-opini.jsp"/>
                </div>
            </c:if>
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
    var refreshTableImageOther;
    $(document).ready(function () {
        $('#form-lpa').find('input, textarea, select').prop('disabled', true);
        callbackModal = function(){
            refreshTableImage();
            $('#modal-image').modal('hide');
        }
        refreshTableImage = function(){
            $.ajax({
                type: "GET",
                cache: false,
                dataType: "html",
                url: '${ctxPath}/inquiry/table-image?surveyId=${formBean.surveyId}&lpaId=${formBean.idLpa}',
                success: function (html) {
                    fillTbodyImage(html);
                    $('#modal-image').modal('hide');
                }
            })
        }
        refreshTableImageOther = function(){
            $.ajax({
                type: "GET",
                cache: false,
                dataType: "html",
                url: '${ctxPath}/inquiry/table-image-other?lpaId=${formBean.idLpa}',
                success: function (html) {
                    fillTbodyImageOther(html);
                    $('#myModalLain').modal('hide');
                }
            })
        }
        function fillTbodyImageOther(html){
            $('#tbody-image-other').html(html);
        }
        function fillTbodyImage(html){
            $('#tbody-image').html(html);
            unbindAndbindImage();
        }
        function unbindAndbindImage() {
            $(".image-edit").unbind('click');
            $(".image-edit").bind('click', function () {
                var id = $(this).data('id');
                $('#iframe-image').prop('src', "${ctxPath}/inquiry/form-image?id="+id+"&lpaId=${formBean.idLpa}&activaId=${formBean.jenisAktiva}");
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
                        url: '${ctxPath}/inquiry/delete-image?id=' + id + '&surveyId=${formBean.surveyId}&lpaId=${formBean.idLpa}',
                        success: function (data) {
                            fillTbodyImage(data);
                            refreshTableImageOther();
                        }
                    })
                }
            });
        }
        function calc(){
            var totalDoc = parseToFloat($('#nilaiDokumen').inputmask('unmaskedvalue'));
            var totalFisik = parseToFloat($('#nilaiFisik').inputmask('unmaskedvalue'));
            var persen = parseToFloat($('#persentase').inputmask('unmaskedvalue'));
            var beriNilaiDoc = $('input[name=berinilaiDokumen]:checked').val();
            var beriNilaiFisik = $('input[name=berinilaiFisik]:checked').val();
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
            var totalDoc = parseToFloat($('#nilaiDokumenEks').inputmask('unmaskedvalue'));
            var totalFisik = parseToFloat($('#nilaiFisikEks').inputmask('unmaskedvalue'));
            var persen = parseToFloat($('#persentaseEks').inputmask('unmaskedvalue'));
            var beriNilaiDoc = $('input[name=berinilaiDokumenEks]:checked').val();
            var beriNilaiFisik = $('input[name=berinilaiFisikEks]:checked').val();
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
        $('#xx').click(function () {
            var totalDokumen = parseToFloat($('#nilaiDokumen').inputmask('unmaskedvalue'));
            var totalDokumenEks = parseToFloat($('#nilaiDokumenEks').inputmask('unmaskedvalue'));

            if (totalDokumen!=null&&totalDokumenEks!=null) {
                var devisiasi = totalDokumenEks - totalDokumen;
                var data2 = (devisiasi / totalDokumen) * 100;
                $('#deviasiDoc').val(data2);
            }

            var totalFisik = parseToFloat($('#nilaiFisik').inputmask('unmaskedvalue'));
            var totalFisikEks = parseToFloat($('#nilaiFisikEks').inputmask('unmaskedvalue'));

            if (totalFisik!=null&&totalFisikEks!=null) {
                var devisiasiFisik = totalFisikEks - totalFisik;
                var data = (devisiasiFisik / totalFisik) * 100;
                $('#deviasiFisik').val(data);
            }

        });
        $('#btn-add-image').click(function () {
            $('#modal-image-label').text('Form Lampiran');
            $('#iframe-image').prop('src', "${ctxPath}/inquiry/form-image?lpaId=${formBean.idLpa}&activaId=${formBean.jenisAktiva}");
            $('#modal-image').modal('show');
        });
        $('#persentase, input[name=berinilaiDokumen], input[name=berinilaiFisik], #nilaiDokumen, #nilaiFisik').change(function () {
            calc();
        });
        <c:if test="${isEksternal}">
        $('#persentaseEks, input[name=berinilaiDokumenEks], input[name=berinilaiFisikEks], #nilaiDokumenEks, #nilaiFisikEks').change(function () {
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
            refreshTableImage();
        refreshTableImageOther();
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