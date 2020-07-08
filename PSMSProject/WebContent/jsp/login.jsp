
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<! doctype html>
<html lang="en">
<head>
<title>LoginForm</title>
</head>
<body>
	<div class="mdl-card__supporting-text">
		<c:if test="${user != null}">
			<form name="myForm" action="/PSMSProject/UserController?op=login"
				method="post" onsubmit="return validateForm()">
		</c:if>


		<c:if test="${user!= null}">
			<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
		</c:if>

		<div class="mdl-textfield mdl-js-textfield">
			<input class="mdl-textfield__input" type="text" name="username"
				value="<c:out value='${user.username}' />" id="username" /> <label
				class="mdl-textfield__label" for="username">UserName</label>
		</div>
		<div class="mdl-textfield mdl-js-textfield">
			<input class="mdl-textfield_input" type="text" name="password"
				value="<c:out value='${user.password}' />" id="password" /> <label
				class="mdl-textfield__label" for="description">Password</label>
		</div>

		<input type="submit"
			class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect"
			value="save">
		</form>
	</div>

	</main>
</body>
</html>