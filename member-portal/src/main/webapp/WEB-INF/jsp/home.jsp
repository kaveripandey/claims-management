<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html class="no-js">
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
				<li class="c0" data-color="red" title="Default">Default</li>
				<li class="c1" data-color="blue" title="Red">Red</li>
				<li class="c2" data-color="green" title="Green">Green</li>
				<li class="c3" data-color="yellow" title="Blue">Blue</li>
			</ul>
		</div>
	</div>

	<section class="site-wrapper">
		<div class="pt-table">
			<div class="pt-tablecell page-services relative">
				<a class="page-close" href="login">Logout</a>
				<div class="container">
					<div class="row">
						<div class="col-xs-12 col-lg-offset-1 col-lg-10">
							<div class="page-title text-center">
								<a style="text-decoration: none;" href="home">
									<h2>
										Claims <span class="primary">Management</span> <span
											class="title-bg">Services</span>
									</h2>
								</a>
								<p>CDE Project</p>
							</div>

							<div class="hexagon-menu services clear"
								style="display: flex; justify-content: space-between;">
								<div class="service-hex">
									<a style="text-decoration: none;" href="showinputclaimstatus">

										<svg version="1.1" xmlns="http://www.w3.org/2000/svg"
											xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
											viewBox="0 0 372 424.5"
											style="enable-background: new 0 0 372 424.5;"
											xml:space="preserve">
                                            <g>
                                                <polygon class="st0"
												points="359.9,314.1 186.9,414 14,314.1 14,114.4 186.9,14.6 359.9,114.4" />
                                                <polygon class="st1"
												points="331.2,297.6 186.9,380.9 42.6,297.6 42.6,131 186.9,47.6 331.2,131" />
                                            </g>
                                        </svg>
										<div class="content">
											<div class="icon">
												<i class="et-line icon-mobile"></i>
											</div>
											<h4>
												View Claim <br> Status
											</h4>
										</div>
									</a>
								</div>
								<div class="service-hex">
									<a style="text-decoration: none;" href="showclaim"> <svg
											version="1.1" xmlns="http://www.w3.org/2000/svg"
											xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
											viewBox="0 0 372 424.5"
											style="enable-background: new 0 0 372 424.5;"
											xml:space="preserve">
                                            <g>
                                                <polygon class="st0"
												points="359.9,314.1 186.9,414 14,314.1 14,114.4 186.9,14.6 359.9,114.4" />
                                                <polygon class="st1"
												points="331.2,297.6 186.9,380.9 42.6,297.6 42.6,131 186.9,47.6 331.2,131" />
                                            </g>
                                        </svg>
										<div class="content">
											<div class="icon">
												<i class="et-line icon-profile-male"></i>
											</div>
											<h4>
												Submit <br> Claim
											</h4>
										</div>
									</a>
								</div>
								<div class="service-hex">
									<a style="text-decoration: none;" href="showinputbill"> <svg
											version="1.1" xmlns="http://www.w3.org/2000/svg"
											xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
											viewBox="0 0 372 424.5"
											style="enable-background: new 0 0 372 424.5;"
											xml:space="preserve">
                                            <g>
                                                <polygon class="st0"
												points="359.9,314.1 186.9,414 14,314.1 14,114.4 186.9,14.6 359.9,114.4" />
                                                <polygon class="st1"
												points="331.2,297.6 186.9,380.9 42.6,297.6 42.6,131 186.9,47.6 331.2,131" />
                                            </g>
                                        </svg>
										<div class="content">
											<div class="icon">
												<i class="et-line icon-heart"></i>
											</div>
											<h4>
												View Bill<br> Status
											</h4>
										</div>
									</a>
								</div>
							</div>

						</div>
						<!-- /.col-xs-12 -->

					</div>
					<!-- /.row -->
				</div>
				<!-- /.container -->

				<nav class="page-nav clear">
					<div class="container">
						<div class="copyright hidden-x" style="text-align: center;">Copyright
							&copy; 2022</div>
					</div>
					<!-- /.page-nav -->
				</nav>
				<!-- /.container -->

			</div>
			<!-- /.pt-tablecell -->
		</div>
		<!-- /.pt-table -->
	</section>
	<!-- /.site-wrapper -->

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