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

    <style>
        .aktif-link {
            display: none;
        }
    </style>
</head>
<body>
<div class="container">
    <f:form id="form-lpa" method="post" modelAttribute="formBean" action="draft-lpa" class="form-horizontal">
        <%--<f:hidden path="id"/>--%>
        <%--<f:hidden path="faktorMarketability"/>--%>
        <div class="page-header-page">
            <div class="absolute-right input-append">
                <c:if test="${formBean.id != null}">
                    <div class="btn-group">
                        <button class="btn btn-info dropdown-toggle" data-toggle="dropdown"><i class="icon-white icon-file"></i>Preview Laporan <span class="caret"></span></button>
                        <ul class="dropdown-menu">
                            <li><a href="laporan-lpa-inspeksi?PIdLpaUmum=${formBean.idLpa}&lpa=lpa-inspeksi" target="_blank">LPA</a></li>
                            <li class="divider"></li>
                            <li id="doks-image" class="aktif-link"><a href="laporan-lpa-inspeksi?PIdLpaUmum=${formBean.idLpa}&lpa=img-survey" target="_blank">Image Hasil Survey</a></li>
                            <li id="doks-barang-jadi" class="aktif-link"><a href="laporan-lpa-inspeksi?PIdLpaUmum=${formBean.idLpa}&lpa=detail-barang-jadi" target="_blank">Lampiran Barang Jadi</a></li>
                            <li id="doks-barang-setengah-jadi" class="aktif-link"><a href="laporan-lpa-inspeksi?PIdLpaUmum=${formBean.idLpa}&lpa=detail-barang-setengah-jadi" target="_blank">Lampiran Barang Setengah Jadi</a></li>
                            <li id="doks-bahan-baku" class="aktif-link"><a href="laporan-lpa-inspeksi?PIdLpaUmum=${formBean.idLpa}&lpa=detail-bahan-baku" target="_blank">Lampiran Bahan Baku</a></li>
                            <%--<li><a href="#myModalLain" data-toggle="modal">Image Dokumen Lain</a></li>--%>
                        </ul>
                    </div>
                </c:if>
                <%--<c:if test="${showButton}">--%>
                    <%--<button type="submit" id="btn-save-all" class="btn btn-success"><i class="icon-white icon-ok"></i>Save</button>--%>
                    <%--<c:if test="${disableDraft}">--%>
                        <%--<button type="submit" id="btn-save-draft" class="btn btn-info"><i class="icon-white icon-camera"></i> Draft</button>--%>
                    <%--</c:if>--%>
                    <%--<button type="reset" class="btn btn-primary"><i class="icon-white icon-refresh"></i> Reset</button>--%>
                    <button type="button" onclick="gotoUrl('${ctxPath}/inquiry/view?id=${formBean.commonId}');" class="btn btn-warning"><i class="icon-white icon-arrow-left"></i> Back</button>
                    <%--<c:if test="${formBean.id != null}">--%>
                        <%--<a href="delete-lpa?id=${formBean.idLpa}&commonId=${formBean.commonId}" id="btn-delete-lpa" class="btn btn-danger"><i class="icon-white icon-remove"></i> Delete</a>--%>
                    <%--</c:if>--%>
                <%--</c:if>--%>
                <%--<c:if test="${!showButton}">--%>
                    <%--<button type="button" onclick="gotoUrl('${ctxPath}/app-approval/view?id=${formBean.commonId}');" class="btn btn-warning"><i class="icon-white icon-arrow-left"></i> Back</button>--%>
                <%--</c:if>--%>
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
            <li><a data-toggle="tab" href="#menu2">Data Persediaan Barang<span class="badge badge-warning hide"></span></a></li>
            <li><a data-toggle="tab" href="#menu3">Barang Jadi<span class="badge badge-warning hide"></span></a></li>
            <li><a data-toggle="tab" href="#menu4">Barang Setengah Jadi<span class="badge badge-warning hide"></span></a></li>
            <li><a data-toggle="tab" href="#menu5">Bahan Baku<span class="badge badge-warning hide"></span></a></li>
            <li><a data-toggle="tab" href="#menu6">Data Lampiran<span class="badge badge-warning hide"></span></a></li>
        </ul>
        <div class="tab-content">
            <div id="menu1" class="tab-pane fade in active panel controls-row">
                <jsp:include page="../../lpa/form-umum.jsp"/>
            </div>
            <div id="menu4" class="tab-pane fade controls-row panel">
                    <div class="control-group span6">
                        <label class="control-label" for="subTotalHalfGoods">Sub Total Barang Setengah Jadi</label>
                        <div class="controls">
                            <f:input path="subTotalHalfGoods" readonly="true"/>
                            <f:errors path="subTotalHalfGoods" cssClass="help-block"/>
                        </div>
                    </div>
                    <div class="control-group span6">
                        <label class="control-label" for="subTotalHalfGoodsFisik">Sub Total Barang Setengah Jadi Fisik</label>
                        <div class="controls">
                            <f:input path="subTotalHalfGoodsFisik" readonly="true"/>
                            <f:errors path="subTotalHalfGoodsFisik" cssClass="help-block"/>
                        </div>
                    </div><br><br>
                    <%--<div class="control-group span6">--%>
                        <div class="page-header">
                            <%--<c:if test="${showButton}">--%>
                                <%--<c:if test="${formBean.id != null}">--%>
                                    <%--<div class="absolute-right">--%>
                                        <%--<button id="btn-add-barang-setengah-jadi"  href="form-item-inspeksi" data-target="#modal-barang-setengah-jadi" role="button" data-toggle="modal" data-remote="true"--%>
                                                <%--type="button" class="btn btn-info"><i class="icon-white icon-plus"></i> Add--%>
                                        <%--</button>--%>
                                    <%--</div>--%>
                                <%--</c:if>--%>
                            <%--</c:if>--%>
                            <h3>Lampiran Data Barang Setengah Jadi</h3>
                        </div>
                    <%--</div>--%>
                    <table id="table-barang-setengah-jadi" class="table dataTable">
                        <thead>
                        <tr>
                            <th style="width: 80px;">Kode</th>
                            <th>Barang Setengah Jadi</th>
                            <th>Jumlah Sesuai Dokumen</th>
                            <th>Jumlah Sesuai Fisik</th>
                        </tr>
                        </thead>
                        <tbody id="tbody-barang-setengah-jadi">
                        <c:if test="${formBean.id == null}">
                            <tr class="odd">
                                <td colspan="4"><i>Untuk Menambah Lampiran Barang Setengah Jadi, lakukan Simpan dahulu</i></td>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>

            </div>
            <div id="menu5" class="tab-pane fade controls-row panel">
                    <div class="control-group span6">
                        <label class="control-label" for="subTotalRawMaterial">Sub Total Bahan Baku</label>
                        <div class="controls">
                            <f:input path="subTotalRawMaterial" readonly="true"/>
                            <f:errors path="subTotalRawMaterial" cssClass="help-block"/>
                        </div>
                    </div>
                    <div class="control-group span6">
                        <label class="control-label" for="subTotalRawMaterialFisik">Sub Total Bahan Baku Fisik</label>
                        <div class="controls">
                            <f:input path="subTotalRawMaterialFisik" readonly="true"/>
                            <f:errors path="subTotalRawMaterialFisik" cssClass="help-block"/>
                        </div>
                    </div><br>
                    <%--<div class="control-group span6">--%>
                        <div class="page-header">
                            <%--<c:if test="${showButton}">--%>
                                <%--<c:if test="${formBean.id != null}">--%>
                                    <%--<div class="absolute-right">--%>
                                        <%--<button id="btn-add-bahan-baku"  href="form-item-inspeksi" data-target="#modal-bahan-baku" role="button" data-toggle="modal" data-remote="true"--%>
                                                <%--type="button" class="btn btn-info"><i class="icon-white icon-plus"></i> Add--%>
                                        <%--</button>--%>
                                    <%--</div>--%>
                                <%--</c:if>--%>
                            <%--</c:if>--%>
                            <h3>Lampiran Data Bahan Baku</h3>
                        </div>
                    <%--</div>--%>
                    <table id="table-bahan-baku" class="table dataTable">
                        <thead>
                        <tr>
                            <th style="width: 80px;">Kode</th>
                            <th>Bahan Baku</th>
                            <th>Jumlah Sesuai Dokumen</th>
                            <th>Jumlah Sesuai Fisik</th>
                        </tr>
                        </thead>
                        <tbody id="tbody-bahan-baku">
                        <c:if test="${formBean.id == null}">
                            <tr class="odd">
                                <td colspan="4"><i>Untuk Menambah Lampiran Bahan Baku, lakukan Simpan dahulu</i></td>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>
            </div>
            <div id="menu6" class="tab-pane fade controls-row panel">
                <c:if test="${showButton}" >
                <div class="absolute-right">
                    <%--<c:if test="${formBean.id != null}"><button id="btn-add-image" type="button" class="btn btn-success"><i class="icon-white icon-plus"></i> Add</button></c:if>--%>
                </div>
                </c:if>
                <table class="table dataTable" style="margin-top:28px;">
                    <thead><tr><th width="100px">#</th><th>Kategori</th><th>Catatan</th></tr></thead>
                    <tbody id="tbody-image"></tbody>
                </table>
            </div>
            <div id="menu2" class="tab-pane fade controls-row panel">
                <div class="page-header" style="clear: both">
                    <h5>Data Dokumen</h5>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="jenisBarang">Jenis Barang</label>
                    <div class="controls">
                        <f:input path="jenisBarang" readonly="true"/>
                        <f:errors path="jenisBarang" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="nomorDaftar">Nomor Daftar Persediaan Barang</label>
                    <div class="controls">
                        <f:input path="nomorDaftar" readonly="true"/>
                        <f:errors path="nomorDaftar" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="tanggalDaftar">Tanggal Daftar Persediaan Barang</label>
                    <div class="controls">
                        <f:input path="tanggalDaftar" data-date-format="dd-mm-yyyy"
                                 data-provide="datepicker"  class="tutup-otomatis" placeholder="DD-MM-YYYY" data-date-end-date="0d"/>
                        <f:errors path="tanggalDaftar" cssClass="help-block"/>
                    </div>
                </div>
                <div class="page-header" style="clear: both">
                    <h5>Data Tempat Penyimpanan</h5>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="jenisTempat">Jenis Tempat Penyimpanan</label>
                    <div class="controls">
                        <f:select path="jenisTempat">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listTempatPenyimpanan}" itemValue="value" itemLabel="label"/>
                        </f:select>
                        <f:errors path="jenisTempat" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="infoAlamatTempat">Informasi Alamat Tempat Penyimpanan</label>
                    <div class="controls">
                        <f:select path="infoAlamatTempat">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listInfoAlamatTempat}" itemValue="value" itemLabel="label"/>
                        </f:select>
                        <f:errors path="infoAlamatTempat" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="alamatLain">Alamat Lain Yaitu Terletak di</label>
                    <div class="controls">
                        <f:input path="alamatLain"/>
                        <f:errors path="alamatLain" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="statusTempat">Status Tempat Penyimpanan</label>
                    <div class="controls">
                        <f:select path="statusTempat">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listStatusTempat}" itemValue="value" itemLabel="label"/>
                        </f:select>
                        <f:errors path="statusTempat" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="kondisiTempat">Kondisi Tempat Penyimpanan</label>
                    <div class="controls">
                        <f:select path="kondisiTempat">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listKondisiTempat}" itemValue="value" itemLabel="label"/>
                        </f:select>
                        <f:errors path="kondisiTempat" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="aktifitasKerja">Aktifitas Kerja Tempat Penyimpanan</label>
                    <div class="controls">
                        <f:select path="aktifitasKerja">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listAktifitasKerja}" itemValue="value" itemLabel="label"/>
                        </f:select>
                        <f:errors path="aktifitasKerja" cssClass="help-block"/>
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
                <div class="control-group span6">
                    <label class="control-label" for="alatPemadam">Alat Pemadam Kebakaran</label>
                    <div class="controls">
                        <f:select path="alatPemadam">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listAlatPemadam}" itemValue="value" itemLabel="label"/>
                        </f:select>
                        <f:errors path="alatPemadam" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="keamanan">Keamanan</label>
                    <div class="controls">
                        <f:select path="keamanan">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listKeamanan}" itemValue="value" itemLabel="label"/>
                        </f:select>
                        <f:errors path="keamanan" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="kondisiJalan">Kondisi Jalan Tempat Penyimpanan</label>
                    <div class="controls">
                        <f:select path="kondisiJalan">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listKondisiJalan}" itemValue="value" itemLabel="label"/>
                        </f:select>
                        <f:errors path="kondisiJalan" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="lingkunganSekitar">Kawasan Lingkungan Sekitar</label>
                    <div class="controls">
                        <f:select path="lingkunganSekitar">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listLingkunganSekitar}" itemValue="value" itemLabel="label"/>
                        </f:select>
                        <f:errors path="lingkunganSekitar" cssClass="help-block"/>
                    </div>
                </div>
                <div class="page-header" style="clear: both">
                    <h5>Data Hasil Pemeriksaan</h5>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="metodePeriksa">Metode Pemeriksaan</label>
                    <div class="controls">
                        <f:select path="metodePeriksa">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listMetodePeriksa}" itemValue="value" itemLabel="label"/>
                        </f:select>
                        <f:errors path="metodePeriksa" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="tempatPersediaan">Penempatan Persediaan Barang</label>
                    <div class="controls">
                        <f:select path="tempatPersediaan">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listTempatPersediaan}" itemValue="value" itemLabel="label"/>
                        </f:select>
                        <f:errors path="" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="susunanTempat">Susunan Penempatan Barang</label>
                    <div class="controls">
                        <f:select path="susunanTempat">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listSusunanTempat}" itemValue="value" itemLabel="label"/>
                        </f:select>
                        <f:errors path="susunanTempat" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="packing">Packing Barang</label>
                    <div class="controls">
                        <f:select path="packing">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listPacking}" itemValue="value" itemLabel="label"/>
                        </f:select>
                        <f:errors path="packing" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="catatanPoint">Catatan Hasil Kunjungan</label>
                    <div class="controls">
                        <f:textarea path="catatanPoint" readonly="true" rows="3" cols="30" maxlength="150"></f:textarea>
                        <f:errors path="catatanPoint" cssClass="help-block"/>
                    </div>
                </div>
            </div>
            <div id="menu3" class="tab-pane fade controls-row panel">
                <div class="control-group span6">
                    <label class="control-label" for="subTotalGoods">Sub Total Barang Jadi</label>
                    <div class="controls">
                        <f:input path="subTotalGoods" readonly="true"/>
                        <f:errors path="subTotalGoods" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="subTotalGoodsFisik">Sub Total Barang Jadi Fisik</label>
                    <div class="controls">
                        <f:input path="subTotalGoodsFisik" readonly="true"/>
                        <f:errors path="subTotalGoodsFisik" cssClass="help-block"/>
                    </div>
                </div><br>
                <%--<div class="control-group span6">--%>
                    <div class="page-header">
                        <%--<c:if test="${showButton}">--%>
                            <%--<c:if test="${formBean.id != null}">--%>
                                <%--<div class="absolute-right">--%>
                                    <%--<button id="btn-add-barang-jadi"  href="form-item-inspeksi" data-target="#modal-barang-jadi" role="button" data-toggle="modal" data-remote="true"--%>
                                            <%--type="button" class="btn btn-info"><i class="icon-white icon-plus"></i> Add--%>
                                    <%--</button>--%>
                                <%--</div>--%>
                            <%--</c:if>--%>
                        <%--</c:if>--%>
                        <h3>Lampiran Data Barang Jadi</h3>
                    </div>
                <%--</div>--%>
                <table id="table-barang-jadi" class="table dataTable">
                    <thead>
                    <tr>
                        <th style="width: 80px;">Kode</th>
                        <th>Barang Jadi</th>
                        <th>Jumlah Sesuai Dokumen</th>
                        <th>Jumlah Sesuai Fisik</th>
                    </tr>
                    </thead>
                    <tbody id="tbody-barang-jadi">
                    <c:if test="${formBean.id == null}">
                        <tr class="odd">
                            <td colspan="4"><i>Untuk Menambah Lampiran Barang Jadi, lakukan Simpan dahulu</i></td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
            <div id="menu7" class="tab-pane fade controls-row panel">

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
<div id="modal-barang-jadi" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="modal-barang-jadi-label" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
        <h3 id="modal-barang-jadi-label">Data Barang Jadi</h3>
    </div>
    <div class="modal-body"></div>
