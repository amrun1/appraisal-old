<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.permata.appraisal2.model.PersonLevel" %>
<%@ page import="com.permata.appraisal2.common.AppConstans" %>
<c:set var="king" value="<%= PersonLevel.KING %>"/>
<c:set var="role911" value="<%= PersonLevel.ROLE_911 %>"/>
<c:set var="personMarketing" value="<%= PersonLevel.MARKETING %>"/>
<c:set var="personAppAdmin" value="<%= PersonLevel.APPRAISAL_ADMIN %>"/>
<c:set var="personAppValuer" value="<%= PersonLevel.APPRAISAL_VALUER %>"/>
<c:set var="personAppSurveyor" value="<%= PersonLevel.APPRAISAL_SURVEYOR %>"/>
<c:set var="personAppApproval" value="<%= PersonLevel.APPRAISAL_APPROVAL %>"/>
<c:set var="admin" value="<%= PersonLevel.ADMIN %>"/>
<c:set var="king" value="<%= PersonLevel.KING %>"/>
<c:set var="rwa" value="<%= PersonLevel.VAULT_RWA %>"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Expires" content="-1"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="js/jquery-ui-1.10.3/themes/base/jquery.ui.all.css">
    <!-- <link rel="stylesheet" href="js/jquery-ui-1.10.3/themes/flick/jquery-ui.css" type="text/css"/>
    <link rel="stylesheet" href="js/jquery-ui-1.10.3/themes/flick/jquery.ui.thems.css" type="text/css"/> -->
    <style type="text/css">
        body, .ui-widget {
            font-size: 11px;
            font-family: "Trebuchet MS", "Arial", "Helvetica", "Verdana", "sans-serif";
        }

        #accordion-resizer {
            width: 150px;
            /*margin-right: 10px;
            padding: 5px 10px 10px 10px;*/
        }

        .ui-accordion .ui-accordion-content {
            padding: 5px;
        }

        #accordion ul {
            list-style: square;
            padding-left: 18px;
            margin: 5px;
        }

        #accordion ul li {
            padding-left: 0px;
        }
    </style>
</head>
<body style="background-color: #FFFFFF">
<div id="accordion-resizer">
    <p>

    </p>
</div>
<div style="border: 1px dotted #ddd; padding: 8px;margin-bottom: 10px;">
    <h3 style="
    margin-top: 0px;
    border-bottom: 1px solid red;">User Information</h3>
    <div>
        <b>User :</b> ${person.userId}<br>
        <b>Name :</b> ${person.name}<br>
        <%--<p>Group :<br>${person.groupName}</p>--%>
        <c:if test="${roleActive.level == personMarketing}"><b>Cabang :</b> ${roleActive.branchName}<br></c:if>
        <c:if test="${roleActive.level == personAppAdmin}"><b>Appraisal Cabang :</b> ${roleActive.appBranchName}
            <br></c:if>
        <b>Role Menu :</b>
        <c:if test="${fn:length(person.roles) > 1}">
            <select id="cmb-person-level">
                <c:forEach items="${person.roles}" var="row">
                    <c:if test="${row.id == roleActive.id}">
                        <option value="${row.id}" selected>${row.level}</option>
                    </c:if>
                    <c:if test="${row.id != roleActive.id}">
                        <option value="${row.id}">${row.level}</option>
                    </c:if>
                </c:forEach>
            </select>
        </c:if>
        <c:if test="${fn:length(person.roles) == 1}">${roleActive.level}</c:if>
        <br>
        <b style="color: ${failExist ? 'red' : 'black'}">${dformat}</b>
    </div>
