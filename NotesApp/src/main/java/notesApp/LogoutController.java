package notesApp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutController extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		if(LoginController.checkSession(req)) {
			HttpSession session=req.getSession(false);
			session.invalidate();
			res.sendRedirect("login");
		}else {
			res.sendRedirect("notes");
		}
	}
}
