<%-- 
    Document   : customer
    Created on : May 20, 2010, 10:39:46 PM
    Author     : NKLapTop
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="DTO.*,BUS.*,java.util.ArrayList,java.text.*" %>
<%
   if (session.getAttribute("Admin") == null) {
    response.sendRedirect("index.jsp");
   }
%>
<%
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    
    ArrayList resultViews = null;
    String strWebTitle= "";
    //Error message
    String strErrorFirstName = "";
    String strErrorLastName = "";
    String strErrorCurrentPoint = "";
    String strErrorAddress = "";
    String strErrorEmail = "";
    String strErrorBirthDay = "";
    String strErrorFavorite = "";
    String strErrorPoint = "";
    String strErrorUsername = "";
    String strErrorPassword = "";
    
    String strURLforward = "index.jsp?TaskID=6";
    String strErrorDelete = "";
    String strErrorUpdate = "";
    String strErrorAdd    = "";
    boolean blError = false;


    DTO_JPOS_Customer custInfor = null;
    String strStyle = "";
    String strCustID = request.getParameter("CustID");

    String strCustomerID = request.getParameter("txtMaKhachHang");
    String strLastName = request.getParameter("txtHo");
    if (strLastName != null)
        strLastName = new String(strLastName.getBytes("ISO-8859-1"),"UTF8");

    String strFirstName = request.getParameter("txtTen");
    if (strFirstName != null)
        strFirstName = new String(strFirstName.getBytes("ISO-8859-1"),"UTF8");

    String strGender = request.getParameter("txtGioiTinh");
    String strCurrentPoint = request.getParameter("txtSoDiem");
    
    String strAddress = request.getParameter("txtDiaChi");
    if (strAddress != null)
        strAddress = new String(strAddress.getBytes("ISO-8859-1"),"UTF8");

    String strEmail = request.getParameter("txtEmail");
    String strBirthDay = request.getParameter("txtNgaySinh");
    String strDateJoin = request.getParameter("txtDateJoin");
    String strFavorite = request.getParameter("txtSoThich");
    
    if (strFavorite != null)
        strFavorite = new String(strFavorite.getBytes("ISO-8859-1"),"UTF8");

    String strStatus = request.getParameter("txtTrangThai");


    String CustUsername = request.getParameter("txtUsername");
    String CustPassword = request.getParameter("txtPassword");
    String RePassword = request.getParameter("txtRePassword");
    
    int iCustomerID = 0;
    int iPoint = 0;
    boolean blGender = false;
    int iStatusCode = 0;
    
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
            {
                blError = false;
                Date dtBirthday = null;
                if (BUS.BUS_JPOS_Customer.CheckUsernameExist(CustUsername, DAO.DataProvider.getConnection(this.getServletConfig())))
                {
                    blError = true;
                    strErrorUsername = "Đã tồn tại username này trong hệ thống";
                }
                if (CustPassword.equals(RePassword) == false)
                {
                    blError = true;
                    strErrorPassword = "Mật khẩu không giống nhau";
                }
                if (strFirstName == "" || strFirstName == null)
                {
                    blError = true;
                    strErrorFirstName = "Vui lòng nhập tên";
                }
                if (strLastName == "" || strLastName == null)
                {
                    blError = true;
                    strErrorLastName = "Vui lòng nhập họ";
                }
                try
                {
                    DateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH);
                    dtBirthday = (java.util.Date)sdf.parse(strBirthDay);
                }
                catch (Exception ex)
                {
                    strErrorBirthDay = "Ngày sinh không đúng định dạng dd-MM-YYYY";
                    blError = true;
                }
                if (strAddress == "" || strAddress == null)
                {
                    blError = true;
                    strErrorAddress = "Vui lòng nhập địa chỉ";
                }
                if (strEmail == "" || strEmail == null)
                {
                    blError = true;
                    strErrorEmail = "Vui lòng nhập Email";
                }
                if (BUS.BUS_JPOS_Customer.CheckCustomerEmailExist(strEmail, DAO.DataProvider.getConnection(this.getServletConfig())))
                {
                    blError = true;
                    strErrorEmail = "Email đã tồn tại, vui lòng nhập email khác";
                }
                try
                {
                    iPoint = Integer.parseInt(strCurrentPoint);
                }                
                catch(Exception ex)
                {
                    blError = true;
                }
                try
                {
                    int iGender = Integer.parseInt(strGender);
                    if (iGender == 1)
                        blGender = true;
                    else
                        blGender = false;
                }
                catch(Exception ex)
                {
                    blError = true;
                }

                if (blError == true )
                {
                    strWebTitle = "Thêm mới khách hàng";
                    int iGenCustID = BUS.BUS_JPOS_Customer.GenerateCustomerID(DAO.DataProvider.getConnection(this.getServletConfig()));
                    %><%@include file="../views/CustomerAddNew.jsp"  %><%
                }
                else
                {
                    DTO_JPOS_Customer cust = new DTO_JPOS_Customer();
                    cust.setUsername(CustUsername);
                    cust.setPassword(CustPassword);
                    cust.setJPOS_CustomerID(iCustomerID);
                    cust.setFirstName(strFirstName);
                    cust.setLastName(strLastName);
                    cust.setEmail(strEmail);
                    cust.setAddress(strAddress);
                    cust.setBirthDay(dtBirthday);
                    cust.setJPOS_CurrentPoint(iPoint);
                    cust.setGender(blGender);
                    cust.setFavorite(strFavorite);
                    if (BUS.BUS_JPOS_Customer.AddCustomer(cust, DAO.DataProvider.getConnection(this.getServletConfig())) == false)
                    {
                        strWebTitle = "Thêm mới khách hàng";
                        strErrorAdd = "Thêm mới không thành công, vui lòng thử lại";
                        int iGenCustID = BUS.BUS_JPOS_Customer.GenerateCustomerID(DAO.DataProvider.getConnection(this.getServletConfig()));
                        %><%@include file="../views/CustomerAddNew.jsp"  %><%
                    }
                    else
                    {
                        %><%@include file="../views/WaitingProcess.jsp" %><%
                    }
                }
                

            }
            break;
        case 3 :                //delete customer
            strCustomerID = request.getParameter("CustID");
            try{
                    iCustomerID = Integer.parseInt(strCustomerID);
                    if (BUS.BUS_JPOS_Customer.DeleteCustomer(iCustomerID, DAO.DataProvider.getConnection(this.getServletConfig())))
                    {
                         %><%@include file="../views/WaitingProcess.jsp" %><%
                    }
                    else
                    {
                        strWebTitle = "Danh sách khách hàng";
                        strErrorDelete = "Xóa khách hàng không thành công, vui lòng kiểm tra lại";
                        resultViews = BUS.BUS_JPOS_Customer.GetCustomerList(DAO.DataProvider.getConnection(this.getServletConfig()));
                        %><%@include file="../views/CustomerList.jsp" %><%
                    }
                }
            catch(Exception ex)
               {
                    strWebTitle = "Danh sách khách hàng";
                    strErrorDelete = "Mã khách hàng không hợp lệ";
                    resultViews = BUS.BUS_JPOS_Customer.GetCustomerList(DAO.DataProvider.getConnection(this.getServletConfig()));
                    %><%@include file="../views/CustomerList.jsp" %><%
                }
            break;
        case 4 :                //show form modify
            {
                strWebTitle = "Thay đổi thông tin khách hàng";
                if (strCustID != null)
                {
                    int iCustID = 0;
                    try{
                        iCustID = Integer.parseInt(strCustID);
                    }catch (Exception ex){}
                    custInfor = BUS_JPOS_Customer.GetCustomerInfor(iCustID, DAO.DataProvider.getConnection(this.getServletConfig()));
                    
                    %><%@include file="../views/CustomerModify.jsp"  %><%
                }
            }
            break;
        case 5 :                //Update customer
            blError = false;
                Date dtBirthday = null;
                if (!CustPassword.equals(RePassword))
                {
                    blError = true;
                    strErrorPassword = "Mật khẩu không giống nhau";
                }
                if (strFirstName == "" || strFirstName == null)
                {
                    blError = true;
                    strErrorFirstName = "Vui lòng nhập tên";
                }
                if (strLastName == "" || strLastName == null)
                {
                    blError = true;
                    strErrorLastName = "Vui lòng nhập họ";
                }
                try
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH);
                    dtBirthday = sdf.parse(strBirthDay);
                }
                catch (Exception ex)
                {
                    strErrorBirthDay = "Ngày sinh không đúng định dạng dd-MM-YYYY";
                    blError = true;
                }
                if (strAddress == "" || strAddress == null)
                {
                    blError = true;
                    strErrorAddress = "Vui lòng nhập địa chỉ";
                }
                if (strEmail == "" || strEmail == null)
                {
                    blError = true;
                    strErrorEmail = "Vui lòng nhập Email";
                }
                try
                {
                    iCustomerID = Integer.parseInt(strCustomerID);
                }
                catch(Exception ex)
                {
                    blError = true;
                }
                try
                {
                    iPoint = Integer.parseInt(strCurrentPoint);
                }
                catch(Exception ex)
                {
                    strErrorCurrentPoint = "Số điểm không hợp lệ";
                    blError = true;
                }
                try
                {
                    int iGender = Integer.parseInt(strGender);
                    if (iGender == 1)
                        blGender = true;
                    else
                        blGender = false;
                }
                catch(Exception ex)
                {
                    blError = true;
                }
                try
                {
                    iStatusCode = Integer.parseInt(strStatus);
                }
                catch (Exception ex)
                {
                    blError = true;
                }
                if (blError == true )
                {
                    strWebTitle = "Thay đổi thông tin khách hàng";
                    custInfor = BUS_JPOS_Customer.GetCustomerInfor(iCustomerID, DAO.DataProvider.getConnection(this.getServletConfig()));
                    %><%@include file="../views/CustomerModify.jsp"  %><%
                }
                else
                {
                    DTO_JPOS_Customer cust = BUS.BUS_JPOS_Customer.GetCustomerInfor(iCustomerID, DAO.DataProvider.getConnection(this.getServletConfig()));

                    if (CustPassword != "" || CustPassword != null)
                    {
                        cust.setPassword(CustPassword);
                    }
                    cust.setFirstName(strFirstName);
                    cust.setLastName(strLastName);
                    cust.setEmail(strEmail);
                    cust.setAddress(strAddress);
                    cust.setBirthDay(dtBirthday);
                    cust.setJPOS_CurrentPoint(iPoint);
                    cust.setGender(blGender);
                    cust.setFavorite(strFavorite);
                    cust.setStatusCode(iStatusCode);
                    
                    if (BUS.BUS_JPOS_Customer.UpdateCustomer(cust, DAO.DataProvider.getConnection(this.getServletConfig())) == false)
                    {
                        strWebTitle = "Thay đổi thông tin khách hàng";
                        custInfor = BUS_JPOS_Customer.GetCustomerInfor(iCustomerID, DAO.DataProvider.getConnection(this.getServletConfig()));
                        %><%@include file="../views/CustomerModify.jsp"  %><%
                    }
                    else
                    {
                        %><%@include file="../views/WaitingProcess.jsp" %><%
                    }
                }
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
        case 7 :
            {
                String strKey = request.getParameter("Search");
                strWebTitle = "Danh sách khách hàng";
                resultViews = BUS.BUS_JPOS_Customer.Search_Customer(strKey,DAO.DataProvider.getConnection(this.getServletConfig()));
                %><%@include file="../views/CustomerList.jsp" %><%
            }
            break;
        default :               //show list
            {
                strWebTitle = "Danh sách khách hàng";
                resultViews = BUS.BUS_JPOS_Customer.GetCustomerList(DAO.DataProvider.getConnection(this.getServletConfig()));
                %><%@include file="../views/CustomerList.jsp" %><%
            }
            break;
    }
%>