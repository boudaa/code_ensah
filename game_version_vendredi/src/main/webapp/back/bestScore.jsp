<%@page import="com.bo.Utilisateur"%>
<%@page import="java.util.List"%>
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

	<%
		List<Utilisateur> users = (List<Utilisateur>) request.getAttribute("users");

		for (Utilisateur it : users) {

			out.print(it.getNom() + " | " + it.getBestScore());
			out.print("<br/>");
		}
	%>

</body>
</html>