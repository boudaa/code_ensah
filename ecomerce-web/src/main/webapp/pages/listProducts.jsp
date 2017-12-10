<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjdt" uri="/struts-jquery-datatables-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring and Struts Integration Demo</title>
<sj:head jquerytheme="redmond" />

</head>
<body>
	<div align="center">
		<sjdt:datatables datatablesTheme="jqueryui"
			buttons="['colvis','pdf','excel','csv','print','copy']" dom="Blfrtip"
			lengthMenu="[5,10,15,20]" pageLength="15"
			columnDefs="[{targets:[4],render:$.fn.dataTable.render.number(',','.',2)}]"
			responsive="true" style="width:100%;">
			<caption class="ui-widget-header">Customers Examples</caption>
			<thead>
				<tr>
					<th>ID</th>
					<th>Nom du produit</th>
					<th>Prix</th>
				
				</tr>
			</thead>
			<tbody>
				<s:iterator value="listProduits">
					<tr>
						<td>${id}</td>
						<td>${nom}</td>
						<td>${prix}</td>
					</tr>
				</s:iterator>
			</tbody>
		</sjdt:datatables>

	</div>
</body>
</html>