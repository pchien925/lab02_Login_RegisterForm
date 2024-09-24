package ltweb_weekly_proj.pc.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ltweb_weekly_proj.pc.models.Email;
import ltweb_weekly_proj.pc.models.User;
import ltweb_weekly_proj.pc.services.IUserService;
import ltweb_weekly_proj.pc.services.Impl.UserServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;


@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IUserService service = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/web/register.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
		if (service.checkExistEmail(email)) {
			alertMes = "Email đã tồn tại";
			request.setAttribute("message", alertMes);
			request.getRequestDispatcher("/views/web/register.jsp").forward(request, response);
			return;
		} else if (service.checkExistPhone(email)) {
			alertMes = "SĐT đã tồn tại";
			request.setAttribute("message", alertMes);
			request.getRequestDispatcher("/views/web/register.jsp").forward(request, response);
			return;
		} else if (service.checkExistUsername(email)) {
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
				boolean isRegister = service.register(user.getUsername(), user.getPassword(), user.getEmail(),
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

}
