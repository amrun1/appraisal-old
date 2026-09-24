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
        <div class="page-header-page">
            <div class="absolute-right input-append">
                <c:if test="${formBean.id != null}">
                    <div class="btn-group">
                        <button class="btn btn-info dropdown-toggle" data-toggle="dropdown"><i class="icon-white icon-file"></i>Preview Laporan <span class="caret"></span></button>
                        <ul class="dropdown-menu">
                            <c:if test="${isEksternal}">
                                <li><a href="laporan-lpa-kapal?PIdLpaUmum=${formBean.idLpa}&lpa=lpa-ext" target="_blank">LPA</a></li>
                            </c:if>
                            <c:if test="${!isEksternal}">
                                <li><a href="laporan-lpa-kapal?PIdLpaUmum=${formBean.idLpa}&lpa=lpa-int" target="_blank">LPA</a></li>
                            </c:if>
                            <li class="divider"></li>
                            <li><a href="laporan-lpa-kapal?PIdLpaUmum=${formBean.idLpa}&lpa=img-survey" target="_blank">Image Hasil Survey</a></li>
                            <li><a href="laporan-lpa-kapal?PIdLpaUmum=${formBean.idLpa}&lpa=img-peta" target="_blank">Image Peta Wilayah / Lokasi</a></li>
                            <li><a href="#myModalLain" data-toggle="modal">Image Dokumen Lain</a></li>
                        </ul>
                    </div>
                </c:if>
                <c:if test="${showButton}">
                    <button type="submit" id="btn-save-all" class="btn btn-success"><i class="icon-white icon-ok"></i>Save</button>
                    <button type="submit" id="btn-save-draft" class="btn btn-info"><i class="icon-white icon-camera"></i> Draf</button>
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
            <li><a data-toggle="tab" href="#menu2">Data Kapal <span class="badge badge-warning hide"></span></a></li>
            <li><a data-toggle="tab" href="#menu6">Data Legalitas <span class="badge badge-warning hide"></span></a></li>
            <li><a data-toggle="tab" href="#menu7">Data Mesin <span class="badge badge-warning hide"></span></a></li>
            <c:if test="${isEksternal}">
                <li><a data-toggle="tab" href="#menu10">Nilai Agunan (Eksternal) <span class="badge badge-warning hide"></span></a></li>
                <li><a data-toggle="tab" href="#menu3">Nilai Agunan (Internal) <span class="badge badge-warning hide"></span></a></li>
            </c:if>
            <c:if test="${!isEksternal}">
                <li><a data-toggle="tab" href="#menu3">Nilai Agunan <span class="badge badge-warning hide"></span></a></li>
            </c:if>
            <li><a data-toggle="tab" href="#menu4">Faktor Marketability & Catatan<span class="badge badge-warning hide"></span></a></li>
            <li><a data-toggle="tab" href="#menu5">Negative List Appraisal <span class="badge badge-warning hide"></span></a></li>
            <c:if test="${isEksternal}">
                <li><a data-toggle="tab" id="xx" href="#menu8">Opini Internal <span class="badge badge-warning hide"></span></a></li>
            </c:if>
            <li><a data-toggle="tab" href="#menu9">Data Lampiran <span class="badge badge-warning hide"></span></a></li>
        </ul>
        <div class="tab-content">
            <div id="menu1" class="tab-pane fade in active panel controls-row">
                <jsp:include page="../lpa/form-umum.jsp"/>
            </div>
            <div id="menu4" class="tab-pane fade controls-row panel">
                <jsp:include page="../lpa/form-market.jsp"/>
            </div>
            <div id="menu5" class="tab-pane fade controls-row panel">
                <jsp:include page="../lpa/form-pertimbangan-dan-negative.jsp"/>
            </div>
            <div id="menu6" class="tab-pane fade controls-row panel">
                <div class="control-group span6">
                    <label class="control-label" for="jenis">Jenis Kapal</label>
                    <div class="controls">
                        <f:select path="jenis">
                            <f:option value="">-- Select --</f:option>
                            <f:options items="${listJenisKapal}" itemLabel="label" itemValue="value"/>
                        </f:select>
                        <f:errors path="jenis" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6" style="clear: both">
                    <label class="control-label" for="noGrossAkta">No. Gross Akta</label>
                    <div class="controls">
                        <f:input path="noGrossAkta"/>
                        <f:errors path="noGrossAkta" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="tanggalGrossAkta">Tgl. Gross Akta</label>
                    <div class="controls">
                        <f:input path="tanggalGrossAkta" data-provide="datepicker"  class="tutup-otomatis" data-date-end-date="0d" placeholder="DD-MM-YYYY" data-date-format="dd-mm-yyyy"/>
                        <f:errors path="tanggalGrossAkta" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6" style="clear: both">
                    <label class="control-label" for="noSuratUkur">No. Surat Ukur</label>
                    <div class="controls">
                        <f:input path="noSuratUkur"/>
                        <f:errors path="noSuratUkur" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="tanggalSuratUkur">Tgl. Surat Ukur</label>
                    <div class="controls">
                        <f:input path="tanggalSuratUkur" data-provide="datepicker"  class="tutup-otomatis" data-date-end-date="0d" placeholder="DD-MM-YYYY" data-date-format="dd-mm-yyyy"/>
                        <f:errors path="tanggalSuratUkur" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6" style="clear: both">
                    <label class="control-label" for="noSertifikatLambung">No. Sertifikat Lambung</label>
                    <div class="controls">
                        <f:input path="noSertifikatLambung"/>
                        <f:errors path="noSertifikatLambung" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="tanggalSertifikatLambung">Tgl. Sertifikat Lambung</label>
                    <div class="controls">
                        <f:input path="tanggalSertifikatLambung" data-provide="datepicker"  class="tutup-otomatis" data-date-end-date="0d" placeholder="DD-MM-YYYY" data-date-format="dd-mm-yyyy"/>
                        <f:errors path="tanggalSertifikatLambung" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="expiredSertifikatLambung">Expired Sertifikat Lambung</label>
                    <div class="controls">
                        <f:input path="expiredSertifikatLambung" data-provide="datepicker"  class="tutup-otomatis"  placeholder="DD-MM-YYYY" data-date-format="dd-mm-yyyy"/>
                        <f:errors path="expiredSertifikatLambung" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6" style="clear: both">
                    <label class="control-label" for="noSertifikatGaris">No. Sertifikat Garis</label>
                    <div class="controls">
                        <f:input path="noSertifikatGaris"/>
                        <f:errors path="noSertifikatGaris" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="tanggalSertifikatGaris">Tgl. Sertifikat Garis</label>
                    <div class="controls">
                        <f:input path="tanggalSertifikatGaris" data-provide="datepicker"  class="tutup-otomatis" data-date-end-date="0d" placeholder="DD-MM-YYYY" data-date-format="dd-mm-yyyy"/>
                        <f:errors path="tanggalSertifikatGaris" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6" style="clear: both">
                    <label class="control-label" for="noSertifikatMesin">No. Sertifikat Mesin</label>
                    <div class="controls">
                        <f:input path="noSertifikatMesin"/>
                        <f:errors path="noSertifikatMesin" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="tanggalSertifikatMesin">Tgl. Sertifikat Mesin</label>
                    <div class="controls">
                        <f:input path="tanggalSertifikatMesin" data-provide="datepicker"  class="tutup-otomatis" placeholder="DD-MM-YYYY" data-date-format="dd-mm-yyyy"/>
                        <f:errors path="tanggalSertifikatMesin" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="expiredSertifikatMesin">Expired Sertifikat Mesin</label>
                    <div class="controls">
                        <f:input path="expiredSertifikatMesin" data-provide="datepicker"  class="tutup-otomatis"  placeholder="DD-MM-YYYY" data-date-format="dd-mm-yyyy"/>
                        <f:errors path="expiredSertifikatMesin" cssClass="help-block"/>
                    </div>
                </div>
            </div>
            <div id="menu7" class="tab-pane fade controls-row panel">
                <div class="control-group span6">
                    <label class="control-label" for="mesinMerk1">Merk Mesin 1</label>
                    <div class="controls">
                        <f:input path="mesinMerk1"/>
                        <f:errors path="mesinMerk1" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="mesinNomor1">No. Mesin 1</label>
                    <div class="controls">
                        <f:input path="mesinNomor1"/>
                        <f:errors path="mesinNomor1" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="mesinKapasitas1">Kapasitas Mesin 1</label>
                    <div class="controls">
                        <f:input path="mesinKapasitas1"/>
                        <f:errors path="mesinKapasitas1" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6" style="clear: both">
                    <label class="control-label" for="mesinMerk2">Merk Mesin 2</label>
                    <div class="controls">
                        <f:input path="mesinMerk2"/>
                        <f:errors path="mesinMerk2" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="mesinNomor2">No. Mesin 2</label>
                    <div class="controls">
                        <f:input path="mesinNomor2"/>
                        <f:errors path="mesinNomor2" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="mesinKapasitas2">Kapasitas Mesin 2</label>
                    <div class="controls">
                        <f:input path="mesinKapasitas2"/>
                        <f:errors path="mesinKapasitas2" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6" style="clear: both">
                    <label class="control-label" for="mesinMerk3">Merk Mesin 3</label>
                    <div class="controls">
                        <f:input path="mesinMerk3"/>
                        <f:errors path="mesinMerk3" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="mesinNomor3">No. Mesin 3</label>
                    <div class="controls">
                        <f:input path="mesinNomor3"/>
                        <f:errors path="mesinNomor3" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6" style="margin-bottom: 20px;">
                    <label class="control-label" for="mesinKapasitas3">Kapasitas Mesin 3</label>
                    <div class="controls">
                        <f:input path="mesinKapasitas3"/>
                        <f:errors path="mesinKapasitas3" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6" style="clear: both">
                    <label class="control-label" for="mesinBackupMerk1">Merk Backup Mesin 1</label>
                    <div class="controls">
                        <f:input path="mesinBackupMerk1"/>
                        <f:errors path="mesinBackupMerk1" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="mesinBackupNomor1">No. Backup Mesin 1</label>
                    <div class="controls">
                        <f:input path="mesinBackupNomor1"/>
                        <f:errors path="mesinBackupNomor1" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="mesinBackupKapasitas1">Kapasitas Backup Mesin 1</label>
                    <div class="controls">
                        <f:input path="mesinBackupKapasitas1"/>
                        <f:errors path="mesinBackupKapasitas1" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6" style="clear: both">
                    <label class="control-label" for="mesinBackupMerk2">Merk Backup Mesin 2</label>
                    <div class="controls">
                        <f:input path="mesinBackupMerk2"/>
                        <f:errors path="mesinBackupMerk2" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="mesinBackupNomor2">No. Backup Mesin 2</label>
                    <div class="controls">
                        <f:input path="mesinBackupNomor2"/>
                        <f:errors path="mesinBackupNomor2" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="mesinBackupKapasitas2">Kapasitas Backup Mesin 2</label>
                    <div class="controls">
                        <f:input path="mesinBackupKapasitas2"/>
                        <f:errors path="mesinBackupKapasitas2" cssClass="help-block"/>
                    </div>
                </div>
            </div>
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
                <c:if test="${showButton}" >
                <div class="absolute-right">
                    <c:if test="${formBean.id != null}"><button id="btn-add-image" type="button" class="btn btn-success"><i class="icon-white icon-plus"></i> Add</button></c:if>
                </div>
                </c:if>
                <table class="table dataTable" style="margin-top:28px;">
                    <thead><tr><th width="100px">#</th><th>Kategori</th><th>Catatan</th></tr></thead>
                    <tbody id="tbody-image"></tbody>
                </table>
            </div>
            <div id="menu2" class="tab-pane fade controls-row panel">
                <div class="control-group span6">
                    <label class="control-label" for="nama">Nama Kapal</label>
                    <div class="controls">
                        <f:input path="nama"/>
                        <f:errors path="nama" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="pemilik">Nama Pemilik</label>
                    <div class="controls">
                        <f:input path="pemilik"/>
                        <f:errors path="pemilik" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="kebangsaan">Kebangsaan</label>
                    <div class="controls">
                        <f:input path="kebangsaan"/>
                        <f:errors path="kebangsaan" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="selar">Selar Kapal</label>
                    <div class="controls">
                        <f:input path="selar"/>
                        <f:errors path="selar" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="galangan">Galangan Kapal</label>
                    <div class="controls">
                        <f:input path="galangan"/>
                        <f:errors path="galangan" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="kelas">Kelas Kapal</label>
                    <div class="controls">
                        <f:select path="kelas">
                            <f:option value="">-- Select --</f:option>
                            <f:options items="${listKelasKapal}" itemLabel="label" itemValue="value"/>
                        </f:select>
                        <f:errors path="kelas" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="tahun">Tahun Pembuatan</label>
                    <div class="controls">
                        <f:input path="tahun" cssClass="input-mini" data-mask="number" maxlength="4"/>
                        <f:errors path="tahun" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="manufaktur">Manufaktur</label>
                    <div class="controls">
                        <f:input path="manufaktur"/>
                        <f:errors path="manufaktur" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="panjang">Panjang</label>
                    <div class="controls">
                        <f:input path="panjang" cssClass="input-mini" data-mask="luas-tanah"/>
                        <f:errors path="panjang" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="lebar">Lebar</label>
                    <div class="controls">
                        <f:input path="lebar" cssClass="input-mini" data-mask="luas-tanah"/>
                        <f:errors path="lebar" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="tinggi">Tinggi</label>
                    <div class="controls">
                        <f:input path="tinggi" cssClass="input-mini" data-mask="luas-tanah"/>
                        <f:errors path="tinggi" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="isiKotor">Berat Isi Kotor</label>
                    <div class="controls">
                        <f:input path="isiKotor" cssClass="input-mini" data-mask="luas-tanah"/>
                        <f:errors path="isiKotor" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="isiBersih">Berat Isi Bersih</label>
                    <div class="controls">
                        <f:input path="isiBersih" cssClass="input-mini" data-mask="luas-tanah"/>
                        <f:errors path="isiBersih" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="statusAgunan">Status Agunan Kapal</label>
                    <div class="controls">
                        <f:select path="statusAgunan">
                            <f:option value="">-- Select --</f:option>
                            <f:options items="${listStatusAgunanKapal}" itemLabel="label" itemValue="value"/>
                        </f:select>
                        <f:errors path="statusAgunan" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="statusJaminanKapal">Status Jaminan Kapal</label>
                    <div class="controls">
                        <f:select path="statusJaminanKapal">
                            <f:option value="">-- Select --</f:option>
                            <f:options items="${listStatusJaminanKapal}" itemLabel="label" itemValue="value"/>
                        </f:select>
                        <f:errors path="statusJaminanKapal" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="kondisiUmum">Kondisi Umum</label>
                    <div class="controls">
                        <f:select path="kondisiUmum">
                            <f:option value="">-- Select --</f:option>
                            <f:options items="${listKondisiUmum}" itemLabel="label" itemValue="value"/>
                        </f:select>
                        <f:errors path="kondisiUmum" cssClass="help-block"/>
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
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">�</button>
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
        $('#form-lpa').find('input, textarea, select').prop('disabled', true);
        $.active = false;
        $('body').bind('click keypress', function() { $.active = true; });
        var logout = '${ctxPath}/logoutExpired';
        var heartBeat = '${ctxPath}/heartbeat';
        checkActivity(${maxInterval}, 60000, 0,logout,heartBeat);
        callbackWorksheet = function(data){

            $('.nav-tabs a[href="#menu3"]').tab('show');

            $('#nilaiDocPasar').val(data.nilaiUnitSesuaiDok);
            $('#nilaiDocPasar').removeProp("readonly");

            $('#nilaiFisikPasar').val(data.nilaiUnitSesuaiFisik);
            $('#nilaiFisikPasar').removeProp("readonly");

            $('#nilaiDocLikuidasi').val(data.nilaiLikuidasiSesuaiDok);
            $('#nilaiDocLikuidasi').removeProp("readonly");

            $('#nilaiFisikLikuidasi').val(data.nilaiLikuidasiSesuaiFisik);
            $('#nilaiFisikLikuidasi').removeProp("readonly");


        }



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
                url: '${ctxPath}/app-approval/table-image?surveyId=${formBean.surveyId}&lpaId=${formBean.idLpa}',
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
                url: '${ctxPath}/app-approval/table-image-other?lpaId=${formBean.idLpa}',
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
                url: 'show-lampiran?idLpaMesin=${formBean.idLpa}',
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
                    $('#nilaiFisikPasar').val(parseToFloat($('#t_totalPasar').inputmask('unmaskedvalue')));
                    $('#nilaiDocPasar').val(parseToFloat($('#t_totalDokumen').inputmask('unmaskedvalue')));
                    $('#modal-image').modal('hide');
                    $('#nilaiDocPasar').trigger('change');
                }
            });
        }
        function fillTbodyImage(html){
            $('#tbody-image').html(html);
            unbindAndbindImage();
        }
        function unbindAndbindImage() {
            $(".image-edit").unbind('click');
            $(".image-edit").bind('click', function () {
                var id = $(this).data('id');
                $('#iframe-image').prop('src', "${ctxPath}/app-approval/form-image?id="+id+"&lpaId=${formBean.idLpa}&activaId=${formBean.jenisAktiva}");
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
                        url: '${ctxPath}/app-approval/delete-image?id=' + id + '&surveyId=${formBean.surveyId}&lpaId=${formBean.idLpa}',
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
                $('#iframe-image').prop('src', "${ctxPath}/lpa-mesin/form-lampiran?lpaId=${formBean.idLpa}&id="+id);
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
                        url: 'delete-kendaraan?id=' + id + '&idLpa=${formBean.idLpa}',
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
                $('#nilaiDocLikuidasi').prop('readonly', false);
                $('#nilaiFisikLikuidasi').prop('readonly', false);
            }
        }
        function calcEks(){
            var hitungManual = $('input[name=manualEks]').prop("checked");
            if (!hitungManual) {
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

        $('#btn-add-image').click(function () {
            $('#modal-image-label').text('Form Lampiran');
            $('#iframe-image').prop('src', "${ctxPath}/app-approval/form-image?lpaId=${formBean.idLpa}&activaId=${formBean.jenisAktiva}");
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

        $('#nilaiDocPasar').trigger('change');

        $('#tanggalSertifikatGaris').datepicker({
            orientation : "bottom auto"
        });

        $('#expiredSertifikatLambung').datepicker({
            orientation : "bottom auto"
        });

    });
</script>
</body>
</html>