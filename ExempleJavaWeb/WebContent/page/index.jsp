<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Java Web Examples</title>
</head>
<body>
	<%
		//On récupère le path du context de l'application dynamiquement
		String contextPath = request.getServletContext().getContextPath();
		// Le context peut etre récupéré également avec   ${pageContext.request.contextPath} 
		//La deuxième méthode est préférée
	%>

	<h1>Exemples de cours Java Web</h1>

	<h2>1- Session</h2>


	<p>
		Ajouter un objet dans la session (Appel de la servlet sessionExamples
		avec task=store )<a href="<%=contextPath%>/sessionExamples?task=store">(Executer)</a>
		<br />Récupérer un objet dans la session (appel de la servlet
		sessionExamples avec task=get ) <a
			href="${pageContext.request.contextPath}/sessionExamples?task=get">(Executer)</a>

	</p>

	<p>Ouvrir un autre navigateur et executer le deuxième lien.
		Expliquer le résultat obtenu</p>


	<h2>2- Application Context</h2>
	<p>
		Ajouter un objet dans le context (appel de la servlet contextExamples
		avec task=store )<a href="<%=contextPath%>/contextExamples?task=store">(Executer)</a>
		<br /> Récupérer un objet du context (appel de la servlet
		contextExamples avec task=get )<a
			href="${pageContext.request.contextPath}/contextExamples?task=get">(Executer)</a>

	</p>
	<p>Ouvrir un autre navigateur et executer le deuxième lien.
		Expliquer le résultat obtenu</p>


	<h2>3- Cookies</h2>
	<p>
		Ajouter un cookie dans la réponse (appel de la servlet cookiesExample
		avec task=store )<a href="<%=contextPath%>/cookiesExamples?task=store">(Executer)</a>
		<br /> Récupérer tous les cookies envoyés par l'utilisateur (appel de
		la servlet cookiesExample avec task=get )<a
			href="${pageContext.request.contextPath}/cookiesExamples?task=get">(Executer)</a>

	</p>

	<p>Ouvrir un autre navigateur et executer le deuxième lien.
		Expliquer le résultat obtenu</p>



</body>
</html>