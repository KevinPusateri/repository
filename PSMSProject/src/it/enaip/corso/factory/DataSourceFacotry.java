package it.enaip.corso.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import oracle.jdbc.pool.OracleDataSource;

public class DataSourceFacotry {

	private final DataSource daso;
	private static final Logger LOGGER = Logger.getLogger(DataSourceFacotry.class.getName());

	public DataSourceFacotry() {
		OracleDataSource daso = null;
		try {
			daso = new OracleDataSource();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//			String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("./resources/database.properties")).getPath();
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
		private static final DataSourceFacotry INSTANCE = new DataSourceFacotry();
	}
}