<!DOCTYPE html>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el"%>
<html lang="en">
<head>
    <c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
    <!-- start: Meta -->
    <meta charset="utf-8">
    <title>Web Appraisal - Collateral Valuation</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">
    <meta http-equiv="Content-Language" content="en-us">
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
    <link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" />
    <!-- end: Meta -->

    <!-- start: Mobile Specific -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <!-- end: Mobile Specific -->

    <!-- start: CSS -->
    <link id="bootstrap-style" href="${ctxPath}/css/login/bootstrap.css" rel="stylesheet">
    <link href="${ctxPath}/css/login/bootstrap-responsive.min.css" rel="stylesheet">
    <link id="base-style" href="${ctxPath}/css/login/style2.css" rel="stylesheet">
    <link id="base-style-responsive" href="${ctxPath}/css/login/style-responsive.css" rel="stylesheet">
    <link id="base-style" href="${ctxPath}/css/login/custom.css" rel="stylesheet">
    <style>
        #username::-webkit-input-placeholder::after {
            display:block;
            content:"Line 2\A Line 3";
        }
    </style>
    <!-- end: CSS -->

    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="${ctxPath}/js/html5shiv.js"></script>
    <script src="${ctxPath}/js/respond.min.js"></script>

    <![endif]-->

    <!-- start: Favicon -->
    <link rel="shortcut icon" href="${ctxPath}/images/favicon.ico">
    <!-- end: Favicon -->
    <script type="text/javascript">
        window.onload=function checkFrame() {
            if(window.location != window.parent.location){
                window.top.location.href = 'logout';
            }
        }
    </script>
</head>

<body>
<div class="container-fluid">

    <div class="row-fluid">

        <div class="login-box">
            <div class="clearfix">&nbsp;</div>
            <div class="text-center"><img src="${ctxPath}/images/header-logo2.png"></div>
            <div class="clearfix">&nbsp;</div>
            <form class="form-horizontal" action="<c:url value='/j_spring_security_check' />" method="POST">
                <fieldset>
                    <c:if test="${error == 'error : No AuthenticationProvider found for org.springframework.security.authentication.UsernamePasswordAuthenticationToken'}">
                        <div class="form-alert alert alert-warning">User dan Password Anda Salah</div>
                    </c:if>
                    <c:if test="${error != null && error != 'error : No AuthenticationProvider found for org.springframework.security.authentication.UsernamePasswordAuthenticationToken'}">
                        <div class="form-alert alert alert-warning">${error}</div>
                    </c:if>
                    <c:if test="${msg != null}">
                        <div class="form-alert alert alert-warning">${msg}</div>
                    </c:if>
                    <div class="input-prepend" title="Username">
                        <span class="add-on"><i class="icon-user"></i></span>
                        <input class="input-large span10" name="username" id="username" type="text" placeholder="username" autofocus/>
                    </div>
                    <div class="clearfix"></div>

                    <div class="input-prepend" title="Password">
                        <span class="add-on"><i class="icon-lock"></i></span>
                        <input class="input-large span10" name="password" id="password" type="password" placeholder="password"/>
                    </div>
                    <div class="clearfix"></div>

                    <div class="btn-group button-login">
                        <button type="submit" class="btn btn-primary">Login</button>
                    </div>
                    <div class="clearfix"></div>
                </fieldset>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </form>
            <p align="center" style="color: #000000" id="timelable"></p>
        </div><!--/span-->

        <p align="center">
            <font style="font-family: Arial; font-size: 11px; color: #cd0a0a">This
                computer application system, its network and data contained therein
                are the property of the PT. Bank Permata,Tbk.<br /> Access to this
                system and network are restricted. Unauthorized access is prohibited
                and is wrongful under law.
            </font>
        </p>
        <p align="center">
            <font style="font-family: Arial; font-size: 11px"><b>Collateral Valuation - PT. Bank Permata, Tbk</b></font>
        </p>
    </div>

</div>

<script language="JavaScript">
    function msieversion() {

        var ua = window.navigator.userAgent;
        var msie = ua.indexOf("MSIE ");

        if (msie > -1 || !!navigator.userAgent.match(/Trident.*rv\:11\./))  // If Internet Explorer, return version number
        {
            document.getElementById("alert-ie").style.display = 'block';
        }
        else  // If another browser, return 0
        {
            // alert('otherbrowser');
        }

        return false;
    }
    msieversion();

    var myVar = setInterval(function(){ myTimer() }, 1000);
    var jsVar=  <%=java.util.Calendar.getInstance().getTimeInMillis()%>;
    var timeZoneOffset=<%=java.util.TimeZone.getDefault().getOffset(System.currentTimeMillis())%>;

    jsVar=jsVar+timeZoneOffset;
    function myTimer() {
        jsVar=jsVar+1000;
        var d = new Date(jsVar);
        var t=d.toUTCString();
        document.getElementById("timelable").innerHTML = t;
    }
</script>

<!-- end: JavaScript-->

</body>
</html>
