<%@ page import="java.util.*, jakarta.servlet.http.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpSession session = request.getSession();
    List<Map<String, Object>> cart = (List<Map<String, Object>>) session.getAttribute("cart");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Checkout</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container my-5">
    <h2 class="text-center mb-4">🛒 Checkout</h2>

    <%
        if (cart == null || cart.isEmpty()) {
    %>
        <div class="alert alert-warning text-center">Your cart is empty!</div>
        <div class="text-center">
            <a href="products.jsp" class="btn btn-primary">Continue Shopping</a>
        </div>
    <%
        } else {
            double total = 0;
    %>
    <table class="table table-striped table-bordered">
        <thead class="table-dark">
        <tr>
            <th>Product</th>
            <th>Price (₹)</th>
            <th>Quantity</th>
            <th>Subtotal (₹)</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Map<String, Object> item : cart) {
                String name = (String) item.get("name");
                double price = (double) item.get("price");
                int quantity = (int) item.get("quantity");
                double subtotal = price * quantity;
                total += subtotal;
        %>
        <tr>
            <td><%= name %></td>
            <td><%= price %></td>
            <td><%= quantity %></td>
            <td><%= subtotal %></td>
        </tr>
        <% } %>
        </tbody>
    </table>

    <div class="d-flex justify-content-between align-items-center mt-4">
        <h4>Total Amount: ₹<%= total %></h4>
        <div>
            <a href="cart.jsp" class="btn btn-secondary me-2">Back to Cart</a>
            <a href="OrderServlet" class="btn btn-success">Place Order ✅</a>
        </div>
    </div>
    <% } %>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
