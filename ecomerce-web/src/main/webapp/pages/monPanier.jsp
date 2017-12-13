<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@ taglib uri="http://displaytag.sf.net" prefix="d"%>



<%@ taglib prefix="sjdt" uri="/struts-jquery-datatables-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring and Struts Integration Demo</title>
<sj:head jquerytheme="redmond" />
<link rel="stylesheet" type="text/css" media="screen,projection,print"
	href=<%out.print(request.getContextPath() + "/styles/displaytag.css");%> />

</head>
<body style="width: 60%; margin: auto">

	<h2>Mon Panier</h2>

	<div id="divdt">

		<d:table name="panier.lignePanierList" export="true"
			requestURI="/monPanier"
			decorator="com.decorators.PanierActionsDecorator" pagesize="20">
			<d:column property="produit.id" title="ID" />
			<d:column property="produit.nom" title="Nom produit" sortable="true" />
			<d:column property="quantite" title="quantite" />
			<d:column property="montant" title="montant" />

			<%--<d:column property="addToPanierLink" title="Action" media="html" /> --%>


		</d:table>

		<a href="validerPanier">Valider</a>
	</div>
</body>
</html>