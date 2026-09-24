<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.permata.appraisal2.model.PersonLevel" %>
<c:set var="taskPosition" value="<%= PersonLevel.APPRAISAL_SURVEYOR %>"/>
<c:set var="showButton" value="${roleActive.level == taskPosition}"/>
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
        .controls label {
            display: inline;
            margin: 0px 5px;
        }
        .controls input[type="radio"] {
            margin: 0;
        }
        .controls span label {
            display: block;
        }
    </style>
</head>
<body>
<div class="container">
    <f:form id="form-lpa" method="post" modelAttribute="formBean" action="save" class="form-horizontal">
        <f:hidden path="id"/>
        <f:hidden path="detailId"/>
        <f:hidden path="commonId"/>
        <c:if test="${showButton}">
            <div class="page-header-page">
                <div class="absolute-right input-append">
                    <c:if test="${showButton}">
                        <button type="submit" class="btn btn-success"><i class="icon-white icon-ok"></i>Save</button>
                        <button type="reset" class="btn btn-info"><i class="icon-white icon-refresh"></i> Reset</button>
                        <button type="button" onclick="gotoUrl('${ctxPath}/app-surveyor/view?id=${formBean.dataCommonId}');" class="btn btn-warning"><i class="icon-white icon-arrow-left"></i> Back</button>
                    </c:if>
                    <c:if test="${!showButton}">
                        <button type="button" onclick="gotoUrl('${ctxPath}/app-surveyor/view?id=${formBean.dataCommonId}');" class="btn btn-warning"><i class="icon-white icon-arrow-left"></i> Back</button>
                    </c:if>
                </div>
                <h1>Data Survey BV</h1>
            </div>
            <div class="clearfix" style="margin-top:60px"></div>
        </c:if>
        <c:if test="${not empty msg}">
            <div class="alert alert-success">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>${msg}</strong>
            </div>
        </c:if>
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#menu1">Data Umum <span class="badge badge-warning hide"></span></a></li>
            <li><a data-toggle="tab" href="#menu2">Sumber Informasi <span class="badge badge-warning hide"></span></a></li>
            <li><a data-toggle="tab" href="#menu3">Tempat Usaha <span class="badge badge-warning hide"></span></a></li>
            <li><a data-toggle="tab" href="#menu4">Informasi Tambahan <span class="badge badge-warning hide"></span></a></li>
            <li><a data-toggle="tab" href="#menu5">Foto Survey <span class="badge badge-warning hide"></span></a></li>
        </ul>
        <div class="tab-content">
            <div id="menu1" class="tab-pane fade in active panel controls-row">
                <div class="control-group span6" style="margin-bottom: 3px;">
                    <label class="control-label" for="alamat">Alamat Survey</label>
                    <div class="controls">
                        <f:textarea path="alamat" rows="3" cols="30"></f:textarea>
                        <f:errors path="alamat" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="vendor">Nama Vendor</label>
                    <div class="controls">
                        <f:input path="vendor" type="text" class="form-control"/>
                        <f:errors path="vendor" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="ditinjau">ID Surveyor</label>
                    <div class="controls">
                        <f:input path="ditinjau" readonly="true"/>
                        <f:errors path="ditinjau" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="sudahPindah">Aplikan Pindah Rumah</label>
                    <div class="controls">
                        <f:radiobutton path="sudahPindah" value="1" label="Ya"/>
                        <f:radiobutton path="sudahPindah" value="0" label="Tidak"/>
                        <f:errors path="sudahPindah" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="lokasiDitemukan">Lokasi Objek Survey</label>
                    <div class="controls">
                        <f:radiobutton path="lokasiDitemukan" value="1" label="Ditemukan"/>
                        <f:radiobutton path="lokasiDitemukan" value="0" label="Tidak Ditemukan"/>
                        <f:errors path="lokasiDitemukan" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="tanggalSurvey">Tanggal Survey</label>
                    <div class="controls">
                        <f:input path="tanggalSurvey" cssClass="input-small" data-provide="datepicker"  class="tutup-otomatis" data-date-end-date="0d" placeholder="DD-MM-YYYY" data-date-format="dd-mm-yyyy"/>
                        <f:errors path="tanggalSurvey" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="jam">Jam</label>
                    <div class="controls">
                        <f:input path="jam"/>
                        <f:errors path="jam" cssClass="help-block"/>
                    </div>
                </div>
            </div>
            <div id="menu2" class="tab-pane fade controls-row panel">
                <div class="page-header"><h4>Sumber Informasi (SI) 1</h4></div>
                <div class="control-group">
                    <label class="control-label" for="s1Ditemukan">Ditemukan</label>
                    <div class="controls">
                        <f:radiobutton path="s1Ditemukan" value="1" label="Ditemukan"/>
                        <f:radiobutton path="s1Ditemukan" value="0" label="Tidak Ditemukan"/>
                        <f:errors path="s1Ditemukan" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="s1Nama">Nama</label>
                    <div class="controls">
                        <f:input path="s1Nama"/>
                        <f:errors path="s1Nama" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="s1Kelamin">Jenis Kelamin</label>
                    <div class="controls">
                        <f:radiobutton path="s1Kelamin" value="L" label="Pria"/>
                        <f:radiobutton path="s1Kelamin" value="W" label="Wanita"/>
                        <f:errors path="s1Kelamin" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="s1Hubungan">Hubungan Dengan Aplikan</label>
                    <div class="controls">
                        <f:select path="s1Hubungan">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listHubungan1DenganAplikan}" itemValue="label" itemLabel="label"/>
                        </f:select>
                        <f:errors path="s1Hubungan" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="s1Kooperatif">Kooperatif</label>
                    <div class="controls">
                        <f:radiobutton path="s1Kooperatif" value="1" label="Ya"/>
                        <f:radiobutton path="s1Kooperatif" value="0" label="Tidak"/>
                        <f:errors path="s1Kooperatif" cssClass="help-block"/>
                    </div>
                </div>
                <div class="page-header" style="clear: both"><h4>Sumber Informasi (SI) 2</h4></div>
                <div class="control-group">
                    <label class="control-label" for="s2Ditemukan">Ditemukan</label>
                    <div class="controls">
                        <f:radiobutton path="s2Ditemukan" value="1" label="Ditemukan"/>
                        <f:radiobutton path="s2Ditemukan" value="0" label="Tidak Ditemukan"/>
                        <f:errors path="s2Ditemukan" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="s2Nama">Nama</label>
                    <div class="controls">
                        <f:input path="s2Nama"/>
                        <f:errors path="s2Nama" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="s2Kelamin">Jenis Kelamin</label>
                    <div class="controls">
                        <f:radiobutton path="s2Kelamin" value="L" label="Pria"/>
                        <f:radiobutton path="s2Kelamin" value="W" label="Wanita"/>
                        <f:errors path="s2Kelamin" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="s2Kenal">Kenal Dengan Aplikan?</label>
                    <div class="controls">
                        <f:radiobutton path="s2Kenal" value="1" label="Ya"/>
                        <f:radiobutton path="s2Kenal" value="0" label="Tidak"/>
                        <f:errors path="s2Kenal" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="s2Hubungan">Hubungan Dengan Aplikan</label>
                    <div class="controls">
                        <f:select path="s2Hubungan">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listHubungan2DenganAplikan}" itemValue="label" itemLabel="label"/>
                        </f:select>
                        <f:errors path="s2Hubungan" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="s2Kooperatif">Kooperatif</label>
                    <div class="controls">
                        <f:radiobutton path="s2Kooperatif" value="1" label="Ya"/>
                        <f:radiobutton path="s2Kooperatif" value="0" label="Tidak"/>
                        <f:errors path="s2Kooperatif" cssClass="help-block"/>
                    </div>
                </div>
                <div class="page-header" style="clear: both"><h4>Sumber Informasi (SI) 3</h4></div>
                <div class="control-group">
                    <label class="control-label" for="s3Ditemukan">Ditemukan</label>
                    <div class="controls">
                        <f:radiobutton path="s3Ditemukan" value="1" label="Ditemukan"/>
                        <f:radiobutton path="s3Ditemukan" value="0" label="Tidak Ditemukan"/>
                        <f:errors path="s3Ditemukan" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="s3Nama">Nama</label>
                    <div class="controls">
                        <f:input path="s3Nama"/>
                        <f:errors path="s3Nama" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="s3Kelamin">Jenis Kelamin</label>
                    <div class="controls">
                        <f:radiobutton path="s3Kelamin" value="L" label="Pria"/>
                        <f:radiobutton path="s3Kelamin" value="W" label="Wanita"/>
                        <f:errors path="s3Kelamin" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="s3Kenal">Kenal Dengan Aplikan?</label>
                    <div class="controls">
                        <f:radiobutton path="s3Kenal" value="1" label="Ya"/>
                        <f:radiobutton path="s3Kenal" value="0" label="Tidak"/>
                        <f:errors path="s3Kenal" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="s3Hubungan">Hubungan Dengan Aplikan</label>
                    <div class="controls">
                        <f:select path="s3Hubungan">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listHubungan3DenganAplikan}" itemValue="label" itemLabel="label"/>
                        </f:select>
                        <f:errors path="s3Hubungan" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="s3Kooperatif">Kooperatif</label>
                    <div class="controls">
                        <f:radiobutton path="s3Kooperatif" value="1" label="Ya"/>
                        <f:radiobutton path="s3Kooperatif" value="0" label="Tidak"/>
                        <f:errors path="s3Kooperatif" cssClass="help-block"/>
                    </div>
                </div>
            </div>
            <div id="menu3" class="tab-pane fade controls-row panel">
                <div class="control-group">
                    <label class="control-label" for="surveyorDiterimaDi">Surveyor Diterima di</label>
                    <div class="controls">
                        <f:select path="surveyorDiterimaDi">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listDiterimaDi}" itemValue="label" itemLabel="label"/>
                        </f:select>
                        <f:errors path="surveyorDiterimaDi" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="jumlahKaryawan">Jumlah Karyawan</label>
                    <div class="controls">
                        <f:select path="jumlahKaryawan">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listJumlahKaryawan}" itemValue="label" itemLabel="label"/>
                        </f:select>
                        <f:errors path="jumlahKaryawan" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="omzetPenjualan">Omzet Penjualan Perbulan</label>
                    <div class="controls">
                        <f:select path="omzetPenjualan">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listOmzetPenjualan}" itemValue="label" itemLabel="label"/>
                        </f:select>
                        <f:errors path="omzetPenjualan" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="detailOmzet">Detail Omzet Perbulan</label>
                    <div class="controls">
                        <f:input path="detailOmzet" cssClass="input-small" data-mask="luas-tanah"/>
                        <f:errors path="detailOmzet" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="kondisiUsaha">Kondisi Tempat Usaha</label>
                    <div class="controls">
                        <f:radiobutton path="kondisiUsaha" value="1" label="Terawat"/>
                        <f:radiobutton path="kondisiUsaha" value="0" label="Tidak Terawat"/>
                        <f:errors path="kondisiUsaha" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="jenisUsaha">Jenis Usaha</label>
                    <div class="controls">
                        <f:select path="jenisUsaha">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listJenisUsaha}" itemValue="label" itemLabel="label"/>
                        </f:select>
                        <f:errors path="jenisUsaha" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="bidangUsaha">Bidang Usaha</label>
                    <div class="controls">
                        <f:input path="bidangUsaha"/>
                        <f:errors path="bidangUsaha" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="lamaPenempatan">Lama Penempatan</label>
                    <div class="controls">
                        <f:select path="lamaPenempatan">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listLamaPenempatan}" itemValue="label" itemLabel="label"/>
                        </f:select>
                        <f:errors path="lamaPenempatan" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="lingkunganSekitar">Lingkungan Sekitar Tempat Usaha</label>
                    <div class="controls">
                        <f:select path="lingkunganSekitar">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listLingkunganSekitar}" itemValue="label" itemLabel="label"/>
                        </f:select>
                        <f:errors path="lingkunganSekitar" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="jalanTempatUsaha">Kondisi Jalan Tempat Usaha</label>
                    <div class="controls">
                        <f:radiobutton path="masukMobil" value="1" label="Masuk Mobil"/>
                        <f:radiobutton path="masukMobil" value="0" label="Tidak Masuk Mobil"/>
                        <f:select path="jalanTempatUsaha">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listJalanTempatUsaha}" itemValue="label" itemLabel="label"/>
                        </f:select>
                        <f:errors path="jalanTempatUsaha" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="bertingkat">Kondisi Bangunan</label>
                    <div class="controls">
                        <f:radiobutton path="bertingkat" value="1" label="Bertingkat"/>
                        <f:radiobutton path="bertingkat" value="0" label="Tidak Bertingkat"/>
                        <f:errors path="bertingkat" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="luasBangunan">Luas Bangunan</label>
                    <div class="controls">
                        <f:select path="luasBangunan">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listLuasBangunan}" itemValue="label" itemLabel="label"/>
                        </f:select>
                        <f:errors path="luasBangunan" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="luasTanah">Luas Tanah</label>
                    <div class="controls">
                        <f:select path="luasTanah">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listLuasTanah}" itemValue="label" itemLabel="label"/>
                        </f:select>
                        <f:errors path="luasTanah" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="jenisBangunan">Jenis Bangunan</label>
                    <div class="controls">
                        <f:select path="jenisBangunan">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listJenisBangunan}" itemValue="label" itemLabel="label"/>
                        </f:select>
                        <f:errors path="jenisBangunan" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="fasilitas">Fasilitas</label>
                    <div class="controls">
                        <f:checkboxes path="fasilitas" items="${listFasilitas}" itemValue="label" itemLabel="label"/>
                        <f:errors path="fasilitas" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="jumlahMotor">Jumlah Motor</label>
                    <div class="controls">
                        <f:input path="jumlahMotor" data-mask="luas-tanah" cssClass="input-mini"/>
                        <f:errors path="jumlahMotor" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="jumlahMobil">Jumlah Mobil</label>
                    <div class="controls">
                        <f:input path="jumlahMobil" data-mask="luas-tanah" cssClass="input-mini"/>
                        <f:errors path="jumlahMobil" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="jumlahTruk">Jumlah Truk</label>
                    <div class="controls">
                        <f:input path="jumlahTruk" data-mask="luas-tanah" cssClass="input-mini"/>
                        <f:errors path="jumlahTruk" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="statusUsaha">Status Kepemilikan Usaha</label>
                    <div class="controls">
                        <f:select path="statusUsaha">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listStatusUsaha}" itemValue="label" itemLabel="label"/>
                        </f:select>
                        <f:errors path="statusUsaha" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="statusLokasi">Status Kepemilikan Lokasi Usaha</label>
                    <div class="controls">
                        <f:select path="statusLokasi">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listStatusLokasi}" itemValue="label" itemLabel="label"/>
                        </f:select>
                        <f:errors path="statusLokasi" cssClass="help-block"/>
                    </div>
                </div>
            </div>
            <div id="menu4" class="tab-pane fade controls-row panel">
                <div class="control-group">
                    <label class="control-label" for="fotoPada">Foto Dilakukan Pada</label>
                    <div class="controls">
                        <f:checkboxes path="fotoPada" items="${listFotoPada}" itemValue="label" itemLabel="label"/>
                        <f:errors path="fotoPada" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="infoS1">Info SI 1</label>
                    <div class="controls">
                        <f:input path="infoS1"/>
                        <f:errors path="infoS1" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="infoS2">Info SI 2</label>
                    <div class="controls">
                        <f:input path="infoS2"/>
                        <f:errors path="infoS2" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="infoS3">Info SI 3</label>
                    <div class="controls">
                        <f:input path="infoS3"/>
                        <f:errors path="infoS3" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="note">Note</label>
                    <div class="controls">
                        <f:input path="note"/>
                        <f:errors path="note" cssClass="help-block"/>
                    </div>
                </div>
            </div>
            <div id="menu5" class="tab-pane fade controls-row panel">
                <div id="container-image">
                    <c:if test="${formBean.id == null}">
                        <div class="control-group">
                            <i>Lakukan proses save terlebih dahulu untuk melakukan proses upload Foto Survey</i>
                        </div>
                    </c:if>
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
    <div class="modal-body" style="height: 200px;">
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
    $(document).ready(function () {
        callbackModal = function(){
            refreshTableImage();
            $('#modal-image').modal('hide');
        }
        refreshTableImage = function(){
            $.ajax({
                type: "GET",
                cache: false,
                dataType: "html",
                url: '${ctxPath}/survey-bv/table-image?surveyId=${formBean.commonId}',
                success: function (html) {
                    fillTbodyImage(html);
                    $('#modal-image').modal('hide');
                }
            })
        }
        function fillTbodyImage(html){
            $('#container-image').html(html);
            unbindAndbindImage();
        }
        function unbindAndbindImage() {
            $(".image-delete").unbind('click');
            $(".image-delete").bind('click', function () {
                if (confirm('Anda yakin hapus data')) {
                    var id = $(this).data('id');
                    $.ajax({
                        type: "GET",
                        cache: false,
                        dataType: "html",
                        url: '${ctxPath}/survey-bv/delete-image?id=' + id + '&surveyId=${formBean.commonId}',
                        success: function (data) {
                            fillTbodyImage(data);
                        }
                    })
                }
            });
            $('.btn-image').click(function(){
                var id = $(this).data('id');
                var keterangan = $(this).data('keterangan');
                $('#iframe-image').prop('src', "${ctxPath}/survey-bv/form-image?jenisFoto="+keterangan.substr(4, keterangan.length-4)+"&id="+id+"&detailId=${formBean.detailId}");
                $('#modal-image').modal('show');
            })
        }

        // main app thread
        <c:if test="${formBean.id != null}">
        refreshTableImage();
        </c:if>

    });
</script>
</body>
</html>