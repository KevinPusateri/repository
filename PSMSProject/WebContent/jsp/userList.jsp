<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
<script>
$(document).ready(function(){
  $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#tab tbody tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});

</script>
<link href="css/search.css" rel="stylesheet">
<link href="css/view.css" rel="stylesheet">
<title>List</title></head>
<body>
	<!-- Always shows a header, even in smaller screens. -->
	<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
		<%@include file="menu.jsp"%>
		
		<main class="md1-layout_content">
		<div class="page-content">
<div class="wrapper">
    <div class="search_box">
        <div class="search_field">
          <input  id="myInput" type="text" class="input" placeholder="Search..">
          <i class="fas fa-search"></i>
      </div>
    </div>
</div>
<br>
			<div class="mdl-grid center-items">
				<div class="mdl-cell mdl-cell--4-col">
						<table
							class="mdl-data-tablemio mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp" id="tab">
							<thead>
								<tr>
									<th class="mdl-data-table_cell--non-numeric">NO</th>
									<th>Name</th>
									<th>Surname</th>
									<th>BirthDate</th>
									<th>Age</th>
									<th>Type</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="count" value="0" scope="page" />
								<c:forEach var="user" items="${listUser}">
									<c:set var="count" value="${count + 1}" scope="page" />
									<tr>
										<td><c:out value="${user.id}" /></td>
										<td><c:out value="${user.name}" /></td>
										<td><c:out value="${user.surname}" /></td>
										<td><c:out value="${user.birthDate}" /></td>
										<td><c:out value="${user.age}" /></td>
										<td><c:out value="${user.type.descType}" /></td>
										<td><a href="/PSMSProject/UserController?op=edit&id=<c:out value='${user.id}' />"class="edit">Edit</a>
											&nbsp;&nbsp;&nbsp;&nbsp;
											<a href="/PSMSProject/UserController?op=delete&id=<c:out value='${user.id}' />" class="elimina">Delete</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
				</div>
			</div>
		</div>
		</main>
	</div>
</body>
</html>