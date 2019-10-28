package com.jdbc.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JdbcPropertyFileReader {

	private static String getDbPropertyFileName(DatabaseType databaseType) {

		String filename = null;
		switch (databaseType) {
		case ORACLE:
			filename = "oracle.properties";
			break;

		case MYSQL:
			filename = "mysql.properties";
			break;
		}
		return filename;
	}
	
	public static Properties getDbProperties(DatabaseType databaseType) {
		String filename=getDbPropertyFileName(databaseType);
		InputStream is=null;
		is=JdbcPropertyFileReader.class.getClassLoader().getResourceAsStream(filename);
		Properties props=new Properties();
		try {
			props.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return props;
		
	}
}
