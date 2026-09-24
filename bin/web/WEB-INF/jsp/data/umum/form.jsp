<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="com.permata.appraisal2.common.AppConstans" %>
<c:set var="taskPosition" value="<%= AppConstans.TaskPosition.USER_TL %>"/>
<c:set var="isGroup" value="${formBean.taskList == taskPosition}"></c:set>
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
    <!--[if lt IE 9]>
    <script src="${ctxPath}/js/html5shiv.js"></script>
    <script src="${ctxPath}/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <f:form method="post" modelAttribute="formBean" action="save" class="form-horizontal" cssStyle="margin-top: 50px;">
        <div class="page-header-page">
            <div class="input-append absolute-right">
                <c:if test="${!isGroup}">
                    <button type="submit" class="btn btn-success"><i class="icon-white icon-ok"></i> Save</button>
                    <button type="button" class="btn btn-warning" onclick="gotoUrl('list')"><i
                            class="icon-white icon-step-backward"></i> Back
                    </button>
                    <button type="reset" class="btn btn-danger"><i class="icon-white icon-repeat"></i> Reset</button>
                    <c:if test="${formBean.statusData != 0}">
                        <button data-id="${formBean.id}" data-id-detail="${formBean.dataDetailId}"
                                type="button" class="btn btn-primary btn-detail"><i class="icon-white icon-edit"></i>
                            Data Detail
                        </button>
                        <c:if test="${formBean.statusData != 1}">
                            <button data-dataCommonId="${formBean.id}" data-assetTypeId="${formBean.detail.assetTypeId}"
                                    data-dataDetailId="${formBean.dataDetailId}" id="btn-attactment" type="button"
                                    class="btn btn-info"><i class="icon-white icon-edit"></i> Add Lampiran
                            </button>
                        </c:if>
                    </c:if>
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
        <f:input path="id" type="hidden"/>
        <f:hidden path="createdBy"/>
        <f:hidden path="statusData"/>
        <f:hidden path="createdDate"/>
        <f:input path="sourceStatus" type="hidden"/>
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
                    <c:if test="${!isGroup}">
                        <button type="button" class="btn btn-primary"><i class="icon-white icon-search"></i> Re-Appraise
                        </button>
                        <button type="button" class="btn btn-info"><i class="icon-white icon-search"></i> ILOAN</button>
                    </c:if>
                </div>
                <f:errors path="name" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="customerStatusId">Status Nasabah</label>
            <div class="controls">
                <f:select path="customerStatusId">
                    <f:option value="" label="-- Select --"/>
                    <f:options items="${lvStatusNasabah}" itemValue="value" itemLabel="label"/>
                </f:select>
                <f:errors path="customerStatusId" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="collateralStatusId">Status Jaminan</label>
            <div class="controls">
                <f:select path="collateralStatusId">
                    <f:option value="" label="-- Select --"/>
                    <f:options items="${lvStatusJaminan}" itemValue="value" itemLabel="label"/>
                </f:select>
                <f:errors path="collateralStatusId" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="typeReport">Jenis Laporan</label>
            <div class="controls">
                <f:select path="typeReport">
                    <f:option value="" label="-- Select --"/>
                </f:select>
                <f:errors path="typeReport" class="error has-error"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="appraisalTypeCategory">Kategori Appraisal</label>
            <div class="controls">
                <f:select path="appraisalTypeCategory">
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
                <f:select path="appraisalTypeId">
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
    <c:if test="${formBean.statusData == 2 || formBean.statusData == -1}">
        <div id="anchor-detail" class="page-header">
            <div class="absolute-right">
                <c:if test="${!isGroup}">
                    <c:if test="${formBean.statusData != 0}">
                        <button data-id="${formBean.id}" data-id-detail="${formBean.dataDetailId}" type="button"
                                class="btn btn-primary btn-detail">
                            <i class="icon-white icon-edit"></i> Data Detail
                        </button>
                    </c:if>
                </c:if>
            </div>
            <h3>Order Data Detail</h3>
        </div>
        <form class="form-horizontal">
            <div class="control-group">
                <label class="control-label">Jenis Aktiva</label>
                <div class="controls">
                    <input type="text" class="form-control" value="${formBean.detail.assetTypeName}" disabled>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">Alamat Agunan</label>
                <div class="controls">
                    <input type="text" class="form-control" value="${formBean.detail.alamat}" disabled>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">Perumahan / Apartemen</label>
                <div class="controls">
                    <input type="text" class="form-control" value="${formBean.detail.homeApartment}" disabled>
                </div>
            </div>
        </form>
    </c:if>
    <c:if test="${formBean.statusData == -1}">
        <div id="anchor-attactment" class="page-header">
            <h3>Order Data Lampiran</h3>
        </div>
        <table class="table dataTable">
            <thead>
            <tr>
                <th width="100px">Action</th>
                <th>Jenis Dokumen</th>
                <th>No. Lampiran</th>
                <th>Nama File</th>
                <th>Extension</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${formBean.attactments}" var="row" varStatus="status">
                <tr id="trid${row.id}" class="${status.index % 2 == 0 ? "even" : "odd"}">
                    <td>
                        <div class="btn-group m-b-sm navbar-right">
                            <c:if test="${!isGroup}">
                                <a data-id="${row.id}" data-dataCommonId="${formBean.id}"
                                   data-assetTypeId="${formBean.detail.assetTypeId}"
                                   data-dataDetailId="${formBean.dataDetailId}" title="Edit Data"
                                   class="btn btn-small btn-success click-edit-data"><i
                                        class="icon-white icon-edit"></i></a>
                                <a data-id="${row.id}" title="Delete Data"
                                   class="btn btn-small btn-danger click-delete-data"><i
                                        class="icon-white icon-remove"></i></a>
                            </c:if>
                            <a data-id="${row.id}" title="Preview" class="btn btn-small btn-info click-view-data"><i
                                    class="icon-white icon-picture"></i></a>
                        </div>
                    </td>
                    <td>${row.documentTypeName}</td>
                    <td>${row.id}</td>
                    <td>${row.name}</td>
                    <td>${row.extension}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
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
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<script src="${ctxPath}/js/jquery.inputmask.bundle.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
    $(document).ready(function () {

        $.active = false;
        $('body').bind('click keypress', function() { $.active = true; });
        var logout = '${ctxPath}/logoutExpired';
        var heartBeat = '${ctxPath}/heartbeat';
        checkActivity(${maxInterval}, 60000, 0,logout,heartBeat);

        mask_form();
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
        $('.btn-detail').click(function () {
            window.location = '${ctxPath}/data-detail/add?dataCommonId=' + $(this).data('id') + '&id=' + $(this).data('id-detail');
        });
        $('#btn-attactment').click(function () {
            window.location = '${ctxPath}/data-attactment/add?dataCommonId=' + $(this).data('datacommonid')
                    + '&assetTypeId=' + $(this).data('assettypeid') + '&dataDetailId=' + $(this).data('datadetailid');
        });
        $('.click-edit-data').click(function () {
            window.location = '${ctxPath}/data-attactment/add?dataCommonId=' + $(this).data('datacommonid')
                    + '&assetTypeId=' + $(this).data('assettypeid') + '&dataDetailId=' + $(this).data('datadetailid')
                    + '&id=' + $(this).data('id');
        });
        $('.click-delete-data').click(function () {
            var _id = $(this).data('id');
            if (confirm('Anda yakin akan hapus data?')) {
                $.post("${ctxPath}/data-attactment/delete", {
                    "id": _id,
                    "${_csrf.parameterName}": "${_csrf.token}"
                }, function (data) {
                    if (data.code == 200) {
                        $("#trid" + _id).remove();
                    }
                });
            }
        });
        $('.click-view-data').click(function () {
            openWindow("${ctxPath}/data-attactment/preview?id=" + $(this).data('id'));
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
            if (statusNasabah.toLocaleLowerCase().search('baru') != -1 || statusJaminan.toLocaleLowerCase().search('baru') != -1) {
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
    });
</script>
</body>
</html>