</div>
<div id="modal-barang-setengah-jadi" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="modal-barang-setengah-jadi-label" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
        <h3 id="modal-barang-setengah-jadi-label">Data Barang Setengah Jadi</h3>
    </div>
    <div class="modal-body"></div>
</div>
<div id="modal-bahan-baku" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="modal-bahan-baku-label" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
        <h3 id="modal-bahan-baku-label">Data Bahan Baku</h3>
    </div>
    <div class="modal-body"></div>
</div>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<script src="${ctxPath}/js/bootstrap-datepicker-1.6.4/js/bootstrap-datepicker.min.js"></script>
<script src="${ctxPath}/js/jquery.inputmask.bundle.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
    var callbackModal;
    var callbackModalBarangJadi;
    var callbackModalBarangSetengahJadi;
    var callbackModalBahanBaku;
    var refreshTableImage;
    var refreshTableImageOther;
    var refreshTableBarang;
    var refreshTableBarangSetengahJadi;
    var refreshTableBahanBaku;
    $(document).ready(function () {
        $('#form-lpa').find('input, textarea, select').prop('disabled', true);
        callbackModal = function(){
            refreshTableImage();
            $('#modal-image').modal('hide');
            refreshTableBarang();
            $('#modal-barang-jadi').modal('hide');
            refreshTableBarangSetengahJadi();
            $('#modal-barang-setengah-jadi').modal('hide');
            refreshTableBahanBaku();
            $('#modal-bahan-baku').modal('hide');
        }
        callbackModalBarangJadi = function(){
            refreshTableBarang();
            $('#modal-barang-jadi').modal('hide');
        }
        callbackModalBarangSetengahJadi = function(){
            refreshTableBarangSetengahJadi();
            $('#modal-barang-setengah-jadi').modal('hide');
        }
        callbackModalBahanBaku = function(){
            refreshTableBahanBaku();
            $('#modal-bahan-baku').modal('hide');
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
            if($('#tbody-image tr').length >0){
                $('#doks-image').removeClass("aktif-link");
            }else{
                $('#doks-image').addClass("aktif-link");
            }
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

        function unbindAndbindBarangJadi() {
            $(".barang-jadi-edit").unbind('click');
            $(".barang-jadi-edit").bind('click', function () {
                var id = $(this).data('id');
                $('#modal-barang-jadi').find(".modal-body").load("form-item-inspeksi?jenisAktiva=${formBean.jenisAktiva}&lpaInspeksiId=${formBean.id}&typeGoods=Goods&id="+id, function(){
                    initFormBarangJadi();
                    $('#modal-barang-jadi').modal('show');
                });
            });
            $(".barang-jadi-delete").unbind('click');
            $(".barang-jadi-delete").bind('click', function () {
                if (confirm('Anda yakin hapus data')) {
                    var id = $(this).data('id');
                    $.ajax({
                        type: "GET",
                        cache: false,
                        dataType: "html",
                        url: 'delete-barang?id=' + id + '&commonId=${formBean.idLpa}&typeGoods=Goods',
                        success: function (data) {
                            fillTbodyBarangJadi(data);
                            refreshTableBarang();
                        }
                    })
                }
            });
        }

        function unbindAndbindBarangSetengahjadi() {
            $(".barang-setengah-jadi-edit").unbind('click');
            $(".barang-setengah-jadi-edit").bind('click', function () {
                var id = $(this).data('id');
                $('#modal-barang-setengah-jadi').find(".modal-body").load("form-item-inspeksi?jenisAktiva=${formBean.jenisAktiva}&lpaInspeksiId=${formBean.id}&typeGoods=Half_Goods&id="+id, function(){
                    initFormBarangSetengahJadi();
                    $('#modal-barang-setengah-jadi').modal('show');
                });
            });
            $(".barang-setengah-jadi-delete").unbind('click');
            $(".barang-setengah-jadi-delete").bind('click', function () {
                if (confirm('Anda yakin hapus data')) {
                    var id = $(this).data('id');
                    $.ajax({
                        type: "GET",
                        cache: false,
                        dataType: "html",
                        url: 'delete-barang?id=' + id + '&commonId=${formBean.idLpa}&typeGoods=Half_Goods',
                        success: function (data) {
                            fillTbodyBarangSetengahJadi(data);
                            refreshTableBarangSetengahJadi();
                        }
                    })
                }
            });
        }

        function unbindAndbindBahanBaku() {
            $(".bahan-baku-edit").unbind('click');
            $(".bahan-baku-edit").bind('click', function () {
                var id = $(this).data('id');
                $('#modal-bahan-baku').find(".modal-body").load("form-item-inspeksi?jenisAktiva=${formBean.jenisAktiva}&lpaInspeksiId=${formBean.id}&typeGoods=Raw_Materials&id="+id, function(){
                    initFormBahanBaku();
                    $('#modal-bahan-baku').modal('show');
                });
            });
            $(".bahan-baku-delete").unbind('click');
            $(".bahan-baku-delete").bind('click', function () {
                if (confirm('Anda yakin hapus data')) {
                    var id = $(this).data('id');
                    $.ajax({
                        type: "GET",
                        cache: false,
                        dataType: "html",
                        url: 'delete-barang?id=' + id + '&commonId=${formBean.idLpa}&typeGoods=Raw_Materials',
                        success: function (data) {
                            fillTbodyBahanBaku(data);
                            refreshTableBahanBaku();
                        }
                    })
                }
            });
        }

        function fillTbodyBarangJadi(html) {
            $('#tbody-barang-jadi').html(html);
            if($('#tbody-barang-jadi tr').length >1){
                $('#doks-barang-jadi').removeClass("aktif-link");
            }else{
                $('#doks-barang-jadi').addClass("aktif-link");
            }
            unbindAndbindBarangJadi();
        }
        function fillTbodyBarangSetengahJadi(html) {
            $('#tbody-barang-setengah-jadi').html(html);
            if($('#tbody-barang-setengah-jadi tr').length >1){
                $('#doks-barang-setengah-jadi').removeClass("aktif-link");
            }else{
                $('#doks-barang-setengah-jadi').addClass("aktif-link");
            }
            unbindAndbindBarangSetengahjadi();
        }
        function fillTbodyBahanBaku(html) {
            $('#tbody-bahan-baku').html(html);
            if($('#tbody-bahan-baku tr').length >1){
                $('#doks-bahan-baku').removeClass("aktif-link");
            }else{
                $('#doks-bahan-baku').addClass("aktif-link");
            }
            unbindAndbindBahanBaku();
        }

        refreshTableBarang = function () {
            $.ajax({
                type: "GET",
                cache: false,
                dataType: "html",
                url: 'table-item-inspeksi?idLpa=${formBean.id}&typeGoods=Goods',
                success: function (data) {
                    fillTbodyBarangJadi(data);
                    $('#modal-barang-jadi').modal('hide');
                }
            })
        }
        refreshTableBarangSetengahJadi = function () {
            $.ajax({
                type: "GET",
                cache: false,
                dataType: "html",
                url: 'table-item-inspeksi?idLpa=${formBean.id}&typeGoods=Half_Goods',
                success: function (data) {
                    fillTbodyBarangSetengahJadi(data);
                    $('#modal-barang-setengah-jadi').modal('hide');
                }
            })
        }
        refreshTableBahanBaku = function () {
            $.ajax({
                type: "GET",
                cache: false,
                dataType: "html",
                url: 'table-item-inspeksi?idLpa=${formBean.id}&typeGoods=Raw_Materials',
                success: function (data) {
                    fillTbodyBahanBaku(data);
                    $('#modal-bahan-baku').modal('hide');
                }
            })
        }


        $('#btn-add-image').click(function () {
            $('#modal-image-label').text('Form Lampiran');
            $('#iframe-image').prop('src', "${ctxPath}/app-valuer/form-image?lpaId=${formBean.idLpa}&activaId=${formBean.jenisAktiva}");
            $('#modal-image').modal('show');
        });
        $('#btn-save-all').click(function () {
            $('#draft').val(false);
            $('#form-lpa').attr('action', 'save-lpa');
            $('select').prop('disabled', false);
        })
        // main app thread
        <c:if test="${formBean.id != null}">
        refreshTableBarang();
        refreshTableBarangSetengahJadi();
        refreshTableBahanBaku();
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

        //inii//
        $('#btn-add-barang-jadi').click(function(){
            $('#modal-barang-jadi').find(".modal-body").load("form-item-inspeksi?jenisAktiva=${formBean.jenisAktiva}&lpaInspeksiId=${formBean.id}&typeGoods=Goods", function(){
                initFormBarangJadi();
            });
            return true;
        })
        $('#btn-add-barang-setengah-jadi').click(function(){
            $('#modal-barang-setengah-jadi').find(".modal-body").load("form-item-inspeksi?jenisAktiva=${formBean.jenisAktiva}&lpaInspeksiId=${formBean.id}&typeGoods=Half_Goods", function(){
                initFormBarangSetengahJadi();
            });
            return true;
        })
        $('#btn-add-bahan-baku').click(function(){
            $('#modal-bahan-baku').find(".modal-body").load("form-item-inspeksi?jenisAktiva=${formBean.jenisAktiva}&lpaInspeksiId=${formBean.id}&typeGoods=Raw_Materials", function(){
                initFormBahanBaku();
            });
            return true;
        })
        var initFormBarangJadi = function(){
            $('#form-item-inspeksi').submit(function(){
                var prosesAjax = true;
                if(prosesAjax) {
                    $.ajax({
                        type: $(this).attr('method'),
                        cache: false,
                        dataType: "html",
                        url: $(this).attr('action'),
                        data: $(this).serialize(),
                        complete: function (data) {
                            $('#modal-barang-jadi').find(".modal-body").html(data.responseText);
                            initFormBarangJadi();
                        }
                    })
                }
                return false;
            });
            mask_form();
        }

        var initFormBarangSetengahJadi = function(){
            $('#form-item-inspeksi').submit(function(){
                var prosesAjax = true;
                if(prosesAjax) {
                    $.ajax({
                        type: $(this).attr('method'),
                        cache: false,
                        dataType: "html",
                        url: $(this).attr('action'),
                        data: $(this).serialize(),
                        complete: function (data) {
                            $('#modal-barang-setengah-jadi').find(".modal-body").html(data.responseText);
                            initFormBarangSetengahJadi();
                        }
                    })
                }
                return false;
            });
            mask_form();
        }

        var initFormBahanBaku = function(){
            $('#form-item-inspeksi').submit(function(){
                var prosesAjax = true;
                if(prosesAjax) {
                    $.ajax({
                        type: $(this).attr('method'),
                        cache: false,
                        dataType: "html",
                        url: $(this).attr('action'),
                        data: $(this).serialize(),
                        complete: function (data) {
                            $('#modal-bahan-baku').find(".modal-body").html(data.responseText);
                            initFormBahanBaku();
                        }
                    })
                }
                return false;
            });
            mask_form();
        }


    });

</script>
</body>
</html>