<jsp:include page="inc/header.jsp" flush="true" />
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="d"%>


<jsp:include page="inc/menu.jsp" flush="true" />



<div>
	<div >RECHERCHE D'UN LIVRE</div>


	<div>

		<div class="contact_tab">

				<s:form action="searchAction" theme="xhtml" method="POST">
					<s:textfield label="Entrer le titre du livre " name="searchedBook" />

					<s:submit value="Rechercher le livre" cssClass="btn btn-primary" />

				</s:form>

			</div>
	</div>
</div>

<jsp:include page="inc/footer.jsp" />

