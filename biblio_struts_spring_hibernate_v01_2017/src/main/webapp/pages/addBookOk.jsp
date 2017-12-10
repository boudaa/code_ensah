<%@taglib uri="/struts-tags" prefix="s"%>



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>test</title>
</head>
<body>
<jsp:include page="inc/menu.jsp" />

Le livre est ajouté avec succès


<s:property value="isbn"/> 
<s:property value="title"/> 


</body>
</html>