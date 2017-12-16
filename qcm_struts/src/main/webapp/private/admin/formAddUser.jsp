<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="d"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<jsp:include page="../inc/header.jsp" flush="true" />

<jsp:include page="../inc/menuAdmin.jsp" />

<div>


	<div id="divdt" style="width: 900px; margin-left: 200px">
		<h2>Ajout d'un utilisateur</h2>

		<s:form action="addUser" method="POST">

			<s:textfield label="nom" name="utilisateur.nom" required="true" />
			<s:textfield label="prénom " name="utilisateur.prenom"
				required="true" />
			<s:textfield label="Login" name="utilisateur.login" required="true" />
			<s:password label="Mot de passe" name="utilisateur.password"
				required="true" />
			<s:textfield label="Email" name="utilisateur.email" required="true" />

			<s:select label="Selectionner un role" headerKey="-1" listKey="id"
				listValue="roleName" headerValue="--- Select ---" list="listRoles"
				name="selectedRole" />
			<s:submit value="Valider" cssClass="btn btn-primary" />

		</s:form>
	</div>
</div>
<jsp:include page="../inc/footer.jsp" />
