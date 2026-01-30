<%@ page import="java.sql.*" %>
<%@ page import="utils.DBConnection" %>
<html><head><title>Dashboard</title></head><body>
<h2>Welcome, <%= session.getAttribute("username") != null ? session.getAttribute("username") : "Guest" %></h2>

<p><a href="createClan.jsp">Create Clan</a> | <a href="clanPage.jsp">My Clan Page</a> | <a href="leaderboard.jsp">Leaderboard</a></p>

</body></html>
