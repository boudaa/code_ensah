<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="d"%>

<jsp:include page="../inc/header.jsp" />

<jsp:include page="../inc/menu.jsp" />

<div style="float: right">

	<h2>Score</h2>

	<div style="width: 1200px;">

		Votre score est :
		<s:property value="qcm.score" />

	</div>
</div>


<jsp:include page="../inc/footer.jsp" />
