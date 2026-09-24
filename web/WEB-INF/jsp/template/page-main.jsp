<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page import="com.permata.appraisal2.common.AppConstans" %>
<%@ page import="com.permata.appraisal2.model.PersonLevel" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
<head>

	<c:set var="ctxPath" value="${pageContext.request.contextPath}" />
	<c:set var="marketing" value="<%= PersonLevel.MARKETING %>"/>
	<c:set var="appAdmin" value="<%= PersonLevel.APPRAISAL_ADMIN %>"/>
	<c:set var="appValuer" value="<%= PersonLevel.APPRAISAL_VALUER %>"/>
	<c:set var="appSurveyor" value="<%= PersonLevel.APPRAISAL_SURVEYOR %>"/>
	<c:set var="appApproval" value="<%= PersonLevel.APPRAISAL_APPROVAL %>"/>
    <c:set var="king" value="<%= PersonLevel.KING %>"/>
	<c:set var="role911" value="<%= PersonLevel.ROLE_911 %>"/>
    <c:set var="adminDoank" value="<%= PersonLevel.ADMIN %>"/>

	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" />
	<title>Web Appraisal - Collateral Valuation</title>
	<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
	<script type="text/javascript">


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
	<c:if test="${person == null}">
		<c:redirect url="logout" />
	</c:if>
	<frameset rows="80,1*,20" cols="*" frameborder="no" border="0" framespacing="0">
		<frame src="page-header" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" title="topFrame" />
		<frameset cols="220,1*" frameborder="no" border="0" framespacing="0" id="midFrame">
			<frame src="page-menu" name="leftFrame" scrolling="auto" noresize="noresize" id="leftFrame" title="leftFrame" />
            <c:choose>
                <c:when test="${roleActive.level == marketing}">
                    <frame src="data-umum/list" name="mainFrame" id="mainFrame" title="mainFrame" scrolling="auto" />
                </c:when>
                <c:when test="${roleActive.level == appAdmin}">
                    <frame src="app-admin/list" name="mainFrame" id="mainFrame" title="mainFrame" scrolling="auto" />
                </c:when>
                <c:when test="${roleActive.level == appValuer}">
                    <frame src="app-valuer/list" name="mainFrame" id="mainFrame" title="mainFrame" scrolling="auto" />
                </c:when>
                <c:when test="${roleActive.level == appSurveyor}">
                    <frame src="app-surveyor/list" name="mainFrame" id="mainFrame" title="mainFrame" scrolling="auto" />
                </c:when>
                <c:when test="${roleActive.level == appApproval}">
                    <frame src="app-approval/list" name="mainFrame" id="mainFrame" title="mainFrame" scrolling="auto" />
                </c:when>
                <c:when test="${roleActive.level == king}">
                    <frame src="admin/user/list" name="mainFrame" id="mainFrame" title="mainFrame" scrolling="auto" />
                </c:when>
                <c:when test="${roleActive.level == adminDoank}">
                    <frame src="master/systemparam/addCot" name="mainFrame" id="mainFrame" title="mainFrame" scrolling="auto" />
                </c:when>
                <c:otherwise>
                    <frame src="page-content" name="mainFrame" id="mainFrame" title="mainFrame" scrolling="auto" />
                </c:otherwise>
            </c:choose>
		</frameset>
	</frameset>
</html>