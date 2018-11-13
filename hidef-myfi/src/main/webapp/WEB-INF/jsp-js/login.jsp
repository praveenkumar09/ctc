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
			<span>${message}</span>
				<h3 class="form-signin-heading" align="center"><font color="black">Please Sign In.</font></h3>
				<br/>
				<input type="text" id="myCBCId" class="form-control" name="myCBCId"
					placeholder="CBC ID" required="required" autofocus/><br/>
				<!-- <input type="text" id="email" class="form-control" name="username"
					placeholder="Email Address" required="required" autofocus/><br/> --> <input
					type="password" id="password" class="form-control" name="password"
					placeholder="Password" required="required" /> <br />
					 <span>${error}</span>
					  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
				<br /> <a href="${pageContext.request.contextPath}/registration">Register User</a> <font color="#337ab7">/</font> <a
					href="${pageContext.request.contextPath}/forgotPassword">Forgot Password?</a>
			</form>

		</div>
	</div>
	<%@ include file="common/footer.jspf"%>