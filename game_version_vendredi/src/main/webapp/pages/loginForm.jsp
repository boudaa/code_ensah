
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="com.bo.Message"%>
<%@page import="java.util.List"%>
<%@page import="com.bo.GameState"%>
<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Signin Template for Bootstrap</title>

<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/style/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/style/signin.css"
	rel="stylesheet">
</head>

<body>

	<div class="container">

		<form class="form-signin"
			action="${pageContext.request.contextPath}/LoginServlet">
			<div>
				<a
					href="${pageContext.request.contextPath}/pages/formInscription.jsp">S'inscrire</a>
			</div>
			<h2 class="form-signin-heading">Connexion</h2>
			<label for="inputEmail" class="sr-only">Nom d'utilisateur</label> <input
				type="text" class="form-control" placeholder="Nom d'utilisateur"
				required autofocus name="login"> <label for="inputPassword"
				class="sr-only">Mot de passe</label> <input type="password"
				id="inputPassword" class="form-control" placeholder="Password" name="password"
				required>

			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
				in</button>
		</form>

		<div>


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
						<li style="color: red">${msg.text}</li>
					</c:if>



				</c:forEach>
			</ul>
		</div>
	</div>
	<!-- /container -->
</body>
</html>
