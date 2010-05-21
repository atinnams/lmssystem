<%-- 
    Document   : card
    Created on : May 20, 2010, 10:39:37 PM
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
%>
<%
    ArrayList resultViews = null;
    String strWebTitle= "";
%>
<%
    String strCardTask = request.getParameter("CardTask");
    int iCardTask = -1;
    if (strCardTask != null)
        try{
            iCardTask = Integer.parseInt(strCardTask);
        }catch (Exception ex){
            iCardTask = -1;
        }
    switch (iCardTask)
    {
        case 1 :            //Form to add new card
            {
            %><%@include file="../views/CardList.jsp" %><%
            }
            break;
        case 2 :            //add new card
            {
                
            }
            break ;
        case 3 :            //delete card
            break;
        case 4 :            //modify card view
            break;
        case 5 :            //Search card
            break;
        case 6 :            //View card information
            {
                %><%@include file="../views/CardInformation.jsp" %><%
            }
            break;
        case 7 :            //modify card information
            {
            }
            break;
        default ://view list card
            {
                resultViews = BUS.BUS_JPOS_Card.getListCard(DAO.DataProvider.getConnection(this.getServletConfig()));
                strWebTitle = "Quản lý thẻ";
                %><%@include file="../views/CardList.jsp" %><%
            }
            break;
    }
%>
