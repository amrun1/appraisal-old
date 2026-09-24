<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.permata.appraisal2.model.PersonLevel" %>
<%@ page import="com.permata.appraisal2.common.AppConstans" %>
<c:set var="kendaraan" value="<%=AppConstans.JenisKategoriCode.KENDARAAN%>"/>
<c:set var="lnb" value="<%=AppConstans.JenisKategoriCode.LNB%>"/>
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
    <title>Appraisal Stock</title>
    <meta http-equiv="Expires" content="-1"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${ctxPath}/js/bootstrap_2.3.2/css/bootstrap.css" rel="stylesheet">
    <link href="${ctxPath}/js/bootstrap_2.3.2/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="${ctxPath}/js/bootstrap_2.3.2/css/docs.css" rel="stylesheet">
    <link href="${ctxPath}/js/bootstrap_2.3.2/js/google-code-prettify/prettify.css" rel="stylesheet">
    <link href="${ctxPath}/js/datatables.min.css" rel="stylesheet">
    <link href="${ctxPath}/js/bootstrap-datepicker-1.6.4/css/bootstrap-datepicker.min.css" rel="stylesheet">
    <link href="${ctxPath}/css/main-app.css" rel="stylesheet">

    <!--[if lt IE 9]>
    <script src="${ctxPath}/js/html5shiv.js"></script>
    <script src="${ctxPath}/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <div class="page-header hide">
        <h3>Survey Data Inspeksi</h3>
    </div>
    <c:if test="${not empty msg}">
        <div class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${msg}</strong>
        </div>
    </c:if>
    <f:form method="post" modelAttribute="formBean" action="save-survey-inspeksi?${_csrf.parameterName}=${_csrf.token}"
            class="form-horizontal"
            enctype="multipart/form-data">
        <f:hidden path="id"/>
        <f:hidden path="idDetail"/>

        <div class="page-header" style="clear: both">
            <h5>Data Dokumen</h5>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="jenisBarang">Jenis Barang</label>
            <div class="controls">
                <f:input path="jenisBarang"/>
                <f:errors path="jenisBarang" cssClass="help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="nomorDaftar">Nomor Daftar Persediaan Barang</label>
            <div class="controls">
                <f:input path="nomorDaftar"/>
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
        <div class="control-group span6" hidden>
            <label class="control-label" for="subTotalGoods">Sub Total Barang Jadi</label>
            <div class="controls">
                <f:input path="subTotalGoods"/>
                <f:errors path="subTotalGoods" cssClass="help-block"/>
            </div>
        </div>
        <div class="control-group span6" hidden>
            <label class="control-label" for="subTotalGoodsFisik">Sub Total Barang Jadi Fisik</label>
            <div class="controls">
                <f:input path="subTotalGoodsFisik"/>
                <f:errors path="subTotalGoodsFisik" cssClass="help-block"/>
            </div>
        </div>
        <div class="control-group span6" hidden>
            <label class="control-label" for="subTotalHalfGoods">Sub Total Barang Setengah Jadi</label>
            <div class="controls">
                <f:input path="subTotalHalfGoods"/>
                <f:errors path="subTotalHalfGoods" cssClass="help-block"/>
            </div>
        </div>
        <div class="control-group span6" hidden>
            <label class="control-label" for="subTotalHalfGoodsFisik">Sub Total Barang Setengah Jadi Fisik</label>
            <div class="controls">
                <f:input path="subTotalHalfGoodsFisik"/>
                <f:errors path="subTotalHalfGoodsFisik" cssClass="help-block"/>
            </div>
        </div>
        <div class="control-group span6" hidden>
            <label class="control-label" for="subTotalRawMaterial">Sub Total Bahan Baku</label>
            <div class="controls">
                <f:input path="subTotalRawMaterial"/>
                <f:errors path="subTotalRawMaterial" cssClass="help-block"/>
            </div>
        </div>
        <div class="control-group span6" hidden>
            <label class="control-label" for="subTotalRawMaterialFisik">Sub Total Bahan Baku Fisik</label>
            <div class="controls">
                <f:input path="subTotalRawMaterialFisik"/>
                <f:errors path="subTotalRawMaterialFisik" cssClass="help-block"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="catatan">Catatan Hasil Survey</label>
            <div class="controls">
                <f:textarea path="catatan" rows="3"/>
                <f:errors path="catatan" class="error has-error"/>
            </div>
        </div>
        <%--<div class="control-group span6">--%>
        <%--<label class="control-label" for="peralatanGudang">Catatan Dari Hasil Kunjungan</label>--%>
        <%--<div class="controls">--%>
        <%--<f:input path="infoAlamatTempat"/>--%>
        <%--<f:errors path="infoAlamatTempat" cssClass="help-block"/>--%>
        <%--</div>--%>
        <%--</div>--%>


        <div class="input-group">
            <div class="input-group-addon">
                <span class="icon icon-"></span>
            </div>
        </div>
        <c:if test="${showButton && !inquery}">
            <div class="control-group span12">
                <label class="control-label">&nbsp;</label>
                <div class="controls">
                    <button type="submit" class="btn btn-success"><i class="icon-white icon-ok-circle"></i> Save
                    </button>
                    <button id="btn-reset" type="reset" class="btn btn-warning"><i class="icon-white icon-refresh"></i>
                        Reset
                    </button>
                </div>
            </div>
        </c:if>
    </f:form>

</div>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<script src="${ctxPath}/js/bootstrap-datepicker-1.6.4/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
    // $(document).off('.datepicker.data-api');
    $(document).ready(function () {

    });
</script>
</body>
</html>