package com.jdbc.curd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jdbc.common.ConnectionManager;

public class EmployeeCrud {
	public static void main(String[] args) throws SQLException {
		//createEmployee();
		listEmployee();
		//deleteEmployee();
	}

	private static void deleteEmployee() throws SQLException {

		Integer id = null;
		String QUERY = null;
		String SELECT_QUERY = "DELETE FROM EMP_TABLE";
		String WHERE_CLAUSE = " WHERE ID=?";
		if (id == null) {
			QUERY = SELECT_QUERY;
		} else {
			QUERY = SELECT_QUERY + WHERE_CLAUSE;
		}

		Connection conn = ConnectionManager.getConnection();
		PreparedStatement ps = conn.prepareStatement(QUERY);
		if (id != null) {
			ps.setInt(1, id);
		}
		
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
	private static void listEmployee() throws SQLException {

		Integer id=1 ;
		String QUERY = null;
		String SELECT_QUERY = "SELECT ID,AGE,NAME FROM EMP_TABLE";
		String WHERE_CLAUSE = " WHERE ID=?";
		if (id == null) {
			QUERY = SELECT_QUERY;
		} else {
			QUERY = SELECT_QUERY + WHERE_CLAUSE;
		}

		Connection conn = ConnectionManager.getConnection();
		PreparedStatement ps = conn.prepareStatement(QUERY);
		 
		if (id != null) {
			System.out.println("comming inside if");
			ps.setInt(1, id);
		} 
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			
			String name=rs.getString("name");
			int idd =rs.getInt("id");
			int age=rs.getInt("age");
			System.out.println(idd+" "+age+" "+name);
			
		}

	}

	private static void createEmployee() throws SQLException {// write insert query
		// create employee
		// setvalue for employee
		// get connection

		// create prepared statement from connection by passing query.
		// set employee valued to prepared statement,
		// execute update for prepared statement
		// commit connection or transaction
		// rollback transaction on error
		String INSERT_QUERY = "INSERT INTO EMP_TABLE(ID,NAME,AGE) VALUES(?,?,?)";
		int id = 2;
		String name = "abcd";
		int age = 30;
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement ps = conn.prepareStatement(INSERT_QUERY);
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
