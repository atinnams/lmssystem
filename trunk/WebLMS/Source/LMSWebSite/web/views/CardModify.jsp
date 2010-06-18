<%--
    Document   : CustomerDetail
    Created on : May 11, 2010, 12:48:19 AM
    Author     : NKLapTop
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.*,java.text.*,DTO.*" %>
 <%
    DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH);
    java.util.Date date = new java.util.Date();
    String strExpireDay = "";
    if (card.getJPOS_ExpireDay() != null)
        strExpireDay = dateFormat.format(card.getJPOS_ExpireDay());    
    ArrayList arrList = BUS.BUS_JPOS_Status.GetStatus("JPOS_Card", DAO.DataProvider.getConnection(this.getServletConfig()));
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>LMS System index page</title>
        <script language="javascript" type="text/javascript" src="js/datetimepicker.js" ></script>
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
                            <a href="index.jsp" onMouseOver="window.status='home page'; changeImages('home_page', 'images/homepage-over.jpg'); return true;" onMouseOut="window.status=''; changeImages('home_page', 'images/homepage.jpg'); return true;" onMouseDown="changeImages('home_page', 'images/homepage-over.jpg'); return true;" onMouseUp="changeImages('home_page', 'images/homepage-over.jpg'); return true;">
                                <img name="home_page" src="images/homepage.jpg" alt="home page" border="0" height="31" width="284"></a></td>
                        <td> <img src="images/spacer.gif" alt="" height="31" width="1"></td>
                    </tr>

                    <tr>
                        <td colspan="5"> <a href="http://www.fit.hcmus.edu.vn" onMouseOver="window.status='Register'; changeImages('register', 'images/register-over.jpg'); return true;" onMouseOut="window.status=''; changeImages('register', 'images/register.jpg'); return true;" onMouseDown="changeImages('register', 'images/register-over.jpg'); return true;" onMouseUp="changeImages('service', 'images/register-over.jpg'); return true;">
                                <img name="register" src="images/register.jpg" alt="register" border="0" height="31" width="284"></a></td>
                        <td> <img src="images/spacer.gif" alt="" height="31" width="1"></td>
                    </tr>

                    <tr>
                        <td colspan="5"> <a href="aboutus.jsp" onMouseOver="window.status='Register'; changeImages('about_us', 'images/aboutus-over.jpg'); return true;" onMouseOut="window.status=''; changeImages('about_us', 'images/aboutus.jpg'); return true;" onMouseDown="changeImages('about_us', 'images/aboutus-over-over.jpg'); return true;" onMouseUp="changeImages('about_us', 'images/aboutus-over-over.jpg'); return true;">
                                <img name="about_us" src="images/aboutus.jpg" alt="about us" border="0" height="31" width="284"></a></td>
                        <td> <img src="images/spacer.gif" alt="" height="31" width="1"></td>
                    </tr>

                    <tr>
                        <td colspan="5" rowspan="3" background="images/menu-blank.jpg">
                            <% if (session.getAttribute("Admin") == null && session.getAttribute("Cust")==null) {
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
								 DTO_JPOS_Customer Customer = (DTO_JPOS_Customer) session.getAttribute("Cust");
                                String Username ;
                                String FirstName;
                                String DateLogin;
                                if (result != null)
                                {
                                    Username = result.getUsername();
                                    FirstName = result.getFirstName();
                                    DateLogin = result.getLastLogin().toString();
                                }
                                else
                                {
                                    Username = Customer.getUsername();
                                    FirstName = Customer.getFirstName();
                                    DateLogin = (new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH)).format(new Date()).toString();
                                }
                            %>
                            <table width="280px">
                                <tr>
                                    <td colspan="3"><span style="font-size:13px;font-family:'Lucida Sans Unicode',sans-serif;color:white" >&raquo;&nbsp;Tài khoản : <%=Username %></span></td>
                                </tr>
                                <tr>
                                    <td colspan="3"><span style="font-size:13px;font-family:'Lucida Sans Unicode',sans-serif;color:white">&raquo;&nbsp;Chào mừng  : <%=FirstName %></span></td>
                                </tr>
                                <tr>
                                    <td colspan="2"><span style="font-size:13px;font-family:'Lucida Sans Unicode',sans-serif;color:white">&raquo;&nbsp;Ngày đăng nhập gần nhất : <%=DateLogin %></span> </td>
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
                                <a href="index.jsp">Trang chủ</a> &#8250; <a href="index.jsp?TaskID=9">Quản lý thẻ</a> &#8250; <a href="index.jsp?TaskID=9&CardTask=7&CardID=<% if (request.getParameter("CardID")!= null){ out.print(request.getParameter("CardID"));} else {out.print(strCardID);}  %>"><%=strWebTitle%></a>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="9" class="main">
                            <div >
                                <div class="content">
                                    <h1 style="text-transform:uppercase"><%=strWebTitle%></h1>
                                    <div style="height:30px"></div>
                                    <form action="index.jsp" method="post">
                                    <span style="color:red"><%=strErrorUpdate %> </span>
                                    <input type="hidden" name="TaskID" value="9" />
                                    <input type="hidden" name="CardTask" value="4" />
                                    <table>
                                        <tr>
                                            <th align="left"> Mã Thẻ : </th>
                                            <td><input type="text" value="<%=card.getJPOS_CardId()%>" name="txtMaThe" readonly></td>
 
                                        </tr>
                                        <tr>
                                            <th align="left"  width="100px"> Ngày hết hạn : </th>
                                            <td><input type="text" value="<%=strExpireDay %>" name="txtNgayHetHan"  readonly id="PickUpTime"><a href="javascript:NewCssCal('PickUpTime','ddmmmyyyy')"><img src="images/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a></td></td>
                                            <td><span style="color:red"><%=strErrorExpireDay %></span></td>
                                        </tr>
                                        <tr>
                                            <th align="left"> Mã kích hoạt </th>
                                            <td><input type="text" value="<%=card.getActiveCode() %>" name="txtMaKichHoat"></td>
                                            <td><span style="color:red"><%=strErrorActiveCode %></span></td>
                                        </tr>
                                        <tr>
                                            <th align="left"> Số tiền trong thẻ </th>
                                            <td><input type="text" value="<%=card.getMonetary() %>" name="txtSoTien"></td>
                                            <td><span style="color:red"><%=strErrorMonetary %></span></td>
                                        </tr>
                                        <tr>
                                            <th align="left"> Trạng thái thẻ </th>
                                            <td>
                                                <select name="txtTrangThai">
                                                <%
                                                for (int i=0;i < arrList.size(); i++ )
                                                {
                                                    DTO_JPOS_Status status = (DTO_JPOS_Status)arrList.get(i);
                                                    if (status.getStatusCode() == card.getStatusCode() )
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
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <th align="left" width="170px"> Mã khách hàng sở hữu </th>
                                            <td>
                                                <%
                                                     if (card.getCustomerOwnerID() == 0)
                                                     {
                                                        %>
                                                        Chưa có sỡ hữu &nbsp;&nbsp;<a href="index.jsp?TaskID=9&CardTask=8&CardID=<%=card.getJPOS_CardId() %>" title="Thẻ chưa cấp phát, click để cấp thẻ">(Cấp thẻ)</a></td>                                                        
                                                        <%
                                                     }
                                                     else
                                                     {
                                                        %>
                                                        <%=card.getCustomerOwnerID() %>&nbsp;&nbsp; <a href="index.jsp?TaskID=9&CardTask=8&CardID=<%=card.getJPOS_CardId() %>" title="Cấp lại thẻ">(Cấp lại)</a>&nbsp;&nbsp;<a href="index.jsp?TaskID=9&CardTask=10&CardID=<%=card.getJPOS_CardId() %>" title="Ngưng cấp thẻ cho khách hàng">(Ngưng cấp)</a></td>
                                                        <%
                                                        
                                                     }

                                                %>
                                            </td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td align="right"><input type="submit" value="Lưu"  style="width:80px;"></td>
                                            <td><a href="index.jsp?TaskID=9"><input type="button" value="Hủy bỏ" style="width:80px;"></a></td>
                                            <td></td>
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

