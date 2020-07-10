<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head><title>List</title></head>
<body>
	<!-- Always shows a header, even in smaller screens. -->
	<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
		<%@include file="menu.jsp"%>
		
		<main class="md1-layout_content">
		<div class="page-content">
			<div class="mdl-grid center-items">
				<div class="mdl-cell mdl-cell--4-col">
					<div>
						<table
							class="mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp">
							<thead>
								<tr>
									<th class="mdl-data-table_cell--non-numeric"></th>
									<th>Welcome user</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="count" value="0" scope="page" />
								<c:forEach var="user" items="${listLogin}">
									<c:set var="count" value="${count + 1}" scope="page" />
									<tr>
										<td class="mdl-data-table_cell--non-numeric"><c:out value="${count}" /></td>
										<td><c:out value="${user.name}" /></td>
										<td><c:out value="${user.surname}" /></td>
										<td><a href="/PSMSProject/UserController?op=edit&id=<c:out value='${user.id}' />">Edit</a>
											&nbsp;&nbsp;&nbsp;&nbsp;
											<a href="/PSMSProject/UserController?op=delete&id=<c:out value='${user.id}' />">Delete</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		</main>
	</div>
</body>
</html>