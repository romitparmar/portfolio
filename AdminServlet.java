package servlets;

import utils.DBConnection;
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String priceStr = request.getParameter("price");
        String stockStr = request.getParameter("stock");
        String imagePath = request.getParameter("image_path");

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO products(name, category, price, stock, image_path) VALUES(?, ?, ?, ?, ?)"
            );
            ps.setString(1, name);
            ps.setString(2, category);
            ps.setDouble(3, Double.parseDouble(priceStr));
            ps.setInt(4, Integer.parseInt(stockStr));
            ps.setString(5, imagePath != null ? imagePath : "");
            ps.executeUpdate();

            response.sendRedirect("admin.jsp?msg=Product added successfully!");
        } catch (Exception e) {
            e.printStackTrace(out);
            out.println("<h3>Error adding product!</h3>");
        }
    }
}
