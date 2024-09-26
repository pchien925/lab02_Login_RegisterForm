<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="col-md-9 col-sm-9">
		<h1>Cloudinary Upload Multipart</h1>
		<div class="content-form-page"></div>
		<c:if test="${sessionScope.userAccount != null}">
			<img alt="profile_img" src="${sessionScope.url_image}">
		</c:if>
		<form action="cloudinaryUpload" method="post"
			enctype="multipart/form-data">
			<label for="file">Choose an image file:</label> <input type="file"
				id="file" name="file"> <br> <br> <input
				type="submit" value="Upload">
		</form>
	</div>
</body>
</html>