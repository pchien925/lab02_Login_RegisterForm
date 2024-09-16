<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
<title>Login</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container h-100">
		<div class="row justify-content-sm-center h-100">
			<div class="col-xxl-4 col-xl-5 col-lg-5 col-md-7 col-sm-9">
				<div class="text-center my-5">
					<img
						src="https://getbootstrap.com/docs/5.0/assets/brand/bootstrap-logo.svg"
						alt="logo" width="100">
				</div>
				<form action="/lab02/login" method="post">
					<div class="mb-3 mt-3">
						<label for="email" class="form-label">Username:</label> <input
							type="text" class="form-control" placeholder="Enter username"
							name="username">
					</div>
					<div class="mb-3">
						<label for="pwd" class="form-label">Password:</label> <input
							type="password" class="form-control" id="pwd"
							placeholder="Enter password" name="password">
					</div>
					<div class="form-check mb-3">
						<label class="form-check-label"> <input
							class="form-check-input" type="checkbox" name="remember">
							Remember me
						</label>
					</div>
					<p class="text-center" style="color: red">${message}</p>
					<button type="submit" class="btn btn-primary">Submit</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>