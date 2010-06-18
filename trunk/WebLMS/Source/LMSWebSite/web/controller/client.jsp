<%-- 
    Document   : client.jsp
    Created on : May 8, 2010, 11:18:50 AM
    Author     : NKLapTop
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="DTO.*,BUS.*,java.util.ArrayList" %>
<%
    String strCustomerID = request.getParameter("txtCustomerID");
    String strLastName = request.getParameter("txtLastName");
    if (strLastName != null)
        strLastName = new String(strLastName.getBytes("ISO-8859-1"),"UTF8");
    
    String strFirstName = request.getParameter("txtFirstName");
    if (strFirstName != null)
        strFirstName = new String(strFirstName.getBytes("ISO-8859-1"),"UTF8");

    String strGender = request.getParameter("txtGender");
    String strCurrentPoint = request.getParameter("txtCurrentPoint");
    String strAddress = request.getParameter("txtAddress");
    if (strAddress != null)
        strAddress = new String(strAddress.getBytes("ISO-8859-1"),"UTF8");
    
    String strEmail = request.getParameter("txtEmail");
    String strBirthDay = request.getParameter("txtBirthday");
    String strDateJoin = request.getParameter("txtDateJoin");
    String strFavorite = request.getParameter("txtFavorite");
    if (strFavorite != null)
        strFavorite = new String(strFavorite.getBytes("ISO-8859-1"),"UTF8");
    
    String strDetail = request.getParameter("Detail");
    DTO_JPOS_Customer custInfor = null;
    ArrayList resultViews = null ;
    String strStyle = "";

    String strError = "";
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
            if (session.getAttribute("Cust") != null)
            {
                custInfor = (DTO_JPOS_Customer)session.getAttribute("Cust");
                %><%@include file="../views/CustomerDetail.jsp"  %><%
            }
            else
            {
                if (strDetail != null)
                {
                    int iCustID = 0;
                    try{
                        iCustID = Integer.parseInt(strDetail);
                    }catch (Exception ex){}
                    custInfor = BUS_JPOS_Customer.GetCustomerInfor(iCustID, DAO.DataProvider.getConnection(this.getServletConfig()));

                    %><%@include file="../views/CustomerDetail.jsp"  %><%
                }
                else
                {
                    if (strCustomerID != null && strAddress != null && strBirthDay != null && strCurrentPoint != null && strDateJoin != null && strEmail != null && strFavorite != null && strFirstName != null && strGender != null && strLastName != null)
                    {
                        int iCustomerID = -1;
                        int iCurrentPoint = -1;
                        boolean  blGender = false;
                        try
                        {
                            iCustomerID = Integer.parseInt(strCustomerID);
                        }catch (Exception ex){}
                        try
                        {
                            iCurrentPoint = Integer.parseInt(strCurrentPoint);
                        }catch (Exception ex){}
                        try
                        {
                            int iGetGender = Integer.parseInt(strGender);
                            if (iGetGender == 1) blGender = true;
                        }catch (Exception ex){}
                        resultViews = BUS_JPOS_Customer.Search_Customer(iCustomerID, strFirstName, strLastName, strAddress, strEmail, strDateJoin, strBirthDay, blGender, strFavorite, iCurrentPoint, DAO.DataProvider.getConnection(this.getServletConfig()));
                    }else {
                    }
                    %><%@include file="../views/CustomerInformation.jsp"  %><%
                }
            
            }
            break;
        case 4:
            strWebTitle = "Thông tin giao dịch";
            if (session.getAttribute("Cust") != null)
            {
                int iCustID = 0;
                custInfor = (DTO_JPOS_Customer)session.getAttribute("Cust");
                iCustID = custInfor.getJPOS_CustomerID();
                resultViews = BUS_JPOS_Customer.Transaction_Detail(iCustID, DAO.DataProvider.getConnection(this.getServletConfig()));
                %><%@include file="../views/TransactionDetail.jsp"  %><%
            }
            else
            {
                if (strDetail != null)
                {
                    int iCustID = 0;
                    try{
                        iCustID = Integer.parseInt(strDetail);
                    }catch (Exception ex){}
                    custInfor = BUS_JPOS_Customer.GetCustomerInfor(iCustID, DAO.DataProvider.getConnection(this.getServletConfig()));
                    if (custInfor == null)
                    {
                        strError = "Không tìm thấy khách hàng";
                        %><%@include file="../views/ViewTransaction.jsp" %><%
                    }
                    else
                    {
                        resultViews = BUS_JPOS_Customer.Transaction_Detail(iCustID, DAO.DataProvider.getConnection(this.getServletConfig()));
                        %><%@include file="../views/TransactionDetail.jsp"  %><%
                    }
                }
                else {
                    if (strCustomerID != null && strAddress != null && strBirthDay != null && strCurrentPoint != null && strDateJoin != null && strEmail != null && strFavorite != null && strFirstName != null && strGender != null && strLastName != null)
                    {
                    int iCustomerID = -1;
                    int iCurrentPoint = -1;
                    boolean  blGender = false;
                    try
                    {
                        iCustomerID = Integer.parseInt(strCustomerID);
                    }catch (Exception ex){}
                    try
                    {
                        iCurrentPoint = Integer.parseInt(strCurrentPoint);
                    }catch (Exception ex){}
                    try
                    {
                        int iGetGender = Integer.parseInt(strGender);
                        if (iGetGender == 1) blGender = true;
                    }catch (Exception ex){}
                    resultViews = BUS_JPOS_Customer.Search_Customer(iCustomerID, strFirstName, strLastName, strAddress, strEmail, strDateJoin, strBirthDay, blGender, strFavorite, iCurrentPoint, DAO.DataProvider.getConnection(this.getServletConfig()));
                    }
                    %><%@include file="../views/TransactionInformation.jsp"  %><%
                }
            }
            break;
        case 16:
            {
                strWebTitle = "Thông tin thẻ khách hàng";
                if (session.getAttribute("Cust") == null)
                {

                    if (strDetail!=null)
                    {
                        DTO_JPOS_Card card = BUS.BUS_JPOS_Card.GetCard(strDetail, DAO.DataProvider.getConnection(this.getServletConfig()));
                        if (card == null)
                        {
                            strError = "Không tìm thấy thông tin thẻ";
                            %><%@include file="../views/ViewCard.jsp" %><%
                        }
                        else
                        {
                            %><%@include file="../views/CardDetail.jsp" %><%
                        }

                    }
                    else
                    {
                        %><%@include file="../views/ViewCard.jsp" %><%
                    }
                }
                else
                {
                    custInfor = (DTO_JPOS_Customer)session.getAttribute("Cust");
                    resultViews = BUS.BUS_JPOS_Card.GetCardBelongToCustomer(custInfor.getJPOS_CustomerID(),DAO.DataProvider.getConnection(this.getServletConfig()));
                    strWebTitle = "Danh sách thẻ";
                    %><%@include file="../views/ViewCardBelong.jsp" %><%
                }
            }
            break;
       case 17:
           {
               if (session.getAttribute("Cust") != null)
                {
                    int iCustID = 0;
                    custInfor = (DTO_JPOS_Customer)session.getAttribute("Cust");
                    iCustID = custInfor.getJPOS_CustomerID();
                    resultViews = BUS_JPOS_Customer.Transaction_Detail(iCustID, DAO.DataProvider.getConnection(this.getServletConfig()));
                    strWebTitle = "Thông tin giao dịch";
                    %><%@include file="../views/TransactionDetail.jsp"  %><%
                }
               else{

                %><%@include file="../views/ViewTransaction.jsp" %><%
                }
           }
           break;
    }
%>

