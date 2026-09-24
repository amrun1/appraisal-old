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
                font: bold 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
                color: #4f6b72;
                border-right: 1px solid #C1DAD7;
                border-bottom: 1px solid #C1DAD7;
                border-top: 1px solid #C1DAD7;
                letter-spacing: 2px;
                text-transform: uppercase;
                text-align: left;
                padding: 6px 6px 6px 12px;
                background: #CAE8EA url(images/bg_header.jpg) no-repeat;
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

        Intra Day Periode <c:out value="${createdDate}"/> - <c:out value="${closedDate}"/>

    </h3>
    <p></p>

    <!--

    <div>Date: <input type="text"  name="dateFrom" id=?

    -->

    <hr></hr>
        <%--<table  id="table-list" class="table dataTable table-hover table-striped bg-white"><!--table-hover-->--%>
    <c:if test="${JumlahRecord > 0}">
        <table class="mytable" width="700">
            <tr>
                <th>Tanggal</th>
                <th><08:00</th>
                <th>08:00-09:00</th>
                <th>09:00-10:00</th>
                <th>10:00-11:00</th>
                <th>11:00-12:00</th>
                <th>12:00-13:00</th>
                <th>13:00-14:00</th>
                <th>14:00-15:00</th>
                <th>15:00-16:00</th>
                <th>16:00-17:00</th>
                <th>17:00-18:00</th>
                <th>18:00<</th>
                <th>Total Daily</th>
            </tr>
            <c:set var="sum7" value="0" />
            <c:set var="sum8" value="0" />
            <c:set var="sum9" value="0" />
            <c:set var="sum10" value="0" />
            <c:set var="sum11" value="0" />
            <c:set var="sum12" value="0" />
            <c:set var="sum13" value="0" />
            <c:set var="sum14" value="0" />
            <c:set var="sum15" value="0" />
            <c:set var="sum16" value="0" />
            <c:set var="sum17" value="0" />
            <c:set var="sum18" value="0" />
            <c:forEach items="${ListView}" var="item">
                <c:set var="sum7" value="${sum7+item.sum7}" />
                <c:set var="sum8" value="${sum8+item.sum8}" />
                <c:set var="sum9" value="${sum9+item.sum9}" />
                <c:set var="sum10" value="${sum10+item.sum10}" />
                <c:set var="sum11" value="${sum11+item.sum11}" />
                <c:set var="sum12" value="${sum12+item.sum12}" />
                <c:set var="sum13" value="${sum13+item.sum13}" />
                <c:set var="sum14" value="${sum14+item.sum14}" />
                <c:set var="sum15" value="${sum15+item.sum15}" />
                <c:set var="sum16" value="${sum16+item.sum16}" />
                <c:set var="sum17" value="${sum17+item.sum17}" />
                <c:set var="sum18" value="${sum18+item.sum18}" />
                <tr>
                    <td>${item.setTgl}</td>
                    <td><c:out value="${item.sum7}" /></td>
                    <td><c:out value="${item.sum8}" /></td>
                    <td><c:out value="${item.sum9}" /></td>
                    <td><c:out value="${item.sum10}" /></td>
                    <td><c:out value="${item.sum11}" /></td>
                    <td><c:out value="${item.sum12}" /></td>
                    <td><c:out value="${item.sum13}" /></td>
                    <td><c:out value="${item.sum14}" /></td>
                    <td><c:out value="${item.sum15}" /></td>
                    <td><c:out value="${item.sum16}" /></td>
                    <td><c:out value="${item.sum17}" /></td>
                    <td><c:out value="${item.sum18}" /></td>
                    <td><c:out value="${item.sum7+item.sum8+item.sum9+item.sum10+item.sum11+item.sum12+item.sum13+item.sum14+item.sum15+item.sum16+item.sum17+item.sum18}" /></td>
                </tr>
            </c:forEach>
            <tr>
                <th>Total</th>
                <th><c:out value="${sum7}" /></th>
                <th><c:out value="${sum8}" /></th>
                <th><c:out value="${sum9}" /></th>
                <th><c:out value="${sum10}" /></th>
                <th><c:out value="${sum11}" /></th>
                <th><c:out value="${sum12}" /></th>
                <th><c:out value="${sum13}" /></th>
                <th><c:out value="${sum14}" /></th>
                <th><c:out value="${sum15}" /></th>
                <th><c:out value="${sum16}" /></th>
                <th><c:out value="${sum17}" /></th>
                <th><c:out value="${sum18}" /></th>
                <th><c:out value="${sum7+sum8+sum9+sum10+sum11+sum12+sum13+sum14+sum15+sum16+sum17+sum18}"/></th>
            </tr>
        </table>
    </c:if>

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