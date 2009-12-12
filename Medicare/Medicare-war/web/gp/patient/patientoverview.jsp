<%@include file="../../jspf/header.jspf" %>
<%@ page import="javax.naming.*,ejb.gp.GPRemote,util.GPDetails,util.PatientDetails"%>

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
        <h1>Patient Overview For gp0</h1>
        <table border="1">
        <%
            Object[] patients =     gpRemote
                                    .getGPDetails("gp0", "gp0")
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
                                   "<a href='../measurement/viewmeasurements.jsp?username="+pd.getUsername()+"'>Measurement</a>" +
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
<%@include file="../../jspf/footer.jspf" %>