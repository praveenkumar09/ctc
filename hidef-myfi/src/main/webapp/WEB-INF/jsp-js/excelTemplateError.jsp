<%@ include file="common/header.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link type="text/css"
	href="${pageContext.request.contextPath}/css/panel-border.css"
	rel="stylesheet">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<link type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrapDatePicker.css"
	rel="stylesheet">
<link type="text/css"
	href="${pageContext.request.contextPath}/css/home.css" rel="stylesheet">
<link type="text/css"
	href="${pageContext.request.contextPath}/css/fieldset.css"
	rel="stylesheet">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/jsgrid.min.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/jsgrid-theme.min.css" />
	
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jsgrid.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap-datepicker.js"></script>

<script src="${pageContext.request.contextPath}/js/home.js"></script>
<%-- <script src="${pageContext.request.contextPath}/js/summary.js"></script> --%>
<body>
	<%@ include file="common/errorNavigation.jspf"%>
	<br/>
	<br/>
		<div class="container">
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading">
				<div class="text-center">
					<strong>IMPORT EXCEL ERROR</strong>
					</div>
				</div>
				<div class="panel-body">
						<br/>
						<br/>
							<p>There was an error during import, please kindly re-check your excel file for incorrect data input and try re-import again.</p>
						<br/>
						<div class="clearfix"></div>
						<br />
						<div class="text-center">
							<a id='backToSumaryButton' href="../home">Back To CBC Summary</a>
						</div>
				</div>
				</div>
			</div>
		</div>