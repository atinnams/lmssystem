<%--
    Document   : Transaction Detail
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
    int PageItems = 9;
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
                                    <% if (resultViews!=null)
                                       {    %>
                                            <h2>Mã khách hàng : <%=iCustID %></h2>
                                                <h3>Tên khách hàng : <%=custInfor.getLastName() + " " + custInfor.getFirstName() %></h3>
                                                <h3>Địa chỉ : <%=custInfor.getAddress() %></h3>
                                                <h3>Email : <%=custInfor.getEmail() %></h3>
                                            <hr>
                                            <%
                                            if (resultViews.isEmpty())
                                            {
                                                %><h2>Không tìm thấy kết quả giao dịch</h2><%
                                            }else
                                            {
                                                int iSize = resultViews.size();
                                                %>                                                
                                                <table cellpadding="0px" cellspacing="0px" width="550px" border="1px" >
                                                    <tr bgcolor="Blue" style="color:white" align="center">
                                                        <th>Mã giao dịch</th>
                                                        <th>Ngày giao dịch</th>
                                                        <th>Loại giao dịch</th>
                                                        <th>Điểm cộng</th>
                                                        <th>Điểm trừ</th>
                                                        <th>PoSCC</th>
                                                        <th>Địa chỉ giao dịch</th>
                                                        <th>Quà tặng</th>
                                                    </tr>
                                                 <pg:pager maxIndexPages="20" export="currentPageNumber=pageNumber" maxPageItems="<%=PageItems %>">
                                                <pg:param name="pg"/>
                                                <pg:param name="q"/>
                                                <%
                                                for (int i=0; i< iSize; i++)
                                                {
                                                    DTO.DTO_Report report = (DTO.DTO_Report)resultViews.get(i);
                                                    String strGift = "";
                                                    if (report.getGiftName() != null)
                                                        strGift = report.getGiftName();
                                                    %>
                                                    <ex:searchresults>
                                                    <pg:item>
                                                    <tr align="center">
                                                        <td><%=report.getLogID() %></td>
                                                        <td><%=report.getDate().toString() %></td>
                                                        <td><%=report.getTask() %></td>
                                                        <td><%=report.getPointGain() %></td>
                                                        <td><%=report.getPointLoss() %></td>
                                                        <td><%=report.getPoSCCName() %></td>
                                                        <td><%=report.getMerchantAddress() %></td>
                                                        <td><%=strGift %></td>
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
                                       else
                                       {
                                            %><h1>Không tìm thấy dữ liệu phù hợp</h1><%
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


