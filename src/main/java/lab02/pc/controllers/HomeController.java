package lab02.pc.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lab02.pc.dao.IUserDao;
import lab02.pc.dao.Impl.UserDaoImpl;
import lab02.pc.services.IUserService;
import lab02.pc.services.Impl.UserServiceImpl;

import java.io.IOException;

/**
 * Servlet implementation class HomeController
 */
@WebServlet(urlPatterns = {"/home", "/login", "/register"})
public class HomeController extends HttpServlet {
	IUserDao userDAO = new UserDaoImpl();
	IUserService userService = new UserServiceImpl();
	
	private static final long serialVersionUID = 1L;
    
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		
		if (url.contains("register")){
			getRegister(request, response);
		}
		else
			if (url.contains("login")) {
				getLogin(request, response);
			}
			else
				homePage(request, response);
	}

	private void homePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/web/home.jsp").forward(request, response);
		
	}

	private void getLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/web/login.jsp").forward(request, response);
		
	}

	private void getRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/web/register.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
