<!DOCTYPE html>
<html>
<head><title>Login - ClashBoard</title></head>
<body>
<h2>Login</h2>
<form action="LoginServlet" method="post">
  Username: <input type="text" name="username" required><br>
  Password: <input type="password" name="password" required><br>
  <input type="submit" value="Login">
</form>
<p style="color:red;"><%= request.getParameter("error") != null ? "Invalid credentials" : "" %></p>
<a href="register.jsp">Register a new account</a>
</body>
</html>
