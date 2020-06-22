<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Bookshop Website</title>
</head>
<body>
<div style="text-align: center">
  <form action="/login" method="post">
    <label name="name">Name and surname:</label>
    <input name="name" size="40" />
    <br><br>
    <label name="password">Password:</label>
    <input type="password" name="password" size="30" />
    <br>${message}
    <br><br>
    <button type="submit">Login</button>
  </form>
</div>
</body>
</html>