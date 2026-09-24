<jsp:root version="1.2" xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:c="urn:jsptld:http://java.sun.com/jstl/core_rt">
    <jsp:directive.page contentType="text/html; charset=UTF-8"/>
    <jsp:text>
        <![CDATA[<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">]]>
    </jsp:text>

    <jsp:text>
        <![CDATA[<html xmlns="http://www.w3.org/1999/xhtml" lang="en">]]>
    </jsp:text>

    <head>
        <meta http-equiv="Expires" content="-1"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <meta http-equiv="Cache-Control" content="no-cache"/>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
        <style type="text/css" media="all">
            @import url("css/lama/maven-base.css");
            @import url("css/lama/maven-theme.css");
            @import url("css/lama/site.css");
            @import url("css/lama/screen.css");
        </style>
        <link rel="stylesheet" href="./css/lama/print.css" type="text/css"
              media="print"/>
        <style>
            #banner img {
                padding-left: 15px;
            }
        </style>
    </head>
    <body style="background-color: #EFEFEF">
    <jsp:text>
    <![CDATA[
    <body class="composite">]]>
    </jsp:text>
    <div id="banner">
        <img src="images/lama/appraisal.gif" align="left"/>
        <div class="xright" style="padding-top: 30px; font-weight: bold;">
            <a target="mainFrame" href="profile/update"><img src="images/lama/prof.png"/> Profile</a>
            <a href="logout" target="_parent"><img src="images/lama/logout.jpg"/>Logout</a>
        </div>
    </div>
    <div style="position: absolute; bottom: 1px; left: 2px;">
        <a id="toggle-menu" href="javascript:window.top.closeMenu();" class="btn btn-danger"><img src="images/lama/logout.jpg" alt="Buka dan Tutup Menu"/></a>
    </div>
    </body>
</jsp:root>