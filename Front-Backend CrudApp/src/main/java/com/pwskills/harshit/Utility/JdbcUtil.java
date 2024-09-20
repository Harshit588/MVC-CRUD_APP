package com.pwskills.harshit.Utility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JdbcUtil {

	private static HikariDataSource dataSource = null;

	private JdbcUtil() {
	}

	static {
		String fileInfo = "D:\\Eclipse\\eclipse_Codes\\Servlet-Jsp\\Front-Backend CrudApp\\src\\main\\java\\com\\pwskills\\harshit\\Properties\\HikariCP.properties";

		try {

			HikariConfig config = new HikariConfig(fileInfo);
			dataSource = new HikariDataSource(config);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection GetDbConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public static void cleanUpResources(ResultSet resultSet, Statement statement, Connection connection) {
		// 5. Close the resources
		if (resultSet != null) {
			try {
				// Closing ResultSet
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (statement != null) {
			try {
				// Closing Statement
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				// Closing Connection
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
