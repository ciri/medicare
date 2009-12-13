<%@include file="../jspf/header.jspf" %>
<%@ page import="javax.naming.*,ejb.gp.GPRemote,util.GPDetails,util.PatientDetails"%>

<%@include file="../jspf/gpremote.jspf" %>

    <%!
    public void jspInit() {
        gpRemote = getGPRemote();
    }
    %>
        <h1>Patient Overview For <%= session_username %></h1>
        <table border="1">
        <%
            Object[] patients =     gpRemote.getGPDetails(session_username)
                                            .getPatients()
                                            .toArray();


            out.println("<thead>");
                out.println("<th>Nr</th>");
                out.println("<th>SSN</th>");
                out.println("<th>Username</th>");
                out.println("<th>View</th>");
                out.println("<th>Modify</th>");
            out.println("</thead>");
            for (int i=0;i<patients.length;i++) {
                PatientDetails pd = (PatientDetails) patients[i];
                out.println("<tr>");
                    out.println("<td>"+i+"</td>");
                    out.println("<td>"+pd.getSSN()+"</td>");
                    out.println("<td>"+pd.getUsername()+"</td>");
                    out.println("<td>" +
                                   "<a href='viewpatient.jsp?username="+pd.getUsername()+"'>Profile</a> | " +
                                   "<a href='../measurement/viewmeasurements.jsp?username="+pd.getUsername()+"'>Measurement</a> | " +
                                   "<a href='../prescription/viewprescriptions.jsp?username="+pd.getUsername()+"'>Prescriptions</a>" +
                               "</td>");
                    out.println("<td>" +
                                   "<a href='editpatient.jsp?username="+pd.getUsername()+"'>Edit Profile</a> | " +
                                   "<a href='../measurement/addmeasurement.jsp?username="+pd.getUsername()+"'>Add measurement</a> | " +
                                   "<a href='../prescription/addprescription.jsp?username="+pd.getUsername()+"'>Add prescription</a>"+
                                "</td>");
                out.println("</tr>");
            }
            
        %>
        </table>
<%@include file="../jspf/footer.jspf" %>