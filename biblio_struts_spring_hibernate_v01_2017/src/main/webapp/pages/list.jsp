<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="d"%>

<jsp:include page="inc/header.jsp" />

<jsp:include page="inc/menu.jsp" />

<div>

	<h2>Liste des livres</h2>

	<div id="divdt" style="width:900px; margin-left:200px">


		<d:table name="livres" export="true" requestURI="/listAction"
			decorator="com.web.decorators.BookActionDecorator" pagesize="20">
			<d:column property="isbn" title="ISBN" />
			<d:column property="title" title="Titre" sortable="true" />
			<d:column property="description" title="Description" />
			<d:column property="nbOfPage" title="Nombre de pages" />
			<d:column property="theme.nom" title="Theme" />
			<d:column property="deleteBookLink" title="Action" media="html" />
			<d:column property="modifyBookLink" title="Action" media="html" />




		</d:table>
	</div>
</div>


<jsp:include page="inc/footer.jsp" />
