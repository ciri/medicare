<%@include file="jspf/header.jspf" %>
<%@ page import="util.PatientDetails,java.util.Collection,java.util.List,java.util.Date,util.TaskDetails"%>
        <h1>View prescriptions</h1>
        <%@include file="jspf/patientremote.jspf" %>

        <%!
            public void jspInit() {
                patientRemote = getPatientRemote();
            }
         %>
         <%
            //View prescriptions for the patient
            TaskDetails td = patientRemote.getTask(session_username);

            //Step 0 : view task
            if(request.getParameter("submit") == null) {
                if(td != null) {
                     %>
                        <p>Viewing your next task ...</p>
                        <table>
                            <thead>
                                <th>Unit</th>
                                <th>Time</th>
                                <th>Dosage</th>
                                <th>Medication</th>
                                <th>Status</th>
                            </thead>
                            <tr>
                                <td><%= td.getPrescription().getUnit()                  %></td>
                                <td><%= td.getTasktime()                                %></td>
                                <td><%= td.getDose()                                    %></td>
                                <td><%= td.getPrescription().getMedication().getName()  %></td>
                                <th><%= td.getStatus()                                  %></th>
                            </tr>
                            </table>

                      <%
                        if(td.getTasktime().compareTo(new Date()) < 0 ) {
                      %>
                          <font color="red">This task has passed, you need to update the task status !</font>
                      <%
                        }
                        else {
                      %>
                          <font color="green">This task takes place in the future, but you may already fill in it's status.</font>
                      <%
                        }
                      %>
                          <form action="viewnexttask.jsp">
                            <input type="hidden" name="taskid" value="<%= td.getId() %>"/>
                            <select name="newstatus">
                                <option value="in progress">In progress</option>
                                <option value="forgotten">Forgotten</option>
                                <option value="unwilling to take">Unwilling to take</option>
                                <option value="completed">Completed</option>
                            </select>
                            <input type="submit" name="submit" value="update"/>
                          </form>
                      <%

                }
                else  {
                     out.println("An error has occured. Or you do not have the required access rights.");
                 }
            }
            //Step 2 : form has been submitted, update the task
            else {

                    if(patientRemote.updateTask(request.getParameter("taskid").toString(),
                                                request.getParameter("newstatus").toString()))
                                out.println("Succes!");
                    else
                                out.println("Something went wrong ...");
                      
                 }
            %>

<%@include file="jspf/footer.jspf" %>