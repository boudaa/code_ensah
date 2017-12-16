<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="d"%>

<jsp:include page="../inc/header.jsp" />

<jsp:include page="../inc/menu.jsp" />

<div style="float: right">


	<div style="width: 1200px; margin-top: 50px">
		<h2>Meilleur Score</h2>

		Meilleur socre :
		<s:property value="bestQcm.score" />
		<br /> Réalisé par :
		<s:property value="bestQcm.utilisateur.nom" />
		<s:property value="bestQcm.utilisateur.prenom" /><br />
		Réalisé  le :<s:property value="bestQcm.dateRea" />

	</div>
</div>


<jsp:include page="../inc/footer.jsp" />
