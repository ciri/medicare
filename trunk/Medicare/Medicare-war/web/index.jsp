<%@include file="jspf/header.jspf" %>
        <h1>Index</h1>
            Login as user : <a href="index.jsp?login=patient0&role=patient">patient0</a>
            Login as admin: <a href="index.jsp?login=gp0&role=gp">gp0</a>
        <%
            if(request != null && session != null && request.getParameter("login") != null) {

                out.println("Setting username to "+request.getParameter("login"));

                session.setAttribute("username", request.getParameter("login"));
                session.setAttribute("password", request.getParameter("login"));
                session.setAttribute("role"    , request.getParameter("role"));
            }
        %>
<%@include file="jspf/footer.jspf" %>