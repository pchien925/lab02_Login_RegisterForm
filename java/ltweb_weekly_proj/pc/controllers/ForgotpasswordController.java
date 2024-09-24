package ltweb_weekly_proj.pc.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ltweb_weekly_proj.pc.models.Email;
import ltweb_weekly_proj.pc.models.User;
import ltweb_weekly_proj.pc.services.IUserService;
import ltweb_weekly_proj.pc.services.Impl.UserServiceImpl;

import java.io.IOException;

@WebServlet(urlPatterns = "/forgotpassword")
public class ForgotpasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IUserService service = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/web/forgotpassword.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		String username = request.getParameter("username");
		String email = request.getParameter("email");

		User user = service.get(username);
		if (user != null)
			if (user.getEmail().equals(email) && user.getUsername().equals(username)) {
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
