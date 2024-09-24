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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/user/profile.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		String fullname = request.getParameter("fullname");
		String phone = request.getParameter("phone");
		String images = request.getParameter("images");

		HttpSession session = request.getSession(false);
		if (session != null) {
			User userSession = (User) session.getAttribute("userAccount");

			userSession.setFullname(fullname);
			userSession.setPhone(phone);
			userSession.setImages(images);

			session.removeAttribute("userAccount");
			session.setAttribute("userAccount", userSession);
			service.update(userSession);
		}
		request.getRequestDispatcher("/views/user/profile.jsp").forward(request, response);
	}

}