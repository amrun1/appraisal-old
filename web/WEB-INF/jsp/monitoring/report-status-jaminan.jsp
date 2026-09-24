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

    <h1>

        Report Status Jaminan

    </h1>
    <p></p>

        <table>
            <thead>
                <tr>
                    <th>Status<br>Jaminan</th>
                    <c:forEach items="${listBranch}" var="row" varStatus="status">
                        <th>${row.name}</th>
                    </c:forEach>
                    <th>GRAND<br>TOTAL</th>
                    <div class="control-group"/>
                </tr>
            </thead>
            <tbody>
            <c:set var="bulan" value=""/>
                <c:forEach items="${listStatusJaminan}" var="rows" varStatus="status">
                    <tr>
                        <c:forEach items="${rows}" var="data" varStatus="status">
                            <c:set var="print" value="true"/>
                            <c:choose>
                                <c:when test="${status.first}">
                                    <c:if  test="${data!=bulan}">
                                        </tr>
                                            <td><strong><c:out value="${data}"/></strong></td>
                                        <c:forEach items="${listBranch}" var="row" varStatus="status">
                                            <td></td>
                                        </c:forEach>
                                        <td/>
                                        <tr>
                                        <c:set var="bulan" value="${data}"/>
                                    </c:if>
                                </c:when>
                                <c:otherwise>
                                    <td><c:out value="${data}"/></td>
                                </c:otherwise>
                            </c:choose>


                        </c:forEach>
                    </tr>
                </c:forEach>
            </tbody>
		</table>
    Total : <c:out value="${totalSemua}"/>
</div>
<!--[if lt IE 9]>
<script type="text/javascript" src="${ctxPath}/js/html5shiv.js"></script>
<script type="text/javascript" src="${ctxPath}/js/respond.min.js"></script>
<![endif]-->

</body>
</html>