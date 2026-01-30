package servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/DBTest")
public class DBTest extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clashboard","root","");
            resp.getWriter().println("✅ Connected to MySQL via XAMPP!"); 
            con.close();
        } catch (Exception e) {
            resp.getWriter().println("❌ Connection failed: " + e.getMessage());
        }
    }
}
