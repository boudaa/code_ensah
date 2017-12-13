<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib prefix="s" uri="/struts-tags"%>




<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>



<%@ taglib prefix="sjdt" uri="/struts-jquery-datatables-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring and Struts Integration Demo</title>
<sj:head jquerytheme="redmond" />

</head>
<body>
	<div align="center">
		<h1>Gestion des clients</h1>
		<h2>Ajout d'un client</h2>

		<s:form action="addClient" method="post">

			<div class="type-text">
				<s:textfield label="nom " name="client.nom" />
			</div>
			<div class="type-text">
				<s:textfield label="Prénom " name="client.prenom" />
			</div>
			<sj:submit value="Save" />

		</s:form>

	</div>
</body>
</html>