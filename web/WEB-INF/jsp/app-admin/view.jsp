<%@ taglib prefix="f"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s"   uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="com.permata.appraisal2.common.AppConstans" %>
<%-- spring security in view --%>
<sec:authorize access="hasRole('APPRAISAL_ADMIN')"/>
<c:set var="taskPosition" value="<%= AppConstans.TaskPosition.APP_ADMIN %>"/>
<c:set var="isGroup" value="${formBean.taskList == taskPosition}"></c:set>
<c:set var="isRwa" value="${formBean.source == 'RWA'}"></c:set>
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
        <c:if test="${!isGroup}">
            <div class="absolute-right">
                <div class="btn-group">
                    <button type="button" class="btn btn-info" onclick="window.open('laporan-order-detail?PIdDataDetail=${formBean.detail.id}','_blank')"><i class="icon-white icon-file"></i>View Order Detail</button>
                </div>
            </div>
        </c:if>
        <h3>Detail Task List</h3>
    </div>

  <c:if test="${!isGroup}">
    <c:if test="${not empty msgErrorPic}">
        <div class="alert alert-error">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${msgErrorPic}</strong>
        </div>
    </c:if>
  </c:if>

    <c:if test="${not empty flashMessage}">
        <div class="alert alert-error">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${flashMessage}</strong>
        </div>
    </c:if>

    <c:if test="${not empty flashMessageSuccess}">
        <div class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${flashMessageSuccess}</strong>
        </div>
    </c:if>
    <f:form method="post" modelAttribute="formBean" action="claim" class="form-horizontal">
        <f:hidden path="id"/>
        <div style="border:1px solid #ddd; padding: 8px;" class="controls-row">
            <div class="control-group span6">
                <label class="control-label" for="id">No. Order</label>
                <div class="controls">
                    <div class="input-append">
                        <f:input path="orderNumber" type="text" class="form-control uneditable-input" readonly="true"/>
                    </div>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="name">Nama Debitur</label>
                <div class="controls">
                    <f:input path="name" type="text" class="form-control" readonly="true"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="customerStatusName">Status Nasabah</label>
                <div class="controls">
                    <f:input path="customerStatusName" type="text" class="form-control" readonly="true"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="collateralStatusName">Status Jaminan</label>
                <div class="controls">
                    <f:input path="collateralStatusName" type="text" class="form-control" readonly="true"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="appraisalTypeName">Jenis Appraisal</label>
                <div class="controls">
                    <f:input path="appraisalTypeName" type="text" class="form-control" readonly="true"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="typeReport">Jenis Laporan</label>
                <div class="controls">
                    <f:input path="typeReport" type="text" class="form-control" readonly="true"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="branchBussinesName">Cabang Bisnis</label>
                <div class="controls">
                    <f:input path="branchBussinesName" type="text" class="form-control" readonly="true"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="plafonMarketing">Plafond Kredit</label>
                <div class="controls">
                    <f:input path="plafonMarketing" readonly="true" data-mask="currency"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="contactName">Nama Contact Person</label>
                <div class="controls">
                    <f:input path="contactName" type="text" class="form-control" readonly="true"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="contactTelp">No. Telp Contact Person</label>
                <div class="controls">
                    <f:input path="contactTelp" type="text" class="form-control" readonly="true"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="catatan">Catatan dari Bisnis Unit</label>
                <div class="controls">
                    <f:input path="catatan" type="text" class="form-control" readonly="true"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="marketingTelp">No. Telepon Marketing</label>
                <div class="controls">
                    <f:input path="marketingTelp" type="text" class="form-control uneditable-input" readonly="true"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="marketingHp">No. HP Marketing</label>
                <div class="controls">
                    <f:input path="marketingHp" type="text" class="form-control uneditable-input" readonly="true"/>
                </div>
            </div>

            <%--<div class="control-group span6">--%>
                <%--<label class="control-label" for="detail.picName">PIC Name</label>--%>
                <%--<div class="controls">--%>
                    <%--<f:input path="detail.picName" type="text" id="txtPic" class="form-control" readonly="${formBean.detail.picName != null}"/>--%>


                    <%--<c:if test="${formBean.detail.picName == null}">--%>
                     <%--<button id="btnAddPic"  type="button" class="btn btn-primary"><i class="icon-white icon-share"></i> Fill Pic</button>--%>
                    <%--</c:if>--%>
                <%--</div>--%>
            <%--</div>--%>

            <c:if test="${!isGroup}">
            <div class="control-group span6">
                <label class="control-label" for="detail.picName">Pic Name</label>
                <div class="controls">
                    <f:select id="picCombo" path="detail.picName" disabled="${formBean.detail.picName != null}" >
                        <f:option value="">-- Select --</f:option>
                        <f:options items="${listPicName}" itemValue="value" itemLabel="label"/>
                    </f:select>
                    <c:if test="${formBean.detail.picName == null}">
                        <button id="btnAddPic"  type="button" class="btn btn-primary"><i class="icon-white icon-share"></i> Fill Pic</button>
                    </c:if>
                </div>
            </div>
            </c:if>

            <div class="control-group span12">
                <label class="control-label">Collateral ID</label>
                <div class="controls">
                    <label class="form-control">(diisi oleh Appraisal Admin)</label>
                </div>
            </div>
        </div>
        <div class="page-header">
            <h3>Order Data Lampiran</h3>
        </div>
        <c:if test="${not empty msgErrorValidate}">
            <div class="alert alert-block">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>${msgErrorValidate}</strong>
            </div>
        </c:if>
        <table class="table dataTable">
            <thead>
            <tr>
                <th>Alamat</th>
                <th>Kabupaten</th>
                <th>Kecamatan</th>
                <th>Jenis Dokumen</th>
                <th>Nama File</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${formBean.attactments}" var="row" varStatus="status">
                <tr id="trid${row.id}" class="${status.index % 2 == 0 ? "even" : "odd"}">
                    <td>${status.index != 0 ? '' : formBean.detail.alamat}</td>
                    <td>${status.index != 0 ? '' : formBean.detail.city}</td>
                    <td>${status.index != 0 ? '' : formBean.detail.district}</td>
                    <td>${row.documentTypeName}</td>
                    <td><a href="${ctxPath}/data-attactment/preview?id=${row.id}"
                           target="_blank">${row.name}.${row.extension}</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <c:if test="${isGroup}">
            <div class="control-group">
                <button type="submit" class="btn btn-success"><i class="icon-white icon-ok"></i> Claim</button>
                <button type="button" class="btn btn-warning"
                        onclick="gotoUrl('list?task=<%= AppConstans.TaskPosition.APP_ADMIN %>')"><i
                        class="icon-white icon-step-backward"></i> Back
                </button>
            </div>
        </c:if>
    </f:form>
    <f:form modelAttribute="formBean" method="post" action="#" cssClass="form-horizontal" id="form-app-admin">
        <f:hidden path="id"/>
        <c:if test="${!isGroup}">
            <div style="border:1px solid #ddd; padding: 8px;">
                <div class="control-group">
                    <label class="control-label" for="collateralId">Collateral ID</label>
                    <div class="controls">
                        <f:input path="collateralId" type="text" class="form-control" placeholder="Collateral ID" readonly="${isRwa ? 'true' : 'false'}"/>
                        <f:errors path="collateralId" class="error has-error"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="retention">Retention</label>
                    <div class="controls">
                        <f:select path="retention">
                            <f:option value="0">No</f:option>
                            <f:option value="1">Yes</f:option>
                        </f:select>
                        <f:errors path="retention" class="error has-error"/>
                    </div>
                </div>
                <c:if test="${fn:containsIgnoreCase(formBean.appraisalTypeCategory, 'eksternal')}">
                <div class="control-group">
                    <label class="control-label" for="kjppId">Penilai Independen</label>
                    <div class="controls">
                        <f:select path="kjppId">
                            <f:option value="">-- Select --</f:option>
                            <f:options items="${listKjpp}" itemValue="value" itemLabel="label"/>
                        </f:select>
                        <small><i>Di isi jika menggunakan surveyor KJPP(eksternal)</i></small>
                        <f:errors path="kjppId" class="error has-error"/>
                    </div>
                </div>
                </c:if>
                <div class="control-group">
                    <label class="control-label" for="message">Message</label>
                    <div class="controls">
                        <f:input path="message" cssClass="input-xxlarge"/>
                        <f:errors path="message" class="error has-error"/>
                    </div>
                </div>
            </div>
            <div class="control-group" style="margin-top: 10px;">

                <c:if test="${empty msgErrorPic && listFlowList!=null}">
                <div class="input-append">
                    <select name="sendTo">
                        <c:forEach items="${listFlowList}" var="row">
                            <option value="${row.destination}">${row.destination}</option>
                        </c:forEach>
                    </select>
                    <button id="btn-submit-to" type="submit" class="btn btn-primary"><i class="icon-white icon-share"></i> Submit</button>
                </div>
                </c:if>

                <button type="button" class="btn btn-info" onclick="gotoUrl('detail?id=${formBean.id}')"><i class="icon-white icon-edit"></i> Data Detail</button>
                <button type="button" class="btn btn-warning" onclick="gotoUrl('list')"><i class="icon-white icon-step-backward"></i> Back</button>
                <c:if test="${listFlowList!=null}"><button id="btn-pending" type="submit" class="btn btn-danger"><i class="icon-white icon-pause"></i> Pending</button></c:if>
            </div>
        </c:if>
    </f:form>
    <c:if test="${fn:length(formBean.messages) > 0 }">
    <div class="page-header">
        <h3><img src="${ctxPath}/images/message.jpg"> Message History</h3>
    </div>
    <table class="table dataTable">
        <thead>
        <tr>
            <th>Tanggal</th>
            <th>Jam</th>
            <th>Divisi</th>
            <th>Oleh</th>
            <th>Message</th>
        </tr>
        </thead>
        <c:forEach items="${formBean.messages}" var="row">
            <tr>
                <td><fmt:formatDate type="date" value="${row.date}" pattern="dd-MM-yyyy"/></td>
                <td><fmt:formatDate type="time" value="${row.date}"/></td>
                <td>${row.position}</td>
                <td>${row.userName}</td>
                <td>${row.message}</td>
            </tr>
        </c:forEach>
    </table>
    </c:if>
