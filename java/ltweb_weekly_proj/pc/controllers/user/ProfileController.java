package ltweb_weekly_proj.pc.controllers.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ltweb_weekly_proj.pc.models.User;
import ltweb_weekly_proj.pc.services.IUserService;
import ltweb_weekly_proj.pc.services.Impl.UserServiceImpl;

@WebServlet(urlPatterns = { "/user/profile" })
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IUserService service = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/user/profile.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		String fullname = request.getParameter("fullname");
		String phone = request.getParameter("phone");
		HttpSession session = request.getSession(false);
		try {
			if (session.getAttribute("userAccount") == null) {
				request.getRequestDispatcher("/views/web/login.jsp").forward(request, response);
			}
			User user = (User) session.getAttribute("userAccount");
			user.setFullname(fullname);
			user.setPhone(phone);
			user.setImages((String) session.getAttribute("url_image"));
			if (service.updateProfile(user)) {
				session.removeAttribute("userAccount");
				session.setAttribute("userAccount", user);
			} else {
				String alertMes = "Cập nhật thất bại";
				request.setAttribute("error", alertMes);
			}
			request.getRequestDispatcher("/views/user/profile.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}