package com.jdbc.myexample;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.jdbc.common.ConnectionManager;

public class SimpleEx {
	public static void main(String[] args) throws IOException, SQLException {
		myEx();

	}

	private static void myEx() throws IOException, SQLException {
		String sql = "INSERT INTO EMP_TABLE(ID,NAME,AGE) VALUES(?,?,?)";
		int id = 1;
		String name = "aditya";
		int age = 30;
		Properties p = new Properties();
//		OutputStream fos=new FileOutputStream("data.properties");
//		p.setProperty("url", "jdbc:oracle:thin:@localhost:1521:xe");
//		p.setProperty("username", "system");
//		p.setProperty("password", "tiger");
//		p.store(fos, null);
		//System.out.println(new File(".").getAbsolutePath());
		InputStream is = SimpleEx.class.getClassLoader().getResourceAsStream("data.properties");
		
				//new FileInputStream("data.properties");
		
		p.load(is);
		String URL = p.getProperty("url");
		String USERNAME = p.getProperty("username");
		String PASSWORD = p.getProperty("password");
		String DRIVER_CLASS_NAME = p.getProperty("driverclass");
		
		try {
			Class.forName (DRIVER_CLASS_NAME);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
System.out.println(URL+USERNAME+PASSWORD);
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ps.setString(2, name);
		ps.setInt(3, age);
		try {
			int rowCount = ps.executeUpdate();
			System.out.println(rowCount);
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
			conn.rollback();
		} finally {
			ConnectionManager.close(conn);
		}
	}
}
