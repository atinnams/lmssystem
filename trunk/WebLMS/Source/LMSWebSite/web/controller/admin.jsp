<%-- 
    Document   : admin
    Created on : May 8, 2010, 11:27:44 AM
    Author     : NKLapTop
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="DTO.*,BUS.*,java.util.ArrayList" %>
<%
   request.setCharacterEncoding("UTF-8");
   response.setContentType("text/html;charset=UTF-8");
   
   if (session.getAttribute("Admin") == null) {
    response.sendRedirect("index.jsp");
   }
   else
   {
        ArrayList resultViews = null;
        String strWebTitle= "";
        String strErrorPassword = "";
        String strErrorUsername = "";
        String strErrorMail = "";
        String strErrorAddNew = "";
        String strURLforward = "index.jsp?TaskID=14";
        String strErrorDelete = "";
        String strErrorUpdate = "";
        
        boolean blError = false;
        request.setCharacterEncoding("utf8");

        String strUsername = request.getParameter("inUsername");
        String strPassword = request.getParameter("inPassword");
        String strRePassword = request.getParameter("txtPasswordRetype");
        String strLastName = request.getParameter("txtHo");
        if (strLastName != null)
            strLastName = new String(strLastName.getBytes("ISO-8859-1"),"UTF8");
        
        String strFirstName = request.getParameter("txtTen");
        if (strFirstName != null)
            strFirstName = new String(strFirstName.getBytes("ISO-8859-1"),"UTF8");
        
        String strEmail = request.getParameter("txtEmail");
        
        String strAdminTask = request.getParameter("AdminTask");
        int iAdminTask = -1;
        if (strAdminTask != null)
            try{
                iAdminTask = Integer.parseInt(strAdminTask);
            }catch (Exception ex){
                iAdminTask = -1;
            }
        switch (iAdminTask)
        {
            case 1:
                {
                    strWebTitle = "Thêm mới tài khoản";
                    %><%@include file="../views/AdminAddNew.jsp" %><%
                }
                break;
            case 2:
                {
                        DTO_JPOS_Admin NewAdmin = new DTO_JPOS_Admin();
                        NewAdmin.setUsername(strUsername);
                        NewAdmin.setFirstName(strFirstName);
                        NewAdmin.setLastName(strLastName);
                        NewAdmin.setPassword(strPassword);
                        NewAdmin.setEmail(strEmail);
                        boolean Check = true;
                        if (strPassword.equals(strRePassword) == false)
                        {
                            Check = false;
                            strErrorPassword = "Password không khớp";
                        }
                        if (BUS.BUS_JPOS_Admin.GetAdmin(strUsername, DAO.DataProvider.getConnection(this.getServletConfig())) != null)
                        {
                            Check = false;
                            strErrorUsername = "Tài khoản đã tồn tại";
                        }
                        if (Check = false)
                        {
                            strWebTitle = "Thêm mới tài khoản";
                            %><%@include file="../views/AdminAddNew.jsp" %><%
                        }
                        else
                        {
                            boolean blResult = BUS.BUS_JPOS_Admin.AddAdmin(NewAdmin, DAO.DataProvider.getConnection(this.getServletConfig()));
                            if (blResult == false)
                            {
                                strErrorAddNew = "Tạo mới tài khoản không thành công";
                                strWebTitle = "Thêm mới tài khoản";
                                %><%@include file="../views/AdminAddNew.jsp" %><%
                            }
                            else
                            {
                                %><%@include file="../views/WaitingProcess.jsp" %><%
                            }
                        }
                }
                break;
            case 3:
                {
                    strUsername = request.getParameter("User");
                    boolean blResult = BUS.BUS_JPOS_Admin.DeleteAdmin(strUsername, DAO.DataProvider.getConnection(this.getServletConfig()));
                    if (blResult == false)
                    {
                        strErrorDelete = "Xóa tài khoản không thành công";
                        resultViews = BUS.BUS_JPOS_Card.getListCard(DAO.DataProvider.getConnection(this.getServletConfig()));
                        strWebTitle = "Danh sách tài khoản";
                        %><%@include file="../views/AdminList.jsp" %><%
                    }
                    else
                    {
                        %><%@include file="../views/WaitingProcess.jsp" %><%
                    }
                }
                break;            
            case 6 :
                {
                    strUsername = request.getParameter("User");
                    strWebTitle = "Chi tiết tài khoản";
                    DTO_JPOS_Admin AdminInfor = BUS.BUS_JPOS_Admin.GetAdmin(strUsername, DAO.DataProvider.getConnection(this.getServletConfig()));
                    %><%@include file="../views/AdminInformation.jsp" %><%
                }
                break;
            case 7 :
                {
                    strUsername = request.getParameter("User");
                    strWebTitle = "Thay đổi thông tin tài khoản";
                    DTO_JPOS_Admin AdminInfor = BUS.BUS_JPOS_Admin.GetAdmin(strUsername, DAO.DataProvider.getConnection(this.getServletConfig()));
                    %><%@include file="../views/AdminModify.jsp" %><%
                }
                break;
            case 5 :
            {
                DTO_JPOS_Admin AdminInfor = BUS.BUS_JPOS_Admin.GetAdmin(strUsername, DAO.DataProvider.getConnection(this.getServletConfig()));
                
                if (AdminInfor != null)
                {
                    boolean Check = true;
                    
                    AdminInfor.setEmail(strEmail);
                    AdminInfor.setFirstName(strFirstName);
                    AdminInfor.setLastName(strLastName);
                    if (strPassword != "" && strPassword != null)
                    {                        
                        if (strPassword.equals(strRePassword) == false)
                        {
                            Check = false;
                            strErrorPassword = "Password không khớp";
                        }
                        else
                        {
                            AdminInfor.setPassword(strPassword);
                        }
                    }
                    if (Check == false)
                    {                        
                        strWebTitle = "Thay đổi thông tin tài khoản";
                        AdminInfor = BUS.BUS_JPOS_Admin.GetAdmin(strUsername, DAO.DataProvider.getConnection(this.getServletConfig()));
                        %><%@include file="../views/AdminModify.jsp" %><%
                    }
                    else
                    {
                        boolean blResult = BUS.BUS_JPOS_Admin.UpdateAdmin(AdminInfor, DAO.DataProvider.getConnection(this.getServletConfig()));
                        if (blResult == false)
                        {
                            strErrorUpdate = "Cập nhật tài khoản không thành công";
                            strWebTitle = "Thay đổi thông tin tài khoản";
                            %><%@include file="../views/AdminModify.jsp" %><%
                        }
                        else
                        {
                            %><%@include file="../views/WaitingProcess.jsp" %><%
                        }
                    }
                    
                }
                else
                {
                    resultViews = BUS.BUS_JPOS_Admin.ListAdmin(DAO.DataProvider.getConnection(this.getServletConfig()));
                    strWebTitle = "Danh sách tài khoản";
                    strErrorDelete = "Tài khoản không tồn tại";
                    %><%@include file="../views/AdminList.jsp" %><%
                }
            }
            break;
            case 11:
                {
                    String Key = request.getParameter("Search");
                    if (Key != null)
                        Key = new String(Key.getBytes("ISO-8859-1"),"UTF8");
                    
                    resultViews = BUS.BUS_JPOS_Admin.SearchAdmin(Key, DAO.DataProvider.getConnection(this.getServletConfig()));
                    strWebTitle = "Tìm kiếm";
                    %><%@include file="../views/AdminList.jsp" %><%
                }
            default:
                {
                    resultViews = BUS.BUS_JPOS_Admin.ListAdmin(DAO.DataProvider.getConnection(this.getServletConfig()));
                    strWebTitle = "Danh sách tài khoản";
                    %><%@include file="../views/AdminList.jsp" %><%
                }
                break;
        }
   }
%>
