<%@ page import="java.sql.*" %>
<%@ page import="utils.DBConnection" %>
<html><head><title>Clan Page</title></head><body>
<h2>My Clan</h2>
<% 
   Integer pid = (Integer) session.getAttribute("player_id"); 
   if (pid == null) { response.sendRedirect("login.jsp"); return; }
   Connection con = DBConnection.getConnection();
   PreparedStatement ps = con.prepareStatement("SELECT c.clan_id,c.clan_name,c.description,p.username,p.role FROM clans c JOIN players p ON c.leader_id = p.player_id WHERE c.clan_id = (SELECT clan_id FROM players WHERE player_id=?)");
   ps.setInt(1, pid);
   ResultSet rs = ps.executeQuery();
   if (rs.next()) {
%>
   <h3><%= rs.getString("clan_name") %></h3>
   <p><%= rs.getString("description") %></p>
   <p>Leader: <%= rs.getString("username") %> ( <%= rs.getString("role") %> )</p>
<% } else { %>
   <p>You are not in a clan yet.</p>
<% } %>
<a href="dashboard.jsp">Back to Dashboard</a>
</body></html>
