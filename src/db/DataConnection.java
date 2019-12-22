package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnection {

	Connection cnx=null;
	String user="root";
	String dbname="magasinjava";
	String password="";
	String url="jdbc:mysql://localhost:3306/";
	
	public DataConnection() {
		try {
			this.cnx=DriverManager.getConnection(this.url+this.dbname, this.user, this.password);
			//System.out.println("Connected !!!!");
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
	}
	
	public Connection getConnection() {
		return this.cnx;
	}
}
