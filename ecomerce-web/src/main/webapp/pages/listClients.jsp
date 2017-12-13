<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>




<%@ taglib prefix="sjdt" uri="/struts-jquery-datatables-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring and Struts Integration Demo</title>
<sj:head jquerytheme="redmond" />

</head>
<body style="width: 60%; margin: auto">

<h2>Liste des produits</h2>
	<div>
		<script type="text/javascript" language="javascript"
			src="http://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js">
			
		</script>
		<script type="text/javascript" language="javascript"
			src="http://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/pdfmake.min.js">
			
		</script>
		<script type="text/javascript" language="javascript"
			src="http://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/vfs_fonts.js">
			
		</script>
		<sjdt:datatables datatablesTheme="jqueryui" buttons="true"
			dom="Blfrtip" lengthMenu="[5,10,15,20]" pageLength="15"
			responsive="true" style="width:100%;">
			<caption class="ui-widget-header">Liste des client</caption>
			<thead>
				<tr>
					<th>ID</th>
					<th>Nom</th>
					<th>Prénom</th>

				</tr>
			</thead>
			<tbody>
				<s:iterator value="listClients">
					<tr>
						<td>${id}</td>
						<td>${nom}</td>
						<td>${prenom}</td>
					</tr>
				</s:iterator>
			</tbody>
		</sjdt:datatables>

	</div>
</body>
</html>