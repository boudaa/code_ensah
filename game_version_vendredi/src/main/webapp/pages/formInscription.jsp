<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

</head>
<body>
	<div class="container">

		<form method="post"
			action="${pageContext.request.contextPath}/InsertionServlet">
			<fieldset>
				<legend>Inscription</legend>
				<p>Entrer vos informations pour s'inscrire sur le site</p>


				<label for="nom">nom<span class="requis">*</span></label> <input
					type="text" id="nom" name="nom" value="" size="20" maxlength="60" /><br />



				<label for="prenom">prénom<span class="requis">*</span></label> <input
					type="text" id="prenom" name="prenom" value="" size="20"
					maxlength="60" /> <br /> <label for="motdepasse">Mot
					depasse <span class="requis">*</span>
				</label> <input type="password" id="motdepasse" name="password" value=""
					size="20" maxlength="20" /><br /> <label for="nom">Nom
					d'utilisateur</label> <input type="text" id="nom" name=login value=""
					size="20" maxlength="20" /> <br /> <input type="submit"
					value="Inscription" class="btn-primary" /> <br />

			</fieldset>
		</form>
	</div>
</body>

</html>