<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
<head>
	<c:set var="ctxPath" value="${pageContext.request.contextPath}" />
	<title>Index Page</title>
</head>
<body>
	<ul>
		<li><a href="${ctxPath}/login">Login</a></li>
		<li><a href="${ctxPath}/logout">Logout</a></li>
	</ul>
	<h1>INDEX DARI FILE INDEX.JSP</h1>
	
	<h2>Do nothing</h2>
	
	<h2>time ${date_time}</h2>
	
	<c:if test="${person == null}">PERSON NULL</c:if><br/>
	<c:if test="${person != null}">${person}</c:if><br/>
	
	<content>
		isi containt 1
	</content>
	<content tag="body">
		isi containt 2
	</content>
</body>
<div id="botton_javascript">
	<script type="text/javascript">
        /* JS on index file 1 */
    </script>
</div>
</html>