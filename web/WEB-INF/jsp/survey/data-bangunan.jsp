<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.permata.appraisal2.model.PersonLevel" %>
<c:set var="showButton" value="false"/>
<c:set var="surveyor" value="<%=PersonLevel.APPRAISAL_SURVEYOR%>"/>
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
    <link href="${ctxPath}/css/main-app.css" rel="stylesheet">

    <!--[if lt IE 9]>
    <script src="${ctxPath}/js/html5shiv.js"></script>
    <script src="${ctxPath}/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <div class="page-header">
        <h3>Survey Data Bangunan</h3>
    </div>
    <c:if test="${not empty msg}">
        <div class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${msg}</strong>
        </div>
    </c:if>
    <f:form method="post" modelAttribute="formBean" action="save-bangunan?${_csrf.parameterName}=${_csrf.token}"
            class="form-horizontal"
            enctype="multipart/form-data">
        <f:hidden path="id"/>
        <f:hidden path="detailId"/>
        <div class="control-group span6">
            <label class="control-label" for="luas">Luas Total bangunan</label>
            <div class="controls">
                <div class="input-append">
                    <f:input path="luas" cssClass="input-small" data-mask="luas-tanah"/>
                    <span class="btn btn-info">M<sup>2</sup></span>
                </div>
                <div>
                    <f:checkbox path="manual" id="manual"></f:checkbox>
                    <label for="manual" style="display: inline">Hitung Manual</label>
                </div>
                <f:errors path="luas" class="error has-error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="lantai1">Luas Lantai 1</label>
            <div class="controls">
                <div class="input-append">
                    <f:input path="lantai1" cssClass="input-small" data-mask="luas-tanah" onblur="calcLuas()"/>
                    <span class="btn btn-info">M<sup>2</sup></span>
                </div>
                <f:errors path="lantai1" class="error has-error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="lantai2">Luas Lantai 2</label>
            <div class="controls">
                <div class="input-append">
                    <f:input path="lantai2" cssClass="input-small" data-mask="luas-tanah" onblur="calcLuas()" disabled="true"/>
                    <span class="btn btn-info">M<sup>2</sup></span>
                </div>
                <f:errors path="lantai2" class="error has-error" />
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="lantai3">Luas Lantai 3</label>
            <div class="controls">
                <div class="input-append">
                    <f:input path="lantai3" cssClass="input-small" data-mask="luas-tanah" onblur="calcLuas()" disabled="true"/>
                    <span class="btn btn-info">M<sup>2</sup></span>
                </div>
                <f:errors path="lantai3" class="error has-error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="lantai4">Luas Lantai 4</label>
            <div class="controls">
                <div class="input-append">
                    <f:input path="lantai4" cssClass="input-small" data-mask="luas-tanah" onblur="calcLuas()" disabled="true"/>
                    <span class="btn btn-info">M<sup>2</sup></span>
                </div>
                <f:errors path="lantai4" class="error has-error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="lantai5">Luas Lantai 5</label>
            <div class="controls">
                <div class="input-append">
                    <f:input path="lantai5" cssClass="input-small" data-mask="luas-tanah" onblur="calcLuas()" disabled="true"/>
                    <span class="btn btn-info">M<sup>2</sup></span>
                </div>
                <f:errors path="lantai5" class="error has-error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="jumlahLantai">Jumlah Lantai</label>
            <div class="controls">
                <f:input path="jumlahLantai" cssClass="input-mini" data-mask="number" maxlength="2"/>
                <f:errors path="jumlahLantai" class="error has-error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="jumlahLantaiTampakDepan">Jumlah Lantai Tampak Depan</label>
            <div class="controls">
                <f:input path="jumlahLantaiTampakDepan" cssClass="input-mini" data-mask="number" maxlength="2"/>
                <f:errors path="jumlahLantaiTampakDepan" class="error has-error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="peruntukan">Peruntukan</label>
            <div class="controls">
                <f:select path="peruntukan">
                    <f:option value="">-- Select --</f:option>
                    <f:options items="${listPeruntukanBangunan}" itemLabel="label" itemValue="label"/>
                </f:select>
                <f:errors path="peruntukan" class="error has-error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="kelas">Kelas Bangunan</label>
            <div class="controls">
                <f:select path="kelas">
                    <f:option value="">-- Select --</f:option>
                    <f:options items="${listKelasBangunan}" itemLabel="label" itemValue="label"/>
                </f:select>
                <f:errors path="kelas" class="error has-error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="jenis">Jenis Bangunan</label>
            <div class="controls">
                <f:select path="jenis">
                    <f:option value="">-- Select --</f:option>
                    <f:options items="${listJenisBangunan}" itemLabel="label" itemValue="label"/>
                </f:select>
                <f:errors path="jenis" class="error has-error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="tahunDibangun">Tahun Dibangun</label>
            <div class="controls">
                <f:input path="tahunDibangun" cssClass="input-mini" maxlength="4" data-mask="number"/>
                <f:errors path="tahunDibangun" class="error has-error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="tahunRenovasi">Tahun Renovasi</label>
            <div class="controls">
                <f:input path="tahunRenovasi" cssClass="input-mini" maxlength="4" data-mask="number"/>
                <f:errors path="tahunRenovasi" class="error has-error"/>
            </div>
        </div>
        <div class="control-group span6">
            <label class="control-label" for="kondisiUmum">Kondisi bangunan</label>
            <div class="controls">
                <f:input path="kondisiUmum" class="form-control"/>
                <f:errors path="kondisiUmum" class="error has-error"/>
            </div>
        </div>
        <div class="control-group span12">
            <label class="control-label">&nbsp;</label>
            <div class="controls">
                <c:if test="${showButton}">
                    <button type="submit" class="btn btn-success"><i class="icon-white icon-ok-circle"></i> Save
                    </button>
                    <button id="btn-reset" type="reset" class="btn btn-danger"><i class="icon-white icon-refresh"></i>
                        Reset
                    </button>
                </c:if>
                <button type="button" class="btn btn-warning"
                        onclick="gotoUrl('show-bangunan?detailId=${formBean.detailId}')"><i
                        class="icon-white icon-step-backward"></i> Back
                </button>
            </div>
        </div>
    </f:form>
