<%--
    Document   : ClientPage
    Created on : May 8, 2010, 5:00:53 PM
    Author     : NKLapTop
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%
    String Reg = "";
    if (request.getQueryString() != null){
        String RequestString = request.getQueryString();

        int t = RequestString.indexOf("&pager.offset=");
        if (t!=-1){
            Reg = RequestString.substring(0, t);
        }else{
            Reg = RequestString;
        }
    }
    int PageItems = 5;
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
                                <a href="index.jsp">Trang chủ</a> &#8250; <a href="index.jsp?<%=request.getQueryString()%>"><%=strWebTitle %></a>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="9" class="main">
                            <div >
                                <div class="content" <%=strStyle%>>
                                    <h1><%=strWebTitle %></h1>
                                     <% if (resultViews ==null) { %>
                                    <center><h2>Thông tin tìm kiếm</h2></center>
                                    <form action="index.jsp" method="get">
                                        <input type="hidden" name="TaskID" value="4">
                                        <table>
                                            <tr>
                                                <td>
                                                    <table>
                                                         <tr>
                                                            <td> Mã khách hàng : </td>
                                                            <td><input type="text" name="txtCustomerID"></td>
                                                        </tr>
                                                        <tr>
                                                            <td> Họ khách hàng : </td>
                                                            <td><input type="text" name="txtLastName"></td>
                                                        </tr>
                                                        <tr>
                                                            <td> Tên khách hàng : </td>
                                                            <td><input type="text" name="txtFirstName"></td>
                                                        </tr>
                                                         <tr>
                                                            <td> Giới tính : </td>
                                                            <td>
                                                                <select name="txtGender">
                                                                    <option value="1">Nam</option>
                                                                    <option value="0">Nữ</option>
                                                                </select>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td> Số điểm hiện tại : </td>
                                                            <td><input type="text" name="txtCurrentPoint"></td>
                                                        </tr>
                                                    </table>
                                                </td>
                                                <td>
                                                    <table>
                                                        <tr>
                                                            <td> Địa chỉ : </td>
                                                            <td><input type="text" name="txtAddress"></td>
                                                        </tr>
                                                        <tr>
                                                            <td> Email : </td>
                                                            <td><input type="text" name="txtEmail"></td>
                                                        </tr>
                                                        <tr>
                                                            <td> Ngày sinh : </td>
                                                            <td><input type="text" name="txtBirthday"></td>
                                                        </tr>
                                                        <tr>
                                                            <td> Ngày tham gia : </td>
                                                            <td><input type="text" name="txtDateJoin"></td>
                                                        </tr>

                                                        <tr>
                                                            <td> Sở thích </td>
                                                            <td><input type="text" name="txtFavorite"></td>
                                                        </tr>

                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="2" align="center"><input type="submit" name="btnSearch" value="Tìm kiếm"></td>
                                            </tr>
                                        </table>
                                    </form>
                                    <%}%>
                                    <% if (resultViews!=null)
                                       {
                                            if (resultViews.isEmpty())
                                            {
                                                %><h2>Không tìm thấy kết quả phù hợp</h2><%
                                            }else
                                            {
                                                int iSize = resultViews.size();
                                                %>
                                                <h2><%=iSize %> Kết quả tìm thấy</h2>
                                                <hr>
                                                <table cellpadding="0px" cellspacing="0px" width="550px">
                                                <pg:pager maxIndexPages="20" export="currentPageNumber=pageNumber" maxPageItems="<%=PageItems %>">
                                                <pg:param name="pg"/>
                                                <pg:param name="q"/>
                                                <%
                                                for (int i=0; i< iSize; i++)
                                                {
                                                    DTO.DTO_JPOS_Customer jposCustomer = (DTO.DTO_JPOS_Customer)resultViews.get(i);
                                                    %>

                                                    <ex:searchresults>
                                                    <pg:item>
                                                    <tr>
                                                        <td>
                                                            <div>
                                                                <span>Mã khách hàng : <%=jposCustomer.getJPOS_CustomerID() %></span><br/>
                                                                <span>Tên khách hàng : <%=jposCustomer.getFirstName() %></span><br/>
                                                                <span>Số điểm hiện tại : <%=jposCustomer.getJPOS_CurrentPoint() %></span><br/>
                                                                <span>Emai : <%=jposCustomer.getEmail() %></span><br/>
                                                                <span>Địa chỉ :<%=jposCustomer.getAddress() %></span><br/>
                                                                <span><a href="index.jsp?TaskID=4&Detail=<%=jposCustomer.getJPOSID_Customer() %>">Chi tiết giao dịch</a></span>
                                                            </div>
                                                            <hr>
                                                        </td>
                                                     </tr>
                                                     </pg:item>
                                                     </ex:searchresults>
                                                     <%
                                                }
                                                %>
                                                </table>
                                                <div class="pagination" align="center">
                                                  <pg:index>
                                                    Pages:
                                                    <pg:prev>&nbsp;<a href="<%= "index.jsp?"+Reg + "&pager.offset=" + (pageNumber-1)*PageItems %>">[&lt;&lt; Prev]</a></pg:prev>
                                                    <pg:pages><%
                                                      if (pageNumber.intValue() < 10) {
                                                        %>&nbsp;<%
                                                      }
                                                      if (pageNumber == currentPageNumber) {
                                                        %><span class="current"><%= pageNumber %></span><%
                                                      } else {
                                                               %><a href="<%= "index.jsp?" + Reg + "&pager.offset=" + (pageNumber-1)*PageItems %>"><%= pageNumber %></a><%
                                                      }
                                                    %>
                                                    </pg:pages>
                                                    <pg:next>&nbsp;<a href="<%= "index.jsp?"+Reg + "&pager.offset=" + (pageNumber-1)*PageItems %>">[Next &gt;&gt;]</a></pg:next>
                                                    <br>
                                                  </pg:index>
                                                  </div>
                                                </pg:pager>
                                                <%
                                            }
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

