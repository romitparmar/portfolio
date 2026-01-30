package servlets;

import utils.DBConnection;
import java.io.*;
import java.sql.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class OrderServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        Integer userId = (Integer) session.getAttribute("userId"); // user must be logged in
        List<Map<String, Object>> cart = (List<Map<String, Object>>) session.getAttribute("cart");

        if (userId == null) {
            response.sendRedirect("login.jsp?msg=Please login first!");
            return;
        }

        if (cart == null || cart.isEmpty()) {
            out.println("<h3>Your cart is empty!</h3>");
            out.println("<a href='products.jsp' class='btn btn-primary'>Continue Shopping</a>");
            return;
        }

        double totalAmount = 0;
        for (Map<String, Object> item : cart) {
            double price = (double) item.get("price");
            int quantity = (int) item.get("quantity");
            totalAmount += price * quantity;
        }

        try (Connection conn = DBConnection.getConnection()) {
            // Insert into orders table
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO orders(user_id, total_amount, order_date) VALUES (?, ?, NOW())",
                Statement.RETURN_GENERATED_KEYS
            );
            ps.setInt(1, userId);
            ps.setDouble(2, totalAmount);
            ps.executeUpdate();

            // Get generated order ID
            ResultSet rs = ps.getGeneratedKeys();
            int orderId = 0;
            if (rs.next()) {
                orderId = rs.getInt(1);
            }

            // Optional: Insert order items into a cart/order_items table if you have one
            // For simplicity, currently storing only total amount in orders table

            session.removeAttribute("cart"); // clear cart
            response.sendRedirect("order_success.jsp");

        } catch (Exception e) {
            e.printStackTrace(out);
            out.println("<h3>Error placing order!</h3>");
        }
    }
}
