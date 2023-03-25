package notesApp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class RegistrationController extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		if(!LoginController.checkSession(req)) {
		req.getRequestDispatcher("register.jsp").forward(req, res);
		}else {
			res.sendRedirect("notes");
		}
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		if(DAO.addUser(new User(req.getParameter("name"),req.getParameter("username"),req.getParameter("password")))) {
			res.sendRedirect("login");
		}else {
			String status="User: "+req.getParameter("username")+" Already Exists";
			res.sendRedirect("?status="+status);
		}
	}

}
