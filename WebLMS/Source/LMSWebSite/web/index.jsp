<%-- 
    Document   : index
    Created on : Apr 24, 2010, 12:06:28 AM
    Author     : NKLapTop
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.hcmus.dao.lms.*,java.sql.Connection,java.sql.DriverManager,java.sql.SQLException" %>
<%
    Connection myConn = DataProvider.getConnection(this.getServletConfig());
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
            <tbody>
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
                    <form>
                    	<table>
                        	<tr>
                            	<td><span class="text1">Username:</span></td>
                                <td><input type="text" value="" name="txtUseName" maxlength="30" size="20" /></td>
                                <td align="left"><input type="button" value="Log In" name="Login"/></td>
                            </tr>
                            
                            <tr>
                            	<td><span class="text1">Password:</span></td>
                                <td><input type="password" value="" name="txtPassword" maxlength="30" size="20" /></td>
                            </tr>
                           
                        </table>
                    </form>
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
                  	<td height="500" colspan="9" align="left"> <!--editable region here--></td>
                </tr>
                
                <tr>
                    <td colspan="9" style="padding-left: 50px; line-height: 16px; text-align:center; color:#000" align="centre" background="images/b_links.jpg" height="80" valign="middle" width="775">
Copyright ©2010 HCMUS. All right reserver.
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
	

