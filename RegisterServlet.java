package servlets;

import utils.DBConnection;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        int townhall = Integer.parseInt(req.getParameter("townhall"));
        int trophies = Integer.parseInt(req.getParameter("trophies"));

        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO players(username,email,password,townhall_lvl,trophies,role) VALUES(?,?,?,?,?,'Member')";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setInt(4, townhall);
            ps.setInt(5, trophies);
            ps.executeUpdate();

            resp.sendRedirect("login.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("Error: " + e.getMessage());
        }
    }
}
