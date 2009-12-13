<%@include file="../jspf/header.jspf" %>
<%@ page import="util.PatientDetails,java.util.Collection,java.util.List,util.MeasurementDetails"%>
        <h1>View measurement</h1>
        <%@include file="../jspf/gpremote.jspf" %>

        <%!
            public void jspInit() {
                gpRemote = getGPRemote();
            }
         %>
         <%
            String username = request.getParameter("username");
            String type     = request.getParameter("type");

            //Step 0 : select type
            if(username != null && type == null) {
                    out.println("You must select a type of measurement that you wish to see...");
                    List<String> types = gpRemote.getMeasurementTypes(username);
                    out.println("<ol>");
                    for(String s : types) {
                        out.println("<li><a href='viewmeasurements.jsp?username="+username+"&type="+s+"'>"+s+"</a></li>");
                    }
                    out.println("</ol>");
            }
            //Stap 1 : view measurements for that type
            else if(username != null && type != null) {
                PatientDetails pd = gpRemote.getPatientDetails(username);
                if(pd != null) {
                    Collection<MeasurementDetails> cmd = pd.getMeasurementsOfType(type);
                    Object[] measurements = cmd == null ? null : cmd.toArray();
                    if(measurements == null || measurements.length == 0)
                        out.println("No measurements of this type have been recorded yet for this user ...");
                    else {
         %>
                        <p>Viewing measurements of type <%= type %> for <%= pd.getName() %>.</p>
                        <table>
                <%
                            out.println("<thead>");
                                out.println("<th>Name</th>");
                                out.println("<th>Type</th>");
                                out.println("<th>Measured Value</th>");
                                out.println("<th>Date</th>");
                            out.println("</thead>");
                            for(int i=0;i<measurements.length;i++) {
                                MeasurementDetails md = (MeasurementDetails) measurements[i];
                                out.println("<tr>");
                                    out.println("<td>"+md.getName()+"</td>");
                                    out.println("<td>"+md.getType()+"</td>");
                                    out.println("<td>"+md.getMeasuredvalue()+"</td>");
                                    out.println("<td>"+md.getMeasurementtime()+"</td>");
                                out.println("</tr>");
                             }
                %>
                    </table>
                 <% }
                 } else out.println("An error has occured. Or you do not have the required access rights.");
                }
                else {
                    %>
                    We couldn't find that users information or you do not have access.<br/>
                    We're sorry for any inconvenience.
                    <%
                }
            %>

<%@include file="../jspf/footer.jspf" %>