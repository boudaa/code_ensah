<jsp:include page="inc/header.jsp" flush="true" />
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="d"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<jsp:include page="inc/menu.jsp" flush="true" />

      <div >MODIFICATION  D'UN LIVRE</div>


<div class="contact_tab">

	<div class="form_contact">

		<div style="margin-bottom: 20px">
			<s:if test="hasActionMessages()">
				<div class="msgok">
					<s:actionmessage />
				</div>
			</s:if>


			<s:if test="hasActionErrors()">
				<div class="errors">
					<s:actionerror />
				</div>
			</s:if>

		</div>


		<s:form action="updateBook" method="POST">


			<s:hidden name="book.id" />
			<s:textfield label="isbn" name="book.isbn" required="true" />
			<s:textfield label="titre " name="book.title" required="true" />
			<s:textfield label="Prix" name="book.price" />
			<s:textfield label="Description" name="book.description" />
			<s:textfield label="Nombre de pages " name="book.nbOfPage" />
			<s:textfield label="Email auteur " name="book.emailAuteur" />

			<s:select label="Selectionner un theme" headerKey="-1"
				headerValue="--- Select ---" list="listThemes" name="book.theme.nom" />

			<s:submit value="modifier le livre" cssClass="btn btn-primary" />

		</s:form>

	</div>
</div>

<jsp:include page="inc/footer.jsp" />

