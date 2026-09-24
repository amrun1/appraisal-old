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
    <link href="${ctxPath}/css/main-app.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="page-header-page">
        <div class="absolute-right btn-group">
            <a href="list" class="btn btn-primary"><i class="icon-white icon-list"></i> List Data</a>
            <a href="javascript:toggleTarget('#xls-info')" title="Show Form Filter" class="btn btn-small btn-success">
                <i class="icon-white icon-inbox"></i> Informasi
            </a>
        </div>
        <div class="page-header">
            <h1>Upload Data Kode Pos</h1>
        </div>
        <div class="panel hide alert alert-info" id="xls-info">
            <strong>
                <p>
                    File upload harus berformat CSV yang tersimpan di FTP<br>
                    Urutan Kolom pada file adalah sebagai berikut<br>
                <ul>
                    <li>Propinsi</li>
                    <li>Kabupaten/Kota</li>
                    <li>Kecamatan</li>
                    <li>Kelurahan</li>
                    <li>Kode Pos</li>
                    <li>Nama PIC</li>
                </ul>
                </p>
            </strong>
        </div>
    </div>
    <form action="upload?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data" class="form-horizontal"
          style="margin-top:60px;">
        <div class="control-group">
                <label class="control-label">File <sup>(FTP)</sup></label>
            <div class="controls">
                <input type="text" readonly="readonly" value="${ftpDir}">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">&nbsp;</label>
            <div class="controls">
                <button type="submit" class="btn btn-success"><i class="icon-white icon-check"></i> Upload</button>
            </div>
        </div>
    </form>
</div>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/jquery.js"></script>
<script src="${ctxPath}/js/bootstrap_2.3.2/js/bootstrap.min.js"></script>
<!--[if lt IE 9]>
<script src="${ctxPath}/js/html5shiv.js"></script>
<script src="${ctxPath}/js/respond.min.js"></script>
<![endif]-->
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
</body>
</html>