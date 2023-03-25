package notesApp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginController extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		if(!checkSession(req)) {
		req.getRequestDispatcher("login.jsp").forward(req, res);
		}else {
			res.sendRedirect("notes");
		}
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		if(DAO.authenticateUser(req.getParameter("username"), req.getParameter("password"))) {
			HttpSession session=req.getSession();
			session.setAttribute("user",req.getParameter("username") );
			res.sendRedirect("notes");
		}else {
			String status="Username/Password is(are) wrong";
			res.sendRedirect("login?status="+status);
		}
	}
	
	public static boolean checkSession(HttpServletRequest req) {
		HttpSession session=req.getSession(false);
		if(session==null) {
			return false;
		}
		String user=(String) session.getAttribute("user");
		if(user!=null) {
			return true;
		}
		return false;
	}
	
	public static String getUser(HttpServletRequest req) {
		HttpSession session=req.getSession(false);
		if(session!=null) {
			return (String) session.getAttribute("user");
		}
		return null;
	}

}
