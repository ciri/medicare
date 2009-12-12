<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="javax.naming.*,ejb.FriendRemote, util.AccountDetails"%>

<%!    private FriendRemote friendRemote = null;

    public void jspInit() {
        try {
            InitialContext ic = new InitialContext();
            friendRemote = (FriendRemote) ic.lookup(FriendRemote.class.getName());
        } catch (Exception ex) {
            System.out.println("Couldn't create friendRemote bean." + ex.getMessage());
        }
    }

    public void jspDestroy() {
        friendRemote = null;
    }
%>

<%
            String username = (String) session.getAttribute("username");
            String password = (String) session.getAttribute("password");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FriendList</title>
    </head>
    <body>
        <%  String action = request.getParameter("action");
            if (action != null) {
                if (action.equalsIgnoreCase("delete")) {
                    friendRemote.removeAccount(username, password);
                }
                if (action.equalsIgnoreCase("delete") || action.equalsIgnoreCase("logout")) {
                    session.setAttribute("username", null);
                    session.setAttribute("password", null);
                }
            }
        %>

        <%  if (session.getAttribute("username") != null) {%>

        <%
  AccountDetails details = friendRemote.getAccountInformation(
          username, password);
        %>


        <h2>Friendlist of <% out.print(details.getName());%></h2>


        <ul>
            <% for (String friend : details.getFriends()) {%>
            <li><% out.print(friend);%></li>
            <% }%>
        </ul>
        <hr>

        <%
              if (session.getAttribute("username") == null) {
                  response.sendRedirect("index.jsp");
              }

              String addFriend = request.getParameter("addfriend");
              String removeFriend = request.getParameter("removefriend");
              String error = null;

              if (addFriend != null) {
                  if (friendRemote.addFriend(username, password, addFriend)) {
                      response.sendRedirect("index.jsp");
                  } else {
                      error = "Adding friend failed";
                  }
              }
              if (removeFriend != null) {
                  if (friendRemote.removeFriend(username, password, removeFriend)) {
                      response.sendRedirect("index.jsp");
                  } else {
                      error = "Removing friend failed";
                  }
              }

              if (error != null) {
        %>
        <p><font color="red"><% out.print(error);
      }%></font></p>

        <form action="index.jsp" method="POST">
            <p>
                <input type="text" name="removefriend" value="" size="50" />
                <input type="submit" value="Remove Friend" />
            </p>
        </form>
        <form action="index.jsp" method="POST">
            <p>
                <input type="text" name="addfriend" value="" size="50" />
                <input type="submit" value="Add Friend" />
            </p>
        </form>

        <hr>

        <a href="index.jsp?action=logout">Logout</a>
        <a href="index.jsp?action=delete">Delete Account</a>



        <%} else {%>

        <%
   boolean attempt = false;
   String loginUsername = request.getParameter("username");
   String loginPassword = request.getParameter("password");

   if (loginUsername != null && loginPassword != null && session.getAttribute("username") == null) {
       attempt = true;
       if (friendRemote.isValidAccount(loginUsername, loginPassword) ||
               friendRemote.addAccount(loginUsername, loginPassword)) {
           session.setAttribute("username", loginUsername);
           session.setAttribute("password", loginPassword);
       }
   }

   if (session.getAttribute("username") != null) {
       response.sendRedirect("index.jsp");
   }
        %>
        <h2>Login</h2>
        <% if (attempt) {%>
        <p><font color="red">Invalid username or password</font></p>
        <% }%>
        <form action="index.jsp" method="POST">
            <p>Username: <input type="text" name="username" value="" size="30" /></p>
            <p>Password: <input type="password" name="password" value="" size="30" /></p>
            <p><input type="submit" value="Login" /> </p>
        </form>


        <%}%>
    </body>
</html>
