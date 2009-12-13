<%@include file="../jspf/header.jspf" %>
<%@ page import="util.PatientDetails"%>

        <h1>Edit Patient</h1>
        <%@include file="../jspf/gpremote.jspf" %>

        <%!
            public void jspInit() {
                gpRemote = getGPRemote();
            }
         %>
         <%
            String username = request.getParameter("username");
            PatientDetails pd = gpRemote.getPatientDetails(username);

            if(pd != null) {
         %>
            <form method="POST" action="savepatient.jsp">
            <table>
                <tr>
                    <td>ID</td>
                    <td><%= pd.getId() %></td>
                </tr>
                <tr>
                    <td>SSN</td>
                    <td><%= pd.getSSN() %></td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td><input type="text" name="name" value="<%= pd.getName() %>"></td>
                </tr>
                <tr>
                    <td>Username</td>
                    <td><input type="text" name="username" value="<%= pd.getUsername() %>"></td>
                </tr>
                <tr>
                    <td>Bloodgroup</td>
                    <td><input type="text" name="bloodgroup" value="<%= pd.getBloodgroup() %>"></td>
                </tr>
                <tr>
                    <td>Birthdate</td>
                    <td><input type="text" name="birthdate" value="<%= pd.getBirthdate() %>"></td>
                </tr>
                <tr>
                    <td>First consult</td>
                    <td><input type="text" name="firstconsult" value="<%= pd.getFirstconsult() %>"></td>
                </tr>
                <tr>
                    <td>Last consult</td>
                    <td><input type="text" name="lastconsult" value="<%= pd.getLastconsult() %>"></td>
                </tr>

                <tr>
                    <td colspan="2">
                        <INPUT TYPE="submit" value="Modify">
                    </td>
                </tr>
            </table>
            </form>
         <%
            } else {
                out.println("We couldn't find that users information or you do not have access.<br/>"
                            +"We're sorry for any inconvenience.");
            }
         %>
         
<%@include file="../jspf/footer.jspf" %>