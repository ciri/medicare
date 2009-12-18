
<%@ page pageEncoding="UTF-8" %>
<%@ page import="javax.servlet.http.HttpSession"%>

<html>
    <head>
        <title>Medicare v1.0</title>
        <LINK href="${pageContext.request.contextPath}/style.css" rel="stylesheet" type="text/css">
 <!--     <LINK href="${pageContext.request.contextPath}/patient/jspf/patientstyle.css" rel="stylesheet" type="text/css">-->
    </head>
    <body>
        <div id="logoDiv">
            Medicare v1.0
        </div>
        <div id="mainDiv">
        <h1>Login</h1>
            <form method="POST" action="j_security_check">
                    <table>
                <tr>
                    <td>Username</td>
                    <td><input type="text" name="j_username"  /></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="j_password"  /></td>
                </tr>


                <tr>
                    <td colspan="2">
                        <INPUT TYPE="submit" name="submit" value="Login" />
                    </td>
                </tr>
            </table>
            </form>
        </div>
    </body>
</html>