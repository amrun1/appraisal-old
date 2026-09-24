<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<c:set var="isEdit" value="${formBean.id != null}"/>
<c:set var="isRwa" value="${formBean.jenisLaporan == 'RWA'}"/>
<c:set var="isDesktop" value="${formBean.jenisAppraisal == 'Desktop'}"/>
<c:set var="isIndikasi" value="${formBean.jenisAppraisal == 'Indikasi'}"/>
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
    <link href="${ctxPath}/js/bootstrap_2.3.2/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="${ctxPath}/js/bootstrap_2.3.2/css/docs.css" rel="stylesheet">
    <link href="${ctxPath}/js/bootstrap_2.3.2/js/google-code-prettify/prettify.css" rel="stylesheet">
    <link href="${ctxPath}/css/main-app.css" rel="stylesheet">

</head>
<body>
<div class="container">
    <f:form method="post" modelAttribute="formBean" action="save" class="form-horizontal" cssStyle="margin-top: 50px;">
        <f:hidden path="idJenisAppraisal"/>
        <f:hidden path="jenisAppraisal"/>
        <f:hidden path="jenisLaporan"/>
        <div class="page-header-page">
            <div class="absolute-right input-append">
                <c:if test="${!isGr}">

                <c:if test="${formBean.countAttactment > 0?'false':'true' && formBean.id != null }">
                <button type="button" onclick="gotoUrl('${ctxPath}/data-detail/deleteDetail?id=${formBean.id}')" class="btn btn-danger" ><i class="icon-white icon-remove"></i> Delete Detail
                 </c:if>

                <button type="submit" class="btn btn-success"><i class="icon-white icon-ok"></i> Save</button>
                <button type="reset" class="btn btn-danger"><i class="icon-white icon-repeat"></i> Reset</button>
                </c:if>
            </div>
            <h1>Order Data Detail Form</h1>
        </div>
        <c:if test="${not empty msg}">
            <div class="alert alert-success">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>${msg}</strong>
            </div>
        </c:if>

        <f:input path="id" type="hidden"/>
        <f:input path="dataCommonId" type="hidden"/>
        <div class="control-group">
            <label class="control-label" for="categoryCode">Jenis Kategori</label>
            <div class="controls">
                <f:select path="categoryCode" disabled="${formBean.countAttactment > 0 ? 'true' : (isRwa ? 'true' : 'false')}">
                    <f:option value="" label="-- Select --"/>
                    <f:options items="${lvJenisCategory}" itemValue="value" itemLabel="label"/>
                </f:select>
                <f:errors path="categoryCode" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="assetTypeId">Jenis Aktiva</label>
            <div class="controls">
                <f:select path="assetTypeId" disabled="${formBean.countAttactment > 0 ? 'true' : 'false'}"/>
                <f:errors path="assetTypeId" class="error has-error"/>
            </div>
        </div>
        <c:if test="${formBean.indikasi}">
            <div class="control-group">&nbsp;</div>
            <div class="control-group">
                <label class="control-label" for="luasTanah">Luas Tanah</label>
                <div class="controls">
                    <f:input path="luasTanah" cssClass="input-mini" data-mask="luas-tanah"/>
                    <f:errors path="luasTanah" class="error has-error"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="luasBangunan">Luas Bangunan</label>
                <div class="controls">
                    <f:input path="luasBangunan" cssClass="input-mini" data-mask="luas-tanah"/>
                    <f:errors path="luasBangunan" class="error has-error"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="jumlahLantai">Jumlah Lantai</label>
                <div class="controls">
                    <f:input path="jumlahLantai" cssClass="input-mini" data-mask="number"/>
                    <f:errors path="jumlahLantai" class="error has-error"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="hargaJual">Harga Jual</label>
                <div class="controls">
                    <f:input path="hargaJual" cssClass="input-small" data-mask="luas-tanah"/>
                    <f:errors path="hargaJual" class="error has-error"/>
                </div>
            </div>
        </c:if>
        <div class="control-group">&nbsp;</div>
        <div class="control-group">
            <label class="control-label" for="homeApartment">Perumahan / Apartemen</label>
            <div class="controls">
                <%--<div class="input-append">--%>
                    <f:input path="homeApartment" type="text" class="form-control"/>
                    <%--<button type="button" id="btn-lookup-complex" class="btn btn-info"><i
                            class="icon-white icon-search"></i> Complek Perumahan
                    </button>
                </div>--%>
                <f:errors path="homeApartment" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="clusterTower">Cluster / Tower</label>
            <div class="controls">
                <f:input path="clusterTower" type="text" class="form-control"/>
                <f:errors path="clusterTower" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="blokFloor">Block / Lantai</label>
            <div class="controls">
                <f:input path="blokFloor" type="text" class="form-control"/>
                <f:errors path="blokFloor" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="alamat">Alamat Agunan</label>
            <div class="controls">
                <f:textarea path="alamat" rows="3" cssClass="input-large"/>
                <f:errors path="alamat" class="error has-error"/>
            </div>
        </div>
        <div class="control-group" style="margin-top: 4px;">
            <label class="control-label" for="rw">RW</label>
            <div class="controls">
                <f:input path="rw" cssClass="input-mini" maxlength="4" data-mask="number"/>
                <f:errors path="rw" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="rt">RT</label>
            <div class="controls">
                <f:input path="rt" cssClass="input-mini" maxlength="4" data-mask="number"/>
                <f:errors path="rt" class="error has-error"/>
            </div>
        </div>


        <div class="control-group" id="yyy">
            <label class="control-label" for="number">Nomor</label>
            <div class="controls">
                <f:input path="number" cssClass="input-mini" maxlength="8" onkeypress="return IsAlphaNumeric(event);" ondrop="return false;"  onpaste= "return false;" />
                <f:errors path="number" class="error has-error"/>
            </div>
            <span id="error" style="color: Red; display: none">* Special Characters not allowed </span>
        </div>


        <div class="control-group">&nbsp;</div>
        <div class="control-group">
            <label class="control-label" for="zipCode">Kode POS</label>
            <div class="controls">
                <div class="input-append">
                    <f:input path="zipCode" type="text" class="form-control" placeholder="Kode POS" readonly="true"/>
                    <c:if test="${!isGr}">
                    <button type="button" id="btn-lookup-zip-code" class="btn btn-info"><i
                            class="icon-white icon-search"></i> Kode POS
                    </button>
                    <button type="button" id="btn-clear-zip-code" class="btn btn-danger"><i class="icon-white icon-remove"></i> Clear
                    </button>
                    </c:if>
                </div>
                <f:errors path="zipCode" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="province">Propinsi</label>
            <div class="controls">
                <f:input path="province" type="text" class="form-control" readonly="true"/>
                <f:errors path="province" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="city">Kota / Kabupaten</label>
            <div class="controls">
                <f:input path="city" type="text" class="form-control" readonly="true"/>
                <f:errors path="city" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="district">Kecamatan</label>
            <div class="controls">
                <f:input path="district" type="text" class="form-control" readonly="true"/>
                <f:errors path="district" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="subDistrict">Kelurahan</label>
            <div class="controls">
                <f:input path="subDistrict" type="text" class="form-control" readonly="true"/>
                <f:errors path="subDistrict" class="error has-error"/>
            </div>
        </div>
        <div class="control-group" style="display: none;">
            <label class="control-label" for="picName">Nama PIC</label>
            <div class="controls">
                <f:input path="picName" type="text" class="form-control" readonly="true"/>
                <f:errors path="picName" class="error has-error"/>
            </div>
        </div>
    </f:form>
