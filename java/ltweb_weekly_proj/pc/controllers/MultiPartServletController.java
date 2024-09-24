package ltweb_weekly_proj.pc.controllers;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.MultipartConfig;

import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.Part;

import java.io.File;

import java.io.FileNotFoundException;

import java.io.IOException;

import ltweb_weekly_proj.pc.utils.Constant;

@WebServlet(

		name = "MultiPartServlet",

		urlPatterns = { "/multiPartServlet" }

)

@MultipartConfig(fileSizeThreshold = 1024 * 1024,

		maxFileSize = 1024 * 1024 * 5,

		maxRequestSize = 1024 * 1024 * 5 * 5)

public class MultiPartServletController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private String getFileName(Part part) {

		for (String content : part.getHeader("content-disposition").split(";")) {

			if (content.trim().startsWith("filename"))

				return content.substring(content.indexOf("=") + 2, content.length() - 1);

		}

		return Constant.DEFAULT_FILENAME;

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/web/multiPartServlet.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uploadPath = File.separator + Constant.UPLOAD_DIRECTORY; // upload vào thư mục bất kỳ

		// String uploadPath = getServletContext().getRealPath("") + File.separator +
		// UPLOAD_DIRECTORY; //upload vào thư mục project

		File uploadDir = new File(uploadPath);

		if (!uploadDir.exists())

			uploadDir.mkdir();

		try {

			String fileName = "";

			for (Part part : request.getParts()) {

				fileName = getFileName(part);

				part.write(uploadPath + File.separator + fileName);

			}

			request.setAttribute("message", "File " + fileName + " has uploaded successfully!");

		} catch (FileNotFoundException fne) {

			request.setAttribute("message", "There was an error: " + fne.getMessage());

		}

		getServletContext().getRequestDispatcher("/views/web/result.jsp").forward(request, response);

	}

}
