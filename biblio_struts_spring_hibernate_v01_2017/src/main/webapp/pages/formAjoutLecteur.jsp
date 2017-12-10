
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="d"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<jsp:include page="inc/header.jsp" flush="true" />

<jsp:include page="inc/menu.jsp" />

<div>
	<div>AJOUT D'UN LIVRE</div>


	<div>

		<div class="contact_tab">
			<s:form action="addLecteur" method="POST">
				<s:textfield label="nom" name="lecteur.nom" required="true" />
				<s:textfield label="prénom " name="lecteur.prenom" required="true" />
				<s:textfield label="email" name="lecteur.email" />


				<s:submit value="Valider" cssClass="btn btn-primary" />

			</s:form>
		</div>
	</div>
	<!-- end of column four -->
</div>
<jsp:include page="inc/footer.jsp" />
