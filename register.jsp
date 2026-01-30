<!DOCTYPE html>
<html>
<head><title>Register - ClashBoard</title></head>
<body>
<h2>Player Registration</h2>
<form action="RegisterServlet" method="post">
  Username: <input type="text" name="username" required><br>
  Email: <input type="email" name="email" required><br>
  Password: <input type="password" name="password" required><br>
  Townhall Level: <input type="number" name="townhall" required><br>
  Trophies: <input type="number" name="trophies" required><br>
  <input type="submit" value="Register">
</form>
<a href="login.jsp">Already have an account? Login</a>
</body>
</html>
