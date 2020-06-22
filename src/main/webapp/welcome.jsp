<%@ page language="java" %>
<html>
<body>
<p>Welcome ${sessionScope['username']}!</p>
<form action="/logout" method="post"/>
<input type="submit" value="Logout" />
</body>
</html>