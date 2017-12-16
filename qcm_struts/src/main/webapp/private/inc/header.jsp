<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="d"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%><%@ taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- load stylesheets -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,400">
<!-- Google web font "Open Sans" -->
<link rel="stylesheet"
	href="font-awesome-4.5.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/magnific-popup.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/templatemo-style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">


<title>QCM</title>
<style type="text/css">
.btn-link {
	border: none;
	outline: none;
	background: none;
	cursor: pointer;
	color: #0000EE;
	padding: 0;
	text-decoration: underline;
	font-family: inherit;
	font-size: inherit;
}
</style>
</head>
<body>

	<div style="margin-bottom: 170px; text-align: center">
		<div style="float: left">
			<p class="logo">
				<img src="<%=request.getContextPath()%>/img/qcm.jpg" />
			</p>
		</div>
		<div style="margin-top: 20px;">
			<p class="siteTitle ">
				Ecole Nationale des Sciences Appliquées d'Al-Hoceima <br /> <span
					class="normalText">Application QCM <br /> GI2 A.U.
					2016/2017
				</span>
			</p>
			<form action="${pageContext.request.contextPath}/logout"
				method="post">
			


				<button type="submit" name="Déconnexion" value="Déconnexion"
					class="btn-link">Déconnexion</button>

			</form>


		</div>
	</div>