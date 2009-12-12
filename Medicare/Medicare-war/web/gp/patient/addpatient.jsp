<%@include file="../../jspf/header.jspf" %>
<%
   String posted = null;

   try {
       if(request != null)
            posted = request.getParameter("submit");
   } catch(Exception e) {}
   
   if(posted == null) {
%>
        <h1>Add patient</h1>
            <form method="POST" action="addpatient.jsp">
            <table>
                <tr>
                    <td>SSN</td>
                    <td><input type="text" name="SSN" value="aaaaa"></td>
                </tr>
                <tr>
                    <td>Username</td>
                    <td><input type="text" name="username" value="dada"></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password" value="random"></td>
                </tr>

                <tr>
                    <td colspan="2">
                        <INPUT TYPE="submit" name="submit" value="Add">
                    </td>
                </tr>
            </table>
                You can also <a href="addexistingpatient.jsp">register an existing patient to yourself</a>.
            </form>
<%
    }
    else {
       %>
       <%@include file="../../jspf/gpremote.jspf" %>
       <%!
            public void jspInit() {
                gpRemote = getGPRemote();
            }
        %>
       <%
            String p_SSN = request.getParameter("SSN");
            String p_username = request.getParameter("username");
            String p_password = request.getParameter("password");

            out.println("Added : "+p_SSN+","+p_username+","+p_password+"<br/>");
            if(gpRemote.createPatient(session_username, session_password, p_SSN, p_username, p_password)) {
                out.println("Succes!");
            }
            else
                out.println("Failed ...");
    }
%>
<%@include file="../../jspf/footer.jspf" %>