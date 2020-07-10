<html>
<head>
	<meta charset="utf-8">
	<title>Sign In</title>
	
	<link href="css/sign.css" rel="stylesheet">
	<link href="css/view.css" rel="stylesheet">

	
	<script>
	
		function avviso(){
			alert("registrazione completata");
		}
		
	</script>
</head>
	
<body style="background-color: #b8f2e5d1">

<a href="index.jsp" class="indietro">&laquo; Indietro</a>

		<div class="loginBox">
		
<form name="myForm" action="/PSMSProject/UserController?op=signInsert" method="post" onsubmit="return (validateForm() && avviso())">

	<img  class="user" src="images/user.jpg">
		
			<h3>Sign in</h3>
			
			<div class="inputBox">
					<input type="text" name="name" id="name" placeholder="Name" required>
				</div>
				
				<div class="inputBox">
					<input type="text" name="surname" id="surname" placeholder="Surname" required>
				</div>
				
				<div class="inputBox">
					<input type="date" name="date"  id="date" placeholder="Date" required>
				</div>
				
				<div class="inputBox">
					<input type="number" name="age" id="age" placeholder="Age" required>
				</div>
				
				<label for="type">Choose a Type</label> <select name="type"
										id="type">
											<option value="CHILD">CHILD</option>
											<option value="OWNER">OWNER</option>
											<option value="SPOUSE">SPOUSE</option>
									</select>
				
				<div class="inputBox">
					<input type="text" name="username" id="username" placeholder="Username" required>
				</div>
				
				<div class="inputBox">
					<input type="password" name="password" id="password_1"  placeholder="Password" required>
				</div>
			<div class="inputBox">
					<input type="password" name="password" id="password_2"  placeholder="Password repeat" required>
				</div>
				
					<input type="submit" name="submit" value="Sign in">
			</form> 
			
			
</div>
		<script type="text/javascript"> 
	function validateForm() {
		var p1 = document.forms["myForm"]["password_1"].value;
		var p2 = document.forms["myForm"]["password_2"].value;

		if (p1 != p2) {
			alert("Le password non corrispondono");
			return false;
		}else{
			alert("registrazione completata");
		}
		
	}
</script>
	
</body>
</html>