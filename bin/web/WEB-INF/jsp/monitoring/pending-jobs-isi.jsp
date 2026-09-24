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
    <link href="${ctxPath}/js/bootstrap_2.3.2/css/bootstrap.css" rel="stylesheet">
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
    </style>
</head>
<body onload="initialize();">
    <%
        // Set refresh, autoload time as 5 seconds
        response.setIntHeader("Refresh", 1200);
        // Get current time

    %>

<div class="container isi">
        <table  id="table-list" class="table dataTable table-hover table-striped bg-white"><!--table-hover-->

			<c:forEach items="${listNama}" var="row" varStatus="status">
                <tbody>
                <tr>
                    <td rowspan="4" width="5%">${status.index+1}</tdrowspan>
                    <td rowspan="4" width="20%">${row.nama}</td>
                    <c:set var="adm" value="0"></c:set>
                    <c:if test="${listAdmin !=null}">
                    <c:forEach items="${listAdmin}" var="rowAdmin" varStatus="admin">
                        <c:if test="${rowAdmin.nama == row.nama && rowAdmin.param==1}">
                            <c:set var="adm" value="1"></c:set>
                            <td width="8%">Admin</td>
                            <td style="text-align: center;" width="10%">${rowAdmin.bd5}</td>
                            <td style="text-align: center;" width="10%">${rowAdmin.bd4}</td>
                            <td style="text-align: center;" width="10%">${rowAdmin.bd3}</td>
                            <td style="text-align: center;" width="10%">${rowAdmin.bd2}</td>
                            <td style="text-align: center;" width="10%">${rowAdmin.bd1}</td>
                            <td style="text-align: center;" width="10%">${rowAdmin.bd0}</td>
                            <td style="text-align: center;" width="10%">${rowAdmin.bd0 + rowAdmin.bd1 + rowAdmin.bd2 + rowAdmin.bd3 + rowAdmin.bd4 + rowAdmin.bd5}</td>
                        </c:if>
                    </c:forEach>
                    </c:if>

                    <c:if test="${adm==0}">
                        <td width="8%">Admin</td>
                        <td style="text-align: center;" width="10%">0</td>
                        <td style="text-align: center;" width="10%">0</td>
                        <td style="text-align: center;" width="10%">0</td>
                        <td style="text-align: center;" width="10%">0</td>
                        <td style="text-align: center;" width="10%">0</td>
                        <td style="text-align: center;" width="10%">0</td>
                        <td style="text-align: center;" width="10%">0</td>
                    </c:if>
                </tr>
                <tr>
                    <c:set var="sur" value="0"></c:set>
                    <c:if test="${listSurveyor !=null}">
                    <c:forEach items="${listSurveyor}" var="rowSurveyor" varStatus="surveyor">
                        <c:if test="${rowSurveyor.nama == row.nama && rowSurveyor.param==1}">
                            <c:set var="sur" value="1"></c:set>
                            <td width="8%">Surveyor</td>
                            <td style="text-align: center;" width="10%">${rowSurveyor.bd5}</td>
                            <td style="text-align: center;" width="10%">${rowSurveyor.bd4}</td>
                            <td style="text-align: center;" width="10%">${rowSurveyor.bd3}</td>
                            <td style="text-align: center;" width="10%">${rowSurveyor.bd2}</td>
                            <td style="text-align: center;" width="10%">${rowSurveyor.bd1}</td>
                            <td style="text-align: center;" width="10%">${rowSurveyor.bd0}</td>
                            <td style="text-align: center;" width="10%">${rowSurveyor.bd0 + rowSurveyor.bd1 + rowSurveyor.bd2 + rowSurveyor.bd3 + rowSurveyor.bd4 + rowSurveyor.bd5}</td>
                        </c:if>
                    </c:forEach>
                    </c:if>

                    <c:if test="${sur==0}">
                        <td width="8%">Surveyor</td>
                        <td style="text-align: center;" width="10%">0</td>
                        <td style="text-align: center;" width="10%">0</td>
                        <td style="text-align: center;" width="10%">0</td>
                        <td style="text-align: center;" width="10%">0</td>
                        <td style="text-align: center;" width="10%">0</td>
                        <td style="text-align: center;" width="10%">0</td>
                        <td style="text-align: center;" width="10%">0</td>
                    </c:if>
                </tr>
                <tr>
                    <c:set var="val" value="0"></c:set>
                    <c:if test="${listValuer !=null}">
                        <c:forEach items="${listValuer}" var="rowValuer" varStatus="valuer">
                            <c:if test="${rowValuer.nama == row.nama && rowValuer.param==1}">
                                <c:set var="val" value="1"></c:set>
                                <td width="8%">Valuer</td>
                                <td style="text-align: center;" width="10%">${rowValuer.bd5}</td>
                                <td style="text-align: center;" width="10%">${rowValuer.bd4}</td>
                                <td style="text-align: center;" width="10%">${rowValuer.bd3}</td>
                                <td style="text-align: center;" width="10%">${rowValuer.bd2}</td>
                                <td style="text-align: center;" width="10%">${rowValuer.bd1}</td>
                                <td style="text-align: center;" width="10%">${rowValuer.bd0}</td>
                                <td style="text-align: center;" width="10%">${rowValuer.bd0 + rowValuer.bd1 + rowValuer.bd2 + rowValuer.bd3 + rowValuer.bd4 + rowValuer.bd5}</td>
                            </c:if>
                        </c:forEach>
                    </c:if>
                    <c:if test="${val==0}">
                        <td width="8%">Valuer</td>
                        <td style="text-align: center;" width="10%">0</td>
                        <td style="text-align: center;" width="10%">0</td>
                        <td style="text-align: center;" width="10%">0</td>
                        <td style="text-align: center;" width="10%">0</td>
                        <td style="text-align: center;" width="10%">0</td>
                        <td style="text-align: center;" width="10%">0</td>
                        <td style="text-align: center;" width="10%">0</td>
                    </c:if>
                </tr>
                <tr>
                    <c:set var="app" value="0"></c:set>
                    <c:if test="${listApproval !=null}">
                        <c:forEach items="${listApproval}" var="rowApproval" varStatus="approval">
                            <c:if test="${rowApproval.nama == row.nama && rowApproval.param==1}">
                                <c:set var="app" value="1"></c:set>
                                <td width="8%">Approval</td>
                                <td style="text-align: center;" width="10%">${rowApproval.bd5}</td>
                                <td style="text-align: center;" width="10%">${rowApproval.bd4}</td>
                                <td style="text-align: center;" width="10%">${rowApproval.bd3}</td>
                                <td style="text-align: center;" width="10%">${rowApproval.bd2}</td>
                                <td style="text-align: center;" width="10%">${rowApproval.bd1}</td>
                                <td style="text-align: center;" width="10%">${rowApproval.bd0}</td>
                                <td style="text-align: center;" width="10%">${rowApproval.bd0 + rowApproval.bd1 + rowApproval.bd2 + rowApproval.bd3 + rowApproval.bd4 + rowApproval.bd5}</td>
                            </c:if>

                        </c:forEach>
                    </c:if>

                    <c:if test="${app == 0}">
                        <td width="8%">Approval</td>
                        <td style="text-align: center;" width="10%">0</td>
                        <td style="text-align: center;" width="10%">0</td>
                        <td style="text-align: center;" width="10%">0</td>
                        <td style="text-align: center;" width="10%">0</td>
                        <td style="text-align: center;" width="10%">0</td>
                        <td style="text-align: center;" width="10%">0</td>
                        <td style="text-align: center;" width="10%">0</td>
                    </c:if>
                </tr>
                </tbody>
            </c:forEach>
		</table>
</div>
<!--[if lt IE 9]>
<script type="text/javascript" src="${ctxPath}/js/html5shiv.js"></script>
<script type="text/javascript" src="${ctxPath}/js/respond.min.js"></script>
<![endif]-->
<script type="text/javascript" src="${ctxPath}/js/jquery-ui-1.10.3/jquery-1.9.1.js"></script>
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

</script>
</body>
</html>