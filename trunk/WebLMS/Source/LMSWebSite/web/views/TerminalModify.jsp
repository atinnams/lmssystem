<%-- 
    Document   : TerminalModify
    Created on : Jun 4, 2010, 12:03:34 PM
    Author     : NKLapTop
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
    ArrayList arrList = BUS.BUS_JPOS_Status.GetStatus("JPOS_Terminal", DAO.DataProvider.getConnection(this.getServletConfig()));
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>LMS System index page</title>

        <script type="text/javascript">
            <!--
            function newImage(arg) {
                if (document.images) {
                    rslt = new Image();
                    rslt.src = arg;
                    return rslt;
                }
            }

            function changeImages() {
                if (document.images && (preloadFlag == true)) {
                    for (var i=0; i<changeImages.arguments.length; i+=2) {
                        document[changeImages.arguments[i]].src = changeImages.arguments[i+1];
                    }
                }
            }

            var preloadFlag = false;
            function preloadImages() {
                if (document.images) {
                    home_page_over = newImage("images/home-page-over.jpg");
                    about_us_over = newImage("images/about-us-over.jpg");
                    preloadFlag = true;
                }
            }
            // -->
        </script>   <!-- End Preload Script -->
        <link type="text/css" rel="stylesheet" href="css/style.css" />
    </head>

    <body leftmargin="0" topmargin="0" onLoad="preloadImages();" style="background-color: rgb(255, 255, 255);" marginheight="0" marginwidth="0">
        <center>
            <table class="frame" id="Table_01" border="0" cellpadding="0" cellspacing="0" height="681" width="776">
                <tbody align="left">
                    <tr>
                        <td colspan="9"> <img src="images/main.jpg" alt="" height="100" width="775"></td>
                        <td> <img src="images/spacer.gif" alt="" height="100" width="1"></td>
                    </tr>

                    <tr>
                        <td colspan="4" rowspan="7"> <img src="images/main-03.jpg" alt="" height="200" width="491"></td>
                        <td colspan="5"> <img src="images/main-04.jpg" alt="" height="12" width="284"></td>
                        <td> <img src="images/spacer.gif" alt="" height="12" width="1"></td>
                    </tr>

                    <tr>
                        <td colspan="5">
                            <a href="#" onMouseOver="window.status='home page'; changeImages('home_page', 'images/homepage-over.jpg'); return true;" onMouseOut="window.status=''; changeImages('home_page', 'images/homepage.jpg'); return true;" onMouseDown="changeImages('home_page', 'images/homepage-over.jpg'); return true;" onMouseUp="changeImages('home_page', 'images/homepage-over.jpg'); return true;">
                                <img name="home_page" src="images/homepage.jpg" alt="home page" border="0" height="31" width="284"></a></td>
                        <td> <img src="images/spacer.gif" alt="" height="31" width="1"></td>
                    </tr>

                    <tr>
                        <td colspan="5"> <a href="#" onMouseOver="window.status='Register'; changeImages('register', 'images/register-over.jpg'); return true;" onMouseOut="window.status=''; changeImages('register', 'images/register.jpg'); return true;" onMouseDown="changeImages('register', 'images/register-over.jpg'); return true;" onMouseUp="changeImages('service', 'images/register-over.jpg'); return true;">
                                <img name="register" src="images/register.jpg" alt="register" border="0" height="31" width="284"></a></td>
                        <td> <img src="images/spacer.gif" alt="" height="31" width="1"></td>
                    </tr>

                    <tr>
                        <td colspan="5"> <a href="#" onMouseOver="window.status='Register'; changeImages('about_us', 'images/aboutus-over.jpg'); return true;" onMouseOut="window.status=''; changeImages('about_us', 'images/aboutus.jpg'); return true;" onMouseDown="changeImages('about_us', 'images/aboutus-over-over.jpg'); return true;" onMouseUp="changeImages('about_us', 'images/aboutus-over-over.jpg'); return true;">
                                <img name="about_us" src="images/aboutus.jpg" alt="about us" border="0" height="31" width="284"></a></td>
                        <td> <img src="images/spacer.gif" alt="" height="31" width="1"></td>
                    </tr>

                    <tr>
                        <td colspan="5" rowspan="3" background="images/menu-blank.jpg">
                            <% if (session.getAttribute("Admin") == null) {
                            %>
                            <form action="index.jsp" method="post">
                                <table>
                                    <tr>
                                        <td ><span class="LoginText">Username:</span></td>
                                        <td><input type="text" value="" name="txtUsername" maxlength="30" size="20" /></td>
                                        <td align="left"><input type="Submit" value="Log In" name="Login" style="font-size:12px;"/></td>
                                    </tr>

                                    <tr>
                                        <td><span class="LoginText">Password:</span></td>
                                        <td><input type="password" value="" name="txtPassword" maxlength="30" size="20" /><input type="hidden" name="TaskID" value="0"/></td>
                                    </tr>

                                </table>
                            </form>
                            <% } else {
                                 DTO_JPOS_Admin result = (DTO_JPOS_Admin) session.getAttribute("Admin");
                            %>
                            <table width="280px">
                                <tr>
                                    <td colspan="3"><span style="font-size:13px;font-family:'Lucida Sans Unicode',sans-serif;color:white" >&raquo;&nbsp;Tài khoản : <%=result.getUsername().toString()%></span></td>
                                </tr>
                                <tr>
                                    <td colspan="3"><span style="font-size:13px;font-family:'Lucida Sans Unicode',sans-serif;color:white">&raquo;&nbsp;Chào mừng  : <%=result.getFirstName()%></span></td>
                                </tr>
                                <tr>
                                    <td colspan="2"><span style="font-size:13px;font-family:'Lucida Sans Unicode',sans-serif;color:white">&raquo;&nbsp;Ngày đăng nhập gần nhất : <%=result.getLastLogin().toString()%></span> </td>
                                </tr>

                                <tr><td colspan="3" align="right" style="font-size:14px;"><a href="index.jsp?TaskID=1" style="color:Red;">Log out</a></td></tr>
                            </table>
                            <%
                                        }
                            %>
                        </td>
                        <td> <img src="images/spacer.gif" alt="" height="31" width="1"></td>
                    </tr>

                    <tr>
                        <td> <img src="images/spacer.gif" alt="" height="31" width="1"></td>
                    </tr>

                    <tr>
                        <td> <img src="images/spacer.gif" alt="" height="31" width="1"></td>
                    </tr>
                    <tr>
                        <td colspan="9" style="padding-top:4px">
                            <div class="path">
                                <a href="index.jsp">Trang chủ</a> &#8250; <a href="index.jsp?TaskID=7">Quản lý thiết bị</a> &#8250; <a href="index.jsp?<%=request.getQueryString()%>"><%=strWebTitle%></a>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="9" class="main">
                            <div >
                                <div class="content">
                                    <h1 style="text-transform:uppercase"><%=strWebTitle%></h1>
                                    <form method="post" action="index.jsp">
                                    <div style="height:30px"></div>
                                    <span style="color:red"><%=strErrorAdd %> </span>
                                    <input type="hidden" name="TaskID" value="7" />
                                    <input type="hidden" name="TerTask" value="4" />
                                    <table>
                                        <tr>
                                            <th align="left"> Mã thiết bị : </th>
                                            <td><input type="text" name="txtTID" value="<%=TerInfor.getTID() %>" readonly></td>
                                            <td><span style="color:red"><%=strErrorTID %></span></td>
                                        </tr>
                                        <tr>
                                            <th align="left"  width="100px"> Mã Pin : </th>
                                            <td><input type="text" name="txtPIN" value="<%=TerInfor.getPIN() %>"></td>
                                            <td><span style="color:red"><%=strErrorPIN %></span></td>
                                        </tr>
                                        <tr>
                                            <th align="left"  width="100px"> Mã kích hoạt : </th>
                                            <td><input type="text" name="txtActiveCode" value="<%=TerInfor.getActiveCode() %>"></td>
                                            <td><span style="color:red"<%=strErrorActiveCode %></span></td>
                                        </tr>
                                        <tr>
                                            <th align="left"> Số lần kích hoạt : </th>
                                            <td><input type="text" name="txtRetry" value="<%=TerInfor.getRetry() %>"> </td>
                                            <td><span style="color:red"><%=strErrorRetry %></span></td>
                                        </tr>
                                        <tr>
                                            <th align="left">Trạng thái : </th>
                                            <td>
                                                <select name="txtTrangThai">
                                             <%
                                                for (int i=0;i < arrList.size(); i++ )
                                                {
                                                    DTO.DTO_JPOS_Status status = (DTO.DTO_JPOS_Status)arrList.get(i);
                                                    if (status.getStatusCode() == TerInfor.getStatus() )
                                                    {
                                                        %><option value="<%=status.getStatusCode() %>" selected><%=status.getStatusName() %></option> <%
                                                    }
                                                    else
                                                    {
                                                        %><option value="<%=status.getStatusCode() %>"><%=status.getStatusName() %></option> <%
                                                    }
                                                }
                                            %>
                                                </select>
                                            </td>
                                            <td><span style="color:red"><%=strErrorStatus %></span></td>
                                        </tr>
                                        <tr>
                                            <td align="left">Mã đại lý trực thuộc : </td>
                                             <%
                                                if (TerInfor.getMID() != null)
                                                {
                                                    %>
                                                    <td><a href="index.jsp?TaskID=8&TerTask=6&MID=<%=TerInfor.getMID()%>" title="Xem chi tiết đại lý"><%=TerInfor.getMID() %></a></td>
                                                    <td>
                                                        <a href="index.jsp?TaskID=7&TerTask=8&TID=<%=TerInfor.getTID()%>" title="Chọn đại lý trực thuộc khác">(Chọn đại lý khác)</a>
                                                        &nbsp;&nbsp;
                                                        <a href="index.jsp?TaskID=7&TerTask=9&TID=<%=TerInfor.getTID()%>">(Hủy chọn đại lý)</a>
                                                    </td>
                                                    <%

                                                }
                                                else
                                                {
                                                    %>
                                                    <td><a href="index.jsp?TaskID=7&TerTask=8&TID=<%=TerInfor.getTID()%>" title="Chọn đại lý trực thuộc">Chưa trực thuộc</a></td>
                                                    <td></td>
                                                    <%
                                                }
                                            %>
                                            
                                        </tr>
                                        
                                        
                                        <tr>
                                            <td></td>
                                            <td align="right"><input type="submit" value="Cập nhật" style="width:80px"></td>
                                            <td><a href="index.jsp?TaskID=7"><input type="button" value="Hủy bỏ" style="width:80px"></a></td>
                                        </tr>
                                    </table>

                                    </form>
                                    <div style="height:400px"></div>
                                </div>

                                <%@include file="../include/Navigation.jsp" %>

                                <div class="clearer">&nbsp;</div>

                            </div>
                        </td>
                    </tr>

                    <tr>
                        <td colspan="9" style="padding-left: 50px; line-height: 16px; text-align:center; color:#000" align="centre" background="images/b_links.jpg" height="80" valign="middle" width="775">
                            <center><img src="images/art_footer.gif"></center>
                            Copyright ©2010 HCMUS. All right reserver.<br>
                            Khoa Công Nghệ Thông Tin - ĐH KHTN TP.HCM.<br>
                        </td>
                        <td> <img src="images/spacer.gif" alt="" height="80" width="1"></td>
                    </tr>

                    <tr>
                        <td> <img src="images/spacer.gif" alt="" height="1" width="42"></td>
                        <td> <img src="images/spacer.gif" alt="" height="1" width="219"></td>
                        <td> <img src="images/spacer.gif" alt="" height="1" width="43"></td>
                        <td> <img src="images/spacer.gif" alt="" height="1" width="187"></td>
                        <td> <img src="images/spacer.gif" alt="" height="1" width="26"></td>
                        <td> <img src="images/spacer.gif" alt="" height="1" width="41"></td>
                        <td> <img src="images/spacer.gif" alt="" height="1" width="22"></td>
                        <td> <img src="images/spacer.gif" alt="" height="1" width="176"></td>
                        <td> <img src="images/spacer.gif" alt="" height="1" width="19"></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
            <!-- End ImageReady Slices -->
        </center>

    </body>
</html



