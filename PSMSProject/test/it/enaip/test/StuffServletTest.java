package it.enaip.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import it.enaip.corso.cruddao.StuffDao;
import it.enaip.corso.factory.DataSourceFactory;
import it.enaip.corso.servlet.StuffController;

public class StuffServletTest extends Mockito {
	StuffController myServlet;

	@Mock
	StuffDao stuffdao;
	
	@Mock
	HttpServletRequest request;

	@Mock
	HttpServletResponse response;

	@Mock
	RequestDispatcher dispatcher;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testShowNewForm() throws IOException, ServletException {
		myServlet = new StuffController();
		when(request.getParameter("op")).thenReturn("new");
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(printWriter);
		when(request.getRequestDispatcher("jsp/stuffForm.jsp")).thenReturn(dispatcher);
		myServlet.doPost(request, response);
		verify(dispatcher).forward(request, response);
	}

	@Test
	public void testListStuff() throws IOException, ServletException {
		myServlet = new StuffController();
		when(request.getParameter("op")).thenReturn("list");
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(printWriter);
		when(request.getRequestDispatcher("jsp/stuffList.jsp")).thenReturn(dispatcher);
		myServlet.doPost(request, response);
		verify(dispatcher).forward(request, response);
	}

	@Test
	public void testInsertStuff() throws IOException, ServletException, SQLException {
		myServlet = new StuffController();
		when(request.getParameter("op")).thenReturn("insert");
		when(request.getParameter("name")).thenReturn("Kevin");
		when(request.getParameter("description")).thenReturn("Desc");
		when(request.getParameter("quantity")).thenReturn("10");
		when(request.getParameter("location")).thenReturn("l");
		
		
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(printWriter);
		when(request.getRequestDispatcher("jsp/stuffList.jsp")).thenReturn(dispatcher);
		myServlet.doPost(request, response);
		verify(dispatcher).forward(request, response);
		
		String sql = "DELETE FROM stuff WHERE  stuff_id = (SELECT Max(stuff_id) FROM stuff)";
		Connection conn = DataSourceFactory.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.executeUpdate();
		
	}
}
