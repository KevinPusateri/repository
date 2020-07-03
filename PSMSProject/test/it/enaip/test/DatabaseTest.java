package it.enaip.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.enaip.corso.factory.DataSourceFactory;

public class DatabaseTest {
	Connection connection;

	@Before
	public void before() throws SQLException {
		connection = DataSourceFactory.getConnection();
	}

	@After
	public void after() {
		DataSourceFactory.closeConnection(connection);
	}

	@Test
	public void closeStatementShouldCloseStatement() throws SQLException {
		Statement statement = connection.createStatement();
		DataSourceFactory.closeStatement(statement);
		assertTrue(statement.isClosed());
	}

	@Test
	public void closeStatementWithNullShouldNotThrow() {
		DataSourceFactory.closeStatement(null);
	}

}
