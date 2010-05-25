<%-- 
    Document   : customer
    Created on : May 20, 2010, 10:39:46 PM
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
    //Error message
    String strErrorCardID = "";
    String strErrorExpireDay = "";
    String strErrorActiveCode = "";
    String strErrorAddNew = "";
    String strURLforward = "index.jsp?TaskID=6";
    String strErrorDelete = "";
    String strErrorUpdate = "";
    boolean blError = false;


    DTO_JPOS_Customer custInfor = null;
    String strStyle = "";
    String strCustID = request.getParameter("CustID");

    String strCustomerID = request.getParameter("txtCustomerID");
    String strLastName = request.getParameter("txtLastName");
    String strFirstName = request.getParameter("txtFirstName");
    String strGender = request.getParameter("txtGender");
    String strCurrentPoint = request.getParameter("txtCurrentPoint");
    String strAddress = request.getParameter("txtAddress");
    String strEmail = request.getParameter("txtEmail");
    String strBirthDay = request.getParameter("txtBirthday");
    String strDateJoin = request.getParameter("txtDateJoin");
    String strFavorite = request.getParameter("txtFavorite");
%>
<%
    String strCustTask = request.getParameter("CustTask");
    int iCustTask = -1;
    if (strCustTask != null)
        try{
            iCustTask = Integer.parseInt(strCustTask);
        }catch (Exception ex){
            iCustTask = -1;
        }
    switch (iCustTask)
    {
        case 1 :                //show form add customer
            {
                strWebTitle = "Thêm mới khách hàng";
                int iGenCustID = BUS.BUS_JPOS_Customer.GenerateCustomerID(DAO.DataProvider.getConnection(this.getServletConfig()));
                %><%@include file="../views/CustomerAddNew.jsp"  %><%
            }
            break;
        case 2 :                //Add new customer
            break;
        case 3 :                //delete customer
            break;
        case 4 :                //show form modify
            break;
        case 5 :                //Update customer
            break;
        case 6 :                //Show detail of customer
             {
                strWebTitle = "Thông tin khách hàng";
                if (strCustID != null)
                {
                    int iCustID = 0;
                    try{
                        iCustID = Integer.parseInt(strCustID);
                    }catch (Exception ex){}
                    custInfor = BUS_JPOS_Customer.GetCustomerInfor(iCustID, DAO.DataProvider.getConnection(this.getServletConfig()));

                    %><%@include file="../views/CustomerDetail.jsp"  %><%
                }
            }
            break;
        default :               //show list
            {
                strWebTitle = "Quản lý khách hàng";
                resultViews = BUS.BUS_JPOS_Customer.GetCustomerList(DAO.DataProvider.getConnection(this.getServletConfig()));
                %><%@include file="../views/CustomerList.jsp" %><%
            }
            break;
    }
%>