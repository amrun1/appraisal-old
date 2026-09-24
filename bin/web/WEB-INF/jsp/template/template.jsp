<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>Title :: <sitemesh:write property='title' /></title>
</head>
<body>
	<ul>
		<li><a href="${ctxPath}/login">Login</a></li>
		<li><a href="${ctxPath}/logout">Logout</a></li>
	</ul>
	<h1>Body From Template</h1>
	<sitemesh:write property='body' />
	<script type="text/javascript">
		/* JS on main template */
	</script>
	+++++
	<sitemesh:write property='div.botton_javascript' />
	-----
</body>
</html>