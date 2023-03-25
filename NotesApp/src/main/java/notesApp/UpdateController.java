package notesApp;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/update")
public class UpdateController extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		if(!LoginController.checkSession(req)) {
			res.sendRedirect("login");
		}else {
			String noteid=req.getParameter("noteID");
			String title=req.getParameter("title");
			String content=req.getParameter("content");
			System.out.println(noteid+" "+title+" "+content);
			DAO.updateNote(new Notes(noteid,title,content));
			res.sendRedirect("notes");
		}
	}

}
