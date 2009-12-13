<%@include file="../jspf/header.jspf" %>
<%@ page import="javax.naming.*,util.MedicationDetails,util.PrescriptionDetails,java.util.List"%>

<h1>Add prescription</h1>
       <%@include file="../jspf/gpremote.jspf" %>
       <%!
            public void jspInit() {
                gpRemote = getGPRemote();
            }
        %>
<%
   String posted        = null;
   String username      = null;
   String medication    = null;
   String gp_name       = "gp0";
   String gp_password   = "gp0";
   try {
       if(request != null) {
            posted = request.getParameter("submit");
            username=request.getParameter("username");
            medication=request.getParameter("medication");
        }
   } catch(Exception e) {}

   //Step 0 : select a medicine to prescribe
   if(posted == null && username != null && medication == null) {
        out.println("<p>Please select a medicine to prescribe to the patient.");
        List<MedicationDetails> medications = gpRemote.getAllMedications();
        out.println("<ol>");
        for(MedicationDetails m : medications)
            out.println("<li><a href='addprescription.jsp?username="+username+"&medication="+m.getName()+"'>"+m.getName()+"</a></li>");
        out.println("</ol>");
   }
   //Step 1 : now select the dosage etc.
   else if(posted == null && username != null && medication != null) {
            //MedicationDetails md =  gpRemote.getMedication(gp_name, gp_password, medication);
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
                    <td><input type="text" name="unit" value="1" /></td>
                </tr>
                <tr>
                    <td>Frequency</td>
                    <td><input type="text" name="freq" value="1" /></td>
                </tr>
                <tr>
                    <td>Start Time</td>
                    <td>
                        <input type="text" name="stime0" size="2" value="01" />-
                        <input type="text" name="stime1" size="2" value="01" />-
                        <input type="text" name="stime2" size="2" value="09" />
                    </td>
                </tr>
                <tr>
                    <td>End Time</td>
                    <td>
                        <input type="text" name="etime0" size="2" value="01" />-
                        <input type="text" name="etime1" size="2" value="01" />-
                        <input type="text" name="etime2" size="2" value="09" />
                    </td>
                </tr>
                <tr>
                    <td>Fixed</td>
                    <td><input type="checkbox" name="fixed" value="0" /></td>
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
   //Step 3 : save the prescriptions
   else if(posted != null && username != null && medication != null) {
            String p_unit   = request.getParameter("unit");
            String p_fixed   = request.getParameter("fixed");
            String p_freq   = request.getParameter("freq");
            String p_stime   = request.getParameter("stime0")+"-"+request.getParameter("stime1")+"-"+request.getParameter("stime2");
            String p_etime   = request.getParameter("etime0")+"-"+request.getParameter("etime1")+"-"+request.getParameter("etime2");
            
            PrescriptionDetails pd = new PrescriptionDetails(p_unit,p_freq,p_stime,p_etime, p_fixed,medication);
            if(gpRemote.addPrescription(username , pd)) {
                out.println("Succes!");
            }
            else
                out.println("Failed ...");
    }
%>
<%@include file="../jspf/footer.jspf" %>