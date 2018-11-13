<%@ include file="common/header.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<link href="${pageContext.request.contextPath}/css/registerEmail.css" rel="stylesheet">
<body>
	<div class="row">
		<div id="imagesrc" class="col-xs-6">
			<div class="zoom">
				<img id="censofImg" src="${pageContext.request.contextPath}/images/CTC_1.0.jpg" alt="new">
			</div>
		</div>
		<div id="formsrc" class="col-xs-3 vcenter" style="">
			<form:form method="POST" modelAttribute="user"
				class="form-signin">
				<h3 class="form-signin-heading" align="center">
					<font color="black">Please verify Email.</font>
				</h3>
				<br />
                <span>${checkEmail}</span>
                <br />
                 <br />
				<a href="${pageContext.request.contextPath}/login">Back to Login</a>
			</form:form>

		</div>
	</div>
	<%@ include file="common/footer.jspf"%>