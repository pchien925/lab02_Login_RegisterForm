<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="forgotpassword" method="post">
		<div class="container">
			<div class="row">
				<div class="col">
					<c:if test="${not empty message}">
						<div class="alert alert-danger">
							<p class="text-center">${message}</p>
						</div>
					</c:if>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col">
					<c:if test="${not empty error}">
						<div class="alert alert-danger">
							<p class="text-center">${error}</p>
						</div>
					</c:if>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<input type="text" class="form-control"
					placeholder="Enter your username" name="username" />
			</div>
			<div class="col">
				<input type="text" class="form-control"
					placeholder="Enter your email" name="email" />
			</div>
			<div class="col">
				<input type="submit" value="xác nhận" />
			</div>
		</div>
	</form>
</body>
</html>