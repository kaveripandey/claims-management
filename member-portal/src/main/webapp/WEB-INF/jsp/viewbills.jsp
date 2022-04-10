<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html class="no-js" lang="">
<head>
<!-- meta charset -->
<meta charset="utf-8">
<!-- site title -->
<title>Claims Management System</title>
<!-- meta description -->
<meta name="description" content="">
<!-- mobile viwport meta -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- fevicon -->
<link rel="shortcut icon" type="image/x-icon" href="favicon.ico">

<!-- ================================
        CSS Files
        ================================= -->
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
				<!-- .close -->
				<a class="page-close" href="login">Logout</a>
				<!-- /.close -->

				<div class="page-title text-center">
					<a style="text-decoration: none;" href="home">
						<h2>
							Claims <span class="primary">Management</span> <span
								class="title-bg">Services</span>
						</h2>
					</a>
				</div>
				<div class="container">
					<table class="table table-hover table-dark" border=1>
						<thead>
							<tr>
								<th scope="col">Names</th>
								<th scope="col">Values</th>

							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Member Id</td>
								<td>${bills.memberId}</td>
							</tr>

							<tr>
								<td>Policy Id</td>
								<td>${bills.policyId}</td>
							</tr>

							<tr>
								<td>Last Paid Date</td>
								<td>${bills.lastPaidDate}</td>
							</tr>

							<tr>
								<td>Due Amount</td>
								<td>${bills.dueAmount}</td>
							</tr>

							<tr>
								<td>Late Charge</td>
								<td>${bills.lateCharge}</td>
							</tr>

							<tr>
								<td>Due Date</td>
								<td>${bills.dueDate}</td>
							</tr>

						</tbody>
					</table>
				</div>



			</div>
			<!-- /.col- -->
		</div>
		<!-- /.row -->

		<nav class="page-nav clear">
			<div class="container">
				<div class="copyright hidden-x" style="text-align: center;">Copyright
					&copy; 2022</div>
			</div>
		</nav>
	</main>
	<!-- /.site-wrapper -->

	<!-- ================================
        JavaScript Libraries
        ================================= -->
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
