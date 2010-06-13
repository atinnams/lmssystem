<%-- 
    Document   : ReportCard
    Created on : May 12, 2010, 8:29:25 PM
    Author     : NKLapTop
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.*,java.text.*" %>
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
    int PageItems = 20;
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
                        <td colspan="9">
                            <div >
                                <center><h1>THỐNG KÊ THẺ</h1></center>
                                <br>
                                <%
                                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                    java.util.Date date = new java.util.Date();
                                    DTO.DTO_JPOS_Admin admin = (DTO.DTO_JPOS_Admin)session.getAttribute("Admin");
                                %>
                                <h3>Ngày thống kê : <%=dateFormat.format(date) %></h3>
                                <h3>Người thống kê : <%=admin.getLastName() + " " + admin.getFirstName() %></h3>
                                <br>
                                <% if (resultViews == null)
                                   {
                                        %>
                                        <h3 style="padding-left:20px">Không tìm thấy dữ liệu thống kê</h3>
                                        <div style="height:400px"></div>
                                        <%
                                   }
                                   else
                                   {
                                        int iSize = resultViews.size();
                                        %>
                                        <table cellpadding="0" cellspacing="0" border="1px"  width="774px" >
                                            <tr bgcolor="blue" align="center">
                                                <th>Mã thẻ</th>
                                                <th>Ngày hết hạn</th>
                                                <th>Trạng thái</th>
                                                <th>Mã kích hoạt</th>
                                                <th>Mã khách hàng</th>                                               
                                            </tr>
                                         <pg:pager maxIndexPages="20" export="currentPageNumber=pageNumber" maxPageItems="<%=PageItems %>">
                                                <pg:param name="pg"/>
                                                <pg:param name="q"/>
                                        <%

                                        for (int i= 0 ; i < iSize; i++ )
                                        {
                                            DTO.DTO_JPOS_Card card = (DTO.DTO_JPOS_Card)resultViews.get(i);
                                            String strExpireDay = "";
                                            if (card.getJPOS_ExpireDay() != null)
                                                strExpireDay = dateFormat.format(card.getJPOS_ExpireDay());                                            

                                            %>
                                            <ex:searchresults>
                                            <pg:item>
                                            <tr align="center">
                                                <td><%=card.getJPOS_CardId() %></td>
                                                <td><%=strExpireDay %></td>
                                                <td><%=card.getStatus() %></td>
                                                <td><%=card.getActiveCode() %></td>
                                                <td><a href="index.jsp?TaskID=2&Detail=<%=card.getCustomerOwnerID() %>"><%=card.getCustomerOwnerID() %></a></td>
                                            </tr>
                                            </pg:item>
                                            </ex:searchresults>
                                            <%
                                        }
                                        %></table>
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
                                        <div style="height:200px"></div><%

                                   }
                                %>


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


