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
    <f:form id="form-lpa" method="post" modelAttribute="formBean" action="draft-lpa" class="form-horizontal">
        <f:hidden path="commonId"/>
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
        <f:hidden path="catatanSurvey"/>
        <f:hidden path="faktorMarketability"/>
        <f:hidden path="ditemui"/>
        <f:hidden path="pembanding3"/>
        <f:hidden path="sla"/>
        <f:hidden path="statusJaminan"/>
        <f:hidden path="idObjectPenilaiFisik"/>
        <f:hidden path="pembanding1"/>
        <f:hidden path="jenisObjectPenilaiFisik"/>
        <f:hidden path="jenisObjectPenilain"/>
        <f:hidden path="ditujuke"/>
        <f:hidden path="pembanding2"/>
        <f:hidden path="branchId"/>
        <f:hidden path="catatanPoint"/>
        <f:hidden path="contactPerson" />
        <div class="page-header-page">
            <div class="absolute-right input-append">
                <c:if test="${formBean.id != null}">
                    <div class="btn-group">
                        <button class="btn btn-info dropdown-toggle" data-toggle="dropdown"><i class="icon-white icon-file"></i>Preview Laporan <span class="caret"></span></button>
                        <ul class="dropdown-menu">

                                <li><a href="laporan-lpa-bv?PIdLpaUmum=${formBean.idLpa}&lpa=lpa-bv" target="_blank">LPA</a></li>

                            <li class="divider"></li>
                            <%--<li><a href="laporan-lpa-bv?PIdLpaUmum=${formBean.idLpa}&lpa=img-survey" target="_blank">Image Hasil Survey</a></li>--%>
                            <li><a href="laporan-lpa-bv?PIdLpaUmum=${formBean.idLpa}&lpa=img-other" target="_blank">Image Dokumen Lain</a></li>
                            <%--<li><a href="#myModalLain" data-toggle="modal">Image Dokumen Lain</a></li>--%>
                        </ul>
                    </div>
                </c:if>
                <c:if test="${showButton}">
                    <%--<button type="submit" id="btn-save-all" class="btn btn-success"><i class="icon-white icon-ok"></i>Save</button>--%>
                    <%--<c:if test="${disableDraft}">--%>
                        <%--<button type="submit" id="btn-save-draft" class="btn btn-info"><i class="icon-white icon-camera"></i> Draft</button>--%>
                    <%--</c:if>--%>
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
            <li class="active"><a data-toggle="tab" href="#menu1">Data Umum <span class="badge badge-warning hide"></span></a></li>
            <li><a data-toggle="tab" href="#menu2">Sumber Informasi <span class="badge badge-warning hide"></span></a></li>
            <li><a data-toggle="tab" href="#menu3">Tempat Usaha <span class="badge badge-warning hide"></span></a></li>
            <li><a data-toggle="tab" href="#menu4">Informasi Tambahan <span class="badge badge-warning hide"></span></a></li>
            <li><a data-toggle="tab" href="#menu5">Data Lampiran <span class="badge badge-warning hide"></span></a></li>
        </ul>
        <div class="tab-content">
            <div id="menu1" class="tab-pane fade in active panel controls-row">
                <div class="control-group span6">
                    <label class="control-label" for="orderNumber">No. Order</label>
                    <div class="controls">
                        <f:input path="orderNumber" type="text" class="form-control" readonly="true"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="lpaNumber">Nomer LPA</label>
                    <div class="controls">
                        <f:input path="lpaNumber" type="text" class="form-control" readonly="true"/>
                        <f:errors path="lpaNumber"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="namaDebitur">Nama Aplikan</label>
                    <div class="controls">
                        <f:input path="namaDebitur" type="text" class="form-control"/>
                    </div>
                </div>
                <div class="control-group span6" style="margin-bottom: 3px;">
                    <label class="control-label" for="lokasiFisik">Alamat Survey</label>
                    <div class="controls">
                        <f:textarea path="lokasiFisik" rows="3" cols="30"></f:textarea>
                        <f:errors path="lokasiFisik" cssClass="help-block"/>

                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="vendor">Nama Vendor</label>
                    <div class="controls">
                        <f:input path="vendor" type="text" class="form-control"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="ditinjau">ID Surveyor</label>
                    <div class="controls">
                        <f:select path="ditinjau">
                            <f:options items="${listSurveyor}" itemValue="label" itemLabel="label"/>
                        </f:select>
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
                    <label class="control-label" for="papanNama">Papan Nama Usaha</label>
                    <div class="controls">
                        <f:radiobutton path="papanNama" value="1" label="Ada"/>
                        <f:radiobutton path="papanNama" value="0" label="Tidak Ada"/>
                        <f:errors path="papanNama" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="kegiatanUsaha">Kegiatan Usaha Saat Survey</label>
                    <div class="controls">
                        <f:select path="kegiatanUsaha">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listKegiatanUsaha}" itemValue="label" itemLabel="label"/>
                        </f:select>
                        <f:errors path="kegiatanUsaha" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span12">&nbsp;</div>
                <div class="control-group span6">
                    <label class="control-label" for="kodePos">Kode Pos</label>
                    <div class="controls">
                        <f:input path="kodePos" readonly="true"/>
                        <f:errors path="kodePos" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="propinsi">Propinsi</label>
                    <div class="controls">
                        <f:input path="propinsi" readonly="true"/>
                        <f:errors path="propinsi" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="kota">Kabupaten/Kotamadya</label>
                    <div class="controls">
                        <f:input path="kota" readonly="true"/>
                        <f:errors path="kota" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="kecamatan">Kecamatan</label>
                    <div class="controls">
                        <f:input path="kecamatan" readonly="true"/>
                        <f:errors path="kecamatan" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="kelurahan">Desa/Kelurahan</label>
                    <div class="controls">
                        <f:input path="kelurahan" readonly="true"/>
                        <f:errors path="kelurahan" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="namaPerumahan">Nama Perumahan/Apartment</label>
                    <div class="controls">
                        <f:input path="namaPerumahan" readonly="true"/>
                        <f:errors path="namaPerumahan" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="namaCluster">Nama Cluster/Tower</label>
                    <div class="controls">
                        <f:input path="namaCluster" readonly="true"/>
                        <f:errors path="namaCluster" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="block">Blok/Gang/Lantai</label>
                    <div class="controls">
                        <f:input path="block" readonly="true"/>
                        <f:errors path="block" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="nomor">Nomor</label>
                    <div class="controls">
                        <f:input path="nomor" readonly="true"/>
                        <f:errors path="nomor" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="rt">RT</label>
                    <div class="controls">
                        <f:input path="rt" readonly="true"/>
                        <f:errors path="rt" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="rw">RW</label>
                    <div class="controls">
                        <f:input path="rw" readonly="true"/>
                        <f:errors path="rw" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span12">&nbsp;</div>
                <div class="control-group span6">
                    <label class="control-label" for="tanggalOrder">Tanggal Order</label>
                    <div class="controls">
                        <f:input path="tanggalOrder" readonly="true"/>
                        <f:errors path="tanggalOrder" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="tanggalSurvey">Tanggal Survey</label>
                    <div class="controls">
                        <f:input path="tanggalSurvey" data-date-format="dd-mm-yyyy"
                                 data-provide="datepicker"  class="tutup-otomatis" placeholder="DD-MM-YYYY" data-date-end-date="0d"/>
                        <f:errors path="tanggalSurvey" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="tanggalTerimaDariEksternal">Tanggal Terima Hasil</label>
                    <div class="controls">
                        <f:input path="tanggalTerimaDariEksternal" data-date-format="dd-mm-yyyy"
                                 data-provide="datepicker"  class="tutup-otomatis" placeholder="DD-MM-YYYY" data-date-end-date="0d"/>
                    </div>
                </div>

                <div class="control-group span12">&nbsp;</div>
                <div class="control-group span6">
                    <label class="control-label" for="mataUang">Mata Uang</label>
                    <div class="controls">
                        <f:select path="mataUang">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${listMataUang}" itemValue="code" itemLabel="code"/>
                        </f:select>
                        <f:errors path="mataUang"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="tanggalRate">Tanggal Rate</label>
                    <div class="controls">
                        <f:input path="tanggalRate" data-date-format="dd-mm-yyyy"
                                 data-provide="datepicker"  class="tutup-otomatis" placeholder="DD-MM-YYYY" data-date-end-date="0d"/>
                        <f:errors path="tanggalRate"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="nilaiRate">Nilai Rate</label>
                    <div class="controls">
                        <f:input path="nilaiRate" data-mask="luas-tanah"/>
                        <f:errors path="nilaiRate"/>
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
                        <f:input path="detailOmzet" cssClass="luas-tanah"/>
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
                        <f:input path="jumlahMotor" data-mask="luas-tanah"/>
                        <f:errors path="jumlahMotor" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="jumlahMobil">Jumlah Mobil</label>
                    <div class="controls">
                        <f:input path="jumlahMobil" data-mask="luas-tanah"/>
                        <f:errors path="jumlahMobil" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="jumlahTruk">Jumlah Truk</label>
                    <div class="controls">
                        <f:input path="jumlahTruk" data-mask="luas-tanah"/>
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
                        <f:textarea path="infoS1" maxlength="1000"/>
                        <f:errors path="infoS1" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="infoS2">Info SI 2</label>
                    <div class="controls">
                        <f:textarea path="infoS2"  maxlength="1000"/>
                        <f:errors path="infoS2" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="infoS3">Info SI 3</label>
                    <div class="controls">
                        <f:textarea path="infoS3"  maxlength="1000"/>
                        <f:errors path="infoS3" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="note">Note</label>
                    <div class="controls">
                        <f:textarea path="note"  maxlength="255"/>
                        <f:errors path="note" cssClass="help-block"/>
                    </div>
                </div>
            </div>
            <div id="menu5" class="tab-pane fade controls-row panel">
                <div class="absolute-right">
                    <%--<c:if test="${formBean.id != null}"><button id="btn-add-image" type="button" class="btn btn-success"><i class="icon-white icon-plus"></i> Add</button></c:if>--%>
                </div>
                <div id="container-image">
                    <c:if test="${formBean.id == null}"><i>Lakukan proses save to draf untuk melihat data</i></c:if>
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
        $.active = false;
        $('body').bind('click keypress', function() { $.active = true; });
        var logout = '${ctxPath}/logoutExpired';
        var heartBeat = '${ctxPath}/heartbeat';
        checkActivity(${maxInterval}, 60000, 0,logout,heartBeat);


        callbackModal = function(){
            refreshTableImage();
            $('#modal-image').modal('hide');
        }
        refreshTableImage = function(){
            $.ajax({
                type: "GET",
                cache: false,
                dataType: "html",
                url: 'table-image-rv-bv?surveyId=${formBean.surveyId}&lpaId=${formBean.idLpa}',
                success: function (html) {
                    fillTbodyImage(html);
                    $('#modal-image').modal('hide');
                    refreshTableImageOther();
                }
            })
        }
        function fillTbodyImage(html){
            $('#container-image').html(html);
            unbindAndbindImage();
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
        function unbindAndbindImage() {
            $(".image-edit").unbind('click');
            $(".image-edit").bind('click', function () {
                var id = $(this).data('id');
                $('#iframe-image').prop('src', "form-image?id="+id+"&lpaId=${formBean.idLpa}&appraisalId=${formBean.idJenisAppraisal}");
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
                        url: 'delete-image?id=' + id + '&surveyId=${formBean.surveyId}&lpaId=${formBean.idLpa}',
                        success: function (data) {
                            fillTbodyImage(data);
                            refreshTableImageOther();
                        }
                    })
                }
            });
            $('.btn-image').click(function(){
                var id = $(this).data('id');
                var keterangan = $(this).data('keterangan');
                $('#iframe-image').prop('src', "form-image?jenisFoto="+keterangan.substr(4, keterangan.length-4)+"&id="+id+"&lpaId=${formBean.idLpa}&appraisalId=${formBean.idJenisAppraisal}");
                $('#modal-image').modal('show');
            })
        }
        $('#btn-save-image').click(function () {
            $.ajax({
                type: "GET",
                cache: false,
                dataType: "html",
                url: 'save-image',
                success: function (html) {
                    fillTbodyImage(html);
                    $('#modal-image').modal('hide');
                }
            })
        })
        $('#btn-add-image').click(function () {
            $('#modal-image-label').text('Form Lampiran');
            $('#iframe-image').prop('src', "form-image?lpaId=${formBean.idLpa}&appraisalId=${formBean.idJenisAppraisal}");
            $('#modal-image').modal('show');
        });
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
        </c:forEach></spring:hasBindErrors>
    });
</script>
</body>
</html>