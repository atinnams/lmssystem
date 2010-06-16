<%-- 
    Document   : MainPage
    Created on : May 5, 2010, 11:06:33 PM
    Author     : NKLapTop
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DTO.*" %>
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
                                <a href="index.jsp">Trang chủ</a>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="9" class="main">
                            <div >
                                <div class="content">
                                    <h1>LMS Website Project</h1>
                                    <div class="descr">July 05, 2010 by Nguyễn Khuyến,Phạm Thế Hùng</div>

                                    <h2>Giới thiệu về LMS system</h2>
                                    <p>
                                        Khóa luận tập trung vào xây dựng giải pháp server cho hệ thống LMS.<br>
                                        Hệ thống server sẽ nhận các gói dữ liệu gởi theo chuẩn ISO8583 từ các thiết bị đọc mã vạch hoặc các máy POS terminal hay thậm chí là từ thiết bị di động có hỗ trợ phần mềm đọc mã vạch.<br>
                                        Sau đó hệ thống sẽ tiến hành thực hiện các tác vụ có liên quan như cộng điểm khi mua hàng trừ điểm khi trả hàng hoặc các tác vụ khác như kích hoạt thẻ sau đó lưu trữ vào database và trả kết quả về cho client.<br>
                                        Hệ thống hỗ trợ khách hàng quản lý truy vấn thông tin tài khoản của mình thông qua giao diện web. <br>
                                        Quản lý thống kê các tài khoản khách hàng, thống kê việc mua bán của khách hàng nhầm giúp các doanh nghiệp đưa ra chuyến lược kinh doanh hợp lý.
                                    </p>
                                    <blockquote>
                                        <p>
                                            Ưu điểm : Hỗ trợ các thiết bị có sẵn như điện thoại di động có khả năng đọc barcode.Các kênh truyền được thiết kế độc lập nhờ một module trung gian giúp cho việc khả chuyển các kênh truyền phục vụ cho việc mở rộng bài toàn sau này.<br>
                                            <br>
                                            Khuyết điểm : Khả năng bảo mật thấp do mã nguồn mở.Việc ứng dụng vào thực tế cần có sự đầu tư của các doanh nghiệp.<br>
                                        </p>
                                    </blockquote>
                                     <h1>Cập nhật cơ sở dữ liệu</h1>
                                    <div class="descr">April 19, 2010 by Nguyễn Khuyến, Phạm Thế Hùng</div>

                                    <p>Cập nhật cơ sở dữ liệu theo yêu cầu mới từ khách hàng, hỗ trợ thêm cho việc truy vấn số dư, các giao dịch liên quan đến tài khoản, số tiền trong thẻ khách hàng.</p>

                                    Các giao dịch bổ sung :
                                        <ul>
                                            <li>Redeem</li>
                                            <li>Reload</li>
                                            <li>Void</li>
                                            <li>Balance Inquiry</li>
                                        </ul>

                                    <p>Thông tin chi tiết về từng giao dịch được miêu tả trong tài liệu luận văn. Để hiểu rõ hơn từng giao dịch xin vui lòng xem chi tiết trong tài liệu đính kèm.</p>
                                    <h1>Khởi tạo dự án website</h1>
                                    <div class="descr">April 24, 2010 by Nguyễn Khuyến</div>

                                    <p>Khởi tạo kiến trúc website, xây dựng nền framework và các thư viện cần thiết, kết hợp với database của JPOS server, cập nhật những class đã có sẵn từ JPOS server và kiến trúc nền trên server</p>

                                    <h3>Cấu trúc Website</h3>
                                    <p>Website được xây dựng dựa trên ngôn ngữ jsp kết hợp với mô hình MVC, thông tin chi tiết có thể tham khảo thêm trong tài liệu luận văn</p>

                                    <h1>Kết thúc dự án website.</h1>

                                    <p>Dự án website LMS là một phần bổ sung cho hệ thống LMS server, giúp hỗ trợ khách hàng tương tác với hệ thống giao diện thân thiện. Do thời gian không cho phép nên chỉ đáp ứng được những thao tác cơ bản nhất đối với hệ thống, những yêu cầu cao hơn như về bảo mật, quản lý các trạng thái sẽ được cập nhật ở những phiên bản nâng cao của hệ thống LMS.</p>
                                    <p>Thông tin hiển thị trên web site là những thông tin mẫu, được xây dựng để kiểm thử hệ thống, để thay đổi những thông tin này vui lòng liên lạc với quản trị viên website để thay đổi</p>
                                    <code>Thông tin tổng đại lý
                            --------------------------------------------------------------------
                            Tên đại lý              : <%=issuer.getIssuerName() %>
                            Địa chỉ                  : <%=issuer.getIssuerAddress() %>
                            Ngày thành lập      : <%=issuer.getIssuerDateFound() %>
                            --------------------------------------------------------------------
                                    </code>
                                    
                                   
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
</html>

