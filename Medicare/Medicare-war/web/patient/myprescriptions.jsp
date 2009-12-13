<%@include file="jspf/header.jspf" %>
<%@ page import="util.PatientDetails,java.util.Collection,java.util.List,util.PrescriptionDetails,java.util.List,util.MedicationDetails"%>
        <h1>View prescriptions</h1>
        <%@include file="jspf/patientremote.jspf" %>

        <%!
            public void jspInit() {
                patientRemote = getPatientRemote();
            }
         %>
         <%
            //View prescriptions for the patient
            List<PrescriptionDetails> prescriptions = patientRemote.getPrescriptions(session_username);

                 if(prescriptions == null || prescriptions.size() == 0)
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
                            for(PrescriptionDetails presdet : prescriptions) {
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
            %>

<%@include file="jspf/footer.jspf" %>