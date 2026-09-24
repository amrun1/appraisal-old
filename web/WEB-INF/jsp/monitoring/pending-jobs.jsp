<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page import="com.permata.appraisal2.common.AppConstans" %>
<%@ page import="com.permata.appraisal2.model.PersonLevel" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
<head>

	<c:set var="ctxPath" value="${pageContext.request.contextPath}" />
	<c:set var="task" value='<%= request.getParameter("task") %>'/>

	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<link rel="shortcut icon" type="image/x-icon" href="${ctxPath}/images/favicon.ico" />
	<title>Pending Job</title>
	<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
	<script type="text/javascript">
		var closeMenu;
		$(document).ready(function () {
			closeMenu = function(){
				var lebar = $('#midFrame').attr('cols');
				if(lebar == "220,1*"){
					$('#midFrame').attr('cols', "0,*");
				} else {
					$('#midFrame').attr('cols', "220,1*");
				}
			}
		})
	</script>
</head>
<c:if test="${task == 1}">
	<frameset rows="100,1*,30" cols="*" frameborder="no" border="0" framespacing="0">
		<frame src="pending-jobs-header" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" title="topFrame" />
		<frameset cols="*" frameborder="no" border="0" framespacing="0" id="midFrame">
			<frame src="pending-jobs-isi" scrolling="no" noresize="noresize"  />
		</frameset>
	</frameset>
</c:if>
<c:if test="${task == 2}">
	<frameset rows="120,1*,30" cols="*" frameborder="no" border="0" framespacing="0">
	<frame src="pending-jobs-cust-header" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" title="topFrame" />
	<frameset cols="*" frameborder="no" border="0" framespacing="0" id="midFrame">
	<frame src="pending-jobs-cust-isi" scrolling="no" noresize="noresize"  />
	</frameset>
	</frameset>
</c:if>
<%--test--%>
<%--test <c:out value="${task}"/>--%>
	<%--<frameset rows="120,1*,30" cols="*" frameborder="no" border="0" framespacing="0">--%>
		<%--<frame src='<c:out value="${header}"/>' name="topFrame" scrolling="auto" noresize="noresize" id="topFrame" title="topFrame" />--%>
		<%--<frameset cols="*" frameborder="no" border="0" framespacing="0" id="midFrame">--%>
			<%--<frame src='<c:out value="${isi}"/>' scrolling="auto" noresize="noresize"  />--%>
		<%--</frameset>--%>
	<%--</frameset>--%>
</html>