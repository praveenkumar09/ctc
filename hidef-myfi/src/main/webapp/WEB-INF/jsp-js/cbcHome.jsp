<%@ include file="common/header.jspf"%>
<link type="text/css" href="/css/home.css" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="/css/jsgrid.min.css" />
<link type="text/css" rel="stylesheet" href="/css/jsgrid-theme.min.css" />
<script type="text/javascript" src="/js/jsgrid.min.js"></script>
<script src="/js/crsHome.js"></script>
<body>
	<%@ include file="common/navigation.jspf"%>
	<div id="cbcbreadcrumb" class="cbcbreadcrumb">
		<ul class="nav nav-tabs">
			<!-- <li id="userProfile" class="active"><a href="#" onclick="userProfileOnClick();">USER PROFILE</a></li> -->
			<li id="metadataBtn" class="active"><a href="#"
				onclick="metaDataOnClick();">METADATA</a></li>
			<li id="reportingFI"><a href="#" onclick="showReportingFI();">REPORTING
					ENTITY</a></li>
			<li id="accountHolder"><a href="#"
				onclick="showAccountHolder();">CBC REPORTS</a></li>
			<!-- <li id="config"><a href="#" onclick="showConfig();">CONFIGURATION</a></li> -->
		</ul>
		<div id="tabinfo"></div>
	</div>
	<div id="userProfile"></div>
	<div id="metaData"></div>
<%@ include file="common/crsname.jspf"%>
<%@ include file="common/addNewReportingFIAddress.jspf"%>
<%@ include file="common/addAccountHolderName.jspf"%>
</body>
<%@ include file="common/footer.jspf"%>