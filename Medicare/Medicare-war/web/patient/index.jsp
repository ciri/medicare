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

    You are currently taking following medications :
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
    <font color="red">Warning : you still have uncompleted medication tasks!</font>
    You can complete your next task <a href="viewnexttask.jsp">here</a>.
<%@include file="jspf/footer.jspf" %>