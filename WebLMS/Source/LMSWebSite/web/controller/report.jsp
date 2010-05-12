<%-- 
    Document   : report
    Created on : May 8, 2010, 11:35:30 AM
    Author     : NKLapTop
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="DTO.*,BUS.*,java.util.ArrayList" %>
<%
   if (session.getAttribute("Admin") == null) {
        response.sendRedirect("index.jsp");
   }
   else
   {
%>
<%
    String strTask = request.getParameter("TaskID");
    String strWebTitle = "";
    int iTaskID = Integer.parseInt(strTask);
    ArrayList resultViews = null;

%>
<%    
    switch (iTaskID){
        case 10:
            {
                strWebTitle = "Thống kê khách hàng";
                resultViews = BUS.BUS_JPOS_Customer.GetCustomerList(DAO.DataProvider.getConnection(this.getServletConfig()));
                %><%@include file="../views/ReportCustomer.jsp" %><%
            }
            break;
        case 11:
            {
                strWebTitle = "Thống kê giao dịch";
                resultViews = BUS.BUS_JPOS_Log.getReportLog(DAO.DataProvider.getConnection(this.getServletConfig()));
                %><%@include file="../views/ReportTransaction.jsp" %><%
            }
            break;
        case 12:
            {
                strWebTitle = "Thống kê thẻ";
                resultViews = BUS.BUS_JPOS_Card.getListCard(DAO.DataProvider.getConnection(this.getServletConfig()));
                %><%@include file="../views/ReportCard.jsp" %><%
            }
            break;
        case 13:
            {
                strWebTitle = "Thống kê thiết bị";
                resultViews = BUS.BUS_JPOS_Terminal.getListTerminal(DAO.DataProvider.getConnection(this.getServletConfig()));
                %><%@include file="../views/ReportTerminal.jsp" %><%
            }
            break;
    }    
%>
<%
   }
%>
