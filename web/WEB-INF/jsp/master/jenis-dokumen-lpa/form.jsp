<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
	<title>Appraisal</title>
	<c:set var="jenis" value='<%= request.getParameter("jenis") %>'/>
	<meta http-equiv="Expires" content="-1" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Cache-Control" content="no-cache" />
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
	<div class="page-header">
		<h1>Form Jenis Dokumen LPA</h1>
	</div>
	<c:if test="${not empty msg}">
		<div class="alert alert-success">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<strong>${msg}</strong>
		</div>
	</c:if>
	<f:form method="post" modelAttribute="formBean" action="${ctxPath}/master/jenis-dokumen-lpa/save?jenis=${jenis}" class="form-horizontal" id="form-jenis-doc">
		<f:hidden path="id"/>

		<div class="control-group">
			<label class="control-label" for="name">Jenis Data</label>
			<div class="controls">
				<f:select path="jenisData">

					<f:options items="${jenisData}" itemValue="value" itemLabel="label"/>
				</f:select>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label" for="name">Nama</label>
			<div class="controls">
				<f:input path="name" type="text" class="form-control" placeholder="Nama" readonly="false" />
				<f:errors path="name" class="error has-error" />
			</div>
		</div>
		<c:if test="${jenis ==2}">
		<div class="control-group">
			<label class="control-label" for="assetTypeId">Jenis Aktiva</label>
			<div class="controls">
				<f:select path="assetTypeId">
					<f:option value="" label="-- Select --"/>
					<f:options items="${listAktiva}" itemValue="id" itemLabel="label"/>
				</f:select>
			</div>
		</div>
		</c:if>
		<c:if test="${jenis ==1}">
		<div class="control-group">
			<label class="control-label" for="appraisalId">Jenis Appraisal</label>
			<div class="controls">
				<f:select path="appraisalId">
					<f:option value="" label="-- Select --"/>
					<f:options items="${listAppraisal}" itemValue="value" itemLabel="label"/>
				</f:select>
			</div>
		</div>
		</c:if>
		<div class="control-group">
			<label class="control-label" for="mandatory">&nbsp;</label>
			<div class="controls">
				<f:checkbox path="mandatory" label="Mandatory"/>
				<f:errors path="mandatory" cssClass="help-block"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="surveyYn">&nbsp;</label>
			<div class="controls">
				<f:checkbox path="surveyYn" label="Survey" />
				<f:errors path="surveyYn" cssClass="help-block"/>
			</div>
		</div>
		<div class="control-group" style="display: none;">
			<label class="control-label" for="readFileYn">&nbsp;</label>
			<div class="controls">
				<f:checkbox path="readFileYn" label="Read File"/>
				<f:errors path="readFileYn" cssClass="help-block"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="name">&nbsp;</label>
			<div class="controls">
				<button type="submit" id ="btnSave" class="btn btn-small btn-success"><i class="icon-white icon-ok"></i> Save</button>
				<button type="button" class="btn btn-warning"
						onclick="gotoUrl('list?task=${taskPosition}&jenis=${jenis}')"><i
						class="icon-white icon-step-backward"></i> Back
				</button>
			</div>
		</div>
	</f:form>
</div>

<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<!--[if lt IE 9]>
<script src="${ctxPath}/js/html5shiv.js"></script>
<script src="${ctxPath}/js/respond.min.js"></script>

<![endif]-->
<script type="text/javascript">

    $(document).ready(function () {

        $.active = false;
        $('body').bind('click keypress', function() { $.active = true; });
        var logout = '${ctxPath}/logoutExpired';
        var heartBeat = '${ctxPath}/heartbeat';
        checkActivity(${maxInterval}, 60000, 0,logout,heartBeat);




		$('#jenisData').change(function(){


			$("#name").prop('readonly', false);
			$("#name").val("");
			$('#surveyYn1').prop("checked", false );
			$('#surveyYn1').prop("disabled", true );

			var value = $(this).find(':selected').text();

			console.log(value);

			if(value.toLowerCase().search("foto survey") != -1){
				$('#surveyYn1').prop("disabled", false );
				$('#surveyYn1').prop("checked", true );

			}

			if(value.toLowerCase().search("worksheet") != -1){
				$("#name").prop('readonly', true);
				$("#name").val("worksheet");
			}

			if(value.toLowerCase().search("dokumen pelengkap") != -1){
				$('#surveyYn1').prop("disabled", true );
				$('#mandatory1').prop("disabled", true );

			}




		})

    });

</script>
</body>
</html>