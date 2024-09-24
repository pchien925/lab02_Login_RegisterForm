<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
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
	<div class="container">
		<ul class="breadcrumb">
			<li><a href="home">Home</a></li>
			<li><a href="#">Pages</a></li>
			<li class="active">Login</li>
		</ul>
		<!-- BEGIN SIDEBAR & CONTENT -->
		<div class="row margin-bottom-40">
			<!-- BEGIN SIDEBAR -->
			<div class="sidebar col-md-3 col-sm-3">
				<ul class="list-group margin-bottom-25 sidebar-menu">
					<li class="list-group-item clearfix"><a href="#"><i
							class="fa fa-angle-right"></i> Login/Register</a></li>
					<li class="list-group-item clearfix"><a href="#"><i
							class="fa fa-angle-right"></i> Restore Password</a></li>
					<li class="list-group-item clearfix"><a href="#"><i
							class="fa fa-angle-right"></i> My account</a></li>
					<li class="list-group-item clearfix"><a href="#"><i
							class="fa fa-angle-right"></i> Address book</a></li>
					<li class="list-group-item clearfix"><a href="#"><i
							class="fa fa-angle-right"></i> Wish list</a></li>
					<li class="list-group-item clearfix"><a href="#"><i
							class="fa fa-angle-right"></i> Returns</a></li>
					<li class="list-group-item clearfix"><a href="#"><i
							class="fa fa-angle-right"></i> Newsletter</a></li>
				</ul>
			</div>
			<!-- END SIDEBAR -->

			<!-- BEGIN CONTENT -->
			<div class="col-md-9 col-sm-9">
				<h1>Login</h1>
				<div class="content-form-page">
					<div class="row">
						<div class="col-md-7 col-sm-7">
							<form action="login" method="post">
								<div class="form-group">
									<label for="Username" class="col-lg-4 control-label">Username
										<span class="require">*</span>
									</label>
									<div class="col-lg-8">
										<input type="text" class="form-control" id="email"
											name="username">
									</div>
								</div>
								<div class="form-group">
									<label for="password" class="col-lg-4 control-label">Password
										<span class="require">*</span>
									</label>
									<div class="col-lg-8">
										<input type="password" class="form-control" id="password"
											name="password">
									</div>
								</div>
								<div class="row">
									<div class="col-lg-8 col-md-offset-4 padding-left-0">
										<a href="forgotpassword">Forget Password?</a>
									</div>
								</div>
								<div class="row">
									<div class="form-check col-lg-8 col-md-offset-4 padding-left-0">
										<label class="form-check-label"> <input
											class="form-check-input" type="checkbox" name="remember">
											Remember me
										</label>
									</div>
								</div>
								<p class="text-center" style="color: red">${message}</p>
								<div class="row">
									<div
										class="col-lg-8 col-md-offset-4 padding-left-0 padding-top-20">
										<button type="submit" class="btn btn-primary">Login</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- END CONTENT -->
		</div>
		<!-- END SIDEBAR & CONTENT -->
	</div>
</body>
</html>