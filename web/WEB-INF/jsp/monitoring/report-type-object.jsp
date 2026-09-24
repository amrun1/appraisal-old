<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.permata.appraisal2.common.AppConstans" %>
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
    <link href="${ctxPath}/css/main-app.css" rel="stylesheet">
    <!--<link href="${ctxPath}/js/bootstrap_2.3.2/css/bootstrap.css" rel="stylesheet">
    <link href="${ctxPath}/js/datatables.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctxPath}/js/DataTables-1.10.12/css/dataTables.bootstrap.min.css" type="text/css" />
    <link href="${ctxPath}/css/main-app.css" rel="stylesheet">
    <style type="text/css">
        /*.table {
            width: auto;
        }
        .table.dataTable {
            width: auto;
        }*/
        div.dataTables_scrollHeadInner {
            width: 100%;
        }
        div.dataTables_wrapper {
            width: 1500px;
            margin: 0 auto;
        }
        #header {
            /*background:red;*/
            /*height:50px;*/
            /*width:100%;*/
            position:fixed;
            /*top:0;*/
            /*left:0;*/
        }
    </style>-->

        <style type="text/css">
            <!--
            #mytable {
                padding: 0;
                margin: 0;

            }

            th {
                /* font: bold 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; */
                color: #fff;
                border-right: 1px solid #C1DAD7;
                border-bottom: 1px solid #C1DAD7;
                border-top: 1px solid #C1DAD7;
                letter-spacing: 2px;
                text-transform: uppercase;
                text-align: left;
                padding: 6px 6px 6px 12px;
                background: #0064ff url(images/bg_header.jpg) no-repeat;
            }

            th.nobg {
                border-top: 0;
                border-left: 0;
                border-right: 1px solid #C1DAD7;
                background: none;
            }

            td {
                border-right: 1px solid #C1DAD7;
                border-bottom: 1px solid #C1DAD7;
                background: #fff;
                padding: 6px 6px 6px 12px;
                color: #4f6b72;
                font-size: 12px
            }


            td.alt {
                background: #F5FAFA;
                color: #797268;
            }

            th.spec {
                border-left: 1px solid #C1DAD7;
                border-top: 0;
                background: #fff url(images/bullet1.gif) no-repeat;
                font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
            }

            th.specalt {
                border-left: 1px solid #C1DAD7;
                border-top: 0;
                background: #f5fafa url(images/bullet2.gif) no-repeat;
                font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
                color: #797268;
            }
            -->
        </style>
</head>
<body onload="initialize();">
    <%
        // Set refresh, autoload time as 5 seconds
        response.setIntHeader("Refresh", 1200);
        // Get current time

    %>

<div class="container isi">
    <p align="center"></p>

    <h3>

        Real Distribution Order Periode <c:out value="${createdDate}"/> - <c:out value="${closedDate}"/>

    </h3>
    <p></p>

    <!--

    <div>Date: <input type="text"  name="dateFrom" id=?

    -->

    <hr></hr>
        <%--<table  id="table-list" class="table dataTable table-hover table-striped bg-white"><!--table-hover-->--%>
        <table>
            <c:set var="par" value="${paramss}"/>
            <c:if test="${par=='1'}">
                <thead>
                <tr>
                    <th>object</th>
                    <c:forEach items="${ListBranch}" var="row" varStatus="status">
                        <th>${row.name}</th>
                    </c:forEach>
                    <th>GRAND<br>TOTAL</th>
                    <div class="control-group">
                </tr>
                </thead>
            </c:if>
            <c:if test="${par=='2'}">
            <thead>
                <tr>
                    <th rowspan="2">object</th>
                    <c:forEach items="${ListBranch}" var="row" varStatus="status">
                        <th colspan="3">${row.name}</th>
                    </c:forEach>
                    <th rowspan="2">GRAND<br>TOTAL</th>
                </tr>
            <tr>
                <c:forEach items="${ListBranch}" var="row" varStatus="status">
                <th>Int</th>
                <th>Eks</th>
                <th>Ind</th>
                </c:forEach>
            </tr>
            </thead>
            </c:if>
            <tbody>
            <c:forEach items="${ListDetail}" var="data" varStatus="status">
                <tr>
                    <td>${data.jenisAktiva}</td>
                    <c:set var="totJenisAktiva" value="0"/>
                    <c:forEach items="${data.bisnisBranch}" var="item">
                        <td>${item}</td>
                        <c:set var="totJenisAktiva" value="${totJenisAktiva+item}"/>
                    </c:forEach>
                    <td><c:out value="${totJenisAktiva}"/></td>
                </tr>
            </c:forEach>
            <tr>
                <th>Total</th>
                <c:set var="grandTot" value="0"/>
                <c:forEach items="${ListTotalGroup}" var="item">
                    <th><c:out value="${item}" /></th>
                    <c:set var="grandTot" value="${grandTot+item}"/>
                </c:forEach>
                <th><c:out value="${grandTot}"/></th>
            </tr>

            </tbody>
		</table>

</div>
<!--[if lt IE 9]>
<script type="text/javascript" src="${ctxPath}/js/html5shiv.js"></script>
<script type="text/javascript" src="${ctxPath}/js/respond.min.js"></script>
<![endif]-->
<!--<script type="text/javascript" src="${ctxPath}/js/jquery-ui-1.10.3/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${ctxPath}/js/DataTables-1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/jquery.inputmask.bundle.min.js"></script>
<script type="text/javascript" src="${ctxPath}/js/main-app.js"></script>
<script type="text/javascript">
    <%--var dataTable;--%>
    <%--var _csrf_token = "${_csrf.token}";--%>
    <%--// init component--%>

    var speed=1
    var currentpos=0,alt=1,curpos1=0,curpos2=-1
    var tmpCurrentpos = 1;
    var upDown = true;

    function scrollwindow(){
        var scrollTop = document.documentElement ? document.documentElement.scrollTop :
                document.body.scrollTop;

        //if (document.all && !document.getElementById)
        //    temp=scrollTop
        //else
        //    temp=window.pageYOffset

        if (alt==0)
            alt=2
        else
            alt=1
        if (alt==0)
            curpos1=scrollTop
        else
            curpos2=scrollTop

        // alert(scrollTop);

        if (curpos1!=curpos2){
            tmpCurrentpos = currentpos;
            if (upDown){
                if (document.all)
                    currentpos=scrollTop+speed
                else
                    currentpos=window.pageYOffset+speed
                window.scroll(0,currentpos)
            }else {
                if (document.all)
                    currentpos=scrollTop-speed
                else
                    currentpos=window.pageYOffset-speed
                window.scroll(0,currentpos)
            }
            if (currentpos == tmpCurrentpos){
                upDown = false;
                curpos2=-1
            }

        }
        else{
            if (currentpos != tmpCurrentpos)
                upDown = true;
            currentpos=1
            window.scroll(0,currentpos)
        }
    }

    function startit(){
        setInterval("scrollwindow()",80)
    }

    function initialize(){
        startit()
    }

//    $(document).ready(function () {
//        $('.isi').hover(function () {
//            window.onload = null;
//        }, function () {
//            window.onload = initialize();
//        });
//    }

</script>-->
</body>
</html>