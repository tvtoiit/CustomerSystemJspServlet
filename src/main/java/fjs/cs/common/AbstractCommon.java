package fjs.cs.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AbstractCommon {
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/customsystem";
			String user = "root";
			String pass = "";
			return DriverManager.getConnection(url, user, pass);
		} catch(ClassNotFoundException | SQLException e) {
			return null;
		}
	}
}
