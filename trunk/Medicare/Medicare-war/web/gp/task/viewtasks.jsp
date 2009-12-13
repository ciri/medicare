<%@include file="../jspf/header.jspf" %>
<%@ page import="util.PatientDetails,java.util.Collection,java.util.List,util.TaskDetails"%>
        <h1>View tasks</h1>
        <%@include file="../jspf/gpremote.jspf" %>
        <%!
            public void jspInit() {
                gpRemote = getGPRemote();
            }
         %>
         <%
            String username = request.getParameter("username");
            
            //View prescriptions for the patient
            if(username != null) {
                List<TaskDetails> tasks = gpRemote.getTasks(username);
                
                if(tasks == null || tasks.size() == 0)
                    out.println("This patient doesn't have any tasks yet.");
                else {
                 %>
                        <p>Viewing tasks for <%= username %>.</p>
                        <table>
                <%
                            out.println("<thead>");
                                out.println("<th>Time</th>");
                                out.println("<th>Status</th>");
                                out.println("<th>Medication</th>");
                            out.println("</thead>");
                            for(TaskDetails td : tasks) {
                                out.println("<tr>");
                                    out.println("<td>"+td.getTasktime()+"</td>");
                                    out.println("<td>"+td.getStatus()+"</td>");
                                    out.println("<td>"+td.getMedication()+"</td>");
                                out.println("</tr>");
                             }
                %>
                    </table>
                 <% }
                 } else out.println("An error has occured. Or you do not have the required access rights.");
            %>

<%@include file="../jspf/footer.jspf" %>