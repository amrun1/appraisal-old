<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.permata.appraisal2.model.PersonLevel" %>
<c:set var="isDesktop" value="${fn:containsIgnoreCase(formBean.jenisAppraisal, 'desktop')}"/>
<c:set var="taskPosition" value="<%= PersonLevel.APPRAISAL_VALUER %>"/>
<c:set var="showButton" value="${roleActive.level == taskPosition}"/>
<c:set var="isEksternal" value="${fn:containsIgnoreCase(formBean.kategoriJenisAppraisal, 'eksternal')}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<title>Appraisal</title>
<meta http-equiv="Expires" content="-1"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link href="${ctxPath}/js/bootstrap_2.3.2/css/bootstrap.css" rel="stylesheet">
<link href="${ctxPath}/js/bootstrap-datepicker-1.6.4/css/bootstrap-datepicker.min.css" rel="stylesheet">
<link href="${ctxPath}/css/main-app.css" rel="stylesheet">
    <style>
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
            <%--<c:if test="${showButton}">--%>
                <c:if test="${formBean.id != null}">
                <div class="btn-group">
                    <button class="btn btn-info dropdown-toggle" data-toggle="dropdown"><i class="icon-white icon-file"></i>Preview Laporan <span class="caret"></span></button>
                    <ul class="dropdown-menu">
                        <c:if test="${formBean.typeReport == 'Progress'}">
                            <li><a href="laporan-lpa?PIdLpaUmum=${formBean.idLpa}&lpa=lpa-int-progress" target="_blank">LPA</a></li>
                        </c:if>
                        <c:if test="${formBean.typeReport != 'Progress'}">
                            <c:if test="${isEksternal}">
                                <li><a href="laporan-lpa?PIdLpaUmum=${formBean.idLpa}&lpa=lpa-ext" target="_blank">LPA</a></li>
                            </c:if>
                            <c:if test="${!isEksternal}">
                                <c:if test="${!isDesktop}">
                                    <li><a href="laporan-lpa?PIdLpaUmum=${formBean.idLpa}&lpa=lpa-int" target="_blank">LPA</a></li>
                                </c:if>
                                <c:if test="${isDesktop}">
                                    <li><a href="laporan-lpa?PIdLpaUmum=${formBean.idLpa}&lpa=lpa-desktop" target="_blank">LPA Desktop</a></li>
                                </c:if>
                            </c:if>                        </c:if>
                        <li id="doks-tanah"  class="aktif-link"><a href="laporan-lpa?PIdLpaUmum=${formBean.idLpa}&lpa=lampiran-tanah" target="_blank">Lampiran Dokumen Tanah</a></li>
                        <li id="doks-bgn"  class="aktif-link"><a href="laporan-lpa?PIdLpaUmum=${formBean.idLpa}&lpa=lampiran-bangunan" target="_blank">Lampiran Dokumen Bangunan</a></li>
                        <li class="divider"></li>
                        <li id="breakdown-nilai"  class="aktif-link"><a href="laporan-lpa?PIdLpaUmum=${formBean.idLpa}&lpa=lampiran-nilai" target="_blank">Breakdown Nilai Sertifikat</a></li>                    
                        <li><a href="laporan-lpa?PIdLpaUmum=${formBean.idLpa}&lpa=lpa-image-survey" target="_blank">Image Hasil Survey</a></li>
                        <li><a href="laporan-lpa?PIdLpaUmum=${formBean.idLpa}&lpa=lpa-image-peta" target="_blank">Image Peta Wilayah / Lokasi</a></li>
                        <li><a href="#myModalLain" data-toggle="modal">Image Dokumen Lain</a></li>
                    </ul>
                </div>
            <%--</c:if>--%>
                <c:if test="${!formBean.create}">
                    <%--<button type="submit" id="btn-save-all" class="btn btn-success"><i class="icon-white icon-ok"></i>Save</button>--%>
                </c:if>
                <%--<button type="submit" id="btn-save-draft" class="btn btn-info"><i class="icon-white icon-camera"></i> Draf</button>--%>
                <%--<button type="reset" class="btn btn-primary"><i class="icon-white icon-refresh"></i> Reset</button>--%>
                <%--<a href="view?id=${formBean.commonId}" class="btn btn-warning"><i class="icon-white icon-arrow-left"></i> Back</a>--%>
                <c:if test="${formBean.id != null}">
                    <%--<a href="delete-lpa?id=${formBean.id}&commonId=${formBean.commonId}" id="btn-delete-lpa" class="btn btn-danger"><i class="icon-white icon-remove"></i> Delete</a>--%>
                </c:if>
            </c:if>


            <%--<c:if test="${!showButton}">--%>
                <a href="${ctxPath}/inquiry/view?id=${formBean.commonId}" class="btn btn-warning"><i
                        class="icon-white icon-arrow-left"></i> Back</a>
            <%--</c:if>--%>
        </div>
        <h1>Generate LPA ${formBean.appraisalTypeCategory} ${formBean.jenisAppraisal}</h1>
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#menu1">Data Umum <span class="badge badge-warning hide"></span></a></li>
            <li><a data-toggle="tab" href="#menu2">Data Tanah <span class="badge badge-warning hide"></span></a></li>
            <li><a data-toggle="tab" href="#menu3">Data Bangunan <span class="badge badge-warning hide"></span></a></li>
            <li><a data-toggle="tab" href="#menu4">Data Lingkungan <span class="badge badge-warning hide"></span></a></li>
            <c:if test="${isEksternal}">
                <li><a data-toggle="tab" href="#menu12">Nilai Agunan (Eksternal) <span class="badge badge-warning hide"></span></a></li>
                <li><a data-toggle="tab" href="#menu5">Nilai Agunan (Internal) <span class="badge badge-warning hide"></span></a></li>
            </c:if>
            <c:if test="${!isEksternal}">
                <li><a data-toggle="tab" href="#menu5">Nilai Agunan <span class="badge badge-warning hide"></span></a></li>
            </c:if>
            <li><a data-toggle="tab" href="#menu6">Marketability & Catatan<span class="badge badge-warning hide"></span></a></li>
            <li><a data-toggle="tab" href="#menu7">Negative List & Pertimbangan Khusus<span class="badge badge-warning hide"></span></a></li>
            <c:if test="${isEksternal}">
                <li><a data-toggle="tab" href="#menu10">Opini Internal <span class="badge badge-warning hide"></span></a></li>
            </c:if>
            <li><a data-toggle="tab" href="#menu11">Data Lampiran <span class="badge badge-warning hide"></span></a></li>
        </ul>
    </div>
    <div class="clearfix" style="margin-top:95px"></div>
    <spring:hasBindErrors name="formBean">
        <div class="alert alert-error">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            Terdapat ${fn:length(errors.allErrors)} Error Validasi, Silahkan Check Form Isian Anda
        </div>
    </spring:hasBindErrors>
    <div class="tab-content">
        <div id="menu1" class="tab-pane fade in active panel controls-row">
            <jsp:include page="../../lpa/form-umum.jsp"/>
        </div>
        <div id="menu2" class="tab-pane fade controls-row panel">
            <div class="page-header"><h3>Data Dokumen Tanah</h3></div>
            <div class="control-group span6">
                <f:hidden path="dokumenTanah.id"/>
                <label class="control-label" for="dokumenTanah.jenisDokumenId">Jenis Dokumen</label>
                <div class="controls">
                    <f:select path="dokumenTanah.jenisDokumenId">
                        <f:option value="" label="-- Select --" data-jatuh-tempo="true"/>
                        <c:forEach items="${listJenisDokumen}" var="row">
                            <f:option value="${row.id}" label="${row.name}" data-jatuh-tempo="${row.jatuhTempo == null ? false : row.jatuhTempo}"/>
                        </c:forEach>
                    </f:select>
                    <f:errors path="dokumenTanah.jenisDokumenId" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="dokumenTanah.namaDokumen">Nomer Dokumen</label>
                <div class="controls">
                    <f:input path="dokumenTanah.namaDokumen"/>
                    <f:errors path="dokumenTanah.namaDokumen" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="dokumenTanah.tanggalSertipikat">Tanggal Sertipikat</label>
                <div class="controls">
                    <f:input path="dokumenTanah.tanggalSertipikat" data-date-format="dd-mm-yyyy"
                             data-provide="datepicker"  class="tutup-otomatis" placeholder="DD-MM-YYYY" data-date-end-date="0d"/>
                    <f:errors path="dokumenTanah.tanggalSertipikat" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="dokumenTanah.namaJalan">Nama Jalan</label>
                <div class="controls">
                    <f:input path="dokumenTanah.namaJalan"/>
                    <f:errors path="dokumenTanah.namaJalan" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="dokumenTanah.blockGangLantai">Block/Gang/Lantai</label>
                <div class="controls">
                    <f:input path="dokumenTanah.blockGangLantai"/>
                    <f:errors path="dokumenTanah.blockGangLantai" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="dokumenTanah.nomor">Nomor</label>
                <div class="controls">
                    <f:input path="dokumenTanah.nomor" cssClass="input-mini" data-mask="number"/>
                    <f:errors path="dokumenTanah.nomor" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="dokumenTanah.rt">RT</label>
                <div class="controls">
                    <f:input path="dokumenTanah.rt" data-mask="number" cssClass="input-mini"/>
                    <f:errors path="dokumenTanah.rt" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="dokumenTanah.rw">RW</label>
                <div class="controls">
                    <f:input path="dokumenTanah.rw" data-mask="number" cssClass="input-mini"/>
                    <f:errors path="dokumenTanah.rw" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="dokumenTanah.kelurahan">Desa/Kelurahan</label>
                <div class="controls">
                    <f:input path="dokumenTanah.kelurahan"/>
                    <f:errors path="dokumenTanah.kelurahan" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="dokumenTanah.kecamatan">Kecamatan</label>
                <div class="controls">
                    <f:input path="dokumenTanah.kecamatan"/>
                    <f:errors path="dokumenTanah.kecamatan" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="dokumenTanah.kota">Kota/Kabupaten</label>
                <div class="controls">
                    <f:input path="dokumenTanah.kota"/>
                    <f:errors path="dokumenTanah.kota" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="dokumenTanah.propinsi">Propinsi</label>
                <div class="controls">
                    <f:input path="dokumenTanah.propinsi"/>
                    <f:errors path="dokumenTanah.propinsi" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="dokumenTanah.namaPemegangHak">Nama Pemegang Hak</label>
                <div class="controls">
                    <f:input path="dokumenTanah.namaPemegangHak"/>
                    <f:errors path="dokumenTanah.namaPemegangHak" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="dokumenTanah.tanggalBerakhirHak">Tanggal Berakhir Hak</label>
                <div class="controls">
                    <f:input readonly="true" path="dokumenTanah.tanggalBerakhirHak" data-date-format="dd-mm-yyyy"
                             data-provide="datepicker"  class="tutup-otomatis" placeholder="DD-MM-YYYY"/>
                    <f:errors path="dokumenTanah.tanggalBerakhirHak" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="dokumenTanah.suratUkur">Gbr Situasi/Surat Ukur</label>
                <div class="controls">
                    <f:input path="dokumenTanah.suratUkur"/>
                    <f:errors path="dokumenTanah.suratUkur" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="dokumenTanah.tanggalSuratUkur">Tanggal Surat Ukur</label>
                <div class="controls">
                    <f:input path="dokumenTanah.tanggalSuratUkur" data-date-format="dd-mm-yyyy"
                             data-provide="datepicker"  class="tutup-otomatis" placeholder="DD-MM-YYYY" data-date-end-date="0d"/>
                    <f:errors path="dokumenTanah.tanggalSuratUkur" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="dokumenTanah.luasTanah">Luas Tanah Sesuai Dokumen</label>
                <div class="controls">
                    <f:input path="dokumenTanah.luasTanah" data-mask="luas-tanah" cssClass="input-mini"/>
                    <f:errors path="dokumenTanah.luasTanah" cssClass="help-block"/>
                </div>
            </div>
            <div style="clear: both"></div>
            <div class="page-header">
                <c:if test="${showButton}">
                    <c:if test="${formBean.id != null}">
                        <div class="absolute-right">
                            <%--<button id="btn-add-doc-tanah" href="form-doc-tanah" data-target="#modal-tanah" role="button" data-toggle="modal" data-remote="true"--%>
                                    <%--type="button" class="btn btn-info"><i class="icon-white icon-plus"></i> Add--%>
                            <%--</button>--%>
                        </div>
                    </c:if>
                </c:if>
                <h3>Lampiran Data Dokumen Tanah Lainnya</h3>
            </div>
            <table class="table dataTable">
                <thead>
                <tr>
                    <th style="width: 80px;">Kode</th>
                    <th>Jenis Dokumen</th>
                    <th>Nomer Dokumen</th>
                    <th>Luas Tanah</th>
                    <th>Tanggal Sertifikat</th>
                    <th>Tanggal Berakhirnya Hak</th>
                    <th>Nama Pemilik Hak</th>
                </tr>
                </thead>
                <tbody id="tbody-doc-tanah">
                <c:if test="${formBean.id == null}">
                    <tr class="odd">
                        <td colspan="4"><i>Untuk Menambah Lampiran Dokumen Tanah, lakukan Simpan dahulu</i></td>
                    </tr>
                </c:if>
                </tbody>
            </table>
            <div class="control-group span12">
                <label class="control-label" for="totalLuasTanahDokumen">Total Luas Tanah Sesuai Dokumen</label>
                <div class="controls">
                    <f:input path="totalLuasTanahDokumen" cssClass="input-mini" data-mask="luas-tanah"
                             readonly="true"/>
                    <f:errors path="totalLuasTanahDokumen" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="luasTanahSesuaiTataKota">Luas Tanah Terkena Rencana Jalan</label>
                <div class="controls">
                    <f:input path="luasTanahSesuaiTataKota" cssClass="input-mini"
                             data-mask="luas-tanah"/>
                    <f:errors path="luasTanahSesuaiTataKota" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label">Luas Tanah Bersih</label>
                <div class="controls">
                    <input type="text" class="input-mini" id="luas-tidak-kena" readonly data-mask="numeric">
                </div>
            </div>
            <div style="clear: both"></div>
            <div class="page-header"><h3>Data Fisik</h3></div>
            <div class="control-group span6">
                <label class="control-label" for="bentukTanah">Bentuk Tanah</label>
                <div class="controls">
                    <f:select path="bentukTanah">
                        <f:option value="">-- Select --</f:option>
                        <f:options items="${listBentukTanah}" itemValue="label" itemLabel="label"></f:options>
                    </f:select>
                    <f:errors path="bentukTanah" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="konturTanah">Kontur Tanah</label>
                <div class="controls">
                    <f:select path="konturTanah">
                        <f:option value="">-- Select --</f:option>
                        <f:options items="${listKonturTanah}" itemValue="label" itemLabel="label"></f:options>
                    </f:select>
                    <f:errors path="konturTanah" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="ketinggianTanah">Ketinggian dari Jalan</label>
                <div class="controls">
                    <f:select path="ketinggianTanah">
                        <f:option value="">-- Select --</f:option>
                        <f:options items="${listKetinggianTanah}" itemValue="label" itemLabel="label"></f:options>
                    </f:select>
                    <f:errors path="ketinggianTanah" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="peruntukanTanah">Peruntukan</label>
                <div class="controls">
                    <f:select path="peruntukanTanah">
                        <f:option value="">-- Select --</f:option>
                        <f:options items="${listPeruntukanTanah}" itemValue="label"
                                   itemLabel="label"></f:options>
                    </f:select>
                    <f:errors path="peruntukanTanah" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="batasTanah">Kesesuaian Batas Tanah</label>
                <div class="controls">
                    <f:select path="batasTanah">
                        <f:option value="">-- Select --</f:option>
                        <f:options items="${listBatasTanah}" itemValue="label" itemLabel="label"></f:options>
                    </f:select>
                    <f:errors path="batasTanah" cssClass="help-block"/>
                </div>
            </div>
            <div style="clear: both"></div>
            <div class="page-header"><h3>Batas Tanah</h3></div>
            <div class="control-group span6">
                <label class="control-label" for="batasUtaraTanah">Utara</label>
                <div class="controls">
                    <f:input path="batasUtaraTanah"/>
                    <f:errors path="batasUtaraTanah" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="batasSelatanTanah">Selatan</label>
                <div class="controls">
                    <f:input path="batasSelatanTanah"/>
                    <f:errors path="batasSelatanTanah" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="batasBaratTanah">Barat</label>
                <div class="controls">
                    <f:input path="batasBaratTanah"/>
                    <f:errors path="batasBaratTanah" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="batasTimurTanah">Timur</label>
                <div class="controls">
                    <f:input path="batasTimurTanah"/>
                    <f:errors path="batasTimurTanah" cssClass="help-block"/>
                </div>
            </div>
            <div style="clear: both; margin-top: 20px;">&nbsp;</div>
            <div class="control-group span6">
                <label class="control-label" for="posisiTanah">Posisi Tanah</label>
                <div class="controls">
                    <f:select path="posisiTanah">
                        <f:option value="">-- Select --</f:option>
                        <f:options items="${listPosisiTanah}" itemValue="label" itemLabel="label"></f:options>
                    </f:select>
                    <f:errors path="posisiTanah" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="kondisiTanah">Kondisi Tanah</label>
                <div class="controls">
                    <f:select path="kondisiTanah">
                        <f:option value="">-- Select --</f:option>
                        <f:options items="${listKondisiTanah}" itemValue="label" itemLabel="label"></f:options>
                    </f:select>
                    <f:errors path="kondisiTanah" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="jenisTanah">Jenis Tanah</label>
                <div class="controls">
                    <f:select path="jenisTanah">
                        <f:option value="">-- Select --</f:option>
                        <f:options items="${listJenisTanah}" itemValue="label" itemLabel="label"></f:options>
                    </f:select>
                    <f:errors path="jenisTanah" cssClass="help-block"/>
                </div>
            </div>
        </div>
        <div id="menu3" class="tab-pane fade controls-row panel">
            <div id="div-doc-bangunan">
                <div class="page-header">
                    <h3>Data Dokumen Bangunan</h3>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="dokumenBangunan.nomorImb">Nomor IMB</label>
                    <div class="controls">
                        <f:hidden path="dokumenBangunan.id"/>
                        <f:input path="dokumenBangunan.nomorImb"/>
                        <f:errors path="dokumenBangunan.nomorImb" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="dokumenBangunan.tanggalImb">Tanggal IMB</label>
                    <div class="controls">
                        <f:input path="dokumenBangunan.tanggalImb" data-provide="datepicker"  class="tutup-otomatis" data-date-end-date="0d"
                                 placeholder="DD-MM-YYYY" data-date-format="dd-mm-yyyy"/>
                        <f:errors path="dokumenBangunan.tanggalImb" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="dokumenBangunan.ijinSesuaiImb">Ijin Sesuai IMB</label>
                    <div class="controls">
                        <f:select path="dokumenBangunan.ijinSesuaiImb">
                            <f:option value="">-- Select --</f:option>
                            <f:options items="${listIjinSesuaiImb}" itemValue="label" itemLabel="label"/>
                        </f:select>
                        <f:errors path="dokumenBangunan.ijinSesuaiImb" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="dokumenBangunan.luasBangunan">Luas Bangunan Sesuai IMB</label>
                    <div class="controls">
                        <f:input path="dokumenBangunan.luasBangunan" cssClass="input-mini" data-mask="luas-tanah"/>
                        <f:errors path="dokumenBangunan.luasBangunan" cssClass="help-block"/>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label" for="dokumenBangunan.jumlahLantai">Jumlah Lantai Sesuai IMB</label>
                    <div class="controls">
                        <f:input path="dokumenBangunan.jumlahLantai" cssClass="input-mini" data-mask="luas-tanah"/>
                        <f:errors path="dokumenBangunan.jumlahLantai" cssClass="help-block"/>
                    </div>
                </div>
            </div>
            <div style="clear: both"></div>
            <div class="page-header">
                <c:if test="${showButton}">
                    <c:if test="${formBean.id != null}">
                        <div class="absolute-right">
                            <%--<button id="btn-add-doc-bangungan" href="#myModal" role="button" data-toggle="modal"--%>
                                    <%--type="button" class="btn btn-info"><i class="icon-white icon-plus"></i> Add--%>
                            <%--</button>--%>
                        </div>
                    </c:if>
                </c:if>
                <h3>Lampiran Dokumen Bangunan</h3>
            </div>
            <table class="table dataTable">
                <thead>
                <tr>
                    <th width="80px;">Kode</th>
                    <th>No IMB</th>
                    <th>Tanggal IMB</th>
                    <th>Ijin IMB</th>
                    <th>Luas</th>
                </tr>
                </thead>
                <tbody id="tbody-doc-bangunan">
                <tr class="odd">
                    <td colspan="6"><i>Untuk Menambah Lampiran Dokumen Bangunan, lakukan Simpan dahulu</i></td>
                </tr>
                </tbody>
            </table>
            <div class="control-group span12">
                <label class="control-label" for="luasLantai1">Luas Bangunan Sesuai Dokumen</label>
                <div class="controls">
                    <f:input path="luasBangunanImb" cssClass="input-small" data-mask="luas-tanah" readonly="true"/>
                    <f:errors path="luasBangunanImb" cssClass="help-block"/>
                </div>
            </div>
            <div style="clear: both"></div>
            <div class="page-header"><h3>Data Luas Bangunan Fisik</h3></div>
            <div class="control-group span6">
                <label class="control-label" for="luasLantai1">Luas Lantai 1</label>
                <div class="controls">
                    <f:input path="luasLantai1" cssClass="input-mini" data-mask="luas-tanah"/>
                    <f:errors path="luasLantai1" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="luasLantai2">Luas Lantai 2</label>
                <div class="controls">
                    <f:input path="luasLantai2" cssClass="input-mini" data-mask="luas-tanah"/>
                    <f:errors path="luasLantai2" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="luasLantai3">Luas Lantai 3</label>
                <div class="controls">
                    <f:input path="luasLantai3" cssClass="input-mini" data-mask="luas-tanah"/>
                    <f:errors path="luasLantai3" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="luasLantai4">Luas Lantai 4</label>
                <div class="controls">
                    <f:input path="luasLantai4" cssClass="input-mini" data-mask="luas-tanah"/>
                    <f:errors path="luasLantai4" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="luasLantai5">Luas Lantai 5</label>
                <div class="controls">
                    <f:input path="luasLantai5" cssClass="input-mini" data-mask="luas-tanah"/>
                    <f:errors path="luasLantai5" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="luasBangunan">Luas Bangunan Sesuai Fisik</label>
                <div class="controls">
                    <f:input path="luasBangunan" cssClass="input-mini" data-mask="luas-tanah"/>
                    <label><f:checkbox path="hitungManual"/> Hitung Manual</label>
                    <f:errors path="luasBangunan" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="jumlahLantaiFisik">Jumlah Lantai Sesuai Fisik</label>
                <div class="controls">
                    <f:input path="jumlahLantaiFisik" cssClass="input-mini" data-mask="luas-tanah"/>
                    <f:errors path="jumlahLantaiFisik" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="jumlahLantaiTampakDepan">Jumlah Lantai Tampak Depan</label>
                <div class="controls">
                    <f:input path="jumlahLantaiTampakDepan" cssClass="input-mini" data-mask="luas-tanah"/>
                    <f:errors path="jumlahLantaiTampakDepan" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="jenisBangunan">Jenis Bangunan</label>
                <div class="controls">
                    <f:select path="jenisBangunan">
                        <f:option value="">-- Select --</f:option>
                        <f:options items="${listJenisBangunan}" itemValue="label" itemLabel="label"/>
                    </f:select>
                    <f:errors path="jenisBangunan" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="digunakanSebagai">Dipergunakan Sebagai</label>
                <div class="controls">
                    <f:select path="digunakanSebagai">
                        <f:option value="">-- Select --</f:option>
                        <f:options items="${listDipergunakan}" itemValue="label" itemLabel="label"/>
                    </f:select>
                    <f:errors path="digunakanSebagai" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="dihuniOleh">Dihuni Oleh</label>
                <div class="controls">
                    <f:select path="dihuniOleh">
                        <f:option value="">-- Select --</f:option>
                        <f:options items="${listDihuni}" itemValue="label" itemLabel="label"/>
                    </f:select>
                    <f:errors path="dihuniOleh" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="statusAgunanDisewakan">Status Agunan Disewakan</label>
                <div class="controls">
                    <f:select path="statusAgunanDisewakan">
                        <f:option value="">-- Select --</f:option>
                        <f:options items="${listStatusAgunan}" itemValue="label" itemLabel="label"/>
                    </f:select>
                    <f:errors path="statusAgunanDisewakan" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="tahunDibangun">Tahun Dibangun</label>
                <div class="controls">
                    <f:input path="tahunDibangun" cssClass="input-mini" data-mask="number" maxlength="4"/>
                    <f:errors path="tahunDibangun" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="tahunDirenov">Tahun Direnovasi</label>
                <div class="controls">
                    <f:input path="tahunDirenov" cssClass="input-mini" data-mask="number" maxlength="4"/>
                    <f:errors path="tahunDirenov" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="kelasBangunan">Kelas Bangunan</label>
                <div class="controls">
                    <f:select path="kelasBangunan">
                        <f:option value="">-- Select --</f:option>
                        <f:options items="${listKelasBangunan}" itemValue="label" itemLabel="label"/>
                    </f:select>
                    <f:errors path="kelasBangunan" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="kondisiUmum">Kondisi Umum</label>
                <div class="controls">
                    <f:input path="kondisiUmum"/>
                    <f:errors path="kondisiUmum" cssClass="help-block"/>
                </div>
            </div>
            <div style="clear: both"></div>
            <div class="page-header"><h3>Konstruksi</h3></div>
            <div class="control-group span6">
                <label class="control-label" for="prosesPembangunan">Proses Pembangunan</label>
                <div class="controls">
                    <f:select path="prosesPembangunan">
                        <f:option value="">-- Select --</f:option>
                        <c:forEach items="${listProsesPembangunan}" var="row">
                            <f:option value="${row.value}" label="${row.value}" data-code="${row.code}"/>
                        </c:forEach>
                    </f:select>
                    <f:errors path="prosesPembangunan" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="statusBangunan">Status Bangunan</label>
                <div class="controls">
                    <f:select path="statusBangunan">
                        <f:option value="">-- Select --</f:option>
                        <f:options items="${listStatusPembangunan}" itemValue="label" itemLabel="label"/>
                    </f:select>
                    <f:errors path="statusBangunan" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="prosesKonstruksi">Proses Konstruksi</label>
                <div class="controls">
                    <div class="input-append">
                        <f:input path="prosesKonstruksi" cssClass="input-mini" data-mask="persen"/>
                        <span type="button" class="btn btn-info"> % </span>
                    </div>
                    <f:errors path="prosesKonstruksi" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="tanggalProsesKonstruksi">Tanggal Progress Konstruksi</label>
                <div class="controls">
                    <f:input path="tanggalProsesKonstruksi" data-provide="datepicker"  class="tutup-otomatis" data-date-end-date="0d"
                             placeholder="DD-MM-YYYY" data-date-format="dd-mm-yyyy"/>
                    <f:errors path="tanggalProsesKonstruksi" cssClass="help-block"/>
                </div>
            </div>
        </div>
        <div id="menu4" class="tab-pane fade controls-row panel">
            <div class="page-header"><h3>Data Lingkungan</h3></div>
            <div class="control-group span6">
                <label class="control-label" for="lingkunganSekitar">Lingkungan Sekitar</label>
                <div class="controls">
                    <f:select path="lingkunganSekitar">
                        <f:option value="">-- Select --</f:option>
                        <f:options items="${listLingkunganSekitar}" itemLabel="label" itemValue="label"/>
                    </f:select>
                    <f:errors path="lingkunganSekitar" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="tingkatKepadatan">Tingkat Kepadatan</label>
                <div class="controls">
                    <f:select path="tingkatKepadatan">
                        <f:option value="">-- Select --</f:option>
                        <f:options items="${listTingkatKepadatan}" itemLabel="label" itemValue="label"/>
                    </f:select>
                    <f:errors path="tingkatKepadatan" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="tingkatKemacetan">Tingkat Kemacetan</label>
                <div class="controls">
                    <f:select path="tingkatKemacetan">
                        <f:option value="">-- Select --</f:option>
                        <f:options items="${listTingkatKemacetan}" itemLabel="label" itemValue="label"/>
                    </f:select>
                    <f:errors path="tingkatKemacetan" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="rencanaJalan">Terkena Rencana Jalan</label>
                <div class="controls">
                    <f:select path="rencanaJalan">
                        <f:option value="">-- Select --</f:option>
                        <f:option value="0">Tidak</f:option>
                        <f:option value="1">Ya</f:option>
                    </f:select>
                    <f:errors path="rencanaJalan" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="lebarJalan">Lebar Jalan</label>
                <div class="controls">
                    <f:input path="lebarJalan" cssClass="input-mini" data-mask="luas-tanah"/>
                    <f:errors path="lebarJalan" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="gsb">Bangunan Terkena GSB</label>
                <div class="controls">
                    <f:input path="gsb" cssClass="input-mini" data-mask="luas-tanah"/>
                    <f:errors path="gsb" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="gss">Garis Sempadan Sungai</label>
                <div class="controls">
                    <f:input path="gss" cssClass="input-mini" data-mask="luas-tanah"/>
                    <f:errors path="gss" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="kendaraanRoda4">Dilalui Kendaraan Roda 4</label>
                <div class="controls">
                    <f:select path="kendaraanRoda4">
                        <f:option value="">-- Select --</f:option>
                        <f:option value="0">Tidak</f:option>
                        <f:option value="1">Ya</f:option>
                    </f:select>
                    <f:errors path="kendaraanRoda4" cssClass="help-block"/>
                </div>
            </div>
        </div>
        <div id="menu5" class="tab-pane fade controls-row panel">
            <div class="control-group span6">
                <label class="control-label" for="hargaTanah">Nilai Tanah</label>
                <div class="controls">
                    <div class="input-append">
                        <f:input path="hargaTanah" cssClass="input-medium" data-mask="currency" readonly="true"/>
                        <button type="button" id="btn-search-harga-tanah" class="btn btn-info"><c:if test="${isDesktop}"><i class="icon-white icon-search"></i> </c:if>per M<sup>2</sup></button>
                    </div>
                    <f:errors path="hargaTanah" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="hargaBangunan">Nilai Bangunan</label>
                <div class="controls">
                    <div class="input-append">
                        <f:input path="hargaBangunan" cssClass="input-medium" data-mask="currency" readonly="true"/>
                        <button type="button" class="btn btn-info">per M<sup>2</sup></button>
                    </div>
                    <f:errors path="hargaBangunan" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="hargaBangunan">&nbsp;</label>
                <div class="controls">
                    <f:checkbox path="hitungManual1" label="Hitung Manual"/>
                </div>
            </div>
            <div style="clear: both"></div>
            <table width="100%" class="table dataTable custom-ui">
                <thead>
                <tr>
                    <th></th>
                    <th>Uraian</th>
                    <th>Nilai Sesuai Dokumen</th>
                    <th>Nilai Sesuai Fisik</th>
                    <th>Prosentase(%)</th>
                </tr></thead>
                <tbody>
                <tr class="odd">
                    <td>I</td>
                    <td>
                        <div class="control-group">
                            <f:select path="uraian1" disabled="true">
                                <f:option value="tanah" label="Tanah"/>
                                <f:option value="bangunan" label="Bangunan"/>
                                <f:option value="unit" label="Per Unit"/>
                                <f:option value="tidak" label="Tidak Diisi"/>
                            </f:select>
                            <f:errors path="uraian1"/>
                        </div>
                    </td>
                    <td>
                        <div>
                            <f:input path="nilaiDokumen1" cssClass="input-medium" data-mask="currency"/>
                        </div>
                        <f:radiobutton path="diberiNilaiDokumen1" value="1" label="Diberi Nilai"/>
                        <f:radiobutton path="diberiNilaiDokumen1" value="0" label="Tidak Diberi Nilai"/>
                    </td>
                    <td>
                        <div><f:input path="nilaiFisik1" cssClass="input-medium" data-mask="currency"/></div>
                        <f:radiobutton path="diberiNilaiFisik1" value="1" label="Diberi Nilai"/>
                        <f:radiobutton path="diberiNilaiFisik1" value="0" label="Tidak Diberi Nilai"/>
                    </td>
                    <td>
                        <div class="control-group">
                            <div class="input-append">
                                <f:input path="prosentase1" cssClass="input-mini" data-mask="persen" maxlength="3"/>
                                <button type="button" class="btn btn-info">%</button>
                            </div>
                            <f:errors path="prosentase1"/>
                        </div>
                    </td>
                </tr>
                <tr class="even">
                    <td>II</td>
                    <td>
                        <div class="control-group">
                            <f:select path="uraian2" disabled="true">
                                <f:option value="tanah" label="Tanah"/>
                                <f:option value="bangunan" label="Bangunan"/>
                                <f:option value="unit" label="Per Unit"/>
                                <f:option value="tidak" label="Tidak Diisi"/>
                            </f:select>
                            <f:errors path="uraian2"/>
                        </div>
                    </td>
                    <td>
                        <div><f:input path="nilaiDokumen2" cssClass="input-medium" data-mask="currency"/></div>
                        <f:radiobutton path="diberiNilaiDokumen2" value="1" label="Diberi Nilai"/>
                        <f:radiobutton path="diberiNilaiDokumen2" value="0" label="Tidak Diberi Nilai"/>
                    </td>
                    <td>
                        <div><f:input path="nilaiFisik2" cssClass="input-medium" data-mask="currency"/></div>
                        <f:radiobutton path="diberiNilaiFisik2" value="1" label="Diberi Nilai"/>
                        <f:radiobutton path="diberiNilaiFisik2" value="0" label="Tidak Diberi Nilai"/>
                    </td>
                    <td>
                        <div class="control-group">
                            <div class="input-append">
                                <f:input path="prosentase2" cssClass="input-mini" data-mask="persen" maxlength="3"/>
                                <button type="button" class="btn btn-info">%</button>
                            </div>
                            <f:errors path="prosentase2"/>
                        </div>
                    </td>
                </tr>
                <tr class="odd">
                    <td></td>
                    <td>Total</td>
                    <td><f:input path="totalSesuaiDokumen" data-mask="currency" readonly="true"/></td>
                    <td colspan="2"><f:input path="totalSesuaiFisik" data-mask="currency" readonly="true"/></td>
                </tr>
                <tr class="even">
                    <td></td>
                    <td>Nilai Likuidasi I</td>
                    <td><f:input path="likuidasiDokumen1" data-mask="currency" readonly="true"/></td>
                    <td colspan="2"><f:input path="likuidasiFisik1" data-mask="currency" readonly="true"/></td>
                </tr>
                <tr class="odd">
                    <td></td>
                    <td>Nilai Likuidasi II</td>
                    <td><f:input path="likuidasiDokumen2" data-mask="currency" readonly="true"/></td>
                    <td colspan="3"><f:input path="likuidasiFisik2" data-mask="currency" readonly="true"/></td>
                </tr>
                <tr class="even">
                    <td></td>
                    <td>Total Likuidasi</td>
                    <td><f:input path="totalLikuidasiDokumen" data-mask="currency" readonly="true"/></td>
                    <td colspan="3"><f:input path="totalLikuidasiFisik" data-mask="currency" readonly="true"/></td>
                </tr>
                <tr class="odd">
                    <td></td>
                    <td>Nilai Teleransi I (20%)</td>
                    <td colspan="4"><f:input path="toleransi1" data-mask="currency" readonly="true"/></td>
                </tr>
                <tr class="even">
                    <td></td>
                    <td>Nilai Teleransi II (30%)</td>
                    <td colspan="3"><f:input path="toleransi2" data-mask="currency" readonly="true"/></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div id="menu6" class="tab-pane fade controls-row panel">
            <jsp:include page="../../lpa/form-market.jsp"/>
        </div>
        <div id="menu7" class="tab-pane fade controls-row panel">
            <jsp:include page="../../lpa/form-pertimbangan-dan-negative.jsp"/>
        </div>
        <div id="menu8" class="tab-pane fade controls-row panel"></div>
        <div id="menu9" class="tab-pane fade controls-row panel"></div>
        <div id="menu10" class="tab-pane fade controls-row panel">
            <jsp:include page="../../lpa/form-opini.jsp"/>
        </div>
        <div id="menu11" class="tab-pane fade controls-row panel">
            <div class="absolute-right">
                <%--<c:if test="${formBean.id != null}"><button id="btn-add-image" type="button" class="btn btn-success"><i class="icon-white icon-plus"></i> Add</button></c:if>--%>
            </div>

            <table class="table dataTable" style="margin-top:28px;">
                <thead><tr><th width="100px">#</th><th>Kategori</th><th>Catatan</th></tr></thead>
                <tbody id="tbody-image">
                    <tr class="odd"><td colspan="3" align="center"><i>Lakukan proses simpan untuk melihat data</i></td></tr>
                </tbody>
            </table>
        </div>
        <div id="menu12" class="tab-pane fade controls-row panel">
            <div class="control-group span6">
                <label class="control-label" for="hargaTanahEks">Nilai Tanah</label>
                <div class="controls">
                    <div class="input-append">
                        <f:input path="hargaTanahEks" cssClass="input-medium" data-mask="currency" readonly="true"/>
                        <button type="button" class="btn btn-info">per M<sup>2</sup></button>
                    </div>
                    <f:errors path="hargaTanahEks" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="hargaBangunanEks">Harga Bangunan</label>
                <div class="controls">
                    <div class="input-append">
                        <f:input path="hargaBangunanEks" cssClass="input-medium" data-mask="currency" readonly="true"/>
                        <button type="button" class="btn btn-info">per M<sup>2</sup></button>
                    </div>
                    <f:errors path="hargaBangunanEks" cssClass="help-block"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label">&nbsp;</label>
                <div class="controls">
                    <f:checkbox path="hitungManualEks" label="Hitung Manual"/>
                </div>
            </div>
            <div style="clear: both"></div>
            <table width="100%" class="table dataTable custom-ui">
                <thead>
                <tr>
                    <th></th>
                    <th>Uraian</th>
                    <th>Nilai Sesuai Dokumen</th>
                    <th>Nilai Sesuai Fisik</th>
                    <th>Prosentase(%)</th>
                </tr></thead>
                <tbody>
                <tr class="odd">
                    <td>I</td>
                    <td>
                        <div class="control-group">
                            <f:select path="uraian1Eks" disabled="true">
                                <f:option value="tanah" label="Tanah"/>
                                <f:option value="bangunan" label="Bangunan"/>
                                <f:option value="unit" label="Per Unit"/>
                                <f:option value="tidak" label="Tidak Diisi"/>
                            </f:select>
                            <f:errors path="uraian1Eks"/>
                        </div>
                    </td>
                    <td>
                        <div>
                            <f:input path="nilaiDokumen1Eks" cssClass="input-medium" data-mask="currency"/>
                        </div>
                        <f:radiobutton path="diberiNilaiDokumen1Eks" value="1" label="Diberi Nilai"/>
                        <f:radiobutton path="diberiNilaiDokumen1Eks" value="0" label="Tidak Diberi Nilai"/>
                    </td>
                    <td>
                        <div><f:input path="nilaiFisik1Eks" cssClass="input-medium" data-mask="currency"/></div>
                        <f:radiobutton path="diberiNilaiFisik1Eks" value="1" label="Diberi Nilai"/>
                        <f:radiobutton path="diberiNilaiFisik1Eks" value="0" label="Tidak Diberi Nilai"/>
                    </td>
                    <td>
                        <div class="control-group">
                            <div class="input-append">
                                <f:input path="prosentase1Eks" cssClass="input-mini" data-mask="numeric" maxlength="3"/>
                                <button type="button" class="btn btn-info">%</button>
                            </div>
                            <f:errors path="prosentase1Eks"/>
                        </div>
                    </td>
                </tr>
                <tr class="even">
                    <td>II</td>
                    <td>
                        <div class="control-group">
                            <f:select path="uraian2Eks" disabled="true">
                                <f:option value="tanah" label="Tanah"/>
                                <f:option value="bangunan" label="Bangunan"/>
                                <f:option value="unit" label="Per Unit"/>
                                <f:option value="tidak" label="Tidak Diisi"/>
                            </f:select>
                            <f:errors path="uraian2Eks"/>
                        </div>
                    </td>
                    <td>
                        <div><f:input path="nilaiDokumen2Eks" cssClass="input-medium" data-mask="currency"/></div>
                        <f:radiobutton path="diberiNilaiDokumen2Eks" value="1" label="Diberi Nilai"/>
                        <f:radiobutton path="diberiNilaiDokumen2Eks" value="0" label="Tidak Diberi Nilai"/>
                    </td>
                    <td>
                        <div><f:input path="nilaiFisik2Eks" cssClass="input-medium" data-mask="currency"/></div>
                        <f:radiobutton path="diberiNilaiFisik2Eks" value="1" label="Diberi Nilai"/>
                        <f:radiobutton path="diberiNilaiFisik2Eks" value="0" label="Tidak Diberi Nilai"/>
                    </td>
                    <td>
                        <div class="control-group">
                            <div class="input-append">
                                <f:input path="prosentase2Eks" cssClass="input-mini" data-mask="numeric" maxlength="3"/>
                                <button type="button" class="btn btn-info">%</button>
                            </div>
                            <f:errors path="prosentase2Eks"/>
                        </div>
                    </td>
                </tr>
                <tr class="odd">
                    <td></td>
                    <td>Total</td>
                    <td><f:input path="totalSesuaiDokumenEks" data-mask="currency" readonly="true"/></td>
                    <td colspan="2"><f:input path="totalSesuaiFisikEks" data-mask="currency" readonly="true"/></td>
                </tr>
                <tr class="even">
                    <td></td>
                    <td>Nilai Likuidasi I</td>
                    <td><f:input path="likuidasiDokumen1Eks" data-mask="currency" readonly="true"/></td>
                    <td colspan="2"><f:input path="likuidasiFisik1Eks" data-mask="currency" readonly="true"/></td>
                </tr>
                <tr class="odd">
                    <td></td>
                    <td>Nilai Likuidasi II</td>
                    <td><f:input path="likuidasiDokumen2Eks" data-mask="currency" readonly="true"/></td>
                    <td colspan="3"><f:input path="likuidasiFisik2Eks" data-mask="currency" readonly="true"/></td>
                </tr>
                <tr class="even">
                    <td></td>
                    <td>Total Likuidasi</td>
                    <td><f:input path="totalLikuidasiDokumenEks" data-mask="currency" readonly="true"/></td>
                    <td colspan="3"><f:input path="totalLikuidasiFisikEks" data-mask="currency" readonly="true"/></td>
                </tr>
                <tr class="odd">
                    <td></td>
                    <td>Nilai Teleransi I (20%)</td>
                    <td colspan="4"><f:input path="toleransi1Eks" data-mask="currency" readonly="true"/></td>
                </tr>
                <tr class="even">
                    <td></td>
                    <td>Nilai Teleransi II (30%)</td>
                    <td colspan="3"><f:input path="toleransi2Eks" data-mask="currency" readonly="true"/></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</f:form>
