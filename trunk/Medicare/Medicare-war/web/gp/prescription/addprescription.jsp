<%@include file="../../jspf/header.jspf" %>
<%@ page import="javax.naming.*,util.MedicationDetails,java.util.List"%>

<h1>Add measurement</h1>
       <%@include file="../../jspf/gpremote.jspf" %>
       <%!
            public void jspInit() {
                gpRemote = getGPRemote();
            }
        %>
<%
   String posted     = null;
   String username   = null;
   String medication = null;
   String gp_name  = "gp0";
   String gp_password  = "gp0";
   try {
       if(request != null) {
            posted = request.getParameter("submit");
            username=request.getParameter("username");
            medication=request.getParameter("medication");
        }
   } catch(Exception e) {}

   //Step 0 : select a medicine to prescribe
   if(posted == null) {
        out.println("<p>Please select a medicine to prescribe to the patient.");
        List<MedicationDetails> medications = gpRemote.getAllMedications(gp_name, gp_password);
        out.println("<ol>");
        for(MedicationDetails m : medications)
            out.println("<a href='addprescription.jsp?username="+username+"&medication="+m.getName()+"'>"+m.getName()+"</a>");
        out.println("</ol>");
   }
   //Step 1 : now select the dosage etc.
   else if(posted != null && username != null && medication != null) {
        gpRemote.getMedication();
%>
            <form method="POST" action="addprescription.jsp">
                <input type="hidden" name="username" value="<%= request.getParameter("username") %>"/>
                <input type="hidden" name="medication" value="<%= request.getParameter("medication") %>"/>
            <table>
                <tr>
                    <td>Medication</td>
                    <td><%= medication %></td>
                </tr>

                <tr>
                    <td>Unit</td>
                    <td><input type="text" name="name" value="ibuprofen" /></td>
                </tr>
                <tr>
                    <td>Frequency</td>
                    <td><input type="text" name="sdose" value="0" /></td>
                </tr>
                <tr>
                    <td>Start Time</td>
                    <td><input type="text" name="udose" value="0" /></td>
                </tr>
                <tr>
                    <td>End Time</td>
                    <td><input type="text" name="udose" value="0" /></td>
                </tr>

                <tr>
                    <td colspan="2">
                        <INPUT TYPE="submit" name="submit" value="Add" />
                    </td>
                </tr>
            </table>
            </form>
<%
    }
    else {
       %>

       <%
            String gp_name  = "gp0";
            String gp_password = "gp0";

            String m_name    = request.getParameter("name");
            String m_sdose   = request.getParameter("sdose");
            String m_udose   = request.getParameter("udose");


            MedicationDetails md = new MedicationDetails(m_name,m_sdose,m_udose);

            if(gpRemote.addMedication(gp_name, gp_password, md)) {
                out.println("Succes!");
            }
            else
                out.println("Failed ...");
    }
%>
<%@include file="../../jspf/footer.jspf" %>