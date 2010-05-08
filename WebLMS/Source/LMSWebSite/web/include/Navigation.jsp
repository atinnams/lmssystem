<%-- 
    Document   : Navigation
    Created on : May 5, 2010, 8:29:14 PM
    Author     : NKLapTop
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="navigation">

    <h2>Khách hàng</h2>
    <ul>
        <li><a href="index.jsp?TaskID=2">Thông tin khách hàng</a></li>
        <li><a href="index.jsp?TaskID=3">Truy vấn số dư</a></li>
        <li><a href="index.jsp?TaskID=4">Thông tin giao dịch</a></li>
        <li><a href="index.jsp?TaskID=5">convallis</a></li>
    </ul>
    <% if (session.getAttribute("Admin") != null) {
    %>
        <h2>Quản lý</h2>
        <ul>
            <li><a href="index.jsp?TaskID=6">Quản lý khách hàng</a></li>
            <li><a href="index.jsp?TaskID=7">Quản lý thiết bị</a></li>
            <li><a href="index.jsp?TaskID=8">Quản lý đại lý</a></li>
            <li><a href="index.jsp?TaskID=9">Quản lý thẻ</a></li>
        </ul>

        <h2>Thống kê</h2>
        <ul>
            <li><a href="index.jsp">Thống kê khách hàng</a></li>
            <li><a href="index.jsp">Thống kê giao dịch</a></li>
            <li><a href="index.jsp">tincidunt</a></li>
            <li><a href="index.jsp">consequat molestie</a></li>
        </ul>
    <%
    }
    %>
</div>
