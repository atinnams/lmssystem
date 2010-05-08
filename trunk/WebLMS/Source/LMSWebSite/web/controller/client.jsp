<%-- 
    Document   : client.jsp
    Created on : May 8, 2010, 11:18:50 AM
    Author     : NKLapTop
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="DTO.*,BUS.*" %>
<%
    String strCustomerID = request.getParameter("txtCustomerID");
    String strStyle;
    if (strCustomerID != null && strCustomerID != ""){
        strStyle = "";
    }else {
        strStyle = "style =\"Height : 400px\"";
    }
%>
<%
    int iTaskID = -1;
    String strWebTitle = "";
    String strTask = request.getParameter("TaskID");
    if (strTask != null)
        try{
            iTaskID = Integer.parseInt(strTask);
        }catch (Exception ex){
            iTaskID = -1;
        }
    switch (iTaskID)
    {
        case 2:
            strWebTitle = "Thông tin khách hàng";
            %><%@include file="../views/ClientInformationPage.jsp"  %><%
            break;
        case 4:
            strWebTitle = "Thông tin giao dịch";
            %><%@include file="../views/TransactionInformation.jsp"  %><%
            break;
    }
%>

