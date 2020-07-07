package it.enaip.corso.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.enaip.corso.cruddao.DaoUser;
import it.enaip.corso.model.Stuff;
import it.enaip.corso.model.User;
import it.enaip.corso.model.User.Type;

/**
 * Servlet implementation class UserController
 */
@WebServlet(name = "UserController", urlPatterns = { "/UserController" })
public class UserController extends HttpServlet {
	private String op;
	private static final long serialVersionUID = 1L;
	private DaoUser UserDao = DaoUser.getInstance();
	private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		op = req.getParameter("op");
		try {
			switch(op) {
			case "new":
				showNewForm(req, resp);
				break;
			case "insert":
				insertUser(req, resp);
				break;
			case "delete":
				deleteUser(req, resp);
				break;
			case "edit":
				showEditForm(req, resp);
				break;
			case "update":
				updateUser(req, resp);
				break;
			default:
				listUser(req, resp);
				break;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "SQL Error", e);
		} catch (ParseException e) {
			LOGGER.log(Level.SEVERE, "Parse Error", e);
			e.printStackTrace();
		}
	}

	private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String surname = req.getParameter("surname");
		String birthDate = req.getParameter("birthDate");
		int age = Integer.parseInt(req.getParameter("age"));
		String type = req.getParameter("type");
		switch (type) {
		case "C":
			type = "CHILD";
			break;
		case "O":
			type = "OWNER";
			break;
		case "S":
			type = "SPOUSE";
			break;
		default:
			break;
		}
		User user = new User(id, name, surname, birthDate, age, Type.valueOf(type));
		UserDao.update(user);
		listUser(req,resp);		
	}

	private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
		String id = req.getParameter("id");
		Optional<User> existingUser = UserDao.find(id);
		RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/userForm.jsp");
		existingUser.ifPresent(s -> req.setAttribute("user", s));
		dispatcher.forward(req, resp);
	}

	private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		User user = new User(id);
		UserDao.delete(user);
		listUser(req,resp);		
	}

	private void insertUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException, ParseException {
		String name = req.getParameter("name");
		String surname = req.getParameter("surname");
		String birthDate = req.getParameter("birthDate");
        int age = Integer.parseInt(req.getParameter("age"));
		String type = String.valueOf(req.getParameter("type"));
		User user = new User(name, surname, birthDate, age, Type.valueOf(type));
		UserDao.save(user);
		listUser(req,resp);
	}

	private void listUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/userList.jsp");
		List<User> listUser = UserDao.findAll();
		req.setAttribute("listUser", listUser);
		dispatcher.forward(req, resp);
	}

	private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/userForm.jsp");
		dispatcher.forward(req, resp);		
	}
	
	

}