</div>
<div id="accordion">
    <c:if test="${roleActive.level != king}">

        <c:if test="${roleActive.level != role911 && roleActive.level != admin}">

        <h3>Transaksi</h3>
        <div>
            <ul>
                <c:if test="${roleActive.level == personMarketing}">
                    <li><a target="mainFrame" href="data-umum/list">Task List [Marketing]</a></li>
                    <li><a target="mainFrame" href="data-umum/list?task=<%= AppConstans.TaskPosition.USER_TL %>">Order
                        List [Marketing]</a></li>
                </c:if>
                <c:if test="${roleActive.level == personAppAdmin}">
                    <li><a target="mainFrame" href="app-admin/list">Task List [AppAdmin]</a></li>
                    <li><a target="mainFrame" href="app-admin/list?task=<%= AppConstans.TaskPosition.APP_ADMIN %>">Order
                        List[AppAdmin]</a></li>
                    <li><a target="mainFrame" href="app-admin/rejectPlus-list">PLUS Reject</a></li>
                    <li><a target="mainFrame" href="app-admin/rejectCarm-list">CARM Reject</a></li>
                </c:if>
                <c:if test="${roleActive.level == personAppValuer}">
                    <li><a target="mainFrame" href="app-valuer/list">Task List [Valuer]</a></li>
                    <li><a target="mainFrame" href="app-valuer/list?task=<%= AppConstans.TaskPosition.APP_VALUER %>">Order
                        List [Valuer]</a></li>
                    <li><a href="monitoring/param-pda-survey" target="mainFrame">Monitoring PDA Survey </a></li>
                </c:if>
                <c:if test="${roleActive.level == personAppSurveyor}">
                    <li><a target="mainFrame" href="app-surveyor/list">Task List [Surveyor]</a></li>
                    <li><a target="mainFrame" href="app-surveyor/list?task=<%= AppConstans.TaskPosition.APP_SURVEYOR%>">Order
                        List [Surveyor]</a></li>
                    <li><a href="monitoring/param-pda-survey" target="mainFrame">Monitoring PDA Survey </a></li>
                </c:if>
                <c:if test="${roleActive.level == personAppApproval}">
                    <li><a target="mainFrame" href="app-approval/list">Task List [Approval]</a></li>
                    <li><a target="mainFrame" href="app-approval/list?task=<%= AppConstans.TaskPosition.APP_APPROVAL%>">Order
                        List [Approval]</a></li>
                    <li><a target="mainFrame" href="app-approval/re-sendPlus-list">PLUS Re-Send</a></li>
                    <li><a target="mainFrame" href="app-approval/re-sendCarm-list">CARM Re-Send</a></li>


                </c:if>
                <c:if test="${roleActive.level == rwa}">
                    <li><a target="mainFrame" href="rwa/re-send-list">RWA Re-Send</a></li>
                    <li><a target="mainFrame" href="rwa/listResendByCol">Send Close List Colleteral</a></li>
                    <li><a target="mainFrame" href="rwa/send-manual-list">Send Manual List</a></li>
                    <li><a target="mainFrame" href="monitoring/report-rwa">Report RWA</a></li>
                </c:if>
                <li><a target="mainFrame" href="inquiry/list">Inquiry</a></li>
            </ul>
        </div>
        </c:if>
        <c:if test="${roleActive.level == admin}">
            <h3>Master Reference</h3>
            <div>
                <ul>
                    <%--<li><a target="mainFrame" href="master/jenis-dokumen-lpa/list?jenis=1">Jenis Dokumen LPA Appraisal</a></li>--%>
                    <li><a target="mainFrame" href="master/jenis-dokumen-lpa/list?jenis=2">Jenis Dokumen LPA Aktiva</a></li>
                    <li><a target="mainFrame" href="master/nilai-tanah/list">Nilai Tanah Desktop</a></li>

                    <li style="display: none;"><a target="mainFrame" href="master/appraisal-divisi/list">Appraisal Divisi</a></li>
                    <li><a target="mainFrame" href="master/jenis-appraisal/list">Jenis Appraisal</a></li>
                    <li><a target="mainFrame" href="master/status-nasabah/list">Status Nasabah</a></li>
                    <li><a target="mainFrame" href="master/status-jaminan/list">Status Jaminan</a></li>
                    <li><a target="mainFrame" href="master/jenis-penilaian/list">Jenis Penilaian</a></li>
                    <%--<li>Jenis Kategori</li>--%>
                    <li><a target="mainFrame" href="master/asset/list">Jenis Aktiva</a></li>
                    <%--<li>Jenis Dokumen</li>--%>
                    <li><a target="mainFrame" href="master/zip-code/list">Kode POS</a></li>
                    <%--<li>Komplek Perumahan</li>--%>
                    <li><a target="mainFrame" href="master/Indikasi/list">Indikasi Formula</a></li>
                    <li><a target="mainFrame" href="master/systemparam/automatic-logout">Automatic Logout Time</a></li>
                    <li><a target="mainFrame" href="master/systemparam/addCot">Setting Cut Of Time</a></li>
                    <li><a target="mainFrame" href="master/valuer-external/list">Valuer Eksternal</a></li>
                    <li><a target="mainFrame" href="master/holiday/list">Hari Libur</a></li>
                    <li><a target="mainFrame" href="master/order-pending/list">Order Pending</a></li>
                    <li><a target="mainFrame" href="master/closeData/list">Order Close</a></li>
                    <li><a target="mainFrame" href="master/dropData/list">Order Drop</a></li>
                    <li><a target="mainFrame" href="master/parameter/list">Parameter</a></li>

                </ul>
            </div>
            <h3>Upload Data</h3>
            <div>
                <ul>
                    <li><a target="mainFrame" href="master/nilai-tanah/upload">Nilai Tanah Desktop</a></li>
                </ul>
            </div>
        </c:if>
        <c:if test="${roleActive.level != role911}">
            <h3>Monitoring</h3>
            <div>
                <ul>
                    <li><a target="mainFrame" href="monitoring/report-order">Report Order</a></li>
                    <li><a target="mainFrame" href="monitoring/report-sla">Report SLA</a></li>
                    <li><a target="mainFrame" href="monitoring/report-productivity">Report Productivity</a></li>
                    <li><a target="mainFrame" href="monitoring/report-pending">Report Pending Job</a></li>
                </ul>
            </div>
        </c:if>

        <c:if test="${roleActive.level == role911}">
            <h3>System Setting</h3>
            <div>
                <ul>
                    <li><a target="mainFrame" href="master/systemparam/form-carm-auth">Setting CARM interface</a></li>
                </ul>
            </div>
            <h3>Monitoring</h3>
            <div>
                <ul>
                    <li><a target="mainFrame" href="monitoring/report-order">Report Order</a></li>
                    <li><a target="mainFrame" href="monitoring/report-sla">Report SLA</a></li>
                    <li><a target="mainFrame" href="monitoring/report-productivity">Report Productivity</a></li>
                    <li><a target="mainFrame" href="monitoring/report-pending">Report Pending Job</a></li>
                    <li><a target="mainFrame" href="master/systemparam/list">System Parameter</a></li>
                    <li><a target="mainFrame" href="inquiry/list">Inquiry</a></li>
                </ul>
            </div>
        </c:if>


        <c:if test="${roleActive.level != personMarketing}">
            <%--<c:if test="${roleActive.level != role911}">--%>
                <h3>Screen Real Time</h3>
                <div>
                    <ul>
                        <li><a href="monitoring/pending-jobs?task=1" target="_blank">Pending Jobs</a></li>
                        <li><a href="monitoring/pending-jobs?task=2" target="_blank">Pending Jobs Customer</a></li>
                        <li><a href="monitoring/param-intra-day" target="mainFrame">Intra Day</a></li>
                        <li><a href="monitoring/param-status-jaminan" target="mainFrame">Status Jaminan</a></li>
                        <li><a href="monitoring/param-type-object" target="mainFrame">Type Of Object</a></li>
                    </ul>
                </div>
            <%--</c:if>--%>
        </c:if>
    </c:if>
    <c:if test="${roleActive.level == king}">
        <h3>Administrator</h3>
        <div>
            <ul>
                <li><a target="mainFrame" href="admin/user/list">User</a></li>
                <li><a target="mainFrame" href="admin/user/listPersonAct">Person Activity</a></li>
                <li><a target="mainFrame" href="admin/user/listDeleted">List Person Deleted</a></li>
                <li><a target="mainFrame" href="monitoring/report-logs">Person Activity Report</a></li>
                <li><a target="mainFrame" href="admin/appraisal-cabang/list">Cabang Appraisal</a></li>
                <li><a target="mainFrame" href="admin/cabang-marketing/list">Cabang Marketing</a></li>
            </ul>
        </div>
    </c:if>
</div>
<script src="js/jquery-ui-1.10.3/jquery-1.9.1.js"></script>
<script src="js/jquery-ui-1.10.3/ui/jquery.ui.core.js"></script>
<script src="js/jquery-ui-1.10.3/ui/jquery.ui.widget.js"></script>
<script src="js/jquery-ui-1.10.3/ui/jquery.ui.mouse.js"></script>
<script src="js/jquery-ui-1.10.3/ui/jquery.ui.resizable.js"></script>
<script src="js/jquery-ui-1.10.3/ui/jquery.ui.accordion.js"></script>
<script>



    $(function () {
        $("#accordion").accordion();

        $('#cmb-person-level').change(function () {
            parent.location = "change-role?id=" + $(this).val();

        });
    });
</script>
</body>
</html>