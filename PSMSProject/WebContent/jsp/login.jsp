<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<! doctype html>
<html lang="en">
<head>
<title>LoginForm</title>
</head>
<body>
	<div class="mdl-Layout mdl-js-layout mdl-layout--fixed-header">
		<%@include file="menu.jsp"%>
		<main class="mdl-layout__content">
			<div class="page-content">
				<div class="mdl-grid center-items">
					<div class="mdl-cell mdl-cell--4-col">
						<div class="mdl-card mdl-shadow--6dp">
							<div
								class="mdl-card_title mdl-color--primary mdl-color-text--white">
								<h2 class="mdl-card_title-text">
									<c:if test="${ login!= null}">Login</c:if>
									<c:if test="${login == null}">Login</c:if>
								</h2>
							</div>
							<div class="mdl-card__supporting-text">
								<c:if test="${login != null}">
									<form name="myForm"
										action="/PSMSProject/UserController?op=login" method="post">
								</c:if>
								<c:if test="${login != null}">
									<input type="hidden" name="id"
										value="<c:out value='${login.id}' />" />
								</c:if>

								<div class="mdl-textfield mdl-js-textfield">
									<input class="mdl-textfield__input" type="text" name="username" placeholder="UserName"
										value="<c:out value='${login.username}' />" id="username" /> 
								</div>
								<div class="mdl-textfield mdl-js-textfield">
									<input class="mdl-textfield__input" type="password" name="password" placeholder="Password"
										value="<c:out value='${login.password}' />" id="password" />
                                      </div>
		       <input type="submit"
			  class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect"
			value="accedi">
			
		</form>
	
   </div>
   </div>
   </div>
   </div>
   </div>
	</main>
	</div>
</body>
</html>