<%-- 
    Document   : merchant
    Created on : May 20, 2010, 10:40:11 PM
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
    String strErrorMID = "";    
    String strErrorStatus = "";
    String strURLforward = "index.jsp?TaskID=8";
    String strErrorDelete = "";
    String strErrorUpdate = "";
    String strErrorAdd    = "";
    boolean blError = false;


    DTO.DTO_JPOS_Merchant MerInfor = null;
    String strStyle = "";
    String strMerchantID = request.getParameter("MID");

    String strMID = request.getParameter("txtMID");
    String strMerchantName = request.getParameter("txtTenDaiLy");
    String strAddress = request.getParameter("txtDiaChi");    
    String strStatus = request.getParameter("txtTrangThai");
    
    int iStatusCode = 0;
%>
<%
    String strMerTask = request.getParameter("MerTask");
    int iMerTask = -1;
    if (strMerTask != null)
        try{
            iMerTask = Integer.parseInt(strMerTask);
        }catch (Exception ex){
            iMerTask = -1;
        }
    switch (iMerTask)
    {
        case 1:         //show form add
            {
                strWebTitle = "Thêm đại lý";
                %><%@include file="../views/MerchantAddNew.jsp" %><%
            }
            break;
        case 2:         //add merchant
            {
                blError = false;
                if (strMID.length() != 15)
                {
                    blError = true;
                    strErrorMID = "Mã đại lý không hợp lệ, Chiều dài mã đại lý là 15 kí tự";
                }
                if (blError == true)
                {
                    strWebTitle = "Thêm đại lý";
                    %><%@include file="../views/MerchantAddNew.jsp" %><%
                }
                else
                {
                    DTO_JPOS_Merchant merchant = new DTO_JPOS_Merchant();
                    merchant.setjPOS_MID(strMID);
                    merchant.setJPOS_MerchantName(strMerchantName);
                    merchant.setAddress(strAddress);

                    if (BUS.BUS_JPOS_Merchant.checkMerchantExist(strMID, DAO.DataProvider.getConnection(this.getServletConfig())))
                    {
                        strErrorAdd = "Đã tồn đại lý này trong CSDL, vui lòng nhập mã đại lý khác";
                        strWebTitle = "Thêm đại lý";
                        %><%@include file="../views/MerchantAddNew.jsp" %><%
                    }
                    else
                    {
                        boolean blResult = BUS.BUS_JPOS_Merchant.addMerchant(merchant, DAO.DataProvider.getConnection(this.getServletConfig()));
                        if (blResult == false)
                        {
                            strErrorAdd = "Tạo mới đại lý không thành công";
                            strWebTitle = "Thêm đại lý";
                            %><%@include file="../views/MerchantAddNew.jsp" %><%
                        }
                        else
                        {
                            %><%@include file="../views/WaitingProcess.jsp" %><%
                        }
                     }
                }
            }
            break;
        case 3:         //show form edit
            {
                MerInfor = BUS.BUS_JPOS_Merchant.getMerchant(strMerchantID, DAO.DataProvider.getConnection(this.getServletConfig()));                
                strWebTitle = "Thay đổi thông tin đại lý";
                %><%@include file="../views/MerchantModify.jsp" %><%
            }
            break;
        case 4:         //update merchant
            {
                blError = false;
                if (strMID.length() != 15)
                {
                    blError = true;
                    strErrorMID = "Mã đại lý không hợp lệ, Chiều dài mã đại lý là 15 kí tự";
                }
                try
                {
                    iStatusCode = Integer.parseInt(strStatus);
                }
                catch (Exception ex)
                {
                    blError = true;
                    strErrorStatus = "Mã trạng thái không phù hợp";
                }
                if (blError == true)
                {
                    MerInfor = BUS.BUS_JPOS_Merchant.getMerchant(strMID, DAO.DataProvider.getConnection(this.getServletConfig()));
                    strWebTitle = "Thay đổi thông tin đại lý";
                    %><%@include file="../views/MerchantModify.jsp" %><%
                }                
                else
                {
                    DTO_JPOS_Merchant merchant = new DTO_JPOS_Merchant();
                    merchant.setjPOS_MID(strMID);
                    merchant.setJPOS_MerchantName(strMerchantName);
                    merchant.setAddress(strAddress);
                    merchant.setStatusCode(iStatusCode);
                    boolean blResult = BUS.BUS_JPOS_Merchant.updateMerchant(merchant, DAO.DataProvider.getConnection(this.getServletConfig()));
                    if (blResult == false)
                    {
                        strErrorUpdate = "Cập nhật thông tin không thành công, vui lòng kiểm tra lại";
                        MerInfor = BUS.BUS_JPOS_Merchant.getMerchant(strMID, DAO.DataProvider.getConnection(this.getServletConfig()));
                        strWebTitle = "Thay đổi thông tin đại lý";
                        %><%@include file="../views/MerchantModify.jsp" %><%
                    }
                    else
                    {
                        %><%@include file="../views/WaitingProcess.jsp" %><%
                    }
                }
                
            }
            break;
        case 5:         //delete merchant
            {
                boolean blResult = BUS.BUS_JPOS_Merchant.deleteMerchant(strMerchantID,DAO.DataProvider.getConnection(this.getServletConfig()));
                if (blResult == false)
                {
                    strErrorDelete = "Xóa đại lý không thành công";
                    resultViews = BUS.BUS_JPOS_Merchant.listMerchant(DAO.DataProvider.getConnection(this.getServletConfig()));
                    strWebTitle = "Quản lý đại lý";
                    %><%@include file="../views/MerchantList.jsp" %><%
                }
                else
                {
                    %><%@include file="../views/WaitingProcess.jsp" %><%
                }
            }
            break;
        case 6:         //Show detail of merchant
            {
                MerInfor = BUS.BUS_JPOS_Merchant.getMerchant(strMerchantID, DAO.DataProvider.getConnection(this.getServletConfig()));                
                strWebTitle = "Thông tin đại lý";
                %><%@include file="../views/MerchantInformation.jsp" %><%
            }
            break;
        default:        //show list of merchant
            {
                resultViews = BUS.BUS_JPOS_Merchant.listMerchant(DAO.DataProvider.getConnection(this.getServletConfig()));
                strWebTitle = "Quản lý đại lý";
                %><%@include file="../views/MerchantList.jsp" %><%
            }
            break;
    }
%>