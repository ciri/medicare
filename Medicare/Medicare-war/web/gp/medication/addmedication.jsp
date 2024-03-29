<%@include file="../jspf/header.jspf" %>
<%@ page import="javax.naming.*,util.MedicationDetails"%>
<%
   String posted = null;

   try {
       if(request != null)
            posted = request.getParameter("submit");
   } catch(Exception e) {}

   if(posted == null) {
%>
        <h1>Add medication</h1>
            <form method="POST" action="addmedication.jsp">
                <input type="hidden" name="username" value="<%= request.getParameter("username") %>"/>
            <table>
                <tr>
                    <td>Name</td>
                    <td><input type="text" name="name" value="ibuprofen" /></td>
                </tr>
                <tr>
                    <td>Standard Dose</td>
                    <td><input type="text" name="sdose" value="1" /></td>
                </tr>
                <tr>
                    <td>Unit Dose</td>
                    <td><input type="text" name="udose" value="milligram" /></td>
                </tr>
                <tr>
                    <td>Required Measurement Type</td>
                    <td><input type="text" name="mrequired" value="none" /></td>
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
       <%@include file="../jspf/gpremote.jspf" %>
       <%!
            public void jspInit() {
                gpRemote = getGPRemote();
            }
        %>
       <%
            String gp_name      = session_username;

            String m_name    = request.getParameter("name");
            String m_sdose   = request.getParameter("sdose");
            String m_udose   = request.getParameter("udose");
            String m_measurementrequired = request.getParameter("mrequired");

            MedicationDetails md = new MedicationDetails(m_name,m_sdose,m_udose,m_measurementrequired);

            if(gpRemote.addMedication( md )) {
                out.println("Succes!");
            }
            else
                out.println("Failed ...");
    }
%>
<%@include file="../jspf/footer.jspf" %>