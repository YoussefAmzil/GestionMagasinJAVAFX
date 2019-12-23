package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnection {

	String user="root";
	String dbname="magasinjava";
	String password="";
	String url="jdbc:mysql://localhost:3306/";
	 static DataConnection instance;
	 Connection connection;


	private DataConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection(url+dbname, user, password);
		} catch (ClassNotFoundException ex) {
			System.out.println("Database Connection Creation Failed : " + ex.getMessage());
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public static DataConnection getInstance() throws SQLException {
		if (instance == null) {
			instance = new DataConnection();
		} else if (instance.getConnection().isClosed()) {
			instance = new DataConnection();
		}

		return instance;
	}
}