</div>
<!--[if lt IE 9]>
<script src="${ctxPath}/js/html5shiv.js"></script>
<script src="${ctxPath}/js/respond.min.js"></script>
<![endif]-->
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<script src="${ctxPath}/js/jquery.inputmask.bundle.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
    var windowOpenKodePos;
    var windowOpenKomplek;
    var callbackKodePos = function (row) {
        //console.log(row);
        // bind to form
        $('input[name=zipCode]').val(row.code);
        $('input[name=province]').val(row.province);
        $('input[name=city]').val(row.city);
        $('input[name=district]').val(row.district);
        $('input[name=subDistrict]').val(row.subDistrict);
        $('input[name=picName]').val(row.picName);
        windowOpenKodePos.close();
    }
    var callbackHouseComplex = function (row) {
        //console.log(row);
        $('input[name=homeApartment]').val(row.name);
        $('input[name=name]').val(row.address);
        windowOpenKomplek.close();
    }

    var specialKeys = new Array();
    specialKeys.push(8); //Backspace
    specialKeys.push(9); //Tab
    specialKeys.push(46); //Delete
    specialKeys.push(36); //Home
    specialKeys.push(35); //End
    specialKeys.push(37); //Left
    specialKeys.push(39); //Right

    function IsAlphaNumeric(e) {
        var keyCode = e.keyCode == 0 ? e.charCode : e.keyCode;
        var ret = ((keyCode >= 48 && keyCode <= 57) ||
                (keyCode >= 65 && keyCode <= 90) || (keyCode >= 97 && keyCode <= 122) ||
                (specialKeys.indexOf(e.keyCode) != -1 && e.charCode != e.keyCode));

        document.getElementById("error").style.display = ret ? "none" : "inline";
        return ret;
    }


    function f(o){
        o.value=o.value.toLowerCase().replace(/([^0-9a-z])/g,"");
    }
    $(document).ready(function () {
        mask_form();
        $('#btn-lookup-zip-code').click(function () {
            windowOpenKodePos = window.open("${ctxPath}/master/zip-code/lookup", "_blank");
        });
        $('#btn-lookup-complex').click(function () {
            windowOpenKomplek = window.open("${ctxPath}/master/house-complex/lookup", "_blank");
        });
        $('#btn-clear-zip-code').click(function () {
            $('input[name=zipCode]').val('');
            $('input[name=province]').val('');
            $('input[name=city]').val('');
            $('input[name=district]').val('');
            $('input[name=subDistrict]').val('');
            $('input[name=picName]').val('');
        });
        $('#categoryCode').change(function () {
            fillJenisAktive($(this).val());
        })

        // loop from backend
        var listJenisAktiva = new Array();
        <c:forEach items="${lvJenisAktiva}" varStatus="status" var="row">
            var add = true;
            <c:if test="${isIndikasi && !(fn:containsIgnoreCase(row.label, 'tinggal') || fn:containsIgnoreCase(row.label, 'rukan') || fn:containsIgnoreCase(row.label, 'vila'))}">
                add = false;
            </c:if>
            if(add){
                var jenisAktifa = new Object();
                jenisAktifa.id = "${row.id}";
                jenisAktifa.category = "${row.value}";
                jenisAktifa.name = "${row.label}";
                listJenisAktiva.push(jenisAktifa);
            }
        </c:forEach>

        <c:if test="${isRwa || isDesktop || isIndikasi}">
            $('#categoryCode option').each(function(k, v){
                var text = $(v).text();
                if(text.toLowerCase().search("tanah") != -1){
                    $('#categoryCode').val($(v).val());
                    $('#categoryCode').prop('disabled', true);
                    return;
                }
            })
        </c:if>

        var fillJenisAktive = function (categoryId) {
            $('#assetTypeId').empty();
            $('#assetTypeId').append('<option value="">-- Select --</option>');
            var selected = "${formBean.assetTypeId}";

            for(var i=0 ; i<listJenisAktiva.length ; i++){
                var row_ = listJenisAktiva[i];
                if(categoryId == row_.category) {
                    if(selected == row_.id){
                        $('#assetTypeId').append('<option value="' + row_.id + '" selected>' + row_.name + '</option>');
                    } else {
                        $('#assetTypeId').append('<option value="' + row_.id + '">' + row_.name + '</option>');
                    }
                }
            }

            <c:if test="${isDesktop}">
                $('#assetTypeId option').each(function(k, v){
                    var text = $(v).text();
                    if(text.toLowerCase().search("tinggal") != -1){
                        $('#assetTypeId').val($(v).val());
                        $('#assetTypeId').prop('disabled', true);
                        return;
                    }
                })
            </c:if>
        }

        $('#formBean').submit(function(){
            $('select').prop('disabled', false);
        })
        fillJenisAktive($('#categoryCode').val());
        <s:hasBindErrors name="formBean">
        <c:forEach var="error" items="${errors.allErrors}">showError('${error.field}');</c:forEach>
        </s:hasBindErrors>
    })
</script>
</body>
</html>