<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
/* Full-width input fields */

</style>
	<title>Login</title>
	<link href="css/stylelogin.css" rel="stylesheet">
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
</head>
	
<body style="background-color: #b8f2e5d1">
	<div class="loginBox">

		<img  class="user" src="images/user.jpg">
		
		<h3>Accedi qui</h3>
		<form action="/PSMSProject/UserController?op=menu" method="post">
		
			<div class="inputBox">
				<span><i class="fa fa-user" aria-hidden="true"></i></span>
				<input type="text" name="username" placeholder="Username" autocomplete="off" required>
			</div>
			
			<div class="inputBox">
				<span><i class="fa fa-lock" aria-hidden="true"></i></span>
				<input type="password" name="password" placeholder="Password" autocomplete="off" required>
			</div>
			<input type="submit" name="submit" value="Login">
		</form> 
		<form action="/PSMSProject/UserController?op=showSignin" method="post" style="margin-top: -40px;">
		<input type="submit" style="background-color: rgb(233, 76, 39);" name="submit" value="Sign in">
		</form>
	</div>
</body>
</html>