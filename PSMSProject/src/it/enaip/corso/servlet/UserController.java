package it.enaip.corso.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.security.auth.login.LoginException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import it.enaip.corso.cruddao.DaoLogin;
import it.enaip.corso.cruddao.DaoUser;
import it.enaip.corso.factory.DataSourceFactory;
import it.enaip.corso.model.Login;
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
	private DaoLogin LoginDao = DaoLogin.getInstance();
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
			switch (op) {
			case "signInsert":
				singInsert(req, resp);
				break;
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
			case "menu":
				menu(req, resp);
				break;
			case "showSignin":
				showSign(req, resp);
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
//		catch (LoginException e) {
//			LOGGER.log(Level.SEVERE, "Login Exception", e);
//			e.printStackTrace();
//		}
	}


	private void singInsert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException, SQLException {
		String name = req.getParameter("name");
		String surname = req.getParameter("surname");
		String birthDate = req.getParameter("birthDate");
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birthDate);
		int age = Integer.parseInt(req.getParameter("age"));
		String type = String.valueOf(req.getParameter("type"));
		User user = new User(name, surname, date, age, Type.valueOf(type));
		UserDao.save(user);
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		Login login = new Login(username,password);
		LoginDao.save(login);
		RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/menu.jsp");
		dispatcher.forward(req, resp);
	}

	private void showSign(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/signin.jsp");
		dispatcher.forward(req, resp);
	}

	private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException, ParseException {
		int id = Integer.parseInt(req.getParameter("1"));
		String name = req.getParameter("name");
		String surname = req.getParameter("surname");
		String birthDate = req.getParameter("birthDate");
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birthDate);
		int age = Integer.parseInt(req.getParameter("age"));
		String type = String.valueOf(req.getParameter("type"));

		User user = new User(id, name, surname, date, age, Type.valueOf(type));
		UserDao.update(user);
		listUser(req, resp);
	}

	private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		String id = req.getParameter("id");
		Optional<User> existingUser = UserDao.find(id);
		RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/userForm.jsp");
		existingUser.ifPresent(s -> req.setAttribute("user", s));
		dispatcher.forward(req, resp);
	}

	private void deleteUser(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		User user = new User(id);
		UserDao.delete(user);
		listUser(req, resp);
	}

	private void insertUser(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException, ParseException {
		String name = req.getParameter("name");
		String surname = req.getParameter("surname");
		String birthDate = req.getParameter("birthDate");
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birthDate);
		int age = Integer.parseInt(req.getParameter("age"));
		String type = String.valueOf(req.getParameter("type"));
		User user = new User(name, surname, date, age, Type.valueOf(type));
		UserDao.save(user);
		listUser(req, resp);
	}

	private void listUser(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/userList.jsp");
		List<User> listUser = UserDao.findAll();
		req.setAttribute("listUser", listUser);
		dispatcher.forward(req, resp);
	}

	private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/userForm.jsp");
		dispatcher.forward(req, resp);
	}
	
	private void menu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/menu.jsp");
		dispatcher.forward(req, resp);
	}

	public JSONObject getJson(String id) throws SQLException, JSONException {
		User user = new User();
		DaoUser dao = new DaoUser();
		user = dao.findUser(id);
		JSONObject jobj = user.getJsonObject();

		return jobj;
	}
	
	public List<JSONObject> getJsonArray() throws SQLException, JSONException {
		List<User> user = new ArrayList<User>();
		DaoUser dao = new DaoUser();
		user = dao.findAll();
		List<JSONObject> jobj = new ArrayList<JSONObject>();
		for (User u : user) {
			jobj.add(u.getJsonObject());
		}

		return jobj;
	}

//	private void showloginForm(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException, LoginException, SQLException {
//		   
//		     
//		     String sql="SELECT  id,username,password  FROM login WHERE id=1 ";
//		     String username= req.getParameter("username");
//		     String password=req.getParameter("password");
//		     Connection conn=DataSourceFactory.getConnection();
//		     PreparedStatement statement=conn.prepareStatement(sql);
//		     ResultSet result=statement.executeQuery();
//		     boolean find=false;
//		     while(result.next()) {
//		    	 if(username.equalsIgnoreCase(result.getString("username")) && password.equalsIgnoreCase(result.getString("password"))) {
//		    	 Cookie loginCookie= new Cookie("username",username);
//		    	 //setting cookie to expiry in 30 mins
//		    	 loginCookie.setMaxAge(30*60);
//		    	 resp.addCookie(loginCookie);
//		    	 find = true;
//		    	 RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/menu.jsp");
//			    	Login login = LoginDao.findUser(String.valueOf(result.getInt("id")));
//			 		req.setAttribute("login", login);
//			 		dispatcher.forward(req, resp);
//		    	 }
//			}
//		     if(!find) {
//		    	 RequestDispatcher dispatch=getServletContext().getRequestDispatcher("/index.jsp");
//		    	 PrintWriter out= resp.getWriter();
//		    	 out.println("<font color=red>Either user name or password is wrong.</font>");
//		    	 dispatch.include(req, resp);
//		     }else {
//		    	
//		     }
//		     
//		  
//	}

		
}
