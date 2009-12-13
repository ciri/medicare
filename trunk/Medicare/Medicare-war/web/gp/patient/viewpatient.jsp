<%@include file="../jspf/header.jspf" %>
<%@ page import="util.PatientDetails,java.util.List"%>

        <h1>View Patient</h1>
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
            <table>
                <tr>
                    <td>ID</td>
                    <td><%= pd.getId() %></td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td><%= pd.getName() %></td>
                </tr>
                <tr>
                    <td>Nickname</td>
                    <td><%= pd.getUsername() %></td>
                </tr>
                <tr>
                    <td>General Practicioners</td>
                    <td>
                        <%
                            Object[] gps = pd.getGps().toArray();
                            
                            if(gps.length > 0) {
                                for(int i=0;i<gps.length;i++) {
                                    out.println(gps[i]+"<br/>");
                                }
                            }
                            else {
                                out.println("This patient has no currently assigned practicioners.");
                            }
                        %>
                    </td>
                </tr>
                <tr>
                    <td><a href="../measurement/viewmeasurements.jsp?username=<%= pd.getUsername() %>">View measurements</a></td>
                </tr>
            </table>
         <% }
            else {
                %>
                We couldn't find that users information or you do not have access.<br/>
                We're sorry for any inconvenience.
                <%
            }
            %>

<%@include file="../jspf/footer.jspf" %>