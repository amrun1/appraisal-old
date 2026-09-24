<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.permata.appraisal2.model.PersonLevel" %>
<%@ page import="com.permata.appraisal2.common.AppConstans" %>
<c:set var="surveyor" value="<%=PersonLevel.APPRAISAL_SURVEYOR%>"/>
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
        <f:hidden path="vesselId"/>
        <f:hidden path="idPoint"/>
        <f:hidden path="detailId"/>
        <f:hidden path="category"/>
        <f:hidden path="activa"/>
        <div class="page-header-page">
            <div class="absolute-right input-append">
                <c:if test="${showButton && !inquery}">
                    <button type="submit" class="btn btn-success"><i class="icon-white icon-check"></i> Save</button>
                    <button type="reset" class="btn btn-primary"><i class="icon-white icon-refresh"></i> Reset</button>
                    <button type="button" onclick="gotoUrl('${ctxPath}/app-surveyor/view?id=${formBean.idCommon}');" class="btn btn-warning"><i class="icon-white icon-arrow-left"></i> Back</button>
                </c:if>
                <c:if test="${!showButton}">
                    <button type="button" onclick="gotoUrl('${ctxPath}/app-approval/view?id=${formBean.idCommon}');" class="btn btn-warning"><i class="icon-white icon-arrow-left"></i> Back</button>
                </c:if>
            </div>
            <h1>Data Survey Mesin Vessel</h1>
        </div>
        <div class="clearfix" style="margin-top:60px"></div>
        <c:if test="${not empty msg}">
            <div class="alert alert-success">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>${msg}</strong>
            </div>
        </c:if>
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#menu1">Data Umum <span class="badge badge-warning hide"></span></a></li>
            <li><a data-toggle="tab" href="#menu2">Data Vessel <span class="badge badge-warning hide"></span></a></li>
            <li><a data-toggle="tab" href="#menu6">Data Mesin Vessel <span class="badge badge-warning hide"></span></a></li>
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
                <div style="clear: both; margin-top: 10px;">&nbsp;</div>
                <c:if test="${formBean.id != null}">
                    <div class="page-header" style="clear: both">
                        <c:if test="${showButton && !inquery}">
                            <div class="absolute-right">
                                <a href="${ctxPath}/survey-vessel/data-image?commonId=${formBean.id}&detailId=${formBean.detailId}" class="btn btn-success"><i
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
                                                    <a href="${ctxPath}/survey-vessel/data-image?id=${row.id}&commonId=${row.commonId}&detailId=${formBean.detailId}"
                                                       class="btn btn-success"><i class="icon-white icon-edit"></i></a>
                                                    <a href="${ctxPath}/survey-vessel/delete-image?id=${row.id}&commonId=${row.commonId}&detailId=${formBean.detailId}"
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
                url: '${ctxPath}/app-valuer/table-image?surveyId=&lpaId=${formBean.id}',
                success: function (html) {
                    fillTbodyImage(html);
                    $('#modal-image').modal('hide');
                }
            })
        }
        function fillTbodyImage(html){
            $('#tbody-image').html(html);
            unbindAndbindImage();
        }

        // main app thread
        <c:if test="${formBean.id != null}">
        </c:if>
    });
</script>
</body>
</html>