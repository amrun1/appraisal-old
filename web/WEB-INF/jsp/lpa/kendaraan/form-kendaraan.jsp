<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="com.permata.appraisal2.model.PersonLevel" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
    <c:set var="taskPosition" value="<%= PersonLevel.APPRAISAL_VALUER %>"/>
    <title>Appraisal</title>
    <meta http-equiv="Expires" content="-1"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${ctxPath}/js/bootstrap_2.3.2/css/bootstrap.css" rel="stylesheet">
    <link href="${ctxPath}/js/bootstrap-datepicker-1.6.4/css/bootstrap-datepicker.css" rel="stylesheet">
    <link href="${ctxPath}/css/main-app.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${ctxPath}/js/html5shiv.js"></script>
    <script src="${ctxPath}/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="">
    <c:if test="${not empty msg}">
        <div class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${msg}</strong>
        </div>
    </c:if>
    <f:form method="post" modelAttribute="formBean" action="save-kendaraan" class="form-horizontal">
        <f:hidden path="id"/>
        <f:hidden path="lpaKendaraanId"/>
        <f:hidden path="lpaId"/>
        <div class="page-header">
            <b>Data Kendaraan</b>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="jenisKendaraan">Jenis</label>
            <div class="controls">
                <f:input path="jenisKendaraan"/>
                <f:errors path="jenisKendaraan" cssClass="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="merkKendaraan">Merk</label>
            <div class="controls">
                <f:input path="merkKendaraan"/>
                <f:errors path="merkKendaraan" cssClass="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="modelType">Model Tipe</label>
            <div class="controls">
                <f:input path="modelType"/>
                <f:errors path="modelType" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="isiSilinder">Isi Silinder</label>
            <div class="controls">
                <f:input path="isiSilinder"/>
                <f:errors path="isiSilinder" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="tahunPembuatan">Tahun Pembuatan</label>
            <div class="controls">
                <f:input path="tahunPembuatan" data-mask="number" maxlength="4" cssClass="input-mini"/>
                <f:errors path="tahunPembuatan" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="tahunPembuatan">Asal Pembuatan</label>
            <div class="controls">
                <f:input path="asalPembuatan"/>
                <f:errors path="asalPembuatan" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="warna">Warna Kendaraan</label>
            <div class="controls">
                <f:input path="warna"/>
                <f:errors path="warna" class="error help-block"/>
            </div>
        </div>
        <div class="span12" style="margin-bottom: 10px;"></div>
        <div class="control-group span6">
            <label class="control-label" for="noPolisi">No. Polisi</label>
            <div class="controls">
                <f:input path="noPolisi"/>
                <f:errors path="noPolisi" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="noRangka">No. Chasis</label>
            <div class="controls">
                <f:input path="noRangka"/>
                <f:errors path="noRangka" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="noMesin">No. Engine</label>
            <div class="controls">
                <f:input path="noMesin"/>
                <f:errors path="noMesin" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="bahanBakar">Jenis Bahan Bakar</label>
            <div class="controls">
                <f:input path="bahanBakar"/>
                <f:errors path="bahanBakar" class="error help-block"/>
            </div>
        </div>

        <div class="page-header" style="clear: both">
            <b>Data Dokumen</b>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="pajakStnk">Nomor STNK</label>
            <div class="controls">
                <f:input path="pajakStnk"/>
                <f:errors path="pajakStnk" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="jatuhTempoStnk">Jatuh Tempo STNK</label>
            <div class="controls">
                <f:input path="jatuhTempoStnk" data-provide="datepicker"  class="tutup-otomatis" placeholder="DD-MM-YYYY" data-date-format="dd-mm-yyyy"/>
                <f:errors path="jatuhTempoStnk" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="jatuhTempoPajak">Jatuh Tempo Pajak</label>
            <div class="controls">
                <f:input path="jatuhTempoPajak" data-provide="datepicker"  class="tutup-otomatis" placeholder="DD-MM-YYYY" data-date-format="dd-mm-yyyy"/>
                <f:errors path="jatuhTempoPajak" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="noFaktur">No Faktur</label>
            <div class="controls">
                <f:input path="noFaktur"/>
                <f:errors path="noFaktur" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="noBpkb">No BPKB</label>
            <div class="controls">
                <f:input path="noBpkb"/>
                <f:errors path="noBpkb" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="tanggalBpkb">Tanggal Penerbitan BPKB</label>
            <div class="controls">
                <f:input path="tanggalBpkb" data-provide="datepicker"  class="tutup-otomatis" data-date-end-date="0d" placeholder="DD-MM-YYYY" data-date-format="dd-mm-yyyy"/>
                <f:errors path="tanggalBpkb" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="atasNamaBpkb">Atas Nama BPKB</label>
            <div class="controls">
                <f:input path="atasNamaBpkb"/>
                <f:errors path="atasNamaBpkb" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="alamatBpkb">Alamat BPKB</label>
            <div class="controls">
                <f:input path="alamatBpkb"/>
                <f:errors path="alamatBpkb" class="error help-block"/>
            </div>
        </div>

        <div class="page-header" style="clear: both">
            <b>Fungsi dan Kondisi</b>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="penggunaan">Penggunaan</label>
            <div class="controls">
                <f:select path="penggunaan">
                    <f:option value="" label="-- Select --"/>
                    <f:options items="${listPenggunaan}" itemValue="label" itemLabel="label"/>
                </f:select>
                <f:errors path="penggunaan" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="pemakaian">Frekuensi Pemakaiaan</label>
            <div class="controls">
                <f:select path="pemakaian">
                    <f:option value="" label="-- Select --"/>
                    <f:options items="${listPemakaian}" itemValue="label" itemLabel="label"/>
                </f:select>
                <f:errors path="pemakaian" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="kilometer">Kilometer</label>
            <div class="controls">
                <f:input path="kilometer" data-mask="numeric" cssClass="input-mini"/>
                <f:errors path="kilometer" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="body">Kondisi Body</label>
            <div class="controls">
                <f:select path="body">
                    <f:option value="" label="-- Select --"/>
                    <f:options items="${listKondisiBody}" itemValue="label" itemLabel="label"/>
                </f:select>
                <f:errors path="body" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="bumper">Kondisi Bumper</label>
            <div class="controls">
                <f:select path="bumper">
                    <f:option value="" label="-- Select --"/>
                    <f:options items="${listKondisiBumper}" itemValue="label" itemLabel="label"/>
                </f:select>
                <f:errors path="bumper" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="cat">Kondisi Cat</label>
            <div class="controls">
                <f:select path="cat">
                    <f:option value="" label="-- Select --"/>
                    <f:options items="${listKondisiCat}" itemValue="label" itemLabel="label"/>
                </f:select>
                <f:errors path="cat" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="interior">Kondisi Interior</label>
            <div class="controls">
                <f:select path="interior">
                    <f:option value="" label="-- Select --"/>
                    <f:options items="${listKondisiInterior}" itemValue="label" itemLabel="label"/>
                </f:select>
                <f:errors path="interior" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="eksterior">Kondisi Eksterior</label>
            <div class="controls">
                <f:select path="eksterior">
                    <f:option value="" label="-- Select --"/>
                    <f:options items="${listKondisiEksterior}" itemValue="label" itemLabel="label"/>
                </f:select>
                <f:errors path="eksterior" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="mesin">Kondisi Mesin</label>
            <div class="controls">
                <f:select path="mesin">
                    <f:option value="" label="-- Select --"/>
                    <f:options items="${listKondisiMesin}" itemValue="label" itemLabel="label"/>
                </f:select>
                <f:errors path="mesin" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="ban">Kondisi Ban</label>
            <div class="controls">
                <f:select path="ban">
                    <f:option value="" label="-- Select --"/>
                    <f:options items="${listKondisiBan}" itemValue="label" itemLabel="label"/>
                </f:select>
                <f:errors path="ban" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6" style="margin-bottom: 3px;">
            <label class="control-label" for="catatan">Catatan</label>
            <div class="controls">
                <f:textarea path="catatan" cols="3"/>
                <f:errors path="catatan" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="nilaiPasar">Nilai Pasar</label>
            <div class="controls">
                <f:input path="nilaiPasar" data-mask="luas-tanah" cssClass="input-medium"/>
                <f:errors path="nilaiPasar" class="error help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="nilaiDokumen">Nilai Dokumen</label>
            <div class="controls">
                <f:input path="nilaiDokumen" data-mask="luas-tanah" cssClass="input-medium"/>
                <f:errors path="nilaiDokumen" class="error help-block"/>
            </div>
        </div>
        <c:if test="${roleActive.level == taskPosition}">
        <div class="control-group" style="clear: both">
            <label class="control-label">&nbsp;</label>
            <div class="controls">
                <button type="submit" class="btn btn-success"><i class="icon-white icon-ok-circle"></i> Save</button>
                <button type="reset" class="btn btn-danger"><i class="icon-white icon-refresh"></i> Reset</button>
            </div>
        </div>
        </c:if>
    </f:form>
</div>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<script src="${ctxPath}/js/jquery.inputmask.bundle.min.js"></script>
<script src="${ctxPath}/js/bootstrap-datepicker-1.6.4/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
    $(document).ready(function () {

    });
</script>
</body>
</html>