</div>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<script src="${ctxPath}/js/jquery.inputmask.bundle.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
    var calcLuas;
    $(document).ready(function () {



        calcLuas = function () {
            var hitungManual = $('#manual').prop("checked");
            var l1 = parseFloat($('#lantai1').inputmask('unmaskedvalue'));
            var l2 = parseFloat($('#lantai2').inputmask('unmaskedvalue'));
            var l3 = parseFloat($('#lantai3').inputmask('unmaskedvalue'));
            var l4 = parseFloat($('#lantai4').inputmask('unmaskedvalue'));

            active2 =  (l1 > 0);
            active3 =  (l2 > 0 && l1 > 0);
            active4 =  (l2 > 0 && l1 > 0 && l3 > 0);
            active5 =  (l2 > 0 && l1 > 0 && l3 > 0 && l4 > 0);

            if (!active2){
                $('#lantai2').val(0);
                $('#lantai3').val(0);
                $('#lantai4').val(0);
                $('#lantai5').val(0);

            }
            if (!active3){
                $('#lantai3').val(0);
                $('#lantai4').val(0);
                $('#lantai5').val(0);

            }
            if (!active4){
                $('#lantai4').val(0);
                $('#lantai5').val(0);
            }

            if (!active5){
                $('#lantai5').val(0);

            }

               $('#lantai2').prop("disabled",!active2);
               $('#lantai3').prop("disabled",!active3);
               $('#lantai4').prop("disabled",!active4);
               $('#lantai5').prop("disabled",!active5);


            if (!hitungManual) {
                var lantai1 = parseFloat($('#lantai1').inputmask('unmaskedvalue'));
                var lantai2 = parseFloat($('#lantai2').inputmask('unmaskedvalue'));
                var lantai3 = parseFloat($('#lantai3').inputmask('unmaskedvalue'));
                var lantai4 = parseFloat($('#lantai4').inputmask('unmaskedvalue'));
                var lantai5 = parseFloat($('#lantai5').inputmask('unmaskedvalue'));
                

                var total = (isNaN(lantai1)?0:lantai1) + (isNaN(lantai2)?0:lantai2)  + (isNaN(lantai3)?0:lantai3)  + (isNaN(lantai4)?0:lantai4)  + (isNaN(lantai5)?0:lantai5) ;

                $('#luas').val(total);



                var jml = ( (lantai1==0 || lantai1==null || isNaN(lantai1) )?0:1) + ( (lantai2==0 ||lantai2==null || isNaN(lantai2) )?0:1) + ( (lantai3==0 ||lantai3==null || isNaN(lantai3) )?0:1) + ( (lantai4==0 ||lantai4==null || isNaN(lantai4) )?0:1) +( (lantai5==0 ||lantai5==null || isNaN(lantai5) )?0:1);

                $('#jumlahLantai').val(jml);


            }
        }

        $('#manual').change(function () {
            if($('#manual').prop("checked")){
                $('#luas').prop('readonly', false);
            } else {
                $('#luas').prop('readonly', true);
                calcLuas();
            }
        });
        $('#manual').trigger('change');

        $('#btn-manual').click(function () {
            $("#detail-from").val("MANUAL");
            return false;
        });
        $('#btn-toggle').click(function () {
            $('#div-info').toggle('slow');
        });
        $(".btn-delete-image ").click(function () {
            var t = $(this);
            var p = t.parent().parent();
            p.find('input[id=input-delete]').val(true);
            p.find('input[type=file]').prop("disabled", true);
        })
        $('#btn-reset').click(function () {
            $('input[type=file]').prop("disabled", false);
        })
    });
</script>
</body>
</html>