<%@include file="../jspf/header.jspf" %>
<%@ page import="util.PatientDetails,java.util.Collection,java.util.List,util.PrescriptionDetails,java.util.List,util.MedicationDetails"%>
        <h1>View prescriptions</h1>
        <%@include file="../jspf/gpremote.jspf" %>
        <%!
            public void jspInit() {
                gpRemote = getGPRemote();
            }
         %>
         <%
            String username = request.getParameter("username");
            String type     = request.getParameter("type");

            //View prescriptions for the patient
            if(username != null) {
                List<PrescriptionDetails> prescriptions = gpRemote.getPrescriptions(username);
                if(prescriptions == null || prescriptions.size() == 0)
                    out.println("This patient doesn't have any prescriptions yet.");
                else {
                 %>
                        <p>Viewing prescriptions for <%= username %>.</p>
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
                                    out.println("<td>"+presdet.getUnit()+"</td>");
                                    out.println("<td>"+presdet.getFrequency()+"</td>");
                                    out.println("<td>"+presdet.getStarttime()+"</td>");
                                    out.println("<td>"+presdet.getEndtime()+"</td>");
                                    out.println("<td>"+presdet.isFixed()+"</td>");
                                    MedicationDetails md = presdet.getMedication();
                                    out.println("<td><b>"+md.getName()+"</b></td>");
                                out.println("</tr>");
                             }
                %>
                    </table>
                 <% }
                 } else out.println("An error has occured. Or you do not have the required access rights.");
            %>

<%@include file="../jspf/footer.jspf" %>