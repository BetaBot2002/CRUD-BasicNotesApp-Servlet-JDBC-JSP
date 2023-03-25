package notesApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String DRIVERNAME="com.mysql.jdbc.Driver";
	private static final String DATABASE="notes";
	private static final String URL="jdbc:mysql://localhost:3306/"+DATABASE;
	
	private static final String USERNAME="root";
	private static final String PASSWORD="";
	
	public static Connection getConnection() {
		Connection connect=null;
		try {
			Class.forName(DRIVERNAME);
			connect=DriverManager.getConnection(URL,USERNAME,PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connect;
	}

}
