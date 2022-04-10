<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html class="no-js" lang="">
<head>
<meta charset="utf-8">
<title>Claims Management System</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" type="image/x-icon" href="favicon.ico">
<link
	href="https://fonts.googleapis.com/css?family=Libre+Baskerville:400,400i|Open+Sans:400,600,700,800"
	rel="stylesheet">
<link rel="stylesheet" href="css/themefisher-fonts.min.css">
<link rel="stylesheet" href="css/owl.carousel.min.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/dark.css">
<link id="color-changer" rel="stylesheet" href="css/colors/color-0.css">
</head>

<body class="dark">

	<div class="preloader">
		<div class="loading-mask"></div>
		<div class="loading-mask"></div>
		<div class="loading-mask"></div>
		<div class="loading-mask"></div>
		<div class="loading-mask"></div>
	</div>

	<div class="preview-wrapper" style="display: none;">
		<div class="switcher-head">
			<span>Style Switcher</span>
			<div class="switcher-trigger tf-tools"></div>
		</div>

		<div class="switcher-body">
			<h4>Choose Color:</h4>
			<ul class="color-options list-none">
				<li class="c0" data-color="color-0" title="Default">Default</li>
				<li class="c1" data-color="color-1" title="Red">Red</li>
				<li class="c2" data-color="color-2" title="Green">Green</li>
				<li class="c3" data-color="color-3" title="Blue">Blue</li>
			</ul>
		</div>
	</div>

	<main class="site-wrapper">
		<div class="pt-table">
			<div class="pt-tablecell page-contact relative">
				<a class="page-close" href="login">Logout</a>

				<div class="page-title text-center">
					<a style="text-decoration: none;" href="home">
						<h2>
							Claims <span class="primary">Management</span> <span
								class="title-bg">Services</span>
						</h2>
					</a>
				</div>

				<div class="container">
					<div class="row">
						<div
							class="col-xs-12 col-md-offset-1 col-md-10 col-lg-offset-2 col-lg-8">
							<div class="page-title text-center">
								<h3 style="font-size: 50px;">
									Submit <span class="primary">Claim Details</span>
								</h3>
							</div>
						</div>
					</div>
				</div>
				<div class="container">
					<form:form action="submitclaim" method="post"
						modelAttribute="claimDetails" class="row">
						<div class="col-md-4">
							<form:label path="claimId" for="claimId">Claim Id</form:label>
							<form:input path="claimId" class="form-control" id="claimId"
								value="${claimId}" />
						</div>
						<div class="col-md-4">
							<form:label path="status" for="status">Status</form:label>
							<form:input path="status" class="form-control" id="status"
								value="${status}" />
						</div>
						<div class="col-md-4">
							<form:label path="description" for="description">Description</form:label>
							<form:input path="description" class="form-control"
								id="description" value="${description}" />
						</div>
						<div class="col-md-4">
							<form:label path="remarks" for="remarks">Remarks</form:label>
							<form:input path="remarks" class="form-control" id="remarks"
								value="${remarks}" />
						</div>
						<div class="col-md-4">
							<form:label path="claimAmount" for="claimAmount">Claim Amount</form:label>
							<form:input path="claimAmount" class="form-control"
								id="claimAmount" value="${claimAmount}" />
						</div>
						<div class="col-md-4">
							<form:label path="hospitalId" for="hospitalId">Hospital Id</form:label>
							<form:input path="hospitalId" class="form-control"
								id="hospitalId" value="${hospitalId}" />
						</div>
						<div class="col-md-4">
							<form:label path="benefitId" for="benefitId">Benefit Id</form:label>
							<form:input path="benefitId" class="form-control" id="benefitId"
								value="${benefitId}" />
						</div>
						<div class="col-md-4">
							<form:label path="policyId" for="policyId">Policy Id</form:label>
							<form:input path="policyId" class="form-control" id="policyId"
								value="${policyId}" />
						</div>
						<div class="col-md-4">
							<form:label path="memberId" for="memberId">Member Id</form:label>
							<form:input path="memberId" class="form-control" id="memberId"
								value="${memberId}" />
						</div>

						<div class="col-md-4">
							<br>
							<button class="btn btn-primary btn-custom-border text-uppercase"
								type="submit">Submit</button>
						</div>
					</form:form>
				</div>

			</div>
		</div>

		<nav
			class="navbar fixed-bottom navbar-expand-lg navbar-dark default-color">
			<div class="container">
				<div class="copyright hidden-x"
					style="text-align: center; padding-top: 60px;">Copyright
					&copy; 2022</div>
			</div>
		</nav>
	</main>

	<script src="js/vendor/jquery-2.2.4.min.js"></script>
	<script src="js/vendor/bootstrap.min.js"></script>
	<script src="js/jquery.easing.min.js"></script>
	<script src="js/isotope.pkgd.min.js"></script>
	<script src="js/jquery.nicescroll.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/jquery-validation.min.js"></script>
	<script src="js/form.min.js"></script>
	<script src="js/main.js"></script>
</body>
</html>