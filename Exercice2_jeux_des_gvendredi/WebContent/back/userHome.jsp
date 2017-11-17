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


	<h1>Jeu dès</h1>


	<form
		action="<%=request.getServletContext().getContextPath()%>/back/GameServlet">
		<input type="submit" value="Envoyer" />
	</form>

	<%
		GameState gameState = (GameState) request.getSession().getAttribute("gameState");

		out.print(gameState);
	%>

</body>
</html>