<%-- 
    Document   : login
    Created on : 13-dec-2009, 2:34:56
    Author     : kenneth
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="javax.servlet.http.HttpSession"%>

<html>
    <head>
        <title>Medicare v1.0</title>
    </head>
    <body>

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
    </body>
</html>