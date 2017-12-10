
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="d"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<jsp:include page="inc/header.jsp" flush="true" />

<jsp:include page="inc/menu.jsp" />

    <div >
      <div >AJOUT  D'UN LIVRE</div>
	  
	  
	  <div>

		<div class="contact_tab" >
				<s:form action="addBook" method="POST">
					<s:textfield label="isbn" name="book.isbn" required="true" />
					<s:textfield label="titre " name="book.title" required="true" />
					<s:textfield label="Prix" name="book.price" />
					<s:textfield label="Description" name="book.description" />
					<s:textfield label="Nombre de pages " name="book.nbOfPage" />
					<s:textfield label="Email auteur " name="book.emailAuteur" />
					<sj:datepicker name="book.dateAchat" maxDate="-1d"
						displayFormat="dd/mm/y" label="Date Achat" />
					<sj:autocompleter id="languages" name="book.theme.nom"
						list="listThemes" label="thème" />

					<s:submit value="Valider" cssClass="btn btn-primary" />

				</s:form>
		</div>
	</div>
    <!-- end of column four -->
  </div>
  <jsp:include page="inc/footer.jsp" />
  