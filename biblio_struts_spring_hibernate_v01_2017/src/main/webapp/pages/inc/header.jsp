<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="d"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link rel="stylesheet" type="text/css" media="screen,projection,print"
	href=<%out.print(request.getContextPath() + "/styles/displaytag.css");%> />



<link rel="stylesheet" type="text/css" media="screen,projection,print"
	href=<%out.print(request.getContextPath() + "/styles/style.css");%> />





<sj:head jquerytheme="redmond" />

<style type="text/css">
.errors {
	background-color: #FFCCCC;
	border: 1px solid #CC0000;
	width: 500px;
	margin-bottom: 8px;
}

.errors li {
	list-style: none;
}

.msgok {
	background-color: #DDFFDD;
	border: 1px solid #009900;
	width: 500px;
}

.msgok li {
	list-style: none;
}



ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    background-color: #f1f1f1;
}

li a {
    display: block;
    color: #000;
    padding: 8px 8px;
    text-decoration: none;
}

li a.active {
    background-color: #4CAF50;
    color: white;
}

li a:hover:not(.active) {
    background-color: #555;
    color: white;
}

</style>

<title>Ajout d'un livre</title>

</head>
<body>

	<div style="margin-bottom:170px; text-align:center">
		<div style="float: left">
			<p class="logo">
				<img src="img/ensah.jpg" />
			</p>
		</div>
		<div style="margin-top:20px; ">
			<p class="siteTitle " >
				Ecole Nationale des Sciences Appliquées d'Al-Hoceima <br /> <span
					class="normalText">Développement des Applications J2EE <br /> 
					Struts 2, Spring et Hibernate <br />  GI2 A.U. 2016/2017</span>
			</p>
		</div>
	</div>