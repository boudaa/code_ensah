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

	<h2>Gestion des étudiants</h2>
	<a href="addStudentForm"> ajouter étudiant</a>

	<div id="divdt">

		<d:table name="studentList" export="true" requestURI="/listStudent"
			pagesize="20">
			<d:column property="nom" title="Nom" sortable="true" />
			<d:column property="prenom" title="Prénom" sortable="true" />
			<d:column property="email" title="email" />

		</d:table>


	</div>
</body>
</html>