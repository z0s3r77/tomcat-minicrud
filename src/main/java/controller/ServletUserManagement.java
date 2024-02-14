package controller;

import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import UserDaoImplementation.UserDaoImplementation;

import model.User;

@WebServlet("/ServletUserManagement")
public class ServletUserManagement extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private UserDaoImplementation userDaoImplementation;
	
	public ServletUserManagement() {
		super();
		this.userDaoImplementation = new UserDaoImplementation();
	}
	
	
	protected  void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		
		String sRol = (String) request.getSession().getAttribute("sRol");

		if (request.getSession() == null || sRol == null || !sRol.equals("administrador")) {
			response.sendRedirect("index.jsp");
		}
		
		
		String action = request.getParameter("action");
		System.out.println("Llega al servlet.. action:" + action);
		
		try {
			switch (action) {
			case "create":
				createUser(request, response);
				break;
			case "delete":
				deleteUser(request, response);
				break;
			case "update":
				updateUser(request, response);
				break;
			default:
		        response.sendRedirect("error.jsp");

			}
		} catch (SQLException e) {

			e.printStackTrace();

	        response.sendRedirect("error.jsp");
		}
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		
		System.out.println("Actualizando usuario...");
		String userId = request.getParameter("user_id");
		String user = request.getParameter("user_name");
		String rol = request.getParameter("user_rol");
		String description = request.getParameter("user_description");
		String password = request.getParameter("user_password");

		User userBuild = new User();
		
		userBuild.setId(Integer.parseInt(userId));
		userBuild.setUser(user);
		userBuild.setRol(rol);
		userBuild.setDescription(description);
		userBuild.setPassword(password);
		
		userDaoImplementation.update(userBuild);
		response.sendRedirect(request.getContextPath() + "/administrador.jsp");

	}

	
	private void createUser(HttpServletRequest request, HttpServletResponse response)

			throws SQLException, IOException {
		
		System.out.println("Creando usuario...");

		String user = request.getParameter("user_name");
		String rol = request.getParameter("user_rol");
		String description = request.getParameter("user_description");
		String password = request.getParameter("user_password");

		User newUser = new User();
		newUser.setUser(user);
		newUser.setRol(rol);
		newUser.setDescription(description);
		newUser.setPassword(password);
		
		userDaoImplementation.add(newUser);

		response.sendRedirect(request.getContextPath() + "/administrador.jsp");
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		System.out.println("Eliminando usuario...");
		String user = request.getParameter("user_name");
		userDaoImplementation.delete(user);
	
		response.sendRedirect(request.getContextPath() + "/administrador.jsp");

	}
	
	
}