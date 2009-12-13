<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../jspf/header.jspf" %>
<%@include file="../jspf/gpremote.jspf" %>

        <%!
            public void jspInit() {
                gpRemote = getGPRemote();
            }
         %>
        <h1>Succes!</h1>
         <!-- Maak patient aan vanuit de POST request -->
         <jsp:useBean id="patient" class="util.PatientDetails" scope="session"/>
         <jsp:setProperty name="patient" property="*"/>       
         <%           
                out.println(patient.getName()+"<br/>");
                out.println(patient.getUsername()+"<br/>");
           if(gpRemote.setPatientInformation(patient)) {
                out.println("Succes!<br/>");
                out.println("<a href='patientoverview.jsp'>Back to overview</a>");
            }
            else
                out.println("An error has occured. Do you have the correct rights to edit this patient?");
         %>
<%@include file="../jspf/footer.jspf" %>