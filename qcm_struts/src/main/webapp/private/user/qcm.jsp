<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="d"%>

<jsp:include page="../inc/header.jsp" />

<jsp:include page="../inc/menu.jsp" />

<div style="float: right">


	<div style="width: 1200px; margin-top: 50px">
		<h2>Réaliser un QCM</h2>

		<s:form namespace="/user" action="repondreQCM" method="POST"
			theme="simple">
	

			<s:iterator value="qcm.questions" status="key">
				<p>
					<s:text name="%{#key.index}+1" />
					-
					<s:property value="texte" />
					<br />
					<s:radio name="reponseName[%{#key.index}]"
						list="#{'1':'A','2':'B','3':'C'}" />
				</p>
               <hr/>
			</s:iterator>


			<s:submit value="Valider" />

		</s:form>

	</div>
</div>


<jsp:include page="../inc/footer.jsp" />
