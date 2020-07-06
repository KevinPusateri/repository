package it.enaip.corso.factory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import oracle.jdbc.pool.OracleDataSource;

public class DataSourceFactory {

	private final DataSource daso;
	private static final Logger LOGGER = Logger.getLogger(DataSourceFactory.class.getName());

	public DataSourceFactory() {
		OracleDataSource daso = null;
		try {
			daso = new OracleDataSource();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		InputStream input = null;

		try {
			Properties prop = new Properties();
			String propFileName = "database.properties";
			input = getClass().getClassLoader().getResourceAsStream(propFileName);
			prop.load(input);

			try {
				Class.forName(prop.getProperty("driverclass"));
				daso.setURL(prop.getProperty("url"));
				daso.setUser(prop.getProperty("user"));
				daso.setPassword(prop.getProperty("password"));

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			LOGGER.log(Level.SEVERE, "File database.properties Not Found", e);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "IO Error", e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					LOGGER.log(Level.SEVERE, "Failed to close streams", e);
				}
			}
		}
		this.daso = daso;
	}

	public static Connection getConnection() throws SQLException {
		return SingletonHelper.INSTANCE.daso.getConnection();
	}

	private static class SingletonHelper {
		private static final DataSourceFactory INSTANCE = new DataSourceFactory();
	}

	public static void closeConnection(Connection conn) {
		try {
			if (null != conn) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public static void closeResultset(ResultSet rs) {
		try {
			if (null != rs) {
				rs.close();
				rs = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public static void closePreparedStatement(PreparedStatement pstmt) {
		try {
			if (null != pstmt) {
				pstmt.close();
				pstmt = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public static void closeStatement(Statement stmt) {
		try {
			if (null != stmt) {
				stmt.close();
				stmt = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
}