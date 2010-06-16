<%-- 
    Document   : Navigation
    Created on : May 5, 2010, 8:29:14 PM
    Author     : NKLapTop
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="navigation">

    <h2><img src="images/cust.jpg" alt="">Khách hàng</h2>
    <ul>
        <li><a href="index.jsp?TaskID=2">Thông tin khách hàng</a></li>        
        <li><a href="index.jsp?TaskID=4">Thông tin giao dịch</a></li>        
    </ul>
    <% if (session.getAttribute("Admin") != null) {
    %>
        <h2><img src="images/manage.jpg" alt="" />Quản lý</h2>
        <ul>
            <li><a href="index.jsp?TaskID=6">Quản lý khách hàng</a></li>
            <li><a href="index.jsp?TaskID=7">Quản lý thiết bị</a></li>
            <li><a href="index.jsp?TaskID=8">Quản lý đại lý</a></li>
            <li><a href="index.jsp?TaskID=9">Quản lý thẻ</a></li>
        </ul>

        <h2><img src="images/report.jpg" alt="">Thống kê</h2>
        <ul>
            <li><a href="index.jsp?TaskID=10">Thống kê khách hàng</a></li>
            <li><a href="index.jsp?TaskID=11">Thống kê giao dịch</a></li>
            <li><a href="index.jsp?TaskID=12">Thống kê thẻ</a></li>
            <li><a href="index.jsp?TaskID=13">Thống kê thiết bị</a></li>
        </ul>
        <h2><img src="images/admin.jpg" alt=""/>Quản trị hệ thống</h2>
        <ul>
            <li><a href="index.jsp?TaskID=14">Quản lý tài khoản</a></li>
            <li><a href="index.jsp?TaskID=15">Cấu hình hệ thống</a></li>
        </ul>
    <%
    }
    %>
</div>
