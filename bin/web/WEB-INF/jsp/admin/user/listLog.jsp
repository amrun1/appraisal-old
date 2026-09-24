<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"> <!--<![endif]-->
<head>
    <c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
    <title>Appraisal</title>
    <meta http-equiv="Expires" content="-1" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Cache-Control" content="no-cache" />
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <link href="${ctxPath}/js/bootstrap_2.3.2/css/bootstrap.css" rel="stylesheet">
    <link href="${ctxPath}/js/datatables.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctxPath}/js/DataTables-1.10.12/css/dataTables.bootstrap.min.css" type="text/css" />
    <link href="${ctxPath}/css/main-app.css" rel="stylesheet">

    <!--[if lt IE 9]>
    <script src="${ctxPath}/js/html5shiv.js"></script>
    <script src="${ctxPath}/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <div class="page-header">

    </div>

    <table id="table-list" class="table table-striped bg-white" width="100%"></table>
</div>
<script type="text/javascript" src="${ctxPath}/js/jquery-ui-1.10.3/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${ctxPath}/js/DataTables-1.10.12/js/jquery.dataTables.min.js"></script>
<%--<script type="text/javascript" src="${ctxPath}/js/DataTables-1.10.12/js/dataTables.bootstrap.min.js"></script>--%>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
    var dataTable;
    var _csrf_token = "${_csrf.token}";
    // init component
    $(document).ready(function() {
        $.active = false;
        $('body').bind('click keypress', function() { $.active = true; });
        var logout = '${ctxPath}/logoutExpired';
        var heartBeat = '${ctxPath}/heartbeat';
        checkActivity(${maxInterval}, 60000, 0,logout,heartBeat);

        dataTable = $('#table-list').dataTable({
            "sAjaxSource" : "<s:url value="findLog"/>",
            "fnServerData" : function(sSource, aoData, fnCallback, oSettings) {

                aoData.push(

                        {
                            "name" : "personId",
                            "value" : "${personId}"
                        }

                );

                $.ajax({
                    "dataType" : 'json',
                    "type" : "GET",
                    "url" : sSource,
                    "data" : aoData,
                    "success" : fnCallback
                });
            },
            "aoColumns" : [

                {"mData" : "personId", "sTitle" : "Username"},
                {"mData" : "dateFormatJson", "sTitle" : "Date"},
                {"mData" : "ipAddress", "sTitle" : "Ip Address"},
                {"mData" : "logCode", "sTitle" : "Log"},
                {"mData" : "keterangan", "sTitle" : "Keterangan"}

            ],

            "fnDrawCallback" : function(settings, json){

            }
        });




        // main app
        dataTable.DataTable().ajax.reload();

    });
</script>
</body>
</html>