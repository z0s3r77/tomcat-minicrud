<%@page import="java.util.List" import="model.User"
	import="UserDaoImplementation.UserDaoImplementation"%>


<%

UserDaoImplementation userDao = new UserDaoImplementation();
List<User> users = userDao.getUsers();
%>
<html>

<h1>JSP application</h1>
<form name="formulario login" method="post" action="sLogin">

	<table>

		<tr>
			<td colspan="3">ACCESO AL PANEL</td>
		</tr>
		<tr>
			<td width="82">&nbsp;</td>
			<td width="157">&nbsp;</td>
			<td width="212">&nbsp;</td>
		</tr>
		<tr>

			<td>Usuario:</td>
			<td><label> <input type="text" name="txtUsuario"
					id="txtusuario">
			</label></td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><label> <input type="password" name="txtPassword"
					id="txtpassword">
			</label></td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td height="48">&nbsp;</td>

			<td align="center"><label> <input type="submit"
					name="button" id="button" value="Acceder">
			</label></td>
		</tr>

	</table>
</form>

<table>
		<thead>
			<tr>
				<th>Nombre de usuario</th>
				<th>Rol de usuario</th>
				<th>Descripción</th>
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
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
</html>