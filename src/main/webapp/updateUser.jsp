<%@page import="java.util.List" import="model.User"
	import="UserDaoImplementation.UserDaoImplementation"%>
<%
String sRol = (String) request.getSession().getAttribute("sRol");

if (request.getSession() == null || sRol == null || !sRol.equals("administrador")) {
	response.sendRedirect("index.jsp");

}

UserDaoImplementation userDao = new UserDaoImplementation();
User user = userDao.getUser(request.getParameter("user_name"));
%>
<html>

<h1>Update page</h1>
<form action="UserManagement" method="post">
	 <input type="hidden" name="user_id" value="<%=user.getId()%>">
    <label for="user_name">User name:</label>
    <input type="text" id="user_name" name="user_name" value="<%=user.getUser()%>" required>
    <br><br>
    <label for="user_rol">User role:</label>
    <input type="text" id="user_rol" name="user_rol" value="<%=user.getRol()%>" required>
    <br><br>
    <label for="user_description">Description:</label>
    <input type="text" id="user_description" name="user_description" value="<%=user.getDescription()%>" required>
    <br><br>
    <label for="user_password">Password:</label>
    <input type="text" id="user_password" name="user_password" value="<%=user.getPassword()%>" required>
    <br><br>
    <button type="submit" name="action" value="update">Update</button>
</form>
</html>