package notesApp;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class DeleteController extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		if(!LoginController.checkSession(req)) {
			res.sendRedirect("login");
		}else {
			DAO.deleteNote(req.getParameter("noteid"));
			res.sendRedirect("notes");
		}
	}
	
}
