package notesApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAO {
	public static boolean insertNote(Notes note,String Username) {
		Connection connect=DBConnection.getConnection();
		try {
			PreparedStatement query=connect.prepareStatement("INSERT INTO NOTES VALUES(?,?,?)");
			query.setString(1, note.getNoteID());
			query.setString(2, note.getTitle());
			query.setString(3, note.getContent());
			int result=query.executeUpdate();
			if(result>0) {
				connectUserNote(Username, note.getNoteID());
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean authenticateUser(String username, String password) {
		Connection connect=DBConnection.getConnection();
		try {
			PreparedStatement query=connect.prepareStatement("SELECT password FROM users WHERE username=?");
			query.setString(1, username);
			ResultSet result=query.executeQuery();
			if(result.next()) {
				if(result.getString("password").equals(password)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean connectUserNote(String Username,String NoteId) {
		Connection connect=DBConnection.getConnection();
		try {
			PreparedStatement query=connect.prepareStatement("INSERT INTO usernotes VALUES(?,?)");
			query.setString(1, Username);
			query.setString(2, NoteId);
			int result=query.executeUpdate();
			if(result>0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static ArrayList<Notes> showNotes(String Username){
		ArrayList<Notes> Notes=new ArrayList<>();
		Connection connect=DBConnection.getConnection();
		try {
			PreparedStatement query=connect.prepareStatement("SELECT N.* FROM users U,usernotes UN,notes N WHERE U.username=UN.username and N.noteid=UN.noteid and U.username=?");
			query.setString(1, Username);
			ResultSet result=query.executeQuery();
			while(result.next()) {
				Notes.add(new Notes(result.getString(1),result.getString(2),result.getString(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Notes;
	}
	
	public static boolean addUser(User user) {
		Connection connect=DBConnection.getConnection();
		try {
			PreparedStatement query=connect.prepareStatement("INSERT INTO USERS VALUES(?,?,?)");
			query.setString(1, user.getUsername());
			query.setString(2, user.getName());
			query.setString(3, user.getPassword());
			int result=query.executeUpdate();
			if(result>0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean updateNote(Notes note) {
		Connection connect=DBConnection.getConnection();
		try {
			PreparedStatement query=connect.prepareStatement("UPDATE notes SET title=?,content=? WHERE noteid=?");
			query.setString(3, note.getNoteID());
			query.setString(1, note.getTitle());
			query.setString(2, note.getContent());
			int result=query.executeUpdate();
			if(result>0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean deleteNote(String noteid) {
		Connection connect=DBConnection.getConnection();
		try {
			PreparedStatement query=connect.prepareStatement("DELETE FROM notes WHERE noteid=?");
			query.setString(1, noteid);
			int result=query.executeUpdate();
			if(result>0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
