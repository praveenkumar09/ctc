<%@ include file="common/header.jspf"%>
<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet">
<body>
	<div class="row">
		<div id="imagesrc" class="col-xs-6">
			<div class="zoom">
			<img id="censofImg" src="${pageContext.request.contextPath}/images/CTC_1.0.jpg" alt="new">
				</div>
		</div>
		<div id="formsrc" class="col-xs-3 vcenter" style="">
			<form class="form-signin" action="login" method="POST">
			<h3 class="form-signin-heading" align="center"><font color="black">Registration Success.</font></h3>
			<br/><br/>
			<span>Your email has been verified. Please login to continue</span>				
				<a href="${pageContext.request.contextPath}/login">Login Here!.</a>
				<!-- <br /> <a href="/registration">Register User</a> <font color="#337ab7">/</font> <a
					href="/forgotPassword">Forgot Password?</a> -->
			</form>

		</div>
	</div>
	<%@ include file="common/footer.jspf"%>