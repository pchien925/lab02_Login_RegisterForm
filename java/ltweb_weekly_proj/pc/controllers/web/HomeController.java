package ltweb_weekly_proj.pc.controllers.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ltweb_weekly_proj.pc.models.Email;
import ltweb_weekly_proj.pc.models.User;
import ltweb_weekly_proj.pc.services.IUserService;
import ltweb_weekly_proj.pc.services.Impl.UserServiceImpl;
import ltweb_weekly_proj.pc.utils.Constant;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.RepaintManager;

/**
 * Servlet implementation class HomeController
 */
@WebServlet(urlPatterns = { "/home", "/login", "/register", "/wating", "/verifyCode", "/logout", "/forgotpassword" })
public class HomeController extends HttpServlet {

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
		} else if (url.contains("waiting")) {
			getWaiting(request, response);
		} else if (url.contains("verifyCode")) {
			request.getRequestDispatcher("/views/web/verify.jsp").forward(request, response);
		} else if (url.contains("logout")) {
			getLogout(request, response);
		} else if (url.contains("forgotpassword")) {
			request.getRequestDispatcher("/views/web/forgotpassword.jsp").forward(request, response);
		} else
			homePage(request, response);
	}

	private void getLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("userAccount");

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (Constant.COOKIE_REMEMBER.equals("username")) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					break;
				}
			}
		}
		response.sendRedirect("./login");
	}

	private void getWaiting(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	private void homePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/web/home.jsp").forward(request, response);

	}

	private void getLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("userAccount") != null) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username")) {
					session = request.getSession(true);
					session.setAttribute("username", cookie.getValue());
					response.sendRedirect(request.getContextPath() + "/home");
					return;
				}
			}
		}
		request.getRequestDispatcher("views/web/login.jsp").forward(request, response);
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
			postRegister(request, response);
		} else if (url.contains("waiting")) {
			//
		} else if (url.contains("verifyCode")) {
			postVerifyCode(request, response);
		} else if (url.contains("forgotpassword")) {
			postForgotpassword(request, response);
		}

	}

	private void postForgotpassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		{
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");

			String username = request.getParameter("username");
			String email = request.getParameter("email");

			User user = userService.get(username);
			if (user == null && user.getEmail().equals(email) && user.getUsername().equals(username)) {
				Email em = new Email();
				boolean sendMail = em.getPassword(user);

				if (sendMail) {
					request.setAttribute("message", "Vui lòng kiểm tra mail để lấy lại mật khẩu");
				} else {
					request.setAttribute("error", "Lỗi gửi mail");
				}
			} else {
				request.setAttribute("error", "Tên tài khoản hoặc emal không chính xác");
				request.getRequestDispatcher("/views/web/forgotpassword.jsp").forward(request, response);
				return;
			}

			response.sendRedirect(request.getContextPath() + "/login");
		}
	}

	private void postVerifyCode(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		try (PrintWriter out = response.getWriter()) {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("userAccount");

			String code = request.getParameter("authcode");

			if (code.equals(user.getCode())) {
				user.setStatus(1);
				userService.updataStatus(user);
				out.println("<p> kích hoạt tài khoản thành công </p>");
			} else {
				out.println("<p>kích hoạt tài khoản không thành công </p>");
			}

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
		} else {

			Email em = new Email();

			String code = em.getRandomCode();

			LocalDate curDate = LocalDate.now();
			Date date = Date.valueOf(curDate);
			User user = new User(username, password, fullname, email, phone, 0, code, 1, date);

			boolean sendMail = em.sendMail(user);

			if (sendMail) {
				HttpSession session = request.getSession();
				session.setAttribute("userAccount", user);
				boolean isRegister = userService.register(user.getUsername(), user.getPassword(), user.getEmail(),
						user.getFullname(), user.getPhone(), user.getStatus(), user.getCode(), user.getRoleid(),
						user.getCreateDate());
				if (isRegister) {
					response.sendRedirect(request.getContextPath() + "/verifyCode");
				} else {
					alertMes = "Lỗi đăng kí";
					request.setAttribute("message", alertMes);
					request.getRequestDispatcher("/views/web/register.jsp").forward(request, response);
					return;
				}

			} else {
				PrintWriter out = response.getWriter();
				out.println("Lỗi gửi mail! Vui lòng kiểm tra lại!");
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
		boolean isRememberMe = false;
		String remember = request.getParameter("remember");
		if ("on".equals(remember)) {
			isRememberMe = true;
		}

		String alertMes = "";
		if (username.isEmpty() || password.isEmpty()) {
			alertMes = "Sai tài khoản hoặc mật khẩu";
			request.setAttribute("message", alertMes);

			request.getRequestDispatcher("/views/web/login.jsp").forward(request, response);
			return;
		}

		User user = userService.login(username, password);

		if (user != null) {
			if (user.getStatus() == 1) {
				HttpSession session = request.getSession(true);
				session.setAttribute("userAccount", user);
				if (isRememberMe) {
					SaveRememberMe(response, username);
				}

				request.getRequestDispatcher("/views/web/home.jsp").forward(request, response);
			} else {
				alertMes = "Tài khoản đã bị khóa";
				request.setAttribute("error", alertMes);
				request.getRequestDispatcher("/views/web/login.jsp").forward(request, response);
			}

		} else {
			alertMes = "Sai tài khoản hoặc mật khẩu";
			request.setAttribute("message", alertMes);
			request.getRequestDispatcher("/views/web/login.jsp").forward(request, response);
		}
	}

	private void SaveRememberMe(HttpServletResponse response, String username) {
		Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, username);
		cookie.setMaxAge(1800);
		cookie.setHttpOnly(true);
		cookie.setSecure(true);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

}
