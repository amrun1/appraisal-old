<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.permata.appraisal2.common.AppConstans" %>
<c:set var="taskPosition" value="<%= AppConstans.TaskPosition.CLOSE %>"/>
<c:set var="isGroup" value="${formBean.taskList == taskPosition}"></c:set>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
    <title>${formBean.taskList} Appraisal ${taskPosition}</title>
    <meta http-equiv="Expires" content="-1"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${ctxPath}/js/bootstrap_2.3.2/css/bootstrap.css" rel="stylesheet">
    <link href="${ctxPath}/css/main-app.css" rel="stylesheet">

    <!--[if gte IE 9]><!-->
    <link rel="stylesheet" media="screen" href="${ctxPath}/css/glyphicons/glyphicons.min.css">
    <!--<![endif]-->
    <!--[if lt IE 9]>
    <script src="${ctxPath}/js/html5shiv.js"></script>
    <script src="${ctxPath}/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <div class="page-header">
        <h3>Detail Order Drop</h3>
    </div>
    <f:form method="post" modelAttribute="formBean" action="claim" class="form-horizontal">
        <f:hidden path="id"/>
        <div style="border:1px solid #ddd; padding: 8px;" class="controls-row">
            <div class="control-group span6">
                <label class="control-label" for="id">No. Order</label>
                <div class="controls">
                    <f:input path="orderNumber" type="text" class="form-control" readonly="true"/>
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
                <label class="control-label" for="appraisalTypeName">Jenis Approval</label>
                <div class="controls">
                    <f:input path="appraisalTypeName" type="text" class="form-control" readonly="true"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="appraisalTypeName">Jenis Laporan</label>
                <div class="controls">
                    <input class="form-control" readonly="readonly" value="" type="text">
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
                    <f:input path="marketingTelp" type="text" class="form-control" readonly="true"/>
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label" for="marketingHp">No. HP Marketing</label>
                <div class="controls">
                    <f:input path="marketingHp" type="text" class="form-control" readonly="true"/>
                </div>
            </div>
            <div class="controls-row span12">
                <label class="control-label" for="collateralId">Collateral ID</label>
                <div class="controls">
                    <f:input path="collateralId" type="text" class="form-control" readonly="true"/>
                </div>
            </div>
        </div>
        <div class="page-header" id="header-survey">
            <h3><img src="${ctxPath}/images/survey.jpg">Data Detail & Survey</h3>
        </div>
        <div style="border:1px solid #ddd; padding: 8px;" class="controls-row">
            <div class="control-group span6">
                <label class="control-label">No. Detail</label>
                <div class="controls">
                    <input type="text" value="${formBean.detail.id}" class="form-control" readonly="true">
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label">Object</label>
                <div class="controls">
                    <input type="text" value="${formBean.detail.assetTypeName}" class="form-control" readonly="true">
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label">Komplek / Perumahan</label>
                <div class="controls">
                    <input type="text" value="${formBean.detail.homeApartment}" class="form-control" readonly="true">
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label">Alamat</label>
                <div class="controls">
                    <input type="text" value="${formBean.detail.alamat}" class="form-control" readonly="true">
                </div>
            </div>

            <div class="control-group span6">
                <label class="control-label">Propinsi</label>
                <div class="controls">
                    <input type="text" value="${formBean.detail.province}" class="form-control" readonly="true">
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label">Kota</label>
                <div class="controls">
                    <input type="text" value="${formBean.detail.city}" class="form-control" readonly="true">
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label">Kecamatan</label>
                <div class="controls">
                    <input type="text" value="${formBean.detail.district}" class="form-control" readonly="true">
                </div>
            </div>
            <div class="control-group span6">
                <label class="control-label">Kelurahan</label>
                <div class="controls">
                    <input type="text" value="${formBean.detail.subDistrict}" class="form-control" readonly="true">
                </div>
            </div>


            <div class="control-group span12">
                <label class="control-label">&nbsp;</label>
                <div class="controls">
                    <a a href="#myModal" role="button" data-toggle="modal" class="btn btn-info"><i class="icon-white icon-share"></i> Attactment
                        <span class="badge badge-warning">${fn:length(formBean.attactments)}</span></a>

                </div>
            </div>
        </div>

            <div class="control-group" style="margin-top: 15px;">
                <button type="submit" class="btn btn-success"><i class="icon-white icon-ok"></i> Claim</button>
                <button type="button" class="btn btn-warning"
                        onclick="gotoUrl('list?task=${taskPosition}')"><i
                        class="icon-white icon-step-backward"></i> Back
                </button>
            </div>

    </f:form>



</div>
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">Data Lampiran</h3>
    </div>
    <div class="modal-body">
        <table class="table table-bordered dataTable">
            <thead>
            <tr>
                <th>No</th>
                <th>Jenis Dokumen</th>
                <th>Nama File</th>
            </tr>
            </thead>
        <c:forEach items="${formBean.attactments}" var="row" varStatus="status">
            <tr class="${status.index % 2 == 0 ? "even" : "odd"}">
                <td>${status.index + 1}</td>
                <td>${row.name}</td>
                <td><a href="${ctxPath}/data-attactment/preview?id=${row.id}" target="_blank">${row.name}.${row.extension}</a></td>
            </tr>
        </c:forEach>
        </table>
    </div>
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

        $('#btn-submit-to').click(function () {
            $('#form-app-admin').attr('action', 'submit');
        });
        $('#btn-reject-to').click(function () {
            if(!$('#message').val() ||  $.trim($('#message').val()) == ""){
                alert('Message harus diisi');
                return false;
            }
            $('#form-app-admin').attr('action', 'reject');
        });
    });
</script>
</body>
</html>