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
    String strErrorCardID = "";
    String strErrorExpireDay = "";
    String strErrorActiveCode = "";
    String strErrorAddNew = "";
    String strURLforward = "index.jsp?TaskID=9";
    String strErrorDelete = "";
    String strErrorUpdate = "";
    boolean blError = false;
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
                strWebTitle = "Thêm thẻ mới";
                %><%@include file="../views/CardAddNew.jsp" %><%
            }
            break;
        case 2 :            //add new card
            {
                String strCardID = request.getParameter("txtMaThe");
                String strMyExpireDay = request.getParameter("txtNgayHetHan");
                String strActiveCode = request.getParameter("txtMaKichHoat");
                java.sql.Date dateExpireDay = null;
                if (strCardID.length() != 16 )
                {
                    strErrorCardID = "Mã thẻ không đúng quy định 16 kí tự";
                    blError = true;
                    
                }                                
                try
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date d = sdf.parse(strMyExpireDay);
                    dateExpireDay = new java.sql.Date(d.getYear(),d.getMonth(),d.getDay());
                }
                catch (Exception ex)
                {
                    strErrorExpireDay = "Ngày hết hạn không đúng định dạng dd/MM/YYYY";
                    blError = true;
                }
                if (blError == true)
                {
                    strWebTitle = "Thêm thẻ mới";
                    %><%@include file="../views/CardAddNew.jsp" %><%
                }
                else
                {
                    DTO_JPOS_Card card = new DTO_JPOS_Card();
                    card.setActiveCode(strActiveCode);
                    card.setJPOS_CardId(strCardID);
                    card.setJPOS_ExpireDay(dateExpireDay);
                    if (BUS.BUS_JPOS_Card.checkCard(strCardID, DAO.DataProvider.getConnection(this.getServletConfig())) == 1)
                    {
                        strErrorAddNew = "Đã tồn tại thẻ này trong CSDL, vui lòng nhập mã thẻ khác";
                        strWebTitle = "Thêm thẻ mới";
                        %><%@include file="../views/CardAddNew.jsp" %><%
                    }
                    else
                    {
                        boolean blResult = BUS.BUS_JPOS_Card.NewCard(card, DAO.DataProvider.getConnection(this.getServletConfig()));
                        if (blResult == false)
                        {
                            strErrorAddNew = "Tạo mới thẻ không thành công";
                            strWebTitle = "Thêm thẻ mới";
                            %><%@include file="../views/CardAddNew.jsp" %><%
                        }
                        else
                        {
                            %><%@include file="../views/WaitingProcess.jsp" %><%
                        }
                     }
                }
            }                            
            break ;
        case 3 :            //delete card
            {
                String strCardID = request.getParameter("CardID");
                boolean blResult = BUS.BUS_JPOS_Card.DeleteCard(strCardID, DAO.DataProvider.getConnection(this.getServletConfig()));
                if (blResult == false)
                {
                    strErrorDelete = "Xóa thẻ không thành công";
                    resultViews = BUS.BUS_JPOS_Card.getListCard(DAO.DataProvider.getConnection(this.getServletConfig()));
                    strWebTitle = "Quản lý thẻ";
                    %><%@include file="../views/CardList.jsp" %><%
                }
                else
                {
                    %><%@include file="../views/WaitingProcess.jsp" %><%
                }
            }
            break;
        case 4 :            //modify card
            {
                String strCardID = request.getParameter("txtMaThe");
                String strMyExpireDay = request.getParameter("txtNgayHetHan");
                String strActiveCode = request.getParameter("txtMaKichHoat");
                String strStatus = request.getParameter("txtTrangThai");
                int iStatus = Integer.parseInt(strStatus);
                java.sql.Date dateExpireDay = null;
                try
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date d = sdf.parse(strMyExpireDay);
                    dateExpireDay = new java.sql.Date(d.getYear(),d.getMonth(),d.getDay());
                }
                catch (Exception ex)
                {
                    strErrorExpireDay = "Ngày hết hạn không đúng định dạng dd/MM/YYYY";
                    blError = true;
                }
                if (blError == true)
                {
                    strWebTitle = "Thay đổi thông tin thẻ";
                    DTO_JPOS_Card card = BUS.BUS_JPOS_Card.GetCard(strCardID, DAO.DataProvider.getConnection(this.getServletConfig()));
                    %><%@include file="../views/CardModify.jsp" %><%
                }
                else
                {
                    DTO_JPOS_Card card = new DTO_JPOS_Card();
                    card.setActiveCode(strActiveCode);
                    card.setJPOS_CardId(strCardID);
                    card.setJPOS_ExpireDay(dateExpireDay);
                    card.setStatusCode(iStatus);
                    boolean blResult = BUS.BUS_JPOS_Card.UpdateCard(card, DAO.DataProvider.getConnection(this.getServletConfig()));
                    if (blResult == false)
                    {
                        strErrorUpdate = "Thay đổi thông tin không thành công";
                        strWebTitle = "Thay đổi thông tin thẻ";
                        card = BUS.BUS_JPOS_Card.GetCard(strCardID, DAO.DataProvider.getConnection(this.getServletConfig()));
                        %><%@include file="../views/CardModify.jsp" %><%
                    }
                    else
                    {
                        %><%@include file="../views/WaitingProcess.jsp" %><%
                    }
                }
            }
            break;
        case 5 :            //Search card
            break;
        case 6 :            //View card information
            {
                String strCardID = request.getParameter("CardID");
                strWebTitle = "Chi tiết thẻ";
                DTO_JPOS_Card card = BUS.BUS_JPOS_Card.GetCard(strCardID, DAO.DataProvider.getConnection(this.getServletConfig()));
                %><%@include file="../views/CardDetail.jsp" %><%
            }
            break;
        case 7 :            //modify card information view
            {
                String strCardID = request.getParameter("CardID");
                strWebTitle = "Thay đổi thông tin thẻ";
                DTO_JPOS_Card card = BUS.BUS_JPOS_Card.GetCard(strCardID, DAO.DataProvider.getConnection(this.getServletConfig()));
                %><%@include file="../views/CardModify.jsp" %><%
            }
            break;
        case 8 :            //Card distribute view
            {
                resultViews = null;
                strWebTitle = "Cấp thẻ";
                resultViews = BUS.BUS_JPOS_Customer.GetCustomerList(DAO.DataProvider.getConnection(this.getServletConfig()));
                %><%@include file="../views/CardDistribute.jsp" %><%
            }
            break;
        case 9 :            //card distribute
            {
                String strCardID = request.getParameter("CardID");
                String strCustomerID = request.getParameter("CustID");
                int iCustID = Integer.parseInt(strCustomerID);
                boolean blResult = BUS.BUS_JPOS_Card.AssignCard(strCardID, iCustID, DAO.DataProvider.getConnection(this.getServletConfig()));
                if (blResult == false)
                {
                    strErrorUpdate = "Cấp thẻ không thành công";
                    strWebTitle = "Cấp thẻ";
                    resultViews = BUS.BUS_JPOS_Customer.GetCustomerList(DAO.DataProvider.getConnection(this.getServletConfig()));
                    %><%@include file="../views/CardDistribute.jsp" %><%
                }
                else
                {
                    %><%@include file="../views/WaitingProcess.jsp" %><%
                }
                
            }
            break;
        case 10:        //stop distribute card
            {
                String strCardID = request.getParameter("CardID");                                
                boolean blResult = BUS.BUS_JPOS_Card.StopAssignCard(strCardID, DAO.DataProvider.getConnection(this.getServletConfig()));
                if (blResult == false)
                {
                   strErrorUpdate = "Ngưng cấp thẻ không thành công";
                   strWebTitle = "Thay đổi thông tin thẻ";
                   DTO_JPOS_Card card = BUS.BUS_JPOS_Card.GetCard(strCardID, DAO.DataProvider.getConnection(this.getServletConfig()));
                   %><%@include file="../views/CardModify.jsp" %><%
                }
                else
                {
                    %><%@include file="../views/WaitingProcess.jsp" %><%
                }
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
