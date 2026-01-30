package servlets;

import utils.DBConnection;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/CreateClan")
public class CreateClanServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String clanName = req.getParameter("clanName");
        String description = req.getParameter("description");
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("player_id") == null) {
            resp.sendRedirect("login.jsp"); return;
        }
        int leaderId = (int) session.getAttribute("player_id");

        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO clans(clan_name, description, leader_id) VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, clanName);
            ps.setString(2, description);
            ps.setInt(3, leaderId);
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                int clanId = keys.getInt(1);
                // update player's clan_id and role
                String up = "UPDATE players SET clan_id=?, role='Leader' WHERE player_id=?";
                PreparedStatement ps2 = con.prepareStatement(up);
                ps2.setInt(1, clanId);
                ps2.setInt(2, leaderId);
                ps2.executeUpdate();
            }
            resp.sendRedirect("clanPage.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("Error: " + e.getMessage());
        }
    }
}
