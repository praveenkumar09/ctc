<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form:form modelAttribute="article" name="article" id="article">
<div class="form-group col-md-11" id="addressFreeTextField">
					<label>Address Free:</label>
					<form:textarea class="form-control" rows="5" id="addressFree"
						path="addressFree"/>
</div>
</form:form>