<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
								<c:if test="${user != null}">Edit Stuff</c:if>
								<c:if test="${user == null}">Add New Stuff</c:if>
							</h2>
						</div>
						<div class="mdl-card__supporting-text">
							<c:if test="${user != null}">
								<form name="myForm" action="/PSMSProject/StuffController?op=update" method="post" onsubmit="return validateForm()">
							</c:if>
							<c:if test="${user == null}">
								<form name="myForm" action="/PSMSProject/StuffController?op=user" method="post" onsubmit="return validateForm()">
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
									name="description"
									value="<c:out value='${user.surname}' />" id="description" />
								<label class="mdl-textfield__label" for="description">Surname</label>
							</div>
							<div class="mdl-textfield mdl-js-textfield">
								<c:choose>
									<c:when test="${user != null }">
										<input class="mdl-textfield__input" type="text"
											name="birthDate" value="<c:out value='${user.birthDate}' />"
											id="birthDate" />
									</c:when>
									<c:otherwise>
										<input class="mdl-textfield__input" type="text"
											name="age" value="<c:out value='${user.birthDate}'/>" id="age" />
									</c:otherwise>
								</c:choose>
								<label class="mdl-textfield__label" for="quantity">Quantity</label>
							</div>
							<div class="mdl-textfield mdl-js-textfield">
								<input class="mdl-textfield__input" type="text" name="type"
									value="<c:out value='${user.type}' />" id="type" /> <label
									class="mdl-textfield__label" for="location">Location</label>
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
	<script type="text/javascript"> 
	function validateForm() {
		var x = document.forms["myForm"]["quantity"].value;
		if (x== "") {
			alert("Quantity mustbe filled out");
			return false;
		}
	}
</script>
</body>
</html>