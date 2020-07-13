<%@include file="head.jsp"%>
<%@page import="java.util.Calendar"%>
<%@ page import="java.text.*,java.util.*" session="true"%>
<header class="mdl-layout__header">

	<div class="mdl-layout__header-row">
		<span class="mdl-layout-title"> Personal </span>

		<div class="mdl-layout-spacer"></div>
		<%
			java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm");
		%>
		<h5><%=df.format(new java.util.Date())%>
		</h5>

		<nav class="mdl-navigation mdl-layout--large-screen-only">
			<a class="mdl-navigation__link"
				href="/PSMSProject/StuffController?op=new" id="new">Add stuff</a> <a
				class="mdl-navigation__link"
				href="/PSMSProject/StuffController?op=list" id="list">List stuff</a>
			<a class="mdl-navigation__link"
				href="/PSMSProject/UserController?op=new" id="new">Add user</a> <a
				class="mdl-navigation__link"
				href="/PSMSProject/UserController?op=list" id="list">List user</a> 
				<a
				class="mdl-navigation__link"
				href="/PSMSProject/UserController?op=prova" id="list">prova</a> 

		</nav>
	</div>
</header>
<div class="mdl-layout__drawer">
	<span class="mdl-layout-title">PSMS</span>
	<nav class="mdl-navigation">
		<a class="mdl-navigation__link"
			href="/PSMSProject/StuffController?op=new" id="new">Add stuff</a> <a
			class="mdl-navigation__link"
			href="/PSMSProject/StuffController?op=list" id="list">List all
			stuff</a> <a class="mdl-navigation__link"
			href="/PSMSProject/UserController?op=new" id="new">Add user</a> <a
			class="mdl-navigation__link"
			href="/PSMSProject/UserController?op=list" id="list">List user</a> <a
			class="mdl-navigation__link"
			href="/PSMSProject/UserController?op=login" id="login">Login</a>

	</nav>
</div>
