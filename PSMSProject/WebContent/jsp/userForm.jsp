<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<! doctype html>
<html lang="en">
<head>
<title>Form</title>
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
									<c:if test="${user != null}">Edit User</c:if>
									<c:if test="${user == null}">Add New User</c:if>
								</h2>
							</div>
							<div class="mdl-card__supporting-text">
								<c:if test="${user != null}">
									<form name="myForm"
										action="/PSMSProject/UserController?op=update" method="post">
								</c:if>
								<c:if test="${user == null}">
									<form name="myForm"
										action="/PSMSProject/UserController?op=insert" method="post">
								</c:if>
								<c:if test="${user != null}">
									<input type="hidden" name="id"
										value="<c:out value='${user.id}' />" />
								</c:if>

								<div class="mdl-textfield mdl-js-textfield">
									<input class="mdl-textfield__input" type="text" name="name" placeholder="Name"
										value="<c:out value='${user.name}' />" id="name" /> 
								</div>
								<div class="mdl-textfield mdl-js-textfield">
									<input class="mdl-textfield__input" type="text" name="surname" placeholder="Surname"
										value="<c:out value='${user.surname}' />" id="surname" />
										
								<div class="mdl-textfield mdl-js-textfield">
									<input class="mdl-textfield__input" type="date"
										name="birthDate"  placeholder="Date" value="<c:out value='${user.birthDate}' />"
										id="birthDate" /> 
								</div>

								<div class="mdl-textfield mdl-js-textfield">
									<input class="mdl-textfield__input" type="number" name="age" placeholder="Age"
										value="<c:out value='${user.age}' />" id="age" />
								</div>

								<!-- Text input-->
								<div class="mdl-textfield mdl-js-textfield">
									<label for="type">Choose a Type</label> <select name="type"
										id="type">
										<c:if test="${user != null}">
										<option value="<c:out value='${user.type}' />"><c:out value='${user.type}' /></option>
										</c:if>
										<option value="OWNER">OWNER</option>
										<option value="SPOUSE">SPOUSE</option>
									</select>
								</div>
								<input type="submit"
									class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect"
									value="save">
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