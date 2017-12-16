<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="d"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Application QCM</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">

<link rel='stylesheet prefetch'
	href='http://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900|RobotoDraft:400,100,300,500,700,900'>
<link rel='stylesheet prefetch'
	href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

	<!-- Mixins-->
	<!-- Pen Title-->
	<div class="pen-title">
		<h1>Bienvenue dans l'application QCM</h1>
		<span>Réalisée par <strong>Tarik BOUDAA</strong> dans les TPs
			de GI2 à <a href='http://ensah.ma'>ENSA Al hoceima</a></span>
	</div>

	<div class="container">
		<div class="card"></div>
		<div class="card">
			<h1 class="title">Authentification</h1>
			<s:form action="login" method="POST">

				<div class="type-text">
					<s:textfield label="login" name="userName" />
				</div>
				<div class="type-text">
					<s:textfield label="mot de passe" name="password" />
				</div>
				<sj:submit value="Connexion" />

			</s:form>
		</div>
	</div>

	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

	<script src="js/index.js"></script>

</body>
</html>
