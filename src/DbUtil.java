package com.capgemini.bankapp.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.log4j.Logger;

public class DbUtil {
	private static String className = "com.mysql.jdbc.Driver";
	private static String dburl = "jdbc:mysql://localhost:3306/bankappdb";
	private static String username = "root";
	private static String password = "root";
	

	static Connection connection;
	static final Logger logger = Logger.getLogger(DbUtil.class);

	public static Connection getConnection() {
			
		
		try {
			Class.forName(className);
			if (connection == null) {
				connection = DriverManager.getConnection(dburl, username, password);
				connection.setAutoCommit(false);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Driver clas not found");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static void commit() {
		try {
			if (connection != null) {
				connection.commit();
			}
		} catch (SQLException e) {
			logger.error("SQLException", e);
		}
	}

	public static void rollback() {
		try {
			if (connection != null) {
				connection.rollback();
			}
		} catch (SQLException e) {
			logger.error("SQLException", e);
		}
	}

}