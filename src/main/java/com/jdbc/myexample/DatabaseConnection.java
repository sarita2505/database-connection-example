package com.jdbc.myexample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
public static void main(String[] args) {
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("driver class is loaded");
		Connection connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
		System.out.println("connection is not established");
	} catch (ClassNotFoundException e) {
		System.out.println("driver class is not loaded");
	} catch (SQLException e) {
		System.out.println("connection is not established");
	}
}
}
