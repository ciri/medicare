<%@include file="jspf/header.jspf" %>
<%@ page import="java.util.List,util.PrescriptionDetails,java.util.List,util.MedicationDetails"%>

<%@ page import="javax.naming.*,util.MeasurementDetails"%>

    <h1>Welcome back!</h1>
    <%@include file="jspf/patientremote.jspf" %>
    <%!
            public void jspInit() {
                patientRemote = getPatientRemote();
            }
    %>

    <p>You are currently taking following medications :</p>
    <p>
    <%
        //View medications for the patient
        List<String> medications = patientRemote.getMedications(session_username);

        if(medications == null || medications.size() == 0)
            out.println("No medications of been recorded for you yet ...");
        else {
            out.println("<ol>");
            for(String medication : medications) {
                out.println("<li>"+medication+"</li>");
            }
            out.println("</ol>");
        }
    %>
    </p>
    <p><font color="red">Warning : you still have uncompleted medication tasks!</font></p>
    <p>You can complete your next task <a href="viewnexttask.jsp">here</a>.</p>
<%@include file="jspf/footer.jspf" %>