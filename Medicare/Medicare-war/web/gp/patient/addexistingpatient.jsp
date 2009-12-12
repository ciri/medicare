<%@include file="../../jspf/header.jspf" %>
<%@ page import="javax.naming.*,ejb.gp.GPRemote,util.GPDetails,util.PatientDetails,java.util.List"%>

<%@include file="../../jspf/patientremote.jspf" %>
<%@include file="../../jspf/gpremote.jspf" %>

    <%!
    public void jspInit() {
        patientRemote = getPatientRemote();
        gpRemote = getGPRemote();
    }
    %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Initialize</title>
    </head>
    <body>
        <h1>Add existing patient ...</h1>
        <%
        String username = request.getParameter("username");
        if(username == null ) {
         out.println("<table border='1'>");
            List<PatientDetails> patients =     gpRemote.getAllPatientDetails("gp0", "gp0");
            out.println("<thead>");
                out.println("<th>Nr</th>");
                out.println("<th>SSN</th>");
                out.println("<th>Username</th>");
                out.println("<th>Add</th>");
            out.println("</thead>");
            int i=1;
            for (PatientDetails pd : patients) {
                out.println("<tr>");
                    out.println("<td>"+(i++)+"</td>");
                    out.println("<td>"+pd.getSSN()+"</td>");
                    out.println("<td>"+pd.getUsername()+"</td>");
                    out.println("<td><a href='addexistingpatient.jsp?username="+pd.getUsername()+"'>Add Patient</a></td>");
                out.println("</tr>");
            }
            out.println("</table>");
        }
        else {
            if(gpRemote.addPatient("gp0", "gp0", username)) {
                out.println("The patient was succesfully registered to yourself.");
            }
            else {
                out.println("An error has occured.");
            }
        }
        %>

<%@include file="../../jspf/footer.jspf" %>