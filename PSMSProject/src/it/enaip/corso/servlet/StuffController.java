package it.enaip.corso.servlet;

import java.io.IOException;
import java.sql.SQLException;
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

import it.enaip.corso.cruddao.DaoStuff;
import it.enaip.corso.model.Stuff;
import it.enaip.corso.model.User;
import it.enaip.corso.model.User.Type;

@WebServlet(name = "StuffController", urlPatterns = { "/StuffController" })
public class StuffController extends HttpServlet {

	private String op;
	private static final long serialVersionUID = 1L;
	private DaoStuff StuffDao = DaoStuff.getInstance();
	private static final Logger LOGGER = Logger.getLogger(StuffController.class.getName());

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		op = req.getParameter("op");
		try {
			switch(op) {
			case "newUser":
				showFormUser(req, resp);
				break;
			case "insertUser":
				insertUser(req, resp);
				break;
			case "listUser":
				insertUser(req, resp);
				break;
			case "new":
				showNewForm(req, resp);
				break;
			case "insert":
				insertStuff(req, resp);
				break;
			case "delete":
				deleteStuff(req, resp);
				break;
			case "edit":
				showEditForm(req, resp);
				break;
			case "update":
				updateStuff(req, resp);
				break;
			default:
				listStuff(req, resp);
				break;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "SQL Error", e);
		}

	}

	private void showFormUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/userForm.jsp");
		dispatcher.forward(req, resp);
	}
	
	private void insertUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String surname = req.getParameter("surname");
		String birthDate = req.getParameter("birthDate");
		int age = Integer.parseInt(req.getParameter("age"));
		String type = req.getParameter("type");
		
		User user = new User(name, surname, birthDate, age, Type.valueOf(type));
//		StuffDao.save(user);
//		listUser(req,resp);
	}
	

	private void updateStuff(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		String location = req.getParameter("location");
		Stuff stuff = new Stuff(id, name, description, quantity, location);
		StuffDao.update(stuff);
		listStuff(req,resp);

	}

	private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		String id = req.getParameter("id");
		Optional<Stuff> existingStuff = StuffDao.find(id);
		RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/stuffForm.jsp");
		existingStuff.ifPresent(s -> req.setAttribute("stuff", s));
		dispatcher.forward(req, resp);
	}

	private void deleteStuff(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(req.getParameter("id"));
		Stuff stuff = new Stuff(id);
		StuffDao.delete(stuff);
		listStuff(req,resp);

	}

	private void showNewForm(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/stuffForm.jsp");
		dispatcher.forward(req, resp);

	}

	private void listStuff(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/stuffList.jsp");
		List<Stuff> listStuff = StuffDao.findAll();
		req.setAttribute("listStuff", listStuff);
		dispatcher.forward(req, resp);

	}

	private void insertStuff(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		String location = req.getParameter("location");
		Stuff stuff = new Stuff(name, description, quantity, location);
		StuffDao.save(stuff);
		listStuff(req,resp);

	}

}