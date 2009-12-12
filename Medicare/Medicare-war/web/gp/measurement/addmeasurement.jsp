<%@include file="../../jspf/header.jspf" %>
<%@ page import="javax.naming.*,util.MeasurementDetails"%>
<%
   String posted = null;

   try {
       if(request != null)
            posted = request.getParameter("submit");
   } catch(Exception e) {}

   if(posted == null) {
%>
        <h1>Add measurement</h1>
            <form method="POST" action="addmeasurement.jsp">
                <input type="hidden" name="username" value="<%= request.getParameter("username") %>"/>
            <table>
                <tr>
                    <td>Name</td>
                    <td><input type="text" name="name" value="glucose" /></td>
                </tr>
                <tr>
                    <td>Type</td>
                    <td><input type="text" name="type" value="default" /></td>
                </tr>
                <tr>
                    <td>Value</td>
                    <td><input type="text" name="value" value="0" /></td>
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
       <%@include file="../../jspf/gpremote.jspf" %>
       <%!
            public void jspInit() {
                gpRemote = getGPRemote();
            }
        %>
       <%
            String gp_name      = session_username;
            String gp_password  = session_password;
            String p_username = request.getParameter("username");
            
            String m_name    = request.getParameter("name");
            String m_type    = request.getParameter("type");
            String m_value   = request.getParameter("value");


            MeasurementDetails md = new MeasurementDetails(m_name,m_type,m_value);

            if(gpRemote.addMeasurement(gp_name, gp_password, p_username, md)) {
                out.println("Succes!");
            }
            else
                out.println("Failed ...");
    }
%>
<%@include file="../../jspf/footer.jspf" %>