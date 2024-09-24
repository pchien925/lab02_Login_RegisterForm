<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="col-md-9 col-sm-9">
		<h1>Servlet Multipart</h1>
		<div class="content-form-page"></div>

		<form method="post" action="MultiPartServletController"
			enctype="multipart/form-data">

			Choose a file: <input type="file" name="multiPartServlet" /> <input
				type="submit" value="Upload" />
		</form>
	</div>
	</div>

</body>
</html>