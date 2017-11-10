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


	<%
		List<Message> messages = (List<Message>) request.getSession().getAttribute("messages");

		if (messages != null) {
			for (Message it : messages) {
				out.print(it);
			}
		}
	%>

	<form
		action="<%=request.getServletContext().getContextPath()%>/LoginServlet">

		<label>Login</label> <input type="text" name="login"> <br />
		<label>Password</label> <input type="password" name="password">
		<br /> <input type="submit" value="Envoyer" />
	</form>



</body>
</html>