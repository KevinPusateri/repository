<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Form</title>
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
										action="/PSMSProject/StuffController?op=update" method="post"
										onsubmit="return validateForm()">
								</c:if>
								<c:if test="${user == null}">
									<form name="myForm"
										action="/PSMSProject/StuffController?op=insertUser" method="post"
										onsubmit="return validateForm()">
								</c:if>
								<c:if test="${user != null}">
									<input type="hidden" name="id"
										value="<c:out value='${user.id}' />" />
								</c:if>

								<div class="mdl-textfield mdl-js-textfield">
									<input class="mdl-textfield__input" type="text" name="name"
										value="<c:out value='${user.name}' />" id="name" /> <label
										class="mdl-textfield__label" for="name">Name</label>
								</div>
								<div class="mdl-textfield mdl-js-textfield">
									<input class="mdl-textfield_input" type="text"
										name="surname" value="<c:out value='${user.surname}' />"
										id="surname" /> <label class="mdl-textfield__label"
										for="surname">Surname</label>
								</div>
								<div class="mdl-textfield mdl-js-textfield">
									<c:choose>
										<c:when test="${user != null }">
											<input class="mdl-textfield__input" type="text"
												name="birthdate" value="<c:out value='${user.birthDate}' />"
												id="birthdate" />
										</c:when>
										<c:otherwise>
											<input class="mdl-textfield__input" type="text" name="birthdate"
												value="<c:out value=''/>" id="birthdate" />
										</c:otherwise>
									</c:choose>
									<label class="mdl-textfield__label" for="birthdate">BirthDate</label>
								</div>
								<div class="mdl-textfield mdl-js-textfield">
									<input class="mdl-textfield__input" type="number" name="age"
										value="<c:out value='${user.age}' />" id="age" /> <label
										class="mdl-textfield__label" for="age">Age</label>
								</div>
								<div class="mdl-textfield mdl-js-textfield">
									<input class="mdl-textfield__input" type="number" name="id"
										value="<c:out value='${user.id}' />" id="id" /> <label
										class="mdl-textfield__label" for="id">Id</label>
								</div>
								<!-- Text input-->

								<label for="type">Choose a Type</label> <select name="type"
									id="type" form="userForm">
									<option value="c">CHILD</option>
									<option value="o">Owner</option>
									<option value="s">Spouse</option>
								</select> <input type="submit"
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
	<script type="text/javascript">
		function validateForm() {
			var x = document.forms["myForm"]["quantity"].value;
			if (x == "") {
				alert("Quantity mustbe filled out");
				return false;
			}
		}
	</script>
</body>
</html>