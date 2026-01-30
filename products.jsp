<%@ page import="java.sql.*, utils.DBConnection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Products</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container my-5">
    <h2 class="text-center mb-4">Available Products</h2>

    <div class="row">
        <%
            try (Connection conn = DBConnection.getConnection()) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM products");
                while(rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String category = rs.getString("category");
                    double price = rs.getDouble("price");
                    int stock = rs.getInt("stock");
                    String img = rs.getString("image_path");
        %>
        <div class="col-md-3 mb-4">
            <div class="card h-100">
                <% if(img != null && !img.isEmpty()) { %>
                    <img src="<%= img %>" class="card-img-top" alt="<%= name %>">
                <% } %>
                <div class="card-body">
                    <h5 class="card-title"><%= name %></h5>
                    <p class="card-text">Category: <%= category %></p>
                    <p class="card-text">Price: ₹<%= price %></p>
                    <p class="card-text">Stock: <%= stock %></p>
                    <form action="CartServlet" method="post">
                        <input type="hidden" name="id" value="<%= id %>">
                        <input type="hidden" name="name" value="<%= name %>">
                        <input type="hidden" name="price" value="<%= price %>">
                        <div class="input-group mb-2">
                            <input type="number" name="quantity" value="1" min="1" max="<%= stock %>" class="form-control">
                            <button class="btn btn-primary">Add to Cart</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <%      }
            } catch(Exception e) {
                out.println("<div class='alert alert-danger'>Error loading products!</div>");
            }
        %>
    </div>

    <div class="text-center mt-4">
        <a href="cart.jsp" class="btn btn-success btn-lg">View Cart 🛒</a>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
