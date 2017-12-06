<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="com.bo.Message"%>
<%@page import="java.util.List"%>
<%@page import="com.bo.GameState"%>
<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Insert title here</title>
</head>
<body>


	<h1>Connexion</h1>

<!-- 	//afficher les message avec JAVA  -->
	
<!--  		List<Message> messages = (List<Message>) request.getAttribute("messages"); -->

<!--  		if (messages != null) { -->
<!-- 			for (Message it : messages) { -->
<!-- 			out.print(it); -->
<!--  			} -->
<!-- 		} -->



	<!-- Ou mieux on affiche les message avec JSTL -->
	<ul>

		<c:forEach items="${requestScope.messages}" var="msg">

			<c:if test="${msg.type == Message.WARN}">
				<li style="color:red">${msg.text}</li>
			</c:if>



		</c:forEach>
	</ul>

	<form action="${pageContext.request.contextPath}/LoginServlet">

		<label>Login</label> <input type="text" name="login"> <br />
		<label>Password</label> <input type="password" name="password">
		<br /> <input type="submit" value="Envoyer" />
	</form>



</body>
</html>