<%-- 
    Document   : terminal
    Created on : May 20, 2010, 10:41:13 PM
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
    ArrayList resultViews = null;
    String strWebTitle= "";

    //Error message
    String strErrorTID = "";
    String strErrorStatus = "";
    String strErrorRetry = "";
    String strURLforward = "index.jsp?TaskID=7";
    String strErrorDelete = "";
    String strErrorUpdate = "";
    String strErrorAdd    = "";
    String strErrorPIN      = "";
    String strErrorActiveCode = "";

    boolean blError = false;


    DTO.DTO_JPOS_Terminal TerInfor = null;
    String strStyle = "";
    String strTerminalID = request.getParameter("TID");

    String strTID = request.getParameter("txtTID");
    String strRetry = request.getParameter("txtRetry");
    String strPin = request.getParameter("txtPIN");
    String strStatus = request.getParameter("txtTrangThai");
    String strActiveCode = request.getParameter("txtActiveCode");
    String strSearch = request.getParameter("Search");
    int iStatusCode = 0;
    int iRetry = 0;    
%>
<%
    String strTerTask = request.getParameter("TerTask");
    int iTerTask = -1;
    if (strTerTask != null)
        try{
            iTerTask = Integer.parseInt(strTerTask);
        }catch (Exception ex){
            iTerTask = -1;
        }
    switch (iTerTask)
    {
        case 1:         //show form add
            {
                strWebTitle = "Thêm thiết bị";
                %><%@include file="../views/TerminalAddNew.jsp" %><%
            }
            break;
        case 2:         //add terminal
            {
                blError = false;
                if (strTID.length() != 8)
                {
                    blError = true;
                    strErrorTID = "Mã thiết bị không hợp lệ, Chiều dài mã thiết bị là 8 kí tự";
                }
                try
                {
                    iRetry = Integer.parseInt(strRetry);
                }
                catch (Exception ex)
                {
                    strErrorRetry = "Số lần kích hoạt không hợp lệ";
                    blError = true;
                }                
                if (blError == true)
                {
                    strWebTitle = "Thêm thiết bị";
                    %><%@include file="../views/TerminalAddNew.jsp" %><%
                }
                else
                {
                    DTO_JPOS_Terminal terminal = new DTO_JPOS_Terminal();
                    terminal.setTID(strTID);
                    terminal.setStatus(iStatusCode);
                    terminal.setPIN(strPin);
                    terminal.setActiveCode(strActiveCode);
                    terminal.setRetry(iRetry);

                    if (BUS.BUS_JPOS_Terminal.checkTerminalExist(strTID, DAO.DataProvider.getConnection(this.getServletConfig())))
                    {
                        strErrorAdd = "Đã tồn thiết bị này trong CSDL, vui lòng nhập mã thiết bị khác";
                        strWebTitle = "Thêm thiết bị";
                        %><%@include file="../views/TerminalAddNew.jsp" %><%
                    }
                    else
                    {
                        boolean blResult = BUS.BUS_JPOS_Terminal.addTerminal(terminal, DAO.DataProvider.getConnection(this.getServletConfig()));
                        if (blResult == false)
                        {
                            strErrorAdd = "Tạo mới thiết bị không thành công";
                            strWebTitle = "Thêm thiết bị";
                            %><%@include file="../views/TerminalAddNew.jsp" %><%
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
                TerInfor = BUS.BUS_JPOS_Terminal.getTerminal(strTerminalID, DAO.DataProvider.getConnection(this.getServletConfig()));

                strWebTitle = "Thay đổi thông tin thiết bị";
                %><%@include file="../views/TerminalModify.jsp" %><%
            }
            break;
        case 4:         //update terminal
            {
                blError = false;
                if (strTID.length() != 8)
                {
                    blError = true;
                    strErrorTID = "Mã thiết bị không hợp lệ, Chiều dài mã thiết bị là 8 kí tự";
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
                 try
                {
                    iRetry = Integer.parseInt(strRetry);
                }
                catch (Exception ex)
                {
                    blError = true;
                }
                if (blError == true)
                {
                    TerInfor = BUS.BUS_JPOS_Terminal.getTerminal(strTID, DAO.DataProvider.getConnection(this.getServletConfig()));
                    strWebTitle = "Thay đổi thông tin thiết bị";
                    %><%@include file="../views/TerminalModify.jsp" %><%
                }
                else
                {
                    DTO_JPOS_Terminal terminal = new DTO_JPOS_Terminal();
                    terminal.setTID(strTID);
                    terminal.setStatus(iStatusCode);
                    terminal.setPIN(strPin);
                    terminal.setActiveCode(strActiveCode);
                    terminal.setRetry(iRetry);

                    boolean blResult = BUS.BUS_JPOS_Terminal.updateTerminal(terminal, DAO.DataProvider.getConnection(this.getServletConfig()));
                    if (blResult == false)
                    {
                        strErrorUpdate = "Cập nhật thông tin không thành công, vui lòng kiểm tra lại";
                        TerInfor = BUS.BUS_JPOS_Terminal.getTerminal(strTID, DAO.DataProvider.getConnection(this.getServletConfig()));
                        strWebTitle = "Thay đổi thông tin thiết bị";
                        %><%@include file="../views/TerminalModify.jsp" %><%
                    }
                    else
                    {
                        %><%@include file="../views/WaitingProcess.jsp" %><%
                    }
                }

            }
            break;
        case 5:         //delete terminal
            {
                boolean blResult = BUS.BUS_JPOS_Terminal.deleteTerminal(strTerminalID,DAO.DataProvider.getConnection(this.getServletConfig()));
                if (blResult == false)
                {
                    strErrorDelete = "Xóa thiết bị không thành công";
                    resultViews = BUS.BUS_JPOS_Terminal.getListTerminal(DAO.DataProvider.getConnection(this.getServletConfig()));
                    strWebTitle = "Danh sách thiết bị";
                    %><%@include file="../views/TerminalList.jsp" %><%
                }
                else
                {
                    %><%@include file="../views/WaitingProcess.jsp" %><%
                }
            }
            break;
        case 6:         //Show detail of terminal
            {
                TerInfor = BUS.BUS_JPOS_Terminal.getTerminal(strTerminalID, DAO.DataProvider.getConnection(this.getServletConfig()));
                strWebTitle = "Thông tin thiết bị";
                %><%@include file="../views/TerminalInformation.jsp" %><%
            }
            break;
        case 7:
            {
                resultViews = BUS.BUS_JPOS_Terminal.searchTerminal(strSearch,DAO.DataProvider.getConnection(this.getServletConfig()));
                strWebTitle = "Danh sách thiết bị";
                %><%@include file="../views/TerminalList.jsp" %><%
            }
            break;
        default:        //show list of terminal
            {
                resultViews = BUS.BUS_JPOS_Terminal.getListTerminal(DAO.DataProvider.getConnection(this.getServletConfig()));
                strWebTitle = "Danh sách thiết bị";
                %><%@include file="../views/TerminalList.jsp" %><%
            }
            break;
    }
%>

<%}%>