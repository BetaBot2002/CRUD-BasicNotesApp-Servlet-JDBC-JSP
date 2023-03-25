<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<style>
	*{
		margin:0;
		padding:0;
		box-sizing:border-box;
		font-family:arial;
	}
	
	body{
		min-height:100vh;
		background:rgb(57, 72, 72);
		display:flex;
		align-items:center;
		justify-content:center;
	}
	
	.container{
		display:flex;
		flex-direction:column;
		align-items:center;
		justify-content:space-between;
		gap:10px;
		height:max-content;
		width:20vw;
		border-radius:10px;
		background:rgb(119, 149, 148);
		padding:15px;
	}
	
	.container > form{
		height:100%;
		display:flex;
		flex-direction:column;
		width:100%;
	}
	
	.container > form >input{
		display:flex;
		flex-direction:column;
		align-items:center;
		width:100%;
		height:5vh;
		padding:15px;
		font-size:16px;
		border-radius:5px;
		outline:none;
		border:none;
		background:rgb(220, 213, 199);
		margin-bottom:10px;
	}
	
	.container > form > button{
		height:5vh;
		padding:5px;
		font-size:18px;
		color:aliceblue;
		font-weight:700;
		background:rgb(35, 39, 43);
		outline:none;
		border:none;
		border-radius:10px;
	}
	
	.alertmsg{
		position:absolute;
		top:12px;
		right:12px;
		z-index:50;
		border-radius:5px;
		display:flex;
		align-items:center;
		background-color: rgba(239, 68, 68,0.8);
		color:black;
		font-weight:900;
		font-size:14px;
		padding:12px 16px 12px 16px;
	}
	
	.alertmsg > svg{
		fill: currentColor;
		width:16px;
		height:16px;
		margin-right:8px;
	}
	
	.hidden{
		display:none
	}
	
</style>
</head>
<body>
<%String status=(String) request.getParameter("status"); %>
<%if(status!=null){ %>
<div class="alertmsg " role="alert">
      <svg class="" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20"><path d="M12.432 0c1.34 0 2.01.912 2.01 1.957 0 1.305-1.164 2.512-2.679 2.512-1.269 0-2.009-.75-1.974-1.99C9.789 1.436 10.67 0 12.432 0zM8.309 20c-1.058 0-1.833-.652-1.093-3.524l1.214-5.092c.211-.814.246-1.141 0-1.141-.317 0-1.689.562-2.502 1.117l-.528-.88c2.572-2.186 5.531-3.467 6.801-3.467 1.057 0 1.233 1.273.705 3.23l-1.391 5.352c-.246.945-.141 1.271.106 1.271.317 0 1.357-.392 2.379-1.207l.6.814C12.098 19.02 9.365 20 8.309 20z"/></svg>

			<p><%=status %></p>
		
</div>
<%} %>
<div class="container">
	<h2>Register</h2>
	<form action="#" method="post">
		<input name="name" type="text" placeholder="Enter your name" required>
		<input name="username" type="text" placeholder="Enter your username or email" required>
		<input name="password" type="password" placeholder="Enter your password" required>
		<button>Register</button>
	</form>
	<h4>Already have an account? <a href="login">Log In</a></h4>
</div>
<script>
	setTimeout(()=>{
	    document.querySelector('.alertmsg').classList.add('hidden')
	},3000)
</script>
</body>
</html>