</div>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<script src="${ctxPath}/js/jquery.inputmask.bundle.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        // timeout = 30 minutes (dari DB), interval = 1 minute.
        $.active = false;
        $('body').bind('click keypress', function() { $.active = true; });
        var logout = '${ctxPath}/logoutExpired';
        var heartBeat = '${ctxPath}/heartbeat';
        checkActivity(${maxInterval}, 60000, 0,logout,heartBeat);

        mask_form();




        $('#btnAddPic').click(function () {
            $.ajax({
                type : "POST",
                url : "<s:url value="updatePic"/>",
                data : {
                    "id" : $('#id').val(),
                    "picName" :$('#picCombo').val(),
                    "${_csrf.parameterName}": "${_csrf.token}"
                },
                "complete" : function(data){

                    window.location.reload();
                }
            })
        });

        $('#btn-submit-to').click(function () {
            var sendTo = $('select[name=sendTo]').val();
            if(sendTo == "User_TL"){
                if(!$('#message').val() ||  $.trim($('#message').val()) == ""){
                    alert('Message harus diisi');
                    return false;
                }
            }

            if (sendTo == "App_Valuer"){
                if(!$('#picCombo').val() || $.trim($('#picCombo').val()) == ""){
                    alert('Nama Pic Kosong, Silahkan Lengkapi data!!');
                    return false;
                }

                <c:if test="${formBean.detail.picName == null}">
                    alert('Harap Pilih Nama Pic (pilih fill pic)!!');
                    return false;
                </c:if>
            }
            $('#form-app-admin').attr('action', 'submit');
        });
        $('#btn-pending').click(function () {
            $('#form-app-admin').attr('action', 'pending');
        });



    });
</script>
</body>
</html>