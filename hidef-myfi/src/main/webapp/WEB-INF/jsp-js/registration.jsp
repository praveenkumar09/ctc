<%@ include file="common/header.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<link href="${pageContext.request.contextPath}/css/register.css" rel="stylesheet">
<script type="text/javascript">

function getType(){
	var messageType = $('#messageType').val();
	if(messageType == 'CRS'){
		$('#cbcorcrs').show();
		/* $('#cbc').hide(); */
	}else if(messageType == 'CBC'){
		/* $('#crs').hide();
		$('#cbc').show(); */
		$('#cbcorcrs').show();
	}else{
		/* $('#crs').hide();
		$('#cbc').hide(); */
		$('#cbcorcrs').hide();
	}
}
</script>
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
					<font color="black">Register User.</font>
				</h3>
				<br />
<%--                 <span>${checkEmail}</span> --%>
                <br />
                 <br />
				<spring:bind path="username">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input type="text" id="name" class="form-control"
							path="username" placeholder="User Name" required="required"
							/>
							<form:errors path="username"></form:errors>
						<br />
					</div>
				</spring:bind>
				
				<spring:bind path="messageType">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<!-- <label for="">Message Type</label> --> 
						<form:select
							class="form-control" id="messageType" path ="messageType">
							<%-- <form:option value="0">Please Choose Message Type</form:option> --%>
								<c:forEach items="${messageType}"
									var="type">
									<form:option value="${type.msgType}">
								${type.msgType}
							</form:option>
								</c:forEach>
						</form:select>
					</div>
				</spring:bind>

				<spring:bind path="myCBCId">
					<div class="form-group ${status.error ? 'has-error' : ''}" id='cbcorcrs'>
						<form:input type="text" id="cbcId" class="form-control"
							path="myCBCId" placeholder="CBC OR CRS ID" required="required" />
							<form:errors path="myCBCId"></form:errors>
						<br />
					</div>
				</spring:bind>
				
				<%-- <spring:bind path="myCBCId">
				<div class="form-group ${status.error ? 'has-error' : ''}" id='crs' style="display: none">
						<form:input type="text" id="crsId" class="form-control"
							path="myCBCId" placeholder="CRS ID" required="required" />
							<form:errors path="myCBCId"></form:errors>
						<br/>
				</div>
				</spring:bind> --%>
				
			
				<spring:bind path="email">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input type="text" id="email" class="form-control"
							path="email" placeholder="Email Address" required="required"/>
							<form:errors path="email"></form:errors>
						<br />
					</div>
				</spring:bind>

				<spring:bind path="password">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input type="password" id="password" class="form-control"
							path="password" placeholder="Password" required="required" />
							<form:errors path="password"></form:errors>
						<br />
					</div>
				</spring:bind>

				<spring:bind path="passwordConfirm">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input type="password" id="passwordConfirm" class="form-control"
							path="passwordConfirm" placeholder="Re-confirm Password"
							required="required" />
							<form:errors path="passwordConfirm"></form:errors>
						<br />
					</div>
				</spring:bind>


				<button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
				<br />
				<a href="${pageContext.request.contextPath}/login">Back to Login</a>
			</form:form>

		</div>
	</div>
	<%@ include file="common/footer.jspf"%>