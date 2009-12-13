<%-- 
    Document   : login
    Created on : 13-dec-2009, 2:34:56
    Author     : kenneth
--%>
<%@include file="jspf/header.jspf" %>
<%
   String posted = null;

   try {
       if(request != null)
            posted = request.getParameter("submit");
    
   } catch(Exception e) {}

   if(posted == null) {
%>
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
<%
    }
    else {
 
    
      //      String p_username  = session_username;
        //    String p_password  = session_password;

       
    }
%>
<%@include file="jspf/footer.jspf" %>