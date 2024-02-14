package controller;

import java.io.IOException;
import java.io.PrintWriter;

import UserDaoImplementation.UserDaoImplementation;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;



@WebServlet(name="ServletLogin", urlPatterns=  {"/ServletLogin"})
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter()){
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet ServletLogin</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet ServletLogin at " + request.getContextPath() +"</h1>" );
			out.println("</body>");
			out.println("</html>");
		}
		
	}
	
	
    public ServletLogin() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		HttpSession sesion =  request.getSession();   
		int conf = Integer.parseInt(request.getParameter("conf"));
		if (conf == 0) {

			sesion.removeAttribute("sUser");
			sesion.removeAttribute("sRol");
			sesion.invalidate();
			response.sendRedirect("index.jsp");
		}
		
		
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		
		String usuario = request.getParameter("txtUsuario");
		String password = request.getParameter("txtPassword");
		

		UserDaoImplementation userDao = new UserDaoImplementation();

		
		User userFromDb = userDao.getUser(usuario, password);
		
	

	    if(userFromDb == null) {

	        response.sendRedirect("error.jsp");

	    } else {


	        if(userFromDb.getRol().equals("administrador")) {
	            sesion.setAttribute("sUser", userFromDb.getUser());
	            sesion.setAttribute("sRol", userFromDb.getRol());
	            
	            RequestDispatcher rd = request.getRequestDispatcher("administrador.jsp");
	            rd.forward(request, response);
	        }
	    }
	}

}