</div>
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <form action="save-doc-bangunan?${_csrf.parameterName}=${_csrf.token}" method="post" class="form-horizontal" id="form-doc-bangunan">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
            <h3 id="myModalLabel">Data Dokumen Bangunan</h3>
        </div>
        <div class="modal-body">
            <input type="hidden" name="id" value="">
            <input type="hidden" name="commonId" value="${formBean.id}">
            <div class="control-group span6">
                <label class="control-label" for="nomorImb">Nomor IMB</label>
                <div class="controls"><input id="nomorImb" name="nomorImb"></div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="tanggalImb">Tanggal IMB</label>
                <div class="controls">
                    <input id="tanggalImb" name="tanggalImb" data-provide="datepicker"  class="tutup-otomatis" data-date-end-date="0d"
                           placeholder="DD-MM-YYYY" data-date-format="dd-mm-yyyy"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="ijinSesuaiImb">Ijin Sesuai IMB</label>
                <div class="controls">
                    <select id="ijinSesuaiImb" name="ijinSesuaiImb">
                        <option value="">-- Select --</option>
                        <c:forEach items="${listIjinSesuaiImb}" var="row">
                            <option value="${row.label}">${row.label}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="luasBangunan">Luas Bangunan Sesuai IMB</label>
                <div class="controls">
                    <input id="luasBangunan" name="luasBangunan" class="input-mini" data-mask="luas-tanah"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="jumlahLantai">Jumlah Lantai Sesuai IMB</label>
                <div class="controls">
                    <input id="jumlahLantai" name="jumlahLantai" class="input-mini" data-mask="luas-tanah"/>
                </div>
            </div>
        </div>
        <c:if test="${showButton}">
            <%--<div class="modal-footer">--%>
                <%--<button id="btn-submit-doc-bangungan" type="button" class="btn btn-success"><i class="icon-white icon-ok"></i> Save</button>--%>
            <%--</div>--%>
        </c:if>
    </form>
