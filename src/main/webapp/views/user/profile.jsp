<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="vi">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>profile</title>
<link rel="stylesheet" href="style.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body>
	<div class="container rounded bg-white mt-5 mb-5">
		<div class="row">
			<div class="col-md-3 border-right">
				<div
					class="d-flex flex-column align-items-center text-center p-3 py-5">
					<img class="rounded-circle mt-5" width="150px"
						src="/resources/tkbnam3.jsp"><span class="font-weight-bold">Edogaru</span><span
						class="text-black-50">edogaru@mail.com.my</span><span> </span>
				</div>
			</div>
			<div class="col-md-5 border-right">
				<form action="profile" method="post">
					<div class="p-3 py-5">
						<div
							class="d-flex justify-content-between align-items-center mb-3">
							<h3 class="text-right">Profile Settings</h3>
						</div>
						<div class="row mt-2">
							<div class="col-md-12">
								<label class="labels">Name</label><input type="text"
									class="form-control"
									value="${sessionScope.userAccount.fullname}" name="fullname">
							</div>
						</div>
						<div class="row mt-3">
							<div class="col-md-12">
								<label class="labels">Mobile Number</label><input type="text"
									class="form-control" placeholder="enter phone number"
									value="${sessionScope.userAccount.phone}" name="phone">
							</div>
							<div class="col-md-12">
								<label class="labels">Email ID</label><input type="text"
									class="form-control" placeholder="enter email id"
									value="${sessionScope.userAccount.email}" name="email">
							</div>
						</div>
						<div class="my-5 text-center">
							<button class="btn btn-primary profile-button" type="submit">Update
								Profile</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>

</html>