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

import it.enaip.corso.cruddao.UserDao;
import it.enaip.corso.factory.DataSourceFactory;
import it.enaip.corso.model.User.Type;
import it.enaip.corso.servlet.UserController;

public class UserServletTest extends Mockito {
	UserController myServlet;

	@Mock
	UserDao userdao;

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
		myServlet = new UserController();
		when(request.getParameter("op")).thenReturn("new");
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(printWriter);
		when(request.getRequestDispatcher("jsp/userForm.jsp")).thenReturn(dispatcher);
		myServlet.doPost(request, response);
		verify(dispatcher).forward(request, response);
	}

	@Test
	public void testListUser() throws IOException, ServletException {
		myServlet = new UserController();
		when(request.getParameter("op")).thenReturn("list");
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(printWriter);
		when(request.getRequestDispatcher("jsp/userList.jsp")).thenReturn(dispatcher);
		myServlet.doPost(request, response);
		verify(dispatcher).forward(request, response);
	}

	@Test
	public void testInsertUser() throws IOException, ServletException, SQLException {
		myServlet = new UserController();
		when(request.getParameter("op")).thenReturn("insert");
		when(request.getParameter("name")).thenReturn("Jeff");
		when(request.getParameter("surname")).thenReturn("Desc");
		when(request.getParameter("birthDate")).thenReturn("1995-07-07");
		when(request.getParameter("age")).thenReturn("25");
		when(request.getParameter("type")).thenReturn(Type.CHILD.toString());

		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(printWriter);
		when(request.getRequestDispatcher("jsp/userList.jsp")).thenReturn(dispatcher);
		myServlet.doPost(request, response);
		verify(dispatcher).forward(request, response);

		String sql = "DELETE FROM users WHERE  id = (SELECT Max(id) FROM users)";
		Connection conn = DataSourceFactory.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.executeUpdate();
	}
	
}