</div>
<div id="modal-tanah" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="modal-tanah-label" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
        <h3 id="modal-tanah-label">Data Dokumen Tanah</h3>
    </div>
    <div class="modal-body"></div>
</div>
<div id="modal-image" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="modal-image-label" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
        <h3 id="modal-image-label">Data Lampiran</h3>
    </div>
    <div class="modal-body" style="height: 300px;">
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
    var calcLuas;
    var refreshTableDokumenBangunan;
    var refreshTableDokumenTanah;
    var refreshTableImage;
    var refreshTableImageOther;
    var calcAgunan;
    var callbackWorksheet;
    <c:if test="${isEksternal}">
        var calcAgunanEks;
    </c:if>
    var callbackModal;
    <c:if test="${isDesktop}">
        var callbackNilaiTanah;
        var windowOpenNilaiTanah;
    </c:if>


    var elems = document.getElementsByTagName('input');
    var len = elems.length;

    for (var i = 0; i < len; i++) {
        elems[i].disabled = true;
    }

    var elems = document.getElementsByTagName('select');
    var len = elems.length;

    for (var i = 0; i < len; i++) {
        elems[i].disabled = true;
    }

    var elems = document.getElementsByTagName('textarea');
    var len = elems.length;

    for (var i = 0; i < len; i++) {
        elems[i].disabled = true;
    }

    $(document).ready(function () {

        $.active = false;
        $('body').bind('click keypress', function() { $.active = true; });
        var logout = '${ctxPath}/logoutExpired';
        var heartBeat = '${ctxPath}/heartbeat';
        checkActivity(${maxInterval}, 60000, 0,logout,heartBeat);

        callbackWorksheet = function(data){
            $('.nav-tabs a[href="#menu5"]').tab('show');
            $('#hitungManual11').prop( "checked", true );
            $('#hitungManual11').trigger('change');
            $('#nilaiDokumen1').val(data.docTanah);
            $('#nilaiFisik1').val(data.fisikTanah);
            $('#nilaiDokumen2').val(data.docBangunan);
            $('#nilaiFisik2').val(data.fisikBangunan);
            $('#totalSesuaiDokumen').val(data.totalDoc);
            $('#totalSesuaiFisik').val(data.totalFisik);
            $('#likuidasiDokumen1').val(data.likuidasiDocTanah);
            $('#likuidasiFisik1').val(data.likuidasiFisikTanah);
            $('#likuidasiDokumen2').val(data.likuidasiDocBangunan);
            $('#likuidasiFisik2').val(data.likuidasiFisikBangunan);
            $('#totalLikuidasiDokumen').val(data.totalLikuidasiDoc);
            $('#totalLikuidasiFisik').val(data.totalLikuidasiFisik);
        }
        <c:if test="${isDesktop}">
            $('#btn-search-harga-tanah').click(function () {
                windowOpenNilaiTanah = window.open("${ctxPath}/master/nilai-tanah/lookup", "_blank");
            });
            callbackNilaiTanah = function(row){
                $('#hargaTanah').val( row.harga );
                $('#pembanding1').val(row.nama+";"+row.jalan+";"+row.posisi+";"+row.harga);
                windowOpenNilaiTanah.close();
                // calcAgunan();
            }
        </c:if>
        callbackModal = function(){
            refreshTableDokumenTanah();
            $('#modal-tanah').modal('hide');
        }
        $('#btn-add-doc-bangungan').click(function () {
            $('#form-doc-bangunan')[0].reset();
            $('#form-doc-bangunan').find('input[name=id]').val('');
        });
        $('#dokumenTanah\\.jenisDokumenId').change(function(){
            var opt = $(this).find('option:selected');
            var adaJatuhTempo = opt.data('jatuh-tempo');
            if(adaJatuhTempo === true){
                $('#dokumenTanah\\.tanggalBerakhirHak').removeAttr('disabled');
            } else {
                $('#dokumenTanah\\.tanggalBerakhirHak').val('');
                $('#dokumenTanah\\.tanggalBerakhirHak').attr('disabled', 'true');
            }
        });
        var initFormTanah = function(){
            $('#jenisDokumenId').change(function(){
                var opt = $(this).find('option:selected');
                var adaJatuhTempo = opt.data('jatuh-tempo');
                if(adaJatuhTempo === true){
                    $('#tanggalBerakhirHak').removeAttr('disabled');
                } else {
                    $('#tanggalBerakhirHak').val('');
                    $('#tanggalBerakhirHak').attr('disabled', 'true');
                }
            });
            $('#jenisDokumenId').trigger('change');
            $('#form-doc-tanah').submit(function(){
                var prosesAjax = true;
                if($('#tanggalBerakhirHak').attr('disabled') == undefined && $('#tanggalBerakhirHak').val() == ""){
                    alert('Tanggal berakhir hak harus diisi');
                    prosesAjax = false;
                }
                if(prosesAjax) {
                    $.ajax({
                        type: $(this).attr('method'),
                        cache: false,
                        dataType: "html",
                        url: $(this).attr('action'),
                        data: $(this).serialize(),
                        complete: function (data) {
                            $('#modal-tanah').find(".modal-body").html(data.responseText);
                            initFormTanah();
                        }
                    })
                }
                return false;
            });
            mask_form();
        }
        $('#btn-add-doc-tanah').click(function(){
            $('#modal-tanah').find(".modal-body").load("form-doc-tanah?jenisAktiva=${formBean.jenisAktiva}&lpaCommonId=${formBean.id}", function(){
                initFormTanah();
            });
            return true;
        })
        $('#btn-add-image').click(function(){
            $('#iframe-image').prop('src', "${ctxPath}/app-valuer/form-image?lpaId=${formBean.idLpa}&activaId=${formBean.jenisAktiva}");
            $('#modal-image').modal('show');
        })
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
                        url: 'delete-image?id=' + id + '&surveyId=${formBean.surveyId}&lpaId=${formBean.idLpa}',
                        success: function (data) {
                            fillTbodyImage(data);
                            refreshTableImageOther();
                        }
                    })
                }
            });
        }
        function unbindAndbind() {
            $(".doc-bangunan-edit").unbind('click');
            $(".doc-bangunan-edit").bind('click', function () {
                $('#form-doc-bangunan')[0].reset();
                var id = $(this).data('id');
                $('#myModal').modal('show');
                $('#form-doc-bangunan').find('input[name=id]').val($("#id" + id).val());
                $('#form-doc-bangunan').find('input[name=nomorImb]').val($("#nomorImb" + id).val());
                $('#form-doc-bangunan').find('input[name=tanggalImb]').val($("#tanggalImb" + id).val());
                $('#form-doc-bangunan').find('select[name=ijinSesuaiImb]').val($("#ijinSesuaiImb" + id).val());
                $('#form-doc-bangunan').find('input[name=luasBangunan]').val($("#luasBangunan" + id).val());
                $('#form-doc-bangunan').find('input[name=jumlahLantai]').val($("#jumlahLantai" + id).val());
            });
            $(".doc-bangunan-delete").unbind('click');
            $(".doc-bangunan-delete").bind('click', function () {
                if (confirm('Anda yakin hapus data')) {
                    var id = $(this).data('id');
                    $.ajax({
                        type: "GET",
                        cache: false,
                        dataType: "html",
                        url: 'delete-doc-bangunan?id=' + id + '&commonId=${formBean.id}&idBangunanUsed=${formBean.dokumenBangunan.id}',
                        success: function (data) {
                            fillTbody(data);
                        }
                    })
                }
            });
        }
        function unbindAndbindTanah() {
            $(".doc-tanah-edit").unbind('click');
            $(".doc-tanah-edit").bind('click', function () {
                var id = $(this).data('id');
                $('#modal-tanah').find(".modal-body").load("form-doc-tanah?jenisAktiva=${formBean.jenisAktiva}&lpaCommonId=${formBean.id}&id="+id, function(){
                    initFormTanah();
                    $('#modal-tanah').modal('show');
                });
            });
            $(".doc-tanah-delete").unbind('click');
            $(".doc-tanah-delete").bind('click', function () {
                if (confirm('Anda yakin hapus data')) {
                    var id = $(this).data('id');
                    $.ajax({
                        type: "GET",
                        cache: false,
                        dataType: "html",
                        url: 'delete-doc-tanah?id=' + id + '&commonId=${formBean.id}&idTanahUsed=${formBean.dokumenTanah.id}',
                        success: function (data) {
                            fillTbodyTanah(data);
                        }
                    })
                }
            });
        }
        function fillTbodyImage(html){
            $('#tbody-image').html(html);
            unbindAndbindImage();
        }
        function fillTbodyImageOther(html){
            $('#tbody-image-other').html(html);
        }
        function fillTbodyTanah(html) {
            $('#tbody-doc-tanah').html(html);
            if($('#tbody-doc-tanah tr').length >1){
                $('#doks-tanah').removeClass("aktif-link");
                $('#breakdown-nilai').removeClass("aktif-link");
            }else{
                $('#doks-tanah').addClass("aktif-link");
                $('#breakdown-nilai').addClass("aktif-link");
            }
            $('#dokumenTanah\\.luasTanah').trigger('blur');
            unbindAndbindTanah();
        }
        function fillTbody(html) {
            $('#tbody-doc-bangunan').html(html);
            if($('#tbody-doc-bangunan tr').length >1){
                $('#doks-bgn').removeClass("aktif-link");
            }else{
                $('#doks-bgn').addClass("aktif-link");
            }
            $('#dokumenTanah\\.luasBangunan').trigger('blur');
            unbindAndbind();
        }
        refreshTableDokumenBangunan = function () {
            $.ajax({
                type: "GET",
                cache: false,
                dataType: "html",
                url: 'table-doc-bangunan?idTanahBangunan=${formBean.id}&idBangunanUsed=${formBean.dokumenBangunan.id}',
                success: function (data) {
                    fillTbody(data);
                }
            })
        }
        refreshTableDokumenTanah = function () {
            $.ajax({
                type: "GET",
                cache: false,
                dataType: "html",
                url: 'table-doc-tanah?idTanahBangunan=${formBean.id}&idTanahUsed=${formBean.dokumenTanah.id}',
                success: function (data) {
                    fillTbodyTanah(data);
                }
            })
        }
        refreshTableImage = function(){
            $.ajax({
                type: "GET",
                cache: false,
                dataType: "html",
                url: 'table-image?surveyId=${formBean.surveyId}&lpaId=${formBean.idLpa}',
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
                url: 'table-image-other?lpaId=${formBean.idLpa}',
                success: function (html) {
                    fillTbodyImageOther(html);
                    $('#myModalLain').modal('hide');
                }
            })
        }
        calcLuas = function () {
            var hitungManual = $('input[name=hitungManual]').prop("checked");
            if (!hitungManual) {
                var lantai1 = parseToFloat($('#luasLantai1').inputmask('unmaskedvalue'));
                var lantai2 = parseToFloat($('#luasLantai2').inputmask('unmaskedvalue'));
                var lantai3 = parseToFloat($('#luasLantai3').inputmask('unmaskedvalue'));
                var lantai4 = parseToFloat($('#luasLantai4').inputmask('unmaskedvalue'));
                var lantai5 = parseToFloat($('#luasLantai5').inputmask('unmaskedvalue'));
                var total = lantai1 + lantai2 + lantai3 + lantai4 + lantai5;
                $('#luasBangunan').val(total);
            }
        }
        $('#luasLantai1, #luasLantai2, #luasLantai3, #luasLantai4, #luasLantai5').blur(function () {
            calcLuas();
        })
        $('input[name=hitungManual]').change(function () {
            if ($(this).prop("checked")) {
                $('#luasBangunan').prop('readonly', false);
            } else {
                $('#luasBangunan').prop('readonly', true);
                calcLuas();
            }
        });
        $('#btn-submit-doc-bangungan').click(function () {
            // validation
            if (!$.trim($('#form-doc-bangunan').find('#nomorImb').val()).length) {
                alert('Nomor IMB harus di isi');
                return false;
            }
            if (!$.trim($('#form-doc-bangunan').find('#tanggalImb').val()).length) {
                alert('Tanggal IMB harus di isi');
                return false;
            }
            if (!$.trim($('#form-doc-bangunan').find('#ijinSesuaiImb').val()).length) {
                alert('Ijin Sesuai IMB harus di isi');
                return false;
            }
            if (!$.trim($('#form-doc-bangunan').find('#luasBangunan').val()).length) {
                alert('Luas Bangunan harus di isi');
                return false;
            }
            if (!$.trim($('#form-doc-bangunan').find('#jumlahLantai').val()).length) {
                alert('Jumlah Lantai harus di isi');
                return false;
            }
            if (parseToFloat($('#form-doc-bangunan').find('#jumlahLantai').inputmask('unmaskedvalue')) == 0) {
                alert('Jumlah Lantai harus tidak boleh kosong');
                return false;
            }
            $.ajax({
                type: "POST",
                cache: false,
                dataType: "json",
                data: $('#form-doc-bangunan').serialize(),
                url: $('#form-doc-bangunan').attr('action'),
                success: function (data) {
                    refreshTableDokumenBangunan();
                    $('#myModal').modal('hide');
                }
            })
        })
        $('#dokumenTanah\\.luasTanah').blur(function () {
            var totalTanahAjax = parseToFloat($('#hasil-ajax-totalTanahDokumen').val());
            var totalTanah1 = parseToFloat($('#dokumenTanah\\.luasTanah').inputmask('unmaskedvalue'));
            $('#totalLuasTanahDokumen').val(totalTanahAjax + totalTanah1);
            $('#luasTanahSesuaiTataKota').trigger('change');
            // calcAgunan();
        })
        $('#dokumenBangunan\\.luasBangunan').blur(function () {
            var totalAjax = parseToFloat($('#totalBangunanImb').val());
            var total1 = parseToFloat($('#dokumenBangunan\\.luasBangunan').inputmask('unmaskedvalue'));
            $('#luasBangunanImb').val(totalAjax + total1);
            // calcAgunan();
        })
        <c:if test="${isDesktop}">
            calcAgunan = function () {
                var totalTanah = parseToFloat($('#totalLuasTanahDokumen').inputmask('unmaskedvalue'));
                var totalBangunan = parseToFloat($('#luasBangunanImb').inputmask('unmaskedvalue'));
                var totalFisikTanah =  parseToFloat($('#totalLuasTanahDokumen').inputmask('unmaskedvalue'));
                var totalFisikBangunan =  parseToFloat($('#luasBangunan').inputmask('unmaskedvalue'));
                var hitungManual = $('#hitungManual11').prop('checked');
                var uraian1 = $('#uraian1').val();
                var uraian2 = $('#uraian2').val();
                var berinilai1 = $('input[name=diberiNilaiDokumen1]:checked').val();
                var berinilaiFisik1 = $('input[name=diberiNilaiFisik1]:checked').val();
                var berinilai2 = $('input[name=diberiNilaiDokumen2]:checked').val();
                var berinilaiFisik2 = $('input[name=diberiNilaiFisik2]:checked').val();
                var persen1 = parseToFloat($('#prosentase1').inputmask('unmaskedvalue'));
                var persen2 = parseToFloat($('#prosentase2').inputmask('unmaskedvalue'));
                var hargaTanah = parseToFloat($('#hargaTanah').inputmask('unmaskedvalue'));

                if(berinilai1 == 0){
                    $('#nilaiDokumen1').prop('readonly', true);
                    $('#nilaiDokumen1').val('0');
                }else{
                    $('#nilaiDokumen1').prop('readonly', false);
                }
                if(berinilai2 == 0){
                    $('#nilaiDokumen2').prop('readonly', true);
                    $('#nilaiDokumen2').val('0');
                } else {
                    $('#nilaiDokumen2').prop('readonly', false);
                }
                if(berinilaiFisik1 == 0){
                    $('#nilaiFisik1').val('0');
                }else{
                }
                if(berinilaiFisik2 == 0){
                    $('#nilaiFisik2').prop('readonly', true);
                    $('#nilaiFisik2').val('0');
                } else {
                    $('#nilaiFisik2').prop('readonly', false);
                }

                var nilaiDokumen1 = parseToFloat($('#nilaiDokumen1').inputmask('unmaskedvalue'));
                var nilaiDokumen2 = parseToFloat($('#nilaiDokumen2').inputmask('unmaskedvalue'));
                if(hitungManual){
                    $('#nilaiFisik1').prop('readonly', false);
                    $('#nilaiFisik2').prop('readonly', false);
                    $('#toleransi1').prop('readonly', false);
                    $('#toleransi2').prop('readonly', false);
                    $('#likuidasiDokumen1').prop('readonly', false);
                    $('#likuidasiDokumen2').prop('readonly', false);
                    $('#likuidasiFisik1').prop('readonly', false);
                    $('#likuidasiFisik2').prop('readonly', false);
                    $('#totalSesuaiDokumen').prop('readonly', false);
                    $('#totalSesuaiFisik').prop('readonly', false);
                    $('#totalLikuidasiDokumen').prop('readonly', false);
                    $('#totalLikuidasiFisik').prop('readonly', false);
                } else {
                    $('#nilaiFisik1').prop('readonly', true);
                    // $('#nilaiFisik2').prop('readonly', true);
                    $('#toleransi1').prop('readonly', true);
                    $('#toleransi2').prop('readonly', true);
                    $('#likuidasiDokumen1').prop('readonly', true);
                    $('#likuidasiDokumen2').prop('readonly', true);
                    $('#likuidasiFisik1').prop('readonly', true);
                    $('#likuidasiFisik2').prop('readonly', true);
                    $('#totalSesuaiDokumen').prop('readonly', true);
                    $('#totalSesuaiFisik').prop('readonly', true);
                    $('#totalLikuidasiDokumen').prop('readonly', true);
                    $('#totalLikuidasiFisik').prop('readonly', true);
                    var totalHargaTanah = 0;
                    var totalHargaBangunan = 0;
                    var maxToleransiBangunan = 0;
                    var nilaiFisik1 = hargaTanah * totalFisikTanah;
                    var nilaiFisik2 = parseToFloat($('#nilaiFisik2').inputmask('unmaskedvalue'));

                    if (uraian1 == "tanah") {
                        totalHargaTanah = nilaiDokumen1;
                    } else if (uraian1 == "bangunan") {
                        totalHargaBangunan = nilaiDokumen1;
                        maxToleransiBangunan = nilaiFisik1;
                    }
                    if (uraian2 == "tanah") {
                        totalHargaTanah = nilaiDokumen2;
                    } else if (uraian2 == "bangunan") {
                        totalHargaBangunan = nilaiDokumen2;
                        maxToleransiBangunan = nilaiFisik2;
                    }

                    var totalNilaiDokumen = nilaiDokumen1 + nilaiDokumen2;
                    var totalFisik = nilaiFisik1 + nilaiFisik2;
                    var likuidasi1 = nilaiDokumen1 * persen1 / 100;
                    var likuidasi2 = nilaiDokumen2 * persen2 / 100;
                    var likuidasiFisik1 = nilaiFisik1 * persen1 / 100;
                    var likuidasiFisik2 = nilaiFisik2 * persen2 / 100;
                    var toleransi1 = totalHargaBangunan + (totalHargaBangunan * 20 / 100);
                    var toleransi2 = totalHargaBangunan + (totalHargaBangunan * 30 / 100);
                    if(toleransi1 > maxToleransiBangunan) toleransi1 = maxToleransiBangunan;
                    if(toleransi2 > maxToleransiBangunan) toleransi2 = maxToleransiBangunan;
                    $('input[name=totalSesuaiDokumen]').val(totalNilaiDokumen);
                    $('input[name=totalSesuaiFisik]').val(totalFisik);
                    $('input[name=likuidasiDokumen1]').val(likuidasi1);
                    $('input[name=likuidasiDokumen2]').val(likuidasi2);
                    $('input[name=likuidasiFisik1]').val(likuidasiFisik1);
                    $('input[name=likuidasiFisik2]').val(likuidasiFisik2);
                    $('input[name=totalLikuidasiDokumen]').val(likuidasi1 + likuidasi2);
                    $('input[name=totalLikuidasiFisik]').val(likuidasiFisik1 + likuidasiFisik2);
                    $('input[name=toleransi1]').val(totalHargaTanah + toleransi1);
                    $('input[name=toleransi2]').val(totalHargaTanah + toleransi2);
                    $('input[name=nilaiFisik1]').val(nilaiFisik1);
                    // $('input[name=nilaiFisik2]').val(nilaiFisik2);
                    $('input[name=hargaBangunan]').val(nilaiFisik2/totalFisikBangunan);
                }
            }
        </c:if>
        <c:if test="${!isDesktop}">
            calcAgunan = function () {
                var totalTanah = parseToFloat($('#totalLuasTanahDokumen').inputmask('unmaskedvalue'));
                var totalBangunan = parseToFloat($('#luasBangunanImb').inputmask('unmaskedvalue'));
                var totalFisikTanah =  parseToFloat($('#totalLuasTanahDokumen').inputmask('unmaskedvalue'));
                var totalFisikBangunan =  parseToFloat($('#luasBangunan').inputmask('unmaskedvalue'));
                var hitungManual = $('#hitungManual11').prop('checked');
                var uraian1 = $('#uraian1').val();
                var uraian2 = $('#uraian2').val();
                var berinilai1 = $('input[name=diberiNilaiDokumen1]:checked').val();
                var berinilaiFisik1 = $('input[name=diberiNilaiFisik1]:checked').val();
                var berinilai2 = $('input[name=diberiNilaiDokumen2]:checked').val();
                var berinilaiFisik2 = $('input[name=diberiNilaiFisik2]:checked').val();
                var persen1 = parseToFloat($('#prosentase1').inputmask('unmaskedvalue'));
                var persen2 = parseToFloat($('#prosentase2').inputmask('unmaskedvalue'));

                $('#nilaiDokumen1').prop('readonly', false);
                $('#nilaiFisik1').prop('readonly', false);
                $('#prosentase1').prop('readonly', false);
                $('#nilaiDokumen2').prop('readonly', false);
                $('#nilaiFisik2').prop('readonly', false);
                $('#prosentase2').prop('readonly', false);
                if(berinilai1 == 0){
                    $('#nilaiDokumen1').prop('readonly', true);
                    $('#nilaiDokumen1').val('0');
                }else{
                    $('#nilaiDokumen1').prop('readonly', false);
                }
                if(berinilai2 == 0){
                    $('#nilaiDokumen2').prop('readonly', true);
                    $('#nilaiDokumen2').val('0');
                } else {
                    $('#nilaiDokumen2').prop('readonly', false);
                }
                if(berinilaiFisik1 == 0){
                    $('#nilaiFisik1').prop('readonly', true);
                    $('#nilaiFisik1').val('0');
                }else{
                    $('#nilaiFisik1').prop('readonly', false);
                }
                if(berinilaiFisik2 == 0){
                    $('#nilaiFisik2').prop('readonly', true);
                    $('#nilaiFisik2').val('0');
                } else {
                    $('#nilaiFisik2').prop('readonly', false);
                }

                var hargaTanah = 0; var hargaBangunan = 0;
                var nilaiDokumen1 = parseToFloat($('#nilaiDokumen1').inputmask('unmaskedvalue'));
                var nilaiDokumen2 = parseToFloat($('#nilaiDokumen2').inputmask('unmaskedvalue'));
                var nilaiFisik1 = parseToFloat($('#nilaiFisik1').inputmask('unmaskedvalue'));
                var nilaiFisik2 = parseToFloat($('#nilaiFisik2').inputmask('unmaskedvalue'));
                if(hitungManual){
                    $('#hargaTanah').prop('readonly', false);
                    $('#hargaBangunan').prop('readonly', false);
                    $('#toleransi1').prop('readonly', false);
                    $('#toleransi2').prop('readonly', false);
                    $('#likuidasiDokumen1').prop('readonly', false);
                    $('#likuidasiDokumen2').prop('readonly', false);
                    $('#likuidasiFisik1').prop('readonly', false);
                    $('#likuidasiFisik2').prop('readonly', false);
                    $('#totalSesuaiDokumen').prop('readonly', false);
                    $('#totalSesuaiFisik').prop('readonly', false);
                    $('#totalLikuidasiDokumen').prop('readonly', false);
                    $('#totalLikuidasiFisik').prop('readonly', false);
                    if (uraian2 == "tidak") {
                        $('#nilaiDokumen2').prop('readonly', true);
                        $('#nilaiDokumen2').val('0');
                        $('#nilaiFisik2').prop('readonly', true);
                        $('#nilaiFisik2').val('0');
                        $('#prosentase2').prop('readonly', true);
                        $('#likuidasiDokumen2').prop('readonly', true);
                        $('#likuidasiDokumen2').val('0');
                        $('#likuidasiFisik2').prop('readonly', true);
                        $('#likuidasiFisik2').val('0');
                        $('#hargaBangunan').prop('readonly', true);
                        $('#hargaBangunan').val('0');
                    }
                } else {
                    $('#hargaTanah').prop('readonly', true);
                    $('#hargaBangunan').prop('readonly', true);
                    $('#toleransi1').prop('readonly', true);
                    $('#toleransi2').prop('readonly', true);
                    $('#likuidasiDokumen1').prop('readonly', true);
                    $('#likuidasiDokumen2').prop('readonly', true);
                    $('#likuidasiFisik1').prop('readonly', true);
                    $('#likuidasiFisik2').prop('readonly', true);
                    $('#totalSesuaiDokumen').prop('readonly', true);
                    $('#totalSesuaiFisik').prop('readonly', true);
                    $('#totalLikuidasiDokumen').prop('readonly', true);
                    $('#totalLikuidasiFisik').prop('readonly', true);
                    var totalHargaTanah = 0;
                    var totalHargaBangunan = 0;
                    var maxToleransiBangunan = 0;

                    if (uraian1 == "tanah") {
                        hargaTanah = nilaiFisik1 / totalFisikTanah;
                        totalHargaTanah = nilaiDokumen1;
                    } else if (uraian1 == "bangunan" || uraian1 == "unit") {
                        hargaBangunan = nilaiFisik1 / totalFisikBangunan;
                        totalHargaBangunan = nilaiDokumen1;
                        maxToleransiBangunan = nilaiFisik1;
                    } else {
                        $('#nilaiDokumen1').prop('readonly', true);
                        $('#nilaiFisik1').prop('readonly', true);
                        $('#prosentase1').prop('readonly', true);
                    }
                    if (uraian2 == "tanah") {
                        hargaTanah = nilaiFisik2 / totalFisikTanah;
                        totalHargaTanah = nilaiDokumen2;
                    } else if (uraian2 == "bangunan" || uraian2 == "unit") {
                        hargaBangunan = nilaiFisik2 / totalFisikBangunan;
                        totalHargaBangunan = nilaiDokumen2;
                        maxToleransiBangunan = nilaiFisik2;
                    } else {
                        $('#nilaiDokumen2').prop('readonly', true);
                        $('#nilaiFisik2').prop('readonly', true);
                        $('#prosentase2').prop('readonly', true);
                    }

                    var totalNilaiDokumen = nilaiDokumen1 + nilaiDokumen2;
                    var totalFisik = nilaiFisik1 + nilaiFisik2;
                    var likuidasi1 = nilaiDokumen1 * persen1 / 100;
                    var likuidasi2 = nilaiDokumen2 * persen2 / 100;
                    var likuidasiFisik1 = nilaiFisik1 * persen1 / 100;
                    var likuidasiFisik2 = nilaiFisik2 * persen2 / 100;
                    var toleransi1 = totalHargaBangunan + (totalHargaBangunan * 20 / 100);
                    var toleransi2 = totalHargaBangunan + (totalHargaBangunan * 30 / 100);
                    if(toleransi1 > maxToleransiBangunan) toleransi1 = maxToleransiBangunan;
                    if(toleransi2 > maxToleransiBangunan) toleransi2 = maxToleransiBangunan;
                    $('input[name=totalSesuaiDokumen]').val(totalNilaiDokumen);
                    $('input[name=likuidasiDokumen1]').val(likuidasi1);
                    $('input[name=likuidasiDokumen2]').val(likuidasi2);
                    $('input[name=totalSesuaiFisik]').val(totalFisik);
                    $('input[name=likuidasiFisik1]').val(likuidasiFisik1);
                    $('input[name=likuidasiFisik2]').val(likuidasiFisik2);
                    $('input[name=totalLikuidasiDokumen]').val(likuidasi1 + likuidasi2);
                    $('input[name=totalLikuidasiFisik]').val(likuidasiFisik1 + likuidasiFisik2);
                    $('input[name=hargaTanah]').val(hargaTanah);
                    $('input[name=hargaBangunan]').val(hargaBangunan);
                    $('input[name=toleransi1]').val(totalHargaTanah + toleransi1);
                    $('input[name=toleransi2]').val(totalHargaTanah + toleransi2);
                }
            }
        </c:if>
        <c:if test="${isEksternal}">
            calcAgunanEks = function () {
                var totalTanah = parseToFloat($('#totalLuasTanahDokumen').inputmask('unmaskedvalue'));
                var totalBangunan = parseToFloat($('#luasBangunanImb').inputmask('unmaskedvalue'));
                var totalFisikTanah =  parseToFloat($('#totalLuasTanahDokumen').inputmask('unmaskedvalue'));
                var totalFisikBangunan =  parseToFloat($('#luasBangunan').inputmask('unmaskedvalue'));
                var hitungManual = $('#hitungManualEks1').prop('checked');
                var uraian1 = $('#uraian1Eks').val();
                var uraian2 = $('#uraian2Eks').val();
                var berinilai1 = $('input[name=diberiNilaiDokumen1Eks]:checked').val();
                var berinilai2 = $('input[name=diberiNilaiDokumen2Eks]:checked').val();
                var berinilaiFisik1 = $('input[name=diberiNilaiFisik1Eks]:checked').val();
                var berinilai2 = $('input[name=diberiNilaiDokumen2Eks]:checked').val();
                var berinilaiFisik2 = $('input[name=diberiNilaiFisik2Eks]:checked').val();
                var persen1 = parseToFloat($('#prosentase1Eks').inputmask('unmaskedvalue'));
                var persen2 = parseToFloat($('#prosentase2Eks').inputmask('unmaskedvalue'));

                if(berinilai1 == 0){
                    $('#nilaiDokumen1Eks').prop('readonly', true);
                    $('#nilaiDokumen1Eks').val('0');
                }else{
                    $('#nilaiDokumen1Eks').prop('readonly', false);
                }
                if(berinilai2 == 0){
                    $('#nilaiDokumen2Eks').prop('readonly', true);
                    $('#nilaiDokumen2Eks').val('0');
                } else {
                    $('#nilaiDokumen2Eks').prop('readonly', false);
                }
                if(berinilaiFisik1 == 0){
                    $('#nilaiFisik1Eks').prop('readonly', true);
                    $('#nilaiFisik1Eks').val('0');
                }else{
                    $('#nilaiFisik1Eks').prop('readonly', false);
                }
                if(berinilaiFisik2 == 0){
                    $('#nilaiFisik2Eks').prop('readonly', true);
                    $('#nilaiFisik2Eks').val('0');
                } else {
                    $('#nilaiFisik2Eks').prop('readonly', false);
                }

                var hargaTanah = 0; var hargaBangunan = 0;
                var nilaiDokumen1 = parseToFloat($('#nilaiDokumen1Eks').inputmask('unmaskedvalue'));
                var nilaiDokumen2 = parseToFloat($('#nilaiDokumen2Eks').inputmask('unmaskedvalue'));
                var nilaiFisik1 = parseToFloat($('#nilaiFisik1Eks').inputmask('unmaskedvalue'));
                var nilaiFisik2 = parseToFloat($('#nilaiFisik2Eks').inputmask('unmaskedvalue'));
                if(hitungManual){
                    $('#hargaTanahEks').prop('readonly', false);
                    $('#hargaBangunanEks').prop('readonly', false);
                    $('#toleransi1Eks').prop('readonly', false);
                    $('#toleransi2Eks').prop('readonly', false);
                    $('#likuidasiDokumen1Eks').prop('readonly', false);
                    $('#likuidasiDokumen2Eks').prop('readonly', false);
                    $('#likuidasiFisik1Eks').prop('readonly', false);
                    $('#likuidasiFisik2Eks').prop('readonly', false);
                    $('#totalSesuaiDokumenEks').prop('readonly', false);
                    $('#totalSesuaiFisikEks').prop('readonly', false);
                    $('#totalLikuidasiDokumenEks').prop('readonly', false);
                    $('#totalLikuidasiFisikEks').prop('readonly', false);
                } else {
                    $('#hargaTanahEks').prop('readonly', true);
                    $('#hargaBangunanEks').prop('readonly', true);
                    $('#toleransi1Eks').prop('readonly', true);
                    $('#toleransi2Eks').prop('readonly', true);
                    $('#likuidasiDokumen1Eks').prop('readonly', true);
                    $('#likuidasiDokumen2Eks').prop('readonly', true);
                    $('#likuidasiFisik1Eks').prop('readonly', true);
                    $('#likuidasiFisik2Eks').prop('readonly', true);
                    $('#totalSesuaiDokumenEks').prop('readonly', true);
                    $('#totalSesuaiFisikEks').prop('readonly', true);
                    $('#totalLikuidasiDokumenEks').prop('readonly', true);
                    $('#totalLikuidasiFisikEks').prop('readonly', true);
                    var totalHargaTanah = 0;
                    var totalHargaBangunan = 0;
                    var maxToleransiBangunan = 0;

                    if (uraian1 == "tanah") {
                        hargaTanah = nilaiFisik1 / totalFisikTanah;
                        totalHargaTanah = nilaiDokumen1;
                    } else if (uraian1 == "bangunan") {
                        hargaBangunan = nilaiFisik1 / totalFisikBangunan;
                        totalHargaBangunan = nilaiDokumen1;
                        maxToleransiBangunan = nilaiFisik1;
                    }
                    if (uraian2 == "tanah") {
                        hargaTanah = nilaiFisik2 / totalFisikTanah;
                        totalHargaTanah = nilaiDokumen2;
                    } else if (uraian2 == "bangunan") {
                        hargaBangunan = nilaiFisik2 / totalFisikBangunan;
                        totalHargaBangunan = nilaiDokumen2;
                        maxToleransiBangunan = nilaiFisik2;
                    }

                    var totalNilaiDokumen = nilaiDokumen1 + nilaiDokumen2;
                    var totalFisik = nilaiFisik1 + nilaiFisik2;
                    var likuidasi1 = nilaiDokumen1 * persen1 / 100;
                    var likuidasi2 = nilaiDokumen2 * persen2 / 100;
                    var likuidasiFisik1 = nilaiFisik1 * persen1 / 100;
                    var likuidasiFisik2 = nilaiFisik2 * persen2 / 100;
                    var toleransi1 = totalHargaBangunan + (totalHargaBangunan * 20 / 100);
                    var toleransi2 = totalHargaBangunan + (totalHargaBangunan * 30 / 100);
                    if(toleransi1 > maxToleransiBangunan) toleransi1 = maxToleransiBangunan;
                    if(toleransi2 > maxToleransiBangunan) toleransi2 = maxToleransiBangunan;
                    $('input[name=totalSesuaiDokumenEks]').val(totalNilaiDokumen);
                    $('input[name=likuidasiDokumen1Eks]').val(likuidasi1);
                    $('input[name=likuidasiDokumen2Eks]').val(likuidasi2);
                    $('input[name=totalSesuaiFisikEks]').val(totalFisik);
                    $('input[name=likuidasiFisik1Eks]').val(likuidasiFisik1);
                    $('input[name=likuidasiFisik2Eks]').val(likuidasiFisik2);
                    $('input[name=totalLikuidasiDokumenEks]').val(likuidasi1 + likuidasi2);
                    $('input[name=totalLikuidasiFisikEks]').val(likuidasiFisik1 + likuidasiFisik2);
                    $('input[name=hargaTanahEks]').val(hargaTanah);
                    $('input[name=hargaBangunanEks]').val(hargaBangunan);
                    $('input[name=toleransi1Eks]').val(totalHargaTanah + toleransi1);
                    $('input[name=toleransi2Eks]').val(totalHargaTanah + toleransi2);
                }
            }
        </c:if>
        function initForm() {
            $('input[name=hargaBangunan], input[name^="diberiNilaiDokumen"], select[name^="uraian"],' +
                    'input[name=hargaTanah], input[name^="prosentase"], input[name=hitungManual1], input[name^=nilaiDokumen],' +
                    'input[name^=diberiNilaiFisik], input[name^=nilaiFisik], input[name=hitungManualEks], ' +
                    'input[name="dokumenTanah.luasTanah"], input[name=luasBangunan], input[name="dokumenBangunan.luasBangunan"], ' +
                    'input[name^=luasLantai]')
                    .change(function () {
                        // calcAgunan();
                        <c:if test="${isEksternal}">
                        // calcAgunanEks();
                        </c:if>
                    });
            <c:if test="${isDesktop}">
                $('#nilaiFisik1').prop('readonly', true);
                $('#nilaiFisik2').prop('readonly', true);
            </c:if>
        }
        function hitungTotal() {
            var nilaiDokumen1 = parseToFloat($('#nilaiDokumen1').inputmask('unmaskedvalue'));
            var nilaiDokumen2 = parseToFloat($('#nilaiDokumen2').inputmask('unmaskedvalue'));
            $('input[name=totalSesuaiDokumen]').val(nilaiDokumen1 + nilaiDokumen2);
        }
        $('#btn-save-draft').click(function () {
            $('select').prop('disabled', false);
        })
        $('#btn-save-all').click(function () {
            if($('#dokumenTanah\\.tanggalBerakhirHak').attr('disabled') == undefined && $('#dokumenTanah\\.tanggalBerakhirHak').val() == ""){
                alert('Tanggal berakhir hak harus diisi');
                return false;
            }
            if($('#tanggalProsesKonstruksi').attr('disabled') == undefined && $('#tanggalProsesKonstruksi').val() == ""){
                alert('Tanggal Progress Konstruksi harus diisi');
                return false;
            }
            $('#draft').val(false);
            $('#form-lpa').attr('action', 'save-lpa');
            $('select').prop('disabled', false);
        })
        $('#luasTanahSesuaiTataKota').change(function(){
            var totalTanah = parseToFloat($('#totalLuasTanahDokumen').inputmask('unmaskedvalue'));
            var tataKota = parseToFloat($('#luasTanahSesuaiTataKota').inputmask('unmaskedvalue'));
            $('#luas-tidak-kena').val(totalTanah-tataKota);
        })
        $('#prosesPembangunan').change(function(){
            if ('code' in $(this).find('option:selected').data()) {
                $('#prosesKonstruksi').val($(this).find('option:selected').attr('data-code'));
                $('#prosesKonstruksi').prop('readonly', true);
            } else {
                $('#prosesKonstruksi').val("00");
                $('#prosesKonstruksi').prop('readonly', false);
            }

            if($(this).find('option:selected').text() == 'Tidak Ada' || $(this).find('option:selected').text() == 'Tanah Matang') {
                $('#tanggalProsesKonstruksi').attr("disabled", "true");
            } else {
                $('#tanggalProsesKonstruksi').removeAttr("disabled");
            }
        });
        $('#jenisBangunan').change(function(){
            var typeReport = "${formBean.typeReport}";
            if (typeReport == 'Progress') {
                $.ajax({
                    type: "GET",
                    cache: false,
                    url: 'get-data-param?jenisBangunan=' + $(this).val(),
                    success: function (data) {
                        $('#prosesPembangunan').empty();
                        var option = document.createElement("option");
                        option.text = "-- Select --";
                        $('#prosesPembangunan').append(option);
                        data.forEach(function(item) {
                            var option = document.createElement("option");
                            option.text = item.value;
                            option.value = item.value;
                            option.dataset.code = item.code;
                            $('#prosesPembangunan').append(option);
                        });
                        $('#prosesKonstruksi').val("00");
                        $('#prosesKonstruksi').prop('readonly', true);
                    }
                })
            }
        });

        // main
        $('#prosesPembangunan').trigger('change');
        $('input[name=hitungManual]').trigger('change');
        initForm();
        <c:if test="${formBean.id != null}">
            refreshTableDokumenBangunan();
            refreshTableDokumenTanah();
            // calcAgunan();
            refreshTableImage();
            refreshTableImageOther();
        </c:if>
        $('#dokumenTanah\\.jenisDokumenId').trigger('change');
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

        $('#form-lpa').find('input, textarea, select').prop('disabled', true);

    });
</script>
</body>
</html>