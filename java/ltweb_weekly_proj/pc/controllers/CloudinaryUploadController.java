package ltweb_weekly_proj.pc.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import ltweb_weekly_proj.pc.configs.CloudinaryConfig;
import ltweb_weekly_proj.pc.models.User;

@WebServlet(urlPatterns = { "/cloudinaryUpload" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024,

		maxFileSize = 1024 * 1024 * 5,

		maxRequestSize = 1024 * 1024 * 5 * 5)

public class CloudinaryUploadController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final Cloudinary cloudinary;

	public CloudinaryUploadController() {
		// Khởi tạo CloudinaryConfig và lấy đối tượng Cloudinary
		CloudinaryConfig cloudinaryConfig = new CloudinaryConfig();
		this.cloudinary = cloudinaryConfig.cloudinary();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/web/multiPartServlet.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Part filePart = request.getPart("file"); // Lấy tệp từ request
		if (filePart != null) {
			try {
				File uploadedFile = convertPartToFile(filePart); // Tải lên tệp
				Map uploadResult = cloudinary.uploader().upload(uploadedFile, ObjectUtils.emptyMap());
				
				HttpSession session = request.getSession(true);
				session.setAttribute("url_image", (String) uploadResult.get("secure_url"));
				request.getRequestDispatcher("/views/user/profile.jsp").forward(request, response);
				// response.getWriter().write("Upload successful: " + (String)
				// uploadResult.get("secure_url"));
			} catch (Exception e) {
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
						"Image upload failed: " + e.getMessage());
				request.getRequestDispatcher("/views/user/profile.jsp").forward(request, response);

			}
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No file uploaded");
			request.getRequestDispatcher("/views/web/profile.jsp").forward(request, response);
		}
	}

	public File convertPartToFile(Part filePart) throws IOException {
		// Tạo tệp tạm thời
		File tempFile = File.createTempFile("upload_", ".tmp");

		try (InputStream inputStream = filePart.getInputStream();
				FileOutputStream outputStream = new FileOutputStream(tempFile)) {

			// Sao chép dữ liệu từ InputStream vào tệp
			byte[] buffer = new byte[1024];
			int bytesRead;

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
		}

		return tempFile; // Trả về tệp tạm thời
	}
}