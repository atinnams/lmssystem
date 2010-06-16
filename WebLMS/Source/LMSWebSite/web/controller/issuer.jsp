<%-- 
    Document   : issuer
    Created on : Jun 16, 2010, 11:08:03 PM
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
    String strWebTitle= "";    
    String strErrorUpdate = "";
    String strErrorDate = "";
    boolean blError = false;
    String strURLforward = "index.jsp?TaskID=15";

    request.setCharacterEncoding("utf8");

    String strName = request.getParameter("txtName");
    if (strName != null)
        strName = new String(strName.getBytes("ISO-8859-1"),"UTF8");
    String strAddress = request.getParameter("txtAddress");
    if (strAddress != null)
        strAddress = new String(strAddress.getBytes("ISO-8859-1"),"UTF8");
    
    String strDate = request.getParameter("txtDate");
    
    
    String strTask = request.getParameter("Task");
    int iTask = -1;
    if (strTask != null)
        try{
            iTask = Integer.parseInt(strTask);
        }catch (Exception ex){
            iTask = -1;
        }
    switch (iTask)
    {
        case 1:
            {
                Date dtFoundday = null;
                try
                {
                    DateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH);
                    dtFoundday = (java.util.Date)sdf.parse(strDate);
                }
                catch (Exception ex)
                {
                    strErrorDate = "Không đúng định dạng dd-MM-YYYY";
                    blError = true;
                }
                if (blError == true)
                {

                    DTO_JPOS_Issuer myIssuer = BUS.BUS_JPOS_Issuer.getIssuer(DAO.DataProvider.getConnection(this.getServletConfig()));
                    strWebTitle = "Thông tin công ty";
                    %><%@include file="../views/IssuerModify.jsp" %><%
                }
                else
                {
                    DTO_JPOS_Issuer issuer = new DTO_JPOS_Issuer();
                    issuer.setIssuerAddress(strAddress);
                    issuer.setIssuerName(strName);
                    issuer.setIssuerDateFound(dtFoundday);
                    if (BUS.BUS_JPOS_Issuer.UpdateIssuer(issuer, DAO.DataProvider.getConnection(this.getServletConfig())) == false )
                    {
                        strErrorUpdate = "Cập nhật không thành công";
                        DTO_JPOS_Issuer myIssuer = BUS.BUS_JPOS_Issuer.getIssuer(DAO.DataProvider.getConnection(this.getServletConfig()));
                        strWebTitle = "Thông tin công ty";
                        %><%@include file="../views/IssuerModify.jsp" %><%
                    }
                    else
                    {
                        %><%@include file="../views/WaitingProcess.jsp" %><%
                    }
                }
               
            }
            break;
        default :
            {
                DTO_JPOS_Issuer myIssuer = BUS.BUS_JPOS_Issuer.getIssuer(DAO.DataProvider.getConnection(this.getServletConfig()));
                strWebTitle = "Thông tin công ty";
                %><%@include file="../views/IssuerModify.jsp" %><%
            }
            break;
    }


    
    }
%>
