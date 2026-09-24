<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="com.permata.appraisal2.common.AppConstans" %>
<c:set var="taskPosition" value="<%= AppConstans.TaskPosition.USER_TL %>"/>
<c:set var="isGroup" value="${formBean.taskList == taskPosition}"></c:set>
<c:set var="isRwa" value="${formBean.source == 'RWA'}"></c:set>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
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

    <style type="text/css">
        .tab-content {
            padding-top: 0px;
        }
        .modal-header {
            padding: 10px;
        }
        .modal {
            width: 800px;
            left: 2;
            margin-left: 10px;
        }
        .modal-body {
            max-height: 800px;
            padding: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <ul class="nav nav-tabs">
        <li class="active"><a data-toggle="tab" href="#menu1">Data Umum Order</a></li>
        <c:if test="${formBean.id != null}">
            <li><a data-toggle="tab" href="#menu2">Data Detail Order</a></li>
            <c:if test="${formBean.statusData != 1}">
                <li><a data-toggle="tab" href="#menu3">Data Lampiran Order</a></li>
            </c:if>
        </c:if>
    </ul>
    <div class="tab-content">
        <div id="menu1" class="tab-pane fade in active panel controls-row">
            <f:form method="post" modelAttribute="formBean" action="save" class="form-horizontal">
                <div class="page-header">
                    <div class="input-append absolute-right">
                        <c:if test="${!isGroup}">
                            <button type="submit" class="btn btn-success" id="btn-submit"><i class="icon-white icon-ok"></i> Save</button>
                            <button type="button" class="btn btn-warning" onclick="gotoUrl('list')"><i
                                    class="icon-white icon-step-backward"></i> Back
                            </button>
                            <button type="reset" id="btn-reset" class="btn btn-danger"><i class="icon-white icon-repeat"></i> Reset</button>
                        </c:if>
                    </div>
                    <h1>Order Data Umum Form</h1>
                </div>
                <c:if test="${not empty msg}">
                    <div class="alert alert-success">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <strong>${msg}</strong>
                    </div>
                </c:if>

                <c:if test="${not empty msgCopyDebitur}">
                    <div class="alert alert-success">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <strong>${msgCopyDebitur}</strong>
                    </div>
                </c:if>
                <f:input path="id" type="hidden"/>
                <f:hidden path="createdBy"/>
                <f:hidden path="statusData"/>
                <f:hidden path="createdDate"/>
                <f:hidden path="copyDebitur"/>
                <f:hidden path="rwaId"/>
                <f:hidden path="source"/>
                <f:hidden path="sourceId"/>

                <c:if test="${formBean.statusData > 0}">
                    <div class="alert alert-info">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <c:if test="${formBean.statusData == 1}">
                            <strong>Data Belum memiliki detail</strong>
                        </c:if>
                        <c:if test="${formBean.statusData == 2}">
                            <strong>Data Belum memiliki attactment</strong>
                        </c:if>
                    </div>
                </c:if>
                <div class="alert alert-info hide" id="visible-alert">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Proses Copy Data Debitur, akan melakukan copy data SURVEY dan LPA</strong>
                </div>
                <div class="control-group">
                    <label class="control-label" for="orderNumber">Nomor Order</label>
                    <div class="controls">
                        <f:input path="orderNumber" readonly="true"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="name">Nama Debitur</label>
                    <div class="controls">
                        <div class="input-append">
                            <f:input path="name" type="text" class="form-control" placeholder="Nama"/>
                            <c:if test="${!isGroup && formBean.id == null}">
                                <button id="btn-re-appraise" type="button" class="btn btn-info btn-mini"><i class="icon-white icon-search"></i> Re-Appraise</button>
                                <button id="btn-iloan" type="button" class="btn btn-info"><i class="icon-white icon-search"></i> ILOAN</button>
                            </c:if>
                        </div>
                        <f:errors path="name" class="error has-error"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="customerStatusId">Status Nasabah</label>
                    <div class="controls">
                        <f:select path="customerStatusId" disabled="${isRwa ? 'true' : 'false'}">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${lvStatusNasabah}" itemValue="value" itemLabel="label"/>
                        </f:select>
                        <f:errors path="customerStatusId" class="error has-error"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="collateralStatusId">Status Jaminan</label>
                    <div class="controls">
                        <f:select path="collateralStatusId" disabled="${isRwa ? 'true' : 'false'}">
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${lvStatusJaminan}" itemValue="value" itemLabel="label"/>
                        </f:select>
                        <f:errors path="collateralStatusId" class="error has-error"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="typeReport">Jenis Laporan</label>
                    <div class="controls">
                        <f:select path="typeReport" disabled="${isRwa ? 'true' : 'false'}">
                            <f:option value="" label="-- Select --"/>
                        </f:select>
                        <f:errors path="typeReport" class="error has-error"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="appraisalTypeCategory">Kategori Appraisal</label>
                    <div class="controls">
                        <f:select path="appraisalTypeCategory" disabled="${formBean.statusData == 2 || formBean.statusData == -1}">
                            <f:option value="<%= AppConstans.JenisAppraisalCategory.INTERNAL%>"
                                      label="<%= AppConstans.JenisAppraisalCategory.INTERNAL%>"/>
                            <f:option value="<%= AppConstans.JenisAppraisalCategory.EKSTERNAL%>"
                                      label="<%= AppConstans.JenisAppraisalCategory.EKSTERNAL%>"/>
                        </f:select>
                        <f:errors path="appraisalTypeCategory" class="error has-error"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="appraisalTypeId">Jenis Appraisal</label>
                    <div class="controls">
                        <f:select path="appraisalTypeId" disabled="${isRwa ? 'true' : 'false' || formBean.statusData == 2 || formBean.statusData == -1}" >
                            <f:option value="" label="-- Select --"/>
                            <f:options items="${lvJenisApproval}" itemValue="id" itemLabel="label"/>
                        </f:select>
                        <f:errors path="appraisalTypeId" class="error has-error"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="plafonMarketing">Plafond Kredit</label>
                    <div class="controls">
                        <f:input path="plafonMarketing" class="form-control" placeholder="Plafond Kredit" data-mask="currency"/>
                        <f:errors path="plafonMarketing" class="error has-error"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="collateralId">Collateral ID</label>
                    <div class="controls">
                        <f:input path="collateralId" type="text" class="form-control" placeholder="Collateral ID"
                                 readonly="true"/>
                        <f:errors path="collateralId" class="error has-error"/>
                    </div>
                </div>
                <div class="control-group">&nbsp;</div>
                <div class="control-group">
                    <label class="control-label" for="contactName">Nama Contact Person</label>
                    <div class="controls">
                        <f:input path="contactName" type="text" class="form-control" placeholder="Nama"/>
                        <f:errors path="contactName" class="error has-error"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="contactTelp">No. Telp Contact Person</label>
                    <div class="controls">
                        <f:input path="contactTelp" placeholder="No. Telp" data-mask="number"/>
                        <f:errors path="contactTelp" class="error has-error"/>
                    </div>
                </div>
                <div class="control-group">&nbsp;</div>
                <div class="control-group">
                    <label class="control-label" for="catatan">Catatan dari Bisnis Unit</label>
                    <div class="controls">
                        <f:input path="catatan" type="text" class="form-control" placeholder="Catatan"/>
                        <f:errors path="catatan" class="error has-error"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="marketingTelp">No. Telepon Marketing</label>
                    <div class="controls">
                        <f:input path="marketingTelp" type="text" class="form-control uneditable-input" placeholder="No. Telp"
                                 readonly="true"/>
                        <f:errors path="marketingTelp" class="error has-error"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="marketingHp">No. HP Marketing</label>
                    <div class="controls">
                        <f:input path="marketingHp" type="text" class="form-control uneditable-input" placeholder="No. HP"
                                 readonly="true"/>
                        <f:errors path="marketingHp" class="error has-error"/>
                    </div>
                </div>
            </f:form>
            <c:if test="${!isGroup}">
                <c:if test="${formBean.statusData != 0}">
                    <form id="form-reject-submit" action="reject?${_csrf.parameterName}=${_csrf.token}" method="post"
                          class="form-inline" style="margin-top: 20px;">
                        <input type="hidden" value="${formBean.id}" name="id">
                        <div class="control-group">
                            <label class="control-label" for="rejectCategory">Message</label>
                            <div class="controls">
                                <input type="text" id="message" name="message" class="input-xxlarge"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <div class="controls">
                                <div class="input-append">
                                    <select name="rejectCategory" id="rejectCategory">
                                        <c:forEach items="${lvReject}" var="row">
                                            <option value="${row.label}">${row.label}</option>
                                        </c:forEach>
                                    </select>
                                    <button id="btn-reject" type="submit" class="btn btn-danger"><i
                                            class="icon-white icon-trash"></i> DROP
                                    </button>
                                </div>
                                <c:if test="${formBean.statusData == -1}">
                                    <div class="input-append">
                                        <select name="sendTo">
                                            <c:forEach items="${lvBranchs}" var="row">
                                                <option value="${row.value}">${row.label}</option>
                                            </c:forEach>
                                        </select>
                                        <button id="btn-submit-to" type="submit" class="btn btn-success"><i
                                                class="icon-white icon-share"></i> Submit
                                        </button>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </form>
                </c:if>
            </c:if>
            <c:if test="${isGroup}">
                <form action="claim?${_csrf.parameterName}=${_csrf.token}" method="post" class="form-inline">
                    <input type="hidden" value="${formBean.id}" name="id">
                    <button type="submit" class="btn btn-success"><i class="icon-white icon-book"></i> Claim</button>
                    <button type="button" class="btn btn-warning" onclick="gotoUrl('list?task=User_TL')"><i
                            class="icon-white icon-step-backward"></i> Back
                    </button>
                </form>
            </c:if>
        </div>
        <c:if test="${formBean.id != null}">
            <div id="menu2" class="tab-pane fade panel controls-row" style="height: 550px;">
                <iframe id="iframe-detail" src="${ctxPath}/data-detail/add?dataCommonId=${formBean.id}&id=${formBean.detail.id}" width="100%" height="100%" frameborder="0"></iframe>
            </div>
            <c:if test="${formBean.statusData != 1}">
                <div id="menu3" class="tab-pane fade panel controls-row" style="height: 550px;">
                    <iframe id="iframe-lampiran" src="${ctxPath}/data-attactment/list?dataCommonId=${formBean.id}&assetTypeId=${formBean.detail.assetTypeId}&dataDetailId=${formBean.detail.id}&msg=${msgAttach}&msgErrorValidate=${msgErrorValidateAttach}" width="100%" height="100%" frameborder="0"></iframe>
                </div>
            </c:if>
        </c:if>
    </div>
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
    var refreshParent;
    var refreshDetail;
    var callBackReappraise;
    var callbackRwa;
    $(document).ready(function () {

        $.active = false;
        $('body').bind('click keypress', function() { $.active = true; });
        var logout = '${ctxPath}/logoutExpired';
        var heartBeat = '${ctxPath}/heartbeat';
        checkActivity(${maxInterval}, 60000, 0,logout,heartBeat);

        var windowOpen;
        refreshParent = function(active,msg,msgErrorValidate){
            if(active == null || active == undefined){
                active = 1;
            }

            if(msg == null || msg == undefined){
                msg = '';
            }
            if(msgErrorValidate == null || msgErrorValidate == undefined){
                msgErrorValidate = '';
            }


            window.location = "${ctxPath}/data-umum/form?id=${formBean.id}&active="+active+"&msg="+msg+"&msgErrorValidate="+msgErrorValidate;
        }
        refreshDetail = function(){
            $('#iframe-detail').attr('src', $('#iframe-detail').attr('src'));
        }
        $('#formBean').submit(function(){
            $(this).find('select').prop('disabled', false);
        })
        $('#btn-reject').click(function () {
            if (!$('#message').val() ||  $.trim($('#message').val()) == "") {
                alert('Proses Reject harus mengisi Message');
                return false;
            }
        });
        $("#btn-submit-to").click(function () {
            $("#form-reject-submit").attr("action", "submit?${_csrf.parameterName}=${_csrf.token}");
            return true; // lanjutkan submit
        });
        $("input[name=name], input[name=contactName]").blur(function () {
            $(this).val($(this).val().toUpperCase());
        });
        $('#appraisalTypeCategory').change(function () {
            var kategori = $(this).val();
            var isInternal = true;
            if (kategori == "<%=AppConstans.JenisAppraisalCategory.EKSTERNAL%>") {
                isInternal = false;
            }
            $('#appraisalTypeId').empty();
            $('#appraisalTypeId').append($('<option>', {value: "", text: "-- Select --"}));
            for (var i = 0; i < lvJenisApproval.length; i++) {
                var row = lvJenisApproval[i];
                if (!isInternal) {
                    if (row.value) {
                        $('#appraisalTypeId').append($('<option>', {value: row.id, text: row.label}));
                    }
                } else {
                    $('#appraisalTypeId').append($('<option>', {value: row.id, text: row.label}));
                }
                $('#appraisalTypeId').val(${formBean.appraisalTypeId});
            }
        });
        $('#customerStatusId, #collateralStatusId').change(function () {
            var statusNasabah = $('#customerStatusId option:selected').text();
            var statusJaminan = $('#collateralStatusId option:selected').text();

            var allNew = false;
            if (statusNasabah.toLocaleLowerCase().search('lama') == -1 || statusJaminan.toLocaleLowerCase().search('lama') == -1) {
                allNew = true;
            } else {
                allNew = false;
            }

            $('#typeReport').empty();
            $('#typeReport').append($('<option>', {value: "", text: "-- Select --"}));
            for (var i = 0; i < listJenisLaporan.length; i++) {
                var row = listJenisLaporan[i];
                if (allNew && row.search('New') != -1) {
                    $('#typeReport').append($('<option>', {value: row, text: row}));
                } else if (!allNew && row.search('New') == -1) {
                    $('#typeReport').append($('<option>', {value: row, text: row}));
                }
            }
            $('#typeReport').val("${formBean.typeReport}");
            if(allNew){
                $('#typeReport').val("New");
            }
        });
        $('#btn-re-appraise').click(function () {
            windowOpen = openWindow('${ctxPath}/data-umum/reappraise');
        })
        $('#btn-iloan').click(function(){
            windowOpen = openWindow('${ctxPath}/iloan/lookup');
        })


        $('#btn-reset').click(function(){
            //Enable Save
            $('#btn-submit').html('<i class="icon-white icon-ok"></i> Save');
            $('#formBean').attr('action', 'save');
            $('#formBean').find('select[name=customerStatusId]').prop('disabled', false);
            $('#formBean').find('select[name=collateralStatusId]').prop('disabled', false);
            $('#formBean').find('input[name=id]').prop('value', '');
//            $('#formBean').find('select[name=appraisalTypeCategory]').prop('disabled', false);
//            $('#formBean').find('select[name=appraisalTypeId]').prop('disabled', false)
            $('#formBean').find('select[name=typeReport]').prop('disabled', false);




        })
        callBackReappraise = function(id){
            windowOpen.close();
            $.ajax({
                url: '${ctxPath}/data-umum/get?id='+id,
                data: {"id" : id},
                dataType: 'json',
                success : function(data){

                    $('#formBean').find('input[name=id]').val(data.id);
                    $('#formBean').find('input[name=orderNumber]').val(data.orderNumber);
                    $('#formBean').find('input[name=name]').val(data.name);
                    $('#formBean').find('select[name=customerStatusId]').val('3');
                    $('#formBean').find('select[name=customerStatusId]').prop('disabled', true);
                    $('#formBean').find('select[name=collateralStatusId]').val('3');
                    $('#formBean').find('select[name=collateralStatusId]').prop('disabled', true);

                    if($('#source').val() != "RWA"){
                        $('#formBean').find('select[name=typeReport]').val('Reappraise');
                        $('#formBean').find('input[name=collateralId]').val(data.collateralId);
                    }



                    $('#formBean').find('select[name=appraisalTypeCategory]').val(data.appraisalTypeCategory);
                    $('#formBean').find('select[name=appraisalTypeCategory]').prop('disabled', true);
                    $('#formBean').find('select[name=appraisalTypeId]').val(data.appraisalTypeId);
                    $('#formBean').find('select[name=appraisalTypeId]').prop('disabled', true)
                    $('#formBean').find('input[name=plafonMarketing]').val(data.plafonMarketing);

                    $('#formBean').find('input[name=contactName]').val(data.contactName);
                    $('#formBean').find('input[name=contactTelp]').val(data.contactTelp);
                    $('#formBean').find('input[name=catatan]').val(data.catatan);
                    $('#formBean').find('input[name=copyDebitur]').val(true);
                    $('#visible-alert').show();
                    $('#btn-submit').html('<i class="icon-white icon-bookmark"></i> Copy Data Debitur');
                    $('#formBean').attr('action', 'copy-debitur');

                    console.log("Masukkkk trigger");
                    $('#collateralStatusId').trigger('change');



                }
            });
        }
        callbackRwa = function(data){
            //console.log(data);
            $('#rwaId').val(data.id);
            $('#source').val("RWA");
            //$('#formBean').find('input[name=orderNumber]').val(data.orderNumber);
            $('#formBean').find('input[name=name]').val(data.name);
            $('#formBean').find('select[name=customerStatusId] option').each(function(k, v){
                var text = $(v).text();
                if(text == "Lama"){
                    $('#formBean').find('select[name=customerStatusId]').val($(v).val());
                    $('#formBean').find('select[name=customerStatusId]').prop('disabled', true);
                }
            });
            $('#formBean').find('select[name=collateralStatusId] option').each(function(k, v){
                var text = $(v).text();
                if(text == "Lama"){
                    $('#formBean').find('select[name=collateralStatusId]').val($(v).val());
                    $('#formBean').find('select[name=collateralStatusId]').prop('disabled', true);
                }
            });
            $('#formBean').find('select[name=typeReport]').val('RWA');
            $('#formBean').find('select[name=typeReport]').prop('disabled', true);
            //$('#formBean').find('select[name=appraisalTypeCategory]').val(data.appraisalTypeCategory);
            $('#formBean').find('select[name=appraisalTypeId] option').each(function(k, v){
                var text = $(v).text();
                if(text == "Appraisal"){
                    $('#formBean').find('select[name=appraisalTypeId]').val($(v).val());
                    $('#formBean').find('select[name=appraisalTypeId]').prop('disabled', true);
                }
            });
            $('#formBean').find('input[name=plafonMarketing]').val(data.plafond);
            $('#formBean').find('input[name=collateralId]').val(data.collateralId);
            $('#formBean').find('input[name=contactName]').val(data.contactName);
            $('#formBean').find('input[name=contactTelp]').val(data.contactTelp);
            $('#formBean').find('input[name=catatan]').val(data.catatan);

            // $('#visible-alert').show();
            windowOpen.close();
        }

        var lvJenisApproval = new Array();
        <c:forEach items="${lvJenisApproval}" var="row" varStatus="status">
        var jenisAppraisal = new Object();
        jenisAppraisal.id = ${row.id};
        jenisAppraisal.value = ${row.value};
        jenisAppraisal.label = "${row.label}";
        lvJenisApproval[${status.index}] = jenisAppraisal;
        </c:forEach>

        var listJenisLaporan = new Array();
        <c:forEach items="${listJenisLaporan}" var="row" varStatus="status">
        listJenisLaporan[${status.index}] = "${row}";
        </c:forEach>
        $('#customerStatusId').trigger('change');

        <s:hasBindErrors name="formBean">
        <c:forEach var="error" items="${errors.allErrors}">showError('${error.field}');
        </c:forEach>
        </s:hasBindErrors>

        <c:choose>
        <c:when test="${active == 2}">$('a[href="#menu2"]').trigger('click');</c:when>
        <c:when test="${active == 3}">$('a[href="#menu3"]').trigger('click');</c:when>
        <c:otherwise>$('a[href="#menu1"]').trigger('click');</c:otherwise>
        </c:choose>
    });
</script>
</body>
</html>
