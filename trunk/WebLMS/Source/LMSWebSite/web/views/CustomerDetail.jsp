<%-- 
    Document   : CustomerDetail
    Created on : May 11, 2010, 12:48:19 AM
    Author     : NKLapTop
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
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
                                <a href="index.jsp">Trang chủ</a> &#8250; <a href="index.jsp?TaskID=6">Quản lý khách hàng</a> &#8250; <a href="index.jsp?<%=request.getQueryString()%>"><%=strWebTitle%></a>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="9" class="main">
                            <div >
                                <div class="content" <%=strStyle%>>
                                    <h1><%=strWebTitle%></h1>
                                    <% if (custInfor != null) {%>
                                    <div style="height:30px"></div>
                                    <table>

                                        <tr>
                                            <th align="left"> Mã khách hàng : </th>
                                            <td><%=custInfor.getJPOS_CustomerID() %></td>
                                        </tr>
                                        <tr>
                                            <th align="left"> Họ khách hàng : </th>
                                            <td><%=custInfor.getLastName() %></td>
                                        </tr>
                                        <tr>
                                            <th align="left"> Tên khách hàng : </th>
                                            <td><%=custInfor.getFirstName() %></td>
                                        </tr>
                                        <tr>
                                            <th align="left"> Giới tính : </th>
                                            <td>
                                                <%
                                                strGender = "Nữ";
                                                if (custInfor.isGender())
                                                    strGender = "Nam";
                                                %>
                                                <%=strGender %>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th align="left"> Số điểm hiện tại : </th>
                                            <td><%=custInfor.getJPOS_CurrentPoint() %></td>
                                        </tr>

                                        <tr>
                                            <th align="left"> Địa chỉ : </th>
                                            <td><%=custInfor.getAddress() %></td>
                                        </tr>
                                        <tr>
                                            <th align="left"> Email : </th>
                                            <td><%=custInfor.getEmail() %></td>
                                        </tr>
                                        <tr>
                                            <th align="left"> Ngày sinh : </th>
                                            <td>
                                            <%if (custInfor.getBirthDay()!= null)
                                                out.print(custInfor.getBirthDay().toString());
                                              else
                                                out.print("null");
                                            %>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th align="left"> Ngày tham gia : </th>
                                            <td>
                                                <%  if (custInfor.getDateJoin()!= null)
                                                        out.print(custInfor.getDateJoin().toString());
                                                    else
                                                        out.print("null");
                                                %>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th align="left"> Sở thích </th>
                                            <td><%=custInfor.getFavorite() %></td>
                                        </tr>
                                        <tr>
                                            <th align="left"> Trạng thái </th>
                                            <td><%=custInfor.getStatusName() %></td>
                                        </tr>
                                    </table>

                                    <%}%>
                                    <% if (session.getAttribute("Admin") != null)
                                        {
                                           %>
                                           <div align="center">
                                               <table>
                                                   <tr>
                                                       <td><a href="index.jsp?TaskID=6&CustTask=4&CustID=<%=custInfor.getJPOS_CustomerID() %>" title="Thay đổi thông tin"><img src="images/modify.jpg"></a></td>
                                                       <td><a href="index.jsp?TaskID=6&CustTask=4&CustID=<%=custInfor.getJPOS_CustomerID() %>" title="Thay đổi thông tin"> Thay đổi thông tin khách hàng</a></td>
                                                   </tr>
                                               </table>
                                           </div>
                                            <%
                                        }
                                    %>                                    
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

