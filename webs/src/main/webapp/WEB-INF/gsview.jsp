<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.util.Map" %>
    <%@page import="java.util.HashMap" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="<%=request.getContextPath() %>/scripts/jquery-3.6.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/My97DatePicker/WdatePicker.js"></script> <!-- 日期引用 -->
<script type="text/javascript" src="<%=request.getContextPath() %>/validate/dist/jquery.validate.min.js"></script> <!--    验证引用 -->
<script type="text/javascript" src="<%=request.getContextPath() %>/validate/dist/localization/messages_zh.min.js"></script> <!--    验证引用 -->
 
<body>
	<form:form action="${pageContext.request.contextPath}/gs" method="POST" modelAttribute="cgs" >
	
	 
	<br>
	<br>
	 
	 <c:if test="${cgs.sid != 0}">
		<form:hidden path="sid"/><br>
		<input type="hidden" name="_method" value="PUT">
		<br>
	</c:if>

	 sname:<form:input path="yuangong"/>
			

		<br>
		<input type="submit">
	</form:form>
</body>
</html>