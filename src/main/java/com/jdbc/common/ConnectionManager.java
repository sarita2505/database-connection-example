package com.jdbc.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
	private static String PASSWORD, DRIVERCLASS, USERNAME, URL;
	static {

		Properties props = JdbcPropertyFileReader.getDbProperties(DatabaseType.ORACLE);

		
		PASSWORD=props.getProperty("password");
		DRIVERCLASS=props.getProperty("driverclass");
		USERNAME=props.getProperty("username");
		URL=props.getProperty("url");
		
		
		try {
			Class.forName (DRIVERCLASS);//WHY..
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection conn=null;
		try {
			conn = DriverManager.getConnection
					  (URL, USERNAME, PASSWORD);
			conn.setAutoCommit(false);
			System.out.println(conn);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;

	}

	public static void main(String[] args) {
		getConnection();
	}
	public static void close(Connection conn) {
		
		if(conn!=null) {
			
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

