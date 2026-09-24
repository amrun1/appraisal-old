<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
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

	<link href="${ctxPath}/js/bootstrap_2.3.2/css/bootstrap.css" rel="stylesheet">
	<link href="${ctxPath}/js/bootstrap_2.3.2/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="${ctxPath}/js/bootstrap_2.3.2/css/docs.css" rel="stylesheet">
	<link href="${ctxPath}/js/bootstrap_2.3.2/js/google-code-prettify/prettify.css" rel="stylesheet">
	<link href="${ctxPath}/css/main-app.css" rel="stylesheet">

	<!--[if gte IE 9]><!-->
		<link rel="stylesheet" media="screen" href="${ctxPath}/css/glyphicons/glyphicons.min.css" >
	<!--<![endif]-->
	<!--[if lt IE 9]>
		<script src="${ctxPath}/js/html5shiv.js"></script>
		<script src="${ctxPath}/js/respond.min.js"></script>
	<![endif]-->
</head>
<body>
<div class="container">
	<div class="page-header">
		<h1>Automatic Logout Form</h1>
	</div>
	<c:if test="${not empty msg}">
		<div class="alert alert-success">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<strong>${msg}</strong>
		</div>
	</c:if>
	<f:form method="post" modelAttribute="formBean" action="updateAutoLogout" class="form-horizontal">
		<div class="control-group">
			<label class="control-label" for="value">Time</label>
			<div class="controls">
				<f:input path="value" type="text" class="form-control" data-mask="number" maxlength="2" placeholder="Time" style="text-align:right;"/>Minute
				<f:errors path="value" class="error has-error" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="name">&nbsp;</label>
			<div class="controls">
				<button type="submit" class="btn btn-small btn-success"><i class="icon-white icon-ok"></i> Save</button>
				<%--<button type="button" class="btn btn-small btn-warning" onclick="gotoUrl('list')"><i class="icon-white icon-step-backward"></i> Back</button>--%>
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
<script src="${ctxPath}/js/jquery.inputmask.bundle.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>

<script>

    $(document).ready(function () {
        $.active = false;
        $('body').bind('click keypress', function() { $.active = true; });
        var logout = '${ctxPath}/logoutExpired';
        var heartBeat = '${ctxPath}/heartbeat';
        checkActivity(${maxInterval}, 60000, 0,logout,heartBeat);


    });


    document.getElementById("value").onkeyup=function(){
        var input=parseInt(this.value);
        if(input<0 || input>60) {
            alert("Value should be between 0 - 60");
            $('#value').val('');
        }
        return;
    }

</script>
</body>
</html>