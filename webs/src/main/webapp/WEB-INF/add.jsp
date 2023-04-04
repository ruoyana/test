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

	<script type="text/javascript" src="<%=request.getContextPath() %>/My97DatePicker/WdatePicker.js"></script>


</head>

<body>
	<form:form action="${pageContext.request.contextPath}/emp" method="POST" modelAttribute="as" enctype="multipart/form-data">
	
	 
	<br>
	<br>
	 
	 <c:if test="${as.id != 0}">
		<form:hidden path="id"/><br>
		<input type="hidden" name="_method" value="PUT">
		<br>
	</c:if>
	
	
	 <c:if test="${as.id == 0}">
			<fmt:message key="user.name"></fmt:message>:<form:input path="name"/><font color="red"><form:errors path="name"></form:errors></font><br>
			
	</c:if>
	
	
	 	gs:<form:select path="gs.sid" items="${yg}"  itemLabel="yuangong" itemValue="sid"></form:select><br>  
	
		head:<input type="file" name="fileHead"><br>
	
	
	
	
	
	
		<fmt:message key="user.addr"></fmt:message>: <form:input path="addr"/><br>
	  
		<fmt:message key="user.sex"></fmt:message>:<form:radiobuttons path="sex" items="${xingbei}"/><br>

		<fmt:message key="user.password"></fmt:message>:<form:input path="password"/><br><!-- itemLabel="yuangong" itemValue="id" -->

		<fmt:message key="user.birth"></fmt:message>:<form:input path="birth"
															   onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"></form:input>


		<br>
		<input type="submit">
	</form:form>
</body>
</html>