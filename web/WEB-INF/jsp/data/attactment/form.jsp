<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
	<title>Appraisal</title>
	<meta http-equiv="Expires" content="-1" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Cache-Control" content="no-cache" />
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
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
	<div class="page-header">
		<h1>Order Data Lampiran</h1>
	</div>
	<c:if test="${not empty msg}">
		<div class="alert alert-success">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<strong>${msg}</strong>
		</div>
	</c:if>

    <c:if test="${not empty msgErrorImage}">
        <div class="alert alert-danger">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${msgErrorImage}</strong>
        </div>
    </c:if>

    <c:if test="${not empty msgErrorValidate}">
        <div class="alert alert-block">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${msgErrorValidate}</strong>
        </div>
    </c:if>


	<f:form method="post" modelAttribute="formBean" action="save?${_csrf.parameterName}=${_csrf.token}" class="form-horizontal" enctype="multipart/form-data">
		<f:hidden path="id"/>
		<f:hidden path="dataCommonId"/>
		<f:hidden path="dataDetailId"/>
		<f:hidden path="assetType"/>
        
		<div class="control-group ${fn:length(lvJenisDokumen) == 0 ? "error" : "" }">
			<label class="control-label" for="documentTypeId">Nama Dokumen</label>
			<div class="controls">
				<f:select path="documentTypeId">
					<f:option value="" label="-- Select --"/>
					<f:options  items="${lvJenisDokumen}" itemValue="value" itemLabel="label"/>
				</f:select>
				<div class="help-block error">${fn:length(lvJenisDokumen) == 0 ? "Hubungi Admin untuk input Jenis Dokumen" : "" }</div>
				<f:errors path="documentTypeId" class="error has-error" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="name">Nomor Dokumen</label>
			<div class="controls">
				<f:input path="name" type="text" class="form-control" placeholder="Nama"/>
				<f:hidden path="extension"/>
				<f:errors path="name" class="error has-error" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="file">Lampiran</label>
			<div class="controls">
				<span class="btn btn-info btn-file">
                    TIF / PDF / IMAGE <f:input path="file" type="file" class="form-control" placeholder="Lampiran File" accept=".pdf, .tif, image/*"/>
                </span>
				<span></span>
				<f:errors path="file" class="error has-error" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">&nbsp;</label>
			<div class="controls">
                <c:if test="${fn:length(formBean.file) > 0}">
                    <a data-id="${formBean.id}" title="Lihat Lampiran" class="btn btn-small btn-info click-view-data"><i
                            class="icon-white icon-picture"></i> Preview</a>
                </c:if>

				<button id ="btnSave" name="btnSave" type="submit" class="btn btn-success"><i class="icon-white icon-ok"></i> Save</button>
				<button type="button" class="btn btn-warning" onclick="gotoUrl('list?dataCommonId=${formBean.dataCommonId}&assetTypeId=${formBean.assetType}&dataDetailId=${formBean.dataDetailId}')"><i class="icon-white icon-step-backward"></i> Back</button>
				<button type="reset" class="btn btn-danger"><i class="icon-white icon-repeat"></i> Reset</button>
			</div>
		</div>
	</f:form>
</div>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap-transition.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap-alert.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap-modal.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap-dropdown.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap-scrollspy.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap-tab.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap-tooltip.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap-popover.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap-button.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap-collapse.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap-carousel.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap-typeahead.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap-affix.js"></script>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#file').change(function(e) {

            var ua = window.navigator.userAgent;
            var msie = ua.indexOf("MSIE ");

            if (msie > -1 || !!navigator.userAgent.match(/Trident.*rv\:11\./))  // If Internet Explorer, return version number
            {



                var fileName = this.value;
                fileNameSubstr = fileName.split('\\').pop();
                var splitStr = fileNameSubstr.split("\.");
                var formatNye =splitStr[splitStr.length-1];
                document.getElementById("btnSave").disabled = false;


                if( !(formatNye.toLowerCase()=='jpeg' || formatNye.toLowerCase()=='jpg' || formatNye.toLowerCase()=='tif' || formatNye.toLowerCase()=='pdf'||formatNye.toLowerCase()=='png') ){
                    alert('File yang diperbolehkan TIF, PDF, dan IMAGE');
                    document.getElementById("btnSave").disabled = true;
                    return false;
                }
                $(this).parent().next().text(fileNameSubstr);
                $('#extension').val(splitStr[splitStr.length-1]);
                var files = e.target.files;
                var file = files[0];
                var size = file.size;
                if(size>3145728) {
                    alert("Ukuran File Maksimum 3MB");
                    document.getElementById("btnSave").disabled = true;
                    return false;
                }
            }
            else
            {
                var files = e.target.files;
                var file = files[0];
                var type = file.type;
                var size = file.size;

                document.getElementById("btnSave").disabled = false;
                if(size>3145728){
                    alert("Ukuran File Maksimum 3MB");
                    document.getElementById("btnSave").disabled = true;
                    return false;

                }
                if(type.search('pdf') == -1 && type.search('tif') == -1 && type.search('image') == -1){
                    alert('File yang diperbolehkan TIF, PDF, dan IMAGE');
                    document.getElementById("btnSave").disabled = true;
                    return false;
                }

                var splitStr = file.name.split("\.");
                $(this).parent().next().text(file.name);
                $('#extension').val(splitStr[splitStr.length-1]);
            }








		});



        $('.click-view-data').click(function () {
            openWindow("${ctxPath}/data-attactment/preview?id=" + $(this).data('id'));
        });
	});
</script>
</body>
</html>