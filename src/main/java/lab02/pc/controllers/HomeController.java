package lab02.pc.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lab02.pc.dao.IUserDao;
import lab02.pc.dao.Impl.UserDaoImpl;
import lab02.pc.models.Email;
import lab02.pc.models.User;
import lab02.pc.services.IUserService;
import lab02.pc.services.Impl.UserServiceImpl;

import java.io.IOException;

/**
 * Servlet implementation class HomeController
 */
@WebServlet(urlPatterns = { "/home", "/login", "/register" })
public class HomeController extends HttpServlet {
	IUserDao userDAO = new UserDaoImpl();
	IUserService userService = new UserServiceImpl();

	private static final long serialVersionUID = 1L;

	public HomeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURL().toString();

		if (url.contains("register")) {
			getRegister(request, response);
		} else if (url.contains("login")) {
			getLogin(request, response);
		} else
			homePage(request, response);
	}

	private void homePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/web/home.jsp").forward(request, response);

	}

	private void getLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/web/login.jsp").forward(request, response);

	}

	private void getRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/web/register.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		if (url.contains("login")) {
			postLogin(request, response);
		} else if (url.contains("register")) {
			postLogin(request, response);
		}

	}

	protected void postRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");

		String alertMes = "";
		if (userService.checkExistEmail(email)) {
			alertMes = "Email đã tồn tại";
			request.setAttribute("message", alertMes);
			request.getRequestDispatcher("/views/web/register.jsp").forward(request, response);
			return;
		} else if (userService.checkExistPhone(email)) {
			alertMes = "SĐT đã tồn tại";
			request.setAttribute("message", alertMes);
			request.getRequestDispatcher("/views/web/register.jsp").forward(request, response);
			return;
		} else if (userService.checkExistUsername(email)) {
			alertMes = "Tên đăng nhập đã tồn tại";
			request.setAttribute("message", alertMes);
			request.getRequestDispatcher("/views/web/register.jsp").forward(request, response);
			return;
		}

		Email em = new Email();

		String code = em.getRandomCode();

		User user = new User(username, password, email, fullname, phone);

		boolean sendMail = em.sendMail(user);

		if (sendMail) {
			boolean register = userService.register(username, password, email, fullname, phone, 0, code);
			if (register) {
				request.getRequestDispatcher("/views/web/Verify.jsp").forward(request, response);
			} else {
				alertMes = "Lỗi đăng kí";
				request.setAttribute("message", alertMes);
				request.getRequestDispatcher("/views/web/register.jsp").forward(request, response);
				return;
			}

		}
	}

	protected void postLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		String alertMes = "";
		if (username.isEmpty() || password.isEmpty()) {
			alertMes = "Sai tài khoản hoặc mật khẩu";
			request.setAttribute("message", alertMes);

			request.getRequestDispatcher("/views/web/login.jsp").forward(request, response);
			return;
		}

		User user = userService.login(username, password);

		if (user != null) {
			request.getRequestDispatcher("/views/web/home.jsp").forward(request, response);
		} else {
			alertMes = "Sai tài khoản hoặc mật khẩu";
			request.setAttribute("message", alertMes);
			request.getRequestDispatcher("/views/web/login.jsp").forward(request, response);
		}
	}
}
