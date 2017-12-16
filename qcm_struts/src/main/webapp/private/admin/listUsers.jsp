<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="d"%>

<jsp:include page="../inc/header.jsp" />

<jsp:include page="../inc/menuAdmin.jsp" />

<div style="float: right">

	<div id="divdt" style="width: 900px; margin-left: 200px">
		<h2>Liste des utilisateurs</h2>

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

		<d:table name="listUsers" export="true"
			requestURI="/private/admin/listUsers" pagesize="20">
			<d:column property="nom" title="nom" />
			<d:column property="prenom" title="prénom" sortable="true" />
			<d:column property="login" title="login" sortable="true" />

		</d:table>
	</div>
</div>


<jsp:include page="../inc/footer.jsp" />
