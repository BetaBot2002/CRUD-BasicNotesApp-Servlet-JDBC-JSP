package notesApp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/notes")
public class NotesController extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		if(LoginController.checkSession(req)) {
			req.setAttribute("Notes", DAO.showNotes(LoginController.getUser(req)));
			req.getRequestDispatcher("notes.jsp").forward(req, res);
		}else {
			res.sendRedirect("login");
		}
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String noteID=req.getParameter("noteID");
		String title=req.getParameter("title");
		String content=req.getParameter("content");
		
		Notes note=new Notes(noteID,title,content);
		String Status="Not Inserted";
		if(DAO.insertNote(note,LoginController.getUser(req))) {
			Status="Inserted";
		}
		res.sendRedirect("notes?status="+Status);
	}

}
