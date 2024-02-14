<%@page import="java.util.List" import="model.User"
	import="UserDaoImplementation.UserDaoImplementation"%>


<%
String sRol = (String) request.getSession().getAttribute("sRol");

if (request.getSession() == null || sRol == null || !sRol.equals("administrador")) {
	response.sendRedirect("index.jsp");

}

String usuario = (String) request.getSession().getAttribute("sUser");

UserDaoImplementation userDao = new UserDaoImplementation();

List<User> users = userDao.getUsers();
%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset UTF-8">
<title>Admin panel</title>
</head>

<body>

	<h1>Only for Admins</h1>

	<p>
		Wellcome:
		<%=usuario%></p>


	<h2>Add user</h2>
	<form action="UserManagement" method="post">
		<label for="user_name">User name:</label> <input type="text"
			name="user_name" id="user_name" required> <br>
		<br> <label for="user_rol">User role:</label> <input
			type="text" name="user_rol" id="user_rol" required> <br>
		<br> <label for="user_description">Description:</label> <input
			type="text" name="user_description" id="user_description" required>
		<br>
		<br> <label for="user_password">Password:</label> <input
			type="password" name="user_password" id="user_password" required>
		<br>
		<br> <input type="submit" name="action" value="create">
	</form>

	<h2>User list:</h2>


	<table>
		<thead>
			<tr>
				<th>User name</th>
				<th>User role</th>
				<th>Description</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (User user : users) {
			%>
			<tr>
				<td><%=user.getUser()%></td>
				<td><%=user.getRol()%></td>
				<td><%=user.getDescription()%></td>
				<td><a href="updateUser.jsp?user_name=<%=user.getUser()%>">Update</a></td>
				<td><form action="UserManagement" method="post">
						<input type="hidden" name="user_name" value="<%=user.getUser()%>">
						<input type="submit" name="action" value="delete">
					</form></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	<br>
	<p><a href="sLogin?conf=0">Logout</a></p>
</body>

</html>