<%@ include file="common/header.jspf"%>
<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet">
<body>
<div class="row">
		<div id="imagesrc" class="col-xs-6">
			<div class="zoom">
			<img id="censofImg" src="${pageContext.request.contextPath}/images/CTC.jpg" alt="new">
				</div>
		</div>
		<div id="formsrc" class="col-xs-3 vcenter" style="">
			<form class="form-signin" action="${pageContext.request.contextPath}/forgotPassword" method="POST">
				<h3 class="form-signin-heading" align="center"><font color="black">FORGOT PASSWORD</font></h3>
				<br/>
				 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<input type="text" id="email" class="form-control" name="email"
					placeholder="Email Address" required="required" autofocus/><br/>
				<button class="btn btn-lg btn-primary btn-block" type="submit">Provide Link Through Email</button>
				<br /> <a href="${pageContext.request.contextPath}/login">Back to Login</a>
			</form>

		</div>
	</div>
	<%@ include file="common/footer.jspf"%>