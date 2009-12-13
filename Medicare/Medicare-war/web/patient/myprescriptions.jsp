<%@include file="../jspf/header.jspf" %>
<%@ page import="util.PatientDetails,java.util.Collection,java.util.List,util.PrescriptionDetails,java.util.List,util.MedicationDetails"%>
        <h1>View prescriptions</h1>
        <%@include file="../jspf/patientremote.jspf" %>

        <%!
            public void jspInit() {
                patientRemote = getPatientRemote();
            }
         %>
         <%
            //View prescriptions for the patient
            PatientDetails pd = patientRemote.getPatientDetails(session_username, session_password);
            if(pd != null) {
                 Collection<PrescriptionDetails> prds = pd.getPrescriptions();                    
                 Object[] prescriptions = (prds == null) ? null : prds.toArray();
                 if(prescriptions == null || prescriptions.length == 0)
                     out.println("No prescriptions of been recorded for you yet ...");
                 else {
         %>
                    <p>Viewing your prescriptions ...</p>
                    <table>
                <%
                            out.println("<thead>");
                                out.println("<th>Unit</th>");
                                out.println("<th>Frequency</th>");
                                out.println("<th>Start time</th>");
                                out.println("<th>End time</th>");
                                out.println("<th>Fixed dosage?</th>");
                                out.println("<th>Medication</th>");
                            out.println("</thead>");
                            for(int i=0;i<prescriptions.length;i++) {
                                PrescriptionDetails presdet = (PrescriptionDetails) prescriptions[i];
                                out.println("<tr>");
                                    out.println("<td>"+presdet.getUnit()      +"</td>");
                                    out.println("<td>"+presdet.getFrequency() +"</td>");
                                    out.println("<td>"+presdet.getStarttime() +"</td>");
                                    out.println("<td>"+presdet.getEndtime()   +"</td>");
                                    out.println("<td>"+presdet.isFixed()      +"</td>");
                                    MedicationDetails md = presdet.getMedication();
                                    out.println("<td><b>"+md.getName()        +"</b></td>");
                                out.println("</tr>");
                             }
                %>
                    </table>
                 <% }
                 } else out.println("An error has occured. Or you do not have the required access rights.");
            %>

<%@include file="../jspf/footer.jspf" %>